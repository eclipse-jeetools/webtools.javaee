/**
 * <copyright>
 * </copyright>
 *
 * $Id: PTAnonymousClassDeclaration.java,v 1.1 2005/10/28 22:56:46 rkulp Exp $
 */
package org.eclipse.jem.internal.instantiation;


import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PT Anonymous Class Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An anonymous class declaration. Currently we only store the 
 * entire declaration as a string, including the "new AClass() {...}".
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTAnonymousClassDeclaration#getDeclaration <em>Declaration</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTAnonymousClassDeclaration#getImports <em>Imports</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTAnonymousClassDeclaration()
 * @model
 * @generated
 */
public interface PTAnonymousClassDeclaration extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Declaration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The declaration text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Declaration</em>' attribute.
	 * @see #setDeclaration(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTAnonymousClassDeclaration_Declaration()
	 * @model
	 * @generated
	 */
	String getDeclaration();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTAnonymousClassDeclaration#getDeclaration <em>Declaration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Declaration</em>' attribute.
	 * @see #getDeclaration()
	 * @generated
	 */
	void setDeclaration(String value);

	/**
	 * Returns the value of the '<em><b>Imports</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the imports needed in the main class by this declaration. This is a transient attribute. It is not serialized, nor is it parsed into from existing code. Its only purpose is to help code generation on a brand new declaration to know what imports should be added to the main class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Imports</em>' attribute list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTAnonymousClassDeclaration_Imports()
	 * @model type="java.lang.String" transient="true"
	 * @generated
	 */
	EList getImports();

} // PTAnonymousClassDeclaration
