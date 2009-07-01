/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers;

import java.util.Collection;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
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
public class EJBImportDataModelVerifier extends ModuleImportDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
	}
	
	@Override
	protected int getExportType() {
		return J2EEVersionConstants.EJB_TYPE;
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
		
		//verify all of the resources from the archive were imported to the project correctly
		for(IArchiveResource sourceResource : sourceResources) {
			resourcePath = sourceResource.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
			Assert.assertTrue("The file " + resourcePath + " should exist in the project", resourceFile.exists());
		}
		
		for(IArchiveResource importedClassResource : importedClassesResources) {
			resourcePath = importedClassResource.getPath();
			resourceFile = importedClassesFolder.getFile(resourcePath);
			Assert.assertTrue("The imported class " + resourcePath + " should exist in the project", resourceFile.exists());
		}
		
		for(IArchiveResource otherResource : otherResources) {
			resourcePath = otherResource.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
			Assert.assertTrue("The resource " + resourcePath + " should exist in the project", resourceFile.exists());
		}
	
		for(IArchive nestedArchive : nestedArchives) {
			
		}
	}
}
