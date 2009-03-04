package org.eclipse.jst.jee.ui.internal.navigator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;

public class DDAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == IJavaElement.class) {
			if (adaptableObject instanceof AbstractDDNode) {
				AbstractDDNode ddNode = (AbstractDDNode) adaptableObject;
				Object node = ddNode.getAdapterNode();
				if (node instanceof IJavaElement) 
					return (IJavaElement) node;
				if (node instanceof IAdaptable) 
					return (IJavaElement) ((IAdaptable) node).getAdapter(IJavaElement.class);
					
				return (IJavaElement) Platform.getAdapterManager().getAdapter(node, IJavaElement.class);
			}
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[] { IJavaElement.class };
	}

}
