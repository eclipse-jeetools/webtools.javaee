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



import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.XDocletRuntime;
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
import org.eclipse.ui.IWorkbenchPreferencePage;


public class XDocletPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage, SelectionListener {

	DialogPanel panel;

	
	
	public XDocletPreferencePage() {
		super();
	}

	

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent e) {
	}

	/* (non-Javadoc)
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
		//noDefaultAndApplyButton();
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
		label.setText("Set XDoclet Runtime Preferences");
	
		panel.preferences = new Control[3];
		panel.fActive = new Button[3];
		panel.preferences[0] = panel.createLabeledCheck(0,false,XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.XDOCLETBUILDERACTIVE),"Enable XDoclet Builder:","Enables xdoclet builder for automatic generation of java classes",XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.XDOCLETBUILDERACTIVE),defPanel);
		panel.preferences[1] = panel.createLabeledCombo(1,false,true,"Version:","XDoclet version",XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETVERSION),new String[]{"1.2.1","1.2.2"},defPanel);
		panel.preferences[2] = panel.createLabeledPath(2,true,"XDoclet Home:","The path of the xdoclet runtime installation",XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETHOME),defPanel);
		final Text xDocletPath = (Text)panel.preferences[2];
		final Combo xDocletVersion = (Combo)panel.preferences[1];
		ModifyListener listener = new ModifyListener(){

			public void modifyText(ModifyEvent e) {
				XDocletRuntime runtime = new XDocletRuntime();
				runtime.setHome(xDocletPath.getText());
				IStatus []result = runtime.validate(xDocletVersion.getItem(xDocletVersion.getSelectionIndex()));
				if(result.length > 0){
					XDocletPreferencePage.this.setErrorMessage(result[0].getMessage());
					XDocletPreferencePage.this.setValid(false);
				}else{
					setMessage("All libraries found", IMessageProvider.INFORMATION);
					XDocletPreferencePage.this.setValid(true);
				}
				
			}};
			
		xDocletPath.addModifyListener(listener);
		xDocletVersion.addModifyListener(listener);
		return composite;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jface.preference.PreferencePage#isValid()
	 */
	public boolean isValid() {
		XDocletRuntime runtime = new XDocletRuntime();
		Text xdocletPath = (Text)panel.preferences[2];
		Combo xdocletVersion = (Combo)panel.preferences[1];
		runtime.setHome(xdocletPath.getText());
		IStatus []result = runtime.validate(xdocletVersion.getItem(xdocletVersion.getSelectionIndex()));		
		return result.length == 0;
	}

	public boolean performOk() {

		String itemValue = "";
		itemValue = ((Text)panel.preferences[2]).getText();
		XDocletPreferenceStore.setProperty(XDocletPreferenceStore.XDOCLETHOME,itemValue);

		Combo combo = ((Combo)panel.preferences[1]);
		itemValue = combo.getItem(combo.getSelectionIndex());
		XDocletPreferenceStore.setProperty(XDocletPreferenceStore.XDOCLETVERSION,itemValue);
		
		XDocletPreferenceStore.setPropertyActive(XDocletPreferenceStore.XDOCLETBUILDERACTIVE,((Button)panel.preferences[0]).getSelection() );
		
		return super.performOk();
	}

}