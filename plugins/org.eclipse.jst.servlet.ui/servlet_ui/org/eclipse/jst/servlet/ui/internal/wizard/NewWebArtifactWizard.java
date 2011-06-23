/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_PACKAGE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.OPEN_IN_EDITOR;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.PROJECT;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.QUALIFIED_CLASS_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.JAVA_SOURCE_FOLDER;
import static org.eclipse.jst.j2ee.internal.web.operations.INewWebClassDataModelProperties.USE_EXISTING_CLASS;

import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.common.operations.JavaModelUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEEditorUtility;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class NewWebArtifactWizard extends NewWebWizard {
	
	protected static final String PAGE_ONE = "pageOne"; //$NON-NLS-1$
	protected static final String PAGE_TWO = "pageTwo"; //$NON-NLS-1$
	protected static final String PAGE_THREE = "pageThree"; //$NON-NLS-1$

	public NewWebArtifactWizard(IDataModel model) {
		super(model);
		setWindowTitle(getTitle());
		setDefaultPageImageDescriptor(getImage());
	}

	@Override
	protected boolean runForked() {
		return false;
	}
	
	@Override
	public boolean canFinish() {
		return getDataModel().isValid();
	}
	
	protected abstract String getTitle();
	
	protected abstract ImageDescriptor getImage();
	
	protected ImageDescriptor getImageFromJ2EEPlugin(String key) {
		URL url = (URL) J2EEPlugin.getDefault().getImage(key);
		return ImageDescriptor.createFromURL(url);
	}
	
	protected void openJavaClass() {
		try {
			String className;
			if (getDataModel().getBooleanProperty(USE_EXISTING_CLASS)) {
				className = getDataModel().getStringProperty(CLASS_NAME);
			} else {
				className = getDataModel().getStringProperty(QUALIFIED_CLASS_NAME);
			}
			IProject p = (IProject) getDataModel().getProperty(PROJECT);
			IJavaProject javaProject = J2EEEditorUtility.getJavaProject(p);
			IType type = javaProject.findType(className);
			IFile file = null;
			
			if (type != null){
				file = (IFile) type.getResource();				
			}
			else if (!getDataModel().getBooleanProperty(USE_EXISTING_CLASS)){
				// If we could not find the type, the class might be in a folder that is not part of the build path. 
				// Let's look for it using the class name and source folder (this does not work when using existing class, but
				// should not be a problem, because the wizard to select the existing class will not let you select class that 
				// is not in the build path)
				IFolder sourceFolder = (IFolder) getDataModel().getProperty(JAVA_SOURCE_FOLDER);
				String packageName = getDataModel().getStringProperty(JAVA_PACKAGE);
				IPackageFragmentRoot packageFragmentRoot = javaProject.getPackageFragmentRoot(sourceFolder);
				IPackageFragment packageFragment = null;
				if (packageFragmentRoot!= null){
					packageFragment = packageFragmentRoot.getPackageFragment(packageName);
				}
				if (packageFragment!= null){
					ICompilationUnit cu = packageFragment.getCompilationUnit(getDataModel().getStringProperty(CLASS_NAME) + JavaModelUtil.DEFAULT_CU_SUFFIX);				
					IResource resource = cu.getResource();
					if (resource.exists() && resource instanceof IFile) {
						file = (IFile)resource;
					}
				}			
			}
			if (file != null){
				openEditor(file);
			}
		} catch (Exception cantOpen) {
			ServletUIPlugin.log(cantOpen);
		}	
	}
	
	protected void openWebFile() {
		try {
			String className = getDataModel().getStringProperty(CLASS_NAME);
			IProject p = (IProject) getDataModel().getProperty(PROJECT);
			IVirtualComponent component = ComponentCore.createComponent(p);
			IContainer webContent = component.getRootFolder().getUnderlyingFolder();
			IFile file = webContent.getFile(new Path(className));
			openEditor(file);
		} catch (Exception cantOpen) {
			ServletUIPlugin.log(cantOpen);
		}	
	}

	protected void openEditor(final IFile file) {
		if (getDataModel().getBooleanProperty(OPEN_IN_EDITOR)) {
			if (file != null) {
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						try {
							IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
							IDE.openEditor(page, file, true);
						}
						catch (PartInitException e) {
							ServletUIPlugin.log(e);
						}
					}
				});
			}
		}
	}

}
