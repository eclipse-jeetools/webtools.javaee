package org.eclipse.jem.internal.core;
/*******************************************************************************
 * Copyright (c) 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: StringPrimitiveLogger.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
/**
 * A simple intermediate logger such that the primitives are logged as String valueOfs.
 * If logger can handle primitives directly, then don't subclass this class.
 * 
 * NOTE: Since this can be used both inside and outside of Eclipse, it is important that
 * this never have any Eclipse imports.
 */
public abstract class StringPrimitiveLogger extends MsgLogger {

	protected StringPrimitiveLogger(boolean logEchoConsole) {
		super(logEchoConsole);
	}
	protected void primLog(boolean msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(byte msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(char msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(double msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(float msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(int msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(long msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}

	protected void primLog(short msg, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_FINEST;
		primLog(String.valueOf(msg), logLevel);
	}
}
