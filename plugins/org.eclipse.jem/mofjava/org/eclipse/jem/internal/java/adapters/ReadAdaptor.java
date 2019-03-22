/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.adapters;
/*


 */

import org.eclipse.emf.ecore.EObject;
/**
 * Extended Adaptor interface which adds support for an adapter
 * providing default values for attributes.
 * Creation date: (6/6/2000 4:41:19 PM)
 * @author: Scott Rich
 */
public interface ReadAdaptor extends org.eclipse.emf.common.notify.Adapter {
	public static final String TYPE_KEY = "JavaReflection"; //$NON-NLS-1$
	public Object getValueIn(EObject object, EObject attribute);
	public boolean reflectValuesIfNecessary(); //FB
}




