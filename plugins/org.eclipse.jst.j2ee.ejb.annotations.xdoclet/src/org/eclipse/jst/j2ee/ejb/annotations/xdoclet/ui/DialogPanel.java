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
package org.eclipse.jst.j2ee.ejb.annotations.xdoclet.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;



public class DialogPanel extends Composite {

	public Control preferences[] = null;

	public Button fActive[] = null;

	public DialogPanel(Composite parent, int style) {
		super(parent, style);
	}

	public Text createLabeledText(int i, boolean checked, String title,
			String tip, String value, Composite defPanel) {
		return this.createLabeledText(i, true, checked, title, tip, value,
				defPanel);
	}

	public Text createLabeledText(int i, boolean addActiveButton,
			boolean checked, String title, String tip, String value,
			Composite defPanel) {
		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		label.setLayoutData(gridData);
		label.setText(title);
		label.setToolTipText(tip);

		if (addActiveButton) {
			Button fButton = new Button(defPanel, SWT.CHECK);

			gridData = new GridData(GridData.BEGINNING);
			gridData.horizontalSpan = 1;
			fButton.setLayoutData(gridData);
			fButton.setSelection(checked);
			fActive[i] = fButton;
		}
		Text fText = new Text(defPanel, SWT.SHADOW_IN | SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = (addActiveButton ? 2 : 3);
		fText.setLayoutData(gridData);
		fText.setText(value);
		fText.setToolTipText(tip);
		return fText;
	}

	public Button createLabeledCheck(int i, boolean checked, String title,
			String tip, boolean value, Composite defPanel) {
		return createLabeledCheck(i, true, checked, title, tip, value, defPanel);
	}

	public Button createLabeledCheck(int i, boolean addActiveButton,
			boolean checked, String title, String tip, boolean value,
			Composite defPanel) {
		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		label.setLayoutData(gridData);
		label.setText(title);
		label.setToolTipText(tip);

		Button fButton = new Button(defPanel, SWT.CHECK);
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		fButton.setLayoutData(gridData);
		fButton.setSelection(value);
		fButton.setToolTipText(tip);
		fButton.setSelection(checked);
		fActive[i] = fButton;
		return fButton;
	}

	public Combo createLabeledCombo(int itemI, boolean checked, String title,
			String tip, String value, String[] choices, Composite defPanel) {

		return createLabeledCombo(itemI, true, checked, title, tip, value,
				choices, defPanel);
	}

	public Combo createLabeledCombo(int itemI, boolean addActiveButton,
			boolean checked, String title, String tip, String value,
			String[] choices, Composite defPanel) {

		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		label.setLayoutData(gridData);
		label.setText(title);
		label.setToolTipText(tip);

		if (addActiveButton) {
			Button fButton = new Button(defPanel, SWT.CHECK);
			gridData = new GridData();
			gridData.horizontalSpan = 1;
			fButton.setLayoutData(gridData);
			fButton.setSelection(checked);
			fActive[itemI] = fButton;
		}

		Combo fCombo = new Combo(defPanel, SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = (addActiveButton ? 2 : 3);
		fCombo.setLayoutData(gridData);
		fCombo.setToolTipText(tip);
		int selected = 0;
		for (int i = 0; i < choices.length; i++) {
			String item = choices[i];
			if (value.equals(item))
				selected = i;
			fCombo.add(item);
		}
		fCombo.select(selected);
		return fCombo;
	}

	public Text createLabeledPath(int i, boolean checked, String title,
			String tip, String value, Composite defPanel) {
		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		// gridData.horizontalSpan = 1;
		label.setLayoutData(gridData);
		label.setText(title);

		final Text fText = new Text(defPanel, SWT.SHADOW_IN | SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL
				| GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 1;
		fText.setLayoutData(gridData);
		fText.setText(value);
		Button fButton = new Button(defPanel, SWT.PUSH);
		fButton.setText("Browse...");
		fButton.setLayoutData(new GridData());
		fButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dlg = new DirectoryDialog(DialogPanel.this
						.getShell());
				dlg.setFilterPath(fText.getText());
				String res = dlg.open();
				if (res != null) {
					fText.setText(res);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}

		});

		return fText;
	}

}
