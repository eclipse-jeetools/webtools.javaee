package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.internal.util.ApplicationResourceImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EAR5ModelProvider implements IEARModelProvider {
	
	private IProject proj;

	public EAR5ModelProvider(IProject proj) {
		super();
		this.proj = proj;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject()
	 */
	public Object getModelObject() {
		ProjectResourceSet resSet = getResourceSet(proj);
		
		IVirtualFolder container = ComponentCore.createComponent(proj).getRootFolder();
		String modelPathURI = J2EEConstants.APPLICATION_DD_URI;
		URI uri = URI.createURI(container.getProjectRelativePath().toString() + "/" + modelPathURI);
		
		ApplicationResourceImpl earRes = (ApplicationResourceImpl) resSet.getResource(uri,true);
		
		if (earRes.getContents().size() > 0) 
			return earRes.getApplication();
		return null;
	}

	private ProjectResourceSet getResourceSet(IProject proj2) {
		return (ProjectResourceSet)WorkbenchResourceHelperBase.getResourceSet(proj);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	public Object getModelObject(IPath modelPath) {
		// TODO Need to implement
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#modify(java.lang.Runnable, org.eclipse.core.runtime.IPath)
	 */
	public void modify(Runnable runnable, IPath modelPath) {
		//About to modify and save this model
		
		// call validateEdit()
		// access model  (write count)
		// run runnable
		// save model
		// release access count
		
	}

	public String getModuleURI(IVirtualComponent moduleComp) {
		IVirtualComponent comp = ComponentCore.createComponent(proj);
		IVirtualReference [] refs = comp.getReferences();
		for(int i=0; i<refs.length; i++){
			if(refs[i].getReferencedComponent().equals(moduleComp)){
				return refs[i].getArchiveName();
			}
		}
		return null;		
	}
	/**
	 * This method will return the context root in this application for the passed in web project.
	 * 
	 * @param webProject
	 * @return contextRoot String
	 */
	public String getWebContextRoot(IProject webProject) {
		if (webProject == null || !J2EEProjectUtilities.isDynamicWebProject(webProject))
			return null;
		IVirtualComponent webComp = ComponentCore.createComponent(webProject);
		String webModuleURI = getModuleURI(webComp);
		if (webModuleURI != null) {
			WebModule webModule = (WebModule) ((Application)getModelObject()).getModule(webModuleURI, null);
			if (webModule != null)
				return webModule.getContextRoot();
		}
		return null;
	}

}
