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
 * Created on Mar 24, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassOperation;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModelModifier;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddListenerOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddListenerOperation(AddListenerDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddListenerDataModel model = (AddListenerDataModel) this.operationDataModel;
		createHelper(this.modifier, model);
	}

	private void createHelper(ModelModifier amodifier, AddListenerDataModel model) {
		String qualifiedClassName = null;
		boolean useExisting = model.getBooleanProperty(AddServletFilterListenerCommonDataModel.USE_EXISTING_CLASS);
		if (!useExisting) {
			NewListenerClassDataModel nestedModel = (NewListenerClassDataModel) model.getNestedModel("NewListenerClassDataModel"); //$NON-NLS-1$
			NewJavaClassOperation op = new NewJavaClassOperation(nestedModel);
			try {
				op.run(null);
			} catch (InvocationTargetException e) {
				Logger.getLogger().log(e);
			} catch (InterruptedException e) {
				Logger.getLogger().log(e);
			}
			qualifiedClassName = nestedModel.getQualifiedClassName();
		} else {
			qualifiedClassName = model.getStringProperty(AddServletFilterListenerCommonDataModel.CLASS_NAME);
		}
		// Set up Listener
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		Listener listener = CommonFactory.eINSTANCE.createListener();
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			// Get values from data model
			String displayNameString = model.getStringProperty(AddServletFilterListenerCommonDataModel.DISPLAY_NAME);
			String descriptionString = model.getStringProperty(AddServletFilterListenerCommonDataModel.DESCRIPTION);
			listener.setDisplayName(displayNameString);
			listener.setDescription(descriptionString);
		}
		listener.setListenerClassName(qualifiedClassName);
		JavaClass listenerClass = JavaRefFactory.eINSTANCE.createClassRef(qualifiedClassName);
		listener.setListenerClass(listenerClass);
		// Set up helper
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Listeners());
		helper.setValue(listener);
		amodifier.addHelper(helper);
	}
}