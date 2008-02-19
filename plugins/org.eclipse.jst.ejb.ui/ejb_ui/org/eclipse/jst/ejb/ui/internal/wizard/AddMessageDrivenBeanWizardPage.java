/*******************************************************************************
 * Copyright (c) 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.dialogs.TypeSearchEngine;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassOptionsWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

public class AddMessageDrivenBeanWizardPage extends
		NewJavaClassOptionsWizardPage implements
		INewMessageDrivenBeanClassDataModelProperties {

	private Text ejbNameText;
	private Combo transactionTypeCombo;
	private TableViewer businessnterfacesList;
	private Button addButton;
	private Button removeButton;

	public AddMessageDrivenBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEAN_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEANS_WIZARD_PAGE_TITLE);
	}

	public DataModelSynchHelper initializeSynchHelper(IDataModel dm) {
		return new ComboIndexSynchHelper(dm);
	}

	protected Composite createTopLevelComposite(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(3, false));

		Label ejbNameLabel = new Label(composite, SWT.LEFT);
		ejbNameLabel.setText(IEjbWizardConstants.EJB_NAME);

		GridData data = new GridData ();
		data.horizontalAlignment = GridData.FILL;
		data.horizontalSpan = 2;
		data.grabExcessHorizontalSpace = true;

		ejbNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		ejbNameText.setLayoutData(data);
		synchHelper.synchText(ejbNameText, EJB_NAME, null);

		Label transactionTypeLabel = new Label(composite, SWT.LEFT);
		transactionTypeLabel.setText(EJBUIMessages.TRANSACTION_TYPE);
		transactionTypeCombo = new Combo(composite, SWT.None | SWT.READ_ONLY);
		transactionTypeCombo.setLayoutData(data);
		transactionTypeCombo.setItems(new String[] {
				IEjbWizardConstants.TRANSACTION_TYPE_CONTAINER, 
				IEjbWizardConstants.TRANSACTION_TYPE_BEAN
		});
		transactionTypeCombo.select(0);
		((ComboIndexSynchHelper) synchHelper).synchComboIndex(transactionTypeCombo, TRANSACTION_TYPE, null);

		addSeperator(composite, 3);

		createInterfaceControls(composite);

		createStubsComposite(composite);

		return composite;
	}

	protected void addSeperator(Composite composite, int horSpan) {
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.widthHint = 300;
		// Separator label
		Label seperator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = horSpan;
		seperator.setLayoutData(data);
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return null;
	}

	@Override
	protected void handleInterfaceAddButtonSelected() {
		IProject project = (IProject) model.getProperty(INewJavaClassDataModelProperties.PROJECT);
		IRunnableContext context = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IJavaProject javaProject = JemProjectUtilities.getJavaProject(project);
		// this eliminates the non-exported classpath entries
		final IJavaSearchScope scope = TypeSearchEngine.createJavaSearchScopeForAProject(javaProject, true, true);
		FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(getShell(),false, context, scope,IJavaSearchConstants.INTERFACE);
		dialog.setTitle(J2EEUIMessages.INTERFACE_SELECTION_DIALOG_TITLE);
		if (dialog.open() == Window.OK) {
			IType type = (IType) dialog.getFirstResult();
			String superclassFullPath = ""; //$NON-NLS-1$
			if (type != null)
				superclassFullPath = type.getFullyQualifiedName();
			if (interfaceViewer.testFindItem(superclassFullPath) == null){
				interfaceViewer.add(superclassFullPath);
			}
		}
		model.setProperty(INewJavaClassDataModelProperties.INTERFACES,new ArrayList(Arrays.asList(interfaceViewer.getList().getItems())));
	}

	@Override
	protected void createStubsComposite(Composite parent) {
		Label stubLabel = new Label(parent, SWT.NONE);
		stubLabel.setText(J2EEUIMessages.JAVA_CLASS_METHOD_STUBS_LABEL);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		stubLabel.setLayoutData(data);

		Composite buttonCompo = new Composite(parent, SWT.NULL);
		buttonCompo.setLayout(new GridLayout());
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		buttonCompo.setLayoutData(data);

		inheritButton = new Button(buttonCompo, SWT.CHECK);
		inheritButton.setText(J2EEUIMessages.JAVA_CLASS_INHERIT_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(inheritButton, INewJavaClassDataModelProperties.ABSTRACT_METHODS, null);

		constructorButton = new Button(buttonCompo, SWT.CHECK);
		constructorButton.setText(J2EEUIMessages.JAVA_CLASS_CONSTRUCTOR_CHECKBOX_LABEL);
		synchHelper.synchCheckbox(constructorButton, INewJavaClassDataModelProperties.CONSTRUCTOR, null);
	}

}

