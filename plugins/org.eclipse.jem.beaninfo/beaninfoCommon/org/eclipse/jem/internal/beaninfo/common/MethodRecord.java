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
 *  $RCSfile: MethodRecord.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:44:52 $ 
 */
package org.eclipse.jem.internal.beaninfo.common;
 

/**
 * This is the data structure for sending the MethodDescriptor info from
 * the BeanInfo vm to the IDE vm. It is serializable so that it can
 * be serialized for transmission.
 * <p>
 * It contains the properties of the MethodDescriptor. 
 * @since 1.1.0
 */
public class MethodRecord extends FeatureRecord {
	
	private static final long serialVersionUID = 1105982213110L;
	
	/**
	 * Method signature for the method this record describes.
	 */
	public ReflectMethodRecord methodForDescriptor;
	/**
	 * Parameter records array. It may be <code>null</code> if there aren't any.
	 */
	public ParameterRecord[] parameters;
}
