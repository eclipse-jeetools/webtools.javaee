/*******************************************************************************
 * Copyright (c) 2005,2006 IBM Corporation
 * Licensed Material - Property of IBM. All rights reserved. 
 * US Government Users Restricted Rights - Use, duplication or disclosure  v1.0
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.core.internal.bindings;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jem.util.RegistryReader;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.core.internal.plugin.J2EECorePlugin;

/**
 * This class is not intended to be subclasses
 * 
 */
public class JNDIBindingsHelperManager {

	private List allHelpers;
	private IJNDIBindingsHelper defaultHelper;
	private static final int TYPE_PROJECT = 0;
	private static final int TYPE_ARCHIVE = 1;

	private static JNDIBindingsHelperManager instance = null;

	/**
	 * Returns the singleton instance.
	 * 
	 * @return the singleton instance.
	 */
	public static JNDIBindingsHelperManager getInstance() {
		if (instance == null) {
			instance = new JNDIBindingsHelperManager();
		}
		return instance;
	}

	/**
	 * Returns an array of bindings helpers applicable for the specified project. If no registered
	 * bindings helpers apply, the returned array will contain a default bindings helper.
	 * 
	 * @param project
	 * @return
	 */
	public IJNDIBindingsHelper[] getBindingsHelpers(IProject project) {
		return getBindingsHelpers(TYPE_PROJECT, project);
	}

	/**
	 * Returns an array of bindings helpers applicable for the specified archive. If no registered
	 * bindings helpers apply, the returned array will contain a default bindings helper.
	 * 
	 * @param project
	 * @return
	 */
	public IJNDIBindingsHelper[] getBindingsHelpers(Archive archive) {
		return getBindingsHelpers(TYPE_ARCHIVE, archive);
	}

	private IJNDIBindingsHelper[] getBindingsHelpers(int type, Object obj) {
		List helpers = getHelpers();
		List appliesHelpers = new ArrayList();
		IJNDIBindingsHelper helper = null;
		for (int i = 0; i < helpers.size(); i++) {
			helper = (IJNDIBindingsHelper) helpers.get(i);
			if ((TYPE_PROJECT == type && helper.appliesFor((IProject) obj)) || (TYPE_ARCHIVE == type && helper.appliesFor((Archive) obj))) {
				appliesHelpers.add(helper);
			}
		}
		if (appliesHelpers.size() == 0) {
			appliesHelpers.add(getDefaultHelper());
		}

		IJNDIBindingsHelper[] helpersArray = new IJNDIBindingsHelper[appliesHelpers.size()];
		for (int i = 0; i < helpersArray.length; i++) {
			helpersArray[i] = (IJNDIBindingsHelper) appliesHelpers.get(i);
		}

		return helpersArray;
	}

	private IJNDIBindingsHelper getDefaultHelper() {
		if (null == defaultHelper) {
			defaultHelper = new AbstractJNDIBindingsHelper() {
				public boolean appliesFor(IProject project) {
					return true;
				}

				public boolean appliesFor(Archive archive) {
					return true;
				}
			};
		}
		return defaultHelper;
	}

	private List getHelpers() {
		if (null == allHelpers) {
			BindingsHelperRegistry registry = new BindingsHelperRegistry();
			registry.readRegistry();
			allHelpers = registry.getHelpers();
		}
		return allHelpers;
	}


	protected String TAG_J2EE_BINDINGS_HELPER = "jndiBindingsHelpers"; //$NON-NLS-1$

	private class BindingsHelperRegistry extends RegistryReader {

		private String ATT_CLASS = "class"; //$NON-NLS-1$
		private String TAG_BINDINGS_HELPER = "jndiBindingsHelper"; //$NON-NLS-1$

		private List helpers = new ArrayList();

		private BindingsHelperRegistry() {
			super(J2EECorePlugin.PLUGIN_ID, TAG_J2EE_BINDINGS_HELPER);
		}

		public boolean readElement(IConfigurationElement element) {
			if (TAG_BINDINGS_HELPER.equals(element.getName())) {
				try {
					addHelper((IJNDIBindingsHelper) element.createExecutableExtension(ATT_CLASS));
				} catch (CoreException e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}
			return false;
		}

		public List getHelpers() {
			return helpers;
		}

		private void addHelper(IJNDIBindingsHelper helper) {
			helpers.add(helper);
		}
	}
}