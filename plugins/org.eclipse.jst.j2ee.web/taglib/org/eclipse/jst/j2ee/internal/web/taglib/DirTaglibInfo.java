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
/*
 * Created on Jun 17, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.taglib;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.web.taglib.IDirTaglibInfo;


/**
 * @author admin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class DirTaglibInfo extends TaglibInfo implements IDirTaglibInfo {

	private IContainer container;

	//private IFile[] tagFiles;

	/**
	 * @param project
	 * @param uri
	 * @param tldLocation
	 */
	public DirTaglibInfo(IContainer container, String uri, IPath tldLocation) {
		super(container.getProject(), uri, tldLocation);
		this.container = container;
		setDirectoryEntry(true);
		// TODO Auto-generated constructor stub
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.IDirTaglibInfo#getTagFiles()
	 */
	public IFile[] getTagFiles() {
		List iFiles = new ArrayList();

		try {
			IResource resources[] = this.container.members();

			for (int i = 0; i < resources.length; i++) {
				IResource resource = resources[i];
				if (resource.getType() == IResource.FILE && resource.getFileExtension().equalsIgnoreCase("tag")) { //$NON-NLS-1$
					iFiles.add(resource);
				}
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (IFile[]) iFiles.toArray(new IFile[iFiles.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.IDirTaglibInfo#getDirectory()
	 */
	public IContainer getDirectory() {
		return this.container;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.taglib.IDirTaglibInfo#getTags()
	 */
	public String[] getTags() {
		IFile tagFiles[] = getTagFiles();
		List tagList = new ArrayList();
		for (int i = 0; i < tagFiles.length; i++) {
			IFile array_element = tagFiles[i];
			String filename = array_element.getName();
			String extn = (new Path(array_element.getName())).getFileExtension();
			String tagname = filename.substring(0, filename.indexOf("." + extn)); //$NON-NLS-1$
			tagList.add(tagname);
		}
		return (String[]) tagList.toArray(new String[tagList.size()]);
	}

}