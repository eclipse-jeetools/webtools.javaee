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
 * Created on May 13, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.operations.J2EEUtilityJarListImportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.nature.EMFNature;

/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEUtilityJarImportTypePage extends WTPWizardPage {

	protected static final String defBrowseButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.BROWSE_LABEL); //$NON-NLS-1$

	private static final int SIZING_TEXT_FIELD_WIDTH = 305;

	protected IStructuredSelection currentResourceSelection;

	private Combo resourceNameCombo;

	private Button copyJarIntoEAR;

	private Button linkJarIntoEAR;

	private Button createLinkedProjects;

	private Button createProjects;

	protected Button browseButton;

	private Button binaryImportCheckbox;

	private Button overrideProjectRootCheckbox;


	protected Text projectRootLocationText;

	private Label moduleProjectLocationLabel;

	protected boolean synching;

	public static final String TITLE = J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_0"); //$NON-NLS-1$
	public static final String DESCRIPTION = J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_1"); //$NON-NLS-1$

	private Group projectRootComposite;


	/**
	 * @param model
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	public J2EEUtilityJarImportTypePage(WTPOperationDataModel model, String pageName, String title, ImageDescriptor titleImage) {
		super(model, pageName, title, titleImage);
		setTitle(""); //$NON-NLS-1$
		setDescription(""); //$NON-NLS-1$
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));
	}

	public J2EEUtilityJarImportTypePage(WTPOperationDataModel model, String pageName, IStructuredSelection selection) {
		super(model, pageName);
		this.currentResourceSelection = selection;
		setTitle(TITLE);
		setDescription(DESCRIPTION);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));
	}

	public J2EEUtilityJarImportTypePage(WTPOperationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(TITLE);
		setDescription(DESCRIPTION);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_IMPORT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{J2EEUtilityJarListImportDataModel.EAR_PROJECT, J2EEUtilityJarListImportDataModel.PROJECT_ROOT};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createEARProjectGroup(composite);
		createUtilityJarImportTypes(composite);
		createProjectCreationOptions(composite);

		setupBasedOnInitialSelections();
		return composite;
	}

	/**
	 * @param composite
	 */
	protected void createUtilityJarImportTypes(Composite parent) {
		Group typesGroup = new Group(parent, SWT.NULL);
		typesGroup.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_2")); //$NON-NLS-1$

		GridLayout layout = new GridLayout(1, false);
		typesGroup.setLayout(layout);
		typesGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createProjects = new Button(typesGroup, SWT.RADIO);
		createProjects.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_3")); //$NON-NLS-1$
		synchHelper.synchRadio(createProjects, J2EEUtilityJarListImportDataModel.CREATE_PROJECT, null);

		createLinkedProjects = new Button(typesGroup, SWT.RADIO);
		createLinkedProjects.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_4")); //$NON-NLS-1$
		synchHelper.synchRadio(createLinkedProjects, J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT, null);

		copyJarIntoEAR = new Button(typesGroup, SWT.RADIO);
		copyJarIntoEAR.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_5")); //$NON-NLS-1$
		synchHelper.synchRadio(copyJarIntoEAR, J2EEUtilityJarListImportDataModel.COPY, null);

		linkJarIntoEAR = new Button(typesGroup, SWT.RADIO);
		linkJarIntoEAR.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_6")); //$NON-NLS-1$
		/* linkJarIntoEAR.addSelectionListener(getTypeSelectionListener()); */
		synchHelper.synchRadio(linkJarIntoEAR, J2EEUtilityJarListImportDataModel.LINK_IMPORT, null);

	}

	protected void createProjectCreationOptions(Composite parent) {

		Group projectOptionsGroup = new Group(parent, SWT.NULL);
		projectOptionsGroup.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_7")); //$NON-NLS-1$

		GridLayout layout = new GridLayout(1, false);
		projectOptionsGroup.setLayout(layout);
		projectOptionsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createBinaryImportCheckbox(projectOptionsGroup);

		overrideProjectRootCheckbox = new Button(projectOptionsGroup, SWT.CHECK);
		overrideProjectRootCheckbox.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_11")); //$NON-NLS-1$
		createProjectRootComposite(projectOptionsGroup);

		synchHelper.synchCheckbox(overrideProjectRootCheckbox, J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT, new Control[]{/*
																																	   * moduleProjectLocationLabel,
																																	   * projectRootLocationText,
																																	   * browseButton
																																	   */});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#propertyChanged(org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModelEvent)
	 */
	public void propertyChanged(WTPOperationDataModelEvent event) {
		//        if (synching) return;
		//        synching = true;
		//        if (J2EEUtilityJarListImportDataModel.CREATE_PROJECT.equals(event.getPropertyName())
		//                || J2EEUtilityJarListImportDataModel.LINK_IMPORT.equals(event.getPropertyName())) {
		//
		//            boolean shouldCreateProject =
		// getModel().getBooleanProperty(J2EEUtilityJarListImportDataModel.CREATE_PROJECT);
		//            boolean linkImportedContent =
		// getModel().getBooleanProperty(J2EEUtilityJarListImportDataModel.LINK_IMPORT);
		//
		//            createProjects.setSelection(shouldCreateProject && !linkImportedContent);
		//            createLinkedProjects.setSelection(shouldCreateProject && linkImportedContent);
		//            linkJarIntoEAR.setSelection(!shouldCreateProject && linkImportedContent);
		//            copyJarIntoEAR.setSelection(!shouldCreateProject && !linkImportedContent);
		//             
		//            enableProjectOptions(shouldCreateProject);
		//
		//        } else
		// if(J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT.equals(event.getPropertyName()))
		// {
		//            boolean overrideProjectRoot =
		// getModel().getBooleanProperty(J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT);
		//            projectRootLocationText.setEnabled(overrideProjectRoot);
		//        }
		//        synching = false;
		super.propertyChanged(event);
	}

	/**
	 * 
	 * @param parent
	 *            a <code>Composite</code> that is to be used as the parent of this group's
	 *            collection of visual components
	 * @see org.eclipse.swt.widgets.Composite
	 */
	protected void createEARProjectGroup(Composite parent) {

		Group earGroup = new Group(parent, SWT.NULL);
		earGroup.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_8")); //$NON-NLS-1$

		GridLayout layout = new GridLayout(2, false);
		earGroup.setLayout(layout);
		earGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//Project label
		Label projectLabel = new Label(earGroup, SWT.NONE);
		projectLabel.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_9")); //$NON-NLS-1$
		//Project combo
		resourceNameCombo = new Combo(earGroup, SWT.SINGLE | SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		resourceNameCombo.setLayoutData(data);
		synchHelper.synchCombo(resourceNameCombo, J2EEUtilityJarListImportDataModel.EAR_PROJECT, null);

	}

	protected void createProjectRootComposite(Composite parent) {
		projectRootComposite = new Group(parent, SWT.NULL);
		projectRootComposite.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.PROJECT_LOCATIONS_GROUP));
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		projectRootComposite.setLayout(layout);
		projectRootComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label description = new Label(projectRootComposite, SWT.NULL);
		description.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_PROJECT_GROUP_DESCRIPTION));
		GridData gd2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd2.horizontalSpan = 3;
		description.setLayoutData(gd2);

		moduleProjectLocationLabel = new Label(projectRootComposite, SWT.NULL);
		moduleProjectLocationLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.USE_DEFAULT_ROOT_RADIO));
		moduleProjectLocationLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		projectRootLocationText = new Text(projectRootComposite, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		projectRootLocationText.setLayoutData(gd);
		projectRootLocationText.setText(ResourcesPlugin.getWorkspace().getRoot().getFullPath().toOSString());
		projectRootLocationText.setEnabled(false); /* disabled by default */

		browseButton = new Button(projectRootComposite, SWT.PUSH);
		browseButton.setText(defBrowseButtonLabel);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_END);
		browseButton.setLayoutData(gd);
		browseButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				handleRootProjectBrowseButtonPressed();
			}
		});

		synchHelper.synchText(projectRootLocationText, J2EEUtilityJarListImportDataModel.PROJECT_ROOT, new Control[]{moduleProjectLocationLabel, projectRootLocationText, browseButton});
	}

	protected void createBinaryImportCheckbox(Composite parent) {
		binaryImportCheckbox = new Button(parent, SWT.CHECK);
		binaryImportCheckbox.setText(J2EEUIMessages.getResourceString("J2EEUtilityJarImportTypePage_UI_10")); //$NON-NLS-1$
		synchHelper.synchCheckbox(binaryImportCheckbox, J2EEUtilityJarListImportDataModel.BINARY_IMPORT, null);
	}

	/**
	 * Open an appropriate directory browser
	 */
	protected void handleRootProjectBrowseButtonPressed() {
		DirectoryDialog dialog = new DirectoryDialog(browseButton.getShell());
		dialog.setMessage(J2EEUIMessages.getResourceString(J2EEUIMessages.SELECT_DIRECTORY_DLG));

		String dirName = getBrowseStartLocation();

		if (!isNullOrEmpty(dirName)) {
			File path = new File(dirName);
			if (path.exists())
				dialog.setFilterPath(dirName);
		}

		String selectedDirectory = dialog.open();
		if (selectedDirectory != null)
			projectRootLocationText.setText(selectedDirectory);

	}

	protected String getBrowseStartLocation() {
		String text = projectRootLocationText.getText();
		return text;
	}


	protected boolean isNullOrEmpty(String aString) {
		return aString == null || aString.length() == 0;
	}

	/**
	 * Populates the resource name field based upon the currently-selected resources.
	 */
	protected void setupBasedOnInitialSelections() {

		if (null == currentResourceSelection || currentResourceSelection.isEmpty() || setupBasedOnRefObjectSelection())
			return; // no
		// setup
		// needed

		java.util.List selections = new ArrayList();
		Iterator aenum = currentResourceSelection.iterator();
		while (aenum.hasNext()) {
			IResource currentResource = (IResource) aenum.next();
			// do not add inaccessible elements
			if (currentResource.isAccessible())
				selections.add(currentResource);
		}
		if (selections.isEmpty())
			return; // setup not needed anymore

		int selectedResourceCount = selections.size();
		if (selectedResourceCount == 1) {
			IResource resource = (IResource) selections.get(0);
			if ((resource instanceof IProject) && checkForNature((IProject) resource)) {
				resourceNameCombo.setText(resource.getName().toString());
			}
		}
	}

	/**
	 * Populates the resource name field based upon the currently-selected resources.
	 */
	protected boolean setupBasedOnRefObjectSelection() {

		if (currentResourceSelection.size() != 1)
			return false;

		Object o = currentResourceSelection.getFirstElement();
		if (!isMetaTypeSupported(o))
			return false;

		EObject ref = (EObject) o;
		IResource resource = ProjectUtilities.getProject(ref);
		if (resource != null)
			resourceNameCombo.setText(resource.getName().toString());
		return true;
	}

	protected boolean checkForNature(IProject project) {
		return EMFNature.hasRuntime(project, getNatureID());
	}

	/**
	 * @return
	 */
	protected String getNatureID() {
		return IEARNatureConstants.NATURE_ID;
	}

	protected boolean isMetaTypeSupported(Object o) {
		return o instanceof EARFile || o instanceof Application;
	}

	protected void enableProjectOptions(boolean enabled) {
		//getModel().setBooleanProperty(J2EEUtilityJarListImportDataModel.BINARY_IMPORT, enabled);
		binaryImportCheckbox.setEnabled(enabled);
		overrideProjectRootCheckbox.setEnabled(enabled);

		if (overrideProjectRootCheckbox.getSelection() && enabled)
			projectRootLocationText.setEnabled(true);
		else
			projectRootLocationText.setEnabled(false);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#isPageComplete()
	 */
	public boolean isPageComplete() {
		return getModel().validateProperty(J2EEUtilityJarListImportDataModel.EAR_PROJECT).isOK();
	}

}