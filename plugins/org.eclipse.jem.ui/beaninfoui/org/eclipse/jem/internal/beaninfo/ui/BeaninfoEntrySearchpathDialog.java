package org.eclipse.jem.internal.beaninfo.ui;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: BeaninfoEntrySearchpathDialog.java,v $
 *  $Revision: 1.3 $  $Date: 2004/03/22 23:48:57 $ 
 */

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.wizards.TypedViewerFilter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IListAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoEntry;
import org.eclipse.jem.internal.beaninfo.core.SearchpathEntry;

/**
 * This dialog is used to display and modify the search path
 * within a BeaninfoEntry.
 * @version 	1.0
 * @author
 */
public class BeaninfoEntrySearchpathDialog extends Dialog {

	private class DialogAdapter implements IListAdapter {

		// -------- IListAdapter --------
		public void customButtonPressed(ListDialogField field, int index) {
			pageCustomButtonPressed(index);
		}

		public void selectionChanged(ListDialogField field) {
		}

		/**
		 * @see org.eclipse.jdt.internal.ui.wizards.dialogfields.IListAdapter#doubleClicked(ListDialogField)
		 */
		public void doubleClicked(ListDialogField field) {
		}

	}

	protected BPBeaninfoListElement infoElement;
	protected IJavaProject jProject;
	protected ListDialogField listField;

	public BeaninfoEntrySearchpathDialog(
		Shell parentShell,
		BPBeaninfoListElement infoElement,
		IJavaProject jProject) {
		super(parentShell);

		this.infoElement = infoElement;
		this.jProject = jProject;

		DialogAdapter adapter = new DialogAdapter();

		String[] buttonLabels = new String[] {
			/* 0 */
			BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_UP),
			/* 1 */
			BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_DOWN),
			/* 2 */
			null,
			/* 3 */
			BeanInfoUIMessages.getString("SearchPathDialog.ChoosePackages"), //$NON-NLS-1$
			/* 4 */
			null,
			/* 5 */
			BeanInfoUIMessages.getString("SearchPathDialog.Remove") }; //$NON-NLS-1$

		listField = new ListDialogField(adapter, buttonLabels, new SearchPathListLabelProvider(jProject));
		listField.setLabelText(BeanInfoUIMessages.getString("SearchPathDialog.Desc.Label")); //$NON-NLS-1$
		listField.setUpButtonIndex(0);
		listField.setDownButtonIndex(1);
		listField.setRemoveButtonIndex(5);
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(
			MessageFormat.format(
				BeanInfoUIMessages.getString("SearchPathDialog.ModifySearchPaths"), //$NON-NLS-1$
				new Object[] { infoElement.getEntry().getPath().toString()}));
	}

	protected Control createDialogArea(Composite parent) {
		PixelConverter converter = new PixelConverter(parent);

		Composite composite = new Composite(parent, SWT.NONE);

		LayoutUtil.doDefaultLayout(
			composite,
			new DialogField[] { listField },
			true,
			SWT.DEFAULT,
			SWT.DEFAULT);
		int buttonBarWidth = converter.convertWidthInCharsToPixels(24);
		listField.setButtonsMinWidth(buttonBarWidth);

		updatePackagesList();

		return composite;
	}

	private void updatePackagesList() {
		if (infoElement.getEntry().getKind() == BeaninfoEntry.BIE_PLUGIN) {
			// We don't allow these to be updated. They should only be used through
			// registered mechanisms and we won't be showing them here normally.
			listField.setLabelText(BeanInfoUIMessages.getString("SearchPathDialog.NotEditable_INFO_")); //$NON-NLS-1$
			listField.setEnabled(false);
			return;
		}

		listField.setElements(Arrays.asList(infoElement.getSearchpaths()));
	}

	protected void pageCustomButtonPressed(int index) {
		if (index != 3)
			return;

		List elementsToAdd = choosePackages();

		if (elementsToAdd != null && !elementsToAdd.isEmpty()) {
			listField.addElements(elementsToAdd);
			listField.postSetSelection(new StructuredSelection(elementsToAdd));
		}
	}

	protected void okPressed() {
		if (listField.isEnabled()) {
			// If the field is not enabled, then there is no update to perform. We don't
			// want to accidentially wipe out the search paths in this case.

			// Override to put the list of elements back into the BPBeaninfoListElement
			// Until then they aren't actually updated.
			List paths = listField.getElements();
			infoElement.setSearchpaths(
				(BPSearchListElement[]) paths.toArray(new BPSearchListElement[paths.size()]));
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
			dialog.setTitle(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_ADDSEARCHPATH_TITLE));

			dialog.setValidator(validator);
			dialog.setMessage(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_ADDSEARCHPATH_DESC));
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
						return BeanInfoUIMessages.getString("SearchPathDialog.PackagePresent_INFO_"); //$NON-NLS-1$
						
					IStatus status = JavaConventions.validatePackageName(newText);
					return status.getSeverity() == IStatus.OK ? null : status.getMessage();
				}
			};
			InputDialog dialog = new InputDialog(getShell(), BeanInfoUIMessages.getString("SearchPathDialog.InputDialog.Title"), BeanInfoUIMessages.getString("SearchPathDialog.InputDialog.Message"), null, validator); //$NON-NLS-1$ //$NON-NLS-2$
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
		List entries = listField.getElements();
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
		List entries = listField.getElements();
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