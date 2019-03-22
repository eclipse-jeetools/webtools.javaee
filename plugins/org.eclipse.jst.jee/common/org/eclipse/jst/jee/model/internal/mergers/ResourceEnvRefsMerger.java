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
import org.eclipse.jst.javaee.core.ResourceEnvRef;

/**
 * Merges Resource Environment References artifacts.
 *  
 * @author Dimitar Giormov
 *
 */
public class ResourceEnvRefsMerger extends BaseRefsMerger {
    
  /**
   *  Constructor for the class. 
   * 
   * @param base - base list with resource environment references.
   *               that have higher priority and are result of the merge. 
   * @param toMerge - toMerge list to be imported in base list.
   */
    public ResourceEnvRefsMerger(List base, List toMerge) {
        super(base, toMerge);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.javaee.ejb.model.mergers.common.ModelElementsMerger#process()
     */
    @Override
	public List process() throws ModelException {
        List warnings = new ArrayList();
        for (Object oToMerge : getToMergeElemets()) {
			ResourceEnvRef resEnvRef = (ResourceEnvRef) oToMerge;
			boolean found = false;
			for (Object oBase : getBaseElements()) {
				ResourceEnvRef resourceEnvRefBase = (ResourceEnvRef) oBase;
				if (resourceEnvRefBase.getResourceEnvRefName().equals(resEnvRef.getResourceEnvRefName())){
					copyMissingPropertesInBase(resourceEnvRefBase, resEnvRef);
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
    
    protected void copyMissingPropertesInBase(ResourceEnvRef baseRef, ResourceEnvRef toMergeRef) {
    	if(baseRef.getResourceEnvRefType() == null){
    		baseRef.setResourceEnvRefType(toMergeRef.getResourceEnvRefType());
    	}
    	
    	if(baseRef.getMappedName() == null){
    		baseRef.setMappedName(toMergeRef.getMappedName());
    	}
    	
      mergeIngectionTargets(baseRef.getInjectionTargets(), toMergeRef.getInjectionTargets());
      mergeDescriptions(baseRef.getDescriptions(), toMergeRef.getDescriptions());
    }

	@Override
	protected void copyMissingPropertesInBase(Object base, Object toMerge) {
		copyMissingPropertesInBase((ResourceEnvRef) base, (ResourceEnvRef)toMerge);
		
	}
}
