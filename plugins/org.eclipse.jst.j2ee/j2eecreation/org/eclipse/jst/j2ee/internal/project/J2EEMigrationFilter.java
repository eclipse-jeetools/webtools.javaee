/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.project;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.internal.migration.IMigrationFilter;


/**
 * @author nagrawal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class J2EEMigrationFilter implements IMigrationFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.common.migration.IMigrationFilter#isFiltered(org.eclipse.core.resources.IProject)
	 */
	public boolean isFiltered(IProject project) {
		J2EENature nature = J2EENature.getRegisteredRuntime(project);
		boolean ret = false;
		if (nature != null) {
			int ver = nature.getJ2EEVersion();
			if (ver == J2EEVersionConstants.J2EE_1_4_ID)
				ret = true;
			else
				ret = false;
		}
		return ret;
	}

}