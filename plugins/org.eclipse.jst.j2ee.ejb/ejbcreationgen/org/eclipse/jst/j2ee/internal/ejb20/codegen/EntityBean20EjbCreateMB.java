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

import java.util.List;

import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EJBGenHelpers;
import org.eclipse.jst.j2ee.internal.ejb.codegen.EntityBeanEjbCreateMB;
import org.eclipse.jst.j2ee.internal.ejb.codegen.IEJBGenConstants;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.RoleHelper;


/**
 * @author DABERG
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 */
public class EntityBean20EjbCreateMB extends EntityBeanEjbCreateMB {
	private static final String BODY_PATTERN = "%0(%1);\n"; //$NON-NLS-1$
	private static final String PRIM_ROLE_KEY_PATTERN = "%0((%1) %2.getPrimaryKey());\n"; //$NON-NLS-1$
	private static final String COMPOUND_ROLE_KEY1_PATTERN = "%0 %1PK = (%0) %1.getPrimaryKey();\n"; //$NON-NLS-1$
	private static final String COMPOUND_ROLE_KEY2_PATTERN = "%0(%1.%2);\n"; //$NON-NLS-1$
	private static final String COMPOUND_ROLE_KEY3_PATTERN = "%0(new %1(%2.%3));\n"; //$NON-NLS-1$

	protected String calculateSetterName(String attrName) {
		StringBuffer nameBuffer = new StringBuffer();
		nameBuffer.append(IEJBGenConstants.SETTER_PREFIX);
		nameBuffer.append(Character.toUpperCase(attrName.charAt(0)));
		for (int i = 1; i < attrName.length(); i++)
			nameBuffer.append(attrName.charAt(i));
		return nameBuffer.toString();
	}

	protected int getBodyPatternIndexes() {
		return 2;
	}

	protected void writeMethodBody(IGenerationBuffer buffer, String[] patternArgs, String parmName) {
		patternArgs[0] = calculateSetterName(parmName);
		patternArgs[1] = parmName;
		buffer.formatWithMargin(BODY_PATTERN, patternArgs);
	}

	/**
	 * @see org.eclipse.jst.j2ee.ejb.codegen.EntityBeanEjbCreateMB#run(IGenerationBuffer)
	 */
	public void run(IGenerationBuffer buffer) throws GenerationException {
		super.run(buffer);
		writeKeyRoles(buffer);
	}

	protected List getKeyRoles() throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		return EJBGenHelpers.getEntityKeyRoles(entity, (EntityHelper) getTopLevelHelper(), getSourceContext().getNavigator());
	}

	protected void writeKeyRoles(IGenerationBuffer buffer) throws GenerationException {
		List roles = getKeyRoles();
		if (roles.isEmpty())
			return;
		for (int i = 0; i < roles.size(); i++)
			writeKeyRole((CommonRelationshipRole) roles.get(i), buffer);
	}

	protected void writeKeyRole(CommonRelationshipRole role, IGenerationBuffer buffer) throws GenerationException {
		ContainerManagedEntity cmp = role.getTypeEntity();
		if (cmp.getPrimaryKeyAttribute() != null)
			writePrimitiveKeyRole(role, buffer);
		else
			writeCompoundKeyRole(role, cmp, buffer);
	}

	protected void writePrimitiveKeyRole(CommonRelationshipRole role, IGenerationBuffer buffer) {
		String[] args = new String[3];
		CMPAttribute att = (CMPAttribute) role.getAttributes().get(0); //should only be one in this
		// case
		args[0] = calculateSetterName(att.getName());
		args[1] = att.getType().getQualifiedName();
		args[2] = RoleHelper.getParameterName(role);
		buffer.format(PRIM_ROLE_KEY_PATTERN, args);
	}

	protected void writeCompoundKeyRole(CommonRelationshipRole role, ContainerManagedEntity roleType, IGenerationBuffer buffer) {
		String[] args = new String[2];
		args[0] = roleType.getPrimaryKeyName();
		args[1] = RoleHelper.getParameterName(role);
		buffer.formatWithMargin(COMPOUND_ROLE_KEY1_PATTERN, args);
		String pkArg = args[1] + "PK"; //$NON-NLS-1$
		List attributes = role.getAttributes();
		writeCompoundKeyRoleAttributes(role, roleType, buffer, pkArg, attributes);
	}

	protected void writeCompoundKeyRoleAttributes(CommonRelationshipRole role, ContainerManagedEntity roleType, IGenerationBuffer buffer, String pkArg, List attributes) {
		CMPAttribute att;
		String[] args2 = new String[4];
		int nameLengthToRemove = role.getName().length() + 1; //include first underscore
		String name, bodyPattern;
		int index;
		JavaClass pkClass = roleType.getPrimaryKey();
		JavaHelpers type = null;
		for (int i = 0; i < attributes.size(); i++) {
			index = 0;
			args2[0] = args2[1] = args2[2] = args2[3] = null;
			bodyPattern = COMPOUND_ROLE_KEY2_PATTERN;
			att = (CMPAttribute) attributes.get(i);
			name = att.getName().substring(nameLengthToRemove, att.getName().length());
			type = att.getOriginatingType();
			if (type == null) {
				Field pkField = pkClass.getFieldExtended(name);
				if (pkField != null)
					type = (JavaHelpers) pkField.getEType();
			}
			args2[index] = calculateSetterName(att.getName());
			if (type != null && type.isPrimitive()) {
				bodyPattern = COMPOUND_ROLE_KEY3_PATTERN;
				args2[++index] = type.getWrapper().getName();
			}
			args2[++index] = pkArg;
			args2[++index] = name;
			buffer.formatWithMargin(bodyPattern, args2);
		}
	}



}