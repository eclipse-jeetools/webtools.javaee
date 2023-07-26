/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *     IBM - bug 281382 clean up
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.net.URL;
import java.util.HashMap;

import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.jee.ui.internal.navigator.ear.AbstractEarNode;
import org.eclipse.jst.jee.ui.internal.navigator.ear.GroupEARProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.internal.util.VirtualReferenceUtilities;
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
	private static HashMap<String, ImageDescriptor> typeToDescriptor;
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
			JEEUIPlugin.logError(msg, e);
			IMG_MODULES_NODE = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_UTIL = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_WEB = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_EJB = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_CLIENT = ImageDescriptor.getMissingImageDescriptor();
			IMG_MODULE_CONNECTOR = ImageDescriptor.getMissingImageDescriptor();
		}
		typeToDescriptor = new HashMap<String, ImageDescriptor>();
		typeToDescriptor.put(IJ2EEFacetConstants.APPLICATION_CLIENT, IMG_MODULE_CLIENT);
		typeToDescriptor.put(IJ2EEFacetConstants.DYNAMIC_WEB, IMG_MODULE_WEB);
		typeToDescriptor.put(IJ2EEFacetConstants.EJB, IMG_MODULE_EJB);
		typeToDescriptor.put(IJ2EEFacetConstants.JCA, IMG_MODULE_CONNECTOR);
		typeToDescriptor.put(IJ2EEFacetConstants.UTILITY, IMG_MODULE_UTIL);
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
			} else {
				String type = JavaEEProjectUtilities.getJ2EEComponentType(component);
				ImageDescriptor desc = typeToDescriptor.get(type);
				ret = J2EENavigatorPlugin.getDefault().getImage(desc == null ? IMG_MODULE_UTIL : desc);
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
			String archiveName = ((IVirtualReference)element).getArchiveName();
			if( component.isBinary()) {
				ret = archiveName != null ? archiveName : (new Path(component.getDeployedName()).lastSegment());
			} else {
				// more efficient to get type once
				String type = JavaEEProjectUtilities.getJ2EEComponentType(component);
				if( archiveName == null )
					archiveName = VirtualReferenceUtilities.INSTANCE.getDefaultArchiveName((IVirtualReference)element);
							
				if( type.equals(IJ2EEFacetConstants.DYNAMIC_WEB))
					ret = "Web " + archiveName; //$NON-NLS-1$
				else if( type.equals(IJ2EEFacetConstants.EJB))
					ret = "EJB " + archiveName;//$NON-NLS-1$
				else if( type.equals(IJ2EEFacetConstants.APPLICATION_CLIENT))
					ret = "APP Client " + archiveName;//$NON-NLS-1$
				else if( type.equals(IJ2EEFacetConstants.JCA))
					ret = "Connector " + archiveName;//$NON-NLS-1$
				else if( type.equals(IJ2EEFacetConstants.UTILITY))
					ret = archiveName;
			}
		}  else if (element instanceof GroupEARProvider){
			ret = ((GroupEARProvider) element).getText(); 
		}
		return ret;
	}

}
