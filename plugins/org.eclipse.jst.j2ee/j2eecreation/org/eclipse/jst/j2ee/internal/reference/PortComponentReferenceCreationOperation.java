/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Feb 12, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.reference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


public class PortComponentReferenceCreationOperation extends ModelModifierOperation {
	PortComponentReferenceDataModel dataModel;

	/**
	 * @param dataModel
	 */
	public PortComponentReferenceCreationOperation(PortComponentReferenceDataModel dataModel) {
		super(dataModel);
		this.dataModel = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		modifier.addHelper(createHelpers());
	}

	/*
	 * @see SimpleCommandWizardPage#createCommandHelper()
	 */
	public ModifierHelper createHelpers() {
		ModifierHelper helper = null;
		if (dataModel != null) {
			helper = new ModifierHelper();
			PortComponentRef ref = ((Webservice_clientPackage) EPackage.Registry.INSTANCE.getEPackage(Webservice_clientPackage.eNS_URI)).getWebservice_clientFactory().createPortComponentRef();
			helper.setOwner((EObject) dataModel.getProperty(PortComponentReferenceDataModel.OWNER));
			ref.setPortComponentLink(dataModel.getStringProperty(PortComponentReferenceDataModel.LINK));
			String serviceEndpointInterface = dataModel.getStringProperty(PortComponentReferenceDataModel.SERVICE_ENDPOINT_INTERFACE);
			ref.setServiceEndpointInterface((JavaClass) JavaRefFactory.eINSTANCE.reflectType(serviceEndpointInterface, (EObject) dataModel.getProperty(PortComponentReferenceDataModel.OWNER)));
			helper.setFeature(Webservice_clientPackage.eINSTANCE.getServiceRef_PortComponentRefs());
			helper.setValue(ref);
		}
		return helper;
	}

}