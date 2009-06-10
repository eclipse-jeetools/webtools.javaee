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

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.resource.ImageDescriptor;
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
 */
public class Ejb3LabelProvider extends J2EELabelProvider {

  public Ejb3LabelProvider() {
    new J2EEAdapterFactoryLabelProvider(new DynamicAdapterFactory(IJ2EENavigatorConstants.VIEWER_ID));
  }
	@Override
	public Image getImage(Object element) {
		Image ret = null;
		if(element instanceof LoadingDDNode)
          return ((LoadingDDNode)element).getImage();
      if (element instanceof IProject || element instanceof IJavaProject)
          return null;
      if(element instanceof GroupEJBProvider) {
        ImageDescriptor imageDescriptor = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.IMG_EJBEEMODEL);
        return imageDescriptor.createImage();
      }
      if(element instanceof AbstractDDNode){
        return ((AbstractDDNode)element ).getImage();
        
      }
      
      if (element instanceof SessionBean){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("sessionBean_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
      
      if (element instanceof MessageDrivenBean){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("message_bean_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
      
      if (element instanceof EntityBean){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("cmpEntity_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
      if (element instanceof EjbLocalRef || element instanceof EjbRef){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("ejbRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      } else if (element instanceof EnvEntry){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      } else if (element instanceof ResourceEnvRef){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      } else if (element instanceof ResourceRef){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      } else if (element instanceof ServiceRef){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("attribute_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      } else if(element instanceof ActivationConfigProperty){
        URL url = (URL) J2EEPlugin.getPlugin().getImage("access_intent_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
      
      
        
      return ret;
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
		}else if(element instanceof ActivationConfigProperty){
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
	

}
