package org.eclipse.jem.internal.beaninfo.adapters;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfoNature.java,v $
 *  $Revision: 1.5 $  $Date: 2004/02/24 19:33:46 $ 
 */

import java.io.*;
import java.text.MessageFormat;
import java.util.*;

import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.apache.xml.serialize.*;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.VMRunnerConfiguration;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import com.ibm.wtp.emf.workbench.ResourceHandler;

import org.eclipse.jem.internal.java.adapters.JavaXMIFactoryImpl;
import org.eclipse.jem.internal.java.beaninfo.IIntrospectionAdapter;
import org.eclipse.jem.internal.java.init.JavaInit;
import org.eclipse.jem.internal.plugin.JavaEMFNature;
import org.eclipse.jem.internal.proxy.core.*;

/**
 * The beaninfo nature. It is created for a project and holds the
 * necessary info for beaninfo to be performed on a project.
 */

public class BeaninfoNature implements IProjectNature {

	public static final String NATURE_ID = "org.eclipse.jem.beaninfo.BeanInfoNature"; //$NON-NLS-1$
	public static final String P_BEANINFO_SEARCH_PATH = ".beaninfoConfig"; //$NON-NLS-1$
	// Persistent key

	private ResourceTracker resourceTracker;
	// This class listens for changes to the beaninfo paths file, and if changed it marks all stale
	// so the next time anything is needed it will recycle the vm. It will also listen about to close or
	// about to delete of the project so that it can cleanup.
	private class ResourceTracker implements IResourceChangeListener{
		public void resourceChanged(IResourceChangeEvent e) {
			// About to close or delete the project and it is ours, so we need to cleanup.
			if (e.getType() == IResourceChangeEvent.PRE_CLOSE || e.getType() == IResourceChangeEvent.PRE_DELETE) {
				// Performance: It has been noted that dres.equals(...) can be slow with the number
				// of visits done. Checking just the last segment (getName()) first before checking
				// the entire resource provides faster testing. If the last segment is not equal,
				// then the entire resource could not be equal.
				IResource eventResource = e.getResource();
				if (eventResource.getName().equals(getProject().getName()) && eventResource.equals(getProject())) {
					cleanup(false);
					return;
				}
			}
			// Note: the BeaninfoModelSynchronizer takes care of both .classpath and .beaninfoconfig changes
			// in this project and any required projects.
		}
	}

	private ProxyFactoryRegistry.IRegistryListener registryListener = new ProxyFactoryRegistry.IRegistryListener() {
		/**
		 * @see org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry.IRegistryListener#registryTerminated(ProxyFactoryRegistry)
		 */
		public void registryTerminated(ProxyFactoryRegistry registry) {
			markAllStale();
		};
	};

	/**
	 * Get the runtime nature for the project, create it if necessary.
	 */
	public static BeaninfoNature getRuntime(IProject project) throws CoreException {
		if (project.hasNature(NATURE_ID))
			return (BeaninfoNature) project.getNature(NATURE_ID);
		else
			return createRuntime(project);
	}

	/**
	 * Test if this is a valid project for a Beaninfo Nature. It must be
	 * a JavaProject.
	 */
	public static boolean isValidProject(IProject project) {
		try {
			return project.hasNature(JavaCore.NATURE_ID);
		} catch (CoreException e) {
			return false;
		}
	}

	/**
	 * Create the runtime.
	 */
	private static BeaninfoNature createRuntime(IProject project) throws CoreException {
		if (!isValidProject(project))
			throw new CoreException(
				new Status(
					IStatus.ERROR,
					BeaninfoPlugin.PI_BEANINFO,
					0,
					MessageFormat.format(
						BeaninfoPlugin.getPlugin().getDescriptor().getResourceString(BeaninfoProperties.INTROSPECTFAILED),
						new Object[] { project.getName(), "Invalid project"}),
					null));

		addNatureToProject(project, NATURE_ID);
		return (BeaninfoNature) project.getNature(NATURE_ID);
	}

	private static void addNatureToProject(IProject proj, String natureId) throws CoreException {
		IProjectDescription description = proj.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length] = natureId;
		description.setNatureIds(newNatures);
		proj.setDescription(description, null);
	}

	private IProject fProject;
	protected ProxyFactoryRegistry fRegistry;
	protected ResourceSet javaRSet;
	protected BeaninfoModelSynchronizer fSynchronizer;
	protected static BeaninfoJavaReflectionKeyExtension fReflectionKeyExtension;

	/** 
	 * Configures the project with this nature.
	 * This is called by <code>IProject.getNature</code> and should not
	 * be called directly by clients.
	 * The nature extension id is added to the list of natures on the project by
	 * <code>IProject.getNature</code>, and need not be added here.
	 *
	 * @exception CoreException if this method fails.
	 */
	public void configure() throws CoreException {
	}

	/** 
	 * Removes this nature from the project, performing any required deconfiguration.
	 * This is called by <code>IProject.removeNature</code> and should not
	 * be called directly by clients.
	 * The nature id is removed from the list of natures on the project by
	 * <code>IProject.removeNature</code>, and need not be removed here.
	 *
	 * @exception CoreException if this method fails. 
	 */
	public void deconfigure() throws CoreException {
		removeSharedProperty(P_BEANINFO_SEARCH_PATH, null);
		cleanup(true);
	}

	/**
	 * Return a new ResourceSet that is linked correctly to this Beaninfo Nature.
	 * This links up a ResourceSet so that it will work correctly with this nature.
	 * It makes sure that going through the ResourceSet that any "java:/..."
	 * classes can be found and it makes sure that any new classes are placed into the
	 * nature's resource set and not resource set doing the calling.
	 * 
	 * The resourceset will have a context assigned to it.
	 * 
	 * This should be used any time a resource set is needed that is not the
	 * project wide resource set associated with beaninfos, but will reference
	 * Java Model classes or instantiate.
	 */
	public ResourceSet newResourceSet() {
		SpecialResourceSet rset = new SpecialResourceSet();
		rset.add(new ResourceHandler() {
			public EObject getEObjectFailed(ResourceSet originatingResourceSet, URI uri, boolean loadOnDemand) {
				return null; // We don't have any special things we can do in this case.
			}

			public Resource getResource(ResourceSet originatingResourceSet, URI uri) {
				// Always try to get it out of the nature's resource set because it may of been loaded there either as 
				// the "java:..." type or it could of been an override extra file (such as an override EMF package, for
				// example jcf has a side package containing the definition of the new attribute type. That file
				// will also be loaded into this resourceset. So to find it we need to go in here and try.
				//
				// However, if not found we won't go and try to load the resource. That could load in the wrong place.
				// TODO Because of a bug in XMLHandler.getPackageFromURI(), it doesn't use getResource(...,true) and it tries instead
				// to use uri inputstream to load the package when not found. This bypasses our special create resource and so
				// packages are not automatically created. So we need to do load on demand here instead if it is a java protocol.
				return getResourceSet().getResource(uri, JavaXMIFactoryImpl.SCHEME.equals(uri.scheme()));
			}

			public Resource createResource(ResourceSet originatingResourceSet, URI uri) {
				// This is the one. It has got here because it couldn't find a resource already loaded.
				// If it is a "java:/..." protocol resource, then we want to make sure it is loaded at the BeaninfoNature context
				// instead of the lower one.
				if (JavaXMIFactoryImpl.SCHEME.equals(uri.scheme()))
					return getResourceSet().getResource(uri, true);
				else
					return null;
			}
		});
		return rset;
	}
	/**
	 * Clean up, this means either the project is being closed, deleted, or it means that
	 * the nature is being removed from the project. Either way that means to
	 * terminate the VM and remove what we added to the context if the flag says clear it.
	 */
	protected void cleanup(boolean clearResults) {
		getProject().getWorkspace().removeResourceChangeListener(resourceTracker);
		resourceTracker = null;
		fSynchronizer.stopSynchronizer(clearResults);
		Init.cleanup(javaRSet, clearResults);
		if (fRegistry != null)
			fRegistry.terminateRegistry();

		javaRSet = null;
		fRegistry = null;
		fProject = null;
		fSynchronizer = null;
	}

	/** 
	 * Returns the project to which this project nature applies.
	 *
	 * @return the project handle
	 */
	public IProject getProject() {
		return fProject;
	}

	/**
	 * Sets the project to which this nature applies.
	 * Used when instantiating this project nature runtime.
	 * This is called by <code>IProject.addNature</code>
	 * and should not be called directly by clients.
	 *
	 * @param project the project to which this nature applies
	 */
	public void setProject(IProject project) {
		fProject = project;

		try {
			// The nature has been started for this project, need to setup the introspection process now.
			JavaEMFNature javaNature = JavaEMFNature.createRuntime(fProject);
			JavaInit.init();
			if (fReflectionKeyExtension == null) {
				// Register the reflection key extension.
				fReflectionKeyExtension = new BeaninfoJavaReflectionKeyExtension();
				JavaXMIFactoryImpl.INSTANCE.registerReflectionKeyExtension(fReflectionKeyExtension);
			}

			javaRSet = javaNature.getResourceSet();
			Init.initialize(javaRSet, new IBeaninfoSupplier() {
				public ProxyFactoryRegistry getRegistry() {
					return BeaninfoNature.this.getRegistry();
				}

				public boolean isRegistryCreated() {
					return BeaninfoNature.this.isRegistryCreated();
				}
				
				public void closeRegistry() {
					BeaninfoNature.this.closeRegistry();
				}
			});
			fSynchronizer =
				new BeaninfoModelSynchronizer(
					(BeaninfoAdapterFactory) EcoreUtil.getAdapterFactory(javaRSet.getAdapterFactories(), IIntrospectionAdapter.ADAPTER_KEY),
					JavaCore.create(javaNature.getProject()));
			resourceTracker = new ResourceTracker();
			project.getWorkspace().addResourceChangeListener(resourceTracker);
		} catch (CoreException e) {
			BeaninfoPlugin.getPlugin().getLogger().log(e.getStatus());
		}
	}

	/**
	 * Get the registry, creating it if necessary.
	 */
	public ProxyFactoryRegistry getRegistry() {
		return getRegistry(new NullProgressMonitor());
	}

	/**
	 * Close the registry. It needs to be recycled because a class has changed
	 * and now the new class needs to be accessed.
	 */
	protected void closeRegistry() {
		ProxyFactoryRegistry reg = null;
		synchronized (this) {
			reg = fRegistry;
			fRegistry = null;
		}
		if (reg != null) {
			reg.removeRegistryListener(registryListener);
			reg.terminateRegistry();
		}
	}

	public synchronized ProxyFactoryRegistry getRegistry(IProgressMonitor pm) {
		if (fRegistry == null) {
			try {
					fRegistry = ProxyPlugin.getPlugin().startImplementation(fProject, "Beaninfo", //$NON-NLS-1$
	new IConfigurationContributor[] { getConfigurationContributor()}, pm);
				fRegistry.addRegistryListener(registryListener);
			} catch (CoreException e) {
				BeaninfoPlugin.getPlugin().getLogger().log(e.getStatus());
			}
		}
		return fRegistry;
	}
	
	public synchronized boolean isRegistryCreated() {
		return fRegistry != null;
	}
	
	/**
	 * Check to see if the nature is still valid. If the project has been
	 * renamed, the nature is still around, but the project has been closed.
	 * So the nature is now invalid.
	 * 
	 * @return Is this a valid nature. I.e. is the project still open.
	 */
	public boolean isValidNature() {
		return fProject != null;
	}

	/**
	 * Set the search path onto the registry.
	 */
	protected void setProxySearchPath(ProxyFactoryRegistry registry, List searchPaths) {
		if (searchPaths != null) {
			String[] stringSearchPath = (String[]) searchPaths.toArray(new String[searchPaths.size()]);
			Utilities.setBeanInfoSearchPath(registry, stringSearchPath);
		} else
			Utilities.setBeanInfoSearchPath(registry, null);
	}

	/*
	 * Get the search path in the old format.
	 */
	private BeaninfoSearchPathEntry[] getOldFormatSearchPath(Element root) {
		NodeList children = root.getChildNodes();
		int childrenLength = children.getLength();
		ArrayList childrenList = new ArrayList(childrenLength);
		for (int i = 0; i < childrenLength; i++) {
			Node child = children.item(i);
			BeaninfoSearchPathEntry bentry = BeaninfoSearchPathEntry.readEntry(child);
			if (bentry != null)
				childrenList.add(bentry);
		}
		return (BeaninfoSearchPathEntry[]) childrenList.toArray(new BeaninfoSearchPathEntry[childrenList.size()]);
	}

	/*
	 * Convert the old format to new format.
	 */
	private BeaninfosDoc convertOldFormatSearchPath(Element root) {
		BeaninfoSearchPathEntry[] entries = getOldFormatSearchPath(root);

		try {
			IJavaProject jp = JavaCore.create(getProject());
			IClasspathEntry[] cpEntries = jp.getRawClasspath();
			HashMap resolvedEntries = new HashMap(cpEntries.length);
			for (int i = 0; i < cpEntries.length; i++) {
				IClasspathEntry resolved = JavaCore.getResolvedClasspathEntry(cpEntries[i]);
				if (resolved != null)
					resolvedEntries.put(resolved.getPath(), new Integer(i));
			}

			List newentries = new ArrayList(entries.length);
			for (int i = 0; i < entries.length; i++) {
				IPath pkgPath = new Path(entries[i].getPackageName().replace('.', '/'));
				try {
					IPackageFragment frag = (IPackageFragment) jp.findElement(pkgPath); // Find the first match
					if (frag != null) {
						IPackageFragmentRoot froot = (IPackageFragmentRoot) frag.getParent();
						Integer index = (Integer) resolvedEntries.get(froot.getPath());
						if (index != null) {
							IClasspathEntry cpe = cpEntries[index.intValue()];
							newentries.add(new SearchpathEntry(cpe.getEntryKind(), cpe.getPath(), frag.getElementName()));
						}
					}
				} catch (ClassCastException e) {
					// It didn't find a IPackageFragment, it should of, so skip this entry.
				}
			}
			return new BeaninfosDoc((IBeaninfosDocEntry[]) newentries.toArray(new IBeaninfosDocEntry[newentries.size()]));
		} catch (JavaModelException e) {
		}
		return null;
	}

	private static final String ENCODING = "UTF-8"; //$NON-NLS-1$
	private static final String sSearchPathElementName = "searchPath"; //$NON-NLS-1$
	// Old format root element name (WSAD 4.0.0)
	static final String sBeaninfos = "beaninfos"; // Root element name //$NON-NLS-1$
	/**
	 * Get the persistent search path. The object returned is a copy of the
	 * list, and it can be modified, but it won't be reflected back into the
	 * nature.
	 */
	public BeaninfosDoc getSearchPath() {
		BeaninfosDoc bdoc = null;
		try {
			InputStream property = getSharedProperty(P_BEANINFO_SEARCH_PATH);
			if (property != null) {
				try {
					// Need to reconstruct from the XML format.
					DocumentBuilderFactoryImpl bldrFactory = new DocumentBuilderFactoryImpl();
					Document doc = bldrFactory.newDocumentBuilder().parse(new InputSource(new InputStreamReader(property, ENCODING)));
					Element root = doc.getDocumentElement();
					if (root != null && root.getNodeName().equalsIgnoreCase(sSearchPathElementName)) {
						// Old format. Need to convert to new format.
						bdoc = convertOldFormatSearchPath(root);
						setSearchPath(bdoc); // Now put out the converted format.
					} else if (root != null && root.getNodeName().equalsIgnoreCase(sBeaninfos)) {
						// New format
						bdoc = BeaninfosDoc.readEntry(new DOMReader(), root, getProject());
					}
				} finally {
					try {
						property.close();
					} catch (IOException e) {
					}
				}
			}
		} catch (CoreException e) {
			BeaninfoPlugin.getPlugin().getLogger().log(e.getStatus());
		} catch (Exception e) {
			BeaninfoPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, BeaninfoPlugin.PI_BEANINFO, 0, "", e));
		}
		return bdoc;
	}

	/**
	 * Set the persistent search path. No progress monitor.
	 */
	public void setSearchPath(BeaninfosDoc searchPath) throws CoreException {
		setSearchPath(searchPath, null);
	}

	/**
	 * Set the persistent search path with a progress monitor
	 */
	public void setSearchPath(BeaninfosDoc searchPath, IProgressMonitor monitor) throws CoreException {
		String property = null;
		if (searchPath != null && searchPath.getSearchpath().length > 0) {
			try {
				DocumentBuilderFactoryImpl bldrFactory = new DocumentBuilderFactoryImpl();
				Document doc = bldrFactory.newDocumentBuilder().newDocument();
				Element root = doc.createElement(sBeaninfos); // Create Root Element
				IBeaninfosDocEntry[] entries = searchPath.getSearchpath();
				for (int i = 0; i < entries.length; i++)
					root.appendChild(entries[i].writeEntry(doc, getProject())); // Add to the search path
				doc.appendChild(root); // Add Root to Document
				OutputFormat format = new OutputFormat(doc); //Serialize DOM
				format.setIndenting(true);
				StringWriter strWriter = new StringWriter();
				Serializer serial = SerializerFactory.getSerializerFactory(format.getMethod()).makeSerializer(strWriter, format);
				serial.asDOMSerializer().serialize(doc.getDocumentElement());
				property = strWriter.toString();
			} catch (Exception e) {
			}
		}

		if (property != null) {
			// If it hasn't changed, don't write it back out. This is so that if the file hasn't
			// been checked out and it is the same, we don't want to bother the user. This is because
			// we don't know if the user had simply browsed the search path or had actually changed and
			// set it back to what it was. In either of those cases it would be a bother to ask the
			// user to checkout the file.
			InputStream is = getSharedProperty(P_BEANINFO_SEARCH_PATH);
			if (is != null) {
				try {
					try {
						InputStreamReader reader = new InputStreamReader(is, ENCODING);
						char[] chars = new char[1000];
						StringBuffer oldProperty = new StringBuffer(1000);
						int read = reader.read(chars);
						while (read != -1) {
							oldProperty.append(chars, 0, read);
							read = reader.read(chars);
						}
						if (oldProperty.toString().equals(property))
							return;
					} catch (IOException e) {
					} // Didn't change.
				} finally {
					try {
						is.close();
					} catch (IOException e) {
					}
				}
			}
			setSharedProperty(P_BEANINFO_SEARCH_PATH, property, monitor);
		} else
			removeSharedProperty(P_BEANINFO_SEARCH_PATH, monitor);
	}

	/**
	 * Return the resource set for all java packages in this nature.
	 */
	public ResourceSet getResourceSet() {
		return javaRSet;
	}

	protected void markAllStale() {
		// Mark all stale so that the registry will be recycled.
		if (fRegistry != null) {
			// We have a registry running, we need to indicate recycle is needed.
			fSynchronizer.getAdapterFactory().markAllStale();
			// Mark all stale. Next time we need anything it will be recycled.
		}
	}

	/**
	 * Compute the file name to use for a given shared property
	 */
	protected String computeSharedPropertyFileName(QualifiedName qName) {
		return qName.getLocalName();
	}

	/**
	 * Retrieve a shared property on a project. If the property is not defined, answers null.
	 * Note that it is orthogonal to IResource persistent properties, and client code has to decide
	 * which form of storage to use appropriately. Shared properties produce real resource files which
	 * can be shared through a VCM onto a server. Persistent properties are not shareable.
	 *
	 */
	protected InputStream getSharedProperty(String propertyFileName) throws CoreException {
		IFile rscFile = getProject().getFile(propertyFileName);
		if (rscFile.exists())
			return rscFile.getContents(true);
		else
			return null;
	}

	/**
	 * Record a shared persistent property onto a project.
	 * Note that it is orthogonal to IResource persistent properties, and client code has to decide
	 * which form of storage to use appropriately. Shared properties produce real resource files which
	 * can be shared through a VCM onto a server. Persistent properties are not shareable.
	 * 
	 * shared properties end up in resource files, and thus cannot be modified during
	 * delta notifications (a CoreException would then be thrown).
	 * 
	 */
	protected void setSharedProperty(String propertyName, String value, IProgressMonitor monitor) throws CoreException {

		try {
			IFile rscFile = getProject().getFile(propertyName);
			InputStream input = new ByteArrayInputStream(value.getBytes(ENCODING));
			// update the resource content
			if (rscFile.exists()) {
				rscFile.setContents(input, true, false, null);
			} else {
				rscFile.create(input, true, monitor);
			}
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * Remove a shared persistent property onto a project.
	 * Note that it is orthogonal to IResource persistent properties, and client code has to decide
	 * which form of storage to use appropriately. Shared properties produce real resource files which
	 * can be shared through a VCM onto a server. Persistent properties are not shareable.
	 * 
	 * shared properties end up in resource files, and thus cannot be modified during
	 * delta notifications (a CoreException would then be thrown).
	 * 
	 */
	protected void removeSharedProperty(String propertyName, IProgressMonitor monitor) throws CoreException {

		IFile rscFile = getProject().getFile(propertyName);
		rscFile.delete(true, true, monitor);
	}

	/**
	 * Return a configuration contributor that sets up a vm to allow
	 * introspection. This will make sure the appropriate paths
	 * are in the classpath to allow access to the beaninfos, and
	 * it will setup the beaninfo search path for this project.
	 */
	public IConfigurationContributor getConfigurationContributor() {
		return new ConfigurationContributor(getSearchPath());
	}

	private static final IPath JRE_LIB_VARIABLE_PATH = new Path(JavaRuntime.JRELIB_VARIABLE);	// TODO Remove when we handle containers
	private class ConfigurationContributor implements IConfigurationContributor {

		BeaninfosDoc doc;
		List computedSearchPath = new ArrayList();
		// Compute the search path as we compute the classpaths. This is saved because it will be used in a separate call.
		HashSet visitedVariablepaths; // Visited registered variable paths, so we don't visit them again.
		List variableContributors = new ArrayList(0); // Variable contributors that were found.

		public ConfigurationContributor(BeaninfosDoc doc) {
			this.doc = doc;
		}

		/**
		 * Method to update any class paths with any
		 * paths that need to be added to a VM. In this case, it is
		 * the proxyvm.jar that needs to be added. This jar contains
		 * the common code that is required by any VM for proxy
		 * support.
		 */
		public void contributeClasspaths(List classPaths, IClasspathContributionController controller) throws CoreException {
			// Need to find any additional beaninfo jars. They can be pointed to within this projects path,
			// or they can be found in pre-req'd project (assuming they are exported).
			HashSet visitedProjects = new HashSet();
			visitedVariablepaths = new HashSet();
			try {
				contributeClasspathsForProject(classPaths, controller, getProject(), visitedProjects, doc);
				// Add the beaninfovm.jar and any nls to the end of the classpath.
				controller.contributeClasspath(ProxyPlugin.getPlugin().urlLocalizeFromPluginDescriptorAndFragments(BeaninfoPlugin.getPlugin().getDescriptor(), "vm/beaninfovm.jar"), //$NON-NLS-1$
				classPaths, -1);
			} finally {
				visitedVariablepaths = null;
			}

			// Now turn the var elements into contributors.
			for (ListIterator itr = variableContributors.listIterator(); itr.hasNext();) {
				IConfigurationElement v = (IConfigurationElement) itr.next();
				IConfigurationContributor contrib = null;
				try {
					contrib = (IConfigurationContributor) v.createExecutableExtension(BeaninfoPlugin.PI_CONTRIBUTOR);
				} catch (ClassCastException e) {
					BeaninfoPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, BeaninfoPlugin.PI_BEANINFO, 0, "", e)); //$NON-NLS-1$
				}

				itr.set(contrib); // Set to what should be used, null is valid for not found.
				if (contrib != null)
					contrib.contributeClasspaths(classPaths, controller);
			}
		}

		private IClasspathEntry get(IClasspathEntry[] array, InternalCPEntry cpe) {
			for (int i = 0; i < array.length; i++) {
				if (cpe.equals(array[i]))
					return array[i];
			}

			return null;
		}

		/*
		 * Contribute classpaths for the specified project. If doc is passed in, then this is the top level and
		 * all should be added. If no doc, then this is pre-req'd project, and then we will handle exported entries only.
		 */
		protected void contributeClasspathsForProject(
			List classPaths,
			IClasspathContributionController controller,
			IProject project,
			HashSet visitedProjects,
			BeaninfosDoc doc)
			throws CoreException {
			if (visitedProjects.contains(project))
				return;
			visitedProjects.add(project);

			IJavaProject jProject = JavaCore.create(project);
			IClasspathEntry[] rawPath = jProject.getRawClasspath();

			// List of classpath entries for this project that have already been processed in the search path list.
			// This is so that at the end when we process the classpath to add in any implicit beaninfos/search paths,
			// we know these had been explicitly handled already.
			List contributedICPEs = new ArrayList();
			InternalCPEntry working = new InternalCPEntry(); // A working copy that we keep reusing.

			// Search path of this project
			IBeaninfosDocEntry[] entries = null;
			if (doc != null)
				entries = doc.getSearchpath();
			else {
				BeaninfosDoc adoc = BeaninfoNature.getRuntime(project).getSearchPath();
				entries = adoc != null ? adoc.getSearchpath() : new IBeaninfosDocEntry[0];
			}

			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

			for (int i = 0; i < entries.length; i++) {
				IBeaninfosDocEntry entry = entries[i];
				if (entry instanceof BeaninfoEntry) {
					BeaninfoEntry be = (BeaninfoEntry) entry;
					if (doc != null || be.isExported()) {
						// First project or this is an exported beaninfo
						Object cp = be.getClasspath();
						if (cp instanceof IProject)
							controller.contributeProject((IProject) cp, classPaths, -1);
						else if (cp instanceof String)
							controller.contributeClasspath((String) cp, classPaths, -1);
						else if (cp instanceof String[])
							controller.contributeClasspath((String[]) cp, classPaths, -1);
						else
							continue; // It was an invalid entry, don't add in its searchpaths.

						// Now add in the package names.
						SearchpathEntry[] sees = be.getSearchPaths();
						for (int j = 0; j < sees.length; j++) {
							SearchpathEntry searchpathEntry = sees[j];
							if (!computedSearchPath.contains(searchpathEntry.getPackage()))
								computedSearchPath.add(searchpathEntry.getPackage());
						}
					}
				} else {
					// Just a search path entry. There is no beaninfo jar to pick up.
					SearchpathEntry se = (SearchpathEntry) entry;
					working.setEntry(se.getKind(), se.getPath());
					int cndx = contributedICPEs.indexOf(working);
					if (cndx == -1) {
						InternalCPEntry icpe = new InternalCPEntry(working.getKind(), working.getPath());
						contributedICPEs.add(icpe); // Keep a record that this entry has been used.
						if (doc == null) {
							// This is the first time we've found this entry and we are in a nested project, find the raw classpath entry to see
							// if this entry is exported. Only do it if exported. (Note: exported is only used on non-source. Source are always exported).
							IClasspathEntry cpe = get(rawPath, icpe);
							if (cpe == null || (cpe.getEntryKind() != IClasspathEntry.CPE_SOURCE && !cpe.isExported())) {
								icpe.setIsExported(false); // Mark it as exported so if found again it won't be used.
								continue; // Not exist or not exported, so we don't want it here either.
							}
						}
					} else {
						InternalCPEntry icpe = (InternalCPEntry) contributedICPEs.get(cndx);
						if (doc == null && !icpe.isExported())
							continue; // We've already determined it is not exported, so don't use it.
					}

					String pkg = se.getPackage();
					if (pkg != null) {
						// Explicit search path
						if (!computedSearchPath.contains(pkg))
							computedSearchPath.add(pkg);
					} else {
						// Process if this is an implicit search path kind of entry.
						// I.e. It is just kind/path and no packagename. This means
						// find the implicit searchpaths for this entry and put them
						// in order here now. This can only be used on implicit kind
						// of classpath entries. Any others don't have any implicit search paths
						// so they are processed, just ignored.
						processImplicitSearchPath(classPaths, controller, visitedProjects, root, se.getKind(), se.getPath());
					}

				}
			}

			// Now we need to go through our raw classpath to handle any not already handled.
			// We only handle implicit search path from the project or registered variable.
			for (int i = 0; i < rawPath.length; i++) {
				IClasspathEntry entry = rawPath[i];
				working.setEntry(entry);
				if (contributedICPEs.contains(working))
					continue; // We've already handled it above.
				processImplicitSearchPath(classPaths, controller, visitedProjects, root, entry.getEntryKind(), entry.getPath());
			}

		}

		protected void processImplicitSearchPath(
			List classPaths,
			IClasspathContributionController controller,
			HashSet visitedProjects,
			IWorkspaceRoot root,
			int kind,
			IPath path)
			throws CoreException {
			// Use the implicit search path from the project or registered variable.
			// For now, only projects.
			if (kind == IClasspathEntry.CPE_PROJECT) {
				IProject reqProject = (IProject) root.findMember(path.lastSegment());
				// Project entries only have one segment.
				if (reqProject != null && reqProject.isOpen())
					contributeClasspathsForProject(classPaths, controller, reqProject, visitedProjects, null);
			} else if (kind == IClasspathEntry.CPE_VARIABLE) {
				// We only handle variables as being registered. 
				if (path == null || path.segmentCount() == 0)
					return; // No path information to process.
				// First we handle the generic kind of for just the variable itself (which is segment 0).
				IPath varpath = path.segmentCount() == 1 ? path : path.removeLastSegments(path.segmentCount() - 1);
				if (!visitedVariablepaths.contains(varpath)) {
					visitedVariablepaths.add(varpath);
					BeaninfoRegistration[] registrations = BeaninfoPlugin.getPlugin().getRegistrations(varpath);
					if (registrations != null)
						processBeaninfoRegistrations(registrations, classPaths, controller);
				}

				// Now process for the specific path (which would be variable followed by some subpaths).
				if (path.segmentCount() > 1 && !visitedVariablepaths.contains(path)) {
					visitedVariablepaths.add(path);
					BeaninfoRegistration[] registrations = BeaninfoPlugin.getPlugin().getRegistrations(path);
					if (registrations != null)
						processBeaninfoRegistrations(registrations, classPaths, controller);
				}
			} else if (kind == IClasspathEntry.CPE_CONTAINER) {
				// KLUDGE TODO For now we can't really handle containers, we will simply hard-code and only handle JRE container to JRE_LIB stuff.
				if (path == null || path.segmentCount() == 0)
					return; // No path information to process.
				if (path.segment(0).equals(JavaRuntime.JRE_CONTAINER)) {
					if (!visitedVariablepaths.contains(JRE_LIB_VARIABLE_PATH)) {
						visitedVariablepaths.add(JRE_LIB_VARIABLE_PATH);
						BeaninfoRegistration[] registrations = BeaninfoPlugin.getPlugin().getRegistrations(JRE_LIB_VARIABLE_PATH);
						if (registrations != null)
							processBeaninfoRegistrations(registrations, classPaths, controller);							
					}
				}
			}
		}

		protected void processBeaninfoRegistrations(
			BeaninfoRegistration[] registrations,
			List classPaths,
			IClasspathContributionController controller)
			throws CoreException {
			for (int i = 0; i < registrations.length; i++)
				processBeaninfoRegistration(registrations[i], classPaths, controller);
		}

		protected void processBeaninfoRegistration(
			BeaninfoRegistration registration,
			List classPaths,
			IClasspathContributionController controller)
			throws CoreException {
			BeaninfosDoc doc = registration.getDoc();
			if (doc == null)
				return;

			IConfigurationElement varElement = registration.getVariableElement();
			if (varElement != null)
				variableContributors.add(varElement);

			IBeaninfosDocEntry[] entries = doc.getSearchpath();

			for (int i = 0; i < entries.length; i++) {
				IBeaninfosDocEntry entry = entries[i];
				if (entry instanceof BeaninfoEntry) {
					BeaninfoEntry be = (BeaninfoEntry) entry;
					Object cp = be.getClasspath();
					if (cp instanceof IProject)
						controller.contributeProject((IProject) cp, classPaths, -1);
					else if (cp instanceof String)
						controller.contributeClasspath((String) cp, classPaths, -1);
					else if (cp instanceof String[])
						controller.contributeClasspath((String[]) cp, classPaths, -1);
					else
						continue; // It was an invalid entry, don't add in its searchpaths.

					// Now add in the package names.
					SearchpathEntry[] sees = be.getSearchPaths();
					for (int j = 0; j < sees.length; j++) {
						SearchpathEntry searchpathEntry = sees[j];
						if (!computedSearchPath.contains(searchpathEntry.getPackage()))
							computedSearchPath.add(searchpathEntry.getPackage());
					}
				} else {
					// Just a search path entry. There is no beaninfo jar to pick up. The paths will be in the current classpath probably from the bean classes jar that this registration matches.
					// There should be no paths or kinds. It should only be packagename.

					String pkg = ((SearchpathEntry) entry).getPackage();
					if (pkg != null) {
						// Explicit search path
						if (!computedSearchPath.contains(pkg))
							computedSearchPath.add(pkg);
					}
				}
			}
		}

		public void contributeToConfiguration(VMRunnerConfiguration config) {
			for (int i = 0; i < variableContributors.size(); i++) {
				IConfigurationContributor contrib = (IConfigurationContributor) variableContributors.get(i);
				if (contrib != null)
					contrib.contributeToConfiguration(config);
			}
		}

		public void contributeToRegistry(ProxyFactoryRegistry registry) {
			setProxySearchPath(registry, computedSearchPath);
			for (int i = 0; i < variableContributors.size(); i++) {
				IConfigurationContributor contrib = (IConfigurationContributor) variableContributors.get(i);
				if (contrib != null)
					contrib.contributeToRegistry(registry);
			}
		}
	}

	/*
	 * An internal type CPEntry because an actual
	 * entry also tests attachments, but we are only interested
	 * in kind/path.
	 * 
	 * Note: This must not be used in a Hash... because its hashCode
	 * doesn't work for this. This is because there is no hashCode
	 * that we could compute that would allow IClasspathEntry's and
	 * InternalCPEntry's that are semantically equal to hash to the same value.
	 */
	private static class InternalCPEntry {
		int kind;
		IPath path;
		boolean isExported = true;

		public InternalCPEntry(int kind, IPath path) {
			setEntry(kind, path);
		}

		public InternalCPEntry() {
		}

		/*
		 * Set if this entry is exported or not. This is not involved in
		 * the equality test.
		 */
		public boolean isExported() {
			return isExported;
		}

		public void setIsExported(boolean isExported) {
			this.isExported = isExported;
		}

		public int getKind() {
			return kind;
		}

		public IPath getPath() {
			return path;
		}

		public boolean equals(Object o) {
			if (this == o)
				return true;

			if (o instanceof IClasspathEntry) {
				IClasspathEntry ce = (IClasspathEntry) o;
				return kind == ce.getEntryKind() && path.equals(ce.getPath());
			}

			if (o instanceof InternalCPEntry) {
				InternalCPEntry ice = (InternalCPEntry) o;
				return kind == ice.kind && path.equals(ice.path);
			}

			return false;
		}

		public void setEntry(IClasspathEntry entry) {
			setEntry(entry.getEntryKind(), entry.getPath());
		}

		public void setEntry(int kind, IPath path) {
			this.kind = kind;
			this.path = path;
		}

	}

}