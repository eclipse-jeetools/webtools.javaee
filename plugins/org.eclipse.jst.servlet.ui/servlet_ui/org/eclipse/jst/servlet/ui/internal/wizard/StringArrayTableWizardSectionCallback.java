/*******************************************************************************
 * Copyright (c) 2007, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.wizard.StringArrayTableWizardSection.StringArrayDialogCallback;
import org.eclipse.swt.widgets.Text;

/**
 * Implementation of the <code>StringArrayDialogCallback</code> interface for 
 * both "Initialization Parameters" and "URL Mappings" table views. 
 */
public class StringArrayTableWizardSectionCallback implements
		StringArrayDialogCallback {

	/**
	 * The first text field should not be empty. 
	 */
	@Override
	public boolean validate(Text[] texts) {
		if (texts.length > 0) {
			return texts[0].getText().trim().length() > 0;
		}
		return true;
	}
	
	/**
	 * Trims the text values. 
	 */
	@Override
	public String[] retrieveResultStrings(Text[] texts) {
		int n = texts.length;
		String[] result = new String[n];
		for (int i = 0; i < n; i++) {
			result[i] = texts[i].getText().trim();
		}
		return result;
	}

}
