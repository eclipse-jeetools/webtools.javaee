package org.eclipse.jst.j2ee.internal.plugin;

/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002 - All Rights Reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.javaeditor.EditorUtility;
import org.eclipse.jem.internal.adapters.jdom.JavaClassJDOMAdaptor;
import org.eclipse.jem.internal.java.adapters.ReadAdaptor;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaPackage;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;

/*
 * Licensed Materials - Property of IBM,
 * WebSphere Studio Workbench
 * (c) Copyright IBM Corp 1999, 2000
 */

/**
 * A number of routines for working with JavaElements in editors
 * 
 * Use 'isOpenInEditor' to test if an element is already open in a editor Use 'openInEditor' to
 * force opening an element in a editor With 'getWorkingCopy' you get the working copy (element in
 * the editor) of an element
 */
public class J2EEEditorUtility {
	// //$NON-NLS-1$
	public static final String EJB_JAVA_EDITOR_ID = "org.eclipse.jst.j2ee.internal.internal.ejb.ui.misc.EJBJavaEditor"; //$NON-NLS-1$

	public static ICompilationUnit getCompilationUnit(JavaClass javaClass) {
		IProject project = ProjectUtilities.getProject(javaClass);
		if (project == null)
			return null;
		return getCompilationUnit(javaClass, project);
	}

	public static ICompilationUnit getCompilationUnit(JavaClass javaClass, IProject project) {
		if (javaClass == null)
			return null;
		IJavaProject javaProj = getJavaProject(project);
		if (javaProj == null)
			return null;
		return getCompilationUnit(javaClass, javaProj);
	}

	public static ICompilationUnit getCompilationUnit(JavaClass javaClass, IJavaProject javaProject) {
		if (javaClass == null)
			return null;
		IType type = getType(javaClass);
		if (type != null)
			return type.getCompilationUnit();
		return null;
	}

	public static IType getType(JavaClass javaClass) {
		if (javaClass != null) {
			JavaClassJDOMAdaptor adaptor = (JavaClassJDOMAdaptor) EcoreUtil.getRegisteredAdapter(javaClass, ReadAdaptor.TYPE_KEY);
			if (adaptor != null)
				return adaptor.getSourceType();
		}
		return null;
	}

	public static IEditorInput getEditorInput(Object input) {
		if (input instanceof EObject)
			return new FileEditorInput(WorkbenchResourceHelper.getFile((EObject) input));
		if (input instanceof IFile)
			return new FileEditorInput((IFile) input);
		return null;
	}

	public static IFile getFile(JavaClass javaClass) {
		ICompilationUnit comp = getCompilationUnit(javaClass);
		if (comp == null)
			return null;
		IEditorInput input = null;
		try {
			input = EditorUtility.getEditorInput(comp);
		} catch (JavaModelException ex) {
			return null;
		}
		if (input != null) {
			return (IFile) input.getAdapter(IFile.class);
		}
		return null;
	}

	public static IJavaProject getJavaProject(IProject aProject) {
		if (aProject == null)
			return null;
		IJavaModel javaModel = JavaProjectUtilities.getJavaModel();
		return javaModel.getJavaProject(aProject.getName());
	}

	/**
	 * Return the IPackageFragment for the JavaPackage for
	 * 
	 * @javaClass.
	 */
	public static IPackageFragment getPackageFragment(JavaClass javaClass, IJavaProject javaProject) {
		if (javaProject != null) {
			try {
				JavaPackage javaPackage = (JavaPackage) javaClass.eContainer();
				String packageName = javaPackage.getName();
				IPackageFragment[] pkgFrags = javaProject.getPackageFragments();
				for (int i = 0; i < pkgFrags.length; i++) {
					if ((pkgFrags[i].getElementName().equals(packageName)))
						return pkgFrags[i];
				}
			} catch (JavaModelException e) {
				//Ignore
			}
		}
		return null;
	}

	/**
	 * Opens a Java editor for the given element if the element is a Java compilation unit or a Java
	 * class file.
	 * 
	 * @return the IEditorPart or null if wrong element type or opening failed
	 */
	public static IEditorPart openInEditor(JavaClass javaClass, IProject aProject) throws JavaModelException, PartInitException {
		return openInEditor(javaClass, getJavaProject(aProject));
	}

	/**
	 * Opens a Java editor for the given element if the element is a Java compilation unit or a Java
	 * class file.
	 * 
	 * @return the IEditorPart or null if wrong element type or opening failed
	 */
	public static IEditorPart openInEditor(JavaClass javaClass, IJavaProject javaProject) throws JavaModelException, PartInitException {
		if (javaClass == null || javaProject == null)
			return null;
		IType type = getType(javaClass);
		if (type == null)
			return openInEditor(getCompilationUnit(javaClass, javaProject));
		return EditorUtility.openInEditor(type);
	}


	/**
	 * Opens a Java editor for the given element if the element is a Java compilation unit or a Java
	 * class file.
	 * 
	 * @return the IEditorPart or null if wrong element type or opening failed
	 */
	public static IEditorPart openInEditor(JavaClass javaClass, IJavaProject javaProject, String editorId) throws JavaModelException, PartInitException {
		return openInEditor(getCompilationUnit(javaClass, javaProject), editorId);
	}

	/**
	 * Opens a Java editor for the given element if the element is a Java compilation unit or a Java
	 * class file.
	 * 
	 * @return the IEditorPart or null if wrong element type or opening failed
	 */
	public static IEditorPart openInEditor(ICompilationUnit aCompilationUnit) throws JavaModelException, PartInitException {
		return EditorUtility.openInEditor(aCompilationUnit);
	}

	/**
	 * Opens a Java editor for the given element if the element is a Java compilation unit or a Java
	 * class file.
	 * 
	 * @return the IEditorPart or null if wrong element type or opening failed
	 */
	public static IEditorPart openInEditor(ICompilationUnit aCompilationUnit, String editorId) throws JavaModelException, PartInitException {
		return openInEditor(EditorUtility.getEditorInput(aCompilationUnit), editorId);
	}

	private static IEditorPart openInEditor(IEditorInput input, String editorID) throws PartInitException {
		if (input != null) {
			IWorkbenchPage p = J2EEUIPlugin.getActiveWorkbenchWindow().getActivePage();
			if (p != null)
				return p.openEditor(input, editorID, true);
		}
		return null;
	}
}