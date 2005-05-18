/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDEArrayBeanProxy.java,v $
 *  $Revision: 1.8 $  $Date: 2005/05/18 23:11:26 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import java.lang.reflect.*;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
/**
 * IDE VM version of the Array proxy
 */

public final class IDEArrayBeanProxy extends IDEObjectBeanProxy implements IArrayBeanProxy {

	private IDEStandardBeanTypeProxyFactory fBeanTypeProxyFactory;
	
IDEArrayBeanProxy(IDEProxyFactoryRegistry aRegistry, Object array,IBeanTypeProxy aType) {
	super(aRegistry, array ,aType);
	fBeanTypeProxyFactory = (IDEStandardBeanTypeProxyFactory)aRegistry.getBeanTypeProxyFactory();
}
/**
 * Get the object at the specified index.
 */
public IBeanProxy get(int index) throws ThrowableProxy {
	// If the component type is primitive, we need to use the correct getter so that a valid value is returned,
	// if we just used the standard get, if the type was "int" the value returned would be an java.lang.Integer.
	IBeanTypeProxy compType = ((IArrayBeanTypeProxy) getTypeProxy()).getComponentType();
	if (!compType.isPrimitive()) {
		// Use standard getter.
		Object result = Array.get(fBean,index);
		if ( result == null ) return null;
		return ((IDEBeanTypeProxy)fBeanTypeProxyFactory.getBeanTypeProxy(result.getClass())).newBeanProxy(result);
	} else {
		// Use the correct primitive getter.
		int id = ((IDEPrimitiveBeanTypeProxy)compType).getPrimitiveType();
		switch (id) {
			case IDEPrimitiveBeanTypeProxy.BYTE:
				return fBeanTypeProxyFactory.byteType.createByteBeanProxy(Array.getByte(fBean,index));
			case IDEPrimitiveBeanTypeProxy.BOOLEAN:
				return fBeanTypeProxyFactory.booleanType.createBooleanBeanProxy(Array.getBoolean(fBean,index));
			case IDEPrimitiveBeanTypeProxy.CHAR:
				return fBeanTypeProxyFactory.charType.createCharBeanProxy(Array.getChar(fBean,index));
			case IDEPrimitiveBeanTypeProxy.DOUBLE:
				return fBeanTypeProxyFactory.doubleType.createDoubleBeanProxy(Array.getDouble(fBean,index));
			case IDEPrimitiveBeanTypeProxy.FLOAT:
				return fBeanTypeProxyFactory.floatType.createFloatBeanProxy(Array.getFloat(fBean,index));
			case IDEPrimitiveBeanTypeProxy.INTEGER:
				return fBeanTypeProxyFactory.intType.createIntegerBeanProxy(Array.getInt(fBean,index));
			case IDEPrimitiveBeanTypeProxy.LONG:
				return fBeanTypeProxyFactory.longType.createLongBeanProxy(Array.getLong(fBean,index));
			case IDEPrimitiveBeanTypeProxy.SHORT:
				return fBeanTypeProxyFactory.shortType.createShortBeanProxy(Array.getShort(fBean,index));
			default :
				return null; // Shouldn't get here, said it was primitive, but not one we understand				
		}
	}		
}
/**
 * Get the object at the specified multi-dimensional index.
 * The array must be at least the number of dimensions specified,
 * and each index along the way must exist.
 * The number of dimensions can't be greater than the number
 * of dimensions of the real object.
 */
public IBeanProxy get(int[] indexes) throws ThrowableProxy {
	if (indexes.length == 1)
		return get(indexes[0]);
	int dims = ((IArrayBeanTypeProxy) getTypeProxy()).getDimensions();
	if (dims < indexes.length)
		throw new IllegalArgumentException(); // Too many dimensions
	// Get up to the next to last dimension so that we can do the appropriate final get depending upon primitive or not.
	Object array = fBean;
	int lastArrayDim = indexes.length - 1;
	for (int i = 0; i < lastArrayDim; i++)
		array = Array.get(array, indexes[i]);

	int index = indexes[lastArrayDim];	// Final array dim to retrieve
	
	if (dims == indexes.length) {
		IBeanTypeProxy finalCompType = ((IArrayBeanTypeProxy) getTypeProxy()).getFinalComponentType();
		if (finalCompType.isPrimitive()) {
			// If the component type at the specified dimension is primitive, we need to use the correct getter so that a valid value is returned,
			// if we just used the standard get, if the type was "int" the value returned would be an java.lang.Integer.
			int id = ((IDEPrimitiveBeanTypeProxy) finalCompType).getPrimitiveType();
			switch (id) {
				case IDEPrimitiveBeanTypeProxy.BYTE :
					return fBeanTypeProxyFactory.byteType.createByteBeanProxy(Array.getByte(array, index));
				case IDEPrimitiveBeanTypeProxy.BOOLEAN :
					return fBeanTypeProxyFactory.booleanType.createBooleanBeanProxy(Array.getBoolean(array, index));
				case IDEPrimitiveBeanTypeProxy.CHAR :
					return fBeanTypeProxyFactory.charType.createCharBeanProxy(Array.getChar(array, index));
				case IDEPrimitiveBeanTypeProxy.DOUBLE :
					return fBeanTypeProxyFactory.doubleType.createDoubleBeanProxy(Array.getDouble(array, index));
				case IDEPrimitiveBeanTypeProxy.FLOAT :
					return fBeanTypeProxyFactory.floatType.createFloatBeanProxy(Array.getFloat(array, index));
				case IDEPrimitiveBeanTypeProxy.INTEGER :
					return fBeanTypeProxyFactory.intType.createIntegerBeanProxy(Array.getInt(array, index));
				case IDEPrimitiveBeanTypeProxy.LONG :
					return fBeanTypeProxyFactory.longType.createLongBeanProxy(Array.getLong(array, index));
				case IDEPrimitiveBeanTypeProxy.SHORT :
					return fBeanTypeProxyFactory.shortType.createShortBeanProxy(Array.getShort(array, index));
				default :
					return null; // Shouldn't get here, said it was primitive, but not one we understand
			}
		}
	}

	// Won't be returning a primitive, so use standard accessor
	Object result = Array.get(array, index);
	if (result == null)
		return null;
	return ((IDEBeanTypeProxy) fBeanTypeProxyFactory.getBeanTypeProxy(result.getClass())).newBeanProxy(
		result);
}

/**
 * Set the object at the specified index.
 */
public void set(IBeanProxy valueProxy, int index) throws ThrowableProxy {
	// If the component type is primitive, we need to use the correct getter so that a valid value is returned,
	// if we just used the standard get, if the type was "int" the value returned would be an java.lang.Integer.
	Object value = ((IIDEBeanProxy)valueProxy).getBean();
	Class valueClass = value.getClass();
	if (!valueClass.isPrimitive()) {
		// Use standard getter.
		Array.set(fBean,index,value);
	} else if (valueClass == Byte.TYPE ) {
		Array.setByte(fBean,index,((Byte)value).byteValue());
	} else if (valueClass == Boolean.TYPE ){
		Array.setBoolean(fBean,index,((Boolean)value).booleanValue());			
	} else if (valueClass == Character.TYPE) {
		Array.setChar(fBean,index,((Character)value).charValue());
	} else if (valueClass == Double.TYPE) {
		Array.setDouble(fBean,index,((Double)value).doubleValue());
	} else if (valueClass == Float.TYPE) {
		Array.setFloat(fBean,index,((Float)value).floatValue());		
	} else if (valueClass == Integer.TYPE) {
		Array.setInt(fBean,index,((Integer)value).intValue());		
	} else if (valueClass == Long.TYPE ) {
		Array.setLong(fBean,index,((Long)value).longValue());		
	} else if (valueClass == Short.TYPE ) {
		Array.setShort(fBean,index,((Short)value).shortValue());		
	}
}
/**
 * Set the object at the specified multi-dimensional index.
 * The array must be at least the number of dimensions specified,
 * and each index along the way must exist.
 * The number of dimensions can't be greater than the number
 * of dimensions of the real object.
 */
public void set(IBeanProxy valueProxy, int[] indexes) throws ThrowableProxy {

	Object subArray = fBean;
	int upTo = indexes.length - 1;
	for (int i = 0; i < upTo; i++)
		subArray = Array.get(subArray, indexes[i]);
	int index = indexes[upTo];

	// Now set the appropriate value in
	// If the component type is primitive, we need to use the correct getter so that a valid value is returned,
	// if we just used the standard get, if the type was "int" the value returned would be an java.lang.Integer.
	Object value = ((IIDEBeanProxy) valueProxy).getBean();
	Class valueClass = value.getClass();
	if (!valueClass.isPrimitive()) {
		// Use standard getter.
		Array.set(subArray, index, value);
	} else if (valueClass == Byte.TYPE) {
		Array.setByte(subArray, index, ((Byte) value).byteValue());
	} else if (valueClass == Boolean.TYPE) {
		Array.setBoolean(subArray, index, ((Boolean) value).booleanValue());
	} else if (valueClass == Character.TYPE) {
		Array.setChar(subArray, index, ((Character) value).charValue());
	} else if (valueClass == Double.TYPE) {
		Array.setDouble(subArray, index, ((Double) value).doubleValue());
	} else if (valueClass == Float.TYPE) {
		Array.setFloat(subArray, index, ((Float) value).floatValue());
	} else if (valueClass == Integer.TYPE) {
		Array.setInt(subArray, index, ((Integer) value).intValue());
	} else if (valueClass == Long.TYPE) {
		Array.setLong(subArray, index, ((Long) value).longValue());
	} else if (valueClass == Short.TYPE) {
		Array.setShort(subArray, index, ((Short) value).shortValue());
	}

}
/**
 * Get the length of the first dimension of this array.
 * If there are multi-dimensions, you must get the appropriate
 * dimension from the get method to see the size of that dimension.
 *
 * e.g.
 *    int [3] returns 3
 *    int [3][2] returns 3
 *
 *    ((IArrayBeanProxy) get(1)).getLength() returns 2
 *    Since arrays do not have to be homogenous, there could
 *    be a different length array for each of the arrays 
 *    returned from the first dimension, the returned length
 *    from get(2) and get(3) could result in a different value
 *    from get(1).
 */
public int getLength() {
	return Array.getLength(fBean);
}	
	/**
	 * @see org.eclipse.jem.internal.proxy.core.IArrayBeanProxy#getCatchThrowableException(int)
	 */
	public IBeanProxy getCatchThrowableException(int index) {
		try {
			return get(index);
		} catch ( ThrowableProxy exc ) {
			ProxyPlugin.getPlugin().getLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getBundle().getSymbolicName(), 0, "", exc));			 //$NON-NLS-1$
			return null;
		}
	}

	
	public IBeanProxy[] getSnapshot() throws ThrowableProxy {
		// If the component type is primitive, we need to use the correct getter so that a valid value is returned,
		// if we just used the standard get, if the type was "int" the value returned would be an java.lang.Integer.
		IBeanTypeProxy compType = ((IArrayBeanTypeProxy) getTypeProxy()).getComponentType();
		int id = compType.isPrimitive() ? ((IDEPrimitiveBeanTypeProxy)compType).getPrimitiveType() : -1;
		IBeanProxy[] result = new IBeanProxy[Array.getLength(fBean)];
		for (int i = 0; i < result.length; i++) {
			if (id == -1) {
				// Use standard getter.
				Object val = Array.get(fBean,i);
				if ( val != null )
					result[i] = ((IDEBeanTypeProxy)fBeanTypeProxyFactory.getBeanTypeProxy(val.getClass())).newBeanProxy(val);
			} else {
				// Use the correct primitive getter.
				switch (id) {
					case IDEPrimitiveBeanTypeProxy.BYTE:
						result[i] = fBeanTypeProxyFactory.byteType.createByteBeanProxy(Array.getByte(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.BOOLEAN:
						result[i] = fBeanTypeProxyFactory.booleanType.createBooleanBeanProxy(Array.getBoolean(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.CHAR:
						result[i] = fBeanTypeProxyFactory.charType.createCharBeanProxy(Array.getChar(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.DOUBLE:
						result[i] = fBeanTypeProxyFactory.doubleType.createDoubleBeanProxy(Array.getDouble(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.FLOAT:
						result[i] = fBeanTypeProxyFactory.floatType.createFloatBeanProxy(Array.getFloat(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.INTEGER:
						result[i] = fBeanTypeProxyFactory.intType.createIntegerBeanProxy(Array.getInt(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.LONG:
						result[i] = fBeanTypeProxyFactory.longType.createLongBeanProxy(Array.getLong(fBean,i));
						break;
					case IDEPrimitiveBeanTypeProxy.SHORT:
						result[i] = fBeanTypeProxyFactory.shortType.createShortBeanProxy(Array.getShort(fBean,i));
						break;
					default :
						break; // Shouldn't get here, said it was primitive, but not one we understand				
				}
			}		
		}
		return result;
	}

}
