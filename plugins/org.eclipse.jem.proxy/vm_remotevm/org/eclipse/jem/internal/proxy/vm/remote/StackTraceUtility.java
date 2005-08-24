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
package org.eclipse.jem.internal.proxy.vm.remote;
/*
 *  $RCSfile: StackTraceUtility.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:08 $ 
 */


import java.io.*;
/**
 * A utility to get the stack trace from an exception
 * back to the client.
 */
public class StackTraceUtility {
	
	public static String printStackTrace(Throwable t) {
		StringWriter writer = new StringWriter();
		t.printStackTrace(new PrintWriter(writer));
		return writer.toString();
	}
		

}
