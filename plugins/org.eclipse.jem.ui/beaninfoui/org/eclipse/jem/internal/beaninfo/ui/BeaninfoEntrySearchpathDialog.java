/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: BeaninfoEntrySearchpathDialog.java,v $
 *  $Revision: 1.9 $  $Date: 2005/10/04 17:59:17 $ 
 */

import java.text.MessageFormat;
import java.util.*;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.dialogs.*;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoEntry;
import org.eclipse.jem.internal.beaninfo.core.SearchpathEntry;

/**
 * This dialog is used to display and modify the search path
 * within a BeaninfoEntry.
 * @version 	1.0
 * @author
 */
public class BeaninfoEntrySearchpathDialog extends Dialog {
	protected BPBeaninfoListElement infoElement;
	protected IJavaProject jProject;
	//protected ListDialogField listField;
	
	private Composite top;
	private Table table;
	private TableViewer tableViewer;
	private Label label;
	private Composite buttonsBar;
	private Button upbutton;
	private Button downButton;
	private Button choosePackagesButton;
	private Button removeButton;
	private List tableElements;
	private boolean enabled = true;

	public BeaninfoEntrySearchpathDialog(
		Shell parentShell,
		BPBeaninfoListElement infoElement,
		IJavaProject jProject) {
		super(parentShell);

		this.infoElement = infoElement;
		this.jProject = jProject;
		this.tableElements = new ArrayList();
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(
			MessageFormat.format(
				BeanInfoUIMessages.SearchPathDialog_ModifySearchPaths, 
				new Object[] { infoElement.getEntry().getPath().toString()}));
	}

	protected Control createDialogArea(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setLayoutData(new GridData(GridData.FILL_BOTH));
		top.setLayout(new GridLayout(2, false));
		
		label = new Label(top, SWT.NONE);
		label.setText(BeanInfoUIMessages.SearchPathDialog_Desc_Label);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		label.setLayoutData(gd);
		
		createTable();
		createButtonsBar();

		updatePackagesList();
		
		updateButtons();

		return top;
	}

	/**
	 * This method initializes table	
	 *
	 */
	private void createTable() {
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		table = new Table(top, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table.setLayoutData(gridData1);
		table.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				updateButtons();
			}
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		tableViewer = new TableViewer(table);
		//tableViewer.setSorter(new BIListElementSorter());
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new SearchPathListLabelProvider(jProject));
		tableViewer.setInput(tableElements);
	}

	/**
	 * This method initializes buttonsBar	
	 *
	 */
	private void createButtonsBar() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = org.eclipse.swt.SWT.VERTICAL;
		rowLayout.marginLeft = 3;
		rowLayout.fill = true;
		GridData gridData2 = new org.eclipse.swt.layout.GridData();
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		buttonsBar = new Composite(top, SWT.NONE);
		buttonsBar.setLayoutData(gridData2);
		buttonsBar.setLayout(rowLayout);
		upbutton = new Button(buttonsBar, SWT.NONE);
		upbutton.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__searchpath_up_button);
		upbutton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
				Iterator selItr = selected.iterator();
				while (selItr.hasNext()) {
					Object element = selItr.next();
					int oldIndex = tableElements.indexOf(element);
					if(oldIndex>0){
						tableElements.remove(element);
						tableElements.add(--oldIndex, element);
					}
				}
				tableViewer.refresh();
				pageChanged();
			}
		});
		downButton = new Button(buttonsBar, SWT.NONE);
		downButton.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__searchpath_down_button);
		downButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
				Iterator selItr = selected.iterator();
				while (selItr.hasNext()) {
					Object element = selItr.next();
					int oldIndex = tableElements.indexOf(element);
					if(oldIndex<(tableElements.size()-1)){
						tableElements.remove(element);
						tableElements.add(++oldIndex, element);
					}
				}
				tableViewer.refresh();
				pageChanged();
			}
		});
		new Label(buttonsBar, SWT.NONE);
		choosePackagesButton = new Button(buttonsBar, SWT.NONE);
		choosePackagesButton.setText(BeanInfoUIMessages.SearchPathDialog_ChoosePackages);
		choosePackagesButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				final List elementsToAdd = choosePackages();
				if (elementsToAdd != null && !elementsToAdd.isEmpty()) {
					tableElements.addAll(elementsToAdd);
					tableViewer.refresh();
					table.getDisplay().asyncExec(new Runnable(){
						public void run() {
							tableViewer.setSelection(new StructuredSelection(elementsToAdd));
						};
					});
				}
			}
		});
		new Label(buttonsBar, SWT.NONE);
		removeButton = new Button(buttonsBar, SWT.NONE);
		removeButton.setText(BeanInfoUIMessages.SearchPathDialog_Remove);
		removeButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
				tableElements.removeAll(selected);
				tableViewer.refresh();
				pageChanged();
			}
		});
	}

	
	protected void pageChanged() {
		// TODO Auto-generated method stub
		
	}

	protected void updateButtons() {
		if(top!=null && !top.isDisposed()){
			upbutton.setEnabled(enabled && canMoveUp());
			downButton.setEnabled(enabled && canMoveDown());
			removeButton.setEnabled(enabled && !tableViewer.getSelection().isEmpty());
			choosePackagesButton.setEnabled(enabled);
		}
	}
	
	private boolean canMoveDown() {
		int[] indc= table.getSelectionIndices();
		int k= tableElements.size() - 1;
		for (int i= indc.length - 1; i >= 0 ; i--, k--) {
			if (indc[i] != k) {
				return true;
			}
		}
		return false;
	}

	private boolean canMoveUp() {
		int[] indc= table.getSelectionIndices();
		for (int i= 0; i < indc.length; i++) {
			if (indc[i] != i) {
				return true;
			}
		}
		return false;
	}

	private void updatePackagesList() {
		if (infoElement.getEntry().getKind() == BeaninfoEntry.BIE_PLUGIN) {
			// We don't allow these to be updated. They should only be used through
			// registered mechanisms and we won't be showing them here normally.
			label.setText(BeanInfoUIMessages.SearchPathDialog_NotEditable_INFO_); 
			updateEnabledState(false);
			return;
		}
		tableElements.clear();
		tableElements.addAll(Arrays.asList(infoElement.getSearchpaths()));
		tableViewer.refresh();
	}

	private void updateEnabledState(boolean b) {
		enabled = b;
		if(top!=null && !top.isDisposed()){
			label.setEnabled(enabled);
			table.setEnabled(enabled);
			updateButtons();
		}
	}

	protected void okPressed() {
		if (table.isEnabled()) {
			// If the field is not enabled, then there is no update to perform. We don't
			// want to accidentially wipe out the search paths in this case.

			// Override to put the list of elements back into the BPBeaninfoListElement
			// Until then they aren't actually updated.
			infoElement.setSearchpaths(
				(BPSearchListElement[]) tableElements.toArray(new BPSearchListElement[tableElements.size()]));
		}
		super.okPressed();
	}

	/**
	 * Choose the packages that should be in the search path.
	 */
	private List choosePackages() {

		IPackageFragmentRoot[] roots = getBeaninfoRoots();
		List newPackageNames = new ArrayList();
		if (roots != null) {
			// It's within the workspace, so we can get the roots and packages.
			ISelectionStatusValidator validator = new PackagesWorkbookPage.ChoosePackagesSelectionValidator();

			// Show up to the package fragment, don't show any contents of it.
			Class[] acceptedClasses = new Class[] { IPackageFragmentRoot.class, IPackageFragment.class };
			Object[] rejectedFragments = getFilteredExistingEntries(roots);

			// Only show package fragments that have children (i.e. there classes in it, not interested
			// in intermediate ones that have no classes defined in them. Those are filtered out.
			TypedViewerFilter filter = new TypedViewerFilter(acceptedClasses, rejectedFragments) {
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					if (super.select(viewer, parentElement, element)) {
						if (element instanceof IPackageFragment) {
							IPackageFragment pkg = (IPackageFragment) element;
							try {
								return pkg.hasChildren();
							} catch (JavaModelException e) {
							}
							return false;
						} else
							return true;
					}
					return false;
				}
			};

			ITreeContentProvider provider = new PackageOnlyContentProvider();

			ILabelProvider labelProvider = new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT);
			ElementTreeSelectionDialog dialog =
				new ElementTreeSelectionDialog(getShell(), labelProvider, provider);
			dialog.setTitle(BeanInfoUIMessages.BeaninfoPathsBlock_UI__addsearchpath_title); 

			dialog.setValidator(validator);
			dialog.setMessage(BeanInfoUIMessages.BeaninfoPathsBlock_UI__addsearchpath_description); 
			dialog.addFilter(filter);
			dialog.setInput(Arrays.asList(getBeaninfoRoots()));

			if (dialog.open() == Window.OK) {
				Object[] elements = dialog.getResult();
				for (int i = 0; i < elements.length; i++) {
					newPackageNames.add(((IPackageFragment) elements[i]).getElementName());
				}
			}
		} else {
			// It's not within the workspace
			final List existingPackagenames = getFilteredExistingEntries();
			IInputValidator validator = new IInputValidator() {
				public String isValid(String newText) {
					if (existingPackagenames.contains(newText))
						return BeanInfoUIMessages.SearchPathDialog_PackagePresent_INFO_; 
						
					IStatus status = JavaConventions.validatePackageName(newText);
					return status.getSeverity() == IStatus.OK ? null : status.getMessage();
				}
			};
			InputDialog dialog = new InputDialog(getShell(), BeanInfoUIMessages.SearchPathDialog_InputDialog_Title, BeanInfoUIMessages.SearchPathDialog_InputDialog_Message, null, validator); 
			if (dialog.open() == Window.OK)
				newPackageNames.add(dialog.getValue());
		}

		if (newPackageNames.size() == 0)
			return Collections.EMPTY_LIST;

		List newElements = new ArrayList(newPackageNames.size());
		for (int i = 0; i < newPackageNames.size(); i++)
			newElements.add(
				new BPSearchListElement(new SearchpathEntry((String) newPackageNames.get(i)), false, false, false));
		return newElements;
	}
	/**
	 * Return the packagefragment roots for the given beaninfo entry.
	 * Return null if it can be handled (i.e. not in a project some where).
	 */
	protected IPackageFragmentRoot[] getBeaninfoRoots() {
		IPackageFragmentRoot[] roots = null;
		if (infoElement.getEntry().getKind() != BeaninfoEntry.BIE_PLUGIN) {
			IClasspathEntry resolved = JavaCore.getResolvedClasspathEntry(((BeaninfoEntry) infoElement.getEntry()).getClasspathEntry());
			IResource res = ResourcesPlugin.getWorkspace().getRoot().findMember(resolved.getPath());
			if (res != null && res.exists()) {
				if (res instanceof IProject) {
					// It is a project itself.
					IJavaProject jp = (IJavaProject) JavaCore.create(res);
					try {
						if (jp != null)
							roots = jp.getPackageFragmentRoots(); // All of the roots in the project are applicable.
					} catch (JavaModelException e) {
					}
				} else {
					// It is within another project
					IProject containingProject = res.getProject();
					IJavaProject jp = JavaCore.create(containingProject);
					if (jp != null) {
						// The roots if this is in the classpath of this project
						try {
							IPackageFragmentRoot root = jp.findPackageFragmentRoot(resolved.getPath());
							if (root != null)
								roots = new IPackageFragmentRoot[] { root };
						} catch (JavaModelException e) {
						}
					}
				}
			}
		}
		return roots;
	}

	/**
	 * Return the list of entries that already are in the search path
	 * so that they don't show up in the list.
	 */
	protected Object[] getFilteredExistingEntries(IPackageFragmentRoot[] roots) {
		List entries = tableElements;
		List fragments = new ArrayList(entries.size());
		Iterator itr = entries.iterator();
		while (itr.hasNext()) {
			BPSearchListElement elem = (BPSearchListElement) itr.next();
			fragments.addAll(getPackages(elem, roots));
		}
		return fragments.toArray();
	}
	
	/**
	 * Return the list of entries that already are in the search path
	 * so that they don't show up in the list. This one is just the package names.
	 */
	protected List getFilteredExistingEntries() {
		List entries = tableElements;
		List names = new ArrayList(entries.size());
		Iterator itr = entries.iterator();
		while (itr.hasNext()) {
			BPSearchListElement elem = (BPSearchListElement) itr.next();
			names.add(((SearchpathEntry) elem.getEntry()).getPackage());
		}
		return names;
	}

	protected List getPackages(BPSearchListElement element, IPackageFragmentRoot[] roots) {
		String packageName = ((SearchpathEntry) element.getEntry()).getPackage();
		if (packageName == null)
			return Collections.EMPTY_LIST;

		try {
			List packages = new ArrayList(10);
			for (int i = 0; i < roots.length; i++) {
				IJavaElement[] pfs = roots[i].getChildren();
				for (int j = 0; j < pfs.length; j++)
					if (pfs[j].getElementType() == IJavaElement.PACKAGE_FRAGMENT
						&& pfs[j].getElementName().equals(packageName)) {
						packages.add(pfs[j]);
						break;
					}
			}
			return packages;
		} catch (JavaModelException e) {
		}
		return Collections.EMPTY_LIST;
	}
}
