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
package org.eclipse.jst.j2ee.internal.ejb.codegen;



/**
 * Insert the type's description here. Creation date: (10/4/00 4:47:37 PM)
 * 
 * @author: Steve Wasleski
 */
public interface IEJBGenConstants extends org.eclipse.jst.j2ee.internal.java.codegen.IJavaGenConstants {
	/**
	 *  
	 */
	String DICTIONARY_NAME = "ejbcreatedictionary.xml"; //$NON-NLS-1$
	/**
	 * Compilation Unit Group Generator Logical Names - An EJB is implemented by several types
	 */
	String SESSION_GENERATOR_NAME = "SessionGenerator"; //$NON-NLS-1$
	String ENTITY_GENERATOR_NAME = "EntityGenerator"; //$NON-NLS-1$
	String CMP_ENTITY_GENERATOR_NAME = "CMPEntityGenerator"; //$NON-NLS-1$
	/**
	 * Compilation Unit Generator Logical Names
	 */
	String SESSION_REMOTE_INTERFACE_CU = "SessionRemoteInterfaceCU"; //$NON-NLS-1$
	String SESSION_SERVICE_ENDPOINT_INTERFACE_CU = "SessionServiceEndpointInterfaceCU"; //$NON-NLS-1$
	String SESSION_HOME_INTERFACE_CU = "SessionHomeInterfaceCU"; //$NON-NLS-1$
	String SESSION_BEAN_CLASS_CU = "SessionBeanClassCU"; //$NON-NLS-1$
	String ENTITY_REMOTE_INTERFACE_CU = "EntityRemoteInterfaceCU"; //$NON-NLS-1$
	String ENTITY_HOME_INTERFACE_CU = "EntityHomeInterfaceCU"; //$NON-NLS-1$
	String ENTITY_BEAN_CLASS_CU = "EntityBeanClassCU"; //$NON-NLS-1$
	String ENTITY_KEY_CLASS_CU = "EntityKeyClassCU"; //$NON-NLS-1$
	String CMP_ENTITY_REMOTE_INTERFACE_CU = "CMPEntityRemoteInterfaceCU"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_INTERFACE_CU = "CMPEntityHomeInterfaceCU"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_CLASS_CU = "CMPEntityBeanClassCU"; //$NON-NLS-1$
	String CMP_ENTITY_KEY_CLASS_CU = "CMPEntityKeyClassCU"; //$NON-NLS-1$

	//Local CU generators
	String SESSION_LOCAL_INTERFACE_CU = "SessionLocalInterfaceCU"; //$NON-NLS-1$
	String SESSION_LOCAL_HOME_INTERFACE_CU = "SessionLocalHomeInterfaceCU"; //$NON-NLS-1$
	String ENTITY_LOCAL_INTERFACE_CU = "EntityLocalInterfaceCU"; //$NON-NLS-1$
	String ENTITY_LOCAL_HOME_INTERFACE_CU = "EntityLocalHomeInterfaceCU"; //$NON-NLS-1$
	/**
	 * Type Generator Logical Names
	 */
	String SESSION_REMOTE_INTERFACE = "SessionRemoteInterface"; //$NON-NLS-1$
	String SESSION_SERVICE_ENDPOINT_INTERFACE = "SessionServiceEndpointInterface"; //$NON-NLS-1$
	String SESSION_HOME_INTERFACE = "SessionHomeInterface"; //$NON-NLS-1$
	String SESSION_BEAN_CLASS = "SessionBeanClass"; //$NON-NLS-1$
	String ENTITY_REMOTE_INTERFACE = "EntityRemoteInterface"; //$NON-NLS-1$
	String ENTITY_HOME_INTERFACE = "EntityHomeInterface"; //$NON-NLS-1$
	String ENTITY_BEAN_CLASS = "EntityBeanClass"; //$NON-NLS-1$
	String ENTITY_KEY_CLASS = "EntityKeyClass"; //$NON-NLS-1$
	String CMP_ENTITY_REMOTE_INTERFACE = "CMPEntityRemoteInterface"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_INTERFACE = "CMPEntityHomeInterface"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_CLASS = "CMPEntityBeanClass"; //$NON-NLS-1$
	String CMP_ENTITY_KEY_CLASS = "CMPEntityKeyClass"; //$NON-NLS-1$

	//Local Type generators
	String SESSION_LOCAL_INTERFACE = "SessionLocalInterface"; //$NON-NLS-1$
	String SESSION_LOCAL_HOME_INTERFACE = "SessionLocalHomeInterface"; //$NON-NLS-1$
	String ENTITY_LOCAL_INTERFACE = "EntityLocalInterface"; //$NON-NLS-1$
	String ENTITY_LOCAL_HOME_INTERFACE = "EntityLocalHomeInterface"; //$NON-NLS-1$

	/**
	 * Common type names used in EJB Generation
	 */
	String EJBOBJECT_INTERFACE_NAME = "javax.ejb.EJBObject"; //$NON-NLS-1$
	String EJBHOME_INTERFACE_NAME = "javax.ejb.EJBHome"; //$NON-NLS-1$
	String SESSIONBEAN_INTERFACE_NAME = "javax.ejb.SessionBean"; //$NON-NLS-1$
	String ENTITYBEAN_INTERFACE_NAME = "javax.ejb.EntityBean"; //$NON-NLS-1$
	String SERIALIZABLE_INTERFACE_NAME = "java.io.Serializable"; //$NON-NLS-1$
	String REMOTE_EXCEPTION_NAME = "java.rmi.RemoteException"; //$NON-NLS-1$
	String NAMING_EXCEPTION_NAME = "javax.naming.NamingException"; //$NON-NLS-1$
	String CREATE_EXCEPTION_NAME = "javax.ejb.CreateException"; //$NON-NLS-1$
	String FINDER_EXCEPTION_NAME = "javax.ejb.FinderException"; //$NON-NLS-1$
	String REMOVE_EXCEPTION_NAME = "javax.ejb.RemoveException"; //$NON-NLS-1$
	String EJB_EXCEPTION_NAME = "javax.ejb.EJBException"; //$NON-NLS-1$
	String SESSION_CONTEXT_NAME = "javax.ejb.SessionContext"; //$NON-NLS-1$
	String ENTITY_CONTEXT_NAME = "javax.ejb.EntityContext"; //$NON-NLS-1$
	String OBJECT_CLASS_NAME = "java.lang.Object"; //$NON-NLS-1$
	String MANY_FINDER_RETURN_TYPE = "java.util.Enumeration"; //$NON-NLS-1$
	String PRIMITIVE_BOOLEAN_NAME = "boolean"; //$NON-NLS-1$
	String ITERATOR_TYPE_NAME = "java.util.Iterator"; //$NON-NLS-1$
	//imports used by associations
	String JAVA_UTIL_ALL = "java.util.*"; //$NON-NLS-1$
	String JAVA_RMI_ALL = "java.rmi.*"; //$NON-NLS-1$
	String JAVAX_EJB_ALL = "javax.ejb.*"; //$NON-NLS-1$
	String JAVAX_NAMING_ALL = "javax.naming.*"; //$NON-NLS-1$
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
	String ENTITY_ATTRIBUTE = "EntityAttribute"; //$NON-NLS-1$
	String ENTITY_ATTRIBUTE_FIELD = "EntityAttributeField"; //$NON-NLS-1$
	String ENTITY_ATTRIBUTE_GETTER = "EntityAttributeGetter"; //$NON-NLS-1$
	String ENTITY_ATTRIBUTE_SETTER = "EntityAttributeSetter"; //$NON-NLS-1$
	String ENTITY_ATTRIBUTE_REMOVE_FROM_KEY = "EntityAttributeRemoveFromKey"; //$NON-NLS-1$
	/**
	 * Shared Enterprise Bean member generators
	 */
	String EJB_SUID_FIELD = "EjbSuidField"; //$NON-NLS-1$
	/**
	 * Session home interface member generators
	 */
	String SESSION_HOME_CREATE = "SessionHomeCreate"; //$NON-NLS-1$

	String SESSION_LOCAL_HOME_CREATE = "SessionLocalHomeCreate"; //$NON-NLS-1$
	/**
	 * Session bean class member generators
	 */
	String SESSION_BEAN_CONTEXT_FIELD = "SessionBeanContextField"; //$NON-NLS-1$
	String SESSION_BEAN_CONTEXT_GETTER = "SessionBeanContextGetter"; //$NON-NLS-1$
	String SESSION_BEAN_CONTEXT_SETTER = "SessionBeanContextSetter"; //$NON-NLS-1$
	String SESSION_BEAN_EJBACTIVATE = "SessionBeanEjbActivate"; //$NON-NLS-1$
	String SESSION_BEAN_EJBCREATE = "SessionBeanEjbCreate"; //$NON-NLS-1$
	String SESSION_BEAN_EJBPASSIVATE = "SessionBeanEjbPassivate"; //$NON-NLS-1$
	String SESSION_BEAN_EJBREMOVE = "SessionBeanEjbRemove"; //$NON-NLS-1$
	/**
	 * Entity home interface member generators
	 */
	String ENTITY_HOME_CREATE = "EntityHomeCreate"; //$NON-NLS-1$
	String ENTITY_HOME_FIND_BY_PRIMARY_KEY = "EntityHomeFindByPrimaryKey"; //$NON-NLS-1$

	String ENTITY_LOCAL_HOME_CREATE = "EntityLocalHomeCreate"; //$NON-NLS-1$
	String ENTITY_LOCAL_HOME_FIND_BY_PRIMARY_KEY = "EntityLocalHomeFindByPrimaryKey"; //$NON-NLS-1$
	/**
	 * CMP entity home interface member generators
	 */
	String CMP_ENTITY_HOME_CREATE = "CMPEntityHomeCreate"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_SPECIALIZED_CREATE = "CMPEntityHomeSpecializedCreate"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_FIND_BY_PRIMARY_KEY = "CMPEntityHomeFindByPrimaryKey"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_ROLE_FINDERS = "CMPEntityHomeRoleFinders"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_FIND_MANY = "CMPEntityHomeManyFinder"; //$NON-NLS-1$
	String CMP_ENTITY_HOME_FIND_ONE = "CMPEntityHomeSingleFinder"; //$NON-NLS-1$
	/**
	 * Entity bean class member generators
	 */
	String ENTITY_BEAN_CONTEXT_FIELD = "EntityBeanContextField"; //$NON-NLS-1$
	String ENTITY_BEAN_CONTEXT_GETTER = "EntityBeanContextGetter"; //$NON-NLS-1$
	String ENTITY_BEAN_CONTEXT_SETTER = "EntityBeanContextSetter"; //$NON-NLS-1$
	String ENTITY_BEAN_CONTEXT_UNSET = "EntityBeanContextUnset"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBACTIVATE = "EntityBeanEjbActivate"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBCREATE = "EntityBeanEjbCreate"; //$NON-NLS-1$
	String ENTITY_BEAN_EJB_FIND_BY_PRIMARY_KEY = "EntityBeanEjbFindByPrimaryKey"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBLOAD = "EntityBeanEjbLoad"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBPASSIVATE = "EntityBeanEjbPassivate"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBPOSTCREATE = "EntityBeanEjbPostCreate"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBREMOVE = "EntityBeanEjbRemove"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBSTORE = "EntityBeanEjbStore"; //$NON-NLS-1$
	String ENTITY_BEAN_EJBCREATE_MB = "EntityBeanEjbCreateMB"; //$NON-NLS-1$
	String ENTITY_BEAN_SPECIALIZED_EJBCREATE_MB = "EntityBeanSpecializedEjbCreateMB"; //$NON-NLS-1$

	/**
	 * Entity key class member generators
	 */
	String ENTITY_KEY_DEFAULT_CTOR = "EntityKeyDefaultCtor"; //$NON-NLS-1$
	String ENTITY_KEY_FEATURE_CTOR = "EntityKeyFeatureCtor"; //$NON-NLS-1$
	String ENTITY_KEY_EQUALS = "EntityKeyEquals"; //$NON-NLS-1$
	String ENTITY_KEY_HASHCODE = "EntityKeyHashCode"; //$NON-NLS-1$
	/**
	 * Container Managed Entity bean class member generators
	 */
	String CMP_ENTITY_BEAN_CONTEXT_FIELD = "CMPEntityBeanContextField"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_CONTEXT_GETTER = "CMPEntityBeanContextGetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_CONTEXT_SETTER = "CMPEntityBeanContextSetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_CONTEXT_UNSET = "CMPEntityBeanContextUnset"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBACTIVATE = "CMPEntityBeanEjbActivate"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBCREATE = "CMPEntityBeanEjbCreate"; //$NON-NLS-1$
	String CMP_SPECIALIZED_ENTITY_BEAN_EJBCREATE = "CMPEntityBeanSpecializedEjbCreate"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBLOAD = "CMPEntityBeanEjbLoad"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBPASSIVATE = "CMPEntityBeanEjbPassivate"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBPOSTCREATE = "CMPEntityBeanEjbPostCreate"; //$NON-NLS-1$
	String CMP_SPECIALIZED_ENTITY_BEAN_EJBPOSTCREATE = "CMPEntityBeanSpecializedEjbPostCreate"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBREMOVE = "CMPEntityBeanEjbRemove"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBSTORE = "CMPEntityBeanEjbStore"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBCREATE_MB = "CMPEntityBeanEjbCreateMB"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_EJBPOSTCREATE_MB = "CMPEntityBeanEjbPostCreateMB"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE = "CMPEntityBeanRoleGroupGenerator"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_LINK_FIELD = "CMPEntityBeanLinkField"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_LINK_GETTER = "CMPEntityBeanLinkGetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_GETTER = "CMPEntityBeanRoleGetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_MANY_GETTER = "CMPEntityBeanRoleManyGetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_SETTER = "CMPEntityBeanRoleSetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_KEY_GETTER = "CMPEntityBeanRoleKeyGetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_KEY_HYDRATE_MB = "CMPEntityBeanRoleKeyHydratorMB"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_KEY_SETTER = "CMPEntityBeanRoleKeySetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_KEY_SETTER_MB = "CMPEntityBeanRoleKeySetterMB"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_PRIVATE_SETTER = "CMPEntityBeanRolePrivateSetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_SECONDARY_SETTER = "CMPEntityBeanRoleSecondarySetter"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_SECONDARY_ADD = "CMPEntityBeanRoleSecondaryAdd"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_SECONDARY_REMOVE = "CMPEntityBeanRoleSecondaryRemove"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_ADD = "CMPEntityBeanRoleAdd"; //$NON-NLS-1$
	String CMP_ENTITY_BEAN_ROLE_REMOVE = "CMPEntityBeanRoleRemove"; //$NON-NLS-1$
	String CMP_ENTITY_KEY_ROLE_GROUP = "CMPEntityKeyRoleGroupGenerator"; //$NON-NLS-1$

	/**
	 * Client view creation strings.
	 */
	String EJB_METHOD_GROUP_GENERATOR = "EJBMethodGroupGenerator"; //$NON-NLS-1$
	String EJB_LOCAL_CLIENT_METHOD_GENERATOR = "EJBLocalClientMethodGenerator"; //$NON-NLS-1$
	String EJB_REMOTE_CLIENT_METHOD_GENERATOR = "EJBRemoteClientMethodGenerator"; //$NON-NLS-1$
	String EJB_SERVICE_END_POINT_CLIENT_METHOD_GENERATOR = "EJBServiceEndpointClientMethodGenerator"; //$NON-NLS-1$

	/**
	 * Common code generation strings.
	 */
	String GETTER_PREFIX = "get"; //$NON-NLS-1$
	String SETTER_PREFIX = "set"; //$NON-NLS-1$
	String BOOLEAN_GETTER_PREFIX = "is"; //$NON-NLS-1$
	String SETTER_PARM_NAME_PREFIX = "new"; //$NON-NLS-1$
	/**
	 * EJBReference static strings.
	 */
	String EJB_REF_NAME_PREFIX = "ejb/"; //$NON-NLS-1$
	String EJB_REFERENCE_LOOKUP_PREFIX = "java:comp/env/"; //$NON-NLS-1$
}