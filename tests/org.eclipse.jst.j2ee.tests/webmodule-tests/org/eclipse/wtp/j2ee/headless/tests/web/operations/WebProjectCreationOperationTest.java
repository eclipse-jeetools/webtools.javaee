/*
 * Created on Nov 7, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.web.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;


public class WebProjectCreationOperationTest extends ModuleProjectCreationOperationTest {
    
    /**
	 * @param name
	 */
	public WebProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new TestSuite(WebProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public IDataModel getComponentCreationDataModel() {
        return DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
    }
    
    public IDataModel getComponentCreationDataModelWithEar() {
        IDataModel model =  DataModelFactory.createDataModel(new WebFacetProjectCreationDataModelProvider());
        FacetDataModelMap map = (FacetDataModelMap) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
        IDataModel facetDM = map.getFacetDataModel(J2EEProjectUtilities.DYNAMIC_WEB);
        facetDM.setBooleanProperty( IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR, true );
        return model;
    } 
    
}
