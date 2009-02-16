package org.eclipse.jst.jee.ui.project.facet;

/***************************************************************************************************
 /***************************************************************************************************
 * Copyright (c) 2007 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class EarJavaEEFacetInstallPage extends
		org.eclipse.jst.j2ee.ui.project.facet.EarFacetInstallPage {

	protected Button addDD;

	public EarJavaEEFacetInstallPage() {
		super();
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		Composite holder = new Composite(composite, SWT.NONE);
		holder.setLayout(new GridLayout());
		holder.setLayoutData(gdhfill());
		createGenerateDescriptorControl(holder, J2EEConstants.APPLICATION_DD_SHORT_NAME);
		
		return composite;
	}
	
}
