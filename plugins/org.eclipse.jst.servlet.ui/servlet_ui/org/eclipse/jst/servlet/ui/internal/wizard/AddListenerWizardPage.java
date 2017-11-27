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
package org.eclipse.jst.servlet.ui.internal.wizard;

import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;
import static org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider.LISTENER_INTERFACES;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ID_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_ASYNC_EVENT_LISTENER;
import static org.eclipse.jst.servlet.ui.internal.wizard.IWebWizardConstants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.web.operations.INewServletClassDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;

public class AddListenerWizardPage extends DataModelWizardPage  {
	
	private static final Image IMG_INTERFACE = JavaPluginImages.get(JavaPluginImages.IMG_OBJS_INTERFACE);

	protected ServletDataModelSyncHelper synchHelper2;
	private Button changeSessionID, asyncListener;
	private Label changeSessionIDIconLabel, changeSessionIDInterfaceLabel, asyncListenerIconLabel, asyncListenerInterfaceLabel;
	
	public AddListenerWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(ADD_LISTENER_WIZARD_PAGE_DESC);
		setTitle(ADD_LISTENER_WIZARD_PAGE_TITLE);
		synchHelper2 = initializeSynchHelper(model);
	}
	
	@Override
	public ServletDataModelSyncHelper initializeSynchHelper(IDataModel dm) {
		return new ServletDataModelSyncHelper(dm);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);
		
		createServletContextEvents(composite);
		createHttpSessionEvents(composite);
		createServletRequestEvents(composite);
		createSelectAllGroup(composite);
		
		Dialog.applyDialogFont(composite);
		
		return composite;
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return new String[] { 
				INTERFACES
		};
	}	
	
	@Override
	protected void enter() {
		super.enter();
		synchHelper2.synchUIWithModel(INTERFACES, DataModelEvent.VALUE_CHG);
	}

	private void createServletContextEvents(Composite parent) {
		Group group = createGroup(parent, ADD_LISTENER_WIZARD_SERVLET_CONTEXT_EVENTS);

		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_LIFECYCLE_L, 
				QUALIFIED_SERVLET_CONTEXT_LISTENER,
				INTERFACES);

		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_C, 
				QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER, 
				INTERFACES);
	}
	
	private void createHttpSessionEvents(Composite parent) {
		Group group = createGroup(parent, ADD_LISTENER_WIZARD_HTTP_SESSION_EVENTS);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_LIFECYCLE_I, 
				QUALIFIED_HTTP_SESSION_LISTENER, 
				INTERFACES);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_H, 
				QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER, 
				INTERFACES);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_SESSION_MIGRATION, 
				QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER, 
				INTERFACES);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_OBJECT_BINDING, 
				QUALIFIED_HTTP_SESSION_BINDING_LISTENER, 
				INTERFACES);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_OBJECT_SESSION_ID, 
				QUALIFIED_HTTP_SESSION_ID_LISTENER, 
				INTERFACES);		
	}
	
	private void createServletRequestEvents(Composite parent) {
		Group group = createGroup(parent, ADD_LISTENER_WIZARD_SERVLET_REQUEST_EVENTS);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_LIFECYCLE_Y, 
				QUALIFIED_SERVLET_REQUEST_LISTENER, 
				INTERFACES);

		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES_G, 
				QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER, 
				INTERFACES);
		
		createEventListenerRow(group, 
				ADD_LISTENER_WIZARD_ASYNC_EVENTS, 
				QUALIFIED_SERVLET_REQUEST_ASYNC_EVENT_LISTENER, 
				INTERFACES);		
	
	}
	
	
	private Group createGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NONE);
		
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		group.setText(text);
		
		return group;
	}
	
	private void createEventListenerRow(Composite parent, String event, String listener, String property) {
		if (listener.equals(QUALIFIED_HTTP_SESSION_ID_LISTENER)){
			changeSessionID = createCheckbox(parent, event, listener, property);
			changeSessionIDIconLabel = createInterfaceIcon(parent);
			changeSessionIDInterfaceLabel = createInterfaceLabel(parent, listener);
		}
		else if (listener.equals(QUALIFIED_SERVLET_REQUEST_ASYNC_EVENT_LISTENER)){
			asyncListener = createCheckbox(parent, event, listener, property);
			asyncListenerIconLabel = createInterfaceIcon(parent);
			asyncListenerInterfaceLabel = createInterfaceLabel(parent, listener);
		}
		else{
			createCheckbox(parent, event, listener, property);
			createInterfaceIcon(parent);
			createInterfaceLabel(parent, listener);
		}
	}
	
	private Button createCheckbox(Composite parent, String text, String value, String property) {
		Button button = new Button(parent, SWT.CHECK);
		
		button.setText(text);
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		synchHelper2.synchCheckbox(button, value, property, null);
		
		return button;
	}
	
	private Label createInterfaceIcon(Composite parent) {
		GridData data = new GridData();
		data.horizontalIndent = 50;

		Label label = new Label(parent, SWT.RIGHT);
		label.setImage(IMG_INTERFACE);
		label.setLayoutData(data);
		
		return label;
	}
	
	private Label createInterfaceLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.LEFT);
		
		label.setText(text);
		
		return label;
	}

	private void createSelectAllGroup(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Button selectAll = new Button(composite, SWT.PUSH);
		selectAll.setText(SELECT_ALL_BUTTON);
		selectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelectAll();
			}
		});
		
		Button clear = new Button(composite, SWT.PUSH);
		clear.setText(CLEAR_BUTTON);
		clear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				handleSelectNone();
			}
		});
	}

	private void handleSelectAll() {
		List interfaces = (List) model.getProperty(INTERFACES);
		if (interfaces == null) {
			interfaces = new ArrayList();
			model.setProperty(INTERFACES, interfaces);
		}
		
		for (String iface : LISTENER_INTERFACES) {
			if (!interfaces.contains(iface)) {
				interfaces.add(iface);
			}
		}
		
		synchHelper2.synchUIWithModel(INTERFACES, DataModelEvent.VALUE_CHG);
		model.notifyPropertyChange(INTERFACES, DataModelEvent.VALUE_CHG);
	}

	private void handleSelectNone() {
		List interfaces = (List) model.getProperty(INTERFACES);
		if (interfaces == null) {
			interfaces = new ArrayList();
			model.setProperty(INTERFACES, interfaces);
		}
		
		interfaces.removeAll(Arrays.asList(LISTENER_INTERFACES));
		
		synchHelper2.synchUIWithModel(INTERFACES, DataModelEvent.VALUE_CHG);
		model.notifyPropertyChange(INTERFACES, DataModelEvent.VALUE_CHG);
	}
	
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		String javaEEVersion = model.getStringProperty(INewServletClassDataModelProperties.JAVA_EE_VERSION);
		if("3.1".equals(javaEEVersion) || "4.0".equals(javaEEVersion)){ //$NON-NLS-1$ //$NON-NLS-2$ 	
			setServlet31ListenersVisible(true);
		}
		else{
			setServlet31ListenersVisible(false);
		}
	}
	
	
	private void setServlet31ListenersVisible(boolean visible){
		changeSessionID.setVisible(visible);
		changeSessionIDIconLabel.setVisible(visible);
		changeSessionIDInterfaceLabel.setVisible(visible);
		asyncListener.setVisible(visible);
		asyncListenerIconLabel.setVisible(visible);
		asyncListenerInterfaceLabel.setVisible(visible);
		
		if (!visible){
			List interfaces = (List) model.getProperty(INTERFACES);
			if (interfaces != null){
				//remove any selected interfaces
				interfaces.remove(QUALIFIED_HTTP_SESSION_ID_LISTENER);
				interfaces.remove(QUALIFIED_SERVLET_REQUEST_ASYNC_EVENT_LISTENER);
				synchHelper2.synchUIWithModel(INTERFACES, DataModelEvent.VALUE_CHG);
				model.notifyPropertyChange(INTERFACES, DataModelEvent.VALUE_CHG);
			}
		}
	}

}
