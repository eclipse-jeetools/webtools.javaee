/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel.USE_ANNOTATIONS;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.CLASS_NAME;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.BROWSE_BUTTON_LABEL;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.CLASS_NAME_LABEL;
import static org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties.PROJECT_NAME;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.GENERATE_DD;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.AnnotationsStandaloneGroup;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.jst.servlet.ui.internal.navigator.CompressedJavaProject;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public abstract class NewWebClassWizardPage extends NewJavaClassWizardPage {

	protected AnnotationsStandaloneGroup annotationsGroup;
	
	protected Button existingClassButton;
	protected Label existingClassLabel;
	protected Text existingClassText;
	protected Button existingButton;
	
	public NewWebClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		
		projectNameLabel.setText(WEBUIMessages.WEB_PROJECT_LBL);
		
		addSeperator(composite, 3);
		createUseExistingGroup(composite);
		createAnnotationsGroup(composite);
		
		Dialog.applyDialogFont(composite);
		
		return composite;
	}
	
	@Override
	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof CompressedJavaProject) {
			return ((CompressedJavaProject) selection).getProject().getProject();
		}
		
		return super.getExtendedSelectedProject(selection);
	}
	
	protected abstract String getUseExistingCheckboxText();
	
	protected abstract String getUseExistingProperty();
	
	protected abstract void handleClassButtonSelected();
	
	/**
	 * Create annotations group and set default enablement
	 */
	private void createAnnotationsGroup(Composite parent) {
		if (isWebDocletProject()) {
			annotationsGroup = new AnnotationsStandaloneGroup(parent, model, J2EEProjectUtilities.EJB.equals(projectType),
					J2EEProjectUtilities.DYNAMIC_WEB.equals(projectType));
			if (!model.isPropertySet(PROJECT_NAME))
				return;
			IProject project = ProjectUtilities.getProject(model.getStringProperty(PROJECT_NAME));
			annotationsGroup.setEnablement(project);
			// annotationsGroup.setUseAnnotations(true);
		} else {
			// not a Web Doclet project - make sure that the USE_ANNOTATIONS property is off
			model.setProperty(USE_ANNOTATIONS, false);
		}
	}

	private void createUseExistingGroup(Composite composite) {
		existingButton = new Button(composite, SWT.CHECK);
		existingButton.setText(getUseExistingCheckboxText());
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 3;
		existingButton.setLayoutData(data);
		synchHelper.synchCheckbox(existingButton, getUseExistingProperty(), null);
		existingButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleExistingButtonSelected();
			}
		});
		
		existingClassLabel = new Label(composite, SWT.LEFT);
		existingClassLabel.setText(CLASS_NAME_LABEL);
		existingClassLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		existingClassLabel.setEnabled(false);

		existingClassText = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		existingClassText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		existingClassText.setEnabled(false);
		synchHelper.synchText(existingClassText, CLASS_NAME, null);

		existingClassButton = new Button(composite, SWT.PUSH);
		existingClassButton.setText(BROWSE_BUTTON_LABEL);
		existingClassButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		existingClassButton.setEnabled(false);
		existingClassButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleClassButtonSelected();
			}
		});
	}
	
	private void handleExistingButtonSelected() {
		boolean enable = existingButton.getSelection();
		existingClassLabel.setEnabled(enable);
		existingClassButton.setEnabled(enable);
		packageText.setEnabled(!enable);
		packageButton.setEnabled(!enable);
		packageLabel.setEnabled(!enable);
		classText.setEnabled(!enable);
		classLabel.setEnabled(!enable);
		superText.setEnabled(!enable);
		superButton.setEnabled(!enable);
		superLabel.setEnabled(!enable);
	}

	private boolean isWebDocletProject() {
		String projectName = model.getStringProperty(PROJECT_NAME);
		if(projectName != null && !"".equals(projectName.trim())){
			IProject project = ProjectUtilities.getProject(projectName);
			try {
				IFacetedProject facetedProject = ProjectFacetsManager.create(project);
				return facetedProject.hasProjectFacet(WebFacetUtils.WEB_XDOCLET_FACET);
			} catch (CoreException e) {
				ServletUIPlugin.log(e);
			}
		}
		return false;
	}

	protected void validateProjectRequirements(IProject project)
	{
		IVirtualComponent component = ComponentCore.createComponent(project);
		if(component.getRootFolder() != null
				&& component.getRootFolder().getUnderlyingFolder() != null){
			IFile ddXmlFile = component.getRootFolder().getUnderlyingFolder().getFile(new Path(J2EEConstants.WEBAPP_DD_URI));
			if (!ddXmlFile.exists())
			{
				// add a flag into the model to create the DD at the beginning of the operation
				model.setBooleanProperty(GENERATE_DD, Boolean.TRUE);
			}
			else
			{
				// don't create a DD, since one already exists.
				model.setBooleanProperty(GENERATE_DD, Boolean.FALSE);
			}
		}
	}
}
