/*
 * Created on Mar 23, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.application.operations.DefaultJ2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.earcreation.DefaultJ2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.ui.WTPWizard;

public class DefaultJ2EEComponentCreationWizard extends WTPWizard {
	private static final String SELECTION_PG = "selection"; //$NON-NLS-1$

	/**
	 * @param model
	 */
	public DefaultJ2EEComponentCreationWizard(DefaultJ2EEComponentCreationDataModel model) {
		super(model);
		initialize();
	}

	/**
	 *  
	 */
	public DefaultJ2EEComponentCreationWizard() {
		super();
		initialize();
	}

	/**
	 *  
	 */
	private void initialize() {
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.DEFAULT_COMPONENT_WIZ_TITLE));
		String iconPath = "icons/full/"; //$NON-NLS-1$
		try {
			URL installURL = IDEWorkbenchPlugin.getDefault().getDescriptor().getInstallURL();
			URL url = new URL(installURL, iconPath + "wizban/new_wiz.gif"); //$NON-NLS-1$
			ImageDescriptor desc = ImageDescriptor.createFromURL(url);
			setDefaultPageImageDescriptor(desc);
		} catch (MalformedURLException e) {
			// Should not happen. Ignore.
		}
		setNeedsProgressMonitor(true);
		setForcePreviousAndNextButtons(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createDefaultModel()
	 */
	protected WTPOperationDataModel createDefaultModel() {
		return new DefaultJ2EEComponentCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizard#createOperation()
	 */
	protected WTPOperation createBaseOperation() {
		if (model.getBooleanProperty(DefaultJ2EEComponentCreationDataModel.ENABLED))
			return new DefaultJ2EEComponentCreationOperation((DefaultJ2EEComponentCreationDataModel) model);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	public void doAddPages() {
		addPage(new NewJ2EEComponentSelectionPage((DefaultJ2EEComponentCreationDataModel) model, SELECTION_PG));
	}

	public boolean canFinish() {
		if (!super.canFinish()) {
			return false;
		}
		return model.getBooleanProperty(DefaultJ2EEComponentCreationDataModel.ENABLED);
	}
}