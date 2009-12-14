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
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyManifestUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.componentcore.export.AbstractExportParticipant;
import org.eclipse.wst.common.componentcore.export.ExportModelUtil;
import org.eclipse.wst.common.componentcore.export.ExportableFile;
import org.eclipse.wst.common.componentcore.export.ExportableFolder;
import org.eclipse.wst.common.componentcore.export.IExportableFolder;
import org.eclipse.wst.common.componentcore.export.IExportableResource;
import org.eclipse.wst.common.componentcore.export.ExportModel.ExportTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * This class is solely responsible for replacing manifest.mf files
 * which require updating before being exported
 * 
 * @author rob
 *
 */
public class ReplaceManifestExportParticipant extends AbstractExportParticipant {
	protected static final IPath MANIFEST_PATH = new Path(J2EEConstants.MANIFEST_URI);
	private List<String> javaClasspathURIs = null;
	
	@Override
	public void finalize(IVirtualComponent component,
			ExportTaskModel dataModel, List<IExportableResource> resources) {
		forceUpdate(component, dataModel, resources);
	}
	
	public void forceUpdate(IVirtualComponent component,
			ExportTaskModel dataModel, List<IExportableResource> resources) {
		if( !getClasspathURIs(component).isEmpty()) {
			// find the old manifest
			IExportableFolder parent = (ExportableFolder)ExportModelUtil.getExistingModuleResource(resources, new Path(J2EEConstants.META_INF));
			IExportableResource[] children = parent.members();
			IFile original = null;
			int originalIndex = 0;
			for( int i = 0; i < children.length; i++) {
				if( children[i].getName().equals(J2EEConstants.MANIFEST_SHORT_NAME)) {
					original = (IFile)children[i].getAdapter(IFile.class);
					originalIndex = i;
					File newManifest = getNewManifest(component.getProject(), original, javaClasspathURIs);
					ExportableFile newManifestExportable = new ExportableFile(newManifest, newManifest.getName(), new Path(J2EEConstants.META_INF));
					children[originalIndex] = newManifestExportable;
					parent.setMembers(children);
					return;
				}
			}
		}
	}

	/**
	 * Return whichever File is the new one, even if it's the same as the old one
	 * @return
	 */
	public static File getNewManifest(IProject project, IFile originalManifest, List<String> javaClasspathURIs) {
		final IPath workingLocation = project.getWorkingLocation(J2EEPlugin.PLUGIN_ID);
		// create path to temp MANIFEST.MF 
		final IPath tempManifestPath = workingLocation.append(MANIFEST_PATH);
		final File tempFile = tempManifestPath.toFile();
		if (!tempFile.exists()) {
			// create parent dirs for temp MANIFEST.MF
			final File parent = tempFile.getParentFile();
			if (!parent.exists()) {
				if (!parent.mkdirs()) {
					return originalManifest != null ? originalManifest.getLocation().toFile() : null;
				}
			}
		}
		try {	
			ClasspathDependencyManifestUtil.updateManifestClasspath(originalManifest, javaClasspathURIs, tempFile);
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
		return tempFile.exists() ? tempFile :  
			originalManifest != null ? originalManifest.getLocation().toFile() : null;
	}
	
	
	protected List<String> getClasspathURIs(IVirtualComponent component) {
		if( javaClasspathURIs == null ) {
			javaClasspathURIs = loadClasspathURIs(component);
		}
		return javaClasspathURIs;
	}
	
	public static List<String> loadClasspathURIs(IVirtualComponent component) {
		ArrayList<String> uris = new ArrayList<String>();
		uris = new ArrayList<String>();
		if (component instanceof J2EEModuleVirtualComponent) {
			final J2EEModuleVirtualComponent j2eeComp = (J2EEModuleVirtualComponent) component;
			final IVirtualReference[] refs = j2eeComp.getJavaClasspathReferences();
			if (refs != null) {
				for (int i = 0; i < refs.length; i++) {
					if (refs[i].getRuntimePath().equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
						uris.add(refs[i].getArchiveName());
					}
				}
			}
		}
		return uris;
	}
	
	
}
