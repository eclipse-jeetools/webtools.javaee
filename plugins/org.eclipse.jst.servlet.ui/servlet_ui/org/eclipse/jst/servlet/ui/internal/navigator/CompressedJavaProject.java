/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.graphics.Image;

public class CompressedJavaProject implements ICompressedNode, IAdaptable { 
	
	private IProject project;
	private CompressedJavaLibraries compressedLibraries;
	private Image image; 
 
	public CompressedJavaProject(StructuredViewer viewer, IProject project) {
		this.project = project; 

	}

	public Image getImage() {
		if(image == null)
			image = JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_PACKFRAG_ROOT);
		return image;
	}

	public String getLabel() {
		return determineLabel();

	}

	public boolean isFlatteningSourceFolder() {
		return getNonExternalSourceFolders().size() == 1;
	}

	private String determineLabel() {
		return WEBUIMessages.Compressed_JavaResources;
	}

	public IProject getProject() {
		return project;
	}

	public Object[] getChildren(ITreeContentProvider delegateContentProvider) {
		
		List nonExternalSourceFolders = getNonExternalSourceFolders();
		nonExternalSourceFolders.add(getCompressedJavaLibraries());
		return nonExternalSourceFolders.toArray();
	}

	public List getNonExternalSourceFolders() {
		List nonExternalSourceFolders = null;
		IPackageFragmentRoot[] sourceFolders;
		try {
			IJavaProject jProject = JavaCore.create(project);
			sourceFolders = jProject.getPackageFragmentRoots();
			nonExternalSourceFolders = new ArrayList(Arrays.asList(sourceFolders));
			for (Iterator iter = nonExternalSourceFolders.iterator(); iter.hasNext();) {
				IPackageFragmentRoot root = (IPackageFragmentRoot) iter.next();
				if (root.isExternal() || root.isArchive() || root.getKind()==IPackageFragmentRoot.K_BINARY)
					iter.remove();
			}
		} catch (JavaModelException e) {
			ServletUIPlugin.log(e);
		}
		return nonExternalSourceFolders != null ? nonExternalSourceFolders : Collections.EMPTY_LIST;
	}
	
	public CompressedJavaLibraries getCompressedJavaLibraries() {		
		if(compressedLibraries == null) 
			compressedLibraries = new CompressedJavaLibraries(this);
		return compressedLibraries;
		
	}

	public Object getAdapter(Class adapter) { 
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public IJavaElement getJavaElement() {
		List nonExternalSourceFolders = getNonExternalSourceFolders();
		if (nonExternalSourceFolders.size() == 1) {
			return (IJavaElement) nonExternalSourceFolders.get(0);
		}
		return JavaCore.create(project);
	}

}
