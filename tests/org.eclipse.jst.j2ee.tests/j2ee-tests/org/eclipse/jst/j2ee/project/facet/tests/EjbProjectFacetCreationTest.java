package org.eclipse.jst.j2ee.project.facet.tests;
import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class EjbProjectFacetCreationTest extends TestCase {
	
	
	public EjbProjectFacetCreationTest(String name) {
		super(name);
	}
	
	public EjbProjectFacetCreationTest() {
		super();
	}
    public static Test suite() {
        return new SimpleTestSuite(EjbProjectFacetCreationTest.class);
    }

   public void testEjbCreation() throws Exception {

		IDataModel dm = DataModelFactory.createDataModel( new EjbFacetProjectCreationDataModelProvider());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, "TestEJBFacet"); //$NON-NLS-1$
		dm.getDefaultOperation().execute(null, null);
    }


}
