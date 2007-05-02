package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectResourceSet;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.web.internal.util.WebResourceImpl;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class Web25ModelProvider implements IModelProvider {
	
	private IProject proj;

	public Web25ModelProvider(IProject proj) {
		super();
		this.proj = proj;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject()
	 */
	public Object getModelObject() {
		ProjectResourceSet resSet = getResourceSet(proj);
		
		IVirtualFolder container = ComponentCore.createComponent(proj).getRootFolder();
		String modelPathURI = J2EEConstants.WEBAPP_DD_URI;
		URI uri = URI.createURI(container.getProjectRelativePath().toString() + "/" + modelPathURI); //$NON-NLS-1$
		
		WebResourceImpl webRes = (WebResourceImpl) resSet.getResource(uri,true);
		
		if (webRes != null) 
			return webRes.getWebApp();
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

}
