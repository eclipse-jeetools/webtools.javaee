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
package org.eclipse.jst.jee.ui.internal.navigator;

import java.net.URL;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.ui.internal.navigator.dnd.IModuleExtensions;
import org.eclipse.jst.jee.ui.internal.navigator.ear.AbstractEarNode;
import org.eclipse.jst.jee.ui.internal.navigator.ear.GroupEARProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * EAR 5.0 Label provider is Deployment Descriptor label provider, 
 * used for decorating of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class Ear5LabelProvider extends J2EELabelProvider {
	
	private static ImageDescriptor IMG_MODULES_NODE;
	private static ImageDescriptor IMG_MODULE_UTIL;
	private static ImageDescriptor IMG_MODULE_WEB;
	private static ImageDescriptor IMG_MODULE_EJB;
	private static ImageDescriptor IMG_MODULE_CLIENT;
	private static ImageDescriptor IMG_MODULE_CONNECTOR;

	static {
		try {
			IMG_MODULES_NODE = J2EENavigatorPlugin.getDefault().getImageDescriptor("folder.gif"); //$NON-NLS-1$
			IMG_MODULE_UTIL = ImageDescriptor.createFromURL((URL) J2EEPlugin.getPlugin().getImage("utiljar_obj")); //$NON-NLS-1$
			IMG_MODULE_WEB = ImageDescriptor.createFromURL((URL) J2EEPlugin.getPlugin().getImage("full/obj16/module_web_obj")); //$NON-NLS-1$
			IMG_MODULE_EJB = ImageDescriptor.createFromURL((URL) J2EEPlugin.getPlugin().getImage("full/obj16/module_ejb_obj")); //$NON-NLS-1$
			IMG_MODULE_CLIENT = ImageDescriptor.createFromURL((URL) J2EEPlugin.getPlugin().getImage("module_clientapp_obj")); //$NON-NLS-1$
			IMG_MODULE_CONNECTOR = ImageDescriptor.createFromURL((URL) J2EEPlugin.getPlugin().getImage("full/obj16/connector_module")); //$NON-NLS-1$
		} catch (RuntimeException e) {
			String msg = e.getMessage() != null ? e.getMessage() : e.toString();
			JEEUIPlugin.getDefault().logError(msg, e);
			IMG_MODULES_NODE = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_UTIL = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_WEB = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_EJB = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_CLIENT = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_CONNECTOR = ImageDescriptor.getMissingImageDescriptor();
		}
	}

	@Override
	public Image getImage(Object element) {
		Image ret = null;

		if (element instanceof AbstractEarNode) {
			ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULES_NODE);
		} else if (element instanceof IVirtualReference) {
			IVirtualComponent component = ((IVirtualReference) element).getReferencedComponent();
			if (component.isBinary()) { 
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_UTIL);
			} else if (JavaEEProjectUtilities.isDynamicWebComponent(component)) {
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_WEB);
			} else if (JavaEEProjectUtilities.isEJBComponent(component)) {
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_EJB);
			} else if (JavaEEProjectUtilities.isApplicationClientComponent(component)) {
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_CLIENT);
			} else if (JavaEEProjectUtilities.isJCAComponent(component)) {
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_CONNECTOR);
			} else if (JavaEEProjectUtilities.isUtilityProject(component.getProject())) {
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_UTIL);
			} else if (component instanceof VirtualArchiveComponent) {
				ret = J2EENavigatorPlugin.getDefault().getImage(IMG_MODULE_UTIL);
			}
		} else if (element instanceof GroupEARProvider){
			ret = ((GroupEARProvider) element).getImage();
		}
		return ret;
	}

	@Override
	public String getText(Object element) {
		String ret = null;
		if (element instanceof AbstractEarNode) {
			ret =  ((AbstractEarNode) element).getText();
		} else if (element instanceof IVirtualReference) {
			IVirtualComponent component = ((IVirtualReference) element).getReferencedComponent();

			if (JavaEEProjectUtilities.isDynamicWebComponent(component)) {
				if (!component.isBinary()) {
					ret = "Web " + component.getName() + IModuleExtensions.DOT_WAR; //$NON-NLS-1$
				} else {
					Path path = new Path(component.getDeployedName());
					return path.lastSegment();
				}
			} else if (JavaEEProjectUtilities.isEJBComponent(component)) {
				if (!component.isBinary()) {
					ret = "EJB " + component.getName() + IModuleExtensions.DOT_JAR; //$NON-NLS-1$
				} else {
					Path path = new Path(component.getDeployedName());
					return path.lastSegment();
				}
			} else if (JavaEEProjectUtilities.isApplicationClientComponent(component)){
				if (!component.isBinary()) {
					ret = "APP Client " + component.getName() + IModuleExtensions.DOT_JAR; //$NON-NLS-1$
				} else {
					Path path = new Path(component.getDeployedName());
					return path.lastSegment();
				}
			} else if (JavaEEProjectUtilities.isJCAProject(component.getProject())) {
				ret = "Connector " + component.getName() + IModuleExtensions.DOT_JAR; //$NON-NLS-1$
			} else if (JavaEEProjectUtilities.isUtilityProject(component.getProject())) {
				ret = component.getName() + IModuleExtensions.DOT_JAR;
			} else if (component.isBinary()) {
				VirtualArchiveComponent virtualArchiveComponent = (VirtualArchiveComponent)component;
				String deployedName = virtualArchiveComponent.getDeployedName();
				Path path = new Path(deployedName);
				return path.lastSegment();
			}
		}  else if (element instanceof GroupEARProvider){
			ret = ((GroupEARProvider) element).getText(); 
		}
		return ret;
	}

}
