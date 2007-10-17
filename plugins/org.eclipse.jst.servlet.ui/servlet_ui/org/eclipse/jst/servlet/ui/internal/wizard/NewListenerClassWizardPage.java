/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.IProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.war.ui.util.WebListenerGroupItemProvider;
import org.eclipse.jst.j2ee.internal.web.operations.INewListenerClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassWizardPage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.servlet.ui.internal.navigator.CompressedJavaProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class NewListenerClassWizardPage extends NewJavaClassWizardPage {

//	private AnnotationsStandaloneGroup annotationsGroup = null;
	private Button existingClassButton;
	private Label existingClassLabel;
	private Text existingClassText;
	private Button existingButton;
	private final static String[] JSPEXTENSIONS = { "jsp" }; //$NON-NLS-1$
	
	
	public NewListenerClassWizardPage(IDataModel model, String pageName, String pageDesc, String pageTitle, String moduleType) {
		super(model, pageName, pageDesc, pageTitle, moduleType);
	}
	
	/**
	 * Create annotations group and set default enablement
	 */
//	private void createAnnotationsGroup(Composite parent) {
//		if (isWebDocletProject()) {
//			annotationsGroup = new AnnotationsStandaloneGroup(parent, model, J2EEProjectUtilities.EJB.equals(projectType),
//					J2EEProjectUtilities.DYNAMIC_WEB.equals(projectType));
//			if (!model.isPropertySet(IArtifactEditOperationDataModelProperties.PROJECT_NAME))
//				return;
//			IProject project = ProjectUtilities.getProject(model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME));
//			annotationsGroup.setEnablement(project);
//			// annotationsGroup.setUseAnnotations(true);
//		} else {
//			// not a Web Doclet project - make sure that the USE_ANNOTATIONS property is off
//			model.setProperty(IAnnotationsDataModel.USE_ANNOTATIONS, false);
//		}
//	}
	
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = super.createTopLevelComposite(parent);
		addSeperator(composite,3);
		createUseExistingGroup(composite);
//		createAnnotationsGroup(composite);
		
		Dialog.applyDialogFont(composite);
		
		return composite;
	}

	private void createUseExistingGroup(Composite composite) {
		existingButton = new Button(composite, SWT.CHECK);
		existingButton.setText(IWebWizardConstants.USE_EXISTING_SERVLET_CLASS);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 3;
		existingButton.setLayoutData(data);
		synchHelper.synchCheckbox(existingButton, INewListenerClassDataModelProperties.USE_EXISTING_CLASS, null);
		existingButton.setEnabled(false);
		existingButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleExistingButtonSelected();
			}
		});
		
		existingClassLabel = new Label(composite, SWT.LEFT);
		existingClassLabel.setText(IWebWizardConstants.CLASS_NAME_LABEL);
		existingClassLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		existingClassLabel.setEnabled(false);

		existingClassText = new Text(composite, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		existingClassText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		existingClassText.setEnabled(false);
		synchHelper.synchText(existingClassText, INewJavaClassDataModelProperties.CLASS_NAME, null);

		existingClassButton = new Button(composite, SWT.PUSH);
		existingClassButton.setText(IWebWizardConstants.BROWSE_BUTTON_LABEL);
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
		if (!enable) {
			existingClassText.setText(""); //$NON-NLS-1$
		}
		existingClassLabel.setEnabled(enable);
		existingClassButton.setEnabled(enable);
		packageText.setEnabled(!enable);
		packageButton.setEnabled(!enable);
		packageLabel.setEnabled(!enable);
		classText.setEnabled(!enable);
		classText.setText(""); //$NON-NLS-1$
		classLabel.setEnabled(!enable);
		superText.setEnabled(!enable);
		superButton.setEnabled(!enable);
		superLabel.setEnabled(!enable);
	}

	private void handleClassButtonSelected() {
//		getControl().setCursor(new Cursor(getShell().getDisplay(), SWT.CURSOR_WAIT));
//		IProject project = (IProject) model.getProperty(INewJavaClassDataModelProperties.PROJECT);
//		IVirtualComponent component = ComponentCore.createComponent(project);
//		ListenerMultiSelectFilteredFileSelectionDialog ms = new ListenerMultiSelectFilteredFileSelectionDialog(
//				getShell(),
//				IWebWizardConstants.NEW_SERVLET_WIZARD_WINDOW_TITLE,
//				IWebWizardConstants.CHOOSE_SERVLET_CLASS, 
//				JSPEXTENSIONS, 
//				false, 
//				project);
//		IContainer root = component.getRootFolder().getUnderlyingFolder();
//		ms.setInput(root);
//		ms.open();
//		if (ms.getReturnCode() == Window.OK) {
//			String qualifiedClassName = ""; //$NON-NLS-1$
////			if (ms.getSelectedItem() == MultiSelectFilteredFileSelectionDialog.JSP) {
////				Object obj = ms.getFirstResult();
////				if (obj != null) {
////					if (obj instanceof IFile) {
////						IFile file = (IFile) obj;
////						IPath pFull = file.getFullPath();
////						IPath pBase = root.getFullPath();
////						IPath path = pFull.removeFirstSegments(pBase.segmentCount());
////						qualifiedClassName = path.makeAbsolute().toString();
////						model.setProperty(INewServletClassDataModelProperties.IS_SERVLET_TYPE, new Boolean(false));
////					}
////				}
////			} 
////			else {
//				IType type = (IType) ms.getFirstResult();
//				if (type != null) {
//					qualifiedClassName = type.getFullyQualifiedName();
////					model.setProperty(INewServletClassDataModelProperties.IS_SERVLET_TYPE, new Boolean(true));
//				}
////			}
//			existingClassText.setText(qualifiedClassName);
//		}
//		getControl().setCursor(null);
	}
	
	protected IProject getExtendedSelectedProject(Object selection) {
		if (selection instanceof WebListenerGroupItemProvider) {
			WebApp webApp = (WebApp)((WebListenerGroupItemProvider)selection).getParent();
			return ProjectUtilities.getProject(webApp);
		}
		else if (selection instanceof CompressedJavaProject) {
			return ((CompressedJavaProject)selection).getProject().getProject();
		}
		return super.getExtendedSelectedProject(selection);
	}
	
//	private boolean isWebDocletProject() {
//		String projectName = model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME);
//		if(projectName != null && !"".equals(projectName.trim())){
//			IProject project = ProjectUtilities.getProject(projectName);
//			try {
//				IFacetedProject facetedProject = ProjectFacetsManager.create(project);
//				return facetedProject.hasProjectFacet(WebFacetUtils.WEB_XDOCLET_FACET);
//			} catch (CoreException e) {
//				Logger.getLogger().log(e);
//			}
//		}
//		return false;
//	}
}
