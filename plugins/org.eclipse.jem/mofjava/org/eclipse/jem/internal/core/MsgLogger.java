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
 *  $RCSfile: MsgLogger.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
/**
 * MsgLogger abstract class. There will be subclasses to implement according to inside Eclipse or not.
 * 
 * NOTE: Very important, this class must not access anything in the workbench. Therefor no imports to workbench
 * things can be here.
 * 
 * When running outside of Eclipse, the echo to console and logging level come from the system properties
 * "logEchoConsole" (="true") and "logLevel" (="n" where "n" is an integer). The defaults are "false" and "5" for LOG_WARNING.
 * 
 * @version 	1.0
 * @author
 */
public abstract class MsgLogger {

	public static final int LOG_DEFAULT = -1;
	public static final int LOG_NONE = 7;
	public static final int LOG_SEVERE = 6;
	public static final int LOG_WARNING = 5;
	public static final int LOG_INFO = 4;
	public static final int LOG_CONFIG = 3;
	public static final int LOG_FINE = 2;
	public static final int LOG_FINER = 1;
	public static final int LOG_FINEST = 0;
	public static final int MIN_LOG = LOG_FINEST;
	
	private static MsgLogger DEFAULT_LOGGER;	// This is the default logger used either when not running in a plugin environment, or if no plugin given (for those that need to run in both).

	private boolean logEchoConsole = false;
	
	/**
	 * Used by those classes that need to run either inside or outside of Eclipse. When inside Eclipse it will use the
	 * appropriate Eclipse logger (e.g. EclipseLogMsgLogger for .log file, or if Hyades is available (and logger is available) use that one.
	 *
	 * @return The default logger.
	 */
	public static MsgLogger getLogger() {
		if (DEFAULT_LOGGER == null) {
			// Use the console logger. If Eclipse was present, there would of been a logger here, installed by JEMPlugin.startUp().
			// With the consoleLogger, echo console and default level comes from a system property being defined in command line.
			boolean echo = System.getProperty("logEchoConsole", "false").equalsIgnoreCase("true");
			int logLevel = Integer.getInteger("logLevel", LOG_WARNING).intValue();
			if (logLevel < MIN_LOG && logLevel > LOG_NONE)
				logLevel = LOG_WARNING;	// Outside of available level.			
			DEFAULT_LOGGER = new ConsoleLogger(echo, logLevel);
		}
		return DEFAULT_LOGGER;
	}
	
	/*
	 * <package-protected> so that only JEMPlugin can do this. Default logger will be calculated otherwise.
	 */
	static void setDefaultLogger(MsgLogger logger) {
		DEFAULT_LOGGER = logger;
	}
	
	protected MsgLogger(boolean logEchoConsole) {
		this.logEchoConsole = logEchoConsole;
	}
	
	public final void log(Throwable e) {
		log(e, LOG_DEFAULT);
	}
	
	public final void log(Throwable e, int logLevel) {
		primLog(e, logLevel);
		if (logEchoConsole)
			echoConsole(e);
	}
	
	protected void echoConsole(Throwable e) {
		e.printStackTrace();
	}

	public final void log(Object object) {
		log(object, LOG_DEFAULT);
	}
	
	public final void log(Object object, int logLevel) {
		primLog(object, logLevel);
		if (logEchoConsole)
			echoConsole(object);
	}

	protected void echoConsole(Object object) {
		System.out.println(object.toString());
	}

	// The following are added to match up with Hyades so that primitives can be logged too.
	public final void log(boolean msg) {
		log(msg, LOG_DEFAULT);
	}
	
	public final void log(boolean msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));		
	}
	
	public final void log(char msg) {
		log(msg, LOG_DEFAULT);
	}
	
	public final void log(char msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));		
	}

	public final void log(byte msg) {
		log(msg, LOG_DEFAULT);
	}

	public final void log(byte msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));		
	}

	public final void write(short msg) {
		log(msg, LOG_DEFAULT);
	}

	public final void log(short msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));		
	}

	public final void log(int msg) {
		log(msg, LOG_DEFAULT);
	}

	public final void log(int msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));		
	}

	public final void log(long msg) {
		log(msg, LOG_DEFAULT);
	}

	public final void log(long msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));				
	}

	public final void log(float msg) {
		log(msg, LOG_DEFAULT);
	}

	public final void log(float msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));		
	}


	public final void log(double msg) {
		log(msg, LOG_DEFAULT);
	}

	public final void log(double msg, int logLevel) {
		primLog(msg, logLevel);
		if (logEchoConsole)
			echoConsole(String.valueOf(msg));				
	}
	
	protected abstract void primLog(Object object, int logLevel);
	protected abstract void primLog(Throwable exc, int logLevel);
	protected abstract void primLog(boolean msg, int logLevel);
	protected abstract void primLog(byte msg, int logLevel);
	protected abstract void primLog(char msg, int logLevel);
	protected abstract void primLog(double msg, int logLevel);
	protected abstract void primLog(float msg, int logLevel);
	protected abstract void primLog(int msg, int logLevel);
	protected abstract void primLog(long msg, int logLevel);
	protected abstract void primLog(short msg, int logLevel);	

	public void setLogEchoConsole(boolean logEchoConsole) {
		this.logEchoConsole = logEchoConsole;
	}

	public boolean isLogEchoConsole() {
		return logEchoConsole;
	}

}
