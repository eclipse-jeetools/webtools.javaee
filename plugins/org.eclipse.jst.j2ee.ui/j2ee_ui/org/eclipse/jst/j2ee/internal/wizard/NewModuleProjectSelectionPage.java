/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 13, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.ui.AppClientModuleCreationWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.wizards.IWizardDescriptor;
import org.eclipse.ui.wizards.IWizardRegistry;
import org.eclipse.wst.common.frameworks.internal.ui.GenericWizardNode;
import org.eclipse.wst.common.frameworks.ui.WTPWizardPage;

import com.ibm.wtp.common.logger.proxy.Logger;


/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class NewModuleProjectSelectionPage extends WTPWizardPage {
	private Button defaultModulesButton;
	private Composite defaultModulesComposite;
	private Composite newModulesComposite;
	private Button appClientRadioButton;
	private Button ejbRadioButton;
	private Button webRadioButton;
	private Button connectorRadioButton;
	private GenericWizardNode appClientNode;
	private GenericWizardNode ejbNode;
	private GenericWizardNode webNode;
	private GenericWizardNode connectorNode;
	private GenericWizardNode selectedNode;
	private StackLayout stackLayout;

	/**
	 * @param model
	 * @param pageName
	 */
	protected NewModuleProjectSelectionPage(DefaultModuleProjectCreationDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_MOD_SEL_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_MOD_SEL_PG_DESC));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{DefaultModuleProjectCreationDataModel.CREATE_APPCLIENT, DefaultModuleProjectCreationDataModel.APPCLIENT_PROJECT_NAME, DefaultModuleProjectCreationDataModel.CREATE_CONNECTOR, DefaultModuleProjectCreationDataModel.CONNECTOR_PROJECT_NAME, DefaultModuleProjectCreationDataModel.CREATE_EJB, DefaultModuleProjectCreationDataModel.EJB_PROJECT_NAME, DefaultModuleProjectCreationDataModel.CREATE_WEB, DefaultModuleProjectCreationDataModel.WEB_PROJECT_NAME, DefaultModuleProjectCreationDataModel.MODULE_NAME_COLLISIONS_VALIDATION, DefaultModuleProjectCreationDataModel.ENABLED};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		setInfopopID(IJ2EEUIContextIds.EAR_NEW_MODULE_PROJECTS_PAGE);
		createDefaultCheckBox(composite);
		Composite forStackComposite = new Composite(composite, SWT.NULL);
		layout = new GridLayout();
		forStackComposite.setLayout(layout);
		forStackComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		Composite stackComposite = createStackLayoutComposite(forStackComposite);
		createDefaultModulesComposite(stackComposite);
		createModuleSelectionComposite(stackComposite);
		stackLayout.topControl = defaultModulesComposite;
		setButtonEnablement();
		return composite;
	}

	protected Composite createStackLayoutComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		stackLayout = new StackLayout();
		composite.setLayout(stackLayout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		return composite;
	}

	private void createDefaultModulesComposite(Composite parent) {
		defaultModulesComposite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		defaultModulesComposite.setLayout(layout);
		defaultModulesComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		//Default Module Controls creation
		createAppClientDefaultModuleControl();
		if (J2EEPlugin.isEJBSupportAvailable())
			createEJBDefaultModuleControl();
		createWebDefaultModuleControl();
		if (J2EEPlugin.isEJBSupportAvailable())
			createConnectorDefaultModuleControl();
	}

	/**
	 * @param parent
	 */
	private void createModuleSelectionComposite(Composite parent) {
		newModulesComposite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		newModulesComposite.setLayout(layout);
		newModulesComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		appClientRadioButton = new Button(newModulesComposite, SWT.RADIO);

		appClientRadioButton.setText(J2EEUIMessages.getResourceString("NewModuleSelectionPage.appClient")); //$NON-NLS-1$
		appClientRadioButton.addListener(SWT.Selection, this);
		if (EarModuleManager.getEJBModuleExtension() != null) {
			ejbRadioButton = new Button(newModulesComposite, SWT.RADIO);
			ejbRadioButton.setText(J2EEUIMessages.getResourceString("NewModuleSelectionPage.ejb")); //$NON-NLS-1$
			ejbRadioButton.addListener(SWT.Selection, this);
		}
		if (EarModuleManager.getWebModuleExtension() != null) {
			webRadioButton = new Button(newModulesComposite, SWT.RADIO);
			webRadioButton.setText(J2EEUIMessages.getResourceString("NewModuleSelectionPage.web")); //$NON-NLS-1$
			webRadioButton.addListener(SWT.Selection, this);
		}
		if (EarModuleManager.getJCAModuleExtension() != null) {
			connectorRadioButton = new Button(newModulesComposite, SWT.RADIO);
			connectorRadioButton.setText(J2EEUIMessages.getResourceString("NewModuleSelectionPage.jca")); //$NON-NLS-1$
			connectorRadioButton.addListener(SWT.Selection, this);
		}
	}

	/**
	 *  
	 */
	private void createConnectorDefaultModuleControl() {
		if (EarModuleManager.getJCAModuleExtension() != null) {
			String label = J2EEUIMessages.getResourceString(J2EEUIMessages.JCA_PROJ_LBL);
			createModuleProjectControl(label, DefaultModuleProjectCreationDataModel.CREATE_CONNECTOR, DefaultModuleProjectCreationDataModel.CONNECTOR_PROJECT_NAME);
		}
	}

	/**
	 *  
	 */
	private void createWebDefaultModuleControl() {
		if (EarModuleManager.getWebModuleExtension() != null) {
			String label = J2EEUIMessages.getResourceString(J2EEUIMessages.WEB_PROJ_LBL);
			createModuleProjectControl(label, DefaultModuleProjectCreationDataModel.CREATE_WEB, DefaultModuleProjectCreationDataModel.WEB_PROJECT_NAME);
		}
	}

	/**
	 *  
	 */
	private void createEJBDefaultModuleControl() {
		if (EarModuleManager.getEJBModuleExtension() != null) {
			String label = J2EEUIMessages.getResourceString(J2EEUIMessages.EJB_PROJ_LBL);
			createModuleProjectControl(label, DefaultModuleProjectCreationDataModel.CREATE_EJB, DefaultModuleProjectCreationDataModel.EJB_PROJECT_NAME);
		}
	}

	private void createAppClientDefaultModuleControl() {
		String label = J2EEUIMessages.getResourceString(J2EEUIMessages.APP_CLIENT_PROJ_LBL);
		createModuleProjectControl(label, DefaultModuleProjectCreationDataModel.CREATE_APPCLIENT, DefaultModuleProjectCreationDataModel.APPCLIENT_PROJECT_NAME);
	}

	private void createModuleProjectControl(String label, String createProperty, String projectProperty) {
		final Button checkBox = new Button(defaultModulesComposite, SWT.CHECK);
		checkBox.setSelection(true);
		checkBox.setText(label);

		final Text textField = new Text(defaultModulesComposite, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		textField.setLayoutData(data);
		synchHelper.synchCheckbox(checkBox, createProperty, null);
		synchHelper.synchText(textField, projectProperty, null);
	}

	private void createDefaultCheckBox(Composite composite) {
		Composite checkBoxComposite = new Composite(composite, SWT.NULL);
		GridLayout layout = new GridLayout();
		checkBoxComposite.setLayout(layout);
		checkBoxComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
		defaultModulesButton = new Button(checkBoxComposite, SWT.CHECK);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING);
		data.horizontalIndent = 0;
		defaultModulesButton.setLayoutData(data);
		defaultModulesButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_MOD_SEL_PG_DEF_BTN));
		defaultModulesButton.setSelection(true);
		defaultModulesButton.addListener(SWT.Selection, this);
		synchHelper.synchCheckbox(defaultModulesButton, DefaultModuleProjectCreationDataModel.ENABLED, null);
		createControlsSeparatorLine(checkBoxComposite);
	}

	protected void createControlsSeparatorLine(Composite parent) {
		// add a horizontal line
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		separator.setLayoutData(data);
	}

	private DefaultModuleProjectCreationDataModel getDefaultModel() {
		return (DefaultModuleProjectCreationDataModel) model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event evt) {
		if (evt.widget == defaultModulesButton)
			handleDefaultModulesButtonPressed();
		else if (!defaultModulesButton.getSelection()) {
			if (evt.widget == appClientRadioButton && appClientRadioButton.getSelection())
				setSelectedNode(getAppClientNode());
			else if (evt.widget == ejbRadioButton && ejbRadioButton.getSelection())
				setSelectedNode(getEjbNode());
			else if (evt.widget == webRadioButton && webRadioButton.getSelection())
				setSelectedNode(getWebNode());
			else if (evt.widget == connectorRadioButton && connectorRadioButton.getSelection())
				setSelectedNode(getConnectorNode());
			validatePage();
		}
		super.handleEvent(evt);
	}

	/**
	 *  
	 */
	private void handleDefaultModulesButtonPressed() {
		if (defaultModulesButton.getSelection()) {
			setSelectedNode(null);
			showDefaultModulesComposite();
		} else
			showNewModulesCompsite();
		setButtonEnablement();
		validatePage();
	}

	private void showDefaultModulesComposite() {
		defaultModulesComposite.setVisible(true);
		newModulesComposite.setVisible(false);
		stackLayout.topControl = defaultModulesComposite;
	}

	/**
	 * This is done based on the J2EE version. We need to disable Connectors if not j2ee 1.3 or
	 * higher.
	 */
	private void setButtonEnablement() {
		if (!defaultModulesButton.getSelection() && connectorRadioButton != null) {
			int version = getDefaultModel().getIntProperty(DefaultModuleProjectCreationDataModel.J2EE_VERSION);
			connectorRadioButton.setEnabled(version > J2EEVersionConstants.J2EE_1_2_ID);
		}
	}

	/**
	 *  
	 */
	private void showNewModulesCompsite() {
		defaultModulesComposite.setVisible(false);
		newModulesComposite.setVisible(true);
		if (!isAnyModuleRadioSelected())
			appClientRadioButton.setSelection(true);
		setSelectedNode(getWizardNodeFromSelection());
		stackLayout.topControl = newModulesComposite;
	}

	/**
	 * @return
	 */
	private GenericWizardNode getWizardNodeFromSelection() {
		if (appClientRadioButton.getSelection())
			return getAppClientNode();
		if (connectorRadioButton != null && connectorRadioButton.getSelection())
			return getConnectorNode();
		if (ejbRadioButton != null && ejbRadioButton.getSelection())
			return getEjbNode();
		if (webRadioButton != null && webRadioButton.getSelection())
			return getWebNode();
		return null;
	}

	/**
	 * @return
	 */
	private boolean isAnyModuleRadioSelected() {
		return appClientRadioButton.getSelection() || (connectorRadioButton != null && connectorRadioButton.getSelection()) || (ejbRadioButton != null && ejbRadioButton.getSelection()) || (webRadioButton != null && webRadioButton.getSelection());
	}

	/**
	 * @return Returns the appClientNode.
	 */
	private GenericWizardNode getAppClientNode() {
		if (appClientNode == null) {
			appClientNode = new GenericWizardNode() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.GenericWizardNode#createWizard()
				 */
				protected IWizard createWizard() {
					return new AppClientModuleCreationWizard(getDefaultModel().getClientModel());
				}
			};
		}
		return appClientNode;
	}

	/**
	 * @return Returns the connectorNode.
	 */
	private GenericWizardNode getConnectorNode() {
		if (connectorNode == null) {
			connectorNode = new GenericWizardNode() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.GenericWizardNode#createWizard()
				 */
				protected IWizard createWizard() {
					IWizard result = null;

					IWizardRegistry newWizardRegistry = WorkbenchPlugin.getDefault().getNewWizardRegistry();
					IWizardDescriptor descriptor = newWizardRegistry.findWizard("org.eclipse.jst.j2ee.internal.internal.jca.ui.internal.wizard.JCAProjectWizard"); //$NON-NLS-1$
					try {
						result = descriptor.createWizard();
					} catch (CoreException ce) {
						Logger.getLogger().log(ce);
					}
					return result;
				}
			};
		}
		return connectorNode;
	}

	/**
	 * @return Returns the ejbNode.
	 */
	private GenericWizardNode getEjbNode() {
		if (ejbNode == null) {
			ejbNode = new GenericWizardNode() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.GenericWizardNode#createWizard()
				 */
				protected IWizard createWizard() {
					IWizard result = null;

					IWizardRegistry newWizardRegistry = WorkbenchPlugin.getDefault().getNewWizardRegistry();
					IWizardDescriptor descriptor = newWizardRegistry.findWizard("org.eclipse.jst.j2ee.internal.internal.ejb.ui.internal.wizard.EJBProjectWizard"); //$NON-NLS-1$
					try {
						result = descriptor.createWizard();
					} catch (CoreException ce) {
						Logger.getLogger().log(ce);
					}
					return result;
				}
			};
		}
		return ejbNode;
	}

	/**
	 * @return Returns the webNode.
	 */
	private GenericWizardNode getWebNode() {
		if (webNode == null) {
			webNode = new GenericWizardNode() {
				/*
				 * (non-Javadoc)
				 * 
				 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.GenericWizardNode#createWizard()
				 */
				protected IWizard createWizard() {
					IWizard result = null;
					IWizardRegistry newWizardRegistry = WorkbenchPlugin.getDefault().getNewWizardRegistry();
					IWizardDescriptor servletWizardDescriptor = newWizardRegistry.findWizard("org.eclipse.jst.servlet.ui.internal.wizard.WEBProjectWizard"); //$NON-NLS-1$
					try {
						result = servletWizardDescriptor.createWizard();
					} catch (CoreException ce) {
						Logger.getLogger().log(ce);
					}
					return result;
				}
			};
		}
		return webNode;
	}

	/**
	 * @param selectedNode
	 *            The selectedNode to set.
	 */
	private void setSelectedNode(GenericWizardNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#canFlipToNextPage()
	 */
	public boolean canFlipToNextPage() {
		if (!defaultModulesButton.getSelection())
			return selectedNode != null;
		return false;
	}

	/**
	 * The <code>WizardSelectionPage</code> implementation of this <code>IWizardPage</code>
	 * method returns the first page of the currently selected wizard if there is one.
	 */
	public IWizardPage getNextPage() {
		if (selectedNode == null)
			return null;
		IPluginContribution pluginContribution = new IPluginContribution() {
			public String getLocalId() {
				String id = null;
				if (selectedNode == appClientNode) {
					id = "org.eclipse.jst.j2ee.internal.internal.internal.appclientProjectWizard"; //$NON-NLS-1$
				} else if (selectedNode == ejbNode) {
					id = "org.eclipse.jst.j2ee.internal.internal.internal.ejb.ui.util.ejbProjectWizard"; //$NON-NLS-1$
				} else if (selectedNode == connectorNode) {
					id = "org.eclipse.jst.j2ee.internal.internal.internal.jcaProjectWizard"; //$NON-NLS-1$
				} else if (selectedNode == webNode) {
					id = "org.eclipse.jst.j2ee.internal.internal.internal.webProjectWizard"; //$NON-NLS-1$
				}
				return id;
			}

			public String getPluginId() {
				return "org.eclipse.jst.j2ee.internal.internal.internal.ui"; //$NON-NLS-1$
			}
		};

		if (!WorkbenchActivityHelper.allowUseOf(pluginContribution)) {
			return null;
		}

		boolean isCreated = selectedNode.isContentCreated();
		IWizard wizard = selectedNode.getWizard();
		if (wizard == null) {
			setSelectedNode(null);
			return null;
		}
		if (!isCreated) // Allow the wizard to create its pages
			wizard.addPages();

		return wizard.getStartingPage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#validatePage()
	 */
	protected void validatePage() {
		if (!defaultModulesButton.getSelection()) {
			setPageComplete(false);
			setErrorMessage(null);
		} else
			super.validatePage();
	}


}