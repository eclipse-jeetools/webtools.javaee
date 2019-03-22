/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.common;
 

/**
 * Ambiguous Method Exception. I.E. There is more than one that could be used.
 */
public class AmbiguousMethodException extends Exception {
	/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0
	 */
	private static final long serialVersionUID = -7084137612344373381L;
	public AmbiguousMethodException() {
	}
	public AmbiguousMethodException(String msg) {
		super(msg);
	}
}