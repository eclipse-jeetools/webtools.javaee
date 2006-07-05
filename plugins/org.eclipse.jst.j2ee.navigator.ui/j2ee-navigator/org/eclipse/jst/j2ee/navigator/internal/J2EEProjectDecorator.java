package org.eclipse.jst.j2ee.navigator.internal;

import java.net.URL;

import org.eclipse.core.resources.IProject;
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
import org.eclipse.wst.common.project.facet.core.internal.FacetedProjectPropertyTester;

/**
 * J2EEProjectDecorator
 */
public class J2EEProjectDecorator extends LabelProvider implements ILightweightLabelDecorator {

    private static ImageDescriptor EAR;
    private static ImageDescriptor APPCLIENT;
    private static ImageDescriptor DYNAMICWEB;
    private static ImageDescriptor EJB;
    private static ImageDescriptor CONNECTOR;
    
    private static final String PROJECT_FACET = "projectFacet"; //$NON-NLS-1$     
    
    /* The */
    private static final String EAR_FACET = "jst.ear"; //$NON-NLS-1$ 
    private static final String APPCLIENT_FACET = "jst.appclient"; //$NON-NLS-1$ 
    private static final String WEB_FACET = "jst.web"; //$NON-NLS-1$ 
    private static final String EJB_FACET = "jst.ejb"; //$NON-NLS-1$ 
    private static final String UTILITY_FACET = "jst.utility"; //$NON-NLS-1$ 
    private static final String CONNECTOR_FACET = "jst.connector"; //$NON-NLS-1$ 
    private static final String STATIC_WEB_FACET = "wst.web"; //$NON-NLS-1$ 
    
    private static final String ICON_DIR = "icons/full/ovr16"; //$NON-NLS-1$
    
    private static final FacetedProjectPropertyTester facetPropertyTester = new FacetedProjectPropertyTester();
    
    public J2EEProjectDecorator() {
        super();
    }
    
    public void decorate(Object element, IDecoration decoration) {
    	
    	if(element instanceof IJavaProject) {
    		element = ((IJavaProject)element).getProject();
    	}
        if (element instanceof IProject) {  
    	
        	IProject project = (IProject) element;
        	ImageDescriptor overlay = null;
			if (hasFacet(element, EAR_FACET))
				overlay=getEAR();
			else if (hasFacet(element, APPCLIENT_FACET))
				overlay=getAPPCLIENT();
			else if (hasFacet(element, WEB_FACET))
				overlay=getDYNAMICWEB();
			else if (hasFacet(element, EJB_FACET))
				overlay=getEJB();
			else if (hasFacet(element, CONNECTOR_FACET))
				overlay=getCONNECTOR();
			else if (hasFacet(element, STATIC_WEB_FACET))
				overlay=getDYNAMICWEB();
			else if (hasFacet(element, UTILITY_FACET))
				overlay=null;
			
			if (overlay != null)
				decoration.addOverlay(overlay); 
        }
	}

    private boolean hasFacet(Object element, String facet) {
		return facetPropertyTester.test(element, PROJECT_FACET, new Object[] {}, facet);
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

