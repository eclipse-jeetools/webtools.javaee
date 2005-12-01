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
/*
 * Created on Jun 21, 2004 
 * @author jsholl
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;


/**
 * @author jsholl
 */
public interface ExtendedImportFactory {

	public Archive openArchive(ArchiveOptions archiveOptions, String uri) throws OpenFailureException;

	public void importModuleFile(IDataModel model, IProgressMonitor monitor) throws InvocationTargetException, InterruptedException;

	public int getSpecVersion(Archive archive);
}
