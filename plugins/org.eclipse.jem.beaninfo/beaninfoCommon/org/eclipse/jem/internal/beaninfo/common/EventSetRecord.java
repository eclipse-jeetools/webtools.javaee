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
