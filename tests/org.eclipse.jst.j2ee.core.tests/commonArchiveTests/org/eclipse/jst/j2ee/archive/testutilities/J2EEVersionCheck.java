/*
 * Created on Apr 14, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.archive.testutilities;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.jca.JcaPackage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

public class J2EEVersionCheck {
	public int version;
	//static versions
	public static final int VERSION_1_2 = 0;
	public static final int VERSION_1_3 = 1;
	public static final int VERSION_1_4 = 2;
	
	//modules
	public static final int APPICATION = 0;
	public static final int APP_CLIENT = 1;
	public static final int CONNECTOR = 2;
	public static final int EJB = 3;
	public static final int WEB = 4;

	public static List cur_Tags;
	private static CommonPackage COM = CommonPackage.eINSTANCE;

    public J2EEVersionCheck() {
        super();
    }
	public static boolean checkAttributeVersion(EAttribute att, int version, int type){
		//TODO: Add version 1.2 excludes
		if(version == VERSION_1_3 || version == VERSION_1_2){
			if(cur_Tags == null){
				cur_Tags = new ArrayList();
				populate_1_3List(type);
			}
		}
		else if(version == VERSION_1_4){
			return true;
		}
		
		if(cur_Tags.contains(att) || cur_Tags.contains(att.getEType()))
			return false;
		return true;
	}
    
     public static boolean checkReferenceVersion(EStructuralFeature ref, int version, int type){
		//TODO: Add version 1.2 excludes
		if(version == VERSION_1_3 || version == VERSION_1_2){
			if(cur_Tags == null){
				cur_Tags = new ArrayList();
				populate_1_3List(type);
			}
		}
		else if(version == VERSION_1_4){
			return true;
		}
		
		if(cur_Tags.contains(ref) || cur_Tags.contains(ref.getEType()))
			return false;
		return true;
    }
       
	private static void populate_1_2List(int type) {
		switch (type) {
			case APPICATION :
				populate_1_2Application();
				break;
			case APP_CLIENT :
				populate_1_2AppClient();
				break;
			case EJB :
				populate_1_2EJB();
				break;
			case CONNECTOR :
				populate_1_2EJB();
				break;
			case WEB :
				populate_1_2Web();
			default :
				break;
		}
	}
	
	private static void populate_1_3List(int type) {
		switch (type) {
			case APPICATION :
				populate_1_3Application();
				break;
			case APP_CLIENT :
				populate_1_3AppClient();
				break;
			case EJB :
				populate_1_3EJB();
				break;
			case CONNECTOR :
				populate_1_3EJB();
				break;
			case WEB :
				populate_1_3Web();
			default :
				break;
		}
	}

    private static void populate_1_2Application(){
		populate_1_3Application();
    }

	private static void populate_1_2AppClient(){
		populate_1_3AppClient();
	}
	
	private static void populate_1_2EJB(){
		populate_1_3EJB();
	}
	
	private static void populate_1_2Connector(){
		populate_1_3Connector();
	}
	
	private static void populate_1_2Web(){
		populate_1_3Web();
	}

	//1.3
	private static void populate_1_3Application(){
		CommonPackage COM = CommonPackage.eINSTANCE;
		cur_Tags.add(COM.getIconType());
		cur_Tags.add(COM.getDisplayName());
		cur_Tags.add(COM.getDescription());
		cur_Tags.add(COM.getDescriptionGroup());
		cur_Tags.add(COM.getSecurityIdentity_Descriptions());
		cur_Tags.add(ApplicationPackage.eINSTANCE.getApplication_Version());	
	}
	
	private static void populate_1_3AppClient(){
		cur_Tags.add(COM.getIconType());
		cur_Tags.add(COM.getDisplayName());
		cur_Tags.add(COM.getDescription());
		cur_Tags.add(COM.getDescriptionGroup());
		cur_Tags.add(ClientPackage.eINSTANCE.getApplicationClient_Version());
		cur_Tags.add(ClientPackage.eINSTANCE.getApplicationClient_ServiceRefs());
		cur_Tags.add(ClientPackage.eINSTANCE.getApplicationClient_MessageDestinationRefs());
		cur_Tags.add(ClientPackage.eINSTANCE.getApplicationClient_MessageDestinations());
		cur_Tags.add(COM.getMessageDestination());
		cur_Tags.add(COM.getMessageDestinationRef());
		//cur_Tags.add(ClientPackage.eINSTANCE.getApplicationClient_ServiceRefs());
	}
	
	private static void populate_1_3Connector(){
		cur_Tags.add(COM.getIconType());
		cur_Tags.add(COM.getDisplayName());
		cur_Tags.add(COM.getDescription());
		cur_Tags.add(COM.getDescriptionGroup());
		cur_Tags.add(JcaPackage.eINSTANCE.getOutboundResourceAdapter());
		cur_Tags.add(JcaPackage.eINSTANCE.getInboundResourceAdapter());
		cur_Tags.add(JcaPackage.eINSTANCE.getAdminObject());
		cur_Tags.add(JcaPackage.eINSTANCE.getConnector_Version());
	}
	
    private static void populate_1_3EJB() {
		cur_Tags.add(COM.getIconType());
		cur_Tags.add(COM.getDisplayName());
		cur_Tags.add(COM.getDescription());
		cur_Tags.add(COM.getDescriptionGroup());
		cur_Tags.add(EjbPackage.eINSTANCE.getEJBJar_Version());
		cur_Tags.add(EjbPackage.eINSTANCE.getSession_ServiceEndpoint());
		cur_Tags.add(COM.getJNDIEnvRefsGroup_MessageDestinationRefs());
		cur_Tags.add(COM.getJNDIEnvRefsGroup_ServiceRefs());
		cur_Tags.add(EjbPackage.eINSTANCE.getMessageDriven_MessagingType());
		cur_Tags.add(EjbPackage.eINSTANCE.getMessageDriven_MessageDestination());
		cur_Tags.add(EjbPackage.eINSTANCE.getMessageDriven_Link());
		cur_Tags.add(EjbPackage.eINSTANCE.getMessageDriven_ActivationConfig());	
    }

	private static void populate_1_3Web(){
		cur_Tags.add(COM.getIconType());
		cur_Tags.add(COM.getDisplayName());
		cur_Tags.add(COM.getDescription());
		cur_Tags.add(COM.getDescriptionGroup());
		cur_Tags.add(WebapplicationPackage.eINSTANCE.getWebApp_Version());
		cur_Tags.add(WebapplicationPackage.eINSTANCE.getWebApp_JspConfig());
		cur_Tags.add(WebapplicationPackage.eINSTANCE.getWebApp_MessageDestinations());
		cur_Tags.add(WebapplicationPackage.eINSTANCE.getWebApp_LocalEncodingMappingList());
	}

}
