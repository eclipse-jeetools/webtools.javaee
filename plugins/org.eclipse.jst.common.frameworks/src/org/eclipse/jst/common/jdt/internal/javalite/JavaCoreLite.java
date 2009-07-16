package org.eclipse.jst.common.jdt.internal.javalite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IAccessRule;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

public final class JavaCoreLite {
	
	public static String NATURE_ID = JavaCore.NATURE_ID;

	public static final IJavaProjectLite create(IProject project) {
		IJavaProject javaProject = JavaCore.create(project);
		return create(javaProject);
	}
	
	public static final IJavaProjectLite create(IJavaProject javaProject) {
		if (javaProject != null) {
			return new JavaProjectLite(javaProject);
		}
		return null;
	}
	
	public static ClasspathContainerInitializer getClasspathContainerInitializer(String containerID) {
		return JavaCore.getClasspathContainerInitializer(containerID);
	}
	
	public static IClasspathEntry newProjectEntry(IPath path) {
		return JavaCore.newProjectEntry(path);
	}
	
	public static IClasspathEntry newProjectEntry(IPath path, boolean isExported) {
		return JavaCore.newProjectEntry(path, isExported);
	}
	
	public static IClasspathEntry newProjectEntry(IPath path, IAccessRule[] accessRules, boolean combineAccessRules, IClasspathAttribute[] extraAttributes,	boolean isExported) {
		return JavaCore.newProjectEntry(path, accessRules, combineAccessRules, extraAttributes, isExported);
	}
	
	public static IClasspathEntry newLibraryEntry(IPath path, IPath sourceAttachmentPath, IPath sourceAttachmentRootPath) {
		return JavaCore.newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath);
	}
	
	public static IClasspathEntry newLibraryEntry(IPath path, IPath sourceAttachmentPath, IPath sourceAttachmentRootPath, boolean isExported) {
		return JavaCore.newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, isExported);
	}
	
	public static IClasspathEntry newLibraryEntry(IPath path, IPath sourceAttachmentPath, IPath sourceAttachmentRootPath, IAccessRule[] accessRules, IClasspathAttribute[] extraAttributes,	boolean isExported) {
		return JavaCore.newLibraryEntry(path, sourceAttachmentPath, sourceAttachmentRootPath, accessRules, extraAttributes, isExported);
	}
}
