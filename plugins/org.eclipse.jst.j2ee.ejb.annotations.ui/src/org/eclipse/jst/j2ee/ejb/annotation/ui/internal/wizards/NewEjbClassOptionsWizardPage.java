/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;

import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassOptionsWizardPage;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * @author naci
 *
 */
public class NewEjbClassOptionsWizardPage extends NewJavaClassOptionsWizardPage {

	public NewEjbClassOptionsWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	public void refreshInterfaces(Object interfaces)
	{
		interfaceViewer.setInput(interfaces);
	}
}
