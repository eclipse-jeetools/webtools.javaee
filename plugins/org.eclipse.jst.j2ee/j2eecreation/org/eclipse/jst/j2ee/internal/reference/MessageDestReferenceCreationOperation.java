/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Mar 24, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.reference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.MessageDestinationUsageType;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.internal.client.impl.ClientFactoryImpl;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


public class MessageDestReferenceCreationOperation extends ModelModifierOperation {
	MessageDestReferenceDataModel dataModel = null;

	/**
	 * @param dataModel
	 */
	public MessageDestReferenceCreationOperation(MessageDestReferenceDataModel dataModel) {
		super(dataModel);
		this.dataModel = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		if (dataModel != null) {
			ModifierHelper baseHelper = createHelpers();
			modifier.addHelper(baseHelper);
			if (!dataModel.getBooleanProperty(MessageDestReferenceDataModel.HAS_LINK)) {
				modifier.addHelper(createMessageDestination());
				modifier.addHelper(createMDBLinkHelper());
			}
		}
	}

	/*
	 * @see SimpleCommandWizardPage#createCommandHelper()
	 */
	public ModifierHelper createHelpers() {
		ModifierHelper helper = null;
		helper = new ModifierHelper();
		MessageDestinationRef ref = CommonFactory.eINSTANCE.createMessageDestinationRef();
		helper.setOwner((EObject) dataModel.getProperty(MessageDestReferenceDataModel.OWNER));
		ref.setName(dataModel.getStringProperty(MessageDestReferenceDataModel.REF_NAME).trim());
		ref.setType(dataModel.getStringProperty(MessageDestReferenceDataModel.TYPE).trim());
		ref.setUsage(MessageDestinationUsageType.get(dataModel.getStringProperty(MessageDestReferenceDataModel.USAGE).trim()));
		ref.setLink(dataModel.getStringProperty(MessageDestReferenceDataModel.LINK));
		Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
		descriptionObj.setValue(dataModel.getStringProperty(MessageDestReferenceDataModel.DESCRIPTION));
		ref.getDescriptions().add(descriptionObj);

		switch (dataModel.getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				helper.setFeature(ClientFactoryImpl.getPackage().getApplicationClient_MessageDestinationRefs());
				break;
			case XMLResource.EJB_TYPE :
				helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_MessageDestinationRefs());
				break;
			case XMLResource.WEB_APP_TYPE :
				helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_MessageDestinationRefs());
				break;
		}
		helper.setValue(ref);

		return helper;
	}

	/**
	 * @return
	 */
	private ModifierHelper createMDBLinkHelper() {
		ModifierHelper helper = new ModifierHelper();
		MessageDriven mdb = (MessageDriven) dataModel.getProperty(MessageDestReferenceDataModel.TARGET);
		helper.setOwner(mdb);
		EAttribute link = EjbPackage.eINSTANCE.getMessageDriven_Link();
		helper.setFeature(link);
		helper.setValue(dataModel.getStringProperty(MessageDestReferenceDataModel.LINK));
		return helper;
	}

	/**
	 * @return
	 */
	private ModifierHelper createMessageDestination() {
		ModifierHelper helper = new ModifierHelper();
		MessageDestination dest = CommonFactory.eINSTANCE.createMessageDestination();
		dest.setName(dataModel.getStringProperty(MessageDestReferenceDataModel.LINK));
		switch (dataModel.getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				helper.setFeature(ClientFactoryImpl.getPackage().getApplicationClient_MessageDestinations());
				helper.setOwner((EObject) dataModel.getProperty(MessageDestReferenceDataModel.OWNER));
				break;
			case XMLResource.EJB_TYPE :
				//whats if no assemply descriptor....fluff one up
				helper.setFeature(EjbPackage.eINSTANCE.getAssemblyDescriptor_MessageDestinations());
				AssemblyDescriptor desc = ((EnterpriseBean) dataModel.getProperty(MessageDestReferenceDataModel.OWNER)).getEjbJar().getAssemblyDescriptor();
				if (desc == null) {
					ModifierHelper descHelper = new ModifierHelper();
					desc = EjbFactory.eINSTANCE.createAssemblyDescriptor();
					desc.setEjbJar(((EnterpriseBean) dataModel.getProperty(MessageDestReferenceDataModel.OWNER)).getEjbJar());
					descHelper.setFeature(EjbPackage.eINSTANCE.getEJBJar_AssemblyDescriptor());
					descHelper.setValue(desc);
					helper.setOwnerHelper(descHelper);
				} else {
					helper.setOwner(desc);
				}
				break;
			case XMLResource.WEB_APP_TYPE :
				helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_MessageDestinations());
				helper.setOwner((EObject) dataModel.getProperty(MessageDestReferenceDataModel.OWNER));
				break;
		}
		helper.setValue(dest);
		return helper;
	}
}