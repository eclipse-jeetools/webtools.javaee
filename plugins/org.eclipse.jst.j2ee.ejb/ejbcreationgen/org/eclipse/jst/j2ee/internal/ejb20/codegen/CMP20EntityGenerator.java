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
package org.eclipse.jst.j2ee.internal.ejb20.codegen;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.ejb.codegen.CMPEntityGenerator;


/**
 * Insert the type's description here. Creation date: (10/3/00 5:34:42 PM)
 * 
 * @author: Steve Wasleski
 */
public class CMP20EntityGenerator extends CMPEntityGenerator {
	private List fGeneratorNames = null;

	/**
	 * Returns the list of generator names.
	 */
	protected List getCUGeneratorNames() {
		if (fGeneratorNames == null) {
			fGeneratorNames = new ArrayList();
			if (shouldGenRemote())
				fGeneratorNames.add(IEJB20GenConstants.CMP_ENTITY_REMOTE_INTERFACE_CU);
			if (shouldGenHome())
				fGeneratorNames.add(IEJB20GenConstants.CMP_ENTITY_HOME_INTERFACE_CU);
			if (shouldGenLocalHome())
				fGeneratorNames.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_HOME_INTERFACE_CU);
			if (shouldGenLocal())
				fGeneratorNames.add(IEJB20GenConstants.CMP20_ENTITY_LOCAL_INTERFACE_CU);
			if (shouldGenEjbClass())
				fGeneratorNames.add(IEJB20GenConstants.CMP20_ENTITY_BEAN_CLASS_CU);
			if (shouldAddPrimaryKeyGenerator())
				fGeneratorNames.add(IEJB20GenConstants.CMP_ENTITY_KEY_CLASS_CU);
		}
		return fGeneratorNames;
	}

	/**
	 * This implementation initializes a Link class compilation unit generator for each role helper
	 * defined by the top level helper.
	 */
	protected void initializeLinkGenerators() throws GenerationException {
		// no link class generators needed
	}
}