/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.ejb.ui.tests;

import org.eclipse.osgi.util.NLS;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(NLS.bind(Messages.TestSuiteTitle, Activator.PLUGIN_ID));
		//$JUnit-BEGIN$
		suite.addTestSuite(org.eclipse.jst.ejb.ui.BusinessInterfaceSelectionExtensionTest.class);
		//$JUnit-END$
		return suite;
	}

}
