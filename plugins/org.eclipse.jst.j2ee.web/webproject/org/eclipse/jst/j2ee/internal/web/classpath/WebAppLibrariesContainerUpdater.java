package org.eclipse.jst.j2ee.internal.web.classpath;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

public class WebAppLibrariesContainerUpdater implements IResourceChangeListener, IResourceDeltaVisitor {

	private static final String DOT_SETTINGS = ".settings";

	private static final String COMPONENT = "org.eclipse.wst.common.component";

	private static final String WEB_INF = "WEB-INF";

	private static final String LIB = "lib";

	private static final String DOT_JAR = ".jar";

	private static final String DOT_ZIP = ".zip";

	private IPath WEB_APP_LIBS_PATH = new Path(WebAppLibrariesContainer.CONTAINER_ID);
	
	private static WebAppLibrariesContainerUpdater instance = null;

	public static WebAppLibrariesContainerUpdater getInstance() {
		if (instance == null) {
			instance = new WebAppLibrariesContainerUpdater();
		}
		return instance;
	}

	List projectsToUpdate = new ArrayList();

	List potentialProjectsToUpdate = new ArrayList();

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			projectsToUpdate.clear();
			potentialProjectsToUpdate.clear();
			event.getDelta().accept(this);
			update(event.getDelta());
		} catch (CoreException e) {
			CommonFrameworksPlugin.log(e);
		}
	}

	public void update(IResourceDelta delta) {
		for (int i = 0; i< potentialProjectsToUpdate.size(); i++) {
			IProject project = (IProject) potentialProjectsToUpdate.get(i);
			if (!projectsToUpdate.contains(project)) {
				WebAppLibrariesContainer container = (WebAppLibrariesContainer)getWebAppLibrariesContainer(project, false);
				if(container.isOutOfDate(delta)){
					projectsToUpdate.add(project);
				}
			}
		}
		for(int i=0;i<projectsToUpdate.size(); i++){
			IProject project = (IProject) projectsToUpdate.get(i);
			WebAppLibrariesContainer container = (WebAppLibrariesContainer)getWebAppLibrariesContainer(project, false);
			container.refresh();
		}
	}
	
	public IClasspathContainer getWebAppLibrariesContainer(IProject webProject, boolean create) {
		IJavaProject jproj = JavaCore.create(webProject);
		IClasspathContainer container = null;
		IClasspathEntry entry = create ? null : J2EEComponentClasspathUpdater.getInstance().getExistingContainer(jproj, WEB_APP_LIBS_PATH);
		if (entry != null || create) {
			try {
				container = JavaCore.getClasspathContainer(WEB_APP_LIBS_PATH, jproj);
			} catch (JavaModelException e) {
				CommonFrameworksPlugin.log(e);
			}
		}
		return container;
	}
	
	public boolean visit(IResourceDelta delta) throws CoreException {
		IResource resource = delta.getResource();
		switch (resource.getType()) {
		case IResource.ROOT:
			return true;
		case IResource.PROJECT:
			return ModuleCoreNature.isFlexibleProject((IProject) resource) && FacetedProjectFramework.hasProjectFacet((IProject) resource, IModuleConstants.JST_WEB_MODULE);
		case IResource.FOLDER: {
			if (resource.getName().equals(DOT_SETTINGS)) {
				return true;
			}
			IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
			IVirtualFolder rootFolder = comp.getRootFolder();
			if (J2EEComponentClasspathUpdater.isRootAncester(resource, rootFolder)) {
				return true;
			}
			IVirtualFolder webInfFolder = rootFolder.getFolder(WEB_INF);
			if (J2EEComponentClasspathUpdater.isFolder(resource, webInfFolder)) {
				return true;
			}
			IVirtualFolder libFolder = rootFolder.getFolder(WEB_INF).getFolder(LIB);
			if (J2EEComponentClasspathUpdater.isFolder(resource, libFolder)) {
				return true;
			}
			return false;
		}
		case IResource.FILE: {
			String name = resource.getName();
			if (name.equals(COMPONENT)) {
				potentialProjectsToUpdate.add(resource.getProject());
			} else if (J2EEComponentClasspathUpdater.endsWithIgnoreCase(name, DOT_JAR) || J2EEComponentClasspathUpdater.endsWithIgnoreCase(name, DOT_ZIP)) {
				IResource parent = resource.getParent();
				IVirtualComponent comp = ComponentCore.createComponent(resource.getProject());
				IVirtualFolder rootFolder = comp.getRootFolder();
				IVirtualFolder libFolder = rootFolder.getFolder(WEB_INF).getFolder(LIB);
				if (J2EEComponentClasspathUpdater.isFolder(parent, libFolder)) {
					projectsToUpdate.add(resource.getProject());
				}
			}
		}
		default:
			return false;
		}
	}

}
