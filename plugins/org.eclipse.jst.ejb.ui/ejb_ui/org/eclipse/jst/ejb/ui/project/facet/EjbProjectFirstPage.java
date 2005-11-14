package org.eclipse.jst.ejb.ui.project.facet;

import org.eclipse.jst.ejb.ui.internal.util.EJBUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentFacetCreationWizardPage;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbProjectFirstPage extends J2EEComponentFacetCreationWizardPage {

	protected String getModuleFacetID() {
		return J2EEProjectUtilities.EJB;
	}

	public EjbProjectFirstPage(IDataModel model, String pageName) {
		super(model, pageName);
		setTitle(EJBUIMessages.EJB_PROJECT_MAIN_PG_TITLE);
		setDescription(EJBUIMessages.EJB_PROJECT_MAIN_PG_DESC);
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EJB_PROJECT_WIZARD_BANNER));
	}

}
