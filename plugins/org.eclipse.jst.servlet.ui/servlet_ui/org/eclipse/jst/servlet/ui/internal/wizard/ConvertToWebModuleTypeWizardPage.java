/*
 * Created on Jun 29, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.servlet.ui.internal.plugin.WEBUIMessages;
import org.eclipse.swt.widgets.Composite;

/**
 * @author fatty
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConvertToWebModuleTypeWizardPage extends WEBProjectCreationPage
{

	/**
	 * @param model
	 * @param pageName
	 */
	protected ConvertToWebModuleTypeWizardPage(
				WebModuleCreationDataModel model, String pageName)
	{
		super(model, pageName);
		// TODO Auto-generated constructor stub

		setDescription(WEBUIMessages.getResourceString(WEBUIMessages.WEB_CONVERT_MAIN_PG_DESC)); //$NON-NLS-1$
		setTitle(WEBUIMessages.getResourceString(WEBUIMessages.WEB_CONVERT_MAIN_PG_TITLE)); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.j2ee.ui.wizard.J2EEProjectCreationPage#createProjectNameGroup(org.eclipse.swt.widgets.Composite)
	 */
	protected void createProjectNameGroup(Composite parent)
	{
		super.createProjectNameGroup(parent);
		projectNameGroup.projectNameField.setEnabled(false);
	}
}