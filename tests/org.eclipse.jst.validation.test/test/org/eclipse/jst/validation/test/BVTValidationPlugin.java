package org.eclipse.jst.validation.test;

import org.eclipse.core.runtime.IPluginDescriptor;

/**
 * Plugin for TVT testing.
 */
public class BVTValidationPlugin extends org.eclipse.wst.common.frameworks.internal.WTPPlugin {
	private static BVTValidationPlugin _inst = null;
	public static final String PLUGIN_ID = "org.eclipse.jst.validation.test"; //$NON-NLS-1$
//	private boolean _debug = false;
//	private static final String LOG_FILE_NAME = "fvtlog.xml";

	/**
	 * ValidationTVTPlugin constructor comment.
	 * @param descriptor org.eclipse.core.runtime.IPluginDescriptor
	 */
	public BVTValidationPlugin(IPluginDescriptor descriptor) {
		super(descriptor);
		if(_inst == null) {
			_inst = this;
		}
	}
	
	public static BVTValidationPlugin getPlugin() {
		return _inst;
	}
/*	
	private void initializeLog(int logLevel) {
		IPath pluginStateLocation = getPlugin().getStateLocation();
		IPath logFilePath = pluginStateLocation.append(LOG_FILE_NAME);
		String logName = logFilePath.toOSString();
		BVTValidationPlugin.getPlugin().getMsgLogger().setFileName(logName);
		ValidationPlugin.getPlugin().getMsgLogger().setFileName(logName);
	}
*/	
/*	public MsgLogger getMsgLogger() {
		if (_logger == null) {
			_logger = (MsgLogger) MsgLogger.getFactory().getLogger(PluginHelperImpl.getMsgLoggerName(this), this);
			
			if(!_logger.isLoggingLevel(Level.SEVERE)) {
				// Turn logging on
				_logger.setLevel(Level.SEVERE);
				_logger.setFileName(org.eclipse.core.runtime.Platform.getLocation().addTrailingSeparator().append(".metadata").addTrailingSeparator().toOSString().concat("LoggingUtil.log"));
			}
			
		}
		return _logger;
	}
	*/
	/**
	 * Retrieves a hashtable of a logger's preferences initially from the com.ibm.etools.logging.util.loggingDefaults  
	 * extension point if specified in the com.ibm.etools.logging.util plugin.xml file.  If specified, the 
	 * com.ibm.etools.logging.util.loggingOptions extension point preferences in the parameter plugin's
	 * plugin.xml file are returned.  
	 * 
	 * The logger's preferences are stored in the return hashtable using the static instance variables 
	 * in LoggerStateHashKeys as keys.
	 * 
	 * @param plugin the Plugin polled for their logger's preferences in the plugin.xml file
	 * @return hashtable of a logger's preferences
	 */
	/*public Hashtable getMsgLoggerConfig(Plugin plugin) {
		return (new PluginHelperImpl().getMsgLoggerConfig(plugin));
	}*/
	
	/**
	 * Sets the logger's preferences based on values in the parameter hashtable.
	 *
	 * The logger's preferences are stored in the parameter hashtable using the static 
	 * instance variables in LoggerStateHashKeys as keys.
	 * 
	 * @param msgLoggerConfig hashtable of the logger's preferences
	 */
/*	public void setMsgLoggerConfig(Hashtable msgLoggerConfig) {
		getMsgLogger().setMsgLoggerConfig(msgLoggerConfig);
	}*/
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.common.WTPPlugin#getPluginID()
	 */
	public String getPluginID() {
	    return PLUGIN_ID;
	}
}
