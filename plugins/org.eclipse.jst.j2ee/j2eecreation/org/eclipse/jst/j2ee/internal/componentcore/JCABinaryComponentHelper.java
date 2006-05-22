/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.componentcore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.RarImportStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JCABinaryComponentHelper extends EnterpriseBinaryComponentHelper {

	public static boolean handlesComponent(IVirtualComponent component) {
		JCABinaryComponentHelper helper = null;
		try {
			helper = new JCABinaryComponentHelper(component);
			return helper.isArchiveValid();
		} catch (Exception e) {
			return false;
		} finally {
			helper.dispose();
		}
	}

	public JCABinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	protected Archive openArchive(String archiveURI) throws OpenFailureException {
		return CommonarchiveFactory.eINSTANCE.openRARFile(getArchiveOptions(), archiveURI);
	}

	public EObject getPrimaryRootObject() {
		return ((RARFile) getArchive()).getDeploymentDescriptor();
	}

	protected ArchiveTypeDiscriminator getDiscriminator() {
		return RarImportStrategyImpl.getDiscriminator();
	}

}
