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

import java.util.List;

import org.eclipse.core.resources.IProject;

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

	public BundledNode(IProject earProject, List modules, String nodeName, BundledNode bundledLibsDirectoryNode) {
		super(earProject, modules);
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
}
