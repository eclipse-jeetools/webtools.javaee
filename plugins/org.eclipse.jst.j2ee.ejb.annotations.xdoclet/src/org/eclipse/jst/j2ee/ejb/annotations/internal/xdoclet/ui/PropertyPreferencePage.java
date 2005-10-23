package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;

public abstract class PropertyPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, IWorkbenchPropertyPage {

	protected IProject fProject = null;
	protected XDocletPreferenceStore store = null;

	public IAdaptable getElement() {
		return fProject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPropertyPage#setElement(org.eclipse.core.runtime.IAdaptable)
	 */
	public void setElement(IAdaptable element) {
		fProject = (IProject) element.getAdapter(IResource.class);

	}

	public XDocletPreferenceStore getStore() {
		if (store == null) {
			store = XDocletPreferenceStore.forProject(fProject);
		}
		return store;
	}

}
