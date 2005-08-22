/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on May 1, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.List;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;


/**
 * @author Sachin
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class WizardClassesImportMainPage extends WizardPage {
	Composite composite;

	protected Button importFromDir;
	protected Button importFromZip;

	private List dragAndDropFileNames = null;

	/**
	 * @param pageName
	 */
	public WizardClassesImportMainPage(String pageName) {
		super(pageName);
		setTitle(J2EEUIMessages.getResourceString("DataTransfer.fileSystemTitle")); //$NON-NLS-1$
		setDescription(J2EEUIMessages.getResourceString("FileImport.importFileSystem")); //$NON-NLS-1$
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor("import_class_file_wiz_ban")); //$NON-NLS-1$
	}

	public WizardClassesImportMainPage(String pageName, List fileNames) {
		super(pageName);
		setTitle(J2EEUIMessages.getResourceString("DataTransfer.fileSystemTitle")); //$NON-NLS-1$
		setDescription(J2EEUIMessages.getResourceString("FileImport.importFileSystem")); //$NON-NLS-1$
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor("import_class_file_wiz_ban")); //$NON-NLS-1$
		dragAndDropFileNames = fileNames;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createControl(Composite parent) {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, IJ2EEUIContextIds.IMPORT_CLASS_WIZARD_P1);
		initializeDialogUnits(parent);
		Composite aComposite = new Composite(parent, SWT.NULL);
		aComposite.setLayout(new GridLayout());
		aComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		aComposite.setSize(aComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		aComposite.setFont(parent.getFont());
		createImportTypeGroup(aComposite);
		setControl(aComposite);
	}

	protected void createImportTypeGroup(Composite parent) {
		Composite importTypeGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		importTypeGroup.setLayout(layout);
		importTypeGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		//importTypeGroup.setText(WorkbenchMessages.getString("WizardExportPage.options"));
		// //$NON-NLS-1$

		SelectionListener selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IWizard wiz = getWizard();
				if (((ClassesImportWizard) wiz).page1 != null) {
					((ClassesImportWizard) wiz).page1.blankPage();
				}
			}
		};
		importFromDir = new Button(importTypeGroup, SWT.RADIO);
		importFromDir.setText(J2EEUIMessages.getResourceString("ClassesImport.fromDir")); //$NON-NLS-1$
		importFromDir.addSelectionListener(selectionListener);

		importFromZip = new Button(importTypeGroup, SWT.RADIO);
		importFromZip.setText(J2EEUIMessages.getResourceString("ClassesImport.fromZip")); //$NON-NLS-1$
		importFromZip.addSelectionListener(selectionListener);

		IWizard wiz = getWizard();
		String fileName = null;
		if (((ClassesImportWizard) wiz).fileNames != null)
			fileName = ((ClassesImportWizard) wiz).fileNames.get(0).toString();
		if (fileName != null && (fileName.endsWith(".zip") || fileName.endsWith(".jar"))) { //$NON-NLS-1$ //$NON-NLS-2$
			importFromDir.setSelection(false);
			importFromZip.setSelection(true);
		} else {
			importFromDir.setSelection(true);
			importFromZip.setSelection(false);
		}
	}

	protected boolean isSetImportFromDir() {
		if (importFromDir != null)
			return importFromDir.getSelection();

		String fileName = dragAndDropFileNames.get(0).toString();
		if (fileName != null && (fileName.endsWith(".zip") || fileName.endsWith(".jar"))) { //$NON-NLS-1$ //$NON-NLS-2$
			return false;
		}
		return true;
	}
}


