/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
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
 */
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @author nagrawal
 */
public class J2EEVersionUtil {

	public static String getServletTextVersion(int aVersion) {
		switch (aVersion) {
		case J2EEVersionConstants.SERVLET_2_2:
			return J2EEVersionConstants.VERSION_2_2_TEXT;

		case J2EEVersionConstants.SERVLET_2_3:
			return J2EEVersionConstants.VERSION_2_3_TEXT;

		case J2EEVersionConstants.SERVLET_2_4:
			return J2EEVersionConstants.VERSION_2_4_TEXT;
		
		case J2EEVersionConstants.SERVLET_2_5:
			return J2EEVersionConstants.VERSION_2_5_TEXT;
		
		case J2EEVersionConstants.SERVLET_3_0:
			return J2EEVersionConstants.VERSION_3_0_TEXT;
		
		case J2EEVersionConstants.SERVLET_3_1:
			return J2EEVersionConstants.VERSION_3_1_TEXT;
		}
		return ""; //$NON-NLS-1$

	}

	public static String getEJBTextVersion(int aVersion) {

		switch (aVersion) {
		case J2EEVersionConstants.EJB_1_0_ID:
			return J2EEVersionConstants.VERSION_1_0_TEXT;

		case J2EEVersionConstants.EJB_1_1_ID:
			return J2EEVersionConstants.VERSION_1_1_TEXT;

		case J2EEVersionConstants.EJB_2_0_ID:
			return J2EEVersionConstants.VERSION_2_0_TEXT;

		case J2EEVersionConstants.EJB_2_1_ID:
			return J2EEVersionConstants.VERSION_2_1_TEXT;
		
		case J2EEVersionConstants.EJB_3_0_ID:
			return J2EEVersionConstants.VERSION_3_0_TEXT;
		
		case J2EEVersionConstants.EJB_3_1_ID:
			return J2EEVersionConstants.VERSION_3_1_TEXT;
		
		case J2EEVersionConstants.EJB_3_2_ID:
			return J2EEVersionConstants.VERSION_3_2_TEXT;
		}
		return ""; //$NON-NLS-1$
	}

	public static String getJCATextVersion(int aVersion) {
		switch (aVersion) {
		case J2EEVersionConstants.JCA_1_0_ID:
			return J2EEVersionConstants.VERSION_1_0_TEXT;

		case J2EEVersionConstants.JCA_1_5_ID:
			return J2EEVersionConstants.VERSION_1_5_TEXT;

		case J2EEVersionConstants.JCA_1_6_ID:
			return J2EEVersionConstants.VERSION_1_6_TEXT;

		}
		return ""; //$NON-NLS-1$
	}

	public static String getJ2EETextVersion(int aVersion) {
		switch (aVersion) {
		case J2EEVersionConstants.J2EE_1_2_ID:
			return J2EEVersionConstants.VERSION_1_2_TEXT;

		case J2EEVersionConstants.J2EE_1_3_ID:
			return J2EEVersionConstants.VERSION_1_3_TEXT;

		case J2EEVersionConstants.J2EE_1_4_ID:
			return J2EEVersionConstants.VERSION_1_4_TEXT;

		case J2EEVersionConstants.JEE_5_0_ID:
			return J2EEVersionConstants.VERSION_5_0_TEXT;

		case J2EEVersionConstants.JEE_6_0_ID:
			return J2EEVersionConstants.VERSION_6_0_TEXT;

		case J2EEVersionConstants.JEE_7_0_ID:
			return J2EEVersionConstants.VERSION_7_0_TEXT;
			
		}
		return "";//$NON-NLS-1$
	}

	public static int convertAppClientVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_1_2_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_3_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_4_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		if (version.equals(J2EEVersionConstants.VERSION_5_0_TEXT))
			return J2EEVersionConstants.JEE_5_0_ID;
		if (version.equals(J2EEVersionConstants.VERSION_6_0_TEXT))
			return J2EEVersionConstants.JEE_6_0_ID;
		if (version.equals(J2EEVersionConstants.VERSION_7_0_TEXT))
			return J2EEVersionConstants.JEE_7_0_ID;
		// default
		return J2EEVersionConstants.JEE_5_0_ID;
	}

	public static int convertEJBVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_1_1_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_0_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_1_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		if (version.equals(J2EEVersionConstants.VERSION_3_0_TEXT))
			return J2EEVersionConstants.JEE_5_0_ID;
		if (version.equals(J2EEVersionConstants.VERSION_3_1_TEXT))
			return J2EEVersionConstants.JEE_6_0_ID;
		if (version.equals(J2EEVersionConstants.VERSION_3_2_TEXT))
			return J2EEVersionConstants.JEE_7_0_ID;
		// default
		return J2EEVersionConstants.JEE_5_0_ID;
	}

	public static int convertWebVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_2_2_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_3_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_4_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_5_TEXT))
			return J2EEVersionConstants.JEE_5_0_ID;
		if (version.equals(J2EEVersionConstants.VERSION_3_0_TEXT))
			return J2EEVersionConstants.JEE_6_0_ID;
		if (version.equals(J2EEVersionConstants.VERSION_3_1_TEXT))
			return J2EEVersionConstants.JEE_7_0_ID;
		// default
		return J2EEVersionConstants.J2EE_1_4_ID;
	}
	
	public static int convertWebFragmentVersionStringToJ2EEVersionID(String version) {
		return J2EEVersionConstants.JEE_6_0_ID;
	}

	public static int convertConnectorVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_1_0_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_5_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_6_TEXT))
			return J2EEVersionConstants.JEE_6_0_ID;
		// default
		return J2EEVersionConstants.JEE_6_0_ID;
	}

	public static int convertJ2EEVersionIDToEJBVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case J2EEVersionConstants.J2EE_1_2_ID:
				return J2EEVersionConstants.EJB_1_1_ID;
			case J2EEVersionConstants.J2EE_1_3_ID:
				return J2EEVersionConstants.EJB_2_0_ID;
			case J2EEVersionConstants.J2EE_1_4_ID:
				return J2EEVersionConstants.EJB_2_1_ID;
			case J2EEVersionConstants.JEE_5_0_ID:
				return J2EEVersionConstants.EJB_3_0_ID;
			case J2EEVersionConstants.JEE_6_0_ID:
				return J2EEVersionConstants.EJB_3_1_ID;
			case J2EEVersionConstants.JEE_7_0_ID:
				return J2EEVersionConstants.EJB_3_2_ID;
		}
		// default
		return J2EEVersionConstants.EJB_3_1_ID;
	}

	public static int convertJ2EEVersionIDToWebVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case J2EEVersionConstants.J2EE_1_2_ID:
				return J2EEVersionConstants.WEB_2_2_ID;
			case J2EEVersionConstants.J2EE_1_3_ID:
				return J2EEVersionConstants.WEB_2_3_ID;
			case J2EEVersionConstants.J2EE_1_4_ID:
				return J2EEVersionConstants.WEB_2_4_ID;
			case J2EEVersionConstants.JEE_5_0_ID:
				return J2EEVersionConstants.WEB_2_5_ID;
			case J2EEVersionConstants.JEE_6_0_ID:
				return J2EEVersionConstants.WEB_3_0_ID;
			case J2EEVersionConstants.JEE_7_0_ID:
				return J2EEVersionConstants.WEB_3_1_ID;
		}
		// default
		return J2EEVersionConstants.WEB_3_0_ID;
	}

	public static int convertJ2EEVersionIDToConnectorVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case J2EEVersionConstants.J2EE_1_3_ID:
				return J2EEVersionConstants.JCA_1_0_ID;
			case J2EEVersionConstants.J2EE_1_4_ID:
			case J2EEVersionConstants.JEE_5_0_ID:
				return J2EEVersionConstants.JCA_1_5_ID;
			case J2EEVersionConstants.JEE_6_0_ID:
			case J2EEVersionConstants.JEE_7_0_ID:
				return J2EEVersionConstants.JCA_1_6_ID;
		}
		// default
		return J2EEVersionConstants.JCA_1_6_ID;
	}
	
	public static int convertVersionStringToInt(IVirtualComponent comp) {
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(comp.getProject());
		if (JavaEEProjectUtilities.isDynamicWebProject(comp.getProject()))
			return convertWebVersionStringToJ2EEVersionID(version);
		if (JavaEEProjectUtilities.isEJBProject(comp.getProject()))
			return convertEJBVersionStringToJ2EEVersionID(version);
		if (JavaEEProjectUtilities.isEARProject(comp.getProject()))
			return convertVersionStringToInt(version);
		if (JavaEEProjectUtilities.isJCAProject(comp.getProject()))
			return convertConnectorVersionStringToJ2EEVersionID(version);
		if (JavaEEProjectUtilities.isApplicationClientProject(comp.getProject()))
			return convertAppClientVersionStringToJ2EEVersionID(version);
		return 0;
	}
	
	public static int convertVersionStringToInt(String version) {
		int nVersion = 0;
		
		if (J2EEVersionConstants.VERSION_1_0_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_0;
		
		else if (J2EEVersionConstants.VERSION_1_1_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_1;
		
		else if (J2EEVersionConstants.VERSION_1_2_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_2;
		
		else if (J2EEVersionConstants.VERSION_1_3_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_3;	
		
		else if (J2EEVersionConstants.VERSION_1_4_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_4;
		
		else if (J2EEVersionConstants.VERSION_1_5_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_5;
		
		else if (J2EEVersionConstants.VERSION_1_6_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_1_6;
		
		else if (J2EEVersionConstants.VERSION_2_0_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_2_0;
		
		else if (J2EEVersionConstants.VERSION_2_1_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_2_1;	
		
		else if (J2EEVersionConstants.VERSION_2_2_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_2_2;
		
		else if (J2EEVersionConstants.VERSION_2_3_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_2_3;
		
		else if (J2EEVersionConstants.VERSION_2_4_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_2_4;
		
		else if (J2EEVersionConstants.VERSION_2_5_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_2_5;	

		else if (J2EEVersionConstants.VERSION_3_0_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_3_0;			

		else if (J2EEVersionConstants.VERSION_3_1_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_3_1;	
		
		else if (J2EEVersionConstants.VERSION_3_2_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_3_2;	
		
		else if (J2EEVersionConstants.VERSION_5_0_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_5_0;			
		
		else if (J2EEVersionConstants.VERSION_6_0_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_6_0;	

		else if (J2EEVersionConstants.VERSION_7_0_TEXT.equals(version))
			nVersion = J2EEVersionConstants.VERSION_7_0;	

		return nVersion;
	}
	public static String convertVersionIntToString(int version) {
		String nVersion = null;
		
		if (version == J2EEVersionConstants.VERSION_1_0)
			nVersion = J2EEVersionConstants.VERSION_1_0_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_1_1)
			nVersion = J2EEVersionConstants.VERSION_1_1_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_1_2)
			nVersion = J2EEVersionConstants.VERSION_1_2_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_1_3)
			nVersion = J2EEVersionConstants.VERSION_1_3_TEXT;	
		
		else if (version == J2EEVersionConstants.VERSION_1_4)
			nVersion = J2EEVersionConstants.VERSION_1_4_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_1_5)
			nVersion = J2EEVersionConstants.VERSION_1_5_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_1_6)
			nVersion = J2EEVersionConstants.VERSION_1_6_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_2_0)
			nVersion = J2EEVersionConstants.VERSION_2_0_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_2_1)
			nVersion = J2EEVersionConstants.VERSION_2_1_TEXT;	
		
		else if (version == J2EEVersionConstants.VERSION_2_2)
			nVersion = J2EEVersionConstants.VERSION_2_2_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_2_3)
			nVersion = J2EEVersionConstants.VERSION_2_3_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_2_4)
			nVersion = J2EEVersionConstants.VERSION_2_4_TEXT;
		
		else if (version == J2EEVersionConstants.VERSION_2_5)
			nVersion = J2EEVersionConstants.VERSION_2_5_TEXT;		

		else if (version == J2EEVersionConstants.VERSION_3_0)
			nVersion = J2EEVersionConstants.VERSION_3_0_TEXT;		

		else if (version == J2EEVersionConstants.VERSION_3_1)
			nVersion = J2EEVersionConstants.VERSION_3_1_TEXT;	
		
		else if (version == J2EEVersionConstants.VERSION_3_2)
			nVersion = J2EEVersionConstants.VERSION_3_2_TEXT;	

		else if (version == J2EEVersionConstants.VERSION_5_0)
			nVersion = J2EEVersionConstants.VERSION_5_0_TEXT;		
		
		else if (version == J2EEVersionConstants.VERSION_6_0)
			nVersion = J2EEVersionConstants.VERSION_6_0_TEXT;	
	
		else if (version == J2EEVersionConstants.VERSION_7_0)
			nVersion = J2EEVersionConstants.VERSION_7_0_TEXT;	
	
		return nVersion;
	}
	
}
