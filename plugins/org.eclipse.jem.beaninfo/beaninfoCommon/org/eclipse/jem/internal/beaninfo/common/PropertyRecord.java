/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PropertyRecord.java,v $
 *  $Revision: 1.1 $  $Date: 2005/02/04 23:11:53 $ 
 */
package org.eclipse.jem.internal.beaninfo.common;
 

/**
 * This is the data structure for sending the PropertyDescriptor info from
 * the BeanInfo vm to the IDE vm. It is serializable so that it can
 * be serialized for transmission.
 * <p>
 * It contains the properties of the PropertyDescriptor. 
 * @since 1.1.0
 */
public class PropertyRecord extends FeatureRecord {
	private static final long serialVersionUID = 1105979276648L;
	
	public String propertyEditorClassName;
	public String propertyTypeName;
	public ReflectMethodRecord readMethod;
	public ReflectMethodRecord writeMethod;
	public boolean bound;
	public boolean constrained;
	public Boolean designTime;
}
