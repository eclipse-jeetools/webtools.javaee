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
/*


 */
package org.eclipse.jem.internal.instantiation.base;


/**
 * This is the default instance of a Java Model Datatype (i.e. primitive).
 * It can be created from a string, which becomes the initialization string
 * for the instance. It's toString will be the initialization string.
 *
 * It should not be referenced directly, the IJavaDataTypeInstance interface should be
 * used instead. It is public so that it can be subclassed. 
 */
public class JavaDataTypeInstance extends JavaInstance implements IJavaDataTypeInstance {

	protected JavaDataTypeInstance() {
	}
		
	public boolean isPrimitive(){
		return true;
	}
}
