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
package org.eclipse.jst.j2ee.commonarchivecore;



import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.exception.DeploymentDescriptorLoadException;
import org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException;
import org.eclipse.jst.j2ee.webapplication.WebApp;


/**
 * @generated
 */
public interface WARFile extends ModuleFile {

	/**
	 * Copy the file, and swizzle the file uri if necessary by prepending the classes directory
	 * 
	 * @throws DuplicateObjectException
	 *             of a file with the modified uri already exists in the archive
	 * 
	 * @throws IllegalArgumentException
	 *             if the parameter is a ReadOnlyDirectory
	 */
	public File addCopyClass(File aFile) throws org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException;

	/**
	 * Copy the file, and swizzle the file uri if necessary by prepending the libs directory
	 * 
	 * @throws DuplicateObjectException
	 *             of a file with the modified uri already exists in the archive
	 * 
	 * @throws IllegalArgumentException
	 *             if the parameter is a ReadOnlyDirectory
	 */
	public File addCopyLib(File aFile) throws org.eclipse.jst.j2ee.commonarchivecore.exception.DuplicateObjectException;

	/**
	 * getClasses() - filter files to return the class files from \web-inf\classes
	 */
	public List getClasses();

	/**
	 * @throws DeploymentDescriptorLoadException -
	 *             is a runtime exception, because we can't override the signature of the generated
	 *             methods
	 */



	/**
	 * @generated This field/method will be replaced during code generation
	 * @return The DeploymentDescriptor reference
	 */
	WebApp getDeploymentDescriptor() throws DeploymentDescriptorLoadException;

	/**
	 * Filter files to return the files from \we-inf\lib
	 */
	public List getLibs();

	/**
	 * Filter files to return the JARs and Zips from \we-inf\lib
	 */
	public List getLibArchives();

	/**
	 * getResources() - filter files to return the Web resources within the WAR (no
	 * classes/libs/metadata)
	 */
	public List getResources();

	/**
	 * Return the source file that matches the output file passed in
	 * 
	 * @param aClassFile
	 *            The .class file or other output file to find the source for
	 * @return String The matching source. Null if there is no matching source found
	 */
	public File getSourceFile(File aClassFile);

	/**
	 * @generated This field/method will be replaced during code generation
	 * @param l
	 *            The new value of the DeploymentDescriptor reference
	 */
	void setDeploymentDescriptor(WebApp value);

}
