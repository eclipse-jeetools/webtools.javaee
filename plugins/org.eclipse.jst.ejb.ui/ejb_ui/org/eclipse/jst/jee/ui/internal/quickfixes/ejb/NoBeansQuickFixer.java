package org.eclipse.jst.jee.ui.internal.quickfixes.ejb;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.IMarkerResolutionGenerator2;

public class NoBeansQuickFixer implements IMarkerResolutionGenerator2  {

	public IMarkerResolution[] getResolutions(IMarker marker) {
        return new IMarkerResolution[] {
        		new CreateBeanQuickFix(CreateBeanQuickFix.BEAN_TYPE.SESSION, (IProject)marker.getResource()),
        		new CreateBeanQuickFix(CreateBeanQuickFix.BEAN_TYPE.MESSAGE_DRIVEN, (IProject)marker.getResource())
        };
    }

	public boolean hasResolutions(IMarker marker) {
		return true;
	}
	
}
