/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Mar 22, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.common.operations;

import java.io.ByteArrayInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModel;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class NewJavaClassOperation extends WTPOperation {

	protected static final String EMPTY_STRING = ""; //$NON-NLS-1$
	protected static final String TAB = "\t"; //$NON-NLS-1$
	protected static final String SPACE = " "; //$NON-NLS-1$
	protected static final String DOT = "."; //$NON-NLS-1$
	protected static final String COMMA = ","; //$NON-NLS-1$
	protected static final String SEMICOLON = ";"; //$NON-NLS-1$
	protected static final String POUND = "#"; //$NON-NLS-1$
	protected static final String OPEN_PAR = "("; //$NON-NLS-1$
	protected static final String CLOSE_PAR = ")"; //$NON-NLS-1$
	protected static final String OPEN_BRA = "{"; //$NON-NLS-1$
	protected static final String CLOSE_BRA = "}"; //$NON-NLS-1$
	protected static final String lineSeparator = System.getProperty("line.separator"); //$NON-NLS-1$

	protected static final String JAVA_LANG_OBJECT = "java.lang.Object"; //$NON-NLS-1$
	protected static final String PACKAGE = "package "; //$NON-NLS-1$
	protected static final String CLASS = "class "; //$NON-NLS-1$
	protected static final String IMPORT = "import "; //$NON-NLS-1$
	protected static final String EXTENDS = "extends "; //$NON-NLS-1$
	protected static final String IMPLEMENTS = "implements "; //$NON-NLS-1$
	protected static final String THROWS = "throws "; //$NON-NLS-1$
	protected static final String SUPER = "super"; //$NON-NLS-1$
	protected static final String PUBLIC = "public "; //$NON-NLS-1$
	protected static final String PROTECTED = "protected "; //$NON-NLS-1$
	protected static final String PRIVATE = "private "; //$NON-NLS-1$
	protected static final String STATIC = "static "; //$NON-NLS-1$
	protected static final String ABSTRACT = "abstract "; //$NON-NLS-1$
	protected static final String FINAL = "final "; //$NON-NLS-1$
	protected static final String VOID = "void"; //$NON-NLS-1$
	protected static final String INT = "int"; //$NON-NLS-1$
	protected static final String BOOLEAN = "boolean"; //$NON-NLS-1$
	protected static final String MAIN_METHOD = "\tpublic static void main(String[] args) {"; //$NON-NLS-1$
	protected static final String TODO_COMMENT = "\t\t// TODO Auto-generated method stub"; //$NON-NLS-1$
	protected static final String RETURN_NULL = "\t\treturn null;"; //$NON-NLS-1$
	protected static final String RETURN_0 = "\t\treturn 0;"; //$NON-NLS-1$
	protected static final String RETURN_FALSE = "\t\treturn false;"; //$NON-NLS-1$

	private List importStatements;

	/**
	 * @param dataModel
	 */
	public NewJavaClassOperation(WTPOperationDataModel dataModel) {
		super(dataModel);
		importStatements = new ArrayList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IFolder sourceFolder = createJavaSourceFolder();
		IPackageFragment pack = createJavaPackage();
		createJavaFile(sourceFolder, pack);
	}

	protected IFolder createJavaSourceFolder() {
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		//IProject project = model.getTargetProject();
		String folderFullPath = model.getStringProperty(NewJavaClassDataModel.SOURCE_FOLDER);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = root.getFolder(new Path(folderFullPath));
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				Logger.getLogger().log(e);
			}
		}
		return folder;
	}

	protected IPackageFragment createJavaPackage() {
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		String packageName = model.getStringProperty(NewJavaClassDataModel.JAVA_PACKAGE);
		IPackageFragmentRoot packRoot = model.getJavaPackageFragmentRoot();
		IPackageFragment pack = packRoot.getPackageFragment(packageName);
		if (pack == null) {
			pack = packRoot.getPackageFragment(""); //$NON-NLS-1$
		}
		// create package fragment if not exists
		if (!pack.exists()) {
			String packName = pack.getElementName();
			try {
				pack = packRoot.createPackageFragment(packName, true, null);
			} catch (JavaModelException e) {
				Logger.getLogger().log(e);
			}
		}
		return pack;
	}

	protected void createJavaFile(IFolder sourceFolder, IPackageFragment pack) {
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		String packageName = model.getStringProperty(NewJavaClassDataModel.JAVA_PACKAGE);
		String className = model.getStringProperty(NewJavaClassDataModel.CLASS_NAME);
		// create java file
		String fileName = className + ".java"; //$NON-NLS-1$
		//ICompilationUnit cu = null;
		try {
			String content = getJavaFileContent(pack, className);
			pack.createCompilationUnit(fileName, content, true, null);
			byte[] contentBytes = content.getBytes();
			IPath packageFullPath = new Path(packageName.replace('.', IPath.SEPARATOR));
			IPath javaFileFullPath = packageFullPath.append(fileName);
			IFile file = sourceFolder.getFile(javaFileFullPath);
			if (file != null && file.exists()) {
				file.setContents(new ByteArrayInputStream(contentBytes), false, true, null);
			} else if (file != null) {
				file.create(new ByteArrayInputStream(contentBytes), false, null);
			}
			//			editModel.getWorkingCopy(cu, true); //Track CU.
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	protected String getJavaFileContent(IPackageFragment pack, String className) {
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		String superclassName = model.getStringProperty(NewJavaClassDataModel.SUPERCLASS);
		List interfaces = (List) model.getProperty(NewJavaClassDataModel.INTERFACES);
		String packageStatement = getPackageStatement(pack);
		setupImportStatements(pack, superclassName, interfaces);
		String classDeclaration = getClassDeclaration(superclassName, className, interfaces);
		String fields = getFields();
		String methods = getMethodStubs(superclassName, className);

		StringBuffer contents = new StringBuffer();
		contents.append(packageStatement);
		String[] importStatementArray = quickSort(importStatements);
		for (int i = 0; i < importStatementArray.length; i++) {
			contents.append(IMPORT + importStatementArray[i] + SEMICOLON);
			contents.append(lineSeparator);
		}
		contents.append(lineSeparator);
		contents.append(classDeclaration);
		contents.append(fields);
		contents.append(methods);
		contents.append(CLOSE_BRA);
		return contents.toString();
	}

	private String getPackageStatement(IPackageFragment pack) {
		StringBuffer sb = new StringBuffer();
		if (!pack.isDefaultPackage()) {
			sb.append(PACKAGE + pack.getElementName() + SEMICOLON);
			sb.append(lineSeparator);
			sb.append(lineSeparator);
		}
		return sb.toString();
	}

	private boolean isSamePackage(IPackageFragment packageFragment, String className) {
		if (className != null && className.length() > 0) {
			String sPackageName = packageFragment.getElementName();
			String classPackageName = Signature.getQualifier(className);
			/*
			 * int index = className.lastIndexOf(DOT); EMPTY_STRING; if (index != -1)
			 * classPackageName = className.substring(0,index);
			 */
			if (classPackageName.equals(sPackageName))
				return true;
		}
		return false;
	}

	private void setupImportStatements(IPackageFragment pack, String superclassName, List interfaces) {
		//NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		if (superclassName != null && superclassName.length() > 0) {
			if (!superclassName.equals(JAVA_LANG_OBJECT) && !isSamePackage(pack, superclassName)) {
				importStatements.add(superclassName);
			}
		}
		if (interfaces != null && interfaces.size() > 0) {
			int size = interfaces.size();
			for (int i = 0; i < size; i++) {
				String interfaceName = (String) interfaces.get(i);
				importStatements.add(interfaceName);
			}
		}
	}

	protected String getClassDeclaration(String superclassName, String className, List interfaces) {
		StringBuffer sb = new StringBuffer();
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		if (model.getBooleanProperty(NewJavaClassDataModel.MODIFIER_PUBLIC))
			sb.append(PUBLIC);
		if (model.getBooleanProperty(NewJavaClassDataModel.MODIFIER_ABSTRACT))
			sb.append(ABSTRACT);
		if (model.getBooleanProperty(NewJavaClassDataModel.MODIFIER_FINAL))
			sb.append(FINAL);
		sb.append(CLASS);
		sb.append(className + SPACE);
		if (superclassName != null && superclassName.length() > 0 && !superclassName.equals(JAVA_LANG_OBJECT)) {
			int index = superclassName.lastIndexOf(DOT);
			if (index != -1)
				superclassName = superclassName.substring(index + 1);
			sb.append(EXTENDS + superclassName + SPACE);
		}
		// interfaces
		if (interfaces != null && interfaces.size() > 0) {
			sb.append(IMPLEMENTS);
			int size = interfaces.size();
			for (int i = 0; i < size; i++) {
				String interfaceName = (String) interfaces.get(i);
				int index = interfaceName.lastIndexOf(DOT);
				if (index != -1)
					interfaceName = interfaceName.substring(index + 1);
				sb.append(interfaceName);
				if (i < size - 1)
					sb.append(COMMA);
				sb.append(SPACE);
			}
		}
		sb.append(OPEN_BRA);
		sb.append(lineSeparator);
		return sb.toString();
	}

	protected String getFields() {
		return EMPTY_STRING;
	}

	private String getMethodStubs(String superclassName, String className) {
		StringBuffer sb = new StringBuffer();
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		IProject project = model.getTargetProject();
		IJavaProject javaProj = ProjectUtilities.getJavaProject(project);
		if (model.getBooleanProperty(NewJavaClassDataModel.MAIN_METHOD)) {
			// Add main method
			sb.append(MAIN_METHOD);
			sb.append(lineSeparator);
			sb.append(TODO_COMMENT);
			sb.append(lineSeparator);
			sb.append(TAB + CLOSE_BRA);
			sb.append(lineSeparator);
			sb.append(lineSeparator);
		}

		IType superClassType = null;
		if (model.getBooleanProperty(NewJavaClassDataModel.CONSTRUCTOR) || model.getBooleanProperty(NewJavaClassDataModel.ABSTRACT_METHODS)) {
			// find super class type
			try {
				superClassType = javaProj.findType(superclassName);
			} catch (JavaModelException e) {
				Logger.getLogger().log(e);
			}
		}
		if (model.getBooleanProperty(NewJavaClassDataModel.CONSTRUCTOR)) {
			// implement constructor from superclass
			try {
				IMethod[] methods = superClassType.getMethods();
				for (int j = 0; j < methods.length; j++) {
					if (methods[j].isConstructor() && !Flags.isPrivate(methods[j].getFlags())) {
						String methodStub = getMethodStub(methods[j], superclassName, className);
						sb.append(methodStub);
					}
				}
			} catch (JavaModelException e) {
				Logger.getLogger().log(e);
			}
		}
		if (model.getBooleanProperty(NewJavaClassDataModel.ABSTRACT_METHODS)) {
			String methodStub = getUnimplementedMethodsFromSuperclass(superClassType, className);
			if (methodStub != null && methodStub.trim().length() > 0)
				sb.append(methodStub);
			methodStub = getUnimplementedMethodsFromInterfaces(superClassType, className, javaProj);
			if (methodStub != null && methodStub.trim().length() > 0)
				sb.append(methodStub);
		}
		String userDefined = getUserDefinedMethodStubs(superClassType);
		if (userDefined != null && userDefined.trim().length() > 0)
			sb.append(userDefined);
		return sb.toString();
	}

	private String getUnimplementedMethodsFromSuperclass(IType superClassType, String className) {
		StringBuffer sb = new StringBuffer();
		try {
			// Implement abstract methods from superclass
			IMethod[] methods = superClassType.getMethods();
			for (int j = 0; j < methods.length; j++) {
				IMethod method = methods[j];
				int flags = method.getFlags();
				if ((Flags.isAbstract(flags) && !Flags.isStatic(flags) && !Flags.isPrivate(flags)) || implementImplementedMethod(methods[j])) {
					String methodStub = getMethodStub(methods[j], superClassType.getFullyQualifiedName(), className);
					sb.append(methodStub);
				}
			}
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
		return sb.toString();
	}

	private String getUnimplementedMethodsFromInterfaces(IType superClassType, String className, IJavaProject javaProj) {
		StringBuffer sb = new StringBuffer();
		NewJavaClassDataModel model = (NewJavaClassDataModel) operationDataModel;
		try {
			// Implement abstract methods from superclass
			List interfaces = (List) model.getProperty(NewJavaClassDataModel.INTERFACES);
			if (interfaces != null) {
				for (int i = 0; i < interfaces.size(); i++) {
					String qualifiedClassName = (String) interfaces.get(i);
					IType interfaceType = javaProj.findType(qualifiedClassName);
					IMethod[] methodArray = interfaceType.getMethods();
					for (int j = 0; j < methodArray.length; j++) {
						if (isMethodImplementedInHierarchy(methodArray[j], superClassType))
							continue;
						String methodStub = getMethodStub(methodArray[j], qualifiedClassName, className);
						sb.append(methodStub);
					}
				}
			}
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
		return sb.toString();
	}

	//	private List setupAllMethodsFromInheritance(IType superClassType, IJavaProject javaProject) {
	//		List methodList = new ArrayList();
	//		try {
	//			IMethod[] methods = superClassType.getMethods();
	//			addMethodArrayToList(methodList, methods);
	//			String superClassName = superClassType.getSuperclassName();
	//			superClassName = processTypeString(superClassName);
	//			while (!superClassName.equals(JAVA_LANG_OBJECT)) {
	//				IType type = javaProject.findType(superClassName);
	//				if (type != null) {
	//					IMethod[] methodArray = type.getMethods();
	//					addMethodArrayToList(methodList, methodArray);
	//				}
	//			}
	//		} catch (JavaModelException e) {
	//			Logger.getLogger().log(e);
	//		}
	//		return methodList;
	//	}

	//	private void addMethodArrayToList(List list, IMethod[] methods) {
	//		// No duplicate
	//		int size = list.size();
	//		int len = methods.length;
	//		for (int m = 0; m < len; m++) {
	//			for (int i = 0; i < size; i++) {
	//				IMethod existMethod = (IMethod) list.get(i);
	//				if (existMethod.equals(methods[m]))
	//					continue;
	//				list.add(methods[m]);
	//			}
	//		}
	//	}

	//	private void addImport(ICompilationUnit cu, String type) {
	//		String simpleName = Signature.getSimpleName(type);
	//		try {
	//			IImportDeclaration importDeclaration = JavaModelUtil.findImport(cu, type);
	//		} catch (JavaModelException ex) {
	//		}
	//	}

	private static boolean isPrimitiveType(String typeName) {
		char first = Signature.getElementType(typeName).charAt(0);
		return (first != Signature.C_RESOLVED && first != Signature.C_UNRESOLVED);
	}

	private String resolveAndAdd(String refTypeSig, IType declaringType) throws JavaModelException {
		String resolvedTypeName = JavaModelUtil.getResolvedTypeName(refTypeSig, declaringType);
		if (resolvedTypeName != null && !importStatements.contains(resolvedTypeName) && !resolvedTypeName.startsWith("java.lang")) { //$NON-NLS-1$
			importStatements.add(resolvedTypeName);
		}
		return Signature.toString(refTypeSig);
	}


	private String getMethodStub(IMethod method, String superClassName, String className) {
		StringBuffer sb = new StringBuffer();
		try {
			IType parentType = method.getDeclaringType();
			String name = method.getElementName();
			String[] paramTypes = method.getParameterTypes();
			String[] paramNames = method.getParameterNames();
			String[] exceptionTypes = method.getExceptionTypes();

			// Parameters String
			String paramString = EMPTY_STRING;
			int nP = paramTypes.length;
			for (int i = 0; i < nP; i++) {
				String type = paramTypes[i];
				// update import statements
				if (!isPrimitiveType(type)) {
					type = resolveAndAdd(type, parentType);
				} else {
					type = Signature.toString(type);
				}

				int index = type.lastIndexOf(DOT);
				if (index != -1)
					type = type.substring(index + 1);
				paramString += type + SPACE + paramNames[i];
				if (i < nP - 1)
					paramString += COMMA + SPACE;
			}
			// Java doc
			sb.append("\t/* (non-Java-doc)"); //$NON-NLS-1$
			sb.append(lineSeparator);
			sb.append("\t * @see "); //$NON-NLS-1$
			sb.append(superClassName + POUND + name + OPEN_PAR);
			sb.append(paramString);
			sb.append(CLOSE_PAR);
			sb.append(lineSeparator);
			sb.append("\t */"); //$NON-NLS-1$
			sb.append(lineSeparator);
			// access
			sb.append(TAB);
			if (Flags.isPublic(method.getFlags()))
				sb.append(PUBLIC);
			else if (Flags.isProtected(method.getFlags()))
				sb.append(PROTECTED);
			else if (Flags.isPrivate(method.getFlags()))
				sb.append(PRIVATE);
			String returnType = null;
			if (method.isConstructor()) {
				sb.append(className);
			} else {
				// return type
				returnType = method.getReturnType();
				if (!isPrimitiveType(returnType)) {
					returnType = resolveAndAdd(returnType, parentType);
				} else {
					returnType = Signature.toString(returnType);
				}
				int idx = returnType.lastIndexOf(DOT);
				if (idx == -1)
					sb.append(returnType);
				else
					sb.append(returnType.substring(idx + 1));
				sb.append(SPACE);
				// name
				sb.append(name);
			}
			// Parameters
			sb.append(OPEN_PAR + paramString + CLOSE_PAR);
			// exceptions
			int nE = exceptionTypes.length;
			if (nE > 0) {
				sb.append(SPACE + THROWS);
				for (int i = 0; i < nE; i++) {
					String type = exceptionTypes[i];
					if (!isPrimitiveType(type)) {
						type = resolveAndAdd(type, parentType);
					} else {
						type = Signature.toString(type);
					}
					int index = type.lastIndexOf(DOT);
					if (index != -1)
						type = type.substring(index + 1);
					sb.append(type);
					if (i < nE - 1)
						sb.append(COMMA + SPACE);
				}
			}
			sb.append(SPACE + OPEN_BRA);
			sb.append(lineSeparator);
			if (method.isConstructor()) {
				sb.append(TAB + TAB + SUPER + OPEN_PAR);
				for (int i = 0; i < nP; i++) {
					sb.append(paramNames[i]);
					if (i < nP - 1)
						sb.append(COMMA + SPACE);
				}
				sb.append(CLOSE_PAR + SEMICOLON);
				sb.append(lineSeparator);
			} else {
				String methodBody = getMethodBody(method, returnType);
				sb.append(methodBody);
			}
			sb.append(TAB + CLOSE_BRA);
			sb.append(lineSeparator);
			sb.append(lineSeparator);
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
		return sb.toString();
	}

	protected String processTypeString(String typeString) {
		return Signature.toString(typeString);
	}


	//	private String getModifiersString(int modifiers) {
	//		String ret = EMPTY_STRING;
	//		if (Modifier.isPublic(modifiers)) {
	//			ret += PUBLIC;
	//		}
	//		if (Modifier.isFinal(modifiers)) {
	//			ret += FINAL;
	//		}
	//		return ret;
	//	}

	private boolean isMethodImplementedInHierarchy(IMethod method, IType superClass) {
		boolean ret = false;
		IMethod foundMethod = findMethodImplementationInHierarchy(method, superClass);
		if (foundMethod != null && foundMethod.exists() && !implementImplementedMethod(method))
			ret = true;
		return ret;
	}

	private IMethod findMethodImplementationInHierarchy(IMethod method, IType superClass) {
		IMethod implementedMethod = null;
		try {
			if (superClass != null && superClass.exists()) {
				ITypeHierarchy tH = superClass.newSupertypeHierarchy(new NullProgressMonitor());
				implementedMethod = findMethodImplementationInHierarchy(tH, superClass, method.getElementName(), method.getParameterTypes(), method.isConstructor());
			}
		} catch (JavaModelException e) {
		}
		return implementedMethod;
	}

	private IMethod findMethodImplementationInHierarchy(ITypeHierarchy tH, IType thisType, String methodName, String parameterTypes[], boolean isConstructor) throws JavaModelException {
		IMethod found = JavaModelUtil.findMethod(methodName, parameterTypes, isConstructor, thisType);
		if (found != null && !Flags.isAbstract(found.getFlags())) {
			return found;
		}
		return JavaModelUtil.findMethodImplementationInHierarchy(tH, thisType, methodName, parameterTypes, isConstructor);
	}

	private String[] quickSort(List list) {
		int size = list.size();
		if (size == 0)
			return new String[0];
		String[] array = new String[size];
		for (int i = 0; i < size; i++) {
			array[i] = (String) list.get(i);
		}
		quickSort(array, 0, size - 1);
		return array;
	}

	/**
	 * Sort the strings in the given collection.
	 */
	private void quickSort(String[] sortedCollection, int left, int right) {
		int original_left = left;
		int original_right = right;
		String mid = sortedCollection[(left + right) / 2];
		do {
			while (sortedCollection[left].compareTo(mid) < 0) {
				left++;
			}
			while (mid.compareTo(sortedCollection[right]) < 0) {
				right--;
			}
			if (left <= right) {
				String tmp = sortedCollection[left];
				sortedCollection[left] = sortedCollection[right];
				sortedCollection[right] = tmp;
				left++;
				right--;
			}
		} while (left <= right);
		if (original_left < right) {
			quickSort(sortedCollection, original_left, right);
		}
		if (left < original_right) {
			quickSort(sortedCollection, left, original_right);
		}
	}

	// Can be overrided
	protected String getMethodBody(IMethod method, String returnType) {
		String body = TODO_COMMENT;
		body += lineSeparator;
		if (returnType == null || returnType.equals(VOID))
			return body;
		if (returnType.equals(INT))
			body += RETURN_0;
		else if (returnType.equals(BOOLEAN))
			body += RETURN_FALSE;
		else
			body += RETURN_NULL;
		body += lineSeparator;
		return body;
	}

	// Can be overrided
	protected String getUserDefinedMethodStubs(IType superClassType) {
		return EMPTY_STRING;
	}

	// Can be overrided
	protected boolean implementImplementedMethod(IMethod method) {
		return false;
	}
}