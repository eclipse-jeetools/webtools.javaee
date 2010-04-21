package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.jst.j2ee.internal.modulecore.util.DummyClasspathDependencyContainerVirtualComponent;
import org.eclipse.jst.j2ee.internal.wizard.AvailableJarsProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.IVirtualComponentLabelProvider;

public class ClasspathDependencyComponentLabelProvider implements
		IVirtualComponentLabelProvider {

	public ClasspathDependencyComponentLabelProvider() {
		// Do nothing
	}

	public boolean canHandle(IVirtualComponent component) {
		if( component instanceof DummyClasspathDependencyContainerVirtualComponent)
			return true;
		return false;
	}

	public String getSourceText(IVirtualComponent component) {
		return Messages.ChildClasspathDependencyDescription;
	}

	public Image getSourceImage(IVirtualComponent component) {
		return AvailableJarsProvider.getClasspathDependencyImage();
	}
	public void dispose() {
		//  Do nothing
	}
}
