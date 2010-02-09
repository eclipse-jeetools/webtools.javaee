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

import org.eclipse.jst.common.internal.modulecore.AddClasspathFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.common.internal.modulecore.AddMappedOutputFoldersParticipant;
import org.eclipse.jst.j2ee.internal.common.exportmodel.ReplaceManifestExportParticipant;
import org.eclipse.jst.j2ee.internal.common.exportmodel.StandardHierarchyParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FilterResourceParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class JavaEEComponentExportOperation extends ComponentExportOperation {

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
		IFlattenParticipant[] participants = new IFlattenParticipant[] {
				new ReplaceManifestExportParticipant(),
				new AddClasspathLibReferencesParticipant(),
				new AddClasspathFoldersParticipant(),
				new AddMappedOutputFoldersParticipant(),
				new StandardHierarchyParticipant(),
				getExtensionFilterParticipant()
		};
		return Arrays.asList(participants);
	}
	
	
	protected IFlattenParticipant getExtensionFilterParticipant() {
		return FilterResourceParticipant.createSuffixFilterParticipant(getExcludeExtensions());
	}

	protected String[] getExcludeExtensions() {
		ArrayList<String> excludeList = new ArrayList<String>();
		excludeList.addAll(Arrays.asList(DOT_FILE_NAMES));
		if (!isExportSource()) {
			excludeList.addAll(Arrays.asList(DOT_SOURCE_FILES));
		}
		return excludeList.toArray(new String[excludeList.size()]);
	}
	
}
