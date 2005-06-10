/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
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
	
	public static int convertVersionStringToInt(IVirtualComponent comp) {
		if (comp.getComponentTypeId().equals(IModuleConstants.JST_WEB_MODULE))
			return convertWebVersionStringToJ2EEVersionID(comp.getVersion());
		if (comp.getComponentTypeId().equals(IModuleConstants.JST_EJB_MODULE))
			return convertEJBVersionStringToJ2EEVersionID(comp.getVersion());
		if (comp.getComponentTypeId().equals(IModuleConstants.JST_EAR_MODULE))
			return convertVersionStringToInt(comp.getVersion());
		if (comp.getComponentTypeId().equals(IModuleConstants.JST_CONNECTOR_MODULE))
			return convertConnectorVersionStringToJ2EEVersionID(comp.getVersion());
		if (comp.getComponentTypeId().equals(IModuleConstants.JST_APPCLIENT_MODULE))
			return convertAppClientVersionStringToJ2EEVersionID(comp.getVersion());
		
		return 0;
	}
	
	public static int convertVersionStringToInt(String version) {
		int nVersion = 0;
		
		if( version.endsWith("")){
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
}
