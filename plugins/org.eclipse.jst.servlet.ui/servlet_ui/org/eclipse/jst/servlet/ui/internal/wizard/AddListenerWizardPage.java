package org.eclipse.jst.servlet.ui.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.web.operations.NewListenerClassDataModelProvider;
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

public class AddListenerWizardPage extends DataModelWizardPage {
	
	private static final Image IMG_INTERFACE = JavaPluginImages.get(JavaPluginImages.IMG_OBJS_INTERFACE);

	protected ServletDataModelSyncHelper synchHelper;
	
	public AddListenerWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setDescription(IWebWizardConstants.ADD_LISTENER_WIZARD_PAGE_DESC);
		setTitle(IWebWizardConstants.ADD_LISTENER_WIZARD_PAGE_TITLE);
		synchHelper = initializeSynchHelper(model);
	}
	
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
				INewJavaClassDataModelProperties.INTERFACES
		};
	}	
	
	@Override
	protected void enter() {
		super.enter();
		synchHelper.synchUIWithModel(INewJavaClassDataModelProperties.INTERFACES, DataModelEvent.VALUE_CHG);
	}

	private void createServletContextEvents(Composite parent) {
		Group group = createGroup(parent, IWebWizardConstants.ADD_LISTENER_WIZARD_SERVLET_CONTEXT_EVENTS);

		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_LIFECYCLE, 
				NewListenerClassDataModelProvider.SERVLET_CONTEXT_LISTENER,
				INewJavaClassDataModelProperties.INTERFACES);

		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES, 
				NewListenerClassDataModelProvider.SERVLET_CONTEXT_ATTRIBUTE_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);
	}
	
	private void createHttpSessionEvents(Composite parent) {
		Group group = createGroup(parent, IWebWizardConstants.ADD_LISTENER_WIZARD_HTTP_SESSION_EVENTS);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_LIFECYCLE, 
				NewListenerClassDataModelProvider.HTTP_SESSION_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES, 
				NewListenerClassDataModelProvider.HTTP_SESSION_ATTRIBUTE_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_SESSION_MIGRATION, 
				NewListenerClassDataModelProvider.HTTP_SESSION_ACTIVATION_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_OBJECT_BINDING, 
				NewListenerClassDataModelProvider.HTTP_SESSION_BINDING_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);
	}
	
	private void createServletRequestEvents(Composite parent) {
		Group group = createGroup(parent, IWebWizardConstants.ADD_LISTENER_WIZARD_SERVLET_REQUEST_EVENTS);
		
		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_LIFECYCLE, 
				NewListenerClassDataModelProvider.SERVLET_REQUEST_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);

		createEventListenerRow(group, 
				IWebWizardConstants.ADD_LISTENER_WIZARD_CHANGES_TO_ATTRIBUTES, 
				NewListenerClassDataModelProvider.SERVLET_REQUEST_ATTRIBUTE_LISTENER, 
				INewJavaClassDataModelProperties.INTERFACES);
	}
	
	private Group createGroup(Composite parent, String text) {
		Group group = new Group(parent, SWT.NONE);
		
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		group.setText(text);
		
		return group;
	}
	
	private void createEventListenerRow(Composite parent, String event, String listener, String property) {
		createCheckbox(parent, event, listener, property);
		createInterfaceIcon(parent);
		createInterfaceLabel(parent, listener);
	}
	
	private Button createCheckbox(Composite parent, String text, String value, String property) {
		Button button = new Button(parent, SWT.CHECK);
		
		button.setText(text);
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		synchHelper.synchCheckbox(button, value, property, null);
		
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
		List interfaces = (List) model.getProperty(INewJavaClassDataModelProperties.INTERFACES);
		if (interfaces == null) {
			interfaces = new ArrayList();
			model.setProperty(INewJavaClassDataModelProperties.INTERFACES, interfaces);
		}
		
		for (String iface : NewListenerClassDataModelProvider.LISTENER_INTERFACES) {
			if (!interfaces.contains(iface)) {
				interfaces.add(iface);
			}
		}
		
		synchHelper.synchUIWithModel(INewJavaClassDataModelProperties.INTERFACES, DataModelEvent.VALUE_CHG);
		model.notifyPropertyChange(INewJavaClassDataModelProperties.INTERFACES, DataModelEvent.VALUE_CHG);
	}

	private void handleSelectNone() {
		List interfaces = (List) model.getProperty(INewJavaClassDataModelProperties.INTERFACES);
		if (interfaces == null) {
			interfaces = new ArrayList();
			model.setProperty(INewJavaClassDataModelProperties.INTERFACES, interfaces);
		}
		
		interfaces.removeAll(Arrays.asList(NewListenerClassDataModelProvider.LISTENER_INTERFACES));
		
		synchHelper.synchUIWithModel(INewJavaClassDataModelProperties.INTERFACES, DataModelEvent.VALUE_CHG);
		model.notifyPropertyChange(INewJavaClassDataModelProperties.INTERFACES, DataModelEvent.VALUE_CHG);
	}

}
