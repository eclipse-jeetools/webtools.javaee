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
package org.eclipse.jst.j2ee.commonarchivecore;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.strategy.LoadStrategy;


public interface ModuleRef extends EObject {

	/**
	 * Return the parsed local deployment descriptor from the ModuleFile
	 */
	EObject getStandardDeploymentDescriptor();

	/**
	 * Return the parsed alt dd, if it exists, from the EAR file containing this module
	 */
	EObject getAltDeploymentDescriptor() throws ArchiveWrappedException;

	/**
	 * Return an alt dd if it exists, otherwise the local dd
	 */
	EObject getDeploymentDescriptor() throws ArchiveWrappedException;


	/**
	 * Gets the uri from the {@link Module}referenced by this ref; if there is no module, (eg. a
	 * standalone JAR), gets the uri from the {@link ModuleFile}. Assumption: The uri of the
	 * {@link Module}and {@link ModuleFile}should stay in sync
	 */
	String getUri();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The ModuleFile reference
	 */
	ModuleFile getModuleFile();

	/**
	 * Similar to {@link Archive#getMofResource(String)}, except that alt-dd indirection is
	 * considered. If this module uses an alt-dd, then the uri will be prepended with the "alt-root"
	 * and the resource will be loaded from the owning EAR file. In this case, all hrefs into and
	 * out of this resource should be relative to the root of the EAR. The hrefs are taken care of
	 * for free when the client uses {@link #makeAltDescriptorsAndResources()}. The alt root is
	 * automatically generated when it does not exist.
	 * 
	 * @see ModuleExtension#getAltRoot()
	 */
	Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException;

	public InputStream getAltResourceInputStream(String uri) throws IOException;

	public InputStream getLocalResourceInputStream(String uri) throws IOException;

	/**
	 * Return an input stream from the resources path of either the ModuleFile, if the module does
	 * not use alt-dd, or from the altRoot of the module, found in the resources path of the EAR
	 * 
	 * @see LoadStrategy#getResourceInputStream(String)
	 */
	public InputStream getResourceInputStream(String uri) throws IOException;


	/**
	 * Retrieve a resource from the ModuleFile
	 * 
	 * @see Archive#getMofResource(String)
	 */
	Resource getLocalMofResource(String uri) throws FileNotFoundException, ResourceLoadException;

	/**
	 * The uri will be prepended with the "alt-root" and the resource will be loaded from the owning
	 * EAR file.
	 */
	Resource getAltMofResource(String uri) throws FileNotFoundException, ResourceLoadException;

	/**
	 * Create a new mof resource and add it to the resource set of the context of either the module
	 * file if this ModuleRef is not an alt, or to the EAR file if it is an alt. If this module is
	 * uses an alt-dd, then the uri will be prepended with the "alt-root" The alt root is
	 * automatically generated when it does not exist.
	 * 
	 * @throws DuplicateObjectException
	 *             if a resource already exists in this archive having the uri
	 */
	Resource makeMofResource(String uri) throws DuplicateObjectException;

	Resource makeLocalMofResource(String uri) throws DuplicateObjectException;

	Resource makeAltMofResource(String uri) throws DuplicateObjectException;


	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the ModuleFile reference
	 */
	void setModuleFile(ModuleFile value);

	/**
	 * Rename this module; both its ModuleFile and Module element from the Application deployment
	 * descriptor.
	 */
	public void setURI(String uri);

	boolean isEJB();

	boolean isWeb();

	boolean isClient();

	boolean isConnector();

	boolean usesAltDD();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The EarFile reference
	 */
	EARFile getEarFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the EarFile reference
	 */
	void setEarFile(EARFile value);

	/**
	 * Returns the value of the '<em><b>Module</b></em>' reference. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Module</em>' reference.
	 * @see #setModule(Module)
	 * @see org.eclipse.jst.j2ee.commonarchivecore.CommonarchivePackage#getModuleRef_Module()
	 * @model required="true"
	 * @generated
	 */
	Module getModule();

	/**
	 * Sets the value of the '
	 * {@link org.eclipse.jst.j2ee.commonarchivecore.ModuleRef#getModule <em>Module</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Module</em>' reference.
	 * @see #getModule()
	 * @generated
	 */
	void setModule(Module value);

} //ModuleRef



