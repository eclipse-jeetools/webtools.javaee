package org.eclipse.jst.j2ee.componentcore.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentcorePackage;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resolvers.IReferenceResolver;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class ClasspathDependencyResolver implements IReferenceResolver {

	public boolean canResolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		URI handle = referencedComponent.getHandle();
		String s = handle.toString();
		if (s.startsWith(ClasspathDependencyContainerVirtualComponent.COMPONENT_ID))
			return true;
		return false;
	}

	public IVirtualReference resolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		IProject p = context.getProject();
		ClasspathDependencyContainerVirtualComponent comp = new ClasspathDependencyContainerVirtualComponent(
				p, context);
		IVirtualReference ref = ComponentCore.createReference(context, comp);
		ref.setArchiveName(referencedComponent.getArchiveName());
		ref.setRuntimePath(referencedComponent.getRuntimePath());
		ref.setDependencyType(referencedComponent.getDependencyType()
				.getValue());
		return ref;
	}

	public boolean canResolve(IVirtualReference reference) {
		IVirtualComponent vc = reference.getReferencedComponent();
		if (vc instanceof ClasspathDependencyContainerVirtualComponent)
			return true;
		return false;
	}

	public ReferencedComponent resolve(IVirtualReference reference) {
		IVirtualComponent vc = reference.getReferencedComponent();
		ClasspathDependencyContainerVirtualComponent comp = (ClasspathDependencyContainerVirtualComponent)vc;
		ReferencedComponent rc = ComponentcorePackage.eINSTANCE.getComponentcoreFactory().createReferencedComponent();
		rc.setArchiveName(reference.getArchiveName());
		rc.setRuntimePath(reference.getRuntimePath());
		rc.setHandle(URI.createURI(comp.getId()));
		// Manually set consumes!
		rc.setDependencyType(DependencyType.CONSUMES_LITERAL);
		return rc;
	}
}
