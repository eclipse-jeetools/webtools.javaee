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
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.client.impl.ClientFactoryImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ResAuthTypeBase;
import org.eclipse.jst.j2ee.common.ResSharingScopeType;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;



public class ResourceReferenceCreationOperation extends ModelModifierOperation {
	ResourceReferenceDataModel dataModel = null;

	/**
	 * @param dataModel
	 */
	public ResourceReferenceCreationOperation(ResourceReferenceDataModel dataModel) {
		super(dataModel);
		this.dataModel = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		ModifierHelper baseHelper = createHelpers();
		modifier.addHelper(baseHelper);
	}

	/*
	 * @see SimpleCommandWizardPage#createCommandHelper()
	 */
	public ModifierHelper createHelpers() {
		ModifierHelper helper = null;
		if (dataModel != null) {
			helper = new ModifierHelper();
			ResourceRef ref = ((CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI)).getCommonFactory().createResourceRef();
			helper.setOwner((EObject) dataModel.getProperty(ResourceReferenceDataModel.OWNER));
			ref.setName(dataModel.getStringProperty(ResourceReferenceDataModel.REF_NAME).trim());
			ref.setType(dataModel.getStringProperty(ResourceReferenceDataModel.TYPE).trim());
			ref.setAuth(ResAuthTypeBase.get(dataModel.getStringProperty(ResourceReferenceDataModel.AUTHENTICATION).trim()));
			String resSharingScope = dataModel.getStringProperty(ResourceReferenceDataModel.SHARING_SCOPE).trim();
			if (resSharingScope != null && !resSharingScope.equals("")) //$NON-NLS-1$
				ref.setResSharingScope(ResSharingScopeType.get(resSharingScope));

			Integer version = (Integer) dataModel.getProperty(ResourceReferenceDataModel.J2EE_VERSION);
			if (version != null && version.intValue() <= J2EEVersionConstants.J2EE_1_3_ID) {
				ref.setDescription(dataModel.getStringProperty(ResourceReferenceDataModel.DESCRIPTION).trim());
			} else {
				Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(dataModel.getStringProperty(ResourceReferenceDataModel.DESCRIPTION).trim());
				ref.getDescriptions().add(descriptionObj);
			}

			switch (dataModel.getDeploymentDescriptorType()) {
				case XMLResource.APP_CLIENT_TYPE :
					helper.setFeature(ClientFactoryImpl.getPackage().getApplicationClient_ResourceRefs());
					break;
				case XMLResource.EJB_TYPE :
					helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceRefs());
					break;
				case XMLResource.WEB_APP_TYPE :
					helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ResourceRefs());
					break;
			}
			helper.setValue(ref);
		}
		return helper;
	}
}