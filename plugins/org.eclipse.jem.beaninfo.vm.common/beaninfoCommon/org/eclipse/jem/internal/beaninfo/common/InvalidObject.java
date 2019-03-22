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

import java.io.ObjectStreamException;
import java.io.Serializable;
 

/**
 * An indicator object for invalid object type. This is used with feature attribute
 * values from the BeanInfo classes. We can only handle certain types when we
 * bring them over from the BeanInfo VM. That is because the classes instantiated
 * in the BeanInfo class may not be available in the IDE.  So any invalid value
 * will be replaced by this class instance.
 * <p>
 * This is a singleton class.
 * There will be one instance (InvalidObject.INSTANCE) in the system. That way
 * "==" can be used to test for it.
 * 
 * @since 1.1.0
 */
public class InvalidObject implements Serializable {
	
	/**
	 * Singleton instance of InvalidObject.
	 * @since 1.1.0
	 */
	public static final InvalidObject INSTANCE = new InvalidObject();
	
	private static final long serialVersionUID = 1105643804370L;
	
	/*
	 * Nobody else should create one of these.
	 */
	private InvalidObject() {
	}

	private Object readResolve() throws ObjectStreamException {
		return INSTANCE;
	}
}
