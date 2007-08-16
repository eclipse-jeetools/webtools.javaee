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
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy;


/**
 * Insert the type's description here. Creation date: (12/04/00 5:24:44 PM)
 * 
 * @author: Administrator
 */
public class RootArchiveTypeDescriminatorImpl extends org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl implements org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator {
	protected static ArchiveTypeDiscriminatorImpl singleton;

	/**
	 * CommonArchiveFactoryDescriminator constructor comment.
	 */
	public RootArchiveTypeDescriminatorImpl() {
		super();
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public boolean canImport(Archive anArchive) {
		return true;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public Archive convert(Archive anArchive) {
		return anArchive;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator This never gets called for this
	 *      implementer.
	 */
	public Archive createConvertedArchive() {
		return null;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator This never gets called for this
	 *      implementer.
	 */
	public org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy createImportStrategy() {
		return null;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator This never gets called for this
	 *      implementer.
	 */
	public ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
		return null;
	}

	/**
	 * @return null This method should not get called at this level
	 * 
	 * @see ArchiveTypeDescriminator#getUnableToOpenMessage()
	 */
	public java.lang.String getUnableToOpenMessage() {
		return "";//$NON-NLS-1$
	}

	public static ArchiveTypeDiscriminator singleton() {
		if (singleton == null) {
			singleton = new RootArchiveTypeDescriminatorImpl();
		}
		return singleton;
	}
}
