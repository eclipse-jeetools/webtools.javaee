/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.Platform;
import org.eclipse.debug.ui.actions.ILaunchable;
import org.eclipse.wst.server.core.IModuleArtifact;


public class J2EEDeployableAdapterFactory implements IAdapterFactory {
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		IModuleArtifact moduleArtifact = null;
		if (adapterType == IModuleArtifact.class ) {
			
			if (moduleArtifact == null && Platform.getAdapterManager().hasAdapter(adaptableObject, "org.eclipse.jst.j2ee.internal.web.deployables.WebModuleArtifact")) {
				moduleArtifact = (IModuleArtifact) Platform.getAdapterManager().loadAdapter(adaptableObject, "org.eclipse.jst.j2ee.internal.web.deployables.WebModuleArtifact");
			}
			if (moduleArtifact == null && Platform.getAdapterManager().hasAdapter(adaptableObject, "org.eclipse.jst.j2ee.ejb.internal.deployables.IEJBModuleArtifact")) {
				moduleArtifact = (IModuleArtifact) Platform.getAdapterManager().loadAdapter(adaptableObject, "org.eclipse.jst.j2ee.ejb.internal.deployables.IEJBModuleArtifact");
			}
			if (moduleArtifact == null && Platform.getAdapterManager().hasAdapter(adaptableObject, "org.eclipse.jst.j2ee.internal.deployables.EnterpriseModuleArtifact")) {
				moduleArtifact = (IModuleArtifact) Platform.getAdapterManager().loadAdapter(adaptableObject, "org.eclipse.jst.j2ee.internal.deployables.EnterpriseModuleArtifact");
			}
			if (moduleArtifact == null && Platform.getAdapterManager().hasAdapter(adaptableObject, "org.eclipse.wst.web.internal.deployables.IStaticWebModuleArtifact")) {
				moduleArtifact = (IModuleArtifact) Platform.getAdapterManager().loadAdapter(adaptableObject, "org.eclipse.wst.web.internal.deployables.IStaticWebModuleArtifact");
			}
		}
		return moduleArtifact;
	}

	public Class[] getAdapterList() {
		return new Class[]{IModuleArtifact.class, ILaunchable.class};
	}


}
