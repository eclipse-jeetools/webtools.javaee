/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

public class Session2xInterfacesTableRow{
	private String abriviation;
	private String className;
	private String propertyName;
	
	Session2xInterfacesTableRow(String abriviation, String className, String propertyName){
		setAbriviation(abriviation);
		setClassName(className);
		setPropertyName(propertyName);
	}
	
	public void setAbriviation(String abriviation) {
		this.abriviation = abriviation;
	}
	public String getAbriviation() {
		return abriviation;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassName() {
		return className;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyName() {
		return propertyName;
	}
}
