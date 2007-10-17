package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.web.operations.INewListenerClassDataModelProperties;
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
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;

public class AddListenerWizardPage extends DataModelWizardPage {
	
	private static final Image IMG_INTERFACE = JavaPluginImages.get(JavaPluginImages.IMG_OBJS_INTERFACE);
	
	public AddListenerWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IWebWizardConstants.ADD_LISTENER_WIZARD_PAGE_DESC);
		setTitle(IWebWizardConstants.ADD_LISTENER_WIZARD_PAGE_TITLE);
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
				INewListenerClassDataModelProperties.SERVLET_CONTEXT_LISTENER, 
				INewListenerClassDataModelProperties.SERVLET_CONTEXT_ATTRIBUTE_LISTENER, 
				INewListenerClassDataModelProperties.HTTP_SESSION_LISTENER, 
				INewListenerClassDataModelProperties.HTTP_SESSION_ATTRIBUTE_LISTENER, 
				INewListenerClassDataModelProperties.HTTP_SESSION_ACTIVATION_LISTENER, 
				INewListenerClassDataModelProperties.HTTP_SESSION_BINDING_LISTENER, 
				INewListenerClassDataModelProperties.SERVLET_REQUEST_LISTENER, 
				INewListenerClassDataModelProperties.SERVLET_REQUEST_ATTRIBUTE_LISTENER
		};
	}
	
	private void createServletContextEvents(Composite parent) {
		Group group = createGroup(parent, IWebWizardConstants.ADD_LISTENER_WIZARD_SERVLET_CONTEXT_EVENTS);

		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_LIFECYCLE, 
				"javax.servlet.ServletContextListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.SERVLET_CONTEXT_LISTENER);

		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES, 
				"javax.servlet.ServletContextAttributeListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
	}
	
	private void createHttpSessionEvents(Composite parent) {
		Group group = createGroup(parent, IWebWizardConstants.ADD_LISTENER_WIZARD_HTTP_SESSION_EVENTS);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_LIFECYCLE, 
				"javax.servlet.http.HttpSessionListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.HTTP_SESSION_LISTENER);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES, 
				"javax.servlet.http.HttpSessionAttributeListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.HTTP_SESSION_ATTRIBUTE_LISTENER);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_SESSION_MIGRATION, 
				"javax.servlet.http.HttpSessionActivationListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.HTTP_SESSION_ACTIVATION_LISTENER);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_OBJECT_BINDING, 
				"javax.servlet.http.HttpSessionBindingListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.HTTP_SESSION_BINDING_LISTENER);
	}
	
	private void createServletRequestEvents(Composite parent) {
		Group group = createGroup(parent, IWebWizardConstants.ADD_LISTENER_WIZARD_SERVLET_REQUEST_EVENTS);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_LIFECYCLE, 
				"javax.servlet.http.ServletRequestListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.SERVLET_REQUEST_LISTENER);

		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES, 
				"javax.servlet.http.ServletRequestAttributeListener", //$NON-NLS-1$
				INewListenerClassDataModelProperties.SERVLET_REQUEST_ATTRIBUTE_LISTENER);
	}
	
	private Group createGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NONE);
		
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		group.setText(text);
		
		return group;
	}
	
	private void createEventListenerRow(Composite parent, String event, String listener, String property) {
		createCheckbox(parent, event, property);
		createInterfaceIcon(parent);
		createInterfaceLabel(parent, listener);
	}
	
	private Button createCheckbox(Composite parent, String text, String property) {
		Button button = new Button(parent, SWT.CHECK);
		
		button.setText(text);
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		synchHelper.synchCheckbox(button, property, null);
		
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
		selectAll.setText(IWebWizardConstants.SELECT_ALL_BUTTON);
		selectAll.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleSelectAll();
			}
		});
		
		Button clear = new Button(composite, SWT.PUSH);
		clear.setText(IWebWizardConstants.CLEAR_BUTTON);
		clear.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleSelectNone();
			}
		});
	}

	private void handleSelectAll() {
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_CONTEXT_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_CONTEXT_ATTRIBUTE_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_ATTRIBUTE_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_ACTIVATION_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_BINDING_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_REQUEST_LISTENER, true);
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_REQUEST_ATTRIBUTE_LISTENER, true);
	}

	private void handleSelectNone() {
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_CONTEXT_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_CONTEXT_ATTRIBUTE_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_ATTRIBUTE_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_ACTIVATION_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.HTTP_SESSION_BINDING_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_REQUEST_LISTENER, false);
		model.setBooleanProperty(INewListenerClassDataModelProperties.SERVLET_REQUEST_ATTRIBUTE_LISTENER, false);
	}

}
