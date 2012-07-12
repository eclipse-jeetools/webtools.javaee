package org.eclipse.wtp.j2ee.headless.tests.j2ee.operations;

import java.io.FileNotFoundException;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;

public class JavaFileTestingUtilities {
	protected static final int MAX_FILE_CREATION_WAIT = 3000;
	
	private static final String WAR_EXTENSION = "war";
	private static final String JAR_EXTENSION = "jar";
	private static final String RAR_EXTENSION = "rar";
	
	private static final String WEB_PACKAGE = "webSrc";
	private static final String APP_CLIENT_PACKAGE = "appClientSrc";
	private static final String EJB_PACKAGE = "ejbSrc";
	private static final String CONNECTOR_PACKAGE = "connectorSrc";
	private static final String UTILITY_PACKAGE = "utilitySrc";
	
	//key is project name, value is array of java class names added to that project
	private static Map<String, String[]> webProjectJavaFiles = new HashMap<String, String[]>();
	private static Map<String, String[]> appClientProjectJavaFiles = new HashMap<String, String[]>();
	private static Map<String, String[]> ejbProjectJavaFiles = new HashMap<String, String[]>();
	private static Map<String, String[]> connectorProjectJavaFiles = new HashMap<String, String[]>();
	private static Map<String, String[]> utilityProjectJavaFiles = new HashMap<String, String[]>();
	
	public static void addJavaFilesToAppClient(String projectName, String[] classNames, String prackageName) throws Exception {
		IVirtualComponent projVirtComp = ComponentUtilities.getComponent(projectName);		
		IVirtualFolder virtRootFolder = projVirtComp.getRootFolder();
    	addJavaFilesToSrcFolder(virtRootFolder, classNames, prackageName, null);
	}
	
	public static void addJavaFilesToEJB(String projectName, String[] classNames, String prackageName) throws Exception {
		IVirtualComponent projVirtComp = ComponentUtilities.getComponent(projectName);		
		IVirtualFolder virtRootFolder = projVirtComp.getRootFolder();
    	addJavaFilesToSrcFolder(virtRootFolder, classNames, prackageName, null);
	}
	
	public static void addJavaFileToEJB(String projectName, String className, String packageName, String classContents) throws Exception {
		IVirtualComponent projVirtComp = ComponentUtilities.getComponent(projectName);		
		IVirtualFolder virtRootFolder = projVirtComp.getRootFolder();
    	addJavaFilesToSrcFolder(virtRootFolder, new String [] {className }, packageName, classContents);
	}
	
	public static void addJavaFilesToWeb(String projectName, String[] classNames, String prackageName) throws Exception {
		IProject proj = J2EEProjectUtilities.getProject(projectName);
		
		//for web projects the default src directory is not in the root folder
		List <IContainer> sourceContainers = JavaLiteUtilities.getJavaSourceContainers(ComponentCore.createComponent(proj));
		Assert.assertTrue("Project should have at least one source root", sourceContainers.size() > 0);
		IFolder srcFolder = (IFolder)sourceContainers.get(0);
		addJavaFilesToSrcFolder(srcFolder, classNames, prackageName, null);
	}
	
	public static void addJavaFilesToConnector(String projectName, String[] classNames, String prackageName) throws Exception {
		IVirtualComponent projVirtComp = ComponentUtilities.getComponent(projectName);		
		IVirtualFolder virtRootFolder = projVirtComp.getRootFolder();
    	addJavaFilesToSrcFolder(virtRootFolder, classNames, prackageName, null);
	}
	
	public static void addJavaFilesToUtility(String projectName, String[] classNames, String prackageName) throws Exception {
		IVirtualComponent projVirtComp = ComponentUtilities.getComponent(projectName);		
		IVirtualFolder virtRootFolder = projVirtComp.getRootFolder();
    	addJavaFilesToSrcFolder(virtRootFolder, classNames, prackageName, null);
	}
	
	public static void verifyJavaFilesInJAR(String archivePath, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		IArchive archive = null;
		
		try {
			archive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(archivePath));
			verifyJavaFilesInJAR(archive, classNames, packageName, withClassFiles, withSource);
		} finally {
			if(archive != null) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
	}
	
	public static void verifyJavaFilesInWAR(String archivePath, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		IArchive archive = null;
		
		try {
			archive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(archivePath));
			verifyJavaFilesInWAR(archive, classNames, packageName, withClassFiles, withSource);
		} finally {
			if(archive != null) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
	}
	
	public static void verifyJavaFilesInRAR(String archivePath, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		IArchive archive = null;
		
		try {
			archive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(archivePath));
			verifyJavaFilesInRAR(archive, classNames, packageName, withClassFiles, withSource);
		} finally {
			if(archive != null) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
	}
	
	public static void verifyJavaFilesInJAR(IArchive archive, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		String srcDirectoryPath = packageName + java.io.File.separatorChar;
		verifyJavaFilesExported(archive, srcDirectoryPath, classNames, withClassFiles, withSource);
	}
	
	public static void verifyJavaFilesInWAR(IArchive archive, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		String srcDirectoryPath = "WEB-INF" + java.io.File.separatorChar + "classes"+ java.io.File.separatorChar + packageName + java.io.File.separatorChar;
		verifyJavaFilesExported(archive, srcDirectoryPath, classNames, withClassFiles, withSource);
	}
	
	public static void verifyJavaFilesInRAR(IArchive archive, String[] classNames, String packageName, boolean withClassFiles, boolean withSource) throws Exception {
		List<IArchiveResource> resources = archive.getArchiveResources();
		IPath resourcePath = null;
		String resourceName = null;
		boolean foundNestedArchiveResource = false;
		for(IArchiveResource resource : resources) {
			resourcePath = resource.getPath();
			resourceName = resourcePath.lastSegment();

			foundNestedArchiveResource = resourceName.contains("_") && resourcePath.getFileExtension().equals("jar");
			if(foundNestedArchiveResource) {
				break;
			}
		}

		Assert.assertTrue("The connector should contain a nested archive resource", foundNestedArchiveResource);

		IArchiveResource nestedArchiveResource = archive.getArchiveResource(new Path(resourceName));
		IArchive nestedArchive = archive.getNestedArchive(nestedArchiveResource);

		String srcDirectoryPath = packageName + java.io.File.separatorChar;
		verifyJavaFilesExported(nestedArchive, srcDirectoryPath, classNames, withClassFiles, withSource);
	}
	
	protected static void addJavaFilesToSrcFolder(IVirtualFolder virtSrcFolder, String[] classNames, String prackageName, String classContents) throws Exception {
		IVirtualFolder packageVirtFolder = virtSrcFolder.getFolder(prackageName);
		packageVirtFolder.create(0, null);
		
		IVirtualFile virtFile = null;
		List <IFile>filesList = new ArrayList<IFile>();
		for(int i = 0; i < classNames.length; i++) {
			virtFile = packageVirtFolder.getFile(classNames[i] + ".java");
			filesList.add(virtFile.getUnderlyingFile());
		}
		
		addJavaFilesToSrcFolder(filesList, prackageName, classContents);
	}
	
	protected static void addJavaFilesToSrcFolder(IFolder srcFolder, String[] classNames, String prackageName, String classContents) throws Exception {
		IFolder packageFolder = srcFolder.getFolder(prackageName);
		packageFolder.create(0, true, null);
		
		IFile file = null;
		List <IFile>filesList = new ArrayList<IFile>();
		for(int i = 0; i < classNames.length; i++) {
			file = packageFolder.getFile(classNames[i] + ".java");
			filesList.add(file);
		}
		
		addJavaFilesToSrcFolder(filesList, prackageName, classContents);
	}
	
	protected static void addJavaFilesToSrcFolder(List<IFile> filesList, String packageName, String classContents) throws Exception {
		int maxWait = MAX_FILE_CREATION_WAIT;
		String fileContents = null;
		String className = null;
		try{
			//add listener to workspace
			ResourcesPlugin.getWorkspace().addResourceChangeListener(JavaFileUpdateListener.getInstance());
			
			//listen for all files in the file list to be created
			List <IFile> files = new ArrayList<IFile>();
			files.addAll(filesList);
			JavaFileUpdateListener.getInstance().setFiles(files);
			
			//create Java files
			for(int i = 0; i < filesList.size(); i++) {
				className = filesList.get(i).getProjectRelativePath().removeFileExtension().lastSegment();
				if(classContents == null){
					fileContents = generateJavaFileContent(className, packageName);
				} else {
					fileContents = classContents;
				}
				filesList.get(i).create(new StringBufferInputStream(fileContents),true,null);
			}
			
			//Wait until resource change events have been processed for all of the Java files created
			while(!JavaFileUpdateListener.getInstance().areFilesCreated() && maxWait > 0) {
				Thread.sleep(10);
				maxWait -= 10;
			}
			Assert.assertTrue("Should not have timed out waiting for Java files to be created", maxWait > 0);
		
		} finally{
			ResourcesPlugin.getWorkspace().removeResourceChangeListener(JavaFileUpdateListener.getInstance());
		}
	}
	
	protected static String generateJavaFileContent(String className, String packageName) {
		return "package " + packageName + "; public class " + className + " { }";
	}
	
	protected static void verifyJavaFilesExported(IArchive archive, String srcDirectoryPath, String[] classNames, boolean withClassFiles, boolean withSource) throws Exception {	
		IArchiveResource resource = null;
		IPath resourcePath = null;
		for(int i = 0; i < classNames.length; i++) {
			if(withClassFiles) {
				resourcePath = new Path(srcDirectoryPath + classNames[i] + ".class");
				try{
					resource = archive.getArchiveResource(resourcePath);
					if(resource == null){
						System.err.println("TODO -- There should be an archive resource class file for class " + classNames[i]);
						System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=195668");
						//Assert.fail("There should be an archive resource class file for class " + classNames[i]);
					}
					resource = null;
					resourcePath = null;
				} catch (FileNotFoundException e){
					System.err.println("TODO -- There should be an archive resource class file for class " + classNames[i]);
					System.err.println("     -- see https://bugs.eclipse.org/bugs/show_bug.cgi?id=195668");
					//Assert.fail("There should be an archive resource class file for class " + classNames[i]);
				}
			}

			if(withSource) {
				resourcePath = new Path(srcDirectoryPath + classNames[i] + ".java");
				resource = archive.getArchiveResource(resourcePath);
				if(resource == null){
					Assert.fail("There should be an archive resource source file for class " + classNames[i]);
				}
				resource = null;
				resourcePath = null;
			}
			else{
				// if the source was not exported, we verify that no java files are included in the exported file 
				// (see bug https://bugs.eclipse.org/bugs/show_bug.cgi?id=380581)
				resourcePath = new Path(srcDirectoryPath + classNames[i] + ".java");
				try{
					resource = archive.getArchiveResource(resourcePath);
					if(resource != null){
						Assert.fail("There should not be an archive resource source file for class " + classNames[i]);
					}
				}
				catch(FileNotFoundException e){
					// Left blank intentionally 
					// archive.getArchiveResource(resourcePath) throws a FileNotFoundException 
					// if the file cannot be found, which is correct in this case. 
				}
				resource = null;
				resourcePath = null;
				
			}
		}
	}
	
	/**
	 * adds two java files to every project that this ear references, to verify these files were exported correctly
	 * run verifyAllJavaFilesExportedToProjectsInEAR
	 * 
	 * be sure to run clearJavaFilesForEAR() when done with export verifications.
	 */
	public static void addJavaFilesToAllProjectsInEAR(IProject earProject) throws Exception {
		IProject[] webProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.DYNAMIC_WEB);
		IProject[] appClientProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.APPLICATION_CLIENT);
		IProject[] connectorProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.JCA);
		IProject[] ejbProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.EJB);
		IProject[] utilityProjects = J2EEProjectUtilities.getAllProjectsInWorkspaceOfType(J2EEProjectUtilities.UTILITY);
		
		String projectName = null;
		int fileCount = 0;
		
		
		List<IProject> referencedProjects = Arrays.asList(earProject.getReferencedProjects());
		
		for(IProject project : webProjects) {
			if(referencedProjects.contains(project)) {
				projectName = project.getName();
				String[] classNameList = {"JavaFile" + (fileCount++), "Javafile" + (fileCount++)};
				JavaFileTestingUtilities.addJavaFilesToWeb(projectName, classNameList, WEB_PACKAGE);
				webProjectJavaFiles.put(projectName, classNameList);
			}
		}
		
		for(IProject project : appClientProjects) {
			if(referencedProjects.contains(project)) {
				projectName = project.getName();
				String[] classNameList = {"JavaFile" + (fileCount++), "Javafile" + (fileCount++)};
				JavaFileTestingUtilities.addJavaFilesToAppClient(projectName, classNameList, APP_CLIENT_PACKAGE);
				appClientProjectJavaFiles.put(projectName, classNameList);
			}
		}

		for(IProject project : connectorProjects) {
			if(referencedProjects.contains(project)) {
				projectName = project.getName();
				String[] classNameList = {"JavaFile" + (fileCount++), "Javafile" + (fileCount++)};
				JavaFileTestingUtilities.addJavaFilesToConnector(projectName, classNameList, CONNECTOR_PACKAGE);
				connectorProjectJavaFiles.put(projectName, classNameList);
			}
		}

		for(IProject project : ejbProjects) {
			if(referencedProjects.contains(project)) {
				projectName = project.getName();
				String[] classNameList = {"JavaFile" + (fileCount++), "Javafile" + (fileCount++)};
				JavaFileTestingUtilities.addJavaFilesToEJB(projectName, classNameList, EJB_PACKAGE);
				ejbProjectJavaFiles.put(projectName, classNameList);
			}
		}
		
		for(IProject project : utilityProjects) {
			if(referencedProjects.contains(project)) {
				projectName = project.getName();
				String[] classNameList = {"JavaFile" + (fileCount++), "Javafile" + (fileCount++)};
				JavaFileTestingUtilities.addJavaFilesToUtility(projectName, classNameList, UTILITY_PACKAGE);
				utilityProjectJavaFiles.put(projectName, classNameList);
			}
		}
	}
	
	/**
	 * be sure to call addJavaFilesToAllProjectsInEAR() before trying to verify that the files were added
	 * 
	 * @param earArchivePath
	 * @param withClassFiles
	 * @param withSource
	 * @throws Exception
	 */
	public static void verifyAllJavaFilesExportedToProjectsInEAR(String earArchivePath, boolean withClassFiles, boolean withSource ) throws Exception {
		IArchive earArchive = null;
		
		try {
			earArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(earArchivePath));
			List<IArchiveResource> resources = earArchive.getArchiveResources();
			
			String extension = null;
			IArchive nestedArchive = null;
			String nestedArchiveName = null;
			String[] classNames = null;
			for(IArchiveResource resource : resources) {
				extension = resource.getPath().getFileExtension();
				
				if(extension != null) {
					if(extension.equals(WAR_EXTENSION)) {
						nestedArchive = earArchive.getNestedArchive(resource);
						nestedArchiveName = nestedArchive.getPath().removeFileExtension().lastSegment();
						classNames = webProjectJavaFiles.get(nestedArchiveName);

						verifyJavaFilesInWAR(nestedArchive, classNames, WEB_PACKAGE, withClassFiles, withSource);
					} else if(extension.equals(JAR_EXTENSION)) {
						nestedArchive = earArchive.getNestedArchive(resource);
						nestedArchiveName = nestedArchive.getPath().removeFileExtension().lastSegment();
						
						//EJB, AppClients and Utilitys have the same extension, so if project isn't in AppClient list
						// check EJB list, if not in EJB list check Utility list
						classNames = appClientProjectJavaFiles.get(nestedArchiveName);
						if(classNames == null) {
							classNames = ejbProjectJavaFiles.get(nestedArchiveName);
							
							if(classNames != null) {
								verifyJavaFilesInJAR(nestedArchive, classNames, EJB_PACKAGE, withClassFiles, withSource);
							} else {
								classNames = utilityProjectJavaFiles.get(nestedArchiveName);
								verifyJavaFilesInJAR(nestedArchive, classNames, UTILITY_PACKAGE, withClassFiles, withSource);
							}
						} else {
							verifyJavaFilesInJAR(nestedArchive, classNames, APP_CLIENT_PACKAGE, withClassFiles, withSource);
						}
						
					} else if(extension.equals(RAR_EXTENSION)) {
						nestedArchive = earArchive.getNestedArchive(resource);
						nestedArchiveName = nestedArchive.getPath().removeFileExtension().lastSegment();
						classNames = connectorProjectJavaFiles.get(nestedArchiveName);

						verifyJavaFilesInRAR(nestedArchive, classNames, CONNECTOR_PACKAGE, withClassFiles, withSource);
					}
				}
			}
			
		} finally {
			if(earArchive != null) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(earArchive);
			}
		}
	}
	
	public static void clearJavaFilesForEAR() {
		appClientProjectJavaFiles.clear();
		ejbProjectJavaFiles.clear();
		connectorProjectJavaFiles.clear();
		webProjectJavaFiles.clear();
	}
}
