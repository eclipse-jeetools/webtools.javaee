/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;


/*
 *  $RCSfile: IStandardBeanProxyFactory.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */


/**
 * The Standard base bean proxy factory.
 * This is the Interface that the desktop will talk
 * to.
 * Creation date: (12/3/99 11:52:09 AM)
 * @author: Joe Winchester
 */
public interface IStandardBeanProxyFactory extends IBeanProxyFactory {
/**
 * Return a new bean proxy for the primitive integer argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IIntegerBeanProxy createBeanProxyWith(int aPrimitiveInteger);
/**
 * Return a new bean proxy for the primitive character argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public ICharacterBeanProxy createBeanProxyWith(char aPrimitiveCharacter);
/**
 * Return a new bean proxy for the primitive byte argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public INumberBeanProxy createBeanProxyWith(byte aPrimitiveByte);
/**
 * Return a new bean proxy for the primitive short argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public INumberBeanProxy createBeanProxyWith(short aPrimitiveShort);
/**
 * Return a new bean proxy for the primitive long argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public INumberBeanProxy createBeanProxyWith(long aPrimitiveLong);
/**
 * Return a new bean proxy for the primitive float argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public INumberBeanProxy createBeanProxyWith(float aPrimitiveFloat);
/**
 * Return a new bean proxy for the primitive double argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public INumberBeanProxy createBeanProxyWith(double aPrimitiveDouble);
/**
 * Return a new bean proxy for the Boolean argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IBooleanBeanProxy createBeanProxyWith(Boolean aBoolean);
/**
 * Return a new bean proxy for the Integer argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IIntegerBeanProxy createBeanProxyWith(Integer anInteger);
/**
 * Return a new bean proxy for the Character argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public ICharacterBeanProxy createBeanProxyWith(Character aCharacter);
/**
 * Return a new bean proxy for the Number argument, can handle any of the numbers.
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public INumberBeanProxy createBeanProxyWith(Number aNumber);
/**
 * Return a new bean proxy for the string argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IStringBeanProxy createBeanProxyWith(String aString);
/**
 * Return a new bean proxy for the boolean argument
 * Creation date: (12/3/99 11:52:20 AM)
 * @author Joe Winchester
 */
public IBooleanBeanProxy createBeanProxyWith(boolean aBoolean);
/**
 * Create an array bean proxy.
 *
 *   - (int, new int[2] {3, 4}) will create:
 *      int [3] [4]
 *
 *   - (int[], new int[1] {1})
 *      int [1]
 *
 *   - (int[], new int[2] {2,3})
 *      int [2] [3]
 * 
 *
 *   - (int[], null) or (int[], new int[0]) or (int, null) or (int, new int[0])
 *      int [0]...
 *     or
 *     (int[][]..., null) or (int[][]..., new int[0])
 *      int[0][]...
 *     This is because an array instance with no specified dimensions is not valid. 
 *
 *   - (int[][], new int[1] {3})
 *      int[3][]
 */
public IArrayBeanProxy  createBeanProxyWith(IBeanTypeProxy type, int[] dimensions) throws ThrowableProxy;
/**
 * Create a one-dimensional array. 
 * The result will be the same as calling 
 *   createBeanProxyWith(IBeanTypeProxy type, new int[1] {x})
 * where 'x' is the value passed in as the dimension.
 */
public IArrayBeanProxy createBeanProxyWith(IBeanTypeProxy type, int dimension) throws ThrowableProxy;

/**
 * Create a bean proxy from an initialization string. 
 * <p>
 * It is not recommended that this method be commonly used. That is because no type checking will be performed
 * on the result. Whatever it evaluates to be will be what it is. It is useful for the rare cases where the desired
 * result type is not known.
 * <p>
 * It is recommended instead to use the newInstance(initString) method on the appropriate IBeanTypeProxy so that the
 * result can be type-checked to make sure the string evaluates to something of that type.
 * 
 * @param initializationString
 * @return
 * @throws ThrowableProxy
 * @throws InstantiationException
 * @throws ClassCastException
 * 
 * @since 1.0.0
 */
public IBeanProxy createBeanProxyFrom(String initializationString) throws ThrowableProxy, InstantiationException, ClassCastException;

/**
 * Release the proxy. In the case of Remote VM, this means
 * it will remove the proxy on the client side, and remove
 * the reference to the real object on the server side. On the server
 * side the real object may not go away because something else could
 * be holding onto it, but it won't be held simply because the client
 * is holding onto it.
 */
public void releaseProxy(IBeanProxy proxy);

/**
 * Create an expression.
 * @return The expression.
 * 
 * @since 1.0.0
 */
public IExpression createExpression();
}
