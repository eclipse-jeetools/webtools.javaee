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
package org.eclipse.jst.jee.ui.internal.navigator.ear;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

/**
 * Ear 5 Deployment descriptor node.
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupEARProvider {

	private static final String PROJECT_RELATIVE_PATH = "META-INF/application.xml"; //$NON-NLS-1$
	private static Image EAR_IMAGE;
	private EARVirtualComponent earComponent;
	private IFile ddFile;

	public GroupEARProvider(EARVirtualComponent wtpComponent) {
		earComponent = wtpComponent;
	}

	public IProject getProject() {
		return earComponent.getProject();
	}

	public String getText() {
		return "Deployment Descriptor: " + earComponent.getName(); //$NON-NLS-1$
	}

	public EARVirtualComponent getEARVirtualComponent() {
		return this.earComponent;
	}

	public List getChildren() {
		return null;
	}

	public Image getImage() {
		return getEarImage();
	}

	public static Image getEarImage() {
		if (EAR_IMAGE == null) {
			ImageDescriptor imageDescriptor = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.EAR_IMAGE);
			EAR_IMAGE = imageDescriptor.createImage();
		}
		return EAR_IMAGE;
	}

	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}
	public IFile getDDFile() {
		if (ddFile != null){
			return ddFile;
		}
		IVirtualFolder virtualFolder = ComponentCore.createComponent(getProject()).getRootFolder();
		ddFile = virtualFolder.getFile(PROJECT_RELATIVE_PATH).getUnderlyingFile();
		return ddFile;
	}
}
