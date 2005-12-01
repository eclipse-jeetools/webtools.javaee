/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.internal.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ElementName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLBinding;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessageMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLOperation;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLPortType;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLServiceName;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage
 * @generated
 */
public class JaxrpcmapAdapterFactory extends AdapterFactoryImpl
{

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected static JaxrpcmapPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  public JaxrpcmapAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = JaxrpcmapPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
  public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @generated
	 */
  protected JaxrpcmapSwitch modelSwitch =
		new JaxrpcmapSwitch() {
			public Object caseJavaWSDLMapping(JavaWSDLMapping object) {
				return createJavaWSDLMappingAdapter();
			}
			public Object casePackageMapping(PackageMapping object) {
				return createPackageMappingAdapter();
			}
			public Object caseJavaXMLTypeMapping(JavaXMLTypeMapping object) {
				return createJavaXMLTypeMappingAdapter();
			}
			public Object caseExceptionMapping(ExceptionMapping object) {
				return createExceptionMappingAdapter();
			}
			public Object caseServiceInterfaceMapping(ServiceInterfaceMapping object) {
				return createServiceInterfaceMappingAdapter();
			}
			public Object caseServiceEndpointInterfaceMapping(ServiceEndpointInterfaceMapping object) {
				return createServiceEndpointInterfaceMappingAdapter();
			}
			public Object caseRootTypeQname(RootTypeQname object) {
				return createRootTypeQnameAdapter();
			}
			public Object caseVariableMapping(VariableMapping object) {
				return createVariableMappingAdapter();
			}
			public Object caseWSDLMessage(WSDLMessage object) {
				return createWSDLMessageAdapter();
			}
			public Object caseConstructorParameterOrder(ConstructorParameterOrder object) {
				return createConstructorParameterOrderAdapter();
			}
			public Object caseElementName(ElementName object) {
				return createElementNameAdapter();
			}
			public Object caseWSDLServiceName(WSDLServiceName object) {
				return createWSDLServiceNameAdapter();
			}
			public Object casePortMapping(PortMapping object) {
				return createPortMappingAdapter();
			}
			public Object caseWSDLPortType(WSDLPortType object) {
				return createWSDLPortTypeAdapter();
			}
			public Object caseWSDLBinding(WSDLBinding object) {
				return createWSDLBindingAdapter();
			}
			public Object caseServiceEndpointMethodMapping(ServiceEndpointMethodMapping object) {
				return createServiceEndpointMethodMappingAdapter();
			}
			public Object caseWSDLOperation(WSDLOperation object) {
				return createWSDLOperationAdapter();
			}
			public Object caseMethodParamPartsMapping(MethodParamPartsMapping object) {
				return createMethodParamPartsMappingAdapter();
			}
			public Object caseWSDLReturnValueMapping(WSDLReturnValueMapping object) {
				return createWSDLReturnValueMappingAdapter();
			}
			public Object caseWSDLMessageMapping(WSDLMessageMapping object) {
				return createWSDLMessageMappingAdapter();
			}
			public Object caseWSDLMessagePartName(WSDLMessagePartName object) {
				return createWSDLMessagePartNameAdapter();
			}
			public Object caseInterfaceMapping(InterfaceMapping object) {
				return createInterfaceMappingAdapter();
			}
			public Object caseQName(QName object) {
				return createQNameAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
  public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping <em>Java WSDL Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaWSDLMapping
	 * @generated
	 */
  public Adapter createJavaWSDLMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageMapping <em>Package Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.PackageMapping
	 * @generated
	 */
  public Adapter createPackageMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping <em>Java XML Type Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping
	 * @generated
	 */
  public Adapter createJavaXMLTypeMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping <em>Exception Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping
	 * @generated
	 */
  public Adapter createExceptionMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping <em>Service Interface Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping
	 * @generated
	 */
  public Adapter createServiceInterfaceMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping <em>Service Endpoint Interface Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping
	 * @generated
	 */
  public Adapter createServiceEndpointInterfaceMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname <em>Root Type Qname</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname
	 * @generated
	 */
  public Adapter createRootTypeQnameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping <em>Variable Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping
	 * @generated
	 */
  public Adapter createVariableMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage <em>WSDL Message</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage
	 * @generated
	 */
  public Adapter createWSDLMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder <em>Constructor Parameter Order</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder
	 * @generated
	 */
  public Adapter createConstructorParameterOrderAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ElementName <em>Element Name</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.ElementName
	 * @generated
	 */
  public Adapter createElementNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLServiceName <em>WSDL Service Name</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLServiceName
	 * @generated
	 */
  public Adapter createWSDLServiceNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping <em>Port Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.PortMapping
	 * @generated
	 */
  public Adapter createPortMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLPortType <em>WSDL Port Type</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLPortType
	 * @generated
	 */
  public Adapter createWSDLPortTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLBinding <em>WSDL Binding</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLBinding
	 * @generated
	 */
  public Adapter createWSDLBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping <em>Service Endpoint Method Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping
	 * @generated
	 */
  public Adapter createServiceEndpointMethodMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLOperation <em>WSDL Operation</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLOperation
	 * @generated
	 */
  public Adapter createWSDLOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping <em>Method Param Parts Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping
	 * @generated
	 */
  public Adapter createMethodParamPartsMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping <em>WSDL Return Value Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping
	 * @generated
	 */
  public Adapter createWSDLReturnValueMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessageMapping <em>WSDL Message Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessageMapping
	 * @generated
	 */
  public Adapter createWSDLMessageMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName <em>WSDL Message Part Name</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessagePartName
	 * @generated
	 */
  public Adapter createWSDLMessagePartNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping <em>Interface Mapping</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.InterfaceMapping
	 * @generated
	 */
  public Adapter createInterfaceMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.jst.j2ee.common.QName <em>QName</em>}'.
	 * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.jst.j2ee.common.QName
	 * @generated
	 */
  public Adapter createQNameAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
  public Adapter createEObjectAdapter() {
		return null;
	}

} //JaxrpcmapAdapterFactory
