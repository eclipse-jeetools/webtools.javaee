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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * 
 * Modules sub-node of EAR Deployment descriptor node Java EE 5 
 * 
 * @author Dimitar Giormov
 *
 */
public class ModulesNode extends AbstractEarNode {

	public ModulesNode(IProject earProject) {
		super(earProject);
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
	
	 @Override
	public List getModules() {
		IVirtualComponent projectComponent = ComponentCore.createComponent(getEarProject());
    	return Arrays.asList(EarUtilities.getJ2EEModuleReferences(projectComponent));
	}
}
