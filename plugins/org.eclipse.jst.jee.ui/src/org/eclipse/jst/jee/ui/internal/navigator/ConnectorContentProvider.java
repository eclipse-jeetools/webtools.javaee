package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.jee.ui.internal.navigator.ra.RaGroupContentProvider;

public class ConnectorContentProvider extends JEE5ContentProvider {

	public Object[] getChildren(Object aParentElement) {
		List<Object> children = new ArrayList<Object>();
		IProject project = null;
		if (IProject.class.isInstance(aParentElement)) {
			project = (IProject) aParentElement;
				AbstractGroupProvider cachedContentProvider = getCachedContentProvider(project);
				if (cachedContentProvider!= null && cachedContentProvider.isValid()){
					children.add(cachedContentProvider);
				}
		} else if (AbstractGroupProvider.class.isInstance(aParentElement)){
			AbstractGroupProvider abstractGroupProvider = (AbstractGroupProvider) aParentElement;
			if (abstractGroupProvider.hasChildren()){
				children.addAll(abstractGroupProvider.getChildren());
			}
		}
		return children.toArray();
	}

	public Object getParent(Object element) {
		return null;
	}

	public boolean hasChildren(Object element) {
		return true;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	protected AbstractGroupProvider getNewContentProviderInstance(IProject project) {
		return new RaGroupContentProvider((Connector)getCachedModelProvider(project).getModelObject(), project);
	}

}
