package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public abstract class J2EEComponentCreationDataModelWizard extends J2EEArtifactCreationWizard {

	/**
	 * <p>
	 * Constant used to identify the key of the main page of the Wizard.
	 * </p>
	 */
	protected static final String MODULE_PG = "module"; //$NON-NLS-1$

	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, no model instance, and no
	 * operation instance. The model and operation will be created as needed.
	 * </p>
	 */
	public J2EEComponentCreationDataModelWizard() {
		super();
	}

	/**
	 * <p>
	 * The model is used to prepopulate the wizard controls and interface with the operation.
	 * </p>
	 * 
	 * @param model
	 *            The model parameter is used to pre-populate wizard controls and interface with the
	 *            operation
	 */
	public J2EEComponentCreationDataModelWizard(IDataModel model) {
		super(model);
	}

	/**
	 * <p>
	 * The selection stored in
	 * {@link J2EEArtifactCreationWizard#init(IWorkbench, IStructuredSelection)}is used to
	 * pre-populate the EAR project in the Wizard dialog controls.
	 * </p>
	 * 
	 * @see J2EEArtifactCreationWizard#init(IWorkbench, IStructuredSelection)
	 * @see J2EEArtifactCreationWizard#doInit()
	 */
	protected void doInit() {
		preFillSelectedEARProject();
	}

	/**
	 * <p>
	 * Subclasses which override this method should always call super.addModulesPageIfNecessary()
	 * ahead of before their own pages.
	 * </p>
	 *  
	 */
	protected void addModulesPageIfNecessary() {
		if (getDataModel().getBooleanProperty(IJ2EEComponentCreationDataModelProperties.UI_SHOW_EAR_SECTION)) {
			addPage(new J2EEModulesDependencyPage(getDataModel(), MODULE_PG));
		}
	}

	/**
	 * @inheritDoc
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	protected void doAddPages() {
		addModulesPageIfNecessary();
	}

	/**
	 * <p>
	 * Skips the page identified by the MODULE_PG name if
	 * {@link J2EEComponentCreationWizard#shouldShowModulesPage()}is false.
	 * </p>
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getPreviousPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getPreviousPage(IWizardPage page) {
		IWizardPage previous = super.getPreviousPage(page);
		if (previous != null && previous.getName().equals(MODULE_PG)) {
			if (!shouldShowModulesPage()) {
				previous = super.getPreviousPage(previous);
			}
		}
		return previous;
	}


	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Skips the page identified by the MODULE_PG name if
	 * {@link J2EEModuleProjectCreationWizard#shouldShowModulesPage()}is false.
	 * </p>
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#getNextPage(org.eclipse.jface.wizard.IWizardPage)
	 */
	public IWizardPage getNextPage(IWizardPage page) {
		IWizardPage next = super.getNextPage(page);
		if (next != null && next.getName().equals(MODULE_PG)) {
			if (!shouldShowModulesPage()) {
				next = super.getNextPage(next);
			}
		}
		return next;
	}

	/**
	 * <p>
	 * Uses the model to determine if the module page should be shown. The default is to AND the
	 * values of model.getBooleanProperty(J2EEModuleCreationDataModel.ADD_TO_EAR) and
	 * shouldShowModulesPageForEAR().
	 * </p>
	 * 
	 * @return Returns a boolean true if the module page should be shown.
	 */
	protected final boolean shouldShowModulesPage() {
		return getDataModel().getBooleanProperty(J2EEComponentCreationDataModel.ADD_TO_EAR) && shouldShowModulesPageForEAR();
	}

	/**
	 * <p>
	 * Uses the model to determine if the module page should be shown for the EAR. If no modules are
	 * present in the classpath selection of the model, the method will return false.
	 * </p>
	 * 
	 * @return true only if the
	 * @see J2EEModuleCreationDataModelOld#getClassPathSelection() is non-empty.
	 */
	protected final boolean shouldShowModulesPageForEAR() {
		//TODO is this what we want here?
		return true;
	}

	private void preFillSelectedEARProject() {
		WorkbenchComponent earModule = getSelectedEARModule();
		EARArtifactEdit earEdit = null;
		int j2eeVersion = 0;
		if (earModule != null && getDataModel() != null) {
			try {
				earEdit = EARArtifactEdit.getEARArtifactEditForRead(earModule);
				j2eeVersion = earEdit.getJ2EEVersion();
			} finally {
				if (earEdit != null)
					earEdit.dispose();
			}
            getDataModel().setIntProperty(ServerTargetDataModel.J2EE_VERSION_ID, j2eeVersion);
            getDataModel().setIntProperty(J2EEComponentCreationDataModel.J2EE_VERSION, j2eeVersion);
            getDataModel().setProperty(J2EEComponentCreationDataModel.EAR_MODULE_NAME, earModule.getName());
		}
	}
}