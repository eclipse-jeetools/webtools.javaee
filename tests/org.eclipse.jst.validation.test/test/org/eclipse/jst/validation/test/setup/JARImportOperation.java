package org.eclipse.jst.validation.test.setup;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.validation.test.BVTValidationPlugin;
import org.eclipse.wst.validation.internal.operations.ValidatorManager;

/**
 * Create a Java project by importing a JAR file.
 */
public class JARImportOperation extends AImportOperation {
	// Most of the code in this class was stolen from the JDT. See org.eclipse.jdt.internal.ui.wizards.buildpaths.BuildPathsBlock.
	public static void createProject(IProject project, IPath locationPath, IProgressMonitor monitor) throws CoreException {
		if (monitor == null) {
			monitor= new NullProgressMonitor();
		}				

		// create the project
		try {
			if (!project.exists()) {
				IProjectDescription desc= project.getWorkspace().newProjectDescription(project.getName());
				if (Platform.getLocation().equals(locationPath)) {
					locationPath= null;
				}
				desc.setLocation(locationPath);
				project.create(desc, monitor);
				monitor= null;
			}
			if (!project.isOpen()) {
				project.open(monitor);
				monitor= null;
			}
		} finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	public static void addJavaNature(IProject project, IProgressMonitor monitor) throws CoreException {
		if (!project.hasNature(JavaCore.NATURE_ID)) {
			IProjectDescription description = project.getDescription();
			String[] prevNatures= description.getNatureIds();
			String[] newNatures= new String[prevNatures.length + 1];
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			newNatures[prevNatures.length]= JavaCore.NATURE_ID;
			description.setNatureIds(newNatures);
			project.setDescription(description, monitor);
		} else {
			monitor.worked(1);
		}
	}
	
	private void configureProject(IProgressMonitor monitor, IProject project) {
		try {
			// Set the classpath to the default.
			IJavaProject jp = JavaCore.create(project);
			jp.setRawClasspath(null, new SubProgressMonitor(monitor, 7));		

			// Now add rt.jar to it
			IClasspathEntry[] existingClasspath = jp.getRawClasspath();
			IClasspathEntry[] classpath= new IClasspathEntry[existingClasspath.length + 1];
			System.arraycopy(existingClasspath, 0, classpath, 0, existingClasspath.length);
			IClasspathEntry rtJar = JavaCore.newVariableEntry(new Path("JRE_LIB"), new Path("JRE_SRC"), null); //$NON-NLS-1$ //$NON-NLS-2$
			classpath[existingClasspath.length] = rtJar;
			jp.setRawClasspath(classpath, new SubProgressMonitor(monitor, 7));
			
			// Enable this java project with the Validation Builder so that automatic validation 
			// can run on it.
			ValidatorManager.addProjectBuildValidationSupport(project);
		}
		catch(JavaModelException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
	}
	
	/**
	 * @see org.eclipse.jst.validation.test.setup.AImportOperation#createNewProject(IProgressMonitor, String, File)
	 */
	protected IProject createNewProject(IProgressMonitor monitor, String projectName, File inputFile) {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		try {
			createProject(project, null, monitor);
			addJavaNature(project, monitor);
			configureProject(monitor, project);
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
		}
		return project;
	}
	
	protected void createFolder(IProgressMonitor monitor, IProject project, IPath pathname) throws CoreException {
		IFolder folder = project.getFolder(pathname);
		IContainer container = folder.getParent();
		if(!container.exists()) {
			createFolder(monitor, project, container.getProjectRelativePath());
		}
		if(!folder.exists()) {
			// Don't overwrite existing files.
			folder.create(true, true, monitor); // true=force, true=local
		}
	}
	
	protected void createFile(IProgressMonitor monitor, IProject project, IPath pathname, InputStream stream) throws CoreException {
		IFile nfile = project.getFile(pathname);
		IContainer container = nfile.getParent();
		if(!container.exists()) {
			createFolder(monitor, project, container.getProjectRelativePath());
		}
		if(!nfile.exists()) {
			// Don't overwrite existing files.
			nfile.create(stream, true, monitor);
		}
	}
	
	/**
	 * @see org.eclipse.jst.validation.test.setup.AImportOperation#importFile(IProgressMonitor, IProject, File)
	 */
	protected boolean importFile(IProgressMonitor monitor, IProject project, File file) {
		// Import the file 
		try {
			ZipFile zipFile = new ZipFile(file);
			Enumeration zipEntries = zipFile.entries();
			while(zipEntries.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry)zipEntries.nextElement();
				IPath pathname = new Path(zipEntry.getName());
				if(zipEntry.isDirectory()) {
					createFolder(monitor, project, pathname);
				}
				else {
					createFile(monitor, project, pathname, zipFile.getInputStream(zipEntry));
				}
			}
			return true;
		}
		catch(IOException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return false;
		}
		catch(CoreException exc) {
			Logger logger = BVTValidationPlugin.getPlugin().getMsgLogger();
			if(logger.isLoggingLevel(Level.SEVERE)) {
				logger.write(Level.SEVERE, exc);
			}
			return false;
		}
	}

}
