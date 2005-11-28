package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.project.facet.JavaUtilityProjectCreationOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbClientProjectCreationOperation
	extends JavaUtilityProjectCreationOperation
	implements IEjbClientProjectCreationDataModelProperties{

	public EjbClientProjectCreationOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return super.execute(monitor, info);
	}
}
