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
package org.eclipse.jst.j2ee.internal.ejb.project;

import java.util.logging.Level;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;
import org.eclipse.jst.j2ee.model.internal.validation.IEJBValidatorConstants;

import com.ibm.wtp.common.logger.LogEntry;
import com.ibm.wtp.common.logger.proxy.Logger;


/**
 * This class loads an EJB Project's resources, but it should not be cached, because the project's
 * model will change periodically. But during a pass when we know that the model cannot change
 * (e.g., when a ResourceChangeListener is invoked), then it is safe to new up one of these classes,
 * and let it cache its RefObjects (for performance reasons).
 * 
 * But this class must always be cleanup() after, because it loads and EJBEditModel which has to be
 * released. It may also open an archive ModuleFile.
 * 
 * This helper class takes care of loading the edit model, and whatever multiple types of
 * information that's needed from the edit model, and frees everything when
 * EJBProjectResources.cleanup() is called.
 */
public class EJBProjectResources {
	private IProject _project = null;
	private EJBEditModel _editModel = null;
	private ModuleFile _moduleFile = null;
	private EJBJar _ejbJar = null;
	private EARNatureRuntime _earNature = null;
	private static LogEntry _logEntry = null;

	public EJBProjectResources(IProject project) {
		_project = project;
	}

	public static Logger getMsgLogger() {
		return J2EEPlugin.getPlugin().getMsgLogger();
	}

	public static LogEntry getDefaultMessage() {
		if (_logEntry == null) {
			_logEntry = new LogEntry(IEJBValidatorConstants.BUNDLE_NAME);
		}
		_logEntry.reset(); // reset the values so that we're not logging stale data
		return _logEntry;
	}

	/**
	 * Free all of the resources which were retrieved during the use of this class.
	 */
	public void cleanup() {
		try {
			if (_moduleFile != null) {
				_moduleFile.close();
			}

			if (_editModel != null) {
				_editModel.releaseAccess(this);
				_editModel = null;
			}
		} catch (Throwable exc) {
			Logger logger = getMsgLogger();
			if (logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
		}
	}

	public EARNatureRuntime getEARNature() {
		if (_earNature == null) {
			_earNature = getEARNatureRuntime();
		}
		return _earNature;
	}

	private EARNatureRuntime getEARNatureRuntime() {
		if (getProject() == null) {
			return null;
		}

		EARNatureRuntime earNature = null;
		if (!(EARNatureRuntime.hasRuntime(getProject())))
			//
			// Catch-22.
			//
			// When an EJBProject is created for the first time, the IProject
			// needs to have an EJBNatureRuntime configured on it. The call to
			// create an EJBNatureRuntime triggers a build. The build triggers
			// a call to each Validator's validate method.
			//
			// In order to load the information from EJBModel.ejbxmi and Map.mapxmi,
			// the EJB Validator needs the EJBNatureRuntime associated with a project.
			// However, since the given project is in the process of creating an
			// EJBNatureRuntime, it won't have a nature associated with it until
			// the build is complete. Thus, the call to getRuntime(getProject()) results
			// in a CoreException (complaining that the project doesn't have a nature).
			//
			// For now, until a better way of handling this is found, I'll just
			// check that the project has an EJBNature configured on it. If not,
			// return, because we can't validate.
			return null;
		earNature = EARNatureRuntime.getRuntime(getProject());
		return earNature;
	}

	/**
	 * Cache the EditModel the first time we load it, in case the user attempts to load it again.
	 * Since this EJBProjectResources should be cleaned up when the caller is done, we don't need to
	 * worry about the caching problem. (That is, the project will not change underneath during
	 * validation.)
	 */
	public EJBEditModel getEditModel() {
		if (_editModel == null) {
			EJBNatureRuntime runtime = getEJBNatureRuntime();
			if (runtime == null) {
				return null;
			}
			_editModel = runtime.getEJBEditModelForRead(this);
		}
		return _editModel;
	}

	/**
	 * Cache the ModuleFile the first time we load it, in case the user attempts to load it again.
	 * Since this EJBProjectResources should be cleaned up when the caller is done, we don't need to
	 * worry about the caching problem. (That is, the project will not change underneath during
	 * validation.)
	 */
	public ModuleFile getEJBFile() {
		if (_moduleFile == null) {
			_moduleFile = getEJBFile(getEditModel());
		}
		return _moduleFile;
	}

	/**
	 * Load the EJB MOF model. If the EJBJar is contained in an EAR file, then the EARFile is
	 * returned.Otherwise, the EJBJarFile is returned.
	 */
	public static ModuleFile getEJBFile(EJBEditModel model) {
		Logger logger = getMsgLogger();

		ModuleFile ejbFile = null; // may be EARFile or EJBJarFile
		if (model == null) {
			// non-NLS
			if (logger.isLoggingLevel(Level.FINE)) {
				LogEntry entry = getDefaultMessage();
				entry.setSourceIdentifier("EJBProjectResources.getEJBFile(EJBEditModel)"); //$NON-NLS-1$
				entry.setText(J2EEPluginResourceHandler.getString("Cannot_load_EJBFile_because_EJBEditModel_is_null_UI_")); //$NON-NLS-1$
				logger.write(Level.FINE, entry);
			}
			return null;
		}

		try {
			EJBNatureRuntime ejbNature = model.getEJBNature();
			if (ejbNature == null) {
				// non-NLS
				if (logger.isLoggingLevel(Level.FINE)) {
					LogEntry entry = getDefaultMessage();
					entry.setSourceIdentifier("EJBProjectResources.getEJBFile(EJBEditModel)"); //$NON-NLS-1$
					entry.setText(J2EEPluginResourceHandler.getString("Cannot_load_EJBFile_because_EJBNatureRuntime_is_null_UI_")); //$NON-NLS-1$
					logger.write(Level.FINE, entry);
				}
				return null;
			}

			// Check if the file exists...
			IFolder metaFolder = ejbNature.getMetaFolder();
			if ((metaFolder == null) || !metaFolder.exists()) {
				if (logger.isLoggingLevel(Level.SEVERE)) {
					LogEntry entry = getDefaultMessage();
					entry.setSourceIdentifier("EJBProjectResources.getEJBFile(EJBEditModel)"); //$NON-NLS-1$
					entry.setMessageTypeIdentifier(J2EEPluginResourceHandler.getString("WARNING_METAFOLDER_MISSING_UI_")); //$NON-NLS-1$
					String metaFolderName = metaFolder == null ? J2EEConstants.META_INF : metaFolder.getName();
					entry.setTokens(new String[]{metaFolderName, ejbNature.getProject().getName(), ejbNature.getProject().getName()});
					logger.write(Level.SEVERE, entry);
				}
				return null;
			}

			IResource ejb_jar_xml = metaFolder.findMember(J2EEConstants.EJBJAR_DD_SHORT_NAME);
			if ((ejb_jar_xml == null) || (!ejb_jar_xml.exists())) {
				if (logger.isLoggingLevel(Level.SEVERE)) {
					LogEntry entry = getDefaultMessage();
					entry.setSourceIdentifier("EJBProjectResources.getEJBFile(EJBEditModel)"); //$NON-NLS-1$
					entry.setMessageTypeIdentifier(J2EEPluginResourceHandler.getString("WARNING_FILE_MISSING_UI_")); //$NON-NLS-1$
					String ejbJarID = ejb_jar_xml == null ? J2EEConstants.EJBJAR_DD_SHORT_NAME : ejb_jar_xml.getName();
					entry.setTokens(new String[]{ejbJarID, ejbNature.getProject().getName()});
					logger.write(Level.SEVERE, entry);
				}
				return null;
			}

			try {
				// First, try loading from the EAR's alt-DD first. If one doesn't exist,
				// then load the DD in this project.

				EJBJarFile ejbJarFile = null;
				try {
					ejbJarFile = ejbNature.asEJBJarFile();
				} catch (OpenFailureException exc) {
					// Failed to open EJBJar file.
					// non-NLS
					if (logger.isLoggingLevel(Level.FINE)) {
						logger.write(Level.FINE, exc);
					}
					return null;
				} catch (NullPointerException exc) {
					// this happens when there's no ejb-jar.xml file
					// non-NLS
					if (logger.isLoggingLevel(Level.FINE)) {
						logger.write(Level.FINE, exc);
					}
					return null;
				}
				if (ejbJarFile == null) {
					// non-NLS
					if (logger.isLoggingLevel(Level.FINE)) {
						LogEntry entry = getDefaultMessage();
						entry.setSourceIdentifier("EJBProjectResources.getEJBFile(EJBEditModel)"); //$NON-NLS-1$
						entry.setText(J2EEPluginResourceHandler.getString("Cannot_load_EJBFile_because_ejbNature.asEJBJarFile()_returns_null_UI_")); //$NON-NLS-1$
						logger.write(Level.FINE, entry);
					}
					return null;
				}

				ejbFile = ejbJarFile;
				EARFile earFile = ejbJarFile.getEARFile();
				ejbFile = earFile;
				if (earFile != null) { // do I need to check for this?
					ejbJarFile.close();
				}
			} catch (Exception exc) {
				// ejb not contained in an EAR file. Return the ejbJarFile instead.
			}
		} catch (Throwable exc) {
			ejbFile = null;
			if (logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
		}
		return ejbFile;
	}

	/**
	 * Cache the EJBJar the first time we load it, in case the user attempts to load it again. Since
	 * this EJBProjectResources should be cleaned up when the caller is done, we don't need to worry
	 * about the caching problem. (That is, the project will not change underneath during
	 * validation.)
	 */
	public EJBJar getEJBJar() {
		if (_ejbJar == null) {
			_ejbJar = getEJBJar(getEditModel());
		}
		return _ejbJar;
	}

	public EJBNatureRuntime getEJBNature() {
		return EJBNatureRuntime.getRuntime(getProject());
	}

	private EJBNatureRuntime getEJBNatureRuntime() {
		if (getProject() == null) {
			return null;
		}

		EJBNatureRuntime ejbNature = null;
		if (!(EJBNatureRuntime.hasRuntime(getProject())))
			//
			// Catch-22.
			//
			// When an EJBProject is created for the first time, the IProject
			// needs to have an EJBNatureRuntime configured on it. The call to
			// create an EJBNatureRuntime triggers a build. The build triggers
			// a call to each Validator's validate method.
			//
			// In order to load the information from EJBModel.ejbxmi and Map.mapxmi,
			// the EJB Validator needs the EJBNatureRuntime associated with a project.
			// However, since the given project is in the process of creating an
			// EJBNatureRuntime, it won't have a nature associated with it until
			// the build is complete. Thus, the call to getRuntime(getProject()) results
			// in a CoreException (complaining that the project doesn't have a nature).
			//
			// For now, until a better way of handling this is found, I'll just
			// check that the project has an EJBNature configured on it. If not,
			// return, because we can't validate.
			return null;

		ejbNature = EJBNatureRuntime.getRuntime(getProject());
		return ejbNature;
	}

	public IProject getProject() {
		return _project;
	}

	/**
	 * Load the EJB MOF model.
	 */
	public static EJBJar getEJBJar(EJBEditModel model) {
		Logger logger = getMsgLogger();
		EJBJar ejbJar = null;

		if (model == null) {
			// non-NLS
			if (logger.isLoggingLevel(Level.FINE)) {
				LogEntry entry = getDefaultMessage();
				entry.setSourceIdentifier(J2EEPluginResourceHandler.getString("EJBProjectResources.getEJBJar(EJBEditModel)_UI_")); //$NON-NLS-1$
				entry.setText(J2EEPluginResourceHandler.getString("Cannot_load_EJBJar_because_model_is_null_UI_")); //$NON-NLS-1$
				logger.write(Level.FINE, entry);
			}
			return null;
		}

		try {
			EJBNatureRuntime ejbNature = model.getEJBNature();
			if (ejbNature == null) {
				// non-NLS
				if (logger.isLoggingLevel(Level.FINE)) {
					LogEntry entry = getDefaultMessage();
					entry.setSourceIdentifier("EJBProjectResources.getEJBJar(EJBEditModel)"); //$NON-NLS-1$
					entry.setText(J2EEPluginResourceHandler.getString("Cannot_load_EJBFile_because_EJBNatureRuntime_is_null_UI_")); //$NON-NLS-1$
					logger.write(Level.FINE, entry);
				}
				return null;
			}

			// Check if the file exists...
			if (!ejbNature.isBinaryProject()) {
				IFolder metaFolder = ejbNature.getMetaFolder();
				if ((metaFolder == null) || !metaFolder.exists()) {
					if (logger.isLoggingLevel(Level.WARNING)) {
						LogEntry entry = getDefaultMessage();
						entry.setSourceIdentifier("EJBProjectResources.getEJBJar(EJBEditModel)"); //$NON-NLS-1$
						entry.setMessageTypeIdentifier(J2EEPluginResourceHandler.getString("WARNING_METAFOLDER_MISSING_UI_")); //$NON-NLS-1$
						String metaName = metaFolder == null ? "ejbModule" : metaFolder.getName(); //$NON-NLS-1$
						entry.setTokens(new String[]{metaName, ejbNature.getProject().getName(), ejbNature.getProject().getName()});
						logger.write(Level.WARNING, entry);
					}
					return null;
				}

				IResource ejb_jar_xml = metaFolder.findMember(J2EEConstants.EJBJAR_DD_SHORT_NAME);
				if ((ejb_jar_xml == null) || (!ejb_jar_xml.exists())) {
					if (logger.isLoggingLevel(Level.WARNING)) {
						LogEntry entry = getDefaultMessage();
						entry.setSourceIdentifier("EJBProjectResources.getEJBJar(EJBEditModel)"); //$NON-NLS-1$
						entry.setMessageTypeIdentifier(J2EEPluginResourceHandler.getString("WARNING_FILE_MISSING_UI_")); //$NON-NLS-1$
						entry.setTokens(new String[]{J2EEConstants.EJBJAR_DD_SHORT_NAME, ejbNature.getProject().getName()});
						logger.write(Level.WARNING, entry);
					}
					return null;
				}
			}

			ejbJar = model.getEJBJar();
		} catch (Throwable exc) {
			ejbJar = null;
			if (logger.isLoggingLevel(Level.FINER)) {
				logger.write(Level.FINER, exc);
			}
		}
		return ejbJar;
	}

}