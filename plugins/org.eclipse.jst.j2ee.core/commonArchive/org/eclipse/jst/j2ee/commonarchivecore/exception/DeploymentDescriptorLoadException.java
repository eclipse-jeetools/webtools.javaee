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
package org.eclipse.jst.j2ee.commonarchivecore.exception;



/**
 * Exception which can occur if an error/exception occurs while loading a deployment descriptor
 */
public class DeploymentDescriptorLoadException extends ArchiveRuntimeException {
	/**
	 * ResourceLoadException constructor comment.
	 */
	public DeploymentDescriptorLoadException() {
		super();
	}

	/**
	 * ResourceLoadException constructor comment.
	 * 
	 * @param e
	 *            java.lang.Exception
	 */
	public DeploymentDescriptorLoadException(Exception e) {
		super(e);
	}

	/**
	 * ResourceLoadException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public DeploymentDescriptorLoadException(String s) {
		super(s);
	}

	/**
	 * ResourceLoadException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 * @param e
	 *            java.lang.Exception
	 */
	public DeploymentDescriptorLoadException(String s, Exception e) {
		super(s, e);
	}
}
