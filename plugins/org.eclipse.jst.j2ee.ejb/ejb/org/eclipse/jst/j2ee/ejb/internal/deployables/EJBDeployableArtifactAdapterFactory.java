/*
 * Created on Jan 18, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.deployables;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.ILaunchable; 
import org.eclipse.wst.server.core.IModuleArtifact;

public class EJBDeployableArtifactAdapterFactory implements IAdapterFactory {

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		IModuleArtifact moduleArtifact = null;
		if (adapterType == IEJBModuleArtifact.class || adapterType == IModuleArtifact.class) {
			moduleArtifact = EJBDeployableArtifactAdapterUtil.getModuleObject(adaptableObject);
		}
		return moduleArtifact;
	}

	public Class[] getAdapterList() {
		return new Class[]{IEJBModuleArtifact.class, ILaunchable.class, IModuleArtifact.class};
	}

}
