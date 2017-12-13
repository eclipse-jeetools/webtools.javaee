/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.TransactionType;

/**
 * Enterprise Bean Merger is used to merge all the common bean properties.
 * For the time being it is used only on Session Beans, until a proper structure in Emf model is introduced.
 * 
 * @author Dimitar Giormov
 */
public class EnterpriseBeanMerger extends ModelElementMerger {


  /**
   * Constructor of the class.
   * 
   * @param _base Session bean artifact element is the main element that values will be taken with highest priority.
   * @param _toMerge Session bean artifact element will give all of his extra values to base.
   * @param _kind
   */
  public EnterpriseBeanMerger(SessionBean base, SessionBean toMerge, int _kind) {
    super(base, toMerge, _kind);
  }

  /**
   * Returns the Session bean base element, which is also the merged result.
   * 
   * @return Session bean base element.
   */
  SessionBean getBaseBean() {
    return (SessionBean) getBase();
  }

  /**
   * 
   * @return toMergeBean element, which will be merged in base.
   */
  SessionBean getToMergeBean() {
    return (SessionBean) getToMerge();
  }


  /* (non-Javadoc)
   * @see org.eclipse.jst.javaee.ejb.model.mergers.ModelElementMerger#process()
   */
  @Override
public List process() throws ModelException {
    List warnings = new ArrayList();
    if(getToMerge() == null){
      return warnings;
    }
    String beanClass = getBaseBean().getEjbClass();
    if (beanClass == null) {
      beanClass = getToMergeBean().getEjbClass();
    }
    getBaseBean().setEjbClass(beanClass);
    mergeJNDIRefs(warnings);
    mergeTransactionType();
    mergeSecurityIdentity(warnings);
    mergeTimeoutMethods(warnings);
    return warnings;
  }


  private void mergeTransactionType() {
    TransactionType toMergeTransaction = getToMergeBean().getTransactionType();
    if (!getBaseBean().isSetTransactionType()){
      getBaseBean().setTransactionType(toMergeTransaction);
    }
  }


  private void mergeTimeoutMethods(List warnings) throws ModelException {
    NamedMethodType timeoutMethod = getBaseBean().getTimeoutMethod();
    if(timeoutMethod == null && getToMergeBean().getTimeoutMethod() != null){
      getBaseBean().setTimeoutMethod((NamedMethodType) EcoreUtil.copy((EObject) getToMergeBean().getTimeoutMethod()));
    }
  }

  private void mergeSecurityIdentity(List warnings) throws ModelException {
    SecurityIdentityType baseSecId = getBaseBean().getSecurityIdentities();
    SecurityIdentityType toMergeSecId = getToMergeBean().getSecurityIdentities();
    if(baseSecId == null && toMergeSecId != null){
      getBaseBean().setSecurityIdentities((SecurityIdentityType) EcoreUtil.copy((EObject)toMergeSecId));              
    }
  }

  private void mergeJNDIRefs(List warnings) throws ModelException {
    JNDIRefsMerger jndiRefsMerger = new JNDIRefsMerger(getBaseBean(), getToMergeBean(), getKind());
    warnings.addAll(jndiRefsMerger.process());
  }    
}
