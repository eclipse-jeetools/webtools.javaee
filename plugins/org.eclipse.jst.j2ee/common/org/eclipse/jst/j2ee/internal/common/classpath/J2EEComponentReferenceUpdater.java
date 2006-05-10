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
package org.eclipse.jst.j2ee.internal.common.classpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class J2EEComponentReferenceUpdater {

	public static IPath WEBLIB = new Path("/WEB-INF/lib"); //$NON-NLS-1$

	/**
	 * Sets the references for modules in the list to only those defined in the MANIFEST.MF which
	 * can be resolved through the specified ear. Any existing references will be removed, except in
	 * the case of web apps; existing references mapping to WEB-INF/LIBS will be preserved.
	 * 
	 * @param earProject
	 * @param moduleComponents
	 */
	public static void updateReferences(IProject earProject) {
		EARArtifactEdit earEdit = null;
		try {
			J2EEComponentClasspathUpdater.getInstance().pauseUpdates();
			J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(earProject);
			earEdit = EARArtifactEdit.getEARArtifactEditForRead(earProject);
			if (earEdit != null) {
				IVirtualReference[] earRefs = earEdit.getComponentReferences();
				for (int i = 0; i < earRefs.length; i++) {
					IVirtualComponent moduleComponent = earRefs[i].getReferencedComponent();
					if (moduleComponent.isBinary()) {
						continue;
					}
					String[] manifestClasspath = null;
					IVirtualFile vManifest = moduleComponent.getRootFolder().getFile(J2EEConstants.MANIFEST_URI);
					if (vManifest.exists()) {
						IFile manifestFile = vManifest.getUnderlyingFile();
						try {
							ArchiveManifest manifest = new ArchiveManifestImpl(manifestFile.getContents());
							manifestClasspath = manifest.getClassPathTokenized();
						} catch (IOException e) {
							Logger.getLogger().logError(e);
						} catch (CoreException e) {
							Logger.getLogger().logError(e);
						}
					}

					if (manifestClasspath == null) {
						manifestClasspath = new String[0];
					}

					// list rather than array incase manifest has bogus entries
					List compRefs = new ArrayList();
					for (int j = 0; j < manifestClasspath.length; j++) {
						IVirtualComponent comp = earEdit.getModuleByManifestURI(manifestClasspath[j]);
						if (comp != null) {
							compRefs.add(ComponentCore.createReference(moduleComponent, comp));
						}
					}
					if (J2EEProjectUtilities.isDynamicWebProject(moduleComponent.getProject())) {
						IVirtualReference[] moduleRefs = moduleComponent.getReferences();
						for (int j = 0; j < moduleRefs.length; j++) {
							if (moduleRefs[j].getRuntimePath().equals(WEBLIB)) {
								compRefs.add(moduleRefs[j]);
							}
						}
					}

					IVirtualReference[] compRefsArray = new IVirtualReference[compRefs.size()];
					for (int j = 0; j < compRefsArray.length; j++) {
						compRefsArray[j] = (IVirtualReference) compRefs.get(j);
					}

					moduleComponent.setReferences(compRefsArray);
				}
			}
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			J2EEComponentClasspathUpdater.getInstance().resumeUpdates();
			if (earEdit != null)
				earEdit.dispose();
		}
	}

}
