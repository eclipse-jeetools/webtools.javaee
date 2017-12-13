/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class AttributeWizardPage extends WizardPage {

	IExtension extension;
	XDocletPreferenceStore preferenceStore;
	AttributesDecorator attributesDecorator;

	public AttributeWizardPage(String pageName, String title, ImageDescriptor titleImage) {
		super(pageName, title, titleImage);
	}

	public AttributeWizardPage(String pageName, IExtension extension, XDocletPreferenceStore preferenceStore) {
		super(pageName);
		this.preferenceStore = preferenceStore;
		this.extension = extension;
	}

	public void createControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		composite.setLayoutData(data);

		initializeDialogUnits(composite);

		attributesDecorator = new AttributesDecorator(extension, preferenceStore);
		attributesDecorator.decorate(composite);
		setControl(composite);

	}

	public void doFinish() {
		attributesDecorator.save();
	}

}
