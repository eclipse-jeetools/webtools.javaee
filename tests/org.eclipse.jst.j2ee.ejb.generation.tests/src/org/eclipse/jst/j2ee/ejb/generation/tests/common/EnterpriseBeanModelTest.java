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
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;

/**
 * @author naci
 *
 */
public class EnterpriseBeanModelTest extends AnnotationTest {
	
	
	public void testSessionBeanDataModel() throws Exception
	{
		createEjbModuleAndProject();
		
		SessionBeanDataModel dataModel = (SessionBeanDataModel)createDefaultSessionModel();
		
		assertNotNull(dataModel);
		
		assertEquals(IStatus.OK , dataModel.validateDataModel().getCode() );
		
		assertEquals(PROJECT_NAME,dataModel.getStringProperty(SessionBeanDataModel.PROJECT_NAME));
		
		assertEquals(MODULE_NAME,dataModel.getStringProperty(SessionBeanDataModel.MODULE_NAME));
		
		assertEquals(BEAN_CLASS, dataModel.getSimpleClassName());
		assertEquals(1, dataModel.getEJBInterfaces().size());
		assertEquals("javax.ejb.SessionBean", dataModel.getEJBInterfaces().get(0));
		
		
	}

}
