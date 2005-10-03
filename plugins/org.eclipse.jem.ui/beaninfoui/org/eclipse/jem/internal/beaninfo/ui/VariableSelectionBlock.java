/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;

import java.util.List;

import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.BuildPathDialogAccess;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.PlatformUI;
/*
 *  $RCSfile: VariableSelectionBlock.java,v $
 *  $Revision: 1.7 $  $Date: 2005/10/03 23:06:42 $ 
 */

public class VariableSelectionBlock {
	
	
	private List fExistingPaths;
	
	private Label variableFieldLabel;
	private Text variableFieldText;
	private Button variableFieldButton;
	private String variableFieldTextContent;
	
	private Label extensionFieldLabel;
	private Text extensionFieldText;
	private Button extensionFieldButton;
	private String extensionFieldTextContent;
	
	private CLabel fFullPath;
	
	private IStatus fVariableStatus;
	private IStatus fExistsStatus;
	private IStatus fExtensionStatus;
	
	private String fVariable;
	private IStatusChangeListener fContext;
	
	private boolean fIsEmptyAllowed;
	
	/**
	 * Constructor for VariableSelectionBlock
	 */
	public VariableSelectionBlock(IStatusChangeListener context, List existingPaths, IPath varPath, String lastVarSelection, boolean emptyAllowed) {	
		fContext= context;
		fExistingPaths= existingPaths;
		fIsEmptyAllowed= emptyAllowed;
		fExistsStatus= StatusHelper.OK_STATUS;
		
		//VariableSelectionAdapter adapter= new VariableSelectionAdapter();

		if (varPath != null) {
			variableFieldTextContent = varPath.segment(0);
			extensionFieldTextContent = varPath.removeFirstSegments(1).toString();
		} else {
			variableFieldTextContent = ""; //$NON-NLS-1$
			extensionFieldTextContent = ""; //$NON-NLS-1$
		}
		updateFullTextField();
		fVariableStatus= variableUpdated();
		fExtensionStatus=extensionUpdated();
	}
	
	public IPath getVariablePath() {
		if (fVariable != null) {
			return new Path(fVariable).append(extensionFieldTextContent);
		}
		return null;
	}
	
	public IPath getResolvedPath() {
		if (fVariable != null) {
			IPath entryPath= JavaCore.getClasspathVariable(fVariable);
			if (entryPath != null) {
				return entryPath.append(extensionFieldTextContent);
			}
		}
		return null;
	}	
			
	public void setFocus(Display display) {
		display.asyncExec(new Runnable(){
			public void run() {
				variableFieldText.setFocus();
			}
		});
	}

	
	public Control createControl(Composite parent) {
		Composite inner= new Composite(parent, SWT.NONE);
		inner.setLayout(new GridLayout(3, false));
		GridData gd = new GridData(GridData.FILL_BOTH);
		inner.setLayoutData(gd);
		
		variableFieldLabel = new Label(inner, SWT.NONE);
		variableFieldLabel.setText(BeanInfoUIMessages.VariableSelectionBlock_variable_label);
		variableFieldLabel.setLayoutData(new GridData());
		variableFieldText = new Text(inner, SWT.BORDER|SWT.SINGLE);
		variableFieldText.setText(variableFieldTextContent);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.widthHint = 200;
		variableFieldText.setLayoutData(gd);
		variableFieldText.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				variableFieldTextContent = variableFieldText.getText();
				fVariableStatus = variableUpdated();
				fExistsStatus= getExistsStatus();
				updateFullTextField();
				fContext.statusChanged(StatusHelper.getMostSevere(new IStatus[] { fVariableStatus, fExtensionStatus, fExistsStatus }));
			}
		});
		variableFieldButton = new Button(inner, SWT.PUSH);
		variableFieldButton.setText(BeanInfoUIMessages.VariableSelectionBlock_variable_button);
		variableFieldButton.setLayoutData(new GridData());
		variableFieldButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				String variable= chooseVariable();
				if (variable != null) {
					variableFieldText.setText(variable);
				}
			}
		});
		
		extensionFieldLabel = new Label(inner, SWT.NONE);
		extensionFieldLabel.setText(BeanInfoUIMessages.VariableSelectionBlock_extension_label);
		extensionFieldLabel.setLayoutData(new GridData());
		extensionFieldText = new Text(inner, SWT.BORDER|SWT.SINGLE);
		extensionFieldText.setText(variableFieldTextContent);
		extensionFieldText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		extensionFieldText.addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				extensionFieldTextContent = extensionFieldText.getText();
				fExtensionStatus= extensionUpdated();
				fExistsStatus= getExistsStatus();
				updateFullTextField();
				fContext.statusChanged(StatusHelper.getMostSevere(new IStatus[] { fVariableStatus, fExtensionStatus, fExistsStatus }));
			}
		});
		extensionFieldButton = new Button(inner, SWT.PUSH);
		extensionFieldButton.setText(BeanInfoUIMessages.VariableSelectionBlock_extension_button);
		extensionFieldButton.setLayoutData(new GridData());
		extensionFieldButton.setEnabled(fVariable!=null);
		extensionFieldButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				IPath filePath= chooseExtJar();
				if (filePath != null) {
					extensionFieldText.setText(filePath.toString());
				}
			}
		});
		
		Label label= new Label(inner, SWT.LEFT);
		label.setLayoutData(new GridData());
		label.setText(BeanInfoUIMessages.VariableSelectionBlock_fullpath_label); 
		
		fFullPath= new CLabel(inner, SWT.NONE);
		gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan=2;
		fFullPath.setLayoutData(gd);
		
		updateFullTextField();
		
		setFocus(parent.getDisplay());
		
		return inner;
	}

	private IStatus variableUpdated() {
		fVariable= null;
		
		IStatus status= StatusHelper.OK_STATUS;
		
		String name = variableFieldTextContent;
		if (name.length() == 0) {
			if (!fIsEmptyAllowed) {
				status = StatusHelper.createStatus(IStatus.ERROR, BeanInfoUIMessages.VariableSelectionBlock_error_entername_ERROR_); 
			} else {
				fVariable= ""; //$NON-NLS-1$
			}
		} else if (JavaCore.getClasspathVariable(name) == null) {
			status = StatusHelper.createStatus(IStatus.ERROR, BeanInfoUIMessages.VariableSelectionBlock_error_namenotexists_ERROR_); 
		} else {
			fVariable= name;
		}
		if(extensionFieldButton!=null)
			extensionFieldButton.setEnabled(fVariable != null);
		return status;
	}
	
	private IStatus extensionUpdated() {
		IStatus status= StatusHelper.OK_STATUS;
		String extension = extensionFieldTextContent;
		if (extension.length() > 0 && !Path.ROOT.isValidPath(extension)) {
			status = StatusHelper.createStatus(IStatus.ERROR, BeanInfoUIMessages.VariableSelectionBlock_error_invalidextension_ERROR_); 
		}
		return status;
	}
		
	private IStatus getExistsStatus() {
		IStatus status = StatusHelper.OK_STATUS;
		IPath path= getResolvedPath();
		if (path != null) {
			if (findPath(path)) {
				status = StatusHelper.createStatus(IStatus.ERROR,  BeanInfoUIMessages.VariableSelectionBlock_error_pathexists_ERROR_); 
			} else if (!path.toFile().isFile()) {
				status = StatusHelper.createStatus(IStatus.WARNING,  BeanInfoUIMessages.VariableSelectionBlock_warning_pathnotexists_WARN_); 
			}
		} else {
			status = StatusHelper.createStatus(IStatus.WARNING,  BeanInfoUIMessages.VariableSelectionBlock_warning_pathnotexists_WARN_); 
		}
		return status;
	}
	
	private boolean findPath(IPath path) {
		for (int i= fExistingPaths.size() -1; i >=0; i--) {
			IPath curr= (IPath) fExistingPaths.get(i);
			if (curr.equals(path)) {
				return true;
			}
		}
		return false;
	}	

	private void updateFullTextField() {
		if (fFullPath != null && !fFullPath.isDisposed()) {
			IPath resolvedPath= getResolvedPath();
			if (resolvedPath != null) {
				fFullPath.setText(resolvedPath.toOSString());
			} else {
				fFullPath.setText(""); //$NON-NLS-1$
			}
		}
	}
	
	private Shell getShell() {
		if (fFullPath != null) {
			return fFullPath.getShell();
		}
		return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	}	
	
	public static boolean isArchivePath(IPath path){
		final String[] archiveExtensions = {"jar", "zip"};
		String ext= path.getFileExtension();
		if (ext != null && ext.length() != 0) {
			for (int i= 0; i < archiveExtensions.length; i++) {
				if (ext.equalsIgnoreCase(archiveExtensions[i])) {
					return true;
				}
			}
		}
		return false;
	}
	
	private IPath chooseExtJar() {
		String lastUsedPath= ""; //$NON-NLS-1$
		IPath entryPath= getResolvedPath();
		if (entryPath != null) {
			if (isArchivePath(entryPath)) {
				lastUsedPath= entryPath.removeLastSegments(1).toOSString();
			} else {
				lastUsedPath= entryPath.toOSString();
			}
		}
		
		FileDialog dialog= new FileDialog(getShell(), SWT.SINGLE);
		dialog.setFilterExtensions(new String[] {"*.jar;*.zip"}); //$NON-NLS-1$
		dialog.setFilterPath(lastUsedPath);
		dialog.setText(BeanInfoUIMessages.VariableSelectionBlock_ExtJarDialog_title); 
		String res= dialog.open();
		if (res == null) {
			return null;
		}
		IPath resPath= new Path(res).makeAbsolute();
		IPath varPath= JavaCore.getClasspathVariable(fVariable);
		
		if (!varPath.isPrefixOf(resPath)) {
			return new Path(resPath.lastSegment());
		} else {
			return resPath.removeFirstSegments(varPath.segmentCount()).setDevice(null);
		}
	}

	private String chooseVariable() {
		IPath[] varPaths = BuildPathDialogAccess.chooseVariableEntries(variableFieldButton.getShell(), new IPath[0]);
		String variable = null;
		if(varPaths!=null && varPaths.length>0){
			variable = varPaths[0].toString();
		}
		return variable;
	}
		

}
