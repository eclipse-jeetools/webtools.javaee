/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;


/**
 * @author cbridgha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EarEditAdapterFactory implements IAdapterFactory {

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		ArtifactEditModel editModel = (ArtifactEditModel)adaptableObject;
		if (editModel.getModuleType().equals(EARArtifactEdit.TYPE_ID))
			return new EARArtifactEdit((ArtifactEditModel)adaptableObject);
		else
			return null;
		
	}


	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		
		return new Class[] { ArtifactEditModel.class };
	}
}
