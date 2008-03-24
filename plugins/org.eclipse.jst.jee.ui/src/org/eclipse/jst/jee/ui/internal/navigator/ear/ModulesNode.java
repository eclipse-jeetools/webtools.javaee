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
import org.eclipse.jst.jee.ui.internal.Messages;

/**
 * 
 * Modules sub-node of EAR Deployment descriptor node Java EE 5 
 * 
 * @author Dimitar Giormov
 *
 */
public class ModulesNode extends AbstractEarNode {

	public ModulesNode(IProject earProject, List modules) {
		super(earProject, modules);
		type = MODULES_TYPE;
	}

	@Override
	public String toString() {
	    return Messages.EAR_MODULES_NODE;
	}

	@Override
	public String getText() {
		return Messages.EAR_MODULES_NODE;
	}
}
