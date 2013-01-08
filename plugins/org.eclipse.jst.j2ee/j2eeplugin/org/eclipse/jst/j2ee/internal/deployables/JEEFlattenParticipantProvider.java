/*******************************************************************************
 * Copyright (c) 2003, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.util.Properties;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.AddClasspathFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.common.internal.modulecore.AddMappedOutputFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.IgnoreJavaInSourceFolderParticipant;
import org.eclipse.jst.common.internal.modulecore.ReplaceManifestExportParticipant;
import org.eclipse.jst.common.internal.modulecore.SingleRootExportParticipant;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.exportmodel.JavaEESingleRootCallback;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.internal.flat.GlobalHeirarchyParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipantProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class JEEFlattenParticipantProvider implements
		IFlattenParticipantProvider {

	/*
	 * The following are all internal to jeetools and are not API,
	 * though may at some point be saved in a settings file in a list 
	 * format. if the user decides to customize their list of participants,
	 * they may add or remove items to the list in the .settings file
	 */
	public static final String JEESingleRootParticipant = "JEESingleRootParticipant"; //$NON-NLS-1$
	public static final String JEEHeirarchyExportParticipant = "JEEHeirarchyExportParticipant"; //$NON-NLS-1$
	public static final String AddClasspathLibReferencesParticipant = "AddClasspathLibReferencesParticipant"; //$NON-NLS-1$
	public static final String AddClasspathFoldersParticipant = "AddClasspathFoldersParticipant"; //$NON-NLS-1$
	public static final String AddMappedOutputFoldersParticipant = "AddMappedOutputFoldersParticipant"; //$NON-NLS-1$
	public static final String IgnoreJavaInSourceFolderParticipant = "IgnoreJavaInSourceFolderParticipant"; //$NON-NLS-1$
	public static final String JEEReplaceManifestExportParticipant = "JEEReplaceManifestExportParticipant"; //$NON-NLS-1$
	public static final String FilterResourceParticipant = "FilterResourceParticipant"; //$NON-NLS-1$
	
	
	public static final String PARTICIPANT_FOR_EXPORT = "exportParticipants"; //$NON-NLS-1$
	public static final String FILTERED = "AddMappedOutputFoldersParticipant.FILTERED"; //$NON-NLS-1$
	
	
	public IFlattenParticipant findParticipant(String id, Properties props) {
		boolean forExport = props != null && props.get(PARTICIPANT_FOR_EXPORT) != null && 
				((Boolean)props.get(PARTICIPANT_FOR_EXPORT)).booleanValue();
		
		if( JEESingleRootParticipant.equals(id))
			return forExport ? null : new SingleRootExportParticipant(new JavaEESingleRootCallback());
		if( JEEHeirarchyExportParticipant.equals(id))
			return forExport ? createExportHierarchyParticipant() : new GlobalHeirarchyParticipant();
		if( AddClasspathLibReferencesParticipant.equals(id))
			return new AddClasspathLibReferencesParticipant();
		if( AddClasspathFoldersParticipant.equals(id))
			return new AddClasspathFoldersParticipant();
		if( AddMappedOutputFoldersParticipant.equals(id)) {
			String[] filter = props == null ? null  : (String[]) props.get(FILTERED);
			filter = filter == null ? new String[0] : filter;
			return new AddMappedOutputFoldersParticipant(filter);
		} if( IgnoreJavaInSourceFolderParticipant.equals(id))
			return forExport ? null : new IgnoreJavaInSourceFolderParticipant();
		if( JEEReplaceManifestExportParticipant.equals(id))
			return new ReplaceManifestExportParticipant(new Path(J2EEConstants.MANIFEST_URI));
		if( FilterResourceParticipant.equals(id)){
			String[] filter = props == null ? null  : (String[]) props.get(FILTERED);
			return (filter == null || !forExport)? null : 
				org.eclipse.wst.common.componentcore.internal.flat.FilterResourceParticipant.createSuffixFilterParticipant(filter);			
		}
		return null;
	}

	protected IFlattenParticipant createExportHierarchyParticipant() {
		return new AbstractFlattenParticipant() {
			@Override
			public boolean isChildModule(IVirtualComponent rootComponent, IVirtualReference reference, FlatComponentTaskModel dataModel) {
				if (!reference.getReferencedComponent().isBinary()) {
					return true;
				}
				return false;
			}
		};
	}
}
