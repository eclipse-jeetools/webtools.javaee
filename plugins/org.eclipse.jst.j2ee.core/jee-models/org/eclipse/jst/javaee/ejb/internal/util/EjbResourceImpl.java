/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb.internal.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.jst.javaee.core.JEEXMLLoadImpl;
import org.eclipse.jst.javaee.core.internal.util.JavaeeResourceImpl;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor;
import org.eclipse.jst.javaee.ejb.IEJBResource;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.util.EjbResourceFactoryImpl
 * @generated
 */
public class EjbResourceImpl extends JavaeeResourceImpl implements IRootObjectResource, IEJBResource {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public EjbResourceImpl(URI uri) {
		super(uri);
	}
	
	@Override
	protected XMLLoad createXMLLoad() {
		 return new JEEXMLLoadImpl(createXMLHelper());
	}

	
	@Override
	protected XMLHelper createXMLHelper() {
		
		return new EjbXMLHelperImpl(this);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.ejb.IEJBResource#getRootObject()
	 */
	public EObject getRootObject() {
		if (contents == null || contents.isEmpty())
			return null;
		Object root = getContents().get(0);
		if(root == null){
			return null;
		}
		return (EObject)((EJBJarDeploymentDescriptor)root).getEjbJar();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.ejb.IEJBResource#getEjbJar()
	 */
	public EJBJar getEjbJar() {
		return (EJBJar)getRootObject();
	}

} //EjbResourceImpl
