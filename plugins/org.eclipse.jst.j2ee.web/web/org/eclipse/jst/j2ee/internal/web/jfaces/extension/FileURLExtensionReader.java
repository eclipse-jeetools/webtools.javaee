/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jun 13, 2004
 */
package org.eclipse.jst.j2ee.internal.web.jfaces.extension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IPath;
import org.eclipse.wst.common.core.util.RegistryReader;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;

/**
 * @author vijayb
 * 
 * This class loads all the extensions that define the correct URL string for the Java Server Faces
 * file
 *  
 *  
 */
public class FileURLExtensionReader extends RegistryReader {
	static FileURLExtensionReader instance = null;
	protected List fileURLExtensions;

	/**
	 * @param registry
	 * @param pluginID
	 * @param extensionPoint
	 */
	public FileURLExtensionReader() {
		super(WebPlugin.PLUGIN_ID, "fileURL"); //$NON-NLS-1$
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
	 */
	@Override
	public boolean readElement(IConfigurationElement element) {
		if (FileURLExtension.FILE_URL_EXTENSION.equals(element.getName())) {
			addExtension(element);
			return true;
		}
		return false;
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtension(IConfigurationElement newExtension) {
		getFileURLExtensions().add(new FileURLExtension(newExtension));
	}

	/**
	 * Sets the extension point.
	 * 
	 * @param extensions
	 *            The extensions to set
	 */
	protected void addExtensionPoint(FileURLExtensionReader newExtension) {
		if (this.fileURLExtensions == null)
			this.fileURLExtensions = new ArrayList();
		this.fileURLExtensions.add(newExtension);
	}

	/**
	 * @return the appropriate handler for the project based on priorities of those which are
	 *         available and enabled
	 */
	public FileURL getFilesURL() {
		FileURLExtension fileURLExt;
		ArrayList<FileURL> fileUrlList = new ArrayList<FileURL>();
		FileURL currentURL = null;
		for (Iterator fileURLExtItr = getFileURLExtensions().iterator(); fileURLExtItr.hasNext();) {
			fileURLExt = (FileURLExtension) fileURLExtItr.next();
			currentURL = fileURLExt.getInstance();
			if (currentURL != null)
				fileUrlList.add(currentURL);
		}
		if (fileUrlList.size() == 1)
			return fileUrlList.get(0);
		return new FileURLWrapper(fileUrlList);
	}

	
	
	private class FileURLWrapper implements FileURL {
		ArrayList<FileURL> myUrlList = new ArrayList<FileURL>();
		public FileURLWrapper(ArrayList<FileURL> newUrlList)
		{
			myUrlList = newUrlList;
		}
		public IPath getFileURL(IResource resource, IPath existingURL) {
			IPath retVal = existingURL;
			for (FileURL fileUrl: myUrlList){
				retVal = fileUrl.getFileURL(resource, existingURL);
				if (retVal != existingURL && retVal != null)
				{
					return retVal;
				}
			}
			return retVal;
		}
	}
	
	
	/**
	 * Gets the instance.
	 * 
	 * @return Returns a FileURLExtensionReader
	 */
	public static FileURLExtensionReader getInstance() {
		if (instance == null) {
			instance = new FileURLExtensionReader();
			instance.readRegistry();
		}
		return instance;
	}

	/**
	 * @return Returns the handlerExtensions.
	 */
	protected List getFileURLExtensions() {
		if (this.fileURLExtensions == null)
			this.fileURLExtensions = new ArrayList();
		return this.fileURLExtensions;
	}


}
