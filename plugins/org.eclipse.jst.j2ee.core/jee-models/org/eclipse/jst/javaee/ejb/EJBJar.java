/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EJB Jar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The ejb-jarType defines the root element of the EJB
 *         deployment descriptor. It contains
 *         
 *         - an optional description of the ejb-jar file
 *         - an optional display name
 *         - an optional icon that contains a small and a large
 *         icon file name
 *         - an optional module name. Only applicable to
 *         stand-alone ejb-jars or ejb-jars packaged in an ear.
 *         Ignored if specified for an ejb-jar.xml within a .war file.
 *         In that case, standard .war file module-name rules apply.
 *         - structural information about all included
 *         enterprise beans that is not specified through
 *         annotations
 *         - structural information about interceptor classes
 *         - a descriptor for container managed relationships, 
 *         if any. 
 *         - an optional application-assembly descriptor
 *         - an optional name of an ejb-client-jar file for the 
 *         ejb-jar.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getEnterpriseBeans <em>Enterprise Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getInterceptors <em>Interceptors</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getAssemblyDescriptor <em>Assembly Descriptor</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getEjbClientJar <em>Ejb Client Jar</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EJBJar#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar()
 * @extends JavaEEObject
 * @generated
 */
public interface EJBJar extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_Descriptions()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_DisplayNames()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_Icons()
	 * @generated
	 */
	List<Icon> getIcons();

	/**
	 * Returns the value of the '<em><b>Module Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             @since Java EE 6, EJB 3.1
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Module Name</em>' attribute.
	 * @see #setModuleName(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_ModuleName()
	 * @generated
	 */
	String getModuleName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getModuleName <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module Name</em>' attribute.
	 * @see #getModuleName()
	 * @generated
	 */
	void setModuleName(String value);

	/**
	 * Returns the value of the '<em><b>Enterprise Beans</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enterprise Beans</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enterprise Beans</em>' containment reference.
	 * @see #setEnterpriseBeans(EnterpriseBeans)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_EnterpriseBeans()
	 * @generated
	 */
	EnterpriseBeans getEnterpriseBeans();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getEnterpriseBeans <em>Enterprise Beans</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enterprise Beans</em>' containment reference.
	 * @see #getEnterpriseBeans()
	 * @generated
	 */
	void setEnterpriseBeans(EnterpriseBeans value);

	/**
	 * Returns the value of the '<em><b>Interceptors</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interceptors</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interceptors</em>' containment reference.
	 * @see #setInterceptors(InterceptorsType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_Interceptors()
	 * @generated
	 */
	InterceptorsType getInterceptors();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getInterceptors <em>Interceptors</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interceptors</em>' containment reference.
	 * @see #getInterceptors()
	 * @generated
	 */
	void setInterceptors(InterceptorsType value);

	/**
	 * Returns the value of the '<em><b>Relationships</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationships</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationships</em>' containment reference.
	 * @see #setRelationships(Relationships)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_Relationships()
	 * @generated
	 */
	Relationships getRelationships();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getRelationships <em>Relationships</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relationships</em>' containment reference.
	 * @see #getRelationships()
	 * @generated
	 */
	void setRelationships(Relationships value);

	/**
	 * Returns the value of the '<em><b>Assembly Descriptor</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             Providing an assembly-descriptor in the deployment
	 *             descriptor is optional for the ejb-jar file or .war file
	 *             producer.
	 *             
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Assembly Descriptor</em>' containment reference.
	 * @see #setAssemblyDescriptor(AssemblyDescriptor)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_AssemblyDescriptor()
	 * @generated
	 */
	AssemblyDescriptor getAssemblyDescriptor();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getAssemblyDescriptor <em>Assembly Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assembly Descriptor</em>' containment reference.
	 * @see #getAssemblyDescriptor()
	 * @generated
	 */
	void setAssemblyDescriptor(AssemblyDescriptor value);

	/**
	 * Returns the value of the '<em><b>Ejb Client Jar</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *             [
	 *             The optional ejb-client-jar element specifies a JAR
	 *             file that contains the class files necessary for a
	 *             client program to access the
	 *             enterprise beans in the ejb-jar file.
	 *             
	 *             Example:
	 *             
	 *             	  <ejb-client-jar>employee_service_client.jar
	 *             	  </ejb-client-jar>
	 *             
	 * 
	 *             @since Java EE 5, EJB 3.0
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb Client Jar</em>' attribute.
	 * @see #setEjbClientJar(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_EjbClientJar()
	 * @generated
	 */
	String getEjbClientJar();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getEjbClientJar <em>Ejb Client Jar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Client Jar</em>' attribute.
	 * @see #getEjbClientJar()
	 * @generated
	 */
	void setEjbClientJar(String value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getId <em>Id</em>}' attribute.
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
	 *           The metadata-complete attribute defines whether this
	 *           deployment descriptor and other related deployment
	 *           descriptors for this module (e.g., web service
	 *           descriptors) are complete, or whether the class
	 *           files available to this module and packaged with
	 *           this application should be examined for annotations
	 *           that specify deployment information.
	 *           
	 *           If metadata-complete is set to "true", the deployment
	 *           tool must ignore any annotations that specify deployment
	 *           information, which might be present in the class files
	 *           of the application.
	 *           
	 *           If metadata-complete is not specified or is set to
	 *           "false", the deployment tool must examine the class
	 *           files of the application for annotations, as
	 *           specified by the specifications.
	 *           
	 *           @since Java EE 5, EJB 3.0
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isSetMetadataComplete()
	 * @see #unsetMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_MetadataComplete()
	 * @generated
	 */
	boolean isMetadataComplete();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#isMetadataComplete <em>Metadata Complete</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMetadataComplete()
	 * @see #isMetadataComplete()
	 * @see #setMetadataComplete(boolean)
	 * @generated
	 */
	void unsetMetadataComplete();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#isMetadataComplete <em>Metadata Complete</em>}' attribute is set.
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
	 * The default value is <code>"3.2"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *           The version specifies the version of the
	 *           EJB specification that the instance document must 
	 *           comply with. This information enables deployment tools
	 *           to validate a particular EJB Deployment
	 *           Descriptor with respect to a specific version of the EJB
	 *           schema. 
	 *           
	 *           @since Java EE 5, EJB 3.0
	 *         
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEJBJar_Version()
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getVersion <em>Version</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	void unsetVersion();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.EJBJar#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	boolean isSetVersion();

} // EJBJar