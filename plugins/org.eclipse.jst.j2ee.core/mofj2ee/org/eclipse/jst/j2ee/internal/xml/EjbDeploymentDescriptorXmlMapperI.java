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
package org.eclipse.jst.j2ee.internal.xml;



/**
 * This interface represents a catalog of all the tag names which occur in 
 * an EJB Jar deployment descriptor. Interface shared by readers and 
 * writers to have access to common constants
 */

public interface EjbDeploymentDescriptorXmlMapperI extends DeploymentDescriptorXmlMapperI {
	String ABSTRACT_SCHEMA_NAME = "abstract-schema-name"; //$NON-NLS-1$	// EJB2.0
	String ACKNOWLEDGE_MODE = "acknowledge-mode";//$NON-NLS-1$ 			// EJB2.0
	String ASSEMBLY_DESCRIPTOR = "assembly-descriptor";//$NON-NLS-1$
	String BEAN = "Bean";//$NON-NLS-1$
	String CASCADE_DELETE = "cascade-delete";//$NON-NLS-1$ 				// EJB2.0
	String CMP_FIELD = "cmp-field";//$NON-NLS-1$
	String CMP_VERSION = "cmp-version";		//$NON-NLS-1$				// EJB2.0
	String CMR_FIELD = "cmr-field";		//$NON-NLS-1$					// EJB2.0
	String CMR_FIELD_NAME = "cmr-field-name";//$NON-NLS-1$				// EJB2.0
	String CMR_FIELD_TYPE = "cmr-field-type";//$NON-NLS-1$				// EJB2.0
	String CONTAINER = "Container";//$NON-NLS-1$
	String CONTAINER_TRANSACTION = "container-transaction";//$NON-NLS-1$
	String DESTINATION_TYPE = "destination-type";	//$NON-NLS-1$		// EJB2.0
	String EJB_CLASS = "ejb-class";//$NON-NLS-1$
	String EJB_CLIENT_JAR = "ejb-client-jar";//$NON-NLS-1$
	String EJB_JAR = "ejb-jar";//$NON-NLS-1$
	String EJB_NAME = "ejb-name";//$NON-NLS-1$
	String EJB_QL = "ejb-ql";		//$NON-NLS-1$						// EJB2.0
	String EJB_RELATION = "ejb-relation";//$NON-NLS-1$					// EJB2.0
	String EJB_RELATION_NAME = "ejb-relation-name";	//$NON-NLS-1$		// EJB2.0
	String EJB_RELATIONSHIP_ROLE = "ejb-relationship-role";//$NON-NLS-1$	// EJB2.0
	String EJB_RELATIONSHIP_ROLE_NAME = "ejb-relationship-role-name";//$NON-NLS-1$ // EJB2.0
	String ENTERPRISE_BEANS = "enterprise-beans";//$NON-NLS-1$
	String ENTITY = "entity";//$NON-NLS-1$
	String EXCLUDE_LIST = "exclude-list";	//$NON-NLS-1$// EJB2.0 - MODELED???
	String FIELD_NAME = "field-name";//$NON-NLS-1$
	String MESSAGE_DRIVEN = "message-driven";	//$NON-NLS-1$			// EJB2.0
	String MESSAGE_DRIVEN_DESTINATION = "message-driven-destination"; //$NON-NLS-1$// EJB2.0
	String MESSAGE_SELECTOR = "message-selector";	//$NON-NLS-1$		// EJB2.0
	String METHOD = "method";//$NON-NLS-1$
	String METHOD_INTF = "method-intf";//$NON-NLS-1$
	String METHOD_NAME = "method-name";//$NON-NLS-1$
	String METHOD_PARAM = "method-param";//$NON-NLS-1$
	String METHOD_PARAMS = "method-params";//$NON-NLS-1$
	String METHOD_PERMISSION = "method-permission";//$NON-NLS-1$
	String MULTIPLICITY = "multiplicity";//$NON-NLS-1$					// EJB2.0
	String PERSISTENCE_TYPE = "persistence-type";//$NON-NLS-1$
	String PRIM_KEY_CLASS = "prim-key-class";//$NON-NLS-1$
	String PRIMKEY_FIELD = "primkey-field";//$NON-NLS-1$
	String REENTRANT = "reentrant";//$NON-NLS-1$
	String RELATIONSHIP_ROLE_SOURCE = "relationship-role-source";//$NON-NLS-1$ // EJB2.0
	String RELATIONSHIPS = "relationships"; 	//$NON-NLS-1$			// EJB2.0
	String RESULT_TYPE_MAPPING = "result-type-mapping";	//$NON-NLS-1$	// EJB2.0
	String QUERY = "query";			//$NON-NLS-1$						// EJB2.0
	String QUERY_METHOD = "query-method";	//$NON-NLS-1$				// EJB2.0
	String SESSION = "session";//$NON-NLS-1$
	String SESSION_TYPE = "session-type";//$NON-NLS-1$
	String SUBSCRIPTION_DURABILITY = "subscription-durability";//$NON-NLS-1$ // EJB2.0
	String TRANS_ATTRIBUTE = "trans-attribute";//$NON-NLS-1$
	String TRANSACTION_TYPE = "transaction-type";//$NON-NLS-1$
	String UNCHECKED = "unchecked";			//$NON-NLS-1$				// EJB2.0

	//EJB 2.1	
	String SERVICE_ENDPOINT = "service-endpoint";//$NON-NLS-1$
	String MESSAGING_TYPE = "messaging-type";//$NON-NLS-1$
	String MESSAGE_DEST_TYPE = "message-destination-type";//$NON-NLS-1$
	String MESSAGE_DEST_LINK = "message-destination-link";//$NON-NLS-1$
	String ACTIVATION_CONF = "activation-config";//$NON-NLS-1$
	String ACTIVATION_CONF_PROPERTY = "activation-config-property";//$NON-NLS-1$
	String ACTIVATION_CONF_PROPERTY_NAME = "activation-config-property-name";//$NON-NLS-1$
	String ACTIVATION_CONF_PROPERTY_VALUE = "activation-config-property-value";//$NON-NLS-1$
}


