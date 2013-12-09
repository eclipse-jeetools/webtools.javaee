/***********************************************************************
 * Copyright (c) 2010, 2013 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *     IBM - Java EE 7 support
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ra;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

public class RaGroupContentProvider extends AbstractConnectorGroupProvider {

	private IProject project;
	private Image CONNECTOR_IMAGE;
	private Image CONNECTOR_17_IMAGE;

	public RaGroupContentProvider(JavaEEObject javaee, IProject project) {
		super(javaee);
		this.project = project;
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		children.add(new GroupRaContentProvider(javaee));
		if(((Connector)javaee).getLicense() != null){
			children.add(((Connector)javaee).getLicense());
		}
		return children;
	}

	@Override
	public Image getImage() {
		// default to 1.7
		Image retVal = getConnector17Image();
		String version = ((Connector)javaee).getVersion();
		if (J2EEVersionConstants.VERSION_1_6_TEXT.equals(version)) {
			retVal = getConnector16Image();
		} else if (J2EEVersionConstants.VERSION_1_7_TEXT.equals(version)) {
			retVal = getConnector17Image();
		} 
		return retVal;
	}
	
	
	@Override
	public String getText() {
		return NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, project.getName());
	}

	private Image getConnector16Image() {
		if (CONNECTOR_IMAGE == null) {
			CONNECTOR_IMAGE = JEEUIPlugin.getDefault().getImage(JEEUIPluginIcons.IMG_CONNECTOR);
		}
		return CONNECTOR_IMAGE;
	}

	private Image getConnector17Image() {
		if (CONNECTOR_17_IMAGE == null) {
			CONNECTOR_17_IMAGE = JEEUIPlugin.getDefault().getImage(JEEUIPluginIcons.IMG_EE7CONNECTOR);
		}
		return CONNECTOR_17_IMAGE;
	}
}