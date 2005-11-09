package org.eclipse.jst.j2ee.ejb.project.facet;



import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.jst.j2ee.project.facet.JavaUtilityComponentCreationDataModelProvider;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

public class EjbFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {

		if (monitor != null) {
			monitor.beginTask("", 1);
		}

		try {
			IDataModel model = (IDataModel) config;

			final IJavaProject jproj = JavaCore.create(project);

			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Create the directory structure.

			final IWorkspace ws = ResourcesPlugin.getWorkspace();
			final IPath pjpath = project.getFullPath();

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);

			// final ComponentType ctype = ComponentcoreFactory.eINSTANCE.createComponentType();
			//
			// ctype.setComponentTypeId(IModuleConstants.JST_EJB_MODULE);
			// ctype.setVersion(fv.getVersionString());
			c.setMetaProperty("java-output-path", "/build/classes/");

			// final StructureEdit edit = StructureEdit.getStructureEditForWrite(project);
			//
			// try {
			// StructureEdit.setComponentType(c, ctype);
			// edit.saveIfNecessary(null);
			// } finally {
			// edit.dispose();
			// }

			final IVirtualFolder ejbroot = c.getRootFolder();
			IFolder ejbFolder = null;
			String configFolder = null;
			if (ejbroot.getProjectRelativePath().segmentCount() == 0) {
				configFolder = model.getStringProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER);
				ejbroot.createLink(new Path("/" + configFolder), 0, null);
	
				String ejbFolderName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER);
				IPath ejbFolderpath = pjpath.append(ejbFolderName);

				ejbFolder = ws.getRoot().getFolder(ejbFolderpath);
			} else
				ejbFolder = project.getFolder(ejbroot.getProjectRelativePath());

			if (!ejbFolder.getFile(J2EEConstants.EJBJAR_DD_URI).exists()) {
				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
				int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				EJBArtifactEdit.createDeploymentDescriptor(project, nVer);
			}

			try {
				createManifest(project, ejbFolder, monitor);
			} catch (InvocationTargetException e) {
				Logger.getLogger().logError(e);
			} catch (InterruptedException e) {
				Logger.getLogger().logError(e);
			}

			// Setup the classpath.
			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}

			// Associate with an EAR, if necessary.

			final String earProjectName = (String) model.getProperty(IEjbFacetInstallDataModelProperties.EAR_PROJECT_NAME);
			if (earProjectName != null && !earProjectName.equals("")) {

				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);

				String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString(J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(ver));

				installEARFacet(j2eeVersionText, earProjectName, monitor);
			}

			// Create the Ejb Client View 
			final boolean createClient = model.getBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT);
			String clientProjectName = (String)model.getProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);
			if( createClient && clientProjectName != null && clientProjectName != ""){
				IProject ejbClientProject = ProjectUtilities.getProject( clientProjectName ); 
				if( ejbClientProject.exists())
					return;
					
				
				
				try{	
					IDataModel dm = DataModelFactory.createDataModel(new JavaUtilityComponentCreationDataModelProvider());
					dm.setProperty(JavaComponentCreationDataModelProvider.PROJECT_NAME,
							clientProjectName);
					dm.setProperty(JavaComponentCreationDataModelProvider.JAVASOURCE_FOLDER,
							model.getProperty(IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER));
					
					dm.setProperty(JavaComponentCreationDataModelProvider.RUNTIME_TARGET_ID,
							model.getProperty(IEjbFacetInstallDataModelProperties.RUNTIME_TARGET_ID));
					
					dm.getDefaultOperation().execute(monitor, null);
				}catch(ExecutionException e){
					Logger.getLogger().logError(e);
				}
			}
			if (monitor != null) {
				monitor.worked(1);
			}
		}

		finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}
}
