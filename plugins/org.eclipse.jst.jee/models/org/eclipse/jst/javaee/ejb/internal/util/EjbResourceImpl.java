/**
 * <copyright>
 * </copyright>
 *
 * $Id: EjbResourceImpl.java,v 1.2 2007/03/23 19:06:33 cbridgha Exp $
 */
package org.eclipse.jst.javaee.ejb.internal.util;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.util.EjbResourceFactoryImpl
 * @generated
 */
public class EjbResourceImpl extends XMLResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public EjbResourceImpl(URI uri) {
		super(uri);
	}
	
	/**
	 * Return the first element in the EList.
	 */
	public EObject getRootObject() {
		if (contents == null || contents.isEmpty())
			return null;
		return (EObject) getContents().get(0);
	}

} //EjbResourceImpl
