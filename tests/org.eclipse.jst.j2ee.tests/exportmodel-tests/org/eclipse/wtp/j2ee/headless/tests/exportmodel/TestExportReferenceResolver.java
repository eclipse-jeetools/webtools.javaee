package org.eclipse.wtp.j2ee.headless.tests.exportmodel;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentcorePackage;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.impl.PlatformURLModuleConnection;
import org.eclipse.wst.common.componentcore.resolvers.IReferenceResolver;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class TestExportReferenceResolver implements IReferenceResolver {
	private static final String FIRST_SEGMENT = "org.eclipse.wtp.j2ee.exportTestResolver";
	public static final String PROTOCOL = PlatformURLModuleConnection.MODULE_PROTOCOL
		+IPath.SEPARATOR + FIRST_SEGMENT + IPath.SEPARATOR;

	public boolean canResolve(IVirtualReference reference) {
		if( reference.getReferencedComponent() 
				instanceof TestExportVirtualComponent )
			return true;
		return false;
	}

	public ReferencedComponent resolve(IVirtualReference reference) {
		TestExportVirtualComponent vc = (TestExportVirtualComponent)reference.getReferencedComponent();
        ReferencedComponent rc = ComponentcorePackage.eINSTANCE.getComponentcoreFactory().createReferencedComponent();
        rc.setArchiveName(reference.getArchiveName());
        rc.setRuntimePath(reference.getRuntimePath());
        rc.setHandle(URI.createURI(PROTOCOL + vc.getFullPath().toString()));
        rc.setDependencyType(DependencyType.CONSUMES_LITERAL);
        return rc;
	}

	public boolean canResolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		URI uri = referencedComponent.getHandle();
		if( uri.segmentCount() >= 1 && FIRST_SEGMENT.equals(uri.segment(0))) 
			return true;
		return false;
	}

	public IVirtualReference resolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		URI uri = referencedComponent.getHandle();
		if( uri.segmentCount() >= 1 && FIRST_SEGMENT.equals(uri.segment(0))) {
			String path = uri.toString().substring(PROTOCOL.length());
			IPath filePath = new Path(path);
			IProject proj = ResourcesPlugin.getWorkspace().getRoot().getProject(filePath.segment(0));
			IFile f = proj.getFile(filePath.removeFirstSegments(1));
            IVirtualReference ref = ComponentCore.createReference(context, 
            		new TestExportVirtualComponent(f));
            ref.setArchiveName(referencedComponent.getArchiveName());
            ref.setRuntimePath(referencedComponent.getRuntimePath());
            ref.setDependencyType(referencedComponent.getDependencyType().getValue());
            return ref;
		}
		return null;
	}

}
