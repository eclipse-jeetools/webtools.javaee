/*******************************************************************************
 * Copyright (c) 2001, 2025 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Apr 2, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author dfholttp
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MethodParamsTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI{
	protected static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;

	//private EStructuralFeature eFeature;

    public MethodParamsTranslator(EStructuralFeature eFeature) {
        super(METHOD_PARAMS+'/'+METHOD_PARAM , eFeature);
        //this.eFeature = eFeature;
        this.fStyle |= Translator.EMPTY_CONTENT_IS_SIGNIFICANT;
    }


    /* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.Translator#isMultiValued()
     */
    @Override
	public boolean isMultiValued() {
		return true;
    }
    
    /* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.Translator#getMOFChildren(org.eclipse.emf.ecore.EObject)
     */
    @Override
	public List getMOFChildren(EObject mofObject) {
 		return ((MethodElement)mofObject).getMethodParams();
    }
    
    /* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.Translator#setMOFValue(org.eclipse.emf.ecore.EObject, java.lang.Object)
     */
    @Override
	public void setMOFValue(EObject mofObject, Object value) {
		List methodParams = ((MethodElement)mofObject).getMethodParams();
		
    	if(value == null || value.toString().length() == 0) {  
    		if(methodParams == null || methodParams.size() == 0 ) {
    			setMOFValueFromEmptyDOMPath(mofObject);
			}
    	} else {
			((MethodElement)mofObject).addMethodParams((String)value);
    	}
    }
    /* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#setMOFValue(org.eclipse.emf.common.notify.Notifier, java.lang.Object, int)
	 */
	@Override
	public void setMOFValue(Notifier owner, Object value, int newIndex) {
		((MethodElement)owner).addMethodParams((String)value);
	}

    /* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.Translator#clearList(org.eclipse.emf.ecore.EObject)
     */
    @Override
	public void clearList(EObject mofObject) {
		mofObject.eUnset(feature);
    }
    
    /* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#shouldRenderEmptyDOMPath(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public boolean shouldRenderEmptyDOMPath(EObject eObject) {
		MethodElement me = ((MethodElement)eObject);
		return me.hasMethodParams() && me.getMethodParams().isEmpty();
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#setMOFValueFromEmptyDOMPath(org.eclipse.emf.ecore.EObject)
	 */
	@Override
	public void setMOFValueFromEmptyDOMPath(EObject eObject) {
		((MethodElement)eObject).applyZeroParams();
	}
}
