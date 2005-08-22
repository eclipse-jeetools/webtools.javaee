/*
 * Created on Mar 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.wizard;

import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class AppClientComponentCreationWizardPage extends J2EEComponentCreationWizardPage {

	public Text contextRootNameField = null;
	public Label contextRootLabel = null;

//	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	private AnnotationsStandaloneGroup annotationsGroup;
	
	/**
	 * @param model
	 * @param pageName
	 */
	public AppClientComponentCreationWizardPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.APPCLIENT_COMPONENT_MAIN_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.APPCLIENT_COMPONENT_MAIN_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.APP_CLIENT_PROJECT_WIZARD_BANNER));
		setInfopopID(IJ2EEUIContextIds.NEW_APPCLIENT_WIZARD_P1);
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
		//annotationsGroup = new AnnotationsStandaloneGroup(parent, getJ2EEModuleCreationDataModel(), false);
	}

	protected String[] getValidationPropertyNames() {
        return super.getValidationPropertyNames();
    }

	public void dispose() {
		super.dispose();
		if (annotationsGroup != null)
			annotationsGroup.dispose();
	}
}