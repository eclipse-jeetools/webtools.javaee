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
import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;

/**
 * EjbRef and EjbLocalRef merger.
 * 
 * @author Dimitar Giormov
 */
public class EJBRefsMerger extends BaseRefsMerger {
    
    /**
     * Constructor for the EjbRefs merger.
     * 
     * @param _baseEjbRefs - base list of references that have higher priority and are result of the merge.
     * @param _toMergeEjbRefs - toMerge list of references
     */
    public EJBRefsMerger(List _baseEjbRefs, List _toMergeEjbRefs) {
        super(_baseEjbRefs, _toMergeEjbRefs);
        
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.javaee.ejb.model.mergers.common.ModelElementsMerger#process()
     */
    @Override
	public List process() throws ModelException {
        List warnings = new ArrayList();
        if (getToMergeElemets() == null || getToMergeElemets().isEmpty()){
          return warnings;
        }
        
        for (Object object : getToMergeElemets()) {
          Object findMatchingRef = null;
          if(object instanceof EjbLocalRef){
            findMatchingRef = findMatchingLocalRef(getBaseElements(), object);
          } else {
            findMatchingRef = findMatchingRef(getBaseElements(), object);
          }
          if (findMatchingRef != null){
            copyMissingPropertesInBase(findMatchingRef, object);
          } else{
            getBaseElements().add(EcoreUtil.copy((EObject) object));
          }
          
          
        }

        
        return warnings;
    }

    private static EjbRef findMatchingRef(List xmlRefs,
            Object ref) throws ModelException {
    	EjbRef result = null;
        for (Object currentXMLRef: xmlRefs) {
            if (isMatchingRef(currentXMLRef, ref)) {
                result = (EjbRef) currentXMLRef;
                break;
            }
        }
        return result;
    }
    
    private static EjbLocalRef findMatchingLocalRef(List xmlRefs,
        Object ref) throws ModelException {
      EjbLocalRef result = null;
    for (Object currentXMLRef: xmlRefs) {
        if (isMatchingRef(currentXMLRef, ref)) {
            result = (EjbLocalRef) currentXMLRef;
            break;
        }
    }
    return result;
}

    private static boolean isMatchingRef(Object xmlRef,
            Object annotatedRef) throws ModelException {
        boolean result = false;
        String xmlRefName = (xmlRef instanceof EjbRef) ? ((EjbRef)xmlRef).getEjbRefName() : ((EjbLocalRef)xmlRef).getEjbRefName();
        String annotatedRefName = (annotatedRef instanceof EjbRef) ? ((EjbRef)annotatedRef).getEjbRefName() : ((EjbLocalRef)annotatedRef).getEjbRefName();
        if (xmlRefName.equals(annotatedRefName)) {
//            if(!isMatchingTargetBean(xmlRef, annotatedRef)){
//            	//TODO   check if correct
//            	return false;
//            }
            result = true;
        }

        return result;
    }

    @Override
	protected void copyMissingPropertesInBase(Object base, Object toMerge) {
	  if(base instanceof EjbRef){
	    copyEjbRefPropsInBase((EjbRef)base, (EjbRef)toMerge);
	  } else if (base instanceof EjbLocalRef){
	    copyEjbLocalRefPropsInBase((EjbLocalRef)base, (EjbLocalRef)toMerge);
	  }
	}

  private void copyEjbLocalRefPropsInBase(EjbLocalRef base, EjbLocalRef toMerge) {
    if (base.getEjbLink() == null){
      base.setEjbLink(toMerge.getEjbLink());
    }
    
    if (base.getMappedName() == null){
      base.setMappedName(toMerge.getMappedName());
    }
    
    if (!base.isSetEjbRefType()){
      base.setEjbRefType(toMerge.getEjbRefType());
    }
    
    if (base.getLocal() == null){
      base.setLocal(toMerge.getLocal());
    }
    
    if (base.getLocalHome() == null){
      base.setLocalHome(toMerge.getLocalHome());
    }
    
  }

  private void copyEjbRefPropsInBase(EjbRef base, EjbRef toMerge) {
    if (base.getEjbLink() == null){
      base.setEjbLink(toMerge.getEjbLink());
    }
    
    if (base.getMappedName() == null){
      base.setMappedName(toMerge.getMappedName());
    }
    
    if (!base.isSetEjbRefType()){
      base.setEjbRefType(toMerge.getEjbRefType());
    }
    
    if (base.getRemote() == null){
      base.setRemote(toMerge.getRemote());
    }
    
    if (base.getHome() == null){
      base.setHome(toMerge.getHome());
    }
    
  }
}
