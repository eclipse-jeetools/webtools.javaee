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
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jdt.internal.ui.preferences.ScrolledPageContent;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;
import org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddSessionBeanWizardPage extends AddEnterpriseBeanWizardPage implements
		INewSessionBeanClassDataModelProperties {

	private Text ejbNameText;
	private Text mappedNameText;
	private Combo transactionTypeCombo;
	
	public AddSessionBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName, 
				IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_DESC, 
				IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_TITLE);
	}

	protected Composite createTopLevelComposite(Composite parent) {
		
		ScrolledPageContent pageContent = new ScrolledPageContent(parent);
		Composite composite = pageContent.getBody();
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
		
		addSeperator(composite, 3);

		createInterfaceControls(composite);
		createExpandableComposite(composite);
		createStubsComposite(composite);

		return pageContent;
	}

	private ExpandableComposite createExpandableComposite(Composite composite) {
		ExpandableComposite excomposite = new ExpandableComposite(composite, SWT.NONE, ExpandableComposite.TWISTIE | ExpandableComposite.CLIENT_INDENT);
		excomposite.setText(EJBUIMessages.HOMECOMPONENTINTERFACE);
		excomposite.setExpanded(false);
		excomposite.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
		excomposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 3, 1));
		excomposite.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				expandedStateChanged((ExpandableComposite) e.getSource());
			}
		});
		
		Composite othersComposite = new Composite(excomposite, SWT.NONE);
		excomposite.setClient(othersComposite);
		othersComposite.setLayout(new GridLayout(1, false));
		
		Button button = new Button(othersComposite, SWT.CHECK);
		button.setText(EJBUIMessages.LOCAL_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(button, LOCAL_HOME, null);
		Button button2 = new Button(othersComposite, SWT.CHECK);
		button2.setText(EJBUIMessages.REMOTE_BUSINESS_INTERFACE);
		synchHelper.synchCheckbox(button2, REMOTE_HOME, null);
		
		return excomposite;
	}

	@Override
	protected void enter() {
		super.enter();
		updateBusinessInterfacesList();
	}
	
	@Override
	protected void createInterfaceControls(Composite composite) {

		Label bussinessInterfaces = new Label(composite,SWT.TOP);
		bussinessInterfaces.setText(EJBUIMessages.BUSSINESS_INTERFACE);
		bussinessInterfaces.setLayoutData(new GridData(SWT.BEGINNING,SWT.BEGINNING,false,false,1,1));

		GridData gridData = new GridData ();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;

		interfaceViewer = new TableViewer(composite, SWT.BORDER);
		interfaceViewer.setContentProvider(new BusinessInterfaceContentProvider());
		interfaceViewer.setLabelProvider(new BusinessInterfaceLabelProvider());
		interfaceViewer.getControl().setLayoutData(gridData);
		interfaceViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) interfaceViewer.getSelection();
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
				IStructuredSelection selection = (IStructuredSelection) interfaceViewer.getSelection();
				BusinessInterface element = (BusinessInterface) selection.getFirstElement();
				interfaceViewer.remove(element);
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
		interfaceViewer.setInput(biList);
	}

	@Override
	protected void updateControls() {
		super.updateControls();
		updateBusinessInterfacesList();
	}

	protected ScrolledPageContent getParentScrolledComposite(Control control) {
		Control parent= control.getParent();
		while (!(parent instanceof ScrolledPageContent) && parent != null) {
			parent= parent.getParent();
		}
		if (parent instanceof ScrolledPageContent) {
			return (ScrolledPageContent) parent;
		}
		return null;
	}
	
	protected final void expandedStateChanged(ExpandableComposite expandable) {
		ScrolledPageContent parentScrolledComposite = getParentScrolledComposite(expandable);
		if (parentScrolledComposite != null) {
			parentScrolledComposite.reflow(true);
		}
	}
}

