/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.web.internal.deployables.FlatComponentDeployable;

/**
 * J2EE module factory.
 */
public class JEEDeployableFactory extends J2EEDeployableFactory {
	public static final String JEE_ID = "org.eclipse.jst.jee.server"; //$NON-NLS-1$
	public static JEEDeployableFactory JEE_INSTANCE;
	public static JEEDeployableFactory jeeInstance() {
		if( JEE_INSTANCE == null ) {
			ensureFactoryLoaded(JEE_ID);
		}
		return JEE_INSTANCE;
	}
	
	public JEEDeployableFactory() {
		super();
	}
	
	@Override
	public void initialize() {
		super.initialize();
		if( getId().equals(JEE_ID))
			JEE_INSTANCE = this;
	}


	@Override
	protected FlatComponentDeployable createModuleDelegate(IProject project, IVirtualComponent component) {
		return new JEEFlexProjDeployable(project, component);
	}

	@Override
	protected boolean canHandleProject(IProject p) {
		// First check the component is of type Java EE, then check J2EE version
		IVirtualComponent comp = ComponentCore.createComponent(p);
		return (JavaEEProjectUtilities.usesJavaEEComponent(comp) && J2EEProjectUtilities.isJEEProject(p));
	}

	@Override
	protected FlatComponentDeployable getNestedDelegate(IVirtualComponent component) {
		return new JEEFlexProjDeployable(component.getProject(), component);
	}

}
