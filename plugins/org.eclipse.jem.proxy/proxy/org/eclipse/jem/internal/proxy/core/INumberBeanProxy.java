package org.eclipse.jem.internal.proxy.core;
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
 *  $RCSfile: INumberBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:46 $ 
 */


/**
 * Interface to an integer bean proxy.
 * Creation date: (2/6/00 8:52:42 AM)
 * @author: Joe Winchester
 */
public interface INumberBeanProxy extends IBeanProxy {
/**
 * Return the primitive byte value of the proxied Number
 * Creation date: (2/6/00 8:52:59 AM)
 */
byte byteValue();
/**
 * Return the primitive double value of the proxied Number
 * Creation date: (2/6/00 8:52:59 AM)
 */
double doubleValue();
/**
 * Return the primitive float value of the proxied Number
 * Creation date: (2/6/00 8:52:59 AM)
 */
float floatValue();
/**
 * Return the primitive int value of the proxied Number
 * Creation date: (2/6/00 8:52:59 AM)
 */
int intValue();
/**
 * Return the primitive long value of the proxied Number
 * Creation date: (2/6/00 8:52:59 AM)
 */
long longValue();
/**
 * Return the value as a Number.
 * Creation date: (2/6/00 8:52:59 AM)
 */
Number numberValue();
/**
 * Return the primitive short value of the proxied Number
 * Creation date: (2/6/00 8:52:59 AM)
 */
short shortValue();
}
