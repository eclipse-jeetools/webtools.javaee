package org.eclipse.jem.internal.core;
/*******************************************************************************
 * Copyright (c) 2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ConsoleLogger.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
 
/**
 * Console Logger.
 * NOTE: This has to be able to run outside of Eclipse. So there must be no imports to anything within Eclipse.
 */
public class ConsoleLogger extends StringPrimitiveLogger {

	protected boolean echoToConsole;	// Have a local so we know it here.
	protected int currentLogLevel;
		
	public ConsoleLogger(boolean echoToConsole, int currentLogLevel) {
		super(false);	// Since we will be logging to console, we will control this such that dups aren't created.
		this.echoToConsole = echoToConsole;
		this.currentLogLevel = currentLogLevel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.core.MsgLogger#primLogObject(java.lang.Object, int)
	 */
	protected void primLog(Object object, int logLevel) {
		if (echoToConsole || doLog(logLevel))
			if (logLevel == LOG_SEVERE || logLevel == LOG_WARNING)
				System.err.println(object);
			else
				System.out.println(object);	// Go to out if not severe/warning (could be simply echo console).
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.core.MsgLogger#primLogObject(java.lang.Throwable, int)
	 */
	protected void primLog(Throwable exc, int logLevel) {
		if (echoToConsole || doLog(logLevel))
			if (logLevel == LOG_SEVERE || logLevel == LOG_WARNING)
				exc.printStackTrace(System.err);
			else
				exc.printStackTrace(System.out);	// Go to out if not severe/warning (could be simply echo console).
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.core.MsgLogger#doLog(int)
	 */
	protected boolean doLog(int logLevel) {
		return logLevel >= currentLogLevel && logLevel < LOG_NONE;
	}

}
