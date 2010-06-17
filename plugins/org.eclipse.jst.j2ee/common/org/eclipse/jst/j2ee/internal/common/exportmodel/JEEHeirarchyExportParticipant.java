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

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class JEEHeirarchyExportParticipant extends AbstractFlattenParticipant {
	public static final String JEE_VERSION = "org.eclipse.jst.j2ee.internal.deployables.export.eeVersion"; //$NON-NLS-1$
	public static String [] CHILDREN_EXTENSIONS = new String [] {IJ2EEModuleConstants.JAR_EXT, ".zip", IJ2EEModuleConstants.RAR_EXT, IJ2EEModuleConstants.WAR_EXT };  //$NON-NLS-1$
	public JEEHeirarchyExportParticipant() {
	}
	
	@Override
	public boolean isChildModule(IVirtualComponent rootComponent,
			IVirtualReference reference, FlatComponentTaskModel dataModel) {
		String parentType = JavaEEProjectUtilities.getJ2EEComponentType(rootComponent);
		String childType = JavaEEProjectUtilities.getJ2EEComponentType(reference.getReferencedComponent());
		return isApprovedNesting(parentType, childType, reference.getReferencedComponent().isBinary());
	}
	
	protected boolean isPossibleChild(String name) {
		for( int i = 0; i < CHILDREN_EXTENSIONS.length; i++ ) {
			if( name.endsWith(CHILDREN_EXTENSIONS[i]))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean isChildModule(IVirtualComponent rootComponent, FlatComponentTaskModel dataModel, IFlatFile file) {
		if (isPossibleChild(file.getName())) {
			String path = null;
			IFile f = (IFile)file.getAdapter(IFile.class);
			if (f != null && f.exists())
				path = f.getFullPath().toString();
			else {
				File f2 = (File)file.getAdapter(File.class);
				if (f2 != null && f2.exists()) {
					path = f2.getAbsolutePath();
				}
			}
			if (path != null) {	
				String parentType = JavaEEProjectUtilities.getJ2EEComponentType(rootComponent);
				IVirtualComponent dynamicComponent = ComponentCore.createArchiveComponent(
						rootComponent.getProject(),
						VirtualArchiveComponent.LIBARCHIVETYPE + path, file.getModuleRelativePath());
				String childType = JavaEEProjectUtilities.getJ2EEComponentType(dynamicComponent);
				return isApprovedNesting(parentType, childType, true);
			}
		}
		return false;
	}
	
	/*
	 * This is needed because otherwise, WebDeployTest fails
	 */
	protected boolean isApprovedNesting(String parentType, String childType, boolean binary) {
		if( childType == null )
			return false;
		if( IJ2EEFacetConstants.UTILITY.equals(childType) && binary)
			return false; // child utility project 

//		if( IJ2EEFacetConstants.UTILITY.equals(childType))
//			return false;
//		if( parentType.equals(IJ2EEFacetConstants.DYNAMIC_WEB) && childType.equals(IJ2EEFacetConstants.EJB))
//			return false;
		return true;
	}
}
