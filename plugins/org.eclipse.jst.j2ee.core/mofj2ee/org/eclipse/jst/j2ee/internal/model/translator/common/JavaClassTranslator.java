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
package org.eclipse.jst.j2ee.internal.model.translator.common;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.wst.common.internal.emf.resource.Translator;
import org.eclipse.wst.common.internal.emf.resource.TranslatorPath;

public class JavaClassTranslator extends Translator {

	/**
	 * Constructor for JavaClassTranslator.
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public JavaClassTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/**
	 * Constructor for JavaClassTranslator.
	 * @param domNameAndPath
	 * @param aFeature
	 * @param path
	 */
	public JavaClassTranslator(String domNameAndPath, EStructuralFeature aFeature, TranslatorPath path) {
		super(domNameAndPath, aFeature, path);
	}

	/**
	 * Constructor for JavaClassTranslator.
	 * @param domNameAndPath
	 * @param aFeature
	 * @param paths
	 */
	public JavaClassTranslator(String domNameAndPath, EStructuralFeature aFeature, TranslatorPath[] paths) {
		super(domNameAndPath, aFeature, paths);
	}

	/**
	 * Constructor for JavaClassTranslator.
	 * @param domNameAndPath
	 * @param aFeature
	 * @param style
	 */
	public JavaClassTranslator(String domNameAndPath, EStructuralFeature aFeature, int style) {
		super(domNameAndPath, aFeature, style);
	}

	public Object convertStringToValue(String nodeName, String readAheadName, String value, Notifier owner) {
		Object result = null;
		if (value != null) {
			result = convertStringToValue(value, (EObject) owner);
		}
		
		return result;
	}

	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(String)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {
		if (strValue != null) {
			if (owner != null) {
				Resource ownerRes = owner.eResource();
				if (ownerRes != null) {
					ResourceSet rs = ownerRes.getResourceSet();
					if (rs != null)
						return JavaRefFactory.eINSTANCE.reflectType(strValue.trim(), rs);
				}
			}
			return JavaRefFactory.eINSTANCE.createClassRef(strValue.trim());
		}
		return null;
	}

	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertValueToString(Object)
	 */
	public String convertValueToString(Object value, EObject owner) {
		if (value != null)
			return ((JavaClass) value).getQualifiedName();
		return null;
	}

}
