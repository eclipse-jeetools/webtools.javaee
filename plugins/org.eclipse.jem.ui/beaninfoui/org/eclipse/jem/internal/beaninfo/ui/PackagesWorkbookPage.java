/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PackagesWorkbookPage.java,v $
 *  $Revision: 1.9 $  $Date: 2005/10/03 23:06:42 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import java.util.*;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.*;

import org.eclipse.jem.internal.beaninfo.core.SearchpathEntry;

public class PackagesWorkbookPage implements IBuildSearchPage {
	
	/**
	 * Validator for this workbook page to verify the selections on Choose page.
	 * @version 	1.0
	 * @author
	 */
	public static class ChoosePackagesSelectionValidator implements ISelectionStatusValidator {

		private IStatus fgErrorStatus = StatusHelper.ERROR_STATUS; //$NON-NLS-1$
		private IStatus fgOKStatus = StatusHelper.OK_STATUS;

		public ChoosePackagesSelectionValidator() {
		}

		/*
		 * @see ISelectionValidator#validate(Object)
		 */
		public IStatus validate(Object[] selection) {
			if (isValid(selection)) {
				return fgOKStatus;
			}
			return fgErrorStatus;
		}

		private boolean isValid(Object[] selection) {
			if (selection.length == 0)
				return false;

			for (int i = 0; i < selection.length; i++) {
				if (selection[i] instanceof IPackageFragment)
					continue; // Fragments are always valid
				return false;
			}

			return true;
		}

	}

	
	private Label label = null;
	private Table table = null;
	private Composite buttonBar = null;
	private Button choosePackagesButton = null;
	private Button chooseDefPathsButton = null;
	private Label spacer1 = null;
	private Button removeButton = null;
	// ... ui
	
	private IJavaProject javaProject = null;
	private SearchPathListLabelProvider labelProvider = null;
	private BeaninfoPathsBlock beaninfosPathsBlock = null;
	private IPackageFragmentRoot[][] rootsPerRawEntry;
	private IClasspathEntry[] rawList;
	private TableViewer tableViewer;
	private List packagesList = null;
	private Composite top;

	public PackagesWorkbookPage(IWorkspaceRoot workspaceRoot, BeaninfoPathsBlock beaninfosPathsBlock) {
		this.beaninfosPathsBlock = beaninfosPathsBlock;
		this.packagesList = new ArrayList();
		this.labelProvider = new SearchPathListLabelProvider();
	}

	public Control createControl(Composite parent){
		top = new Composite(parent, SWT.NONE);
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.horizontalSpan = 2;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		top.setLayout(gridLayout);
		label = new Label(top, SWT.NONE);
		label.setText(BeanInfoUIMessages.PackagesWorkbook_LabelText);
		label.setLayoutData(gridData);
		createTable();
		createButtonBar();
		top.setSize(new Point(300, 200));
		updateEnabledStates();
		if(spacer1==null){
			//TODO: 
		}
		return top;
	}

	/**
	 * This method initializes table	
	 *
	 */
	private void createTable() {
		GridData gridData1 = new org.eclipse.swt.layout.GridData();
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		table = new Table(top, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		table.setLayoutData(gridData1);
		table.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				updateButtons();
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
				widgetSelected(e);
			}
		});
		tableViewer = new TableViewer(table);
		tableViewer.setLabelProvider(labelProvider);
		tableViewer.setSorter(new SPListElementSorter());
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setInput(packagesList);
	}

	/**
	 * This method initializes buttonBar	
	 *
	 */
	private void createButtonBar() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = org.eclipse.swt.SWT.VERTICAL;
		rowLayout.fill = true;
		GridData gridData2 = new org.eclipse.swt.layout.GridData();
		gridData2.grabExcessVerticalSpace = true;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		buttonBar = new Composite(top, SWT.NONE);
		buttonBar.setLayoutData(gridData2);
		buttonBar.setLayout(rowLayout);
		choosePackagesButton = new Button(buttonBar, SWT.NONE);
		choosePackagesButton.setText(BeanInfoUIMessages.PackagesWorkbook_ChoosePackages);
		choosePackagesButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				final List elementsToAdd = choosePackages();
				addToPackagesList(elementsToAdd);
			}
		});
		chooseDefPathsButton = new Button(buttonBar, SWT.NONE);
		chooseDefPathsButton.setText(BeanInfoUIMessages.PackagesWorkbook_ChooseDefinedPaths);
		chooseDefPathsButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List elementsToAdd = chooseDefined();
				addToPackagesList(elementsToAdd);
			}
		});
		spacer1 = new Label(buttonBar, SWT.NONE);
		removeButton = new Button(buttonBar, SWT.NONE);
		removeButton.setText(BeanInfoUIMessages.PackagesWorkbook_Remove);
		removeButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
				packagesList.removeAll(selected);
				tableViewer.refresh();
				pageChanged();
			}
		});
	}
	
	private void addToPackagesList(final List toAdd){
		if (toAdd != null && !toAdd.isEmpty()) {
			packagesList.addAll(toAdd);
			tableViewer.refresh();
			table.getDisplay().asyncExec(new Runnable(){
				public void run() {
					tableViewer.setSelection(new StructuredSelection(toAdd));
				}
			});
			pageChanged();
		}
	}

	public void init(IJavaProject jproject) {
		javaProject = jproject;
		labelProvider.setJavaProject(jproject);
		try {
			rawList = javaProject.getRawClasspath();
			rootsPerRawEntry = new IPackageFragmentRoot[rawList.length][];
			for (int i = 0; i < rawList.length; i++) {
				rootsPerRawEntry[i] = javaProject.findPackageFragmentRoots(rawList[i]);
			}		
		} catch (JavaModelException e) {
			rawList = new IClasspathEntry[0];
			rootsPerRawEntry = new IPackageFragmentRoot[0][];
		}
		updatePackagesList();
	}

	public List getSelection() {
		return BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
	}

	public void setSelection(List selection) {
		tableViewer.setSelection(new StructuredSelection(selection));
	}
	
	private void updatePackagesList() {
		List spelements = beaninfosPathsBlock.getSearchpathOrderingPage().getElements();

		List packageElements = new ArrayList(spelements.size());
		for (int i = 0; i < spelements.size(); i++) {
			BPListElement spe = (BPListElement) spelements.get(i);
			if (spe instanceof BPSearchListElement) {
				packageElements.add(spe);
			}
		}
		packagesList.clear();
		packagesList.addAll(packageElements);
		if(tableViewer!=null && !table.isDisposed())
			tableViewer.refresh();
	}

	/**
	 * Choose the packages that should be in the search path.
	 */
	private List choosePackages() {

		ISelectionStatusValidator validator = new ChoosePackagesSelectionValidator();

		// Show up to the package fragment, don't show any contents of it.
		Class[] acceptedClasses =
			new Class[] { IJavaProject.class, IPackageFragmentRoot.class, IPackageFragment.class };
		Object[] rejectedFragments = getFilteredExistingEntries();

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
			new ElementTreeSelectionDialog(top.getShell(), labelProvider, provider);
		dialog.setTitle(BeanInfoUIMessages.BeaninfoPathsBlock_UI__addsearchpath_title);

		dialog.setValidator(validator);
		dialog.setMessage(BeanInfoUIMessages.BeaninfoPathsBlock_UI__addsearchpath_description);
		dialog.addFilter(filter);
		dialog.setInput(javaProject);

		if (dialog.open() == Window.OK) {
			Object[] elements = dialog.getResult();
			List newElements = new ArrayList(elements.length);
			for (int i = 0; i < elements.length; i++) {
				BPListElement newGuy = newBPListElement(elements[i]);
				if (newGuy != null)
					newElements.add(newGuy);
			}
			return newElements;
		}
		return Collections.EMPTY_LIST;
	}

	/**
	 * Create a new BPListElement for the given object.
	 */
	protected BPListElement newBPListElement(Object element) {
		SearchpathEntry se = null;
		boolean isExported = false;
		IPackageFragment frag = (IPackageFragment) element;
		// Need to find corresponding raw class path entry.
		IPackageFragmentRoot root = (IPackageFragmentRoot) frag.getParent(); // Get frag root.
		for (int i = 0; i < rootsPerRawEntry.length; i++) {
			for (int j = 0; j < rootsPerRawEntry[i].length; j++) {
				if (rootsPerRawEntry[i][j].equals(root)) {
					isExported = rawList[i].isExported() || rawList[i].getEntryKind() == IClasspathEntry.CPE_SOURCE;
					se = new SearchpathEntry(rawList[i].getEntryKind(), rawList[i].getPath(), frag.getElementName());
					break;
				}
			}
		}

		return new BPSearchListElement(se, false, false, isExported);
	}

	/**
	 * Return the list of entries that already are in the search path
	 * so that they don't show up in the list.
	 */
	protected Object[] getFilteredExistingEntries() {
		try {
			IPackageFragmentRoot[] roots = javaProject.getPackageFragmentRoots();
			List entries = packagesList;
			List fragments = new ArrayList(entries.size());
			Iterator itr = entries.iterator();
			while (itr.hasNext()) {
				BPListElement elem = (BPListElement) itr.next();
				if (elem instanceof BPSearchListElement) {
					BPSearchListElement bse = (BPSearchListElement) elem;
					fragments.addAll(getPackages(bse, roots));
				}
			}
			return fragments.toArray();
		} catch (JavaModelException e) {
		}
		return new Object[0];
	}
	
	/**
	 * Find the package fragments for this package entry.
	 */
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
	/**
	 * Choose the pre-defined search paths that should be in the search path.
	 */
	private List chooseDefined() {

		// Current pre-defined ones are only pre-reqed projects.
		// The list of inputs will not contain any already in the path.
		// We will create them here and if not selected they will thrown away.
		// The assumption is that there are not very many and our SearchPathListLabelProvider does
		// a good job of showing them. Otherwise we would need to come up with one that can show
		// IJavaProjects when we get them.
		List inputs = new ArrayList();
		List currentList = packagesList;
		for (int i = 0; i < rawList.length; i++) {
			if (rawList[i].getEntryKind() == IClasspathEntry.CPE_PROJECT) {
				boolean exists = false;
				for (int j = 0; j < currentList.size(); j++) {
					BPSearchListElement bse = (BPSearchListElement) currentList.get(j);
					if (bse.getEntry().getKind() == IClasspathEntry.CPE_PROJECT && rawList[i].getPath().equals(bse.getEntry().getPath())) {
						exists = true;
						break;
					}
				}
				
				if (!exists)
					inputs.add(new BPSearchListElement(new SearchpathEntry(IClasspathEntry.CPE_PROJECT, rawList[i].getPath(), null), false, false, rawList[i].isExported()));
			}
		}
		
		ILabelProvider labelProvider1 = new SearchPathListLabelProvider(javaProject);
		ElementListSelectionDialog dialog =
			new ElementListSelectionDialog(top.getShell(), labelProvider1);
		dialog.setTitle(BeanInfoUIMessages.PackagesWorkbook_SelectionDialog_DefinedPaths_Title); 

		dialog.setMessage(BeanInfoUIMessages.PackagesWorkbook_SelectionDialog_DefinedPaths_Message); 
		dialog.setElements(inputs.toArray());

		if (dialog.open() == Window.OK)
			return Arrays.asList(dialog.getResult());
		else
			return Collections.EMPTY_LIST;
	}

	protected void updateButtons(){
		chooseDefPathsButton.setEnabled(beaninfosPathsBlock.isBeaninfoEnabled());
		choosePackagesButton.setEnabled(beaninfosPathsBlock.isBeaninfoEnabled());
		
		List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
		removeButton.setEnabled(selected!=null && selected.size()>0 && beaninfosPathsBlock.isBeaninfoEnabled());
	}
	
	/**
	 * Something important about the page changed - perform update.
	 * 
	 * @since 1.2.0
	 */
	protected void pageChanged(){
		updateSearchpathList();
	}
	
	private void updateSearchpathList() {
		List searchelements = beaninfosPathsBlock.getSearchpathOrderingPage().getElements();

		List packageelements = packagesList;

		boolean changeDone = false;
		// First go through the search path and remove any SearchListElements that are
		// not in the search packages list from this page.
		for (ListIterator spitr = searchelements.listIterator(searchelements.size());
			spitr.hasPrevious();
			) {
			BPListElement element = (BPListElement) spitr.previous();
			if (element instanceof BPSearchListElement && !packageelements.remove(element)) {
				// Search element and not found in packages list so remove it from searchpath list.
				spitr.remove();
				changeDone = true;
			}
		}
		// Any left over in packages list are new and need to be added.
		searchelements.addAll(packageelements);
		changeDone = changeDone || !packageelements.isEmpty();

		if (changeDone)
			beaninfosPathsBlock.setSearchOrderElements(searchelements);
	}

	protected void updateEnabledStates(){
		updateButtons();
		table.setEnabled(beaninfosPathsBlock.isBeaninfoEnabled());
		label.setEnabled(beaninfosPathsBlock.isBeaninfoEnabled());
	}
	
	public void setBeaninfoEnabled(boolean enable) {
		if(top!=null && !top.isDisposed())
			updateEnabledStates();
	}
}
