/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;


/**
 * @version 	1.0
 * @author
 */
public interface IRemoteType {
	public int IS_REMOTE = IEJBType.REMOTE;
	public int MESSAGE_REMOTE_EXCEPTION_SEVERITY = IValidationContext.ERROR; // If a method does not throw an exception when it should, RMIC blows up, hence this situation is an error.
}
