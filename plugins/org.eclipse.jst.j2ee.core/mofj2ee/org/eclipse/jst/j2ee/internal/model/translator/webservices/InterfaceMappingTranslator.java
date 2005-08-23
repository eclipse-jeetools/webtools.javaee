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

package org.eclipse.jst.j2ee.internal.model.translator.webservices;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointInterfaceMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceInterfaceMapping;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class InterfaceMappingTranslator extends Translator implements JaxrpcmapXmlMapperI {

	
	private static Translator[] SERVICE_INTERFACE_10XLATORS = new Translator[] {
     new Translator(SERVICE_INTERFACE, JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_ServiceInterface()),
     create10WSDLServiceNameTranslator(),
     createPortMappingTranslator()
	};

	private static Translator[] SERVICE_INTERFACE_11XLATORS = new Translator[] {
     new Translator(SERVICE_INTERFACE, JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_ServiceInterface()),
     CommonTranslators.createQNameTranslator(WSDL_SERVICE_NAME, JaxrpcmapPackage.eINSTANCE.getServiceInterfaceMapping_WsdlServiceName()),
     createPortMappingTranslator()
	};

	private static Translator[] SEI_10XLATORS = new Translator[] {
     new Translator(SERVICE_ENDPOINT_INTERFACE, JaxrpcmapPackage.eINSTANCE.getServiceEndpointInterfaceMapping_ServiceEndpointInterface()),
      create10WSDLPortTypeTranslator(),
      create10WSDLBindingTranslator(),
      create10SEMethodMappingTranslator()
	};

	private static Translator[] SEI_11XLATORS = new Translator[] {
     new Translator(SERVICE_ENDPOINT_INTERFACE, JaxrpcmapPackage.eINSTANCE.getServiceEndpointInterfaceMapping_ServiceEndpointInterface()),
     CommonTranslators.createQNameTranslator(WSDL_PORT_TYPE, JaxrpcmapPackage.eINSTANCE.getServiceEndpointInterfaceMapping_WsdlPortType()),
     CommonTranslators.createQNameTranslator(WSDL_BINDING, JaxrpcmapPackage.eINSTANCE.getServiceEndpointInterfaceMapping_WsdlBinding()),
     create11SEMethodMappingTranslator()
	};
	
	public InterfaceMappingTranslator() {
		super(SERVICE_INTERFACE_MAPPING+','+SERVICE_ENDPOINT_INTERFACE_MAPPING, JaxrpcmapPackage.eINSTANCE.getJavaWSDLMapping_InterfaceMappings());
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;
		if (o instanceof ServiceInterfaceMapping)
			return getServiceInterfaceTranslator(versionID);
		else if (o instanceof ServiceEndpointInterfaceMapping)
			return getSEITranslator(versionID);
		else
			throw new IllegalStateException("Internal error: InterfaceMapping expected"); //$NON-NLS-1$
	}
	
	
	public String getDOMName(Object value) {
		if (value instanceof ServiceInterfaceMapping)
			return SERVICE_INTERFACE_MAPPING;
		else if (value instanceof ServiceEndpointInterfaceMapping)
			return SERVICE_ENDPOINT_INTERFACE_MAPPING;
		else 
			throw new IllegalStateException("Internal error: InterfaceMapping expected"); //$NON-NLS-1$
	}

		
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		JaxrpcmapFactory fact = JaxrpcmapPackage.eINSTANCE.getJaxrpcmapFactory();
		if (SERVICE_INTERFACE_MAPPING.equals(nodeName))
			return fact.createServiceInterfaceMapping();
		else if (SERVICE_ENDPOINT_INTERFACE_MAPPING.equals(nodeName))
			return fact.createServiceEndpointInterfaceMapping();
		else
			return null;
	}
	
	public boolean isManagedByParent() {
		return false;
	}

   private Translator[] getServiceInterfaceTranslator(int versionID)
   {
		switch (versionID) {
			case J2EEVersionConstants.J2EE_1_2_ID :
			case J2EEVersionConstants.J2EE_1_3_ID :
				return SERVICE_INTERFACE_10XLATORS;	
			default :
				return SERVICE_INTERFACE_11XLATORS; 
		}
    
   }

   private Translator[] getSEITranslator(int versionID)
   {
		switch (versionID) {
			case J2EEVersionConstants.J2EE_1_2_ID :
			case J2EEVersionConstants.J2EE_1_3_ID :
				return SEI_10XLATORS;	
			default :
				return SEI_11XLATORS; 
		}
    
   }
	private static Translator create10WSDLServiceNameTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
	    CommonPackage commonPackage = CommonPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(WSDL_SERVICE_NAME, jaxrpcmapPackage.getServiceInterfaceMapping_WsdlServiceName());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(NAMESPACEURI, commonPackage.getQName_NamespaceURI()),
			new Translator(LOCALPART, commonPackage.getQName_LocalPart())
		});
		return result;	
	}

	private static Translator createPortMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(PORT_MAPPING, jaxrpcmapPackage.getServiceInterfaceMapping_PortMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(PORT_NAME, jaxrpcmapPackage.getPortMapping_PortName()),
			new Translator(JAVA_PORT_NAME, jaxrpcmapPackage.getPortMapping_JavaPortName())
		});
		return result;	
	}
	/*
	private static Translator create10PortMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(PORT_MAPPING, jaxrpcmapPackage.getServiceInterfaceMapping_PortMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(PORT_NAME, jaxrpcmapPackage.getPortMapping_PortName()),
			new Translator(JAVA_PORT_NAME, jaxrpcmapPackage.getPortMapping_JavaPortName())
		});
		return result;	
	}
	*/

	private static Translator create10WSDLPortTypeTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
	    CommonPackage commonPackage = CommonPackage.eINSTANCE;	    
		GenericTranslator result = new GenericTranslator(WSDL_PORT_TYPE, jaxrpcmapPackage.getServiceEndpointInterfaceMapping_WsdlPortType());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(NAMESPACEURI, commonPackage.getQName_NamespaceURI()),
			new Translator(LOCALPART, commonPackage.getQName_LocalPart())
		});
		return result;	
	}

	private static Translator create10WSDLBindingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
	    CommonPackage commonPackage = CommonPackage.eINSTANCE;	    	    
		GenericTranslator result = new GenericTranslator(WSDL_BINDING, jaxrpcmapPackage.getServiceEndpointInterfaceMapping_WsdlBinding());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(NAMESPACEURI, commonPackage.getQName_NamespaceURI()),
			new Translator(LOCALPART, commonPackage.getQName_LocalPart())
		});
		return result;	
	}

	private static Translator create10SEMethodMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(SERVICE_ENDPOINT_METHOD_MAPPING, jaxrpcmapPackage.getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(JAVA_METHOD_NAME, jaxrpcmapPackage.getServiceEndpointMethodMapping_JavaMethodName()),
			new Translator(WSDL_OPERATION, jaxrpcmapPackage.getServiceEndpointMethodMapping_WsdlOperation()),
			new Translator(WRAPPED_ELEMENT, jaxrpcmapPackage.getServiceEndpointMethodMapping_WrappedElement(), Translator.EMPTY_TAG),
			create10MethodParamPartsMappingTranslator(),
			create10WSDLReturnValueMappingTranslator()
		});
		return result;	
	}

	private static Translator create11SEMethodMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(SERVICE_ENDPOINT_METHOD_MAPPING, jaxrpcmapPackage.getServiceEndpointInterfaceMapping_ServiceEndpointMethodMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(JAVA_METHOD_NAME, jaxrpcmapPackage.getServiceEndpointMethodMapping_JavaMethodName()),
			new Translator(WSDL_OPERATION, jaxrpcmapPackage.getServiceEndpointMethodMapping_WsdlOperation()),
			new Translator(WRAPPED_ELEMENT, jaxrpcmapPackage.getServiceEndpointMethodMapping_WrappedElement(), Translator.EMPTY_TAG),
			create11MethodParamPartsMappingTranslator(),
			create11WSDLReturnValueMappingTranslator()
		});
		return result;	
	}

	private static Translator create10MethodParamPartsMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(METHOD_PARAM_PARTS_MAPPING, jaxrpcmapPackage.getServiceEndpointMethodMapping_MethodParamPartsMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(PARAM_POSITION, jaxrpcmapPackage.getMethodParamPartsMapping_ParamPosition()),
			new Translator(PARAM_TYPE, jaxrpcmapPackage.getMethodParamPartsMapping_ParamType()),
			create10WSDLMessageMappingTranslator()
		});
		return result;	
	}

	private static Translator create11MethodParamPartsMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(METHOD_PARAM_PARTS_MAPPING, jaxrpcmapPackage.getServiceEndpointMethodMapping_MethodParamPartsMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(PARAM_POSITION, jaxrpcmapPackage.getMethodParamPartsMapping_ParamPosition()),
			new Translator(PARAM_TYPE, jaxrpcmapPackage.getMethodParamPartsMapping_ParamType()),
			create11WSDLMessageMappingTranslator()
		});
		return result;	
	}

	private static Translator create10WSDLMessageMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(WSDL_MESSAGE_MAPPING, jaxrpcmapPackage.getMethodParamPartsMapping_WsdlMessageMapping());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
          create10WSDLMessageTranslator(jaxrpcmapPackage.getWSDLMessageMapping_WsdlMessage()),
			new Translator(WSDL_MESSAGE_PART_NAME, jaxrpcmapPackage.getWSDLMessageMapping_WsdlMessagePartName()),
			new Translator(PARAMETER_MODE, jaxrpcmapPackage.getWSDLMessageMapping_ParameterMode()),
			new Translator(SOAP_HEADER, jaxrpcmapPackage.getWSDLMessageMapping_SoapHeader(), Translator.EMPTY_TAG),
		});
		return result;	
	}

	private static Translator create11WSDLMessageMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(WSDL_MESSAGE_MAPPING, jaxrpcmapPackage.getMethodParamPartsMapping_WsdlMessageMapping());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
          CommonTranslators.createQNameTranslator(WSDL_MESSAGE, jaxrpcmapPackage.getWSDLMessageMapping_WsdlMessage()),
			new Translator(WSDL_MESSAGE_PART_NAME, jaxrpcmapPackage.getWSDLMessageMapping_WsdlMessagePartName()),
			new Translator(PARAMETER_MODE, jaxrpcmapPackage.getWSDLMessageMapping_ParameterMode()),
			new Translator(SOAP_HEADER, jaxrpcmapPackage.getWSDLMessageMapping_SoapHeader(), Translator.EMPTY_TAG),
		});
		return result;	
	}

	private static Translator create10WSDLMessageTranslator(EStructuralFeature feature) {
	    //JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
	    CommonPackage commonPackage = CommonPackage.eINSTANCE;	    	    	    
		GenericTranslator result = new GenericTranslator(WSDL_MESSAGE, feature);
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(NAMESPACEURI, commonPackage.getQName_NamespaceURI()),
			new Translator(LOCALPART, commonPackage.getQName_LocalPart())
		});
		return result;	
	}

	private static Translator create10WSDLReturnValueMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(WSDL_RETURN_VALUE_MAPPING, jaxrpcmapPackage.getServiceEndpointMethodMapping_WsdlReturnValueMapping());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(METHOD_RETURN_VALUE, jaxrpcmapPackage.getWSDLReturnValueMapping_MethodReturnValue()),
			create10WSDLMessageTranslator(jaxrpcmapPackage.getWSDLReturnValueMapping_WsdlMessage()),
			new Translator(WSDL_MESSAGE_PART_NAME, jaxrpcmapPackage.getWSDLReturnValueMapping_WsdlMessagePartName())
		});
		return result;	
	}

	private static Translator create11WSDLReturnValueMappingTranslator() {
	    JaxrpcmapPackage jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		GenericTranslator result = new GenericTranslator(WSDL_RETURN_VALUE_MAPPING, jaxrpcmapPackage.getServiceEndpointMethodMapping_WsdlReturnValueMapping());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(METHOD_RETURN_VALUE, jaxrpcmapPackage.getWSDLReturnValueMapping_MethodReturnValue()),
          CommonTranslators.createQNameTranslator(WSDL_MESSAGE, jaxrpcmapPackage.getWSDLReturnValueMapping_WsdlMessage()),
			new Translator(WSDL_MESSAGE_PART_NAME, jaxrpcmapPackage.getWSDLReturnValueMapping_WsdlMessagePartName())
		});
		return result;	
	}

}
