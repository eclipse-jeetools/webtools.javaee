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
package org.eclipse.jst.j2ee.internal;

import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.internal.web.operations.WebProjectInfo;
import org.eclipse.jst.j2ee.internal.web.operations.WebProjectPropertiesUpdateOperation;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.modulecore.ModuleCore;

/**
 * @author jsholl
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class J2EEPropertiesPage extends PropertyPage implements J2EEPropertiesConstants {
	protected IProject project = null;
	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	Text contextRootNameField, webContentFolderField;
	protected WebProjectInfo wtWebProjectInfo = new WebProjectInfo();

	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Control control = null;
		IProject aProject = getJ2EEProject();
		if (aProject != null) {
			Composite containerGroup = new Composite(parent, SWT.NONE);
			control = containerGroup;
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;

			containerGroup.setLayout(layout);

			fillInformation(aProject, containerGroup);
		}
		return control;
	}

	private void fillInformation(IProject p, Composite c) {
		try {
			if (EARNatureRuntime.hasRuntime(p)) {
				EARNatureRuntime nature = EARNatureRuntime.getRuntime(p);
				fillJ2EELevel(nature, c);
			} else if (EJBNatureRuntime.hasRuntime(p)) {
				fillEJBLevel(p, c);
			} else if (ApplicationClientNatureRuntime.hasRuntime(p)) {
				fillAppClientLevel(p, c);
			} else if (ConnectorNatureRuntime.hasRuntime(p)) {
				fillConnectorLevel(p, c);
			}
			//Do: need to rework based on Module
			//else if (J2EEWebNatureRuntime.hasRuntime(p)) {
				//fillWebLevel(p, c);
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param p
	 * @param c
	 */
	private void fillWebLevel(IProject p, Composite c) {
		int servletVersion = getModuleServletVersion();
		
		//fillJ2EELevel(nature,c);
		Label label = new Label(c, SWT.NONE);
		//label.setText(WEB_LEVEL + " " + nature.getModuleVersionText()); //$NON-NLS-1$
		label.setText(WEB_LEVEL + " " + getModuleServletVersion()); //$NON-NLS-1$
		String moduleDesc = null;
		//switch (nature.getModuleVersion()) {
		switch ( servletVersion ) {
			case J2EEVersionConstants.WEB_2_2_ID :
				moduleDesc = WEB_22_DESCRIPTION;
				break;
			case J2EEVersionConstants.WEB_2_3_ID :
				moduleDesc = WEB_23_DESCRIPTION;
				break;
			case J2EEVersionConstants.WEB_2_4_ID :
			default :
				moduleDesc = WEB_24_DESCRIPTION;
				break;
		}
		fillDescription(c, moduleDesc);
		fillContextRoot(p, c);
		fillWebContentFolderName(p, c);
	}


	/**
	 * @param p
	 * @param c
	 */
	private void fillWebContentFolderName(IProject p, Composite c) {
		// TODO Auto-generated method stub
		Label webContentFolderLabel = new Label(c, SWT.NULL);
		webContentFolderLabel.setText(WEB_CONTENT_FOLDER_NAME);
		GridData data = new GridData();
		data.horizontalIndent = 15;
		webContentFolderLabel.setLayoutData(data);

		webContentFolderField = new Text(c, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		webContentFolderField.setLayoutData(data);
		webContentFolderField.setEditable(true);

		String s = getModuleServerRoot().getName();
		webContentFolderField.setText(s);

		webContentFolderField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String newWebContent = webContentFolderField.getText();
				validateWebContentName(newWebContent);
			}
		});

	}

	private void fillContextRoot(IProject p, Composite c) {
		Label contextRootLabel = new Label(c, SWT.NULL);
		contextRootLabel.setText(WEB_CONTEXT_ROOT);
		GridData data = new GridData();
		data.horizontalIndent = 15;
		contextRootLabel.setLayoutData(data);

		contextRootNameField = new Text(c, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		contextRootNameField.setLayoutData(data);
		contextRootNameField.setEditable(true);

		String s = getModuleContextRoot();
		contextRootNameField.setText(s);

		contextRootNameField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String newContextRoot = contextRootNameField.getText();
				validateContextRoot(newContextRoot);
			}
		});
	}

	/**
	 * @param p
	 * @param c
	 */
	private void fillConnectorLevel(IProject p, Composite c) {
		ConnectorNatureRuntime nature = ConnectorNatureRuntime.getRuntime(p);
		//fillJ2EELevel(nature, c);
		Label label = new Label(c, SWT.NONE);
		label.setText(CONNECTOR_LEVEL + " " + nature.getModuleVersionText()); //$NON-NLS-1$
		String moduleDesc = null;
		switch (nature.getModuleVersion()) {
			case J2EEVersionConstants.JCA_1_0_ID :
				moduleDesc = CONNECTOR_10_DESCRIPTION;
				break;
			case J2EEVersionConstants.JCA_1_5_ID :
			default :
				moduleDesc = CONNECTOR_15_DESCRIPTION;
				break;
		}
		fillDescription(c, moduleDesc);
	}

	/**
	 * @param p
	 * @param c
	 */
	private void fillAppClientLevel(IProject p, Composite c) {
		ApplicationClientNatureRuntime nature = ApplicationClientNatureRuntime.getRuntime(p);
		//fillJ2EELevel(nature, c);
		Label label = new Label(c, SWT.NONE);
		label.setText(APP_CLIENT_LEVEL + " " + nature.getModuleVersionText()); //$NON-NLS-1$
		String moduleDesc = null;
		switch (nature.getModuleVersion()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				moduleDesc = APP_CLIENT_12_DESCRIPTION;
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				moduleDesc = APP_CLIENT_13_DESCRIPTION;
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				moduleDesc = APP_CLIENT_14_DESCRIPTION;
				break;
		}
		fillDescription(c, moduleDesc);
	}

	/**
	 * @param p
	 * @param c
	 */
	private void fillEJBLevel(IProject p, Composite c) {
		EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(p);
		//fillJ2EELevel(nature, c);
		Label label = new Label(c, SWT.NONE);
		label.setText(EJB_LEVEL + " " + nature.getModuleVersionText()); //$NON-NLS-1$
		String moduleDesc = null;
		switch (nature.getModuleVersion()) {
			case J2EEVersionConstants.EJB_1_1_ID :
				moduleDesc = EJB_11_DESCRIPTION;
				break;
			case J2EEVersionConstants.EJB_2_0_ID :
				moduleDesc = EJB_20_DESCRIPTION;
				break;
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				moduleDesc = EJB_21_DESCRIPTION;
				break;
		}
		fillDescription(c, moduleDesc);
	}

	private void fillDescription(Composite c, String s) {
		GridData data = new GridData();
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		Label label = new Label(c, SWT.NONE);
		label.setLayoutData(data);
		label.setText(DESCRIPTION);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.horizontalSpan = 2;
		data.horizontalIndent = 15;
		data.widthHint = 250;
		data.heightHint = 70;
		Text text = new Text(c, SWT.V_SCROLL | SWT.BORDER | SWT.MULTI | SWT.WRAP);
		text.setLayoutData(data);
		text.setTextLimit(80);
		text.setSize(300, 120);
		text.setEditable(false);
		text.setText(s);
	}

	private void fillJ2EELevel(J2EENature nature, Composite c) {
		Label label = new Label(c, SWT.NONE);
		label.setText(J2EE_LEVEL + " " + nature.getJ2EEVersionText()); //$NON-NLS-1$
		String desc = null;
		switch (nature.getJ2EEVersion()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				desc = J2EE_12_DESCRIPTION;
				break;
			case J2EEVersionConstants.J2EE_1_3_ID :
				desc = J2EE_13_DESCRIPTION;
				break;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				desc = J2EE_14_DESCRIPTION;
				break;
		}
		fillDescription(c, desc);
	}

	private IProject getJ2EEProject() {
		if (project == null) {
			Object element = getElement();
			if (element == null) {
				return null;
			}
			if (element instanceof IProject) {
				project = isJ2EEProject((IProject) element) ? (IProject) element : null;
				return project;
			}
			return null;
		}
		return project;
	}

	private static boolean isJ2EEProject(IProject p) {
		return EARNatureRuntime.hasRuntime(p) || /* //To Do: need to rework based on Module J2EEWebNatureRuntime.hasRuntime(p) || */ EJBNatureRuntime.hasRuntime(p) || ApplicationClientNatureRuntime.hasRuntime(p) || ConnectorNatureRuntime.hasRuntime(p);
	}



	private IHeadlessRunnableWithProgress getWebPropertiesUpdateOperation() {
		IProject aProject = getJ2EEProject();
		wtWebProjectInfo.setProject(aProject);
		int servletLevel = getModuleServletVersion();
		int jspLevel = getModuleJSPVersion();
				
		if (getContextRoot() != null)
			wtWebProjectInfo.setContextRoot(getContextRoot());
		if (getModuleServerRootName() != null)
			wtWebProjectInfo.setWebContentName(getModuleServerRootName());
		wtWebProjectInfo.setJSPLevel(jspLevel);
		wtWebProjectInfo.setServletLevel(servletLevel);

		return new WebProjectPropertiesUpdateOperation(wtWebProjectInfo);
	}

	/**
	 * @return
	 */
	private String getModuleServerRootName() {
		return (webContentFolderField != null) ? webContentFolderField.getText() : null;
	}

	/**
	 * @return
	 */
	private String getContextRoot() {
		return (contextRootNameField != null) ? contextRootNameField.getText() : null;
	}

	protected boolean hasUpdatedInformation() {
		return hasContextRootChanged() || hasWebContentNameChanged();

	}

	private boolean hasContextRootChanged() {
		String oldContextRoot = getModuleContextRoot();
		return oldContextRoot == null || !oldContextRoot.equals(getContextRoot());
	}

	private boolean hasWebContentNameChanged() {
		String oldWebContentName = getModuleServerRoot().getName();
		return oldWebContentName == null || !oldWebContentName.equals(getModuleServerRootName());
	}

	protected void performDefaults() {
		super.performDefaults();

       	String contextRoot = getModuleContextRoot();
       	String moduleServerRoot = getModuleServerRoot().getName();

		if (contextRootNameField != null)
			contextRootNameField.setText(contextRoot);

		if (webContentFolderField != null)
			webContentFolderField.setText(moduleServerRoot);
	}

	public boolean performOk() {
		boolean retVal = true;
		//IProject aProject = getJ2EEProject();
		// if the project isn't open, OK worked.
		
		//To Do: need to rework based on  Module
		
		/*
		if ( J2EEWebNatureRuntime.hasRuntime(aProject)) {
			WorkspaceModifyComposedOperation composedOp = new WorkspaceModifyComposedOperation();
			if (hasUpdatedInformation()) {
				IHeadlessRunnableWithProgress runnable = getWebPropertiesUpdateOperation();
				IRunnableWithProgress op = new RunnableWithProgressWrapper(runnable);
				composedOp.addRunnable(op);
				Shell shell = getControl().getShell();
				try {
					// perform a validateEdit to make sure we can write to the WebSettings file.
					WebSettingsStateInputProvider wssip = new WebSettingsStateInputProvider(aProject);
					ResourceStateValidatorImpl rsvi = new ResourceStateValidatorImpl(wssip);
					ValidateEditListener vel = new ValidateEditListener(null, rsvi);
					vel.setShell(shell);
					if (!vel.validateState().isOK())
						return false;
					new ProgressMonitorDialog(shell).run(false, true, composedOp);
				} catch (InvocationTargetException e) {
					Throwable t = e.getTargetException();
					if (t instanceof CoreException) {
						ErrorDialog.openError(getShell(), IDEWorkbenchMessages.getString("InternalError"), //$NON-NLS-1$
									e.getLocalizedMessage(), ((CoreException) t).getStatus());
						return false;
					}
					Logger.getLogger().logError(e);
					e.printStackTrace();
					return false;
				} catch (InterruptedException e) {
					// cancelled
					return false;
				}
			}
		} */
		return retVal;
	}

	public void validateWebContentName(String name) {
		String msg = WebPropertiesUtil.validateWebContentName(this.getModuleServerRootName(), getJ2EEProject(), null);
		if (msg != null) {
			setValid(false);
			setErrorMessage(msg);
		} else {
			setValid(true);
			setErrorMessage(null);
		}
	}



	public void validateContextRoot(String name) {
		boolean bValid = true;
		if (name == null || name.length() == 0) { //$NON-NLS-1$
			//  this was added because the error message shouldnt be shown initially. It should be
			// shown only if context
			// root field is edited to
			this.setErrorMessage(ProjectSupportResourceHandler.getString("Context_Root_cannot_be_empty_2")); //$NON-NLS-1$
			bValid = false;
		}

		if (name.trim().equals(name)) {
			StringTokenizer stok = new StringTokenizer(name, "."); //$NON-NLS-1$
			outer : while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				for (int i = 0; i < token.length(); i++) {
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						if (Character.isWhitespace(token.charAt(i)) == false) {
							this.setErrorMessage(ProjectSupportResourceHandler.getString("The_character_is_invalid_in_a_context_root", new Object[]{(new Character(token.charAt(i))).toString()})); //$NON-NLS-1$
							bValid = false;
						}
					}
				}
			}
		} // en/ end of if(name.trim
		else {
			this.setErrorMessage(ProjectSupportResourceHandler.getString("Names_cannot_begin_or_end_with_whitespace_5")); //$NON-NLS-1$
			bValid = false;
		}
		if (bValid)
			this.setErrorMessage(null);
		this.setValid(bValid);

	}


	protected String getModuleContextRoot() {
		WebArtifactEdit webEdit = null;
		try{
			webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
       		if (webEdit != null) {
       			return webEdit.getServerContextRoot();
       		}			
		} finally{
			if( webEdit != null )
				webEdit.dispose();
		}
		return null;
	}
	
	protected IContainer getModuleServerRoot() {
		return WebPropertiesUtil.getModuleServerRoot(project);
	}
	
	protected int getModuleServletVersion() {
		WebArtifactEdit webEdit = null;
		try{
			webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
       		if (webEdit != null) {
       			return webEdit.getServletVersion();
       		}			
		} finally{
			if( webEdit != null )
				webEdit.dispose();
		}
		return J2EEVersionConstants.SERVLET_2_2;
	}
	
	protected int getModuleJSPVersion() {
		WebArtifactEdit webEdit = null;
		try{
			webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
       		if (webEdit != null) {
       			return webEdit.getJSPVersion();
       		}			
		} finally{
			if( webEdit != null )
				webEdit.dispose();
		}
		return J2EEVersionConstants.SERVLET_2_2;
	}

}