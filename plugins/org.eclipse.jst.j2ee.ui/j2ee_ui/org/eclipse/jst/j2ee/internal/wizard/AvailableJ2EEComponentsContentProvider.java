/*
 * Created on Mar 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class AvailableJ2EEComponentsContentProvider implements IStructuredContentProvider, ITableLabelProvider {
	private int j2eeVersion;

	public AvailableJ2EEComponentsContentProvider(int j2eeVersion) {
		super();
		this.j2eeVersion = j2eeVersion;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		Object[] empty = new Object[0];
		if (!(inputElement instanceof IWorkspaceRoot))
			return empty;
		IProject[] projects = ((IWorkspaceRoot) inputElement).getProjects();
		if (projects == null || projects.length == 0)
			return empty;
		List validCompList = new ArrayList();
		for (int i = 0; i < projects.length; i++) {
			// get flexible project
			IProject project = projects[i];
			IFlexibleProject flexProj = ComponentCore.createFlexibleProject(project);
			IVirtualComponent[] comps = flexProj.getComponents();
			for (int j = 0; j < comps.length; j++) {
				IVirtualComponent component = comps[j];
				String compType = component.getComponentTypeId();
				if ((compType.equals(IModuleConstants.JST_APPCLIENT_MODULE)) ||
						(compType.equals(IModuleConstants.JST_EJB_MODULE)) ||
						(compType.equals(IModuleConstants.JST_WEB_MODULE)) ||
						(compType.equals(IModuleConstants.JST_CONNECTOR_MODULE)))
					validCompList.add(component);
			}
		}
		return validCompList.toArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
	 */
	public String getColumnText(Object element, int columnIndex) {
		return ((IVirtualComponent) element).getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		//do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void addListener(ILabelProviderListener listener) {
		//do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
	 *      java.lang.String)
	 */
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
	 */
	public void removeListener(ILabelProviderListener listener) {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}
}