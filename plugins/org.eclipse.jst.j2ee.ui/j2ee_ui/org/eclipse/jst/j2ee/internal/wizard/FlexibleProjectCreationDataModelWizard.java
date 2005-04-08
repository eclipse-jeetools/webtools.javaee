package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.ui.DataModelWizard;

public class FlexibleProjectCreationDataModelWizard extends DataModelWizard { 

	/**
	 * <p>
	 * Constant used to identify the key of the main page of the Wizard.
	 * </p>
	 */
	protected static final String MAIN_PG = "main"; //$NON-NLS-1$
	private IStructuredSelection selection;
	public static final String WIZARD_ID = FlexibleProjectCreationWizard.class.getName();

	/**
	 * <p>
	 * Creates a default instance of the wizard with no configuration data, no selection, and no
	 * operation data model.
	 * </p>
	 */
	public FlexibleProjectCreationDataModelWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate wizard controls and to collect data from the user. The model
	 * will eventually be used to run the operation, if the user does not cancel the Wizard.
	 * </p>
	 * 
	 * @param model
	 *            used to collect information and interface with the WTP Operation
	 */
	public FlexibleProjectCreationDataModelWizard(IDataModel model) {
		super(model);
	}

	/**
	 * <p>
	 * The selection is used to pre-populate values in the Wizard dialog controls.
	 * </p>
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 * 
	 * @param workbench
	 *            the current workbench parent of the wizard
	 * @param aSelection
	 *            the selection from the view used to start the wizard (if any)
	 */
	public final void init(IWorkbench workbench, IStructuredSelection aSelection) {
		setNeedsProgressMonitor(true);
		this.selection = aSelection;
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.FLEXIBLE_PROJECT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
		setHelpAvailable(false);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#addPages()
	 */
	protected void doAddPages() {
		addPage(new FlexibleProjectCreationDataModelWizardPage(getDataModel(), MAIN_PG));
	}

	protected IDataModelProvider getDefaultProvider() {
		return new FlexibleProjectCreationDataModelProvider();
	}
}