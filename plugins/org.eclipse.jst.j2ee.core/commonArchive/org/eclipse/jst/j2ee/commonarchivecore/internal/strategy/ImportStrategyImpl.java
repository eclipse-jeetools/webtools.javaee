/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;


import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveFactoryRegistry;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;


/**
 * Abstract implementation off which other import strategies can subclass
 * 
 * @see ImportStrategy
 */
public abstract class ImportStrategyImpl extends ArchiveStrategyImpl implements ImportStrategy {
	/**
	 * ArchiveImportStrategy constructor comment.
	 */
	public ImportStrategyImpl() {
		super();
	}

	public static CommonarchiveFactory getArchiveFactory() {
		return CommonArchiveFactoryRegistry.INSTANCE.getCommonArchiveFactory();
	}

	/**
	 * @see com.ibm.etools.archive.ImportStrategy
	 */
	@Override
	public boolean isEJB10() {
		return false;
	}
}
