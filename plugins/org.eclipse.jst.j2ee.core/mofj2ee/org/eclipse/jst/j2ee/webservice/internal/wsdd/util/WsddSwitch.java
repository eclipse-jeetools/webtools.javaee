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
package org.eclipse.jst.j2ee.webservice.internal.wsdd.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.webservice.wsdd.BeanLink;
import org.eclipse.jst.j2ee.webservice.wsdd.EJBLink;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.ServiceImplBean;
import org.eclipse.jst.j2ee.webservice.wsdd.ServletLink;
import org.eclipse.jst.j2ee.webservice.wsdd.WSDLPort;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;



/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.webservice.wsdd.WsddPackage
 * @generated
 */
public class WsddSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected static WsddPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public WsddSwitch() {
		if (modelPackage == null) {
			modelPackage = WsddPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
  public Object doSwitch(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case WsddPackage.WEB_SERVICES: {
					WebServices webServices = (WebServices)theEObject;
					Object result = caseWebServices(webServices);
					if (result == null) result = caseCompatibilityDescriptionGroup(webServices);
					if (result == null) result = caseDescriptionGroup(webServices);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.WEB_SERVICE_DESCRIPTION: {
					WebServiceDescription webServiceDescription = (WebServiceDescription)theEObject;
					Object result = caseWebServiceDescription(webServiceDescription);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.PORT_COMPONENT: {
					PortComponent portComponent = (PortComponent)theEObject;
					Object result = casePortComponent(portComponent);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.WSDL_PORT: {
					WSDLPort wsdlPort = (WSDLPort)theEObject;
					Object result = caseWSDLPort(wsdlPort);
					if (result == null) result = caseQName(wsdlPort);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.SERVICE_IMPL_BEAN: {
					ServiceImplBean serviceImplBean = (ServiceImplBean)theEObject;
					Object result = caseServiceImplBean(serviceImplBean);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.SERVLET_LINK: {
					ServletLink servletLink = (ServletLink)theEObject;
					Object result = caseServletLink(servletLink);
					if (result == null) result = caseBeanLink(servletLink);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.EJB_LINK: {
					EJBLink ejbLink = (EJBLink)theEObject;
					Object result = caseEJBLink(ejbLink);
					if (result == null) result = caseBeanLink(ejbLink);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.HANDLER: {
					Handler handler = (Handler)theEObject;
					Object result = caseHandler(handler);
					if (result == null) result = caseCompatibilityDescriptionGroup(handler);
					if (result == null) result = caseDescriptionGroup(handler);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				case WsddPackage.BEAN_LINK: {
					BeanLink beanLink = (BeanLink)theEObject;
					Object result = caseBeanLink(beanLink);
					if (result == null) result = defaultCase(theEObject);
					return result;
				}
				default: return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Web Services</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Web Services</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseWebServices(WebServices object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Web Service Description</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Web Service Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseWebServiceDescription(WebServiceDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Port Component</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Port Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object casePortComponent(PortComponent object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>WSDL Port</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>WSDL Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseWSDLPort(WSDLPort object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Service Impl Bean</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Service Impl Bean</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseServiceImplBean(ServiceImplBean object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Servlet Link</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Servlet Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseServletLink(ServletLink object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EJB Link</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EJB Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseEJBLink(EJBLink object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Handler</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Handler</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseHandler(Handler object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Bean Link</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Bean Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseBeanLink(BeanLink object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseDescriptionGroup(DescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>Compatibility Description Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCompatibilityDescriptionGroup(CompatibilityDescriptionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>QName</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>QName</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
  public Object caseQName(QName object) {
		return null;
	}

	/**
	 * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
  public Object defaultCase(EObject object) {
		return null;
	}

} //WsddSwitch
