/*
 * Created on Mar 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.jca.internal.deployables;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.server.core.IConnectorModule;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


public class ConnectorFlexibleDeployable extends J2EEFlexProjDeployable implements IConnectorModule {

	public static String CONNECTOR_TYPE = IModuleConstants.JST_CONNECTOR_MODULE;

	public ConnectorFlexibleDeployable(IProject project, String aFactoryId, IVirtualComponent aComponent) {
		super(project, aFactoryId, aComponent);

	}

	public String getJ2EESpecificationVersion() {
		if (component != null)
			return String.valueOf(J2EEVersionUtil.convertConnectorVersionStringToJ2EEVersionID(component.getVersion()));
		else
			return null;
	}
	
    /**
     * Returns the classpath as a list of absolute IPaths.
     * 
     * @param java.util.List
     */
    public IPath[] getClasspath() {
		List paths = new ArrayList();
        IJavaProject proj = JemProjectUtilities.getJavaProject(getProject());
        URL[] urls = JemProjectUtilities.getClasspathAsURLArray(proj);
		for (int i = 0; i < urls.length; i++) {
			URL url = urls[i];
			paths.add(Path.fromOSString(url.getPath()));
		}
        return  (IPath[]) paths.toArray(new IPath[paths.size()]);

    }


	public String getType() {
		return "j2ee.connector"; //$NON-NLS-1$
	}



	public IStatus validate(IProgressMonitor monitor) {
		return null;
	}


	public String getJ2CSpecificationVersion() {
		if (component != null)
			return component.getVersion();
		else
			return null;
	}

}
