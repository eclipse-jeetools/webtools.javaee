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
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.modulecore.ComponentType;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

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
			ModuleCore moduleCore = null;
			try {
				if (!project.hasNature(IModuleConstants.MODULE_NATURE_ID)) 
					continue;
				// get all J2EE module in the project
				moduleCore = ModuleCore.getModuleCoreForRead(project);
				moduleCore.prepareProjectModulesIfNecessary(); 
				WorkbenchComponent[] appClientComps = moduleCore.findWorkbenchModuleByType(IModuleConstants.JST_APPCLIENT_MODULE);
				for (int j = 0; j < appClientComps.length; j++) {
					String version = appClientComps[j].getComponentType().getVersion();
					int versionID = convertAppClientVersionStringToJ2EEVersionID(version);
					if (versionID < j2eeVersion)
						validCompList.add(appClientComps[j]);
				}
				WorkbenchComponent[] ejbComps = moduleCore.findWorkbenchModuleByType(IModuleConstants.JST_EJB_MODULE);
				for (int j = 0; j < ejbComps.length; j++) {
					String version = ejbComps[j].getComponentType().getVersion();
					int versionID = convertEJBVersionStringToJ2EEVersionID(version);
					if (versionID < j2eeVersion)
						validCompList.add(ejbComps[j]);
				}
				WorkbenchComponent[] webComps = moduleCore.findWorkbenchModuleByType(IModuleConstants.JST_WEB_MODULE);
				for (int j = 0; j < webComps.length; j++) {
					String version = webComps[j].getComponentType().getVersion();
					int versionID = convertWebVersionStringToJ2EEVersionID(version);
//					if (versionID < j2eeVersion)
						validCompList.add(webComps[j]);
				}
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if (moduleCore != null)
					moduleCore.dispose();
			}			
		}
		return validCompList.toArray();
	}
	
	private boolean isValidComponentForEAR(WorkbenchComponent comp) {
		ComponentType type = comp.getComponentType();
		String id = type.getModuleTypeId();
		String version = type.getVersion();
		int versionID = convertAppClientVersionStringToJ2EEVersionID(version);
		if (IModuleConstants.JST_APPCLIENT_MODULE.equals(id) && versionID < j2eeVersion)
			return true;
		if (IModuleConstants.JST_EJB_MODULE.equals(id) && versionID < j2eeVersion)
			return true;
		if (IModuleConstants.JST_WEB_MODULE.equals(id) && versionID < j2eeVersion)
			return true;
		if (IModuleConstants.JST_CONNECTOR_MODULE.equals(id) && versionID < j2eeVersion)
			return true;
		return false;
	}
	
	private int convertAppClientVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_1_2_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_3_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_4_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		return Integer.MAX_VALUE;
	}
	
	private int convertEJBVersionStringToJ2EEVersionID(String version) {		
		if (version.equals(J2EEVersionConstants.VERSION_1_1_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_0_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_1_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		return Integer.MAX_VALUE;
	}
	
	private int convertWebVersionStringToJ2EEVersionID(String version) {		
		// Web module
		if (version.equals(J2EEVersionConstants.VERSION_2_2_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_3_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_4_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		return Integer.MAX_VALUE;
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
		return ((IProject) element).getName();
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