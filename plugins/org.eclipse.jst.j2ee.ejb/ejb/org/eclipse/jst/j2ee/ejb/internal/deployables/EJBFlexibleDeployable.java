/*
 * Created on Mar 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.server.core.IEJBModule;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;


public class EJBFlexibleDeployable extends J2EEFlexProjDeployable implements IEJBModule {

	public static String EJB_TYPE = IModuleConstants.JST_EJB_MODULE;

	public EJBFlexibleDeployable(IProject project, String aFactoryId, IVirtualComponent aComponent) {
		super(project, aFactoryId, aComponent);


	}

	public String getJ2EESpecificationVersion() {
		if (component != null)
			return String.valueOf(J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(component.getVersion()));
		else
			return null;
	}


	public String getJNDIName(String ejbName) {
		EjbModuleExtensionHelper modHelper = null;
		EJBJar jar = null;
		EJBArtifactEdit ejbEdit = null;
		try {
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(component);
			if (ejbEdit != null) {
				jar = ejbEdit.getEJBJar();
				modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}


		return modHelper == null ? null : modHelper.getJNDIName(jar, jar.getEnterpriseBeanNamed(ejbName));

	}

	public String getType() {
		return "j2ee.ejb"; //$NON-NLS-1$
	}



	public IStatus validate(IProgressMonitor monitor) {
		return null;
	}


	public String getEJBSpecificationVersion() {
		if (component != null)
			return component.getVersion();
		else
			return null;
	}

}
