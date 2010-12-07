/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleImportOperationTest;

public class WebImportOperationBaseTest extends ModuleImportOperationTest{
	
	public WebImportOperationBaseTest() {
		super("WebImportOperationBaseTest");
	}
	
	public WebImportOperationBaseTest(String name) {
		super(name);
	}
	
	@Override
	protected String getModuleExtension() {
		return ".war";
	}
	
    @Override
    protected IDataModel getImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose) {
    	return getWebImportDataModel(filePath, projectName, creationModel, closeArchiveOnDispose, null, null);
    }
    
    public static IDataModel getWebImportDataModel(String filePath, String projectName, IDataModel creationModel, boolean closeArchiveOnDispose, List<IArchive> webLibArchivesToExpand, String contextRoot) {
    	IDataModel importModel = DataModelFactory.createDataModel(new WebComponentImportDataModelProvider());
    	
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.FILE_NAME, filePath);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.PROJECT_NAME, projectName);
    	importModel.setProperty(IJ2EEComponentImportDataModelProperties.CLOSE_ARCHIVE_ON_DISPOSE, closeArchiveOnDispose);
    	
    	
    	if(creationModel != null) {
    		importModel.setProperty(IJ2EEComponentImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION, creationModel);
    	}
    	
    	if(webLibArchivesToExpand != null) {
    		importModel.setProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED, webLibArchivesToExpand);
    	}
    	
    	if(contextRoot != null) {
    		importModel.setProperty(IWebComponentImportDataModelProperties.CONTEXT_ROOT, contextRoot);
    	}
    	
    	return importModel;
    }
    
    @Override
    protected IDataModel getExportDataModel(String projectName, String destination, boolean exportSource, boolean runBuild, boolean overwriteExisting) {
    	return WebExportOperationTest.getWebExportDataModel(projectName, destination, exportSource, runBuild, overwriteExisting, false);
    }
}
