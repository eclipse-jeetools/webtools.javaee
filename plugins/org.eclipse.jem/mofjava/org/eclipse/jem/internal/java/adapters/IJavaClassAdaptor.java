package org.eclipse.jem.internal.java.adapters;
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
 *  $RCSfile: IJavaClassAdaptor.java,v $
 *  $Revision: 1.2 $  $Date: 2004/06/16 20:49:21 $ 
 */
/**
 * Insert the type's description here.
 * Creation date: (8/27/2001 1:17:46 PM)
 * @author: Administrator
 */
public interface IJavaClassAdaptor {
/**
 * Return true if the sourceType is null or if
 * it is a binary type.
 */
boolean isSourceTypeFromBinary() ;
/**
 * Return true if the sourceType can be found.
 */
boolean sourceTypeExists() ;

/**
 * Reflect the fields
 * @return <code>true</code> if reflection occurred.
 * 
 * @since 1.0.0
 */
boolean reflectFieldsIfNecessary();	

/**
 * Reflect the methods.
 * @return <code>true</code> if reflection occurred.
 * 
 * @since 1.0.0
 */
boolean reflectMethodsIfNecessary();
}




