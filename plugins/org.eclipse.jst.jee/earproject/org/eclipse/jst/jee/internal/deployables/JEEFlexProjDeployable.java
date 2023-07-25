/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.internal.deployables;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;

/**
 * JEE module superclass.
 */
public class JEEFlexProjDeployable extends J2EEFlexProjDeployable {

	/**
	 * Constructor for JEEFlexProjDeployable.
	 * 
	 * @param project
	 * @param aComponent
	 */
	public JEEFlexProjDeployable(IProject project, IVirtualComponent aComponent) {
		super(project, aComponent);
	}

	/**
	 * Constructor for JEEFlexProjDeployable.
	 * 
	 * @param project
	 */
	public JEEFlexProjDeployable(IProject project) {
		super(project);
	}

    @Override
	protected IModule gatherModuleReference(IVirtualComponent component, IChildModuleReference child ) {
    	if (!child.isBinary()) 
    		return super.gatherModuleReference(component, child);
    	return JEEDeployableFactory.jeeInstance().createChildModule(this, child);
    }

    
    /*_________________________________
     * 
	 * Methods for specific J2EE / JEE Interfaces are below
	 *_________________________________
	 */
    public String getJNDIName(String ejbName, String interfaceName) {
    	if (!JavaEEProjectUtilities.isEJBProject(component.getProject()))
    		return null;

		EjbModuleExtensionHelper modHelper = null;
		EJBJar jar = null;
		
		IModelProvider model = ModelProviderManager.getModelProvider(component.getProject());
		if (model != null) {
			jar = (EJBJar) model.getModelObject();
			SessionBean bean = getSessionBeanNamed(jar, ejbName);
			modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
			return modHelper == null ? null : modHelper.getJavaEEJNDIName(jar, bean, interfaceName);
		}
		
		return null;
	}
    /**
     * Return List of Session beans in this jar.
     * @return java.util.List
     */
    public SessionBean getSessionBeanNamed(EJBJar jar, String beanName) {
    	
    	EnterpriseBeans allBeans = jar.getEnterpriseBeans();
    	for (Iterator iterator = allBeans.getSessionBeans().iterator(); iterator.hasNext();) {
			SessionBean bean = (SessionBean) iterator.next();
    		if (bean.getEjbName().equals(beanName))
    			return bean;
    	}
    	return null;
    }
    
}
