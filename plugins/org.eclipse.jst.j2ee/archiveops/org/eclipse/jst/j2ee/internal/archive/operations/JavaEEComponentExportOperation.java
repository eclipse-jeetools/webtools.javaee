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

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.AddClasspathFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.common.internal.modulecore.AddMappedOutputFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.ReplaceManifestExportParticipant;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyEnablement;
import org.eclipse.jst.j2ee.internal.common.exportmodel.JavaEEComponentExportCallback;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FilterResourceParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class JavaEEComponentExportOperation extends ComponentExportOperation {

	public JavaEEComponentExportOperation() {
		super();
	}

	public JavaEEComponentExportOperation(IDataModel model) {
		super(model);
	}
	
	@Override
	protected List<IFlattenParticipant> getParticipants() {		
		List<IFlattenParticipant> participants = new ArrayList<IFlattenParticipant>();
		String[] filteredExtensions = getFilteredExtensions();
		
		participants.add(createHierarchyParticipant());
		participants.add(new AddMappedOutputFoldersParticipant(filteredExtensions));
		participants.add(FilterResourceParticipant.createSuffixFilterParticipant(filteredExtensions));
		participants.add(new AddClasspathLibReferencesParticipant());
		participants.add(new AddClasspathFoldersParticipant());	
		if (ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
			participants.add(new ReplaceManifestExportParticipant(new Path(J2EEConstants.MANIFEST_URI)));
		}
		
		return participants;
	}
	
	protected IFlattenParticipant createHierarchyParticipant() {
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

	@Override
	protected FlatComponentArchiver createFlatComponentArchiver(OutputStream out) {
		return new FlatComponentArchiver(getComponent(), out, getParticipants(), new JavaEEComponentExportCallback(isExportSource()));
	}
	
	
}
