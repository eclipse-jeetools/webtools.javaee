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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



/**
 * @author mdelder
 */
public class ArchiveTypeDiscriminatorRegistry {

	private Collection discriminators = null;
	private Collection customTypes = null;

	/*
	 * Most known types are of length 3. Whenver a new type is added that is not of length 3,
	 * modifications may be necessary to the 'isKnownArchiveType() method
	 */
	private static final String[] defaultKnownTypes = new String[]{"ear", "war", "jar", "zip", "far"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$


	public static final ArchiveTypeDiscriminatorRegistry INSTANCE = new ArchiveTypeDiscriminatorRegistry();

	public static void registorDiscriminator(GenericArchiveTypeDiscriminator discriminator) {
		INSTANCE.getDiscriminators().add(discriminator);
		INSTANCE.addKnownArchiveTypes(discriminator.getCustomFileExtensions());
	}

	public static ArchiveTypeDiscriminatorRegistry getInstance() {
		return INSTANCE;
	}

	/**
	 * @return
	 */
	public Collection getDiscriminators() {
		if (discriminators == null)
			discriminators = new ArrayList();
		return discriminators;
	}

	public void contributeTypes(Archive archive) {
		if (discriminators == null)
			return;
		GenericArchiveTypeDiscriminator discriminator = null;
		for (Iterator itr = discriminators.iterator(); itr.hasNext();) {
			discriminator = (GenericArchiveTypeDiscriminator) itr.next();
			if (discriminator.discriminate(archive))
				archive.getTypes().add(discriminator.getTypeKey());
		}
	}

	public void addKnownArchiveTypes(String[] newTypes) {
		if (customTypes == null) {
			customTypes = new ArrayList();
		}
		for (int i = 0; i < newTypes.length; i++) {
			customTypes.add(newTypes[i]);
		}
	}

	public boolean isKnownArchiveType(String fileURI) {
		if (fileURI == null || fileURI.length() == 0)
			return false;

		String lowerCaseUri = fileURI.toLowerCase();
		/*
		 * Ensure that the length of the URI is long enough to contain a .3 style extension
		 */
		if (lowerCaseUri.length() > 4 && lowerCaseUri.charAt(lowerCaseUri.length() - 4) == '.') {
			String ending = lowerCaseUri.substring(lowerCaseUri.length() - 3);
			for (int i = 0; i < defaultKnownTypes.length; i++)
				if (defaultKnownTypes[i].equals(ending))
					return true;
		}

		String customType = null;
		if (customTypes != null) {
			Iterator customTypesIterator = customTypes.iterator();
			while (customTypesIterator.hasNext()) {
				customType = (String) customTypesIterator.next();
				if (fileURI.endsWith(customType))
					return true;
			}
		}

		return false;
	}

}
