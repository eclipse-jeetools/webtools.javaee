/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Preferences;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class JavaEEPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private Preferences preferences;
	private String name = J2EEPlugin.DYNAMIC_TRANSLATION_OF_JET_TEMPLATES_PREF_KEY;
	private Button showReferences;
	private boolean dynamicTranslation;
	private ArrayList<IJavaEEPreferencePageExtender> extenders = new ArrayList();

	public JavaEEPreferencePage() {
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVA_EE_PREFERENCE_PAGE_NAME));
	}

	@Override
	protected void performDefaults() {
		preferences.setToDefault(name);
		J2EEPlugin.getDefault().savePluginPreferences();
		dynamicTranslation = preferences.getBoolean(name);
		showReferences.setSelection(dynamicTranslation);
		for(IJavaEEPreferencePageExtender extender : extenders ){
			 extender.performDefaults();
		}

		super.performDefaults();
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite result= new Composite(parent, SWT.NONE);
		GridLayout layout= new GridLayout();
		layout.marginHeight= convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth= 0;
		layout.verticalSpacing= convertVerticalDLUsToPixels(10);
		layout.horizontalSpacing= convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		result.setLayout(layout);
		Group buttonComposite= new Group(result, SWT.NONE);
		buttonComposite.setLayout(new GridLayout());		
		buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        buttonComposite.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVA_EE_PREFERENCE_PAGE_JET_TEMPLATE));

		showReferences = new Button(buttonComposite, SWT.CHECK);
		showReferences.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.JAVA_EE_PREFERENCE_PAGE_DYN_TRANSLATION_BTN_NAME)); 
		showReferences.setSelection(dynamicTranslation);
		showReferences.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dynamicTranslation = showReferences.getSelection();
			}
		});
		invokeExtensions(result);

		return result;
	}

	public void init(IWorkbench workbench) {
		preferences = J2EEPlugin.getDefault().getPluginPreferences();
		dynamicTranslation = preferences.getBoolean(name);
	}

	@Override
	public boolean performOk() {
		preferences.setValue(name, showReferences.getSelection());
		J2EEPlugin.getDefault().savePluginPreferences();
		boolean result = false;
		for(IJavaEEPreferencePageExtender extender : extenders ){
			result = extender.performOk();
		}
		result = super.performOk();
		return result;
	}
	
	
	protected void invokeExtensions(Composite parent){
		List<JavaEEPreferencePageExtension> list = JavaEEPreferencePageExtensionReader.getInstance().getJavaEEPageExtenders();
		for(JavaEEPreferencePageExtension pageExtension : list ){
			IJavaEEPreferencePageExtender extender = pageExtension.getInstance();
			extenders.add(extender);
			extender.extendPage(parent);
		}
	}
	
	 @Override
	public void dispose(){
			for(IJavaEEPreferencePageExtender extender : extenders ){
				extender.dispose();
			}
	 }
}