/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.internal.IEJBModelExtenderManager;
import org.eclipse.wst.validation.internal.provisional.core.ValidationException;


/**
 * @version 1.0
 * @author
 */
public abstract class AValidationRule implements IValidationRule {

    private Set _dependents = null;

    protected EjbModuleExtensionHelper modHelper = null;

    /*
     * @see IValidationRule#addDependent(IValidationRule)
     */
    public void addDependent(IValidationRule rule) {
        if (rule == null) { return; }

        if (_dependents == null) {
            _dependents = new HashSet();
        }

        _dependents.add(rule);
    }

    /*
     * @see IValidationRule#getDependents()
     */
    public Set getDependents() {
        return _dependents;
    }

    protected EjbModuleExtensionHelper getEjbExtensionHelper(Object context) {
        return IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(context);
    }

    /**
     * @param EnterpriseBean
     * @return EnterpriseBean return the super type of an EnterpriseBean by
     *         loading the extnension plugin if extension plugin fails to load
     *         return null
     */
    public final EnterpriseBean getSuperType(EnterpriseBean enterpriseBean) {
        EnterpriseBean superType = null;
        EjbModuleExtensionHelper aModHelper = getEjbExtensionHelper(enterpriseBean);
        if (aModHelper != null) superType = aModHelper.getSuperType(enterpriseBean);
        return superType;
    }

    /**
     * @param EnterpriseBean
     * @return String - super type file name return the super type file of an
     *         EnterpriseBean by loading the extnension plugin if extension
     *         plugin fails to load return null
     */
    public final String getEJBInheritanceFileName() {
        String superTypeFileName = null;
        // TODO MDE Revisit this after the drop
        EjbModuleExtensionHelper aModHelper = getEjbExtensionHelper(null);
        if (aModHelper != null) superTypeFileName = aModHelper.getEJBInheritanceFileName();
        return superTypeFileName;
    }

    /**
     * @param EnterpriseBean
     * @return List - extended role names return the extended role method name
     *         of an EnterpriseBean by loading the extnension plugin if
     *         extension plugin fails to load return null
     */
    public final List getRoleMethodNamesExtended(EnterpriseBean enterpriseBean) {
        List roleNameExtended = null;
        EjbModuleExtensionHelper aModHelper = getEjbExtensionHelper(enterpriseBean);
        if (aModHelper != null && enterpriseBean.isContainerManagedEntity())
                roleNameExtended = aModHelper.getRoleMethodNamesExtended((ContainerManagedEntity) enterpriseBean);
        return roleNameExtended;
    }

    /*
     * @see IValidationRule#preValidate(IEJBValidationContext, Object, Object)
     */
    public void preValidate(IEJBValidationContext vc, Object targetParent, Object target)
            throws ValidationCancelledException, ValidationException {
        // By default, allocate nothing.
    }

    /*
     * @see IValidationRule#postValidate(IEJBValidationContext, Object, Object)
     */
    public void postValidate(IEJBValidationContext vc, Object targetParent, Object target)
            throws ValidationCancelledException, ValidationException {
        // By default, assume that nothing was allocated in preValidate.
    }

    /*
     * @see IValidationRule#reset()
     */
    public void reset() {
        // By default, assume that nothing needs to be reset
    }

}
