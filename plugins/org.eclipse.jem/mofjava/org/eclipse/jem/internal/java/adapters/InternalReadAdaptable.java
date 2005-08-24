/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.java.adapters;
/*
 *  $RCSfile: InternalReadAdaptable.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:20:25 $ 
 */

public interface InternalReadAdaptable extends org.eclipse.emf.common.notify.Notifier {
/**
 * Implementers of this interface need to contain the following method
 * implementations:
 * public Object primRefValue(EObject a) {
 *	return super.getValue(a);
 * }
 * public Object getValue(EObject a) {
 *	return ReflectionAdaptor.getValue(this, a);
 * }
 */
//public Object primRefValue(EObject a);
void setReflected(boolean aBoolean);
}




