/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * 
 * Bundled Libraries node. Sub-node of EAR 5 Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class BundledNode extends AbstractEarNode {

	private static final List<String> UTILITY = Collections.singletonList(J2EEProjectUtilities.UTILITY);
	private String nodeName;
	private final BundledNode bundledLibsDirectoryNode;

	public BundledNode(IProject earProject, String nodeName, BundledNode bundledLibsDirectoryNode) {
		super(earProject);
		this.nodeName = nodeName;
		this.bundledLibsDirectoryNode = bundledLibsDirectoryNode;
		type = LIBS_TYPE;
	}

	@Override
	public String toString() {
		return nodeName;
	}

	@Override
	public String getText() {
		return nodeName;
	}
	
	public void setText(String label) {
		nodeName = label;
	}

	public BundledNode getBundledLibsDirectoryNode() {
		return bundledLibsDirectoryNode;
	}

	@Override
	public List getModules() {
		IVirtualComponent projectComponent = ComponentCore.createComponent(getEarProject());

		Path libPath = new Path("/"); //$NON-NLS-1$
		if (bundledLibsDirectoryNode == null){
			String modelLibPath = EarUtilities.getEARLibDir(projectComponent);
			String prefix = "/"; //$NON-NLS-1$
			if (modelLibPath.startsWith(prefix)){
				prefix = ""; //$NON-NLS-1$
			}
			libPath = new Path(prefix + modelLibPath); 
		}
		
		List libs = getComponentReferencesAsList(UTILITY, projectComponent,
				libPath);
		libs.addAll(getBinariesInLibDir(projectComponent,libPath, UTILITY));
		if(bundledLibsDirectoryNode == null)
			libs.addAll(getComponentReferencesAsList(implicitUtilityReferenceTypes, projectComponent, libPath));

		List modules = new ArrayList();
		for (int i = 0; i < libs.size(); i++) {
			IVirtualReference reference = (IVirtualReference) libs.get(i);
			
			IPath runtimePath =  getRealRuntimePath(reference);

			if (runtimePath != null && runtimePath.segment(0) != null && !libPath.makeRelative().isEmpty() &&
					runtimePath.makeRelative().equals(libPath.makeRelative())) {
				if (bundledLibsDirectoryNode == null){
					modules.add(libs.get(i));
				}

			} else {
				if (bundledLibsDirectoryNode != null){
					modules.add(libs.get(i));
				}
			}
		}
		if (bundledLibsDirectoryNode != null){
			modules.add(bundledLibsDirectoryNode);
		}
		return modules;
	}

}
