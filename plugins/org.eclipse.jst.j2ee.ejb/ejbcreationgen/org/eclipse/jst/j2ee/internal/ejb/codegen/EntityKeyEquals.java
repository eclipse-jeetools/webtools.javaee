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
public class EntityKeyEquals extends JavaMethodGenerator {
	/**
	 * EntityKeyEquals constructor comment.
	 */
	public EntityKeyEquals() {
		super();
	}

	/**
	 * Check for same type and equality of fields.
	 */
	protected void getBody(IGenerationBuffer bodyBuf) throws GenerationException {
		Entity entity = (Entity) getSourceElement();
		EntityHelper topHelper = (EntityHelper) getTopLevelHelper();
		JavaParameterDescriptor[] parmDescs = EJBGenHelpers.getEntityKeyFieldsAsFlatParms(entity, topHelper, getSourceContext().getNavigator());

		bodyBuf.indent();
		String[] stdArgs = new String[]{entity.getPrimaryKeyName()};
		bodyBuf.formatWithMargin("if (otherKey instanceof %0) {\n", stdArgs);//$NON-NLS-1$
		bodyBuf.indent();
		bodyBuf.formatWithMargin("%0 o = (%0)otherKey;\nreturn (", stdArgs);//$NON-NLS-1$

		if ((parmDescs != null) && (parmDescs.length > 0)) {
			String[] parmName = new String[1];
			String template = null;
			bodyBuf.indent();
			for (int i = 0; i < parmDescs.length; i++) {
				parmName[0] = parmDescs[i].getName();
				template = getTemplateFor(parmDescs[i].getType());
				if (i > 0)
					bodyBuf.appendWithMargin("&& ");//$NON-NLS-1$
				bodyBuf.format(template, parmName);
				if (i < (parmDescs.length - 1))
					bodyBuf.append(IEJBGenConstants.LINE_SEPARATOR);
			}
			bodyBuf.unindent();
		} else {
			bodyBuf.append("super.equals(otherKey)");//$NON-NLS-1$
		}

		bodyBuf.append(");\n");//$NON-NLS-1$
		bodyBuf.unindent();
		bodyBuf.appendWithMargin("}\nreturn false;\n");//$NON-NLS-1$
		bodyBuf.unindent();
	}

	/**
	 * Returns the JavaDoc comment for the member.
	 */
	protected String getComment() throws GenerationException {
		return "Returns true if both keys are equal.\n";//$NON-NLS-1$
	}

	/**
	 * getName method comment.
	 */
	protected String getName() throws GenerationException {
		return "equals";//$NON-NLS-1$
	}

	/**
	 * The other key is the parameter.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		JavaParameterDescriptor[] parms = new JavaParameterDescriptor[1];
		parms[0] = new JavaParameterDescriptor();
		parms[0].setName("otherKey");//$NON-NLS-1$
		parms[0].setType(IEJBGenConstants.OBJECT_CLASS_NAME);
		return parms;
	}

	/**
	 * getReturnType method comment.
	 */
	protected String getReturnType() throws GenerationException {
		return "boolean";//$NON-NLS-1$
	}

	/**
	 * Returns the primitive or object template.
	 */
	protected String getTemplateFor(String type) {
		if (EJBGenHelpers.isPrimitive(type))
			return "(this.%0 == o.%0)";//$NON-NLS-1$
		else if (EJBGenHelpers.isArray(type))
			return "(java.util.Arrays.equals(this.%0, o.%0))";//$NON-NLS-1$
		else
			return "(this.%0.equals(o.%0))";//$NON-NLS-1$
	}
}