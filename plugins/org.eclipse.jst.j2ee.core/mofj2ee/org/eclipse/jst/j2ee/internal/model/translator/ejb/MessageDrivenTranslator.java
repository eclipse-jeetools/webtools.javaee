/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 31, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class MessageDrivenTranslator extends AbstractEJBTranslator {
	private static Translator[] children13;
	private static Translator[] children14;
	
	private static Translator TRANSACTION_TYPE_XLATOR = new Translator(TRANSACTION_TYPE, EJB_PKG.getMessageDriven_TransactionType());
	
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public MessageDrivenTranslator() {
		super(ENTERPRISE_BEANS+'/'+MESSAGE_DRIVEN);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.internal.model.translator.ejb.AbstractEJBTranslator#getSpecificMaps()
	 */
	protected Translator[] getSpecificMaps(int versionID) {
		switch (versionID) {
			case J2EE_1_4_ID:
				return getSpecificMaps14();
			default:
				return getSpecificMaps13();
		}
	}
	
	protected Translator[] getSpecificMaps13() {
	
		return new Translator[] {
			TRANSACTION_TYPE_XLATOR,
			
			//EJB 2.0 PATH
			new Translator(MESSAGE_SELECTOR, EJB_PKG.getMessageDriven_MessageSelector()),
			new AcknowledgeModeTranslator(),
			createMessageDrivenDestinationTranslator(),
			
		}; 
	}

	protected Translator[] getSpecificMaps14() {

		return new Translator[] {
			//EJB 2.1 Only
			new JavaClassTranslator(MESSAGING_TYPE, EJB_PKG.getMessageDriven_MessagingType()),
			//Both
			TRANSACTION_TYPE_XLATOR,
			//EJB 2.1 PATH
			new JavaClassTranslator(MESSAGE_DEST_TYPE, EJB_PKG.getMessageDriven_MessageDestination()),
			new Translator(MESSAGE_DEST_LINK, EJB_PKG.getMessageDriven_Link()),
			createActivationConfigurationMaps(),
		}; 
	}
	
	
	/**
	 * @return
	 */
	private Translator createActivationConfigurationMaps() {
		GenericTranslator result = new GenericTranslator(ACTIVATION_CONF, EJB_PKG.getMessageDriven_ActivationConfig());
		result.setChildren(new Translator[]{
			CommonTranslators.createDescriptionTranslator(EJB_PKG.getActivationConfig_Descriptions()),
			createActivationConfigPropertiesMap(),
		//	CommonTranslators.createDeploymentExtensionTranslator(EJB_PKG.getActivationConfig_Extensions())
		});
		
		return result;
	}


	/**
	 * @return
	 */
	private Translator createActivationConfigPropertiesMap() {
		GenericTranslator result = new GenericTranslator(ACTIVATION_CONF_PROPERTY, EJB_PKG.getActivationConfig_ConfigProperties());
		result.setChildren(new Translator[] {
			new Translator(ACTIVATION_CONF_PROPERTY_NAME, EJB_PKG.getActivationConfigProperty_Name()),
			new Translator(ACTIVATION_CONF_PROPERTY_VALUE, EJB_PKG.getActivationConfigProperty_Value())
		});		
		return result;
	}


	/**
	 * @return
	 */
	protected Translator createMessageDrivenDestinationTranslator() {
		GenericTranslator result = new GenericTranslator(MESSAGE_DRIVEN_DESTINATION,EJB_PKG.getMessageDriven_Destination());
		result.setChildren( new Translator[]{
			new MessageDrivenDestinationTypeTranslator(),
			new Translator(SUBSCRIPTION_DURABILITY, EJB_PKG.getMessageDrivenDestination_SubscriptionDurability())
		});
		return result;
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		switch (versionID) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :
				if (children13 == null)
					children13 = create13Children();
				return children13;	
			default :
				if (children14 == null)
					children14 = create14Children();
				return children14; 
		}
	}
	


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		return EjbFactory.eINSTANCE.createMessageDriven();
	}
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.internal.model.translator.ejb.AbstractEJBTranslator#getEndMaps13()
	 */
	protected Translator[] getEndMaps13() {
		return new Translator[] {
			CommonTranslators.JNDI_REF_GROUP_ENV_ENTRY_1_3,
			CommonTranslators.JNDI_REF_GROUP_EJB_REF_1_3,
			CommonTranslators.JNDI_REF_GROUP_EJB_LOCAL_REF_1_3,
			new SecurityIdentityTranslator(),
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_REF_1_3,
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_ENV_REF_1_3
		};
	}
	
	protected Translator[] getEndMaps14() {
		return new Translator[] {
			CommonTranslators.JNDI_REF_GROUP_ENV_ENTRY_1_4,
			CommonTranslators.JNDI_REF_GROUP_EJB_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_EJB_LOCAL_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_SERVICE_REF_1_4,	
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_RESOURCE_ENV_REF_1_4,
			CommonTranslators.JNDI_REF_GROUP_MESSAGE_DEST_REF_1_4,
			new SecurityIdentityTranslator(),
		};
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.internal.model.translator.ejb.AbstractEJBTranslator#usesHomeRemoteAttributes()
	 */
	protected boolean usesHomeRemoteAttributes() {
		return false;
	}

}
