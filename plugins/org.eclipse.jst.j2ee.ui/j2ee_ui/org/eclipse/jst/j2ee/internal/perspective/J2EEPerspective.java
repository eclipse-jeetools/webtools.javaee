/*******************************************************************************
 * Copyright (c) 2005, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.perspective;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressConstants;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.wst.project.facet.IProductConstants;
import org.eclipse.wst.project.facet.ProductManager;

public class J2EEPerspective implements org.eclipse.ui.IPerspectiveFactory {

	protected static String ID_SERVERS_VIEW = "org.eclipse.wst.server.ui.ServersView"; //$NON-NLS-1$
	protected static String ID_J2EE_HIERARCHY_VIEW = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$

	private static String ID_WST_SNIPPETS_VIEW = "org.eclipse.wst.common.snippets.internal.ui.SnippetsView"; //$NON-NLS-1$	
	private static final String ID_SEARCH_VIEW = "org.eclipse.search.ui.views.SearchView"; //$NON-NLS-1$
	private static final String ID_DATA_VIEW = "org.eclipse.datatools.connectivity.DataSourceExplorerNavigator"; //$NON-NLS-1$
	private static final String ID_CONSOLE_VIEW= "org.eclipse.ui.console.ConsoleView"; //$NON-NLS-1$
	private static final String ID_MARKERS_VIEW= "org.eclipse.ui.views.AllMarkersView"; //$NON-NLS-1$
	private static final String ID_TASKLIST_VIEW= "org.eclipse.mylyn.tasks.ui.views.tasks"; //$NON-NLS-1$
	private static final String ID_TERMINAL_VIEW = "org.eclipse.tm.terminal.view.ui.TerminalsView"; //$NON-NLS-1$
	private static final String ID_GIT_REPO_VIEW = "org.eclipse.egit.ui.RepositoriesView";  //$NON-NLS-1$
	private static final String ID_GIT_STG_VIEW = "org.eclipse.egit.ui.internal.staging.StagingView";  //$NON-NLS-1$

	public J2EEPerspective() {
		super();
		//If preference exists for alternate view, replace.
		String viewerID = ProductManager.getProperty(IProductConstants.ID_PERSPECTIVE_HIERARCHY_VIEW);
		if (viewerID != null) {
			// verify that the view actually exists
			if (PlatformUI.getWorkbench().getViewRegistry().find(viewerID) != null){
				ID_J2EE_HIERARCHY_VIEW = viewerID;
			}
		} else {
			viewerID = J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.ID_PERSPECTIVE_HIERARCHY_VIEW);
			if (viewerID != null){
				if (PlatformUI.getWorkbench().getViewRegistry().find(viewerID) != null){
					ID_J2EE_HIERARCHY_VIEW = viewerID;
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineLayout(layout);
		defineActions(layout);
	}
	
	

	public void defineActions(IPageLayout layout) {
		layout.addActionSet("org.eclipse.jst.j2ee.J2eeMainActionSet"); //$NON-NLS-1$
		layout.addActionSet("org.eclipse.jdt.ui.JavaActionSet"); //$NON-NLS-1$

		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);

		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);

		layout.addShowViewShortcut(ID_J2EE_HIERARCHY_VIEW);
		layout.addShowViewShortcut(ID_SERVERS_VIEW);
		layout.addShowViewShortcut(ID_DATA_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
		layout.addShowViewShortcut(ID_WST_SNIPPETS_VIEW);
		layout.addShowViewShortcut(ID_MARKERS_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(ID_TASKLIST_VIEW);
		layout.addShowViewShortcut(ID_TERMINAL_VIEW);
		layout.addShowViewShortcut(JavaUI.ID_PACKAGES_VIEW);
		
		// views - search
		layout.addShowViewShortcut(ID_SEARCH_VIEW);
		// views - debugging
		layout.addShowViewShortcut(ID_CONSOLE_VIEW);

		layout.addShowInPart(ID_J2EE_HIERARCHY_VIEW);
		layout.addShowInPart(JavaUI.ID_PACKAGES_VIEW);
	}

	public void defineLayout(IPageLayout layout) {
		// Editors are placed for free.
		String editorArea = layout.getEditorArea();

		// Top left.
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.2f, editorArea);//$NON-NLS-1$
		topLeft.addView(ID_J2EE_HIERARCHY_VIEW);
		topLeft.addPlaceholder(JavaUI.ID_PACKAGES_VIEW);
		topLeft.addPlaceholder(IPageLayout.ID_PROJECT_EXPLORER);
		topLeft.addPlaceholder(JavaUI.ID_TYPE_HIERARCHY);
		topLeft.addPlaceholder(IDebugUIConstants.ID_DEBUG_VIEW);
		topLeft.addPlaceholder(ID_SEARCH_VIEW);
		topLeft.addPlaceholder(ID_GIT_REPO_VIEW);

		// Bottom.
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, 0.8f, editorArea);//$NON-NLS-1$
		bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomRight.addView(ID_SERVERS_VIEW);
		addViewIfPresent(bottomRight, ID_TERMINAL_VIEW);
		addViewIfPresent(bottomRight, ID_DATA_VIEW);
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
		bottomRight.addPlaceholder(ID_WST_SNIPPETS_VIEW);
		bottomRight.addPlaceholder(ID_MARKERS_VIEW);
		bottomRight.addPlaceholder(IPageLayout.ID_BOOKMARKS);
		bottomRight.addPlaceholder(IPageLayout.ID_TASK_LIST);
		bottomRight.addPlaceholder(ID_CONSOLE_VIEW);
		bottomRight.addPlaceholder(IProgressConstants.PROGRESS_VIEW_ID);
		bottomRight.addPlaceholder(ID_GIT_STG_VIEW);
		bottomRight.addPlaceholder(JavaUI.ID_JAVADOC_VIEW);
		bottomRight.addPlaceholder(JavaUI.ID_MEMBERS_VIEW);
		bottomRight.addPlaceholder(JavaUI.ID_SOURCE_VIEW);

		// Top right.
		IFolderLayout topRight = layout.createFolder("topRight", IPageLayout.RIGHT, 0.8f, editorArea);//$NON-NLS-1$
		topRight.addView(IPageLayout.ID_OUTLINE);
		topRight.addPlaceholder(IPageLayout.ID_MINIMAP_VIEW);
		topLeft.addPlaceholder(IDebugUIConstants.ID_VARIABLE_VIEW);
		topLeft.addPlaceholder(IDebugUIConstants.ID_EXPRESSION_VIEW);
		topLeft.addPlaceholder(IDebugUIConstants.ID_BREAKPOINT_VIEW);
		topLeft.addPlaceholder(IDebugUIConstants.ID_MEMORY_VIEW);
	}

	private void addViewIfPresent(IFolderLayout layout, String viewID) {
		IViewDescriptor descriptor = PlatformUI.getWorkbench().getViewRegistry().find(viewID);
		if (descriptor != null) {
			layout.addView(viewID);
		}
		else {
			layout.addPlaceholder(viewID);
		}
	}
}


