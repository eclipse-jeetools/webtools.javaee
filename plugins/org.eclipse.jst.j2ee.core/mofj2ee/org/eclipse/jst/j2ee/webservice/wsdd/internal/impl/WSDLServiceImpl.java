/**
 * <copyright>
 * </copyright>
 *
 * $Id: WSDLServiceImpl.java,v 1.2 2009/06/09 19:35:55 jsholl Exp $
 */
package org.eclipse.jst.j2ee.webservice.wsdd.internal.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.jst.j2ee.common.internal.impl.QNameImpl;

import org.eclipse.jst.j2ee.webservice.wsdd.WSDLService;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>WSDL Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class WSDLServiceImpl extends QNameImpl implements WSDLService {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WSDLServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WsddPackage.Literals.WSDL_SERVICE;
	}

} //WSDLServiceImpl
