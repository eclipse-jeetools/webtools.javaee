package org.eclipse.jst.common.jdt.internal.javalite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public final class JavaLiteUtilities {

	public final static List<IContainer> getAllJavaSourceContainers(final IJavaProjectLite javaProject) {
		IClasspathEntry[] entries = javaProject.readRawClasspath();
		List<IContainer> sourceContainers = new ArrayList<IContainer>();
		for (IClasspathEntry entry : entries) {
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IContainer container = javaProject.getProject().getFolder(entry.getPath());
				sourceContainers.add(container);
			}
		}
		return sourceContainers;
	}

	public final static List<IContainer> getJavaSourceContainers(final IVirtualComponent component) {
		if (!component.isBinary()) {
			IJavaProjectLite javaProject = JavaCoreLite.create(component.getProject());
			if (javaProject != null) {
				List<IContainer> allSourceContainers = getAllJavaSourceContainers(javaProject);
				List<IContainer> componentSourceContainers = new ArrayList<IContainer>();
				for (IContainer sourceContainer : allSourceContainers) {
					IVirtualResource[] virtualResources = ComponentCore.createResources(sourceContainer);
					for (IVirtualResource virtualResource : virtualResources) {
						if (virtualResource.getComponent().equals(component)) {
							componentSourceContainers.add(sourceContainer);
							break;
						}
					}
				}
			}
		}
		return Collections.emptyList();
	}

	public final static List<IContainer> getOutputContainers(final IVirtualComponent component, int kind) {
		if (!component.isBinary()) {
			IJavaProjectLite javaProject = JavaCoreLite.create(component.getProject());
			if (javaProject != null) {
				IClasspathEntry[] entries;
				entries = javaProject.readRawClasspath();
				List<IContainer> componentOutputContainers = new ArrayList<IContainer>();
				for (IClasspathEntry entry : entries) {
					IContainer container = null;
					if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE
							&& (kind & IClasspathEntry.CPE_SOURCE) == IClasspathEntry.CPE_SOURCE) {
						container = javaProject.getProject().getFolder(entry.getPath());
					} else if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY
							&& (kind & IClasspathEntry.CPE_LIBRARY) == IClasspathEntry.CPE_LIBRARY) {
						container = javaProject.getProject().getFolder(entry.getPath());
						if (!container.exists()) {
							container = null;
						}
					}
					if (container != null) {
						IVirtualResource[] virtualResources = ComponentCore.createResources(container);
						for (IVirtualResource virtualResource : virtualResources) {
							if (virtualResource.getComponent().equals(component)) {
								IPath outputPath = entry.getOutputLocation();
								IContainer outputContainer = null;
								if (outputPath == null) {
									if (javaProject.readOutputLocation().segmentCount() == 1) {
										outputContainer = javaProject.getProject();
									} else {
										outputContainer = javaProject.getProject().getFolder(
												javaProject.readOutputLocation().removeFirstSegments(1));
									}
								} else {
									outputContainer = javaProject.getProject().getFolder(
											outputPath.removeFirstSegments(1));
								}
								if (!componentOutputContainers.contains(outputContainer)) {
									componentOutputContainers.add(outputContainer);
								}
								break;
							}
						}
					}
				}
				return componentOutputContainers;
			}
		}
		return Collections.emptyList();
	}

}
