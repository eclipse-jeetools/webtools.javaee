/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;


//

public class WscddEditModel extends EditModel {

	private static final String SUPPORTED_DESCRIPTION_NAME = "webservicesclient.xml"; //$NON-NLS-1$

	//private WscddResource fWscddResource;
	private WebServicesResource fWscddResource;

	public Resource getModelResource(String descriptorName) {
		try {
			if (descriptorName.equals(SUPPORTED_DESCRIPTION_NAME)) {
				//IFile webservicesFile = fProject.getFile(descriptorName);
				if (fInputFile.exists()) {
					URI uri = URI.createPlatformResourceURI(fInputFile.getFullPath().toString());
					fWscddResource = (WebServicesResource) fResourceSet.getResource(uri, true);
					return fWscddResource;
				}
			}
			return null;
		} catch (RuntimeException re) {
			//Explicitly remove this resource from the resource set if an empty one has been left
			EList resources = fResourceSet.getResources();
			Iterator i = resources.iterator();
			while (i.hasNext()) {
				Resource res = (Resource) i.next();
				if (res instanceof WebServicesResource) {
					resources.remove(res);
					break;
				}
			}

			return null;
		}
	}

	public EObject getRootModelObject(String descriptorName) {
		if (fWscddResource == null)
			return null;

		return fWscddResource.getWebServicesClient();
	}

	public EObject getRootModelObject() {
		if (fWscddResource == null)
			return null;
		return fWscddResource.getWebServicesClient();
	}

	public Resource getRootModelResource() {
		return fWscddResource;
	}


}