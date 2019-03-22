/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.webfragment.internal.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.jst.javaee.core.JEEXMLLoadImpl;
import org.eclipse.jst.javaee.core.internal.util.JavaeeResourceImpl;
import org.eclipse.jst.javaee.web.IWebFragmentResource;
import org.eclipse.jst.javaee.web.WebFragment;
import org.eclipse.jst.javaee.webfragment.WebAppDeploymentDescriptor;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.webfragment.internal.util.WebfragmentResourceFactoryImpl
 * @generated
 */
public class WebfragmentResourceImpl extends JavaeeResourceImpl implements IRootObjectResource, IWebFragmentResource {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public WebfragmentResourceImpl(URI uri) {
		super(uri);
	}

	@Override
	protected XMLLoad createXMLLoad() {
		 return new JEEXMLLoadImpl(createXMLHelper());
	}

	
	@Override
	protected XMLHelper createXMLHelper() {
		
		return new WebfragmentXMLHelperImpl(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.jca.IConnectorResource#getRootObject()
	 */
	public EObject getRootObject() {
		if (contents == null || contents.isEmpty())
			return null;
		Object root = getContents().get(0);
		if(root == null){
			return null;
		}
		return (EObject)((WebAppDeploymentDescriptor)root).getWebFragment();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.jca.IConnectorResource#getConnector()
	 */
	public WebFragment getWebFragment() {
		return (WebFragment)getRootObject();
	}

} //WebfragmentResourceImpl
