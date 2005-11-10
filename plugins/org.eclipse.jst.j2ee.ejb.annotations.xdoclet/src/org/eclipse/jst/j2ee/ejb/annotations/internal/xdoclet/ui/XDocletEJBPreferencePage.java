/*******************************************************************************
 * Copyright (c) 2002, 2003,2004 Eteration Bilisim A.S.
 * Naci Dai and others.
 * 
 * Parts developed under contract ref:FT/R&D/MAPS/AMS/2004-09-09/AL are 
 * Copyright France Telecom, 2004.
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

import java.util.StringTokenizer;



import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.Logger;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletBuildUtility;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;

public class XDocletEJBPreferencePage extends PropertyPreferencePage implements SelectionListener {

	//private static ResourceBundle bundle = ResourceBundle
	//		.getBundle("org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui.preferences");

	private static final String[][] ejboptions = {
			{ XDocletPreferenceStore.EJB_JBOSS, "JBoss", Messages.desc_ejbdoclet_jboss, "CHECK",
					"2.4,3.0,3.0.1,3.0.2,3.0.3,3.2,4.0", "2.4" },
			{ XDocletPreferenceStore.EJB_JONAS, "JOnAS", Messages.desc_ejbdoclet_jonas, "CHECK", "2.3,2.4,2.5,2.6,3.0",
					"2.6" },
			{ XDocletPreferenceStore.EJB_WEBLOGIC, "WebLogic", Messages.desc_ejbdoclet_weblogic, "CHECK",
					"6.0,6.1,7.0,8.1", "6.1" },
			{ XDocletPreferenceStore.EJB_WEBSPHERE, "WebSphere", Messages.desc_ejbdoclet_websphere, "CHECK", "all", "all" }

	};

	DialogPanel panel;

	public XDocletEJBPreferencePage() {
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

		Label label = new Label(defPanel, SWT.WRAP);
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		label.setLayoutData(gridData);
		label.setText(Messages.label_set_ejbdoclet_preference);

		panel.preferences = new Control[ejboptions.length + 11];
		panel.fActive = new Button[ejboptions.length+11];

		for (int i = 0; i < ejboptions.length; i++) {
			String versions[] = parseVersions(ejboptions[i][4]);
			panel.preferences[i] = panel.createLabeledCombo(i, getStore().isPropertyActive(ejboptions[i][0]), ejboptions[i][1] + ":",
					ejboptions[i][2], getStore().getProperty(ejboptions[i][0] + "_VERSION"), versions, defPanel);
		}
		panel.preferences[ejboptions.length] = panel.createLabeledCheck(
				ejboptions.length, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATELOCAL),
				Messages.label_generate_local,
				Messages.label_generate_local_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATELOCAL), defPanel);
		panel.preferences[ejboptions.length + 1] = panel.createLabeledCheck(
				ejboptions.length+1, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEREMOTE),
				Messages.label_generate_remote,
				Messages.label_generate_remote_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEREMOTE), defPanel);
		panel.preferences[ejboptions.length + 2] = panel.createLabeledCheck(
				ejboptions.length+2, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEUTIL),
				Messages.label_generate_util,
				Messages.label_generate_util_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEUTIL), defPanel);
		panel.preferences[ejboptions.length + 3] = panel.createLabeledCheck(
				ejboptions.length+3, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEDATAOBJECT),
				Messages.label_generate_dataobject,
				Messages.label_generate_dataobject_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEDATAOBJECT), defPanel);
		panel.preferences[ejboptions.length + 4] = panel.createLabeledCheck(
				ejboptions.length+4, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEDAO),
				Messages.label_generate_dao,
				Messages.label_generate_dao_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEDAO), defPanel);
		panel.preferences[ejboptions.length + 5] = panel.createLabeledCheck(
				ejboptions.length+5, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEVALUEOBJECT),
				Messages.label_generate_valueobject,
				Messages.label_generate_valueobject_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEVALUEOBJECT), defPanel);
		panel.preferences[ejboptions.length + 6] = panel.createLabeledCheck(
				ejboptions.length+6, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYPK),
				Messages.label_generate_entitypk,
				Messages.label_generate_entitypk_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYPK), defPanel);
		panel.preferences[ejboptions.length + 7] = panel.createLabeledCheck(
				ejboptions.length+7, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYCMP),
				Messages.label_generate_entitycmp,
				Messages.label_generate_entitycmp_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYCMP), defPanel);
		panel.preferences[ejboptions.length + 8] = panel.createLabeledCheck(
				ejboptions.length+8, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYBMP),
				Messages.label_generate_entitybmp,
				Messages.label_generate_entitybmp_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYBMP), defPanel);
		panel.preferences[ejboptions.length + 9] = panel.createLabeledCheck(
				ejboptions.length+9, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATESESSION),
				Messages.label_generate_session,
				Messages.label_generate_session_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATESESSION), defPanel);
		panel.preferences[ejboptions.length + 10] = panel.createLabeledCheck(
				ejboptions.length+10, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEMDB),
				Messages.label_generate_mdb,
				Messages.label_generate_mdb_desc, 
				getStore().isPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEMDB), defPanel);

		return composite;
	}

	/**
	 * @param string
	 * @return
	 */
	private String[] parseVersions(String string) {
		StringTokenizer tokenizer = new StringTokenizer(string, ",");
		int i = 0, count = tokenizer.countTokens();
		String[] versions = new String[count];
		while (tokenizer.hasMoreTokens()) {
			versions[i++] = tokenizer.nextToken();
		}
		return versions;
	}

	public boolean performOk() {
		for (int i = 0; i < ejboptions.length; i++) {
			Combo combo = ((Combo) panel.preferences[i]);
			boolean itemActive = panel.fActive[i].getSelection();
			String itemValue = combo.getItem(combo.getSelectionIndex());
			getStore().setProperty(ejboptions[i][0] + "_VERSION", itemValue);
			getStore().setPropertyActive(ejboptions[i][0], itemActive);
		}
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATELOCAL,
				((Button)panel.preferences[ejboptions.length]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEREMOTE,
				((Button)panel.preferences[ejboptions.length+1]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEUTIL,
				((Button)panel.preferences[ejboptions.length+2]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEDATAOBJECT,
				((Button)panel.preferences[ejboptions.length+3]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEDAO,
				((Button)panel.preferences[ejboptions.length+4]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEVALUEOBJECT,
				((Button)panel.preferences[ejboptions.length+5]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYPK,
				((Button)panel.preferences[ejboptions.length+6]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYCMP,
				((Button)panel.preferences[ejboptions.length+7]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEENTITYBMP,
				((Button)panel.preferences[ejboptions.length+8]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATESESSION,
				((Button)panel.preferences[ejboptions.length+9]).getSelection());
		getStore().setPropertyActive(XDocletPreferenceStore.XDOCLETGENERATEMDB,
				((Button)panel.preferences[ejboptions.length+10]).getSelection());

		getStore().save();
		try {
			XDocletBuildUtility.runNecessaryBuilders(new NullProgressMonitor(), (IProject)getElement());
		} catch (CoreException e) {
			Logger.logException(e);
		}
		return super.performOk();
	}



}