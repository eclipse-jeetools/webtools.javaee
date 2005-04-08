package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.ui.DataModelWizard;

public class EARComponentCreationDataModelWizard extends DataModelWizard implements IExecutableExtension, INewWizard {
	
	/**
	 * <p>
	 * The Wizard ID of the EARComponentCreationWizard. Used for internal purposes and activities management.
	 * </p>
	 */
	public static final String WIZARD_ID =  EARComponentCreationWizard.class.getName();
	protected static final String MAIN_PG = "main"; //$NON-NLS-1$
	protected static final String SECOND_PG = "second"; //$NON-NLS-1$
	 
	
	/**
	 * <p>
	 * The default constructor. Creates a wizard with no selection, 
	 * no model instance, and no operation instance. The model and 
	 * operation will be created as needed.
	 * </p>
	 */
	public EARComponentCreationDataModelWizard() {
		super();
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
		setWindowTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_WIZ_TITLE));
		setDefaultPageImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
	} 
	 
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Adds a {@link JCAProjectCreationPage} as the {@link J2EEComponentCreationWizard#MAIN_PG}.
	 * </p>
	 */
	protected void doAddPages() {
		EARComponentCreationDataModelWizardPage page1 = new EARComponentCreationDataModelWizardPage(getDataModel(), MAIN_PG);
//		page.setInfopopID("org.eclipse.jst.j2ee.ui.webw1000"); //$NON-NLS-1$
		addPage(page1);
		EARComponentCreationSecondWizardPage page2 = new EARComponentCreationSecondWizardPage(getDataModel(), SECOND_PG);
		addPage(page2);
		super.doAddPages();
	}

	protected IDataModelProvider getDefaultProvider() {
		return new EarComponentCreationDataModelProvider();
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	} 

}