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

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.FileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
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
		} else {
			jqp = new JavaEEQuickPeek(null);
		}
	}

	public JavaEEQuickPeek getJavaEEQuickPeek() {
		return jqp;
	}

	public Archive getCommonArchive() {
		return (Archive) commonArchive;
	}

	public Object getUnderLyingArchive() {
		if (archive != null) {
			return archive;
		}
		if (commonArchive != null) {
			return commonArchive;
		}
		fail();
		return null;
	}

	private ArchiveWrapper cachedParent = null;

	public ArchiveWrapper getParent() {
		if (null != cachedParent) {
			return cachedParent;
		}
		if (archive != null) {
			cachedParent = new ArchiveWrapper(archive.getArchive());
			return cachedParent;
		}
		if (commonArchive != null) {
			cachedParent = new ArchiveWrapper((Archive) commonArchive.eContainer());
			return cachedParent;
		}
		fail();
		return null;
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
			Logger.getLogger().logError(e);
			throw e;
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

	private List<ArchiveWrapper> cachedWebLibs;

	public List<ArchiveWrapper> getWebLibs() {
		if (cachedWebLibs != null) {
			return cachedWebLibs;
		}
		if (jqp.getType() != J2EEVersionConstants.WEB_TYPE) {
			fail();
		}

		cachedWebLibs = new ArrayList<ArchiveWrapper>();

		if (commonArchive != null) {
			WARFile war = (WARFile) commonArchive;
			List libs = war.getLibArchives();
			for (int i = 0; i < libs.size(); i++) {
				cachedWebLibs.add(new ArchiveWrapper((Archive) libs.get(i)));
			}
			return cachedWebLibs;
		}
		if (archive != null) {
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
									cachedWebLibs.add(new ArchiveWrapper(webLib));
								} catch (ArchiveOpenFailureException e) {
									Logger.getLogger().logError(e);
								}
							}
						}
					}
				}
			}
			return cachedWebLibs;
		}
		fail();
		return null;
	}

	private List<ArchiveWrapper> cachedEARModules;

	public List<ArchiveWrapper> getEarModules() {
		if (cachedEARModules != null) {
			return cachedEARModules;
		}
		if (jqp.getType() != J2EEVersionConstants.APPLICATION_TYPE) {
			fail();
		}
		cachedEARModules = new ArrayList<ArchiveWrapper>();

		if (commonArchive != null) {
			EARFile ear = (EARFile) commonArchive;
			List earMods = ear.getModuleFiles();
			for (int i = 0; i < earMods.size(); i++) {
				cachedEARModules.add(new ArchiveWrapper((Archive) earMods.get(i)));
			}
			return cachedEARModules;
		}
		if (archive != null) {
			List<IArchiveResource> resources = archive.getArchiveResources();
			for (IArchiveResource resource : resources) {
				if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
					IPath path = resource.getPath();
					if (path.segmentCount() > 0) {
						String lastSegment = path.lastSegment();
						if (lastSegment.endsWith("jar") || lastSegment.endsWith("zip") || lastSegment.endsWith("rar") || lastSegment.endsWith("war")) {
							IArchive earmodule;
							try {
								earmodule = archive.getNestedArchive(resource);
								cachedEARModules.add(new ArchiveWrapper(earmodule));
							} catch (ArchiveOpenFailureException e) {
								Logger.getLogger().logError(e);
							}
						}
					}
				}
			}
			return cachedEARModules;
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

	public boolean isModule() {
		if (isApplicationClientFile()) {
			return true;
		}
		if (isWARFile()) {
			return true;
		}
		if (isEJBJarFile()) {
			return true;
		}
		if (isRARFile()) {
			return true;
		}
		return false;
	}

	public boolean isApplicationClientFile() {
		if (commonArchive != null)
			return commonArchive.isApplicationClientFile();
		if (archive != null)
			return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.APPLICATION_CLIENT_TYPE);
		fail();
		return false;
	}

	public boolean isWARFile() {
		if (commonArchive != null)
			return commonArchive.isWARFile();
		if (archive != null)
			return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.WEB_TYPE);
		fail();
		return false;
	}

	public boolean isEJBJarFile() {
		if (commonArchive != null)
			return commonArchive.isEJBJarFile();
		if (archive != null)
			return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.EJB_TYPE);
		fail();
		return false;
	}

	public boolean isEarFile() {
		if (commonArchive != null)
			return commonArchive.isEARFile();
		if (archive != null)
			return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.APPLICATION_TYPE);
		fail();
		return false;
	}

	public boolean isRARFile() {
		if (commonArchive != null)
			return commonArchive.isRARFile();
		if (archive != null)
			return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.CONNECTOR_TYPE);
		fail();
		return false;
	}

	// This is an array so we can tell the difference between initialized and null vs not initialized
	private String[] cachedWebContextRoot = null;

	public String getWebContextRoot() {
		if (cachedWebContextRoot != null) {
			return cachedWebContextRoot[0];
		}

		if (!isWARFile()) {
			fail();
			return null;
		}
		cachedWebContextRoot = new String[1];

		if (commonArchive != null) {
			cachedWebContextRoot[0] = ((WebModule) ((EARFile) commonArchive.getContainer()).getModule(commonArchive.getURI(), null)).getContextRoot();
			return cachedWebContextRoot[0];
		}
		if (archive != null) {
			IArchive earArchive = archive.getArchive();
			Application application;
			try {
				application = (Application) earArchive.getModelObject();
				String moduleName = archive.getPath().toString();
				Module module = (Module) application.getFirstModule(moduleName);
				cachedWebContextRoot[0] = module.getWeb().getContextRoot();				
			} catch (ArchiveModelLoadException e) {
				Logger.getLogger().logError(e);
			}
			return cachedWebContextRoot[0];
		}
		fail();
		return null;
	}

	// This is an array so we can tell the difference between initialized and null vs not initialized
	private ArchiveWrapper[] cachedEJBClientArchiveWrapper = null;

	public ArchiveWrapper getEJBClientArchiveWrapper(ArchiveWrapper ejbWrapper) {
		if (cachedEJBClientArchiveWrapper != null) {
			return cachedEJBClientArchiveWrapper[0];
		}

		if (!ejbWrapper.isEJBJarFile() || !isEarFile()) {
			fail();
			return null;
		}

		cachedEJBClientArchiveWrapper = new ArchiveWrapper[1];

		if (commonArchive != null) {
			try {
				EJBJar jar = ((EJBJarFile) ejbWrapper.getUnderLyingArchive()).getDeploymentDescriptor();
				if (jar != null) {
					if (jar.getEjbClientJar() != null) {
						String clientName = jar.getEjbClientJar();
						cachedEJBClientArchiveWrapper[0] = new ArchiveWrapper((Archive) ((EARFile) commonArchive).getFile(clientName));
					}
				}
				return cachedEJBClientArchiveWrapper[0];
			} catch (FileNotFoundException e) {
				Logger.getLogger().logError(e);
			}
		}
		if (archive != null) {
			try {
				org.eclipse.jst.javaee.ejb.EJBJar edd = (org.eclipse.jst.javaee.ejb.EJBJar) ((IArchive) ejbWrapper.getUnderLyingArchive()).getModelObject();
				String clientJar = edd.getEjbClientJar();
				if (null != clientJar) {
					IArchiveResource ar = archive.getArchiveResource(new Path(clientJar));
					if (ar.getType() == IArchiveResource.ARCHIVE_TYPE) {
						cachedEJBClientArchiveWrapper[0] = new ArchiveWrapper((IArchive) ar);
					} else {
						try {
							cachedEJBClientArchiveWrapper[0] = new ArchiveWrapper(archive.getNestedArchive(ar));
						} catch (ArchiveOpenFailureException e) {
							Logger.getLogger().logError(e);
						}
					}
				}
				return cachedEJBClientArchiveWrapper[0];
			} catch (FileNotFoundException e) {
				Logger.getLogger().logError(e);
			} catch (ArchiveModelLoadException e) {
				Logger.getLogger().logError(e);
			}
		}
		fail();
		return null;
	}

	private List<ArchiveWrapper> cachedEARUtilitiesAndWebLibs = null;

	public List<ArchiveWrapper> getEARUtilitiesAndWebLibs() {
		if (null != cachedEARUtilitiesAndWebLibs) {
			return cachedEARUtilitiesAndWebLibs;
		}

		if (!isEarFile()) {
			fail();
			return null;
		}

		cachedEARUtilitiesAndWebLibs = new ArrayList<ArchiveWrapper>();
		if (commonArchive != null) {
			List files = commonArchive.getFiles();
			for (int i = 0; i < files.size(); i++) {
				FileImpl file = (FileImpl) files.get(i);
				if (file.isArchive() && !file.isModuleFile() && file.getURI().endsWith(".jar")) { //$NON-NLS-1$
					cachedEARUtilitiesAndWebLibs.add(new ArchiveWrapper((Archive) file));
				}
				if (file.isWARFile()) {
					ArchiveWrapper wrapper = new ArchiveWrapper((Archive) file);
					cachedEARUtilitiesAndWebLibs.addAll(wrapper.getWebLibs());
				}
			}
			return cachedEARUtilitiesAndWebLibs;
		}
		if (archive != null) {
			List files = archive.getArchiveResources();
			for (int i = 0; i < files.size(); i++) {
				IArchiveResource file = (IArchiveResource) files.get(i);
				String lastSegment = file.getPath().lastSegment();
				if (lastSegment.endsWith(".jar") || lastSegment.endsWith(".rar") || lastSegment.endsWith(".war") || lastSegment.endsWith("zip")) {
					IArchive nestedArchive;
					try {
						nestedArchive = archive.getNestedArchive(file);
						ArchiveWrapper nestedWrapper = new ArchiveWrapper(nestedArchive);
						if (nestedWrapper.isWARFile()) {
							cachedEARUtilitiesAndWebLibs.addAll(nestedWrapper.getWebLibs());
						} else if (!nestedWrapper.isModule()) {
							cachedEARUtilitiesAndWebLibs.add(nestedWrapper);
						}
					} catch (ArchiveOpenFailureException e) {
						Logger.getLogger().logError(e);
					}
				}
			}
			return cachedEARUtilitiesAndWebLibs;

		}
		fail();
		return null;
	}
}