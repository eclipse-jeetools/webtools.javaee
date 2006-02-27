/*
 * Created on May 20, 2004
 *
 */
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.Set;

import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;



/**
 * @author dfholt
 *
 */
public class EJBClientJarFileMoveDataModelProvider
 						extends AbstractDataModelProvider
 						implements IEJBClientJarFileMoveDataModelProperties{
    
	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EJB_CLIENTVIEW_PROJECT_NAME);
		propertyNames.add(EJB_PROJECT_NAME);
		propertyNames.add(FILES_TO_MOVE_MAP);
		return propertyNames;
	}
	

	public IDataModelOperation getDefaultOperation() {
		return new EJBClientJarFileMoveOperation( model );
	}

}
