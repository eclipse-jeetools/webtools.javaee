/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Apr 29, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import java.net.URL;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.actions.AbstractOpenAction;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.wst.internet.webbrowser.WebBrowser;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class OpenExternalWSDLAction extends AbstractOpenAction {

	String uri = ""; //$NON-NLS-1$

	/**
	 * @param text
	 */
	public OpenExternalWSDLAction(String text) {
		super(text);
		this.setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(J2EEUIPlugin.PLUGIN_ID, "icons/web_type.gif")); //$NON-NLS-1$
	}

	public void run() {
		try {
			WebBrowser.openURL(new URL(uri), WebBrowser.LOCATION_BAR | WebBrowser.BUTTON_BAR, null);
		} catch (Exception e) {
			//Ignore
		}
	}

	public boolean updateSelection(IStructuredSelection s) {
		Object obj = s.getFirstElement();
		if (obj.getClass().getName().equals("org.eclipse.wst.wsdl.internal.util.WSDLResourceImpl"))
			uri = ((Resource) obj).getURI().toString();
		return super.updateSelection(s);
	}
}