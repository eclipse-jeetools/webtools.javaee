package org.eclipse.jst.j2ee.internal.web.migration;

import java.util.ArrayList;

import org.eclipse.core.internal.resources.Container;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.web.operations.OldWebSettingsForMigration;
import org.eclipse.wst.common.internal.migration.IMigratorStrategy;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.Property;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;



public class WebProjectMigratorStrategy implements IMigratorStrategy {

	protected IProject project;
	protected static String JAVA_SOURCE = "JavaSource";
	protected static String CONTEXT_ROOT = "ContextRoot";
	protected static String JSP_LEVEL = "JSPLevel";
	protected static String FEATURE_ID = "FeatureID";
	protected static String JAVA_SOURCE_DEPLOY_PATH_NAME = "/WEB-INF/classes";
	protected static String WEB_DEPLOYMENT_DESCRIPTOR_PATH = "/WEB-INF/web.xml";

	protected OldWebSettingsForMigration fWebSettings;
	int fVersion;



	public IJavaProject getJavaProject() {
		IJavaProject javaProject = JavaCore.create(project);
		return javaProject;
	}

	public IPackageFragmentRoot[] getPackageRoots() {
		try {
			return getJavaProject().getAllPackageFragmentRoots();
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		return null;

	}

	public IResource[] getResources(String componentName, int type) {
		IResource[] resources = null;
		switch (type) {
			case IMigratorStrategy.SOURCE : {
				IPackageFragmentRoot[] roots = getPackageRoots();
				ArrayList sourceFolders = new ArrayList(roots.length);
				for (int i = 0; i < roots.length; i++) {
					IResource packageResource;
					try {
						packageResource = (roots[i].getKind() == IPackageFragmentRoot.K_SOURCE) ? roots[i].getCorrespondingResource() : null;
						if (packageResource != null && packageResource.getType() == IResource.FOLDER)
							sourceFolders.add(packageResource);
					} catch (JavaModelException e) {
						e.printStackTrace();
					}
				}
				resources = new Container[sourceFolders.size()];
				sourceFolders.toArray(resources);
				break;
			}
			case IMigratorStrategy.CONTENT : {
				resources = new Container[1];
				Container webContent = (Container) project.getFolder(getBasicWebModulePath());
				if (webContent != null)
					resources[0] = (Container) webContent;
				break;
			}
		};

		return resources;
	}

	public OldWebSettingsForMigration getWebSettings() {
		if (fWebSettings == null) {
			fWebSettings = new OldWebSettingsForMigration(getProject());
		}
		return fWebSettings;
	}

	public IPath getRuntimeType(IResource resource, int type) {
		if (resource.getName().equals(JAVA_SOURCE))
			return (new Path(JAVA_SOURCE_DEPLOY_PATH_NAME));
		return new Path("/");
	}

	public String[] getComponentNames() {
		return new String[]{project.getName()};
	}


	public void setCurrentProject(IProject aProject) {
		project = aProject;
	}

	public IProject getProject() {
		return project;
	}

	public IPath getBasicWebModulePath() {
		OldWebSettingsForMigration webSettings = getWebSettings();
		String name = webSettings.getWebContentName();
		if (name == null) {
			int version = getVersion();
			// If created in V5 or beyond
			if (version != -1 && version >= 500)
				return IWebNatureConstants.WEB_MODULE_PATH_;
			else
				return IWebNatureConstants.WEB_MODULE_PATH_V4;
		}
		return new Path(name);
	}

	public int getVersion() {
		if (fVersion == -1) {
			try {
				String versionString = getWebSettings().getVersion();
				if (versionString != null)
					fVersion = Integer.parseInt(versionString);
			} catch (NumberFormatException e) {
			}
		}
		return fVersion;
	}


	public IResource[] getExcludedResources(String componentName, int type) {
		return null;
	}

	public String getComponentTypeName(String componentName) {
		return IModuleConstants.JST_WEB_MODULE;
	}

	public String getComponetTypeVersion(String componentName) {
        return J2EEVersionUtil.getJ2EETextVersion(getJ2EEVersion());
	}

	public Property[] getProperties(String componentName) {
		String contextRootName = getWebSettings().getContextRoot();
		String jspLevel = getWebSettings().getJSPLevel();
		String[] featureIDs = getWebSettings().getFeatureIds();
		Property[] properties = new Property[featureIDs.length + 2];
		properties[0] = createProperty(CONTEXT_ROOT, contextRootName);
		properties[1] = createProperty(JSP_LEVEL, jspLevel);
		for (int i = 2; i < featureIDs.length + 2; i++) {
			properties[i] = createProperty(FEATURE_ID + "_" + (i - 1), featureIDs[i - 2]);

		}
		return properties;
	}

	public Property createProperty(String name, String value) {
		Property property = ModuleCoreFactory.eINSTANCE.createProperty();
		property.setName(name);
		property.setValue(value);
		return property;
	}
	
	
	public int getJ2EEVersion() {
		int j2eeVersion;
		switch (getModuleVersion()) {
			case J2EEVersionConstants.WEB_2_2_ID :
				j2eeVersion = J2EEVersionConstants.J2EE_1_2_ID;
				break;
			case J2EEVersionConstants.WEB_2_3_ID :
				j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
				break;
			default :
				j2eeVersion = J2EEVersionConstants.J2EE_1_4_ID;
		}
		return j2eeVersion;
	}


	/**
	 * @return
	 */
	private int getModuleVersion() {
		return getWebSettings().getModuleVersion();
	}

	public void postMigrateStrategy() {
	}
}
