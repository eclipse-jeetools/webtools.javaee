/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;


/**
 * Insert the type's description here. Creation date: (08/18/00 4:56:13 PM)
 * 
 * @author: Administrator
 */
public class CommandExecutionFailure extends RuntimeException {
	private Throwable targetFailure;

	/**
	 * J2EERelatedCommandExecutionFailure constructor comment.
	 */
	public CommandExecutionFailure() {
		super();
	}

	/**
	 * J2EERelatedCommandExecutionFailure constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public CommandExecutionFailure(String s) {
		super(s);
	}

	/**
	 * J2EERelatedCommandExecutionFailure constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public CommandExecutionFailure(String aDescription, Throwable e) {
		super(aDescription + " :: " + e.toString());//$NON-NLS-1$
		targetFailure = e;
	}

	/**
	 * J2EERelatedCommandExecutionFailure constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public CommandExecutionFailure(Throwable e) {
		super(e.toString());
		targetFailure = e;
	}

	/**
	 * Insert the method's description here. Creation date: (4/17/2001 3:51:48 PM)
	 * 
	 * @return java.lang.Throwable
	 */
	public java.lang.Throwable getTargetFailure() {
		return targetFailure;
	}

	public void printStackTrace() {
		synchronized (System.err) {
			if (getTargetFailure() != null) {
				com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(getTargetFailure());
			} else
				super.printStackTrace();
		}
	}

	/**
	 * Prints this <code>Throwable</code> and its backtrace to the specified print stream.
	 */
	public void printStackTrace(java.io.PrintStream s) {
		synchronized (s) {
			if (getTargetFailure() != null) {
				s.println(this);
				getTargetFailure().printStackTrace(s);
			} else
				super.printStackTrace(s);
		}
	}

	/**
	 * Prints this <code>Throwable</code> and its backtrace to the specified print stream.
	 */
	public void printStackTrace(java.io.PrintWriter s) {
		synchronized (s) {
			if (getTargetFailure() != null) {
				s.println(this);
				getTargetFailure().printStackTrace(s);
			} else
				super.printStackTrace(s);
		}
	}
}