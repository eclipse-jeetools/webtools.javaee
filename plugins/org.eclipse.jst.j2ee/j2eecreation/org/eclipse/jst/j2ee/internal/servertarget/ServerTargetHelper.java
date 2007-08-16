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
/*
 * Created on Aug 15, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.servertarget;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerUtil;

//import com.ibm.etools.j2ee.internal.project.EAREditModel;
//import com.ibm.etools.j2ee.internal.project.EARNatureRuntime;

/**
 * @author vijayb
 * @deprecated - Use server core api to set Runtime Targets. Use artifact edit api to
 * get to any j2ee related methods in the class.
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class ServerTargetHelper {
	public static final String JAVA_NATURE_ID = "org.eclipse.jdt.core.javanature"; //$NON-NLS-1$

	public static final String SERVER_CONTAINER = "org.eclipse.jst.server.core.container"; //$NON-NLS-1$

	/**
	 *  
	 */
	public ServerTargetHelper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void cleanUpNonServerTargetClasspath(IProject project) {
		List existingClasspathEntries = ServerTargetHelper.getExistingNonServerTargetClasspath(project);
		if (!existingClasspathEntries.isEmpty())
			ServerTargetHelper.removeNonSeverTargetClasspathEntries(project, existingClasspathEntries);
	}

	/**
	 * @param existingClasspathEntries
	 */
	public static void removeNonSeverTargetClasspathEntries(IProject project, List existingClasspathEntries) {
		try {
			JemProjectUtilities.removeFromJavaClassPath(project, existingClasspathEntries);
		} catch (JavaModelException jme) {
			Logger.getLogger().logError(jme);
		}
	}

	/**
	 * @param project
	 */
	// public static void syncNoServerTargetForEARModules(IProject project) {
	// removeServerTargetForEARModuleProjects(project);
	// removeServerTargetForEARUtilsProject(project);
	// }
	/**
	 * @param project
	 */
	// public static void removeServerTargetForEAR(IProject project, boolean
	// updateModules) {
	// //We don't need to remove a server target anyomre as it need to be
	// // there
	// //ServerTargetManager.removeServerTarget(project, null);
	// if (updateModules) {
	// removeServerTargetForEARModuleProjects(project);
	// removeServerTargetForEARUtilsProject(project);
	// }
	// }
	/**
	 * @param project
	 */
	// private static void removeServerTargetForEARModuleProjects(IProject
	// project) {
	// List moduleProjects =
	// ServerTargetHelper.getAllEarModuleProjects(project);
	// if (!moduleProjects.isEmpty()) {
	// for (int i = 0; i < moduleProjects.size(); i++) {
	// IProject moduleProject = (IProject) moduleProjects.get(i);
	// ServerTargetType tt =
	// ServerTargetManager.getServerTargetType(moduleProject);
	// if (tt != null) {
	// J2EEJavaProjectInfo info = getNewJ2EEProjectInfo(moduleProject);
	// if (info != null) {
	// info.setProject(moduleProject);
	// try {
	// info.calculateServerClasspathEntries();
	// info.getJavaProject().setRawClasspath(info.getClasspathEntries(), null);
	// } catch (JavaModelException jme) {
	// Logger.getLogger().logTrace(jme);
	// }
	// }
	// }
	// }
	// }
	// }
	/**
	 * @param project
	 */
	// private static void removeServerTargetForEARUtilsProject(IProject
	// project) {
	// EARNatureRuntime nature = EARNatureRuntime.getRuntime(project);
	// EAREditModel editModel = null;
	// Object key = new ServerTargetHelper();
	// try {
	// editModel = nature.getEarEditModelForRead(key);
	// List utilProjects =
	// ServerTargetHelper.getAllEarUtilityProjects(editModel, project);
	// if (!utilProjects.isEmpty()) {
	// for (int i = 0; i < utilProjects.size(); i++) {
	// IProject moduleProject = (IProject) utilProjects.get(i);
	// //We don't need to remove a server target anyomre as it need to be there
	// //ServerTargetType existingTargetType =
	// // ServerTargetManager.getServerTargetType(moduleProject);
	// //if (existingTargetType != null) {
	// //if (!editModel.isEjbClientJarProject(moduleProject))
	// //We don't need to remove a server target anyomre as it
	// // need to be there
	// //ServerTargetManager.removeServerTarget(moduleProject,null);
	// //else
	// // removeServerTargetForEJBClientJar(moduleProject);
	// }
	// }
	// } finally {
	// editModel.releaseAccess(key);
	// }
	// }
	/**
	 * @param moduleProject
	 */
	// public static void removeServerTargetForEJBClientJar(IProject
	// ejbClientJarProject) {
	// EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
	// J2EEJavaProjectInfo info = ejbExt.createProjectInfo();
	// if (info != null) {
	// info.setProject(ejbClientJarProject);
	// try {
	// info.calculateServerClasspathEntries();
	// info.getJavaProject().setRawClasspath(info.getClasspathEntries(), null);
	// } catch (JavaModelException jme) {
	// Logger.getLogger().logTrace(jme);
	// }
	// }
	// }
	// public static void removeServerTargetOnProject(IProject project, String
	// projectType) {
	// setServerClassPathForJ2EEProject(project, projectType);
	// }
	/**
	 * @param project
	 */
	// public static void setServerClassPathForJ2EEProject(IProject project,
	// String projectType) {
	// try {
	// J2EEJavaProjectInfo info = getNewJ2EEProjectInfo(project, projectType);
	// if (info != null) {
	// info.setProject(project);
	// info.calculateServerClasspathEntries();
	// info.getJavaProject().setRawClasspath(info.getClasspathEntries(), null);
	// }
	// } catch (JavaModelException jme) {
	// Logger.getLogger().logError(jme);
	// }
	// }
	// public static J2EEJavaProjectInfo getNewJ2EEProjectInfo(IProject project,
	// String projectType)
	// {
	// if (projectType.equals(IServerTargetConstants.APP_CLIENT_TYPE))
	// return new ApplicationClientProjectInfo();
	// else if (projectType.equals(IServerTargetConstants.WEB_TYPE)) {
	// WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
	// J2EEJavaProjectInfo info = webExt.createProjectInfo();
	// String webNature = J2EENature.getRegisteredRuntimeID(project);
	// info.setJ2EEVersion(webNature.equals(J2EEVersionConstants.VERSION_1_3_TEXT)
	// ?
	// J2EEVersionConstants.J2EE_1_3_ID : J2EEVersionConstants.J2EE_1_2_ID);
	// return info;
	// } else if (projectType.equals(IServerTargetConstants.EJB_TYPE)) {
	// EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
	// return ejbExt.createProjectInfo();
	// } else if (projectType.equals(IServerTargetConstants.CONNECTOR_TYPE)) {
	// JcaModuleExtension jcaExt = EarModuleManager.getJCAModuleExtension();
	// return jcaExt.createProjectInfo();
	// }
	// return null;
	// }
	// public static J2EEJavaProjectInfo getNewJ2EEProjectInfo(IProject project)
	// {
	// if (J2EENature.hasRuntime(project, IEJBNatureConstants.EJB_NATURE_IDS)) {
	// EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
	// return ejbExt.createProjectInfo();
	// } else if (J2EENature.hasRuntime(project,
	// IWebNatureConstants.J2EE_NATURE_ID)) {
	// WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
	// return webExt.createProjectInfo();
	// } else if (ApplicationClientNatureRuntime.hasRuntime(project))
	// return new ApplicationClientProjectInfo();
	// else if (J2EENature.hasRuntime(project,
	// IConnectorNatureConstants.CONNECTOR_NATURE_ID)) {
	// JcaModuleExtension jcaExt = EarModuleManager.getJCAModuleExtension();
	// return jcaExt.createProjectInfo();
	// }
	// return null;
	// }
//	public static void setNewServerTargetForEARModules(IRuntime newServerTarget, IProject project) {
//		List moduleProjects = ServerTargetHelper.getAllEarModuleProjects(project);
//		if (!moduleProjects.isEmpty()) {
//			for (int i = 0; i < moduleProjects.size(); i++) {
//				IProject moduleProject = (IProject) moduleProjects.get(i);
//				String[] projectAttr = ServerTargetHelper.getProjectTypeAndJ2EELevel(moduleProject);
//				if (projectAttr[0] != null && projectAttr[1] != null) {
//					IRuntime existingTargetType = ServerCore.getProjectProperties(moduleProject).getRuntimeTarget();
//					if (existingTargetType != null) {
//						if (!existingTargetType.getId().equals(newServerTarget.getId()))
//							setServerTargetForModuleProject(newServerTarget, moduleProject, projectAttr);
//						// TODO Move the WLP sync to this method
//					} else
//						setServerTargetForModuleProject(newServerTarget, moduleProject, projectAttr);
//				}
//			}
//		}
//	}

//	public static void setServerTargetForModuleProject(IRuntime newServerTarget, IProject moduleProject, String[] projectAttr) {
//		ServerTargetHelper.cleanUpNonServerTargetClasspath(moduleProject);
//		setServerTarget(moduleProject, newServerTarget, null);
//	}

//	public static List getWebProjectsInEAR(IProject earProject) {
//		List moduleProjects = ServerTargetHelper.getAllEarModuleProjects(earProject);
//		if (!moduleProjects.isEmpty()) {
//			List webProjects = new ArrayList();
//			for (int i = 0; i < moduleProjects.size(); i++) {
//				IProject project = (IProject) moduleProjects.get(i);
//				WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
//				if (webExt.hasRuntime(project))
//					webProjects.add(project);
//			}
//			return webProjects;
//		}
//		return Collections.EMPTY_LIST;
//	}

	/**
	 * @param newServerTarget
	 * @param tt
	 * @param project
	 */
//	public static void setNewServerTargetForEARUtilityJars(IRuntime newServerTarget, IProject project) {
//		EARNatureRuntime nature = EARNatureRuntime.getRuntime(project);
//		EAREditModel editModel = null;
//		Object key = new ServerTargetHelper();
//		try {
//			editModel = nature.getEarEditModelForRead(key);
//			List utilProjects = ServerTargetHelper.getAllEarUtilityProjects(editModel, project);
//			if (!utilProjects.isEmpty()) {
//				for (int i = 0; i < utilProjects.size(); i++) {
//					IProject moduleProject = (IProject) utilProjects.get(i);
//					IRuntime existingTargetType = ServerCore.getProjectProperties(moduleProject).getRuntimeTarget();
//					if (existingTargetType != null) {
//						if (!existingTargetType.getId().equals(newServerTarget.getId())) {
//							setServerTargetForUtilProject(newServerTarget, moduleProject);
//						}
//					} else {
//						setServerTargetForUtilProject(newServerTarget, moduleProject);
//					}
//				}
//			}
//		} finally {
//			editModel.releaseAccess(key);
//		}
//	}

//	public static void setServerTargetForUtilProject(IRuntime newServerTarget, IProject moduleProject) {
//		ServerTargetHelper.cleanUpNonServerTargetClasspath(moduleProject);
//		setServerTarget(moduleProject, newServerTarget, null);
//	}

//	public static void setServerTarget(IProject project, IRuntime runtime, IProgressMonitor monitor) {
//		try {
//			ServerCore.getProjectProperties(project).setRuntimeTarget(runtime, monitor);
//		} catch (CoreException ce) {
//			Logger.getLogger().logError(ce);
//		}
//	}

	/**
	 * @param earProject
	 */
//	public static List getAllEarModuleProjects(IProject earProject) {
//		EARNatureRuntime nature = EARNatureRuntime.getRuntime(earProject);
//		EAREditModel editModel = null;
//		List moduleProjects = new ArrayList();
//		Object key = new ServerTargetHelper();
//		try {
//			editModel = nature.getEarEditModelForRead(key);
//			Set allModuleProjects = editModel.getModuleMappedModuleProjects();
//			if (!allModuleProjects.isEmpty())
//				moduleProjects.addAll(allModuleProjects);
//			return moduleProjects;
//		} finally {
//			if (editModel != null)
//				editModel.releaseAccess(key);
//		}
//	}
//
	public static String[] getProjectTypeAndJ2EELevel(IProject project) {
		String[] projectAttr = new String[2];
//		boolean isJ2EE13 = true;
//		if (EARNatureRuntime.hasRuntime(project)) {
//			projectAttr[0] = IServerTargetConstants.EAR_TYPE;
//			isJ2EE13 = EARNatureRuntime.getRuntime(project).isJ2EE1_3();
//		} else if (J2EENature.hasRuntime(project, IEJBNatureConstants.EJB_NATURE_IDS)) {
//			projectAttr[0] = IServerTargetConstants.EJB_TYPE;
//			isJ2EE13 = J2EENature.getRuntime(project, IEJBNatureConstants.EJB_NATURE_IDS).isJ2EE1_3();
//		} else if (J2EENature.hasRuntime(project, IWebNatureConstants.J2EE_NATURE_ID)) {
//			projectAttr[0] = IServerTargetConstants.WEB_TYPE;
//			isJ2EE13 = J2EENature.getRuntime(project, IWebNatureConstants.J2EE_NATURE_ID).isJ2EE1_3();
//		} else if (J2EENature.hasRuntime(project,IApplicationClientNatureConstants.NATURE_ID)) {
//			projectAttr[0] = IServerTargetConstants.APP_CLIENT_TYPE;
//			isJ2EE13 = J2EENature.getRuntime(project,IApplicationClientNatureConstants.NATURE_ID).isJ2EE1_3();
//		} else if (J2EENature.hasRuntime(project, IConnectorNatureConstants.NATURE_ID)) {
//			projectAttr[0] = IServerTargetConstants.CONNECTOR_TYPE;
//			isJ2EE13 = J2EENature.getRuntime(project, IConnectorNatureConstants.NATURE_ID).isJ2EE1_3();
//		} else if (hasJavaNature(project))
//			projectAttr[0] = null;
//		if (projectAttr[0] != null && projectAttr[0].length() > 0) {
//			if (isJ2EE13)
//				projectAttr[1] = IServerTargetConstants.J2EE_13;
//			else
//				projectAttr[1] = IServerTargetConstants.J2EE_12;
//		}
		return projectAttr;
	}

	/**
	 * @param project
	 * @return
	 */
	public static boolean hasJavaNature(IProject project) {
		try {
			return project.hasNature(JAVA_NATURE_ID);
		} catch (CoreException ce) {
			Logger.getLogger().logTrace(ce);
		}
		return false;
	}

	/**
	 * @param earProject
	 */
//	public static List getAllEarUtilityProjects(EAREditModel earEditModel, IProject earProject) {
//		List utilityProjects = new ArrayList();
//		Set utilProjects = earEditModel.getModuleMappedUtilityJarProjects();
//		if (!utilProjects.isEmpty())
//			utilityProjects.addAll(utilProjects);
//		return utilityProjects;
//	}

	public static int getExistingServerTargetIndex(List validServerTargets, IProject project) {
		if (validServerTargets != null) {
			IRuntime st = ServerCore.getProjectProperties(project).getRuntimeTarget();
			if (st != null) {
				for (int i = 0; i < validServerTargets.size(); i++) {
					if (((IRuntime) validServerTargets.get(i)).getId() == st.getId())
						return i;
				}
			}
		}
		return -1;
	}

	public static List getExistingNonServerTargetClasspath(IProject project) {
		IJavaProject javaProject = null;
		List list = new ArrayList();
		try {
			javaProject = (IJavaProject) project.getNature(JavaCore.NATURE_ID);
		} catch (Exception e) {
		}
		if (javaProject != null) {
			try {
				IClasspathEntry[] cp = javaProject.getRawClasspath();
				int size = cp.length;
				for (int i = 0; i < size; i++) {
					int entryKind = cp[i].getEntryKind();
					if (entryKind != IClasspathEntry.CPE_SOURCE && entryKind != IClasspathEntry.CPE_LIBRARY && entryKind != IClasspathEntry.CPE_PROJECT && (entryKind == IClasspathEntry.CPE_VARIABLE && isWASVariable(cp[i])) && (entryKind != IClasspathEntry.CPE_CONTAINER || !cp[i].getPath().segment(0).equals(SERVER_CONTAINER))) {
						list.add(cp[i]);
					}
				}
			} catch (Exception e) {
			}
			return list;
		}
		return list;
	}

	/**
	 * @param entry
	 * @return
	 */
	private static boolean isWASVariable(IClasspathEntry entry) {
		// TODO This is no longer valid in the base.
		// IPath path = entry.getPath();
		// if (path != null && path.segmentCount() != 0) {
		// String classpathVariable = path.segment(0);
		// return
		// classpathVariable.equals(IEJBNatureConstants.WAS_PLUGINDIR_VARIABLE)
		// ||
		// classpathVariable.equals(IEJBNatureConstants.WAS_50_PLUGINDIR_VARIABLE)
		// ||
		// classpathVariable.equals(IEJBNatureConstants.SERVERJDK_50_PLUGINDIR_VARIABLE)
		// ||
		// classpathVariable.equals(IEJBNatureConstants.SERVERJDK_PLUGINDIR_VARIABLE)
		// || classpathVariable.equals(J2EEPlugin.SOAP_PLUGINDIR_VARIABLE)
		// || classpathVariable.equals(J2EEPlugin.SOAPSEC_PLUGINDIR_VARIABLE);
		// //|| classpathVariable.equals(J2EEPlugin.XERCES_IMPL_VARIABLE)
		// //|| classpathVariable.equals(J2EEPlugin.XERCES_PLUGINDIR_VARIABLE)
		// //|| classpathVariable.equals(J2EEPlugin.WORF_PLUGINDIR_VARIABLE)
		// //|| classpathVariable.equals(J2EEPlugin.MAIL_CLASSPATH_VARIABLE)
		// //||
		// classpathVariable.equals(J2EEPlugin.ACTIVATION_CLASSPATH_VARIABLE);
		// }
		return false;
	}

	/**
	 * @param earProject
	 * @param st
	 * @return
	 */
//	public static boolean serverTargetValidForEAR(IProject earProject, IRuntime st) {
//		return !(J2EENature.hasRuntime(earProject, IEARNatureConstants.NATURE_ID) && st.getId().equals(IServerTargetConstants.aesV4ServerTargetId));
//	}

	public static List getServerTargets(String type, String version) {
		List targets = Arrays.asList(ServerUtil.getRuntimes(type, version));
		return targets;
	}
	/*
	 * public static ITargetType getV50TargetType(IProject project, IServerTarget v50ServerTarget) {
	 * if (J2EENature.hasRuntime(project, IEARNatureConstants.NATURE_ID)) return
	 * ServerTargetManager.getTargetType(v50ServerTarget, IServerTargetConstants.EAR_TYPE); if
	 * (J2EENature.hasRuntime(project, IApplicationClientNatureConstants.NATURE_ID)) return
	 * ServerTargetManager.getTargetType(v50ServerTarget, IServerTargetConstants.EAR_TYPE); else if
	 * (J2EENature.hasRuntime(project, IEJBNatureConstants.NATURE_ID)) return
	 * ServerTargetManager.getTargetType(v50ServerTarget, IServerTargetConstants.EJB_TYPE); else if
	 * (J2EENature.hasRuntime(project, IWebNatureConstants.J2EE_NATURE_ID)) return
	 * ServerTargetManager.getTargetType(v50ServerTarget, IServerTargetConstants.WEB_TYPE); return
	 * null; }
	 */
}
