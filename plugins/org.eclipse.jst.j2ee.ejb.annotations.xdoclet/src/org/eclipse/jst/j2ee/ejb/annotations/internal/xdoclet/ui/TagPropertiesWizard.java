/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAnnotationPlugin;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;

public class TagPropertiesWizard extends Wizard {

	IExtension extension;
	IConfigurationElement[] params;
	IConfigurationElement tag;
	AttributeWizardPage mainPage;
	private XDocletPreferenceStore preferenceStore;
	
	public TagPropertiesWizard(IExtension extension, XDocletPreferenceStore store)
	{
		this.extension = extension;
		this.preferenceStore = store;
		params = extension.getConfigurationElements();
		tag = params[0];
		
		setDefaultPageImageDescriptor(XDocletAnnotationPlugin.createImageDescriptor(XDocletAnnotationPlugin.getDefault().getBundle().getEntry("/"),"icons/xdoclet.jpg"));
		setWindowTitle(tag.getAttribute("name"));
	}
	public boolean performFinish() {
		mainPage.doFinish();
		return true;
	}
	
	
	public void addPages() {
		try {
			super.addPages();
			mainPage = new AttributeWizardPage(tag.getAttribute("name") + " attributes", extension, preferenceStore);
			mainPage.setWizard(this);
			addPage(mainPage);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}		
	}

}
