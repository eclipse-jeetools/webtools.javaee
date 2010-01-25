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

import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.EJB_NAME;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewEnterpriseBeanClassDataModelProperties.TRANSACTION_TYPE;
import static org.eclipse.jst.j2ee.ejb.internal.operations.INewMessageDrivenBeanClassDataModelProperties.MESSAGE_LISTENER_INTERFACE;
import static org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties.INTERFACES;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.dialogs.TypeSearchEngine;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.HyperlinkGroup;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;

public class AddMessageDrivenBeanWizardPage extends
		AddEnterpriseBeanWizardPage {

	private class HyperlinkDataModelSynchHelper extends DataModelSynchHelper {

		public HyperlinkDataModelSynchHelper(IDataModel model) {
			super(model);
		}
		
		public void synchHyperlink(Hyperlink hyperlink, String propertyName, Control[] dependentControls) {
			synchComposite(hyperlink, propertyName, dependentControls);
		}
		
		@Override
		public void synchUIWithModel(final String propertyName, final int flag) {
			if (MESSAGE_LISTENER_INTERFACE.equals(propertyName)) {
				if(Thread.currentThread() == Display.getDefault().getThread()) {
					setHyperlinkText();
				} else {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							setHyperlinkText();
						}
					});
				}
			} else if (INTERFACES.equals(propertyName)) {
				if(Thread.currentThread() == Display.getDefault().getThread()) {
					updateInterfaces();
				} else {
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							updateInterfaces();
						}
					});
				}
			}
			
			super.synchUIWithModel(propertyName, flag);
		}
		
		private void setHyperlinkText() {
			if (null != messageListenerInterfaceHyperlink) {
				String value = getDataModel().getStringProperty(MESSAGE_LISTENER_INTERFACE);
				if (value == null || value.trim().length() == 0) {
					value = IEjbWizardConstants.CLICK_TO_SELECT;
				}
				messageListenerInterfaceHyperlink.setText(value);
			}
		}

		private void updateInterfaces() {
			if (null != interfaceViewer) {
				interfaceViewer.setInput(getDataModel().getProperty(INTERFACES));
			}
		}
		
	}

	private Text ejbNameText;
	private Combo transactionTypeCombo;

	private Hyperlink messageListenerInterfaceHyperlink;

	public AddMessageDrivenBeanWizardPage(IDataModel model, String pageName) {
		super(model, pageName,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEAN_WIZARD_PAGE_DESC,
				IEjbWizardConstants.ADD_MESSAGE_DRIVEN_BEANS_WIZARD_PAGE_TITLE);
	}
	
	@Override
	public DataModelSynchHelper initializeSynchHelper(IDataModel dm) {
		return new HyperlinkDataModelSynchHelper(dm);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));
		GridData data = new GridData(GridData.FILL_BOTH);
		data.widthHint = 300;
		composite.setLayoutData(data);
		
		createBeanNameTransactionType(composite);
		addSeperator(composite, 2);
		createInterfaceControls(composite);
		createMessageListenerInterfaceControl(composite);
		addSeperator(composite, 2);
		createStubsComposite(composite);

		return composite;
	}
	
	private void createBeanNameTransactionType(Composite composite) {
		// bean name
		Label ejbNameLabel = new Label(composite, SWT.LEFT);
		ejbNameLabel.setText(IEjbWizardConstants.EJB_NAME);

		GridData data = new GridData();
		data.horizontalAlignment = GridData.FILL;
		data.grabExcessHorizontalSpace = true;

		ejbNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		ejbNameText.setLayoutData(data);
		synchHelper.synchText(ejbNameText, EJB_NAME, null);

		// transaction type
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
	}

	private void createMessageListenerInterfaceControl(Composite composite) {
		Label messageListenerInterfaceLabel = new Label(composite, SWT.LEFT);
		messageListenerInterfaceLabel.setText(EJBUIMessages.MESSAGE_LISTENER_INTERFACE_HYPERLINK);
		messageListenerInterfaceHyperlink = new Hyperlink(composite, SWT.NULL);
		messageListenerInterfaceHyperlink.setToolTipText(IEjbWizardConstants.MESSAGE_LISTENER_INTERFACE_HYPERLINK_TOOLTIP);
		messageListenerInterfaceHyperlink.setVisible(true);
		messageListenerInterfaceHyperlink.setUnderlined(true);
		messageListenerInterfaceHyperlink.addHyperlinkListener(new HyperlinkAdapter() {
			@Override
			public void linkActivated(HyperlinkEvent e){
				IProject project = (IProject) model.getProperty(INewJavaClassDataModelProperties.PROJECT);
				IRunnableContext context = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IJavaProject javaProject = JemProjectUtilities.getJavaProject(project);
				// this eliminates the non-exported classpath entries
				final IJavaSearchScope scope = TypeSearchEngine.createJavaSearchScopeForAProject(javaProject, true, true);
				FilteredTypesSelectionDialog dialog = new FilteredTypesSelectionDialog(getShell(), false, context, scope, IJavaSearchConstants.INTERFACE);
				dialog.setTitle(J2EEUIMessages.INTERFACE_SELECTION_DIALOG_TITLE);
				if (dialog.open() == Window.OK) {
					IType type = (IType) dialog.getFirstResult();
					String fullyQualifiedName = type.getFullyQualifiedName();
					getDataModel().setProperty(MESSAGE_LISTENER_INTERFACE, fullyQualifiedName);
				}
			}
		});
		new HyperlinkGroup(Display.getCurrent()).add(messageListenerInterfaceHyperlink);
		((HyperlinkDataModelSynchHelper) synchHelper).synchHyperlink(messageListenerInterfaceHyperlink, MESSAGE_LISTENER_INTERFACE, null);
	}

	@Override
	protected boolean showValidationErrorsOnEnter() {
		return true;
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return new String[] { MESSAGE_LISTENER_INTERFACE , EJB_NAME};
	}

}

