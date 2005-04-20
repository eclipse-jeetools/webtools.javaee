/*
 * Created on Mar 28, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.server.core.IEJBModule;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;


public class EJBFlexibleDeployable extends J2EEFlexProjDeployable implements IEJBModule {

	public static String EJB_TYPE = "jst.ejb";

	public EJBFlexibleDeployable(IProject project, String aFactoryId, WorkbenchComponent aWorkbenchModule) {
		super(project, aFactoryId, aWorkbenchModule);


	}

	public String getJ2EESpecificationVersion() {
		String Version = "1.2"; //$NON-NLS-1$

		EJBArtifactEdit ejbEdit = null;
		try {
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(wbModule);
			if (ejbEdit != null) {
				int nVersion = ejbEdit.getJ2EEVersion();
				switch (nVersion) {
					case 12 :
						Version = IModuleConstants.J2EE_VERSION_1_2;
						break;
					case 13 :
						Version = IModuleConstants.J2EE_VERSION_1_3;
						break;
					case 14 :
						Version = IModuleConstants.J2EE_VERSION_1_4;
						break;
					default :
						Version = IModuleConstants.J2EE_VERSION_1_2;
						break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}

		return Version;
	}


	public String getJNDIName(String ejbName) {
		EjbModuleExtensionHelper modHelper = null;
		EJBJar jar = null;
		EJBArtifactEdit ejbEdit = null;
		try {
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(wbModule);
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

	public IPath getRootFolder() {

		IProject project = StructureEdit.getContainingProject(wbModule);
		Path path = new Path(wbModule.getName());
		if (project == null)
			return null;
		else
			return project.getFullPath();
	}

	public String getType() {
		return "j2ee.ejb"; //$NON-NLS-1$
	}



	public IStatus validate(IProgressMonitor monitor) {
		return null;
	}


	public String getEJBSpecificationVersion() {
		EJBArtifactEdit ejbEdit = null;
		EJBJar jar = null;
		try {
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(wbModule);
			if (ejbEdit != null) {
				jar = ejbEdit.getEJBJar();
				return jar.getVersion();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}

		return null;
	}

}
