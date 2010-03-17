package org.eclipse.jst.j2ee.componentcore.util;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.wst.common.componentcore.internal.resources.AbstractResourceListVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * Initially this component will have no elements.
 * A reference to this element will be provided with 
 * new EAR projects to be able to 'edit' the elements inside 
 * the children that may be eligible to be 'pulled in' to the ear. 
 * 
 * Ideally this would actually provide the resources, as well, but
 * IVirtualFile does not have a method to transform to java.io.File. 
 */
public class ClasspathDependencyContainerVirtualComponent extends AbstractResourceListVirtualComponent {
	public static final String COMPONENT_ID = "org.eclipse.jst.j2ee.componentcore.util.ClasspathDependencies"; //$NON-NLS-1$
	
	public ClasspathDependencyContainerVirtualComponent(IProject p,
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
