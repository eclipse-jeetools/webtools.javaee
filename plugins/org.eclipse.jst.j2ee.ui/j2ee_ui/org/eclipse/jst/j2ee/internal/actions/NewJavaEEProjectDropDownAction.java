/*******************************************************************************
 * Copyright (c) 2007,2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.actions;

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
public class NewJavaEEProjectDropDownAction extends NewJavaEEDropDownAction {
	
	private final static String ATT_JAVAEEPROJECT = "javaeeproject";//$NON-NLS-1$

	@Override
	protected String getTypeAttribute() {
		return ATT_JAVAEEPROJECT;
	}

}
