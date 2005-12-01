/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal;



import java.io.InputStream;

import org.eclipse.emf.ecore.EObject;

/**
 * @generated
 */
public interface File extends EObject{

	/**
	 * Return the path up to the filename; e.g., from com/ibm/foo/bar.class, com/ibm/foo
	 */
	public String getDirectoryURI();

	public InputStream getInputStream() throws java.io.FileNotFoundException, java.io.IOException;

	/**
	 * Return the tail of the file path; e.g., from com/ibm/foo/bar.class, return bar.class
	 */
	public String getName();

	public boolean isApplicationClientFile();

	public boolean isArchive();

	public boolean isContainer();

	public boolean isEARFile();

	public boolean isFARFile();

	public boolean isEJBJarFile();

	public boolean isModuleFile();

	public boolean isRARFile();

	public boolean isReadOnlyDirectory();

	public boolean isWARFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the URI attribute
	 */
	String getURI();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param value
	 *            The new value of the URI attribute
	 */
	void setURI(String value);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the LastModified attribute
	 */
	long getLastModified();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#getLastModified <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Modified</em>' attribute.
	 * @see #isSetLastModified()
	 * @see #unsetLastModified()
	 * @see #getLastModified()
	 * @generated
	 */
	void setLastModified(long value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#getLastModified <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSetLastModified()
	 * @see #getLastModified()
	 * @see #setLastModified(long)
	 * @generated
	 */
	void unsetLastModified();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#getLastModified <em>Last Modified</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return whether the value of the '<em>Last Modified</em>' attribute is set.
	 * @see #unsetLastModified()
	 * @see #getLastModified()
	 * @see #setLastModified(long)
	 * @generated
	 */
	boolean isSetLastModified();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the Size attribute
	 */
	long getSize();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.jst.j2ee.internal.commonarchivecore.File#getSize <em>Size</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Size</em>' attribute.
	 * @see #isSetSize()
	 * @see #unsetSize()
	 * @see #getSize()
	 * @generated
	 */
	void setSize(long value);

	/**
	 * Unsets the value of the '
	 * {@link org.eclipse.jst.j2ee.internal.commonarchivecore.File#getSize <em>Size</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetSize()
	 * @see #getSize()
	 * @see #setSize(long)
	 * @generated
	 */
	void unsetSize();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#getSize <em>Size</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return whether the value of the '<em>Size</em>' attribute is set.
	 * @see #unsetSize()
	 * @see #getSize()
	 * @see #setSize(long)
	 * @generated
	 */
	boolean isSetSize();

	/**
	 * Returns the value of the '<em><b>Directory Entry</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Directory Entry</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Directory Entry</em>' attribute.
	 * @see #isSetDirectoryEntry()
	 * @see #unsetDirectoryEntry()
	 * @see #setDirectoryEntry(boolean)
	 * @see org.eclipse.jst.j2ee.internal.commonarchivecore.CommonarchivePackage#getFile_DirectoryEntry()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isDirectoryEntry();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#isDirectoryEntry <em>Directory Entry</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Directory Entry</em>' attribute.
	 * @see #isSetDirectoryEntry()
	 * @see #unsetDirectoryEntry()
	 * @see #isDirectoryEntry()
	 * @generated
	 */
	void setDirectoryEntry(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#isDirectoryEntry <em>Directory Entry</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isSetDirectoryEntry()
	 * @see #isDirectoryEntry()
	 * @see #setDirectoryEntry(boolean)
	 * @generated
	 */
	void unsetDirectoryEntry();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.commonarchivecore.internal.File#isDirectoryEntry <em>Directory Entry</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return whether the value of the '<em>Directory Entry</em>' attribute is set.
	 * @see #unsetDirectoryEntry()
	 * @see #isDirectoryEntry()
	 * @see #setDirectoryEntry(boolean)
	 * @generated
	 */
	boolean isSetDirectoryEntry();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The value of the OriginalURI attribute
	 */
	String getOriginalURI();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param value
	 *            The new value of the OriginalURI attribute
	 */
	void setOriginalURI(String value);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The LoadingContainer reference
	 */
	Container getLoadingContainer();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the LoadingContainer reference
	 */
	void setLoadingContainer(Container value);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The Container reference
	 */
	Container getContainer();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the Container reference
	 */
	void setContainer(Container value);

}
