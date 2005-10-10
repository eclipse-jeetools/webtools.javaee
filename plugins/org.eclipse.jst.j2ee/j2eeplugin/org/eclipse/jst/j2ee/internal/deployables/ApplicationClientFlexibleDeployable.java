/*
 * Created on Mar 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.server.core.IApplicationClientModule;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


public class ApplicationClientFlexibleDeployable extends J2EEFlexProjDeployable implements IApplicationClientModule {
	
	public ApplicationClientFlexibleDeployable(IProject project, String aFactoryId, IVirtualComponent aComponent) {
		super(project, aFactoryId, aComponent);
	}

	public String getJ2EESpecificationVersion() {
		if (component != null)
			return J2EEVersionUtil.convertVersionIntToString(J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(component.getVersion()));
		return null;
	}
	
	public String getType() {
		return "j2ee.appclient"; //$NON-NLS-1$
	}
}
