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
package org.eclipse.jst.j2ee.internal.web.operations;



/**
 * Code generation class compiled from template file MasterCSS.tpl
 */
public class MasterCSS implements WebToolingTemplate {
	private static final String TEXT_1 = "BODY\r\n{\r\n    BACKGROUND-COLOR: #FFE4B5;\r\n    COLOR: black;\r\n    FONT-FAMILY: 'Times New Roman'\r\n}\r\nH1\r\n{\r\n    COLOR: navy;\r\n    FONT-FAMILY: 'Times New Roman';\r\n    FONT-SIZE: x-large;\r\n    FONT-WEIGHT: bolder;\r\n    TEXT-TRANSFORM: capitalize\r\n}\r\nH2\r\n{\r\n    COLOR: navy;\r\n    FONT-FAMILY: 'Times New Roman';\r\n    FONT-SIZE: large;\r\n    FONT-WEIGHT: bolder;\r\n    TEXT-TRANSFORM: capitalize\r\n}\r\nH3\r\n{\r\n    COLOR: navy;\r\n    FONT-FAMILY: 'Times New Roman';\r\n    FONT-SIZE: large;\r\n    FONT-WEIGHT: lighter;\r\n    TEXT-TRANSFORM: capitalize\r\n}";//$NON-NLS-1$
	private static final String TEXT_2 = "\r\n";//$NON-NLS-1$

	/**
	 * Code generation method
	 * 
	 * @return String
	 */
	public String generate(TemplateData data) {
		StringBuffer sb = new StringBuffer();
		sb.append(TEXT_1);
		sb.append(TEXT_2);
		return sb.toString();
	}
}