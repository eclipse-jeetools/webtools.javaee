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
/*
 * Created on Jun 28, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.project;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.wst.common.internal.emf.utilities.ResourceDependencyRegister;

import com.ibm.wtp.emf.workbench.ProjectResourceSet;

/**
 * @author daberg
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class J2EEResourceDependencyRegister extends ResourceDependencyRegister implements J2EEWorkbenchURIConverterImpl.InputChangedListener {
	/**
	 * @param aResourceSet
	 */
	public J2EEResourceDependencyRegister(ProjectResourceSet aResourceSet) {
		super(aResourceSet);
		initializeConverter(aResourceSet);
	}

	private void initializeConverter(ResourceSet aResourceSet) {
		if (aResourceSet.getURIConverter() instanceof J2EEWorkbenchURIConverterImpl) {
			J2EEWorkbenchURIConverterImpl converter = (J2EEWorkbenchURIConverterImpl) aResourceSet.getURIConverter();
			converter.addListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EEWorkbenchURIConverterImpl.InputChangedListener#inputsChanged(org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EEWorkbenchURIConverterImpl)
	 */
	public void inputsChanged(J2EEWorkbenchURIConverterImpl aConverter) {
		localDependencies.clear();
		initializeLocalDependencies(aConverter);
	}


}