/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AttributesDecorator {

	private IExtension extension;
	private IConfigurationElement tag;
	private IConfigurationElement[] params;
	HashMap allProperties = new HashMap();

	private XDocletPreferenceStore preferenceStore;

	public AttributesDecorator(IExtension extension, XDocletPreferenceStore preferenceStore) {
		super();

		this.extension = extension;
		params = this.extension.getConfigurationElements();
		tag = params[0];
		this.preferenceStore = preferenceStore;
	}

	public void decorate(final Composite composite) {

		Group attributes = new Group(composite, SWT.NONE);
		attributes.setText("Attributes " + tag.getAttribute("name"));
		attributes.setToolTipText(tag.getAttribute("description"));

		GridLayout layout = new GridLayout(4, false);
		attributes.setLayout(layout);
		GridData attributesGrid = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		attributes.setLayoutData(attributesGrid);

		allProperties = new HashMap();
		// The first configuration is the tag provider so skip
		for (int i = 1; i < params.length; i++) {
			final IConfigurationElement attribute = params[i];

			Group attributeGroup = new Group(attributes, SWT.NONE);
			attributeGroup.setText(attribute.getAttribute("label"));
			attributeGroup.setToolTipText(attribute.getAttribute("description"));
			layout = new GridLayout(3, false);
			attributeGroup.setLayout(layout);
			GridData gridData = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
			attributeGroup.setLayoutData(gridData);
			createAttributeControl(attributeGroup, attribute);
			String includeId = attribute.getAttribute("id") + ".include";
			Button bool = createLabeledCheck("include", preferenceStore.getBooleanProperty(includeId), attributes);
			bool.setData(attribute);
			allProperties.put(includeId, bool);
			bool.addSelectionListener(new SelectionListener() {
				public void widgetSelected(SelectionEvent e) {

					String id = attribute.getAttribute("id");
					preferenceStore.setProperty(id + ".include", ((Button) e.item).getSelection());
				}

				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});

		}
		Dialog.applyDialogFont(composite);
	}

	private void createAttributeControl(Composite parent, IConfigurationElement attribute) {
		String type = attribute.getAttribute("type");
		if ("string".equals(type)) {
			Text str = createLabeledText("", preferenceStore.getProperty(attribute.getAttribute("id")), parent);
			str.setData(attribute);
			allProperties.put(attribute.getAttribute("id"), str);
		} else if ("boolean".equals(type)) {
			Button bool = createLabeledCheck("", preferenceStore.getBooleanProperty(attribute.getAttribute("id")), parent);
			bool.setData(attribute);
			allProperties.put(attribute.getAttribute("id"), bool);
		} else if ("enum".equals(type)) {
			Combo combo = createLabeledCombo(parent, attribute);
			combo.setData(attribute);
			allProperties.put(attribute.getAttribute("id"), combo);
		}

	}

	private Combo createLabeledCombo(Composite defPanel, IConfigurationElement attribute) {

		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		label.setLayoutData(gridData);
		label.setText("");

		Combo combo = new Combo(defPanel, SWT.READ_ONLY);
		gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 2;
		combo.setLayoutData(gridData);
		String value = preferenceStore.getProperty(attribute.getAttribute("id"));
		StringTokenizer tokenizer = new StringTokenizer(attribute.getAttribute("restriction"), ",");
		int i = 0;
		int sel = 0;
		while (tokenizer.hasMoreTokens()) {
			String item = tokenizer.nextToken().trim();
			if (item.equals(value))
				sel = i;
			combo.add(item);
			++i;

		}
		if (combo.getItemCount() > 0)
			combo.select(sel);
		return combo;
	}

	private Button createLabeledCheck(String title, boolean value, Composite defPanel) {
		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		label.setLayoutData(gridData);
		label.setText(title);

		Button fButton = new Button(defPanel, SWT.CHECK);

		gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 2;
		fButton.setLayoutData(gridData);
		fButton.setSelection(value);
		fButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {

			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		return fButton;
	}

	private Text createLabeledText(String title, String value, Composite defPanel) {
		GridData gridData;
		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		label.setLayoutData(gridData);
		label.setText(title);

		Text text = new Text(defPanel, SWT.SHADOW_IN | SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
		gridData.horizontalSpan = 2;
		text.setLayoutData(gridData);
		text.setText(value);

		return text;
	}

	public void save() {
		Iterator iterator = allProperties.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			Object widget = allProperties.get(key);
			if (widget instanceof Text) {
				Text text = (Text) widget;
				String value = text.getText();
				preferenceStore.setProperty(key, value);
			} else if (widget instanceof Button) {
				Button bool = (Button) widget;
				preferenceStore.setProperty(key, bool.getSelection());
			} else if (widget instanceof Combo) {
				Combo combo = (Combo) widget;
				if (combo.getSelectionIndex() >= 0) {
					String value = combo.getItem(combo.getSelectionIndex());
					preferenceStore.setProperty(key, value);
				}
			}
		}

	}

}