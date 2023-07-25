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

import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.web.WebApp;

/**
 * 
 * JNDI References merger. It can be applied on Enterprise beans or Web resource artifacts.
 * 
 * @author Dimitar Giormov
 */
public class JNDIRefsMerger extends ModelElementMerger {

    /**
     * 
     * @param base - base list of references that have higher priority and are result of the merge. 
     * @param toMerge - toMerge list of references
     * @param kind
     */
    public JNDIRefsMerger(JavaEEObject base,
        JavaEEObject toMerge, int kind) {
        super(base, toMerge, kind);
        
    }

    /**
     * @return
     */
    SessionBean getBaseSessionBean() {
        return (SessionBean) getBase();
    }

    /**
     * @return
     */
    SessionBean getToMergeSessionBean () {
        return (SessionBean) getToMerge();
    }
    
    /**
     * @return
     */
    MessageDrivenBean getBaseMdbBean() {
      return (MessageDrivenBean) getBase();
  }

    /**
     * @return
     */
    MessageDrivenBean getToMergeMdbBean () {
      return (MessageDrivenBean) getToMerge();
  }
    
    /**
     * @return
     */
    WebApp getBaseWebApp() {
      return (WebApp) getBase();
  }

    /**
     * @return
     */
    WebApp getToMergeWebApp() {
      return (WebApp) getToMerge();
  }

    @Override
	public List process() throws ModelException {
        List warnings = new ArrayList();
        if(getBase() instanceof SessionBean){
          warnings.addAll(new EnvEntriesMerger(getBaseSessionBean().getEnvEntries(), getToMergeSessionBean().getEnvEntries()).process());
          warnings.addAll(new ResourceEnvRefsMerger(getBaseSessionBean().getResourceEnvRefs(), getToMergeSessionBean().getResourceEnvRefs()).process());
          warnings.addAll(new ResourceRefsMerger(getBaseSessionBean().getResourceRefs(), getToMergeSessionBean().getResourceRefs()).process());
          warnings.addAll(new MessageDestinationRefsMerger(getBaseSessionBean().getMessageDestinationRefs(), getToMergeSessionBean().getMessageDestinationRefs()).process());
          warnings.addAll(new ServiceRefsMerger(getBaseSessionBean().getServiceRefs(), getToMergeSessionBean().getServiceRefs()).process());
          warnings.addAll(new PersistenceContextRefsMerger(getBaseSessionBean().getPersistenceContextRefs(), getToMergeSessionBean().getPersistenceContextRefs()).process());
          warnings.addAll(new PersistenceUnitRefsMerger(getBaseSessionBean().getPersistenceUnitRefs(), getToMergeSessionBean().getPersistenceUnitRefs()).process());
          warnings.addAll(new EJBRefsMerger(getBaseSessionBean().getEjbLocalRefs(), getToMergeSessionBean().getEjbLocalRefs()).process());
          warnings.addAll(new EJBRefsMerger(getBaseSessionBean().getEjbRefs(), getToMergeSessionBean().getEjbRefs()).process());  
        } else if(getBase() instanceof MessageDrivenBean) {
          warnings.addAll(new EnvEntriesMerger(getBaseMdbBean().getEnvEntries(), getToMergeMdbBean().getEnvEntries()).process());
          warnings.addAll(new ResourceEnvRefsMerger(getBaseMdbBean().getResourceEnvRefs(), getToMergeMdbBean().getResourceEnvRefs()).process());
          warnings.addAll(new ResourceRefsMerger(getBaseMdbBean().getResourceRefs(), getToMergeMdbBean().getResourceRefs()).process());
          warnings.addAll(new MessageDestinationRefsMerger(getBaseMdbBean().getMessageDestinationRefs(), getToMergeMdbBean().getMessageDestinationRefs()).process());
          warnings.addAll(new ServiceRefsMerger(getBaseMdbBean().getServiceRefs(), getToMergeMdbBean().getServiceRefs()).process());
          warnings.addAll(new PersistenceContextRefsMerger(getBaseMdbBean().getPersistenceContextRefs(), getToMergeMdbBean().getPersistenceContextRefs()).process());
          warnings.addAll(new PersistenceUnitRefsMerger(getBaseMdbBean().getPersistenceUnitRefs(), getToMergeMdbBean().getPersistenceUnitRefs()).process());
          warnings.addAll(new EJBRefsMerger(getBaseMdbBean().getEjbLocalRefs(), getToMergeMdbBean().getEjbLocalRefs()).process());
          warnings.addAll(new EJBRefsMerger(getBaseMdbBean().getEjbRefs(), getToMergeMdbBean().getEjbRefs()).process());
        } else if(getBase() instanceof WebApp) {
          warnings.addAll(new EnvEntriesMerger(getBaseWebApp().getEnvEntries(), getToMergeWebApp().getEnvEntries()).process());
          warnings.addAll(new ResourceEnvRefsMerger(getBaseWebApp().getResourceEnvRefs(), getToMergeWebApp().getResourceEnvRefs()).process());
          warnings.addAll(new ResourceRefsMerger(getBaseWebApp().getResourceRefs(), getToMergeWebApp().getResourceRefs()).process());
          warnings.addAll(new MessageDestinationRefsMerger(getBaseWebApp().getMessageDestinationRefs(), getToMergeWebApp().getMessageDestinationRefs()).process());
          warnings.addAll(new ServiceRefsMerger(getBaseWebApp().getServiceRefs(), getToMergeWebApp().getServiceRefs()).process());
          warnings.addAll(new PersistenceContextRefsMerger(getBaseWebApp().getPersistenceContextRefs(), getToMergeWebApp().getPersistenceContextRefs()).process());
          warnings.addAll(new PersistenceUnitRefsMerger(getBaseWebApp().getPersistenceUnitRefs(), getToMergeWebApp().getPersistenceUnitRefs()).process());
          warnings.addAll(new EJBRefsMerger(getBaseWebApp().getEjbLocalRefs(), getToMergeWebApp().getEjbLocalRefs()).process());
          warnings.addAll(new EJBRefsMerger(getBaseWebApp().getEjbRefs(), getToMergeWebApp().getEjbRefs()).process());
        }
        return warnings;
    }
 }
