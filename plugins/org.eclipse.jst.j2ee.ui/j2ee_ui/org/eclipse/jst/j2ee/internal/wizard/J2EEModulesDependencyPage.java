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
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jst.j2ee.application.operations.ClassPathSelection;
import org.eclipse.jst.j2ee.application.operations.ClasspathElement;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.UpdateManifestDataModel;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelEvent;
import org.eclipse.wst.common.framework.operation.WTPOperationDataModelListener;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.internal.common.ui.WTPWizardPage;

/*
 * Created on Nov 13, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and
 * Comments
 */

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class J2EEModulesDependencyPage extends WTPWizardPage {

	private CheckboxTableViewer availableJarsViewer;

	public J2EEModulesDependencyPage(J2EEModuleCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_DESC));
	}

	protected void enter() {
		super.enter();
		updateJarViewer();
	}


	private void updateJarViewer() {
		J2EEModuleCreationDataModel dataModel = (J2EEModuleCreationDataModel) model;

		ClassPathSelection classPathSelection = dataModel.getClassPathSelection();

		if (availableJarsViewer.getInput() != classPathSelection) {
			availableJarsViewer.setInput(classPathSelection);
		}
	}

	protected String[] getValidationPropertyNames() {
		return null;
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		setInfopopID(IJ2EEUIContextIds.NEW_EJB_WIZARD_P3);
		Label projectLabel = new Label(composite, SWT.NONE);
		projectLabel.setText(WorkbenchMessages.getString("WizardNewProjectCreationPage.nameLabel")); //$NON-NLS-1$
		Text projectText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		synchHelper.synchText(projectText, EditModelOperationDataModel.PROJECT_NAME, null);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		projectText.setLayoutData(gd);

		Label earLabel = new Label(composite, SWT.NONE);
		earLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_PROJECT_FOR_MODULE_CREATION));
		Text earText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
		synchHelper.synchText(earText, J2EEModuleCreationDataModel.EAR_PROJECT_NAME, null);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		earText.setLayoutData(gd);

		Label spacerLabel = new Label(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		spacerLabel.setLayoutData(gd);

		Label jarsLabel = new Label(composite, SWT.NONE);
		jarsLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_AVAILABLE_JARS));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		jarsLabel.setLayoutData(gd);

		Composite tableComposite = new Composite(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		gd.horizontalIndent = 0;
		gd.heightHint = 60;
		gd.widthHint = 200;
		tableComposite.setLayoutData(gd);
		GridLayout tableLayout = new GridLayout();
		tableLayout.marginWidth = 0;
		tableComposite.setLayout(tableLayout);
		createAvailableJarsList(tableComposite);

		spacerLabel = new Label(composite, SWT.NONE);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		spacerLabel.setLayoutData(gd);

		Label classpathLabel = new Label(composite, SWT.NONE);
		classpathLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_CLASSPATH));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		classpathLabel.setLayoutData(gd);

		Text classpathText = new Text(composite, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.READ_ONLY);
		synchHelper.synchText(classpathText, J2EEModuleCreationDataModel.JAR_LIST_TEXT_UI, null);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.heightHint = 30;
		classpathText.setLayoutData(gd);

		return composite;
	}

	protected void createAvailableJarsList(Composite listGroup) {
		availableJarsViewer = CheckboxTableViewer.newCheckList(listGroup, SWT.CHECK | SWT.FULL_SELECTION | SWT.BORDER);
		addResizeListenerToTable();
		AvailableJarsProvider provider = new AvailableJarsProvider();
		availableJarsViewer.setContentProvider(provider);
		availableJarsViewer.setLabelProvider(provider);
		availableJarsViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				availableJARCheckStateChanged(event);
			}
		});
		Table table = availableJarsViewer.getTable();
		// set up table layout
		TableLayout tableLayout = new org.eclipse.jface.viewers.TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		table.setLayout(tableLayout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		availableJarsViewer.setSorter(null);
		GridData gd = new GridData(GridData.FILL_BOTH);
		table.setLayoutData(gd);

		// table columns
		TableColumn fileNameColumn = new TableColumn(table, SWT.NONE);
		fileNameColumn.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_TABLE_MODULE));
		fileNameColumn.setResizable(true);

		TableColumn projectColumn = new TableColumn(table, SWT.NONE);
		projectColumn.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_TABLE_PROJECT));
		projectColumn.setResizable(true);

		updateJarViewer();
	}

	public void availableJARCheckStateChanged(CheckStateChangedEvent event) {
		ClasspathElement element = (ClasspathElement) event.getElement();
		element.setSelected(event.getChecked());
		String classEntry = element.getText();
		UpdateManifestDataModel updateManifest = ((J2EEModuleCreationDataModel) model).getUpdateManifestDataModel();
		List classpathList = (List) updateManifest.getProperty(UpdateManifestDataModel.JAR_LIST);
		if (event.getChecked()) {
			if (!classpathList.contains(classEntry)) {
				classpathList.add(classEntry);
			}
		} else if (classpathList.contains(classEntry)) {
			classpathList.remove(classEntry);
		}
		updateManifest.setProperty(UpdateManifestDataModel.JAR_LIST, classpathList);
		updateManifest.propertyChanged(new WTPOperationDataModelEvent(updateManifest, UpdateManifestDataModel.JAR_LIST, classpathList, classpathList, WTPOperationDataModelListener.PROPERTY_CHG));
	}

	protected void addResizeListenerToTable() {
		Table aTable = availableJarsViewer.getTable();
		aTable.addControlListener(new ControlAdapter() {
			boolean fResized = false;

			public void controlResized(ControlEvent e) {
				if (e.widget instanceof Table && !fResized) {
					final Table table = (Table) e.widget;
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							Point size = table.getSize();
							if (size.x > 4) {
								setResized();
								int newSize = size.x / 2 - 2;
								TableColumn column = table.getColumn(0);
								if (column != null && !column.isDisposed())
									column.setWidth(newSize);
								column = table.getColumn(1);
								if (column != null && !column.isDisposed())
									column.setWidth(newSize);
							}
						}
					});
				}
			}

			public void setResized() {
				fResized = true;
			}
		});
	}

}