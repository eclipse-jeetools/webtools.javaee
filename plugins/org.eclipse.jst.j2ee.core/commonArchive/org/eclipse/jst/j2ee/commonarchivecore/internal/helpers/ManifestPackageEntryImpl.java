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




/**
 * Used for manifest support.
 */
public class ManifestPackageEntryImpl extends java.util.jar.Attributes {
	static final String NAME = "Name";//$NON-NLS-1$
	static final String SPECIFICATION_TITLE = "Specification-Title";//$NON-NLS-1$
	static final String SPECIFICATION_VERSION = "Specification-Version";//$NON-NLS-1$
	static final String SPECIFICATION_VENDOR = "Specification-Vendor";//$NON-NLS-1$
	static final String IMPLEMENTATION_TITLE = "Implementation-Title";//$NON-NLS-1$
	static final String IMPLEMENTATION_VERSION = "Implementation-Version";//$NON-NLS-1$
	static final String IMPLEMENTATION_VENDOR = "Implementation-Vendor";//$NON-NLS-1$

	/**
	 * ManifestPackageEntry constructor comment.
	 */
	public ManifestPackageEntryImpl() {
		super();
	}

	/**
	 * ManifestPackageEntry constructor comment.
	 * 
	 * @param size
	 *            int
	 */
	public ManifestPackageEntryImpl(int size) {
		super(size);
	}

	/**
	 * ManifestPackageEntry constructor comment.
	 * 
	 * @param attr
	 *            java.util.jar.Attributes
	 */
	public ManifestPackageEntryImpl(java.util.jar.Attributes attr) {
		super(attr);
	}

	public String getImplementationTitle() {
		return (String) get(IMPLEMENTATION_TITLE);
	}

	public String getImplementationVendor() {
		return (String) get(IMPLEMENTATION_VENDOR);
	}

	public String getImplementationVersion() {
		return (String) get(IMPLEMENTATION_VERSION);
	}

	public String getName() {
		return (String) get(NAME);
	}

	public String getSpecificationTitle() {
		return (String) get(SPECIFICATION_TITLE);
	}

	public String getSpecificationVendor() {
		return (String) get(SPECIFICATION_VENDOR);
	}

	public String getSpecificationVersion() {
		return (String) get(SPECIFICATION_VERSION);
	}

	public void setImplementationTitle(String value) {
		put(IMPLEMENTATION_TITLE, value);
	}

	public void setImplementationVendor(String value) {
		put(IMPLEMENTATION_VENDOR, value);
	}

	public void setImplementationVersion(String value) {
		put(IMPLEMENTATION_VERSION, value);
	}

	public void setName(String value) {
		put(NAME, value);
	}

	public void setSpecificationTitle(String value) {
		put(SPECIFICATION_TITLE, value);
	}

	public void setSpecificationVendor(String value) {
		put(SPECIFICATION_VENDOR, value);
	}

	public void setSpecificationVersion(String value) {
		put(SPECIFICATION_VERSION, value);
	}
}
