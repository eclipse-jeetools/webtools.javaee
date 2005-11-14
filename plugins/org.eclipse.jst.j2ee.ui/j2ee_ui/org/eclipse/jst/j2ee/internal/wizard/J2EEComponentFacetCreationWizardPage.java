/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.web.ui.internal.wizards.DataModelFacetCreationWizardPage;

public abstract class J2EEComponentFacetCreationWizardPage extends DataModelFacetCreationWizardPage implements IFacetProjectCreationDataModelProperties {

	protected EarSelectionPanel earPanel;

	public J2EEComponentFacetCreationWizardPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite top = new Composite(parent, SWT.NONE);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(top, getInfopopID());
		top.setLayout(new GridLayout());
		top.setLayoutData(new GridData(GridData.FILL_BOTH));
		createProjectGroup(top);
		Composite composite = new Composite(top, SWT.NONE);
		composite.setLayoutData(gdhfill());
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		createServerTargetComposite(composite);
		createEarComposite(composite);
		return top;
	}

	private void createEarComposite(Composite composite) {
		FacetDataModelMap map = (FacetDataModelMap) model.getProperty(FACET_DM_MAP);
		IDataModel facetModel = (IDataModel) map.get(getModuleFacetID());

		earPanel = new EarSelectionPanel(facetModel, composite, SWT.NONE);
		GridData data = gdhfill();
		data.horizontalSpan = 3;
		earPanel.setLayoutData(data);
	}

	protected abstract String getModuleFacetID();

	public void dispose() {
		super.dispose();
		if (earPanel != null)
			earPanel.dispose();
	}
}