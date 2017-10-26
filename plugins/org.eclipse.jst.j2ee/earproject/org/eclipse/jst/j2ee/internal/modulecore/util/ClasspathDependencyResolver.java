package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentcorePackage;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resolvers.IReferenceResolver;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * This may never be used and may be a candidate for deletion, primarily
 * because this reference type is not persisted in the component xml 
 * anywhere inside the jeetools codebase. 
 */
public class ClasspathDependencyResolver implements IReferenceResolver {

	@Override
	public boolean canResolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		URI handle = referencedComponent.getHandle();
		String s = handle.toString();
		if (s.startsWith(DummyClasspathDependencyContainerVirtualComponent.COMPONENT_ID))
			return true;
		return false;
	}

	@Override
	public IVirtualReference resolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		IProject p = context.getProject();
		DummyClasspathDependencyContainerVirtualComponent comp = new DummyClasspathDependencyContainerVirtualComponent(
				p, context);
		IVirtualReference ref = ComponentCore.createReference(context, comp);
		ref.setArchiveName(referencedComponent.getArchiveName());
		ref.setRuntimePath(referencedComponent.getRuntimePath());
		ref.setDependencyType(referencedComponent.getDependencyType()
				.getValue());
		return ref;
	}

	@Override
	public boolean canResolve(IVirtualReference reference) {
		IVirtualComponent vc = reference.getReferencedComponent();
		if (vc instanceof DummyClasspathDependencyContainerVirtualComponent)
			return true;
		return false;
	}

	@Override
	public ReferencedComponent resolve(IVirtualReference reference) {
		IVirtualComponent vc = reference.getReferencedComponent();
		DummyClasspathDependencyContainerVirtualComponent comp = (DummyClasspathDependencyContainerVirtualComponent)vc;
		ReferencedComponent rc = ComponentcorePackage.eINSTANCE.getComponentcoreFactory().createReferencedComponent();
		rc.setArchiveName(reference.getArchiveName());
		rc.setRuntimePath(reference.getRuntimePath());
		rc.setHandle(URI.createURI(comp.getId()));
		// Manually set consumes!
		rc.setDependencyType(DependencyType.CONSUMES_LITERAL);
		return rc;
	}
}
