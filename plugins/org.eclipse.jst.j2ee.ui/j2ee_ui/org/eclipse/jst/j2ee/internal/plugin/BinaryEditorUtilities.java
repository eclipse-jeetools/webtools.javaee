/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.jdt.core.IJavaModelStatusConstants;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.JavaModelManager;
import org.eclipse.jdt.internal.core.JavaModelStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class BinaryEditorUtilities {

	private static class JarEntryFile extends PlatformObject implements
			IStorage {
		private String entryName;
		private String zipName;
		private IPath path;

		public JarEntryFile(String entryName, String zipName) {
			this.entryName = entryName;
			this.zipName = zipName;
			this.path = new Path(this.entryName);
		}

		@Override
		public InputStream getContents() throws CoreException {

			try {
				if (JavaModelManager.ZIP_ACCESS_VERBOSE) {
					//$ANALYSIS-IGNORE
					System.out.println("(" + Thread.currentThread() + ") [JarEntryFile.getContents()] Creating ZipFile on " + this.zipName); //$NON-NLS-1$  //$NON-NLS-2$
				}
				ZipFile zipFile = ArchiveUtil.newZipFile(this.zipName);
				ZipEntry zipEntry = zipFile.getEntry(this.entryName);
				if (zipEntry == null) {
					throw new JavaModelException(new JavaModelStatus(
							IJavaModelStatusConstants.INVALID_PATH,
							this.entryName));
				}
				return zipFile.getInputStream(zipEntry);
			} catch (IOException e) {
				throw new JavaModelException(e,
						IJavaModelStatusConstants.IO_EXCEPTION);
			}
		}

		/**
		 * @see IStorage#getFullPath
		 */
		@Override
		public IPath getFullPath() {
			return this.path;
		}

		/**
		 * @see IStorage#getName
		 */
		@Override
		public String getName() {
			return this.path.lastSegment();
		}

		/**
		 * @see IStorage#isReadOnly()
		 */
		@Override
		public boolean isReadOnly() {
			return true;
		}

		/**
		 * @see IStorage#isReadOnly()
		 */
		@Override
		public String toString() {
			return "JarEntryFile[" + this.zipName + "::" + this.entryName + "]"; //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-1$
		}
	}

	private static class JarEntryEditorInput implements IStorageEditorInput {

		private IStorage fJarEntryFile;

		public JarEntryEditorInput(IStorage jarEntryFile) {
			fJarEntryFile = jarEntryFile;
		}

		@Override
		public int hashCode() {
			return super.hashCode() + fJarEntryFile.hashCode();
		}
		
		/*
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof JarEntryEditorInput))
				return false;
			JarEntryEditorInput other = (JarEntryEditorInput) obj;
			return fJarEntryFile.equals(other.fJarEntryFile);
		}

		/*
		 * @see IEditorInput#getPersistable()
		 */
		@Override
		public IPersistableElement getPersistable() {
			return null;
		}

		/*
		 * @see IEditorInput#getName()
		 */
		@Override
		public String getName() {
			return fJarEntryFile.getName();
		}

		/*
		 * @see IEditorInput#getToolTipText()
		 */
		@Override
		public String getToolTipText() {
			return fJarEntryFile.getFullPath().toString();
		}

		/*
		 * @see IEditorInput#getImageDescriptor()
		 */
		@Override
		public ImageDescriptor getImageDescriptor() {
			IEditorRegistry registry = PlatformUI.getWorkbench()
					.getEditorRegistry();
			return registry.getImageDescriptor(fJarEntryFile.getFullPath()
					.getFileExtension());
		}

		/*
		 * @see IEditorInput#exists()
		 */
		@Override
		public boolean exists() {
			// JAR entries can't be deleted
			return true;
		}

		/*
		 * @see IAdaptable#getAdapter(Class)
		 */
		@Override
		public Object getAdapter(Class adapter) {
			return null;
		}

		/*
		 * see IStorageEditorInput#getStorage()
		 */
		@Override
		public IStorage getStorage() {
			return fJarEntryFile;
		}
	}

	/**
	 * 
	 * @param component
	 * @param archiveRelativePath
	 * @return
	 * @deprecated
	 */
	public static IEditorInput getBinaryEditorInput(
			VirtualArchiveComponent component, String archiveRelativePath) {
		return getBinaryEditorInput((IVirtualComponent)component, archiveRelativePath);
	}

	public static IEditorInput getBinaryEditorInput(IVirtualComponent component, String archiveRelativePath) {
		File f = component.getAdapter(File.class);
		return getBinaryEditorInput(f.getAbsolutePath(), archiveRelativePath);
	}

	
	public static IEditorInput getBinaryEditorInput(IPath archivePath,
			String archiveRelativePath) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IResource resource = root.findMember(archivePath);
		if (resource == null) {
			return null;
		}
		String archiveOSPath = resource.getLocation().toOSString();
		IEditorInput editorInput = getBinaryEditorInput(archiveOSPath, archiveRelativePath);
		return editorInput;
	}
	
	/**
	 * [Bug 238616]
	 * 
	 * Gets binary editor input given an OS relative path to an archive and
	 * 	the archive relative path to a file
	 * 
	 * @param archiveOSPath the OS relative path to the archive
	 * @param archiveRelativePath the archive relative path to the file to get binary editor input for
	 * @return
	 */
	public static IEditorInput getBinaryEditorInput(String archiveOSPath,
			String archiveRelativePath) {
		JarEntryFile jarFile = new JarEntryFile(archiveRelativePath, archiveOSPath);
		JarEntryEditorInput editorInput = new JarEntryEditorInput(jarFile);
		return editorInput;
	}

}
