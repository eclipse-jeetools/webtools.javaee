/**
 * 
 */
package org.eclipse.jst.j2ee.ejb.annotation.ui.internal.wizards;

import org.eclipse.jst.j2ee.internal.wizard.NewJavaClassOptionsWizardPage;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;

/**
 * @author naci
 *
 */
public class NewEjbClassOptionsWizardPage extends NewJavaClassOptionsWizardPage {

	public NewEjbClassOptionsWizardPage(ArtifactEditOperationDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName, pageDesc, pageTitle);
	}
	
	public void refreshInterfaces(Object interfaces)
	{
		interfaceViewer.setInput(interfaces);
	}

}
