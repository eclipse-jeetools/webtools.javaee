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
package org.eclipse.jst.j2ee.commonarchivecore.impl;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AltResourceRegister {
	protected static Set registeredURIs = new HashSet();

	protected AltResourceRegister() {
	}


	/**
	 * Registers an Archive relative path for the resource which can be copied up as an alt in an
	 * EAR file. The uri should be in cananonical form and use the forward slash, eg,
	 * "META-INF/vendor-extensions.xmi"
	 */
	public static void registerURI(String uri) {
		registeredURIs.add(uri);
	}

	public static void deRegisterURI(String uri) {
		registeredURIs.remove(uri);
	}

	public static boolean isRegistered(String uri) {
		return registeredURIs.contains(uri);
	}

	public static Set getRegisteredURIs() {
		return Collections.unmodifiableSet(registeredURIs);
	}
}
