package org.eclipse.jem.internal.java.instantiation;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IInstantiationInstance.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
import org.eclipse.jem.internal.java.JavaHelpers;

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
