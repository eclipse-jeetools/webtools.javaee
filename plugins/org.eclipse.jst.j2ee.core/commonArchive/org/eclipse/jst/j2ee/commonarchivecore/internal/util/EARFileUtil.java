/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;


public class EARFileUtil {

	/**
	 * Constructor for EARFileUtil.
	 */
	private EARFileUtil() {
		super();
	}

	/**
	 * Returns a list of all module files which are a spec level too high for the EAR file. If the
	 * EAR is 1.3, returns an empty list. Otherwise returns any J2EE 1.3 modules.
	 */
	public static List getIncompatible13ModuleFiles(EARFile earFile) {
		if (isJ2EE13(earFile))
			return Collections.EMPTY_LIST;

		List moduleFiles = earFile.getModuleFiles();
		List result = new ArrayList();
		for (int i = 0; i < moduleFiles.size(); i++) {
			ModuleFile aModuleFile = (ModuleFile) moduleFiles.get(i);
			if (isJ2EE13(aModuleFile))
				result.add(aModuleFile);
		}
		return result;
	}

	/**
	 * Returns a list of all module files which are a spec level too high for the EAR file. If the
	 * EAR is 1.4, returns an empty list. Otherwise returns any J2EE 1.4 modules.
	 */
	public static List getIncompatible14ModuleFiles(EARFile earFile) {
		if (isJ2EE14(earFile))
			return Collections.EMPTY_LIST;

		List moduleFiles = earFile.getModuleFiles();
		List result = new ArrayList();
		for (int i = 0; i < moduleFiles.size(); i++) {
			ModuleFile aModuleFile = (ModuleFile) moduleFiles.get(i);
			if (isJ2EE14(aModuleFile))
				result.add(aModuleFile);
		}
		return result;
	}

	public static boolean isJ2EE13(ModuleFile aModuleFile) {
		try {
			XMLResource xmlResource = (XMLResource) aModuleFile.getDeploymentDescriptorResource();
			return xmlResource.getJ2EEVersionID() == J2EEVersionConstants.J2EE_1_3_ID;
		} catch (FileNotFoundException ex) {
			return false;
		}
	}

	public static boolean isJ2EE14(ModuleFile aModuleFile) {
		try {
			XMLResource xmlResource = (XMLResource) aModuleFile.getDeploymentDescriptorResource();
			return xmlResource.getJ2EEVersionID() == J2EEVersionConstants.J2EE_1_4_ID;
		} catch (FileNotFoundException ex) {
			return false;
		}
	}



}
