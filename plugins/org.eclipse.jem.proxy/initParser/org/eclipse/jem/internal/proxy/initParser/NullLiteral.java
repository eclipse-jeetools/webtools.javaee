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
package org.eclipse.jem.internal.proxy.initParser;

/*
 *  $RCSfile: NullLiteral.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:35:20 $ 
 */


public class NullLiteral extends Expression {
public NullLiteral(){
}
/**
 * evaluate method comment.
 */
public Object evaluate() {
	return null;
}
/**
 * evaluate method comment.
 */
public Class getTypeClass(){
	return MethodHelper.NULL_TYPE;
}

protected String getTypeClassName() {
	return MethodHelper.NULL_TYPE.getName();
}
/**
 * true or false cannot consume any kind of expression so we must return null
 */
public Expression push(char[] token , char delimiter){
	return null;
}

public boolean isComplete(){
	return true;
}

/**
 * Null is not primitive
 */
public boolean isPrimitive() {
	return false;
}
}


