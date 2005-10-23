/*******************************************************************************
 * Copyright (c) 2002, 2003,2004,2005 Eteration Bilisim A.S.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci Dai
 * For more information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import java.util.Map;
import java.util.ResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.Logger;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletBuildUtility;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletRuntime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;

public class XDocletPreferencePage extends PropertyPreferencePage implements SelectionListener {

	DialogPanel panel;

	private Map fData; // page data

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#applyData(java.lang.Object)
	 */
	public void applyData(Object data) {
		if (data instanceof Map) {
			fData = (Map) data;
		}

	}

	protected Map getData() {
		return fData;
	}

	protected boolean useProjectSettings() {
		return isProjectPreferencePage();
	}

	protected boolean isProjectPreferencePage() {
		return fProject != null;
	}

	protected IProject getProject() {
		return fProject;
	}

	public XDocletPreferencePage() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	protected Composite createContainer(Composite parent) {
		panel = new DialogPanel(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.makeColumnsEqualWidth = false;
		panel.setLayout(layout);
		GridData gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		panel.setLayoutData(gridData);
		return panel;
	}

	/*
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		// noDefaultAndApplyButton();
		Composite composite = createContainer(parent);
		GridLayout gridLayout = new GridLayout();
		gridLayout.marginHeight = 0;
		gridLayout.marginWidth = 0;
		composite.setLayout(gridLayout);

		Composite defPanel = new Composite(composite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 4;
		defPanel.setLayout(layout);
		GridData gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		defPanel.setLayoutData(gridData);

		ResourceBundle bundle = ResourceBundle.getBundle("org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui.preferences");

		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		label.setLayoutData(gridData);
		label.setText(bundle.getString("label_set_xdoclet_runtime_preference"));

		int numCont = 3;
		if (isProjectPreferencePage())
			numCont = 4;

		panel.preferences = new Control[numCont];
		panel.fActive = new Button[numCont];

		if (isProjectPreferencePage())
			panel.preferences[3] = panel.createLabeledCheck(3, getStore().isPropertyActive(
					XDocletPreferenceStore.XDOCLETUSEGLOBAL), bundle.getString("label_global_pref"), bundle
					.getString("desc_global_pref"), getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETUSEGLOBAL), defPanel);

		panel.preferences[0] = panel.createLabeledCheck(0, false, getStore().isPropertyActive(
				XDocletPreferenceStore.XDOCLETBUILDERACTIVE), bundle.getString("label_enable_xdoclet_builder"), bundle
				.getString("desc_enable_xdoclet_builder"), getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETBUILDERACTIVE),
				defPanel);
		panel.preferences[2] = panel.createLabeledPath(2, true, bundle.getString("label_xdoclet_home"), bundle
				.getString("desc_xdoclet_home"), getStore().getProperty(XDocletPreferenceStore.XDOCLETHOME), defPanel);
		panel.preferences[1] = panel.createLabeledCombo(1, false, true, bundle.getString("label_xdoclet_version"), bundle
				.getString("desc_xdoclet_version"), getStore().getProperty(XDocletPreferenceStore.XDOCLETVERSION), new String[] {
				"1.2.1", "1.2.2", "1.2.3" }, defPanel);
		final Text xDocletPath = (Text) panel.preferences[2];
		final Combo xDocletVersion = (Combo) panel.preferences[1];
		
		validateCurrentPreferences(xDocletPath, xDocletVersion);

		ModifyListener listener = new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				validateCurrentPreferences(xDocletPath, xDocletVersion);

			}
		};

		xDocletPath.addModifyListener(listener);
		xDocletVersion.addModifyListener(listener);
		return composite;
	}

	public boolean performOk() {

		String itemValue = "";
		itemValue = ((Text) panel.preferences[2]).getText();
		getStore().setProperty(XDocletPreferenceStore.XDOCLETHOME, itemValue);
		
		if (isProjectPreferencePage()) {
			getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETUSEGLOBAL, ((Button) panel.preferences[3]).getSelection());
		}
		
		Combo combo = ((Combo) panel.preferences[1]);
		int itemIndex = combo.getSelectionIndex();
		if (itemIndex != -1) {
			itemValue = combo.getItem(combo.getSelectionIndex());
			getStore().setProperty(XDocletPreferenceStore.XDOCLETVERSION, itemValue);
		} else {
			getStore().save();
			return false;
		}

		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETBUILDERACTIVE, ((Button) panel.preferences[0]).getSelection());
		getStore().save();
		try {
			XDocletBuildUtility.runNecessaryBuilders(new NullProgressMonitor(), (IProject)getElement());
		} catch (CoreException e) {
			Logger.logException(e);
		}
		return super.performOk();
	}

	protected void validateCurrentPreferences(final Text xDocletPath, final Combo xDocletVersion) {
		XDocletRuntime runtime = new XDocletRuntime();
		runtime.setHome(xDocletPath.getText());
		IStatus[] result = runtime.validate(xDocletVersion.getItem(xDocletVersion.getSelectionIndex()));
		// Clear the message
		XDocletPreferencePage.this.setErrorMessage(null);
		if (result.length > 0) {
			XDocletPreferencePage.this.setErrorMessage(result[0].getMessage());
			// XDocletPreferencePage.this.setValid(false);
		} else {
			setMessage("All libraries found", IMessageProvider.INFORMATION);
			// XDocletPreferencePage.this.setValid(true);
		}
	}

}