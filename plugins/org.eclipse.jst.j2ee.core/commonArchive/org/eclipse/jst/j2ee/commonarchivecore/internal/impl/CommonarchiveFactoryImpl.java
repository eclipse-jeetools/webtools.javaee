/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ArchiveTypeDiscriminatorRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ClientModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ConnectorModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.AppClient12ImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.DirectoryArchiveLoadStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.DirectoryArchiveLoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.Ear12ImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.EjbJar11ImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.NestedArchiveLoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.NullLoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.RarImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ReadOnlyDirectoryLoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.TempZipFileLoadStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.War22ImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;

import com.ibm.wtp.common.logger.proxy.Logger;


/**
 * @generated
 */
public class CommonarchiveFactoryImpl extends EFactoryImpl implements CommonarchiveFactory {


	protected Map openArchives;
	private static boolean delegateNeedsInit = true;
	private CommonarchiveFactory delegate = null;

	static {
		initPrereqs();
	}

	/**
	 *  
	 */
	public CommonarchiveFactoryImpl() {
		super();
		initDelegate();
	}

	/**
	 *  
	 */
	private void initDelegate() {
		if (delegateNeedsInit) {
			delegateNeedsInit = false;
			delegate = new CommonarchiveFactoryImpl() {

				public ApplicationClientFile createApplicationClientFile() {
					return createApplicationClientFileGen();
				}


				public ClientModuleRef createClientModuleRef() {
					return createClientModuleRefGen();
				}

				public ConnectorModuleRef createConnectorModuleRef() {
					return createConnectorModuleRefGen();
				}

				public EARFile createEARFile() {
					return createEARFileGen();
				}

				public EJBJarFile createEJBJarFile() {
					return createEJBJarFileGen();
				}

				public EJBModuleRef createEJBModuleRef() {
					return createEJBModuleRefGen();
				}

				public RARFile createRARFile() {
					return createRARFileGen();
				}

				public WARFile createWARFile() {
					return createWARFileGen();
				}

				public WebModuleRef createWebModuleRef() {
					return createWebModuleRefGen();
				}
			};
		}

	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CommonarchivePackage.FILE :
				return createFile();
			case CommonarchivePackage.ARCHIVE :
				return createArchive();
			case CommonarchivePackage.EJB_JAR_FILE :
				return createEJBJarFile();
			case CommonarchivePackage.WAR_FILE :
				return createWARFile();
			case CommonarchivePackage.EAR_FILE :
				return createEARFile();
			case CommonarchivePackage.APPLICATION_CLIENT_FILE :
				return createApplicationClientFile();
			case CommonarchivePackage.READ_ONLY_DIRECTORY :
				return createReadOnlyDirectory();
			case CommonarchivePackage.RAR_FILE :
				return createRARFile();
			case CommonarchivePackage.EJB_MODULE_REF :
				return createEJBModuleRef();
			case CommonarchivePackage.WEB_MODULE_REF :
				return createWebModuleRef();
			case CommonarchivePackage.CLIENT_MODULE_REF :
				return createClientModuleRef();
			case CommonarchivePackage.CONNECTOR_MODULE_REF :
				return createConnectorModuleRef();
			default :
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public void archiveClosed(Archive aClosedArchive) {
		getOpenArchives().remove(aClosedArchive);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public void archiveOpened(Archive anOpenArchive) {
		getOpenArchives().put(anOpenArchive, null);
	}

	/**
	 * @deprecated Use {@link #getOpenArchivesDependingOn(Archive)}
	 */
	public boolean canClose(Archive anArchive) {
		return !getOpenArchivesDependingOn(anArchive).isEmpty();
	}

	public void closeOpenArchives() {
		if (getOpenArchives().isEmpty())
			return;
		List opened = new ArrayList(getOpenArchives().size());
		Iterator it = getOpenArchives().keySet().iterator();
		while (it.hasNext()) {
			opened.add(it.next());
		}
		for (int i = 0; i < opened.size(); i++) {
			Archive anArchive = (Archive) opened.get(i);
			anArchive.close();
		}
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public Archive copy(Archive anArchive) {
		return new ArchiveCopyUtility().copy(anArchive);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public ModuleFile copy(ModuleFile aModuleFile) {
		return new ArchiveCopyUtility().copy(aModuleFile);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public ApplicationClientFile createApplicationClientFileInitialized(java.lang.String uri) {
		ApplicationClientFile clientFile = createApplicationClientFile();
		initializeNewApplicationClientFile(clientFile, uri);
		return clientFile;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public Archive createArchiveInitialized(java.lang.String uri) {
		Archive anArchive = createArchive();
		initializeNewArchive(anArchive, uri);
		return anArchive;

	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public Archive createArchiveInitialized(ArchiveOptions options, java.lang.String uri) {
		Archive anArchive = createArchive();
		initializeNewArchive(anArchive, uri, options);
		return anArchive;

	}

	public LoadStrategy createChildLoadStrategy(String uri, LoadStrategy parent) throws java.io.IOException, java.io.FileNotFoundException {

		LoadStrategy childStrategy = null;
		if (parent.isDirectory()) {
			String dirName = ((DirectoryArchiveLoadStrategy) parent).getDirectoryUri();
			String qualifiedUri = ArchiveUtil.getOSUri(dirName, uri);
			childStrategy = createLoadStrategy(qualifiedUri);
		} else {
			childStrategy = createNestedLoadStrategy(uri, parent);
		}
		return childStrategy;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EARFile createEARFileInitialized(java.lang.String uri) {
		EARFile earFile = createEARFile();
		initializeNewEARFile(earFile, uri);
		return earFile;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EARFile createEARFileInitialized(ArchiveOptions options, java.lang.String uri) {
		EARFile earFile = createEARFile();
		initializeNewEARFile(earFile, uri, options);
		return earFile;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile createEJBJarFileInitialized(java.lang.String uri) {
		EJBJarFile ejbJarFile = createEJBJarFile();
		initializeNewEJBJarFile(ejbJarFile, uri);
		return ejbJarFile;

	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile createEJBJarFileInitialized(ArchiveOptions options, java.lang.String uri) {
		EJBJarFile ejbJarFile = createEJBJarFile();
		initializeNewEJBJarFile(ejbJarFile, uri, options);
		return ejbJarFile;

	}

	/**
	 * Returns a NullLoadStrategyImpl; used for new archives
	 */
	public LoadStrategy createEmptyLoadStrategy() {
		return new NullLoadStrategyImpl();
	}

	/**
	 * Helper method to dynamically build a load strategy from the file system. Determines whether
	 * the uri points to a jar file or directory and returns the appropriate strategy
	 */
	public LoadStrategy createLoadStrategy(String uri) throws FileNotFoundException, IOException {
		String filename = uri.replace('/', java.io.File.separatorChar);
		java.io.File file = new java.io.File(filename);
		if (!file.exists()) {
			throw new FileNotFoundException(CommonArchiveResourceHandler.getString("file_not_found_EXC_", (new Object[]{uri, file.getAbsolutePath()}))); //$NON-NLS-1$ = "URI Name: {0}; File name: {1}"
		}
		if (file.isDirectory()) {
			return new DirectoryArchiveLoadStrategyImpl(uri);
		}
		return new org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ZipFileLoadStrategyImpl(file);
	}

	/**
	 * Create a load strategy for a nested archive; by default will extract the nested archive to a
	 * temp file for performance reasons. This is because random access to the zip entries in a
	 * nested archive is not supported by the java.util.zip package, and if the archive's contents
	 * are being modified, copied, etc, this is much faster. If a temp file can not be created, or
	 * if the archive is opened read only (for runtime), then use a NestedArchiveLoadStrategy, which
	 * retrieves the contents of a zip entry by sequentially searching a zip input stream
	 */
	public LoadStrategy createNestedLoadStrategy(String uri, LoadStrategy parent) {
		LoadStrategy loadStrategy = null;
		ArchiveOptions options = ((Archive) parent.getContainer()).getOptions();

		if (!options.isReadOnly(uri))
			loadStrategy = createTempZipFileStrategyIfPossible(uri, parent);

		if (loadStrategy == null)
			return new NestedArchiveLoadStrategyImpl(parent);
		return loadStrategy;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public RARFile createRARFileInitialized(java.lang.String uri) {
		RARFile rarFile = createRARFile();
		initializeNewRARFile(rarFile, uri);
		return rarFile;
	}

	public LoadStrategy createTempZipFileStrategyIfPossible(String uri, LoadStrategy parent) {

		if (!ArchiveUtil.shouldUseTempDirectoryForRead())
			return null;

		try {
			java.io.File tempFile = ArchiveUtil.createTempFile(uri);
			tempFile.deleteOnExit();
			InputStream in = parent.getInputStream(uri);
			OutputStream out = new FileOutputStream(tempFile);
			ArchiveUtil.copy(in, out);
			return new TempZipFileLoadStrategyImpl(tempFile);
		} catch (IOException ex) {
			ArchiveUtil.inform(CommonArchiveResourceHandler.getString("make_temp_file_WARN_", (new Object[]{uri})) + ex.getLocalizedMessage()); //$NON-NLS-1$ = "Warning: Unable to create temp file for {0}.  This will impact performance."
		}
		return null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public WARFile createWARFileInitialized(java.lang.String uri) {
		WARFile warFile = createWARFile();
		initializeNewWARFile(warFile, uri);
		return warFile;
	}

	protected ArchiveOptions defaultOptions(LoadStrategy aLoadStrategy) {
		ArchiveOptions options = new ArchiveOptions();
		options.setLoadStrategy(aLoadStrategy);
		return options;
	}

	public static CommonarchiveFactory getActiveFactory() {
		CommonarchivePackage pkg = getPackage();
		if (pkg != null)
			return pkg.getCommonarchiveFactory();
		return null;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public java.lang.String[] getManifestClassPathValues(java.lang.String uri) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		String[] result = anArchive.getManifest().getClassPathTokenized();
		anArchive.close();
		return result;
	}

	/**
	 * Insert the method's description here. Creation date: (02/23/01 2:35:55 PM)
	 * 
	 * @return java.util.Map
	 */
	public java.util.Map getOpenArchives() {
		if (openArchives == null)
			openArchives = new WeakHashMap();
		return openArchives;
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public Set getOpenArchivesDependingOn(Archive anArchive) {
		Set dependents = new HashSet();
		Iterator opened = getOpenArchives().keySet().iterator();
		while (opened.hasNext()) {
			Archive openedArchive = (Archive) opened.next();
			if (openedArchive == anArchive)
				continue;
			if (!openedArchive.isIndexed())
				//**********Optimization***********
				//If the file list has never been built for the archive, we don't want to trigger
				// it now,
				//and we are sure that the archive is not preventing the parameter from closing
				continue;
			List files = openedArchive.getFiles();
			for (int i = 0; i < files.size(); i++) {
				File aFile = (File) files.get(i);
				if (aFile.getLoadingContainer() == anArchive) {
					Archive outermost = openedArchive;
					Container c = openedArchive.getContainer();
					while (c != null && c.isArchive()) {
						outermost = (Archive) c;
						c = c.getContainer();
					}
					dependents.add(outermost);
				}
			}
		}
		//Elements from one of the children (e.g., a module file in an ear) may have been copied to
		//another archive
		List nestedArchives = anArchive.getArchiveFiles();
		for (int i = 0; i < nestedArchives.size(); i++) {
			dependents.addAll(getOpenArchivesDependingOn((Archive) nestedArchives.get(i)));
		}
		return dependents;
	}

	protected static void initPrereqs() {
		org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveInit.invokePrereqInits(true);
		ArchiveTypeDiscriminator disc = RootArchiveTypeDescriminatorImpl.singleton();
		disc.addChild(Ear12ImportStrategyImpl.getDiscriminator());
		disc.addChild(War22ImportStrategyImpl.getDiscriminator());
		disc.addChild(AppClient12ImportStrategyImpl.getDiscriminator());
		disc.addChild(RarImportStrategyImpl.getDiscriminator());
		disc.addChild(RootEJBJarDescriminatorImpl.singleton());
	}

	public void initializeNewApplicationClientFile(ApplicationClientFile anArchive, String uri) {
		initializeNewModuleFile(anArchive, uri);
	}

	public void initializeNewArchive(Archive anArchive, String uri) {
		anArchive.setURI(uri);
		anArchive.setSize(0);
		anArchive.setLastModified(System.currentTimeMillis());
		anArchive.setDirectoryEntry(false);
		anArchive.setLoadStrategy(createEmptyLoadStrategy());
	}

	public void initializeNewEARFile(EARFile anArchive, String uri) {
		initializeNewModuleFile(anArchive, uri);
	}

	public void initializeNewEJBJarFile(EJBJarFile anArchive, String uri) {
		initializeNewModuleFile(anArchive, uri);
	}

	public void initializeNewModuleFile(ModuleFile anArchive, String uri) {
		initializeNewArchive(anArchive, uri);
		anArchive.makeDeploymentDescriptorResource();
	}

	public void initializeNewRARFile(RARFile anArchive, String uri) {
		initializeNewModuleFile(anArchive, uri);
	}

	public void initializeNewWARFile(WARFile anArchive, String uri) {
		initializeNewModuleFile(anArchive, uri);
	}

	public void initializeNewApplicationClientFile(ApplicationClientFile anArchive, String uri, ArchiveOptions options) {
		initializeNewModuleFile(anArchive, uri, options);
	}

	public void initializeNewArchive(Archive anArchive, String uri, ArchiveOptions options) {
		if (options.getLoadStrategy() == null) {
			try {
				options.setLoadStrategy(createEmptyLoadStrategy());
			} catch (Exception ex) {
				Logger.getLogger().logError(ex);
			}
		}

		anArchive.setURI(uri);
		anArchive.setSize(0);
		anArchive.setLastModified(System.currentTimeMillis());
		anArchive.setDirectoryEntry(false);
		anArchive.setLoadStrategy(options.getLoadStrategy());
		anArchive.setOptions(options);

	}

	public void initializeNewEARFile(EARFile anArchive, String uri, ArchiveOptions options) {
		initializeNewModuleFile(anArchive, uri, options);
	}

	public void initializeNewEJBJarFile(EJBJarFile anArchive, String uri, ArchiveOptions options) {
		initializeNewModuleFile(anArchive, uri, options);
	}

	public void initializeNewModuleFile(ModuleFile anArchive, String uri, ArchiveOptions options) {
		initializeNewArchive(anArchive, uri, options);
		anArchive.makeDeploymentDescriptorResource();
	}

	public void initializeNewRARFile(RARFile anArchive, String uri, ArchiveOptions options) {
		initializeNewModuleFile(anArchive, uri, options);
	}

	public void initializeNewWARFile(WARFile anArchive, String uri, ArchiveOptions options) {
		initializeNewModuleFile(anArchive, uri, options);
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public ApplicationClientFile openApplicationClientFile(ArchiveOptions options, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		ArchiveTypeDiscriminator disc = AppClient12ImportStrategyImpl.getDiscriminator();
		return (ApplicationClientFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public ApplicationClientFile openApplicationClientFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		ArchiveTypeDiscriminator disc = AppClient12ImportStrategyImpl.getDiscriminator();
		return (ApplicationClientFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public ApplicationClientFile openApplicationClientFile(String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		ArchiveTypeDiscriminator disc = AppClient12ImportStrategyImpl.getDiscriminator();
		return (ApplicationClientFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public Archive openArchive(ArchiveOptions options, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		return openSpecificArchive(anArchive, RootArchiveTypeDescriminatorImpl.singleton());
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public Archive openArchive(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		return openSpecificArchive(anArchive, RootArchiveTypeDescriminatorImpl.singleton());
	}

	/**
	 * openArchive(String uri) - open the archive by the passed name, setting up the appropriate
	 * strategies. Name may be a path to a jar, a zip, or a directory return the appropriate Archive
	 * type
	 */
	public Archive openArchive(java.lang.String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		return openSpecificArchive(anArchive, RootArchiveTypeDescriminatorImpl.singleton());
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public Archive openArchive(String uri, String extraClassPath) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		anArchive.setExtraClasspath(extraClassPath);
		return openSpecificArchive(anArchive, RootArchiveTypeDescriminatorImpl.singleton());
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EARFile openEARFile(ArchiveOptions options, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		ArchiveTypeDiscriminator disc = Ear12ImportStrategyImpl.getDiscriminator();
		return (EARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EARFile openEARFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		ArchiveTypeDiscriminator disc = Ear12ImportStrategyImpl.getDiscriminator();
		return (EARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EARFile openEARFile(String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		ArchiveTypeDiscriminator disc = Ear12ImportStrategyImpl.getDiscriminator();
		return (EARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EJBJarFile openEJB11JarFile(ArchiveOptions options, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		ArchiveTypeDiscriminator disc = EjbJar11ImportStrategyImpl.getDiscriminator();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJB11JarFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		ArchiveTypeDiscriminator disc = EjbJar11ImportStrategyImpl.getDiscriminator();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJB11JarFile(String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		ArchiveTypeDiscriminator disc = EjbJar11ImportStrategyImpl.getDiscriminator();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EJBJarFile openEJBJarFile(ArchiveOptions options, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		RootEJBJarDescriminatorImpl disc = (RootEJBJarDescriminatorImpl) RootEJBJarDescriminatorImpl.singleton();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJBJarFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		RootEJBJarDescriminatorImpl disc = (RootEJBJarDescriminatorImpl) RootEJBJarDescriminatorImpl.singleton();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJBJarFile(LoadStrategy aLoadStrategy, String uri, String extraClassPath) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		anArchive.setExtraClasspath(extraClassPath);
		RootEJBJarDescriminatorImpl disc = (RootEJBJarDescriminatorImpl) RootEJBJarDescriminatorImpl.singleton();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJBJarFile(String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		RootEJBJarDescriminatorImpl disc = (RootEJBJarDescriminatorImpl) RootEJBJarDescriminatorImpl.singleton();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJBJarFile(String uri, String extraClassPath) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		anArchive.setExtraClasspath(extraClassPath);
		RootEJBJarDescriminatorImpl disc = (RootEJBJarDescriminatorImpl) RootEJBJarDescriminatorImpl.singleton();
		return (EJBJarFile) openSpecificArchive(anArchive, disc);
	}

	public Archive openNestedArchive(LooseArchive loose, Archive parent) throws OpenFailureException {
		String uri = loose.getUri();
		try {
			LoadStrategy childStrategy = createLoadStrategy(loose.getBinariesPath());
			childStrategy.setLooseArchive(loose);
			ArchiveOptions options = parent.getOptions().cloneWith(childStrategy, loose.getUri());
			return primOpenArchive(options, uri);
		} catch (IOException ex) {
			throw new OpenFailureException(CommonArchiveResourceHandler.getString("open_nested_EXC_", (new Object[]{uri, parent.getURI()})), ex); //$NON-NLS-1$ = "Could not open the nested archive "{0}" in "{1}""
		}
	}

	/**
	 * @see CommonarchiveFactory
	 */
	public Archive openNestedArchive(String uri, Archive parent) throws OpenFailureException {
		try {
			LoadStrategy childStrategy = createChildLoadStrategy(uri, parent.getLoadStrategy());
			ArchiveOptions options = parent.getOptions().cloneWith(childStrategy, uri);
			if (options.shouldDiscriminateNestedArchives())
				return openArchive(options, uri);
			return primOpenArchive(options, uri);
		} catch (IOException ex) {
			throw new OpenFailureException(CommonArchiveResourceHandler.getString("open_nested_EXC_", (new Object[]{uri, parent.getURI()})), ex); //$NON-NLS-1$ = "Could not open the nested archive "{0}" in "{1}""
		}
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public RARFile openRARFile(ArchiveOptions options, java.lang.String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		ArchiveTypeDiscriminator disc = RarImportStrategyImpl.getDiscriminator();
		return (RARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public RARFile openRARFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		ArchiveTypeDiscriminator disc = RarImportStrategyImpl.getDiscriminator();
		return (RARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public RARFile openRARFile(String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		ArchiveTypeDiscriminator disc = RarImportStrategyImpl.getDiscriminator();
		return (RARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * openReadOnlyDirectory method comment.
	 */
	public ReadOnlyDirectory openReadOnlyDirectory(java.lang.String uri) throws java.io.IOException {
		java.io.File aFile = new java.io.File(uri);
		if (!aFile.exists())
			throw new FileNotFoundException(CommonArchiveResourceHandler.getString("could_not_find_dir_EXC_", (new Object[]{uri}))); //$NON-NLS-1$ = "Unable to open directory "
		if (!aFile.isDirectory())
			throw new IOException(CommonArchiveResourceHandler.getString("not_a_dir_EXC_", (new Object[]{uri}))); //$NON-NLS-1$ = "Unable to open directory because file is not a directory :"
		LoadStrategy strategy = new ReadOnlyDirectoryLoadStrategyImpl(uri);
		ReadOnlyDirectory container = createReadOnlyDirectory();
		container.setURI(uri);
		container.setLoadStrategy(strategy);
		container.setLastModified(aFile.lastModified());
		return container;
	}

	/**
	 * Take the primitive archive and run it through the list of discriminators to convert it to the
	 * correct specialized type; after after conversion, tell the archive to initalize itself if
	 * necessary.
	 */
	protected Archive openSpecificArchive(Archive anArchive, ArchiveTypeDiscriminator disc) throws OpenFailureException {
		if (!disc.canImport(anArchive)) {
			anArchive.close();
			throw new OpenFailureException(disc.getUnableToOpenMessage());
		}
		Archive specificArchive = disc.openArchive(anArchive);
		specificArchive.initializeAfterOpen();
		return specificArchive;
	}

	/**
	 * Special case for ejb jar files, because of the need to support non-compliant 1.0 jars
	 */
	protected Archive openSpecificArchive(Archive anArchive, RootEJBJarDescriminatorImpl disc) throws OpenFailureException {
		Archive specific = openSpecificArchive(anArchive, (ArchiveTypeDiscriminator) disc);
		if (specific == anArchive) {
			//The discriminator failed to convert the archve to an ejb jar file
			anArchive.close();
			throw new OpenFailureException(disc.getUnableToOpenMessage());
		}
		return specific;
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public WARFile openWARFile(ArchiveOptions options, java.lang.String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(options, uri);
		ArchiveTypeDiscriminator disc = War22ImportStrategyImpl.getDiscriminator();
		return (WARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public WARFile openWARFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(aLoadStrategy, uri);
		ArchiveTypeDiscriminator disc = War22ImportStrategyImpl.getDiscriminator();
		return (WARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public WARFile openWARFile(String uri) throws OpenFailureException {
		Archive anArchive = primOpenArchive(uri);
		ArchiveTypeDiscriminator disc = War22ImportStrategyImpl.getDiscriminator();
		return (WARFile) openSpecificArchive(anArchive, disc);
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public Archive primOpenArchive(ArchiveOptions options, String uri) throws OpenFailureException {
		if (options.getLoadStrategy() == null) {
			try {
				options.setLoadStrategy(createLoadStrategy(uri));
			} catch (IOException ex) {
				throw new OpenFailureException(CommonArchiveResourceHandler.getString("could_not_open_EXC_", (new Object[]{uri})), ex); //$NON-NLS-1$ = "Could not open "
			}
		}
		Archive anArchive = createArchive();
		anArchive.setURI(uri);
		anArchive.setOriginalURI(uri);
		anArchive.setLoadStrategy(options.getLoadStrategy());
		anArchive.setOptions(options);
		ArchiveTypeDiscriminatorRegistry.getInstance().contributeTypes(anArchive);
		return anArchive;
	}

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public Archive primOpenArchive(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException {
		return primOpenArchive(defaultOptions(aLoadStrategy), uri);
	}

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public Archive primOpenArchive(String uri) throws OpenFailureException {
		return primOpenArchive(new ArchiveOptions(), uri);
	}

	protected void setOpenArchives(java.util.Map newOpenArchives) {
		openArchives = newOpenArchives;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WARFile createWARFileGen() {
		WARFileImpl warFile = new WARFileImpl();
		return warFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EJBJarFile createEJBJarFileGen() {
		EJBJarFileImpl ejbJarFile = new EJBJarFileImpl();
		return ejbJarFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ApplicationClientFile createApplicationClientFileGen() {
		ApplicationClientFileImpl applicationClientFile = new ApplicationClientFileImpl();
		return applicationClientFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EARFile createEARFileGen() {
		EARFileImpl earFile = new EARFileImpl();
		return earFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public RARFile createRARFileGen() {
		RARFileImpl rarFile = new RARFileImpl();
		return rarFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public File createFile() {
		FileImpl file = new FileImpl();
		return file;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ReadOnlyDirectory createReadOnlyDirectory() {
		ReadOnlyDirectoryImpl readOnlyDirectory = new ReadOnlyDirectoryImpl();
		return readOnlyDirectory;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public CommonarchivePackage getCommonarchivePackage() {
		return (CommonarchivePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static CommonarchivePackage getPackage() {
		return CommonarchivePackage.eINSTANCE;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public EJBModuleRef createEJBModuleRefGen() {
		EJBModuleRefImpl ejbModuleRef = new EJBModuleRefImpl();
		return ejbModuleRef;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public WebModuleRef createWebModuleRefGen() {
		WebModuleRefImpl webModuleRef = new WebModuleRefImpl();
		return webModuleRef;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ClientModuleRef createClientModuleRefGen() {
		ClientModuleRefImpl clientModuleRef = new ClientModuleRefImpl();
		return clientModuleRef;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public ConnectorModuleRef createConnectorModuleRefGen() {
		ConnectorModuleRefImpl connectorModuleRef = new ConnectorModuleRefImpl();
		return connectorModuleRef;
	}

	public ClientModuleRef createClientModuleRef(ApplicationClientFile clientFile) {
		ClientModuleRef ref = createClientModuleRef();
		ref.setModuleFile(clientFile);
		return ref;
	}

	/*
	 * @see CommonarchiveFactory#createConnectorModuleRef(RARFile)
	 */
	public ConnectorModuleRef createConnectorModuleRef(RARFile rarFile) {
		ConnectorModuleRef ref = createConnectorModuleRef();
		ref.setModuleFile(rarFile);
		return ref;

	}

	/*
	 * @see CommonarchiveFactory#createEJBModuleRef(EJBJarFile)
	 */
	public EJBModuleRef createEJBModuleRef(EJBJarFile ejbJarFile) {
		EJBModuleRef ref = createEJBModuleRef();
		ref.setModuleFile(ejbJarFile);
		return ref;
	}

	/*
	 * @see CommonarchiveFactory#createWebModuleRef(WARFile)
	 */
	public WebModuleRef createWebModuleRef(WARFile warFile) {
		WebModuleRef ref = createWebModuleRef();
		ref.setModuleFile(warFile);
		return ref;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Archive createArchive() {
		ArchiveImpl archive = new ArchiveImpl();
		return archive;
	}

	public ApplicationClientFile createApplicationClientFile() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createApplicationClientFile();
	}


	public ClientModuleRef createClientModuleRef() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createClientModuleRef();
	}

	public ConnectorModuleRef createConnectorModuleRef() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createConnectorModuleRef();
	}

	public EARFile createEARFile() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createEARFile();
	}

	public EJBJarFile createEJBJarFile() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createEJBJarFile();
	}

	public EJBModuleRef createEJBModuleRef() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createEJBModuleRef();
	}

	public RARFile createRARFile() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createRARFile();
	}

	public WARFile createWARFile() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createWARFile();
	}

	public WebModuleRef createWebModuleRef() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory().createWebModuleRef();
	}

	/**
	 * @return
	 */
	public CommonarchiveFactory getDelegate() {
		return delegate;
	}

}
