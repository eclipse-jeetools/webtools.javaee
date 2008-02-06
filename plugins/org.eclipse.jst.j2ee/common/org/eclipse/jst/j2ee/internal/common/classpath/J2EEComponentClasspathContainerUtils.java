package org.eclipse.jst.j2ee.internal.common.classpath;

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.jdt.internal.classpath.FlexibleProjectContainer;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class J2EEComponentClasspathContainerUtils {

	public final static String EAR_LIBRARIES_PROCESSED = "ear_libraries_processed"; //$NON-NLS-1$

	public final static String WEB_APP_LIBRARIES_PROCESSED = "web_app_libraries_processed"; //$NON-NLS-1$

	public static boolean isWebAppLibrariesProcessed(IProject project) {
		return isLibraryProcessed(project, WEB_APP_LIBRARIES_PROCESSED);
	}

	public static boolean isEARLibrariesProcessed(IProject project) {
		return isLibraryProcessed(project, EAR_LIBRARIES_PROCESSED);
	}

	private static boolean isLibraryProcessed(IProject project, String propertyName) {
		IVirtualComponent comp = ComponentCore.createComponent(project);
		if (comp == null) {
			return true;
		}
		Properties properties = comp.getMetaProperties();
		String property = properties.getProperty(propertyName);
		if (property == null) {
			return false;
		} else {
			return parseBoolean(property);
		}
	}

	private static boolean parseBoolean(String name) {
		return ((name != null) && name.equalsIgnoreCase("true")); //$NON-NLS-1$
	}

	public static void setEARLibrariesProcessed(IProject project, boolean processed) {
		setLibraryProcessed(project, processed, EAR_LIBRARIES_PROCESSED);
	}

	public static void setWebAppLibrariesProcessed(IProject project, boolean processed) {
		setLibraryProcessed(project, processed, WEB_APP_LIBRARIES_PROCESSED);
	}

	private static void setLibraryProcessed(IProject project, boolean processed, String propertyName) {
		IVirtualComponent comp = ComponentCore.createComponent(project);
		String value = processed ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
		comp.setMetaProperty(propertyName, value);
	}

	public static boolean getDefaultUseEARLibraries() {
		return J2EEPlugin.getDefault().getJ2EEPreferences().getUseEARLibraries();
	}

	public static boolean getDefaultUseEARLibrariesJDTExport() {
		return J2EEPlugin.getDefault().getJ2EEPreferences().getUseEARLibrariesJDTExport();
	}
	
	public static boolean getDefaultUseWebAppLibraries() {
		return J2EEPlugin.getDefault().getJ2EEPreferences().getUseWebLibaries();
	}
	
	public static IClasspathContainer getInstalledContainer(IProject project, IPath containerPath) {
		IJavaProject jproj = JavaCore.create(project);
		IClasspathEntry entry = getInstalledContainerEntry(jproj, containerPath);
		IClasspathContainer container = null;
		if (entry != null) {
			try {
				container = JavaCore.getClasspathContainer(containerPath, jproj);
			} catch (JavaModelException e) {
				J2EEPlugin.getDefault().getLogger().logError(e);
			}
		}
		return container;
	}

	public static J2EEComponentClasspathContainer getInstalledEARLibrariesContainer(IProject project) {
		IClasspathContainer container = getInstalledContainer(project, J2EEComponentClasspathContainer.CONTAINER_PATH);
		J2EEComponentClasspathContainer earLibrariesContainer = null;
		if (null != container && container instanceof J2EEComponentClasspathContainer) {
			earLibrariesContainer = (J2EEComponentClasspathContainer) container;
		}
		return earLibrariesContainer;
	}

	public static FlexibleProjectContainer getInstalledWebAppLibrariesContainer(IProject project){
		IClasspathContainer container = getInstalledContainer(project, J2EEComponentClasspathUpdater.WEB_APP_LIBS_PATH);
		FlexibleProjectContainer webAppLibrariesContainer = null;
		if (null != container && container instanceof FlexibleProjectContainer) {
			webAppLibrariesContainer = (FlexibleProjectContainer) container;
		}
		return webAppLibrariesContainer;
	}
	
	public static IClasspathEntry getInstalledContainerEntry(IJavaProject jproj, IPath classpathContainerPath) {
		try {
			IClasspathEntry[] cpes;
			cpes = jproj.getRawClasspath();
			for (int j = 0; j < cpes.length; j++) {
				final IClasspathEntry cpe = cpes[j];
				if (cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
					if (cpe.getPath().equals(classpathContainerPath)) {
						return cpe; // entry found
					}
				}
			}
		} catch (JavaModelException e) {
			J2EEPlugin.getDefault().getLogger().logError(e);
		}
		// entry not found
		return null;
	}

}
