/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.EARProjectMap;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;

/**
* @deprecated
* Use
* <p>
* Need to use the ModuleCore and WorkbenchComponent Api to get to the referenced modules
* as with the Flexible project changes a .modulemaps file will not exist in an EAR module and
* all the info that was captured in .modulemaps file will is now captured in the .wtpmodules file
*/

public class ModuleMapHelper {

	/**
	 * Constructor for ModuleMapHelper.
	 */
	protected ModuleMapHelper() {
		super();
	}

	public static ModuleMapping getModuleMapping(Module module, EARProjectMap projectMap) {
		if (module == null)
			return null;
		List maps = getModuleMappings(projectMap);
		for (int i = 0; i < maps.size(); i++) {
			ModuleMapping map = (ModuleMapping) maps.get(i);
			if (module.equals(map.getModule()))
				return map;
		}
		return null;
	}

	public static List getModuleMappings(EARProjectMap map) {
		if (map == null)
			return java.util.Collections.EMPTY_LIST;
		return map.getMappings();
	}

	public static UtilityJARMapping getUtilityJARMapping(String uri, EARProjectMap projectMap) {
		if (uri == null || projectMap == null)
			return null;
		List maps = getUtilityJARMappings(projectMap);
		for (int i = 0; i < maps.size(); i++) {
			UtilityJARMapping map = (UtilityJARMapping) maps.get(i);
			if (uri.equals(map.getUri()))
				return map;
		}
		return null;
	}

	public static List getUtilityJARMappings(EARProjectMap map) {
		if (map == null)
			return java.util.Collections.EMPTY_LIST;
		return map.getUtilityJARMappings();
	}

	public static EARProjectMap getEARProjectMap(Resource res) {
		if (res == null || res.getContents().isEmpty())
			return null;
		return (EARProjectMap) res.getContents().get(0);
	}

	public static List getAllMappedJARs(EARFile ear) {
		if (ear == null)
			return Collections.EMPTY_LIST;
		Resource res = null;
		try {
			res = ear.getMofResource(EAREditModel.MODULE_MAP_URI);
		} catch (Exception ex) {
			Logger.getLogger().logError(ex);
			return Collections.EMPTY_LIST;
		}
		EARProjectMap map = getEARProjectMap(res);
		if (map == null)
			return Collections.EMPTY_LIST;

		List result = new ArrayList();
		List moduleMaps = getModuleMappings(map);
		for (int i = 0; i < moduleMaps.size(); i++) {
			ModuleMapping mMap = (ModuleMapping) moduleMaps.get(i);
			Module m = mMap.getModule();
			if (m == null)
				continue;
			addArchiveIfNotNull(result, m.getUri(), ear);
		}
		List utilMaps = getUtilityJARMappings(map);
		for (int i = 0; i < utilMaps.size(); i++) {
			UtilityJARMapping uMap = (UtilityJARMapping) utilMaps.get(i);
			addArchiveIfNotNull(result, uMap.getUri(), ear);
		}
		return result;
	}

	protected static void addArchiveIfNotNull(List result, String uri, EARFile ear) {
		Archive anArchive = getArchive(uri, ear);
		if (anArchive != null)
			result.add(anArchive);
	}

	protected static Archive getArchive(String uri, EARFile ear) {
		if (uri == null || uri.trim().length() == 0)
			return null;
		try {
			return (Archive) ear.getFile(uri);
		} catch (FileNotFoundException ex) {
			Logger.getLogger().logError(ex);
		} catch (ClassCastException ex2) {
			Logger.getLogger().logError(ex2);
		}
		return null;
	}

	public static IProject getProject(String uri, EARFile ear) {
		if (uri == null || uri.length() == 0 || ear == null)
			return null;
		Resource res = null;
		try {
			res = ear.getMofResource(EAREditModel.MODULE_MAP_URI);
		} catch (Exception ex) {
			return null;
		}
		EARProjectMap map = getEARProjectMap(res);
		if (map == null)
			return null;

		String projectName = null;
		UtilityJARMapping uMap = getUtilityJARMapping(uri, map);
		if (uMap != null)
			projectName = uMap.getProjectName();
		else {
			Module m = ear.getDeploymentDescriptor().getFirstModule(uri);
			if (m != null) {
				ModuleMapping mMap = getModuleMapping(m, map);
				if (mMap != null)
					projectName = mMap.getProjectName();
			}
		}
		if (projectName != null && projectName.trim().length() > 0)
			return ResourcesPlugin.getWorkspace().getRoot().getProject(projectName.trim());
		return null;
	}



}