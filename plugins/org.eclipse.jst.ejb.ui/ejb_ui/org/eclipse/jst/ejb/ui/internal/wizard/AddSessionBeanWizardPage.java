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

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.MAPPED_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.TRANSACTION_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_COMPONENT_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.LOCAL_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_COMPONENT_INTERFACE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewSessionBeanClassDataModelProperties.REMOTE_HOME_INTERFACE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.jdt.internal.ui.preferences.ScrolledPageContent;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.ejb.internal.operations.BusinessInterface;
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
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AddSessionBeanWizardPage extends AddEnterpriseBeanWizardPage {

	private Text ejbNameText;
	private Text mappedNameText;
	private Combo transactionTypeCombo;
	private Session2xInterfacesTable localIntfTable;
	private Session2xInterfacesTable remoteIntfTable;

	public AddSessionBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName, IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_SESSION_BEAN_WIZARD_PAGE_TITLE);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {

		ScrolledPageContent pageContent = new ScrolledPageContent(parent);
		Composite composite = pageContent.getBody();
		composite.setLayout(new GridLayout(3, false));

		Label ejbNameLabel = new Label(composite, SWT.LEFT);
		ejbNameLabel.setText(IEjbWizardConstants.EJB_NAME);

		GridData data = new GridData();
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
		synchHelper.synchText(mappedNameText, MAPPED_NAME, null);

		Label transactionTypeLabel = new Label(composite, SWT.LEFT);
		transactionTypeLabel.setText(EJBUIMessages.TRANSACTION_TYPE);
		transactionTypeCombo = new Combo(composite, SWT.None | SWT.READ_ONLY);
		transactionTypeCombo.setLayoutData(data);

		DataModelPropertyDescriptor[] descriptors = model.getValidPropertyDescriptors(TRANSACTION_TYPE);
		for (DataModelPropertyDescriptor descriptor : descriptors) {
			transactionTypeCombo.add(descriptor.getPropertyDescription());
		}

		transactionTypeCombo.select(0);
		synchHelper.synchCombo(transactionTypeCombo, TRANSACTION_TYPE, null);

		addSeperator(composite, 3);
		createInterfaceControls(composite);
		createExpandableComposite(composite);
		createStubsComposite(composite);

		return pageContent;
	}

	private ExpandableComposite createExpandableComposite(Composite composite) {
		ExpandableComposite excomposite = new ExpandableComposite(composite, SWT.NONE, ExpandableComposite.TWISTIE
				| ExpandableComposite.CLIENT_INDENT);
		excomposite.setText(EJBUIMessages.HOMECOMPONENTINTERFACE);
		excomposite.setExpanded(false);
		excomposite.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
		excomposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 3, 1));
		excomposite.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				expandedStateChanged((ExpandableComposite) e.getSource());
			}
		});

		Composite othersComposite = new Composite(excomposite, SWT.NONE);
		excomposite.setClient(othersComposite);
		othersComposite.setLayout(new GridLayout(2, false));
		final Button local2xCheck = new Button(othersComposite, SWT.CHECK | SWT.TOP);
		local2xCheck.setText(EJBUIMessages.LOCAL_BUSINESS_INTERFACE);

		synchHelper.synchCheckbox(local2xCheck, LOCAL_HOME, null);

		Session2xInterfacesTableRow localRow = new Session2xInterfacesTableRow(
				EJBUIMessages.LOCAL_COMPONENT_INTERFACE_CODE, model.getStringProperty(LOCAL_COMPONENT_INTERFACE),
				LOCAL_COMPONENT_INTERFACE);
		Session2xInterfacesTableRow localRowHome = new Session2xInterfacesTableRow(
				EJBUIMessages.LOCAL_HOME_INTERFACE_CODE, model.getStringProperty(LOCAL_HOME_INTERFACE),
				LOCAL_HOME_INTERFACE);
		Session2xInterfacesTableRow[] localTableRows = { localRow, localRowHome };
		localIntfTable = new Session2xInterfacesTable(othersComposite, new String[0], model, localTableRows);
		localIntfTable.getTable().setEnabled(model.getBooleanProperty(LOCAL_HOME));
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		gridData.verticalSpan = 2;
		gridData.verticalAlignment = SWT.BEGINNING;
		gridData.verticalSpan = 1;
		local2xCheck.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

			}

			public void widgetSelected(SelectionEvent e) {
				localIntfTable.getTable().setEnabled(local2xCheck.getSelection());
			}

		});

		final Button remote2xCheck = new Button(othersComposite, SWT.CHECK);
		remote2xCheck.setText(EJBUIMessages.REMOTE_BUSINESS_INTERFACE_E);

		synchHelper.synchCheckbox(remote2xCheck, REMOTE_HOME, null);

		Session2xInterfacesTableRow remoteRow = new Session2xInterfacesTableRow(
				EJBUIMessages.REMOTE_COMPONENT_INTERFACE_CODE, model.getStringProperty(REMOTE_COMPONENT_INTERFACE),
				REMOTE_COMPONENT_INTERFACE);
		Session2xInterfacesTableRow remoteRowHome = new Session2xInterfacesTableRow(
				EJBUIMessages.REMOTE_HOME_INTERFACE_CODE, model.getStringProperty(REMOTE_HOME_INTERFACE),
				REMOTE_HOME_INTERFACE);
		Session2xInterfacesTableRow[] remoteTableRows = { remoteRow, remoteRowHome };
		remoteIntfTable = new Session2xInterfacesTable(othersComposite, new String[0], model, remoteTableRows);
		remoteIntfTable.getTable().setEnabled(model.getBooleanProperty(REMOTE_HOME));
		remote2xCheck.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {

			}

			public void widgetSelected(SelectionEvent e) {
				remoteIntfTable.getTable().setEnabled(remote2xCheck.getSelection());
			}

		});

		return excomposite;
	}

	@Override
	protected void enter() {
		super.enter();
		updateBusinessInterfacesList();
	}

	@Override
	protected IStructuredContentProvider getInterfaceContentProvider() {
		return new BusinessInterfaceContentProvider();
	}

	@Override
	protected ILabelProvider getInterfaceLabelProvider() {
		return new BusinessInterfaceLabelProvider();
	}

	@Override
	protected void handleInterfaceAddButtonSelected() {
		AddButtonListener listener = new AddButtonListener(this, model);
		listener.widgetSelected(null);
	}
 	

	@Override
	protected void handleInterfaceRemoveButtonSelected() {
		IStructuredSelection selection = (IStructuredSelection) interfaceViewer.getSelection();
		Iterator iterator = selection.iterator();
		while (iterator.hasNext()) {
			BusinessInterface element = (BusinessInterface) iterator.next();
			if (element.getJavaType() == null) {
				if (element.isLocal())
					model.setBooleanProperty(LOCAL, false);
				else
					model.setBooleanProperty(REMOTE, false);
			} else {
				removeInterfaceFromModel(element);
			}
		}
		updateBusinessInterfacesList();
	}

	private void removeInterfaceFromModel(BusinessInterface element) {
		Collection<BusinessInterface> biList = (Collection<BusinessInterface>) model.getProperty(INTERFACES);
		biList.remove(element);
		model.setProperty(INTERFACES, biList);
		model.notifyPropertyChange(INTERFACES, IDataModel.VALUE_CHG);
	}

	public void updateBusinessInterfacesList() {
		Object biList = getDataModel().getProperty(INTERFACES);
		interfaceViewer.setInput(biList);
	}
	
	public void updateInterfaces(){
		if(localIntfTable != null){
			ArrayList input = new ArrayList();
			Session2xInterfacesTableRow localRow = new Session2xInterfacesTableRow(
					EJBUIMessages.LOCAL_COMPONENT_INTERFACE_CODE, 
					model.getStringProperty(LOCAL_COMPONENT_INTERFACE), 
					LOCAL_COMPONENT_INTERFACE);
			input.add(localRow);
			Session2xInterfacesTableRow localRowHome = new Session2xInterfacesTableRow(
					EJBUIMessages.LOCAL_HOME_INTERFACE_CODE, 
					model.getStringProperty(LOCAL_HOME_INTERFACE), 
					LOCAL_HOME_INTERFACE);
			input.add(localRowHome);
			localIntfTable.getTableViewer().setInput(input);
			//localIntfTable.getTableViewer().refresh();
		}
		if(remoteIntfTable != null){
			ArrayList input = new ArrayList();
			Session2xInterfacesTableRow remoteRow = new Session2xInterfacesTableRow(
					EJBUIMessages.REMOTE_COMPONENT_INTERFACE_CODE, 
					model.getStringProperty(REMOTE_COMPONENT_INTERFACE), 
					REMOTE_COMPONENT_INTERFACE);
			input.add(remoteRow);
			Session2xInterfacesTableRow remoteRowHome = new Session2xInterfacesTableRow(
					EJBUIMessages.REMOTE_HOME_INTERFACE_CODE, 
					model.getStringProperty(REMOTE_HOME_INTERFACE), 
					REMOTE_HOME_INTERFACE);
			input.add(remoteRowHome);
			remoteIntfTable.getTableViewer().setInput(input);
		}
	}

	@Override
	protected void updateControls() {
		super.updateControls();
		updateBusinessInterfacesList();
		updateInterfaces();
	}

	protected ScrolledPageContent getParentScrolledComposite(Control control) {
		Control parent = control.getParent();
		while (!(parent instanceof ScrolledPageContent) && parent != null) {
			parent = parent.getParent();
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

	@Override
	protected String[] getValidationPropertyNames() {
		return new String[] { LOCAL_HOME_INTERFACE, REMOTE_HOME_INTERFACE, LOCAL_COMPONENT_INTERFACE,
				REMOTE_COMPONENT_INTERFACE, EJB_NAME, INTERFACES };
	}

	@Override
	protected boolean showValidationErrorsOnEnter() {
		return true;
	}

}
