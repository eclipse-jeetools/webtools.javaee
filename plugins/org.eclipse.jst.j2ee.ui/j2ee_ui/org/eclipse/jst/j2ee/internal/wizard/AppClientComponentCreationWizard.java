/*
 * Created on Mar 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.ui.J2EEArtifactCreationWizard;
import org.eclipse.jst.j2ee.ui.J2EEModuleCreationWizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

public class AppClientComponentCreationWizard extends J2EEModuleCreationWizard implements IExecutableExtension, INewWizard {
	
	/**
	 * <p>
	 * The Wizard ID of the EARComponentCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID =  AppClientComponentCreationWizard.class.getName();	 
	
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public AppClientComponentCreationWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls
	 * and interface with the operation.
	 * </p>
	 * @param model The model parameter is used to pre-populate wizard controls and interface with the operation
	 */
	public AppClientComponentCreationWizard(AppClientComponentCreationDataModel model) {
		super(model);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return a {@link WebProjectCreationDataModel} and defaults
	 * the value of {@link J2EEModuleCreationDataModelOld#ADD_TO_EAR} to <b>true</b>
	 * </p>
	 * 
	 * @return Returns the specific operation data model for the creation of J2EE Web modules
	 */
	protected WTPOperationDataModel createDefaultModel() {
		AppClientComponentCreationDataModel aModel = new AppClientComponentCreationDataModel();
		return aModel;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Overridden to return an {@link WebProjectCreationOperation}. 
	 * </p>
	 * 
	 * @return Returns the specific operation for the creation of J2EE Web modules
	 */
	protected WTPOperation createBaseOperation() {
		return new AppClientComponentCreationOperation(getSpecificDataModel());
	}

	/** 
	 * {@inheritDoc}   
	 * 
	 * <p>
	 * Sets up the dialog window title and default page image. 
	 * </p> 
	 * 
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	protected void doInit() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APPCLIENT_COMPONENT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	} 
	 
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@link JCAProjectCreationPage} as the {@link J2EEModuleCreationWizard#MAIN_PG}.
	 * </p>
	 */
	protected void doAddPages() {
		AppClientComponentCreationPage page1 = new AppClientComponentCreationPage(getSpecificDataModel(), MAIN_PG);
		addPage(page1);
		super.doAddPages();
	} 
	
	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.extensions.ExtendableWizard#getWizardID()
	 */
	public String getWizardID() {
		return WIZARD_ID;
	} 
	
	private AppClientComponentCreationDataModel getSpecificDataModel() {
		return (AppClientComponentCreationDataModel) getModel();
	}

}