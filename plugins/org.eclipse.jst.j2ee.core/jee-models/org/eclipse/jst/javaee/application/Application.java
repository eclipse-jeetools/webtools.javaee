/**
 * <copyright>
 * </copyright>
 *
 * $Id: Application.java,v 1.1 2007/05/16 06:42:34 cbridgha Exp $
 */
package org.eclipse.jst.javaee.application;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The applicationType defines the structure of the
 * 	application.
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getModules <em>Modules</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getSecurityRoles <em>Security Roles</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getLibraryDirectory <em>Library Directory</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication()
 * @extends JavaEEObject
 * @generated
 */
public interface Application extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Descriptions()
	 * @generated
	 */
	List getDescriptions();

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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_DisplayNames()
	 * @generated
	 */
	List getDisplayNames();

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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Icons()
	 * @generated
	 */
	List getIcons();

	/**
	 * Returns the value of the '<em><b>Modules</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.application.Module}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The application deployment descriptor must have one
	 * 	    module element for each Java EE module in the
	 * 	    application package. A module element is defined
	 * 	    by moduleType definition.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modules</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Modules()
	 * @generated
	 */
	List getModules();

	/**
	 * Returns the value of the '<em><b>Security Roles</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.SecurityRole}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Roles</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_SecurityRoles()
	 * @generated
	 */
	List getSecurityRoles();

	/**
	 * Returns the value of the '<em><b>Library Directory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	    The library-directory element specifies the pathname
	 * 	    of a directory within the application package, relative
	 * 	    to the top level of the application package.  All files
	 * 	    named "*.jar" in this directory must be made available
	 * 	    in the class path of all components included in this
	 * 	    application package.  If this element isn't specified,
	 * 	    the directory named "lib" is searched.  An empty element
	 * 	    may be used to disable searching.
	 * 
	 * 	  
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Library Directory</em>' attribute.
	 * @see #setLibraryDirectory(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_LibraryDirectory()
	 * @generated
	 */
	String getLibraryDirectory();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getLibraryDirectory <em>Library Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Library Directory</em>' attribute.
	 * @see #getLibraryDirectory()
	 * @generated
	 */
	void setLibraryDirectory(String value);

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
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * The default value is <code>"5"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 * 	  The required value for the version is 5.
	 * 
	 * 	
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #isSetVersion()
	 * @see #unsetVersion()
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getApplication_Version()
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}' attribute.
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
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	void unsetVersion();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.application.Application#getVersion <em>Version</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Version</em>' attribute is set.
	 * @see #unsetVersion()
	 * @see #getVersion()
	 * @see #setVersion(String)
	 * @generated
	 */
	boolean isSetVersion();

	/**
	 * Gets the first module matching the specified uri
	 * @param uri The uri of a module to find.
	 * @return The first matching module or null if no module is found.
	 */
	public Module getFirstModule(String uri);

	public Module getModule(String uri, String altDD);

} // Application