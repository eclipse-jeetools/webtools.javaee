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




