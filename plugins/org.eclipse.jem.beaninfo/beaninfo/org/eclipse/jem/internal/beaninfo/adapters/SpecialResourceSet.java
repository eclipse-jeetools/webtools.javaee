package org.eclipse.jem.internal.beaninfo.adapters;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: SpecialResourceSet.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */

import com.ibm.etools.emf.workbench.ProjectResourceSetImpl;
import com.ibm.etools.emf.workbench.plugin.EMFWorkbenchPlugin;

/**
 * A Special resource set that is used to link together to the beaninfo
 * nature's resource set and makes sure that any request for a new "java:/..."
 * JavaClass is redirected to the beaninfo nature's resource set. Otherwise
 * the classes will be all over the place and not all gathered in one place.
 * 
 * *package* protected because no one should create one of these. They are
 * returned by the BeaninfoNature.newResourceSet() request.
 * 
 * @version 	1.0
 * @author
 */
class SpecialResourceSet extends ProjectResourceSetImpl {

	/**
	 * Constructor for SpecialResourceSet.
	 * @param aProject
	 */
	public SpecialResourceSet() {
		super(null);
		EMFWorkbenchPlugin.getSharedCache().stopListening(this);	// We don't care about listening.
	}

}
