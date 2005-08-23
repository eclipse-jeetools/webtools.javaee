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
package org.eclipse.jst.j2ee.model.internal.validation;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.internal.impl.ApplicationClientImpl;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.internal.impl.EjbRefImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ValidateXmlCommand;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.NoModuleFileException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;


/**
 * Insert the type's description here.
 * Creation date: (12/6/2000 11:08:55 AM)
 * @author: Administrator
 */
public class EarValidator extends org.eclipse.jst.j2ee.model.internal.validation.J2EEValidator implements EARMessageConstants {
	public static final String RES_REF_GROUP_NAME = "RES_REF_GROUP_NAME"; //$NON-NLS-1$
	public static final String RES_ENV_REF_GROUP_NAME = "RES_ENV_REF_GROUP_NAME"; //$NON-NLS-1$
	public static final String SERVICE_REF_GROUP_NAME = "SERVICE_REF_GROUP_NAME"; //$NON-NLS-1$
	public static final String EJB_REF_GROUP_NAME = "EJB_REF_GROUP_NAME"; //$NON-NLS-1$
	public static final String SEC_ROLE_REF_GROUP_NAME = "SEC_ROLE_REF_GROUP_NAME"; //$NON-NLS-1$
	public static final String MESSAGE_REF_GROUP_NAME = "MESSAGE_REF_GROUP_NAME"; //$NON-NLS-1$
	protected EARFile earFile;
	protected Application appDD;
	
	/**
	 * RelationshipMapValidator constructor comment.
	 */
	public EarValidator() {
		super();
	}// EarValidator

	/**
	 * Creates the validateXML command.
	 * 
	 * @return Command
	 */
	public Command createValidateXMLCommand() {
		Command cmd = new ValidateXmlCommand(earFile);
		return cmd;
	}// createValidateXMLCommand
	
	/**
	 * <p>Answer the id of the resource bundle which is
	 * used by the receiver.</p>
	 * 
	 * @return String
	 */
	public String getBaseName() {
		return EAR_CATEGORY;
	}// getBaseName
	
	/**
	 * XML Validation now handles validation of Deployment Descriptor
	 * 
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
	  validateModules(appDD.getModules());
	  validateSecurity();
	  validateRefs();
	  validateWebContexts();
	  validateSpecLevel();
	}// validate
	
	/**
	 * Does the validation
	 */
	public void validate(IValidationContext inHelper, IReporter inReporter) throws ValidationException {
		inReporter.removeAllMessages(this);
		super.validate(inHelper, inReporter);
		try {
			earFile = (EARFile) _helper.loadModel(EAR_MODEL_NAME);
			if (earFile != null) {
				appDD = earFile.getDeploymentDescriptor();
				if (appDD != null && appDD.eResource() != null && appDD.eResource().isLoaded())
					validate();
				else {
					IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, EAR_DD_CANNOT_OPEN_DD, new String[] { getResourceName()});
					throw new ValidationException(errorMsg);
				}
			} else {
				IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_EAR_INVALID_EAR_FILE, new String[] { getResourceName()});
				throw new ValidationException(errorMsg);
			} // if
		} catch (ValidationException ex) {
			throw ex;
		} catch (Exception e) {
			String[] param = new String[1];
			if (earFile != null)
				param[0] = earFile.getName();
			Logger.getLogger().logError(e);
			IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, EAR_VALIDATION_INTERNAL_ERROR_UI_, param);
			throw new ValidationException(errorMsg, e);
		} // try 
	} // validate

	
	/**
	 * Gets the resource name
	 * 
	 * @return String
	 */
	protected String getResourceName() {
		return earFile.getURI();
	}// getResourceName
	
	/**
	 * validate for duplicates in EAR Roles
	 * 
	 * @param EList earRoleList - List of ear roles.
	 */
	public void validateEarRoles(EList earRoleList) {
		Set s = new HashSet(earRoleList.size());
		for (int i = 0; i < earRoleList.size(); i++) {
			SecurityRole securityRole = (SecurityRole) earRoleList.get(i);
			if (!(s.add(securityRole.getRoleName()))) {
				String roleName = securityRole.getRoleName();
				String[] params = new String[1];
				params[0] = roleName;
				addWarning(getBaseName(), ERROR_EAR_DUPLICATE_ROLES, params, appDD );
			}// if
		}// for
	}// validateEarRoles
	
	/**
	 * validateRefs(WebApp) - validate EJB  references
	 * 
	 * @param EjbRef eref - An ejb ref.
	 * @param Sting uri - The uri of the module.
	 */
	public void validateEJBRefMandatoryElements(List ejbRefs, String uri) {
	 for(int i = 0; i < ejbRefs.size(); i++) {
	  EjbRef eref = (EjbRef) ejbRefs.get(i);
	  String refName = eref.getName();
	  String[] params = new String[2];
	  params[0] = refName;
	  params[1] = uri;
	  
	  if ((eref.getName().trim() == null) || (eref.getName().trim().length() == 0)) {
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFNAME, params);
	  }// if
	  if (eref.isSetType())
	    {if(eref.getType() == null ) 
		  addWarning(EREF_CATEGORY, ERROR_EAR_INVALID_EREFTYPE, params);}
	  else 
	  	 addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFTYPE, params);
	  // if
	  if ((eref.getHome() == null) || (eref.getHome().trim().length() == 0)) {
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFHOME, params);
	  }// if
	  if ((eref.getRemote() == null) || (eref.getRemote().trim().length() == 0)) {
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFREMOTE, params);
	  }
	 }
	}
	
	/**
	 * Compare the ejb interfaces to check if they are similar.
	 * 
	 * @param EjbRef eref - The ejb reference.
	 * @param EnterpriseBean ejb - The enterprise bean
	 */
	public boolean isSimilarEJBInterface(EjbRef eref, EnterpriseBean ejb) {
		
		if( eref.isLocal() ) {
			if( ejb.getLocalHomeInterfaceName() == null ||
				ejb.getLocalInterfaceName() == null || 
				((EJBLocalRef)eref).getLocalHome() == null  || 
				((EJBLocalRef)eref).getLocal() == null ) {
				return false;
			}// if
			
			boolean isHomeLocalOk = ejb.getLocalHomeInterfaceName().equals(((EJBLocalRef)eref).getLocalHome());
			boolean isRemoteLocalOk =  ejb.getLocalInterfaceName().equals(((EJBLocalRef)eref).getLocal());
		 	return isHomeLocalOk && isRemoteLocalOk;
		}// if
		
		if( ejb.getHomeInterfaceName() == null ||
			ejb.getRemoteInterfaceName() == null || 
			eref.getHome() == null  || 
			eref.getRemote() == null ) {
			return false;
		}// if
		
		boolean isHomeOk = ejb.getHomeInterfaceName().equals( eref.getHome() );
		boolean isRemoteOk = ejb.getRemoteInterfaceName().equals( eref.getRemote() );
		
		return isHomeOk && isRemoteOk;
	}// isSimilarEJBInterface
		
	/**
	 * validate for duplicates in EAR Roles
	 * 
	 * @param List ejbRefs - List of ejb refs.
	 * @param String uri - The uri of the module.
	 */
	public void validateEJBRefs(List ejbRefs, String uri) {
	  for (int i = 0; i < ejbRefs.size(); i++) {
	  	  EjbRef eref = (EjbRef) ejbRefs.get(i);
		  if( eref != null && eref.getLink() != null && eref.getLink().length() > 0) {
		  	EnterpriseBean ejb = earFile.getEnterpiseBeanFromRef( eref, uri );
		  	if( ejb == null ) {
		  		String[] params = new String[3];
				params[0] = eref.getName();
				params[1] = uri;
				params[2] = earFile.getName();
		  		addWarning(EREF_CATEGORY,UNRESOLVED_EJB_REF_WARN_,params);
		  	} else {
		  		if( !isSimilarEJBInterface( eref, ejb ) ) {
		  			String[] params = new String[3];
					params[0] = ejb.getName();
					params[1] = eref.getName();
					params[2] = uri;
		  			addError( getBaseName(), EJB_BEAN_EJB_LINK_INTEFACE_MISMATCH_ERROR_, params, appDD);
		  		}
		  	}
		  }
	  }
	}
	
	/**
	 * validate the existance of the EJB Roles in the EAR Roles and duplicates ib EJB Roles
	 * 
	 * @param EList earRoleList - List of ear roles.
	 * @param EList ejbRoles - List of ejb roles.
	 */
	public void validateEJBRolesWithEARRoles(EList earRoleList, EList ejbRoles) {
	  for (int i = 0; i < ejbRoles.size(); i++) {
		if (!(earRoleList.contains(ejbRoles.get(i)))) {
		  String[] params = new String[1];
		  params[0] = ((SecurityRole) (ejbRoles.get(i))).getRoleName();		          
		  addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EJB_ROLE, params);
		}// if
	  }// for
	}// validateEJBRolesWithEARRoles
	
	/**
	 * For each module, make sure its archive exists
	 *(a very expensive, but more meaningful test would be to also make sure they can be loaded)
	 * 
	 * @param EList modulesList - List of modules
	 */
	public void validateModules(EList modulesList) {
//		String errorString = ""; //$NON-NLS-1$
		HashSet duplicateURI = new HashSet();
	
		for (int i = 0; i < modulesList.size(); i++) {
			Module m = (Module) modulesList.get(i);
			String filename = m.getUri();
			if ((filename == null) || (filename.length() == 0)) {
				addError(getBaseName(), MESSAGE_EAR_NO_MODULE_URI, null);
			} else {
				//check if the URI has any spaces
				if(  filename.indexOf( " " ) != -1) { //$NON-NLS-1$
					String[] params = new String[1];
					params[0] = filename;
					addError(getBaseName(), URI_CONTAINS_SPACES_ERROR_, params, appDD);
				}// if
			}// if
			String altDD = m.getAltDD();
			if (altDD != null)
				altDD = altDD.trim();
			validateAltDD(m, altDD);
			String key = altDD == null ? filename + altDD : filename;
			if (!duplicateURI.add(key)) {
				String[] params = new String[2];
				params[0] = m.getUri();
				params[1] = earFile.getName();
				addError(getBaseName(), MESSAGE_EAR_DUPLICATE_URI_ERROR_, params, appDD);
			}// if
	
		}// for
	
	}// validateModules
	
	/**
	 * Validate the alt dd
	 * 
	 * @param Module m - A module.
	 * @param String altDD - An altDD
	 */
	protected void validateAltDD(Module m, String altDD) {
		//isDuplicate will test if it is a file or a loaded resource
		if ("".equals(altDD)) { //$NON-NLS-1$
			String[] params = new String[2];
			params[0] = m.getUri();
			params[1] = earFile.getName();
			addError(getBaseName(), MESSAGE_EMPTY_ALT_DD_ERROR_, params, appDD);
		} else if (altDD != null && !earFile.isDuplicate(altDD)) {
			String[] params = new String[3];
			params[0] = m.getUri();
			params[1] = altDD;
			params[2] = earFile.getName();
			addWarning(getBaseName(), MESSAGE_INVALID_ALT_DD_WARN_, params, appDD);
		}// if
	}// validateAltDD
	
	/**
	 * validate EJB and resource references
	 */
	public void validateRefs() {
		List moduleList = earFile.getModuleRefs();
		for (int i = 0; i < moduleList.size(); i++) {
			
			ModuleRef ref = (ModuleRef) moduleList.get(i);
			try {
				if(ref.isWeb()) {
					validateWebAppRefs(ref);				}  
				else if( ref.isEJB() ) {
				    validateEJBModuleRefs(ref);
				} else if(ref.isClient())
					validateAppClientRefs(ref);
			} catch (ArchiveWrappedException ex) {
				Exception nested = ex.getNestedException();
				if (!(nested instanceof NoModuleFileException)) 
					Logger.getLogger().logError(ex);
				//otherwise ignore it; there are other validations for this
			} 
			
		}
	}	
	
	/**
	 * @param ref
	 */
	private void validateAppClientRefs(ModuleRef ref) throws ArchiveWrappedException {
		ApplicationClient appClient = (ApplicationClientImpl)ref.getDeploymentDescriptor();
		List ejbRefs = new ArrayList();
	    ejbRefs.addAll(appClient.getEjbReferences());
		validateEJBRefMandatoryElements(ejbRefs, ref.getUri());
		validateEJBRefs(ejbRefs, ref.getUri());
		if (appClient != null && appClient.getVersionID() <= J2EEVersionConstants.J2EE_1_3_ID) {
			Set allRefs = new HashSet();
			List resourceRefs = appClient.getResourceRefs();
			List resourceEnvRefs = appClient.getResourceEnvRefs();
			List serviceRefs = appClient.getServiceRefs();
			    
			validateDuplicateEJBRefs(allRefs,ejbRefs);
			validateDuplicateResourceRefs(allRefs,resourceRefs);
			validateDuplicateResourceEnvRefs(allRefs,resourceEnvRefs);
			validateDuplicateServiceRefs(allRefs,serviceRefs);
		}
		
	}

	/**
	 * @param ref
	 * @throws ArchiveWrappedException
	 */
	private void validateWebAppRefs(ModuleRef ref) throws ArchiveWrappedException {
		WebApp webApp = (WebApp)ref.getDeploymentDescriptor();
		List ejbRefs = new ArrayList();
	    ejbRefs.addAll(webApp.getEjbRefs());
	    ejbRefs.addAll(webApp.getEjbLocalRefs());
		validateEJBRefMandatoryElements(ejbRefs, ref.getUri());
		validateEJBRefs(ejbRefs, ref.getUri());
		if (webApp != null && webApp.getVersionID() <= J2EEVersionConstants.WEB_2_3_ID) {
			Set allRefs = new HashSet();
			List resourceRefs = webApp.getResourceRefs();
			List resourceEnvRefs = webApp.getResourceEnvRefs();
			List serviceRefs = webApp.getServiceRefs();
			
			validateDuplicateEJBRefs(allRefs,ejbRefs);
			validateDuplicateResourceRefs(allRefs,resourceRefs);
			validateDuplicateResourceEnvRefs(allRefs,resourceEnvRefs);
			validateDuplicateServiceRefs(allRefs,serviceRefs);
		}
	}
	/**
	 * @param ref
	 * @throws ArchiveWrappedException
	 */
	private void validateEJBModuleRefs(ModuleRef ref) throws ArchiveWrappedException {
		EJBJar ejbJar = (EJBJar)ref.getDeploymentDescriptor();
		if( ejbJar != null ) {
			List ejbCollection = ejbJar.getEnterpriseBeans();			
			if( ejbCollection != null || !ejbCollection.isEmpty() ) {
				Resource res = ejbJar.eResource();
				cleanUpAllRefSubTaskMessages(res);
				Iterator iterator = ejbCollection.iterator();	
				while( iterator.hasNext() ) {
					EnterpriseBean ejbBean = (EnterpriseBean)iterator.next();
					if( ejbBean != null ) {
					    List ejbRefs = new ArrayList();
					    ejbRefs.addAll(ejbBean.getEjbRefs());
					    ejbRefs.addAll(ejbBean.getEjbLocalRefs());
						validateEJBRefMandatoryElements(ejbRefs, ref.getUri());
						validateEJBRefs(ejbRefs, ref.getUri());
						if(ejbJar.getVersionID() <= J2EEVersionConstants.EJB_2_0_ID) {
							Set allRefs = new HashSet();
							List resourceRefs = ejbBean.getResourceRefs();
							List resourceEnvRefs = ejbBean.getResourceEnvRefs();
							List secRoleRefs = ejbBean.getSecurityRoleRefs();
							List serviceRefs = ejbBean.getServiceRefs();
							List messageDestRefs = ejbBean.getMessageDestinationRefs();
							    
							validateDuplicateEJBRefs(allRefs,ejbRefs);
							validateDuplicateResourceRefs(allRefs,resourceRefs);
							validateDuplicateResourceEnvRefs(allRefs,resourceEnvRefs);
							validateDuplicateSecurityRoleRefs(allRefs,secRoleRefs);
							validateDuplicateServiceRefs(allRefs,serviceRefs);
							validateDuplicateMessageDestRefs(allRefs,messageDestRefs);
					   }
					}
				}
			}
		}
	 
	}

	/**
	 * @param allRefs
	 * @param serviceRefs
	 */
	private void validateDuplicateServiceRefs(Set allRefs, List serviceRefs) {
		if (!serviceRefs.isEmpty()) {
			ServiceRef firstRef = (ServiceRef)(serviceRefs.get(0));
		if(!(firstRef.eContainer() instanceof EnterpriseBean))
			cleanUpSubTaskMessages(firstRef);
		for (int refNo = 0; refNo < serviceRefs.size(); refNo++) {
			ServiceRef ref = (ServiceRef) (serviceRefs.get(refNo));
			String refName = ref.getServiceRefName();
			String[] parms = new String[1];
			parms[0] = refName;
			if (!(allRefs.add(refName)))
			  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_SERVICEREF, parms,ref,SERVICE_REF_GROUP_NAME);
		}
	  }
	}

	/**
	 * @param allRefs
	 * @param secRoleRefs
	 */
	private void validateDuplicateSecurityRoleRefs(Set allRefs, List secRoleRefs) {
		if (!secRoleRefs.isEmpty()) {
			SecurityRoleRef firstRef = (SecurityRoleRef)(secRoleRefs.get(0));
		if(!(firstRef.eContainer() instanceof EnterpriseBean))
			cleanUpSubTaskMessages(firstRef);
		for (int refNo = 0; refNo < secRoleRefs.size(); refNo++) {
			SecurityRoleRef ref = (SecurityRoleRef) (secRoleRefs.get(refNo));
			String refName = ref.getName();
			String[] parms = new String[1];
			parms[0] = refName;
			if (!(allRefs.add(refName)))
			  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_SECURITYROLEREF, parms,ref,SEC_ROLE_REF_GROUP_NAME);
		}
	  }
	}

	/**
	 * @param allRefs
	 * @param resourceEnvRefs
	 */
	private void validateDuplicateResourceEnvRefs(Set allRefs, List resourceEnvRefs) {
		if (!resourceEnvRefs.isEmpty()) {
			ResourceEnvRef firstRef = (ResourceEnvRef)(resourceEnvRefs.get(0));
		if(!(firstRef.eContainer() instanceof EnterpriseBean))
			cleanUpSubTaskMessages(firstRef);
		for (int refNo = 0; refNo < resourceEnvRefs.size(); refNo++) {
			ResourceEnvRef ref = (ResourceEnvRef) (resourceEnvRefs.get(refNo));
			String refName = ref.getName();
			String[] parms = new String[1];
			parms[0] = refName;
			if (!(allRefs.add(refName)))
			  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_RESENVREF, parms,ref,RES_ENV_REF_GROUP_NAME);
		}
	  }
	}

	/**
	 * @param allRefs
	 * @param resourceRefs
	 */
	private void validateDuplicateResourceRefs(Set allRefs, List resourceRefs) {
		if (!resourceRefs.isEmpty()) {
			ResourceRef firstRef = (ResourceRef)(resourceRefs.get(0));
		if(!(firstRef.eContainer() instanceof EnterpriseBean))
			cleanUpSubTaskMessages(firstRef);
		for (int refNo = 0; refNo < resourceRefs.size(); refNo++) {
			ResourceRef ref = (ResourceRef) (resourceRefs.get(refNo));
			String refName = ref.getName();
			String[] parms = new String[1];
			parms[0] = refName;
			if (!(allRefs.add(refName)))
			  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_RESREF, parms,ref,RES_REF_GROUP_NAME);
		}
	  }
	}
	/**
	 * @param allRefs
	 * @param ejbRefs
	 */
	protected void validateDuplicateEJBRefs(Set allRefs, List ejbRefs) {
		if (!ejbRefs.isEmpty()) {
			EjbRef firstRef = (EjbRef)(ejbRefs.get(0));
		if(!(firstRef.eContainer() instanceof EnterpriseBean))
			cleanUpSubTaskMessages(firstRef);
		for (int refNo = 0; refNo < ejbRefs.size(); refNo++) {
			EjbRef ref = (EjbRefImpl) (ejbRefs.get(refNo));
			String refName = ref.getName();
			String[] parms = new String[1];
			parms[0] = refName;
			if (!(allRefs.add(refName)))
			  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_EJBREF, parms, ref, EJB_REF_GROUP_NAME);
		 }
		}
	}
	
	/**
	 * @param allRefs
	 * @param ejbRefs
	 */
	protected void validateDuplicateMessageDestRefs(Set allRefs, List messageDestRefs) {
		if (!messageDestRefs.isEmpty()) {
			MessageDestinationRef firstRef = (MessageDestinationRef)(messageDestRefs.get(0));
		if(!(firstRef.eContainer() instanceof EnterpriseBean))
			cleanUpSubTaskMessages(firstRef);
		for (int refNo = 0; refNo < messageDestRefs.size(); refNo++) {
			MessageDestinationRef ref = (MessageDestinationRef) (messageDestRefs.get(refNo));
			String refName = ref.getName();
			String[] parms = new String[1];
			parms[0] = refName;
			if (!(allRefs.add(refName)))
			  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_MESSSAGEDESTINATIONREF, parms,ref, MESSAGE_REF_GROUP_NAME);
		 }
		}
	}

	/**
	 * @param firstRef
	 */
	protected void cleanUpSubTaskMessages(EObject ref) {
		//No Op - subclass overrides 
	}
	
	protected void cleanUpAllRefSubTaskMessages(Resource res) {
		//No Op - subclass overrides 
	}

	/**
	 * Ejb refs to the running list of ejb refs.
	 * 
	 * @param List currentRefs - List of current ejb refs to be added.
	 * @param List ejbRefs - collection of running ejbrefs
	 */
	public void addEJBRefs( List currentRefs, List ejbRefs ) {		
		if( currentRefs == null || ejbRefs == null )
			return;
		
		ejbRefs.addAll( currentRefs );									
	}// addEJBRefs

	/**
	 * validate security constraints, roles, and security role refs.
	 */
	public void validateSecurity() {
		EList earRoleList = appDD.getSecurityRoles();
		if (!earRoleList.isEmpty())
			validateEarRoles(earRoleList);
		EList moduleList = appDD.getModules();
		if (!moduleList.isEmpty()) {
	
			for (int i = 0; i < moduleList.size(); i++) {
				Module m = (Module) moduleList.get(i);
				if (m.isEjbModule()) {
					EList ejbRoles = m.getApplication().getSecurityRoles();
					if (!ejbRoles.isEmpty())
						validateEJBRolesWithEARRoles(earRoleList, ejbRoles);
				}// if
				if (m.isWebModule()) {
					EList webRoles = m.getApplication().getSecurityRoles();
					if (!webRoles.isEmpty())
						validateWEBRolesWithEARRoles(earRoleList, webRoles);
				}// if
			}// for
		}// if
	}// validateSecurity
	
	/**
	 * Validates an ear for duplicate web contexts.
	 */
	public void validateWebContexts() {
		
		EList moduleList = appDD.getModules();
	  	Map visitedWebContext = new HashMap();
	  	
	  	if( !moduleList.isEmpty() ) {		
			Iterator iterator = moduleList.iterator();		
			while( iterator.hasNext() ) {
		  		Module module = (Module)iterator.next();
		  		if( module.isWebModule() ) {
		  			
		  			WebModule webModule = (WebModule)module;
			  			
			  		if( webModule != null ) {
			  			if( visitedWebContext.containsKey( webModule.getContextRoot() ) ) {
			  				WebModule tempWebModule = (WebModule)visitedWebContext.get( webModule.getContextRoot() );
							String[] params = new String[3];
							params[0] = webModule.getContextRoot();
							params[1] = webModule.getUri();
							params[2] = tempWebModule.getUri();
			  				addError(getBaseName(), MESSAGE_EAR_DUPICATE_ROOTCONTEXT_ERROR_, params, appDD);
			  			} else {
			  				visitedWebContext.put( webModule.getContextRoot(), webModule );
			  			}// if
			  				
			  		}// if
		  			
		  		}// if
			}// while
	  	}// if
	}// validateWebContexts
	
	
	/**
	 * Checks if the modules are a spec level too high for the EAR file.
	 */
	private void validateSpecLevel() {
		int earVersion = getVersionID(earFile);
		List modules = earFile.getModuleFiles();
		
		if( !modules.isEmpty() ) {
			Iterator iterator = modules.iterator();
			ModuleFile moduleFile = null;
			while (iterator.hasNext()) {
		  		moduleFile = (ModuleFile)iterator.next();
		  		if (moduleFile != null && getVersionID(moduleFile) > earVersion) {
			  		String[] params = new String[] {moduleFile.getURI(), earFile.getName()};
					addWarning(getBaseName(), MESSAGE_INCOMPATIBLE_SPEC_WARNING_, params, appDD);
		  		}
			}		  			
		}
	}
	
	/**
	 * @param moduleFile
	 * @return
	 */
	private int getVersionID(ModuleFile moduleFile) {
		XMLResource res = null;
		try {
			res = (XMLResource) moduleFile.getDeploymentDescriptorResource();
		} catch (ResourceLoadException e) {
			//Ignore
		} catch (FileNotFoundException e) {	
			//Ignore
		}
		if (res != null)
			return res.getJ2EEVersionID();
		return -1;
	}
}// EarValidator
