package org.eclipse.jem.internal.beaninfo.adapters;
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
 *  $RCSfile: BeaninfoRegistration.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */

import org.eclipse.core.runtime.IConfigurationElement;

/**
 * This is the registration information for registered beaninfos in the BeaninfoPlugin.
 * @version 	1.0
 * @author
 */
public class BeaninfoRegistration {
	
	protected BeaninfosDoc doc;
	protected IConfigurationElement variableElement;	// This is used to get contributor element. If null, then no contributor.
	
	public BeaninfoRegistration() {
	}
	
	/**
	 * Construct with a BeaninfosDoc
	 */
	public BeaninfoRegistration(BeaninfosDoc doc) {
		this.doc = doc;
	}
	
	/**
	 * Construct with a BeaninfosDoc and variable element.
	 */
	public BeaninfoRegistration(BeaninfosDoc doc, IConfigurationElement variableElement) {
		this.doc = doc;
		this.variableElement = variableElement;
	}	

	/**
	 * Gets the doc.
	 * @return Returns a BeaninfosDoc
	 */
	public BeaninfosDoc getDoc() {
		return doc;
	}

	/**
	 * Sets the doc.
	 * @param doc The doc to set
	 */
	public void setDoc(BeaninfosDoc doc) {
		this.doc = doc;
	}

	/**
	 * Gets the variableElement.
	 * @return Returns a IConfigurationElement
	 */
	public IConfigurationElement getVariableElement() {
		return variableElement;
	}

	/**
	 * Sets the variableElement.
	 * @param variableElement The variableElement to set
	 */
	public void setVariableElement(IConfigurationElement variableElement) {
		this.variableElement = variableElement;
	}

}
