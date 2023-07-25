/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal;


import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.jca.Connector;

/**
 * @generated
 */
public interface ConnectorModuleRef extends ModuleRef {
	Connector getConnector() throws ArchiveWrappedException;

} //ConnectorModuleRef



