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



import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.ImportStrategy;


/**
 * An interface that defines an API for opening archives. The {@link CommonArchiveFactory}
 * implementation uses a root level discriminator to open an archive. The main benefit of this api
 * is it allows extended teams or third parties to plug in to the framework to allow specialized
 * kinds of jar files do be discerned at creation/open time without the client having to provide a
 * lot of logic. For example, a client would simply write
 * <code>((CommonArchivePackage)EPackage.Registry.INSTANCE.getEPackage(CommonArchivePackage.eNS_URI)).getCommonArchiveFactory().open(aString)<code>, and get back 
 * the correct kind of archive instance.  A discriminator contains children, which is an ordered list of discriminators, each of which 
 * can have its turn to determine if it can open an archive.  The first one to succeed wins.  Once a discriminator determines that it
 * can open an archive, it gives each of its children the opportuntity to do something more specific, and so on. In the base
 * implementation, discriminators are defined as single instances of an inner class for each import strategy.  The following code shows 
 * how to register a discriminator for a specialized EJBJarFile: 
 * <code>EjbJar11ImportStrategyImpl.getDiscriminator().addChild(aDiscriminator);</code>
 * This would be done as an initialization at startup time.  If the child discriminator is ever invoked, the parent will have already
 * determined that the archive is an EJBJarFile and will have converted it to that type.
 */
public interface ArchiveTypeDiscriminator {
	public void addChild(ArchiveTypeDiscriminator child);

	/**
	 * @throws java.util.NoSuchElementException
	 *             if the predecessor is not included in the list of children
	 */
	public void addChildAfter(ArchiveTypeDiscriminator child, ArchiveTypeDiscriminator predecessor) throws java.util.NoSuchElementException;

	/**
	 * @throws java.util.NoSuchElementException
	 *             if the successor is not included in the list of children
	 */
	public void addChildBefore(ArchiveTypeDiscriminator child, ArchiveTypeDiscriminator successor) throws java.util.NoSuchElementException;

	/**
	 * Performs tests on the archive to determine if it is the kind this discriminator is interested
	 * in
	 */
	public boolean canImport(Archive anArchive);

	/**
	 * Factory method to create the import strategy for a converted archive
	 */
	public ImportStrategy createImportStrategy(Archive old, Archive newArchive);

	/**
	 * Return a string to be presented either through an exception or error message when a specific
	 * kind of archive is expected and this discriminator can't open it.
	 */
	public String getUnableToOpenMessage();

	public boolean hasChild(ArchiveTypeDiscriminator disc);

	public boolean hasChildren();

	/**
	 * Point of entry for attempting to open an archive
	 * 
	 * @return a converted archive or null if this discriminator can't convert it
	 */
	public Archive openArchive(Archive anArchive) throws OpenFailureException;

	public void removeChild(ArchiveTypeDiscriminator child);
}
