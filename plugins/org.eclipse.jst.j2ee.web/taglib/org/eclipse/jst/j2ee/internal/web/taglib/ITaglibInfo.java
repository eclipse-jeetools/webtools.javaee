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
package org.eclipse.jst.j2ee.internal.web.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

/**
 * @version 1.0
 * @author
 */
public interface ITaglibInfo {

	/**
	 * This method is added so that all implementors of ITaglibInfo implement clone and hence object
	 * references of type ITaglibInfo can be cloned.
	 * 
	 * @return Object
	 */
	public Object clone();

	/**
	 * Return the URI that this taglib is known by. This string does not have anything to do with
	 * the tablib's location in the project structure.
	 * 
	 * @return String The uri.
	 */
	String getURI();

	/**
	 * Returns the project relative location of the taglib. If the taglib is inside of a .jar file,
	 * then the project relative location of the jar file is returned. If the taglib is specified by
	 * a loose tld file, then the project relative location of the tld file is returned. If it is a
	 * server contributed taglib jar, return the actual file system location of the file.
	 * 
	 * @return IPath The project relative location of the taglib.
	 */
	IPath getLocation();

	/**
	 * Returns the relative location of the TLD file for the taglib. If the TLD is loose, then the
	 * path returned is project relative. If it is in a jar file, then the path returned is relative
	 * to the jar file. If the taglib is a mapping in the web.xml file, then this returns the
	 * project relative value of the <taglib-location>file.
	 * 
	 * @return IPath Return location of the TLD file
	 */
	IPath getTLDLocation();

	/**
	 * Return an input stream on TLD file that this taglib represents. It is the responsibility of
	 * the caller of this method to close the stream.
	 * 
	 * @return InputStream
	 */
	InputStream getTLDStream() throws ZipException, IOException, CoreException;

	/**
	 * Return the recommended prefix for this taglib. If unset, the short-name from the TLD file is
	 * returned
	 * 
	 * @return String The recommended prefix for this taglib
	 */
	String getPrefix();

	/**
	 * Return the project that is the source of this taglib (where it resides). In the case of a
	 * library module, this would be the referenced JavaProject.
	 * 
	 * @return IProject the source project for this taglib
	 */
	IProject getSourceProject();

	/**
	 * Sets the recommended prefix for this taglib when it is used in a jsp.
	 * 
	 * @param prefix
	 *            The prefix- typically 4 characters or less.
	 */
	void setPrefix(String prefix);

	/**
	 * Return true if the tld file of the taglib is inside a jar file and false if it is in loose in
	 * the project.
	 * 
	 * @return boolean True if tld file is in a jar
	 */
	boolean isInJar();

	/**
	 * Return true if this Taglib is a web.xml mapping
	 * 
	 * @return boolean True if web.xml mapped.
	 */
	boolean isWebXMLEntry();

	/**
	 * Return true if the URI of this mapping was retrieved from the <uri>element of the TLD file.
	 * 
	 * @return boolean True if uri is from a TLD. False otherwise.
	 */
	boolean isURIFromTLD();

	/**
	 * Return true if the taglib mapping represented is valid. Valid means that the location of the
	 * TLD can actually be resolved.
	 * 
	 * @return boolean
	 */
	boolean isValid();

	/**
	 * Return true if the taglib is inside a lib module mapped jar. If this is the case, then
	 * getLocation() returns the jar name and getTLDLocation() returns the project relative TLD file
	 * location in the java project.
	 * 
	 * @return boolean True if TLD is in a lib mapped project.
	 */
	boolean isLibModule();

	/**
	 * Sets the isWebXMLEntry.
	 * 
	 * @param isWebXMLEntry
	 *            The isWebXMLEntry to set
	 */
	void setIsWebXMLEntry(boolean isWebXMLEntry);

	/**
	 * Set the validity of the taglib entry
	 * 
	 * @param isValid
	 */
	void setIsValid(boolean isValid);


	/**
	 * Return true if the taglib is contributed from by a server's container as what is known as
	 * well known URI's in jsp spec
	 * 
	 * @return
	 */
	boolean isServerContribution();

	/**
	 * Return true if its a directory entry which contains .tag files
	 * 
	 * @return
	 */
	boolean isDirectoryEntry();
}