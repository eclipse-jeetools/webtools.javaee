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
import org.eclipse.jst.javaee.core.MessageDestinationRef;

/**
 * MessageDestinationRefsMerger merges MessageDestinationRef objects.
 * 
 * @author Dimitar Giormov
 *
 */
public class MessageDestinationRefsMerger extends BaseRefsMerger {
  
  
    /**
     * Constructor for the class.
     * 
     * @param base - base list with message destination references.
     *               that have higher priority and are result of the merge. 
     * @param toMerge - toMerge list to be imported in base list.
     */
    public MessageDestinationRefsMerger(List base, List toMerge) {
        super(base, toMerge);
    }

    @Override
	public List<String> process() throws ModelException {
        List<String> warnings = new ArrayList<String>();
        for (Object oToMerge : getToMergeElemets()) {
			MessageDestinationRef envEntry = (MessageDestinationRef) oToMerge;
			boolean found = false;
			for (Object oBase : getBaseElements()) {
			  MessageDestinationRef envEntryBase = (MessageDestinationRef) oBase;
				if (envEntryBase.getMessageDestinationRefName().equals(envEntry.getMessageDestinationRefName())){
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
    
    protected void copyMissingPropertesInBase(MessageDestinationRef baseElement, MessageDestinationRef toMergeElement) {
    	
    	if(baseElement.getMappedName() == null){
    		baseElement.setMappedName(toMergeElement.getMappedName());
    	}
    	
    	if(baseElement.getMessageDestinationLink() == null){
          baseElement.setMessageDestinationLink(toMergeElement.getMessageDestinationLink());
      }
    	
    	if(baseElement.getMessageDestinationType() == null){
          baseElement.setMessageDestinationType(toMergeElement.getMessageDestinationType());
      }
    	
    	if(!baseElement.isSetMessageDestinationUsage()){
          baseElement.setMessageDestinationUsage(toMergeElement.getMessageDestinationUsage());
      }

        mergeIngectionTargets(baseElement.getInjectionTargets(), toMergeElement.getInjectionTargets());
        mergeDescriptions(baseElement.getDescriptions(), toMergeElement.getDescriptions());
    }
    
    
    @Override
	protected void copyMissingPropertesInBase(Object base, Object toMerge){
    	copyMissingPropertesInBase((MessageDestinationRef) base, (MessageDestinationRef) toMerge);
    }
}
