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
package org.eclipse.jst.j2ee.internal.archive;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;

public class ArchiveWrapper {

	private Archive commonArchive = null;
	private IArchive archive = null;
	private JavaEEQuickPeek jqp = null;

	public ArchiveWrapper(IArchive archive) {

		this.archive = archive;
		JavaEEArchiveUtilities jea = JavaEEArchiveUtilities.INSTANCE;
		jqp = jea.getJavaEEQuickPeek(archive);

	}

	public ArchiveWrapper(Archive mFile) {

		this.commonArchive = mFile;
		int type = J2EEConstants.UNKNOWN;

		if (mFile.isApplicationClientFile()) {
			type = J2EEConstants.APPLICATION_CLIENT_TYPE;
		} else if (mFile.isEARFile()) {
			type = J2EEConstants.APPLICATION_TYPE;
		} else if (mFile.isEJBJarFile()) {
			type = J2EEConstants.EJB_TYPE;
		} else if (mFile.isWARFile()) {
			type = J2EEConstants.WEB_TYPE;
		} else if (mFile.isRARFile()) {
			type = J2EEConstants.CONNECTOR_TYPE;
		}
		if (type != J2EEConstants.UNKNOWN) {
			int version = ArchiveUtil.getFastSpecVersion((ModuleFile) mFile);
			jqp = new JavaEEQuickPeek(type, version);
		}

	}

	public JavaEEQuickPeek getJavaEEQuickPeek() {
		return jqp;
	}

	public Archive getCommonArchive() {
		return (Archive) commonArchive;
	}

	public IArchive getIArchive() {
		return archive;
	}

	public void close() {
		try {
			if (commonArchive != null) {
				commonArchive.close();
			}
			if (archive != null) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		} catch (RuntimeException e) {
			fail();
		}

	}

	public IPath getPath() {
		if (commonArchive != null) {
			IPath path = new Path(commonArchive.getURI());
			return path;
		}
		if (archive != null) {
			return archive.getPath();
		}
		fail();
		return null;
	}

	private void fail() {
		throw new RuntimeException("ArchiveWrapper is inconsistent.");
	}

	public int getSize() {
		if (commonArchive != null)
			return commonArchive.getFiles().size();
		else
			return archive.getArchiveResources().size();
	}

	private List <ArchiveWrapper> webLibs;
	
	public List <ArchiveWrapper> getWebLibs() {
		if(webLibs != null){
			return webLibs;
		}
		if (jqp.getType() != J2EEVersionConstants.WEB_TYPE) {
			fail();
		}
		if (commonArchive != null) {
			WARFile war = (WARFile) commonArchive;
			List wrappedLibs = new ArrayList();
			List libs = war.getLibArchives();
			for(int i=0;i<libs.size(); i++){
				wrappedLibs.add(new ArchiveWrapper((Archive)libs.get(i)));
			}
			webLibs = wrappedLibs;
			return wrappedLibs;
		}
		if (archive != null) {
			webLibs = new ArrayList<ArchiveWrapper>();
			List<IArchiveResource> resources = archive.getArchiveResources();
			for (IArchiveResource resource : resources) {
				if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
					IPath path = resource.getPath();
					if (path.segmentCount() > 2) {
						if (path.segment(0).equals("WEB-INF") && path.segment(1).equals("lib")) {
							String lastSegment = path.lastSegment();
							if (lastSegment.endsWith("jar") || lastSegment.endsWith("zip")) {
								IArchive webLib;
								try {
									webLib = archive.getNestedArchive(resource);
									webLibs.add(new ArchiveWrapper(webLib));
								} catch (ArchiveOpenFailureException e) {
									Logger.getLogger().logError(e);
								}

							}
						}
					}
				}
			}
			
			return webLibs;
		}
		fail();
		return null;
	}
	
	public String getName() {
		if (commonArchive != null) {
			return commonArchive.getName();
		}
		if (archive != null) {
			return archive.getPath().lastSegment();
		}
		fail();
		return null;
	}

}
