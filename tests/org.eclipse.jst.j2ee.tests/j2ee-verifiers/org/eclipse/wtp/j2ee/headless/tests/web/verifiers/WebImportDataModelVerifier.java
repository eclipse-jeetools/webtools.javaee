/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.verifiers;

import java.io.FileNotFoundException;
import java.util.Collection;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleImportDataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class WebImportDataModelVerifier extends ModuleImportDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
	}
	
	@Override
	protected int getExportType() {
		return J2EEVersionConstants.WEB_TYPE;
	}
	
	@Override
	protected void verifyImportedResources(
			Collection<IArchiveResource> sourceResources,
			Collection<IArchiveResource> importedClassesResources,
			Collection<IArchiveResource> otherResources,
			Collection<IArchive> nestedArchives,
			IContainer rootFolder, IFolder importedClassesFolder) {
		
		IPath resourcePath = null;
		IFile resourceFile = null;
		IFolder sourceFolder = project.getFolder("src");
		
		//verify all of the resources from the archive were imported to the project correctly
		for(IArchiveResource sourceResource : sourceResources) {
			resourcePath = sourceResource.getPath();
			resourcePath = resourcePath.removeFirstSegments(2);
			resourceFile = sourceFolder.getFile(resourcePath);
			Assert.assertTrue("The file " + resourcePath + " should exist in the project", resourceFile.exists());
		}
		
		for(IArchiveResource importedClassResource : importedClassesResources) {
			resourcePath = importedClassResource.getPath().removeFirstSegments(2);
			resourceFile = importedClassesFolder.getFile(resourcePath);
			if(!resourceFile.exists()){
				Assert.fail("The imported class " + resourcePath + " should exist in the project");
			}
		}
		
		for(IArchiveResource otherResource : otherResources) {
			resourcePath = otherResource.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
			Assert.assertTrue("The resource " + resourcePath + " should exist in the project", resourceFile.exists());
		}
	
		for(IArchive nestedArchive : nestedArchives) {
			
		}
	}
	
	protected boolean isClassWithoutSource(IArchive archive, IArchiveResource aFile) {
		String javaUri = ArchiveUtil.classUriToJavaUri(aFile.getPath().toString());
		if (javaUri == null)
			return true;
		IPath javaPath = new Path(javaUri);
		if (archive.containsArchiveResource(javaPath)) {
			return false;
		}
		// see if it is a JSP
		String jspUri = javaUri.substring(0, javaUri.indexOf(ArchiveUtil.DOT_JAVA));
		int lastSlash = jspUri.lastIndexOf('/');
		int _index = lastSlash == -1 ? ArchiveConstants.WEBAPP_CLASSES_URI.length() : lastSlash + 1;
		if (jspUri.length() > _index && jspUri.charAt(_index) == '_') {
			jspUri = jspUri.substring(ArchiveConstants.WEBAPP_CLASSES_URI.length(), _index) + jspUri.substring(_index + 1) + ArchiveUtil.DOT_JSP;
			IPath jspPath = new Path(jspUri);
			if (archive.containsArchiveResource(jspPath)) {
				return false;
			}
		}

		//This is to handle archives created by an earlier version
		//The format was to include the source files in a directory called source in WEB-INF
		//Example: class  is in WEB-INF/classes/test/Foo.class
		//         source is in WEB-INF/source/test/Foo.java
		if(javaPath.segmentCount() > 2 && javaPath.segment(0).equals("WEB-INF") && javaPath.segment(1).equals("classes")){
			String alternateJavaUri = javaUri.replaceFirst("classes", "source");
			IPath alternateJavaPath = new Path(alternateJavaUri);
			if (archive.containsArchiveResource(alternateJavaPath)){
				IArchiveResource sourceFile;
				try {
					sourceFile = archive.getArchiveResource(alternateJavaPath);
					if(sourceFile != null){
						return false;
					}
				} catch (FileNotFoundException e) {
				}
				
			}
			
		}
		
		return true;
	}
}
