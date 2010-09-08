/******************************************************************************
 * Copyright (c) 2009 Red Hat and Others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - Ongoing improvements
 *    Konstantin Komissarchik - misc. UI cleanup
 ******************************************************************************/
package org.eclipse.jst.servlet.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ProjectReferenceWizardFragment;
import org.eclipse.wst.common.componentcore.ui.internal.taskwizard.IWizardHandle;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;

public class CustomWebProjectReferenceWizardFragment extends ProjectReferenceWizardFragment {
	
	public CustomWebProjectReferenceWizardFragment() {
		super();
	}

	private boolean isWebLib = true;
	private Button button;
	
	@Override
	public Composite createComposite(Composite parent, IWizardHandle handle) {
		
		Composite c = new Composite(parent, SWT.NONE);
		
		final GridLayout gl = new GridLayout();
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		
		c.setLayout(gl);
		
		Composite tableComp = super.createComposite(c, handle);
		tableComp.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		// make button
		button = new Button(c, SWT.CHECK);
		button.setText(Messages.CustomWebProjectReferenceWizardFragment); 

		final GridData gd = new GridData();
		gd.horizontalIndent = 5;
		button.setLayoutData(gd);
		
		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}
			public void widgetSelected(SelectionEvent e) {
				handleEdit();
			}
		});
		button.setSelection(true);

		return c;
	}
	
	public Composite createTableColumnComposite(Composite parent) {
		Composite aButtonColumn = new Composite(parent, SWT.NONE);
	
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		aButtonColumn.setLayout(layout);
		GridData data = new GridData(GridData.FILL_BOTH);
		//data.minimumHeight = 500;
		//data.minimumWidth = 400;
		aButtonColumn.setLayoutData(data);
		return aButtonColumn;
	}

	protected void handleEdit() {
		isWebLib = button.getSelection();
	}

	@Override
	protected String getArchiveName(IProject proj, IVirtualComponent comp) {
		return super.getArchiveName(proj, comp);
	}

	@Override
	public void performFinish(IProgressMonitor monitor) throws CoreException {
		for (int i = 0; i < selected.length; i++) {
			IProject proj = selected[i];
			if(JavaEEProjectUtilities.getJ2EEProjectType(proj).equals("")) //$NON-NLS-1$
				J2EEProjectUtilities.createFlexJavaProjectForProjectOperation(proj).execute(monitor, null);
		}
		String location = "/"; //$NON-NLS-1$
		if(isWebLib) location += J2EEConstants.WEB_INF_LIB;
		getTaskModel().putObject(IReferenceWizardConstants.DEFAULT_LIBRARY_LOCATION, location);
		super.performFinish(monitor);
	}
	
}
