/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

	private final String nodeName;
	private final BundledNode bundledLibsDirectoryNode;

	public final static String EAR_DEFAULT_LIB = "lib"; //$NON-NLS-1$

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

	public BundledNode getBundledLibsDirectoryNode() {
		return bundledLibsDirectoryNode;
	}

	@Override
	public List getModules() {
		IVirtualComponent projectComponent = ComponentCore.createComponent(getEarProject());

		List libs = getComponentReferencesAsList(Collections.singletonList(J2EEProjectUtilities.UTILITY), projectComponent,
				new Path("/" + EAR_DEFAULT_LIB)); //$NON-NLS-1$

		List modules = new ArrayList();
		for (int i = 0; i < libs.size(); i++) {
			IVirtualReference reference = (IVirtualReference) libs.get(i);
			IPath runtimePath = reference.getRuntimePath();

			if (runtimePath != null && runtimePath.segment(0) != null && 
					runtimePath.equals(new Path("/" + EAR_DEFAULT_LIB))) { //$NON-NLS-1$
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
