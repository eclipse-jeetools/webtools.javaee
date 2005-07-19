/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEComponentSaveStrategyImpl;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebComponentSaveStrategyImpl extends J2EEComponentSaveStrategyImpl {

	public WebComponentSaveStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public void save(File aFile, FileIterator iterator) throws SaveFailureException {
		if (aFile.isArchive() && operationHandlesNested((Archive) aFile)) {
			return;
		}
		super.save(aFile, iterator);
	}

	protected boolean operationHandlesNested(Archive archive) {
		if (null != dataModel) {
			List list = (List) dataModel.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_ARCHIVES_SELECTED);
			return list.contains(archive);
		}
		return false;
	}

	protected String getImportedClassesURI(File aFile) {
		String uri = aFile.getURI();
		return WTProjectStrategyUtils.makeRelative(uri, ArchiveConstants.WEBAPP_CLASSES_URI);
	}

	protected IPath getImportedClassesRuntimePath() {
		return new Path("/" + ArchiveConstants.WEBAPP_CLASSES_URI);
	}

}
