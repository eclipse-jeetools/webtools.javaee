/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.deployables;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * @author blancett
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ModuleAdapter extends AdapterImpl {
    
    J2EEFlexProjWebDeployable delegate;

    public void setModuleDelegate(J2EEFlexProjWebDeployable moduleDelegate) {
        delegate = moduleDelegate;
    }

    public J2EEFlexProjWebDeployable getDelegate() {
        return delegate;
    }
}
