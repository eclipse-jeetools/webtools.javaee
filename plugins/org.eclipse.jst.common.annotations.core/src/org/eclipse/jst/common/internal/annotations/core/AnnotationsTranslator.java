/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.annotations.core;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.internal.emf.resource.Translator;



/**
 * @author mdelder
 *  
 */
public class AnnotationsTranslator extends Translator {

	private AnnotatedCommentHandler handler;

	private AnnotationTagParser parser;

	public static final AnnotationsTranslator INSTANCE = new AnnotationsTranslator();

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public AnnotationsTranslator() {
		super("#comment", AnnotationsAdapter.NOTIFICATION_FEATURE, Translator.COMMENT_FEATURE); //$NON-NLS-1$
	}

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public AnnotationsTranslator(String domNameAndPath) {
		super(domNameAndPath, AnnotationsAdapter.NOTIFICATION_FEATURE, Translator.COMMENT_FEATURE);
	}

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 * @param style
	 */
	public AnnotationsTranslator(String domNameAndPath, int style) {
		super(domNameAndPath, AnnotationsAdapter.NOTIFICATION_FEATURE, style | Translator.COMMENT_FEATURE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.emf.xml.Translator#setMOFValue(org.eclipse.emf.ecore.EObject,
	 *      java.lang.Object)
	 */
	@Override
	public void setMOFValue(EObject emfObject, Object value) {
		if (value == null)
			return;
		getHandler().getAnnotations().clear();
		getParser().setParserInput(value.toString());
		getParser().parse();
		String name;
		Map annotations = getHandler().getAnnotations();
		for (Iterator keys = annotations.keySet().iterator(); keys.hasNext();) {
			name = (String) keys.next();
			AnnotationsAdapter.addAnnotations(emfObject, name, annotations.get(name));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf2xml.impl.Translator#isSetMOFValue(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean isSetMOFValue(EObject emfObject) {
		return getMOFValue(emfObject) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf2xml.impl.Translator#getMOFValue(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public Object getMOFValue(EObject emfObject) {
		return AnnotationsAdapter.getAnnotations(emfObject, AnnotationsAdapter.GENERATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf2xml.impl.Translator#unSetMOFValue(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void unSetMOFValue(EObject emfObject) {
		AnnotationsAdapter.removeAnnotations(emfObject, AnnotationsAdapter.GENERATED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf2xml.impl.Translator#featureExists(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean featureExists(EObject emfObject) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf2xml.impl.Translator#isDataType()
	 */
	@Override
	public boolean isDataType() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.emf2xml.impl.Translator#isMapFor(java.lang.Object, java.lang.Object,
	 *      java.lang.Object)
	 */
	@Override
	public boolean isMapFor(Object aFeature, Object oldValue, Object newValue) {
		return (aFeature == feature);
	}

	/**
	 * @return Returns the handler.
	 */
	protected AnnotatedCommentHandler getHandler() {
		if (handler == null)
			handler = new AnnotatedCommentHandler();
		return handler;
	}

	/**
	 * @return Returns the parser.
	 */
	protected AnnotationTagParser getParser() {
		if (parser == null)
			parser = new AnnotationTagParser(getHandler());
		return parser;
	}
}