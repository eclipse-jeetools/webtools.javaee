/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.jca.verifiers;

import junit.framework.Assert;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.project.facet.IConnectorFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.AssertWarn;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author Administrator
 * @author Ian Tewksbury (ictewksb@us.ibm.com)
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class JCAProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
		
		this.verifySourceFolder();
	}
	
	@Override
	protected void setFacetProjectType() {
		this.facetProjectType = J2EEProjectUtilities.JCA;
	}
	
	@Override
	protected IFile getDDFile() {
		return component.getRootFolder().getFile(J2EEConstants.RAR_DD_URI).getUnderlyingFile();
	}
	
	@Override
	protected void verifyDD(Object modelObj) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(project);
		Connector connector = (Connector)modelObj;
		AssertWarn.warnEquals("Invalid project version", version, connector.getVersion());
	}
	
    private void verifySourceFolder() {
    	FacetDataModelMap facetMap = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
    	IDataModel facetModel = facetMap.getFacetDataModel(J2EEProjectUtilities.JCA);
		String sourceFolder = facetModel.getStringProperty(IConnectorFacetInstallDataModelProperties.CONFIG_FOLDER);
		IPath sourceFolderPath = new Path(sourceFolder);
		Assert.assertTrue("Source directory should exist", project.exists(sourceFolderPath));
    }
}
