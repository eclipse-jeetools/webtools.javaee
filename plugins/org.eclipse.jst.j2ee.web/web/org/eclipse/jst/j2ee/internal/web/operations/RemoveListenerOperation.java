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
/*
 * Created on Jun 8, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class RemoveListenerOperation extends ModelModifierOperation {
	public RemoveListenerOperation(RemoveListenerDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		RemoveListenerDataModel model = (RemoveListenerDataModel) this.operationDataModel;
		boolean removeClass = model.getBooleanProperty(RemoveListenerDataModel.REMOVE_JAVA_CLASS);
		IProject project = model.getTargetProject();
		List listenerList = (List) model.getProperty(RemoveListenerDataModel.LISTENER_LIST);
		int count = listenerList.size();
		for (int i = 0; i < count; i++) {
			Listener listener = (Listener) listenerList.get(i);
			WebApp webApp = (WebApp) listener.eContainer();
			// remove Listener
			ModifierHelper helper = createRemoveListenerHelper(webApp, listener);
			this.modifier.addHelper(helper);
			if (removeClass) {
				try {
					JavaClass javaClass = listener.getListenerClass();
					String qualifiedName = javaClass.getQualifiedName();
					qualifiedName = qualifiedName.replace('.', IPath.SEPARATOR) + ".java"; //$NON-NLS-1$
					List sourceFolders = ProjectUtilities.getSourceContainers(project);
					for (int j = 0; j < sourceFolders.size(); j++) {
						IFolder sourceFolder = (IFolder) sourceFolders.get(j);
						IFile file = sourceFolder.getFile(qualifiedName);
						if (file.exists()) {
							file.delete(true, null);
							break;
						}
					}
				} catch (CoreException ex) {
					Logger.getLogger().log(ex);
				}
			}
		}
	}

	private ModifierHelper createRemoveListenerHelper(WebApp webApp, Listener Listener) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Listeners());
		helper.setValue(Listener);
		helper.doUnsetValue();
		return helper;
	}
}