/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @author nagrawal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
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
		}
		return ""; //$NON-NLS-1$
	}

	public static String getJCATextVersion(int aVersion) {
		switch (aVersion) {
		case J2EEVersionConstants.JCA_1_0_ID:
			return J2EEVersionConstants.VERSION_1_0_TEXT;

		case J2EEVersionConstants.JCA_1_5_ID:
			return J2EEVersionConstants.VERSION_1_5_TEXT;

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
		// default
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	public static int convertEJBVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_1_1_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_0_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_1_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		// default
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	public static int convertWebVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_2_2_TEXT))
			return J2EEVersionConstants.J2EE_1_2_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_3_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_2_4_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		// default
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	public static int convertConnectorVersionStringToJ2EEVersionID(String version) {
		if (version.equals(J2EEVersionConstants.VERSION_1_0_TEXT))
			return J2EEVersionConstants.J2EE_1_3_ID;
		if (version.equals(J2EEVersionConstants.VERSION_1_5_TEXT))
			return J2EEVersionConstants.J2EE_1_4_ID;
		// default
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	public static int convertJ2EEVersionIDToEJBVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case J2EEVersionConstants.J2EE_1_2_ID:
				return J2EEVersionConstants.EJB_1_1_ID;
			case J2EEVersionConstants.J2EE_1_3_ID:
				return J2EEVersionConstants.EJB_2_0_ID;
			case J2EEVersionConstants.J2EE_1_4_ID:
				return J2EEVersionConstants.EJB_2_1_ID;
		}
		// default
		return J2EEVersionConstants.EJB_2_1_ID;
	}

	public static int convertJ2EEVersionIDToWebVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case J2EEVersionConstants.J2EE_1_2_ID:
				return J2EEVersionConstants.WEB_2_2_ID;
			case J2EEVersionConstants.J2EE_1_3_ID:
				return J2EEVersionConstants.WEB_2_3_ID;
			case J2EEVersionConstants.J2EE_1_4_ID:
				return J2EEVersionConstants.WEB_2_4_ID;
		}
		// default
		return J2EEVersionConstants.WEB_2_4_ID;
	}

	public static int convertJ2EEVersionIDToConnectorVersionID(int j2eeVersionId) {
		switch (j2eeVersionId) {
			case J2EEVersionConstants.J2EE_1_3_ID:
				return J2EEVersionConstants.JCA_1_0_ID;
			case J2EEVersionConstants.J2EE_1_4_ID:
				return J2EEVersionConstants.JCA_1_5_ID;
		}
		// default
		return J2EEVersionConstants.JCA_1_5_ID;
	}
	
	public static int convertVersionStringToInt(IVirtualComponent comp) {
		if (J2EEProjectUtilities.isDynamicWebProject(comp.getProject()))
			return convertWebVersionStringToJ2EEVersionID(comp.getVersion());
		if (J2EEProjectUtilities.isEJBProject(comp.getProject()))
			return convertEJBVersionStringToJ2EEVersionID(comp.getVersion());
		if (J2EEProjectUtilities.isEARProject(comp.getProject()))
			return convertVersionStringToInt(comp.getVersion());
		if (J2EEProjectUtilities.isJCAProject(comp.getProject()))
			return convertConnectorVersionStringToJ2EEVersionID(comp.getVersion());
		if (J2EEProjectUtilities.isApplicationClientProject(comp.getProject()))
			return convertAppClientVersionStringToJ2EEVersionID(comp.getVersion());
		return 0;
	}
	
	public static int convertVersionStringToInt(String version) {
		int nVersion = 0;
		
		if( version.endsWith("")){ //$NON-NLS-1$
			nVersion = 0;
		}
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
	
		return nVersion;
	}
	public static String convertVersionIntToString(int version) {
		String nVersion = null;
		
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
	
		return nVersion;
	}
	
}
