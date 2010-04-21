package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.wst.common.componentcore.internal.resources.AbstractResourceListVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * A stub 'dummy' reference which is returned on a getReferences() call to an Ear project
 * when passed the IVirtualComponent.DISPLAYABLE_REFERENCES type. 
 */
public class DummyClasspathDependencyContainerVirtualComponent extends AbstractResourceListVirtualComponent {
	public static final String COMPONENT_ID = "org.eclipse.jst.j2ee.componentcore.util.ClasspathDependencies"; //$NON-NLS-1$
	
	public DummyClasspathDependencyContainerVirtualComponent(IProject p,
			IVirtualComponent referencingComponent) {
		super(p, referencingComponent);
	}

	@Override
	protected String getFirstIdSegment() {
		return COMPONENT_ID;
	}

	@Override
	protected IContainer[] getUnderlyingContainers() {
		return new IContainer[]{};
	}

	@Override
	protected IResource[] getLooseResources() {
		return new IResource[]{};
	}
}
