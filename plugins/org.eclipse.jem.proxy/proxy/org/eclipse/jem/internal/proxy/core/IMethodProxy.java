package org.eclipse.jem.internal.proxy.core;
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
 *  $RCSfile: IMethodProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * Interface for Method Proxies
 * Creation date: (12/3/99 11:36:29 AM)
 * @author: Joe Winchester
 */
public interface IMethodProxy extends IBeanProxy {
/**
 * Answer the class the method is defined in.
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanTypeProxy getClassType();
/**
 * Answer the name of the method
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
String getName();
/**
 * Answer the parameter types of the method
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanTypeProxy[] getParameterTypes();
/**
 * Answer the return type of the method
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanTypeProxy getReturnType();
/**
 * Invoke us on the subject
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanProxy invoke(IBeanProxy subject) throws ThrowableProxy;
/**
 * Invoke us on the subject with the specified arguments
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanProxy invoke(IBeanProxy subject, IBeanProxy[] arguments) throws ThrowableProxy;
/**
 * Invoke us on the subject with the specified argument
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanProxy invoke(IBeanProxy subject, IBeanProxy argument) throws ThrowableProxy;

/**
 * Invoke us on the subject, however catch all exceptions
 * Only to be used when you don't want ThrowableExceptions. This should
 * not be the normal way to invoke.
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject);
/**
 * Invoke us on the subject with the specified arguments, however catch all exceptions
 * Only to be used when you don't want ThrowableExceptions. This should
 * not be the normal way to invoke.
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy[] arguments);
/**
 * Invoke us on the subject with the specified argument, however catch all exceptions
 * Only to be used when you don't want ThrowableExceptions. This should
 * not be the normal way to invoke.
 * Creation date: (12/3/99 11:37:12 AM)
 * @author Joe Winchester
 */
IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy argument);

}
