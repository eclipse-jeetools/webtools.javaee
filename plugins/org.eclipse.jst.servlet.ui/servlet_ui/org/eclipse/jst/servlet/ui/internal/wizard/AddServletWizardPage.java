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
package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IType;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.internal.web.operations.AddServletDataModel;
import org.eclipse.jst.j2ee.internal.web.operations.AddServletFilterListenerCommonDataModel;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntimeUtilities;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.StringArrayTableWizardSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.web.internal.operation.IBaseWebNature;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * Servlet Wizard Setting Page
 */
public class AddServletWizardPage extends WTPWizardPage {
	final static String[] JSPEXTENSIONS = {"jsp"}; //$NON-NLS-1$

	private Text displayNameText;

	private Label classLabel;

	private Text classText;

	private Button classButton;

	private Button existingButton;

	private StringArrayTableWizardSection urlSection;
	
	private AnnotationsStandaloneGroup annotationsGroup = null;

	public AddServletWizardPage(AddServletDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_DESC);
		this.setTitle(IWebWizardConstants.ADD_SERVLET_WIZARD_PAGE_TITLE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{AddServletFilterListenerCommonDataModel.DISPLAY_NAME, AddServletFilterListenerCommonDataModel.USE_EXISTING_CLASS, AddServletFilterListenerCommonDataModel.CLASS_NAME,
				AddServletDataModel.INIT_PARAM, AddServletDataModel.URL_MAPPINGS};
	}

	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout());
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);

		createNameDescription(composite);
		new StringArrayTableWizardSection(composite, IWebWizardConstants.INIT_PARAM_LABEL, IWebWizardConstants.ADD_BUTTON_LABEL,
				IWebWizardConstants.REMOVE_BUTTON_LABEL, new String[]{IWebWizardConstants.NAME_LABEL, IWebWizardConstants.VALUE_LABEL, IWebWizardConstants.DESCRIPTION_LABEL}, null,// WebPlugin.getDefault().getImage("initializ_parameter"),
				model, AddServletDataModel.INIT_PARAM);
		urlSection = new StringArrayTableWizardSection(composite, IWebWizardConstants.URL_MAPPINGS_LABEL, IWebWizardConstants.ADD_BUTTON_LABEL, IWebWizardConstants.REMOVE_BUTTON_LABEL,
				new String[]{IWebWizardConstants.URL_PATTERN_LABEL}, null,// WebPlugin.getDefault().getImage("url_type"),
				model, AddServletDataModel.URL_MAPPINGS);
		createClassGroup(composite);
		displayNameText.setFocus();

		IStatus projectStatus = validateProjectName();
		if (!projectStatus.isOK()) {
			setErrorMessage(projectStatus.getMessage());
			composite.setEnabled(false);
		} else
			createAnnotationsGroup(composite);
		return composite;
	}

	protected IStatus validateProjectName() {
		// check for empty
		if (model.getStringProperty(EditModelOperationDataModel.PROJECT_NAME) == null || model.getStringProperty(EditModelOperationDataModel.PROJECT_NAME).trim().length() == 0) {
			return WTPCommonPlugin.createErrorStatus(IWebWizardConstants.NO_WEB_PROJECTS);
		}
		return WTPCommonPlugin.OK_STATUS;
	}

	protected void createNameDescription(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		// display name
		Label displayNameLabel = new Label(composite, SWT.LEFT);
		displayNameLabel.setText(IWebWizardConstants.NAME_LABEL);
		displayNameLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		displayNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		displayNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		displayNameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String text = displayNameText.getText();
				// Set default URL Pattern
				List input = new ArrayList();
				input.add(new String[]{"/" + text}); //$NON-NLS-1$
				urlSection.setInput(input);
				// // Set default class name
				// WTPOperationDataModel nestedModel =
				// model.getNestedModel("NewServletClassDataModel");
				// //$NON-NLS-1$
				// if (nestedModel != null) {
				// if (text.trim().length() == 0)
				// return;
				// String className =
				// nestedModel.getStringProperty(NewServletClassDataModel.CLASS_NAME);
				// if (className != null && className.trim().length() > 0)
				// return;
				// char[] textChar = text.toCharArray();
				// textChar[0] = Character.toUpperCase(textChar[0]);
				// className = String.valueOf(textChar);
				// nestedModel.setProperty(NewServletClassDataModel.CLASS_NAME,
				// className);
				// }
			}

		});
		synchHelper.synchText(displayNameText, AddServletFilterListenerCommonDataModel.DISPLAY_NAME, null);

		// description
		Label descLabel = new Label(composite, SWT.LEFT);
		descLabel.setText(IWebWizardConstants.DESCRIPTION_LABEL);
		descLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		Text descText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		descText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(descText, AddServletFilterListenerCommonDataModel.DESCRIPTION, null);
	}

	protected void createClassGroup(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(3, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		existingButton = new Button(composite, SWT.CHECK);
		existingButton.setText(IWebWizardConstants.USE_EXISTING_SERVLET_CLASS);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 3;
		existingButton.setLayoutData(data);
		synchHelper.synchCheckbox(existingButton, AddServletFilterListenerCommonDataModel.USE_EXISTING_CLASS, null);
		existingButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleExistingButtonSelected();
			}
		});

		classLabel = new Label(composite, SWT.LEFT);
		classLabel.setText(IWebWizardConstants.CLASS_NAME_LABEL);
		classLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		classLabel.setEnabled(false);

		classText = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		classText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		classText.setEnabled(false);
		synchHelper.synchText(classText, AddServletFilterListenerCommonDataModel.CLASS_NAME, null);

		classButton = new Button(composite, SWT.PUSH);
		classButton.setText(IWebWizardConstants.BROWSE_BUTTON_LABEL);
		classButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		classButton.setEnabled(false);
		classButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleClassButtonSelected();
			}
		});
	}

	private void handleExistingButtonSelected() {
		boolean enable = existingButton.getSelection();
		if (!enable) {
			classText.setText(""); //$NON-NLS-1$
		}
		classLabel.setEnabled(enable);
		classButton.setEnabled(enable);
	}

	private void handleClassButtonSelected() {
		getControl().setCursor(new Cursor(getShell().getDisplay(), SWT.CURSOR_WAIT));
		IProject project = model.getTargetProject();
		
		MultiSelectFilteredFileSelectionDialog ms = new MultiSelectFilteredFileSelectionDialog(getShell(), IWebWizardConstants.NEW_SERVLET_WIZARD_WINDOW_TITLE,
				IWebWizardConstants.CHOOSE_SERVLET_CLASS, JSPEXTENSIONS, false, project);
		IBaseWebNature nature = J2EEWebNatureRuntimeUtilities.getRuntime(project);
		IContainer root = nature.getRootPublishableFolder();
		ms.setInput(root);
		ms.open();
		if (ms.getReturnCode() == Window.OK) {
			String qualifiedClassName = ""; //$NON-NLS-1$
			if (ms.getSelectedItem() == MultiSelectFilteredFileSelectionDialog.JSP) {
				Object obj = ms.getFirstResult();
				if (obj != null) {
					if (obj instanceof IFile) {
						IFile file = (IFile) obj;
						IPath pFull = file.getFullPath();
						IPath pBase = root.getFullPath();
						IPath path = pFull.removeFirstSegments(pBase.segmentCount());
						qualifiedClassName = path.makeAbsolute().toString();
						model.setProperty(AddServletDataModel.IS_SERVLET_TYPE, new Boolean(false));
					}
				}
			} else {
				IType type = (IType) ms.getFirstResult();
				if (type != null) {
					qualifiedClassName = type.getFullyQualifiedName();
					model.setProperty(AddServletDataModel.IS_SERVLET_TYPE, new Boolean(true));
				}
			}
			classText.setText(qualifiedClassName);
		}
		getControl().setCursor(null);
	}

	public boolean canFlipToNextPage() {
		// when the existingButton is checked, following pages are not needed
		if (existingButton.getSelection())
			return false;
		return super.canFlipToNextPage();
	}

	public boolean canFinish() {
		// when the existingButton is checked, only need to check if class is
		// set
		if (existingButton.getSelection())
			return classText.getText().trim().length() > 0 && super.canFlipToNextPage();
		return false;
	}

	/**
	 * Create annotations group and set default enablement
	 */
	private void createAnnotationsGroup(Composite parent) {
		annotationsGroup = new AnnotationsStandaloneGroup(parent, model, true, true);
		IProject project = null;
		project = model.getProjectHandle(EditModelOperationDataModel.PROJECT_NAME);
		annotationsGroup.setEnablement(project);
		annotationsGroup.setUseAnnotations(true);
	}

	public String getDisplayName() {
		return displayNameText.getText();
	}
}