/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.mergers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jst.javaee.core.ServiceRef;

/**
 * @author Dimitar Giormov
 *
 */
public class ServiceRefsMerger extends BaseRefsMerger {


  /**
   *  Constructor for the class. 
   * 
   * @param base - base list with service references.
   *               that have higher priority and are result of the merge. 
   * @param toMerge - toMerge list to be imported in base list.
   */
  public ServiceRefsMerger(List _baseRefs, List _toMergeRefs) {
    super(_baseRefs, _toMergeRefs);
  }

  /* (non-Javadoc)
   * @see org.eclipse.jst.javaee.ejb.model.mergers.common.ModelElementsMerger#process()
   */
  @Override
public List process() throws ModelException {
    List warnings = new ArrayList();
    for (Object oToMerge : getToMergeElemets()) {
      ServiceRef servivceRef = (ServiceRef) oToMerge;
      boolean found = false;
      for (Object oBase : getBaseElements()) {
        ServiceRef ServiceRefBase = (ServiceRef) oBase;
        if (ServiceRefBase.getServiceRefName().equals(servivceRef.getServiceRefName())){
          copyMissingPropertesInBase(ServiceRefBase, servivceRef);
          found = true;
          break;
        }
      }
      if (found){
        continue;
      }
      getBaseElements().add(EcoreUtil.copy((EObject) oToMerge));
    }
    return warnings;
  }


  @Override
  protected void copyMissingPropertesInBase(Object base, Object toMerge) {
    copyMissingPropertesInBase((ServiceRef) base, (ServiceRef) toMerge);
  }
  protected void copyMissingPropertesInBase(ServiceRef base, ServiceRef toMerge) {
    if (base.getMappedName() == null){
      base.setMappedName(toMerge.getMappedName());
    }

    if (base.getWsdlFile() == null){
      base.setWsdlFile(toMerge.getWsdlFile());
    }
    //	  if (base.getServiceInterface().equals("javax.xml.ws.Service")) {
    if (base.getServiceInterface() == null) {
      base.setServiceInterface(toMerge.getServiceInterface());
    }
    //	  }
  }

}
