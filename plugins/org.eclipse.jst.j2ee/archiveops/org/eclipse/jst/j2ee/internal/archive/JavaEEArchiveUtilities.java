/*******************************************************************************
 * Copyright (c) 2005, 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.util.IAnnotation;
import org.eclipse.jdt.core.util.IClassFileAttribute;
import org.eclipse.jdt.core.util.IClassFileReader;
import org.eclipse.jdt.core.util.IRuntimeVisibleAnnotationsAttribute;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentLoadAdapter;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.WebUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.archive.internal.ArchiveFactoryImpl;
import org.eclipse.jst.jee.archive.internal.ArchiveImpl;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.jst.jee.archive.internal.ZipFileArchiveLoadAdapterImpl;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class JavaEEArchiveUtilities extends ArchiveFactoryImpl {

	/**
	 * Default value = Boolean.TRUE Valid values = Boolean.TRUE or Boolean.FALSE
	 * 
	 * An ArchiveOption used to specify whether
	 * { @link #openArchive(ArchiveOptions)} should attempt to discriminate
	 * between different Java EE archive types. The default behavior is to
	 * always discriminate fully for all types except EJB 3.0 archives
	 * { @link #DISCRIMINATE_EJB_ANNOTATIONS}. In order to fully discriminate
	 * EJB 3.0 archives, it is necessary to set both this flag and
	 * { @link #DISCRIMINATE_EJB_ANNOTATIONS} to true.
	 */
	public static final String DISCRIMINATE_JAVA_EE = "DISCRIMINATE_EJB"; //$NON-NLS-1$

	/**
	 * Default value = Boolean.TRUE Valid values = Boolean.TRUE or Boolean.FALSE
	 * 
	 * An ArchiveOption used to specify whether
	 * { @link #openArchive(ArchiveOptions)} should attempt to fully discriminate
	 * a JAR file from an EJB JAR file. This option is only relevant if the
	 * { @link #DISCRIMINATE_JAVA_EE} option is also set to Boolean.TRUE. If both
	 * options are set to true then as a last resort all .class files byte codes
	 * will be analyzed for EJB annotations in order to discriminate whether the
	 * specified IArchive is an EJB 3.0 jar.
	 */
	public static final String DISCRIMINATE_EJB_ANNOTATIONS = "DISCRIMINATE_EJB_ANNOTATIONS"; //$NON-NLS-1$
	
	/**
	 * Default value = Boolean.TRUE Valid values = Boolean.TRUE or Boolean.FALSE
	 * 
	 * An ArchiveOption used to specify whether
	 * { @link #openArchive(ArchiveOptions)} should attempt to fully discriminate
	 * a JAR file from an Application Client JAR file. This option is only relevant 
	 * if the {@link #DISCRIMINATE_JAVA_EE} option is also set to Boolean.TRUE. If 
	 * both options are set to true then as a last resort the MANIFEST.MF
	 * will be analyzed for a Main-Class entry in order to discriminate whether the
	 * specified IArchive is an Application Client jar.
	 */
	public static final String DISCRIMINATE_MAIN_CLASS = "DISCRIMINATE_MAIN_CLASS"; //$NON-NLS-1$

	/**
	 * Default value = null
	 * 
	 * An ArchiveOption used to specify the original load adapter in the case it
	 * swapped out with an {@link #JavaEEWrappingLoadAdapter}.
	 */
	public static final String WRAPPED_LOAD_ADAPTER = "WRAPPED_LOAD_ADAPTER"; //$NON-NLS-1$
	
	/**
	 * Default value = null
	 * 
	 * An ArchiveOption used to specify the IRuntime of the EAR that the archive
	 * is linked to.
	 */
	public static final String PARENT_RUNTIME = "PARENT_RUNTIME"; //$NON-NLS-1$

	private JavaEEArchiveUtilities() {
	}

	public static JavaEEArchiveUtilities INSTANCE = new JavaEEArchiveUtilities();

	public static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	public static final String DOT_CLASS = ".class"; //$NON-NLS-1$

	public static boolean isJava(IFile iFile) {
		return hasExtension(iFile, DOT_JAVA);
	}

	public static boolean isClass(IFile iFile) {
		return hasExtension(iFile, DOT_CLASS);
	}

	public static boolean hasExtension(IFile iFile, String ext) {
		String name = iFile.getName();
		return hasExtension(name, ext);
	}

	public static boolean hasExtension(String name, String ext) {
		int offset = ext.length();
		return name.regionMatches(true, name.length() - offset, ext, 0, offset);
	}

	public IArchive openArchive(IVirtualComponent virtualComponent) throws ArchiveOpenFailureException {
		if (virtualComponent.isBinary()) {
			return openBinaryArchive(virtualComponent, true);
		}
		int type = J2EEVersionConstants.UNKNOWN;
		IArchiveLoadAdapter archiveLoadAdapter = null;
		if (JavaEEProjectUtilities.isEARProject(virtualComponent.getProject())) {
			archiveLoadAdapter = new EARComponentArchiveLoadAdapter(virtualComponent);
			type = J2EEVersionConstants.APPLICATION_TYPE;
		} else if (JavaEEProjectUtilities.isEJBComponent(virtualComponent)) {
			archiveLoadAdapter = new EJBComponentArchiveLoadAdapter(virtualComponent);
			type = J2EEVersionConstants.EJB_TYPE;
		} else if (JavaEEProjectUtilities.isApplicationClientComponent(virtualComponent)) {
			archiveLoadAdapter = new AppClientComponentArchiveLoadAdapter(virtualComponent);
			type = J2EEVersionConstants.APPLICATION_CLIENT_TYPE;
		} else if (JavaEEProjectUtilities.isJCAComponent(virtualComponent)) {
			archiveLoadAdapter = new ConnectorComponentArchiveLoadAdapter(virtualComponent);
			type = J2EEVersionConstants.CONNECTOR_TYPE;
		} else if (JavaEEProjectUtilities.isDynamicWebComponent(virtualComponent)) {
			archiveLoadAdapter = new WebComponentArchiveLoadAdapter(virtualComponent);
			type = J2EEVersionConstants.WEB_TYPE;
		} else if (JavaEEProjectUtilities.isUtilityProject(virtualComponent.getProject())) {
			archiveLoadAdapter = new JavaComponentArchiveLoadAdapter(virtualComponent);
		}

		if (archiveLoadAdapter != null) {
			ArchiveOptions options = new ArchiveOptions();
			options.setOption(ArchiveOptions.LOAD_ADAPTER, archiveLoadAdapter);
			IArchive archive = super.openArchive(options);
			if (type != J2EEVersionConstants.UNKNOWN) {
				int version = J2EEVersionConstants.UNKNOWN;
				String versionStr = JavaEEProjectUtilities.getJ2EEDDProjectVersion(virtualComponent.getProject());
				if(versionStr == null){
					versionStr = J2EEProjectUtilities.getJ2EEProjectVersion(virtualComponent.getProject());
				}
				switch (type) {
				case J2EEVersionConstants.APPLICATION_CLIENT_TYPE:
				case J2EEVersionConstants.APPLICATION_TYPE:
					if (versionStr.equals(J2EEVersionConstants.VERSION_1_2_TEXT)) {
						version = J2EEVersionConstants.J2EE_1_2_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_1_3_TEXT)) {
						version = J2EEVersionConstants.J2EE_1_3_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_1_4_TEXT)) {
						version = J2EEVersionConstants.J2EE_1_4_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_5_0_TEXT)) {
						version = J2EEVersionConstants.JEE_5_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_6_0_TEXT)) {
						version = J2EEVersionConstants.JEE_6_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_7_0_TEXT)) {
						version = J2EEVersionConstants.JEE_7_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_8_0_TEXT)) {
						version = J2EEVersionConstants.JEE_8_0_ID;
					}
					break;
				case J2EEVersionConstants.CONNECTOR_TYPE:
					if (versionStr.equals(J2EEVersionConstants.VERSION_1_0_TEXT)) {
						version = J2EEVersionConstants.JCA_1_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_1_5_TEXT)) {
						version = J2EEVersionConstants.JCA_1_5_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_1_6_TEXT)) {
						version = J2EEVersionConstants.JCA_1_6_ID;
					}
					break;
				case J2EEVersionConstants.EJB_TYPE:
					if (versionStr.equals(J2EEVersionConstants.VERSION_1_1_TEXT)) {
						version = J2EEVersionConstants.EJB_1_1_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_2_0_TEXT)) {
						version = J2EEVersionConstants.EJB_2_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_2_1_TEXT)) {
						version = J2EEVersionConstants.EJB_2_1_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_3_0_TEXT)) {
						version = J2EEVersionConstants.EJB_3_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_3_1_TEXT)) {
						version = J2EEVersionConstants.EJB_3_1_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_3_2_TEXT)) {
						version = J2EEVersionConstants.EJB_3_2_ID;
					}
					break;
				case J2EEVersionConstants.WEB_TYPE:
					if (versionStr.equals(J2EEVersionConstants.VERSION_2_2_TEXT)) {
						version = J2EEVersionConstants.WEB_2_2_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_2_3_TEXT)) {
						version = J2EEVersionConstants.WEB_2_3_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_2_4_TEXT)) {
						version = J2EEVersionConstants.WEB_2_4_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_2_5_TEXT)) {
						version = J2EEVersionConstants.WEB_2_5_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_3_0_TEXT)) {
						version = J2EEVersionConstants.WEB_3_0_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_3_1_TEXT)) {
						version = J2EEVersionConstants.WEB_3_1_ID;
					} else if (versionStr.equals(J2EEVersionConstants.VERSION_4_0_TEXT)) {
						version = J2EEVersionConstants.WEB_4_0_ID;
					}
					break;
				}
				if (version != J2EEVersionConstants.UNKNOWN) {
					archiveToJavaEEQuickPeek.put(archive, new JavaEEQuickPeek(type, version));
				}
			}
			return archive;
		}
		return null;
	}

	public IArchive openBinaryArchive(IVirtualComponent virtualComponent, boolean descriminateMainClass) throws ArchiveOpenFailureException {
		JavaEEBinaryComponentLoadAdapter loadAdapter = new JavaEEBinaryComponentLoadAdapter((VirtualArchiveComponent) virtualComponent);
		ArchiveOptions archiveOptions = new ArchiveOptions();
		archiveOptions.setOption(ArchiveOptions.LOAD_ADAPTER, loadAdapter);
		archiveOptions.setOption(ArchiveOptions.ARCHIVE_PATH, loadAdapter.getArchivePath());
		archiveOptions.setOption(DISCRIMINATE_MAIN_CLASS, descriminateMainClass);
		if(descriminateMainClass == true){
			archiveOptions.setOption(JavaEEArchiveUtilities.DISCRIMINATE_JAVA_EE, Boolean.TRUE);
		}
		IArchive parentEARArchive = null;
		boolean foundParentArchive = false;
		try {
			if (JavaEEProjectUtilities.usesJavaEEComponent(virtualComponent)
					&& ((J2EEModuleVirtualArchiveComponent) virtualComponent).isLinkedToEAR()) {
				try {
					IProject earProject = virtualComponent.getProject();
					if(earProject != null && EarUtilities.isEARProject(earProject)){
						IVirtualComponent earComponent = ComponentCore.createComponent(virtualComponent.getProject());
						String earLibDir = EarUtilities.getEARLibDir(earComponent);
						boolean inLibDir = isInLibDir(earComponent, virtualComponent, earLibDir);
						if(inLibDir == true && earLibDir != null){
							archiveOptions.setOption(DISCRIMINATE_JAVA_EE, false);
						}
						if(earComponent != null) {
							parentEARArchive = openArchive(earComponent);
							if(parentEARArchive != null) {
								foundParentArchive = true;
								archiveOptions.setOption(ArchiveOptions.PARENT_ARCHIVE, parentEARArchive);
								IFacetedProject facetedProject = ProjectFacetsManager.create(earProject);
								if (facetedProject != null) {
									IRuntime runtime = facetedProject.getPrimaryRuntime();
									archiveOptions.setOption(PARENT_RUNTIME, runtime);
								}
							}
						}
					}
				} catch(ArchiveOpenFailureException e) {
					org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
				} catch (CoreException e) {
					org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
				}
			}
			if(!foundParentArchive && JavaEEProjectUtilities.usesJavaEEComponent(virtualComponent)){
				IProject webProject = virtualComponent.getProject();
				if(webProject != null && WebUtilities.isDynamicWebProject(webProject)){
					archiveOptions.setOption(DISCRIMINATE_MAIN_CLASS, false);
				}
			}
			return openArchive(archiveOptions);
		} finally {
			if (parentEARArchive != null){
				archiveOptions.removeOption(ArchiveOptions.PARENT_ARCHIVE);
				closeArchive(parentEARArchive);
			}
		}
	}

	private Map<IArchive, JavaEEQuickPeek> archiveToJavaEEQuickPeek = Collections.synchronizedMap(new WeakHashMap<IArchive, JavaEEQuickPeek>());

	/**
	 * Returns a utility for getting the type of Java EE archive, the Java EE
	 * version, and the Module version
	 * 
	 * @param archive
	 * @return
	 */
	public JavaEEQuickPeek getJavaEEQuickPeek(IArchive archive) {
		if (archiveToJavaEEQuickPeek.containsKey(archive)) {
			return archiveToJavaEEQuickPeek.get(archive);
		}
		String[] deploymentDescriptorsToCheck = new String[] { J2EEConstants.APPLICATION_DD_URI, J2EEConstants.APP_CLIENT_DD_URI, J2EEConstants.EJBJAR_DD_URI, J2EEConstants.WEBAPP_DD_URI,
				J2EEConstants.RAR_DD_URI, J2EEConstants.WEBFRAGMENT_DD_URI };
		for (int i = 0; i < deploymentDescriptorsToCheck.length; i++) {
			final IPath deploymentDescriptorPath = new Path(deploymentDescriptorsToCheck[i]);
			if (archive.containsArchiveResource(deploymentDescriptorPath)) {
				InputStream in = null;
				IArchiveResource dd;
				try {
					dd = archive.getArchiveResource(deploymentDescriptorPath);
					in = dd.getInputStream();
					JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(in);
					archiveToJavaEEQuickPeek.put(archive, quickPeek);
					return quickPeek;
				} catch (FileNotFoundException e) {
					ArchiveUtil.warn(e);
				} catch (IOException e) {
					ArchiveUtil.warn(e);
				}
			}
		}
		JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(null);
		archiveToJavaEEQuickPeek.put(archive, quickPeek);
		return quickPeek;
	}

	/**
	 * Returns an IArchive. This method will attempt to discriminate the
	 * specific Java EE archive type based on the following simple rules. Please
	 * note that these rules do not adhere exactly to the Java EE specification
	 * because they are written for a tooling environment rather than a runtime
	 * environment. Thus these rules attempt to compensate for user error with
	 * the understanding that other areas of the tooling environment will help
	 * detect and correct these errors.
	 * 
	 * <ol>
	 * <li> An archive containing a deployment descriptor is considered to be of
	 * that type </li>
	 * <li> An archive whose name ends with '.ear' is considered an EAR </li>
	 * <li> An archive whose name ends with '.war' is considered a WAR </li>
	 * <li> An archive whose name ends with '.jar' and which contains a
	 * META-INF/MANIFEST.MF file containing a Main-class attribute is considered
	 * an Application Client </li>
	 * <li> If the ArchiveOptions specify the
	 * { @link #DISCRIMINATE_EJB_ANNOTATIONS} as Boolean.TRUE then if the archive
	 * contains any .class file with EJB annotations it is considered an EJB
	 * JAR. Be warned that this full check does have performance implications
	 * and is not done by default.</li>
	 * An archive whose name ends with '.jar' is considered a Utility </li>
	 * </ol>
	 */
	@Override
	public IArchive openArchive(ArchiveOptions archiveOptions) throws ArchiveOpenFailureException {
		IArchive simpleArchive = super.openArchive(archiveOptions);
		Object discriminateJavaEE = archiveOptions.getOption(DISCRIMINATE_JAVA_EE);
		if (discriminateJavaEE != null && !((Boolean) discriminateJavaEE).booleanValue()) {
			archiveToJavaEEQuickPeek.put(simpleArchive, new JavaEEQuickPeek(null));
			return simpleArchive;
		}
		return refineForJavaEE(simpleArchive);
	}

	private IArchive refineForJavaEE(final IArchive simpleArchive) {
		boolean isNestedWithinEar5OrAbove = false;		
		String earLibDirectory = null;
		String defaultEARLibDir = new Path(J2EEConstants.EAR_DEFAULT_LIB_DIR).makeRelative().toString();
		
		//Check to see if this archive is actually being opened as a nested archive from within an EAR
		//if it is then the EAR's DD needs to be checked to see exactly what type of archive this is.
		if (simpleArchive.getArchiveOptions().hasOption(ArchiveOptions.PARENT_ARCHIVE)) {
			IArchive parent = (IArchive) simpleArchive.getArchiveOptions().getOption(ArchiveOptions.PARENT_ARCHIVE);
			JavaEEQuickPeek qp = getJavaEEQuickPeek(parent);

			if (qp.getType() == JavaEEQuickPeek.APPLICATION_TYPE) {
				IPath ddPath = new Path(J2EEConstants.APPLICATION_DD_URI);
				if (parent.containsArchiveResource(ddPath)) {
					try {
						Object ddObj = parent.getModelObject(ddPath);
						IPath archivePath = simpleArchive.getPath();
						if (archivePath == null) {
							Object obj = simpleArchive.getArchiveOptions().getOption(ArchiveOptions.ARCHIVE_PATH);
							if (null != obj) {
								archivePath = (IPath) obj;
							}
						}
						int definedType = J2EEVersionConstants.UNKNOWN;
						if(archivePath != null) {
							if (qp.getVersion() == JavaEEQuickPeek.JEE_5_0_ID ||
									qp.getVersion() == JavaEEQuickPeek.JEE_6_0_ID ||
									qp.getVersion() == JavaEEQuickPeek.JEE_7_0_ID ||
									qp.getVersion() == JavaEEQuickPeek.JEE_8_0_ID) {
								isNestedWithinEar5OrAbove = true;
								org.eclipse.jst.javaee.application.Application app = (org.eclipse.jst.javaee.application.Application) ddObj;
								// If lib directory is not specified in deployment descriptor, use the default 
								earLibDirectory = app.getLibraryDirectory() == null? defaultEARLibDir : app.getLibraryDirectory();
								org.eclipse.jst.javaee.application.Module module = app.getFirstModule(archivePath.toString());
								//if the archive isn't found, do a smart search for it
								if(module == null){
									IPath noDevicePath = archivePath.setDevice(null);
									for(int i=1; i<noDevicePath.segmentCount() && module == null; i++){
										String stringPath = noDevicePath.removeFirstSegments(i).toString();
										module = app.getFirstModule(stringPath);
									}
								}
								if (null != module) {
									if (module.getEjb() != null) {
										definedType = J2EEVersionConstants.EJB_TYPE;
									} else if (module.getConnector() != null) {
										definedType = J2EEVersionConstants.CONNECTOR_TYPE;
									} else if (module.getJava() != null) {
										definedType = J2EEVersionConstants.APPLICATION_CLIENT_TYPE;
									} else if (module.getWeb() != null) {
										definedType = J2EEVersionConstants.WEB_TYPE;
									}
								}
							} else { //J2EE 1.4 or below, rely solely on DD
								org.eclipse.jst.j2ee.application.Application app = (org.eclipse.jst.j2ee.application.Application) ddObj;
								org.eclipse.jst.j2ee.application.Module module = app.getFirstModule(archivePath.toString());
								//if the archive isn't found, do a smart search for it
								if(module == null){
									IPath noDevicePath = archivePath.setDevice(null);
									for(int i=1; i<noDevicePath.segmentCount() && module == null; i++){
										String stringPath = noDevicePath.removeFirstSegments(i).toString();
										module = app.getFirstModule(stringPath);
									}
								}
								if(module == null) {
									module = getModuleFromURI(app, archivePath.toString());
								}
								if (null != module) {
									if (module.isEjbModule()) {
										definedType = J2EEVersionConstants.EJB_TYPE;
									} else if (module.isConnectorModule()) {
										definedType = J2EEVersionConstants.CONNECTOR_TYPE;
									} else if (module.isJavaModule()) {
										definedType = J2EEVersionConstants.APPLICATION_CLIENT_TYPE;
									} else if (module.isWebModule()) {
										definedType = J2EEVersionConstants.WEB_TYPE;
									}
								} else { //J2EE 1.4 or below, and not in DD, treat as utility
									JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(null);
									archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
									return simpleArchive;
								}
							}
						}
						if (definedType != J2EEVersionConstants.UNKNOWN) {
							String ddToCheck = null;
							switch (definedType) {
							case J2EEVersionConstants.EJB_TYPE:
								ddToCheck = J2EEConstants.EJBJAR_DD_URI;
								break;
							case J2EEVersionConstants.CONNECTOR_TYPE:
								ddToCheck = J2EEConstants.RAR_DD_URI;
								break;
							case J2EEVersionConstants.APPLICATION_CLIENT_TYPE:
								ddToCheck = J2EEConstants.APP_CLIENT_DD_URI;
								break;
							case J2EEVersionConstants.WEB_TYPE:
								ddToCheck = J2EEConstants.WEBAPP_DD_URI;
								break;
							}
							IArchive wrappedForDD = wrapForDD(simpleArchive, definedType, new Path(ddToCheck));
							if (wrappedForDD != null) {
								return wrappedForDD;
							}
							// else there is no DD and we need to decide on a version
							JavaEEQuickPeek quickPeek = null;
							String ddURI = null;
							IRuntime runtime = null;
							Object obj = simpleArchive.getArchiveOptions().getOption(PARENT_RUNTIME);
							if (null != obj) {
								runtime = (IRuntime) obj;
							}
							
							switch (definedType) {
							case J2EEVersionConstants.EJB_TYPE: {
								ddURI = J2EEConstants.EJBJAR_DD_URI;
								if (runtime == null || runtime.supports(IJ2EEFacetConstants.EJB_31)) {
									quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.EJB_TYPE, JavaEEQuickPeek.EJB_3_1_ID, JavaEEQuickPeek.JEE_6_0_ID);
								}
								else {
									quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.EJB_TYPE, JavaEEQuickPeek.EJB_3_0_ID, JavaEEQuickPeek.JEE_5_0_ID);
								}
								break;
							}
							case J2EEVersionConstants.APPLICATION_CLIENT_TYPE: {
								ddURI = J2EEConstants.APPLICATION_DD_URI;
								if (runtime == null || runtime.supports(IJ2EEFacetConstants.APPLICATION_CLIENT_60)) {
									quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.APPLICATION_CLIENT_TYPE, JavaEEQuickPeek.JEE_6_0_ID, JavaEEQuickPeek.JEE_6_0_ID);
								}
								else {
									quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.APPLICATION_CLIENT_TYPE, JavaEEQuickPeek.JEE_5_0_ID, JavaEEQuickPeek.JEE_5_0_ID);
								}
								break;
							}
							case J2EEVersionConstants.WEB_TYPE: {
								ddURI = J2EEConstants.WEBAPP_DD_URI;
								if (runtime == null || runtime.supports(IJ2EEFacetConstants.DYNAMIC_WEB_30)) {
									quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.WEB_TYPE, JavaEEQuickPeek.WEB_3_0_ID, JavaEEQuickPeek.JEE_6_0_ID);
								}
								else {
									quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.WEB_TYPE, JavaEEQuickPeek.WEB_2_5_ID, JavaEEQuickPeek.JEE_5_0_ID);
								}
								break;
							}
							case J2EEVersionConstants.CONNECTOR_TYPE: {
								ddURI = J2EEConstants.RAR_DD_URI;
								quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.CONNECTOR_TYPE, JavaEEQuickPeek.JCA_1_6_ID, JavaEEQuickPeek.JEE_6_0_ID);
								break;
							}
							}
							
							if (quickPeek != null) {
								archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
								wrapArchive(simpleArchive, new Path(ddURI));
								return simpleArchive;
							}
						}
					} catch (ArchiveModelLoadException e) {
						org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
					}
				}
				else {
					//Parent EAR does not have deployment descriptor, so it is not legacy
					isNestedWithinEar5OrAbove = true;
					earLibDirectory = defaultEARLibDir;
				}
			}			
		}
		IPath archivePath = simpleArchive.getPath();
		if (archivePath == null) {
			Object obj = simpleArchive.getArchiveOptions().getOption(ArchiveOptions.ARCHIVE_PATH);
			if (null != obj) {
				archivePath = (IPath) obj;
			}
		}
		String lastSegment = null == archivePath ? null : archivePath.lastSegment().toLowerCase();
		
		IArchive wrappedArchive = checkJavaEEDD(lastSegment, simpleArchive);
		if(wrappedArchive != null){
			return wrappedArchive;
		}
		
		if (lastSegment != null) {
			if (lastSegment.endsWith(IJ2EEModuleConstants.EAR_EXT)) {
				JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.APPLICATION_TYPE, JavaEEQuickPeek.JEE_6_0_ID, JavaEEQuickPeek.JEE_6_0_ID);
				archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
				wrapArchive(simpleArchive, new Path(J2EEConstants.APPLICATION_DD_URI));
				return simpleArchive;
			} else if (lastSegment.endsWith(IJ2EEModuleConstants.RAR_EXT)){
				JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.CONNECTOR_TYPE, JavaEEQuickPeek.JCA_1_6_ID, JavaEEQuickPeek.JEE_6_0_ID);
				archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
				wrapArchive(simpleArchive, new Path(J2EEConstants.RAR_DD_URI));
				return simpleArchive;
			} else if (lastSegment.endsWith(IJ2EEModuleConstants.WAR_EXT)) {
				JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.WEB_TYPE, JavaEEQuickPeek.WEB_3_0_ID, JavaEEQuickPeek.JEE_6_0_ID);
				archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
				wrapArchive(simpleArchive, new Path(J2EEConstants.WEBAPP_DD_URI));
				return simpleArchive;
			} else if (lastSegment.endsWith(IJ2EEModuleConstants.JAR_EXT)) {
				String libPath = null == archivePath ? null : archivePath.removeLastSegments(1).lastSegment();
				// Do not look for main class in manifest.mf if jar is on lib directory of EAR 5 or above
				boolean skipDiscriminateMainClass = isNestedWithinEar5OrAbove && earLibDirectory!= null && earLibDirectory.equals(libPath);
				Object discriminateMainClass = simpleArchive.getArchiveOptions().getOption(DISCRIMINATE_MAIN_CLASS);
				if (!skipDiscriminateMainClass && (null == discriminateMainClass || ((Boolean) discriminateMainClass).booleanValue())) {
					IPath manifestPath = new Path(J2EEConstants.MANIFEST_URI);
					if (simpleArchive.containsArchiveResource(manifestPath)) {
						InputStream in = null;
						try {
							IArchiveResource manifestResource = simpleArchive.getArchiveResource(manifestPath);
							in = manifestResource.getInputStream();
							Manifest manifest = new Manifest(in);
							Attributes attributes = manifest.getMainAttributes();
							String mainClassName = attributes.getValue("Main-Class"); //$NON-NLS-1$
							if (mainClassName != null) {
								JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.APPLICATION_CLIENT_TYPE, JavaEEQuickPeek.JEE_6_0_ID, JavaEEQuickPeek.JEE_6_0_ID);
								archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
								wrapArchive(simpleArchive, new Path(J2EEConstants.APPLICATION_DD_URI));
								return simpleArchive;
							}
						} catch (FileNotFoundException e) {
							ArchiveUtil.warn(e);
						} catch (IOException e) {
							ArchiveUtil.warn(e);
						} finally {
							if (in != null) {
								try {
									in.close();
								} catch (IOException e) {
									ArchiveUtil.warn(e);
								}
							}
						}
					}
				}
				Object discriminateEJB30 = simpleArchive.getArchiveOptions().getOption(DISCRIMINATE_EJB_ANNOTATIONS);
				if (null == discriminateEJB30 || ((Boolean) discriminateEJB30).booleanValue()) {
					if (isEJBArchive(simpleArchive)) {
						JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(JavaEEQuickPeek.EJB_TYPE, JavaEEQuickPeek.EJB_3_1_ID, JavaEEQuickPeek.JEE_6_0_ID);
						archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
						wrapArchive(simpleArchive, new Path(J2EEConstants.EJBJAR_DD_URI));
						return simpleArchive;
					}
				}

			}
		}

		return simpleArchive;
	}
	
	private org.eclipse.jst.j2ee.application.Module getModuleFromURI(org.eclipse.jst.j2ee.application.Application app, String uri) {
		if(uri == null)
			return null;
		String archiveName = (new Path(uri)).lastSegment();
		List<org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl> modules = app.getModules();
		for (org.eclipse.jst.j2ee.application.internal.impl.ModuleImpl curModule : modules ){
			if(curModule != null && curModule.getUri() != null) {
				if(new Path(curModule.getUri()).lastSegment().equals(archiveName)) {
					return curModule;
				}
			}
		}
		return null;
	}

	/**
	 * This method checks for the existence of deployment descriptors to
	 * determine the correct Java EE type. The last segment of the file name is
	 * passed in as a hint.
	 * 
	 * @param lastSegment
	 * @param simpleArchive
	 * @return
	 */
	private IArchive checkJavaEEDD(final String lastSegment, final IArchive simpleArchive) {
		class DeploymentDescriptorCheck {
			private String ddString;
			private int typeToVerify;
			private boolean checked = false;

			public DeploymentDescriptorCheck(String ddString, int typeToVerify) {
				this.ddString = ddString;
				this.typeToVerify = typeToVerify;
			}

			public IArchive wrapForDD(IArchive simpleArchive) {
				if (checked) {
					return null;
				}
				checked = true;
				IPath path = new Path(ddString);
				return JavaEEArchiveUtilities.this.wrapForDD(simpleArchive, typeToVerify, path);
			}
		}

		int EAR_INDEX = 0;
		int RAR_INDEX = 1;
		int WAR_INDEX = 2;
		int EJB_INDEX = 3;
		int APP_CLIENT_INDEX = 4;
		int WEB_FRAGMENT_INDEX = 5;

		DeploymentDescriptorCheck[] deploymentDescriptorsToCheck = new DeploymentDescriptorCheck[6];
		deploymentDescriptorsToCheck[EAR_INDEX] = new DeploymentDescriptorCheck(J2EEConstants.APPLICATION_DD_URI, J2EEVersionConstants.APPLICATION_TYPE);
		deploymentDescriptorsToCheck[RAR_INDEX] = new DeploymentDescriptorCheck(J2EEConstants.RAR_DD_URI, J2EEVersionConstants.CONNECTOR_TYPE);
		deploymentDescriptorsToCheck[WAR_INDEX] = new DeploymentDescriptorCheck(J2EEConstants.WEBAPP_DD_URI, J2EEVersionConstants.WEB_TYPE);
		deploymentDescriptorsToCheck[EJB_INDEX] = new DeploymentDescriptorCheck(J2EEConstants.EJBJAR_DD_URI, J2EEVersionConstants.EJB_TYPE);
		deploymentDescriptorsToCheck[APP_CLIENT_INDEX] = new DeploymentDescriptorCheck(J2EEConstants.APP_CLIENT_DD_URI, J2EEVersionConstants.APPLICATION_CLIENT_TYPE);
		deploymentDescriptorsToCheck[WEB_FRAGMENT_INDEX] = new DeploymentDescriptorCheck(J2EEConstants.WEBFRAGMENT_DD_URI, J2EEVersionConstants.WEBFRAGMENT_TYPE);

		if (lastSegment != null) {
			if (lastSegment.endsWith(IJ2EEModuleConstants.EAR_EXT)) {
				IArchive wrappedForDD = deploymentDescriptorsToCheck[EAR_INDEX].wrapForDD(simpleArchive);
				if (wrappedForDD != null) {
					return wrappedForDD;
				}
			} else if (lastSegment.endsWith(IJ2EEModuleConstants.RAR_EXT)) {
				IArchive wrappedForDD = deploymentDescriptorsToCheck[RAR_INDEX].wrapForDD(simpleArchive);
				if (wrappedForDD != null) {
					return wrappedForDD;
				}
			} else if (lastSegment.endsWith(IJ2EEModuleConstants.WAR_EXT)) {
				IArchive wrappedForDD = deploymentDescriptorsToCheck[WAR_INDEX].wrapForDD(simpleArchive);
				if (wrappedForDD != null) {
					return wrappedForDD;
				}
			} else if (lastSegment.endsWith(IJ2EEModuleConstants.JAR_EXT)) {
				IArchive wrappedForDD = deploymentDescriptorsToCheck[EJB_INDEX].wrapForDD(simpleArchive);
				if (wrappedForDD != null) {
					return wrappedForDD;
				}
				wrappedForDD = deploymentDescriptorsToCheck[APP_CLIENT_INDEX].wrapForDD(simpleArchive);
				if (wrappedForDD != null) {
					return wrappedForDD;
				}
				wrappedForDD = deploymentDescriptorsToCheck[WEB_FRAGMENT_INDEX].wrapForDD(simpleArchive);
				if (wrappedForDD != null) {
					return wrappedForDD;
				}
			}
		}

		for (DeploymentDescriptorCheck ddToCheck : deploymentDescriptorsToCheck) {
			IArchive wrappedForDD = ddToCheck.wrapForDD(simpleArchive);
			if (wrappedForDD != null) {
				return wrappedForDD;
			}
		}
		return null;
	}
	
	private IArchive wrapForDD(final IArchive simpleArchive, final int currentType, final IPath deploymentDescriptorPath) {
		if (simpleArchive.containsArchiveResource(deploymentDescriptorPath)) {
			InputStream in = null;
			IArchiveResource dd;
			try {
				dd = simpleArchive.getArchiveResource(deploymentDescriptorPath);
				in = dd.getInputStream();
				JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(in);
				if (quickPeek.getType() == currentType && quickPeek.getVersion() != JavaEEQuickPeek.UNKNOWN){
					if(isBinary(simpleArchive) || !simpleArchive.containsModelObject(deploymentDescriptorPath)){
						archiveToJavaEEQuickPeek.put(simpleArchive, quickPeek);
						wrapArchive(simpleArchive, deploymentDescriptorPath);
						return simpleArchive;
					}
				}
			} catch (FileNotFoundException e) {
				ArchiveUtil.warn(e);
			} catch (IOException e) {
				ArchiveUtil.warn(e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						ArchiveUtil.warn(e);
					}
				}
			}
		}
		return null;
	}
		
	public static boolean isBinary(IArchive anArchive){
		IArchiveLoadAdapter loadAdapter = null;
		if(anArchive.getArchiveOptions().hasOption(WRAPPED_LOAD_ADAPTER)){
			loadAdapter = (IArchiveLoadAdapter)anArchive.getArchiveOptions().getOption(WRAPPED_LOAD_ADAPTER);
		} else {
			loadAdapter = (IArchiveLoadAdapter)anArchive.getArchiveOptions().getOption(ArchiveOptions.LOAD_ADAPTER);
		}
		if(loadAdapter instanceof JavaEEBinaryComponentLoadAdapter){
			return true;
		} else if(loadAdapter instanceof ZipFileArchiveLoadAdapterImpl){
			return true;
		}
		return false;
	}
	
	public static IArchive findArchive(Object modelObject){
		if(modelObject instanceof EObject){
			EObject eObject = (EObject)modelObject;
			return JavaEEEMFArchiveAdapterHelper.findArchive(eObject);
		}
		return null;
	}
	
	public static IVirtualComponent findComponent(IArchive anArchive){
		IArchiveLoadAdapter loadAdapter = null;
		if(anArchive.getArchiveOptions().hasOption(WRAPPED_LOAD_ADAPTER)){
			loadAdapter = (IArchiveLoadAdapter)anArchive.getArchiveOptions().getOption(WRAPPED_LOAD_ADAPTER);
		} else {
			loadAdapter = (IArchiveLoadAdapter)anArchive.getArchiveOptions().getOption(ArchiveOptions.LOAD_ADAPTER);
		}
		if(loadAdapter instanceof JavaEEBinaryComponentLoadAdapter){
			return ((JavaEEBinaryComponentLoadAdapter)loadAdapter).getArchiveComponent();
		}
		return null;
	}
	

	public static class JavaEEWrappingLoadAdapter implements IArchiveLoadAdapter {

		private IArchive simpleArchive;
		private IArchiveLoadAdapter simpleLoadAdapter;
		private IPath deploymentDescriptorPath;
		private JavaEEEMFArchiveAdapterHelper emfHelper;

		public JavaEEWrappingLoadAdapter(IArchive simpleArchive, IPath deploymentDescriptorPath) {
			this.simpleArchive = simpleArchive;
			this.simpleLoadAdapter = this.simpleArchive.getLoadAdapter();
			this.deploymentDescriptorPath = deploymentDescriptorPath;
			this.emfHelper = new JavaEEEMFArchiveAdapterHelper(this.simpleArchive);
		}

		@Override
		public void close() {
			simpleLoadAdapter.close();
		}

		@Override
		public boolean containsArchiveResource(IPath resourcePath) {
			return simpleLoadAdapter.containsArchiveResource(resourcePath);
		}

		@Override
		public boolean containsModelObject(IPath modelObjectPath) {
			IPath localModelObjectPath = modelObjectPath;
			if (simpleLoadAdapter.containsArchiveResource(localModelObjectPath)) {
				return true;
			}
			if (IArchive.EMPTY_MODEL_PATH == localModelObjectPath) {
				localModelObjectPath = deploymentDescriptorPath;
			}
			return emfHelper.containsModelObject(localModelObjectPath);
		}

		@Override
		public IArchiveResource getArchiveResource(IPath resourcePath) throws FileNotFoundException {
			return simpleLoadAdapter.getArchiveResource(resourcePath);
		}

		@Override
		public List<IArchiveResource> getArchiveResources() {
			return simpleLoadAdapter.getArchiveResources();
		}

		@Override
		public InputStream getInputStream(IArchiveResource archiveResource) throws IOException, FileNotFoundException {
			return simpleLoadAdapter.getInputStream(archiveResource);
		}

		@Override
		public Object getModelObject(IPath modelObjectPath) throws ArchiveModelLoadException {
			IPath localModelObjectPath = modelObjectPath;
			if (IArchive.EMPTY_MODEL_PATH != localModelObjectPath 
					&& simpleLoadAdapter.containsModelObject(localModelObjectPath)) {
				return simpleLoadAdapter.getModelObject(localModelObjectPath);
			}
			if (IArchive.EMPTY_MODEL_PATH == localModelObjectPath) {
				localModelObjectPath = deploymentDescriptorPath;
			}
			return emfHelper.getModelObject(localModelObjectPath);
		}

		@Override
		public IArchive getArchive() {
			return simpleLoadAdapter.getArchive();
		}

		@Override
		public void setArchive(IArchive archive) {
			simpleLoadAdapter.setArchive(archive);
		}

		public IArchiveLoadAdapter getWrappedLoadAdatper() {
			return simpleLoadAdapter;
		}

		@Override
		public String toString() {
			return simpleLoadAdapter.toString();
		}

	}

	private static void wrapArchive(final IArchive simpleArchive, final IPath deploymentDescriptorPath) {
		IArchiveLoadAdapter wrappingEMFLoadAdapter = new JavaEEWrappingLoadAdapter(simpleArchive, deploymentDescriptorPath);
		simpleArchive.getArchiveOptions().setOption(ArchiveOptions.LOAD_ADAPTER, wrappingEMFLoadAdapter);
		simpleArchive.getArchiveOptions().setOption(WRAPPED_LOAD_ADAPTER, simpleArchive.getLoadAdapter());
		((ArchiveImpl) simpleArchive).setLoadAdapter(wrappingEMFLoadAdapter);
	}

	private static final char[] RUNTIME_VISIBLE = "RuntimeVisibleAnnotations".toCharArray(); //$NON-NLS-1$

	private static final char[] STATELESS = "Ljavax/ejb/Stateless;".toCharArray();//$NON-NLS-1$

	private static final char[] STATEFUL = "Ljavax/ejb/Stateful;".toCharArray();//$NON-NLS-1$

	private static final char[] MESSAGEDRIVEN = "Ljavax/ejb/MessageDriven;".toCharArray();//$NON-NLS-1$

	private static final char[] SINGLETON = "Ljavax/ejb/Singleton;".toCharArray();//$NON-NLS-1$

	
	public boolean isEJBArchive(IArchive archive) {
		// first check for the deployment descriptor
		if (archiveToJavaEEQuickPeek.containsKey(archive)) {
			JavaEEQuickPeek qp = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive);
			if (qp.getType() == JavaEEQuickPeek.EJB_TYPE) {
				return true;
			}
		}

		List<IArchiveResource> archiveResources = archive.getArchiveResources();
		for (IArchiveResource archiveResource : archiveResources) {
			if (archiveResource.getType() == IArchiveResource.FILE_TYPE) {
				if (archiveResource.getPath().lastSegment().endsWith(DOT_CLASS)) {
					InputStream ioStream = null;
					try {
						ioStream = archiveResource.getInputStream();
						if (ioStream != null) {
							IClassFileReader classFileReader = ToolFactory.createDefaultClassFileReader(ioStream, IClassFileReader.CLASSFILE_ATTRIBUTES);
							//classFileReader will be null if this is an invalid java .class file
							if(classFileReader != null) {
								IClassFileAttribute[] attributes = classFileReader.getAttributes();
								for (IClassFileAttribute attribute : attributes) {
									char[] attributeName = attribute.getAttributeName();
									if (Arrays.equals(attributeName, RUNTIME_VISIBLE)) {
										IRuntimeVisibleAnnotationsAttribute annotationsAttribute = (IRuntimeVisibleAnnotationsAttribute) attribute;
										IAnnotation[] annotations = annotationsAttribute.getAnnotations();
										for (IAnnotation annotation : annotations) {
											char[] typedName = annotation.getTypeName();
											if (Arrays.equals(typedName, STATELESS) || Arrays.equals(typedName, STATEFUL) || Arrays.equals(typedName, MESSAGEDRIVEN) || Arrays.equals(typedName, SINGLETON)) {
												return true;
											}
										}
									}
								}
							}
						}
					} catch (FileNotFoundException e) {
						ArchiveUtil.warn(e);
					} catch (IOException e) {
						ArchiveUtil.warn(e);
					} finally {
						if (null != ioStream) {
							try {
								ioStream.close();
							} catch (IOException e) {
								ArchiveUtil.warn(e);
							}
						}
						ioStream = null;
					}
				}
			}
		}
		return false;
	}

	public Manifest getManifest(IArchive archive) {
		Manifest manifest = null;
		IPath manifestPath = new Path(J2EEConstants.MANIFEST_URI);
		if (archive.containsArchiveResource(manifestPath)) {
			InputStream in = null;
			try {
				IArchiveResource manifestResource = archive.getArchiveResource(manifestPath);
				in = manifestResource.getInputStream();
				manifest = new Manifest(in);
			} catch (FileNotFoundException e) {
				ArchiveUtil.warn(e);
			} catch (IOException e) {
				ArchiveUtil.warn(e);
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						ArchiveUtil.warn(e);
					}
				}
			}
		}
		return manifest;
	}
	

	private boolean isInLibDir(IVirtualComponent earComp, IVirtualComponent component, String libDir){
		if (libDir != null && libDir.length() > 0) {
			IVirtualReference earRef = earComp.getReference(component.getName());
			IPath libDirPath = new Path(libDir).makeRelative();
			if(earRef != null){
				if(libDirPath.equals(earRef.getRuntimePath().makeRelative())){
					return true;
				}
				IPath fullPath = earRef.getRuntimePath().append(earRef.getArchiveName());
				if(fullPath.removeLastSegments(1).makeRelative().equals(libDirPath)){
					return true;
				}
			}
		}
		return false;
	}
	
}
