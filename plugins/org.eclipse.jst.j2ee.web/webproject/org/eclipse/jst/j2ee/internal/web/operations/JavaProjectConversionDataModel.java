/*
 * Created on Dec 1, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.jdt.internal.integration.JavaProjectCreationDataModel;

/**
 * @author dfholt
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JavaProjectConversionDataModel extends JavaProjectCreationDataModel {
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		if(propertyName.equals(PROJECT_NAME) || propertyName.equals(PROJECT_LOCATION))
			return OK_STATUS;
		return super.doValidateProperty(propertyName);
	}
}
