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
package org.eclipse.jst.j2ee.commonarchivecore.impl;


import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.wst.common.emf.utilities.EtoolsCopySession;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


/**
 * OverRide class to use the proper copying of XMLResource doctypes. Had to create this class Due to
 * the way it's handled in the copy commands of EtoolsCopyUtility
 * 
 * Creation date: (11/18/01 88888888:48 PM)
 * 
 * @author: Jared Jurkiewicz
 */
public class ArchiveCopySessionUtility extends EtoolsCopySession {
	public EObject copy(EObject aRefObject, String idSuffix) {
		EObject copied = super.copy(aRefObject, idSuffix);
		if (copied instanceof ContainerManagedEntity)
			copyPrimKeyInfo((ContainerManagedEntity) aRefObject, (ContainerManagedEntity) copied);
		return copied;
	}

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
	public Resource newInstance(Resource aResource, String newUri) {
		Resource copyResource = super.newInstance(aResource, newUri);

		if (aResource instanceof XMLResource)
			((XMLResource) copyResource).setVersionID(((XMLResource) aResource).getVersionID());
		return copyResource;
	}

	public EObject getCopyIfFound(EObject anObject) {
		EObject copiedObject = super.getCopyIfFound(anObject);
		if ((anObject instanceof JavaClass) && (anObject == copiedObject)) {
			copiedObject = newInstance(anObject);
			URI uri = EcoreUtil.getURI(anObject);
			((InternalEObject) copiedObject).eSetProxyURI(uri);
		}
		return copiedObject;
	}

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

	protected void copyReference(EReference aReference, EObject aRefObject, String idSuffix, EObject copyRef) {
		if (aReference.isMany()) {
			List value = (List) aRefObject.eGet(aReference);
			if (value != null)
				copyManyReference(aReference, value, aRefObject, idSuffix, copyRef);
		} else if (aRefObject.eIsSet(aReference)) {
			Object value = aRefObject.eGet(aReference);
			if (value == null)
				value = ((InternalEObject) aRefObject).eGet(aReference, false);
			copySingleReference(aReference, (EObject) value, aRefObject, idSuffix, copyRef);
		}
	}

}
