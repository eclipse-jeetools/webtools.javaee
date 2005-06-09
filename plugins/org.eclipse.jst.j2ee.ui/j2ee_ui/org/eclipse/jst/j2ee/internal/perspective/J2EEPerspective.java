/*
 * Created on Dec 7, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.perspective;

import org.eclipse.debug.ui.IDebugUIConstants;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class J2EEPerspective implements org.eclipse.ui.IPerspectiveFactory {

	protected static String ID_SERVERS_VIEW = "org.eclipse.wst.server.ui.ServersView"; //$NON-NLS-1$
	//protected static String ID_J2EE_HIERARCHY_VIEW = "org.eclipse.wst.navigator.ui.WTPCommonNavigator"; //$NON-NLS-1$
	protected static String ID_J2EE_HIERARCHY_VIEW = "org.eclipse.wst.navigator.ui.WTPWorkingSetCommonNavigator"; //$NON-NLS-1$

	private static String ID_WST_SNIPPETS_VIEW = "org.eclipse.wst.common.snippets.internal.ui.SnippetsView"; //$NON-NLS-1$	
	private static String ID_EJB_PROJECT_WIZARD = "org.eclipse.jst.ejb.ui.EJBComponentCreationWizard"; //$NON-NLS-1$
	private static String ID_DYNAMIC_WEB_COMPONENT_WIZARD = "org.eclipse.jst.servlet.ui.WebComponentCreationWizard"; //$NON-NLS-1$
	private static String ID_STATIC_WEB_COMPONENT_WIZARD = "org.eclipse.wst.web.ui.internal.wizards.SimpleWebModuleCreation"; //$NON-NLS-1$
	private static final String ID_SEARCH_VIEW = "org.eclipse.search.ui.views.SearchView"; //$NON-NLS-1$
	public static final String ID_CONSOLE_VIEW= "org.eclipse.ui.console.ConsoleView"; //$NON-NLS-1$

	public J2EEPerspective() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineLayout(layout);
		defineActions(layout);
	}
	
	

	public void defineActions(IPageLayout layout) {
		layout.addActionSet("org.eclipse.jst.j2ee.J2eeMainActionSet"); //$NON-NLS-1$
		layout.addActionSet("org.eclipse.jst.j2ee.J2eeMainActionSet2"); //$NON-NLS-1$
		layout.addActionSet("org.eclipse.jst.j2ee.J2eeMainActionSet3"); //$NON-NLS-1$
		layout.addActionSet("org.eclipse.jst.j2ee.J2eeMainActionSet4"); //$NON-NLS-1$
		layout.addActionSet("org.eclipse.jdt.ui.JavaActionSet"); //$NON-NLS-1$

		layout.addActionSet(IDebugUIConstants.LAUNCH_ACTION_SET);
		layout.addActionSet(IDebugUIConstants.DEBUG_ACTION_SET);

		layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);

		layout.addShowViewShortcut(ID_J2EE_HIERARCHY_VIEW);
		layout.addShowViewShortcut(ID_SERVERS_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
		layout.addShowViewShortcut(ID_WST_SNIPPETS_VIEW);
		
		// views - search
		layout.addShowViewShortcut(ID_SEARCH_VIEW);
				// views - debugging
		layout.addShowViewShortcut(ID_CONSOLE_VIEW);

		layout.addNewWizardShortcut(ID_STATIC_WEB_COMPONENT_WIZARD);
		layout.addNewWizardShortcut(ID_DYNAMIC_WEB_COMPONENT_WIZARD);
		layout.addNewWizardShortcut(ID_EJB_PROJECT_WIZARD);

		// Add "new wizards"
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.project");//$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");//$NON-NLS-1$
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");//$NON-NLS-1$

		layout.addShowInPart(ID_J2EE_HIERARCHY_VIEW);
	}

	public void defineLayout(IPageLayout layout) {
		// Editors are placed for free.
		String editorArea = layout.getEditorArea();

		// Top left.
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, (float) 0.26, editorArea);//$NON-NLS-1$
		topLeft.addView(ID_J2EE_HIERARCHY_VIEW);
		topLeft.addPlaceholder(IPageLayout.ID_RES_NAV);

		// Bottom left.
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, (float) 0.66, "topLeft");//$NON-NLS-1$ //$NON-NLS-2$
		bottomLeft.addView(IPageLayout.ID_OUTLINE);

		// Bottom right.
		IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.BOTTOM, (float) 0.66, editorArea);//$NON-NLS-1$
		bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);
		bottomRight.addView(IPageLayout.ID_TASK_LIST);
		bottomRight.addView(IPageLayout.ID_PROP_SHEET);
		bottomRight.addView(ID_SERVERS_VIEW);

		// Fast views
		layout.addFastView(ID_WST_SNIPPETS_VIEW, (float) 0.25);
	}
}



