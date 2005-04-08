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

import java.util.Map;
import java.util.Set;

import org.eclipse.wst.validation.internal.provisional.core.ValidationException;


public interface IValidationRule {
	/**
	 * Returns the id of this rule. Given this id, this rule can be loaded from
	 * the AbstractEJBValidationRuleFactory.
	 */
	public Object getId();
	
	/**
	 * Returns the ids of the rules which this rule depends on. (That is,
	 * if one of these rules runs, then this rule needs to be run.)
	 */
	public Object[] getDependsOn();
	
	/**
	 * Add a validation rule which needs to be notified when this rule is run.
	 */
	public void addDependent(IValidationRule rule);
	
	/**
	 * Return a collection of the IValidationRules which depend on this rule.
	 */
	public Set getDependents();
	
	/**
	 * Given the target's parent and the changed target, return the target which 
	 * this rule should validate. For example, if a bean class changes, then the 
	 * target parent would be the EnterpriseBean, and this rule could return
	 * the home interface, or the component interface, or the key class, etc.
	 */
	public Object getTarget(Object targetParent, Object target);

	/**
	 * If any information needs to be calculated before the validate method is called,
	 * then that information should be calculated here. This cached information should
	 * be cleaned up in the postValidate method.
	 */
	public void preValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException;
	
	/**
	 * Validate the target object. Report all messages, load models, etc.
	 * through the IValidtionContext parameter.
	 */
	public void validate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException;
	
	/**
	 * This method cleans up any resources calculated or cached in the preValidate method.
	 * This method is called after the validate method has completed.
	 */
	public void postValidate(IEJBValidationContext vc, Object targetParent, Object target) throws ValidationCancelledException, ValidationException;
	
	/**
	 * To enable this rule for reuse, reset any stored values (which were calculated 
	 * during validation) to the default value.
	 */
	public void reset();
	
	/**
	 * Return a mapping of CHKJ message id to the message id in the resource bundle.
	 */
	public Map getMessageIds();
	
}
