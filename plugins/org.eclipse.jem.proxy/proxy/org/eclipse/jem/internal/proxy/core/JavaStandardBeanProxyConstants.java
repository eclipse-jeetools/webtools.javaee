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
 *  $RCSfile: JavaStandardBeanProxyConstants.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:46 $ 
 */


/**
 * Standard Proxy constants.
 *
 * Use the static accessor method (getConstants()) to get the appropriate 
 * constants instance for the registry. This is done so that until the
 * the constants are needed, they aren't registered.
 *
 * There are some standard utility functions too.
 *
 * NOTE: Since everything in here just proxies, there is
 *       no need to have them separated by VM. That is why
 *       this in the Proxy package.
 *
 * This is final because this can't be extended. This specific one
 * will be registered with the factory for this key. Any extensions
 * must be done in their own constants and registry key.
 *
 * Creation date: (4/7/00 4:47:48 PM)
 * @author: Administrator
 */
public final class JavaStandardBeanProxyConstants {
	public static final String REGISTRY_KEY = "STANDARDPROXYCONSTANTS:"; //$NON-NLS-1$
		
	final IBeanTypeProxy fCollectionType;
	final IBeanTypeProxy fListType;
	final IBeanTypeProxy fIteratorType;
	final IBeanTypeProxy fListIteratorType;
	final IBeanTypeProxy fEnumerationType;	
	final IBeanTypeProxy fSystemType;
	final IBeanTypeProxy fStringType;
	
	IMethodProxy fCollectionAdd, fCollectionAddAll, fCollectionClear, fCollectionContains,
		fCollectionContainsAll, fCollectionIsEmpty, fCollectionIterator, fCollectionRemove,
		fCollectionRemoveAll, fCollectionRetainAll, fCollectionSize, fCollectionToArray,
		fCollectionToArrayWithArray;
		
	IMethodProxy fListAddWithInt, fListAddAllWithInt, fListGet, fListIndexOf, fListLastIndexOf,
		fListListIterator, fListListIteratorWithInt, fListRemoveInt, 
		fListSet, fListSubList;
		
	IMethodProxy fIteratorHasNext, fIteratorNext, fIteratorRemove;

	IMethodProxy fListIteratorAdd, fListIteratorHasPrevious, fListIteratorNextIndex, fListIteratorPrevious,
		fListIteratorPreviousIndex, fListIteratorRemove, fListIteratorSet;
		
	IMethodProxy fEnumerationHasMoreElements, fEnumerationNextElement;
	
	IMethodProxy fSystemArraycopy;

/**
 * Get the constants instance for the specified registry.
 */
public static JavaStandardBeanProxyConstants getConstants(ProxyFactoryRegistry registry) {
	JavaStandardBeanProxyConstants constants = (JavaStandardBeanProxyConstants) registry.getConstants(REGISTRY_KEY);
	if (constants == null)
		registry.registerConstants(REGISTRY_KEY, constants = new JavaStandardBeanProxyConstants(registry));	
	return constants;
}


/**
 * IDEJavaBeanConstants constructor comment.
 */
private JavaStandardBeanProxyConstants(ProxyFactoryRegistry registry) {
	super();
	
	IStandardBeanTypeProxyFactory typeFactory = registry.getBeanTypeProxyFactory();
	fCollectionType = typeFactory.getBeanTypeProxy("java.util.Collection");//$NON-NLS-1$
	fListType = typeFactory.getBeanTypeProxy("java.util.List"); //$NON-NLS-1$
	fIteratorType = typeFactory.getBeanTypeProxy("java.util.Iterator"); //$NON-NLS-1$
	fListIteratorType = typeFactory.getBeanTypeProxy("java.util.ListIterator"); //$NON-NLS-1$
	fEnumerationType = typeFactory.getBeanTypeProxy("java.util.Enumeration");	 //$NON-NLS-1$
	fSystemType = typeFactory.getBeanTypeProxy("java.lang.System"); //$NON-NLS-1$
	fStringType = typeFactory.getBeanTypeProxy("java.lang.String"); //$NON-NLS-1$
}

public IBeanTypeProxy getStringType() {
	return fStringType;
}

/**
 * Collection method accessors
 */
public IMethodProxy getCollectionAdd() {
	if (fCollectionAdd == null)
		fCollectionAdd = fCollectionType.getMethodProxy("add", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionAdd;
}
	
public IMethodProxy getCollectionAddAll(){
	if (fCollectionAddAll == null)
		fCollectionAddAll = fCollectionType.getMethodProxy("addAll", "java.util.Collection"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionAddAll;
}

public IMethodProxy getCollectionClear() {
	if (fCollectionClear == null)
		fCollectionClear = fCollectionType.getMethodProxy("clear"); //$NON-NLS-1$
	return fCollectionClear;
}

public IMethodProxy getCollectionContains() {
	if (fCollectionContains == null)
		fCollectionContains = fCollectionType.getMethodProxy("contains", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionContains;
}
	
public IMethodProxy getCollectionContainsAll() {
	if (fCollectionContainsAll == null)
		fCollectionContainsAll = fCollectionType.getMethodProxy("containsAll", "java.util.Collection"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionContainsAll;
}

public IMethodProxy getCollectionIsEmpty() {
	if (fCollectionIsEmpty == null)
		fCollectionIsEmpty = fCollectionType.getMethodProxy("isEmpty"); //$NON-NLS-1$
	return fCollectionIsEmpty;
}

public IMethodProxy getCollectionIterator() {
	if (fCollectionIterator == null)
		fCollectionIterator = fCollectionType.getMethodProxy("iterator"); //$NON-NLS-1$
	return fCollectionIterator;
}

public IMethodProxy getCollectionRemove() {
	if (fCollectionRemove == null)
		fCollectionRemove = fCollectionType.getMethodProxy("remove", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionRemove;
}

public IMethodProxy getCollectionRemoveAll() {
	if (fCollectionRemoveAll == null)
		fCollectionRemoveAll = fCollectionType.getMethodProxy("removeAll", "java.util.Collection"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionRemoveAll;
}

public IMethodProxy getCollectionRetainAll() {
	if (fCollectionRetainAll == null)
		fCollectionRetainAll = fCollectionType.getMethodProxy("retainAll", "java.util.Collection"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionRetainAll;
}

public IMethodProxy getCollectionSize()  {
	if (fCollectionSize == null)
		fCollectionSize = fCollectionType.getMethodProxy("size"); //$NON-NLS-1$
	return fCollectionSize;
}

public IMethodProxy getCollectionToArray() {
	if (fCollectionToArray == null)
		fCollectionToArray = fCollectionType.getMethodProxy("toArray"); //$NON-NLS-1$
	return fCollectionToArray;
}

public IMethodProxy getCollectionToArrayWithArray() {
	if (fCollectionToArrayWithArray == null)
		fCollectionToArrayWithArray = fCollectionType.getMethodProxy("toArray", "[Ljava.lang.Object;"); //$NON-NLS-1$ //$NON-NLS-2$
	return fCollectionToArrayWithArray;
}

/**
 * Iterator accessors
 */

public IMethodProxy getIteratorHasNext() {
	if (fIteratorHasNext == null)
		fIteratorHasNext = fIteratorType.getMethodProxy("hasNext"); //$NON-NLS-1$
	return fIteratorHasNext;
}

public IMethodProxy getIteratorNext() {
	if (fIteratorNext == null)
		fIteratorNext = fIteratorType.getMethodProxy("next"); //$NON-NLS-1$
	return fIteratorNext;
}

public IMethodProxy getIteratorRemove() {
	if (fIteratorRemove == null)
		fIteratorRemove = fIteratorType.getMethodProxy("remove"); //$NON-NLS-1$
	return fIteratorRemove;
}

/**
 * Listaccessors
 */

public IMethodProxy getListAddWithInt() {
	if (fListAddWithInt == null)
		fListAddWithInt = fListType.getMethodProxy("add", new String[] {"int", "java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fListAddWithInt;
}

public IMethodProxy getListAddAllWithInt() {
	if (fListAddAllWithInt == null)
		fListAddAllWithInt = fListType.getMethodProxy("addAll", new String[] {"int", "java.util.Collection"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fListAddAllWithInt;
}

public IMethodProxy getListGet() {
	if (fListGet == null)
		fListGet = fListType.getMethodProxy("get", "int"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListGet;
}

public IMethodProxy getListIndexOf() {
	if (fListIndexOf == null)
		fListIndexOf = fListType.getMethodProxy("indexOf", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListIndexOf;
}

public IMethodProxy getListLastIndexOf() {
	if (fListLastIndexOf == null)
		fListLastIndexOf = fListType.getMethodProxy("lastIndexOf", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListLastIndexOf;
}

public IMethodProxy getListListIterator() {
	if (fListListIterator == null)
		fListListIterator = fListType.getMethodProxy("listIterator"); //$NON-NLS-1$
	return fListListIterator;
}

public IMethodProxy getListListIteratorWithInt() {
	if (fListListIteratorWithInt == null)
		fListListIteratorWithInt = fListType.getMethodProxy("listIterator", "int"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListListIteratorWithInt;
}

public IMethodProxy getListRemoveInt() {
	if (fListRemoveInt == null)
		fListRemoveInt = fListType.getMethodProxy("remove", "int"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListRemoveInt;
}

public IMethodProxy getListSet() {
	if (fListSet == null)
		fListSet = fListType.getMethodProxy("set", new String[] {"int", "java.lang.Object"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fListSet;
}

public IMethodProxy getListSubList() {
	if (fListSubList == null)
		fListSubList = fListType.getMethodProxy("subList", new String[] {"int", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	return fListSubList;
}

/**
 * ListIterator accessors
 */
 
public IMethodProxy getListIteratorAdd() {
	if (fListIteratorAdd == null)
		fListIteratorAdd = fListIteratorType.getMethodProxy("add", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListIteratorAdd;
}

public IMethodProxy getListIteratorHasPrevious() {
	if (fListIteratorHasPrevious == null)
		fListIteratorHasPrevious = fListIteratorType.getMethodProxy("hasPrevious"); //$NON-NLS-1$
	return fListIteratorHasPrevious;
}

public IMethodProxy getListIteratorNextIndex() {
	if (fListIteratorNextIndex == null)
		fListIteratorNextIndex = fListIteratorType.getMethodProxy("nextIndex"); //$NON-NLS-1$
	return fListIteratorNextIndex;
}

public IMethodProxy getListIteratorPrevious() {
	if (fListIteratorPrevious == null)
		fListIteratorPrevious = fListIteratorType.getMethodProxy("previous"); //$NON-NLS-1$
	return fListIteratorPrevious;
}

public IMethodProxy getListIteratorPreviousIndex() {
	if (fListIteratorPreviousIndex == null)
		fListIteratorPreviousIndex = fListIteratorType.getMethodProxy("previousIndex"); //$NON-NLS-1$
	return fListIteratorPreviousIndex;
}

public IMethodProxy getListIteratorSet() {
	if (fListIteratorSet == null)
		fListIteratorSet = fListIteratorType.getMethodProxy("set", "java.lang.Object"); //$NON-NLS-1$ //$NON-NLS-2$
	return fListIteratorSet;
}

public IMethodProxy getListIteratorRemove() {
	if (fListIteratorRemove == null)
		fListIteratorRemove = fListIteratorType.getMethodProxy("remove"); //$NON-NLS-1$
	return fListIteratorRemove;
}

protected IMethodProxy getSystemArraycopy() {
	if (fSystemArraycopy == null)
		fSystemArraycopy = fSystemType.getMethodProxy("arraycopy", new String[] {"java.lang.Object", "int", "java.lang.Object", "int", "int"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
	return fSystemArraycopy;
}

/**
 * Enumeration accessors
 */
public IMethodProxy getEnumerationHasMoreElements() {
	if (fEnumerationHasMoreElements == null)
		fEnumerationHasMoreElements = fEnumerationType.getMethodProxy("hasMoreElements"); //$NON-NLS-1$
	return fEnumerationHasMoreElements;
}

public IMethodProxy getEnumerationNextElement() {
	if (fEnumerationNextElement == null)
		fEnumerationNextElement = fEnumerationType.getMethodProxy("nextElement"); //$NON-NLS-1$
	return fEnumerationNextElement;
}

/**
 * Utility function to copy an array.
 */
public void arraycopy(IBeanProxy src, int srcPosition, IBeanProxy dest, int destPosition, int length) throws ThrowableProxy {
	IStandardBeanProxyFactory factory = src.getProxyFactoryRegistry().getBeanProxyFactory();
	IBeanProxy[] args = new IBeanProxy[] {
		src,
		factory.createBeanProxyWith(srcPosition),
		dest,
		factory.createBeanProxyWith(destPosition),
		factory.createBeanProxyWith(length)		
	};
	
	getSystemArraycopy().invoke(null, args);
}
}
