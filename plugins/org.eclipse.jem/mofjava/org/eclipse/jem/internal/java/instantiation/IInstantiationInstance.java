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
package org.eclipse.jem.internal.java.instantiation;
/*


 */
import org.eclipse.jem.java.JavaHelpers;

/**
 * The interface for an instance. The actual implementation
 * is in a separate project so that instantiation code will not be loaded
 * unless it needs to be.
 */
public interface IInstantiationInstance {
	/**
	 * @return The JavaHelpers for the java type of the instance.
	 */
	public JavaHelpers getJavaType();

}
