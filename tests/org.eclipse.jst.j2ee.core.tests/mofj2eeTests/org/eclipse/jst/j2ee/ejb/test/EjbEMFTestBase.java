/*
 * Created on Mar 14, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.ejb.test;

import org.eclipse.jst.j2ee.archive.emftests.EjbEMFTest;
import org.eclipse.jst.j2ee.archive.testutilities.EMFAttributeFeatureGenerator;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EjbEMFTestBase extends EjbEMFTest {

	/**
	 * @param name
	 */
	public EjbEMFTestBase(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	protected void init() throws Exception{
		EMFAttributeFeatureGenerator.reset();
		createEAR();
		createEJB();

		EJBResource DD = (EJBResource) ejbFile.getDeploymentDescriptorResource();
		//TODO: individual test for each version
		DD.setVersionID(J2EEVersionConstants.J2EE_1_4_ID);
		setVersion(VERSION_1_4);
		setModuleType(EJB);
		populateRoot(DD.getRootObject());
	}
}
