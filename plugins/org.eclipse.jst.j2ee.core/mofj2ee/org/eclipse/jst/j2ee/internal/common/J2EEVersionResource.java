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
/*
 * Common Interface used to check version of J2EE model resources
 */
package org.eclipse.jst.j2ee.internal.common;

/**
 * @author cbridgha
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface J2EEVersionResource {
	public int getJ2EEVersionID();
	public int getModuleVersionID();
	public int getVersionID();
}
