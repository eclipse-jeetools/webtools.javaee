/*******************************************************************************
 * Copyright (c) 2009 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.server.core.IJ2EEModule;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.IModuleResource;
import org.eclipse.wst.server.core.util.ModuleFile;
import org.eclipse.wst.server.core.util.ProjectModule;

public class BinaryFileModuleDelegate 
	/* SHOULD extend ModuleDelegate */
	extends ProjectModule 
	/* SHOULD NOT */ implements IJ2EEModule {
	
	private File file;
	
	/**
	 * Constructor for a simple binary single-file module
	 * @param file The file
	 */
	public BinaryFileModuleDelegate(File file) {
		this.file = file;
	}
	
	@Override
	public IStatus validate() {
		return Status.OK_STATUS;
	}

	@Override
	public IModule[] getChildModules() {
		return new IModule[]{};
	}

	@Override
	public IModuleResource[] members() throws CoreException {
		return new IModuleResource[]{
				new ModuleFile(file, file.getName(), new Path("")) //$NON-NLS-1$
		};
	}

	
	/*
	 * The following 3 methods are here solely to not break 
	 * generic server. 
	 * 
	 * These methods should be removed as soon as generic
	 * server stops assuming all child modules are 
	 * ProjectModule objects or IJ2EEModule objects,
	 * which are heavy assumptions. 
	 */
	@Override
	public IContainer[] getResourceFolders() {
		return new IContainer[0];
	}

	@Override
	public IContainer[] getJavaOutputFolders() {
		return new IContainer[0];
	}

	@Override
	public boolean isBinary() {
		return true;
	}

}
