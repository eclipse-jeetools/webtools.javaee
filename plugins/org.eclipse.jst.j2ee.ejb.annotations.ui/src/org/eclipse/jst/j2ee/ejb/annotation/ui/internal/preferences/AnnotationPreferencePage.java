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
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.AnnotationPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class AnnotationPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, SelectionListener {

	DialogPanel panel;

	public AnnotationPreferencePage() {
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
		GridData gridData = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
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
		GridData gridData = new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL);
		defPanel.setLayoutData(gridData);

		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		label.setLayoutData(gridData);
		label.setText("Set J2EE Annotation Preferences");

		panel.preferences = new Control[1];
		panel.preferences[0] = panel
				.createLabeledCombo(
						1,
						false,
						true,
						"Active Annotation Provider:",
						"Choose the annotation provider that you will use for J2EE development and artifacts",
						AnnotationPreferenceStore
								.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER),
						AnnotationUtilities.getProviderNames(), defPanel);
		final Combo annotationProvider = (Combo) panel.preferences[0];
		ModifyListener listener = new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				String provider = annotationProvider.getText();
				AnnotationPreferencePage.this.setValid(true);

			}
		};

		annotationProvider.addModifyListener(listener);
		return composite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.preference.PreferencePage#isValid()
	 */
	public boolean isValid() {
		return true;
	}

	public boolean performOk() {

		String itemValue = "";

		Combo combo = ((Combo) panel.preferences[0]);
		itemValue = combo.getItem(combo.getSelectionIndex());
		AnnotationPreferenceStore.setProperty(
				AnnotationPreferenceStore.ANNOTATIONPROVIDER, itemValue);
		return super.performOk();
	}

}