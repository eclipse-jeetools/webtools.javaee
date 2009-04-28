/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;

import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.ui.IWorkbenchWindowPulldownDelegate2;


/**
 * A wizard is added to the "New Java EE Project" drop down if it has a parameter 'javaeeproject':
 *     <wizard
 *         name="My Java EE Project Wizard"
 *         icon="icons/wiz.gif"
 *         category="mycategory"
 *         id="xx.MyWizard">
 *         <class class="org.xx.MyWizard">
 *             <parameter name="javaeeproject" value="true"/>
 *         </class> 
 *         <description>
 *             My Wizard
 *         </description>
 *      </wizard>
 */
@Deprecated
//This class is being deprecated in 3.1, and is in plan to be removed
//in 3.2, since it is not being used.
public class NewJavaEEProjectDropDownAction extends NewJavaEEDropDownAction implements IMenuCreator, IWorkbenchWindowPulldownDelegate2 {
	
	private final static String ATT_JAVAEEPROJECT = "javaeeproject";//$NON-NLS-1$

	@Override
	protected String getTypeAttribute() {
		return ATT_JAVAEEPROJECT;
	}

}
