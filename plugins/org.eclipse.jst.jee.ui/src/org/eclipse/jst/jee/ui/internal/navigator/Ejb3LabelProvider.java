/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 *     IBM - Java EE 7 support
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.provider.J2EEAdapterFactoryLabelProvider;
import org.eclipse.jst.j2ee.navigator.internal.IJ2EENavigatorConstants;
import org.eclipse.jst.j2ee.navigator.internal.J2EELabelProvider;
import org.eclipse.jst.j2ee.navigator.internal.LoadingDDNode;
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.ui.internal.navigator.ejb.GroupEJBProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;

/**
 * Ejb 3.0 Label provider is Deployment Descriptor label provider, 
 * used for decorating of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 * @author Kaloyan Raev
 */
public class Ejb3LabelProvider extends J2EELabelProvider {
	
	private Image ejb30Image;
	private Image ejb31Image;
	private Image ejb32Image;
	private Image ejb40Image;
	private Image sessionBeanImage;
	private Image mdbImage;
	private Image cmpImage;
	private Image ejbRefImage;
	private Image resourceRefImage;
	private Image serviceRefImage;
	private Image activationConfigImage;


	public Ejb3LabelProvider() {
		new J2EEAdapterFactoryLabelProvider(new DynamicAdapterFactory(IJ2EENavigatorConstants.VIEWER_ID));
	}
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof LoadingDDNode) {
			return ((LoadingDDNode)element).getImage();		
		} else if (element instanceof IProject || element instanceof IJavaProject) {
			return null;
		} else if (element instanceof GroupEJBProvider) {
			String version = ((GroupEJBProvider) element).getEjbJar().getVersion();
			if (J2EEVersionConstants.VERSION_3_0_TEXT.equals(version)) {
				return getEjb30Image();
			} else if (J2EEVersionConstants.VERSION_3_1_TEXT.equals(version)) {
				return getEjb31Image();
			} else if (J2EEVersionConstants.VERSION_3_2_TEXT.equals(version)) {
				return getEjb32Image();
			} else if (J2EEVersionConstants.VERSION_4_0_TEXT.equals(version)) {
                return getEjb40Image();
            }
			return getEjb30Image();
		} else if(element instanceof AbstractDDNode) {
			return ((AbstractDDNode) element).getImage();
		} else if (element instanceof SessionBean) {
			return getSessionBeanImage();
		} else if (element instanceof MessageDrivenBean) {
			return getMDBImage();
		} else if (element instanceof EntityBean){
			return getCMPImage();
		} else if (element instanceof EjbLocalRef || element instanceof EjbRef) {
			return getEjbRefImage();
		} else if (element instanceof EnvEntry) {
			return getResourceRefImage();
		} else if (element instanceof ResourceEnvRef) {
			return getResourceRefImage();
		} else if (element instanceof ResourceRef) {
			return getResourceRefImage();
		} else if (element instanceof ServiceRef) {
			return getServiceRefImage();
		} else if(element instanceof ActivationConfigProperty) {
			return getActivationConfigImage();
		}
      
		return null;
	}

	@Override
	public String getText(Object element) {
		String ret = null;
		if (element instanceof SessionBean || element instanceof MessageDrivenBean || element instanceof EntityBean) {
			ret = (element instanceof SessionBean) ? ((SessionBean) element).getEjbName() : 
			          (element instanceof MessageDrivenBean) ? ((MessageDrivenBean) element).getEjbName() : 
			            (element instanceof EntityBean) ? ((EntityBean) element).getEjbName() : null;
		} else if (element instanceof AbstractDDNode) {
			ret = ((AbstractDDNode) element).getText();
		} else if (element instanceof AbstractGroupProvider) {
			ret = ((AbstractGroupProvider) element).getText();
		} else if (element instanceof EjbLocalRef || element instanceof EjbRef){
		  ret  = element instanceof EjbLocalRef ? ((EjbLocalRef)element).getEjbRefName() : element instanceof EjbRef ? ((EjbRef)element).getEjbRefName() : null;
		} else if (element instanceof EnvEntry){
		  ret = ((EnvEntry) element).getEnvEntryName();
		} else if (element instanceof ResourceEnvRef){
		  ret = ((ResourceEnvRef) element).getResourceEnvRefName();
		} else if (element instanceof ResourceRef){
		  ret = ((ResourceRef) element).getResRefName();
		} else if (element instanceof ServiceRef){
		  ret = ((ServiceRef) element).getServiceRefName();
		} else if(element instanceof ActivationConfigProperty){
		  ret = ((ActivationConfigProperty)element).getActivationConfigPropertyName() + ":" +((ActivationConfigProperty)element).getActivationConfigPropertyValue(); //$NON-NLS-1$
	    }

		return ret;
	}

	@Override
	public String getDescription(Object anElement) {
		String description = super.getDescription(anElement);

		if (anElement instanceof AbstractDDNode) {
			String desc = ((AbstractDDNode) anElement).getDescription();
			if (desc != null) {
				description = desc;
			}
		} 

		return description;
	}
	
	private Image getEjb30Image() {
		if (ejb30Image == null) {
			ejb30Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.IMG_EJBEEMODEL).createImage();
		}
		return ejb30Image;
	}
	
	private Image getEjb31Image() {
		if (ejb31Image == null) {
			ejb31Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.IMG_EJBEE6MODEL).createImage();
		}
		return ejb31Image;
	}

	private Image getEjb32Image() {
		if (ejb32Image == null) {
			ejb32Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.IMG_EJBEE7MODEL).createImage();
		}
		return ejb32Image;
	}
	
	private Image getEjb40Image() {
        if (ejb40Image == null) {
            ejb40Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.IMG_EJBEE10MODEL).createImage();
        }
        return ejb40Image;
    }

	private Image getSessionBeanImage() {
		if (sessionBeanImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("sessionBean_obj"); //$NON-NLS-1$
	        sessionBeanImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return sessionBeanImage;
	}
	
	private Image getMDBImage() {
		if (mdbImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("message_bean_obj"); //$NON-NLS-1$
	        mdbImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return mdbImage;
	}
	
	private Image getCMPImage() {
		if (cmpImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("cmpEntity_obj"); //$NON-NLS-1$
	        cmpImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return cmpImage;
	}
	
	private Image getEjbRefImage() {
		if (ejbRefImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("ejbRef_obj"); //$NON-NLS-1$
	        ejbRefImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return ejbRefImage;
	}
	
	private Image getResourceRefImage() {
		if (resourceRefImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
	        resourceRefImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return resourceRefImage;
	}
	
	private Image getServiceRefImage() {
		if (serviceRefImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("attribute_obj"); //$NON-NLS-1$
	        serviceRefImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return serviceRefImage;
	}
	
	private Image getActivationConfigImage() {
		if (activationConfigImage == null) {
			URL url = (URL) J2EEPlugin.getPlugin().getImage("access_intent_obj"); //$NON-NLS-1$
			activationConfigImage = ImageDescriptor.createFromURL(url).createImage();
		}
		return activationConfigImage;
	}

}
