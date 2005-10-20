package org.eclipse.jst.j2ee.internal.earcreation;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class EarFacetInstallOperation extends AbstractDataModelOperation{

	
	public EarFacetInstallOperation(IDataModel model) {
		super(model);
	}
	
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (monitor != null) {
			monitor.beginTask("", 1);
		}

		try {
			IProject project = ProjectUtilities.getProject(model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME));
			IProjectFacetVersion fv = (IProjectFacetVersion) model.getProperty(IFacetDataModelProperties.FACET_VERSION);
			
			if (monitor != null) {
				monitor.worked(1);
			}
			// Add WTP natures.

			WtpUtils.addNatures(project);
			
			final IVirtualComponent c = ComponentCore.createComponent(project);
			c.create(0, null);
			
			final IVirtualFolder ejbroot = c.getRootFolder();
			ejbroot.createLink(new Path("/"), 0, null);
			
			
			if (!project.getFile(J2EEConstants.APPLICATION_DD_URI).exists()) {
	    		String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
	    		int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				EARArtifactEdit.createDeploymentDescriptor( project, nVer );
			}
			
		}catch (CoreException e) {
			throw new ExecutionException(e.getMessage(), e);
		}

		finally {
			if (monitor != null) {
				monitor.done();
			}
		}
		return OK_STATUS;

	}

}
