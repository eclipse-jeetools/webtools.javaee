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
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The messageadapterType specifies information about the
 * messaging capabilities of the resource adapter. This
 * contains information specific to the implementation of the
 * resource adapter library as specified through the
 * messagelistener element.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.MessageAdapter#getMessageListeners <em>Message Listeners</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getMessageAdapter()
 * @model
 * @generated
 * @since 1.0 */
public interface MessageAdapter extends EObject{
	/**
	 * Returns the value of the '<em><b>Message Listeners</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.jca.MessageListener}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Listeners</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Listeners</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getMessageAdapter_MessageListeners()
	 * @model type="org.eclipse.jst.j2ee.jca.MessageListener" containment="true" required="true"
	 * @generated
	 */
	EList getMessageListeners();

} // MessageAdapter
