package org.eclipse.jem.internal.adapters.jdom;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaFieldJDOMAdaptor.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2003/12/16 19:29:09 $ 
 */
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jem.internal.java.*;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;
/**
 * Insert the type's description here.
 * Creation date: (6/6/2000 4:42:50 PM)
 * @author: Administrator
 */
public class JavaFieldJDOMAdaptor extends JDOMAdaptor {
	private static final String BEGIN_COMMENT = "/*"; //$NON-NLS-1$
	private static final String END_COMMENT = "*/"; //$NON-NLS-1$
	protected IField sourceField = null;
	protected IType parentType = null;
	public JavaFieldJDOMAdaptor(Notifier target, IJavaProject workingProject) {
		super(target, workingProject);
	}
	protected void clearSource() {
		sourceField = null;
	}
	/**
	 * Return a String for the source starting after the field's name to the end of
	 * the source range.  This will be the source after the name which could include comments.
	 */
	protected String getFieldInitializerSource() {
		IOpenable openable = ((JavaElement) getSourceField()).getOpenableParent();
		try {
			ISourceRange nameRange, sourceRange;
			int start = -1, length = 0;
			IBuffer buffer = openable.getBuffer();
			if (buffer == null) {
				return ""; //$NON-NLS-1$
			}
			nameRange = getSourceField().getNameRange();
			start = nameRange.getOffset() + nameRange.getLength();
			if (start != -1) {
				sourceRange = getSourceField().getSourceRange();
				if (sourceRange.getOffset() != -1)
					length = sourceRange.getOffset() + sourceRange.getLength() - start;
				return buffer.getText(start, length);
			}
			return null;
		} catch (JavaModelException e) {
			return ""; //$NON-NLS-1$
		}
	}
	/**
	 * Return the field source string without comments
	 */
	protected String getFieldInitializerSourceWithoutComments() {
		String s = getFieldInitializerSource();
		int start = 0;
		int startComment = -1;
		int endComment = -1;
		while (start < s.length()) {
			startComment = s.indexOf(BEGIN_COMMENT, start);
			if (startComment > 0) {
				String newString;
				endComment = s.indexOf(END_COMMENT, start);
				newString = s.substring(start, startComment);
				s = newString + s.substring(endComment + END_COMMENT.length(), s.length());
				start = 0;
				startComment = -1;
				endComment = -1;
			} else {
				start = s.length();
			}
		}
		return s;
	}
	/**
	 * getFieldInitializerString - parse the source for our source field 
	 * 	and return the initialization string.
	 *  Return null if no initialization string or constant value is present.
	 * i.e. - public String foo = "foo default"; should return "foo default" (including quotes)
	 */
	protected String getFieldInitializerString() {
		String result = null;
		try {
			if (!getParentType().isBinary()) {
				String source = getFieldInitializerSourceWithoutComments();
				if (source != null && source.length() != 0) {
					int equalsPos = source.indexOf('=');//$NON-NLS-1$
					int endPos = source.indexOf(',');//$NON-NLS-1$
					if (endPos == -1)
						endPos = source.length() - 1;
					if (equalsPos != -1) {
						// Copy from after "=" to before ";" or ","
						result = source.substring(equalsPos + 1, endPos);
						result = result.trim();
					}
				}
			} else {
				// Binary type, see if we can use the constant
				// it's not clear from the API's, but this is probably only
				// available for statics.
				Object constantValue = getSourceField().getConstant();
				// Need to convert the constant to a String
				if (constantValue != null) {
					result = constantValue.toString();
					if (constantValue instanceof String) {
						result = "\"" + result + "\""; //$NON-NLS-2$//$NON-NLS-1$
					}
				}
			}
		} catch (JavaModelException e) {
			// punt
		}
		return result;
	}
	/**
	 * getParentType - return the IType which corresponds to our parent JavaClass
	 * we're going to do this a lot, so cache it.
	 */
	protected IType getParentType() {
		if (parentType == null) {
			Field targetField = (Field) getTarget();
			JavaClass parentJavaClass = (JavaClass) targetField.getJavaClass();
			if (parentJavaClass != null) {
				JavaClassJDOMAdaptor pa = (JavaClassJDOMAdaptor) EcoreUtil.getAdapter(parentJavaClass.eAdapters(), ReadAdaptor.TYPE_KEY);
				if (pa != null)
					parentType = pa.getSourceType();
			}
		}
		return parentType;
	}
	public Object getReflectionSource() {
		return getSourceField();
	}
	/**
	 * getSourceField - return the IField which describes our implementing field
	 */
	protected IField getSourceField() {
		if (sourceField == null) {
			IType parent = this.getParentType();
			if (parent != null)
				sourceField = parent.getField(((Field) getTarget()).getName());
		}
		return sourceField;
	}
	public Field getTargetField() {
		return (Field) getTarget();
	}
	protected IType getType() {
		return getParentType();
	}
	protected Map getTypeResolutionCache() {
		Field field = getTargetField();
		if (field != null) {
			JavaClass javaClass = field.getJavaClass();
			if (javaClass != null) {
				JDOMAdaptor classAdaptor = (JDOMAdaptor) retrieveAdaptorFrom(javaClass);
				if (classAdaptor != null)
					return classAdaptor.getTypeResolutionCache();
			}
		}
		return null;
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
		if (getSourceProject() != null && getSourceField() != null) {
			setModifiers();
			//	setNaming();
			setType();
			return true;
		}
		return false;
	}
	/**
	 * setModifiers - set the attribute values related to modifiers here
	 */
	protected void setModifiers() {
		Field javaFieldTarget = (Field) getTarget();
		try {
			String initializer = getFieldInitializerString();
			if (initializer != null)
				javaFieldTarget.setInitializer(createBlock(javaFieldTarget.getName(), initializer));
			int flags = getSourceField().getFlags();
			javaFieldTarget.setFinal(Flags.isFinal(flags));
			javaFieldTarget.setStatic(Flags.isStatic(flags));
			javaFieldTarget.setTransient(Flags.isTransient(flags));
			javaFieldTarget.setVolatile(Flags.isVolatile(flags));
			// Set visibility
			if (Flags.isPublic(flags))
				javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PUBLIC_LITERAL);
			else if (Flags.isPrivate(flags))
				javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PRIVATE_LITERAL);
			else if (Flags.isProtected(flags))
				javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PROTECTED_LITERAL);
			else
				javaFieldTarget.setJavaVisibility(JavaVisibilityKind.PACKAGE_LITERAL);
		} catch (JavaModelException npe) {
			System.out.println(ResourceHandler.getString("Error_Introspecting_Flags_ERROR_", new Object[] {((XMIResource) javaFieldTarget.eResource()).getID(javaFieldTarget), npe.getMessage()})); //$NON-NLS-1$ = "error introspecting flags on {0}"
		}
	}
	/**
	 * setNaming - set the naming values here
	 * 	- qualified name must be set first, that is the path to the real Java class
	 *	- ID
	 * 	- null UUID
	 */
	protected void setNaming() {
		Field javaFieldTarget = (Field) getTarget();
		JavaClass parent = javaFieldTarget.getContainingJavaClass();
		((XMIResource) javaFieldTarget.eResource()).setID(javaFieldTarget, parent.getName() + "_" + javaFieldTarget.getName()); //$NON-NLS-1$
	}
	/**
	 * setType - set our type here
	 */
	protected void setType() {
		String typeName = null;
		try {
			typeName = typeNameFromSignature(getSourceField().getTypeSignature());
		} catch (JavaModelException npe) {
			// name stays null and we carry on
		}
		setFieldType(getTargetField(), typeName);
	}
}
