/*
 * Created on Jan 5, 2004
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.application.internal.operations.AppClientModuleExportDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleProjectCreationDataModel;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientModuleImportDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EJBModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorModuleImportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleImportDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers.EJBProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.DataModelVerifier.FlexibleProjectCreationDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.jca.verifiers.JCAExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.jca.verifiers.JCAImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebExportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebImportDataModelVerifier;
import org.eclipse.wtp.j2ee.headless.tests.web.verifiers.WebProjectCreationDataModelVerifier;

/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class DataModelVerifierFactory {

	private Map dataModelVerifiersMap = null;
	private DataModelVerifier defaultDataModelVerifier = new DataModelVerifier();
	
	private static final DataModelVerifierFactory instance = new DataModelVerifierFactory();

	public static DataModelVerifierFactory getInstance() {
		return instance;
	}

	public DataModelVerifier createVerifier(WTPOperationDataModel model)  {
		DataModelVerifier verifier = getDefaultDataModelVerifier();
		String verifierClassName = null;
		if (model != null) {
			verifierClassName = (String) getDataModelVerifiersMap().get(model.getClass().getName());
			if (verifierClassName != null) {
				try {
					Class verifierClass = Class.forName(verifierClassName);
					verifier = (DataModelVerifier) verifierClass.newInstance();
				} catch (Exception e) { 
					verifier = getDefaultDataModelVerifier();
				}
			}
		}  
		return verifier;
	}

	/**
	 * @return Returns the dataModelVerifiersMap.
	 */
	protected Map getDataModelVerifiersMap() {
		if (dataModelVerifiersMap == null) {
			dataModelVerifiersMap = new HashMap();
			dataModelVerifiersMap.put(EJBModuleImportDataModel.class.getName(), EJBImportDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(ConnectorModuleImportDataModel.class.getName(),JCAImportDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(WebModuleImportDataModel.class.getName(), WebImportDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(AppClientModuleImportDataModel.class.getName(), AppClientImportDataModelVerifier.class.getName());
			
			dataModelVerifiersMap.put(EJBModuleExportDataModel.class.getName(), EJBExportDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(JCAExportDataModelVerifier.class.getName(), JCAExportDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(WebModuleExportDataModel.class.getName(), WebExportDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(AppClientModuleExportDataModel.class.getName(), AppClientExportDataModelVerifier.class.getName());
			
			dataModelVerifiersMap.put(WebComponentCreationDataModel.class.getName(), WebProjectCreationDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(EARComponentCreationDataModel.class.getName(), EARProjectCreationDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(EjbComponentCreationDataModel.class.getName(), EJBProjectCreationDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(AppClientComponentCreationDataModel.class.getName(),AppClientProjectCreationDataModelVerifier.class.getName());
			
			dataModelVerifiersMap.put(FlexibleProjectCreationDataModel.class.getName(),FlexibleProjectCreationDataModelVerifier.class.getName());
			dataModelVerifiersMap.put(FlexibleJavaProjectCreationDataModel.class.getName(),FlexibleProjectCreationDataModelVerifier.class.getName());
		}
		
		return dataModelVerifiersMap;
	}

	/**
	 * @return Returns the defaultDataModelVerifier.
	 */
	protected DataModelVerifier getDefaultDataModelVerifier() {
		return defaultDataModelVerifier;
	}

}
