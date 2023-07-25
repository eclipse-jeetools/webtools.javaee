/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBExtensionFilter;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;

public abstract class ContainerManagedEntityFilter implements EJBExtensionFilter {
 	
	private static ThreadLocal<CMPCache> _cache = new ThreadLocal<CMPCache>();

    public List filter(ContainerManagedEntity cmp){
    	List list = getCache(this, cmp);
    	if (list != null)return list;
    	
    	list = filterNotcached(cmp);
    	setCache(this, cmp, list);
    	return list;
    }
    
    /**
     * Subclasses that don't implement the filter method need to override this method 
     * to provided the filtered results. 
     */
    protected List filterNotcached(ContainerManagedEntity cmp){
    	return new ArrayList();
    }

    /**
     * All CMPAttributeFilters only operate on ContainerManagedEntityExtension
     * objects.
     */
    @Override
	public List filter(EnterpriseBean ejb) {
        if (ejb.isEntity() && ((Entity) ejb).isContainerManagedEntity()) return filter((ContainerManagedEntity) ejb);
        return new ArrayList();
    }

    protected void filterRoleAttributesByName(List allAttributes, List roleAttributes) {
        if (!roleAttributes.isEmpty()) {
            int allSize, roleSize;
            roleSize = roleAttributes.size();
            CMPAttribute roleAtt, allAtt;
            for (int i = 0; i < roleSize; i++) {
                roleAtt = (CMPAttribute) roleAttributes.get(i);
                allSize = allAttributes.size();
                for (int j = allSize - 1; j != -1; j--) {
                    allAtt = (CMPAttribute) allAttributes.get(j);
                    if (roleAtt == allAtt || roleAtt.getName().equals(allAtt.getName())) {
                        allAttributes.remove(j);
                        break;
                    }
                }
            }
        }
    }

    protected EjbModuleExtensionHelper getEjbModuleExtHelper(Object context) {
        return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(context);
    }

    protected List getLocalRelationshipRoles(ContainerManagedEntity cmp) {
        EjbModuleExtensionHelper modelExtender = null;
        if (cmp.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)
            return cmp.getRoles();
        else if ( (modelExtender = getEjbModuleExtHelper(cmp)) != null ){
            return modelExtender.getLocalRelationshipRoles_cmp11(cmp);
        } 
        return Collections.EMPTY_LIST;
    }
    
    protected List getRelationshipRoles(ContainerManagedEntity cmp) { 
        List roles = new ArrayList(); 
        collectRelationshipRoles(cmp, getEjbModuleExtHelper(cmp), roles);
        return Collections.unmodifiableList(roles);
    }
    
    public void collectRelationshipRoles(ContainerManagedEntity cmp, EjbModuleExtensionHelper extensionHelper, List containerList) {
        if(cmp == null)
            return;
        containerList.addAll(getLocalRelationshipRoles(cmp));
        if(extensionHelper != null)
            collectRelationshipRoles((ContainerManagedEntity) extensionHelper.getSuperType(cmp), extensionHelper, containerList);        
    }
    
    /**
     * Clear the cache and turn off caching.
     */
	public void clearCache(){
		if (_cache == null)return;
		getCache().clear();
		getCache().setEnabled(false);
	}
	
	
	protected void setCache(ContainerManagedEntityFilter filter, ContainerManagedEntity cmp, List list) {
		getCache().set(filter, cmp, list);
	}


	protected List getCache(ContainerManagedEntityFilter filter, ContainerManagedEntity cmp) {
		return getCache().get(filter, cmp);
	}
	
	/**
	 * Answer the CMPCache for this thread.
	 * @return
	 */
	private CMPCache getCache(){
		CMPCache cache = _cache.get();
		if (cache == null){
			cache = new CMPCache();
			_cache.set(cache);
		}
		return cache;
	}
	
	/**
	 * This method needs to be called (with the parameter true) if you wish the 
	 * filter results to be cached. By default the results are not cached.
	 * <p>
	 * The cache is thread based. When done the clearCache method should be called.
	 * 
	 * @param isEnabled set this to true to have the filter results cached.
	 */
	public void enableCache(boolean isEnabled){
		getCache().setEnabled(isEnabled);
	}

    /**
     * Keep a thread local cache of filtered results.
     * @author karasiuk
     *
     */
	private static class CMPCache {
		
		/*
		 * We discovered some very deep code paths when validating EJBs. In one example project that 
		 * only had a few beans, it took 12 hours to validate. Keeping a cache during validation
		 * reduced the time to seconds.
		 */
		
		private Map 		_map = new HashMap(30);
		
		/** Is the cache turned on, by default it is not. */
		private boolean		_enabled;
		
		public void setEnabled(boolean isEnabled){
			_enabled = isEnabled;
		}

		public List get(ContainerManagedEntityFilter filter, ContainerManagedEntity cmp) {
			if (!_enabled)return null;
			
			Map map = (Map)_map.get(filter);
			if (map == null){
				map = new HashMap(100);
				_map.put(filter, map);
			}
			return (List)map.get(cmp);
		}

		public void set(ContainerManagedEntityFilter filter, ContainerManagedEntity cmp, List list) {
			if (!_enabled)return; 
			
			Map map = (Map)_map.get(filter);
			if (map == null){
				map = new HashMap(100);
				_map.put(filter, map);
			}
			
			map.put(cmp, list);			
		}
		
		public void clear(){
			_map.clear();
		}
		
	}

}
