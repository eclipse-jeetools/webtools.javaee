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
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.MethodParams;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SessionBean;

/**
 * 
 * Merger for Session Beans.
 * This class will merge all the values of the two beans into the Base bean. 
 * 
 * @author Dimitar Giormov
 *
 */
public class SessionBeanMerger extends AccessibleBeanMerger {

  /**
   * @param _baseBean element is the main element that values will be taken with highest priority.
   * @param _toMergeBean element will give all of his extra values to base.
   * @param _kind
   */
  public SessionBeanMerger(SessionBean _baseBean, SessionBean _toMergeBean, int _kind) {
    super(_baseBean, _toMergeBean, _kind);
  }


  /* (non-Javadoc)
   * @see org.eclipse.jst.javaee.ejb.model.mergers.AccessibleBeanMerger#process()
   */
  @Override
public List process() throws ModelException {

    List warnings = new ArrayList();
    warnings.addAll(super.process());
    mergeInitMethods(warnings);
    mergeRemoveMethods(warnings);
    mergeSecurityRoleRefs(warnings);
    return warnings;
  }

  private void mergeInitMethods(List warnings) throws ModelException {
    List initXMLMappings = getBaseBean().getInitMethods();
    List initAnnotationMappings = getToMergeBean().getInitMethods();
    for (Object object : initAnnotationMappings) {
      if (!containsInitMethod(initXMLMappings, (InitMethodType) object)){
        initXMLMappings.add(EcoreUtil.copy((EObject) object));
      }
    }
  }

  private boolean containsInitMethod(List initXMLMappings, InitMethodType initMethod) {
    for (Object object : initXMLMappings) {
      InitMethodType searched = (InitMethodType) object;
      if(initMethod.getBeanMethod().getMethodName().equals(searched.getBeanMethod().getMethodName()) &&
          sameParams(initMethod.getBeanMethod().getMethodParams(), searched.getBeanMethod().getMethodParams())){

        //CHECK for create method
        return true;
      }
    }
    return false;
  }


  private void mergeRemoveMethods(List warnings) throws ModelException {

    List removeBaseMethods = getBaseBean().getRemoveMethods();
    List removeToMergeMethods = getToMergeBean().getRemoveMethods();
    if(removeToMergeMethods == null || removeToMergeMethods.isEmpty()){
      return;
    }
    for (Object object : removeToMergeMethods) {
      RemoveMethodType base = getRemoveMethodType(removeBaseMethods, (RemoveMethodType) object);
      if(base != null){
        if (((RemoveMethodType)object).isRetainIfException() != base.isRetainIfException()){
          base.setRetainIfException(((RemoveMethodType)object).isRetainIfException());
        }
      } else {
        getBaseBean().getRemoveMethods().add((RemoveMethodType) EcoreUtil.copy((EObject) object)); 
      }
    } 
  }
  private RemoveMethodType getRemoveMethodType(List removeBaseMethods, RemoveMethodType object) {
    for (Object base: removeBaseMethods) {
      RemoveMethodType tmpBase = (RemoveMethodType)base;
      if ( tmpBase.getBeanMethod().getMethodName().equals(object.getBeanMethod().getMethodName()) 
          && sameParams(tmpBase.getBeanMethod().getMethodParams(), object.getBeanMethod().getMethodParams())) {
        return (RemoveMethodType) base;
      }
    }
    return null;
  }


  private boolean sameParams(MethodParams methodParams,
      MethodParams methodParams2) {
    if(methodParams == null && methodParams2 == null){
      return true;
    }
    if(methodParams == null || methodParams2 == null){
      return false;
    }

    if (methodParams.getMethodParams().size() != methodParams2.getMethodParams().size()){
      return false;
    }

    for (int i = 0; i < methodParams.getMethodParams().size(); i++) {
      if(!methodParams.getMethodParams().get(i).equals(methodParams2.getMethodParams().get(i))){
        return false;
      }
    }
    return true;
  }

  private void mergeSecurityRoleRefs(List warnings) throws ModelException {
    List toMergeList = getToMergeBean().getSecurityRoleRefs();
    for (Object roleRef:toMergeList) {
      if(!existingSecRoleRef(getBaseBean().getSecurityRoleRefs(), (SecurityRoleRef) roleRef)){
        getBaseBean().getSecurityRoleRefs().add((SecurityRoleRef) EcoreUtil.copy((EObject) roleRef));
      }

    } 
  }
  
  private boolean existingSecRoleRef(List roles, SecurityRoleRef secRole){
    for (Object object : roles) {
      SecurityRoleRef cRole = (SecurityRoleRef) object;
      if (cRole.getRoleName().equals(secRole.getRoleName())){
        return true;
      }
    }
    return false;
  }
}
