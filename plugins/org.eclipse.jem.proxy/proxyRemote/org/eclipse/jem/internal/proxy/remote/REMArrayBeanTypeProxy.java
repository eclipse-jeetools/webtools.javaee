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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMArrayBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */



import org.eclipse.jem.internal.proxy.common.MapTypes;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Array Type Proxy for the IDE VM.
 *
 * The class is final because array's are special and shouldn't have
 * any subclasses since the factory would never call it.
 */

public final class REMArrayBeanTypeProxy extends REMAbstractBeanTypeProxy implements IArrayBeanTypeProxy {
	
	/**
	 * Create with a registry and a class. It is package so that only 
	 * the factory with this IDE package can call it.
	 */
	protected REMArrayBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy objectType) {
		super(aRegistry, anID, aClassname, objectType);
	}
	/**
	 * This is an array type!
	 */
	public boolean isArray() {
		return true;
	}
	/**
	 * Return the final component type.
	 */
	public IBeanTypeProxy getFinalComponentType() {
		// Component type is strip off all '[' and return class name (if we ever add classLoader support, this won't work).
		// However, if the type is a primitive we need to go through the change table).
		int typeStart = getTypeName().lastIndexOf('[')+1;
		if (getTypeName().charAt(typeStart) == 'L') {
			// It is a class.
			// Strip off up to the 'L', and the trailing ';'. That is the class name.
			return fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy(getTypeName().substring(typeStart+1, getTypeName().length()-1));
		} else {
			// It is a type. Need to map it.
			Class aType = (Class) MapTypes.MAP_SHORTSIG_TO_TYPE.get(getTypeName().substring(typeStart, typeStart+1));
			return aType != null ? fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy(aType.getName()) : null;
		}
	}
	
	/**
	 * Return the component type.
	 */
	public IBeanTypeProxy getComponentType() {
		// Component type is strip off first '[' and return class name (if we ever add classLoader support, this won't work).
		// However, if the type is a primitive we need to go through the change table).
		if (getTypeName().charAt(1) != '[') {
			// It is a one dimensional array, just send to getFinalComponentType to figure out the type.
			return getFinalComponentType();
		} else {
			// It is a multi-dimensional array. Just strip off one '[' and send that to the bean type factory.
			return fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy(getTypeName().substring(1));
		}
	}
	/**
	 * Return the number of dimensions.
	 */
	public int getDimensions() {
		return getTypeName().lastIndexOf('[')+1;
	}
	
	/**
	 * Create a new bean proxy with the specified object
	 * Use the constructor that allows the type proxy to be set as well as the bean
	 * because we are the type and by setting it now we might avoid it being lookup for in the
	 * factory the first time it is asked for.
	 */
	public IREMBeanProxy newBeanProxy(Integer anID) {
		return new REMArrayBeanProxy(fRegistry, anID, this);
	}
	
	
	/**
	 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
	 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
	 * This is because subclasses, unless explicitly overridden, should use the same kind
	 * of beantype proxy. This way if anything special is needed for a specific type, then
	 * that can be done.
	 *
	 * Arrays can't be subclassed.
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the null ctor proxy for this method with these arguments.
	 * For all of the array types, this is an invalid operation.	 
	 */		
	public IConstructorProxy getNullConstructorProxy() {	
		throw new UnsupportedOperationException();
	}
		
	/**
	 * Return a proxy to the constructor for the target VM being the same as the REM
	 * For all of the array types, this is an invalid operation.	 
	 */
	public IConstructorProxy getConstructorProxy(String[] argumentClassNames) {	
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Return a proxy to the constructor for the target VM being the same as the REM
	 * For all of the array types, this is an invalid operation.	 
	 */
	public IConstructorProxy getConstructorProxy(IBeanTypeProxy[] argumentTypes) {	
		throw new UnsupportedOperationException();
	}	
	
	/**
	 * Return a proxy to the field for the target VM being the same as the REM
	 * For all of the array types, this is an invalid operation.	 
	 */	
	public IFieldProxy getFieldProxy(String fieldName) {	
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the array types, this is an invalid operation.	 
	 */
	public IMethodProxy getMethodProxy(String methodName, IBeanTypeProxy[] argumentTypes) {
		throw new UnsupportedOperationException();
	}
		
	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the array types, this is an invalid operation.	 
	 */
	public IMethodProxy getMethodProxy(String methodName, String[] argumentClassNames) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the array types, this is an invalid operation.	 
	 */	
	public IMethodProxy getMethodProxy(String methodName, String argumentQualifiedTypeName) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the method proxy for this method with these arguments.
	 * For all of the array types, this is an invalid operation.	 
	 */		
	public IMethodProxy getMethodProxy(String methodName) {	
		throw new UnsupportedOperationException();
	}
	
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
	 *      int [0]
	 *     (int[][], null) or (int[][], new int[0])
	 *      int[0][]
	 *     This is because an array instance with no dimensions specified is not valid. 
	 *
	 *   - (int[][], new int[1] {3})
	 *      int[3][]
	 *
	 * It is package protected so that only the REM Factory can call it.
	 */
	REMArrayBeanProxy  createBeanProxyWith(int[] dimensions) throws ThrowableProxy {
		// If the dimensions is null or zero length, then create the
		// array as a 0 sized array one dimensional array.
		if (dimensions == null || dimensions.length == 0)
			return createBeanProxyWith(new int[] {0});
			
		// Remove from this type the number of dimensions specified
		// coming in, up to but no further than the final component.
		// From here we can then use the appropriate java.lang.reflect.Array
		// method to create the array.
		IBeanTypeProxy compType = null;	// The type of array to create (it may be different number of dimensions).
		int numDims = getDimensions();
		if (numDims <= dimensions.length)
			compType = getFinalComponentType();	// There are the same or more requested dimensions, so use final component type
		else {
			// There are fewer requested dimensions, so strip off that many
			String compClass = getTypeName().substring(dimensions.length);
			compType = fRegistry.getBeanTypeProxyFactory().getBeanTypeProxy(compClass);
		}
		
		if (dimensions.length == 1) {
			// Use the one dimension call
			Object[] parms = new Object[] {compType, new Integer(dimensions[0])};
			return (REMArrayBeanProxy) ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getArrayNewInstanceOneDimension()).invokeWithParms(null, parms);
		} else {
			// Use the multi dimension call
			Object[] parms = new Object[] {compType, dimensions};
			return (REMArrayBeanProxy) ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fRegistry).getArrayNewInstanceMultiDimension()).invokeWithParms(null, parms);
		}
	}
		
	/**
	 * @see IBeanTypeProxy#getFormalTypeName()
	 */
	public String getFormalTypeName() {
		String name = getTypeName();
		int dims = name.lastIndexOf('[') + 1;
		String type = getFinalComponentType().getTypeName();
		StringBuffer formalType = new StringBuffer(type.length()+("[]".length()*dims)); //$NON-NLS-1$
		formalType.append(type);
		for (int i=0; i<dims; i++)
			formalType.append("[]"); //$NON-NLS-1$
		return formalType.toString();
	}

}


