/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import java.util.Map;

import org.eclipse.jst.j2ee.application.internal.operations.AppClientModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleExportDataModel;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.jca.verifiers.JCAExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebExportDataModelVerifier;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class DataModelVerifierFactory extends org.eclipse.wst.common.tests.DataModelVerifierFactory {

	public static org.eclipse.wst.common.tests.DataModelVerifierFactory getInstance() {
		org.eclipse.wst.common.tests.DataModelVerifierFactory fact = org.eclipse.wst.common.tests.DataModelVerifierFactory.getInstance();
		initJ2EEDataModelVerifiersMap(fact.getDataModelVerifiersMap());
		return fact;
	}

	/**
	 * @return Returns the dataModelVerifiersMap.
	 */
	protected static void initJ2EEDataModelVerifiersMap(Map verifierMap) {
		
			//verifierMap.put(EJBModuleImportDataModel.class.getName(), EJBImportDataModelVerifier.class.getName());
			//verifierMap.put(ConnectorModuleImportDataModel.class.getName(),JCAImportDataModelVerifier.class.getName());
			//verifierMap.put(WebModuleImportDataModel.class.getName(), WebImportDataModelVerifier.class.getName());
			//verifierMap.put(AppClientModuleImportDataModel.class.getName(), AppClientImportDataModelVerifier.class.getName());
			
			verifierMap.put(EJBModuleExportDataModel.class.getName(), EJBExportDataModelVerifier.class.getName());
			verifierMap.put(JCAExportDataModelVerifier.class.getName(), JCAExportDataModelVerifier.class.getName());
			verifierMap.put(WebModuleExportDataModel.class.getName(), WebExportDataModelVerifier.class.getName());
			verifierMap.put(AppClientModuleExportDataModel.class.getName(), AppClientExportDataModelVerifier.class.getName());
//TODO: add verifiers for new IDataModels			
//			verifierMap.put(WebComponentCreationDataModel.class.getName(), WebProjectCreationDataModelVerifier.class.getName());
//			verifierMap.put(EARComponentCreationDataModel.class.getName(), EARProjectCreationDataModelVerifier.class.getName());
//			verifierMap.put(EjbComponentCreationDataModel.class.getName(), EJBProjectCreationDataModelVerifier.class.getName());
//			verifierMap.put(AppClientComponentCreationDataModel.class.getName(),AppClientProjectCreationDataModelVerifier.class.getName());
			
//			verifierMap.put(FlexibleProjectCreationDataModel.class.getName(),FlexibleProjectCreationDataModelVerifier.class.getName());
//			verifierMap.put(FlexibleJavaProjectCreationDataModel.class.getName(),FlexibleProjectCreationDataModelVerifier.class.getName());

	}

}
