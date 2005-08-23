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
package org.eclipse.jst.j2ee.commonarchivecore.internal.strategy;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ResourceLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.FileIterator;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;


/**
 * LoadStrategy knows how to load or read the contents of a container. This provides a delegation
 * model for reading in the contents of an archive file. Clients can implement this interface, and
 * "plug in" to an instance of an archive. Examples might include reading from a zip file, from an
 * input stream, from the local file system, or from a workbench.
 */
public interface LoadStrategy {
	public void addOrReplaceMofResource(Resource aResource);

	/**
	 * Release any resources being held by this object
	 */
	public void close();

	public boolean contains(String uri);

	/**
	 * Return the absolute path of this strategy, if it exists; cases where it might not exist is if
	 * the load strategy is "virtual", e.g., a jar within a jar
	 */
	public String getAbsolutePath() throws FileNotFoundException;

	/**
	 * Return the absolute path of the root from which meta resources get loaded
	 */
	public String getResourcesPath() throws FileNotFoundException;

	/**
	 * Return the absolute path of the root from which classes and properties are loaded
	 */
	public String getBinariesPath() throws FileNotFoundException;


	public Container getContainer();

	public ResourceSet getResourceSet();

	public ResourceSet primGetResourceSet();

	/**
	 * Used internally; clients should not need to call
	 */
	public FileIterator getFileIterator() throws IOException;

	/**
	 * Used by an archive to obtain a list of it's files, which is usually deferred until the first
	 * time it is invoked, as an optimization.
	 */
	public List collectFiles();

	/**
	 * Returns an input stream on an entry named by the parameter
	 */
	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException;

	/**
	 * Returns an input stream on an entry named by the parameter, from the "resources path" of the
	 * loose archive if it exists, Otherwise the default behavior is the same as
	 * {@link #getInputStream(String)}
	 */
	public InputStream getResourceInputStream(String uri) throws IOException;

	public Collection getLoadedMofResources();

	public Resource getExistingMofResource(String uri);

	public Resource getMofResource(String uri) throws FileNotFoundException, ResourceLoadException;

	/**
	 * An archive uses a custom class loader for java reflection within a mof resourceSet;
	 * implementers of LoadStrategy may supply a mof resourceSet for which this class loader is not
	 * necessary, or could even cause breakage; this test gives the strategy the chance to "opt out"
	 * of the class loading game
	 */
	public boolean isClassLoaderNeeded();

	/**
	 * Returns whether this archive is reading files from a directory on the file system
	 */
	public boolean isDirectory();

	public boolean isMofResourceLoaded(String uri);

	/**
	 * Returns whether resources can be read by this strategy
	 */
	public boolean isOpen();

	/**
	 * Is the parameter in use by this strategy? Needed for when the archive is saved to the same
	 * location from which it was read
	 */
	public boolean isUsing(java.io.File aSystemFile);

	/**
	 * Used for optimizations, where the contents of the archive have not been changed; instead of
	 * iterating each file in the archive, we may be able to bulk save the whole thing
	 */
	public boolean requiresIterationOnSave();

	public Resource makeMofResource(String uri, EList extent);

	public void setContainer(Container aContainer);

	public void setResourceSet(ResourceSet aResourceSet);

	public LooseArchive getLooseArchive();

	public void setLooseArchive(LooseArchive aLooseArchive);

	public boolean isReadOnly();

	public void setReadOnly(boolean readOnly);

	public int getRendererType();

	public void setRendererType(int i);

}
