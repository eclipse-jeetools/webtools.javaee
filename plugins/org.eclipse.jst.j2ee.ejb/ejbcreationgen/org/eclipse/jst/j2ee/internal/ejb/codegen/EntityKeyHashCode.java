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



import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EntityHelper;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaMethodGenerator;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaParameterDescriptor;


/**
 * Insert the type's description here. Creation date: (10/11/00 11:52:10 AM)
 * 
 * @author: Steve Wasleski
 */
public class EntityKeyHashCode extends JavaMethodGenerator {
	/**
	 * EntityKeyHashCode constructor comment.
	 */
	public EntityKeyHashCode() {
		super();
	}

	/**
	 * Check for same type and equality of fields.
	 */
	protected void getBody(IGenerationBuffer bodyBuf) throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		JavaParameterDescriptor[] parmDescs = EJBGenHelpers.getEntityKeyFieldsAsFlatParms(entity, topHelper, getSourceContext().getNavigator());
		if (hasArrayType(parmDescs))
			getBodyWithArrayTypes(bodyBuf, parmDescs);
		else
			getBodyWithSimpleTypes(bodyBuf, parmDescs);
	}

	protected void getBodyWithArrayTypes(IGenerationBuffer bodyBuf, JavaParameterDescriptor[] parmDescs) {
		bodyBuf.indent();
		bodyBuf.appendWithMargin("int code = 0;\n"); //$NON-NLS-1$
		bodyBuf.appendWithMargin("int i;\n"); //$NON-NLS-1$

		for (int i = 0; i < parmDescs.length; i++) {
			if (EJBGenHelpers.isArray(parmDescs[i].getType())) {
				addArrayForLoop(bodyBuf, parmDescs[i]);
			} else {
				addArrayHashCodeAddition(bodyBuf, parmDescs[i]);
			}
		}
		bodyBuf.appendWithMargin("return code;\r\n"); //$NON-NLS-1$
		bodyBuf.unindent();

	}

	protected void addArrayForLoop(IGenerationBuffer bodyBuf, JavaParameterDescriptor parmDesc) {
		bodyBuf.formatWithMargin("for (i=0; i<%0.length; i++)\n", new String[]{parmDesc.getName()}); //$NON-NLS-1$
		bodyBuf.indent();
		int index = parmDesc.getType().indexOf('[');
		String rootType = parmDesc.getType().substring(0, index);
		addArrayHashCodeAddition(bodyBuf, parmDesc.getName(), rootType, true);
		bodyBuf.unindent();
	}

	protected void addArrayHashCodeAddition(IGenerationBuffer bodyBuf, JavaParameterDescriptor parmDesc) {
		addArrayHashCodeAddition(bodyBuf, parmDesc.getName(), parmDesc.getType(), false);
	}

	protected void addArrayHashCodeAddition(IGenerationBuffer bodyBuf, String name, String type, boolean isRootArrayType) {
		String[] parms = new String[2];
		parms[0] = name;
		parms[1] = EJBGenHelpers.getPrimitiveWrapper(type);
		String template = getTemplateFor(type, isRootArrayType);
		bodyBuf.appendWithMargin("code += "); //$NON-NLS-1$
		bodyBuf.format(template, parms);
		bodyBuf.append(";\r\n"); //$NON-NLS-1$
	}


	protected boolean hasArrayType(JavaParameterDescriptor[] parmDescs) {
		if (parmDescs != null && parmDescs.length > 0) {
			for (int i = 0; i < parmDescs.length; i++) {
				if (EJBGenHelpers.isArray(parmDescs[i].getType()))
					return true;
			}
		}
		return false;
	}

	protected void getBodyWithSimpleTypes(IGenerationBuffer bodyBuf, JavaParameterDescriptor[] parmDescs) {
		bodyBuf.indent();
		bodyBuf.appendWithMargin("return (");//$NON-NLS-1$

		if ((parmDescs != null) && (parmDescs.length > 0)) {
			String[] parmName = new String[2];
			String template = null;
			bodyBuf.indent();
			for (int i = 0; i < parmDescs.length; i++) {
				parmName[0] = parmDescs[i].getName();
				parmName[1] = EJBGenHelpers.getPrimitiveWrapper(parmDescs[i].getType());
				template = getTemplateFor(parmDescs[i].getType());
				if (i > 0)
					bodyBuf.appendWithMargin("+ ");//$NON-NLS-1$
				bodyBuf.format(template, parmName);
				if (i < (parmDescs.length - 1))
					bodyBuf.append(IEJBGenConstants.LINE_SEPARATOR);
			}
			bodyBuf.unindent();
		} else {
			bodyBuf.append("super.hashCode()");//$NON-NLS-1$
		}

		bodyBuf.append(");\n");//$NON-NLS-1$
		bodyBuf.unindent();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Returns the hash code for the key.\n";//$NON-NLS-1$
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "hashCode";//$NON-NLS-1$
	}

	/**
	 * getReturnType method comment.
	 */
	protected String getReturnType() throws GenerationException {
		return "int";//$NON-NLS-1$
	}

	protected String getTemplateFor(String type) {
		return getTemplateFor(type, false);
	}

	/**
	 * Returns the primitive or object template.
	 */
	protected String getTemplateFor(String type, boolean isRootArrayType) {
		if (EJBGenHelpers.isPrimitive(type)) {
			if (isRootArrayType)
				return "(new %1(%0[i]).hashCode())";//$NON-NLS-1$
			return "(new %1(%0).hashCode())";//$NON-NLS-1$
		} else if (isRootArrayType)
			return "%0[i].hashCode()";//$NON-NLS-1$
		else
			return "%0.hashCode()";//$NON-NLS-1$
	}
}