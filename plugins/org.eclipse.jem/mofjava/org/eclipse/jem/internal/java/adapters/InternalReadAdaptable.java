package org.eclipse.jem.internal.java.adapters;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: InternalReadAdaptable.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:37:02 $ 
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




