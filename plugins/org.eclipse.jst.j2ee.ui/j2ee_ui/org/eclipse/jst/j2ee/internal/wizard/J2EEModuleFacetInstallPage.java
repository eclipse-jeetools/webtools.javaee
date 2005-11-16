/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;


import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.web.ui.internal.wizards.DataModelFacetInstallPage;

public abstract class J2EEModuleFacetInstallPage extends DataModelFacetInstallPage implements IJ2EEModuleFacetInstallDataModelProperties {

	public J2EEModuleFacetInstallPage(String pageName) {
		super(pageName);
	}

	protected EarSelectionPanel earPanel;

	public void dispose() {
		if (null != earPanel) {
			earPanel.dispose();
		}
		super.dispose();
	}

	protected void setupEarControl(final Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setLayoutData(gdhfill());
		final GridLayout layout = new GridLayout(3, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		c.setLayout(layout);
		this.earPanel = new EarSelectionPanel(model, c);
	}
}
