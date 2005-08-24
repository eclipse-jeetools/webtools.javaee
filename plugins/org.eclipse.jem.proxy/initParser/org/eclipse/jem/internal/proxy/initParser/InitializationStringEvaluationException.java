/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.initParser;
/*
 *  $RCSfile: InitializationStringEvaluationException.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:07 $ 
 */



public class InitializationStringEvaluationException extends Exception {
	
	
/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0
	 */
	private static final long serialVersionUID = -8633390926210276727L;
public InitializationStringEvaluationException(Throwable exc){
	super(exc);
}
public Throwable getOriginalException(){
	return getCause();
}
}
