/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.beaninfo.common;

import java.io.Serializable;
 

/**
 * This is the data structure for sending the FeatureDescriptor info from
 * the BeanInfo vm to the IDE vm. It is serializable so that it can
 * be serialized for transmission.
 * <p>
 * It contains the properties of the FeatureDescriptor. 
 * @since 1.1.0
 */
public class FeatureRecord implements Serializable {

	private static final long serialVersionUID = 1105979276648L;
	
	public String name;	// Some decorators use this and others don't. Each decorator type will decide whether this is of importance.
	public String displayName;
	public String shortDescription;
	public String category;
	public boolean expert;
	public boolean hidden;
	public boolean preferred;
	public String[] attributeNames;
	public FeatureAttributeValue[] attributeValues;
}
