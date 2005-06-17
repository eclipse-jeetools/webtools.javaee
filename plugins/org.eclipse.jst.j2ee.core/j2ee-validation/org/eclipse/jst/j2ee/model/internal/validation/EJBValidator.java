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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.util.logger.LogEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.MessageLimitException;

/**
 * @version 	1.0
 * @author
 */
public class EJBValidator extends AbstractEJBValidator {
	private static EJBValidator _inst = null;
	private static TargetObjectPool _targetObjectPoolSingleton = null;
	private LogEntry logEntry = null;
	
	
	public EJBValidator() {
		_inst = this;
	}
	
	public static EJBValidator getValidator() {
		return _inst;
	}
	
	private LogEntry getLogEntry(){
	    if(logEntry == null)
	        logEntry = new LogEntry(IEJBValidatorConstants.BUNDLE_NAME);
	    return logEntry;
	}
	
	public String getBaseName() {
		return "ejbvalidator"; //$NON-NLS-1$
	}

	/*
	 * @see IValidator#validate(IValidationContext, IReporter, IFileDelta[])
	 */
	public void validate(IValidationContext helper, IReporter reporter) throws ValidationException {
		long start = System.currentTimeMillis();
		Logger logger = Logger.getLogger(IEJBValidatorConstants.J2EE_CORE_PLUGIN);
		if(logger != null && logger.isLoggingLevel(Level.FINER)) {
			long end = System.currentTimeMillis();
			LogEntry entry = getLogEntry();
			entry.setSourceID("EJBValidator::validate"); //$NON-NLS-1$
			entry.setText("validate took " + (end - start) + " milliseconds."); //$NON-NLS-1$  //$NON-NLS-2$
			logger.write(Level.FINER, entry);
		}
		
		EJBValidationContext vc = new EJBValidationContext(this, helper, reporter);
		setValidationContext(vc);
		if(isFullValidate(vc)) {
			fullValidate(vc);
		}
		else {
			incrementalValidate(vc);
		}
		if(logger != null && logger.isLoggingLevel(Level.FINER)) {
			long end = System.currentTimeMillis();
			LogEntry entry = getLogEntry();
			entry.setSourceID("EJBValidator::validate"); //$NON-NLS-1$
			entry.setText("validate took " + (end - start) + " milliseconds."); //$NON-NLS-1$  //$NON-NLS-2$
			logger.write(Level.FINER, entry);
		}
	}
	
	public boolean isFullValidate(IEJBValidationContext vc) {
		String[] fileURIs = vc.getURIs();
		if(fileURIs == null) {
			return true;
		}
		if(fileURIs.length == 0) {
			return true;
		}
		
		for(int i=0; i<fileURIs.length; i++) {
			String uri = fileURIs[i];
			if(uri.endsWith(J2EEConstants.EJBJAR_DD_SHORT_NAME)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void runDependents(IEJBValidationContext vc, IValidationRule rule, Object targetParent, Object target) throws ValidationException {
		// If a class is being run only because it depends on a rule which has changed,
		// i.e., it's a dependent, then we don't want to run its dependents because the
		// class itself hasn't changed.
		Set dependents = rule.getDependents();
		if(dependents == null) {
			return;
		}
		
		Iterator iterator = dependents.iterator();
		while(iterator.hasNext()) {
			try {
				IValidationRule dRule = (IValidationRule)iterator.next();
				Object dRuleTarget = dRule.getTarget(targetParent, target);
				if(dRuleTarget != null) {
					run(dRule, targetParent, dRuleTarget); // false=not full validation
				}
			}
			catch(ValidationCancelledException e) {
				throw e;
			}
			catch(ValidationException e) {
				throw e;
			}
			catch(MessageLimitException e) {
				throw e;
			}
			catch(Throwable exc) {
				addInternalErrorMessage(getValidationContext(), exc);
			}
			finally {
				EJBValidationRuleFactory.getFactory().release(rule);
			}
		}
	}
	
	protected String internalErrorMessage() {
		return IEJBValidatorMessageConstants.CHKJ2900;
	}
	
	
	
	protected void logMissingRule(IEJBValidationContext vc, Object ruleId) {
		Logger logger = vc.getMsgLogger();
		if (logger != null && logger.isLoggingLevel(Level.SEVERE)) {
			logger.write(Level.SEVERE, ruleId + " = null"); //$NON-NLS-1$
		}
		addInternalErrorMessage(vc);
	}
	
	protected void preRemoveOldMessages(IEJBValidationContext vc, Map targets) throws ValidationException {
		Set validatedClasses = new HashSet();
		
		try {	
			String[] uris = vc.getURIs();
			for(int i=0; i<uris.length; i++) {
				String uriInst = uris[i];
				if((uriInst == null) || (uriInst.length() == 0)) {
					continue;
				}
				
				Object id = EJBValidationRuleFactory.getFactory().getRuleId(vc, uriInst);
				if(id == null) {
					Object[] clazzAndBean = (Object[])vc.loadModel(uriInst, null); // Don't need a second parameter, but can't cast a RefObject to an Object[], so use the second load method.
					if(clazzAndBean == null) {
						// Log, add "Cannot validate" to task list, and return.
						logMissingRule(vc, id);
						continue;
					}
	
					// In the clazzAndBean object array, the first entry is the JavaClass,
					// and the rest of the entries are the EnterpriseBean instances which 
					// use the JavaClass.
					JavaClass clazz = (JavaClass)clazzAndBean[0];
					List beans = (List)clazzAndBean[1];

					// The validatedClass set keeps track of JavaClasses 
					// that have changed, and this set is used to determine 
					// whose children need to be found and validated. 
					// Validation is performed after all of the changed files 
					// are validated so that all of the children of all of
					// the changed files can be searched for at once. Searching
					// once on a group produces performance savings because
					// the type hierarchy method takes a non-trivial amount
					// of time when there's a large group of deltas.
					validatedClasses.add(clazz);

					if((beans == null) || (beans.size() == 0)) {
						// The JavaClass itself is not part of an enterprise bean, but one of its children may be.
					}
					else {
						Iterator iterator = beans.iterator();
						while(iterator.hasNext()) {
							EnterpriseBean bean = (EnterpriseBean)iterator.next();
							id = EJBValidationRuleFactory.getFactory().getRuleId(vc, clazz, bean);
		
							IValidationRule clazzRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
							if(clazzRule == null) {
								// This has already been logged by the AbstractEJBValidationRuleFactory (if it's
								// an error - this is expected if the key is a primitive primary key).
								continue;
							}
		
							setValidated(clazzRule.getId(), bean, clazz);
						}
					}
					
				}
				else {
					EJBJar ejbJar = (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
					if(ejbJar == null) {
						// Log, add "Cannot validate" to task list, and return.
						continue;
					}
					
					IValidationRule ejbExtRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
					if(ejbExtRule == null) {
						// This has already been logged by the AbstractEJBValidationRuleFactory, so just
						// need to add "Cannot validate" to the task list.
						continue;
					}
					
					setValidated(ejbExtRule.getId(), null, ejbJar);
				}
			} // end for
			
			// Always validate ejb-jar.xml, because a change to one of the files it references
			// may mean that it needs to be revalidated.	
			EJBJar ejbJar = (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
			if(ejbJar != null) {
				Object id = EJBValidationRuleFactory.getFactory().getRuleId(vc, J2EEConstants.EJBJAR_DD_SHORT_NAME);
				if(id == null) {
					// Log, add "Cannot validate" to task list, and return.
					logMissingRule(vc, id);
				}
				else {
					IValidationRule ejbJarRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
					if(ejbJarRule == null) {
						logMissingRule(vc, id);
					}
					setValidated(ejbJarRule.getId(), null, ejbJar);
				}
			}
			
			if(validatedClasses.size() > 0) {
				// Check the children of the changed classes.
				// This check must be done before the dependents, because
				// the dependents of the children classes must be checked
				// as well.

				// Class never validated before, so check its children
				JavaClass[] children = (JavaClass[])vc.loadModel(EJBValidatorModelEnum.CHILDREN, new Object[]{vc.getReporter(), validatedClasses});
				validatedClasses.clear(); // Don't need this cache any more; free the memory.
				if((children != null) && (children.length > 0)) {
					Iterator bciterator = null;
					Object id = null;
					for(int c=0; c<children.length; c++) {
						JavaClass child = children[c];
						List beans = (List)vc.loadModel(EJBValidatorModelEnum.EJB, new Object[]{child});
						if((beans == null) || (beans.size() == 0)) {
							// The child is not a member of an enterprise bean.
							continue;
						}
						
						bciterator = beans.iterator();
						while(bciterator.hasNext()) {
							EnterpriseBean bean = (EnterpriseBean)bciterator.next();
							id = EJBValidationRuleFactory.getFactory().getRuleId(vc, child, bean);
		
							IValidationRule clazzRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
							if(clazzRule == null) {
								// This has already been logged by the AbstractEJBValidationRuleFactory, so just
								// need to add "Cannot validate" to the task list.
								continue;
							}
		
							setValidated(clazzRule.getId(), bean, child);
						}
					}
				}
			}
			
			// Now, validate the dependents.
			targets.putAll(_validated);
			Iterator iterator = targets.keySet().iterator();
			while(iterator.hasNext()) {
				Object id = iterator.next();
				IValidationRule rule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
				if(rule == null) {
					continue;
				}
				
				Set contexts = (Set)targets.get(id);
				if(contexts == null) {
					continue;
				}
				
				Iterator cIterator = contexts.iterator();
				while(cIterator.hasNext()) {
					TargetObject to = (TargetObject)cIterator.next();
					Object targetParent = to.getTargetParent();
					Object target = to.getTarget();
					Set dependents = rule.getDependents();
					if(dependents == null) {
						continue;
					}
					
					Iterator dIterator = dependents.iterator();
					while(dIterator.hasNext()) {
						try {
							IValidationRule dRule = (IValidationRule)dIterator.next();
							Object dRuleTarget = dRule.getTarget(targetParent, target);
							if(dRuleTarget != null) {
								setValidated(dRule.getId(), targetParent, dRuleTarget);
							}
						}
						catch(ValidationCancelledException e) {
							throw e;
						}
						catch(MessageLimitException e) {
							throw e;
						}
						catch(Throwable exc) {
							addInternalErrorMessage(getValidationContext(), exc);
						}
						finally {
							EJBValidationRuleFactory.getFactory().release(rule);
						}
					}
				}
			}
		}
		finally {
			// No matter what, clear the temporary caches.
			targets.clear();
			validatedClasses.clear();
			
			// Now put the "validated" results in "done", because they weren't
			// really validated; it was just a tracking mechanism.
			targets.putAll(_validated);
			_validated.clear(); // Clear the "validated" cache because the targets weren't really validated; they were just tracked.
		}
	}
	
	protected String removeOldMessagesString() {
		return EJBValidatorModelEnum.REMOVE_OLD_MESSAGES;
	}
	
	public void fullValidate(IEJBValidationContext vc) throws ValidationException {
		removeOldMessages(vc,null); // null == no IFileDelta, null = don't track targets
		
		EJBJar ejbJar = (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
		if(ejbJar == null) {
			// Log, add "Cannot validate" to task list, and return.
			// EJBProjectResources will already have logged the problem.
			
			IMessage mssg = vc.getMessage();
			mssg.setId(IEJBValidatorMessageConstants.CHKJ2905);
			vc.addMessage(mssg);
			return;
		}
		
		Object id = EJBValidationRuleFactory.getFactory().getRuleId(vc, J2EEConstants.EJBJAR_DD_SHORT_NAME);
		if(id == null) {
			// Log, add "Cannot validate" to task list, and return.
			logMissingRule(vc, J2EEConstants.EJBJAR_DD_SHORT_NAME);
			return;
		}
		IValidationRule ejbJarRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
		if(ejbJarRule == null) {
			logMissingRule(vc, id);
			return;
		}
		run(ejbJarRule, null, ejbJar); // true= full validation

		List beans = ejbJar.getEnterpriseBeans();
		Iterator iterator = beans.iterator();
		id = null;

		while(iterator.hasNext()) {
			EnterpriseBean bean = (EnterpriseBean)iterator.next();
			JavaClass[] classes = getJavaClass(bean);
			for(int i=0; i<classes.length; i++) {
				JavaClass clazz = classes[i];
				id = EJBValidationRuleFactory.getFactory().getRuleId(vc, clazz, bean);
		
				IValidationRule clazzRule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
				if(clazzRule == null) {
					// This has already been logged by the AbstractEJBValidationRuleFactory (if it's
					// an error - this is expected if the key is a primitive primary key).
					continue;
				}
		
				run(clazzRule, bean, clazz); // true = full validation
			}
		}
	}
	
	protected JavaClass[] getJavaClass(EnterpriseBean bean) {
		int count = 0;
		JavaClass[] classes = new JavaClass[6];
		
		JavaClass ejbClass = bean.getEjbClass();
		if((ejbClass != null) && (ejbClass.isExistingType())) {
			classes[count++] = ejbClass;
		}
		
		JavaClass remoteClass = bean.getRemoteInterface();
		if((remoteClass != null) && (remoteClass.isExistingType())) {
			classes[count++] = remoteClass;
		}
		
		JavaClass localClass = bean.getLocalInterface();
		if((localClass != null) && (localClass.isExistingType())) {
			classes[count++] = localClass;
		}
		
		JavaClass homeClass = bean.getHomeInterface();
		if((homeClass != null) && (homeClass.isExistingType())) {
			classes[count++] = homeClass;
		}
		
		JavaClass localHomeClass = bean.getLocalHomeInterface();
		if((localHomeClass != null) && (localHomeClass.isExistingType())) {
			classes[count++] = localHomeClass;
		}
		
		if(bean instanceof Entity) {
			JavaClass key = ((Entity)bean).getPrimaryKey();
			if((key != null) && (key.isExistingType())) {
				classes[count++] = key;
			}
		}

		if(count == 6) {
			return classes;
		}
		
		JavaClass[] result = new JavaClass[count];
		System.arraycopy(classes, 0, result, 0, count);
		return result;		
	}
	
	public void incrementalValidate(IEJBValidationContext vc) throws ValidationException {
		Map targets = new HashMap();
		try {
			removeOldMessages(vc,targets);
			
			Iterator iterator = targets.keySet().iterator();
			while(iterator.hasNext()) {
				Object id = iterator.next();
				IValidationRule rule = EJBValidationRuleFactory.getFactory().getRule(vc, id);
				if(rule == null) {
					continue;
				}
				
				Set contexts = (Set)targets.get(id);
				if(contexts == null) {
					continue;
				}
				
				Iterator cIterator = contexts.iterator();
				while(cIterator.hasNext()) {
					TargetObject to = (TargetObject)cIterator.next();
					run(rule, to.getTargetParent(), to.getTarget());
				}
			}
		}
		finally {
			targets.clear();
			targets = null;
		}
	}
	
	protected TargetObjectPool getTargetObjectPool() {
		if(_targetObjectPoolSingleton == null) {
			_targetObjectPoolSingleton = new TargetObjectPool(100);
		}
		return _targetObjectPoolSingleton;
	}

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.model.validation.AbstractEJBValidator#releaseRules(org.eclipse.jst.j2ee.internal.model.validation.ejb.IValidationRule)
     */
    protected void releaseRules(IValidationRule rule) {
        EJBValidationRuleFactory.getFactory().release(rule);
        
    }
	
}
