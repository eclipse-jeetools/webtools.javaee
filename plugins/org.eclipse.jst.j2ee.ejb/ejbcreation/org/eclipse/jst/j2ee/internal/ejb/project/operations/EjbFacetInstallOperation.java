package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.EarUtil;
import org.eclipse.jst.j2ee.project.facet.IFacetDataModelPropeties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.classpath.ClasspathHelper;

public class EjbFacetInstallOperation
	extends AbstractDataModelOperation
	implements IDataModelOperation, IEjbFacetInstallDataModelProperties{

	
	
	public EjbFacetInstallOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (monitor != null) {
			monitor.beginTask("", 1);
		}

		try {
			IProject project = ProjectUtilities.getProject(model.getStringProperty(IEjbFacetInstallDataModelProperties.FACET_PROJECT_NAME));
			IProjectFacetVersion fv = (IProjectFacetVersion) model.getProperty(IEjbFacetInstallDataModelProperties.FACET_VERSION);
			
			final IJavaProject jproj = JavaCore.create(project);

			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Create the directory structure.

			final IWorkspace ws = ResourcesPlugin.getWorkspace();
			final IPath pjpath = project.getFullPath();

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);

//			final ComponentType ctype = ComponentcoreFactory.eINSTANCE.createComponentType();
//
//			ctype.setComponentTypeId(IModuleConstants.JST_EJB_MODULE);
//			ctype.setVersion(fv.getVersionString());
			c.setMetaProperty("java-output-path","/build/classes/");

//			final StructureEdit edit = StructureEdit.getStructureEditForWrite(project);
//
//			try {
//				StructureEdit.setComponentType(c, ctype);
//				edit.saveIfNecessary(null);
//			} finally {
//				edit.dispose();
//			}
        
			final IVirtualFolder ejbroot = c.getRootFolder();
			ejbroot.createLink(new Path("/" + model.getStringProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER)), 0, null);

			
			String ejbFolderName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER);
			IPath ejbFolderpath = pjpath.append(ejbFolderName);
			
			IFolder ejbFolder = ws.getRoot().getFolder(ejbFolderpath);
			
			if (!ejbFolder.getFile(J2EEConstants.EJBJAR_DD_URI).exists()) {
				String ver = model.getStringProperty(IFacetDataModelPropeties.FACET_VERSION_STR);
	    		int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				EJBArtifactEdit.createDeploymentDescriptor(project,nVer);
			}

			// Setup the classpath.
			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}


			// Associate with an EAR, if necessary.

			final String earProjectName = model.getStringProperty(IEjbFacetInstallDataModelProperties.EAR_PROJECT_NAME);

			if (earProjectName != null && !earProjectName.equals("")) {
				final IProject earproj = ws.getRoot().getProject(earProjectName);

				final EPackage.Registry registry = EPackage.Registry.INSTANCE;

				final EPackage pack = registry.getEPackage(ApplicationPackage.eNS_URI);

				final ApplicationFactory appfact = ((ApplicationPackage) pack).getApplicationFactory();

				final EjbModule m = appfact.createEjbModule();
				EarUtil.addModuleReference(earproj, project, m);
			}

			if (monitor != null) {
				monitor.worked(1);
			}
		} catch (CoreException e) {
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
