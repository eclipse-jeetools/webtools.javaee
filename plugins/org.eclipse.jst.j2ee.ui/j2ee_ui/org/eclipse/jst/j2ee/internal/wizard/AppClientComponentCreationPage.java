/*
 * Created on Mar 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EECreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;

public class AppClientComponentCreationPage extends J2EEModuleCreationPage {

	public Text contextRootNameField = null;
	public Label contextRootLabel = null;

	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	private AnnotationsStandaloneGroup annotationsGroup;
	
	/**
	 * @param model
	 * @param pageName
	 */
	public AppClientComponentCreationPage(AppClientComponentCreationDataModel  model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APPCLIENT_COMPONENT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APPCLIENT_COMPONENT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.ui.wizard.J2EEModuleCreationPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected void addToAdvancedComposite(Composite advanced) {
		super.addToAdvancedComposite(advanced);
		createAnnotationsGroup(advanced);
	}

	private void createAnnotationsGroup(Composite parent) {
		annotationsGroup = new AnnotationsStandaloneGroup(parent, getJ2EEModuleCreationDataModel(), false);
	}

	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
		return new String[]{
				J2EECreationDataModel.PROJECT_NAME, 
				J2EECreationDataModel.MODULE_NAME, 
				J2EECreationDataModel.J2EE_MODULE_VERSION, 
				WTPOperationDataModel.NESTED_MODEL_VALIDATION_HOOK, 
				J2EEComponentCreationDataModel.ADD_TO_EAR};
	}

	public void dispose() {
		super.dispose();
		if (annotationsGroup != null)
			annotationsGroup.dispose();
	}
}