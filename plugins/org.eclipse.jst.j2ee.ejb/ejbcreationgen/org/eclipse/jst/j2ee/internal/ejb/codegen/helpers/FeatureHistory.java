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



/**
 * Insert the type's description here. Creation date: (11/14/2000 12:25:00 PM)
 * 
 * @author: Administrator
 */
public abstract class FeatureHistory extends MetadataHistory {
	private java.lang.String typeName;

	//protected EStructuralFeature multiplicity;

	/**
	 * FeatureHistory constructor comment.
	 */
	public FeatureHistory() {
		super();
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:25:16 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTypeName() {
		return typeName;
	}

	/**
	 * Insert the method's description here. Creation date: (11/14/2000 12:25:16 PM)
	 * 
	 * @param newTypeName
	 *            java.lang.String
	 */
	public void setTypeName(java.lang.String newTypeName) {
		typeName = newTypeName;
	}
	/**
	 * Returns the multiplicity.
	 * 
	 * @return EStructuralFeature
	 */
	/*
	 * public EStructuralFeature getMultiplicity() { return multiplicity; }
	 */

	/**
	 * Sets the multiplicity.
	 * 
	 * @param multiplicity
	 *            The multiplicity to set
	 */
	/*
	 * public void setMultiplicity(EStructuralFeature multiplicity) { this.multiplicity =
	 * multiplicity; }
	 */

}