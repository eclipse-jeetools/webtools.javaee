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



/**
 * @author mdelder
 */
public interface GenericArchiveTypeDiscriminator {

	/**
	 * This is a unique identifier that contributors should use to specify the generic type that
	 * should be associated with archives. Example: com.yourcompany.j2ee.extension.customModule
	 * 
	 * @return
	 */
	public String getTypeKey();

	/**
	 * If common archive should recognize file extensions other than the standard ones, e.g., .jar,
	 * .zip, then these file extensions can be specified here.
	 * 
	 * @return
	 */
	public String[] getCustomFileExtensions();

	/**
	 * Method that individual discriminators can implement to determine if an Archive is of a
	 * particular type. Implementers should be sensitive to performance requirments. Usually simple
	 * tests should be performed, such as:
	 * <code>if (anArchive.containsFile("xxx.xml")<code>.  The result will
	 * be stored in the Archive's "types" attribute.
	 * @param anArchive
	 * @return
	 */
	public boolean discriminate(Archive anArchive);

}
