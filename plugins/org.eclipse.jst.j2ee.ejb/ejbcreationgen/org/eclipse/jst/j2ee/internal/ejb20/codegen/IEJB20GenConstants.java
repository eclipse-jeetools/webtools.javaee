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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;

/**
 * Insert the type's description here. Creation date: (10/4/00 4:47:37 PM)
 * 
 * @author: Steve Wasleski
 */
public interface IEJB20GenConstants extends IEJBGenConstants {
	/**
	 *  
	 */
	String DICTIONARY_NAME = "ejb20createdictionary.xml"; //$NON-NLS-1$
	String EMPTY_BODY = ""; //$NON-NLS-1$

	/**
	 * Compilation Unit Group Generator Logical Names - An EJB is implemented by several types
	 */
	String CMP20_ENTITY_GENERATOR_NAME = "CMP20EntityGenerator"; //$NON-NLS-1$

	/**
	 * Compilation Unit Generator Logical Names
	 */
	String CMP20_ENTITY_LOCAL_INTERFACE_CU = "CMP20EntityLocalInterfaceCU"; //$NON-NLS-1$
	String CMP20_ENTITY_LOCAL_HOME_INTERFACE_CU = "CMP20EntityLocalHomeInterfaceCU"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_CLASS_CU = "CMP20EntityBeanClassCU"; //$NON-NLS-1$

	/**
	 * Type Generator Logical Names
	 */
	String CMP20_ENTITY_LOCAL_INTERFACE = "CMP20EntityLocalInterface"; //$NON-NLS-1$
	String CMP20_ENTITY_LOCAL_HOME_INTERFACE = "CMP20EntityLocalHomeInterface"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_CLASS = "CMP20EntityBeanClass"; //$NON-NLS-1$

	String LINK20_CLASS = "Link20Class"; //$NON-NLS-1$

	/**
	 * Common type names used in EJB Generation
	 */
	String EJBOBJECT_LOCAL_INTERFACE_NAME = "javax.ejb.EJBLocalObject"; //$NON-NLS-1$
	String EJBLOCALHOME_INTERFACE_NAME = "javax.ejb.EJBLocalHome"; //$NON-NLS-1$

	//imports used by associations
	String EJB_ASSOC_INTERFACES_ALL = "com.ibm.ivj.ejb.associations.interfaces.*"; //$NON-NLS-1$
	String EJB_ASSOC_LINKS_ALL = "com.ibm.ivj.ejb.associations.links.*"; //$NON-NLS-1$
	String EJB_ASSOC_LINK_CLASS = "com.ibm.ivj.ejb.associations.links.Link"; //$NON-NLS-1$
	String EJB_ONE_TO_ONE_SUPER_LINK = "com.ibm.ivj.ejb.associations.links.SingleToSingleLink"; //$NON-NLS-1$
	String EJB_MANY_TO_ONE_SUPER_LINK = "com.ibm.ivj.ejb.associations.links.ManyToSingleLink"; //$NON-NLS-1$
	String EJB_ONE_TO_MANY_SUPER_LINK = "com.ibm.ivj.ejb.associations.links.SingleToManyLink"; //$NON-NLS-1$
	String EJB_SINGLE_LINK_INT_NAME = "com.ibm.ivj.ejb.associations.interfaces.SingleLink"; //$NON-NLS-1$
	String EJB_MANY_LINK_INT_NAME = "com.ibm.ivj.ejb.associations.interfaces.ManyLink"; //$NON-NLS-1$

	/**
	 * Feature generator names
	 */
	String ENTITY20_ATTRIBUTE = "Entity20Attribute"; //$NON-NLS-1$
	String ENTITY20_ATTRIBUTE_GETTER = "Entity20AttributeGetter"; //$NON-NLS-1$
	String ENTITY20_ATTRIBUTE_SETTER = "Entity20AttributeSetter"; //$NON-NLS-1$

	/**
	 * Container Managed Entity bean class member generators
	 */
	String CMP20_ENTITY_BEAN_CONTEXT_FIELD = "EntityBeanContextField"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_CONTEXT_SETTER = "EntityBeanContextSetter"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_CONTEXT_GETTER = "EntityBeanContextGetter"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_CONTEXT_UNSET = "EntityBeanContextUnset"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBACTIVATE = "EntityBeanEjbActivate"; //$NON-NLS-1$
	String CMP20_SPECIALIZED_ENTITY_BEAN_EJBCREATE = "CMP20EntityBeanSpecializedEjbCreate"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBCREATE = "EntityBeanEjbCreate"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE = "CMP20EntityBeanFlatKeyRoleEjbCreate"; //$NON-NLS-1$
	String CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE = "CMP20EntityBeanSpecializedFlatKeyRoleEjbCreate"; //$NON-NLS-1$
	String ENTITY20_BEAN_SPECIALIZED_EJBCREATE_MB = "EntityBean20SpecializedEjbCreateMB"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBLOAD = "EntityBeanEjbLoad"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBPASSIVATE = "EntityBeanEjbPassivate"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBPOSTCREATE = "CMP20EntityBeanEjbPostCreate"; //$NON-NLS-1$
	String CMP20_SPECIALIZED_ENTITY_BEAN_EJBPOSTCREATE = "CMP20EntityBeanSpecializedEjbPostCreate"; //$NON-NLS-1$
	String CMP20_SPECIALIZED_ENTITY_BEAN_EJBPOSTCREATE_MB = "CMP20EntityBeanSpecializedEjbPostCreateMB"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE = "CMP20EntityBeanFlatKeyRoleEjbPostCreate"; //$NON-NLS-1$
	String CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBPOSTCREATE = "CMP20EntityBeanSpecializedFlatKeyRoleEjbPostCreate"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBREMOVE = "EntityBeanEjbRemove"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_EJBSTORE = "EntityBeanEjbStore"; //$NON-NLS-1$

	String CMP20_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE_MB = "CMP20EntityBeanFlatKeyRoleEjbCreateMB"; //$NON-NLS-1$
	String CMP20_SPECIALIZED_ENTITY_BEAN_FLAT_KEY_ROLE_EJBCREATE_MB = "CMP20EntityBeanSpecializedFlatKeyRoleEjbCreateMB"; //$NON-NLS-1$

	String CMP20_ENTITY_BEAN_ROLE = "CMP20EntityBeanRoleGroupGenerator"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_ROLE_GETTER = "CMP20EntityBeanRoleGetter"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_ROLE_MANY_GETTER = "CMP20EntityBeanRoleManyGetter"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_ROLE_MANY_SETTER = "CMP20EntityBeanRoleManySetter"; //$NON-NLS-1$
	String CMP20_ENTITY_BEAN_ROLE_SETTER = "CMP20EntityBeanRoleSetter"; //$NON-NLS-1$

	/**
	 * Entity home interface member generators
	 */
	String CMP20_FLAT_KEY_ROLE_HOME_CREATE = "CMP20FlatKeyRoleHomeCreate"; //$NON-NLS-1$
	String CMP20_ENTITY_LOCAL_HOME_CREATE = ENTITY_LOCAL_HOME_CREATE;
	String CMP20_ENTITY_LOCAL_HOME_SPECIALIZED_CREATE = "EntityLocalHomeSpecializedCreate"; //$NON-NLS-1$
	String CMP20_ENTITY_LOCAL_HOME_FIND_BY_PRIMARY_KEY = ENTITY_LOCAL_HOME_FIND_BY_PRIMARY_KEY;

	/**
	 * MDB Generators
	 */

	String MDB_GENERATOR_NAME = "MessageDrivenGenerator"; //$NON-NLS-1$

	String MDB_BEAN_CLASS_CU = "MessageDrivenBeanClassCU"; //$NON-NLS-1$

	String MDB_BEAN_CLASS = "MessageDrivenBeanClass"; //$NON-NLS-1$

	//Interfaces for the bean class
	String MESSAGE_DRIVEN_BEAN_INTERFACE_NAME = "javax.ejb.MessageDrivenBean"; //$NON-NLS-1$
	String MESSAGE_DRIVEN_CONTEXT_TYPE_NAME = "javax.ejb.MessageDrivenContext"; //$NON-NLS-1$
	String MESSAGE_LISTENTER_INTERFACE_NAME = "javax.jms.MessageListener"; //$NON-NLS-1$
	String EJB_INTERFACE_METHOD_GENERATOR = "EJBMethodGenerator"; //$NON-NLS-1$
	String MESSAGE_INTERFACE_NAME = "javax.jms.Message"; //$NON-NLS-1$
	String UNIMPLEMENTED_METHOD_GENERATOR = "UnimplementedMethodGroupGenerator"; //$NON-NLS-1$
	String MDB_EJBCREATE = "MessageDrivenEjbCreate"; //$NON-NLS-1$
	String MDB_ONMESSAGE = "MessageDrivenOnMessage"; //$NON-NLS-1$
	String MDB_EJBREMOVE = "MessageDrivenEjbRemove"; //$NON-NLS-1$
	String MDB_CONTEXT_FIELD = "MessageDrivenContextField"; //$NON-NLS-1$
	String MDB_CONTEXT_GETTER = "MessageDrivenContextGetter"; //$NON-NLS-1$
	String MDB_CONTEXT_SETTER = "MessageDrivenContextSetter"; //$NON-NLS-1$

	/*
	 * Service Endpoint Interface
	 */
	String SEI_INTERFACE_NAME = "java.rmi.Remote";//$NON-NLS-1$
}