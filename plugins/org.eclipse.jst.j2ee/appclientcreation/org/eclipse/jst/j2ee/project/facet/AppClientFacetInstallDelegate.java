package org.eclipse.jst.j2ee.project.facet;

import java.lang.reflect.InvocationTargetException;

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
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.classpath.ClasspathHelper;

public class AppClientFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

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
			c.setMetaProperty("java-output-path", "/build/classes/");



			final IVirtualFolder root = c.getRootFolder();
			String configFolder = model.getStringProperty(IJ2EEFacetInstallDataModelProperties.CONFIG_FOLDER);
			root.createLink(new Path("/" + configFolder), 0, null);


			String configFolderName = model.getStringProperty(IJ2EEFacetInstallDataModelProperties.CONFIG_FOLDER);
			IPath configFolderpath = pjpath.append(configFolderName);

			IFolder configIFolder = ws.getRoot().getFolder(configFolderpath);

			if (!configIFolder.getFile(J2EEConstants.APP_CLIENT_DD_URI).exists()) {
				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
				int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				AppClientArtifactEdit.createDeploymentDescriptor(project, nVer);
			}

			try {
				createManifest(project, configFolder, monitor);
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

			final String earProjectName = (String) model.getProperty(IJ2EEFacetInstallDataModelProperties.EAR_PROJECT_NAME);
			if (earProjectName != null && !earProjectName.equals("")) {

				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);

				String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString(J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(ver));

				installEARFacet(j2eeVersionText, earProjectName, monitor);
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
