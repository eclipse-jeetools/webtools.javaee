/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jst.j2ee.internal.common.exportmodel.AddClasspathReferencesParticipant;
import org.eclipse.jst.j2ee.internal.common.exportmodel.AddMappedOutputFoldersParticipant;
import org.eclipse.jst.j2ee.internal.common.exportmodel.ReplaceManifestExportParticipant;
import org.eclipse.jst.j2ee.internal.common.exportmodel.SingleRootExportParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FilterResourceParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class JavaEEComponentExportOperation extends ComponentExportOperation {

	public static IFlattenParticipant[] fixedParticpants = new IFlattenParticipant[] { 
		new ReplaceManifestExportParticipant(),
		new AddClasspathReferencesParticipant(),
		new AddMappedOutputFoldersParticipant(),
		//bug 301577 - non-binary used references are not returned from FlatVirtualComponent
		//this code can be removed once this bug is fixed
		new AbstractFlattenParticipant() {
			@Override
			public boolean isChildModule(IVirtualComponent rootComponent, IVirtualReference reference, FlatComponentTaskModel dataModel) {
				return !reference.getReferencedComponent().isBinary();
			}
		}
	};

	public static String[] DOT_FILE_NAMES = new String[] {
		".project", 	//$NON-NLS-1$
		".classpath", 	//$NON-NLS-1$
		".cvsignore", 	//$NON-NLS-1$
	};

	public static String[] DOT_SOURCE_FILES = new String[] {
		".java",		//$NON-NLS-1$
		".sqlj",		//$NON-NLS-1$
	};
		
	public JavaEEComponentExportOperation() {
		super();
	}

	public JavaEEComponentExportOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected List<IFlattenParticipant> getParticipants() {
		List<IFlattenParticipant> participants = new ArrayList<IFlattenParticipant>();
		participants.addAll(Arrays.asList(fixedParticpants));		
		participants.add(getExtensionFilterParticipant(isExportSource()));
		if (!isExportSource()) {
			participants.add(new SingleRootExportParticipant());
		}	
		return participants;
	}
	
	
	protected IFlattenParticipant getExtensionFilterParticipant(boolean isExportSource) {
		return FilterResourceParticipant.createSuffixFilterParticipant(getExcludeExtensions(isExportSource));
	}

	protected String[] getExcludeExtensions(boolean isExportSource) {
		ArrayList<String> excludeList = new ArrayList<String>();
		excludeList.addAll(Arrays.asList(DOT_FILE_NAMES));
		if (!isExportSource) {
			excludeList.addAll(Arrays.asList(DOT_SOURCE_FILES));
		}
		return excludeList.toArray(new String[excludeList.size()]);
	}
	
}
