/*******************************************************************************
 * Copyright (c) 2007 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * BEA Systems, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.classpathdep;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;

/**
 * Contains utility code for working manipulating the module MANIFEST.MF
 * classpath to reflect classpath component dependencies.
 */
public class ClasspathDependencyManifestUtil {

	/**
	 * Generates new MANIFEST.MF with a dynamically updated classpath that is written to the specified
	 * output stream.
	 * @param manifestFile The current MANIFEST.MF file.
	 * @param dynamicURIs Is List of URIs to dynamically add to the manifest classpath.
	 * @param outputStream Stream to which the modified entry should be written.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static void updateManifestClasspath(final IFile manifestFile, final List dynamicURIs, final OutputStream outputStream) throws IOException, FileNotFoundException {
		
        try {
        	InputStream in = null;
        	ArchiveManifest manifest = null;
        	try {
        		in = manifestFile.getContents();
        		manifest = new ArchiveManifestImpl(in);
        	} catch (CoreException ce) {
        		throw new IOException(ce.getLocalizedMessage());
        	} finally {
        		if (in != null) {
        			try {
        				in.close();
        				in = null;
        			} catch (IOException e) {
        				Logger.getLogger().logError(e);
        			}
        		}
        	}
        	final String[] manifestClasspath = manifest.getClassPathTokenized();
        	final List updatedCP = new ArrayList();
        	for (int i = 0; i < manifestClasspath.length; i++) {
        		updatedCP.add(manifestClasspath[i]);
        	}
        	// update manifest classpath to include dynamic entries
        	for (int j = 0; j < dynamicURIs.size(); j++) {
        		final String containerURI = (String) dynamicURIs.get(j);
        		// need to check existing entries to ensure it doesn't are exist on the classpath
        		boolean exists = false;
        		for (int i = 0; i < manifestClasspath.length; i++) {
        			if (manifestClasspath[i].equals(containerURI)) {
        				exists = true;
        				break;
        			}
        		}
        		if (!exists) {
        			updatedCP.add(containerURI);
        		}
        	}
        	final StringBuffer cpBuffer = new StringBuffer();
        	boolean first = true;
        	for (int j = 0; j < updatedCP.size(); j++) {
        		if (!first) {
        			cpBuffer.append(" ");
        		} else {
        			first = false;
        		}
        		cpBuffer.append((String) updatedCP.get(j));
        	}
        	manifest.setClassPath(cpBuffer.toString());
        	manifest.write(outputStream);
        	outputStream.flush();
        } finally {
        	if (outputStream != null) {
        		outputStream.close();
        	}
        }
    }
}
