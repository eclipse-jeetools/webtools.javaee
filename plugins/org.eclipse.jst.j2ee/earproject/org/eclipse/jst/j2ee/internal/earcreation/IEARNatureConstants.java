/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;




/* The Ear Nature constants are the constants for the web nature interface to the plugins */

public interface IEARNatureConstants {

	String PLUG_IN_ID = "org.eclipse.jst.j2ee"; //$NON-NLS-1$
	String NATURE_ID = PLUG_IN_ID + ".EARNature"; //$NON-NLS-1$
	String NO_NATURE_MESSAGE = EARCreationResourceHandler.getString("Not_an_ear_project_UI_"); //$NON-NLS-1$
	String NOT_OPEN_MESSAGE = EARCreationResourceHandler.getString("EAR_PROJECT_MUST_BE_OPEN_UI_"); //$NON-NLS-1$

	String DEFAULT_META_PATH = "META-INF"; //$NON-NLS-1$
	String[] NATURE_IDS = {NATURE_ID};
	String EDIT_MODEL_ID = "com.ibm.wtp.application.editModel"; //$NON-NLS-1$
}