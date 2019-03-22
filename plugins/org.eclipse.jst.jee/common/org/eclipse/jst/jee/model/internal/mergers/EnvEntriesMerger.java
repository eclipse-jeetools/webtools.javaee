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
import org.eclipse.jst.javaee.core.EnvEntry;

/**
 * Merges Environment Entries.
 * 
 * @author Dimitar Giormov
 */
public class EnvEntriesMerger extends BaseRefsMerger {

    /**
     *  Constructor for the class. 
     * 
     * @param baseList - base list with environment entries
     * @param toMergeList - toMerge list with environment entries
     */
    public EnvEntriesMerger(List baseList, List toMergeList) {
        super(baseList, toMergeList);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.javaee.ejb.model.mergers.common.ModelElementsMerger#process()
     */
    @Override
	public List process() throws ModelException {
        List warnings = new ArrayList();
        for (Object oToMerge : getToMergeElemets()) {
			EnvEntry envEntry = (EnvEntry) oToMerge;
			boolean found = false;
			for (Object oBase : getBaseElements()) {
				EnvEntry envEntryBase = (EnvEntry) oBase;
				if (envEntryBase.getEnvEntryName().equals(envEntry.getEnvEntryName())){
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
    
    
    protected void copyMissingPropertesInBase(EnvEntry baseElement, EnvEntry toMergeElement) {
    	if(baseElement.getEnvEntryValue() == null){
    		baseElement.setEnvEntryValue(toMergeElement.getEnvEntryValue());
    	}
    	
    	if(baseElement.getMappedName() == null){
    		baseElement.setMappedName(toMergeElement.getMappedName());
    	}
    	
    	if(!baseElement.isSetEnvEntryType()){
          baseElement.setEnvEntryType(toMergeElement.getEnvEntryType());
      }

        mergeIngectionTargets(baseElement.getInjectionTargets(), toMergeElement.getInjectionTargets());
        mergeDescriptions(baseElement.getDescriptions(), toMergeElement.getDescriptions());
    }
    
    
    @Override
	protected void copyMissingPropertesInBase(Object base, Object toMerge){
    	copyMissingPropertesInBase((EnvEntry) base, (EnvEntry) toMerge);
    }
}
