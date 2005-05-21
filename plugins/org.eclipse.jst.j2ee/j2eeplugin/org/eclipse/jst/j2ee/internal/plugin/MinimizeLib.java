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
package org.eclipse.jst.j2ee.internal.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageDeclaration;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.J2EENature;

/**
 * Minimize the number of loose class files from an unzipped class library by removing ones
 * corresponding to Java source files.
 */
public class MinimizeLib {

	static boolean DEBUG = true;

	/**
	 * Helper class for discarding class files for which there is corresponding source. Note: this
	 * could be a fairly expensive operation.
	 * <p>
	 * Assumptions:
	 * <ul>
	 * <li>The source files are in the source folders of the project.</li>
	 * <li>The source folders are source package fragment roots. This allows us to use the Java
	 * model to find and access source files.</li>
	 * <li>The class files are in the "imported_classes" root folder of the project.</li>
	 * <li>We want to delete any class files in the "imported_classes" folder for which there is
	 * corresponding source file in any source folder.</li>
	 * </ul>
	 * </p>
	 * 
	 * @param project
	 *            the Java project to minimize
	 */
	public static void minimize(IJavaProject project) {

		if (DEBUG) {
			System.out.println(J2EEPluginResourceHandler.getString("Minimizing_project_UI_") + project.getElementName()); //$NON-NLS-1$
		}

		final IFolder classesFolder = project.getProject().getFolder(LibCopyBuilder.IMPORTED_CLASSES_PATH);

		if (!classesFolder.exists()) {
			// no classes folder means nothing to prune
			if (DEBUG) {
				System.out.println(J2EEPluginResourceHandler.getString("No_library_folder_UI_") + classesFolder.getFullPath()); //$NON-NLS-1$
			}
			return;
		}

		// List of fully qualified type names for which we have source
		// (element type: String)
		final Set sourceTypeNames = new HashSet(1000);

		J2EENature nature = J2EENature.getRegisteredRuntime(project.getProject());
		if (nature == null) {
			// not a valid project to build
			if (DEBUG) {
				System.out.println(J2EEPluginResourceHandler.getString("Not_a_J2EE_project_UI_") + project.getProject()); //$NON-NLS-1$
			}
			return;
		}
		List sourceFolders = JemProjectUtilities.getSourceContainers(project.getProject());
		for (Iterator iter = sourceFolders.iterator(); iter.hasNext();) {
			IFolder srcFolder = (IFolder) iter.next();
			// use Java model to rip through sources to get list of type names
			try {
				IPackageFragmentRoot srcRoot = project.getPackageFragmentRoot(srcFolder);
				IJavaElement[] pkgs = srcRoot.getChildren();
				for (int i = 0; i < pkgs.length; i++) {
					if (pkgs[i] instanceof IPackageFragment) {
						IPackageFragment pkg = (IPackageFragment) pkgs[i];
						ICompilationUnit[] cus = pkg.getCompilationUnits();
						for (int j = 0; j < cus.length; j++) {
							ICompilationUnit cu = cus[j];
							sourceTypeNames.addAll(Arrays.asList(extractTypeNames(cu)));
						}
					}
				}
			} catch (JavaModelException e) {
				// unexpected
				e.printStackTrace();
			}

		}

		if (sourceTypeNames.isEmpty()) {
			// no source types means no pruning possible
			if (DEBUG) {
				System.out.println(J2EEPluginResourceHandler.getString("No_source_types_UI_")); //$NON-NLS-1$
			}
			return;
		}

		// verify that none of the type names are problematic (contain "$")
		// if the name contains '$' we can't pattern match class file names
		for (Iterator it = sourceTypeNames.iterator(); it.hasNext();) {
			String sourceTypeName = (String) it.next();
			if (sourceTypeName.indexOf('$') >= 0) {
				// we're in trouble
				throw new RuntimeException(J2EEPluginResourceHandler.getString("Some_source_types_have___$___in_their_name_ERROR_")); //$NON-NLS-1$
			}
		}

		// walk the classes folder deleting class files for which there is source
		class Visitor implements IResourceVisitor {
			public boolean visit(IResource res) throws CoreException {
				if (res.getType() == IResource.FILE) {
					IFile file = (IFile) res;
					String ext = res.getFileExtension();
					if (ext != null && ext.equals("class")) { //$NON-NLS-1$
						IPath pkgPath = file.getFullPath().removeFirstSegments(2).removeLastSegments(1);
						String pkgName = pkgPath.toString().replace('/', '.');
						String baseTypeName = pkgName.length() == 0 ? baseTypeName(file) : pkgName + "." + baseTypeName(file); //$NON-NLS-1$
						if (DEBUG) {
							System.out.println("Have source for " + baseTypeName + "? " + (sourceTypeNames.contains(baseTypeName) ? "Yes" : "No")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
						}
						if (sourceTypeNames.contains(baseTypeName)) {
							deleteClassFile(file);
						}
					}
				}
				return true;
			}
		}

		try {
			classesFolder.accept(new Visitor());
		} catch (CoreException e) {
			// should not happen
			e.printStackTrace();
		}
	}

	/**
	 * Deletes the given class file resource. Does nothing if unable to delete it.
	 * 
	 * @param classFile
	 *            the class file resource to delete
	 */
	static void deleteClassFile(IFile classFile) {
		if (DEBUG) {
			System.out.println("Delete " + classFile.getFullPath()); //$NON-NLS-1$
		}
		try {
			classFile.delete(true, false, (IProgressMonitor) null);
		} catch (CoreException e) {
			// unexpected
			e.printStackTrace();
		}
	}

	/**
	 * Returns the base type name for the given class file, assuming the standard scheme Java
	 * compilers use to name generated class files.
	 * <p>
	 * For example,
	 * <ul>
	 * <li>file <code>Foo.class</code> returns <code>"Foo"</code></li>
	 * <li>file <code>Foo$1.class</code> returns <code>"Foo"</code></li>
	 * <li>file <code>Foo$Bar.class</code> returns <code>"Foo"</code></li>
	 * <li>file <code>Foo$1$Bar.class</code> returns <code>"Foo"</code></li>
	 * </ul>
	 * 
	 * @param classFile
	 *            the class file
	 * @return the name of the corresponding top-level type
	 */
	static String baseTypeName(IFile classFile) {
		String fileName = classFile.getName();
		int x = fileName.lastIndexOf(".class"); //$NON-NLS-1$
		if (x < 0) {
			throw new IllegalArgumentException();
		}
		// strip off .class suffix
		String binaryTypeName = fileName.substring(0, x);
		int d = binaryTypeName.indexOf("$"); //$NON-NLS-1$
		if (d < 0)
			return binaryTypeName;
		// the characters before the '$' is the top-level type name
		return binaryTypeName.substring(0, d);
	}

	/**
	 * Returns a list of fully-qualifed names of top-level types declared in the given compilation
	 * unit. Returns the empty list if the compilation unit is empty or could not be parsed.
	 * 
	 * @param cu
	 *            the Java compilation unit
	 * @return the list of fully-qualified names of package-member types
	 */
	static String[] extractTypeNames(ICompilationUnit cu) {
		List typeNames = new ArrayList();
		try {
			IPackageDeclaration[] pds = cu.getPackageDeclarations();
			String packageName = pds.length == 0 ? "" : pds[0].getElementName(); //$NON-NLS-1$
			IType[] types = cu.getTypes();
			for (int k = 0; k < types.length; k++) {
				IType type = types[k];
				String name = type.getElementName();
				String fqTypeName = packageName.length() == 0 ? name : packageName + "." + name; //$NON-NLS-1$
				typeNames.add(fqTypeName);
			}
		} catch (JavaModelException e) {
			// unexpected
			e.printStackTrace();
		}
		String[] result = new String[typeNames.size()];
		typeNames.toArray(result);
		return result;
	}

}