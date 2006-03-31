package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;

/**
 * J2EEProjectDecorator
 */
public class J2EEProjectDecorator extends LabelProvider implements ILightweightLabelDecorator {

    private static final ImageDescriptor EAR = getImageDescriptor("enterprise_app_ovr"); //$NON-NLS-1$
    private static final ImageDescriptor APPCLIENT = getImageDescriptor("client_app_ovr"); //$NON-NLS-1$
    private static final ImageDescriptor DYNAMICWEB = getImageDescriptor("web_module_ovr"); //$NON-NLS-1$
    private static final ImageDescriptor EJB = getImageDescriptor("ejb_module_ovr"); //$NON-NLS-1$
    private static final ImageDescriptor CONNECTOR = getImageDescriptor("connector_ovr"); //$NON-NLS-1$
    
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
			if (J2EEProjectUtilities.isEARProject(project))
				overlay=EAR;
			else if (J2EEProjectUtilities.isApplicationClientProject(project))
				overlay=APPCLIENT;
			else if (J2EEProjectUtilities.isDynamicWebProject(project))
				overlay=DYNAMICWEB;
			else if (J2EEProjectUtilities.isEJBProject(project))
				overlay=EJB;
			else if (J2EEProjectUtilities.isJCAProject(project))
				overlay=CONNECTOR;
			else if (J2EEProjectUtilities.isStaticWebProject(project))
				overlay=DYNAMICWEB;
			else if (J2EEProjectUtilities.isUtilityProject(project))
				overlay=null;
			
			if (overlay != null)
				decoration.addOverlay(overlay);
        }
	}

    private static ImageDescriptor getImageDescriptor(String imageFileName) {
        if (imageFileName != null)
            return J2EEUIPlugin.getDefault().getImageDescriptor(imageFileName);
        return null;
    }

}

