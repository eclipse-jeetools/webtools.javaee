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
 *  $RCSfile: EventSetRecord.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:44:52 $ 
 */
package org.eclipse.jem.internal.beaninfo.common;
 

/**
 * This is the data structure for sending the EventSetDescriptor info from
 * the BeanInfo vm to the IDE vm. It is serializable so that it can
 * be serialized for transmission.
 * <p>
 * It contains the properties of the EventSetDescriptor. 
 * @since 1.1.0
 */
public class EventSetRecord extends FeatureRecord {
	
	private static final long serialVersionUID = 1105980773420L;
	
	public ReflectMethodRecord addListenerMethod;
	public String eventAdapterClassName;
	public MethodRecord[] listenerMethodDescriptors;
	public String listenerTypeName;
	public ReflectMethodRecord removeListenerMethod;
	public boolean inDefaultEventSet;
	public boolean unicast;
}
