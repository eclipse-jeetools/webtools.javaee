/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.util.List;

import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.datamodel.properties.IEARComponentImportDataModelProperties;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentArchiveSaveAdapter extends ComponentArchiveSaveAdapter {


	public EARComponentArchiveSaveAdapter(IVirtualComponent component) {
		super(component);
	}

	public void setDataModel(IDataModel dataModel) {
		super.setDataModel(dataModel);
	}

	
	protected SubProgressMonitor subMonitor() {
		return new SubProgressMonitor(progressMonitor, 10);
	}

	public void setMonitor(org.eclipse.core.runtime.IProgressMonitor newMonitor) {
		progressMonitor = newMonitor;
	}

	protected boolean shouldSave(IArchiveResource aRes) {
	
		// TODO: go back and revisit it in the future.
		
		/*if (aRes.getType() == IArchiveResource.ARCHIVE_TYPE) {
		//	return getFilter().shouldSave(aFile.getURI(), getArchive());
		}*/
		return super.shouldSave(aRes);
	}

	protected boolean shouldLinkAsComponentRef(Archive archive) {
		if (null != dataModel) {
			List list = (List) dataModel.getProperty(IEARComponentImportDataModelProperties.HANDLED_PROJECT_MODELS_LIST);
			for (int i = 0; i < list.size(); i++) {
				if (archive == ((IDataModel) list.get(i)).getProperty(IEARComponentImportDataModelProperties.FILE)) {
					return false;
				}
			}
		}
		return true;
	}

	/*protected boolean shouldSave(String uri) {
		if (overwriteHandler != null) {
			if (overwriteHandler.isOverwriteNone())
				return false;
			return ((super.shouldSave(uri)) && (overwriteHandler.isOverwriteAll() || overwriteHandler.isOverwriteResources() || overwriteHandler.shouldOverwrite(uri)));
		}
		return true;
	}*/




}
