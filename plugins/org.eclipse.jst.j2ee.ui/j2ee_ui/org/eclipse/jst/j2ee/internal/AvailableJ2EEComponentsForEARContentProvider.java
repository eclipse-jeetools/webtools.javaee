/*
 * Created on Mar 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class AvailableJ2EEComponentsForEARContentProvider implements IStructuredContentProvider, ITableLabelProvider {
	private int j2eeVersion;
	private IVirtualComponent earComponent;

	public AvailableJ2EEComponentsForEARContentProvider(IVirtualComponent aEarComponent, int j2eeVersion) {
		super();
		this.j2eeVersion = j2eeVersion;
		earComponent = aEarComponent;
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
			if(ModuleCoreNature.isFlexibleProject(project)){
				IVirtualComponent component = ComponentCore.createComponent(project);
				if (J2EEProjectUtilities.isApplicationClientProject(project) ||
						J2EEProjectUtilities.isEJBProject(project) ||
						J2EEProjectUtilities.isDynamicWebProject(project) ||
						J2EEProjectUtilities.isJCAProject(project) ||
						J2EEProjectUtilities.isUtilityProject(project) ){
					int compJ2EEVersion = J2EEVersionUtil.convertVersionStringToInt(component);
					if( compJ2EEVersion <= j2eeVersion)
						//validCompList.add(component.getProject());
						validCompList.add(component);
				}else if(J2EEProjectUtilities.isEARProject(project)){
					//find the ArchiveComponent
					if( component.equals( earComponent )){
						IVirtualReference[] newrefs = component.getReferences();
						for( int k=0; k< newrefs.length; k++ ){
							IVirtualReference tmpref = newrefs[k];
							//IVirtualComponent enclosingcomp = tmpref.getEnclosingComponent();
							//boolean isBinary = enclosingcomp.isBinary();
							IVirtualComponent referencedcomp = tmpref.getReferencedComponent();		
							boolean isBinary = referencedcomp.isBinary();
							if( isBinary ){
								validCompList.add(referencedcomp);
								//validCompList.add(referencedcomp.getProject());
								//IPath path = ComponentUtilities.getResolvedPathForArchiveComponent(name);
							}	
						}	
					}
				}
			} else
				try {
					if (project.exists() && project.isAccessible() && project.hasNature("org.eclipse.jdt.core.javanature")){ //$NON-NLS-1$
						validCompList.add(project);
					}
				} catch (CoreException e) {
					Logger.getLogger().log(e);
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
		if (element instanceof IVirtualComponent) {
			IVirtualComponent comp = (IVirtualComponent)element;
			if( columnIndex == 0 )
				return comp.getName();
			if( columnIndex == 1  )
				return comp.getProject().getName();
		}else if(element instanceof IProject){
			return ((IProject)element).getName();
		}		
		return null;
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