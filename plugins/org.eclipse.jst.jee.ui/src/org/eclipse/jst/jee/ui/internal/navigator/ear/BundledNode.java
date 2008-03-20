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

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * 
 * Bundled Libraries node. Sub-node of EAR 5 Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class BundledNode extends AbstractEarNode {

	public BundledNode(IProject earProject, IVirtualReference[] modules) {
		super(earProject, modules);
		type = LIBS_TYPE;
	}

	@Override
	public String toString() {
	    return "Bundled library"; //$NON-NLS-1$
	}

	@Override
	public String getText() {
		return "Bundled library"; //$NON-NLS-1$
	}
}
