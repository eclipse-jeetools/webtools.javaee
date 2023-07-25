/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca.internal.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.jst.javaee.core.JEEXMLLoadImpl;
import org.eclipse.jst.javaee.core.internal.util.JavaeeResourceImpl;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor;
import org.eclipse.jst.javaee.jca.IConnectorResource;
import org.eclipse.wst.common.internal.emf.resource.IRootObjectResource;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.jca.internal.util.JcaResourceFactoryImpl
 * @generated
 */
public class JcaResourceImpl extends JavaeeResourceImpl implements IRootObjectResource, IConnectorResource {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public JcaResourceImpl(URI uri) {
		super(uri);
	}

	@Override
	protected XMLLoad createXMLLoad() {
		 return new JEEXMLLoadImpl(createXMLHelper());
	}

	
	@Override
	protected XMLHelper createXMLHelper() {
		
		return new JcaXMLHelperImpl(this);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.jca.IConnectorResource#getRootObject()
	 */
	@Override
	public EObject getRootObject() {
		if (contents == null || contents.isEmpty())
			return null;
		Object root = getContents().get(0);
		if(root == null){
			return null;
		}
		return (EObject)((ConnectorDeploymentDescriptor)root).getConnector();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.javaee.jca.IConnectorResource#getConnector()
	 */
	@Override
	public Connector getConnector() {
		return (Connector)getRootObject();
	}

} //JcaResourceImpl
