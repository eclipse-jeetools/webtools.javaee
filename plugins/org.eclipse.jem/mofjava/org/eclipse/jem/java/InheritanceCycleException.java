package org.eclipse.jem.java;
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
 *  $RCSfile: InheritanceCycleException.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/13 16:16:21 $ 
 */
import java.text.MessageFormat;

import org.eclipse.jem.internal.java.adapters.nls.ResourceHandler;

/**
 * @author DABERG
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class InheritanceCycleException extends Exception {
	private static String MSG_PATTERN = ResourceHandler.getString("Java_Inh_Cycle_ERROR_");

	/**
	 * 
	 */
	public InheritanceCycleException(JavaClass aSubclass, JavaClass aSuperclass) {
		this(MessageFormat.format(MSG_PATTERN, new String[]{aSubclass.getQualifiedName(), aSuperclass.getQualifiedName()}));
	}

	/**
	 * @param s
	 */
	public InheritanceCycleException(String s) {
		super(s);
	}

}
