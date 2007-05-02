package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderFactory;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class Web25ModelProviderFactory implements IModelProviderFactory {

	public IModelProvider create(IProject project) {
		return new Web25ModelProvider(project);
	}

	public IModelProvider create(IVirtualComponent component) {
		return new Web25ModelProvider(component.getProject());
	}

}
