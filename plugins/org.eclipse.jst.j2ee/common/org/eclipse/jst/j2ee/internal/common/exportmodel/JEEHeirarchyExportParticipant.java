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

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.export.AbstractExportParticipant;
import org.eclipse.wst.common.componentcore.export.ExportableFile;
import org.eclipse.wst.common.componentcore.export.ExportModel.ExportTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class JEEHeirarchyExportParticipant extends AbstractExportParticipant {
	public static final String JEE_VERSION = "org.eclipse.jst.j2ee.internal.deployables.export.eeVersion"; //$NON-NLS-1$
	public static String [] CHILDREN_EXTENSIONS = new String [] {IJ2EEModuleConstants.JAR_EXT, ".zip", IJ2EEModuleConstants.RAR_EXT, IJ2EEModuleConstants.WAR_EXT };  //$NON-NLS-1$
	public JEEHeirarchyExportParticipant() {
	}
	
	@Override
	public boolean isChildModule(IVirtualComponent rootComponent,
			IVirtualReference reference, ExportTaskModel dataModel) {
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
	public boolean isChildModule(IVirtualComponent rootComponent,
			ExportTaskModel dataModel, ExportableFile file) {
		if( isPossibleChild(file.getName())) {
			File f = (File)file.getAdapter(File.class);
			if( f != null && f.exists()) {
				String parentType = JavaEEProjectUtilities.getJ2EEComponentType(rootComponent);
				String childType = JavaEEProjectUtilities.getJ2EEFileType(new Path(f.getAbsolutePath()));
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
