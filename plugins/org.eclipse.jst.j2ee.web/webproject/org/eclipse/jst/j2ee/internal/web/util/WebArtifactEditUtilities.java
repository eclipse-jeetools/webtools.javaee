package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.web.internal.operation.ILibModule;

public class WebArtifactEditUtilities {

	public static WebApp getWebApplication(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return (WebApp) webEdit.getDeploymentDescriptorRoot();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return null;
	}
	
	public static WebAppResource getWebApplicationXmiResource(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return (WebAppResource) webEdit.getDeploymentDescriptorResource();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return null;
	}
	
	public static IPath getWebDeploymentDescriptorPath(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return webEdit.getDeploymentDescriptorPath();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return null;
	}
	
	public static IFile getWTPModuleFile(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return webEdit.getWTPModuleFile();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return null;
	}
	
	public static int getJ2EEVersion(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return webEdit.getJ2EEVersion();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return 0;
	}
	
	public static int getJSPVersion(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return webEdit.getJSPVersion();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return 0;
	}
	
	public static ILibModule[] getLibModules(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return webEdit.getLibModules();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return new ILibModule[] {};
	}
	
	public static int getServletVersion(WorkbenchModule aModule) {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(aModule);
			if (webEdit != null)
				return webEdit.getServletVersion();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return 0;
	}
}
