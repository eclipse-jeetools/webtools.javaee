/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo.ui;
/*
 *  $RCSfile: PackagesWorkbookPage.java,v $
 *  $Revision: 1.4 $  $Date: 2004/08/27 15:35:42 $ 
 */

import java.util.*;
import java.util.List;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.wizards.TypedViewerFilter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.*;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.*;

import org.eclipse.jem.internal.beaninfo.core.SearchpathEntry;
import org.eclipse.jem.internal.ui.core.JEMUIPlugin;
/**
 * Workbook page for selecting the packages from the current
 * project that are in the search path.
 * @version 	1.0
 * @author
 */
public class PackagesWorkbookPage extends BuildSearchBasePage {

	// Note: Not happy with how this works. It does a lot of manipulating of classpath to
	// fragments and back and forth. But it works for now.

	/**
	 * Validator for this workbook page to verify the selections on Choose page.
	 * @version 	1.0
	 * @author
	 */
	public static class ChoosePackagesSelectionValidator implements ISelectionStatusValidator {

		private IStatus fgErrorStatus = new StatusInfo(IStatus.ERROR, ""); //$NON-NLS-1$
		private IStatus fgOKStatus = new StatusInfo();

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

	private BeaninfoPathsBlock biPathsBlock;
	private IJavaProject fCurrJProject;

	private Control fSWTControl;

	private ListDialogField fSearchPackagesList;

	private IPackageFragmentRoot[][] rootsPerRawEntry;
	private IClasspathEntry[] rawList;
	
	SearchPathListLabelProvider labelProvider;

	public PackagesWorkbookPage(IWorkspaceRoot root, BeaninfoPathsBlock biPathsBlock, List interestedFieldsForEnableControl) {
		this.biPathsBlock = biPathsBlock;

		fSWTControl = null;

		PackagesAdapter adapter = new PackagesAdapter();

		String[] buttonLabels;

		buttonLabels = new String[] {
			/* 0 */
			BeanInfoUIMessages.getString("PackagesWorkbook.ChoosePackages"), //$NON-NLS-1$
			/* 1 */
			BeanInfoUIMessages.getString("PackagesWorkbook.ChooseDefinedPaths"), //$NON-NLS-1$
			/* 2 */
			null,
			/* 3 */
			BeanInfoUIMessages.getString("PackagesWorkbook.Remove") }; //$NON-NLS-1$

		labelProvider = new SearchPathListLabelProvider();
		fSearchPackagesList = new ListDialogField(adapter, buttonLabels, labelProvider);
		fSearchPackagesList.setDialogFieldListener(adapter);
		fSearchPackagesList.setLabelText(BeanInfoUIMessages.getString("PackagesWorkbook.LabelText")); //$NON-NLS-1$
		fSearchPackagesList.setRemoveButtonIndex(3);

		fSearchPackagesList.setViewerSorter(new SPListElementSorter());
		
		interestedFieldsForEnableControl.add(fSearchPackagesList);
	}

	public void init(IJavaProject jproject) {
		fCurrJProject = jproject;
		labelProvider.setJavaProject(jproject);
		try {
			rawList = fCurrJProject.getRawClasspath();
			rootsPerRawEntry = new IPackageFragmentRoot[rawList.length][];
			for (int i = 0; i < rawList.length; i++) {
				rootsPerRawEntry[i] = fCurrJProject.findPackageFragmentRoots(rawList[i]);
			}		
		} catch (JavaModelException e) {
			rawList = new IClasspathEntry[0];
			rootsPerRawEntry = new IPackageFragmentRoot[0][];
		}
		updatePackagesList();
	}

	private void updatePackagesList() {
		List spelements = biPathsBlock.getSearchOrder().getElements();

		List packageElements = new ArrayList(spelements.size());
		for (int i = 0; i < spelements.size(); i++) {
			BPListElement spe = (BPListElement) spelements.get(i);
			if (spe instanceof BPSearchListElement) {
				packageElements.add(spe);
			}
		}
		fSearchPackagesList.setElements(packageElements);
	}

	public Control getControl(Composite parent) {
		PixelConverter converter = new PixelConverter(parent);

		Composite composite = new Composite(parent, SWT.NONE);

		LayoutUtil.doDefaultLayout(
			composite,
			new DialogField[] { fSearchPackagesList },
			true,
			SWT.DEFAULT,
			SWT.DEFAULT);

		int buttonBarWidth = converter.convertWidthInCharsToPixels(24);
		fSearchPackagesList.setButtonsMinWidth(buttonBarWidth);

		fSWTControl = composite;

		return composite;
	}

	private Shell getShell() {
		if (fSWTControl != null) {
			return fSWTControl.getShell();
		}
		return JEMUIPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell();
	}

	private class PackagesAdapter implements IListAdapter, IDialogFieldListener {

		// -------- IListAdapter --------
		public void customButtonPressed(ListDialogField field, int index) {
			packagesPageCustomButtonPressed(field, index);
		}

		public void selectionChanged(ListDialogField field) {
		}

		// ---------- IDialogFieldListener --------
		public void dialogFieldChanged(DialogField field) {
			packagesPageDialogFieldChanged(field);
		}
		/**
		 * @see org.eclipse.jdt.internal.ui.wizards.dialogfields.IListAdapter#doubleClicked(ListDialogField)
		 */
		public void doubleClicked(ListDialogField field) {
		}

	}

	private void packagesPageCustomButtonPressed(DialogField field, int index) {
		if (field == fSearchPackagesList) {
			List elementsToAdd = null;
			if (index == 0)
				elementsToAdd = choosePackages();
			else if (index == 1)
				elementsToAdd = chooseDefined();

			if (elementsToAdd != null && !elementsToAdd.isEmpty()) {
				fSearchPackagesList.addElements(elementsToAdd);
				fSearchPackagesList.postSetSelection(new StructuredSelection(elementsToAdd));
			}
		}
	}

	private void packagesPageDialogFieldChanged(DialogField field) {
		if (fCurrJProject == null) {
			// not initialized
			return;
		}

		if (field == fSearchPackagesList) {
			updateSearchpathList();
		}
	}

	private void updateSearchpathList() {
		List searchelements = biPathsBlock.getSearchOrder().getElements();

		List packageelements = fSearchPackagesList.getElements();

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
			biPathsBlock.setSearchOrderElements(searchelements);
	}
	/*
	 * @see BuildPathBasePage#getSelection
	 */
	public List getSelection() {
		return fSearchPackagesList.getSelectedElements();
	}

	/*
	 * @see BuildPathBasePage#setSelection
	 */
	public void setSelection(List selElements) {
		fSearchPackagesList.selectElements(new StructuredSelection(selElements));
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
			new ElementTreeSelectionDialog(getShell(), labelProvider, provider);
		dialog.setTitle(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_ADDSEARCHPATH_TITLE));

		dialog.setValidator(validator);
		dialog.setMessage(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_ADDSEARCHPATH_DESC));
		dialog.addFilter(filter);
		dialog.setInput(fCurrJProject);

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
	 * Return the list of entries that already are in the search path
	 * so that they don't show up in the list.
	 */
	protected Object[] getFilteredExistingEntries() {
		try {
			IPackageFragmentRoot[] roots = fCurrJProject.getPackageFragmentRoots();
			List entries = fSearchPackagesList.getElements();
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
		List currentList = fSearchPackagesList.getElements();
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
		
		ILabelProvider labelProvider1 = new SearchPathListLabelProvider(fCurrJProject);
		ElementListSelectionDialog dialog =
			new ElementListSelectionDialog(getShell(), labelProvider1);
		dialog.setTitle(BeanInfoUIMessages.getString("PackagesWorkbook.SelectionDialog.DefinedPaths.Title")); //$NON-NLS-1$

		dialog.setMessage(BeanInfoUIMessages.getString("PackagesWorkbook.SelectionDialog.DefinedPaths.Message")); //$NON-NLS-1$
		dialog.setElements(inputs.toArray());

		if (dialog.open() == Window.OK)
			return Arrays.asList(dialog.getResult());
		else
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
	
	
}
