/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;


/**
 * Abstract implementer for archives that use xml deployment descriptors
 */
public abstract class XmlBasedImportStrategyImpl extends ImportStrategyImpl {
	/**
	 * XmlBasedImportStrategy constructor comment.
	 */
	public XmlBasedImportStrategyImpl() {
		super();
	}

	public ModuleFile getModuleFile() {
		return (ModuleFile) getArchive();
	}

	protected EObject primLoadDeploymentDescriptor() throws Exception {
		Resource resource = null;

		resource = getModuleFile().getDeploymentDescriptorResource();
		EObject root = null;

		root = ArchiveUtil.getRoot(resource);

		return root;
	}
}
