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

import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.webservice.internal.WebServiceConstants;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.wst.common.internal.emf.resource.ConstantAttributeTranslator;
import org.eclipse.wst.common.internal.emf.resource.GenericTranslator;
import org.eclipse.wst.common.internal.emf.resource.IDTranslator;
import org.eclipse.wst.common.internal.emf.resource.RootTranslator;
import org.eclipse.wst.common.internal.emf.resource.Translator;




public class JaxrpcmapTranslator extends RootTranslator implements JaxrpcmapXmlMapperI, J2EEConstants{
	public static JaxrpcmapTranslator INSTANCE = new JaxrpcmapTranslator();
	private static Translator[] children10;
	private static Translator[] children11;
	private JaxrpcmapPackage jaxrpcmapPackage;
	private CommonPackage commonPackage;
	
	
	protected JaxrpcmapTranslator() {
		super(JAVA_WSDL_MAPPING, JaxrpcmapPackage.eINSTANCE.getJavaWSDLMapping());
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
	    jaxrpcmapPackage = JaxrpcmapPackage.eINSTANCE;
		commonPackage = CommonPackage.eINSTANCE;

		switch (versionID) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :
				if (children10 == null)
				{
					children10 = create10Children();
				}
				return children10;	
			default :
				if (children11 == null)
				{
					children11 = create11Children();
				}
				return children11; 
		}
	}


	protected Translator[] create10Children() {
		
		return new Translator[] {
			IDTranslator.INSTANCE,
			createPackageMappingTranslator(),
			create10JavaXMLTypeMappingTranslator(),
			create10ExceptionMappingTranslator(),
			new InterfaceMappingTranslator()
		};
	}

	protected Translator[] create11Children() {
		
		return new Translator[] {
			IDTranslator.INSTANCE,
			new ConstantAttributeTranslator(XML_NS, J2EE_NS_URL),
			new ConstantAttributeTranslator(XML_NS_XSI, XSI_NS_URL),
			new ConstantAttributeTranslator(XSI_SCHEMA_LOCATION, J2EE_NS_URL+' '+WebServiceConstants.JAXRPCMAP_SCHEMA_LOC),
			new ConstantAttributeTranslator(VERSION, WebServiceConstants.WEBSERVICE_SCHEMA_VERSION),			  			  
			createPackageMappingTranslator(),
			create11JavaXMLTypeMappingTranslator(),
			create11ExceptionMappingTranslator(),
			new InterfaceMappingTranslator()
		};
	}

	public Translator createPackageMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(PACKAGE_MAPPING, jaxrpcmapPackage.getJavaWSDLMapping_PackageMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(PACKAGE_TYPE, jaxrpcmapPackage.getPackageMapping_PackageType()),
			new Translator(NAMESPACEURI, jaxrpcmapPackage.getPackageMapping_NamespaceURI())
		});
		return result;	
	}

	public Translator create10JavaXMLTypeMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(JAVA_XML_TYPE_MAPPING, jaxrpcmapPackage.getJavaWSDLMapping_JavaXMLTypeMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(CLASS_TYPE, jaxrpcmapPackage.getJavaXMLTypeMapping_ClassType()),
			create10RootTypeQnameTranslator(),
			new Translator(QNAME_SCOPE, jaxrpcmapPackage.getJavaXMLTypeMapping_QnameScope()),
			create10VariableMappingTranslator()
		});
		return result;	
	}

	public Translator create11JavaXMLTypeMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(JAVA_XML_TYPE_MAPPING, jaxrpcmapPackage.getJavaWSDLMapping_JavaXMLTypeMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(JAVA_TYPE, jaxrpcmapPackage.getJavaXMLTypeMapping_ClassType()),
			new Translator(ANONYMOUS_TYPE_QNAME, jaxrpcmapPackage.getJavaXMLTypeMapping_AnonymousTypeQname()),
			CommonTranslators.createQNameTranslator(ROOT_TYPE_QNAME, jaxrpcmapPackage.getJavaXMLTypeMapping_RootTypeQname()),
			//create10RootTypeQnameTranslator(),  //RootTypeQName is still going to work the old (1.3) way because of anonymous types.
			new Translator(QNAME_SCOPE, jaxrpcmapPackage.getJavaXMLTypeMapping_QnameScope()),
			create11VariableMappingTranslator()
		});
		return result;	
	}

	public Translator create10ExceptionMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(EXCEPTION_MAPPING, jaxrpcmapPackage.getJavaWSDLMapping_ExceptionMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(EXCEPTION_TYPE, jaxrpcmapPackage.getExceptionMapping_ExceptionType()),
			create10WSDLMessageTranslator(),
			createConstructorParamOrderTranslator()
		});
		return result;	
	}

	public Translator create11ExceptionMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(EXCEPTION_MAPPING, jaxrpcmapPackage.getJavaWSDLMapping_ExceptionMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(EXCEPTION_TYPE, jaxrpcmapPackage.getExceptionMapping_ExceptionType()),
			CommonTranslators.createQNameTranslator(WSDL_MESSAGE, jaxrpcmapPackage.getExceptionMapping_WsdlMessage()),
			new Translator(WSDL_MESSAGE_PART_NAME, jaxrpcmapPackage.getExceptionMapping_WsdlMessagePartName()),
			createConstructorParamOrderTranslator()
		});
		return result;	
	}

	public Translator createInterfaceMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(SERVICE_INTERFACE_MAPPING, jaxrpcmapPackage.getJavaWSDLMapping());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(PACKAGE_TYPE, jaxrpcmapPackage.getPackageMapping_PackageType()),
			new Translator(NAMESPACEURI, jaxrpcmapPackage.getPackageMapping_NamespaceURI())
		});
		return result;	
	}


	public Translator create10RootTypeQnameTranslator() {
	    
		GenericTranslator result = new GenericTranslator(ROOT_TYPE_QNAME, jaxrpcmapPackage.getJavaXMLTypeMapping_RootTypeQname());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(NAMESPACEURI, commonPackage.getQName_NamespaceURI()),
			new Translator(LOCALPART, commonPackage.getQName_LocalPart())			
		});
		return result;	
	}

	public Translator create10VariableMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(VARIABLE_MAPPING, jaxrpcmapPackage.getJavaXMLTypeMapping_VariableMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(JAVA_VARIABLE_NAME, jaxrpcmapPackage.getVariableMapping_JavaVariableName()),
			new Translator(DATA_MEMBER, jaxrpcmapPackage.getVariableMapping_DataMember(), Translator.EMPTY_TAG),
			new Translator(XML_ELEMENT_NAME, jaxrpcmapPackage.getVariableMapping_XmlElementName())
		});
		return result;	
	}

	public Translator create11VariableMappingTranslator() {
	    
		GenericTranslator result = new GenericTranslator(VARIABLE_MAPPING, jaxrpcmapPackage.getJavaXMLTypeMapping_VariableMappings());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(JAVA_VARIABLE_NAME, jaxrpcmapPackage.getVariableMapping_JavaVariableName()),
			new Translator(DATA_MEMBER, jaxrpcmapPackage.getVariableMapping_DataMember(), Translator.EMPTY_TAG),
			new Translator(XML_ATTRIBUTE_NAME, jaxrpcmapPackage.getVariableMapping_XmlAttributeName()),
			new Translator(XML_ELEMENT_NAME, jaxrpcmapPackage.getVariableMapping_XmlElementName()),
			new Translator(XML_WILDCARD, jaxrpcmapPackage.getVariableMapping_XmlWildcard(), Translator.EMPTY_TAG)
		});
		return result;	
	}

	public Translator create10WSDLMessageTranslator() {
	    
		GenericTranslator result = new GenericTranslator(WSDL_MESSAGE, jaxrpcmapPackage.getExceptionMapping_WsdlMessage());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new Translator(NAMESPACEURI, commonPackage.getQName_NamespaceURI()),
			new Translator(LOCALPART, commonPackage.getQName_LocalPart())			
		});
		return result;	
	}

	public Translator createConstructorParamOrderTranslator() {
		GenericTranslator result = new GenericTranslator(CONSTRUCTOR_PARAMETER_ORDER, jaxrpcmapPackage.getExceptionMapping_ConstructorParameterOrder());
		result.setChildren(new Translator[] {
			IDTranslator.INSTANCE,
			new ElementNameTranslator()
		});
		return result;	
	}
	
}

