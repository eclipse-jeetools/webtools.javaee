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
 *  $RCSfile: JavaObjectInstance.java,v $
 *  $Revision: 1.18 $  $Date: 2005/08/23 21:13:01 $ 
 */
package org.eclipse.jem.internal.instantiation.base;
 

/**
 * Java Object Instance implementation.
 * @since 1.1.0.1
 */
public class JavaObjectInstance extends JavaInstance implements IJavaObjectInstance {

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.instantiation.base.IJavaInstance#isPrimitive()
	 */
	public boolean isPrimitive() {
		return false;
	}

}
