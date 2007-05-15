package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.javaee.applicationclient.internal.util.ApplicationclientResourceImpl;

public class AppClient5ModelProvider extends JEE5ModelProvider {
	
	public AppClient5ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.APP_CLIENT_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	public Object getModelObject(IPath modelPath) {
		ApplicationclientResourceImpl appRes = (ApplicationclientResourceImpl)getModelResource(modelPath);
		if (appRes.getContents().size() > 0) 
			return appRes.getApplicationClient();
		return null;
	}


}
