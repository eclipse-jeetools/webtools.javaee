package org.eclipse.jem.internal.proxy.initParser;
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
 *  $RCSfile: Cast.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:55:20 $ 
 */


import java.util.HashMap;

public class Cast extends Expression {
	protected static final int BYTE_TYPE = 0;
	protected static final int SHORT_TYPE = 1;
	protected static final int INT_TYPE = 2;
	protected static final int LONG_TYPE = 3;
	protected static final int FLOAT_TYPE = 4;
	protected static final int DOUBLE_TYPE = 5;
	
	protected static final HashMap sTypeLookup;
	static {
		sTypeLookup = new HashMap(6);
		sTypeLookup.put(Byte.TYPE, new Integer(BYTE_TYPE));
		sTypeLookup.put(Short.TYPE, new Integer(SHORT_TYPE));
		sTypeLookup.put(Integer.TYPE, new Integer(INT_TYPE));
		sTypeLookup.put(Long.TYPE, new Integer(LONG_TYPE));
		sTypeLookup.put(Float.TYPE, new Integer(FLOAT_TYPE));
		sTypeLookup.put(Double.TYPE, new Integer(DOUBLE_TYPE));
	}
		
	protected Static fCastType;
	protected boolean fIsClosed = false;
	
	public Cast(Static castType, ClassLoader aClassLoader) {
		fCastType = castType;
		fClassLoader = aClassLoader;
	}
	
	public boolean isPrimitive() throws Exception {
		return getTypeClass().isPrimitive();
	}
	
	/**
	 * The type of a Cast expression is the type of the cast type.
	 */
	public Class getTypeClass() throws Exception {
		return fCastType.getTypeClass();
	}
	
	
	public boolean isComplete() {
		return currentExpression != null && currentExpression.isComplete();
	}
	
	/**
	 * If the type is primitive, then we need to
	 * convert the value, otherwise just pass
	 * the value on.
	 *
	 * This is important for primitives because if your
	 * entire initialization string was simply "(short) 3"
	 * then a Short value must be returned so that the
	 * correct kind of primitive proxy is created.
	 */
	public Object evaluate() throws Exception {
		if (getTypeClass() == currentExpression.getTypeClass())
			return currentExpression.evaluate();	// They're the same, so just return it.
		if (getTypeClass().isPrimitive()) {
			// Can only cast a primitive to a primitive, except null can't be cast to a primitive.
			if (!currentExpression.getTypeClass().isPrimitive() || currentExpression.getTypeClass() == Void.TYPE)
				throw new EvaluationException(new ClassCastException(currentExpression.getTypeClass().getName()));			
			// boolean only allows boolean cast.
			if (fCastType.getTypeClass() == Boolean.TYPE || currentExpression.getTypeClass() == Boolean.TYPE)
				throw new EvaluationException(new ClassCastException(currentExpression.getTypeClass().getName()));
			if (fCastType.getTypeClass() == Character.TYPE) {
				// So we have either a Character or a number as the value. Cast that.				
				return new Character((char) ((Number) currentExpression.evaluate()).intValue());
			} else {
				Number value = null;
				// See if the value is character or a number.
				if (currentExpression.getTypeClass() == Character.TYPE)
					value = new Integer(((Character) currentExpression.evaluate()).charValue());
				else
					value = (Number) currentExpression.evaluate();
				switch (((Integer) sTypeLookup.get(fCastType.getTypeClass())).intValue()) {
					case BYTE_TYPE:
						return new Byte(value.byteValue());
					case SHORT_TYPE:
						return new Short(value.shortValue());
					case INT_TYPE:
						return new Integer(value.intValue());
					case LONG_TYPE:
						return new Long(value.longValue());
					case FLOAT_TYPE:
						return new Float(value.floatValue());
					case DOUBLE_TYPE:
						return new Double(value.doubleValue());
					default:
						return null;	// Shouldn't occur. Just satisifies compiler.
				}
			}
			
		} else
			return currentExpression.evaluate();
	}
	/**
	 * A cast expression always pushes onto the cast value
	 */
	public Expression push(char[] token , char tokenDelimiter) {
		// If we don't have a class yet then we are within the statement to try and deterine the type being cast to
		if(fCastType.getPrimitiveTypeClass() == null && !fIsClosed){
			fCastType.push(token,tokenDelimiter);
			// If the type was completed and we have a ) then close us
			if(fCastType.getPrimitiveTypeClass() != null && tokenDelimiter == DelimiterCloseParen){
				fIsClosed = true;
			}
			return this;			
		}
		
		if (!fIsClosed) {
			// The cast is not closed, but we have a static class.  This class must therefore keep processing the tokens as it could be a method or message send
			Expression result =  fCastType.push(token,tokenDelimiter);
			result.parenthesisLevel++;
			return result; 
		}
		
		// If we have no expression push onto the cast value		
		if(currentExpression == null){
			currentExpression = new Statement(fClassLoader);
			currentExpression = currentExpression.push(token,tokenDelimiter);
			return this;
		} 
		Expression result = currentExpression.push(token, tokenDelimiter);
		// If the result if a push then push the stack
		if ( result.isComplete() ) {
			popExpressionStack();
		} else if ( result != currentExpression ) {
			pushExpressionStack(result);
		}		
		return this;
	}
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Cast("); //$NON-NLS-1$
		if (fCastType != null){
			buffer.append(fCastType.toString());
		} else {
			buffer.append("???"); //$NON-NLS-1$
		}
		buffer.append(") "); //$NON-NLS-1$
		if ( currentExpression != null){
			buffer.append(currentExpression.toString());
		} else {
			buffer.append("???");			 //$NON-NLS-1$
		}
		return buffer.toString();
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.initParser.Expression#getTypeClassName()
	 */
	protected String getTypeClassName() {
		return fCastType.getTypeClassName();
	}

}