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
 *  $RCSfile: IIntegerBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:46 $ 
 */


/**
 * Interface to an integer bean proxy.
 * We originally had only an Integer proxy,
 * so the use of it was throughout the system.
 * We are now supporting Number instead, but
 * because there were so many places using int,
 * we've left it in.
 * Creation date: (2/6/00 8:52:42 AM)
 * @author: Joe Winchester
 */
public interface IIntegerBeanProxy extends INumberBeanProxy {
}
