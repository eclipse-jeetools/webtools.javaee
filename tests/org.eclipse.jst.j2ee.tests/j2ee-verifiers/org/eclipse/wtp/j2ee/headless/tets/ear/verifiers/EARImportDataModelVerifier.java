/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tets.ear.verifiers;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.ArchiveWrapper;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationPropertiesNew;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wst.common.tests.DataModelVerifierFactory;
import org.eclipse.wst.common.tests.ProjectUtility;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.JEEImportDataModelVerifier;

/**
 * @author Administrator
 * @author itewk
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EARImportDataModelVerifier extends JEEImportDataModelVerifier {

    public void verify(IDataModel model) throws Exception {
        super.verify(model);
    }
    
    @Override
    protected int getExportType() {
    	return J2EEVersionConstants.APPLICATION_TYPE;
    }
    
    @Override
	protected void verifyImportedResources (
			Collection<IArchiveResource> sourceResources,
			Collection<IArchiveResource> importedClassesResources,
			Collection<IArchiveResource> otherResources,
			Collection<IArchive> nestedArchives,
			IContainer rootFolder, IFolder importedClassesFolder)throws Exception {
		
		IPath resourcePath = null;
		IFile resourceFile = null;
    	
		for(IArchiveResource otherResource : otherResources) {
			resourcePath = otherResource.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
			if(resourceFile.getName().equalsIgnoreCase("Manifest.mf")) {
				AssertWarn.warnTrue("The resource " + resourcePath + " should exist in the project " + project.getName(), resourceFile.exists());
			} else {
				Assert.assertTrue("The resource " + resourcePath + " should exist in the project " + project.getName(), resourceFile.exists());
			}
		}
		
		Map<IPath,IArchive> nestedArchiveMap = new HashMap<IPath,IArchive>();
		for(IArchive nestedArchive : nestedArchives) {
			resourcePath = nestedArchive.getPath();
			resourceFile = rootFolder.getFile(resourcePath);
			Assert.assertTrue("The nested archive " + resourcePath + " should exist in the project", resourceFile.exists());
			nestedArchiveMap.put(nestedArchive.getPath(), nestedArchive);
		}
		
		List<IDataModel> selectedModelsList = (List)model.getProperty(IEARComponentImportDataModelProperties.SELECTED_MODELS_LIST);
		DataModelVerifierFactory verifierFactory = DataModelVerifierFactory.getInstance();
		JEEImportDataModelVerifier verifier = null;
		ArchiveWrapper nestedArchiveWrapper = null;
		IArchive nestedArchive = null;
		for(IDataModel selectedModel : selectedModelsList) {
			verifier = (JEEImportDataModelVerifier)verifierFactory.createVerifier(selectedModel);
			nestedArchiveWrapper = (ArchiveWrapper)selectedModel.getProperty(IJ2EEModuleImportDataModelProperties.ARCHIVE_WRAPPER);
			nestedArchive = nestedArchiveMap.get(nestedArchiveWrapper.getPath());
			Assert.assertNotNull("There should be a nested archive object for this selected model: " + selectedModel, nestedArchive);
			verifier.verify(selectedModel, nestedArchive);
		}
		
		List<IDataModel> moduleModelsList = (List)model.getProperty(IEARComponentImportDataModelProperties.MODULE_MODELS_LIST);
		moduleModelsList.removeAll(selectedModelsList);
		String projectName = null;
		nestedArchive = null;
		for(IDataModel nonExplodedNestedModels : moduleModelsList) {
			projectName = nonExplodedNestedModels.getStringProperty(IProjectCreationPropertiesNew.PROJECT_NAME);
			nestedArchiveWrapper = (ArchiveWrapper)nonExplodedNestedModels.getProperty(IJ2EEModuleImportDataModelProperties.ARCHIVE_WRAPPER);
			Assert.assertFalse("The nested archive, " + nestedArchiveWrapper.getPath() + " should not have been exploded into a project.", ProjectUtility.getProject(projectName).exists());
		}
		
		
		List utilityList = (List)model.getProperty(IEARComponentImportDataModelProperties.UTILITY_LIST);
		List ejbClientList = (List)model.getProperty(IEARComponentImportDataModelProperties.EJB_CLIENT_LIST);
		List utilityModelsList = (List)model.getProperty(IEARComponentImportDataModelProperties.UTILITY_MODELS_LIST);
	}    
}
