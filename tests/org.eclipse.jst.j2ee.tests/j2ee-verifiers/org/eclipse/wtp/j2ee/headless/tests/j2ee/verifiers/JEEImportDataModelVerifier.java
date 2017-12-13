/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wst.common.tests.DataModelVerifier;
import org.eclipse.wst.common.tests.ProjectUtility;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public abstract class JEEImportDataModelVerifier extends DataModelVerifier {
	private static final String CLASS_EXTENSION = "class";
	private static final String JAVA_EXTENSION = "java";
	private static final String JAR_EXTENSION = "jar";
	private static final String RAR_EXTENSION = "rar";
	private static final String WAR_EXTENSION = "war";
	
	protected IDataModel model;
	protected IProject project;

	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);

		this.model = model;
		String projectName = model.getStringProperty(IJ2EEModuleImportDataModelProperties.PROJECT_NAME);
		project = ProjectUtility.getProject(projectName);

		this.verifyProjectCreated();

		String archivePath = model.getStringProperty(IJ2EEModuleImportDataModelProperties.FILE_NAME);
		
		IArchive archive = null;
		try {
			archive = JavaEEArchiveUtilities.INSTANCE.openArchive(new Path(archivePath));

			this.verifyImportedProjectTypeAndVersion(archive);
			this.verifyAllFilesImported(archive);
			
		} finally {
			if (null != archive && archive.isOpen()) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		}
	}
	
	/**
	 * used for verifying imported archives that are nested inside of other archives
	 * 
	 * @param nestedArchiveImportModel
	 * @param importedNestedArchive
	 */
	public void verify(IDataModel nestedArchiveImportModel, IArchive importedNestedArchive) throws Exception {
		this.model = nestedArchiveImportModel;
		String projectName = model.getStringProperty(IJ2EEModuleImportDataModelProperties.PROJECT_NAME);
		project = ProjectUtility.getProject(projectName);

		this.verifyProjectCreated();
		
		this.verifyImportedProjectTypeAndVersion(importedNestedArchive);
		this.verifyAllFilesImported(importedNestedArchive);
	}

	protected abstract int getExportType();

	private void verifyImportedProjectTypeAndVersion(IArchive archive) throws Exception {
		if(JavaEEProjectUtilities.isUtilityProject(project)){
			JavaEEQuickPeek archiveQuickPeek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive);
			int type = archiveQuickPeek.getType();
			if(JavaEEQuickPeek.UNKNOWN != type){
				AssertWarn.warnEquals("Archive is not a utility, but was imported as such, archive="+archive, getExportType(), type);
			}
		} else {
			JavaEEQuickPeek archiveQuickPeek = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive);
			int type = archiveQuickPeek.getType();
			
			if(getExportType() != type){
				AssertWarn.warnEquals("Archive type did not match imported project type, archive="+archive, getExportType(), type);
			}
	
			String sProjVersion = J2EEProjectUtilities.getJ2EEDDProjectVersion(project);
			int iProjVersion = J2EEVersionUtil.convertVersionStringToInt(sProjVersion);
			int iVersionConstant = archiveQuickPeek.getVersion();
			if(iProjVersion != iVersionConstant){
				AssertWarn.warnEquals("Archive version did not match imported project version, archive="+archive, iProjVersion, iVersionConstant);
			}
		}
	}

	private void verifyProjectCreated() {
		Assert.assertTrue("A project with name, " + project.getName() + ", should have been created by import", project.exists());
	}

	
	protected boolean isClassWithoutSource(IArchive archive, IArchiveResource aFile) {
		String javaUri = classUriToJavaUri(aFile.getPath().toString());
		if (javaUri == null)
			return false;
		return !archive.containsArchiveResource(new Path(javaUri));
	}

	protected final String DOT_CLASS = ".class"; //$NON-NLS-1$

	protected final String DOT_JAVA = ".java"; //$NON-NLS-1$
	
	public String classUriToJavaUri(String classUri) {
		if (classUri == null || !classUri.endsWith(DOT_CLASS))
			return null;

		String truncated = truncateIgnoreCase(classUri, DOT_CLASS);
		StringTokenizer tok = new StringTokenizer(truncated, "$"); //$NON-NLS-1$
		return tok.nextToken().concat(DOT_JAVA);
	}
	
	/**
	 * Return a substring of the first parameter, up to the last index of the
	 * second
	 */
	public static String truncateIgnoreCase(String aString, String trailingSubString) {
		int index = aString.toLowerCase().lastIndexOf(trailingSubString.toLowerCase());
		if (index != -1)
			return aString.substring(0, index);
		return aString;
	}
	private void verifyAllFilesImported(IArchive archive) throws Exception {
		List<IArchiveResource> resources = archive.getArchiveResources();
		IPath resourcePath = null;
		IFolder importedClassesFolder = project.getFolder("ImportedClasses");

		IVirtualComponent projectComponent = ComponentUtilities.getComponent(project.getName());
		IVirtualFolder rootVirtFolder = projectComponent.getRootFolder();
		IContainer rootFolder = rootVirtFolder.getUnderlyingFolder();
		Assert.assertTrue("The root folder " + rootFolder.getName() + " should exist in the project" , rootFolder.exists());
		
		// when the for loops is done the classes will contain only those classes that were imported,
		// the sourceResources list will contain a list of all of the java source resources,
		// the otherResources list will contain all other resources that are not nested archives,
		// and any nested archive in this archive will have been set as a nested archive in 'archive'
		List<IArchiveResource> classes = new ArrayList<IArchiveResource>();
		List<IArchiveResource> sourceResources = new ArrayList<IArchiveResource>();
		List<IArchiveResource> otherResources = new ArrayList<IArchiveResource>();
		
		String extension = null;
		for(IArchiveResource resource : resources) {
			resourcePath = resource.getPath();

			switch (resource.getType()) {
				case IArchiveResource.FILE_TYPE :
					extension = resourcePath.getFileExtension();
					
					// Note: For war archive, the class must be in WEB-INF/classes, otherwise it's just content
					if(extension.equals(CLASS_EXTENSION) &&
							((getExportType() == J2EEVersionConstants.WEB_TYPE) ? (resourcePath.segmentCount() > 2 && resourcePath.segment(0).equals("WEB-INF") && resourcePath.segment(1).equals("classes")) : true)){
						if(isClassWithoutSource(archive, resource)){
							classes.add(resource);
						}
					} else if(extension.equals(JAVA_EXTENSION)){
						sourceResources.add(resource);
					} else if(extension.equals(JAR_EXTENSION) || extension.equals(RAR_EXTENSION) || extension.equals(WAR_EXTENSION)) {
						archive.getNestedArchive(resource);
					} else {
						otherResources.add(resource);
					}
					
					break;
				case IArchiveResource.DIRECTORY_TYPE :
	
					break;
				case IArchiveResource.ARCHIVE_TYPE :
	
					break;
			}
		}
		
		List<IArchive> nestedArchives = archive.getNestedArchives();
		
		verifyImportedResources(sourceResources, classes, otherResources, nestedArchives, rootFolder, importedClassesFolder);
	}
	
	protected abstract void verifyImportedResources(Collection<IArchiveResource> sourceResources, Collection<IArchiveResource> importedClassesResources, Collection<IArchiveResource> otherResources, Collection<IArchive> nestedArchives, IContainer rootFolder, IFolder importedClassesFolder) throws Exception;
}
