
package org.eclipse.wtp.j2ee.headless.tests.ejb.operations;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest;

public class EJBProjectCreationOperationTest extends ModuleProjectCreationOperationTest {

    /**
	 * @param name
	 */
	public EJBProjectCreationOperationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public static Test suite() {
        return new TestSuite(EJBProjectCreationOperationTest.class);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wtp.j2ee.headless.tests.j2ee.operations.ModuleProjectCreationOperationTest#getProjectCreationDataModel()
     */
    public IDataModel getComponentCreationDataModel() {
        return DataModelFactory.createDataModel(new EjbComponentCreationDataModelProvider());
    }
  
}
