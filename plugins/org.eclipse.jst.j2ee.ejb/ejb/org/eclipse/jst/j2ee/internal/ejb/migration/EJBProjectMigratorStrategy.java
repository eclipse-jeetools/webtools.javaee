package org.eclipse.jst.j2ee.internal.ejb.migration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.internal.resources.Container;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.EARProjectMap;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMappingImpl;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.project.J2EESettings;
import org.eclipse.wst.common.internal.migration.IMigratorStrategy;
import org.eclipse.wst.common.internal.migration.plugin.MigrationPlugin;
import org.eclipse.wst.common.modulecore.DependencyType;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.Property;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;



public class EJBProjectMigratorStrategy implements IMigratorStrategy {
	protected IProject project;
	protected IProject clientJarProject;
	protected ArrayList utilityProjects = new ArrayList(2);
	protected EJBJar jar = null;
	protected static String JAVA_SOURCE = "ejbModule";
	protected static String JAVA_SOURCE_DEPLOY_PATH_NAME = JAVA_SOURCE;
	protected static String EJB_DEPLOYMENT_DESCRIPTOR_PATH = "/" + JAVA_SOURCE + "/META-INF/ejb-jar.xml";
	protected static String MODULE_URI = "module:/resource/";

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
						if (packageResource != null && packageResource.getType() == IResource.FOLDER) {
							if (project == packageResource.getProject())
								sourceFolders.add(packageResource);
						}
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
		//if (resource.getName().equals(JAVA_SOURCE))
		 return (new Path(JAVA_SOURCE_DEPLOY_PATH_NAME));
		//return new Path("/");
	}


	public String[] getComponentNames() {
		return new String[]{project.getName()};
	}

	private String[] getALLComponentNames(String name, String[] utilityProjectNames) {
		String[] allNames = new String[1 + utilityProjectNames.length];
		allNames[0] = name;
		System.arraycopy(utilityProjectNames, 0, allNames, 1, utilityProjectNames.length);
		return allNames;
	}

	private String[] getUtilityProjectNames() {
		String[] projectNames = new String[utilityProjects.size()];
		int i = 0;
		if (utilityProjects.size() > 0)
			for (Iterator iter = utilityProjects.iterator(); iter.hasNext();) {
				IProject project = (IProject) iter.next();
				projectNames[i++] = project.getName();
			}
		return projectNames;
	}

	private IProject findClientUtilityProjects(String eJBJarName, String eJBClientName) {
		HashMap map = MigrationPlugin.getDefault().getModuleMaps();
		if (map == null)
			return null;
		Collection col = map.values();
		for (Iterator iter = col.iterator(); iter.hasNext();) {
			EARProjectMap projectMap = (EARProjectMap) iter.next();
			EList list = projectMap.getMappings();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				ModuleMappingImpl moduleMapping = (ModuleMappingImpl) iterator.next();
				if (moduleMapping.getProjectName().equals(eJBJarName)) {
					EList utilityMaps = projectMap.getUtilityJARMappings();
					if (utilityMaps.size() > 0)
						for (Iterator aIterator = utilityMaps.iterator(); aIterator.hasNext();) {
							UtilityJARMapping jarMapping = (UtilityJARMapping) aIterator.next();
							if (jarMapping.getUri().toLowerCase().equals(eJBClientName.toLowerCase())) {
								clientJarProject = getProjectFromWorkspace(jarMapping.getProjectName());
								if (utilityProjects != null && !utilityProjects.contains(clientJarProject))
									utilityProjects.add(clientJarProject);
								continue;
							}
							IProject project = getProjectFromWorkspace(jarMapping.getProjectName());
							if (project != null && utilityProjects != null && (!utilityProjects.contains(clientJarProject)))
								utilityProjects.add(project);
						}

				}

			}

		}
		return clientJarProject;

	}

	private IProject getProjectFromWorkspace(String projectName) {
		return getProject().getWorkspace().getRoot().getProject(projectName);
	}


	private String getClientProjectName() {
		String jarName = getProject().getName();
		String clientName = getEJBJar().getEjbClientJar();
		findClientUtilityProjects(jarName, clientName);
		if (clientJarProject != null)
			return clientJarProject.getName();
		return null;
	}

	private boolean hasEJBClientJar() {
		jar = getEJBJar();
		return (jar != null && jar.getEjbClientJar() != null);
	}

	private EJBJar getEJBJar() {
		if (jar == null) {
			IFile file = getProject().getFile(EJB_DEPLOYMENT_DESCRIPTOR_PATH);
			Resource resource = WorkbenchResourceHelperBase.getResource(file, true);
			if (resource != null && resource.getContents() != null && resource.getContents().size() > 0)
				jar = (EJBJar) resource.getContents().get(0);
		}
		return jar;
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
		if (isUtilityProject())
			return IModuleConstants.JST_UTILITY_MODULE;
		return IModuleConstants.JST_EJB_MODULE;
	}

	public String getComponetTypeVersion(String componentName) {
		if (isUtilityProject())
			return null;

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



	public IProject getClientJarProject() {
		if (clientJarProject == null)
			getClientProjectName();
		return clientJarProject;
	}

	public void setClientJarProject(IProject clientJarProject) {
		this.clientJarProject = clientJarProject;
	}


	public boolean hasReferencedComponent(String componentName) {
		boolean hasClientJar = hasEJBClientJar();
		boolean isClientJarProject = (project != getClientJarProject());
		String clientJarName = getClientProjectName();
		List list = Arrays.asList(getUtilityProjectNames());
		return isClientJarProject && (hasClientJar || list.contains(componentName));
	}


	public String[] getReferencedComponentNames(String componentName) {
		return getUtilityProjectNames();
	}


	public DependencyType getDependancyType(String referencedComponentName, String componentName) {
		return DependencyType.USES_LITERAL;
	}

	public IPath getReferencedComponentHandleURI(String referencedComponentName, String componentName) {
		return new Path(MODULE_URI + referencedComponentName + "/" + referencedComponentName);
	}

	public IPath getReferencedComponentRuntimeType(String referencedComponentName, String componentName) {
		return new Path("/");
	}

	public IProject[] getRequiredProjectsForMigration() {
		if (project != getClientJarProject())
			return new IProject[]{getClientJarProject()};
		else
			return null;
	}

	private boolean isUtilityProject() {
		return utilityProjects.contains(project);
	}
}
