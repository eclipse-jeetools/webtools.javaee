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

public interface J2EEDeleteUIConstants {
	String DELETE = J2EEUIMessages.getResourceString("Delete_1"); //$NON-NLS-1$
	String DELETE_PROJECTS = J2EEUIMessages.getResourceString("Delete_selected_project(s)_2"); //$NON-NLS-1$
	String DELETE_MODULES = J2EEUIMessages.getResourceString("Remove_module(s)_from_all_Enterprise_Applications_3"); //$NON-NLS-1$
	String DELETE_MODULES_OTHER = J2EEUIMessages.getResourceString("Remove_module(s)_from_all_other_Enterprise_Applications_4"); //$NON-NLS-1$
	String DELETE_MODULE_DEPENDENCIES = J2EEUIMessages.getResourceString("Remove_module_dependencies_referencing_selected_project(s)_5"); //$NON-NLS-1$
	String DELETE_MODULE_OPTIONS = J2EEUIMessages.getResourceString("Delete_Module_Options_6"); //$NON-NLS-1$
	String DELETE_EAR_OPTIONS = J2EEUIMessages.getResourceString("Delete_Enterprise_Application_Options_7"); //$NON-NLS-1$
	String DELETE_NOT_COMPLETED = J2EEUIMessages.getResourceString("Delete_could_not_be_completed_8"); //$NON-NLS-1$
	String DELETE_ERROR = J2EEUIMessages.getResourceString("Delete_error_9"); //$NON-NLS-1$
	String DELETE_DIALOG_MESSAGE = J2EEUIMessages.getResourceString("What_would_you_like_to_delete__10"); //$NON-NLS-1$
	String DELETE_EAR_PROJECTS = J2EEUIMessages.getResourceString("Delete_selected_Enterprise_Application_project(s)_only_11"); //$NON-NLS-1$
	String DELETE_REFERENCED_PROJECTS = J2EEUIMessages.getResourceString("Also_delete_module_and_utility_Java_projects_12"); //$NON-NLS-1$
	String DELETE_PROJECTS_ONLY = J2EEUIMessages.getResourceString("Delete_selected_project(s)_only_13"); //$NON-NLS-1$
	String DELETE_PROJECT_REFERENCES = J2EEUIMessages.getResourceString("Also_delete_references_to_selected_project(s)_14"); //$NON-NLS-1$
	String CUSTOM_DELETE_MIX_MATCH = J2EEUIMessages.getResourceString("CUSTOM_DELETE_MIX_MATCH_UI_"); //$NON-NLS-1$
}