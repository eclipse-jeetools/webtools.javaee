/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jca.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.jca.AuthenticationMechanism;
import org.eclipse.jst.j2ee.jca.ConfigProperty;
import org.eclipse.jst.j2ee.jca.License;
import org.eclipse.jst.j2ee.jca.RequiredConfigPropertyType;
import org.eclipse.jst.j2ee.jca.SecurityPermission;


/**
 * @deprecated
 */
public class JCADescriptionHelper {
	public static void setDescription(ConfigProperty property, String description, int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	property.setDescription(description);
		    	break;
		    case J2EEVersionConstants.JCA_1_5_ID: {
		        Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(description);
				property.getDescriptions().add(descriptionObj);
		    }
		}
	}
	public static String getDescription(ConfigProperty property,int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	return property.getDescription();
		    case J2EEVersionConstants.JCA_1_5_ID: {
				 EList localizedDescriptions = property.getDescriptions();
				 if(localizedDescriptions == null || localizedDescriptions.size() == 0)
				 	return null;
			     Description localizedDescription = (Description)localizedDescriptions.get(0);
			     if(localizedDescription != null)
			     	return localizedDescription.getValue();
		    }
		}
		return null;
	}
	public static void setDescription(SecurityPermission property, String description, int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	property.setDescription(description);
		    	break;
		    case J2EEVersionConstants.JCA_1_5_ID: {
		        Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(description);
				property.getDescriptions().add(descriptionObj);
		    }
		}
	}
	
	public static String getDescription(SecurityPermission property,int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	return property.getDescription();
		    case J2EEVersionConstants.JCA_1_5_ID: {
				 EList localizedDescriptions = property.getDescriptions();
				 if(localizedDescriptions == null || localizedDescriptions.size() == 0)
				 	return null;
			     Description localizedDescription = (Description)localizedDescriptions.get(0);
			     if(localizedDescription != null)
			     	return localizedDescription.getValue();
		    }
		}
		return null;
	}
	public static void setDescription(License property, String description, int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	property.setDescription(description);
		    	break;
		    case J2EEVersionConstants.JCA_1_5_ID: {
		        Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(description);
				property.getDescriptions().add(descriptionObj);
		    }
		}
	}
	
	public static String getDescription(License property,int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	return property.getDescription();
		    case J2EEVersionConstants.JCA_1_5_ID: {
				 EList localizedDescriptions = property.getDescriptions();
				 if(localizedDescriptions == null || localizedDescriptions.size() == 0)
				 	return null;
			     Description localizedDescription = (Description)localizedDescriptions.get(0);
			     if(localizedDescription != null)
			     	return localizedDescription.getValue();
		    }
		}
		return null;
	}
	public static void setDescription(AuthenticationMechanism property, String description, int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	property.setDescription(description);
		    	break;
		    case J2EEVersionConstants.JCA_1_5_ID: {
		        Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(description);
				property.getDescriptions().add(descriptionObj);
		    }
		}
	}
	
	public static String getDescription(AuthenticationMechanism property,int jcaVersionID) {
		switch(jcaVersionID) {
		    case J2EEVersionConstants.JCA_1_0_ID: 
		    	return property.getDescription();
		    case J2EEVersionConstants.JCA_1_5_ID: {
				 EList localizedDescriptions = property.getDescriptions();
				 if(localizedDescriptions == null || localizedDescriptions.size() == 0)
				 	return null;
			     Description localizedDescription = (Description)localizedDescriptions.get(0);
			     if(localizedDescription != null)
			     	return localizedDescription.getValue();
		    }
		}
		return null;
	}
	public static void setDescription(RequiredConfigPropertyType property, String description, int jcaVersionID) {
		if(jcaVersionID == J2EEVersionConstants.JCA_1_5_ID) {
	        Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
			descriptionObj.setValue(description);
			property.getDescriptions().add(descriptionObj);
	    }
	}
	public static String getDescription(RequiredConfigPropertyType property,int jcaVersionID) {
		if(jcaVersionID == J2EEVersionConstants.JCA_1_5_ID) {
			 EList localizedDescriptions = property.getDescriptions();
			 if(localizedDescriptions == null || localizedDescriptions.size() == 0)
			 	return null;
		     Description localizedDescription = (Description)localizedDescriptions.get(0);
		     if(localizedDescription != null)
		     	return localizedDescription.getValue();
	    }
		return null;
	}
}
