/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project;


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.wst.common.internal.emf.utilities.ExtendedEcoreUtil;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;


/**
 * Insert the type's description here. Creation date: (5/23/2001 4:30:40 PM)
 * 
 * @author: Administrator
 */
public abstract class AbstractEJBEditModel extends J2EEEditModel {
	/**
	 * AbstractEJBEditModel constructor comment.
	 * 
	 * @param aKey
	 *            java.lang.Object
	 */
	public AbstractEJBEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly) {
		super(editModelID, context, readOnly);
	}

	/**
	 * AbstractEJBEditModel constructor comment.
	 * 
	 * @param aKey
	 *            java.lang.Object
	 */
	public AbstractEJBEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnknownResourcesAsReadOnly) {
		super(editModelID, context, readOnly, accessUnknownResourcesAsReadOnly);
	}

	public boolean ejbXmiResourceExists() {
		return getEJBNature().fileExists(J2EEConstants.EJBJAR_DD_URI);
	}

	/**
	 * Return the root object, the ejb-jar, from the ejb-jar.xml DD.
	 */
	public EJBJar getEJBJar() {
		Resource dd = getEjbXmiResource();
		if (dd != null) {
			Object ejbJar = getRoot(dd);
			if (ejbJar instanceof EJBJar)
				return (EJBJar) ejbJar;
		}
		return null;
	}

	public EJBNatureRuntime getEJBNature() {
		return EJBNatureRuntime.getRuntime(getProject());
	}

	public EJBResource getEjbXmiResource() {
		return (EJBResource) getResource(J2EEConstants.EJBJAR_DD_URI_OBJ);
	}

	/**
	 * Return true if
	 * 
	 * @aResource is the EJBResource.
	 */
	protected boolean isEJBResource(Resource aResource) {
		return ExtendedEcoreUtil.endsWith(aResource.getURI(), J2EEConstants.EJBJAR_DD_URI_OBJ);
	}

	public EJBResource makeEjbXmiResource() {
		return (EJBResource) createResource(J2EEConstants.EJBJAR_DD_URI_OBJ);
	}

	public Resource makeDeploymentDescriptorWithRoot() {
		XMLResource res = makeEjbXmiResource();
		res.setModuleVersionID(getEJBNature().getModuleVersion());
		addEJBJarIfNecessary(res);
		return res;
	}

	public void addEJBJarIfNecessary() {
		Resource res = getEjbXmiResource();
		addEJBJarIfNecessary(res);
	}

	public void addEJBJarIfNecessary(Resource res) {
		if (res != null && res.getContents().isEmpty()) {
			EJBJar ejbJar = EjbPackage.eINSTANCE.getEjbFactory().createEJBJar();
			res.getContents().add(ejbJar);
			ejbJar.setDisplayName(getProject().getName());
			((XMIResource) res).setID(ejbJar, J2EEConstants.EJBJAR_ID);
		}
	}
}