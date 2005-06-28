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
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.componentcore.util.ComponentUtilities;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.project.J2EEComponentUtilities;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EARComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	public EARComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}

	public List getFiles() {
		super.getFiles();
		addModulesAndUtilities();
		return filesList;
	}

	public void addModulesAndUtilities() {
		EARArtifactEdit earArtifactEdit = null;
		try {
			earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(vComponent);
			List components = earArtifactEdit.getComponentReferences();
			for (Iterator iterator = components.iterator(); iterator.hasNext();) {
				IVirtualReference reference = (IVirtualReference) iterator.next();
				IVirtualComponent referencedComponent = reference.getReferencedComponent();
				String componentTypeId = referencedComponent.getComponentTypeId();
				if (IModuleConstants.JST_APPCLIENT_MODULE.equals(componentTypeId) || IModuleConstants.JST_EJB_MODULE.equals(componentTypeId) || IModuleConstants.JST_WEB_MODULE.equals(componentTypeId) || IModuleConstants.JST_CONNECTOR_MODULE.equals(componentTypeId)) {
					ArtifactEdit componentArtifactEdit = null;
					try {
						componentArtifactEdit = ComponentUtilities.getArtifactEditForRead(referencedComponent);
						Archive archive = ((EnterpriseArtifactEdit) componentArtifactEdit).asArchive(exportSource);
						archive.setURI(earArtifactEdit.getModuleURI(referencedComponent));
						filesList.add(archive);
					} catch (OpenFailureException oe) {
						// TODO
					} finally {
						if (componentArtifactEdit != null) {
							componentArtifactEdit.dispose();
						}
					}
				} else if (IModuleConstants.JST_UTILITY_MODULE.equals(componentTypeId)) {
					try {
						String uri = referencedComponent.getName() + ".jar";
						Archive archive = J2EEComponentUtilities.asArchive(uri, referencedComponent, exportSource);
						filesList.add(archive);
					} catch (OpenFailureException e) {
						Logger.getLogger().logError(e);
					}
				}
			}

		} finally {
			if (null != earArtifactEdit) {
				earArtifactEdit.dispose();
			}
		}
	}
}
