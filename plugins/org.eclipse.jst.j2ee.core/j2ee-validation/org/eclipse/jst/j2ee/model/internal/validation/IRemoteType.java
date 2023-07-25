/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	public int MESSAGE_REMOTE_EXCEPTION_SEVERITY = IEJBValidationContext.ERROR; // If a method does not throw an exception when it should, RMIC blows up, hence this situation is an error.
}
