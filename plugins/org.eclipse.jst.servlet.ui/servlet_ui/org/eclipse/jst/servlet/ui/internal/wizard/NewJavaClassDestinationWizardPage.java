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
package org.eclipse.jst.servlet.ui.internal.wizard;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.internal.ui.dialogs.TypeSelectionDialog;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.internal.dialogs.TypeSearchEngine;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetHelper;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.ui.WTPWizardPage;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class NewJavaClassDestinationWizardPage extends WTPWizardPage {
	private Text folderText;
	private Button folderButton;
	private Text packageText;
	private Button packageButton;
	protected Text classText;
	private Text superText;
	private Button superButton;

	/**
	 * @param model
	 * @param pageName
	 */
	public NewJavaClassDestinationWizardPage(WTPOperationDataModel model, String pageName, String pageDesc, String pageTitle) {
		super(model, pageName);
		setDescription(pageDesc);
		this.setTitle(pageTitle);
		setPageComplete(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	protected String[] getValidationPropertyNames() {
		return new String[]{NewJavaClassDataModel.SOURCE_FOLDER, NewJavaClassDataModel.JAVA_PACKAGE, NewJavaClassDataModel.CLASS_NAME, NewJavaClassDataModel.SUPERCLASS};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.common.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	protected Composite createTopLevelComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		composite.setLayout(layout);
		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		data.widthHint = 300;
		composite.setLayoutData(data);

		// folder
		Label folderLabel = new Label(composite, SWT.LEFT);
		folderLabel.setText(IWebWizardConstants.FOLDER_LABEL);
		folderLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		folderText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		folderText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(folderText, NewJavaClassDataModel.SOURCE_FOLDER, null);

		folderButton = new Button(composite, SWT.PUSH);
		folderButton.setText(IWebWizardConstants.BROWSE_BUTTON_LABEL);
		folderButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		folderButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				handleFolderButtonPressed();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				// Do nothing
			}
		});

		// package
		Label packageLabel = new Label(composite, SWT.LEFT);
		packageLabel.setText(IWebWizardConstants.JAVA_PACKAGE_LABEL);
		packageLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		packageText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		packageText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(packageText, NewJavaClassDataModel.JAVA_PACKAGE, null);

		packageButton = new Button(composite, SWT.PUSH);
		packageButton.setText(IWebWizardConstants.BROWSE_BUTTON_LABEL);
		packageButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		packageButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				handlePackageButtonPressed();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				// Do nothing
			}
		});

		// class name
		Label classLabel = new Label(composite, SWT.LEFT);
		classLabel.setText(IWebWizardConstants.CLASS_NAME_LABEL);
		classLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		classText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		classText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(classText, NewJavaClassDataModel.CLASS_NAME, null);

		new Label(composite, SWT.LEFT);

		// Separator label
		Label seperator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
		data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 3;
		seperator.setLayoutData(data);

		// superclass
		Label superLabel = new Label(composite, SWT.LEFT);
		superLabel.setText(IWebWizardConstants.SUPERCLASS_LABEL);
		superLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		superText = new Text(composite, SWT.SINGLE | SWT.BORDER);
		superText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		synchHelper.synchText(superText, NewJavaClassDataModel.SUPERCLASS, null);

		superButton = new Button(composite, SWT.PUSH);
		superButton.setText(IWebWizardConstants.BROWSE_BUTTON_LABEL);
		superButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		superButton.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				handleSuperButtonPressed();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				// Do nothing
			}
		});

		folderText.setFocus();
		PlatformUI.getWorkbench().getHelpSystem().setHelp(composite, getInfopopID());
		return composite;
	}

	/**
	 * Browse for a new Destination Folder
	 */
	protected void handleFolderButtonPressed() {
		ISelectionStatusValidator validator = getContainerDialogSelectionValidator();
		ViewerFilter filter = getContainerDialogViewerFilter();
		ITreeContentProvider contentProvider = new WorkbenchContentProvider();
		ILabelProvider labelProvider = new DecoratingLabelProvider(new WorkbenchLabelProvider(), PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator());
		ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(), labelProvider, contentProvider);
		dialog.setValidator(validator);
		dialog.setTitle(IWebWizardConstants.CONTAINER_SELECTION_DIALOG_TITLE);
		dialog.setMessage(IWebWizardConstants.CONTAINER_SELECTION_DIALOG_DESC);
		dialog.addFilter(filter);
		dialog.setInput(J2EEUIPlugin.getWorkspace().getRoot());
		IProject project = ((NewJavaClassDataModel)model).getTargetProject();
		if (project != null)
			dialog.setInitialSelection(project);
		if (dialog.open() == Window.OK) {
			Object element = dialog.getFirstResult();
			try {
				if (element instanceof IContainer) {
					IContainer container = (IContainer) element;
					folderText.setText(container.getFullPath().toString());
					// dealWithSelectedContainerResource(container);
				}
			} catch (Exception ex) {
				// Do nothing
			}

		}
	}

	protected void handlePackageButtonPressed() {
		IPackageFragmentRoot packRoot = ((NewJavaClassDataModel) model).getJavaPackageFragmentRoot();
		if (packRoot == null)
			return;
		IJavaElement[] packages = null;
		try {
			packages = packRoot.getChildren();
		} catch (JavaModelException e) {
			// Do nothing
		}
		if (packages == null)
			packages = new IJavaElement[0];

		ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT));
		dialog.setTitle(IWebWizardConstants.PACKAGE_SELECTION_DIALOG_TITLE);
		dialog.setMessage(IWebWizardConstants.PACKAGE_SELECTION_DIALOG_DESC);
		dialog.setEmptyListMessage(IWebWizardConstants.PACKAGE_SELECTION_DIALOG_MSG_NONE);
		dialog.setElements(packages);
		if (dialog.open() == Window.OK) {
			IPackageFragment fragment = (IPackageFragment) dialog.getFirstResult();
			if (fragment != null) {
				packageText.setText(fragment.getElementName());
			} else {
				packageText.setText(IWebWizardConstants.EMPTY_STRING);
			}
		}
	}

	protected void handleSuperButtonPressed() {
		getControl().setCursor(new Cursor(getShell().getDisplay(), SWT.CURSOR_WAIT));
		IPackageFragmentRoot packRoot = ((NewJavaClassDataModel) model).getJavaPackageFragmentRoot();
		if (packRoot == null)
			return;

		// this eliminates the non-exported classpath entries
		final IJavaSearchScope scope = TypeSearchEngine.createJavaSearchScopeForAProject(packRoot.getJavaProject(), true, true);

		// This includes all entries on the classpath. This behavior is
		// identical
		// to the Super Class Browse Button on the Create new Java Class Wizard
		// final IJavaSearchScope scope = SearchEngine.createJavaSearchScope(
		// new IJavaElement[] {root.getJavaProject()} );
		TypeSelectionDialog dialog = new TypeSelectionDialog(getShell(), getWizard().getContainer(), IJavaSearchConstants.CLASS, scope);
		dialog.setTitle(IWebWizardConstants.SUPERCLASS_SELECTION_DIALOG_TITLE);
		dialog.setMessage(IWebWizardConstants.SUPERCLASS_SELECTION_DIALOG_DESC);

		// if (getTypeRegionData().getSuperClass() != null)
		// dialog.setFilter(getTypeRegionData().getSuperClass().getElementName());

		if (dialog.open() == Window.OK) {
			IType type = (IType) dialog.getFirstResult();
			String superclassFullPath = IWebWizardConstants.EMPTY_STRING;
			if (type != null) {
				superclassFullPath = type.getFullyQualifiedName();
			}
			superText.setText(superclassFullPath);
			getControl().setCursor(null);
			return;
		}
		getControl().setCursor(null);
	}

	/**
	 * Returns a new instance of the Selection validator for the Container
	 * Selection Dialog This method can be extended by subclasses, as it does
	 * some basic validation.
	 */
	protected ISelectionStatusValidator getContainerDialogSelectionValidator() {
		return new ISelectionStatusValidator() {
			public IStatus validate(Object[] selection) {
				Status ret = new Status(IStatus.ERROR, J2EEUIPlugin.PLUGIN_ID, IStatus.ERROR, IWebWizardConstants.CONTAINER_SELECTION_DIALOG_VALIDATOR_MESG, null);
				try {
					if (selection[0] != null && !(selection[0] instanceof IProject))
						ret = new Status(IStatus.OK, J2EEUIPlugin.PLUGIN_ID, IStatus.OK, "", null); //$NON-NLS-1$
				} catch (Exception e) {
					//Do nothing
				}
				return ret;
			}
		};
	}
	/**
	 * Returns a new instance of the Selection Listner for the Container
	 * Selection Dialog
	 */
	protected ViewerFilter getContainerDialogViewerFilter() {
		return new ViewerFilter() {
			public boolean select(Viewer viewer, Object parent, Object element) {
				boolean ret = false;
				if (element instanceof IProject) {
					IProject project = (IProject) element;
					return ServerTargetHelper.hasJavaNature(project);
				} else if (element instanceof IFolder) {
					IFolder folder = (IFolder) element;
					// only show source folders
					if (ProjectUtilities.getSourceContainers(folder.getProject()).contains(folder)) {
						ret = true;
					}
				}
				return ret;
			}
		};
	}
}