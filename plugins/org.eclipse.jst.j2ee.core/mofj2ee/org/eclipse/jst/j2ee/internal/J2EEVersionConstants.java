/*******************************************************************************
 * Copyright (c) 2001, 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Apr 16, 2003
 *
 */
package org.eclipse.jst.j2ee.internal;

/**
 * These constants are used with the XMLResource
 */
public interface J2EEVersionConstants {
	
	public int UNKNOWN = -1;
	public int EJB_TYPE = 0;
	public int APPLICATION_TYPE = 1;
	public int CONNECTOR_TYPE = 2;
	public int APPLICATION_CLIENT_TYPE = 3;
	public int WEB_TYPE = 4;
	public int WEBSERVICES_TYPE = 5;
	public int WEBFRAGMENT_TYPE = 6;
	
	public int J2EE_1_2_ID = 12;
	public int J2EE_1_3_ID = 13;
	public int J2EE_1_4_ID = 14;
	public int JEE_5_0_ID = 50;
	public int JEE_6_0_ID = 60;
	public int JEE_7_0_ID = 70;
	public int JEE_8_0_ID = 80;
	public int JEE_9_0_ID = 90;
	public int EJB_1_0_ID = 10;	
	public int EJB_1_1_ID = 11;	
	public int EJB_2_0_ID = 20;	
	public int EJB_2_1_ID = 21;	
	public int EJB_3_0_ID = 30;
	public int EJB_3_1_ID = 31;
	public int EJB_3_2_ID = 32;
	public int WEB_2_2_ID = 22;
	public int WEB_2_3_ID = 23;
	public int WEB_2_4_ID = 24;
	public int WEB_2_5_ID = 25;
	public int WEB_3_0_ID = 30;
	public int WEB_3_1_ID = 31;
	public int WEB_4_0_ID = 40;
	public int WEB_5_0_ID = 50;
	public int WEBFRAGMENT_3_0_ID = 30;
	public int WEBFRAGMENT_3_1_ID = 31;
	public int WEBFRAGMENT_4_0_ID = 40;
	public int TAGLIB_1_1_ID = 11;
	public int TAGLIB_1_2_ID = 12;
	public int TAGLIB_2_0_ID = 20;
	public int JSP_1_1_ID = 11;
	public int JSP_1_2_ID = 12;
	public int JSP_2_0_ID = 20;
	public int SERVLET_2_2 = 22;
	public int SERVLET_2_3 = 23;
	public int SERVLET_2_4 = 24;
	public int SERVLET_2_5 = 25;
	public int SERVLET_3_0 = 30;
	public int SERVLET_3_1 = 31;
	public int SERVLET_4_0 = 40;
	public int SERVLET_5_0 = 50;
	public int JCA_1_0_ID = 10;
	public int JCA_1_5_ID = 15;
	public int JCA_1_6_ID = 16;
	public int JCA_1_7_ID = 17;
	public int WEBSERVICES_1_0_ID = 10;
	public int WEBSERVICES_1_1_ID = 11;
	public int WEBSERVICES_1_2_ID = 12;
	public int WEBSERVICES_1_3_ID = 13;
	public int WEBSERVICES_1_4_ID = 14;
	public String J2EE_1_4_TEXT 		= "1.4"; //$NON-NLS-1$
	


	public int VERSION_1_0 = 10;	
	public int VERSION_1_1 = 11;	
	public int VERSION_1_2 = 12;	
	public int VERSION_1_3 = 13;	
	public int VERSION_1_4 = 14;
	public int VERSION_1_5 = 15;
	public int VERSION_1_6 = 16;
	public int VERSION_1_7 = 17;
	public int VERSION_1_8 = 18;
	public int VERSION_2_0 = 20;
	public int VERSION_2_1 = 21;	
	public int VERSION_2_2 = 22;	
	public int VERSION_2_3 = 23;	
	public int VERSION_2_4 = 24;	
	public int VERSION_2_5 = 25; 
	public int VERSION_3_0 = 30;
	public int VERSION_4_0 = 40;
	public int VERSION_3_1 = 31; 
	public int VERSION_3_2 = 32;
	public int VERSION_5_0 = 50; 
	public int VERSION_6_0 = 60; 
	public int VERSION_7_0 = 70;
	public int VERSION_8_0 = 80;
	
	public String VERSION_1_0_TEXT		= "1.0"; //$NON-NLS-1$
	public String VERSION_1_1_TEXT    	= "1.1"; //$NON-NLS-1$
	public String VERSION_1_2_TEXT		= "1.2"; //$NON-NLS-1$
	public String VERSION_1_3_TEXT		= "1.3"; //$NON-NLS-1$
	public String VERSION_1_4_TEXT		= "1.4"; //$NON-NLS-1$
	public String VERSION_1_5_TEXT		= "1.5"; //$NON-NLS-1$
	public String VERSION_1_6_TEXT		= "1.6"; //$NON-NLS-1$
	public String VERSION_1_7_TEXT		= "1.7"; //$NON-NLS-1$
	public String VERSION_1_8_TEXT		= "1.8"; //$NON-NLS-1$
	public String VERSION_2_0_TEXT		= "2.0"; //$NON-NLS-1$
	public String VERSION_2_1_TEXT		= "2.1"; //$NON-NLS-1$
	public String VERSION_2_2_TEXT		= "2.2"; //$NON-NLS-1$
	public String VERSION_2_3_TEXT		= "2.3"; //$NON-NLS-1$
	public String VERSION_2_4_TEXT		= "2.4"; //$NON-NLS-1$
	public String VERSION_2_5_TEXT		= "2.5"; //$NON-NLS-1$
	public String VERSION_3_0_TEXT		= "3.0"; //$NON-NLS-1$
	public String VERSION_3_1_TEXT		= "3.1"; //$NON-NLS-1$
	public String VERSION_3_2_TEXT		= "3.2"; //$NON-NLS-1$
	public String VERSION_4_0_TEXT		= "4.0"; //$NON-NLS-1$
	public String VERSION_5_0_TEXT		= "5.0"; //$NON-NLS-1$
	public String VERSION_6_0_TEXT		= "6.0"; //$NON-NLS-1$
	public String VERSION_7_0_TEXT		= "7.0"; //$NON-NLS-1$
	public String VERSION_8_0_TEXT		= "8.0"; //$NON-NLS-1$
	public String VERSION_5_TEXT        = "5";   //$NON-NLS-1$
	public String VERSION_6_TEXT        = "6";   //$NON-NLS-1$
	public String VERSION_7_TEXT        = "7";   //$NON-NLS-1$
	public String VERSION_8_TEXT        = "8";	//$NON-NLS-1$
	public String VERSION_9_TEXT        = "9";	//$NON-NLS-1$
					
}
