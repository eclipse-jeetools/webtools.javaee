/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.common.J2EECommonMessages;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class NewJavaClassDataModel extends J2EEModelModifierOperationDataModel {

	public static final String SOURCE_FOLDER = "NewJavaClassDataModel.SOURCE_FOLDER"; //$NON-NLS-1$
	public static final String JAVA_PACKAGE = "NewJavaClassDataModel.JAVA_PACKAGE"; //$NON-NLS-1$
	public static final String CLASS_NAME = "NewJavaClassDataModel.CLASS_NAME"; //$NON-NLS-1$
	public static final String SUPERCLASS = "NewJavaClassDataModel.SUPERCLASS"; //$NON-NLS-1$
	public static final String MODIFIER_PUBLIC = "NewJavaClassDataModel.MODIFIER_PUBLIC"; //$NON-NLS-1$
	public static final String MODIFIER_ABSTRACT = "NewJavaClassDataModel.MODIFIER_ABSTRACT"; //$NON-NLS-1$
	public static final String MODIFIER_FINAL = "NewJavaClassDataModel.MODIFIER_FINAL"; //$NON-NLS-1$
	public static final String INTERFACES = "NewJavaClassDataModel.INTERFACES"; //$NON-NLS-1$
	public static final String MAIN_METHOD = "NewJavaClassDataModel.MAIN_METHOD"; //$NON-NLS-1$
	public static final String CONSTRUCTOR = "NewJavaClassDataModel.CONSTRUCTOR"; //$NON-NLS-1$
	public static final String ABSTRACT_METHODS = "NewJavaClassDataModel.ABSTRACT_METHODS"; //$NON-NLS-1$

	public static final String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature"; //$NON-NLS-1$

	private IJavaProject javaProject;

	// Methods to be overrided
	public IStatus validateJavaSourceFolder(String folderFullPath) {
		// check for closed project
		IProject project = getTargetProject();
		if (project == null) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_FOLDER_NOT_EXIST);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		if (!project.isAccessible()) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_FOLDER_NOT_EXIST);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		try {
			if (!project.hasNature(JAVA_NATURE_ID)) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NOT_JAVA_PROJECT);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		} catch (CoreException e) {
			Logger.getLogger().log(e);
		}
		IFolder sourcefolder = getJavaSourceFolder();
		if (sourcefolder == null || (sourcefolder != null && !sourcefolder.getFullPath().equals(new Path(folderFullPath)))) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_FOLDER_NOT_SOURCE, new String[]{folderFullPath});
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	// Methods to be overrided
	protected IFolder getDefaultJavaSourceFolder() {
		IProject project = getTargetProject();
		if (project == null)
			return null;
		IContainer output = ProjectUtilities.getJavaProjectOutputContainer(project);
		List sources = ProjectUtilities.getSourceContainers(project);
		//TODO: We need to be able to support the project as the source, but this would be a
		// breaking change
		if (sources == null || sources.isEmpty() || ((IContainer) sources.get(0)).getType() != IResource.FOLDER)
			return null;
		if (output != null && sources.contains(output))
			return (IFolder) output;
		return (IFolder) sources.get(0);
	}

	// Methods to be overrided
	public IPackageFragmentRoot getJavaPackageFragmentRoot() {
		IProject project = getTargetProject();
		IJavaProject aJavaProject = ProjectUtilities.getJavaProject(project);
		if (aJavaProject != null) {
			IFolder sourcefolder = getJavaSourceFolder();
			if (sourcefolder != null)
				return aJavaProject.getPackageFragmentRoot(sourcefolder);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(SOURCE_FOLDER);
		addValidBaseProperty(JAVA_PACKAGE);
		addValidBaseProperty(CLASS_NAME);
		addValidBaseProperty(SUPERCLASS);
		addValidBaseProperty(MODIFIER_PUBLIC);
		addValidBaseProperty(MODIFIER_ABSTRACT);
		addValidBaseProperty(MODIFIER_FINAL);
		addValidBaseProperty(INTERFACES);
		addValidBaseProperty(MAIN_METHOD);
		addValidBaseProperty(CONSTRUCTOR);
		addValidBaseProperty(ABSTRACT_METHODS);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(SOURCE_FOLDER)) {
			IFolder sourceFolder = getDefaultJavaSourceFolder();
			if (sourceFolder != null && sourceFolder.exists())
				return sourceFolder.getFullPath().toOSString();
		}
		if (propertyName.equals(SUPERCLASS)) {
			return new String("java.lang.Object"); //$NON-NLS-1$
		}
		if (propertyName.equals(MODIFIER_PUBLIC)) {
			return new Boolean(true);
		}
		if (propertyName.equals(CONSTRUCTOR)) {
			return new Boolean(true);
		}
		if (propertyName.equals(ABSTRACT_METHODS)) {
			return new Boolean(true);
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if (propertyName.equals(SOURCE_FOLDER))
			return validateFolder(getStringProperty(propertyName));
		if (propertyName.equals(JAVA_PACKAGE))
			return validateJavaPackage(getStringProperty(propertyName));
		if (propertyName.equals(CLASS_NAME))
			return validateJavaClassName(getStringProperty(propertyName));
		if (propertyName.equals(SUPERCLASS))
			return validateSuperclass(getStringProperty(propertyName));
		if (propertyName.equals(MODIFIER_ABSTRACT))
			return validateModifierAbstract(getBooleanProperty(propertyName));
		if (propertyName.equals(MODIFIER_FINAL))
			return validateModifierFinal(getBooleanProperty(propertyName));
		return super.doValidateProperty(propertyName);
	}

	protected IStatus validateFolder(String folderFullPath) {
		// check if empty
		if (folderFullPath == null || folderFullPath.length() == 0) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_FOLDER_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		return validateJavaSourceFolder(folderFullPath);
	}

	protected IStatus validateJavaPackage(String packName) {
		if (packName != null && packName.trim().length() > 0) {
			IStatus javaStatus = JavaConventions.validatePackageName(packName);
			if (javaStatus.getSeverity() == IStatus.ERROR) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_PACAKGE_NAME_INVALID) + javaStatus.getMessage();
				return WTPCommonPlugin.createErrorStatus(msg);
			} else if (javaStatus.getSeverity() == IStatus.WARNING) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_PACKAGE_NAME_WARNING) + javaStatus.getMessage();
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateClassName(String className) {
		// check if empty
		if (className == null || className.trim().length() == 0) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check for duplicate
		IFolder sourceFolder = getJavaSourceFolder();
		if (sourceFolder != null && sourceFolder.exists()) {
			String packageName = getStringProperty(JAVA_PACKAGE);
			String fullClassName = packageName + "." + className; //$NON-NLS-1$
			if (findTypeInClasspath(fullClassName) != null) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_EXIST, new String[]{className});
				return WTPCommonPlugin.createErrorStatus(msg);
			}
			// check if the case insensitive java file name exists
			String fullClassFileName = fullClassName.replace('.', IPath.SEPARATOR);
			int len = fullClassFileName.length();
			char[] nameArray = fullClassFileName.toCharArray();
			for (int i = 0; i < len; i++) {
				char ch = nameArray[i];
				if (!Character.isLetter(ch))
					continue;
				for (int k = 0; k < 2; k++) {
					if (k == 0)
						nameArray[i] = Character.toLowerCase(ch);
					else
						nameArray[i] = Character.toUpperCase(ch);
					String javaFileName = String.valueOf(nameArray);
					IFile file = sourceFolder.getFile(javaFileName + ".java"); //$NON-NLS-1$
					if (file.exists()) {
						String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_FILE_NAME_EXIST, new String[]{className});
						return WTPCommonPlugin.createErrorStatus(msg);
					}
					nameArray[i] = ch;
				}
			}
		}
		return validateJavaClassName(className);
	}

	protected IStatus validateJavaClassName(String className) {
		// do not allow qualified name
		if (className.lastIndexOf('.') != -1) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_QUALIFIED);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check Java class name by convention
		IStatus javaStatus = JavaConventions.validateJavaTypeName(className);
		if (javaStatus.getSeverity() == IStatus.ERROR) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_INVALID) + javaStatus.getMessage();
			return WTPCommonPlugin.createErrorStatus(msg);
		} else if (javaStatus.getSeverity() == IStatus.WARNING) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_WARNING) + javaStatus.getMessage();
			return WTPCommonPlugin.createWarningStatus(msg);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateSuperclass(String superclassName) {
		// check if empty
		if (superclassName == null || superclassName.trim().length() == 0) {
			String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_NAME_EMPTY);
			return WTPCommonPlugin.createErrorStatus(msg);
		}
		// check name
		String className = superclassName;
		int index = superclassName.lastIndexOf("."); //$NON-NLS-1$
		if (index != -1) {
			className = superclassName.substring(index + 1);
		}
		IStatus status = validateJavaClassName(className);
		if (status.isOK()) {
			// check if the supperclss exists
			IType superClassType = findTypeInClasspath(superclassName);
			if (superClassType == null) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_SUPERCLASS_NOT_EXIST);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
			// check if the superclass is final
			int flags = -1;
			try {
				flags = superClassType.getFlags();
			} catch (JavaModelException e) {
				Logger.getLogger().log(e);
			}
			if (Modifier.isFinal(flags)) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_JAVA_CLASS_SUPERCLASS_FINAL);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return status;
	}

	protected IStatus validateModifierAbstract(boolean prop) {
		// check if both Abstract and Final are checked
		if (prop) {
			// check if Final is checked
			if (getBooleanProperty(MODIFIER_FINAL)) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_BOTH_FINAL_AND_ABSTRACT);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected IStatus validateModifierFinal(boolean prop) {
		// check if both Abstract and Final are checked
		if (prop) {
			// check if Abstract is checked
			if (getBooleanProperty(MODIFIER_ABSTRACT)) {
				String msg = J2EECommonMessages.getResourceString(J2EECommonMessages.ERR_BOTH_FINAL_AND_ABSTRACT);
				return WTPCommonPlugin.createErrorStatus(msg);
			}
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new NewJavaClassOperation(this);
	}

	public String getQualifiedClassName() {
		String packageName = getStringProperty(JAVA_PACKAGE);
		String className = getStringProperty(CLASS_NAME);
		if (packageName != null && packageName.trim().length() > 0)
			return packageName + "." + className; //$NON-NLS-1$
		return className;
	}

	private IType findTypeInClasspath(String fullClassName) {
		if (javaProject == null)
			javaProject = ProjectUtilities.getJavaProject(getTargetProject());
		try {
			IType type = JavaModelUtil.findType(javaProject, fullClassName);
			return type;
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
		return null;
	}

	protected IFolder getJavaSourceFolder() {
		List sources = getAllsourceFolders();
		if (sources == null || sources.isEmpty() || ((IContainer) sources.get(0)).getType() != IResource.FOLDER)
			return null;
		String folderFullPath = getStringProperty(SOURCE_FOLDER);
		for (int i = 0; i < sources.size(); i++) {
			IFolder folder = (IFolder) sources.get(i);
			if (folder.getFullPath().equals(new Path(folderFullPath))) {
				return folder;
			}
		}
		return null;
	}

	private List getAllsourceFolders() {
		IProject project = getTargetProject();
		if (project == null)
			return null;
		List sources = ProjectUtilities.getSourceContainers(project);
		return sources;
	}

	public boolean isSourceFolder(String folderFullName) {
		List sources = getAllsourceFolders();
		boolean result = false;
		if (sources != null || !sources.isEmpty()) {
			for (int i = 0; i < sources.size(); i++) {
				IFolder folder = (IFolder) sources.get(i);
				if (folder.getFullPath().equals(new Path(folderFullName))) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
}