package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: REMFieldProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;

final class REMFieldProxy extends REMAbstractBeanProxy implements IFieldProxy {
	private IBeanTypeProxy fFieldType;

	REMFieldProxy(REMProxyFactoryRegistry aRegistry, Integer anID){
		super(aRegistry, anID);
	}


	/**
	 * Return the type of the field.
	 */
	public IBeanTypeProxy getFieldType() {
		if (fFieldType == null)
			fFieldType = (IBeanTypeProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetType().invokeCatchThrowableExceptions(this);
			
		return fFieldType;
	}
	
	
	/**
	 * Release the proxy, i.e. mark it invalid.
	 */
	public void release() {
		fFieldType = null;
		super.release();
	}
	
	/**
	 * Get the value of this field from the subject
	 */
	public IBeanProxy get(IBeanProxy subject) throws ThrowableProxy {
		// Format of getter parms is: (Object (subject)).
		// If the component type is primitive, we need to use the correct getter so that a valid value is returned,
		// if we just used the standard get, if the type was "int" the value returned would be an java.lang.Integer.
		IBeanTypeProxy fieldType = getFieldType();
		if (!fieldType.isPrimitive()) {
			// Use standard getter.
			return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGet()).invoke(this, subject);
		} else {
			// Use the correct primitive getter.
			int id = ((IREMBeanProxy) fieldType).getID().intValue();
			switch (id) {
				case Commands.BYTE_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetByte()).invoke(this, subject);
				case Commands.BOOLEAN_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetBoolean()).invoke(this, subject);
				case Commands.CHARACTER_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetChar()).invoke(this, subject);
				case Commands.DOUBLE_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetDouble()).invoke(this, subject);
				case Commands.FLOAT_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetFloat()).invoke(this, subject);
				case Commands.INTEGER_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetInt()).invoke(this, subject);
				case Commands.LONG_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetLong()).invoke(this, subject);
				case Commands.SHORT_TYPE:
					return ((IREMMethodProxy) REMStandardBeanProxyConstants.getConstants(fFactory).getFieldGetShort()).invoke(this, subject);
			}
		}
		
		return null;
	}

	/**
	 * Set the value of this field in this subject to this value.
	 */
	public void set(IBeanProxy subject, IBeanProxy value) throws ThrowableProxy {
		REMStandardBeanProxyConstants.getConstants(fFactory).getFieldSet().invoke(this, new IBeanProxy[] {subject, value});	
	}
	
	/**
	 * The type proxy is constant proxy out of the method factory.
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMMethodProxyFactory) fFactory.getMethodProxyFactory()).fieldType;
	} 

}