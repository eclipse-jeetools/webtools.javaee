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
/*
 * Abstract class for EJB Validation rules
 */
package org.eclipse.jst.j2ee.model.internal.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.ejb.EJBJar;

/**
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public abstract class AbstractEJBValidationRuleFactory {

    /**
     * 
     */
    public AbstractEJBValidationRuleFactory() {
		//Default
    }

    protected HashMap _ruleList = null;

    public void release(IValidationRule rule) {
    	if(rule == null) {
    		return;
    	}
    	
    	// Since we don't put a lock on the rule when it's checked out via getRule(Object),
    	// there's nothing to do other than reset the rule to prepare it for reuse.
    	rule.reset();
    }

    protected Iterator addDependentRuleToRuleList() {
    	Iterator iterator = _ruleList.values().iterator();
    	while(iterator.hasNext()) {
    		IValidationRule rule = (IValidationRule)iterator.next();
    		Object[] ids = rule.getDependsOn();
    		if(ids == null) {
    			continue;
    		}
    		
    		for(int i=0; i<ids.length; i++) {
    			IValidationRule dRule = (IValidationRule)_ruleList.get(ids[i]);
    			if(dRule != null) {
    				dRule.addDependent(rule);
    			}
    		}
    	}
    	return iterator;
    }

    protected void logAllDependentRules() {
    	Iterator iterator;
    	Logger logger = getMsgLogger();
    	if (logger.isLoggingLevel(Level.FINEST)) {
    		iterator = _ruleList.values().iterator();
    		Iterator depIterator = null;
    		while(iterator.hasNext()) {
    			IValidationRule rule = (IValidationRule)iterator.next();
    			Set dependents = rule.getDependents();
    			StringBuffer buffer = new StringBuffer();
    			buffer.append("list of dependents for rule:"); //$NON-NLS-1$
    			buffer.append(rule.getClass().getName());
    			buffer.append("\n"); //$NON-NLS-1$
    			if(dependents != null) {
    				depIterator = dependents.iterator();
    				while(depIterator.hasNext()) {
    					IValidationRule dRule = (IValidationRule)depIterator.next();
    					buffer.append("\t"); //$NON-NLS-1$
    					buffer.append(dRule.getClass().getName());
    					buffer.append("\n"); //$NON-NLS-1$
    				}
    			}
    			buffer.append("\n"); //$NON-NLS-1$
    			logger.write(Level.FINEST, buffer.toString());
    		}
    	}
    }

    /**
     * @return
     */
    protected abstract Logger getMsgLogger();

    protected EJBJar getEJBJar(IEJBValidationContext vc) {
    	return (EJBJar)vc.loadModel(EJBValidatorModelEnum.EJB_MODEL);
    }

    /**
     * This method will return the identified IValidationRule.
     * If the ruleid is not registered, or if there is a problem
     * instantiating the IValidationRule, then null will be returned.
     */
    public IValidationRule getRule(IEJBValidationContext vc, Object ruleId) {
    	// To avoid loading a rule until it is needed, intialize
    	// the list with a rule id as the key, and a String class name
    	// as a value. When a rule is loaded, if the value is a String,
    	// then the IValidationRule has not been instantiated. Create an
    	// instance and store that in place of the String. If the value
    	// is already an IValidationRule instance, return it.
    	
    	Object rule = _ruleList.get(ruleId);
    	if(rule == null) {
    		// No such ruleId registered.
    		Logger logger = vc.getMsgLogger();
    		if (logger != null && logger.isLoggingLevel(Level.FINE)) {
    			logger.write(Level.FINE, "Cannot load rule from ruleId: " + ruleId); //$NON-NLS-1$
    		}
    		return null;
    	}
    	
    	return (IValidationRule)rule;
    }
}
