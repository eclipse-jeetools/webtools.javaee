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
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ConnectorModule;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.JavaClientModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.EmptyResourceException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ObjectNotFoundException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * @generated
 */
public interface EARFile extends ModuleFile {

	/**
	 * Makes a copy of
	 * 
	 * @aModuleFile, using its local deployment descriptor; creates a new Module and adds it to the
	 *               Application deployment descriptor of this EAR file and adds the copy of the
	 *               ModuleFile to this EAR.
	 * 
	 * @return The copied module file
	 * 
	 * @exception DuplicateObjectException
	 *                if this EAR already contains a file with the same uri as
	 * @aModuleFile
	 */

	public ModuleFile addCopy(ModuleFile aModuleFile) throws DuplicateObjectException;


	/**
	 * This is the same as addCopy(ModuleFile) except the return value is the new ModuleRef
	 */
	public ModuleRef addCopyRef(ModuleFile aModuleFile) throws DuplicateObjectException;

	/**
	 * Add a copy of the security role to the dd for the module; if an alt dd is specified, add to
	 * that dd; otherwise add to the standard dd of the module; also add a copy of the role to the
	 * ear file dd if a role with that name does not already exist
	 * 
	 * @throws DuplicateObjectException
	 *             if the dd for aModule already contains a role with that name
	 */
	public SecurityRole addCopy(SecurityRole aRole, Module aModule) throws DuplicateObjectException;

	/**
	 * Add a copy of the security role to the ear file's dd, if it does not already contain a role
	 * with the same name
	 */
	public SecurityRole addCopyIfNotExists(SecurityRole aRole);

	EObject getAltDeploymentDescriptor(Module aModule) throws FileNotFoundException, ResourceLoadException, EmptyResourceException;

	/**
	 * Returns a filtered list of ApplicationClientFiles; adds will not be reflected; use
	 * 
	 * @link Archive#add(File)
	 */
	public List getApplicationClientFiles();

	/**
	 * Returns a filtered list of ClientModuleRefs
	 */
	public List getClientModuleRefs();

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */



	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The DeploymentDescriptor reference
	 */
	Application getDeploymentDescriptor() throws DeploymentDescriptorLoadException;

	Connector getDeploymentDescriptor(ConnectorModule aModule) throws FileNotFoundException, ResourceLoadException, EmptyResourceException;

	EJBJar getDeploymentDescriptor(EjbModule aModule) throws FileNotFoundException, ResourceLoadException, EmptyResourceException;

	ApplicationClient getDeploymentDescriptor(JavaClientModule aModule) throws FileNotFoundException, ResourceLoadException, EmptyResourceException;

	EObject getDeploymentDescriptor(Module aModule) throws FileNotFoundException, ResourceLoadException, EmptyResourceException;

	WebApp getDeploymentDescriptor(WebModule aModule) throws FileNotFoundException, ResourceLoadException, EmptyResourceException;

	/**
	 * Returns a filtered list of EJBJarFiles; adds will not be reflected; use
	 * 
	 * @link Archive#add(File)
	 */
	public List getEJBJarFiles();

	/**
	 * Returns a filtered list of EJBModuleRefs
	 */
	public List getEJBModuleRefs();


	/**
	 * Return an enterprise bean referenced by the EjbRef, if one exists. The ejb-link value of the
	 * ref must equate to a named enterprise bean contained in the jar; otherwise return null.
	 * Returns the first hit found; assumption that the ejb names are unique within the scope of the
	 * ear file. This will likely be replaced with a better way for dereferencing ejb refs.
	 * 
	 * Can be used with ejb 1.1 references only.
	 * 
	 * @deprecated {@link#getEnterpiseBeanFromRef(EjbRef ref, String moduleUri )
	 * @param EjbRef
	 *            ref - An ejb reference
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEnterpiseBeanFromRef(EjbRef ref);

	/**
	 * Return an enterprise bean referenced by the EjbRef and a module uri, if one exists. The
	 * ejb-link value of the ref must equate to a named enterprise bean contained in the jar;
	 * otherwise return null. Returns the first hit found; assumption that the ejb names are unique
	 * within the scope of the ear file. This will likely be replaced with a better way for
	 * dereferencing ejb refs.
	 * 
	 * Can be used with ejb 1.1 & ejb 2.0 references.
	 * 
	 * @param EjbRef
	 *            ref - An ejb reference
	 * @param String
	 *            moduleUri - The module uri
	 * @return EnterpriseBean
	 */
	public EnterpriseBean getEnterpiseBeanFromRef(EjbRef ref, String moduleUri);

	public Module getModule(String uri, String altDD);

	/**
	 * @return the module ref which references
	 * @moduleDescriptor
	 */
	public ModuleRef getModuleRef(Module moduleDescriptor);

	/**
	 * @return java.util.List of all module refs in this EAR having a reference to
	 * @aModuleFile
	 */
	public List getModuleRefs(ModuleFile aModuleFile);

	/**
	 * Returns a filtered list of ModuleFiles; adds will not be reflected; use
	 * 
	 * @link Archive#add(File)
	 */
	public List getModuleFiles();

	/**
	 * Returns a filtered list of RarFiles; adds will not be reflected; use
	 * 
	 * @link Archive#add(File)
	 */
	public List getRARFiles();

	/**
	 * Returns a filtered list of FARFiles; adds will not be reflected; use
	 * {@link Archive#add(File)}
	 */
	public List getFARFiles();

	/**
	 * Returns a filtered list of ConnectorModuleRefs
	 */
	public List getConnectorModuleRefs();

	/**
	 * Return all security roles from all existing modules (EjbModule and WebModule)
	 */
	public EList getRolesFromAllModules();

	/**
	 * Return all security roles from an existing module (EjbModule and WebModule)
	 */
	public EList getRolesFromModule(Module aModule);

	/**
	 * Returns a filtered list of WarFiles; adds will not be reflected; use
	 * 
	 * @link Archive#add(File)
	 */
	public List getWARFiles();

	/**
	 * Returns a filtered list of WebModuleRefs
	 */
	public List getWebModuleRefs();

	/**
	 * Copy the role into each ModuleFile in the ear file which does not already contain the role
	 * Assumption: a role with the same name as
	 * 
	 * @role exists in the application deployment descriptor
	 */
	public void pushDownRole(SecurityRole role);

	/**
	 * Copy the role into the ModuleFile for the module, if the module does not already contain the
	 * role Assumption: a role with the same name as
	 * 
	 * @role exists in the application deployment descriptor
	 */
	public void pushDownRole(SecurityRole role, Module aModule);

	/**
	 * Attempt to remove the module for the parameter from this object's dd, then remove the module
	 * file, if it is not referenced from any other ModuleRef, from the list of files
	 */
	public void remove(ModuleRef aModuleRef);

	/**
	 * Rename the security role in the ear file's dd; push this change down to any contained module
	 * dd's; if the module specifies an alt-dd, the change will be reflected there; otherwise it
	 * will be reflected in the standard dd of the module
	 * 
	 * @throws ObjectNotFoundException
	 *             if the dd for the ear does not contain a role with the existingRoleName
	 * 
	 * @throws DuplicateObjectException
	 *             if the dd for the ear file already contains a role with the new name
	 */
	public void renameSecurityRole(String existingRoleName, String newRoleName) throws ObjectNotFoundException, DuplicateObjectException;

	/**
	 * For each security role in the dd for each module, add a copy to the ear file's dd; if an
	 * alt-dd is specified for the module, use that dd; otherwise use the standard dd in the module
	 * file
	 */
	public void rollUpRoles();

	/**
	 * For each security role in the dd for a module, add a copy to the ear file's dd; if an alt-dd
	 * is specified for the module, use that dd; otherwise use the standard dd in the module file
	 */
	public void rollUpRoles(Module aModule);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the DeploymentDescriptor reference
	 */
	void setDeploymentDescriptor(Application value);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The list of ModuleRefs references
	 */
	EList getModuleRefs();

	public List getArchivesOfType(String type);

	/**
	 * Return a Map of Modules as keys and a List of EJB References as the values. This will let you
	 * know which Modules the references came from. You can filter the list of EJB References
	 * returned on the linked attributed of the reference. This means that you can filter linked
	 * references, non-linked references, or neither (i.e., return all references).
	 */
	Map getEJBReferences(boolean filterLinkedReferences, boolean filterNonLinkedReferences) throws ArchiveWrappedException;
}
