/*
 * Created on Mar 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

/**
 * @author nagrawal
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class J2EEVersionUtil {
	
	public static String getServletTextVersion(int aVersion){
		switch(aVersion){
			case J2EEVersionConstants.SERVLET_2_2: 
				return J2EEVersionConstants.VERSION_2_2_TEXT;
				
			case J2EEVersionConstants.SERVLET_2_3:
				return J2EEVersionConstants.VERSION_2_3_TEXT;
				
			case J2EEVersionConstants.SERVLET_2_4:
				return J2EEVersionConstants.VERSION_2_4_TEXT;
			}
			return "";	//$NON-NLS-1$
			
		}
		
		public static String getEJBTextVersion(int aVersion){	
			
			switch(aVersion){
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
		
		
		public static String getJCATextVersion(int aVersion){
			switch(aVersion){
				case J2EEVersionConstants.JCA_1_0_ID:
					return J2EEVersionConstants.VERSION_1_0_TEXT;
				
				case J2EEVersionConstants.JCA_1_5_ID:
					return J2EEVersionConstants.VERSION_1_5_TEXT;
				
			}
			return ""; //$NON-NLS-1$
		}
		
		public static String getJ2EETextVersion(int aVersion){
			switch(aVersion){
				case J2EEVersionConstants.J2EE_1_2_ID:
					return J2EEVersionConstants.VERSION_1_2_TEXT;
				
				case J2EEVersionConstants.J2EE_1_3_ID:
					return J2EEVersionConstants.VERSION_1_3_TEXT;
				
				case J2EEVersionConstants.J2EE_1_4_ID:
					return J2EEVersionConstants.VERSION_1_4_TEXT;				
				
			}
			return "";
		}		
}
