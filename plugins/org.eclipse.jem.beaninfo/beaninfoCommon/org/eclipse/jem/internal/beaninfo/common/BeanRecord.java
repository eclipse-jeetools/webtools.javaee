/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanRecord.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:44:52 $ 
 */
package org.eclipse.jem.internal.beaninfo.common;
 

/**
 * This is the data structure for sending the BeanDescriptor info from
 * the BeanInfo vm to the IDE vm. It is serializable so that it can
 * be serialized for transmission.
 * <p>
 * It contains the properties of the BeanDescriptor. 
 * @since 1.1.0
 */
public class BeanRecord extends FeatureRecord {

	private static final long serialVersionUID = 1105979920664L;
	
	public String customizerClassName;
	public boolean mergeInheritedProperties;
	public boolean mergeInheritedOperations;
	public boolean mergeInheritedEvents;
	/**
	 * Names of properties that are to not be inherited in getAllProperties(). It is set only
	 * if the list is not the full list of inherited properties.
	 * If all inherited or mergeInheritedProperties is false, then the field will be <code>null</code>. Save space that way.
	 */
	public String[] notInheritedPropertyNames;
	/**
	 * Names of operations that are to not be inherited in getEAllOperations(). It is set only
	 * if the list is not the full list of inherited operations. 
	 * If all are inherited or if mergeInheritedOperations is false, then the field will be <code>null</code>. Save space that way.
	 */
	public String[] notInheritedOperationNames;
	/**
	 * Names of events that are to not be inherited in getAllEvents(). It is set only
	 * if the list is not the full list of inherited events.
	 * If all are inherited or if mergeInheritedEvents is false, then the field will be <code>null</code>. Save space that way.
	 */
	public String[] notInheritedEventNames;

}
