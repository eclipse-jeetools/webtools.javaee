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



import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.internal.common.impl.ResourceRefImpl;
import org.eclipse.jst.j2ee.internal.common.impl.SecurityRoleImpl;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidator;


/**
 * Insert the type's description here.
 * Creation date: (3/9/2001 3:34:39 PM)
 * @author: Administrator
 */
public abstract class J2EEValidator implements IValidator, J2EEMessageConstants {
  protected IReporter _reporter;
  protected IValidationContext _helper;

	public J2EEValidator()
	{
  	    initialize();
	}
/**
 * <p>Create am <em>error</em> message and route it to the
 * cached reporter.  This form of <code>addError</code> is
 * for messages which require no arguments.</p>
 *
 * @param msgId The ID of the message to be created.
 */
 
public void addError(String msgId)
{
  addError(getBaseName(), msgId, null);
}
/**
 * <p>Create am <em>error</em> message and route it to the
 * cached reporter.  This form of <code>addError</code> is
 * for messages which require no arguments.</p>
 *
 * @param msgId The ID of the message to be created.
 */
 
public void addError(String msgId, String[] msgArgs)
{
  addError(getBaseName(), msgId, msgArgs);
}
/**
 * <p>Create am <em>error</em> message and route it to the
 * cached reporter.  This form of <code>addError</code> is
 * for messages which require no arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 */
 
public void addError(String msgCategory, String msgId)
{
  addError(msgCategory, msgId, null);
}
/**
 * <p>Create an <em>error</em> message and route it to
 * the cached reporter.  This form of <code>addError</code>
 * is for messages which require one or more arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addError(String bundleName, String msgId, String[] msgArgs)
{
	this.addError(bundleName, msgId, msgArgs, null);
}
/**
 * <p>Create an <em>error</em> message and route it to
 * the cached reporter.  This form of <code>addError</code>
 * is for messages which require one or more arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addError(String bundleName, String msgId, String[] msgArgs, Object target)
{
  _reporter.addMessage( this,
	  new Message(
		  	bundleName, 
	  		IMessage.HIGH_SEVERITY,
		  	msgId, 
		    msgArgs,
		    target));
}
public void addError(String bundleName, String msgId, String[] msgArgs, Object target, String groupName) {
	IMessage message =  new Message(
		  	bundleName, 
	  		IMessage.HIGH_SEVERITY,
		  	msgId, 
		    msgArgs,
		    target);
	message.setGroupName(groupName);
	_reporter.addMessage(this, message);
}
public void addError(String bundleName, String msgId, String[] msgArgs, Object target, String groupName, int lineNumber)
{
	IMessage message = new Message(
		  	bundleName, 
	  		IMessage.HIGH_SEVERITY,
		  	msgId, 
		    msgArgs,
		    target);
	message.setLineNo(lineNumber);	
	message.setGroupName(groupName);
	
  _reporter.addMessage( this, message);
	  
}
/**
 * <p>Create an <em>informational</em> message and route it
 * to the cached reporter.  This form of <code>addError</code>
 * is for messages which require no arguments.</p>
 *
 * @param msgId The ID of the message to be created.
 */

public void addInfo(String msgId)
{
  addInfo(getBaseName(), msgId, null);
}
/**
 * <p>Create an <em>informational</em> message and route it
 * to the cached reporter.  This form of <code>addError</code>
 * is for messages which require no arguments.</p>
 *
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addInfo(String msgId, String[] msgArgs)
{
  addInfo(getBaseName(), msgId, msgArgs);
}
/**
 * <p>Create an <em>informational</em> message and route it
 * to the cached reporter.  This form of <code>addError</code>
 * is for messages which require no arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 */

public void addInfo(String msgCategory, String msgId)
{
  addInfo(msgCategory, msgId, null);
}
/**
 * <p>Create an <em>informational</em> message and route
 * it to the cached reporter.  This form of <code>addError</code>
 * is for messages which require one or more arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addInfo(String bundleName, String msgId, String[] msgArgs)
{
  addInfo(bundleName, msgId, msgArgs, null);
}
/**
 * <p>Create an <em>informational</em> message and route
 * it to the cached reporter.  This form of <code>addError</code>
 * is for messages which require one or more arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addInfo(String bundleName, String msgId, String[] msgArgs, Object target)
{
  _reporter.addMessage( this,
	  new Message(
		  	bundleName, 
	  		IMessage.LOW_SEVERITY,
		  	msgId, 
		    msgArgs,
		    target));
}
/**
 * <p>Create a <em>warning</em> message and route it to the
 * cached reporter.  This form of <code>addError</code> is
 * for messages which require no arguments.</p>
 *
 * @param msgId The ID of the message to be created.
 */
 
public void addWarning(String msgId)
{
  addWarning(getBaseName(), msgId, null);
}
/**
 * <p>Create a <em>warning</em> message and route it to the
 * cached reporter.  This form of <code>addError</code> is
 * for messages which require no arguments.</p>
 *
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */
 
public void addWarning(String msgId, String[] msgArgs)
{
  addWarning(getBaseName(), msgId, msgArgs);
}
/**
 * <p>Create a <em>warning</em> message and route it to the
 * cached reporter.  This form of <code>addError</code> is
 * for messages which require no arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 */
 
public void addWarning(String msgCategory, String msgId)
{
  addWarning(msgCategory, msgId, null);
}
/**
 * <p>Create a <em>warning</em> message and route it to
 * the cached reporter.  This form of <code>addError</code>
 * is for messages which require one or more arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addWarning(String bundleName, String msgId, String[] msgArgs)
{
  addWarning(bundleName, msgId, msgArgs, null);
}
/**
 * <p>Create a <em>warning</em> message and route it to
 * the cached reporter.  This form of <code>addError</code>
 * is for messages which require one or more arguments.</p>
 *
 * @param msgCategory The base name of the message bundle to use.
 * @param msgId The ID of the message to be created.
 * @param msgArgs The parameters for the message.
 */

public void addWarning(String bundleName, String msgId, String[] msgArgs, Object target)
{
  _reporter.addMessage( this,
	  new Message(
		  	bundleName, 
	  		IMessage.NORMAL_SEVERITY,
		  	msgId, 
		    msgArgs,
		    target));
}
public void addWarning(String bundleName, String msgId, String[] msgArgs, Object target, String groupName)
{
	IMessage message = new Message(
		  	bundleName, 
	  		IMessage.NORMAL_SEVERITY,
		  	msgId, 
		    msgArgs,
		    target);
	message.setGroupName(groupName);
	_reporter.addMessage(this, message);		   
}
/**
 * Perform any resource cleanup once validation is complete. 
 */
public void cleanup() {
	//Default
}
/**
 * Perform any resource cleanup once validation is complete. If cleanup will
 * take some time, the IValidator should report subtask information to the
 * user through the IReporter parameter. The AReporter parameter will not be
 * null.
 * @deprecated Use cleanup()
 */
public void cleanup(IReporter reporter) {
	//Default
}
/**
 * Returns the name of the Validator, as it should be displayed in the UI.
 * The ValidationBuilder uses this string as input to the IProgressMonitor.
 * i.e., At some point during the validation process, the Validation Builder
 * will output this string: "{0} is validating {1}.", where {0} is the string
 * that this method returns, and {1} is the name of the current resource.
 */
public String[] get1Param(String string1){
	 String[] parms = new String[1];
	 parms[0] = string1;
	 return parms;
	 }
/**
 * Returns the name of the Validator, as it should be displayed in the UI.
 * The ValidationBuilder uses this string as input to the IProgressMonitor.
 * i.e., At some point during the validation process, the Validation Builder
 * will output this string: "{0} is validating {1}.", where {0} is the string
 * that this method returns, and {1} is the name of the current resource.
 */
public String[] get2Param(String string1,String string2){
	 String[] parms = new String[2];
	 parms[0] = string1;
	 parms[1] = string2;
	 return parms;
	 }
/**
 * Returns the name of the Validator, as it should be displayed in the UI.
 * The ValidationBuilder uses this string as input to the IProgressMonitor.
 * i.e., At some point during the validation process, the Validation Builder
 * will output this string: "{0} is validating {1}.", where {0} is the string
 * that this method returns, and {1} is the name of the current resource.
 */
public String[] get3Param(String string1,String string2,String string3){
	 String[] parms = new String[2];
	 parms[0] = string1;
	 parms[1] = string2;
	 parms[2] = string3;
	 return parms;
	 }
/**
 * <p>Answer the id of the resource bundle which is
 * used by the receiver.</p>
 */
 
public abstract String getBaseName();
/**
 * Returns a localized message from a resource bundle.
 * @param msgCategory The baseName of the resource bundle (filename minus any locale suffix)
 * @param msgId The key of the message to lookup from the bundle.
 * @param msgArgs An array of arguments that will be substituted into any message arguments in the message's value.
 * @param locale The locale to retrieve the message text from. 
 */
public String getMessage(String msgCategory, String msgId, String[] msgArgs, Locale locale) {
  return J2EEValidationResourceHandler.getExternalizedMessage(msgCategory, msgId, getClass(), msgArgs, locale);
}
/**
 * Returns a localized message from a resource bundle.
 * @param msgCategory The baseName of the resource bundle (filename minus any locale suffix)
 * @param msgId The key of the message to lookup from the bundle.
 * @param locale The locale to retrieve the message text from. 
 */
public String getMessage(String msgCategory, String msgId, Locale locale) {
  return J2EEValidationResourceHandler.getExternalizedMessage(msgCategory, msgId, getClass(), locale);
}
	/**
	 * Returns the name of the Validator, as it should be displayed
	 * in the UI.   The ValidationBuilder uses this string as input to
	 * the IProgressMonitor.  i.e., At some point during the validation
	 * process, the Validation Builder will output this string:
	 *     "{0} is validating {1}.",
	 * where
	 *     {0} is the string that this method returns,
	 * and
	 *     {1} is the name of the current resource.
	 */
	public String getName(){
		return getName(Locale.getDefault());
	}

/**
 * <p>Returns the name of the Validator, as it should be displayed
 * in the UI.   The ValidationBuilder uses this string as input to
 * the IProgressMonitor.  i.e., At some point during the validation
 * process, the Validation Builder will output this string:</p>
 * <pre>
 *     "{0} is validating {1}.",
 * </pre>
 * <p>where</p>
 * <pre>
 *     {0} is the string that this method returns,
 * </pre>
 * <p>and</p>
 * <pre>
 *     {1} is the name of the current resource.
 * </pre>
 *
 * @param locale The locale from which to retrieve the name text.
 */

public String getName(Locale locale)
{
  return getMessage(getBaseName(), VALIDATOR_NAME, locale);
}
/**
 * Returns the name of the Validator, as it should be displayed in the UI.
 * The ValidationBuilder uses this string as input to the IProgressMonitor.
 * i.e., At some point during the validation process, the Validation Builder
 * will output this string: "{0} is validating {1}.", where {0} is the string
 * that this method returns, and {1} is the name of the current resource.
 */
public String[] getParam1(String string1){
	 String[] parms = new String[1];
	 parms[0] = string1;
	 return parms;
	 }
	protected void initialize(){
		//Default
	}
/**
 * This is the method which performs the validation on the MOF model.
 * <br><br>
 * <code>helper</code> and <code>reporter</code> may not be null. <code>changedFiles</code> may be null, if a full
 * build is desired.
 * <br><br>
 * <code>helper</code> loads a EObject. The EObject is the root of the 
 * MOF model about to be validated. When this object is traversed,
 * you can reach every element in the MOF model which needs to be validated.
 * <br><br>
 * <code>reporter</code> is an instance of an IReporter interface, which is used for interaction with the user.
 * <br><br>
 * <code>changedFiles</code> is an array of file names which have changed since the last validation. 
 * If <code>changedFiles</code> is null, or if it is an empty array, then a full build
 * is performed. Otherwise, validation on just the files listed in the Vector is performed.
 */
public void validate(IValidationContext inHelper, IReporter inReporter) throws ValidationException {

  _helper = inHelper;
  _reporter = inReporter;
  if ((inHelper == null) || (inReporter == null)) {
	return;
  }
}
/**
   * Validate EJB references.
   */
public void validateEJBRefManadatoryElements(EjbRef eref, String ownerName) {
	String[] parms = new String[2];
	parms[0] = eref.getName();
	parms[1] = ownerName;
	
	if ((eref.getName() == null) || (eref.getName().trim().length() == 0))
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFNAME, parms, eref);
	if (eref.isSetType()) {
		if (eref.getType() == null)
			addWarning(EREF_CATEGORY, ERROR_EAR_INVALID_EREFTYPE, parms);
	} else
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFTYPE, parms);
		
	String refHome = eref.getHome();
	if ((refHome == null) || (refHome.trim().length() == 0))
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFHOME, parms, eref);
		
	if ((eref.getRemote() == null) || (eref.getRemote().trim().length() == 0))
		addWarning(EREF_CATEGORY, ERROR_EAR_MISSING_EREFREMOTE, parms, eref);
}
/**
   * Validate for duplicates in EAR Roles.
   */
public void validateResourceRefs(List resourceRefs) {

  int numRefs = resourceRefs.size();
  Set refSet = new HashSet(numRefs);

  for (int refNo = 0; refNo < numRefs; refNo++) {
	String refName = ((ResourceRefImpl) (resourceRefs.get(refNo))).getName();
	String[] parms = new String[1];
	parms[0] = refName;
	if (!(refSet.add(refName)))
	  addError(EREF_CATEGORY, ERROR_EAR_DUPLICATE_RESREF, parms, resourceRefs.get(refNo));
  }
}
/**
   * Validate the existance of Web Roles within the EAR Roles and
   * duplicates in Web Roles.
   */
public void validateWEBRolesWithEARRoles(EList earRoleList, EList warRoles) {
  int numRoles = warRoles.size();

  // TFB This implementation requires a 'small' list of warRoles.
  // If 'warRoles' gets too big then another implementation
  // will be necessary.

  for (int roleNo = 0; roleNo < numRoles; roleNo++) {
	SecurityRoleImpl nextRole = (SecurityRoleImpl) (warRoles.get(roleNo));
	String[] parms = new String[1];
	parms[0] = nextRole.getRoleName();
	if (!(earRoleList.contains(nextRole)))
	  addWarning(WAR_CATEGORY, ERROR_EAR_MISSING_EJB_ROLE, parms, nextRole);
  }
}
}
