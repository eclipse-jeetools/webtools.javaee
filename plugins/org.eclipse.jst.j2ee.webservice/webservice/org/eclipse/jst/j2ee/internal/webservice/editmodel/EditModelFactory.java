/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;

public class EditModelFactory {
	private static EditModelFactory fEditModelFactory;
	private Hashtable fWebServicesEditModels = new Hashtable();
	private Hashtable fWebServicesClientEditModels = new Hashtable();

	private EditModelFactory() {
		//Default constructor
	}

	public static EditModelFactory getEditModelFactory() {
		if (fEditModelFactory == null) {
			fEditModelFactory = new EditModelFactory();
		}

		return fEditModelFactory;
	}

	public WebServicesEditModel getWebServicesEditModel(IFile f) {

		WebServicesEditModel wsem;
		Object wsemObj = fWebServicesEditModels.get(f.getProject());
		if (wsemObj != null) {
			wsem = (WebServicesEditModel) wsemObj;
			return wsem;
		}
		WebServicesEditModelOwner owner = new WebServicesEditModelOwner(f);
		wsem = (WebServicesEditModel) owner.createEditModel();
		fWebServicesEditModels.put(f.getProject(), wsem);
		return wsem;
	}

	public WebServicesClientEditModel getWebServicesClientEditModel(IFile f) {
		WebServicesClientEditModel wscem;
		Object wscemObj = fWebServicesClientEditModels.get(f.getProject());
		if (wscemObj != null) {
			wscem = (WebServicesClientEditModel) wscemObj;
			return wscem;
		}

		WebServicesClientEditModelOwner owner = new WebServicesClientEditModelOwner(f);
		wscem = (WebServicesClientEditModel) owner.createEditModel();
		fWebServicesClientEditModels.put(f.getProject(), wscem);
		return wscem;

	}

	public void disposeWebServicesEditModel(IProject p) {
		//Remove from table
		fWebServicesEditModels.remove(p);
	}

	public void disposeWebServicesClientEditModel(IProject p) {
		//Remove from table
		fWebServicesClientEditModels.remove(p);
	}
}