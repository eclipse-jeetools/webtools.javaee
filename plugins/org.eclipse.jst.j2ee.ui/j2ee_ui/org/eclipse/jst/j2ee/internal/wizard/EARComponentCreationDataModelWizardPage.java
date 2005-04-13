package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentCreationDataModelWizardPage extends J2EEComponentCreationDataModelWizardPage {

	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	
	/**
	 * @param model
	 * @param pageName
	 */
	public EARComponentCreationDataModelWizardPage(IDataModel  model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
	}

	//TODO: utility to handle additions
	protected String[] getValidationPropertyNames() {
		return new String[]{IComponentCreationDataModelProperties.PROJECT_NAME, 
				IComponentCreationDataModelProperties.COMPONENT_NAME, 
                IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION};
	}
}