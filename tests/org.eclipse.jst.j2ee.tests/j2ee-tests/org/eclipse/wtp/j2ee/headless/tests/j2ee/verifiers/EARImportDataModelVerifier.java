/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers;

import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EARImportDataModelVerifier extends J2EEImportDataModelVerifier {

    public void verify(WTPOperationDataModel model) throws Exception {
        super.verify(model);
//        EnterpriseApplicationImportDataModel importModel = (EnterpriseApplicationImportDataModel)model;
//        List list = importModel.getEARFile().getModuleFiles();
//        for (Iterator iter = list.iterator(); iter.hasNext();) {
//            ModuleFile element = (ModuleFile) iter.next();
//            String uri = element.getURI();
//            ProjectUtility.verifyProject(uri.substring(0, uri.indexOf(".")), true);
//        }
    }
    
}
