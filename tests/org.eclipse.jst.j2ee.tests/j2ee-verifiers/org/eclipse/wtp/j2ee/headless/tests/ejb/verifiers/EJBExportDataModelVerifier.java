/*
 * Created on Jan 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.wtp.j2ee.headless.tests.ejb.verifiers;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleExportDataModelVerifier;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class EJBExportDataModelVerifier extends ModuleExportDataModelVerifier {
	@Override
	public void verify(IDataModel model) throws Exception {
		super.verify(model);
	}
	
	@Override
	protected int getExportType() {
		return J2EEVersionConstants.EJB_TYPE;
	}
}
