package org.eclipse.jst.j2ee.internal.ui;
/*
 * Licensed Material - Property of IBM (C) Copyright IBM Corp. 2002 - All
 * Rights Reserved. US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 */
import java.lang.reflect.InvocationTargetException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jst.j2ee.internal.J2EEPropertiesConstants;
import org.eclipse.jst.j2ee.internal.WorkspaceModifyComposedOperation;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.frameworks.internal.ui.RunnableWithProgressWrapper;
import org.eclipse.wst.web.internal.operation.WebProjectPropertiesUpdateOperation;


/**
 * 
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates. To enable and disable the creation of
 * type comments go to Window>Preferences>Java>Code Generation.
 */
public class J2EEPropertiesPage extends PropertyPage implements J2EEPropertiesConstants  {
	private int newSelectedIndex;
	protected IProject project = null;
	private static final int SIZING_TEXT_FIELD_WIDTH = 250;
	Text contextRootNameField, webContentFolderField;

	
	/**
	 * @see org.eclipse.jface.preference.PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Control control = null;
		project = getProject();
		if (project != null) {
			Composite containerGroup = new Composite(parent, SWT.NONE );
			control = containerGroup;
			GridLayout layout = new GridLayout();
			layout.numColumns = 2;
			
			containerGroup.setLayout(layout);
			
			fillInformation(project, containerGroup);
		}
		return control;
	}
	
	private void fillInformation(IProject p, Composite c) {
		try {
			if( J2EEProjectUtilities.getJ2EEProjectType(p).equals( J2EEProjectUtilities.DYNAMIC_WEB) ||
						J2EEProjectUtilities.getJ2EEProjectType(p).equals( J2EEProjectUtilities.STATIC_WEB)	){
				fillContextRoot(p,c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * @param p
	 * @param c
	 */

	private void fillContextRoot(IProject p, Composite c) {
		Label contextRootLabel = new Label(c, SWT.NULL);
		contextRootLabel.setText(J2EEPropertiesConstants.WEB_CONTEXT_ROOT);
		GridData data = new GridData();
		data.horizontalIndent = 15;
		contextRootLabel.setLayoutData(data);
		
		contextRootNameField = new Text(c, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		contextRootNameField.setLayoutData(data);
		contextRootNameField.setEditable(true);
		
		String s = J2EEProjectUtilities.getServerContextRoot(p);
		contextRootNameField.setText(s);
		
		contextRootNameField.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				String newContextRoot = contextRootNameField.getText();
				validateContextRoot(newContextRoot);
			}
        });
		
		
	}


	private IProject getProject() {
		if (project == null) {
			Object element = getElement();
			if (element == null) {
				return null;
			}
			if (element instanceof IProject) {
				project = (IProject)element;
				return project;
			}
			return null;
		}
		return project;
	}

	/**
	 * @return
	 */
	private String getContextRoot() {
		return (contextRootNameField != null) ? contextRootNameField.getText() : null;
	}

	protected boolean hasUpdatedInformation() {
		return hasContextRootChanged();
 	
	 }
	
	private boolean hasContextRootChanged() {
		String oldContextRoot = J2EEProjectUtilities.getServerContextRoot( project );
		if (oldContextRoot == null) return true;
		return !oldContextRoot.equals(getContextRoot());
	}
	

	
	protected void performDefaults() {
		super.performDefaults();
	
		if (this.contextRootNameField != null)
			contextRootNameField.setText(J2EEProjectUtilities.getServerContextRoot(project));
		
	}
	
	
	private IHeadlessRunnableWithProgress getWebPropertiesUpdateOperation() {
		return new WebProjectPropertiesUpdateOperation(project, getContextRoot());
	}
	
	
	public boolean performOk() {
		boolean retVal = true;
	
		// if the project isn't open, OK worked.

			WorkspaceModifyComposedOperation composedOp = new WorkspaceModifyComposedOperation();
			if (hasUpdatedInformation()) {
				IHeadlessRunnableWithProgress runnable= getWebPropertiesUpdateOperation();
				IRunnableWithProgress op= new RunnableWithProgressWrapper(runnable);
				composedOp.addRunnable(op);
				Shell shell= getControl().getShell();
				try {
					new ProgressMonitorDialog(shell).run(false, true, composedOp);
				} catch (InvocationTargetException e) {
					Throwable t = e.getTargetException();
					if (t instanceof CoreException) {
						ErrorDialog.openError(
								getShell(), 
								IDEWorkbenchMessages.InternalError, //$NON-NLS-1$
								e.getLocalizedMessage(),
								((CoreException)t).getStatus()); 
							return false;
					} else {
						Logger.getLogger().logError(e);
						e.printStackTrace();
					}
					return false;
				} catch (InterruptedException e) {
					// cancelled
					return false;
				} 
			}

		return retVal;
	}
	

	 
	 public void validateContextRoot(String name) {  
        boolean bValid = true;
        if (name == null || name.length() ==0 ) { 
            //  this was added because the error message shouldnt be shown initially. It should be shown only if context
            // root field is edited to
            this.setErrorMessage(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Context_Root_cannot_be_empty_2, new Object[0]));
            bValid = false;
        }

        if (name.trim().equals(name)) {
            StringTokenizer stok = new StringTokenizer(name, "."); //$NON-NLS-1$
            outer : while (stok.hasMoreTokens()) {
                String token = stok.nextToken();
                for (int i = 0; i < token.length(); i++) {
                    if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
                        if (Character.isWhitespace(token.charAt(i)) == false) {
                        	this.setErrorMessage( ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.The_character_is_invalid_in_a_context_root , new Object[] {(new Character(token.charAt(i))).toString()}));
                        	bValid = false;
                        }
                    }
                }
            }
        } // en/ end of if(name.trim
        else {
        	this.setErrorMessage(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Names_cannot_begin_or_end_with_whitespace_5, new Object[0]));
        	bValid = false;
        }
        if (bValid)   	this.setErrorMessage(null);
        this.setValid(bValid);
     
    }
	
}
