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
package org.eclipse.jst.j2ee.internal.java.codegen;


/**
 * Information about a Java interface that is to be generated is gathered into a interface
 * descriptor for ease of handling.
 */
public class JavaInterfaceDescriptor extends JavaTypeDescriptor {
	/**
	 * JavaInterfaceDescriptor default constructor.
	 */
	public JavaInterfaceDescriptor() {
		super();
	}

	/**
	 * Returns true if this is a interface descriptor.
	 */
	public boolean isInterface() {
		return true;
	}
}