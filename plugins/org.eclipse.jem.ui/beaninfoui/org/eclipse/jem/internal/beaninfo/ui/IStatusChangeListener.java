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
 *  $RCSfile: IStatusChangeListener.java,v $
 *  $Revision: 1.1 $  $Date: 2005/09/26 20:26:59 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import org.eclipse.core.runtime.IStatus;
 
/**
 * Status change listener
 * 
 * @since 1.2.0
 */
public interface IStatusChangeListener {
	
	/**
	 * Notifies this listener that the given status has changed.
	 * 
	 * @param	status	the new status
	 */
	void statusChanged(IStatus status);
}
