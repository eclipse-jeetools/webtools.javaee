/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IInvokable.java,v $
 *  $Revision: 1.1 $  $Date: 2004/08/10 17:52:10 $ 
 */
package org.eclipse.jem.internal.proxy.core;
 

/**
 * Interface for invoking method calls on the vm.
 * IMethodProxy implements it for when you have a method proxy available,
 * but you can also get a straight invokable which will get the
 * proxy on the vm at the time of execution.
 * <p>
 * The method proxy factory can be used to return either method
 * proxies when the invokable will be used over and over, and
 * it can return IInvokable's when it is one shot deal. Though even
 * when one-shot deal it can be used over and over. There is just overhead because the 
 * method is looked up each time. So a reasonable compromise would be if
 * using it infrequently or use closely together once or twice it is better
 * to use a Invokable instead of a method proxy.
 * @since 1.0.0
 */
public interface IInvokable {

	/**
	 * Invoke us on the subject
	 * Creation date: (12/3/99 11:37:12 AM)
	 * @author Joe Winchester
	 */
	IBeanProxy invoke(IBeanProxy subject) throws ThrowableProxy;

	/**
	 * Invoke us on the subject with the specified argument
	 * Creation date: (12/3/99 11:37:12 AM)
	 * @author Joe Winchester
	 */
	IBeanProxy invoke(IBeanProxy subject, IBeanProxy argument) throws ThrowableProxy;

	/**
	 * Invoke us on the subject with the specified arguments
	 * Creation date: (12/3/99 11:37:12 AM)
	 * @author Joe Winchester
	 */
	IBeanProxy invoke(IBeanProxy subject, IBeanProxy[] arguments) throws ThrowableProxy;

	/**
	 * Invoke us on the subject, however catch all exceptions
	 * Only to be used when you don't want ThrowableExceptions. This should
	 * not be the normal way to invoke.
	 * Creation date: (12/3/99 11:37:12 AM)
	 * @author Joe Winchester
	 */
	IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject);

	/**
	 * Invoke us on the subject with the specified argument, however catch all exceptions
	 * Only to be used when you don't want ThrowableExceptions. This should
	 * not be the normal way to invoke.
	 * Creation date: (12/3/99 11:37:12 AM)
	 * @author Joe Winchester
	 */
	IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy argument);

	/**
	 * Invoke us on the subject with the specified arguments, however catch all exceptions
	 * Only to be used when you don't want ThrowableExceptions. This should
	 * not be the normal way to invoke.
	 * Creation date: (12/3/99 11:37:12 AM)
	 * @author Joe Winchester
	 */
	IBeanProxy invokeCatchThrowableExceptions(IBeanProxy subject, IBeanProxy[] arguments);

}
