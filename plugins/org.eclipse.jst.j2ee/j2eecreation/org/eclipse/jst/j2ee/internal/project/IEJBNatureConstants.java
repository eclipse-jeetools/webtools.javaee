/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.project;

import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;

/**
 * @deprecated
 * Use
 * <p>
 * All nature constants interfaces are also deprecated along with nature runtime classes
 * IModuleConstants need to be used.
 * </p>
 *
 */

public interface IEJBNatureConstants {
	String NATURE_ID = "org.eclipse.jst.j2ee.ejb.EJBNature"; //$NON-NLS-1$ 

	String DEFAULT_EJB_MODULE_PATH = "ejbModule"; //$NON-NLS-1$
	String META_PATH = ArchiveConstants.META_INF;
	String J2EE_PLUGIN_ID = "org.eclipse.jst.j2ee"; //$NON-NLS-1$

	/**
	 * @deprecated - Please use
	 *             {@link com.ibm.etools.archive.ArchiveConstants#com.ibm.etools.archive.ArchiveConstants.EJBJAR_DD_URI
	 */
	String MODEL_RESOURCE_URI = META_PATH + "/ejb-jar.xml"; //$NON-NLS-1$


	/** The possible nature ids available for an EJB project */
	String[] EJB_NATURE_IDS = {NATURE_ID};

	String EDIT_MODEL_ID = "org.eclipse.jst.ejb.editModel"; //$NON-NLS-1$
	String WEB_SERVICE_EDIT_MODEL_ID = "org.eclipse.jst.j2ee.webservice.ejb.editModel"; //$NON-NLS-1$

}