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
import org.eclipse.jst.javaee.core.ResourceRef;

/**
 * Merges Resource References artifacts.
 * 
 * @author Dimitar Giormov
 *
 */
public class ResourceRefsMerger extends BaseRefsMerger {
    
  /**
   *  Constructor for the class. 
   * 
   * @param base - base list with resource references.
   *               that have higher priority and are result of the merge. 
   * @param toMerge - toMerge list to be imported in base list.
   */
	public ResourceRefsMerger(List base, List toMerge) {
        super(base, toMerge);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.javaee.ejb.model.mergers.common.ModelElementsMerger#process()
     */
    @Override
	public List process() throws ModelException {
        List warnings = new ArrayList();
        for (Object oToMerge : getToMergeElemets()) {
			ResourceRef resRef = (ResourceRef) oToMerge;
			boolean found = false;
			for (Object oBase : getBaseElements()) {
				ResourceRef resRefBase = (ResourceRef) oBase;
				if (resRefBase.getResRefName().equals(resRef.getResRefName())){
					copyMissingPropertesInBase(resRefBase, resRef);
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
    
    protected void copyMissingPropertesInBase(ResourceRef baseElement, ResourceRef toMergeElement) {
    	if(baseElement.getResType() == null){
    		baseElement.setResType(toMergeElement.getResType());
    	}
    	
    	if(!baseElement.isSetResAuth()){
    		baseElement.setResAuth(toMergeElement.getResAuth());
    	}
    	
    	if(!baseElement.isSetResSharingScope()){
    		baseElement.setResSharingScope(toMergeElement.getResSharingScope());
    	}
    	
    	if(baseElement.getMappedName() == null){
    		baseElement.setMappedName(toMergeElement.getMappedName());
    	}


    }

	@Override
	protected void copyMissingPropertesInBase(Object base, Object toMerge) {
		copyMissingPropertesInBase((ResourceRef) base, (ResourceRef )toMerge);
		
	}
}
