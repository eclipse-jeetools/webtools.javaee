/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import java.util.ArrayList;
import java.util.Hashtable;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

//import org.eclipse.emf.ecore.resource.ResourceSet;
//import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

//import com.ibm.etools.emf.workbench.plugin.EMFWorkbenchPlugin;

//import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
//import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;

//import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;


public class WebServicesClientEditModel extends CompositeEditModel {

	private WebServicesClientEditModelOwner fOwner;

	public WebServicesClientEditModel(WebServicesClientEditModelOwner owner) {
		//set the fields inherited from CompositeEditModel
		fEditModels = new Hashtable();
		fResources = new Hashtable();
		fRootObjects = new Hashtable();
		fDescriptors = new ArrayList();

		//set the fields inherited from EditModel
		fOwner = owner;
		fResourceSet = owner.getResourceSet();
		fProject = owner.getProject();
		fInputFile = owner.getInputFile();

	}

	public IConfigurationElement[] getExtensions() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		return reg.getConfigurationElementsFor("com.ibm.wtp.webservice", "webservicesclienteditmodelcontainer"); //$NON-NLS-1$ //$NON-NLS-2$
	}


	public EObject getRootModelObject() {
		return getRootModelObject("webservicesclient.xml"); //$NON-NLS-1$
	}

	public Resource getRootModelResource() {
		return getModelResource("webservicesclient.xml"); //$NON-NLS-1$
	}

	protected void dispose() {
		//Only dispose if no one is hanging on
		if (fReferenceCount > 0)
			return;

		fOwner.dispose();
	}


}