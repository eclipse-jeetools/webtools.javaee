package org.eclipse.jst.j2ee.internal.ejb.migration;

import java.util.ArrayList;

import org.eclipse.core.internal.resources.Container;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EESettings;
import org.eclipse.wst.common.internal.migration.IMigratorStrategy;
import org.eclipse.wst.common.modulecore.DependencyType;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.Property;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;



public class EJBProjectMigratorStrategy implements IMigratorStrategy {

	protected IProject project;
	protected static String JAVA_SOURCE = "ejbModule";
	protected static String JAVA_SOURCE_DEPLOY_PATH_NAME = JAVA_SOURCE;
	protected static String EJB_DEPLOYMENT_DESCRIPTOR_PATH = "/" + JAVA_SOURCE + "/META-INF/ejb-jar.xml";


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
				break;
			}
		};

		return resources;
	}



	public IPath getRuntimeType(IResource resource, int type) {
		if (resource.getName().equals(JAVA_SOURCE))
			return (new Path(JAVA_SOURCE_DEPLOY_PATH_NAME));
		return new Path("/");
	}

	public String[] getComponentNames() {
		IFile file = getProject().getFile(EJB_DEPLOYMENT_DESCRIPTOR_PATH);
		Resource resource= WorkbenchResourceHelperBase.getResource(file,true);
		EJBJar jar = (EJBJar)resource.getContents().get(0);
		jar.getEjbClientJar();
		int x = jar.getEjbClientJar().indexOf(".");
		String jarProject = jar.getEjbClientJar().substring(0,x);
		IProject clientProject = ProjectUtilities.getProject(jarProject);
		clientProject.exists();
		return new String[]{project.getName()};
	}


	public void setCurrentProject(IProject aProject) {
		project = aProject;
	}

	public IProject getProject() {
		return project;
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
		return null;
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
			case J2EEVersionConstants.WEB_2_4_ID :
				j2eeVersion = J2EEVersionConstants.WEB_2_4_ID;
			default :
				j2eeVersion = J2EEVersionConstants.J2EE_1_4_ID;
		}
		return j2eeVersion;
	}


	private int getModuleVersion() {
		return getJ2EESettings().getModuleVersion();
	}


	private J2EESettings getJ2EESettings() {
		J2EESettings j2eeSetting = new J2EESettings(getProject());
		return j2eeSetting;
	}

	public void postMigrateStrategy() {
	}


	public boolean isReferencedComponent(String componentName) {
		return false;
	}


	public String getReferencedComponentName(String componentName) {
		return null;
	}


	public String getReferencedComponentURI(String componentName) {
		return null;
	}


	public DependencyType getDependancyType(String componentName) {
		return null;
	}
}
