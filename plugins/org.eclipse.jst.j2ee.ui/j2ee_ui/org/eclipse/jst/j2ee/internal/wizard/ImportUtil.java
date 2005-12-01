/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.wizard;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.frameworks.internal.WTPPlugin;



/**
 * @author Sachin
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class ImportUtil {

	public static final int UNKNOWN = 0;
	public static final int EARFILE = 1;
	public static final int EJBJARFILE = 2;
	public static final int WARFILE = 3;
	public static final int CLIENTJARFILE = 4;
	public static final int RARFILE = 5;
	public static final int IMPORTCLASSTYPE = 6;
	public static final int J2EE14 = 256;
	public static final int J2EE13 = 128;
	public static final int J2EE12 = 64;
	public static final int J2EESpec = J2EE12 + J2EE13 + J2EE14;
	public static final String EAR = "EAR"; //$NON-NLS-1$
	public static final String EJB = "EJB"; //$NON-NLS-1$
	public static final String WAR = "WEB"; //$NON-NLS-1$
	public static final String JAR = "CLIENT"; //$NON-NLS-1$
	public static final String RAR = "RAR"; //$NON-NLS-1$
	public static final String[] SUFFIXES = {"", EAR, EJB, WAR, JAR, RAR, ""}; //$NON-NLS-1$ //$NON-NLS-2$

	public static int getFileType(String fileName) {
		Archive anArchive = null;
		try {
			try {
				anArchive = CommonarchiveFactoryImpl.getActiveFactory().openArchive(fileName);
				int archiveType = getArchiveType(anArchive);
				if (archiveType == UNKNOWN && isImportClassType(fileName))
					return IMPORTCLASSTYPE;
				return archiveType;
			} catch (Exception e) {
				if (isImportClassType(fileName))
					return IMPORTCLASSTYPE;
				return UNKNOWN;
			}
		} finally {
			if (anArchive != null && anArchive.isOpen())
				anArchive.close();
		}
	}

	//TODO: use new getJ2EEVerions switch statements
	public static int getVersionedFileType(String fileName) {
		Archive anArchive = null;
		try {
			int archiveType = UNKNOWN;
			try {
				anArchive = CommonarchiveFactoryImpl.getActiveFactory().openArchive(fileName);
				try {
					if (anArchive.isEJBJarFile()) {
						archiveType = EJBJARFILE;
						EJBJar ejbJar = ((EJBJarFile) anArchive).getDeploymentDescriptor();
						if (ejbJar.getVersionID() == J2EEVersionConstants.EJB_1_1_ID)
							archiveType |= J2EE12;
						else if (ejbJar.getVersionID() == J2EEVersionConstants.EJB_2_0_ID)
							archiveType |= J2EE13;
						else if (ejbJar.getVersionID() == J2EEVersionConstants.EJB_2_1_ID)
							archiveType |= J2EE14;
					} else if (anArchive.isWARFile()) {
						archiveType = WARFILE;
						WebApp war = ((WARFile) anArchive).getDeploymentDescriptor();
						if (war.getVersionID() == J2EEVersionConstants.WEB_2_2_ID)
							archiveType |= J2EE12;
						else if (war.getVersionID() == J2EEVersionConstants.WEB_2_3_ID)
							archiveType |= J2EE13;
						else if (war.getVersionID() == J2EEVersionConstants.WEB_2_4_ID)
							archiveType |= J2EE14;
					} else if (anArchive.isApplicationClientFile()) {
						archiveType = CLIENTJARFILE;
						ApplicationClient appClient = ((ApplicationClientFile) anArchive).getDeploymentDescriptor();
						if (appClient.getVersionID() == J2EEVersionConstants.J2EE_1_2_ID)
							archiveType |= J2EE12;
						else if (appClient.getVersionID() == J2EEVersionConstants.J2EE_1_3_ID)
							archiveType |= J2EE13;
						else if (appClient.getVersionID() == J2EEVersionConstants.J2EE_1_4_ID)
							archiveType |= J2EE14;
					} else if (anArchive.isRARFile()) {
						archiveType = RARFILE | J2EE13;
					} else if (anArchive.isEARFile()) {
						archiveType = EARFILE;
						Application app = ((EARFile) anArchive).getDeploymentDescriptor();
						if (app.getVersionID() == J2EEVersionConstants.J2EE_1_2_ID)
							archiveType |= J2EE12;
						else if (app.getVersionID() == J2EEVersionConstants.J2EE_1_3_ID)
							archiveType |= J2EE13;
						else if (app.getVersionID() == J2EEVersionConstants.J2EE_1_4_ID)
							archiveType |= J2EE14;
					}
				} catch (Exception e) {
					//Ignore
				}

			} catch (Exception e) {
				//Ignore
			}
			if (archiveType == UNKNOWN && isImportClassType(fileName))
				archiveType = IMPORTCLASSTYPE;
			return archiveType;
		} finally {
			if (anArchive != null && anArchive.isOpen())
				anArchive.close();
		}
	}

	public static int getArchiveType(Archive anArchive) {
		int type = UNKNOWN;
		try {
			if (anArchive.isEJBJarFile())
				type = EJBJARFILE;
			else if (anArchive.isWARFile())
				type = WARFILE;
			else if (anArchive.isApplicationClientFile())
				type = CLIENTJARFILE;
			else if (anArchive.isRARFile())
				type = RARFILE;
			else if (anArchive.isEARFile())
				type = EARFILE;
		} catch (Exception e) {
			//Ignore
		}
		return type;
	}

	public static boolean isImportClassType(String fileName) {
		File file = new File(fileName);
		String fileExtension = getExtension(file);
		if (file.isFile()) {
			if (fileExtension.equalsIgnoreCase("jar") || //$NON-NLS-1$
						fileExtension.equalsIgnoreCase("zip") || //$NON-NLS-1$
						fileExtension.equalsIgnoreCase("class")) //$NON-NLS-1$
				return true;
		} else if (file.isDirectory()) { //disable/enable drag/drop directories
			return false;
		}
		return false;
	}

	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}


	public static String findMatchingProjectName(String projectName) {
		if (projectName.trim().length() > 0) {
			IWorkspaceRoot root = J2EEPlugin.getWorkspace().getRoot();
			IProject[] projects = root.getProjects();
			String lowerCaseName = projectName.toLowerCase();
			// iterate through all projects a compare lowercase names
			if (projectName == null || projectName.length() == 0) {
				if (projects.length == 1)
					return projects[0].getName();
				return null;
			}
			for (int i = 0; i < projects.length; i++) {
				if (projects[i].exists()) {
					if (WTPPlugin.isPlatformCaseSensitive()) {
						if (projects[i].getName().equals(projectName))
							return projects[i].getName();
					} else {
						if (projects[i].getName().toLowerCase().equals(lowerCaseName))
							return projects[i].getName();
					}
				}
			}
		}
		return projectName;
	}



}
