/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;

public class AddSessionBeanWizardPage extends DataModelWizardPage implements
		INewSessionBeanClassDataModelProperties {

	private static final String EMPTY = ""; //$NON-NLS-1$
	
	private Text ejbNameText;
	private Text mappedNameText;
	private Combo transactionTypeCombo;
	private TableViewer businessnterfacesList;
	private Button addButton;
	private Button removeButton;
	private ScrolledForm form;
	
	public AddSessionBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_DESC);
		setTitle(IEjbWizardConstants.ADD_SESSION_BEANS_WIZARD_PAGE_TITLE);
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

		Label ejbMappedNameLabel = new Label(composite, SWT.LEFT);
		ejbMappedNameLabel.setText(EJBUIMessages.MAPPED_NAME);

		mappedNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		mappedNameText.setLayoutData(data);
		synchHelper.synchText(mappedNameText,MAPPED_NAME, null);

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

		createBussinesInterface(composite);

		addSeperator(composite, 3);

		Composite bottomComposite = new Composite(composite,SWT.None);
		bottomComposite.setLayout(new GridLayout());
		GridData bottomDataLayout = new GridData();
		bottomDataLayout.horizontalSpan = 3;
		bottomComposite.setLayoutData(bottomDataLayout);
		createExpandableComposite(bottomComposite);

		return composite;
	}

	private void createExpandableComposite(Composite composite) {
		
		FormToolkit toolkit = new FormToolkit(composite.getDisplay());
		toolkit.setBackground(composite.getBackground());
		form = toolkit.createScrolledForm(composite);
		form.setText(EMPTY);
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 1;
		form.getBody().setLayout(layout);
		Section section = toolkit.createSection(form.getBody(), 
				Section.TWISTIE|Section.COMPACT);
		TableWrapData td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		section.setLayoutData(td);
		section.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		section.setText(EJBUIMessages.HOMECOMPONENTINTERFACE);
		toolkit.createCompositeSeparator(section);
		Composite sectionClient = toolkit.createComposite(section);
		sectionClient.setLayout(new GridLayout());
		Button button = new Button(sectionClient,SWT.CHECK);
		button.setText(EJBUIMessages.LOCAL_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(button, LOCAL_HOME, null);
		Button button2 = new Button(sectionClient,SWT.CHECK);
		button2.setText(EJBUIMessages.REMOTE_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(button2, REMOTE_HOME, null);
		section.setClient(sectionClient);
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

	private void createBussinesInterface(Composite composite) {

		Label bussinessInterfaces = new Label(composite,SWT.TOP);
		bussinessInterfaces.setText(EJBUIMessages.BUSSINESS_INTERFACE);
		bussinessInterfaces.setLayoutData(new GridData(SWT.BEGINNING,SWT.BEGINNING,false,false,1,1));

		GridData gridData = new GridData ();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;

		businessnterfacesList = new TableViewer(composite, SWT.BORDER);
		businessnterfacesList.setContentProvider(new BusinessInterfaceContentProvider());
		businessnterfacesList.setLabelProvider(new BusinessInterfaceLabelProvider());
		businessnterfacesList.getControl().setLayoutData(gridData);
		businessnterfacesList.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) businessnterfacesList.getSelection();
				BusinessInterface element = (BusinessInterface) selection.getFirstElement();
				removeButton.setEnabled(element != null);
			}
			
		});
		updateBusinessInterfacesList();

		Composite buttonComposite = new Composite(composite, SWT.BEGINNING);
		GridLayout buttonLayout = new GridLayout(1,true);
		GridData buttonGridData = new GridData();
		buttonGridData.grabExcessHorizontalSpace = true;
		buttonComposite.setLayout(buttonLayout);
		buttonComposite.setLayoutData(new GridData(SWT.CENTER,SWT.BEGINNING,false,false,1,1));

		addButton = new Button(buttonComposite, SWT.PUSH);
		addButton.setText(EJBUIMessages.ADD_INTERFACES);
		addButton.addSelectionListener(new AddButtonListener(this, model));
		addButton.setLayoutData(buttonGridData);
		removeButton = new Button(buttonComposite, SWT.PUSH);
		removeButton.setText(EJBUIMessages.REMOVE_INTERFACES);
		removeButton.setEnabled(false);
		removeButton.setLayoutData(buttonGridData);
		removeButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection selection = (IStructuredSelection) businessnterfacesList.getSelection();
				BusinessInterface element = (BusinessInterface) selection.getFirstElement();
				businessnterfacesList.remove(element);
				if (element.getJavaType() == null) {
					if (element.isLocal())
						model.setBooleanProperty(LOCAL, false);
					else
						model.setBooleanProperty(REMOTE, false);
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	public void updateBusinessInterfacesList() {
		Object biList = getDataModel().getProperty(BUSINESS_INTERFACES);		
		businessnterfacesList.setInput(biList);
	}

	@Override
	protected void updateControls() {
		super.updateControls();
		updateBusinessInterfacesList();
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return null;
	};
}

