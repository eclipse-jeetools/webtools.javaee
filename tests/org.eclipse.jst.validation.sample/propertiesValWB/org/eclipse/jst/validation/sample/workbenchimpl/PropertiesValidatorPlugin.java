package org.eclipse.jst.validation.sample.workbenchimpl;
/*
 * Licensed Material - Property of IBM (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved. US
 * Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP
 * Schedule Contract with IBM Corp.
 * 
 * DISCLAIMER OF WARRANTIES. The following [enclosed] code is sample code created by IBM
 * Corporation. This sample code is not part of any standard or IBM product and is provided to you
 * solely for the purpose of assisting you in the development of your applications. The code is
 * provided "AS IS". IBM MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, REGARDING THE
 * FUNCTION OR PERFORMANCE OF THIS CODE. THIS CODE MAY CONTAIN ERRORS. IBM shall not be liable for
 * any damages arising out of your use of the sample code, even if it has been advised of the
 * possibility of such damages.
 *  
 */

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;
/**
 * This class is the Plugin class identified by the com.ibm.etools.validation.core.prop's plugin.xml
 * file (i.e., the &lt;plugin <br><br>
 * class=&quot;class="com.ibm.etools.validation.core.properties.workbenchimpl.PropertiesValidatorPlugin"&quot&gt;
 */
public class PropertiesValidatorPlugin extends WTPPlugin {
	private static PropertiesValidatorPlugin _inst = null;
	public static final String PLUGIN_ID = "org.eclipse.jst.validation.sample"; //$NON-NLS-1$
	private static Logger _logger;
	public PropertiesValidatorPlugin() {
		super();
		if (_inst == null) {
			_inst = this;
		}
	}
	public static PropertiesValidatorPlugin getPlugin() {
		return _inst;
	}
	public Logger getMsgLogger() {
		if (_logger == null)
			_logger = Logger.getLogger();
		return _logger;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.WTPPlugin#getPluginID()
	 */
	public String getPluginID() {
		return PLUGIN_ID;
	}
}
