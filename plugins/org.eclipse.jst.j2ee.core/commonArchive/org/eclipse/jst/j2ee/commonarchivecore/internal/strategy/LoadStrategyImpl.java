package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;

/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/*
 * Licensed Material - Property of IBM
 * (C) Copyright IBM Corp. 2001 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jem.internal.java.adapters.jdk.JavaJDKAdapterFactory;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveURIConverterImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIteratorImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseConfigRegister;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResouceFactorySaxRegistry;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResourceFactoryDomRegistry;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;

/**
 * Abstact implementer off which and load strategy may subclass
 * 
 * @see LoadStrategy
 */
public abstract class LoadStrategyImpl extends AdapterImpl implements LoadStrategy {

	/** flag to indicate whether underlying resources have been closed */
	protected boolean isOpen = true;

	/** The archive or directory to which this strategy belongs */
	protected Container container;

	/** ResourceSet used for mof/xmi resources */
	protected ResourceSet resourceSet;

	protected LooseArchive looseArchive;

	protected Map collectedLooseArchiveFiles;

	protected boolean readOnly = false;

	private int rendererType;

	public LoadStrategyImpl() {
		super();
	}

	/**
	 * @see Archive
	 */
	public void addOrReplaceMofResource(Resource aResource) {
		Resource existingResource = getResourceSet().getResource(aResource.getURI(), false);
		if (existingResource != null)
			getResourceSet().getResources().remove(existingResource);
		getResourceSet().getResources().add(aResource);
	}

	protected void updateModificationTracking(Resource res) {
		boolean trackingMods = res.isTrackingModification();
		boolean isReadOnly = (container != null) ? ((Archive) container).getOptions().isReadOnly() : false;
		boolean shouldTrackMods = !(isReadOnly || ArchiveUtil.isJavaResource(res) || ArchiveUtil.isRegisteredURIMapping(res));
		if (shouldTrackMods && !trackingMods)
			res.setTrackingModification(true);
	}

	/**
	 * Release any resources being held by this object and set the state to closed. Subclasses
	 * should override as necessary
	 */
	public void close() {
		setIsOpen(false);
	}

	protected abstract boolean primContains(String uri);

	/**
	 * @see LoadStrategy
	 */
	public boolean contains(String uri) {
		if (containsUsingLooseArchive(uri))
			return true;
		return primContains(uri);
	}

	/*
	 * Try the resources path first; if that false, see if we have a child loose archive with the
	 * uri
	 */
	protected boolean containsUsingLooseArchive(String uri) {
		if (getLooseArchive() == null)
			return false;

		LooseArchive loose = getLooseArchive();
		if (loose.getResourcesPath() == null)
			return false;

		java.io.File aFile = new java.io.File(loose.getResourcesPath(), uri);
		if (aFile.exists())
			return true;

		return LooseConfigRegister.singleton().findFirstLooseChild(uri, loose) != null;
	}

	protected File createFile(String uri) {
		File aFile = null;
		if (isArchive(uri))
			aFile = openNestedArchive(uri);
		if (aFile == null) {
			aFile = getArchiveFactory().createFile();
			aFile.setURI(uri);
			aFile.setOriginalURI(uri);
		}
		aFile.setLoadingContainer(getContainer());
		return aFile;
	}

	protected void finalize() throws Throwable {
		close();
	}

	/**
	 * @see LoadStrategy
	 */
	public java.lang.String getAbsolutePath() throws FileNotFoundException {
		throw new FileNotFoundException(CommonArchiveResourceHandler.getString("Absolute_path_unknown_EXC_")); //$NON-NLS-1$ = "Absolute path unknown"
	}

	public String getResourcesPath() throws FileNotFoundException {
		return getLooseArchive() == null ? getAbsolutePath() : getLooseArchive().getResourcesPath();
	}

	protected String primGetResourcesPath() {
		return getLooseArchive() == null ? null : getLooseArchive().getResourcesPath();
	}

	public String getBinariesPath() throws FileNotFoundException {
		return getLooseArchive() == null ? getAbsolutePath() : getLooseArchive().getBinariesPath();
	}

	public CommonarchiveFactory getArchiveFactory() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory();
	}

	public Container getContainer() {
		return container;
	}

	public ResourceSet primGetResourceSet() {
		return resourceSet;
	}

	/**
	 * 
	 * Should we iterate all the files in the archive as part of saving, or can we treat the archive
	 * as one big file during save? The following rules apply, iterating the files if: 1) If the
	 * archive is a module file and it is NOT read-only 2) If the load strategy is a directory 3) If
	 * the archive is a utility JAR, and the files list has never been initialized, or if the
	 * loading containers for all the files are the same AND not directories, AND the
	 * {@link ArchiveOptions#isSaveLibrariesAsFiles()}of the archive is true.
	 * 
	 * @see com.ibm.etools.archive.LoadStrategy#requiresIterationOnSave()
	 */
	public boolean requiresIterationOnSave() {
		if (!getContainer().isArchive() || isDirectory())
			return true;
		Archive anArchive = (Archive) getContainer();
		//We should leave utility JARs intact, unless were told not to
		//The manifest may have been signed
		if (anArchive.isModuleFile())
			return !anArchive.getOptions().isReadOnly();
		else if (anArchive.getOptions().isSaveLibrariesAsFiles() && anArchive.getLoadingContainer() != null) {
			if (anArchive.isIndexed()) {
				List files = anArchive.getFiles();
				File aFile = null;
				Container firstContainer = null;
				Container lContainer = null;
				for (int i = 0; i < files.size(); i++) {
					aFile = (File) files.get(i);
					if (i == 0) {
						firstContainer = aFile.getLoadingContainer();
						if (firstContainer.getLoadStrategy().isDirectory())
							return true;
					}
					lContainer = aFile.getLoadingContainer();
					if (lContainer != firstContainer)
						return true;
				}
			}
			return false;
		} else
			return true;
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			initializeResourceSet();
			resourceSet.eAdapters().add(this);
		}
		return resourceSet;
	}

	/**
	 * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(Notification)
	 */
	public void notifyChanged(Notification msg) {
		switch (msg.getEventType()) {
			case Notification.ADD :
				updateModificationTracking((Resource) msg.getNewValue());
				break;
			case Notification.ADD_MANY :
				List list = (List) msg.getNewValue();
				for (int i = 0; i < list.size(); i++) {
					updateModificationTracking((Resource) list.get(i));
				}
			default :
				break;
		}
	}

	/**
	 * Used internally; clients should not need to call
	 */
	public FileIterator getFileIterator() throws IOException {
		return new FileIteratorImpl(getContainer().getFiles());
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public abstract List getFiles();

	public List collectFiles() {
		//The loose archives need to be read first
		collectFilesFromLooseArchives();
		List files = getFiles();
		files.addAll(collectedLooseArchiveFiles.values());
		collectedLooseArchiveFiles = null;
		return files;
	}

	protected void collectFilesFromLooseArchives() {
		if (!canHaveLooseChildren() || getLooseArchive() == null) {
			collectedLooseArchiveFiles = Collections.EMPTY_MAP;
			return;
		}

		collectedLooseArchiveFiles = new HashMap();
		List children = LooseConfigRegister.singleton().getLooseChildren(getLooseArchive());

		for (int i = 0; i < children.size(); i++) {
			LooseArchive loose = (LooseArchive) children.get(i);
			String uri = loose.getUri();
			if (!collectedLooseArchiveFiles.containsKey(uri)) {
				Archive archive = openNestedArchive(loose);
				if (archive != null) {
					collectedLooseArchiveFiles.put(uri, archive);
					archive.setLoadingContainer(getContainer());
				}
			}
		}
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy
	 */
	public abstract InputStream getInputStream(String uri) throws IOException, FileNotFoundException;

	public InputStream getResourceInputStream(String uri) throws IOException {
		return getResourceSet().getURIConverter().createInputStream(URI.createURI(uri));
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive returns an immutable collection of the loaded
	 *      resources in the resource set
	 */
	public Collection getLoadedMofResources() {
		Collection resources = getResourceSet().getResources();
		if (resources.isEmpty())
			return Collections.EMPTY_LIST;

		List result = new ArrayList(resources.size());
		Iterator iter = resources.iterator();
		while (iter.hasNext()) {
			Resource res = (Resource) iter.next();
			if (res.isLoaded())
				result.add(res);
		}
		return result;
	}

	/**
	 * @see com.ibm.etools.commonarchive.Archive
	 */
	public Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException {
		try {
			return getResourceSet().getResource(URI.createURI(uri), true);
		} catch (WrappedException wrapEx) {
			if ((ExtendedEcoreUtil.getFileNotFoundDetector().isFileNotFound(wrapEx))) {
				FileNotFoundException fileNotFoundEx = ExtendedEcoreUtil.getInnerFileNotFoundException(wrapEx);
				throw fileNotFoundEx;
			}
			throwResourceLoadException(uri, wrapEx);
			return null; //never happens - compiler expects it though
		}
	}

	protected void initializeResourceSet() {
		//Not the best design here, because a load strategy should only know
		// about
		//container; however, this method will only get called when the
		// container
		//is an archive
		Archive archive = (Archive) getContainer();
		URIConverter converter = new ArchiveURIConverterImpl(archive, primGetResourcesPath());
		ResourceSet rs = new ResourceSetImpl();
		Resource.Factory.Registry reg = createResourceFactoryRegistry();
		rs.setResourceFactoryRegistry(reg);
		setResourceSet(rs);
		rs.setURIConverter(converter);
		if (archive.shouldUseJavaReflection()) {
			rs.getAdapterFactories().add(new JavaJDKAdapterFactory());
			archive.initializeClassLoader();
		}
	}

	protected Resource.Factory.Registry createResourceFactoryRegistry() {
		if (isReadOnly())
			return new J2EEResouceFactorySaxRegistry();

		Resource.Factory.Registry registry = null;
		switch (getRendererType()) {
			case ArchiveOptions.SAX :
				registry = new J2EEResouceFactorySaxRegistry();
				break;
			case ArchiveOptions.DOM :
				registry = new J2EEResourceFactoryDomRegistry();
				break;
			case ArchiveOptions.DEFAULT :
			default :
				registry = new J2EEResourceFactoryRegistry();
				break;
		}
		return registry;
	}

	/**
	 * @return
	 */
	public int getRendererType() {
		return rendererType;
	}

	protected boolean isArchive(String uri) {
		return ((Archive) getContainer()).isNestedArchive(uri);
	}

	/**
	 * An archive uses a custom class loader for java reflection within a mof resourceSet;
	 * implementers of LoadStrategy may supply a mof resourceSet for which this class loader is not
	 * necessary, or could even cause breakage; this test gives the strategy the chance to "opt out"
	 * of the class loading game
	 */
	public boolean isClassLoaderNeeded() {
		return true;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy The default is false
	 */
	public boolean isDirectory() {
		return false;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy#getExistingMofResource(String)
	 */
	public Resource getExistingMofResource(String uri) {
		return getResourceSet().getResource(URI.createURI(uri), false);
	}

	public boolean isMofResourceLoaded(java.lang.String uri) {
		Resource res = getExistingMofResource(uri);
		return res != null && res.isLoaded();
	}

	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * @see com.ibm.etools.archive.LoadStrategy return false by default; subclasses should override
	 *      if necessary
	 */
	public boolean isUsing(java.io.File aSystemFile) {
		return false;
	}

	public Resource makeMofResource(String uri, EList extent) {
		Resource existing = getExistingMofResource(uri);
		if (existing != null)
			return existing;
		return getResourceSet().createResource(URI.createURI(uri));
	}

	protected Archive openNestedArchive(String uri) {

		try {
			return ((Archive) getContainer()).openNestedArchive(uri);
		} catch (OpenFailureException e) {
			//Caught an exception trying to open the nested archive
			Logger.getLogger().logError(e);
			return null;
		}

	}

	protected Archive openNestedArchive(LooseArchive loose) {

		try {
			return ((Archive) getContainer()).openNestedArchive(loose);
		} catch (OpenFailureException e) {
			//Caught an exception trying to open the nested archive
			Logger.getLogger().logError(e);
			return null;
		}

	}

	public void setContainer(Container newContainer) {
		container = newContainer;
	}

	public void setResourceSet(org.eclipse.emf.ecore.resource.ResourceSet newResourceSet) {
		// fixes problem in reopen
		if (resourceSet != newResourceSet) {

			// remove adapter from old resource set
			if (resourceSet != null)
				resourceSet.eAdapters().remove(this);

			// add as adapter to new resource set if necessary
			if (newResourceSet != null && !newResourceSet.eAdapters().contains(this))
				newResourceSet.eAdapters().add(this);

			resourceSet = newResourceSet;
		} // no need to update if old set equals new set (by reference)
	}

	protected void setIsOpen(boolean newIsOpen) {
		isOpen = newIsOpen;
	}

	protected void throwResourceLoadException(String resourceUri, Exception ex) throws ResourceLoadException {
		throw new ResourceLoadException(CommonArchiveResourceHandler.getString("load_resource_EXC_", (new Object[]{resourceUri, getContainer().getURI()})), ex); //$NON-NLS-1$ = "Could not load resource "{0}" in archive "{1}""
	}

	/**
	 * Gets the looseArchive.
	 * 
	 * @return Returns a LooseArchive
	 */
	public LooseArchive getLooseArchive() {
		return looseArchive;
	}

	/**
	 * Sets the looseArchive.
	 * 
	 * @param looseArchive
	 *            The looseArchive to set
	 */
	public void setLooseArchive(LooseArchive looseArchive) {
		this.looseArchive = looseArchive;
		checkLoosePathsValid();
	}

	/*
	 * Added to support WAS runtime; throw an ArchiveRuntimeException if one of the paths in the
	 * loose config does not point to an existing file
	 */
	protected void checkLoosePathsValid() {
		if (looseArchive == null)
			return;

		String path = looseArchive.getBinariesPath();
		if (path != null) {
			java.io.File ioFile = new java.io.File(path);
			if (!ioFile.exists())
				throw new ArchiveRuntimeException("Invalid binaries path: " + path); //$NON-NLS-1$
		}
		path = looseArchive.getResourcesPath();
		if (path != null) {
			java.io.File ioFile = new java.io.File(path);
			if (!ioFile.exists())
				throw new ArchiveRuntimeException("Invalid resources path: " + path); //$NON-NLS-1$
		}
	}

	protected boolean canHaveLooseChildren() {
		return container.isEARFile() || container.isWARFile();
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * @param rendererType
	 *            The rendererType to set.
	 */
	public void setRendererType(int rendererType) {
		this.rendererType = rendererType;
	}
}
