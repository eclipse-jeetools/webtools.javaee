/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;



/**
 * Base exception class for non-runtime exceptions, where a caught exception
 * causes this exception to be thrown
 */
public abstract class WrappedException extends Exception implements IWrappedException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2477488574387544467L;
	/** The exception which necessitated this exception */
	protected Exception nestedException;

public WrappedException() {
	super();
}
public WrappedException(Exception e) {
	super();
	setNestedException(e);
}
public WrappedException(String s) {
	super(s);
}
public WrappedException(String s, Exception e) {
	super(s);
	setNestedException(e);
}
/**
 * Return the messages from this and all nested exceptions, in order from outermost to innermost
 */
@Override
public java.lang.String[] getAllMessages() {
	return ExceptionHelper.getAllMessages(this);
}
/**
 * Return the messages from this and all nested exceptions, in order from outermost to innermost,
 * concatenated as one
 */
@Override
public java.lang.String getConcatenatedMessages() {
	return ExceptionHelper.getConcatenatedMessages(this);
}
@Override
public java.lang.Exception getNestedException() {
	return nestedException;
}
/**
 * Added to provide compatibility for the Java 1.4.2 addition of
 * <code>Throwable.getCause()</code>.
 */
@Override
public synchronized java.lang.Throwable getCause() {
	return getNestedException();
}
/**
 * Print out a stack trace to the system err.
 */
@Override
public void printStackTrace() {
	printStackTrace(System.err);
}
/**
* Prints the exception to System.err.
* If we have a nested exception, print its stack.
*/
@Override
public void printStackTrace(java.io.PrintStream s) {
	if (nestedException != null) {
		s.println(this);
		s.println(MOFJ2EEResourceHandler.Stack_trace_of_nested_exce); // = "Stack trace of nested exception:"
		nestedException.printStackTrace(s);
	} else {
		super.printStackTrace(s);
	}
}
/**
* Prints the exception to System.err.
* If we have a nested exception, print its stack.
*/
@Override
public void printStackTrace(java.io.PrintWriter s) {
	if (nestedException != null) {
		s.println(this);
		s.println(MOFJ2EEResourceHandler.Stack_trace_of_nested_exce); // = "Stack trace of nested exception:"
		nestedException.printStackTrace(s);
	} else {
		super.printStackTrace(s);
	}
}
public void setNestedException(java.lang.Exception newNestedException) {
	nestedException = newNestedException;
    initCause(newNestedException);
}
}


