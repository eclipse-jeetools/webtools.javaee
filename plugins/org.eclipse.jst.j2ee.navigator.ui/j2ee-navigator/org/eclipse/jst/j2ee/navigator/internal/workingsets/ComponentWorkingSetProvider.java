/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.navigator.internal.workingsets;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.common.navigator.internal.ui.workingsets.ICommonWorkingSetProvider;
import org.eclipse.ui.IWorkingSet;

/**
 * @author Admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ComponentWorkingSetProvider implements ICommonWorkingSetProvider {

	private static final ComponentWorkingSetRegistry COMPONENT_WORKING_SET_REGISTRY = ComponentWorkingSetRegistry.getInstance();
	boolean bInitialized = false;
	List workingSets = new ArrayList();
	/**
	 * 
	 */
	public ComponentWorkingSetProvider() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.common.navigator.internal.ui.workingsets.IWorkingSetProvider#getWorkingSets()
	 */
	public IWorkingSet[] getWorkingSets() {
		if (!bInitialized) {
		    //workingSets.clear();
			synchronized (workingSets) {
				ComponentWorkingSetDescriptor[] set = COMPONENT_WORKING_SET_REGISTRY.getComponentWorkingSetDescriptors();
				ComponentWorkingSetDescriptor descriptor = null;
				IWorkingSet workingSet = null;
				for (int x=0; x<set.length;++ x) {
					descriptor = set[x];
					workingSet=  new ComponentWorkingSet(descriptor); 
					workingSets.add(workingSet);
					bInitialized = true;
				}	
			}
		}
		
		IWorkingSet[] ws = new IWorkingSet[workingSets.size()];
		return (IWorkingSet[])workingSets.toArray(ws);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.common.navigator.internal.ui.workingsets.IWorkingSetProvider#init(org.eclipse.jst.common.navigator.internal.ui.workingsets.WorkingSetModel)
	 */
//	public void init(WorkingSetModel workingSetModel) {
//		// TODO Auto-generated method stub

//	}

}
