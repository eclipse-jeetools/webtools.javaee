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
 * Created on Mar 31, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration;

import java.util.List;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanismType;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.ConnectionDefinition;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.jst.j2ee.jca.OutboundResourceAdapter;
import org.eclipse.jst.j2ee.jca.ResourceAdapter;
import org.eclipse.jst.j2ee.jca.SecurityPermission;


/** 
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Connector14SpecificationMigrator {
	/**
	 * @param connector
	 */
	public J2EEMigrationStatus migrateConnectorTo14(Connector connector) {
		 migrateResourceAdaptor(connector);
		 return new J2EEMigrationStatus(J2EEMigrationStatus.COMPLETED_OK,(
		 	new J2EEMigrationStatus()).format(J2EESpecificationMigrator.DEFAULT_COMPLETED_STATUS_MSG,connector.getDisplayName()));
	}
	
	/**
	 * @param connector
	 */
	private void migrateResourceAdaptor(Connector connector) {
		ResourceAdapter resAdaptor = connector.getResourceAdapter();
		if(resAdaptor != null) {
			OutboundResourceAdapter  outboundAdaptor = JcaFactory.eINSTANCE.createOutboundResourceAdapter();
			outboundAdaptor.setReauthenticationSupport(resAdaptor.isReauthenticationSupport());
			outboundAdaptor.setTransactionSupport(resAdaptor.getTransactionSupport());
			
			ConnectionDefinition connDefinition = JcaFactory.eINSTANCE.createConnectionDefinition();
			connDefinition.setManagedConnectionFactoryClass(resAdaptor.getManagedConnectionFactoryClass());
			connDefinition.setConnectionFactoryInterface(resAdaptor.getConnectionFactoryInterface());
			connDefinition.setConnectionFactoryImplClass(resAdaptor.getConnectionFactoryImplClass());
			connDefinition.setConnectionInterface(resAdaptor.getConnectionInterface());
			connDefinition.setConnectionImplClass(resAdaptor.getConnectionImplClass());
			
			outboundAdaptor.getConnectionDefinitions().add(connDefinition);

			resAdaptor.setOutboundResourceAdapter(outboundAdaptor);
			
			migrateAuthenticationMechanism(resAdaptor);
			
			migrateDescriptions(resAdaptor);
			
		}
	}
	/**
	 * @param resAdaptor
	 */
	private void migrateDescriptions(ResourceAdapter resAdaptor) {
		List secPermissions = resAdaptor.getSecurityPermissions();
		if(!secPermissions.isEmpty()) {
			for(int i = 0; i < secPermissions.size(); i++) {
				SecurityPermission secPerm = (SecurityPermission)secPermissions.get(i);
				String secDescription = secPerm.getDescription();
				if(secDescription != null && secDescription.length() > 0) {
					Description description = CommonFactory.eINSTANCE.createDescription();
					description.setValue(secDescription);
					secPerm.getDescriptions().add(description);
				}
				
			}
		}
		List authMechanisms = resAdaptor.getAuthenticationMechanisms();
		if(!authMechanisms.isEmpty()) {
			for(int i = 0 ; i < authMechanisms.size(); i++) {
				AuthenticationMechanism authMech = (AuthenticationMechanism)authMechanisms.get(i);
				String authDescrition = authMech.getDescription();
				if(authDescrition != null && authDescrition.length() > 0) {
					Description description = CommonFactory.eINSTANCE.createDescription();
					description.setValue(authDescrition);
					authMech.getDescriptions().add(description);
				}
			}
		}
		List configProps = resAdaptor.getConfigProperties();
		if(!configProps.isEmpty()) {
			for(int i = 0; i < configProps.size(); i++) {
				ConfigProperty configProp = (ConfigProperty)configProps.get(i);
				String configDescription = configProp.getDescription();
				if(configDescription != null && configDescription.length() > 0) {
					Description description = CommonFactory.eINSTANCE.createDescription();
					description.setValue(configDescription);
					configProp.getDescriptions().add(description);
				}
			}
		}
	}
	/**
	 * @param resAdaptor
	 */
	private void migrateAuthenticationMechanism(ResourceAdapter resAdaptor) {
		List authMechanisms = resAdaptor.getAuthenticationMechanisms();
		for (int i = 0; i < authMechanisms.size(); i++) {
			AuthenticationMechanism authMech = (AuthenticationMechanism)authMechanisms.get(i);
			AuthenticationMechanismType authType = authMech.getAuthenticationMechanismType();
			if(authType != null) {
				authMech.setAuthenticationMechanism(authType.getName());
			} else {
				String customAuth = authMech.getCustomAuthMechType();
				if(customAuth != null && customAuth.length() > 0) {
					authMech.setAuthenticationMechanism(customAuth);
				}
			}
		}
	}
}
