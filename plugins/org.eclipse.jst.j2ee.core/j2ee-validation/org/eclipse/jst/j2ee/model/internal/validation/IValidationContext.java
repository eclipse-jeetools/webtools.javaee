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

import org.eclipse.wst.validation.internal.core.IHelper;
import org.eclipse.wst.validation.internal.core.IMessage;
import org.eclipse.wst.validation.internal.core.IReporter;
import org.eclipse.wst.validation.internal.core.IValidator;
import org.eclipse.wst.validation.internal.core.MessageLimitException;
import org.eclipse.wst.validation.internal.core.SeverityEnum;

import com.ibm.wtp.common.logger.LogEntry;
import com.ibm.wtp.common.logger.proxy.Logger;

public interface IValidationContext {
	public static final int ERROR = SeverityEnum.HIGH_SEVERITY;
	public static final int WARNING = SeverityEnum.NORMAL_SEVERITY;
	public static final int INFO = SeverityEnum.LOW_SEVERITY;
	
	
	public IValidator getValidator();
	public IHelper getHelper();
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
	
	public void addMessage(IMessage message) throws MessageLimitException;
	public void addMessage(int severity, String messageId) throws MessageLimitException;
	public void addMessage(int severity, String messageId, String[] parms) throws MessageLimitException;
	public void addMessage(int severity, String messageId, Object target) throws MessageLimitException;
	public void addMessage(int severity, String messageId, String[] parms, Object target) throws MessageLimitException;
	public void addMessage(int severity, String messageId, Object target, String groupName) throws MessageLimitException;
	public void addMessage(int severity, String messageId, String[] parms, Object target, String groupName) throws MessageLimitException;
	
	public void subtask(String messageId);
	public void subtask(String messageId, String[] parms);
}
