/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.instantiation;
/*
 *  $RCSfile: JavaAllocation.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/27 15:33:17 $ 
 */
 
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java Allocation</b></em>'.
 * <p>
 * This class is the abstract base class of the allocation class. It is the value of the "allocation" property on a Java Object so that the actual allocation can be controlled. For example, there could be one for just init string, or one for serialized.
 * </p>
 * @since 1.0.0
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class is the abstract base class of the allocation class. It is the value of the "allocation" property on a Java Object so that the actual allocation can be controlled. For example, there could be one for just init string, or one for serialized.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getJavaAllocation()
 * @model abstract="true"
 * @generated
 */
public interface JavaAllocation extends EObject{
} // JavaAllocation
