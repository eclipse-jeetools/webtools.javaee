/*
 * Created on Jun 28, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationOperation;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;

/**
 * @author fatty
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ConvertWebProjectDataModel extends WebModuleCreationDataModel
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.iwt.webproject.operations.WebProjectCreationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation()
	{
		// TODO Auto-generated method stub
		return new WebModuleCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.application.operations.J2EEModuleCreationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName)
	{
		if( EditModelOperationDataModel.PROJECT_NAME.equals(propertyName) )
		{
			return validateProjectName((String) getProperty(PROJECT_NAME));
		}
		return super.doValidateProperty(propertyName);
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.application.operations.J2EEModuleCreationDataModel#initProjectModel()
	 */
	public void initProjectModel()
	{
		// use JavaProjectConverstionDataModel instead of JavaProjectCreationDataModel
		// to avoid validating project name and location
		setProjectDataModel(new JavaProjectCreationDataModel());
	}
}