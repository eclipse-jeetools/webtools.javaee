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
package org.eclipse.jst.j2ee.commonarchivecore.internal;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

import org.eclipse.emf.ecore.EFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;


/**
 * @generated
 */
public interface CommonarchiveFactory extends EFactory {

	/**
	 * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	CommonarchiveFactory eINSTANCE = new org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl();

	/**
	 * Tell the factory that an archive has been opened; the factory maintains a weak set of all the
	 * open archives to determine if another archive can be closed.
	 */
	public void archiveClosed(Archive aClosedArchive);

	/**
	 * Tell the factory that an archive has been opened; the factory maintains a weak set of all the
	 * open archives to determine if another archive can be closed.
	 */
	public void archiveOpened(Archive anOpenArchive);

	/**
	 * @deprecated Use {@link #getOpenArchivesDependingOn(Archive)}
	 * 
	 * If any opened archive contains files that have the parameter as its loading container, return
	 * false; otherwise return true. This method supports the following scenario: open jar A. create
	 * jar B. Copy files from A to B. Attempt to close jar A before saving jar B. Then attempt to
	 * save B, and the save fails because A is closed. This method allows client code to test for
	 * dependent open archives before saving the source archive. If this method returns false, the
	 * solution is to either close or save B before closing A.
	 */
	public boolean canClose(Archive anArchive);

	/**
	 * Close any open archives and delete the temp files associated with nested archives. Due to
	 * limitations in the deleteOnExit() method of file, in 1.2.2 there is no way to ensure these
	 * files get deleted. Client code should use good practice by calling {@link Archive#close}when
	 * finished with an Archive instance, rather than just discard an instance with open file
	 * handles and wait for it to be gc'd. Beyond that, program code compiled for 1.3 can (and
	 * should) implement the following shutdown hook: <code>
	 * 	Runtime.getRuntime().addShutdownHook(new Thread() {
	 public void run() {
	 ((CommonarchivePackage)EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory().closeOpenArchives();
	 }
	 });</code>
	 */
	public void closeOpenArchives();

	public Archive copy(Archive anArchive);

	public ModuleFile copy(ModuleFile aModuleFile);

	/**
	 * Creates a new archive for editing, and initializes it appropriately (adds an empty deployment
	 * descriptor)
	 */
	public ApplicationClientFile createApplicationClientFileInitialized(String uri);

	/**
	 * Creates a new archive for editing, and initializes it appropriately
	 */
	public Archive createArchiveInitialized(String uri);

	/**
	 * Used internally; clients usually should not need this method
	 */
	public LoadStrategy createChildLoadStrategy(String uri, LoadStrategy parent) throws java.io.IOException, java.io.FileNotFoundException;

	/**
	 * Creates a new archive for editing, and initializes it appropriately (adds an empty deployment
	 * descriptor)
	 */
	public EARFile createEARFileInitialized(String uri);

	/**
	 * Creates a new archive for editing, and initializes it appropriately (adds an empty deployment
	 * descriptor)
	 */
	public EJBJarFile createEJBJarFileInitialized(String uri);

	/**
	 * Create an initialized archive based on the given URI and options
	 */
	public Archive createArchiveInitialized(ArchiveOptions options, java.lang.String uri);

	/**
	 * Create an initialized EAR based on the given URI and options
	 */
	public EARFile createEARFileInitialized(ArchiveOptions options, java.lang.String uri);

	/**
	 * Create an initialized EJB based on the given URI and options
	 */
	public EJBJarFile createEJBJarFileInitialized(ArchiveOptions options, java.lang.String uri);

	/**
	 * Initialize archive based on the options
	 */
	public void initializeNewApplicationClientFile(ApplicationClientFile anArchive, String uri, ArchiveOptions options);

	/**
	 * Initialize archive based on the options
	 */
	public void initializeNewArchive(Archive anArchive, String uri, ArchiveOptions options);

	/**
	 * Initialize archive based on the options
	 */
	public void initializeNewEARFile(EARFile anArchive, String uri, ArchiveOptions options);

	/**
	 * Initialized archive based on the options
	 */
	public void initializeNewEJBJarFile(EJBJarFile anArchive, String uri, ArchiveOptions options);

	/**
	 * Initialized archive based on the options
	 */
	public void initializeNewModuleFile(ModuleFile anArchive, String uri, ArchiveOptions options);

	/**
	 * Initialized archive based on the options
	 */
	public void initializeNewRARFile(RARFile anArchive, String uri, ArchiveOptions options);

	/**
	 * Initialized archive based on the options
	 */
	public void initializeNewWARFile(WARFile anArchive, String uri, ArchiveOptions options);

	/**
	 * Returns a NullLoadStrategyImpl; used for new archives
	 */
	LoadStrategy createEmptyLoadStrategy();

	/**
	 * Helper method to dynamically build a load strategy from the file system. Determines whether
	 * the uri points to a jar file or directory and returns the appropriate strategy
	 */
	public LoadStrategy createLoadStrategy(String uri) throws FileNotFoundException, IOException;

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public RARFile createRARFileInitialized(java.lang.String uri);

	/**
	 * Creates a new archive for editing, and initializes it appropriately (adds an empty deployment
	 * descriptor)
	 */
	public WARFile createWARFileInitialized(String uri);

	/**
	 * Helper method to introspect an archive and get it's class path entries before fully opening
	 * the archive; needed because we may need extra classpath info to be able to open the 1.0 file
	 * and deserialize its deployment descriptor
	 * 
	 * @return a tokenized array of class path components
	 */
	public String[] getManifestClassPathValues(String uri) throws OpenFailureException;

	/**
	 * Return a list of all root level (non-nested) opened archives containing files that have the
	 * parameter as its loading container; the set will be empty if no such opened archive exists.
	 * This method supports the following scenario: open jar A. create jar B. Copy files from A to
	 * B. Attempt to close jar A before saving jar B. Then attempt to save B, and the save fails
	 * because A is closed. This method allows client code to test for dependent open archives
	 * before saving the source archive. If the return value is not empty, the solution is to either
	 * close or save B before closing A.
	 */
	public Set getOpenArchivesDependingOn(Archive anArchive);

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public ApplicationClientFile openApplicationClientFile(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public ApplicationClientFile openApplicationClientFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	public ApplicationClientFile openApplicationClientFile(String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public Archive openArchive(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public Archive openArchive(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	/**
	 * open the archive by the passed uri
	 * 
	 * @return the appropriate kind of archive
	 */
	public Archive openArchive(String uri) throws OpenFailureException;

	/**
	 * open the archive by the passed uri, and use the extraClassPath for java reflection, in
	 * addition to the manifest class-path; mostly used for ejb 1.0 jar files to be converted
	 * 
	 * @return the appropriate kind of archive
	 */
	public Archive openArchive(String uri, String extraClassPath) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EARFile openEARFile(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EARFile openEARFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	public EARFile openEARFile(String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EJBJarFile openEJB11JarFile(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJB11JarFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public EJBJarFile openEJB11JarFile(String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EJBJarFile openEJBJarFile(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EJBJarFile openEJBJarFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public EJBJarFile openEJBJarFile(LoadStrategy aLoadStrategy, String uri, String extraClassPath) throws OpenFailureException;

	public EJBJarFile openEJBJarFile(String uri) throws OpenFailureException;

	public EJBJarFile openEJBJarFile(String uri, String extraClassPath) throws OpenFailureException;

	/**
	 * Used internally for openning an Archive in an Archive
	 */
	public Archive openNestedArchive(String uri, Archive parent) throws OpenFailureException;

	/**
	 * Used internally for openning an Archive in an Archive
	 */
	public Archive openNestedArchive(LooseArchive loose, Archive parent) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public RARFile openRARFile(ArchiveOptions options, java.lang.String uri) throws OpenFailureException;

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public RARFile openRARFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	/**
	 * @see com.ibm.etools.commonarchive.CommonarchiveFactory
	 */
	public RARFile openRARFile(String uri) throws OpenFailureException;

	public ReadOnlyDirectory openReadOnlyDirectory(String uri) throws java.io.IOException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public WARFile openWARFile(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * open the archive, setting up the appropriate strategies, using the loadStrategy passed in;
	 * URI still necessary so the archive has a name, but it will not be used for io.
	 */
	public WARFile openWARFile(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	public WARFile openWARFile(String uri) throws OpenFailureException;

	/**
	 * Open the archive by the passed parameter, without attempting to determine what kind of
	 * archive it is
	 * 
	 * @return an instance of Archive, but not a subclass
	 */
	Archive primOpenArchive(String uri) throws OpenFailureException;

	Archive primOpenArchive(LoadStrategy aLoadStrategy, String uri) throws OpenFailureException;

	Archive primOpenArchive(ArchiveOptions options, String uri) throws OpenFailureException;

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return WARFile value
	 */
	WARFile createWARFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EJBJarFile value
	 */
	EJBJarFile createEJBJarFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ApplicationClientFile value
	 */
	ApplicationClientFile createApplicationClientFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EARFile value
	 */
	EARFile createEARFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return RARFile value
	 */
	RARFile createRARFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return Archive value
	 */
	Archive createArchive();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return File value
	 */
	File createFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ReadOnlyDirectory value
	 */
	ReadOnlyDirectory createReadOnlyDirectory();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	CommonarchivePackage getCommonarchivePackage();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return EJBModuleRef value
	 */
	EJBModuleRef createEJBModuleRef();

	/**
	 * Convienince method for wrapping a standalone EJB JAR file
	 */
	EJBModuleRef createEJBModuleRef(EJBJarFile ejbJarFile);


	/**
	 * @generated This field/method will be replaced during code generation
	 * @return WebModuleRef value
	 */
	WebModuleRef createWebModuleRef();

	/**
	 * Convienince method for wrapping a standalone WAR file
	 */
	WebModuleRef createWebModuleRef(WARFile warFile);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ClientModuleRef value
	 */
	ClientModuleRef createClientModuleRef();

	/**
	 * Convienince method for wrapping a standalone Application Client JAR file
	 */
	ClientModuleRef createClientModuleRef(ApplicationClientFile clientFile);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return ConnectorModuleRef value
	 */
	ConnectorModuleRef createConnectorModuleRef();

	/**
	 * Convienince method for wrapping a standalone RAR file
	 */
	ConnectorModuleRef createConnectorModuleRef(RARFile rarFile);
}
