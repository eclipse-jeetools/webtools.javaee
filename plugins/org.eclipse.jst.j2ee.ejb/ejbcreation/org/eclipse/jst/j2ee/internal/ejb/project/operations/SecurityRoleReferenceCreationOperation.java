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
 * Created on Jan 27, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.internal.reference.ReferenceDataModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class SecurityRoleReferenceCreationOperation extends ModelModifierOperation {
	SecurityRoleReferenceDataModel dataModel = null;

	/**
	 * @param dataModel
	 */
	public SecurityRoleReferenceCreationOperation(SecurityRoleReferenceDataModel dataModel) {
		super(dataModel);
		this.dataModel = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		modifier.addHelper(createHelper());
	}

	/*
	 * @see SimpleCommandWizardPage#createCommandHelper()
	 */
	public ModifierHelper createHelper() {
		ModifierHelper helper = new ModifierHelper();
		if (dataModel != null) {

			SecurityRoleRef ref = ((CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI)).getCommonFactory().createSecurityRoleRef();
			ref.setName(dataModel.getStringProperty(ReferenceDataModel.REF_NAME).trim());

			Integer version = (Integer) dataModel.getProperty(ReferenceDataModel.J2EE_VERSION);
			if (version != null && version.intValue() <= J2EEVersionConstants.J2EE_1_3_ID) {
				ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
			} else {
				Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
				ref.getDescriptions().add(descriptionObj);
			}

			if (!EJBCreationResourceHandler.getString("None_UI_").equals(dataModel.getStringProperty(SecurityRoleReferenceDataModel.LINK).trim())) { //$NON-NLS-1$
				ref.setLink(dataModel.getStringProperty(SecurityRoleReferenceDataModel.LINK).trim());
			} else
				ref.setLink(null);
			helper.setOwner((EObject) dataModel.getProperty(ReferenceDataModel.OWNER));
			helper.setFeature(EjbFactoryImpl.getPackage().getEnterpriseBean_SecurityRoleRefs());
			helper.setValue(ref);
		}
		return helper;
	}

}