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
package org.eclipse.jst.j2ee.internal.ejb.codegen.helpers;



import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;


/**
 * Insert the type's description here. Creation date: (11/16/00 6:37:32 PM)
 * 
 * @author: Administrator
 */
public class AttributeHistory extends FeatureHistory {
	/**
	 * AttributeHistory constructor comment.
	 */
	public AttributeHistory() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:30:23 PM)
	 * 
	 * @param anObject
	 *            CMPAttribute
	 */
	public void storeHistory(CMPAttribute anObject) {
		JavaHelpers jh = anObject.getType();
		if (jh != null)
			setTypeName(jh.getQualifiedName());
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:30:23 PM)
	 * 
	 * @param anObject
	 *            EObject
	 */
	public void storeHistory(EObject anObject) {
		super.storeHistory(anObject);
		storeHistory((CMPAttribute) anObject);
	}
}