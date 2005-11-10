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
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;



import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy;


/**
 * @see ArchiveTypeDiscriminator
 */
public abstract class ArchiveTypeDiscriminatorImpl implements ArchiveTypeDiscriminator {
	protected List children;

	public ArchiveTypeDiscriminatorImpl() {
		super();
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public void addChild(ArchiveTypeDiscriminator child) {
		if (hasChild(child))
			return;
		getChildren().add(child);
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public void addChildAfter(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator child, org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator predecessor) throws java.util.NoSuchElementException {
		if (hasChild(child))
			return;
		int index = getChildren().indexOf(predecessor);
		if (index >= 0) {
			index++;
			getChildren().add(index, child);
		} else {
			throw new java.util.NoSuchElementException(predecessor.toString());
		}
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public void addChildBefore(ArchiveTypeDiscriminator child, ArchiveTypeDiscriminator successor) throws java.util.NoSuchElementException {
		if (hasChild(child))
			return;
		int index = getChildren().indexOf(successor);
		if (index >= 0) {
			getChildren().add(index, child);
		} else {
			throw new java.util.NoSuchElementException(successor.toString());
		}
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public abstract boolean canImport(Archive anArchive);

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public Archive convert(Archive anArchive) throws OpenFailureException {
		Archive destination = createConvertedArchive();

		//turn of notifications
		destination.eSetDeliver(false);
		destination.eSetDeliver(false);

		//Copy the relevant attributes
		destination.setURI(anArchive.getURI());
		destination.setOriginalURI(anArchive.getURI());
		destination.setSize(anArchive.getSize());
		destination.setLastModified(anArchive.getLastModified());

		destination.setLoadStrategy(anArchive.getLoadStrategy());
		destination.setOptions(anArchive.getOptions());
		destination.setExtraClasspath(anArchive.getExtraClasspath());
		if (destination.isModuleFile()) {
			ImportStrategy importStrategy = createImportStrategy(anArchive, destination);
			((ModuleFile) destination).setImportStrategy(importStrategy);
		}



		//turn notifications back on
		destination.eSetDeliver(true);
		destination.eSetDeliver(true);

		return destination;
	}

	public abstract Archive createConvertedArchive();

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public abstract ImportStrategy createImportStrategy(Archive old, Archive newArchive);

	public java.util.List getChildren() {
		if (children == null)
			children = new java.util.ArrayList();
		return children;
	}

	/**
	 * Iterate through each child and attempt to convert the archive to the child's type; return the
	 * converted archive from the first child that succeeds, or null if no child succeeds or no
	 * child exists
	 */
	protected Archive getImportableArchiveFromChild(Archive anArchive) throws OpenFailureException {
		if (!hasChildren()) {
			return null;
		}
		List theChildren = getChildren();
		Archive childConvertedArchive = null;
		for (int i = 0; i < theChildren.size(); i++) {
			ArchiveTypeDiscriminator child = (ArchiveTypeDiscriminator) theChildren.get(i);
			childConvertedArchive = child.openArchive(anArchive);
			if (childConvertedArchive != null) {
				return childConvertedArchive;
			}
		}
		return null;
	}

	protected String getXmlDDMessage(String archiveType, String ddUri) {
		return CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.invalid_archive_EXC_, (new Object[]{archiveType, ddUri})); //$NON-NLS-1$ = "Archive is not a valid {0} because the deployment descriptor can not be found (case sensitive): {1}"
	}

	public boolean hasChild(ArchiveTypeDiscriminator disc) {
		return hasChildren() && getChildren().contains(disc);
	}

	public boolean hasChildren() {
		return children != null && children.size() > 0;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public Archive openArchive(Archive anArchive) throws OpenFailureException {
		if (!canImport(anArchive)) {
			return null;
		}
		Archive convertedArchive = convert(anArchive);
		Archive childConvertedArchive = getImportableArchiveFromChild(convertedArchive);
		if (childConvertedArchive != null)
			return childConvertedArchive;
		return convertedArchive;
	}

	/**
	 * @see com.ibm.etools.archive.ArchiveTypeDiscriminator
	 */
	public void removeChild(org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator child) {
		getChildren().remove(child);
	}

	public void setChildren(java.util.List newChildren) {
		children = newChildren;
	}
}
