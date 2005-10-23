/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.generation.tests.common;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author naci
 *
 */
public class EnterpriseBeanModelTest extends AnnotationTest {
	
	
	public void testSessionBeanDataModel() throws Exception
	{
		createEjbModuleAndProject();
		
		IDataModel dataModel = createDefaultSessionModel();
		
		assertNotNull(dataModel);
		IStatus status = dataModel.validate();
		assertTrue(status.isOK() );
		
		assertEquals(PROJECT_NAME,dataModel.getStringProperty(SessionBeanDataModelProvider.PROJECT_NAME));
		
		
		assertEquals(BEAN_CLASS, dataModel.getProperty(SessionBeanDataModelProvider.CLASS_NAME));
		String[] interfaces = (String[])dataModel.getProperty(SessionBeanDataModelProvider.EJB_INTERFACES);
		assertEquals(1, interfaces.length);
		assertEquals("javax.ejb.SessionBean", interfaces[0]);
		
		
	}

}
