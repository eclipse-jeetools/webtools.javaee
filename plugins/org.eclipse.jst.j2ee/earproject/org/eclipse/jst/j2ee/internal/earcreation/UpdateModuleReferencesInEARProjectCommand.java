/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.J2EEEditModel;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientEditModel;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.moduleextension.EjbModuleExtension;
import org.eclipse.jst.j2ee.moduleextension.JcaModuleExtension;
import org.eclipse.jst.j2ee.moduleextension.WebModuleExtension;


public class UpdateModuleReferencesInEARProjectCommand extends ModuleInEARProjectCommand {
	protected List ejbRefernces;
	protected String oldModuleUri;
	protected List nestedEditModels;

	/**
	 * Constructor for UpdateModuleReferencesInEARProjectCommand.
	 * 
	 * @param anEarProject
	 * @param uri
	 */
	public UpdateModuleReferencesInEARProjectCommand(IProject anEarProject, Module aModule, String newUri) {
		setEarProject(anEarProject);
		setModule(aModule);
		setModuleUri(newUri);
	}

	protected void primExecute() {
		oldModuleUri = getModule().getUri();
		try {
			initializeEjbReferencesToModule();
			if (ejbRefernces != null)
				updateEjbReferences();
		} finally {
			saveAndReleaseEditModels();
		}
	}

	protected void initializeEjbReferencesToModule() {
		EARNatureRuntime earNature = EARNatureRuntime.getRuntime(getEarProject());
		Collection modNatures = earNature.getModuleProjects().values();
		J2EENature nature;
		Iterator it = modNatures.iterator();
		while (it.hasNext()) {
			nature = (J2EENature) it.next();
			initializeEjbReferencesToModule(nature);
		}
	}

	/**
	 * Method initializeEjbReferencesToModule.
	 * 
	 * @param nature
	 */
	protected void initializeEjbReferencesToModule(J2EENature moduleNature) {
		if (moduleNature == null)
			return;
		switch (moduleNature.getDeploymentDescriptorType()) {
			case XMLResource.EJB_TYPE :
				EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
				ejbExt.initializeEjbReferencesToModule(moduleNature, this);
				break;
			case XMLResource.WEB_APP_TYPE :
				WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
				webExt.initializeEjbReferencesToModule(moduleNature, this);
				break;
			case XMLResource.APP_CLIENT_TYPE :
				initializeEjbReferencesToModule((ApplicationClientNatureRuntime) moduleNature);
				break;
			case XMLResource.RAR_TYPE :
				JcaModuleExtension jcaExt = EarModuleManager.getJCAModuleExtension();
				jcaExt.initializeEjbReferencesToModule(moduleNature, this);
				break;
		}
	}



	public boolean initializeEjbReferencesToModule(List someEjbReferences) {
		if (!someEjbReferences.isEmpty()) {
			EjbRef ref;
			for (int i = 0; i < someEjbReferences.size(); i++) {
				ref = (EjbRef) someEjbReferences.get(i);
				if (hasModuleRefernces(ref.getLink())) {
					addEjbReference(ref);
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasModuleRefernces(String aString) {
		return aString != null && aString.indexOf(oldModuleUri) > -1;
	}

	private void addEjbReference(EjbRef ref) {
		if (ejbRefernces == null)
			ejbRefernces = new ArrayList();
		ejbRefernces.add(ref);
	}

	public void addNestedEditModel(J2EEEditModel anEditModel) {
		if (nestedEditModels == null)
			nestedEditModels = new ArrayList();
		nestedEditModels.add(anEditModel);
	}

	protected void initializeEjbReferencesToModule(ApplicationClientNatureRuntime moduleNature) {
		AppClientEditModel anEditModel = moduleNature.getAppClientEditModelForWrite(this);
		boolean foundRef = false;
		try {
			ApplicationClient appclient = anEditModel.getApplicationClient();
			if (appclient != null)
				foundRef = initializeEjbReferencesToModule(appclient.getEjbReferences());
			if (foundRef)
				addNestedEditModel(anEditModel);
		} finally {
			if (!foundRef)
				anEditModel.releaseAccess(this);
		}
	}

	protected void updateEjbReferences() {
		if (ejbRefernces != null && !ejbRefernces.isEmpty()) {
			EjbRef ref;
			for (int i = 0; i < ejbRefernces.size(); i++) {
				ref = (EjbRef) ejbRefernces.get(i);
				updateEjbReference(ref);
			}
		}
	}

	protected void updateEjbReference(EjbRef ref) {
		String uri = ref.getLink();
		int index = uri.indexOf(oldModuleUri);
		String pre, post;
		pre = uri.substring(0, index);
		post = uri.substring(index + oldModuleUri.length());
		ref.setLink(pre + getModuleUri() + post);
	}

	protected void saveAndReleaseEditModels() {
		if (nestedEditModels != null) {
			J2EEEditModel anEditModel;
			for (int i = 0; i < nestedEditModels.size(); i++) {
				anEditModel = (J2EEEditModel) nestedEditModels.get(i);
				try {
					anEditModel.saveIfNecessary(this);
				} finally {
					anEditModel.releaseAccess(this);
				}
			}
		}
	}

	protected void primUndo() {
		//not undoable at this time.
	}
}