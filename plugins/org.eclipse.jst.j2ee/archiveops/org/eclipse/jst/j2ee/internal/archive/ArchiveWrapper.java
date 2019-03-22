/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.EARArchiveOpsResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;

public class ArchiveWrapper {

	private IArchive archive = null;
	private JavaEEQuickPeek jqp = null;

	public ArchiveWrapper(IArchive archive) {
		this.archive = archive;
		if(archive == null){
			throw new NullPointerException();
		}
		JavaEEArchiveUtilities jea = JavaEEArchiveUtilities.INSTANCE;
		jqp = jea.getJavaEEQuickPeek(archive);
	}

	public JavaEEQuickPeek getJavaEEQuickPeek() {
		return jqp;
	}

	public Object getUnderLyingArchive() {
		return archive;
	}

	private ArchiveWrapper cachedParent = null;

	public ArchiveWrapper getParent() {
		if (null != cachedParent) {
			return cachedParent;
		}
		cachedParent = new ArchiveWrapper(archive.getArchive());
		return cachedParent;
	}

	public IArchive getIArchive() {
		return archive;
	}

	public void close() {
		try {
			if (archive.isOpen()) {
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archive);
			}
		} catch (RuntimeException e) {
			J2EEPlugin.logError(e);
			throw e;
		}
	}

	public IPath getPath() {
		return archive.getPath();
	}

	private void fail() {
		throw new RuntimeException("ArchiveWrapper is inconsistent."); //$NON-NLS-1$
	}

	public int getSize() {
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

		List<IArchiveResource> resources = archive.getArchiveResources();
		for (IArchiveResource resource : resources) {
			if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
				IPath path = resource.getPath();
				if (path.segmentCount() > 2) {
					if (path.segment(0).equals("WEB-INF") && path.segment(1).equals("lib")) { //$NON-NLS-1$ //$NON-NLS-2$
						String lastSegment = path.lastSegment();
						if (lastSegment.endsWith("jar") || lastSegment.endsWith("zip")) { //$NON-NLS-1$ //$NON-NLS-2$
							IArchive webLib;
							try {
								webLib = archive.getNestedArchive(resource);
								cachedWebLibs.add(new ArchiveWrapper(webLib));
							} catch (ArchiveOpenFailureException e) {
								J2EEPlugin.logError(e);
							}
						}
					}
				}
			}
		}
		return cachedWebLibs;
	}

	private List <String>cachedDDMappedModuleURIs = null;
	
	public List<String> getDDMappedModuleURIs() {
		if(cachedDDMappedModuleURIs != null){
			return cachedDDMappedModuleURIs;
		}
		if (jqp.getType() != J2EEVersionConstants.APPLICATION_TYPE) {
			fail();
		}
		
		cachedDDMappedModuleURIs = new ArrayList<String>();
		
		if(archive.containsArchiveResource(new Path(J2EEConstants.APPLICATION_DD_URI))){
			if(jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_5_0_ID ||
					jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_6_0_ID ||
					jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_7_0_ID || 
					jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_8_0_ID){
				try {
					Application application = (Application) archive.getModelObject();
					List modules = application.getModules();
					for(int i=0;i<modules.size();i++){
						Module module = (Module)modules.get(i);
						String uri = module.getUri();
						cachedDDMappedModuleURIs.add(uri);
					}
				} catch (ArchiveModelLoadException e) {
					J2EEPlugin.logError(e);
				}
			} else { 
				try{
					org.eclipse.jst.j2ee.application.Application application = (org.eclipse.jst.j2ee.application.Application)archive.getModelObject();
					List modules = application.getModules();
					for(int i=0;i<modules.size();i++){
						org.eclipse.jst.j2ee.application.Module module = (org.eclipse.jst.j2ee.application.Module)modules.get(i);
						String uri = module.getUri();
						cachedDDMappedModuleURIs.add(uri);
					}
				}catch (ArchiveModelLoadException e) {
					J2EEPlugin.logError(e);
				}
			}
		}
		return cachedDDMappedModuleURIs;
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
		if(jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_5_0_ID ||
				jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_6_0_ID ||
				jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_7_0_ID ||
				jqp.getJavaEEVersion() == JavaEEQuickPeek.JEE_8_0_ID){
			List<IArchiveResource> resources = archive.getArchiveResources();
			for (IArchiveResource resource : resources) {
				if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
					IPath path = resource.getPath();
					if (path.segmentCount() > 0) {
						String lastSegment = path.lastSegment();
						if (lastSegment.endsWith("jar") || lastSegment.endsWith("zip") || lastSegment.endsWith("rar") || lastSegment.endsWith("war")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
							IArchive earmodule;
							try {
								earmodule = archive.getNestedArchive(resource);
								cachedEARModules.add(new ArchiveWrapper(earmodule));
							} catch (ArchiveOpenFailureException e) {
								J2EEPlugin.logError(e);
							}
						}
					}
				}
			}
		} else {
			try {
				org.eclipse.jst.j2ee.application.Application app = (org.eclipse.jst.j2ee.application.Application)archive.getModelObject();
				List modules = app.getModules();
				for(int i=0;i<modules.size();i++){
					org.eclipse.jst.j2ee.application.Module mod = (org.eclipse.jst.j2ee.application.Module)modules.get(i);
					String uri = mod.getUri();
					IPath path = new Path(uri);
					if(archive.containsArchiveResource(path)){
						try {
							IArchiveResource resource = archive.getArchiveResource(path);
							IArchive earmodule;
							earmodule = archive.getNestedArchive(resource);
							cachedEARModules.add(new ArchiveWrapper(earmodule));
						} catch (ArchiveOpenFailureException e) {
							J2EEPlugin.logError(e);
						} catch (FileNotFoundException e) {
							org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
						}
					}
				}
			} catch (ArchiveModelLoadException e) {
				org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
			}
		}
		return cachedEARModules;
	}

	public String getName() {
		return archive.getPath().lastSegment();
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
		return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.APPLICATION_CLIENT_TYPE);
	}

	public boolean isWARFile() {
		return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.WEB_TYPE);
	}

	public boolean isEJBJarFile() {
		return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.EJB_TYPE);
	}

	public boolean isEarFile() {
		return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.APPLICATION_TYPE);
	}

	public boolean isRARFile() {
		return (JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(archive).getType() == J2EEConstants.CONNECTOR_TYPE);
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

		IArchive earArchive = archive.getArchive();
		if(earArchive.containsArchiveResource(new Path(J2EEConstants.APPLICATION_DD_URI))){
			JavaEEQuickPeek earJQP = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(earArchive);
			if(earJQP.getJavaEEVersion() == JavaEEQuickPeek.JEE_5_0_ID ||
					earJQP.getJavaEEVersion() == JavaEEQuickPeek.JEE_6_0_ID || 
					earJQP.getJavaEEVersion() == JavaEEQuickPeek.JEE_7_0_ID ||
					earJQP.getJavaEEVersion() == JavaEEQuickPeek.JEE_8_0_ID){
				try {
					Application application = (Application) earArchive.getModelObject();
					String moduleName = archive.getPath().toString();
					Module module = application.getFirstModule(moduleName);
					if(module != null){
						cachedWebContextRoot[0] = module.getWeb().getContextRoot();
					} else {
						cachedWebContextRoot[0] = getDefaultContextRoot();
					}
				} catch (ArchiveModelLoadException e) {
					J2EEPlugin.logError(e);
				}
			} else {
				try{
					org.eclipse.jst.j2ee.application.Application application = (org.eclipse.jst.j2ee.application.Application)earArchive.getModelObject();
					String moduleName = archive.getPath().toString();
					org.eclipse.jst.j2ee.application.WebModule module = (org.eclipse.jst.j2ee.application.WebModule)application.getFirstModule(moduleName);
					if(module == null){
						cachedWebContextRoot[0] = null;
					} else {
						cachedWebContextRoot[0] = module.getContextRoot();
					}
				} catch (ArchiveModelLoadException e) {
					J2EEPlugin.logError(e);
				}
			}
		} else {
			cachedWebContextRoot[0] = getDefaultContextRoot();
		}
		return cachedWebContextRoot[0];
	}

	private String getDefaultContextRoot() {
		//J2EE spec 8.3.1.3.c (pg 149)
		String contextRoot = archive.getPath().toString();
		contextRoot = contextRoot.substring(0, contextRoot.lastIndexOf('.'));
		return contextRoot;
	}

	// This is an array so we can tell the difference between initialized and null vs not initialized
	private HashMap<ArchiveWrapper, ArchiveWrapper> cachedEJBClientArchiveWrapper = null;

	public ArchiveWrapper getEJBClientArchiveWrapper(ArchiveWrapper ejbWrapper) {
		if (cachedEJBClientArchiveWrapper == null) {
			cachedEJBClientArchiveWrapper = new HashMap<ArchiveWrapper, ArchiveWrapper>();
		}
		
		if(cachedEJBClientArchiveWrapper.containsKey(ejbWrapper)){
			return cachedEJBClientArchiveWrapper.get(ejbWrapper);
		}
		

		if (!ejbWrapper.isEJBJarFile() || !isEarFile()) {
			fail();
			return null;
		}

		ArchiveWrapper ejbClientArchiveWrapper = null;
		try {
			JavaEEQuickPeek jqp = JavaEEArchiveUtilities.INSTANCE.getJavaEEQuickPeek(ejbWrapper.archive);
			String clientJarName = null;
			if(jqp.getVersion() == JavaEEQuickPeek.EJB_3_0_ID || jqp.getVersion() == JavaEEQuickPeek.EJB_3_1_ID || jqp.getVersion() == JavaEEQuickPeek.EJB_3_2_ID){
				if(ejbWrapper.archive.containsArchiveResource(new Path(J2EEConstants.EJBJAR_DD_URI))){
					org.eclipse.jst.javaee.ejb.EJBJar edd = (org.eclipse.jst.javaee.ejb.EJBJar) ejbWrapper.archive.getModelObject();
					clientJarName = edd.getEjbClientJar();
				}
			} else {
				EJBJar jar = (EJBJar)ejbWrapper.archive.getModelObject();
				if (jar != null) {
					clientJarName = jar.getEjbClientJar();
				}
			}
			
			if (null != clientJarName) {
				IPath clientJarPath = new Path(clientJarName);
				if(archive.containsArchiveResource(clientJarPath)){
					IArchiveResource clientJar = archive.getArchiveResource(clientJarPath);
					if(null != clientJar){
						if (clientJar.getType() == IArchiveResource.ARCHIVE_TYPE) {
							ejbClientArchiveWrapper = new ArchiveWrapper((IArchive) clientJar);
						} else {
							try {
								ejbClientArchiveWrapper = new ArchiveWrapper(archive.getNestedArchive(clientJar));
							} catch (ArchiveOpenFailureException e) {
								J2EEPlugin.logError(e);
							}
						}
					}
				} else {
					J2EEPlugin.logWarning(EARArchiveOpsResourceHandler.bind(EARArchiveOpsResourceHandler.MISSING_CLIENT_JAR, new Object[] {clientJarName, ejbWrapper.getName(), archive.getPath()}));
				}
			}
		} catch (FileNotFoundException e) {
			J2EEPlugin.logError(e);
		} catch (ArchiveModelLoadException e) {
			J2EEPlugin.logError(e);
		} finally {
			cachedEJBClientArchiveWrapper.put(ejbWrapper, ejbClientArchiveWrapper);
		}
		return ejbClientArchiveWrapper;
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
		List files = archive.getArchiveResources();
		for (int i = 0; i < files.size(); i++) {
			IArchiveResource file = (IArchiveResource) files.get(i);
			String lastSegment = file.getPath().lastSegment();
			if (lastSegment.endsWith(IJ2EEModuleConstants.JAR_EXT) || lastSegment.endsWith(IJ2EEModuleConstants.RAR_EXT) || lastSegment.endsWith(IJ2EEModuleConstants.WAR_EXT) || lastSegment.endsWith("zip")) { //$NON-NLS-1$
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
					J2EEPlugin.logError(e);
				}
			}
		}
		return cachedEARUtilitiesAndWebLibs;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		if(this == otherObject){
			return true;
		}
		if(null == otherObject || !(otherObject instanceof ArchiveWrapper)){
			return false;
		}
		ArchiveWrapper other = (ArchiveWrapper)otherObject;
		
		if(archive != null){
			return archive.equals(other.archive);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		if(archive != null){
			return archive.hashCode();
		}
		return super.hashCode();
	}
	
}