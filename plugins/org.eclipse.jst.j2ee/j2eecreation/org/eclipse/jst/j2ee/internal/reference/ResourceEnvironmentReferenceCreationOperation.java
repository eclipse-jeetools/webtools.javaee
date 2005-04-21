/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.reference;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.client.impl.ClientFactoryImpl;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.componentcore.internal.operation.ModelModifierOperation;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;



public class ResourceEnvironmentReferenceCreationOperation extends ModelModifierOperation {
	ResourceEnvironmentReferenceDataModel dataModel = null;

	/**
	 * @param dataModel
	 */
	public ResourceEnvironmentReferenceCreationOperation(ResourceEnvironmentReferenceDataModel dataModel) {
		super(dataModel);
		this.dataModel = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		ModifierHelper baseHelper = createHelper();
		modifier.addHelper(baseHelper);
	}


	public ModifierHelper createHelper() {
		ModifierHelper helper = new ModifierHelper();
		ResourceEnvRef ref = ((CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI)).getCommonFactory().createResourceEnvRef();
		helper.setOwner((EObject) dataModel.getProperty(ResourceEnvironmentReferenceDataModel.OWNER));
		ref.setName(dataModel.getStringProperty(ResourceEnvironmentReferenceDataModel.REF_NAME).trim());
		ref.setTypeName(dataModel.getStringProperty(ResourceEnvironmentReferenceDataModel.TYPE).trim());

		switch (dataModel.getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				helper.setFeature(ClientFactoryImpl.getPackage().getApplicationClient_ResourceEnvRefs());
				break;
			case XMLResource.EJB_TYPE :
				helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceEnvRefs());
				break;
			case XMLResource.WEB_APP_TYPE :
				helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceEnvRefs());
				break;
		}
		Integer version = (Integer) dataModel.getProperty(ResourceEnvironmentReferenceDataModel.J2EE_VERSION);
		if (version != null && version.intValue() <= J2EEVersionConstants.J2EE_1_3_ID) {
			ref.setDescription(dataModel.getStringProperty(ResourceEnvironmentReferenceDataModel.DESCRIPTION).trim());
		} else {
			Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
			descriptionObj.setValue(dataModel.getStringProperty(ResourceEnvironmentReferenceDataModel.DESCRIPTION).trim());
			ref.getDescriptions().add(descriptionObj);
		}

		helper.setValue(ref);
		return helper;
	}

}