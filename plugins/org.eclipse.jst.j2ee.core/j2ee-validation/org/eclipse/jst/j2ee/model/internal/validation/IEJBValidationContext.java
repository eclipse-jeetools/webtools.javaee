/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;

import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;

public interface IEJBValidationContext extends IValidationContext {
	public static final int ERROR = IMessage.HIGH_SEVERITY;
	public static final int WARNING = IMessage.NORMAL_SEVERITY;
	public static final int INFO = IMessage.LOW_SEVERITY;
	
	
	public IValidator getValidator();
	public IValidationContext getHelper();
	public IReporter getReporter();
	
	public IMessage getMessage(); // returns an empty IMessage which can be reused
	public Logger getMsgLogger();
	
	public void terminateIfCancelled() throws ValidationCancelledException;
	
	public LogEntry getLogEntry();
	
	public Object loadModel(String symbolicName);
	public Object loadModel(String symbolicName, Object[] parms);
	
	public void removeAllMessages();
	public void removeMessages(Object target);
	public void removeMessages(Object target, String groupIdentifier);
	
	public void addMessage(IMessage message);
	public void addMessage(int severity, String messageId);
	public void addMessage(int severity, String messageId, String[] parms);
	public void addMessage(int severity, String messageId, Object target);
	public void addMessage(int severity, String messageId, String[] parms, Object target);
	public void addMessage(int severity, String messageId, Object target, String groupName);
	public void addMessage(int severity, String messageId, String[] parms, Object target, String groupName);
	
	public void subtask(String messageId);
	public void subtask(String messageId, String[] parms);
}
