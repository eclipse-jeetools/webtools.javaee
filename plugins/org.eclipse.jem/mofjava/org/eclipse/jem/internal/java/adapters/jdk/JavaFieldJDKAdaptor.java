package org.eclipse.jem.internal.java.adapters.jdk;
/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaFieldJDKAdaptor.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.internal.java.*;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
/**
 * Insert the type's description here.
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public class JavaFieldJDKAdaptor extends JDKAdaptor {
	protected java.lang.reflect.Field sourceField = null;
	protected Class parentType = null;
	public JavaFieldJDKAdaptor(Notifier target, JavaJDKAdapterFactory anAdapterFactory) {
		super(target, anAdapterFactory);
	}
	/**
	 * getParentType - return the Class which corresponds to our parent JavaClass
	 * we're going to do this a lot, so cache it.
	 */
	protected Class getParentType() {
		if (parentType == null) {
			Field targetField = (Field) getTarget();
			JavaClass parentJavaClass = (JavaClass) targetField.getJavaClass();
			JavaClassJDKAdaptor pa = (JavaClassJDKAdaptor) EcoreUtil.getAdapter(parentJavaClass.eAdapters(), ReadAdaptor.TYPE_KEY);
			if (pa != null)
				parentType = pa.getSourceType();
		}
		return parentType;
	}
	public Object getReflectionSource() {
		return getSourceField();
	}
	/**
	 * getSourceField - return the java.lang.reflect.Field which describes our implementing field
	 */
	protected java.lang.reflect.Field getSourceField() {
		if (sourceField == null) {
			Class parent = this.getParentType();
			if (parent != null) {
				try {
					sourceField = parent.getDeclaredField(((Field) getTarget()).getName());
				} catch (NoSuchFieldException e) {
					// OK, carry on
				}
			}
		}
		return sourceField;
	}
	public Field getTargetField() {
		return (Field) getTarget();
	}
	/**
	 * getValueIn method comment.
	 */
	public Object getValueIn(EObject object, EObject attribute) {
		// At this point, this adapter does not dynamically compute any values,
		// all values are pushed back into the target on the initial call.
		return super.getValueIn(object, attribute);
	}
	/**
	 * reflectValues - template method, subclasses override to pump values into target.
	 * on entry: name, containing package (and qualified name), and document must be set.
	 * JavaClass adaptor:
	 *	- set modifiers
	 *	- set name
	 * 	- set type
	 */
	public boolean reflectValues() {
		if (getSourceField() != null) {
			setModifiers();
			setType();
			setInitializerIfPossible();
			return true;
		}
		return false;
	}
	/**
	 * Used for the very narrow case of final static Strings
	 */
	protected void setInitializerIfPossible() {
		if (!(getTargetField().isStatic() && getTargetField().isFinal() && "java.lang.String".equals(((JavaHelpers) getTargetField().getEType()).getQualifiedName()))) //$NON-NLS-1$
			return;

		String value = null;
		try {
			value = (String) getSourceField().get(null);
		} catch (IllegalAccessException ex) {
			return;
		}

		if (value == null)
			return;
		else
			value = "\"".concat(value).concat("\""); //$NON-NLS-2$//$NON-NLS-1$

		Block aBlock = createBlock(getTargetField().getName(), value);
		getTargetField().setInitializer(aBlock);
	}
	/**
	 * setModifiers - set the attribute values related to modifiers here
	 */
	protected void setModifiers() {
		Field javaFieldTarget = (Field) getTarget();
		// For JDK reflection, I don't think we can set the initializer
		int modifiers = getSourceField().getModifiers();
		javaFieldTarget.setStatic(java.lang.reflect.Modifier.isStatic(modifiers));
		javaFieldTarget.setTransient(java.lang.reflect.Modifier.isTransient(modifiers));
		javaFieldTarget.setVolatile(java.lang.reflect.Modifier.isVolatile(modifiers));
		javaFieldTarget.setFinal(java.lang.reflect.Modifier.isFinal(modifiers));
		// Set visibility
		if (java.lang.reflect.Modifier.isPublic(modifiers))
			javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PUBLIC_LITERAL);
		else if (java.lang.reflect.Modifier.isPrivate(modifiers))
			javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PRIVATE_LITERAL);
		else if (java.lang.reflect.Modifier.isProtected(modifiers))
			javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PROTECTED_LITERAL);
		else
			javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PACKAGE_LITERAL);
	}
	/**
	 * setType - set our type here
	 */
	protected void setType() {
		String typeName = getTypeName(getSourceField().getType());
		setFieldType(getTargetField(), typeName);
	}
}
