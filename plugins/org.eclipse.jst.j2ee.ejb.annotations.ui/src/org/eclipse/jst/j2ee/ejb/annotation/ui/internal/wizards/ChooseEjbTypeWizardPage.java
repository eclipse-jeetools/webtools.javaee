/***************************************************************************************************
 * Copyright (c) 2005, 2006 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 *				 David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 **************************************************************************************************/


package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.ejb.annotation.internal.messages.IEJBAnnotationConstants;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBeanClassDataModelProperties;
import org.eclipse.jst.j2ee.ejb.annotation.internal.preferences.AnnotationPreferenceStore;
import org.eclipse.jst.j2ee.ejb.annotation.internal.provider.IAnnotationProvider;
import org.eclipse.jst.j2ee.ejb.annotation.internal.utility.AnnotationUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;

/**
 * @author naci
 */
public class ChooseEjbTypeWizardPage extends DataModelWizardPage {

	protected Button sessionType;
	protected Button messageDrivenType;
	protected Button containerManagedEntityType;

	protected ChooseEjbTypeWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		this.setDescription(IEJBAnnotationConstants.ADD_EJB_WIZARD_PAGE_DESC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[] { IEnterpriseBeanClassDataModelProperties.EJB_TYPE };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite aComposite = new Composite(parent, SWT.NULL);
		aComposite.setLayout(new GridLayout());
		aComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		aComposite.setSize(aComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		aComposite.setFont(parent.getFont());
		createEjbTypeGroup(aComposite);
		createAnnotationProviderGroup(aComposite);
		addPreferenceLink(aComposite);

		setControl(aComposite);
		Dialog.applyDialogFont(parent);
		return aComposite;

	}

	protected void createEjbTypeGroup(Composite parent) {
		Composite ejbTypeGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		ejbTypeGroup.setLayout(layout);
		ejbTypeGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		sessionType = new Button(ejbTypeGroup, SWT.RADIO);
		sessionType.setText(Messages.label_session_bean); //$NON-NLS-1$
		sessionType.setSelection(true);
		sessionType.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (sessionType.getSelection()) {
					ChooseEjbTypeWizardPage.this.model.setProperty(IEnterpriseBeanClassDataModelProperties.EJB_TYPE, "SessionBean");
					validateProvider();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				this.widgetSelected(e);
			}
		});

		messageDrivenType = new Button(ejbTypeGroup, SWT.RADIO);
		messageDrivenType.setText(Messages.label_message_driven_bean); //$NON-NLS-1$
		messageDrivenType.setSelection(false);
		messageDrivenType.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (messageDrivenType.getSelection()) {
					ChooseEjbTypeWizardPage.this.model.setProperty(IEnterpriseBeanClassDataModelProperties.EJB_TYPE,
							"MessageDrivenBean");
					validateProvider();
				}
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				this.widgetSelected(e);
			}
		});


		containerManagedEntityType = new Button(ejbTypeGroup, SWT.RADIO);
		containerManagedEntityType.setText(Messages.label_container_managed_entity_bean); //$NON-NLS-1$
		containerManagedEntityType.setSelection(false);
		containerManagedEntityType
				.addSelectionListener(new SelectionListener() {

					public void widgetSelected(SelectionEvent e) {
						if (containerManagedEntityType.getSelection()) {
							ChooseEjbTypeWizardPage.this.model
									.setProperty(
											IEnterpriseBeanClassDataModelProperties.EJB_TYPE,
											"ContainerManagedEntityBean");
							validateProvider();
						}
					}

					public void widgetDefaultSelected(SelectionEvent e) {
						this.widgetSelected(e);
					}
				});
	}

	protected void createAnnotationProviderGroup(Composite parent) {
		Composite annotationGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		annotationGroup.setLayout(layout);
		annotationGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

		//Label label = new Label(annotationGroup, SWT.WRAP);
		//label.setText("Annotation Provider:");
		//label.setToolTipText("Choose the annotation provider that will be used to create java classes and J2EE artifacts");

		// annotationProvider = new Combo(annotationGroup, SWT.RADIO);
		String preferred = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
		ChooseEjbTypeWizardPage.this.model.setProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER, preferred);
		// String providerS = provider[0];
		// for (int i = 0; i < provider.length; i++) {
		// String name = provider[i];
		// annotationProvider.add(name);
		// if (preferred.equals(name)) {
		// providerS = name;
		// annotationProvider.select(i);
		// selected = true;
		// }
		//
		// }
		// if (!selected) {
		// providerS = provider[0];
		// annotationProvider.select(0);
		// }

		validateProvider();

		if (model != null)
			model.setProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER, preferred);

	}

	public String getEJBType() {
		return model.getStringProperty(IEnterpriseBeanClassDataModelProperties.EJB_TYPE);
	}

	public boolean isPageComplete() {
		String provider = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
		IAnnotationProvider annotationProvider = null;
		try {
			annotationProvider = AnnotationUtilities.findAnnotationProviderByName(provider);
		} catch (Exception e) {
			return false;
		}

		return (annotationProvider != null && annotationProvider.isValid());
	}

	private void addPreferenceLink(final Composite composite) {
		/*
		 * TODO: Bug 161150
		 * 
		 * If there's no preference mapping, don't link to a nonexistant page?
		 * Perhaps add a combo here, letting the user select from the list of 
		 * annotation providers, with a button next to it allowing them to edit 
		 * that provider's preferences? 
		 * 
		 * Would we need eventually project-specific preferences? I don't know. 
		 */
		if( getPreferencePageId() != null ) {
			Link link = new Link(composite, SWT.NONE);
			link.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false, 3, 1));
			link.setText(Messages.label_change_your_provider_preference);
	
			link.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					if (showPreferencePage(composite)) {
					}
					validateProvider();
				}
	
				public void widgetDefaultSelected(SelectionEvent e) {
					widgetSelected(e);
				}
			});
		}
	}

	protected boolean showPreferencePage(Composite composite) {
		return PreferencesUtil.createPreferenceDialogOn(composite.getShell(), getPreferencePageId(), null, null)
				.open() == Window.OK;
	}
	
	private String getPreferencePageId() {
		String provider = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
		IConfigurationElement[] configurationElements = Platform.getExtensionRegistry().getConfigurationElementsFor(
		"org.eclipse.jst.j2ee.ejb.annotations.ui.ProviderPreferenceMapping");
		
		for( int i = 0; i < configurationElements.length; i++ ) {
			if( provider.equals(configurationElements[i].getAttribute("name"))) {
				return configurationElements[i].getAttribute("preferencePage");
			}
		}
		
		return null;
	}

	private void validateProvider() {
		String provider = AnnotationPreferenceStore.getProperty(AnnotationPreferenceStore.ANNOTATIONPROVIDER);
		ChooseEjbTypeWizardPage.this.model.setProperty(IEnterpriseBeanClassDataModelProperties.ANNOTATIONPROVIDER, provider);
		IAnnotationProvider annotationProvider = null;
		try {
			annotationProvider = AnnotationUtilities.findAnnotationProviderByName(provider);
		} catch (Exception ex) {
		}
		if (annotationProvider != null && annotationProvider.isValid()) {
			this.setErrorMessage(null);
			this.setPageComplete(true);
		} else
			this.setErrorMessage(Messages.msg_err_annotation_provider_not_valid);
		getContainer().updateMessage();
	}

	public boolean canFlipToNextPage() {
		return isPageComplete();// &&
		// wizard.getPageGroupManager().hasNextPage();
	}

}
