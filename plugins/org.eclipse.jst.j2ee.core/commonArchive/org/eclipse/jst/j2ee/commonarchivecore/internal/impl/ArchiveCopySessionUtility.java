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
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;


import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.internal.impl.ContainerManagedEntityImpl;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.internal.emf.utilities.EtoolsCopySession;
import org.eclipse.wst.common.internal.emf.utilities.EtoolsCopyUtility;


/**
 * OverRide class to use the proper copying of XMLResource doctypes. Had to create this class Due to
 * the way it's handled in the copy commands of EtoolsCopyUtility
 * 
 * Creation date: (11/18/01 88888888:48 PM)
 * 
 * @author: Jared Jurkiewicz
 */
public class ArchiveCopySessionUtility extends EtoolsCopySession {
	@Override
	public EObject copy(EObject aRefObject, String idSuffix) {
		EObject copied = super.copy(aRefObject, idSuffix);
		if (copied instanceof ContainerManagedEntity)
			copyPrimKeyInfo((ContainerManagedEntity) aRefObject, (ContainerManagedEntity) copied);
		return copied;
	}

	@Override
	public EObject primCopy(EObject aRefObject, String idSuffix) {
		EObject copied = super.primCopy(aRefObject, idSuffix);
		if (copied instanceof ContainerManagedEntity)
			copyPrimKeyInfo((ContainerManagedEntity) aRefObject, (ContainerManagedEntity) copied);
		return copied;
	}

	public ArchiveCopySessionUtility(EtoolsCopyUtility aCopyUtility) {
		super(aCopyUtility);
	}

	/**
	 * @see com.ibm.etools.emf.ecore.utilities.copy.EtoolsCopySession#newInstance(Resource, String)
	 */
	@Override
	public Resource newInstance(Resource aResource, String newUri) {
		Resource copyResource = super.newInstance(aResource, newUri);

		if (aResource instanceof XMLResource)
			((XMLResource) copyResource).setVersionID(((XMLResource) aResource).getVersionID());
		return copyResource;
	}

	@Override
	public EObject getCopyIfFound(EObject anObject) {
		EObject copiedObject = super.getCopyIfFound(anObject);
		if ((anObject instanceof JavaClass) && (anObject == copiedObject)) {
			copiedObject = newInstance(anObject);
			URI uri = EcoreUtil.getURI(anObject);
			((InternalEObject) copiedObject).eSetProxyURI(uri);
		}
		return copiedObject;
	}

	@Override
	public EObject copyObject(EObject aRefObject, String idSuffix) {
		EObject copied = super.copyObject(aRefObject, idSuffix);
		if (copied instanceof ContainerManagedEntity)
			copyPrimKeyInfo((ContainerManagedEntity) aRefObject, (ContainerManagedEntity) copied);
		return copied;
	}

	public void copyPrimKeyInfo(ContainerManagedEntity source, ContainerManagedEntity copied) {
		CMPAttribute primKeyField = source.getPrimKeyField();
		if (primKeyField != null)
			copied.setPrimKeyField(primKeyField);
	}

	/*
	 * Super class override to handle unresolvable proxies (JavaClass)
	 */

	@Override
	protected void copyReference(EReference aReference, EObject aRefObject, String idSuffix, EObject copyRef) {
		if (aReference.isMany()) {
			if (shouldCopyReference(aReference, aRefObject)) { // Bugzilla 177397
				List value = (List) aRefObject.eGet(aReference);
				if (value != null)
					copyManyReference(aReference, value, aRefObject, idSuffix, copyRef);
			}
		} else if (aRefObject.eIsSet(aReference)) {
			Object value = aRefObject.eGet(aReference);
			if (value == null)
				value = aRefObject.eGet(aReference, false);
			copySingleReference(aReference, (EObject) value, aRefObject, idSuffix, copyRef);
		}
	}

    /**
     * <p>Constant introduced for bugzilla 177397: The name of the CMP 'keyAttributes'
     * attribute, which is not to be copied if in an uninitialized state.</p>
     */
    
    public static final String CMP_KEY_ATTRIBUTES_NAME = "keyAttributes"; //$NON-NLS-1$

    /**
     * <p>Test introduced for bugzilla 177397: Tell if a specified reference
     * should be copied.  This implementation checks for a CMP Bean, and for
     * the key attributes attribute, and answers false when this attribute
     * is in an uninitialized state.</p>
     * 
     * <p>This attribute is initialized using reflection; there are copy cases
     * where the parent EJB Jar does not have its full classpath environment
     * available, which prevents the loading of dependent classes.  Since the
     * initialization is lazy, there is no need to resolve this when copying;
     * access to the copy target can perform the initialization.</p>
     *
     * @param aReference The reference attribute being copied.
     * @param aRefObject The model object being copied.
     * 
     * @return True if the reference attribute is to be copied.
     *         False if the reference attribute is not to be copied.
     */
    
    protected boolean shouldCopyReference(EReference aReference, EObject aRefObject)
    {        
        if ( !(aRefObject instanceof ContainerManagedEntityImpl) ||
             !aReference.getName().equals(CMP_KEY_ATTRIBUTES_NAME) )
            return true;    
        
        ContainerManagedEntityImpl cmpBean = (ContainerManagedEntityImpl) aRefObject;
        
        return cmpBean.getIsInitializedKeyAttributes();
    }
}
