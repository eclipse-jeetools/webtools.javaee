package org.eclipse.jst.validation.test.fwk.validator;

import java.util.logging.Level;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.internal.plugin.JavaEMFNature;
import org.eclipse.jem.java.JavaHelpers;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.jst.validation.test.fwk.TestOpConstrInputOperation;
import org.eclipse.jst.validation.test.internal.registry.BVTValidationRegistryReader;
import org.eclipse.jst.validation.test.internal.registry.ValidatorTestcase;


/**
 * This class contains some utility methods used to 
 * access, manipulate, etc. jdt types.
 */
public final class JDTUtility {
	private JDTUtility() {
		// Do not need any instances of this class.
	}
	
	/**
	 * Return the containers in the given project that contain either
	 * .java source files or .class binary files.
	 */
	public static IContainer[] getJavaContainers(IJavaProject jp) {
		if (jp == null) {
			return null;
		}

		IProject project = jp.getProject();
		IClasspathEntry[] classpath = null;
		try {
			classpath = jp.getResolvedClasspath(true); // true means ignore unresolved (missing) variables, instead of throwing an exception
		}
		catch (JavaModelException exc) {
			exc.printStackTrace();
			return null;
		}

		if (classpath == null) {
			return null;
		}

		// Traverse the classpath, and calculate a list of just the 
		// IFolders and IProjects (i.e., IContainers) that contain source
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IContainer[] icontainers = new IContainer[classpath.length];
		int validCount = 0;
		for (int i = 0; i < classpath.length; i++) {
			IClasspathEntry entry = classpath[i];
			if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IResource res = root.findMember(entry.getPath());
				if(res == null) {
					// not in the workspace
					continue;
				}

				if(!res.getProject().equals(project)) {
					// in a different project
					continue;
				}				

				if (res instanceof IContainer) {
					icontainers[validCount++] = (IContainer) res;
				}
			}
		}

		try {
			IContainer outputContainer = (IContainer)root.findMember(jp.getOutputLocation());
			IContainer[] containers = null;
			if(outputContainer == null) {
				containers = new IContainer[validCount];
			}
			else if(outputContainer.getProject().equals(project)) {
				containers = new IContainer[validCount+1];
				containers[validCount] = outputContainer;
			}
			else {
				containers = new IContainer[validCount];
			}
			System.arraycopy(icontainers, 0, containers, 0, validCount);
			return containers;
		}
		catch(JavaModelException exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return the IType that represents this file, or null if none exists.
	 */
	public static IType getType(IContainer[] javaContainers, IFile file) {
		if(file == null) {
			return null;
		}
		
		String fileExtension = file.getFileExtension();
		if(fileExtension == null) {
			// not a .java or a .class file
			return null;
		}
		
		if(!(fileExtension.equals("java") || fileExtension.equals("class"))) { //$NON-NLS-1$ //$NON-NLS-2$
			return null;
		}
		
		IProject project = file.getProject();
		IJavaProject jp = JavaCore.create(project);
		if(jp == null) {
			return null;
		}
		
		try {
			IPath relativePath = getRelativePath(javaContainers, file);
			if(relativePath == null) {
				// Not a member of a source or output container
				return null;
			}

			IPath packagePath = relativePath.removeLastSegments(1);
			String packageName = packagePath.toString().replace(IPath.SEPARATOR, '.');
			String typeName = relativePath.lastSegment();
			typeName = typeName.substring(0, typeName.length() - fileExtension.length() - 1);
			String qualifiedName = null;
			if (packageName.length() > 0) {
				qualifiedName = packageName + "." + typeName; //$NON-NLS-1$
			} else {
				qualifiedName = typeName;
			}
			return jp.findType(qualifiedName);
		}
		catch(JavaModelException exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Return the IPath of the resource relative to the first container that the
	 * resource is found in. If the resource is contained in more than one container
	 * in the array, return the first IPath found. If the resource is not a member
	 * of any of the containers, return null.
	 */
	public static IPath getRelativePath(IContainer[] containers, IResource resource) {
		for(int i=0; i<containers.length; i++) {
			IPath path = getRelativePath(containers[i], resource);
			if(path != null) {
				return path;
			}
		}
		return null;
	}
	
	/**
	 * Returns the IPath of a resource relative to the container.
	 * If the IResource is not a member of the container, return null. 
	 */
	public static IPath getRelativePath(IContainer container, IResource resource) {
		if ((resource == null) || (container == null)) {
			return null;
		}

		// Is the path part of the IContainer?
		int matchingFirstSegments = container.getFullPath().matchingFirstSegments(resource.getFullPath());
		if(matchingFirstSegments == 0) {
			return null;
		}
		return resource.getFullPath().removeFirstSegments(matchingFirstSegments);
	}
	
	public static JavaHelpers getJavaHelpers(IFile file) {
		IType type = getType(file);
		if(type == null) {
			return null;
		}
		
		return getJavaHelpers(type);
	}
	
	public static IType getType(IFile file) {
		IJavaProject jp = JavaCore.create(file.getProject());
		IContainer[] javaContainers = (jp == null) ? null : JDTUtility.getJavaContainers(jp);
		if(javaContainers != null) {
			return JDTUtility.getType(javaContainers, file);
		}
		return null;
	}

	public static JavaHelpers getJavaHelpers(IType type) {
		ResourceSet resourceSet = getResourceSet(type);
		if(resourceSet == null) {
			return null;
		}
		return JavaRefFactory.eINSTANCE.reflectType(type.getFullyQualifiedName(), resourceSet);
	}
	
	public static ResourceSet getResourceSet(IType type) {
		JavaEMFNature nature = getNature(type);
		if(nature == null) {
			return null;
		}
		return nature.getResourceSet();
	}

	public static JavaEMFNature getNature(IType type) {
		IJavaProject jp = type.getJavaProject();
		IProject project = jp.getProject();
		
		try {
			JavaEMFNature nature = JavaEMFNature.createRuntime(project);
			return nature;
		}
		catch(CoreException exc) {
			exc.printStackTrace();
			return null;
		}
	}

	public static IResource getResource(IProject project, JavaHelpers clazz) {
		IType type = getType(project, clazz);
		return type.getResource();
	}

	public static IType getType(IProject project, JavaHelpers clazz) {
		if(clazz == null) {
			return null;
		}
		
		String fullyQualifiedName = clazz.getJavaName();
		IJavaProject jp = JavaCore.create(project);
		try {
			return jp.findType(fullyQualifiedName);
		}
		catch(JavaModelException exc) {
			exc.printStackTrace();
			return null;
		}
	}

	/**
	 * Return the three validator test cases used to test the validation framework.
	 */
	public static ValidatorTestcase[] getVFTests(IProgressMonitor monitor, IProject project) {
		ValidatorTestcase[] tmds = BVTValidationRegistryReader.getReader().getAllValidatorTests(monitor, project);
		if((tmds == null) || (tmds.length == 0)) {
			return new ValidatorTestcase[0];
		}
		
		ValidatorTestcase fwkNobuildTestTMD = null;
		ValidatorTestcase fwkTestTMD = null;
		ValidatorTestcase propTMD = null;
		ValidatorTestcase[] result = new ValidatorTestcase[3];
		int count = 0;
		for(int i=0; i<tmds.length; i++) {
			ValidatorTestcase tmd = tmds[i];
			if(tmd.getValidatorClass().equals(TestOpConstrInputOperation.FWK_TEST_VALIDATOR_CLASS)) {
				fwkTestTMD = tmd;
				result[count++] = tmd;
			}
			else if(tmd.getValidatorClass().equals(TestOpConstrInputOperation.PROPERTIES_VALIDATOR_CLASS)) {
				propTMD = tmd;
				result[count++] = tmd;
			}
			else if(tmd.getValidatorClass().equals(TestOpConstrInputOperation.FWK_NOBUILD_TEST_VALIDATOR_CLASS)) {
				fwkNobuildTestTMD = tmd;
				result[count++] = tmd;
			}
			
			if((fwkTestTMD != null) && (propTMD != null) && (fwkNobuildTestTMD != null)) {
				break;
			}
		}
		
		if(count != result.length) {
			// On this project, not all of the test validators are used.
			ValidatorTestcase[] r = new ValidatorTestcase[count];
			System.arraycopy(result, 0, r, 0, count);
			return r;
		}

		return result;
	}

	public static void setAutoBuild(boolean autoBuildEnabled) {
		try {
			IWorkspaceDescription desc = ResourcesPlugin.getWorkspace().getDescription();
			desc.setAutoBuilding(autoBuildEnabled);
			ResourcesPlugin.getWorkspace().setDescription(desc);
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
}
