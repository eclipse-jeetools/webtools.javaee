package org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;

public class AppClientEditAdapterFactory implements IAdapterFactory {

	public AppClientEditAdapterFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adapterType == AppClientArtifactEdit.ADAPTER_TYPE || adapterType == ArtifactEdit.ADAPTER_TYPE) {
			ArtifactEditModel editModel = (ArtifactEditModel) adaptableObject;
			if (editModel.getModuleType().equals(AppClientArtifactEdit.TYPE_ID))
				return new AppClientArtifactEdit((ArtifactEditModel) adaptableObject);
		}
		return null;
	}

	public Class[] getAdapterList() {
		return new Class[]{ArtifactEdit.class, AppClientArtifactEdit.class};
	}

}
