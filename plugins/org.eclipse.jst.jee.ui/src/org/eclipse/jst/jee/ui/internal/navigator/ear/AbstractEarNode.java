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

public abstract class AbstractEarNode {
    public static int MODULES_TYPE = 0;    
    public static int LIBS_TYPE = 1;
    
    protected int type;
    
    private IProject earProject;
    private IVirtualReference[] modules;
    
    public AbstractEarNode(IProject earProject, IVirtualReference[] modules) {
        this.earProject = earProject;
        this.modules = modules;
    }

    public IProject getEarProject() {
        return earProject;
    }
    
    public IVirtualReference[] getModules() {
        return modules;
    }

    public int getType() {
        return type;
    }
    
    public abstract String getText();

}
