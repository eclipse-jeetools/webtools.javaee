/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.core;
/*


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
