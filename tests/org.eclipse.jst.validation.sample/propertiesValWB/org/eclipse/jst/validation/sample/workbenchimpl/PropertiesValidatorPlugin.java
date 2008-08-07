/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.validation.sample.workbenchimpl;

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
