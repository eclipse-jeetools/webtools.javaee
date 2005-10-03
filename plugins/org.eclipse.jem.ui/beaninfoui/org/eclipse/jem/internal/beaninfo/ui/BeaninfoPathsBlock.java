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
 *  $RCSfile: BeaninfoPathsBlock.java,v $
 *  $Revision: 1.16 $  $Date: 2005/10/03 23:06:42 $ 
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
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoNature;
import org.eclipse.jem.internal.beaninfo.core.*;
import org.eclipse.jem.internal.ui.core.JEMUIPlugin;

public class BeaninfoPathsBlock {

	//private Shell sShell1 = null;  //  @jve:decl-index=0:visual-constraint="40,19"
	private Composite top = null;
	private TabFolder tabFolder = null;
	private Button enableBeaninfoCheckbox = null;
	private PackagesWorkbookPage packagesWorkbookPage2 = null;
	private SearchpathOrderingWorkbookPage searchpathOrderingWorkbookPage2 = null;
	private BeaninfosWorkbookPage beaninfosPropertyPage2 = null;
	private Control packagesPageControl = null;
	private Control searchpathPageControl = null;
	private Control beaninfosPageControl = null;
	private Image packagesTabImage;
	private Image beaninfosTabImage;
	private Image searchPathTabImage;
	// ...ui
	
	private IWorkspaceRoot workspaceRoot;
	private IStatusChangeListener statusChangeListener;
	private IBuildSearchPage currentPage;
	private IJavaProject javaProject;
	private boolean enableBeaninfo = true;
	
	public BeaninfoPathsBlock(IWorkspaceRoot workspaceRoot, IStatusChangeListener statusChangeListener){
		this.workspaceRoot = workspaceRoot;
		this.statusChangeListener = statusChangeListener;
	}
	
	private void setEnableBeaninfo(boolean enable){
		enableBeaninfo = enable;
		if(enableBeaninfoCheckbox!=null && !enableBeaninfoCheckbox.isDisposed())
			enableBeaninfoCheckbox.setSelection(enable);
	}
	
//	/**
//	 * This method initializes sShell1	
//	 *
//	 */
//	private void createSShell1() {
//		sShell1 = new Shell();
//		sShell1.setLayout(new FillLayout());
//		createTop();
//		sShell1.setSize(new org.eclipse.swt.graphics.Point(403,289));
//	}

	/**
	 * This method initializes top	
	 *
	 */
	public Control createControl(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		top.setLayout(new GridLayout());
		createTabFolder();
		enableBeaninfoCheckbox = new Button(top, SWT.CHECK);
		enableBeaninfoCheckbox.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__enablebeaninfo);
		enableBeaninfoCheckbox.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				enableBeaninfo = enableBeaninfoCheckbox.getSelection();
				packagesWorkbookPage2.setBeaninfoEnabled(enableBeaninfo);
				beaninfosPropertyPage2.setBeaninfoEnabled(enableBeaninfo);
				searchpathOrderingWorkbookPage2.setBeaninfoEnabled(enableBeaninfo);
			}
			public void widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent e) {
				widgetSelected(e);
			}
		});
		enableBeaninfoCheckbox.setSelection(enableBeaninfo);
		if(javaProject!=null){
			getPackagesPage().init(javaProject);
			getBeaninfosPage().init(javaProject);
			getSearchpathOrderingPage().init(javaProject);
		}
		return top;
	}

	/**
	 * This method initializes tabFolder	
	 *
	 */
	private void createTabFolder() {
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		tabFolder = new TabFolder(top, SWT.NONE);
		tabFolder.setLayoutData(gridData);
		createPackagesWorkbookPage2();
		createBeaninfosPropertyPage2();
		createSearchpathOrderingWorkbookPage2();
		tabFolder.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				tabChanged(e.item);
			}
		});
				
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText(BeanInfoUIMessages.BeanInfoPathsBlock_Page_Tab_Packages);
		tabItem.setImage(getPackagesTabImage());
		tabItem.setControl(packagesPageControl);
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText(BeanInfoUIMessages.BeanInfoPathsBlock_Page_Tab_Classes);
		tabItem1.setImage(getBeaninfosTabImage());
		tabItem1.setControl(beaninfosPageControl);
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setImage(getSearchPathTabImage());
		tabItem2.setText(BeanInfoUIMessages.BeaninfoPathsBlock_UI__serachpath_tab_order);
		tabItem2.setControl(searchpathPageControl);
		tabFolder.setSelection(2);
	}

	private Image getSearchPathTabImage() {
		if(searchPathTabImage==null)
			searchPathTabImage = JEMUIPlugin.imageDescriptorFromPlugin(JEMUIPlugin.getPlugin().getBundle().getSymbolicName(), "icons/cp_order_obj.gif").createImage();
		return searchPathTabImage;
	}

	private Image getPackagesTabImage() {
		if(packagesTabImage==null)
			packagesTabImage = JavaUI.getSharedImages().getImage(ISharedImages.IMG_OBJS_PACKAGE);
		return packagesTabImage;
	}
	
	private Image getBeaninfosTabImage(){
		if(beaninfosTabImage==null){
			URL imageURL = Platform.find(JEMUIPlugin.getPlugin().getBundle(), new Path("icons/javabean.gif")); //$NON-NLS-1$
			if (imageURL != null) 
				beaninfosTabImage = ImageDescriptor.createFromURL(imageURL).createImage();
			else
				beaninfosTabImage = ImageDescriptor.getMissingImageDescriptor().createImage();
		}
		return beaninfosTabImage;
	}

	/**
	 * This method initializes packagesWorkbookPage2	
	 *
	 */
	private void createPackagesWorkbookPage2() {
		packagesPageControl = getPackagesPage().createControl(tabFolder);
	}

	/**
	 * This method initializes searchpathOrderingWorkbookPage2	
	 *
	 */
	private void createSearchpathOrderingWorkbookPage2() {
		searchpathPageControl = getSearchpathOrderingPage().createControl(tabFolder);
		searchpathPageControl.addDisposeListener(new org.eclipse.swt.events.DisposeListener() {
			public void widgetDisposed(org.eclipse.swt.events.DisposeEvent e) {
				if(searchPathTabImage!=null){
					if(!searchPathTabImage.isDisposed())
						searchPathTabImage.dispose();
					searchPathTabImage=null;
				}
			}
		});
	}

	/**
	 * This method initializes beaninfosPropertyPage2	
	 *
	 */
	private void createBeaninfosPropertyPage2() {
		beaninfosPageControl = getBeaninfosPage().createControl(tabFolder);
		beaninfosPageControl.addDisposeListener(new org.eclipse.swt.events.DisposeListener() {
			public void widgetDisposed(org.eclipse.swt.events.DisposeEvent e) {
				if(beaninfosTabImage!=null){
					if(!beaninfosTabImage.isDisposed())
						beaninfosTabImage.dispose();
					beaninfosTabImage=null;
				}
			}
		});
	}

	PackagesWorkbookPage getPackagesPage(){
		if(packagesWorkbookPage2==null)
			packagesWorkbookPage2 = new PackagesWorkbookPage(workspaceRoot, this);
		return packagesWorkbookPage2;
	}
	
	BeaninfosWorkbookPage getBeaninfosPage(){
		if(beaninfosPropertyPage2==null)
			beaninfosPropertyPage2 = new BeaninfosWorkbookPage(workspaceRoot, this);
		return beaninfosPropertyPage2;
	}
	
	SearchpathOrderingWorkbookPage getSearchpathOrderingPage(){
		if(searchpathOrderingWorkbookPage2==null)
			searchpathOrderingWorkbookPage2 = new SearchpathOrderingWorkbookPage(this);
		return searchpathOrderingWorkbookPage2;
	}
	
	private void tabChanged(Widget widget) {
		if (widget instanceof TabItem) {
			IBuildSearchPage newPage = (IBuildSearchPage) ((TabItem) widget).getData();
			if (currentPage != null) {
				List selection = currentPage.getSelection();
				if (!selection.isEmpty()) {
					newPage.setSelection(selection);
				}
			}
			currentPage = newPage;
		}
	}	
	
	/**
	 * Creates a runnable that sets the configured build paths.
	 */
	public IRunnableWithProgress getRunnable() {
		final boolean wantNature = enableBeaninfo;
		final List searchPathEntries = wantNature ? getSearchpathOrderingPage().getElements() : null;

		return new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				if (monitor == null) {
					monitor = new NullProgressMonitor();
				}
				monitor.beginTask(
					BeanInfoUIMessages.BeaninfoPathsBlock_UI__searchpath_operationdescription, 
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
			if (!javaProject.getProject().hasNature(BeaninfoNature.NATURE_ID)) {
				addNatureIDToProject(javaProject.getProject(), BeaninfoNature.NATURE_ID, monitor);
			}

			BeaninfoNature nature = BeaninfoNature.getRuntime(javaProject.getProject());
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
			removeNatureIDFromProject(javaProject.getProject(), BeaninfoNature.NATURE_ID, monitor);
		}
	}

	/**
	 * Initializes the classpath for the given project. Multiple calls to init are allowed,
	 * but all existing settings will be cleared and replace by the given or default paths.
	 * @param project The java project to configure.
	 */
	public void init(IJavaProject jproject) {
		this.javaProject = jproject;
		//TODO: labelProvider.setJavaProject(jproject);

		try {
			// If we have a config file, we will assume we have a nature. It will add it automatically
			// when we ask for the information. Even if we didn't have the nature, as soon as someone
			// asks for it, we would create it anyhow, and it would use the existing config file.
			// If we don't have a config file, we could have the nature, so we will check for that too.
			boolean haveConfigFile = jproject.getProject().getFile(BeaninfoNature.P_BEANINFO_SEARCH_PATH).exists();
			boolean haveNature = javaProject.getProject().hasNature(BeaninfoNature.NATURE_ID);
			enableBeaninfo = haveConfigFile || haveNature;
			if (haveNature || haveConfigFile) {
				BeaninfosDoc doc = BeaninfoNature.getRuntime(javaProject.getProject()).getSearchPath();
				IClasspathEntry[] raw = javaProject.getRawClasspath();

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
							Object[] paths = be.getClasspath(javaProject);
							if (paths != null && paths.length > 0) {
								for (int j = 0; !isMissing && j < paths.length; j++) {
									Object path = paths[j];
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
										IPackageFragmentRoot[] roots = javaProject.findPackageFragmentRoots(raw[j]);
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

				if (getPackagesPage() != null) {
					getPackagesPage().init(javaProject);
					getBeaninfosPage().init(javaProject);
					getSearchpathOrderingPage().init(javaProject);
				}
			} else {
				// No nature, disable,
				setEnableBeaninfo(false);
			}
		} catch (JavaModelException e) {
			setEnableBeaninfo(false);
		} catch (CoreException e) {
			setEnableBeaninfo(false);
		}

//		listenForClasspathChange();
		doStatusLineUpdate();
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
	
	void doStatusLineUpdate() {
		statusChangeListener.statusChanged(getSearchpathOrderingPage().getStatus());
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
		
		getSearchpathOrderingPage().setElements(newElements);
		getSearchpathOrderingPage().setCheckedElements(exportedEntries);
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
			IResource res = workspaceRoot.findMember(resolved.getPath());
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
	
	
	public static List getSelectedList(ISelection selection){
		List selectedList = null;
		if(selection!=null && !selection.isEmpty() && selection instanceof IStructuredSelection){
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			selectedList = new ArrayList(Arrays.asList(structuredSelection.toArray()));
		}
		return selectedList;
	}
	
	public boolean isBeaninfoEnabled(){
		return enableBeaninfo;
	}
}
