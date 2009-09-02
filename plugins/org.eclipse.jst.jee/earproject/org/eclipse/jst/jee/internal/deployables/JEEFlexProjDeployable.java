/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.internal.deployables;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.ServerUtil;

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

	protected boolean shouldIncludeUtilityComponent(IVirtualComponent virtualComp,IVirtualReference[] references, IEARModelProvider model) {
		// If the component module is an EAR we know all archives are filtered out of virtual component members
		// and we will return only those archives which are not binary J2EE modules in the EAR DD.  These J2EE modules will
		// be returned by getChildModules()
		if (JavaEEProjectUtilities.isEARProject(component.getProject()))
			return virtualComp != null && virtualComp.isBinary() && !isNestedJ2EEModule(virtualComp, references, model);
		else 
			return super.shouldIncludeUtilityComponent(virtualComp, references, ArtifactEdit.class.isInstance(model) ? (ArtifactEdit)model : null);
	}
	    
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

    protected IModule gatherModuleReference(IVirtualComponent component, IVirtualComponent targetComponent ) {
    	IModule module = super.gatherModuleReference(component, targetComponent);
    	// Handle binary module components
    	if (targetComponent instanceof J2EEModuleVirtualArchiveComponent) {
    		if (JavaEEProjectUtilities.isEARProject(component.getProject()) || targetComponent.getProject()!=component.getProject())
    			module = ServerUtil.getModule(JEEDeployableFactory.ID+":"+targetComponent.getName()); //$NON-NLS-1$
    	}
		return module;
    }    

    @Override
	protected boolean shouldIncludeUtilityComponent(IVirtualComponent virtualComp,IVirtualReference[] references, ArtifactEdit edit) {
    	if( edit instanceof IEARModelProvider) {
    		if (JavaEEProjectUtilities.isEARProject(component.getProject()))
    			return virtualComp != null && virtualComp.isBinary() && !isNestedJ2EEModule(virtualComp, references, (IEARModelProvider)edit);
    	}
    	return super.shouldIncludeUtilityComponent(virtualComp, references, edit);
	}

    /**
     * Determine if the component parameter is of a valid version and type
	 * 
     * @param aComponent a binary "utility" component
     * @param references the list of references the parent ear component has
     * @param model the artifact edit of the ear, if it exists 
     * @return
     */
    private boolean isNestedJ2EEModule(IVirtualComponent aComponent, IVirtualReference[] references, IEARModelProvider model) {
    	if (model==null) 
			return false;
		int version = JavaEEBinaryComponentHelper.
			getJavaEEQuickPeek(aComponent).getJavaEEVersion();
		boolean knownType = 
			JavaEEBinaryComponentHelper.getJavaEEQuickPeek(aComponent).getType() != JavaEEQuickPeek.UNKNOWN;
		
		return version >= J2EEVersionConstants.JEE_5_0_ID && knownType;
    }
}
