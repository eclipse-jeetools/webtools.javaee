/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.util;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jem.util.plugin.JEMUtilPlugin;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.core.internal.util.JavaeeResourceFactoryImpl
 * @generated
 */
public class JavaeeResourceImpl extends XMLResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public JavaeeResourceImpl(URI uri) {
		super(uri);
	}
	
	@Override
	public void save(Map options) throws IOException {
		
		IFile file = getPlatformFile();
		
		if (file == null || !file.exists()) return; // Only save if file existed
		super.save(options);
	}
	
	/**
	 * Return the IFile for the <code>uri</code> within the Workspace. This URI is assumed to be
	 * absolute in the following format: platform:/resource/....
	 */
	private IFile getPlatformFile(URI uri) {
		if (WorkbenchResourceHelperBase.isPlatformResourceURI(uri)) {
			String fileString = URI.decode(uri.path());
			fileString = fileString.substring(JEMUtilPlugin.PLATFORM_RESOURCE.length() + 1);
			return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(fileString));
		}
		return null;
	}

	private IFile getPlatformFile() {
		IFile file = null;
		file = getPlatformFile(getURI());
		if (file == null) {
			if (getResourceSet() != null) {
				URIConverter converter = getResourceSet().getURIConverter();
				URI convertedUri = converter.normalize(uri);
				if (!uri.equals(convertedUri))
					file = getPlatformFile(convertedUri);
			}
		}
		return file;
	}

	public void save(Map options, boolean force) throws IOException {
		IFile file = getPlatformFile();
		if (!force && (file == null || !file.exists())) return; // Only save if file existed
		super.save(options);
		if (force) getResourceSet().getResources().remove(this); //Remove initial resource
	}

} //JavaeeResourceImpl
