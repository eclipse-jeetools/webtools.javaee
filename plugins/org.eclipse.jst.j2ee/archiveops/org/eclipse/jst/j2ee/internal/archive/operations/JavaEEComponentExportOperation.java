/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.exportmodel.JavaEEComponentExportCallback;
import org.eclipse.jst.j2ee.internal.deployables.JEEFlattenParticipantProvider;
import org.eclipse.wst.common.componentcore.internal.flat.FlattenParticipantModel;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.web.internal.deployables.IFlatDeployable;

public class JavaEEComponentExportOperation extends ComponentExportOperation {

	public JavaEEComponentExportOperation() {
		super();
	}

	public JavaEEComponentExportOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected List<IFlattenParticipant> getParticipants() {
		String[] moduleParticipants = getModuleParticipants();

		// Load from the model
		String[] filteredExtensions = getFilteredExtensions();

		Properties props = new Properties();
		props.put(JEEFlattenParticipantProvider.FILTERED, filteredExtensions);
		props.put(JEEFlattenParticipantProvider.PARTICIPANT_FOR_EXPORT, true);
		
		ArrayList<IFlattenParticipant> participants = new ArrayList<IFlattenParticipant>();
		IFlattenParticipant tmp;
		for( int i = 0; i < moduleParticipants.length; i++ ) {
			tmp = FlattenParticipantModel.getDefault().getParticipant(moduleParticipants[i], props);
			// A null participant is not an error. It simply means that participant provider has chosen to 
			// do nothing given the properties passed in, in this case, export
			if( tmp != null )
				participants.add(tmp);
		}
		return participants;
	}
	
	protected String[] getModuleParticipants() {
		IVirtualComponent vc = (IVirtualComponent)model.getProperty(IJ2EEComponentExportDataModelProperties.COMPONENT);
		if( vc != null ) {
			String p = (String) model.getProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME);
			IProject p2 = p == null ? null : ResourcesPlugin.getWorkspace().getRoot().getProject(p);
			if( p2 == null )
				p2 = vc.getProject();
			if( p2 != null && p2.isAccessible() ) {
				IModule[] projectMods = ServerUtil.getModules(p2);
				for( int i = 0; i < projectMods.length; i++ ) {
					IVirtualComponent tmp = (IVirtualComponent)projectMods[i].loadAdapter(IVirtualComponent.class, null);
					if( tmp.equals(vc)) {
						IModule mod = projectMods[i];
						IFlatDeployable o9 = (IFlatDeployable) mod.loadAdapter(IFlatDeployable.class, null);
						if( o9 != null ) {
							return o9.getParticipantIds();
						}
					}
				}
			}
		}
		return null;
	}
	
	
	@Override
	protected FlatComponentArchiver createFlatComponentArchiver(OutputStream out) {
		return new FlatComponentArchiver(getComponent(), out, getParticipants(), new JavaEEComponentExportCallback(isExportSource()));
	}
}
