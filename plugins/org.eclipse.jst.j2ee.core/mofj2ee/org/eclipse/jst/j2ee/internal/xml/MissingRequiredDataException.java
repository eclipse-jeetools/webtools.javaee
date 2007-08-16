/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.xml;



public class MissingRequiredDataException extends RuntimeException {

private static final long serialVersionUID = -301039854542561304L;
/**
 * MissingRequiredDataException constructor comment.
 */
public MissingRequiredDataException() {
	super();
}
/**
 * MissingRequiredDataException constructor comment.
 * @param s java.lang.String
 */
public MissingRequiredDataException(String s) {
	super(s);
}
}


