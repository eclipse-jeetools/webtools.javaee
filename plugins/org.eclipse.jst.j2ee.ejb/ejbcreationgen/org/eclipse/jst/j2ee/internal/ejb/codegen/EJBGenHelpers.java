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


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jem.internal.adapters.jdom.JDOMAdaptor;
import org.eclipse.jem.internal.adapters.jdom.JavaClassJDOMAdaptor;
import org.eclipse.jem.internal.adapters.jdom.JavaMethodJDOMAdaptor;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.impl.ContainerManagedEntityFilter;
import org.eclipse.jst.j2ee.ejb.impl.KeyRelationshipRoleAttributeFilter;
import org.eclipse.jst.j2ee.ejb.impl.KeyRelationshipRoleFilter;
import org.eclipse.jst.j2ee.ejb.impl.ModelledKeyAttributeFilter;
import org.eclipse.jst.j2ee.ejb.impl.ModelledPersistentAttributeFilter;
import org.eclipse.jst.j2ee.ejb.impl.NonKeyRequiredRoleFilter;
import org.eclipse.jst.j2ee.ejb.impl.RequiredRelationshipRoleFilter;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.Navigator;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.AttributeHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodeGenResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EJBCodegenHandlerExtensionReader;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.IEJBCodegenHandler;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;
import org.eclipse.wst.common.internal.jdt.integration.WorkingCopyProvider;
import org.eclipse.wst.validation.internal.operations.EnabledValidatorsOperation;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Insert the type's description here. Creation date: (10/12/00 10:20:00 PM)
 * 
 * @author: Steve Wasleski
 */
public final class EJBGenHelpers {
	// Navigator keys
	private static final String ENTITY_KEY_ATTRIBUTE_FIELDS_AS_PARMS = "EntityKeyAttributeFieldsAsParms"; //$NON-NLS-1$
	private static final String ENTITY_KEY_ROLE_FIELDS_AS_ROUND_PARMS = "EntityKeyRoleFieldsAsRoundParms"; //$NON-NLS-1$
	private static final String ENTITY_KEY_ROLE_FIELDS_AS_BEAN_PARMS = "EntityKeyRoleFieldsAsBeanParms"; //$NON-NLS-1$
	private static final String ENTITY_NONKEY_REQUIRED_ATTRIBUTE_FIELDS_AS_PARMS = "EntityNonKeyRequiredAttributeFieldsAsParms"; //$NON-NLS-1$
	private static final String ENTITY_NON_KEY_REQ_ROLE_FIELDS_AS_BEAN_PARMS = "EntityNonKeyRequiredRoleFieldsAsBeanParms"; //$NON-NLS-1$
	private static final String ENTITY_KEY_FIELDS_AS_FLAT_PARMS = "EntityKeyFieldsAsFlatParms"; //$NON-NLS-1$
	private static final String ENTITY_KEY_FIELDS_AS_ROUND_PARMS = "EntityKeyFieldsAsRoundParms"; //$NON-NLS-1$
	private static final String ENTITY_KEY_FIELDS_AS_BEAN_PARMS = "EntityKeyFieldsAsBeanParms"; //$NON-NLS-1$
	private static final String ENTITY_REQ_ROLE_FIELDS_AS_FLAT_PARMS = "EntityRequiredRoleFieldsAsFlatParms"; //$NON-NLS-1$
	private static final String ENTITY_REQ_ROLE_FIELDS_AS_ROUND_PARMS = "EntityRequiredRoleFieldsAsRoundParms"; //$NON-NLS-1$
	private static final String ENTITY_REQ_ROLE_FIELDS_AS_BEAN_PARMS = "EntityRequiredRoleFieldsAsBeanParms"; //$NON-NLS-1$
	private static final String ENTITY_REQ_FIELDS_AS_FLAT_PARMS = "EntityRequiredFieldsAsFlatParms"; //$NON-NLS-1$
	private static final String ENTITY_REQ_FIELDS_AS_ROUND_PARMS = "EntityRequiredFieldsAsRoundParms"; //$NON-NLS-1$
	private static final String ENTITY_REQ_FIELDS_AS_BEAN_PARMS = "EntityRequiredFieldsAsBeanParms"; //$NON-NLS-1$

	private static final String A = "a"; //$NON-NLS-1$
	private static final String AN = "an"; //$NON-NLS-1$
	private static final char[] VOWELS = new char[]{'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', 'u'};
	protected IEJBCodegenHandler codegenHandler;

	/**
	 * EJBGenHelpers constructor comment.
	 */
	private EJBGenHelpers() {
		super();
	}

	/**
	 * Add a vowel prefix to
	 * 
	 * @aString.
	 */
	public static final String asParameterName(String aString) {
		String name = firstAsUppercase(aString);
		name = withVowelPrefix(name);
		return name;
	}

	private final static String computeCookieKey(String baseKey, EObject anObject) {
		if (anObject == null)
			return baseKey;
		return baseKey + anObject.hashCode();
	}

	/**
	 * Return aString where the first character is uppercased.
	 */
	public static final String firstAsUppercase(String aString) {
		if (aString != null && aString.length() > 0 && !Character.isUpperCase(aString.charAt(0))) {
			char[] chars = aString.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			return String.valueOf(chars);
		}
		return aString;
	}

	/**
	 * Returns the type for the given attribute.
	 */
	private static JavaHelpers getAttributeType(Entity entity, CMPAttribute attribute) throws GenerationException {
		JavaHelpers type = attribute.getType();
		if (type == null && entity.isContainerManagedEntity()) {
			ContainerManagedEntity root = getRootEnterpriseBean((ContainerManagedEntity) entity);
			if (root != entity) {
				CMPAttribute rootAttribute = root.getPersistentAttribute(attribute.getName());
				type = rootAttribute == null ? null : rootAttribute.getType();
			}
		}
		return type;
	}

	private static ContainerManagedEntity getRootEnterpriseBean(ContainerManagedEntity entity) {
		EjbModuleExtensionHelper handler = getEJBModuleExtension();
		if (handler == null)
			return null;
		EnterpriseBean bean = handler.getSuperType(entity);
		return (ContainerManagedEntity) bean;
	}

	protected static IEJBCodegenHandler getCodegenHandler(EnterpriseBean anEJB) {
		return EJBCodegenHandlerExtensionReader.getInstance().getEJBExtHandler(ProjectUtilities.getProject(anEJB));
	}

	protected static EjbModuleExtensionHelper getEJBModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * Return the String that should be used in an EJBReference for the ejb-ref-name.
	 */
	public static final String getEJBReferenceLookupString(EnterpriseBean anEJB) {
		if (anEJB == null)
			return null;
		return IEJBGenConstants.EJB_REFERENCE_LOOKUP_PREFIX + getEJBRefName(anEJB);
	}

	/**
	 * Return the String that should be used in an EJBReference for the ejb-ref-name.
	 */
	public static final String getEJBReferenceLookupString(EjbRef anEjbRef) {
		if (anEjbRef == null)
			return null;
		return IEJBGenConstants.EJB_REFERENCE_LOOKUP_PREFIX + anEjbRef.getName();
	}

	/**
	 * Return the String that should be used in an EJBReference for the ejb-ref-name.
	 */
	public static final String getEJBRefName(EnterpriseBean anEJB) {
		if (anEJB == null)
			return null;
		return IEJBGenConstants.EJB_REF_NAME_PREFIX + anEJB.getName();
	}

	/**
	 * Returns the attribute key fields as parms.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyAttributeFieldsAsParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(ENTITY_KEY_ATTRIBUTE_FIELDS_AS_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			if (!entity.isContainerManagedEntity())
				return new JavaParameterDescriptor[0];
			List attributes = ((ContainerManagedEntity) entity).getFilteredFeatures(ModelledKeyAttributeFilter.singleton());
			parmDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, attributes);
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the non key required attribute fields as parms.
	 */
	public final static JavaParameterDescriptor[] getEntityNonKeyRequiredAttributeFieldsAsParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(ENTITY_NONKEY_REQUIRED_ATTRIBUTE_FIELDS_AS_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			if (!entity.isContainerManagedEntity())
				return new JavaParameterDescriptor[0];
			List attributes = null;
			attributes = ((ContainerManagedEntity) entity).getFilteredFeatures(ModelledPersistentAttributeFilter.singleton());

			ArrayList nonKeyRequiredAttributes = new ArrayList();
			for (Iterator iter = attributes.iterator(); iter.hasNext();) {
				CMPAttribute cmpAttribute = (CMPAttribute) iter.next();
				// Add the non key non null attributes to the list
				if (cmpAttribute.getLowerBound() == 1 && !cmpAttribute.isKey())
					nonKeyRequiredAttributes.add(cmpAttribute);
			}
			// return null if there are not any non key required attributes
			if (!nonKeyRequiredAttributes.isEmpty())
				parmDescs = getEntityNonKeyRequiredAttributeFieldsAsParms(entity, topHelper, nonKeyRequiredAttributes);
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the attribute key fields as parms.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyAttributeFieldsAsParms(Entity entity, EntityHelper topHelper, List attributes) throws GenerationException {
		return getEntityAttributeFieldsAsParms(entity, attributes, topHelper.getKeyAttributeHelpers());
	}

	/**
	 * Returns the non key required attribute fields as parms.
	 */
	public final static JavaParameterDescriptor[] getEntityNonKeyRequiredAttributeFieldsAsParms(Entity entity, EntityHelper topHelper, List attributes) throws GenerationException {
		return getEntityAttributeFieldsAsParms(entity, attributes, topHelper.getAttributeHelpers());
	}

	/**
	 * Returns the attribute key fields as parms.
	 */
	private final static JavaParameterDescriptor[] getEntityAttributeFieldsAsParms(Entity entity, List attributes, List attrHelpers) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = new JavaParameterDescriptor[attributes.size()];
		// Just attributes for now.
		CMPAttribute attribute = null;
		String typeName = null;
		String name = null;
		Iterator attributesIter = attributes.iterator();
		int i = 0;
		while (attributesIter.hasNext()) {
			typeName = null;
			parmDescs[i] = new JavaParameterDescriptor();
			attribute = (CMPAttribute) attributesIter.next();
			name = attribute.getName();
			parmDescs[i].setName(name);
			JavaHelpers type = getAttributeType(entity, attribute);
			if (type != null)
				typeName = type.getQualifiedName();
			if (typeName == null) {
				AttributeHelper attrHelper = null;
				Iterator iter = attrHelpers.iterator();
				while ((iter.hasNext()) && (typeName == null)) {
					attrHelper = (AttributeHelper) iter.next();
					if (attrHelper.getName().equals(name))
						typeName = attrHelper.getGenerationTypeName();
				}
			}
			if (typeName == null)
				throw new GenerationException(EJBCodeGenResourceHandler.getString("New_key_attribute_added_wi_ERROR_")); //$NON-NLS-1$ = "New key attribute added without attribute helper."
			parmDescs[i].setType(typeName);
			i++;
		}
		return parmDescs;
	}

	/**
	 * Returns the key fields as parms. Role fields are beans.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyFieldsAsBeanParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(ENTITY_KEY_FIELDS_AS_BEAN_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			JavaParameterDescriptor[] attrDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, navigator);
			JavaParameterDescriptor[] roleDescs = getEntityKeyRoleFieldsAsBeanParms(entity, topHelper, navigator);
			parmDescs = new JavaParameterDescriptor[attrDescs.length + roleDescs.length];
			System.arraycopy(attrDescs, 0, parmDescs, 0, attrDescs.length);
			System.arraycopy(roleDescs, 0, parmDescs, attrDescs.length, roleDescs.length);
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the key fields as parms. Role fields are flat.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyFieldsAsFlatParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(ENTITY_KEY_FIELDS_AS_FLAT_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			JavaParameterDescriptor[] attrDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, navigator);
			JavaParameterDescriptor[] roleDescs = getEntityKeyRoleFieldsAsFlatParms(entity, topHelper, navigator);
			parmDescs = new JavaParameterDescriptor[attrDescs.length + roleDescs.length];
			System.arraycopy(attrDescs, 0, parmDescs, 0, attrDescs.length);
			System.arraycopy(roleDescs, 0, parmDescs, attrDescs.length, roleDescs.length);
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the key fields as parms. Role fields are round.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyFieldsAsRoundParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(ENTITY_KEY_FIELDS_AS_ROUND_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			JavaParameterDescriptor[] attrDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, navigator);
			JavaParameterDescriptor[] roleDescs = getEntityKeyRoleFieldsAsRoundParms(entity, topHelper, navigator);
			parmDescs = new JavaParameterDescriptor[attrDescs.length + roleDescs.length];
			System.arraycopy(attrDescs, 0, parmDescs, 0, attrDescs.length);
			System.arraycopy(roleDescs, 0, parmDescs, attrDescs.length, roleDescs.length);
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the role key fields as bean parms. Create parameters with the role name and its
	 * target type's remote interface name.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyRoleFieldsAsBeanParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsBeanParms(entity, topHelper, navigator, ENTITY_KEY_ROLE_FIELDS_AS_BEAN_PARMS, KeyRelationshipRoleFilter.singleton());
	}

	/**
	 * Returns the role key fields as flat parms. Return parameters for each set of key roles.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyRoleFieldsAsFlatParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsFlatParms(entity, topHelper, navigator, ENTITY_KEY_FIELDS_AS_FLAT_PARMS, KeyRelationshipRoleAttributeFilter.singleton());
	}

	/**
	 * Returns the role key fields as round parms. Return the key roles' target key class as a
	 * parameter.
	 */
	public final static JavaParameterDescriptor[] getEntityKeyRoleFieldsAsRoundParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsRoundParms(entity, topHelper, navigator, ENTITY_KEY_ROLE_FIELDS_AS_ROUND_PARMS, KeyRelationshipRoleFilter.singleton());
	}

	/**
	 * Return the key roles.
	 */
	public final static List getEntityKeyRoles(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		if (!entity.isContainerManagedEntity())
			return Collections.EMPTY_LIST;
		String key = null;
		List result = ((ContainerManagedEntity) entity).getFilteredFeatures(KeyRelationshipRoleFilter.singleton());
		if (result == null)
			result = Collections.EMPTY_LIST;
		if (navigator != null)
			navigator.setCookie(key, result);
		return result;
	}

	/**
	 * Returns the required fields as parms. Role fields are beans.
	 */
	public final static JavaParameterDescriptor[] getEntityRequiredFieldsAsBeanParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String key = computeCookieKey(ENTITY_REQ_FIELDS_AS_BEAN_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(key);
		if (parmDescs == null) {
			JavaParameterDescriptor[] attrDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, navigator);
			JavaParameterDescriptor[] roleDescs = getEntityRequiredRoleFieldsAsBeanParms(entity, topHelper, navigator);
			parmDescs = new JavaParameterDescriptor[attrDescs.length + roleDescs.length];
			System.arraycopy(attrDescs, 0, parmDescs, 0, attrDescs.length);
			System.arraycopy(roleDescs, 0, parmDescs, attrDescs.length, roleDescs.length);
			if (navigator != null)
				navigator.setCookie(key, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the required fields as parms. Role fields are flat.
	 */
	public final static JavaParameterDescriptor[] getEntityRequiredFieldsAsFlatParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String key = computeCookieKey(ENTITY_REQ_FIELDS_AS_FLAT_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(key);
		if (parmDescs == null) {
			JavaParameterDescriptor[] attrDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, navigator);
			JavaParameterDescriptor[] roleDescs = getEntityRequiredRoleFieldsAsFlatParms(entity, topHelper, navigator);
			parmDescs = new JavaParameterDescriptor[attrDescs.length + roleDescs.length];
			System.arraycopy(attrDescs, 0, parmDescs, 0, attrDescs.length);
			System.arraycopy(roleDescs, 0, parmDescs, attrDescs.length, roleDescs.length);
			if (navigator != null)
				navigator.setCookie(key, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the required fields as parms. Role fields are round.
	 */
	public final static JavaParameterDescriptor[] getEntityRequiredFieldsAsRoundParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		JavaParameterDescriptor[] parmDescs = null;
		String key = computeCookieKey(ENTITY_REQ_FIELDS_AS_ROUND_PARMS, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(key);
		if (parmDescs == null) {
			JavaParameterDescriptor[] attrDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, navigator);
			JavaParameterDescriptor[] roleDescs = getEntityRequiredRoleFieldsAsRoundParms(entity, topHelper, navigator);
			parmDescs = new JavaParameterDescriptor[attrDescs.length + roleDescs.length];
			System.arraycopy(attrDescs, 0, parmDescs, 0, attrDescs.length);
			System.arraycopy(roleDescs, 0, parmDescs, attrDescs.length, roleDescs.length);
			if (navigator != null)
				navigator.setCookie(key, parmDescs);
		}
		return parmDescs;
	}

	public final static JavaParameterDescriptor[] getEntityNonKeyRequiredRoleFieldsAsBeanParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsBeanParms(entity, topHelper, navigator, ENTITY_NON_KEY_REQ_ROLE_FIELDS_AS_BEAN_PARMS, NonKeyRequiredRoleFilter.singleton());
	}

	/**
	 * Returns the required role fields as bean parms. Create parameters with the role name and its
	 * target type's remote interface name.
	 */
	public final static JavaParameterDescriptor[] getEntityRequiredRoleFieldsAsBeanParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsBeanParms(entity, topHelper, navigator, ENTITY_REQ_ROLE_FIELDS_AS_BEAN_PARMS, RequiredRelationshipRoleFilter.singleton());
	}

	/**
	 * Returns the required role fields as flat parms. Return parameters for each set of key roles.
	 */
	public final static JavaParameterDescriptor[] getEntityRequiredRoleFieldsAsFlatParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsFlatParms(entity, topHelper, navigator, ENTITY_REQ_ROLE_FIELDS_AS_FLAT_PARMS, RequiredRelationshipRoleFilter.singleton());
	}

	/**
	 * Returns the required role fields as round parms. Return the key roles' target key class as a
	 * parameter.
	 */
	public final static JavaParameterDescriptor[] getEntityRequiredRoleFieldsAsRoundParms(Entity entity, EntityHelper topHelper, Navigator navigator) throws GenerationException {
		return getEntityRoleFieldsAsRoundParms(entity, topHelper, navigator, ENTITY_REQ_ROLE_FIELDS_AS_ROUND_PARMS, RequiredRelationshipRoleFilter.singleton());
	}

	/**
	 * Returns the role key fields as bean parms. Create parameters with the role name and its
	 * target type's remote interface name.
	 */
	private final static JavaParameterDescriptor[] getEntityRoleFieldsAsBeanParms(Entity entity, EntityHelper topHelper, Navigator navigator, String baseKey, ContainerManagedEntityFilter aFilter) throws GenerationException {
		if (!entity.isContainerManagedEntity())
			return new JavaParameterDescriptor[0];
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(baseKey, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			List keyRoles = ((ContainerManagedEntity) entity).getFilteredFeatures(aFilter);
			parmDescs = new JavaParameterDescriptor[keyRoles.size()];
			CommonRelationshipRole role;
			for (int i = 0; i < keyRoles.size(); i++) {
				role = (CommonRelationshipRole) keyRoles.get(i);
				parmDescs[i] = new JavaParameterDescriptor();
				parmDescs[i].setName(RoleHelper.getParameterName(role));
				ContainerManagedEntity cmp = RoleHelper.getRoleType(role);
				if (cmp != null) {
					switch (cmp.getVersionID()) {
						case J2EEVersionConstants.EJB_1_0_ID :
						case J2EEVersionConstants.EJB_1_1_ID :
							parmDescs[i].setType(cmp.getRemoteInterfaceName());
							break;
						case J2EEVersionConstants.EJB_2_0_ID :
						case J2EEVersionConstants.EJB_2_1_ID :
						default :
							parmDescs[i].setType(cmp.getLocalInterfaceName());
							break;
					}
				}
			}
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the role key fields as flat parms. Return parameters for each set of key roles.
	 */
	private final static JavaParameterDescriptor[] getEntityRoleFieldsAsFlatParms(Entity entity, EntityHelper topHelper, Navigator navigator, String baseKey, ContainerManagedEntityFilter aFilter) throws GenerationException {
		if (!entity.isContainerManagedEntity())
			return new JavaParameterDescriptor[0];
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(baseKey, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			List roleAttributes = ((ContainerManagedEntity) entity).getFilteredFeatures(aFilter);
			parmDescs = getEntityKeyAttributeFieldsAsParms(entity, topHelper, roleAttributes);
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the role fields as round parms. Return the key roles' target key class as a
	 * parameter.
	 */
	private final static JavaParameterDescriptor[] getEntityRoleFieldsAsRoundParms(Entity entity, EntityHelper topHelper, Navigator navigator, String baseKey, ContainerManagedEntityFilter aFilter) throws GenerationException {
		if (!entity.isContainerManagedEntity())
			return new JavaParameterDescriptor[0];
		JavaParameterDescriptor[] parmDescs = null;
		String cookieKey = computeCookieKey(baseKey, entity);
		if (navigator != null)
			parmDescs = (JavaParameterDescriptor[]) navigator.getCookie(cookieKey);
		if (parmDescs == null) {
			List roles = ((ContainerManagedEntity) entity).getFilteredFeatures(aFilter);
			parmDescs = new JavaParameterDescriptor[roles.size()];
			CommonRelationshipRole role;
			for (int i = 0; i < roles.size(); i++) {
				role = (CommonRelationshipRole) roles.get(i);
				parmDescs[i] = new JavaParameterDescriptor();
				parmDescs[i].setName(RoleHelper.getParameterName(role));
				parmDescs[i].setType(RoleHelper.getRoleType(role).getPrimaryKeyName());
			}
			if (navigator != null)
				navigator.setCookie(cookieKey, parmDescs);
		}
		return parmDescs;
	}

	/**
	 * Returns the primitive wrapper for the type.
	 */
	public final static String getPrimitiveWrapper(String type) {
		if (type.equals("boolean")) //$NON-NLS-1$
			return "java.lang.Boolean"; //$NON-NLS-1$
		if (type.equals("byte")) //$NON-NLS-1$
			return "java.lang.Byte"; //$NON-NLS-1$
		if (type.equals("char")) //$NON-NLS-1$
			return "java.lang.Character"; //$NON-NLS-1$
		if (type.equals("short")) //$NON-NLS-1$
			return "java.lang.Short"; //$NON-NLS-1$
		if (type.equals("int")) //$NON-NLS-1$
			return "java.lang.Integer"; //$NON-NLS-1$
		if (type.equals("long")) //$NON-NLS-1$
			return "java.lang.Long"; //$NON-NLS-1$
		if (type.equals("float")) //$NON-NLS-1$
			return "java.lang.Float"; //$NON-NLS-1$
		if (type.equals("double")) //$NON-NLS-1$
			return "java.lang.Double"; //$NON-NLS-1$
		return null;
	}

	/**
	 * Returns the primitive wrapper for the type.
	 */
	public final static String getPrimitiveWrapperShortName(String type) {
		if (type.equals("boolean")) //$NON-NLS-1$
			return "Boolean"; //$NON-NLS-1$
		if (type.equals("byte")) //$NON-NLS-1$
			return "Byte"; //$NON-NLS-1$
		if (type.equals("char")) //$NON-NLS-1$
			return "Character"; //$NON-NLS-1$
		if (type.equals("short")) //$NON-NLS-1$
			return "Short"; //$NON-NLS-1$
		if (type.equals("int")) //$NON-NLS-1$
			return "Integer";//$NON-NLS-1$
		if (type.equals("long")) //$NON-NLS-1$
			return "Long"; //$NON-NLS-1$
		if (type.equals("float")) //$NON-NLS-1$
			return "Float"; //$NON-NLS-1$
		if (type.equals("double")) //$NON-NLS-1$
			return "Double"; //$NON-NLS-1$
		return null;
	}

	/**
	 * Returns true if the type is a primitive.
	 */
	public final static boolean isPrimitive(String type) {
		return (type.equals("boolean") //$NON-NLS-1$
					|| type.equals("byte") //$NON-NLS-1$
					|| type.equals("char") //$NON-NLS-1$
					|| type.equals("short") //$NON-NLS-1$
					|| type.equals("int") //$NON-NLS-1$
					|| type.equals("long") //$NON-NLS-1$
					|| type.equals("float") //$NON-NLS-1$
		|| type.equals("double")); //$NON-NLS-1$
	}

	public static boolean isRoot(EnterpriseBean anEJB) {
		if (anEJB != null) {
			EnterpriseBean superType = getSupertype(anEJB);
			if (superType == null)
				return true;
		}
		return false;
	}

	protected static EnterpriseBean getSupertype(EnterpriseBean ejb) {
		EjbModuleExtensionHelper helper = getEjbModuleExtension();
		if (helper != null)
			return helper.getSuperType(ejb);
		return null;
	}

	protected static EjbModuleExtensionHelper getEjbModuleExtension() {
		return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
	}

	/**
	 * Return aString where with a prefixed vowel (a or an).
	 */
	public static final boolean isVowel(char aChar) {
		for (int i = 0; i < VOWELS.length; i++) {
			if (aChar == VOWELS[i])
				return true;
		}
		return false;
	}

	/**
	 * Return aString where with a prefixed vowel (a or an).
	 */
	public static final String withVowelPrefix(String aString) {
		if (aString != null && aString.length() > 0) {
			char first = aString.charAt(0);
			if (isVowel(first))
				return AN + aString;
			return A + aString;
		}
		return aString;
	}

	/**
	 * Method isArray.
	 * 
	 * @param type
	 * @return boolean
	 */
	public static boolean isArray(String type) {
		if (type != null && type.length() > 0) {
			int index = type.indexOf('[');
			return index > -1;
		}
		return false;
	}

	public static ICompilationUnit getCompilationUnit(JavaClass javaClass) {
		IType type = getType(javaClass);
		if (type != null)
			return type.getCompilationUnit();
		return null;
	}

	public static IType getType(JavaClass javaClass) {
		if (javaClass != null) {
			JavaClassJDOMAdaptor adaptor = (JavaClassJDOMAdaptor) EcoreUtil.getRegisteredAdapter(javaClass, ReadAdaptor.TYPE_KEY);
			if (adaptor != null)
				return adaptor.getSourceType();
		}
		return null;
	}

	public static IMethod getMethod(Method javaMethod) {
		if (javaMethod != null) {
			JavaMethodJDOMAdaptor adaptor = (JavaMethodJDOMAdaptor) EcoreUtil.getRegisteredAdapter(javaMethod, ReadAdaptor.TYPE_KEY);
			if (adaptor != null)
				return adaptor.getSourceMethod();
		}
		return null;
	}

	public static ICompilationUnit getWorkingCopy(JavaClass javaClass, WorkingCopyProvider provider, Object shell) throws org.eclipse.core.runtime.CoreException {
		ICompilationUnit cu = getCompilationUnit(javaClass);
		IStatus status = validateEdit(cu, shell);
		if (cu != null && (status == null || status.isOK()))
			return provider.getWorkingCopy(cu, false);
		return null;
	}

	public static IMethod getMethod(MethodElement me, WorkingCopyProvider provider, Map typeCache, Object shell) throws CoreException {
		return getMethod(me, provider, typeCache, shell, true);
	}

	public static IMethod getMethod(MethodElement me, WorkingCopyProvider provider, Map typeCache, Object shell, boolean useWorkingCopy) throws CoreException {
		if (me != null)
			return getMethod(me, me.getTypeJavaClass(), provider, typeCache, shell, useWorkingCopy);
		return null;
	}

	public static IMethod getMethod(MethodElement me, JavaClass typeClass, WorkingCopyProvider provider, Object shell, Map typeCache) throws CoreException {
		return getMethod(me, typeClass, provider, typeCache, shell, false);
	}

	public static IMethod getMethod(MethodElement me, JavaClass typeClass, WorkingCopyProvider provider, Map typeCache, Object shell, boolean useWorkingCopy) throws CoreException {
		IMethod method = null;
		if (me != null) {
			IType type = getType(typeClass);
			if (type == null)
				return null;
			if (type.isBinary() || !useWorkingCopy)
				method = getMethod(me, type, typeCache);
			if (method == null) {
				ICompilationUnit cu = getWorkingCopy(typeClass, provider, shell);
				if (cu != null)
					method = getMethod(me, cu.findPrimaryType(), typeCache);
			}
		}
		return method;
	}

	private static IMethod getMethod(MethodElement me, IType type, Map typeCache) throws CoreException {
		IMethod[] children = type.getMethods();
		IMethod method;
		for (int i = 0; i < children.length; i++) {
			method = children[i];
			if (method.getElementName().equals(me.getName())) {
				String[] typeNames = method.getParameterTypes();
				List parms = me.getMethodParams();
				if (typeNames.length != parms.size())
					continue;
				String parm;
				String resolvedName;
				boolean isMatch = true;
				for (int j = 0; j < typeNames.length; j++) {
					parm = (String) parms.get(j);
					resolvedName = JDOMAdaptor.typeNameFromSignature(typeNames[j], type, typeCache);
					if (!resolvedName.equals(parm)) {
						isMatch = false;
						break;
					}
				}
				if (isMatch)
					return method;
			}
		}
		return null;
	}

	public static IMethod[] getMethods(QueryMethod query, WorkingCopyProvider provider, Map typeCache, Object shell) throws CoreException {
		return getMethods(query, provider, typeCache, shell, false);
	}

	public static IMethod[] getMethods(QueryMethod query, WorkingCopyProvider provider, Map typeCache, Object shell, boolean useWorkingCopy) throws CoreException {
		if (query != null) {
			JavaClass[] typeClasses = query.getClientTypeJavaClasses();
			if (typeClasses.length == 1)
				return new IMethod[]{getMethod(query, provider, typeCache, shell, useWorkingCopy)};
			List methods = new ArrayList(4);
			IMethod method;
			for (int i = 0; i < typeClasses.length; i++) {
				method = getMethod(query, typeClasses[i], provider, typeCache, shell, useWorkingCopy);
				if (method != null)
					methods.add(method);
			}
			IMethod[] result = new IMethod[methods.size()];
			methods.toArray(result);
			return result;
		}
		return null;
	}

	public static String typeNameFromSignature(String sig, IMethod method, Map typeCache) {
		if (sig != null && method != null) {
			IType type = method.getDeclaringType();
			if (type != null)
				return JDOMAdaptor.typeNameFromSignature(sig, type, typeCache);
		}
		return null;
	}

	/**
	 * The sourceMethod will be updated with the contents from the updateMethod. If the signature
	 * has changed, the sourceMethod will be updated.
	 */
	public static void editMethod(IMethod sourceMethod, IDOMMethod updateMethod, WorkingCopyProvider provider, Object shell) throws JavaModelException, CoreException {
		if (sourceMethod != null && updateMethod != null && !sourceMethod.isBinary()) {
			IMethod wcMethod = getWorkingCopyMethod(sourceMethod, provider, shell);
			IType type = wcMethod.getDeclaringType();
			IJavaElement sibling = getSibling(wcMethod, type);
			String formattedSource = ToolFactory.createCodeFormatter().format(updateMethod.getContents(), 1, null, null) + "\n"; //$NON-NLS-1$
			wcMethod.delete(true, null);
			type.createMethod(formattedSource, sibling, true, null);
		}
	}

	public static void editMethods(IMethod[] sourceMethods, IDOMMethod[] updateMethods, WorkingCopyProvider provider, Object shell) throws JavaModelException, CoreException {
		if (sourceMethods != null) {
			for (int i = 0; i < sourceMethods.length; i++) {
				editMethod(sourceMethods[i], updateMethods[i], provider, shell);
			}
		}
	}

	public static IMethod getWorkingCopyMethod(IMethod sourceMethod, WorkingCopyProvider provider, Object shell) throws CoreException {
		if (sourceMethod != null && provider != null) {
			ICompilationUnit cu = sourceMethod.getCompilationUnit();
			IStatus status = validateEdit(cu, shell);
			if (!cu.isWorkingCopy() && (status == null || status.isOK())) {
				ICompilationUnit wc = provider.getWorkingCopy(sourceMethod.getCompilationUnit(), false);
				IType type = wc.findPrimaryType();
				IMethod[] methods = type.findMethods(sourceMethod);
				if (methods.length > 0 && methods[0] != null)
					return methods[0];
			}
		}
		return sourceMethod;
	}

	public static IJavaElement getSibling(IMethod sourceMethod, IType type) throws JavaModelException {
		if (sourceMethod != null && type != null) {
			IJavaElement[] elements = type.getChildren();
			int max = elements.length - 1;
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] == sourceMethod && i != max)
					return elements[i + 1];
			}
		}
		return null;
	}

	public static IStatus validateEdit(ICompilationUnit cu, Object obj) {
		if (cu != null && !cu.isWorkingCopy()) {
			try {
				IResource res = cu.getUnderlyingResource();
				if (res.getType() == IResource.FILE && res.isReadOnly())
					return ResourcesPlugin.getWorkspace().validateEdit(new IFile[]{(IFile) res}, obj);
			} catch (JavaModelException e) {
				Logger.getLogger().logError(e);
			}
		}
		return null;
	}

	public static void runWithJavaCoreWithDelayedValidation(IWorkspaceRunnable workspaceRunnable, IProject project) {
		boolean needsSuspending = !ValidatorManager.getManager().isSuspended(project);
		try {
			if (needsSuspending) {
				ValidatorManager.getManager().suspendValidation(project, true);
			}
			JavaCore.run(workspaceRunnable, null);
		} catch (CoreException ex) {
			Logger.getLogger().logError(ex);
		} finally {
			if (needsSuspending) {
				ValidatorManager.getManager().suspendValidation(project, false);
			}
		}

		if (needsSuspending) {
			EnabledValidatorsOperation validationOperation = new EnabledValidatorsOperation(project, true);
			validationOperation.run(new NullProgressMonitor());
		}
	}

}