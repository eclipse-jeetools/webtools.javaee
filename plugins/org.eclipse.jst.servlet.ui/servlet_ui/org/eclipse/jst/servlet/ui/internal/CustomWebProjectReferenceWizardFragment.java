/******************************************************************************
 * Copyright (c) 2009 Red Hat, IBM
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - Ongoing improvements
 ******************************************************************************/
package org.eclipse.jst.servlet.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ProjectReferenceWizardFragment;
import org.eclipse.wst.common.componentcore.ui.internal.taskwizard.IWizardHandle;

public class CustomWebProjectReferenceWizardFragment extends ProjectReferenceWizardFragment {
	
	public CustomWebProjectReferenceWizardFragment() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean isWebLib;
	private Button button;
	
	@Override
	public Composite createComposite(Composite parent, IWizardHandle handle) {
		
		handle.setTitle(org.eclipse.wst.common.componentcore.ui.Messages.ProjectReferenceTitle);
		handle.setDescription(org.eclipse.wst.common.componentcore.ui.Messages.ProjectReferenceDescription);
		Composite c = newComposite(parent);
		
		createTable(c);
		createButtonColumn(c);
		return c;
	}
	private void createTable(Composite c) {
		
		Composite comp = createTableColumnComposite(c);
		
		viewer = new TreeViewer(comp, SWT.SINGLE | SWT.BORDER);
		viewer.setContentProvider(getContentProvider());
		viewer.setLabelProvider(getLabelProvider());
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				selChanged();
			}
		});
		viewer.setInput(ResourcesPlugin.getWorkspace());
	}
	private Composite newComposite(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		c.setLayout(layout);
		c.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,true));
		return c;
	}
	protected void createButtonColumn(Composite parent) {
		Composite buttonColumn = createButtonColumnComposite(parent);
		GridData data = new GridData(GridData.VERTICAL_ALIGN_END);
		buttonColumn.setLayoutData(data);
		button = new Button(buttonColumn, SWT.CHECK);
		button.setText(Messages.getString("CustomWebProjectReferenceWizardFragment.0")); //$NON-NLS-1$
		button.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		button.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				handleEdit();
			}
		});
	}
	public Composite createButtonColumnComposite(Composite parent) {
		Composite aButtonColumn = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		aButtonColumn.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		aButtonColumn.setLayoutData(data);
		return aButtonColumn;
	}
	public Composite createTableColumnComposite(Composite parent) {
		Composite aButtonColumn = new Composite(parent, SWT.NONE);
//		GridLayout layout = new GridLayout();
//		layout.numColumns = 1;
//		layout.marginHeight = 0;
//		layout.marginWidth = 0;
		aButtonColumn.setLayout(new FillLayout());
//		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
//				| GridData.VERTICAL_ALIGN_FILL);
//		aButtonColumn.setLayoutData(data);
		return aButtonColumn;
	}

	protected void handleEdit() {
		isWebLib = button.getSelection();
	}

	@Override
	protected String getArchiveName(IProject proj, IVirtualComponent comp) {
		// TODO Auto-generated method stub
		String name = super.getArchiveName(proj, comp);
		if (isWebLib)
			name = new Path(J2EEConstants.WEB_INF_LIB).append(name).makeAbsolute().toString();
		
		return name;
	}

}
