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
 * Created on Jun 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.locator;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.internal.web.operations.ServerTargetUtil;
import org.eclipse.jst.j2ee.internal.web.taglib.ServerJarsUtil;
import org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester;
import org.eclipse.jst.j2ee.internal.web.taglib.TaglibInfo;
import org.eclipse.jst.j2ee.web.taglib.ITaglibInfo;
import org.eclipse.wst.server.core.IRuntime;

/**
 * @author admin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class WebProjectServerTargetTaglibLocator extends AbstractWebTaglibLocator {

	private List serverTaglibs;
	private IRuntime runtime;

	/**
	 * @param project
	 */
	public WebProjectServerTargetTaglibLocator(IProject project) {
		super(project);
		this.serverTaglibs = new ArrayList(2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.taglib.AbstractTaglibLocator#search(org.eclipse.core.resources.IResource)
	 */
	public ITaglibInfo[] search(IResource resource) {
		// ignore resource calculate server target taglibs
		IRuntime newRuntime = ServerTargetUtil.getServerTarget(this.project.getName());
		if (this.runtime != newRuntime) {
			try {
				// TODO right now only v6 server contributes taglibs, if more servers are added need
				// to determine that
				if (ServerJarsUtil.isTargetedAtWASV6(newRuntime)) {
					this.serverTaglibs = calculateServerTargetTaglibs();
				} else
					this.serverTaglibs.clear();

			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				this.runtime = newRuntime;
			}
		}
		return (ITaglibInfo[]) this.serverTaglibs.toArray(new ITaglibInfo[this.serverTaglibs.size()]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.taglib.AbstractTaglibLocator#searchFile(org.eclipse.core.resources.IFile)
	 */
	protected ITaglibInfo[] searchFile(File file) {
		if (hasJarExtension(file.getName())) {
			return searchJarFile(file);
		}
		return EMPTY_TAGLIBINFO_ARRAY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.taglib.AbstractTaglibLocator#searchFile(org.eclipse.core.resources.IFile)
	 */
	protected ITaglibInfo[] searchFile(IFile file) {
		// do nothing
		return null;
	}

	/**
	 * @return
	 */
	protected List calculateServerTargetTaglibs() {
		IPath serverJars[] = ServerJarsUtil.getServerJars(this.project);
		List serverTaglibList = new ArrayList();
		for (int i = 0; i < serverJars.length; i++) {
			IPath path = serverJars[i];
			serverTaglibList.addAll(Arrays.asList(searchFile(path.toFile())));
		}
		return serverTaglibList;
	}

	protected ITaglibInfo createTaglibForJar(String uri, IPath jarfile, IPath tldLocation) {
		TaglibInfo info = (TaglibInfo) super.createTaglibForJar(uri, jarfile, tldLocation);
		info.setServerContribution(true);
		return info;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.web.taglib.locator.AbstractWebTaglibLocator#canAddTaglibTld(org.eclipse.jst.j2ee.internal.web.taglib.TLDDigester)
	 */
	protected boolean canAddTaglibTld(TLDDigester digester) {
		// as this is a server contributed entry
		return false;
	}
}