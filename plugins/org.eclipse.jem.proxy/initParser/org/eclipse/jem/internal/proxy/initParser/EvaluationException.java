package org.eclipse.jem.internal.proxy.initParser;
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
 *  $RCSfile: EvaluationException.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.lang.reflect.InvocationTargetException;

/**
 * This is an InitializationStringEvaluationException, but
 * in addition, the exception that it wrappers is what should be
 * returned. This means it was a valid exception, parsing had
 * gone fine, but it was an evaluation that occured. This is
 * distinguished from the straight InitializationStringEvaluationException,
 * which means some error during parsing and evaluation.
 * 
 * Note: InvocationTargetExceptions will be unwrappered to the true exception.
 */
public class EvaluationException extends InitializationStringEvaluationException {

	/**
	 * Constructor for ReturnableException.
	 * @param exc
	 */
	public EvaluationException(Throwable exc) {
		super(exc instanceof InvocationTargetException ? ((InvocationTargetException) exc).getTargetException() : exc);
	}

}
