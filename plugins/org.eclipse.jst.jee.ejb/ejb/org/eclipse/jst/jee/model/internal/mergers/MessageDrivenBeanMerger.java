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
import org.eclipse.jst.javaee.ejb.ActivationConfig;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.TransactionType;

/**
 * Merger for Message Driven Beans.
 * This class will merge all the values of the two beans into the Base bean. 
 * 
 * @author Dimitar Giormov
 */
public class MessageDrivenBeanMerger extends ModelElementMerger {

  /**
   * Constructor for the Merger class.
   * 
   * @param baseBean element is the main element that values will be taken with highest priority.
   * @param toMergeBean element will give all of his extra values to base.
   * @param _kind
   */
  public MessageDrivenBeanMerger(MessageDrivenBean _baseBean, MessageDrivenBean _toMergeBean, int _kind) {
    super(_baseBean, _toMergeBean, _kind);
  }

  private void mergeMessagingType(List<String> warnings) {
    if (getBaseBean().getMessagingType() == null){
      getBaseBean().setMessagingType(getToMergeBean().getMessagingType());
    }
  }

  private void mergeActivationConfigurationProps(List<String> warnings) {
    ActivationConfig baseActConf = getBaseBean().getActivationConfig();
    if(baseActConf == null){
      getBaseBean().setActivationConfig(getToMergeBean().getActivationConfig());
    } else{
      List baseActConfProps = baseActConf.getActivationConfigProperties();
      if(getToMergeBean().getActivationConfig() == null){
        return;
      }
      List annotationProps = getToMergeBean().getActivationConfig().getActivationConfigProperties();
      for (Object object : annotationProps) {
        ActivationConfigProperty element = (ActivationConfigProperty) object;
        if(!containsActivationConfigProperty(baseActConfProps, element)){
          baseActConfProps.add(EcoreUtil.copy((EObject) element));
        }
      }
    }

  }

  private boolean containsActivationConfigProperty(List properties, ActivationConfigProperty acp){
    for (Object object : properties) {
      ActivationConfigProperty actConfProp = (ActivationConfigProperty) object;
      if(actConfProp.getActivationConfigPropertyName().equals(acp.getActivationConfigPropertyName())){
        return true;
      }
    }
    return false;
  }


  /**
   * @return base element is the main element that values will be taken with highest priority.
   */
  MessageDrivenBean getBaseBean() {
    return (MessageDrivenBean) getBase();
  }

  /**
   * @return toMergeBean element will give all of his extra values to base.
   */
  MessageDrivenBean getToMergeBean() {
    return (MessageDrivenBean) getToMerge();
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
    //  mergeInterceptors(warnings);
    mergeTransactionType();
    mergeContainerTransactions(warnings);
    mergeSecurityIdentity(warnings);
    mergeTimeoutMethods(warnings);

    mergeMessagingType(warnings);
    mergeActivationConfigurationProps(warnings);
    return warnings;
  }


  private void mergeTransactionType() {
    TransactionType toMergeTransaction = getToMergeBean().getTransactionType();
    TransactionType baseTransaction = getBaseBean().getTransactionType();
    if (baseTransaction != null){
      //		          getBaseBean().setTransactionType(toMergeTransaction);
    } else {
      if (toMergeTransaction != null){
        getBaseBean().setTransactionType(toMergeTransaction);
      }
    }
  }
  
  private void mergeJNDIRefs(List warnings) throws ModelException {
    JNDIRefsMerger jndiRefsMerger = new JNDIRefsMerger(getBaseBean(), getToMergeBean(), getKind());
    warnings.addAll(jndiRefsMerger.process());
  } 

  private void mergeSecurityIdentity(List warnings) throws ModelException {
    SecurityIdentityType baseSecId = getBaseBean().getSecurityIdentity();
    SecurityIdentityType toMergeSecId = getToMergeBean().getSecurityIdentity();
    if(baseSecId == null && toMergeSecId != null){
      getBaseBean().setSecurityIdentity((SecurityIdentityType) EcoreUtil.copy((EObject)toMergeSecId));              
    }
  }

  private void mergeContainerTransactions(List warnings) throws ModelException {
  }

  private void mergeTimeoutMethods(List warnings) throws ModelException {
    NamedMethodType timeoutMethod = getBaseBean().getTimeoutMethod();
    if(timeoutMethod == null && getToMergeBean().getTimeoutMethod() != null){
      getBaseBean().setTimeoutMethod((NamedMethodType) EcoreUtil.copy((EObject) getToMergeBean().getTimeoutMethod()));
    }


  }
}
