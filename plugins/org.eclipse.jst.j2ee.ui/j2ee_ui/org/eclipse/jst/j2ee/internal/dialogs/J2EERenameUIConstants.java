/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.dialogs;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;

public interface J2EERenameUIConstants {
	String RENAME = J2EEUIMessages.getResourceString("Rename_1"); //$NON-NLS-1$
	String RENAME_PROJECTS = J2EEUIMessages.getResourceString("Rename_selected_project_2"); //$NON-NLS-1$
	String RENAME_MODULES = J2EEUIMessages.getResourceString("Rename_module_in_all_Enterprise_Applications_3"); //$NON-NLS-1$
	String RENAME_MODULES_OTHER = J2EEUIMessages.getResourceString("Rename_module_in_all_other_Enterprise_Applications_4"); //$NON-NLS-1$
	String RENAME_MODULE_DEPENDENCIES = J2EEUIMessages.getResourceString("Rename_module_dependencies_referencing_selected_project_5"); //$NON-NLS-1$
	String RENAME_MODULE_OPTIONS = J2EEUIMessages.getResourceString("Rename_Module_Options_6"); //$NON-NLS-1$
	String RENAME_EAR_OPTIONS = J2EEUIMessages.getResourceString("Rename_Enterprise_Application_Options_7"); //$NON-NLS-1$
	String RENAME_NOT_COMPLETED = J2EEUIMessages.getResourceString("Rename_could_not_be_completed_8"); //$NON-NLS-1$
	String RENAME_ERROR = J2EEUIMessages.getResourceString("Rename_error_9"); //$NON-NLS-1$
	String RENAME_DIALOG_MESSAGE = J2EEUIMessages.getResourceString("What_would_you_like_to_rename_this_to__10"); //$NON-NLS-1$
	String RENAME_CONTEXT_ROOT = J2EEUIMessages.getResourceString("Context_Root__11"); //$NON-NLS-1$
	String RENAME_EAR_PROJECTS = J2EEUIMessages.getResourceString("Rename_selected_Enterprise_Application_project_only_12"); //$NON-NLS-1$
	String RENAME_REFERENCED_PROJECTS = J2EEUIMessages.getResourceString("Also_rename_module_and_utility_Java_projects_13"); //$NON-NLS-1$
}