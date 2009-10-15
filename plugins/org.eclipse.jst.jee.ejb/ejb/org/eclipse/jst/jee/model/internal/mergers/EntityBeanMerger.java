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

import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.EntityBean;

/**
 * @author Dimitar Giormov
 *
 */
public class EntityBeanMerger extends ModelElementMerger {

  /**
   * @param _base
   * @param _toMergeBean
   * @param _kind
   */
  public EntityBeanMerger(EntityBean _base, EntityBean _toMergeBean, int _kind) {
    super(_base, _toMergeBean, _kind);
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


    mergeInterfaces(warnings);
    mergeSecurityRoleRefs(warnings);
    mergeJNDIRefs(warnings);
    return warnings;
  }

  EntityBean getBaseBean() {
    return (EntityBean) getBase();
  }

  EntityBean getToMergeBean() {
    return (EntityBean) getToMerge();
  }



  private void mergeSecurityRoleRefs(List warnings) throws ModelException {
    List<SecurityRoleRef> toMergeList = getToMergeBean().getSecurityRoleRefs();
    addAllRoleRefs(toMergeList, warnings);
  }

  private void addAllRoleRefs(List<SecurityRoleRef> roleRefs, List warnings) {
    for (SecurityRoleRef roleRef:roleRefs) {
      if(!existingSecRoleRef(getBaseBean().getSecurityRoleRefs(), roleRef)){
        getBaseBean().getSecurityRoleRefs().add(roleRef);
      }

    }
  }

  private boolean existingSecRoleRef(List roles, SecurityRoleRef secRole){
    for (Object object : roles) {
      SecurityRoleRef cRole = (SecurityRoleRef) object;
      if (cRole.getRoleName().equals(secRole.getRoleName())){

      }
    }
    return false;
  }



  private void mergeInterfaces(List warnings) throws ModelException {
      EntityBean baseBean = getBaseBean();
      EntityBean toMergeBean = getToMergeBean();

      if (baseBean.getLocal() == null){
        String toMergeLocal = toMergeBean.getLocal();
        baseBean.setLocal(toMergeLocal);   
      }

      if (baseBean.getLocalHome() == null){
        String toMergeLocalHome = toMergeBean.getLocalHome();
        baseBean.setLocalHome(toMergeLocalHome);   
      }

      if (baseBean.getRemote() == null){
        String toMergeRemote = toMergeBean.getRemote();
        baseBean.setRemote(toMergeRemote);   
      }

      if (baseBean.getRemote() == null){
        String toMergeRemoteHome = toMergeBean.getRemote();
        baseBean.setRemote(toMergeRemoteHome);   
      }
  }

  private void mergeJNDIRefs(List warnings) throws ModelException {
    
      JNDIRefsMerger jndiRefsMerger = new JNDIRefsMerger(getBaseBean(), getToMergeBean(), getKind());
      warnings.addAll(jndiRefsMerger.process());
  }    



}
