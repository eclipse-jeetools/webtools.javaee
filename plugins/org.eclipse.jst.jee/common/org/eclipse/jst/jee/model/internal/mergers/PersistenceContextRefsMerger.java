/***********************************************************************
 * Copyright (c) 2008, 2019 by SAP AG, Walldorf. 
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
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PropertyType;

/**
 * Merges persistence context references artifacts.
 * 
 * @author Dimitar Giormov
 *
 */
public class PersistenceContextRefsMerger extends BaseRefsMerger {
	
    /**
     *  Constructor for the class. 
     * 
     * @param base - base list with persistence context references.
     *               that have higher priority and are result of the merge. 
     * @param toMerge - toMerge list to be imported in base list.
     */
    public PersistenceContextRefsMerger(List _base, List _toMerge) {
        super(_base, _toMerge);
    }

    
    /* (non-Javadoc)
     * @see org.eclipse.jst.javaee.ejb.model.mergers.common.ModelElementsMerger#process()
     */
    @Override
	public List process() throws ModelException {
        List warnings = new ArrayList();
        for (Object oToMerge : getToMergeElemets()) {
			PersistenceContextRef envEntry = (PersistenceContextRef) oToMerge;
			boolean found = false;
			for (Object oBase : getBaseElements()) {
				PersistenceContextRef envEntryBase = (PersistenceContextRef) oBase;
				if (envEntryBase.getPersistenceContextRefName().equals(envEntry.getPersistenceContextRefName())){
					copyMissingPropertesInBase(envEntryBase, envEntry);
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
    

    protected void copyMissingPropertesInBase(PersistenceContextRef baseElement, PersistenceContextRef toMergeElement) {
    	if(baseElement.getPersistenceUnitName() == null){
    		baseElement.setPersistenceUnitName(toMergeElement.getPersistenceUnitName());
    	}
    	
    	if(baseElement.getPersistenceContextType() == null){
    		baseElement.setPersistenceContextType(toMergeElement.getPersistenceContextType());
    	}
    	
    	if(baseElement.getMappedName() == null){
          baseElement.setMappedName(toMergeElement.getMappedName());
      }
    	
    	copyMissingPersistentPropertiesInBase(baseElement.getPersistenceProperties(), toMergeElement.getPersistenceProperties());
    }
    
    
    private void copyMissingPersistentPropertiesInBase(
        List persistenceProperties, List persistenceProperties2) {
      for (Object mergeProps : persistenceProperties2) {
        PropertyType mProp = (PropertyType) mergeProps;
        if(!containsProperty(persistenceProperties , mProp)){
          persistenceProperties.add( mProp);
        }
        
      }
    }
    
    private boolean containsProperty(List list, PropertyType p){
      for (Object baseProps : list) {
        PropertyType bProp = (PropertyType) baseProps;
        if(p.getName().equals(bProp.getName())){
          return true;
        }
      }
      return false;
    }


    @Override
	protected void copyMissingPropertesInBase(Object base, Object toMerge){
    	copyMissingPropertesInBase((PersistenceContextRef) base, (PersistenceContextRef) toMerge);
    }
    
}
