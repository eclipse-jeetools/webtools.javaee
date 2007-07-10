package org.eclipse.wtp.j2ee.headless.tests.utility.verifiers;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wtp.j2ee.headless.tests.j2ee.verifiers.ModuleProjectCreationDataModelVerifier;

/**
 * @author itewk
 */
public class UtilityProjectCreationDataModelVerifier extends ModuleProjectCreationDataModelVerifier {	
	@Override
	public void verify(IDataModel model) throws Exception {
		this.hasModelProvider = false;
		
		super.verify(model);
	}

	@Override
	protected IFile getDDFile() {
		//Utility projects don't have DDs
		return null;
	}

	@Override
	protected void setFacetProjectType() {
		this.facetProjectType = J2EEProjectUtilities.UTILITY;
	}

	@Override
	protected void verifyDD(Object modelObj) {
		//Utility projects don't have DDs
		return;
	}
}
