/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.j2ee.internal.delete.DeleteOptions;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.model.WorkbenchLabelProvider;


public class DeleteEARComposite extends Composite implements J2EEDeleteUIConstants, Listener, ICheckStateListener {

	protected DeleteEARDialog dialog;
	protected Button deleteAppProjectsBtn;
	protected Button deleteRefProjectsBtn;
	protected Composite radioComposite;
	protected Button detailsBtn;
	protected DeleteModuleReferencesComposite moduleRefsComposite;
	protected CheckboxTableViewer projectsList;
	protected boolean listCreated = false;
	protected Map referencedProjects;
	protected WorkbenchLabelProvider workbenchLabelProvider = new WorkbenchLabelProvider();

	/**
	 * Constructor for DeleteEARComposite.
	 * 
	 * @param parent
	 * @param style
	 */
	public DeleteEARComposite(Composite parent, DeleteEARDialog dialog, int style, Set referencedProjects) {
		super(parent, style);
		this.dialog = dialog;
		initReferencedProjects(referencedProjects);
		addChildren();
	}

	/**
	 * Answer the referenced projects which the user has chosen to also delete
	 */
	public java.util.List getSelectedReferencedProjects() {
		if (deleteAppProjectsBtn.getSelection())
			return Collections.EMPTY_LIST;
		java.util.List result = new ArrayList();
		for (Iterator iter = referencedProjects.entrySet().iterator(); iter.hasNext();) {
			Map.Entry element = (Map.Entry) iter.next();
			boolean isSelected = ((Boolean) element.getValue()).booleanValue();
			if (isSelected)
				result.add(element.getKey());
		}
		return result;
	}

	/**
	 * @see J2EEDeleteDialog#createDeleteOptions()
	 */
	public DeleteOptions createDeleteOptions() {
		DeleteOptions opts = new DeleteOptions();
		opts.setIsEARDelete(true);
		opts.setDeleteProjects(true);
		opts.setDeleteModuleDependencies(moduleRefsComposite.shouldDeleteModuleDependencies());
		opts.setDeleteModules(moduleRefsComposite.shouldDeleteModules());
		opts.setSelectedReferencedProjects(getSelectedReferencedProjects());
		return opts;
	}



	protected void initReferencedProjects(Set projects) {
		referencedProjects = new HashMap();
		for (Iterator iter = projects.iterator(); iter.hasNext();) {
			IProject project = (IProject) iter.next();
			referencedProjects.put(project, Boolean.TRUE);
		}
	}

	protected void addChildren() {
		setLayout();
		addRadioComposite();
		moduleRefsComposite = new DeleteModuleReferencesComposite(this, SWT.NONE, true);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		data.horizontalIndent = 10;
		moduleRefsComposite.setLayoutData(data);
		//setup defaults
		deleteAppProjectsBtn.setSelection(true);
		deleteAppProjectsBtnSelected();
	}

	protected void setLayout() {
		GridLayout lay = new GridLayout();
		lay.numColumns = 1;
		setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		setLayoutData(data);
	}

	protected void addRadioComposite() {
		radioComposite = new Composite(this, SWT.NONE);
		GridLayout lay = new GridLayout();
		lay.numColumns = 2;
		radioComposite.setLayout(lay);
		GridData data = new GridData(GridData.FILL_BOTH);
		radioComposite.setLayoutData(data);
		deleteAppProjectsBtn = new Button(radioComposite, SWT.RADIO);
		deleteAppProjectsBtn.setText(DELETE_EAR_PROJECTS);
		deleteAppProjectsBtn.addListener(SWT.Selection, this);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		deleteAppProjectsBtn.setLayoutData(data);

		deleteRefProjectsBtn = new Button(radioComposite, SWT.RADIO);
		deleteRefProjectsBtn.setText(DELETE_REFERENCED_PROJECTS);
		deleteRefProjectsBtn.addListener(SWT.Selection, this);
		deleteRefProjectsBtn.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		detailsBtn = new Button(radioComposite, SWT.PUSH);
		detailsBtn.setText(IDialogConstants.SHOW_DETAILS_LABEL);
		detailsBtn.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		detailsBtn.addListener(SWT.Selection, this);
		detailsBtn.setEnabled(false);
	}

	/**
	 * @see Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == deleteAppProjectsBtn)
			deleteAppProjectsBtnSelected();
		else if (event.widget == deleteRefProjectsBtn)
			deleteRefProjectsBtnSelected();
		else if (event.widget == detailsBtn)
			detailsBtnSelected();
	}

	protected void deleteAppProjectsBtnSelected() {
		if (deleteAppProjectsBtn.getSelection()) {
			if (listCreated)
				toggleDetailsArea();
			detailsBtn.setEnabled(false);
			moduleRefsComposite.setButtonsEnabled(false);
		}
	}

	protected void deleteRefProjectsBtnSelected() {
		if (deleteRefProjectsBtn.getSelection()) {
			detailsBtn.setEnabled(true);
			moduleRefsComposite.setButtonsEnabled(true);
		}
	}

	/**
	 * Toggles the unfolding of the details area. This is triggered by the user pressing the details
	 * button.
	 */
	protected void toggleDetailsArea() {
		Point windowSize = getShell().getSize();
		Point oldSize = dialog.getContents().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		if (listCreated) {
			projectsList.getControl().dispose();
			listCreated = false;
			detailsBtn.setText(IDialogConstants.SHOW_DETAILS_LABEL);
		} else {
			createDropDownList();
			detailsBtn.setText(IDialogConstants.HIDE_DETAILS_LABEL);
		}

		Point newSize = dialog.getContents().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		getShell().setSize(new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));
	}

	protected void createDropDownList() {
		// create the list
		projectsList = CheckboxTableViewer.newCheckList(radioComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		projectsList.setLabelProvider(createLabelProvider());
		projectsList.setSorter(new ViewerSorter() {
		});
		projectsList.addCheckStateListener(this);
		// fill the list
		populateList();

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL);
		data.heightHint = projectsList.getTable().getItemHeight() * referencedProjects.size();
		data.horizontalSpan = 2;
		data.horizontalIndent = 10;
		projectsList.getTable().setLayoutData(data);

		listCreated = true;
	}

	protected void populateList() {
		for (Iterator iter = referencedProjects.entrySet().iterator(); iter.hasNext();) {
			Map.Entry entry = (Map.Entry) iter.next();
			projectsList.add(entry.getKey());
			boolean checked = ((Boolean) entry.getValue()).booleanValue();
			projectsList.setChecked(entry.getKey(), checked);
		}
	}

	protected void detailsBtnSelected() {
		toggleDetailsArea();
	}

	/**
	 * @see ICheckStateListener#checkStateChanged(CheckStateChangedEvent)
	 */
	public void checkStateChanged(CheckStateChangedEvent event) {
		referencedProjects.put(event.getElement(), new Boolean(event.getChecked()));
	}

	protected ITableLabelProvider createLabelProvider() {
		return new ITableLabelProvider() {
			public void dispose() {
			}

			public Image getColumnImage(Object element, int columnIndex) {
				return workbenchLabelProvider.getImage(element);
			}

			/**
			 * @see ITableLabelProvider#getColumnText(Object, int)
			 */
			public String getColumnText(Object element, int columnIndex) {
				return workbenchLabelProvider.getText(element);
			}

			public void addListener(ILabelProviderListener listener) {
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
			}
		};
	}
}