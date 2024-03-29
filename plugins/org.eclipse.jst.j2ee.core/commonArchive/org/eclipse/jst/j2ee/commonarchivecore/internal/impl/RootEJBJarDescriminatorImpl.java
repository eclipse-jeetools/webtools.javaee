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
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.EjbJar11ImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy;


/**
 * Insert the type's description here. Creation date: (12/04/00 5:24:44 PM)
 * 
 * @author: Administrator
 */
public class RootEJBJarDescriminatorImpl extends org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminatorImpl {
	protected static ArchiveTypeDiscriminatorImpl singleton;

	/**
	 * CommonArchiveFactoryDescriminator constructor comment.
	 */
	public RootEJBJarDescriminatorImpl() {
		super();
		initialize();
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	@Override
	public boolean canImport(Archive anArchive) {
		java.util.List theChildren = getChildren();
		for (int i = 0; i < theChildren.size(); i++) {
			ArchiveTypeDiscriminator child = (ArchiveTypeDiscriminator) theChildren.get(i);
			if (child.canImport(anArchive))
				return true;
		}
		return false;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	@Override
	public Archive convert(Archive anArchive) {
		return anArchive;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator This never gets called for this
	 *      implementer.
	 */
	@Override
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
	@Override
	public ImportStrategy createImportStrategy(Archive old, Archive newArchive) {
		return null;
	}

	/**
	 * @return null This method should not get called at this level
	 * 
	 * @see ArchiveTypeDescriminator#getUnableToOpenMessage()
	 */
	@Override
	public java.lang.String getUnableToOpenMessage() {
		StringBuffer message = new StringBuffer();
		java.util.List theChildren = getChildren();
		for (int i = 0; i < theChildren.size(); i++) {
			ArchiveTypeDiscriminator child = (ArchiveTypeDiscriminator) theChildren.get(i);
			message.append(child.getUnableToOpenMessage());
			message.append('\n');
		}
		return message.toString();
	}

	public void initialize() {
		addChild(EjbJar11ImportStrategyImpl.getDiscriminator());
	}

	/**
	 * @see ArchiveTypeDiscriminator#openArchive(Archive)
	 */
	@Override
	public Archive openArchive(Archive anArchive) throws OpenFailureException {
		Archive result = super.openArchive(anArchive);
		if (result == anArchive)
			//Couldn't convert
			return null;
		return result;
	}

	public static ArchiveTypeDiscriminator singleton() {
		if (singleton == null) {
			singleton = new RootEJBJarDescriminatorImpl();
		}
		return singleton;
	}
}
