/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.provider;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IContainerManagedEntityBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IMessageDrivenBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.ISessionBean;

/**
 * @author naci
 *
 */
public interface IEJBGenerator {
	public void generateSession(ISessionBean bean,IProgressMonitor monitor) throws CoreException,InterruptedException;
	public void generateMessageDriven(IMessageDrivenBean bean,IProgressMonitor monitor) throws CoreException, InterruptedException;
	public void generateCMP(IContainerManagedEntityBean bean,IProgressMonitor monitor) throws CoreException, InterruptedException;
}
