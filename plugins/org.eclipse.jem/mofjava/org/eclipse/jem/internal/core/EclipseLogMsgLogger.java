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
 *  $RCSfile: EclipseLogMsgLogger.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.Plugin;

/**
 * Logger that uses the Eclipse plugin's .log file.
 */
public class EclipseLogMsgLogger extends StringPrimitiveLogger {
	
	public static final int[] STATUS_LEVEL;
	public static final int[] LEVEL_STATUS;
	
	static {
		// Status levels that correspond to the log levels, from finest to none.
		STATUS_LEVEL =
			new int[] { IStatus.OK, IStatus.OK, IStatus.OK, IStatus.OK, IStatus.INFO, IStatus.WARNING, IStatus.ERROR, IStatus.OK };

		// Levels that correspond to the IStatus levels.
		int maxID = Math.max(IStatus.OK, Math.max(IStatus.INFO, Math.max(IStatus.WARNING, IStatus.ERROR)));
		LEVEL_STATUS = new int[maxID+1];
		LEVEL_STATUS[IStatus.OK] = LOG_FINE;
		LEVEL_STATUS[IStatus.INFO] = LOG_INFO;
		LEVEL_STATUS[IStatus.WARNING] = LOG_WARNING;
		LEVEL_STATUS[IStatus.ERROR] = LOG_SEVERE;
	}	
	

	public static final String DEBUG_ECHOCONSOLE_OPTION = "/debug/echoconsole"; //$NON-NLS-1$
	public static final String DEBUG_LOG_LEVEL = "/debug/loglevel"; // The logging level to use when no Hyaedes.
	public static final String DEFAULT_OPTION = "default";	// If option value is this, then the value from JEMPlugin will be used.


	/**
	 * A helper to create a EclipseLogMsgLogger for a plugin. It will
	 * get the echoConsole and currentLogLevel's out of the .options file in the JEM plugin.
	 * 
	 * @param plugin
	 * @return
	 */
	public static MsgLogger createLogger(Plugin plugin) {
		String pluginOption = Platform.getDebugOption(plugin.getDescriptor().getUniqueIdentifier() + DEBUG_ECHOCONSOLE_OPTION);
		if (pluginOption == null || "default".equalsIgnoreCase(pluginOption))
			pluginOption = Platform.getDebugOption(JEMPlugin.getPlugin().getDescriptor().getUniqueIdentifier() + DEBUG_ECHOCONSOLE_OPTION); 
		boolean echoConsole = "true".equalsIgnoreCase(pluginOption);
		
		pluginOption = Platform.getDebugOption(plugin.getDescriptor().getUniqueIdentifier() + DEBUG_LOG_LEVEL);
		if (pluginOption == null || "default".equalsIgnoreCase(pluginOption))
			pluginOption = Platform.getDebugOption(JEMPlugin.getPlugin().getDescriptor().getUniqueIdentifier() + DEBUG_LOG_LEVEL);
		
		int logLevel = LOG_WARNING;
		if (pluginOption != null) {
			try {
				logLevel = Integer.parseInt(pluginOption);
				if (logLevel < MIN_LOG && logLevel > LOG_NONE)
					logLevel = LOG_WARNING;	// Outside of available level.
			} catch (NumberFormatException e) {
			}
		}
		return new EclipseLogMsgLogger(plugin, echoConsole, logLevel);		
	}
	
	private Plugin plugin;
	private String pluginID;
	private int currentLogLevel;

	/**
	 * @param plugin
	 * @param logEchoConsole
	 */
	public EclipseLogMsgLogger(Plugin plugin, boolean logEchoConsole, int currentLogLevel) {
		super(logEchoConsole);
		this.plugin = plugin;
		pluginID = plugin.getDescriptor().getUniqueIdentifier();
		this.currentLogLevel = currentLogLevel;
	}

	protected void primLogStatus(IStatus status, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = getLogLevel(status);
		if (doLog(logLevel)) {
			getPluginLog().log(status);
		}
	}

	protected void primLogCoreException(CoreException ext, int logLevel) {
		primLogStatus(ext.getStatus(), logLevel);
	}
	
	protected void primLog(Object object, int logLevel) {
		if (object instanceof IStatus)
			primLogStatus((IStatus) object, logLevel);
		else if (object instanceof CoreException) 
			primLogCoreException((CoreException) object, logLevel);
		else {
			if (logLevel == LOG_DEFAULT)
				logLevel = LOG_FINEST;
			if (doLog(logLevel)) {
				getPluginLog().log(
					new Status(
						getStatusSeverity(logLevel),
						pluginID,
						0,
						object.toString(),
						null));
			}
		}
	}

	protected void primLog(Throwable exc, int logLevel) {
		if (logLevel == LOG_DEFAULT)
			logLevel = LOG_SEVERE;		
		if (doLog(logLevel)) {
			getPluginLog().log(
				new Status(
					getStatusSeverity(logLevel),
					pluginID,
					0,
					"Exception",
					exc));
		}
	}

	protected boolean doLog(int logLevel) {
		return logLevel >= currentLogLevel && logLevel < LOG_NONE;
	}

	public ILog getPluginLog() {
		return plugin.getLog();
	}

	protected int getLogLevel(IStatus status) {
		return LEVEL_STATUS[status.getSeverity()];
	}

	protected int getStatusSeverity(int logLevel) {
		return STATUS_LEVEL[logLevel];
	}
}
