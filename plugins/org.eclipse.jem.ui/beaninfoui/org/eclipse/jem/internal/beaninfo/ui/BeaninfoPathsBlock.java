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
 *  $RCSfile: BeaninfoPathsBlock.java,v $
 *  $Revision: 1.5 $  $Date: 2004/05/24 23:23:43 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.util.TabFolderLayout;
import org.eclipse.jdt.internal.ui.viewsupport.ImageDisposer;
import org.eclipse.jdt.internal.ui.wizards.IStatusChangeListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.*;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature;
import org.eclipse.jem.internal.beaninfo.core.*;
import org.eclipse.jem.internal.ui.core.JEMUIPlugin;

public class BeaninfoPathsBlock {

	private IWorkspaceRoot fWorkspaceRoot;

	private CheckedListDialogField fSearchOrder;
	// Search path list. filtered for each tab page to appropriate entries
	private SelectionButtonDialogField fEnableBeaninfoDialogField;
	// Checkbox to add/remove beaninfo nature entirely.

	private StatusInfo fSearchStatus;

	private IJavaProject fCurrJProject;

	private IStatusChangeListener fContext;
	private PackagesWorkbookPage fPackagesPage;
	private BeaninfosWorkbookPage fBeaninfosPage;

	private BuildSearchBasePage fCurrPage;
	private SearchPathListLabelProvider labelProvider;
	
	public BeaninfoPathsBlock(IWorkspaceRoot root, IStatusChangeListener context) {
		fWorkspaceRoot = root;
		fContext = context;
		fCurrPage = null;

		BuildPathAdapter adapter = new BuildPathAdapter();

		String[] buttonLabels = new String[] {
			/* 0 */
			BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_UP),
			/* 1 */
			BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_DOWN), 
			/* 2 */ null,
			/* 3 */ BeanInfoUIMessages.getString("BeanInfoPathsBlock.ExportAll") , //$NON-NLS-1$
			/* 4 */ BeanInfoUIMessages.getString("BeanInfoPathsBlock.UnexportAll") //$NON-NLS-1$
			};
		
		labelProvider = new SearchPathListLabelProvider();	// We keep around to update with latest project.
		fSearchOrder = new CheckedListDialogField(null, buttonLabels, labelProvider);
		fSearchOrder.setDialogFieldListener(adapter);
		fSearchOrder.setLabelText(
			BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_LABEL));
		fSearchOrder.setUpButtonIndex(0);
		fSearchOrder.setDownButtonIndex(1);
		fSearchOrder.setCheckAllButtonIndex(3);
		fSearchOrder.setUncheckAllButtonIndex(4);

		fEnableBeaninfoDialogField = new SelectionButtonDialogField(SWT.CHECK);
		fEnableBeaninfoDialogField.setLabelText(
			BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_ENABLEBEANINFO));

		fSearchStatus = new StatusInfo();

		fCurrJProject = null;
	}
	
	public CheckedListDialogField getSearchOrder() {
		return fSearchOrder;
	}
	
	/*
	 * searchOrder dialog must never have all of elements set
	 * directly. Must come through here first so that we can
	 * make sure updates occur in correct sequence, else
	 * we will wind up with entries being marked as unexported
	 * when they really are exported. 
	 */
	public void setSearchOrderElements(List newElements) {
		ArrayList exportedEntries = new ArrayList(newElements.size());
		for (Iterator iter = newElements.iterator(); iter.hasNext();) {
			BPListElement element = (BPListElement) iter.next();
			if (element.isExported())
				exportedEntries.add(element);
		}
		
		inUpdate = true;	// So that on first set we don't waste time updating the list and reseting all of our entries.
		fSearchOrder.setElements(newElements);
		inUpdate = false;
		fSearchOrder.setCheckedElements(exportedEntries);
	}

	// -------- UI creation ---------

	public Control createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);

		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.numColumns = 1;
		composite.setLayout(layout);

		TabFolder folder = new TabFolder(composite, SWT.NONE);
		folder.setLayout(new TabFolderLayout());
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));
		folder.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				tabChanged(e.item);
			}
		});

		ImageRegistry imageRegistry = JavaPlugin.getDefault().getImageRegistry();

		TabItem item;

		ArrayList interestedDialogFields = new ArrayList(3);

		fPackagesPage = new PackagesWorkbookPage(fWorkspaceRoot, this, interestedDialogFields);
		item = new TabItem(folder, SWT.NONE);
		item.setText(BeanInfoUIMessages.getString("BeanInfoPathsBlock.Page.Tab.Packages")); //$NON-NLS-1$
		item.setImage(imageRegistry.get(JavaPluginImages.IMG_OBJS_PACKAGE));
		item.setData(fPackagesPage);
		item.setControl(fPackagesPage.getControl(folder));

		// a non shared image
		Image fBeanImage = null;
		URL imageURL = Platform.find(JEMUIPlugin.getPlugin().getBundle(), new Path("icons/javabean.gif")); //$NON-NLS-1$
		if (imageURL != null) 
			fBeanImage = ImageDescriptor.createFromURL(imageURL).createImage();
		else
			fBeanImage = ImageDescriptor.getMissingImageDescriptor().createImage();
		composite.addDisposeListener(new ImageDisposer(fBeanImage));
				
		fBeaninfosPage= new BeaninfosWorkbookPage(fWorkspaceRoot, this, interestedDialogFields);		
		item= new TabItem(folder, SWT.NONE);
		item.setText(BeanInfoUIMessages.getString("BeanInfoPathsBlock.Page.Tab.Classes")); //$NON-NLS-1$
		item.setImage(fBeanImage);
		item.setData(fBeaninfosPage);
		item.setControl(fBeaninfosPage.getControl(folder));

		// a non shared image
		Image cpoImage = JavaPluginImages.DESC_TOOL_CLASSPATH_ORDER.createImage();
		composite.addDisposeListener(new ImageDisposer(cpoImage));
		
		SearchpathOrderingWorkbookPage ordpage = new SearchpathOrderingWorkbookPage(fSearchOrder, interestedDialogFields);
		item = new TabItem(folder, SWT.NONE);
		item.setText(BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_TAB_ORDER));
		item.setImage(cpoImage);
		item.setData(ordpage);
		item.setControl(ordpage.getControl(folder));

		if (fCurrJProject != null) {
			fPackagesPage.init(fCurrJProject);
			fBeaninfosPage.init(fCurrJProject);
		}

		fEnableBeaninfoDialogField.doFillIntoGrid(composite, 1);
		fEnableBeaninfoDialogField.attachDialogFields((DialogField[]) interestedDialogFields.toArray(new DialogField[interestedDialogFields.size()]));

		folder.setSelection(2);
		fCurrPage = ordpage;
		fSearchOrder.selectFirstElement();

		//		WorkbenchHelp.setHelp(composite, new Object[] { IJavaHelpContextIds.BUILD_PATH_BLOCK });				
		return composite;
	}


	/**
	 * Initializes the classpath for the given project. Multiple calls to init are allowed,
	 * but all existing settings will be cleared and replace by the given or default paths.
	 * @param project The java project to configure.
	 */
	public void init(IJavaProject jproject) {
		fCurrJProject = jproject;
		labelProvider.setJavaProject(jproject);

		try {
			// If we have a config file, we will assume we have a nature. It will add it automatically
			// when we ask for the information. Even if we didn't have the nature, as soon as someone
			// asks for it, we would create it anyhow, and it would use the existing config file.
			// If we don't have a config file, we could have the nature, so we will check for that too.
			boolean haveConfigFile = jproject.getProject().getFile(BeaninfoNature.P_BEANINFO_SEARCH_PATH).exists();
			boolean haveNature = fCurrJProject.getProject().hasNature(BeaninfoNature.NATURE_ID);
			fEnableBeaninfoDialogField.setSelection(haveConfigFile || haveNature);
			if (haveNature || haveConfigFile) {
				BeaninfosDoc doc = BeaninfoNature.getRuntime(fCurrJProject.getProject()).getSearchPath();
				IClasspathEntry[] raw = fCurrJProject.getRawClasspath();

				List newSearchpath = new ArrayList();
				if (doc != null) {
					IBeaninfosDocEntry[] sp = doc.getSearchpath();
					for (int i = 0; i < sp.length; i++) {
						IBeaninfosDocEntry curr = sp[i];
						boolean isMissing = false;
						BPListElement elem = null;
						if (curr instanceof BeaninfoEntry) {
							BeaninfoEntry be = (BeaninfoEntry) curr;

							// get the resource, this tells if the beaninfos exist or not.
							Object[] paths = be.getClasspath(fCurrJProject);
							if (paths != null && paths.length > 0) {
								for (int j = 0; !isMissing && j < paths.length; j++) {
									Object path = paths[i];
									if (path instanceof IProject)
										isMissing = !((IProject) path).exists();
									else if (path instanceof String) {
										File f = new File((String) path);
										isMissing = !f.exists();
									} else if (path instanceof IPath) {
										isMissing = true;	// Plugins are invalid in BeaninfoConfig. They only apply within contributions.
									} else
										isMissing = true; // Invalid, so isMissing.
								}								
							} else
								isMissing = true;

							elem = new BPBeaninfoListElement(be, getInitialSearchpaths(be), isMissing);
						} else {
							// Searchpath entry, see if we can find the raw classpath entry that it is for.
							boolean isExported = false;
							boolean isPackageMissing = true;
							isMissing = true;	// Initially missing until we find it.
							SearchpathEntry ce = (SearchpathEntry) curr;
							int kind = ce.getKind();
							IPath path = ce.getPath();
							for (int j = 0; j < raw.length; j++) {
								if (raw[j].getEntryKind() == kind && raw[j].getPath().equals(path)) {
									isMissing = false;
									isExported = raw[j].isExported() || raw[j].getEntryKind() == IClasspathEntry.CPE_SOURCE;
									String packageName = ce.getPackage();
									if (packageName != null) {
										IPackageFragmentRoot[] roots = fCurrJProject.findPackageFragmentRoots(raw[j]);
										for (int k = 0; k < roots.length; k++) {
											IPackageFragmentRoot iroot = roots[k];
											if (iroot.getPackageFragment(packageName).exists()) {
												isPackageMissing = false;
												break;
											}
										}
									} else
										isPackageMissing = false;
									break;
								}
							}
							elem = new BPSearchListElement(ce, isMissing, isPackageMissing, isExported);
						}

						newSearchpath.add(elem);
					}
				}

				// inits the dialog field
				setSearchOrderElements(newSearchpath);

				if (fPackagesPage != null) {
					fPackagesPage.init(fCurrJProject);
					fBeaninfosPage.init(fCurrJProject);
				}
			} else {
				// No nature, disable,
				fEnableBeaninfoDialogField.setSelection(false);
			}
		} catch (JavaModelException e) {
			fEnableBeaninfoDialogField.setSelection(false);
		} catch (CoreException e) {
			fEnableBeaninfoDialogField.setSelection(false);
		}

//		listenForClasspathChange();
		doStatusLineUpdate();
	}
	
	/*
	 * Create the Searchpath elements for a BeaninfoElement.
	 * Return null if not to update.
	 */
	private BPSearchListElement[] getInitialSearchpaths(BeaninfoEntry infoEntry) {

		// We can only verify the existence of packages within beaninfos that are 
		// located within a project on the desktop. Otherwise we can't find out what
		// packages are in them.
		IPackageFragmentRoot[] roots = null;

		if (infoEntry.getKind() != BeaninfoEntry.BIE_PLUGIN) {
			IClasspathEntry resolved = JavaCore.getResolvedClasspathEntry(infoEntry.getClasspathEntry());
			IResource res = fWorkspaceRoot.findMember(resolved.getPath());
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

		SearchpathEntry[] entries = infoEntry.getSearchPaths();
		BPSearchListElement[] packageElements = new BPSearchListElement[entries.length];
		for (int i = 0;
			i < entries.length;
			i++) { // Searchpath entry, see if we can find the raw classpath entry that it is for.
			boolean isPackageMissing = roots != null;
			// If roots is null, then the package isn't missing because we can't test for it.
			SearchpathEntry ce = entries[i];
			if (roots != null) {
				String packageName = ce.getPackage();
				for (int k = 0; k < roots.length; k++) {
					IPackageFragmentRoot iroot = roots[k];
					if (iroot.getPackageFragment(packageName).exists()) {
						isPackageMissing = false;
						break;
					}
				}
			}
			packageElements[i] = new BPSearchListElement(ce, false, isPackageMissing, false);
		}

		return packageElements;
	}

	// -------- public api --------

	/**
	 * Returns the Java project. Can return <code>null<code> if the page has not
	 * been initialized.
	 */
	public IJavaProject getJavaProject() {
		return fCurrJProject;
	}

	private class BuildPathAdapter implements IDialogFieldListener {

		// ---------- IDialogFieldListener --------
		public void dialogFieldChanged(DialogField field) {
			buildPathDialogFieldChanged(field);
		}
	}

	private void buildPathDialogFieldChanged(DialogField field) {
		if (field == fSearchOrder) {
			updateSearchPathStatus();
		}
		doStatusLineUpdate();
	}

	// -------- verification -------------------------------

	private void doStatusLineUpdate() {
		fContext.statusChanged(fSearchStatus);
	}

	private boolean inUpdate;
	// Flag to indicate we are in updateSearchPathStatus and to not do it again. This can
	// happen due to using setCheckedElements instead of setCheckedWithoutUpdate.
	/**
	 * Validates the search path.
	 */
	private void updateSearchPathStatus() {
		if (inUpdate)
			return;
		try {
			inUpdate = true;

			fSearchStatus.setOK();

			List elements = fSearchOrder.getElements();

			boolean entryMissing = false;

			// Because of bug in setcheckedWithoutUpdate, which sets to true no matter what the state is, we need
			// to accumulate the checked elements and re-set them again after this so that they will be correct.	
			ArrayList exported = new ArrayList();

			for (Iterator entries = elements.iterator(); entries.hasNext();) {
				BPListElement currElement = (BPListElement) entries.next();

				boolean isChecked = fSearchOrder.isChecked(currElement);
				if (currElement.canExportBeChanged()) {
					if (isChecked)
						exported.add(currElement);
					currElement.setExported(isChecked);
				} else {
					//				fSearchOrder.setCheckedWithoutUpdate(currElement, currElement.isExported());
					if (currElement.isExported())
						exported.add(currElement);
				}

				entryMissing = entryMissing || currElement.isMissing();
			}

			// Now reset the checked states, due to bug
			fSearchOrder.setCheckedElements(exported);

			if (entryMissing) {
				fSearchStatus.setWarning(
					BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_MISSING));
			}
		} finally {
			inUpdate = false;
		}
	}

	// -------- creation -------------------------------

	/**
	 * Creates a runnable that sets the configured build paths.
	 */
	public IRunnableWithProgress getRunnable() {
		final boolean wantNature = fEnableBeaninfoDialogField.isSelected();
		final List searchPathEntries = wantNature ? fSearchOrder.getElements() : null;

		return new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				if (monitor == null) {
					monitor = new NullProgressMonitor();
				}
				monitor.beginTask(
					BeanInfoUIMessages.getString(BeanInfoUIMessages.BPB_SEARCHPATH_OPDESC),
					10);
				try {
					setBeaninfoSearchpath(wantNature, searchPathEntries, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
	}

	private void setBeaninfoSearchpath(
		boolean wantNature,
		List searchPathEntries,
		IProgressMonitor monitor)
		throws CoreException {

		if (wantNature) {
			// create beaninfo nature
			if (!fCurrJProject.getProject().hasNature(BeaninfoNature.NATURE_ID)) {
				addNatureIDToProject(fCurrJProject.getProject(), BeaninfoNature.NATURE_ID, monitor);
			}

			BeaninfoNature nature = BeaninfoNature.getRuntime(fCurrJProject.getProject());
			// Now build/set the search path.
			if (searchPathEntries.size() > 0) {
				IBeaninfosDocEntry[] sparray = new IBeaninfosDocEntry[searchPathEntries.size()];
				Iterator itr = searchPathEntries.iterator();
				int i = 0;
				while (itr.hasNext()) {
					BPListElement elem = (BPListElement) itr.next();
					sparray[i++] = elem.getEntry();
				}
				nature.setSearchPath(new BeaninfosDoc(sparray), monitor);
			} else
				nature.setSearchPath(null, monitor);
		} else {
			// Remove the nature, no longer wanted.
			removeNatureIDFromProject(fCurrJProject.getProject(), BeaninfoNature.NATURE_ID, monitor);
		}
	}

	/**
	 * Adds a nature to a project
	 */
	private void addNatureIDToProject(IProject proj, String natureId, IProgressMonitor monitor)
		throws CoreException {
		IProjectDescription description = proj.getDescription();
		String[] prevNatures = description.getNatureIds();
		String[] newNatures = new String[prevNatures.length + 1];
		System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
		newNatures[prevNatures.length] = natureId;
		description.setNatureIds(newNatures);
		proj.setDescription(description, monitor);
	}

	private void removeNatureIDFromProject(IProject proj, String natureId, IProgressMonitor monitor)
		throws CoreException {
		IProjectDescription description = proj.getDescription();
		String[] prevNatures = description.getNatureIds();
		int indx = -1;
		for (int i = 0; i < prevNatures.length; i++) {
			if (prevNatures[i].equals(natureId)) {
				indx = i;
				break;
			}
		}
		if (indx == -1)
			return;

		String[] newNatures = new String[prevNatures.length - 1];
		if (newNatures.length != 0) {
			System.arraycopy(prevNatures, 0, newNatures, 0, indx);
			if (indx < newNatures.length)
				System.arraycopy(prevNatures, indx + 1, newNatures, indx, newNatures.length - indx);
		}
		description.setNatureIds(newNatures);
		proj.setDescription(description, monitor);
	}

	// -------- tab switching ----------

	private void tabChanged(Widget widget) {
		if (widget instanceof TabItem) {
			BuildSearchBasePage newPage = (BuildSearchBasePage) ((TabItem) widget).getData();
			if (fCurrPage != null) {
				List selection = fCurrPage.getSelection();
				if (!selection.isEmpty()) {
					newPage.setSelection(selection);
				}
			}
			fCurrPage = newPage;
		}
	}	
}