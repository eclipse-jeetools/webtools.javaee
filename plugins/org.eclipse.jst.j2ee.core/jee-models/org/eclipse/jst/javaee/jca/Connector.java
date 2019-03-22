/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The connectorType defines a resource adapter.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getVendorName <em>Vendor Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getEisType <em>Eis Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getResourceadapterVersion <em>Resourceadapter Version</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getLicense <em>License</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getResourceadapter <em>Resourceadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getRequiredWorkContext <em>Required Work Context</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.Connector#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector()
 * @extends JavaEEObject
 * @generated
 */
public interface Connector extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Module Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element module-name specifies the name of the
	 *             resource adapter.
	 *             
	 *             If there is no module-name specified, the module-name
	 *             is determined as defined in Section EE.8.1.1 and EE.8.1.2 
	 *             of the Java Platform, Enterprise Edition (Java EE) 
	 *             Specification, version 6.
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Module Name</em>' attribute.
	 * @see #setModuleName(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_ModuleName()
	 * @generated
	 */
	String getModuleName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getModuleName <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Name</em>' attribute.
	 * @see #getModuleName()
	 * @generated
	 */
	void setModuleName(String value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_Descriptions()
	 * @generated
	 */
	List<Description> getDescriptions();

	/**
	 * Returns the value of the '<em><b>Display Names</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.DisplayName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Names</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Names</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_DisplayNames()
	 * @generated
	 */
	List<DisplayName> getDisplayNames();

	/**
	 * Returns the value of the '<em><b>Icons</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Icon}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Icons</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Icons</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Vendor Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element vendor-name specifies the name of
	 *             resource adapter provider vendor.
	 * 
	 *             If there is no vendor-name specified, the application 
	 *             server must consider the default "" (empty string) as
	 *             the name of the resource adapter provider vendor.
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vendor Name</em>' attribute.
	 * @see #setVendorName(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_VendorName()
	 * @generated
	 */
	String getVendorName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getVendorName <em>Vendor Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vendor Name</em>' attribute.
	 * @see #getVendorName()
	 * @generated
	 */
	void setVendorName(String value);

	/**
	 * Returns the value of the '<em><b>Eis Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element eis-type contains information about the
	 *             type of the EIS. For example, the type of an EIS can
	 *             be product name of EIS independent of any version
	 *             info.
	 *             
	 *             This helps in identifying EIS instances that can be
	 *             used with this resource adapter.
	 *             
	 *             If there is no eis-type specified, the application 
	 *             server must consider the default "" (empty string) as
	 *             the type of the EIS.
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Eis Type</em>' attribute.
	 * @see #setEisType(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_EisType()
	 * @generated
	 */
	String getEisType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getEisType <em>Eis Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eis Type</em>' attribute.
	 * @see #getEisType()
	 * @generated
	 */
	void setEisType(String value);

	/**
	 * Returns the value of the '<em><b>Resourceadapter Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element resourceadapter-version specifies a string-based version
	 *             of the resource adapter from the resource adapter
	 *             provider.
	 *             
	 *             If there is no resourceadapter-version specified, the application 
	 *             server must consider the default "" (empty string) as
	 *             the version of the resource adapter.
	 *             	
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resourceadapter Version</em>' attribute.
	 * @see #setResourceadapterVersion(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_ResourceadapterVersion()
	 * @generated
	 */
	String getResourceadapterVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getResourceadapterVersion <em>Resourceadapter Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceadapter Version</em>' attribute.
	 * @see #getResourceadapterVersion()
	 * @generated
	 */
	void setResourceadapterVersion(String value);

	/**
	 * Returns the value of the '<em><b>License</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>License</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>License</em>' containment reference.
	 * @see #setLicense(License)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_License()
	 * @generated
	 */
	License getLicense();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getLicense <em>License</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>License</em>' containment reference.
	 * @see #getLicense()
	 * @generated
	 */
	void setLicense(License value);

	/**
	 * Returns the value of the '<em><b>Resourceadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resourceadapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resourceadapter</em>' containment reference.
	 * @see #setResourceadapter(ResourceAdapter)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_Resourceadapter()
	 * @generated
	 */
	ResourceAdapter getResourceadapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getResourceadapter <em>Resourceadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceadapter</em>' containment reference.
	 * @see #getResourceadapter()
	 * @generated
	 */
	void setResourceadapter(ResourceAdapter value);

	/**
	 * Returns the value of the '<em><b>Required Work Context</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element required-work-context specifies a fully qualified class 
	 *             name that implements WorkContext interface, that the resource adapter 
	 *             requires the application server to support.
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required Work Context</em>' attribute list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_RequiredWorkContext()
	 * @generated
	 */
	List<String> getRequiredWorkContext();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The metadata-complete attribute defines whether the deployment 
	 *           descriptor for the resource adapter module is complete, or whether
	 *           the class files available to the module and packaged with the resource 
	 *           adapter should be examined for annotations that specify deployment 
	 *           information.
	 *           
	 *           If metadata-complete is set to "true", the deployment tool of the 
	 *           application server must ignore any annotations that specify deployment 
	 *           information, which might be present in the class files of the 
	 *           application.If metadata-complete is not specified or is set to "false", 
	 *           the deployment tool must examine the class files of the application for 
	 *           annotations, as specified by this specification. If the 
	 *           deployment descriptor is not included or is included but not marked 
	 *           metadata-complete, the deployment tool will process annotations.
	 *           
	 *           Application servers must assume that metadata-complete is true for 
	 *           resource adapter modules with deployment descriptor version 
	 *           lower than 1.6.
	 *           		
	 *           @since Java EE 6, Connector 1.6
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_MetadataComplete()
	 * @generated
	 */
	boolean isMetadataComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @generated
	 */
	void setMetadataComplete(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	void unsetMetadataComplete();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.Connector#isMetadataComplete <em>Metadata Complete</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Metadata Complete</em>' attribute is set.
	 * @see #unsetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	boolean isSetMetadataComplete();

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>"1.7"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The version indicates the version of the schema to be used by the
	 *           deployment tool. This element doesn't have a default, and the resource adapter 
	 *           developer/deployer is required to specify it. The element allows the deployment 
	 *           tool to choose which schema to validate the descriptor against.
	 *           	  
	 *           @since Java EE 6, Connector 1.6
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnector_Version()
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	void unsetVersion();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.Connector#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	boolean isSetVersion();

} // Connector
