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
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ReopenException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.RuntimeClasspathEntry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.SaveFilter;
import org.eclipse.jst.j2ee.commonarchivecore.internal.looseconfig.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;


/**
 * @generated
 */
public interface Archive extends Container {

	public static final int EXPAND_NONE = 0;
	public static final int EXPAND_WAR_FILES = 1 << 1;
	public static final int EXPAND_EAR_FILES = 1 << 2;
	public static final int EXPAND_EJBJAR_FILES = 1 << 3;
	public static final int EXPAND_APPCLIENT_FILES = 1 << 4;
	public static final int EXPAND_ARCHIVES = 1 << 5;
	public static final int EXPAND_RAR_FILES = 1 << 6;
	public static final int EXPAND_ALL = (1 << 1) | (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5) | (1 << 6);

	public Archive addCopy(Archive anArchive) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;

	public File addCopy(File aFile) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;

	/**
	 * Get a flattened list from the directory, then addCopy the list
	 * 
	 * @throws com.ibm.etools.archive.exception.DuplicateObjectException
	 *             if a file with a uri that equals one of the nested files in the directory exists
	 * 
	 * @return java.util.List the copied files that were added to the archive
	 */
	public List addCopy(ReadOnlyDirectory dir) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;

	public List addCopyFiles(List listOfFiles) throws org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;

	public void addOrReplaceMofResource(Resource aResource);

	/**
	 * @deprecated Use {@link #getDependentOpenArchives()}
	 * 
	 * If any opened archive contains files that have this archive as its loading container, return
	 * false; otherwise return true. This method supports the following scenario: open jar A. create
	 * jar B. Copy files from A to B. Attempt to close jar A before saving jar B. Then attempt to
	 * save B, and the save fails because A is closed. This method allows client code to test for
	 * dependent open archives before saving the source archive. If this method returns false, the
	 * solution is to either close or save B before closing A.
	 */
	public boolean canClose();

	/**
	 * Closes the load strategy for this archive and closes all contained archives; WARNING: If
	 * files have been copied from this archive to another archive, then the destination archive
	 * should be saved or closed before this archive can be safely closed; to test if this archive
	 * can safely close invoke {@link #canClose()}
	 */
	public void close();

	/**
	 * Save this archive as an expanded directory where the flags is the result of bitwise or of the
	 * specified types to be expanded; example:
	 * <code>anEarFile.saveAsDirectory(anEarFile.EXPAND_WAR_FILES | anEarFile.EXPAND_EJBJARFILES)</code>;
	 * 
	 * If this archive was loaded from the same uri as it is being extracted to, the orignal will be
	 * deleted and replaced with the directory
	 * 
	 * @throws SaveFailureException
	 *             if an exception occurs while saving
	 * 
	 * @throws ReopenException
	 *             if an exception occurs while re-syncing the archive to the newly saved
	 *             destination
	 */
	public void extract(int expansionFlags) throws SaveFailureException, ReopenException;

	/**
	 * For performance, save the archive without reopening; Further operations on this instance
	 * without first calling {@link #reopen}will yield unexpected results.
	 * 
	 * @see #extract(int)
	 */
	public void extractNoReopen(int expansionFlags) throws SaveFailureException;

	/**
	 * Save this archive as a directory using the specified uri
	 * 
	 * The archive will not be renamed
	 * 
	 * @throws SaveFailureException
	 *             if an exception occurs while saving
	 * 
	 * @see #extract(int)
	 */
	public void extractTo(String uri, int expansionFlags) throws SaveFailureException;

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return java.lang.ClassLoader
	 */
	public java.lang.ClassLoader getArchiveClassLoader();

	/**
	 * Return a list of files in the ARchive that start with the prefix
	 */
	public java.util.List filterFilesByPrefix(String prefix);

	/**
	 * Return a list of files in the Archive excluding any file that starts with one of the prefixes
	 */
	public java.util.List filterFilesWithoutPrefix(String[] prefixes);

	/**
	 * Returns a filtered list of archive files; adds will not be reflected; use
	 * 
	 * @link Archive#add(File)
	 */
	public List getArchiveFiles();

	public ResourceSet getResourceSet();

	/**
	 * Return a list of all root level (non-nested) opened archives containing files that have this
	 * archive as its loading container; the set will be empty if no such opened archive exists.
	 * This method supports the following scenario: open jar A. create jar B. Copy files from A to
	 * B. Attempt to close jar A before saving jar B. Then attempt to save B, and the save fails
	 * because A is closed. This method allows client code to test for dependent open archives
	 * before saving the source archive. If the return value is not empty, the solution is to either
	 * close or save B before closing A.
	 */
	public Set getDependentOpenArchives();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getExtraClasspath();

	/**
	 * Used internally by the framework, specifically as an optimization when saving/exploding
	 * archives with nested archives
	 */
	public FileIterator getFilesForSave() throws IOException;

	public Collection getLoadedMofResources();

	public ArchiveManifest getManifest();

	public Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException;

	public org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions getOptions();

	/**
	 * @see LoadStrategy#getResourceInputStream(String)
	 */
	public InputStream getResourceInputStream(String uri) throws IOException;

	/**
	 * Used for websphere runtime where archives are on disk (not nested in jars)
	 * 
	 * @return list of absolute paths that represents this archive only, and in the case of
	 *         WARFiles, the nested loadable contents.
	 */
	public RuntimeClasspathEntry[] getLocalRuntimeClassPath();

	/**
	 * Used for websphere runtime where archives are on disk (not nested in jars) to get the
	 * recursive behavior, the Archive must belong to an EAR file
	 * 
	 * @return list of absolute paths that represents this archive, all it's prereqs, recursive.
	 */
	public RuntimeClasspathEntry[] getFullRuntimeClassPath();

	/**
	 * Used for websphere runtime where archives are on disk (not nested in jars) to get the
	 * recursive behavior, the Archive must belong to an EAR file
	 * 
	 * @return list of absolute paths that represents the dependencies of this Archive, all it's
	 *         prereqs, recursive.
	 */
	public RuntimeClasspathEntry[] getDependencyClassPath();

	/**
	 * Return the absolute path of the root from which meta resources get loaded
	 */
	public String getResourcesPath() throws FileNotFoundException;

	/**
	 * Return the absolute path of the root from which classes and properties are loaded
	 */
	public String getBinariesPath() throws FileNotFoundException;

	/**
	 * Optional filter for saving a subset of files; filter will be applied for all save and extract
	 * invokations
	 */
	public SaveFilter getSaveFilter();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return com.ibm.etools.archive.SaveStrategy
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy getSaveStrategy();

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getXmlEncoding();

	/**
	 * Return whether this Archive has
	 * 
	 * @other on it's classpath, either directly or transitively
	 * @param Archive
	 *            other - another archive in the same EAR file
	 */
	public boolean hasClasspathVisibilityTo(Archive other);

	/**
	 * Internal API; Used for implementation of {@link #hasClasspathVisibilityTo(Archive)}
	 * 
	 * @param Archive
	 *            other - another archive in the same EAR file
	 * @param Set
	 *            visited - the set of archives already visited
	 */
	public boolean hasClasspathVisibilityTo(Archive other, Set visited, EARFile ear);

	/**
	 * Perform any necessary initialization after the archive has been opened.
	 */
	public void initializeAfterOpen();

	/**
	 * Used internally by the load strategy
	 */
	public void initializeClassLoader();

	/**
	 * An item is considered a duplicate if the archive contains a file or loaded mof resource with
	 * the uri, or if the uri is equal to the manifest uri
	 */
	public boolean isDuplicate(String uri);

	/**
	 * Used as an optimization at copy time
	 */
	public boolean isManifestSet();

	public boolean isMofResourceLoaded(String uri);

	/**
	 * Used internally for dispatch between the archive and the load strategy when building the file
	 * list; clients should not need to call this method.
	 */
	public boolean isNestedArchive(String uri);

	/**
	 * Indicates whether the archive is still opened for read; if not, IOExceptions could be thrown
	 * on attempts to get input streams on file entries. reopen() will cause this archive and its
	 * nested archives to rebuild their load strategies
	 */
	public boolean isOpen();

	/**
	 * Create a new mof resource and add it to the resource set of the context of this archive; all
	 * resources in memory are saved when the archive is saved
	 * 
	 * @throws DuplicateObjectException
	 *             if a resource already exists in this archive having the uri
	 */
	public Resource makeMofResource(String uri) throws DuplicateObjectException;

	/**
	 * Create a new mof resource and add it to the resource set of the context of this archive; all
	 * resources in memory are saved when the archive is saved
	 * 
	 * @throws DuplicateObjectException
	 *             if a resource already exists in this archive having the uri
	 */
	public Resource makeMofResource(String uri, EList extent) throws DuplicateObjectException;

	/**
	 * Used internally for dispatch between the archive and the load strategy when building the file
	 * list; clients should not need to call this method.
	 */
	public Archive openNestedArchive(String uri) throws OpenFailureException;

	/**
	 * Used internally for dispatch between the archive and the load strategy when building the file
	 * list; clients should not need to call this method.
	 */
	public Archive openNestedArchive(LooseArchive loose) throws OpenFailureException;

	/**
	 * Set the value of the extra class path with no refresh of the class loader
	 */
	public void primSetExtraClasspath(java.lang.String newExtraClasspath);

	public void remove(File aFile);

	/**
	 * Used internally for "re-syncing" an archive after save; clients normally should not need this
	 * method
	 */
	public void reopen() throws ReopenException;

	/**
	 * Used internally for reopening nested archives; clients normally should not need this method
	 */
	public void reopen(Archive parent) throws ReopenException;

	/**
	 * Save this archive as a jar file with the uri of the archive;
	 * 
	 * @throws SaveFailureException
	 *             if an exception occurs while saving
	 * 
	 * @throws ReopenException
	 *             if an exception occurs while re-syncing the archive to the newly saved
	 *             destination
	 */
	public void save() throws SaveFailureException, ReopenException;

	/**
	 * Save this archive using the save strategy specified
	 * 
	 * @throws SaveFailureException
	 *             if an exception occurs while saving
	 */
	public void save(SaveStrategy aStrategy) throws SaveFailureException;

	/**
	 * Save this archive as a jar file using uri provided; If the uri is different than the URI of
	 * this archive, the uri of this archive will change to the new uri (for reopen)
	 * 
	 * @throws SaveFailureException
	 *             if an exception occurs while saving
	 * 
	 * @throws ReopenException
	 *             if an exception occurs while re-syncing the archive to the newly saved
	 *             destination
	 */
	public void saveAs(String uri) throws SaveFailureException, ReopenException;

	/**
	 * For performance, save the archive without reopening; Further operations on this instance
	 * without first calling {@link #reopen}will yield unexpected results.
	 * 
	 * @see #saveAs(String)
	 */
	public void saveAsNoReopen(String uri) throws SaveFailureException;

	/**
	 * For performance, save the archive without reopening; Further operations on this instance
	 * without first calling {@link #reopen}will yield unexpected results.
	 * 
	 * @see #save()
	 */
	public void saveNoReopen() throws SaveFailureException;

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newArchiveClassLoader
	 *            java.lang.ClassLoader
	 */
	public void setArchiveClassLoader(java.lang.ClassLoader newArchiveClassLoader);

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newExtraClasspath
	 *            java.lang.String
	 */
	public void setExtraClasspath(java.lang.String newExtraClasspath);

	public void setManifest(ArchiveManifest newManifest);

	public void setManifest(java.util.jar.Manifest aManifest);

	/**
	 * Sets the Class-path manifest entry, rebuilds the class loader, and refreshes any reflected
	 * java classes
	 */
	public void setManifestClassPathAndRefresh(String classpath);

	public void setOptions(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions newOptions);

	/**
	 * Optional filter for saving a subset of files; filter will be applied for all save and extract
	 * invokations
	 */
	public void setSaveFilter(SaveFilter aFilter);

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newSaveStrategy
	 *            com.ibm.etools.archive.SaveStrategy
	 */
	public void setSaveStrategy(org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy newSaveStrategy);

	/**
	 * Insert the method's description here. Creation date: (11/29/00 6:35:08 PM)
	 * 
	 * @param newXmlEncoding
	 *            java.lang.String
	 */
	public void setXmlEncoding(java.lang.String newXmlEncoding);

	/**
	 * Determine whether java reflection should be set up for this archive
	 */
	public boolean shouldUseJavaReflection();

	/**
	 * Returns the value of the '<em><b>Types</b></em>' attribute list. The list contents are
	 * of type {@link java.lang.String}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Types</em>' attribute list isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Types</em>' attribute list.
	 * @see org.eclipse.jst.j2ee.internal.commonarchivecore.CommonarchivePackage#getArchive_Types()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getTypes();

	boolean isType(String type);
}
