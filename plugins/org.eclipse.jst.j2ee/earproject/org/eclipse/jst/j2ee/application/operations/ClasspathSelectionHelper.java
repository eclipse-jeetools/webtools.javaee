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
 * Created on Nov 14, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.NullLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;

import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ClasspathSelectionHelper {

	public static ClassPathSelection createClasspathSelection(IProject moduleProject, String moduleExtension, IProject earProject, EClass moduleType) {
		//		if(true){
		//			return null;
		//		}

		if (earProject == null || !earProject.exists()) {
			return null;
		}
		EARFile earFile = null;
		try {
			EARNatureRuntime earNature = (EARNatureRuntime) earProject.getNature(IEARNatureConstants.NATURE_ID);
			earFile = earNature.asEARFile(true, false);

			CommonarchiveFactory factory = getCommonArchiveFactory();
			CommonarchivePackage pkg = getCommonArchiveFactory().getCommonarchivePackage();
			ModuleFile bogusModuleFile = null;
			if (moduleType == pkg.getEJBJarFile())
				bogusModuleFile = factory.createEJBJarFile();
			else if (moduleType == pkg.getApplicationClientFile())
				bogusModuleFile = factory.createApplicationClientFile();
			else if (moduleType == pkg.getWARFile())
				bogusModuleFile = factory.createWARFile();
			else if (moduleType == pkg.getRARFile())
				bogusModuleFile = factory.createRARFile();

			bogusModuleFile.setURI(getUniqueUriInEAR(moduleProject, moduleExtension, earNature));
			bogusModuleFile.setManifest((ArchiveManifest) new ArchiveManifestImpl());
			bogusModuleFile.setLoadStrategy(new NullLoadStrategyImpl());
			earFile.getFiles().add(bogusModuleFile);

			return new ClassPathSelection(bogusModuleFile, moduleProject.getName(), earFile);
		} catch (CoreException e) {
			Logger.getLogger().logError(e);
		} catch (OpenFailureException e) {
			Logger.getLogger().logError(e);
		} finally {
			// The client of the ClasspathSelection has the responsibility of closing
			// the EARFile
			//		    if(earFile != null)
			//		        earFile.close();
		}
		return null;

	}

	protected static CommonarchiveFactory getCommonArchiveFactory() {
		return ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
	}

	protected static String getUniqueUriInEAR(IProject moduleProject, String moduleExtension, EARNatureRuntime earNature) {
		String base = moduleProject.getName().replace(' ', '_');
		String result = base + moduleExtension;
		EAREditModel earEditModel = null;
		Object key = new Object();
		try {
			if (earNature == null)
				return result;
			earEditModel = earNature.getEarEditModelForRead(key);
			Application app = earEditModel.getApplication();
			if (app == null)
				return result;
			int counter = 1;
			while (app.getFirstModule(result) != null)
				result = base + (counter++) + moduleExtension;
		} finally {
			if (null != earEditModel) {
				earEditModel.releaseAccess(key);
			}
			earEditModel = null;
		}
		return result;
	}

}