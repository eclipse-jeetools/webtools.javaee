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
import org.eclipse.jst.j2ee.internal.rename.RenameOptions;
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


public class RenameEARComposite extends Composite implements J2EERenameUIConstants, Listener, ICheckStateListener {

	protected Button renameAppProjectsBtn;
	protected Button renameRefProjectsBtn;
	protected Composite radioComposite;
	protected Button detailsBtn;
	protected RenameModuleReferencesComposite moduleRefsComposite;
	protected CheckboxTableViewer projectsList;
	protected boolean listCreated = false;
	protected Map referencedProjects;
	protected WorkbenchLabelProvider workbenchLabelProvider = new WorkbenchLabelProvider();

	/**
	 * Constructor for RenameEARComposite.
	 * 
	 * @param parent
	 * @param style
	 */
	public RenameEARComposite(Composite parent, int style, Set referencedProjects) {
		super(parent, style);
		initReferencedProjects(referencedProjects);
		addChildren();
	}

	/**
	 * Answer the referenced projects which the user has chosen to also rename
	 */
	public java.util.List getSelectedReferencedProjects() {
		if (renameAppProjectsBtn.getSelection())
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
	 * @see J2EERenameDialog#createRenameOptions()
	 */
	public RenameOptions createRenameOptions() {
		RenameOptions opts = new RenameOptions();
		opts.setIsEARRename(true);
		opts.setRenameProjects(true);
		opts.setRenameModuleDependencies(moduleRefsComposite.shouldRenameModuleDependencies());
		opts.setRenameModules(moduleRefsComposite.shouldRenameModules());
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
		moduleRefsComposite = new RenameModuleReferencesComposite(this, SWT.NONE, true);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		data.horizontalIndent = 10;
		moduleRefsComposite.setLayoutData(data);
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
		renameAppProjectsBtn = new Button(radioComposite, SWT.RADIO);
		renameAppProjectsBtn.setText(RENAME_EAR_PROJECTS);
		renameAppProjectsBtn.addListener(SWT.Selection, this);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		renameAppProjectsBtn.setLayoutData(data);

		renameRefProjectsBtn = new Button(radioComposite, SWT.RADIO);
		renameRefProjectsBtn.setText(RENAME_REFERENCED_PROJECTS);
		renameRefProjectsBtn.addListener(SWT.Selection, this);
		renameRefProjectsBtn.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
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
		if (event.widget == renameAppProjectsBtn)
			renameAppProjectsBtnSelected();
		else if (event.widget == renameRefProjectsBtn)
			renameRefProjectsBtnSelected();
		else if (event.widget == detailsBtn)
			detailsBtnSelected();
	}

	protected void renameAppProjectsBtnSelected() {
		if (renameAppProjectsBtn.getSelection()) {
			if (listCreated)
				toggleDetailsArea();
			detailsBtn.setEnabled(false);
			moduleRefsComposite.setButtonsEnabled(false);
		}
	}

	protected void renameRefProjectsBtnSelected() {
		if (renameRefProjectsBtn.getSelection()) {
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
		Point oldSize = getParent().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		if (listCreated) {
			projectsList.getControl().dispose();
			listCreated = false;
			detailsBtn.setText(IDialogConstants.SHOW_DETAILS_LABEL);
		} else {
			createDropDownList();
			detailsBtn.setText(IDialogConstants.HIDE_DETAILS_LABEL);
		}

		Point newSize = getParent().computeSize(SWT.DEFAULT, SWT.DEFAULT);

		getShell().setSize(new Point(windowSize.x, windowSize.y + (newSize.y - oldSize.y)));
	}

	protected void createDropDownList() {
		// create the list
		projectsList = CheckboxTableViewer.newCheckList(radioComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		projectsList.setLabelProvider(createLabelProvider());
		projectsList.setSorter(new ViewerSorter() {/*viewersorter*/});
		projectsList.addCheckStateListener(this);
		// fill the list
		populateList();

		GridData data = new GridData(GridData.FILL_BOTH);
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
				//dispose
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
				//do nothing
			}

			public boolean isLabelProperty(Object element, String property) {
				return false;
			}

			public void removeListener(ILabelProviderListener listener) {
				//do nothing
			}
		};
	}
}