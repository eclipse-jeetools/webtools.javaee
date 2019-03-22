/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

/**
 * J2EEProjectDecorator
 */
public class J2EEProjectDecorator extends LabelProvider implements ILightweightLabelDecorator {

    private static ImageDescriptor EAR;
    private static ImageDescriptor APPCLIENT;
    private static ImageDescriptor DYNAMICWEB;
    private static ImageDescriptor EJB;
    private static ImageDescriptor CONNECTOR;
    private static ImageDescriptor WEBFRAGMENT;
      
    /* The constants are duplicated here to avoid plugin loading. */
    private static final String EAR_FACET = "jst.ear"; //$NON-NLS-1$ 
    private static final String APPCLIENT_FACET = "jst.appclient"; //$NON-NLS-1$ 
    private static final String WEB_FACET = "jst.web"; //$NON-NLS-1$ 
    private static final String EJB_FACET = "jst.ejb"; //$NON-NLS-1$ 
    private static final String CONNECTOR_FACET = "jst.connector"; //$NON-NLS-1$ 
    private static final String STATIC_WEB_FACET = "wst.web"; //$NON-NLS-1$ 
    private static final String WEB_FRAGMENT_FACET = "jst.webfragment"; //$NON-NLS-1$
    
    private static final String ICON_DIR = "icons/full/ovr16"; //$NON-NLS-1$
    
    
    public J2EEProjectDecorator() {
        super();
    }
    
    public void decorate(Object element, IDecoration decoration) {
    	IProject project = null;
    	if(element instanceof IProject) {
    		project = (IProject) element;
    	} else if(element instanceof IJavaProject) {
    		project = ((IJavaProject)element).getProject();
    	}
        if (project != null) {  
        	ImageDescriptor overlay = null;
			if (hasFacet(project, EAR_FACET))
				overlay=getEAR();
			else if (hasFacet(project, APPCLIENT_FACET))
				overlay=getAPPCLIENT();
			else if (hasFacet(project, WEB_FACET))
				overlay=getDYNAMICWEB();
			else if (hasFacet(project, EJB_FACET))
				overlay=getEJB();
			else if (hasFacet(project, CONNECTOR_FACET))
				overlay=getCONNECTOR();
			else if (hasFacet(project, STATIC_WEB_FACET))
				overlay=getDYNAMICWEB();
			else if (hasFacet(project, WEB_FRAGMENT_FACET))
				overlay=getWEBFRAGMENT();

			// keep it null for if (hasFacet(project, UTILITY_FACET))
			if (overlay != null)
				decoration.addOverlay(overlay); 
        }
	}

    private boolean hasFacet(IProject project, String facet) {
		try {
			return FacetedProjectFramework.hasProjectFacet(project, facet);
		} catch (CoreException e) { 
			J2EENavigatorPlugin.logError(0, e.getMessage(), e);
			return false;
		}
	}
    
    /**
	 * This gets a .gif from the icons folder.
	 */
	private static ImageDescriptor getImageDescriptor(String key) {
		ImageDescriptor imageDescriptor = null;
		if (key != null) {
			String gif = "/" + key + ".gif"; //$NON-NLS-1$ //$NON-NLS-2$
			IPath path = new Path(ICON_DIR).append(gif);
			URL gifImageURL = FileLocator.find(Platform.getBundle(J2EENavigatorPlugin.PLUGIN_ID), path, null);
			if (gifImageURL != null)
				imageDescriptor = ImageDescriptor.createFromURL(gifImageURL);
		}
		return imageDescriptor;
	}

    private static ImageDescriptor getEAR() {
    	if (EAR == null) {
    		EAR = getImageDescriptor("enterprise_app_ovr"); //$NON-NLS-1$
    	}
    	return EAR;
    }
    
    private static ImageDescriptor getAPPCLIENT() {
    	if (APPCLIENT == null) {
    		APPCLIENT = getImageDescriptor("client_app_ovr"); //$NON-NLS-1$
    	}
    	return APPCLIENT;
    }
    
    private static ImageDescriptor getDYNAMICWEB() {
    	if (DYNAMICWEB == null) {
    		DYNAMICWEB = getImageDescriptor("web_module_ovr"); //$NON-NLS-1$
    	}
    	return DYNAMICWEB;
    }
    
    private static ImageDescriptor getWEBFRAGMENT() {
    	if (WEBFRAGMENT == null) {
    		WEBFRAGMENT = getImageDescriptor("webfragmentoverlayicon"); //$NON-NLS-1$
    	}
    	return WEBFRAGMENT;
    }
    
    private static ImageDescriptor getEJB() {
    	if (EJB == null) {
    		EJB = getImageDescriptor("ejb_module_ovr"); //$NON-NLS-1$
    	}
    	return EJB;
    }
    
    private static ImageDescriptor getCONNECTOR() {
    	if (CONNECTOR == null) {
    		CONNECTOR = getImageDescriptor("connector_ovr"); //$NON-NLS-1$
    	}
    	return CONNECTOR;
    }

}

