package org.eclipse.jem.internal.proxy.common.remote;
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
 *  $RCSfile: TransmitableArray.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * A value can be a TransmitableArray. This means this value is an array that
 * needs to have a ValueRetriever created for it. This is used if the array has ID's in
 * it and is not an existing array on the server or all constant values.
 *
 * NOTE: It is assumed that all IBeanTypeProxies of the parms have already been retrieved.
 *       It should still work, but it could be asking for them in the middle of the request
 *       if they are not first gotton. And this could throw a non-recoverable exception if not found.
 */
public final class TransmitableArray {
	public int componentTypeID;	// The id of the type of the component type of the array.
	public Object[] array;	// The array it self.
	public TransmitableArray(int typeID, Object[] anArray) {
		componentTypeID = typeID;
		array = anArray;
	}
}


