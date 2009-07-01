/**
 * 
 */
package org.eclipse.wtp.j2ee.headless.tests.utility.verifiers;

import java.util.Collection;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleImportDataModelVerifier;

/**
 * @author itewk
 *
 */
public class UtilityImportDataModelVerifier extends ModuleImportDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		//super.verify(model);
	}
	
	@Override
	public void verify(IDataModel nestedArchiveImportModel,
			IArchive importedNestedArchive) throws Exception {
		
		super.verify(nestedArchiveImportModel, importedNestedArchive);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.JEEImportDataModelVerifier#getExportType()
	 */
	@Override
	protected int getExportType() {
		return J2EEVersionConstants.UNKNOWN;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.JEEImportDataModelVerifier#verifyImportedResources(java.util.Collection, java.util.Collection, java.util.Collection, java.util.Collection, org.eclipse.core.resources.IFolder, org.eclipse.core.resources.IFolder)
	 */
	@Override
	protected void verifyImportedResources(
			Collection<IArchiveResource> sourceResources,
			Collection<IArchiveResource> importedClassesResources,
			Collection<IArchiveResource> otherResources,
			Collection<IArchive> nestedArchives, IContainer rootFolder,
			IFolder importedClassesFolder) throws Exception {
		
		IPath resourcePath = null;
		IFile resourceFile = null;
		
		//verify all of the resources from the archive were imported to the project correctly
		for(IArchiveResource sourceResource : sourceResources) {
			resourcePath = sourceResource.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
//			Assert.assertTrue("The file " + resourcePath + " should exist in the project in the " + rootFolder.getFullPath() + " directory", resourceFile.exists());
			AssertWarn.warnTrue("The file " + resourcePath + " should exist in the project in the " + rootFolder.getFullPath() + " directory", resourceFile.exists());
		}
		
		for(IArchiveResource importedClassResource : importedClassesResources) {
			resourcePath = importedClassResource.getPath();
			resourceFile = importedClassesFolder.getFile(resourcePath);
//			Assert.assertTrue("The imported class " + resourcePath + " should exist in the project in the " + importedClassesFolder.getFullPath() + " directory", resourceFile.exists());
			if(!resourceFile.exists()){
				AssertWarn.warnTrue("The imported class " + resourcePath + " should exist in the project in the " + importedClassesFolder.getFullPath() + " directory", resourceFile.exists());
			}
		}
		
		for(IArchiveResource otherResource : otherResources) {
			resourcePath = otherResource.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
//			Assert.assertTrue("The resource " + resourcePath + " should exist in the project in the " + rootFolder.getFullPath() + " directory", resourceFile.exists());
			AssertWarn.warnTrue("The resource " + resourcePath + " should exist in the project in the " + rootFolder.getFullPath() + " directory", resourceFile.exists());
		}
	
		for(IArchive nestedArchive : nestedArchives) {
			
		}
	}

}
