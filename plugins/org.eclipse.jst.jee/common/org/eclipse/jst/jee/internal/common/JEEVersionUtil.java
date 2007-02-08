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
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.jee.internal.common;

import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.jee.JEEVersionConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @author nagrawal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 * TODO cleaning, as we copied from J2EEVersionUtil and added the ejb 30, jee 50 constants, 
 */
public class JEEVersionUtil {

	public static String getServletTextVersion(int aVersion) {
		switch (aVersion) {
		case JEEVersionConstants.SERVLET_2_5:
			return JEEVersionConstants.VERSION_2_5_TEXT;

		default : 
			return J2EEVersionUtil.getServletTextVersion(aVersion);
		}
	}

	public static String getEJBTextVersion(int aVersion) {

		switch (aVersion) {
		case JEEVersionConstants.EJB_3_0_ID:
			return JEEVersionConstants.VERSION_3_0_TEXT;

		default : 
			return J2EEVersionUtil.getEJBTextVersion(aVersion);
		}
	}
/*
	public static String getJCATextVersion(int aVersion) {
		switch (aVersion) {
		case J2EEVersionConstants.JCA_1_0_ID:
			return J2EEVersionConstants.VERSION_1_0_TEXT;

		case J2EEVersionConstants.JCA_1_5_ID:
			return J2EEVersionConstants.VERSION_1_5_TEXT;

		}
		return ""; //$NON-NLS-1$
	}
*/
	public static String getJ2EETextVersion(int aVersion) {
		switch (aVersion) {
		case JEEVersionConstants.J2EE_5_0_ID:
			return JEEVersionConstants.VERSION_5_0_TEXT;

		default : 
			return J2EEVersionUtil.getJ2EETextVersion(aVersion);

		}
	}

	public static int convertAppClientVersionStringToJ2EEVersionID(String version) {
		if (version.equals(JEEVersionConstants.VERSION_5_0_TEXT))
			return JEEVersionConstants.J2EE_5_0_ID;
		else
			return J2EEVersionUtil.convertAppClientVersionStringToJ2EEVersionID(version);
	}

	public static int convertEJBVersionStringToJ2EEVersionID(String version) {
		if (version.equals(JEEVersionConstants.VERSION_3_0_TEXT))
			return JEEVersionConstants.J2EE_5_0_ID;
		else
			return J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(version);
	}

	public static int convertWebVersionStringToJ2EEVersionID(String version) {
		if (version.equals(JEEVersionConstants.VERSION_2_5_TEXT))
			return JEEVersionConstants.J2EE_5_0_ID;
		else
			return J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(version);
	}

	public static int convertConnectorVersionStringToJ2EEVersionID(String version) {
		// 	default
		return J2EEVersionUtil.convertConnectorVersionStringToJ2EEVersionID(version);
	}

	public static int convertJ2EEVersionIDToEJBVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case JEEVersionConstants.J2EE_5_0_ID:
				return JEEVersionConstants.EJB_3_0_ID;
			default : 
				return J2EEVersionUtil.convertJ2EEVersionIDToEJBVersionID(j2eeVersionId);
		}
	}

	public static int convertJ2EEVersionIDToWebVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
		case JEEVersionConstants.J2EE_5_0_ID:
			return JEEVersionConstants.WEB_2_5_ID;
		default : 
			return J2EEVersionUtil.convertJ2EEVersionIDToWebVersionID(j2eeVersionId);
		}
	}

	public static int convertJ2EEVersionIDToConnectorVersionID(int j2eeVersionId) {
		// default
		return J2EEVersionUtil.convertJ2EEVersionIDToConnectorVersionID(j2eeVersionId);
	}
	
	public static int convertVersionStringToInt(String version) {
		int nVersion = 0;
		
		if( version.endsWith("")){ //$NON-NLS-1$
			nVersion = 0;
		}
		

		if (version.equals(JEEVersionConstants.VERSION_5_0_TEXT))
			nVersion = JEEVersionConstants.VERSION_5_0;		

		else if (version.equals(JEEVersionConstants.VERSION_3_0_TEXT))
			nVersion = JEEVersionConstants.VERSION_3_0;		

		else
			nVersion = J2EEVersionUtil.convertVersionStringToInt(version);
/*
		if (version.equals(J2EEVersionConstants.VERSION_1_0_TEXT))
			nVersion = J2EEVersionConstants.VERSION_1_0;
		
		if (version.equals(J2EEVersionConstants.VERSION_1_1_TEXT))
			nVersion = J2EEVersionConstants.VERSION_1_1;
		
		if (version.equals(J2EEVersionConstants.VERSION_1_2_TEXT))
			nVersion = J2EEVersionConstants.VERSION_1_2;
		
		if (version.equals(J2EEVersionConstants.VERSION_1_3_TEXT))
			nVersion = J2EEVersionConstants.VERSION_1_3;	
		
		if (version.equals(J2EEVersionConstants.VERSION_1_4_TEXT))
			nVersion = J2EEVersionConstants.VERSION_1_4;
		
		if (version.equals(J2EEVersionConstants.VERSION_1_5_TEXT))
			nVersion = J2EEVersionConstants.VERSION_1_5;
		
		if (version.equals(J2EEVersionConstants.VERSION_2_0_TEXT))
			nVersion = J2EEVersionConstants.VERSION_2_0;
		
		if (version.equals(J2EEVersionConstants.VERSION_2_1_TEXT))
			nVersion = J2EEVersionConstants.VERSION_2_1;	
		
		if (version.equals(J2EEVersionConstants.VERSION_2_2_TEXT))
			nVersion = J2EEVersionConstants.VERSION_2_2;
		
		if (version.equals(J2EEVersionConstants.VERSION_2_3_TEXT))
			nVersion = J2EEVersionConstants.VERSION_2_3;
		
		if (version.equals(J2EEVersionConstants.VERSION_2_4_TEXT))
			nVersion = J2EEVersionConstants.VERSION_2_4;
		
		if (version.equals(J2EEVersionConstants.VERSION_2_5_TEXT))
			nVersion = J2EEVersionConstants.VERSION_2_5;		
*/	
		return nVersion;
		
	}
	public static String convertVersionIntToString(int version) {
		String nVersion = null;


		if (version == JEEVersionConstants.VERSION_5_0)
			nVersion = JEEVersionConstants.VERSION_5_0_TEXT;		
		else if (version == JEEVersionConstants.VERSION_3_0)
			nVersion = JEEVersionConstants.VERSION_3_0_TEXT;	
		else 
			nVersion = J2EEVersionUtil.convertVersionIntToString(version);
/*
		if (version == J2EEVersionConstants.VERSION_1_0)
			nVersion = J2EEVersionConstants.VERSION_1_0_TEXT;

		if (version == J2EEVersionConstants.VERSION_1_0)
			nVersion = J2EEVersionConstants.VERSION_1_0_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_1_1)
			nVersion = J2EEVersionConstants.VERSION_1_1_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_1_2)
			nVersion = J2EEVersionConstants.VERSION_1_2_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_1_3)
			nVersion = J2EEVersionConstants.VERSION_1_3_TEXT;	
		
		if (version == J2EEVersionConstants.VERSION_1_4)
			nVersion = J2EEVersionConstants.VERSION_1_4_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_1_5)
			nVersion = J2EEVersionConstants.VERSION_1_5_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_2_0)
			nVersion = J2EEVersionConstants.VERSION_2_0_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_2_1)
			nVersion = J2EEVersionConstants.VERSION_2_1_TEXT;	
		
		if (version == J2EEVersionConstants.VERSION_2_2)
			nVersion = J2EEVersionConstants.VERSION_2_2_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_2_3)
			nVersion = J2EEVersionConstants.VERSION_2_3_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_2_4)
			nVersion = J2EEVersionConstants.VERSION_2_4_TEXT;
		
		if (version == J2EEVersionConstants.VERSION_2_5)
			nVersion = J2EEVersionConstants.VERSION_2_5_TEXT;		

*/		return nVersion;
		
	}

	public static int convertVersionStringToInt(IVirtualComponent comp) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(comp.getProject());
		if (J2EEProjectUtilities.isDynamicWebProject(comp.getProject()))
			return convertWebVersionStringToJ2EEVersionID(version);
		if (J2EEProjectUtilities.isEJBProject(comp.getProject()))
			return convertEJBVersionStringToJ2EEVersionID(version);
		if (J2EEProjectUtilities.isEARProject(comp.getProject()))
			return convertVersionStringToInt(version);
		if (J2EEProjectUtilities.isJCAProject(comp.getProject()))
			return convertConnectorVersionStringToJ2EEVersionID(version);
		if (J2EEProjectUtilities.isApplicationClientProject(comp.getProject()))
			return convertAppClientVersionStringToJ2EEVersionID(version);
		return 0;
	}
	
}
