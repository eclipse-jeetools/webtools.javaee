/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.adapters;
/*


 */

import org.eclipse.jem.internal.util.emf.workbench.ProjectResourceSetImpl;
import org.eclipse.jem.util.plugin.JEMUtilPlugin;


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
		JEMUtilPlugin.getSharedCache().stopListening(this);	// We don't care about listening.
	}

}
