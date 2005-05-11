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
 *  $RCSfile: InitializationStringEvaluationException.java,v $
 *  $Revision: 1.3 $  $Date: 2005/05/11 19:01:12 $ 
 */



public class InitializationStringEvaluationException extends Exception {
	
	
public InitializationStringEvaluationException(Throwable exc){
	super(exc);
}
public Throwable getOriginalException(){
	return getCause();
}
}