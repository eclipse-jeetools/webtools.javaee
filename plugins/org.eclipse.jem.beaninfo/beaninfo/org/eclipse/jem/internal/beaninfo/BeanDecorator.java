package org.eclipse.jem.internal.beaninfo;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeanDecorator.java,v $
 *  $Revision: 1.3 $  $Date: 2004/03/06 11:28:26 $ 
 */


import org.eclipse.jem.java.JavaClass;
import java.net.URL;
import java.util.Map;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bean Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperBehaviors <em>Merge Super Behaviors</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectProperties <em>Introspect Properties</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectBehaviors <em>Introspect Behaviors</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectEvents <em>Introspect Events</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isDoBeaninfo <em>Do Beaninfo</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getCustomizerClass <em>Customizer Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator()
 * @model 
 * @generated
 */


public interface BeanDecorator extends FeatureDecorator{
	/**
	 * Set merge super properties proxy. This can't be answered from the BeanDescriptor proxy,
	 * so it must be explicitly set from the beaninfo class adapter.
	 */
	public void setMergeSuperPropertiesProxy(Boolean bool);
	
	/**
 	 * Set merge super behaviors proxy. This can't be answered from the BeanDescriptor proxy,
	 * so it must be explicitly set from the beaninfo class adapter.
	 * @param value The new value of the MergeSuperBehaviors attribute
	 */
	public void setMergeSuperBehaviorsProxy(Boolean value);
	
	/**
 	 * Set merge super events proxy. This can't be answered from the BeanDescriptor proxy,
	 * so it must be explicitly set from the beaninfo class adapter.
	 * @param value The new value of the MergeSuperBehaviors attribute
	 */
	public void setMergeSuperEventsProxy(Boolean value);	
	
	/**
	 * Returns the value of the '<em><b>Merge Super Properties</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Super Properties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the properties of super types be merged when asking for eAllAttributes/eAllReferences.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Super Properties</em>' attribute.
	 * @see #isSetMergeSuperProperties()
	 * @see #unsetMergeSuperProperties()
	 * @see #setMergeSuperProperties(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_MergeSuperProperties()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isMergeSuperProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Super Properties</em>' attribute.
	 * @see #isSetMergeSuperProperties()
	 * @see #unsetMergeSuperProperties()
	 * @see #isMergeSuperProperties()
	 * @generated
	 */
	void setMergeSuperProperties(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMergeSuperProperties()
	 * @see #isMergeSuperProperties()
	 * @see #setMergeSuperProperties(boolean)
	 * @generated
	 */
	void unsetMergeSuperProperties();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Merge Super Properties</em>' attribute is set.
	 * @see #unsetMergeSuperProperties()
	 * @see #isMergeSuperProperties()
	 * @see #setMergeSuperProperties(boolean)
	 * @generated
	 */
	boolean isSetMergeSuperProperties();

	/**
	 * Returns the value of the '<em><b>Merge Super Behaviors</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Super Behaviors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the behaviors of super types be merged when asking for eAllBehaviors.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Super Behaviors</em>' attribute.
	 * @see #isSetMergeSuperBehaviors()
	 * @see #unsetMergeSuperBehaviors()
	 * @see #setMergeSuperBehaviors(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_MergeSuperBehaviors()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isMergeSuperBehaviors();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperBehaviors <em>Merge Super Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Super Behaviors</em>' attribute.
	 * @see #isSetMergeSuperBehaviors()
	 * @see #unsetMergeSuperBehaviors()
	 * @see #isMergeSuperBehaviors()
	 * @generated
	 */
	void setMergeSuperBehaviors(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperBehaviors <em>Merge Super Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMergeSuperBehaviors()
	 * @see #isMergeSuperBehaviors()
	 * @see #setMergeSuperBehaviors(boolean)
	 * @generated
	 */
	void unsetMergeSuperBehaviors();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperBehaviors <em>Merge Super Behaviors</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Merge Super Behaviors</em>' attribute is set.
	 * @see #unsetMergeSuperBehaviors()
	 * @see #isMergeSuperBehaviors()
	 * @see #setMergeSuperBehaviors(boolean)
	 * @generated
	 */
	boolean isSetMergeSuperBehaviors();

	/**
	 * Returns the value of the '<em><b>Merge Super Events</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Super Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the events of super types be merged when asking for eAllEvents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Super Events</em>' attribute.
	 * @see #isSetMergeSuperEvents()
	 * @see #unsetMergeSuperEvents()
	 * @see #setMergeSuperEvents(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_MergeSuperEvents()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isMergeSuperEvents();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Super Events</em>' attribute.
	 * @see #isSetMergeSuperEvents()
	 * @see #unsetMergeSuperEvents()
	 * @see #isMergeSuperEvents()
	 * @generated
	 */
	void setMergeSuperEvents(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMergeSuperEvents()
	 * @see #isMergeSuperEvents()
	 * @see #setMergeSuperEvents(boolean)
	 * @generated
	 */
	void unsetMergeSuperEvents();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Merge Super Events</em>' attribute is set.
	 * @see #unsetMergeSuperEvents()
	 * @see #isMergeSuperEvents()
	 * @see #setMergeSuperEvents(boolean)
	 * @generated
	 */
	boolean isSetMergeSuperEvents();

	/**
	 * Returns the value of the '<em><b>Introspect Properties</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Introspect Properties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the properties from the introspection be added to the class. This allows properties to not be introspected and to use only what is defined explicitly in the JavaClass xmi file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Introspect Properties</em>' attribute.
	 * @see #setIntrospectProperties(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_IntrospectProperties()
	 * @model default="true"
	 * @generated
	 */
	boolean isIntrospectProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectProperties <em>Introspect Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Introspect Properties</em>' attribute.
	 * @see #isIntrospectProperties()
	 * @generated
	 */
	void setIntrospectProperties(boolean value);

	/**
	 * Returns the value of the '<em><b>Introspect Behaviors</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Introspect Behaviors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the behaviors from the introspection be added to the class. This allows behaviors to not be introspected and to use only what is defined explicitly in the JavaClass xmi file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Introspect Behaviors</em>' attribute.
	 * @see #setIntrospectBehaviors(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_IntrospectBehaviors()
	 * @model default="true"
	 * @generated
	 */
	boolean isIntrospectBehaviors();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectBehaviors <em>Introspect Behaviors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Introspect Behaviors</em>' attribute.
	 * @see #isIntrospectBehaviors()
	 * @generated
	 */
	void setIntrospectBehaviors(boolean value);

	/**
	 * Returns the value of the '<em><b>Introspect Events</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Introspect Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the events from the introspection be added to the class. This allows events to not be introspected and to use only what is defined explicitly in the JavaClass xmi file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Introspect Events</em>' attribute.
	 * @see #setIntrospectEvents(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_IntrospectEvents()
	 * @model default="true"
	 * @generated
	 */
	boolean isIntrospectEvents();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectEvents <em>Introspect Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Introspect Events</em>' attribute.
	 * @see #isIntrospectEvents()
	 * @generated
	 */
	void setIntrospectEvents(boolean value);

	/**
	 * Returns the value of the '<em><b>Customizer Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customizer Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customizer Class</em>' reference.
	 * @see #setCustomizerClass(JavaClass)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_CustomizerClass()
	 * @model 
	 * @generated
	 */
	JavaClass getCustomizerClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getCustomizerClass <em>Customizer Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customizer Class</em>' reference.
	 * @see #getCustomizerClass()
	 * @generated
	 */
	void setCustomizerClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Do Beaninfo</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Do Beaninfo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This means do we go and get the beaninfo from the remote vm. If false, then it will not try to get the beaninfo. This doesn't prevent introspection through reflection. That is controled by the separate introspect... attributes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Do Beaninfo</em>' attribute.
	 * @see #setDoBeaninfo(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_DoBeaninfo()
	 * @model default="true"
	 * @generated
	 */
	boolean isDoBeaninfo();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isDoBeaninfo <em>Do Beaninfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Do Beaninfo</em>' attribute.
	 * @see #isDoBeaninfo()
	 * @generated
	 */
	void setDoBeaninfo(boolean value);

	/**
	 * Return the URL of a 16x16 Color icon
	 */
	URL getIconURL();
	
	Map getStyleDetails();
	
}
