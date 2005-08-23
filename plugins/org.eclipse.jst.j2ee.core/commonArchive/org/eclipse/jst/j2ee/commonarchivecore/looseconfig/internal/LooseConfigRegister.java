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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveRuntimeException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.wst.common.internal.emf.utilities.StringUtil;


/**
 * Used internally for loose module support
 */
public class LooseConfigRegister {

	/**
	 * The system property used to specify the absolute path to the loose config document;
	 * "was.loose.config"
	 */
	public static final String LOOSE_CONFIG_PROPERTY = "was.loose.config"; //$NON-NLS-1$

	protected static LooseConfigRegister singleton;

	protected LooseConfiguration looseConfiguration;

	protected ResourceSet resourceSet;

	/** Have we previously loaded or tried to load the loose configuration? */
	protected boolean loadAttempted = false;

	private Map looseEarMap;

	/**
	 * Constructor for LooseConfigRegister.
	 */
	protected LooseConfigRegister() {
		super();
		initialize();
	}

	public static LooseConfigRegister singleton() {
		if (singleton == null)
			singleton = new LooseConfigRegister();

		return singleton;
	}

	/**
	 * Searches for the ear file in the fluffed LooseConfiguration object, and attempts to load the
	 * LooseApplication on demand from the existing mappings if it is not already loaded.
	 * 
	 * @param earFileURI
	 * @return
	 */
	public LooseApplication findLooseApplication(String earFileURI) {
		// System.out.println(getClass().getName() + ".findLooseApplication(String
		// earFileURI=\""+earFileURI+"\")");
		if (getLooseConfiguration() != null) {
			List apps = getLooseConfiguration().getApplications();
			for (int i = 0; i < apps.size(); i++) {
				LooseApplication app = (LooseApplication) apps.get(i);
				// System.out.println(getClass().getName() + ".findLooseApplication():
				// app["+i+"].getURI()=\""+app.getUri()+"\"" );
				if (app.getUri().equals(earFileURI))
					return app;
			}
		}
		// System.out.println(getClass().getName() + ".findLooseApplication(): did not find loaded
		// LooseApplication, loading new value." );
		if (getDocumentURI() == null || getDocumentURI().trim().length() == 0) {
			URI looseConfig = (URI) getLooseEarMap().get(earFileURI);
			// System.out.println(getClass().getName() + ".findLooseApplication(): looseConfig URI
			// from map \""+looseConfig+"\"");
			LooseApplication application = loadLooseApplication(looseConfig);
			// System.out.println(getClass().getName() + ".findLooseApplication():
			// looseApplication="+application);
			if (application != null) {
				List modules = application.getLooseArchives();
				// System.out.println(getClass().getName() + ".findLooseApplication():
				// looseApplication contains " + modules.size() + " modules.");
				for (int i = 0; i < modules.size(); i++)
					// System.out.println(getClass().getName() + ".findLooseApplication():
					// modules.get("+i+"):"+ modules.get(i));
					getLooseConfiguration().getApplications().add(application);
				return application;
			}
		}
		return null;
	}

	/**
	 * @param earFileURI
	 *            The containing EAR -- the absolute file system URI will be determined
	 *            automatically
	 * @param looseApplicationResource
	 *            The absolute file system URI of a document containing a LooseApplication as its
	 *            root
	 */
	public void addLooseMapping(Container container, String looseApplicationResource) {
		if (container != null)
			addLooseMapping(ArchiveUtil.getOSUri(container.getURI()), looseApplicationResource);
	}

	/**
	 * @param earFileURI
	 *            The absolute file system URI of the EAR file of the given mapping
	 * @param looseApplicationResource
	 *            The absolute file system URI of a document containing a LooseApplication as its
	 *            root
	 */
	public void addLooseMapping(String earFileURI, String looseApplicationResource) {
		if (earFileURI != null) {
			// System.out.println(getClass().getName() + ".addLooseMapping(String
			// earFileURI=\""+earFileURI+"\", String
			// looseApplicationResource=\""+looseApplicationResource+"\")");
			if (getLooseEarMap().containsKey(earFileURI)) {
				// System.out.println(getClass().getName() + ".addLooseMapping(): Found existing key
				// with same earFileURI, looking for loaded app.");
				LooseApplication app = findLooseApplicationIfLoaded(earFileURI);
				// System.out.println(getClass().getName() + ".addLooseMapping(): Loaded app=" +
				// app);
				if (app != null) {
					Resource res = loadLooseApplicationResource(URI.createURI(looseApplicationResource));
					// System.out.println(getClass().getName() + ".addLooseMapping(): clearing
					// resource:" + res);
					res.unload();
					removeLooseApplication(app);
				}
			}
			getLooseEarMap().put(earFileURI, URI.createURI(looseApplicationResource));
		}
	}

	/**
	 * @param earFileURI
	 * @return
	 */
	public LooseApplication findLooseApplicationIfLoaded(String earFileURI) {
		// System.out.println(getClass().getName() + ".findLooseApplicationIfLoaded(String
		// earFileURI=\""+earFileURI+"\")");
		if (getLooseConfiguration() != null) {
			List apps = getLooseConfiguration().getApplications();
			for (int i = 0; i < apps.size(); i++) {
				LooseApplication app = (LooseApplication) apps.get(i);
				// System.out.println(getClass().getName() + ".findLooseApplicationIfLoaded():
				// app["+i+"].getURI()=\""+app.getUri()+"\"" );
				if (app.getUri().equals(earFileURI))
					return app;
			}
		}
		return null;
	}

	/**
	 * Allows adding LooseApplications dynmically.
	 * 
	 * @param application
	 *            An application created and persisted by an external entity
	 */
	public void addLooseApplication(LooseApplication application) {
		getLooseConfiguration().getApplications().add(application);
	}

	/**
	 * Allows removing LooseApplications dynmically.
	 * 
	 * @param application
	 *            An application created and persisted by an external entity
	 */
	public void removeLooseApplication(LooseApplication application) {
		// System.out.println(getClass().getName() + ".removeLooseApplication(LooseApplication
		// application=\""+application+"\")");
		getLooseConfiguration().getApplications().remove(application);
	}

	/**
	 * @param earFileURI
	 *            The absolute file system URI of the EAR file of the given mapping
	 */
	public void removeLooseMapping(String earFileURI) {
		// System.out.println(getClass().getName() + ".removeLooseMapping(String
		// earFileURI=\""+earFileURI+"\")");
		if (earFileURI != null)
			getLooseEarMap().remove(earFileURI);
	}

	public LooseModule findLooseModule(LooseApplication app, String uri, String altDD) {
		List archives = app.getLooseArchives();
		for (int i = 0; i < archives.size(); i++) {
			LooseArchive element = (LooseArchive) archives.get(i);
			if (!element.isModule())
				continue;

			if (StringUtil.stringsEqual(uri, element.getUri()) && StringUtil.stringsEqual(altDD, ((LooseModule) element).getAltDD()))
				return (LooseModule) element;
		}
		return null;
	}

	public LooseModule findLooseModule(Module m, EARFile ear) {
		LooseApplication app = findLooseApplication(ear.getURI());
		return app == null ? null : findLooseModule(app, m.getUri(), m.getAltDD());
	}

	/**
	 * Clears the loose configuration; the next time it is requested it will be reloaded;
	 */
	public void flush() {
		loadAttempted = false;
		looseConfiguration = null;
		resourceSet = new ResourceSetImpl();
	}

	protected void initialize() {
		resourceSet = new ResourceSetImpl();
	}

	protected void loadLooseConfiguration() {
		Resource res = loadLooseConfigurationResource();
		if (res != null && !res.getContents().isEmpty())
			setLooseConfiguration((LooseConfiguration) res.getContents().get(0));
		else
			/*
			 * if the document is empty then fluff up a LooseConfiguration object
			 */
			setLooseConfiguration(LooseconfigFactory.eINSTANCE.createLooseConfiguration());
		loadAttempted = true;
	}

	protected Resource loadLooseConfigurationResource() {
		String uri = getDocumentURI();
		if (ArchiveUtil.isNullOrEmpty(uri))
			return null;
		try {
			return resourceSet.getResource(URI.createURI(uri), true);
		} catch (Exception ex) {
			throw new ArchiveRuntimeException("Exception occurred loading loose configuration", ex); //$NON-NLS-1$
		}
	}

	protected LooseApplication loadLooseApplication(URI looseAppURI) {
		Resource res = loadLooseApplicationResource(looseAppURI);
		if (res != null && !res.getContents().isEmpty())
			return (LooseApplication) res.getContents().get(0);
		return null;
	}

	protected Resource loadLooseApplicationResource(URI looseConfigURI) {
		if (looseConfigURI == null)
			return null;
		try {
			return resourceSet.getResource(looseConfigURI, true);
		} catch (Exception ex) {
			throw new ArchiveRuntimeException("Exception occurred loading loose application", ex); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the looseConfiguration. Basic accessor with no initialization
	 * 
	 * @return Returns a LooseConfiguration
	 */
	public LooseConfiguration primGetLooseConfiguration() {
		return looseConfiguration;
	}

	/** The value of the loose config property */
	protected String getDocumentURI() {
		return System.getProperty(LOOSE_CONFIG_PROPERTY);
	}

	/**
	 * Gets the looseConfiguration. Tries to load it using the system property was.loose.config, if
	 * necessary.
	 * 
	 * @return Returns a LooseConfiguration or null
	 */
	public LooseConfiguration getLooseConfiguration() {
		if (looseConfiguration == null && !loadAttempted)
			loadLooseConfiguration();
		return looseConfiguration;
	}

	/**
	 * Assumption: the parameter must be a type that can have children, e.g., LooseWAR or
	 * LooseApplication
	 */
	public List getLooseChildren(LooseArchive loose) {
		if (loose != null) {
			if (loose.isEAR())
				return ((LooseApplication) loose).getLooseArchives();
			else if (loose.isWAR())
				return ((LooseWARFile) loose).getLooseLibs();
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Assumption: the parameter must be a type that can have children, e.g., LooseWAR or
	 * LooseApplication
	 */
	public LooseArchive findFirstLooseChild(String uri, LooseArchive loose) {
		List children = getLooseChildren(loose);
		for (int i = 0; i < children.size(); i++) {
			LooseArchive child = (LooseArchive) children.get(i);
			if (StringUtil.stringsEqual(uri, child.getUri()))
				return loose;
		}
		return null;
	}

	/**
	 * Sets the looseConfiguration.
	 * 
	 * @param looseConfiguration
	 *            The looseConfiguration to set
	 */
	public void setLooseConfiguration(LooseConfiguration config) {
		looseConfiguration = config;
	}

	/**
	 * Have we previously loaded or tried to load the loose configuration?
	 * 
	 * @return Returns a boolean
	 */
	public boolean getLoadAttempted() {
		return loadAttempted;
	}

	/**
	 * @return Returns the looseEarMap.
	 */
	public Map getLooseEarMap() {
		if (looseEarMap == null)
			looseEarMap = new HashMap();
		return looseEarMap;
	}
}
