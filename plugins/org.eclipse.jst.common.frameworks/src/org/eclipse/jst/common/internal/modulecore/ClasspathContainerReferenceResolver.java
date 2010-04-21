/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.componentcore.internal.ComponentcorePackage;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.impl.PlatformURLModuleConnection;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualReference;
import org.eclipse.wst.common.componentcore.resolvers.IReferenceResolver;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class ClasspathContainerReferenceResolver implements IReferenceResolver {
	public static final String PROTOCOL = PlatformURLModuleConnection.MODULE_PROTOCOL
		+IPath.SEPARATOR + ClasspathContainerVirtualComponent.CLASSPATH_CON + IPath.SEPARATOR;
	public boolean canResolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		URI uri = referencedComponent.getHandle();
		if( uri.segmentCount() > 2 && uri.segment(0).equals(ClasspathContainerVirtualComponent.CLASSPATH)
				&& uri.segment(1).equals(ClasspathContainerVirtualComponent.CON))
			return true;
		return false;
	}

	public IVirtualReference resolve(IVirtualComponent context,
			ReferencedComponent referencedComponent) {
		URI uri = referencedComponent.getHandle();
		if( uri.segmentCount() > 2 && uri.segment(0).equals(ClasspathContainerVirtualComponent.CLASSPATH)
				&& uri.segment(1).equals(ClasspathContainerVirtualComponent.CON)) {
			String path = uri.toString().substring(PROTOCOL.length());
            IVirtualReference ref = new VirtualReference(context, 
            		new ClasspathContainerVirtualComponent(context.getProject(), context, path));
            ref.setArchiveName(referencedComponent.getArchiveName());
            ref.setRuntimePath(referencedComponent.getRuntimePath());
            ref.setDependencyType(referencedComponent.getDependencyType().getValue());
            return ref;
		}
		return null;
	}

	public boolean canResolve(IVirtualReference reference) {
		if( reference.getReferencedComponent() instanceof ClasspathContainerVirtualComponent )
			return true;
		return false;
	}

	public ReferencedComponent resolve(IVirtualReference reference) {
		ClasspathContainerVirtualComponent vc = (ClasspathContainerVirtualComponent)reference.getReferencedComponent();
        ReferencedComponent rc = ComponentcorePackage.eINSTANCE.getComponentcoreFactory().createReferencedComponent();
        rc.setArchiveName(reference.getArchiveName());
        rc.setRuntimePath(reference.getRuntimePath());
        rc.setHandle(URI.createURI(PROTOCOL + vc.getClasspathContainerPath()));
        rc.setDependencyType(DependencyType.CONSUMES_LITERAL);
        return rc;
	}

}
