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
 *  $RCSfile: BeaninfosWorkbookPage.java,v $
 *  $Revision: 1.10 $  $Date: 2005/10/03 23:06:42 $ 
 */
package org.eclipse.jem.internal.beaninfo.ui;

import java.util.*;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jdt.ui.wizards.BuildPathDialogAccess;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.dialogs.StatusDialog;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.*;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import org.eclipse.jem.internal.beaninfo.core.BeaninfoEntry;
import org.eclipse.jem.internal.ui.core.JEMUIPlugin;

public class BeaninfosWorkbookPage implements IBuildSearchPage{

	private static final String DIALOGSTORE_LASTVARIABLE = JEMUIPlugin.PI_BEANINFO_UI + ".lastvar"; //$NON-NLS-1$
	
	// a dialog to choose a variable
	private class VariableSelectionDialog extends StatusDialog implements IStatusChangeListener {	
		private VariableSelectionBlock fVariableSelectionBlock;
		IDialogSettings dialogSettings= JEMUIPlugin.getPlugin().getDialogSettings();
				
		public VariableSelectionDialog(Shell parent, List existingPaths) {
			super(parent);
			setTitle("New Variable Classpath Entry"); //$NON-NLS-1$
			String initVar= dialogSettings.get(DIALOGSTORE_LASTVARIABLE);
			fVariableSelectionBlock= new VariableSelectionBlock(this, existingPaths, null, initVar, false);
		}
		
		/*
		 * @see Windows#configureShell
		 */
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
//			WorkbenchHelp.setHelp(newShell, IJavaHelpContextIds.VARIABLE_SELECTION_DIALOG);
		}		

		/*
		 * @see StatusDialog#createDialogArea()
		 */				
		protected Control createDialogArea(Composite parent) {
			Composite composite= (Composite)super.createDialogArea(parent);
					
			Label message= new Label(composite, SWT.WRAP);
			message.setText("New Variable Classpath Entry"); //$NON-NLS-1$
			message.setLayoutData(new GridData());	
						
			Control inner= fVariableSelectionBlock.createControl(composite);
			inner.setLayoutData(new GridData(GridData.FILL_BOTH));
			return composite;
		}
		
		/*
		 * @see Dialog#okPressed()
		 */
		protected void okPressed() {
			dialogSettings.put(DIALOGSTORE_LASTVARIABLE, getVariable().segment(0));
			super.okPressed();
		}	

		/*
		 * @see IStatusChangeListener#statusChanged()
		 */			
		public void statusChanged(IStatus status) {
			updateStatus(status);
		}
		
		public IPath getVariable() {
			return fVariableSelectionBlock.getVariablePath();
		}		
	}

	
	//controls
	private Composite javaProjectsComposite = null;
	private Label label = null;
	private Table table = null;
	private Composite buttonsBar = null;
	private Button addFoldersbutton = null;
	private Button addJarsButton = null;
	private Button addExtJarsButton = null;
	private Button addVariableButton = null;
	private Button addProjectButton = null;
	private Button modifyPathsButton = null;
	private Button removeButton = null;
	private Label spacer2 = null;
	private Label spacer1 = null;
	// .. controls
	private TableViewer tableViewer;
	
	private BeaninfoPathsBlock beaninfoPathsBlock;
	private IJavaProject javaProject;
	private List beaninfosList;
	private IWorkspaceRoot workspaceRoot;
	private IClasspathEntry[] resolvedList;
	private IClasspathEntry[] rawList;
	private SearchPathListLabelProvider labelProvider;

	public BeaninfosWorkbookPage(IWorkspaceRoot workspaceRoot, BeaninfoPathsBlock beaninfoPathsBlock) {
		this.workspaceRoot = workspaceRoot;
		this.beaninfoPathsBlock = beaninfoPathsBlock;
		this.labelProvider = new SearchPathListLabelProvider();
		this.beaninfosList = new ArrayList();
		if(spacer1==null || spacer2==null){
			// just have SOME read access
		}
	}

	/**
	 * This method initializes javaProjectsComposite	
	 *
	 */
	public Control createControl(Composite parent) {
		GridData gridData = new org.eclipse.swt.layout.GridData();
		gridData.horizontalSpan = 2;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		javaProjectsComposite = new Composite(parent, SWT.NONE);
		javaProjectsComposite.setLayout(gridLayout);
		label = new Label(javaProjectsComposite, SWT.NONE);
		label.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_List_Text);
		label.setLayoutData(gridData);
		createTable();
		createButtonsBar();
		updateEnabledStates();
		return javaProjectsComposite;
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
		table = new Table(javaProjectsComposite, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
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
		tableViewer.setSorter(new BIListElementSorter());
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(labelProvider);
		tableViewer.setInput(beaninfosList);
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
		buttonsBar = new Composite(javaProjectsComposite, SWT.NONE);
		buttonsBar.setLayoutData(gridData2);
		buttonsBar.setLayout(rowLayout);
		addFoldersbutton = new Button(buttonsBar, SWT.NONE);
		addFoldersbutton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_AddFolders);
		addFoldersbutton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List beaninfoEntries = chooseClassContainers();
				addToBeaninfosList(beaninfoEntries);
			}
		});
		addJarsButton = new Button(buttonsBar, SWT.NONE);
		addJarsButton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_AddJARs);
		addJarsButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List beaninfoEntries = chooseJarFiles();
				addToBeaninfosList(beaninfoEntries);
			}
		});
		addExtJarsButton = new Button(buttonsBar, SWT.NONE);
		addExtJarsButton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_AddExternalJAR);
		addExtJarsButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List beaninfoEntries = chooseExtJarFiles();
				addToBeaninfosList(beaninfoEntries);
			}
		});
		addVariableButton = new Button(buttonsBar, SWT.NONE);
		addVariableButton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_AddVariable);
		addVariableButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List beaninfoEntries = chooseVariableEntries();
				addToBeaninfosList(beaninfoEntries);
			}
		});
		addProjectButton = new Button(buttonsBar, SWT.NONE);
		addProjectButton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_AddProjects);
		addProjectButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List beaninfoEntries = chooseProjects();
				addToBeaninfosList(beaninfoEntries);
			}
		});
		spacer1 = new Label(buttonsBar, SWT.NONE);
		modifyPathsButton = new Button(buttonsBar, SWT.NONE);
		modifyPathsButton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_ModifyPaths);
		modifyPathsButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				modifySearchPaths();
			}
		});
		spacer2 = new Label(buttonsBar, SWT.NONE);
		removeButton = new Button(buttonsBar, SWT.NONE);
		removeButton.setText(BeanInfoUIMessages.BeanInfosWorkbookPage_Remove);
		removeButton.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
				beaninfosList.removeAll(selected);
				tableViewer.refresh();
				pageChanged();
			}
		});
	}
	
	private List chooseVariableEntries() {
		// Remove existing variable entries in both the classpath and the beaninfopaths.
		// Don't want classpath ones because they should be of been selected in the search paths page.
		ArrayList existingPaths= new ArrayList();
		for (int i= 0; i < rawList.length; i++) {
			if (rawList[i].getEntryKind() == IClasspathEntry.CPE_VARIABLE) {
				existingPaths.add(rawList[i].getPath());
			}
		}
		
		List ours = beaninfosList;
		for (int i = 0; i < ours.size(); i++) {
			BPBeaninfoListElement bpb = (BPBeaninfoListElement) ours.get(i);
			if (bpb.getEntry().getKind() == IClasspathEntry.CPE_VARIABLE)
				existingPaths.add(bpb.getEntry().getPath());
		}
		
		VariableSelectionDialog dialog= new VariableSelectionDialog(addVariableButton.getShell(), existingPaths);
		if (dialog.open() == Window.OK) {
			IPath path= dialog.getVariable();
			IClasspathEntry cpe = JavaCore.newVariableEntry(path, null, null);
			IPath resolvedPath= JavaCore.getResolvedVariablePath(path);
			boolean isMissing = resolvedPath == null || !resolvedPath.toFile().isFile();
			return Collections.singletonList(new BPBeaninfoListElement(new BeaninfoEntry(cpe, null, true), null, isMissing));
		}
		return null;
	}	
	
	private List chooseJarFiles() {
		IPath[] jarPaths = BuildPathDialogAccess.chooseJAREntries(addJarsButton.getShell(), javaProject.getPath(), getUsedJARFiles());
		if (jarPaths!=null) {
			List res= new ArrayList(jarPaths.length);
			for (int i= 0; i < jarPaths.length; i++) {
				BPBeaninfoListElement newGuy = newBPBeaninfoListElementFromFullpath(jarPaths[i]);
				if (newGuy != null)
					res.add(newGuy);
			}
			return res;
		}
		return null;
	}

	private List chooseExtJarFiles() {
		IPath[] extJARPaths = BuildPathDialogAccess.chooseExternalJAREntries(addExtJarsButton.getShell());
		List elems = null;
		if(extJARPaths!=null){
			elems = new ArrayList(extJARPaths.length);
			for (int i= 0; i < extJARPaths.length; i++) {
				BPBeaninfoListElement newGuy = newBPBeaninfoListElement(extJARPaths[i]);
				if (newGuy != null)
					elems.add(newGuy);
			}
		}
		return elems;
	}

	private BPBeaninfoListElement newBPBeaninfoListElement(IPath path) {
		// Create for an external, if not already used in either classpath or beaninfo path.
		// These can't be pre-selected out like for the other choose dialogs.
		for (int i = 0; i < resolvedList.length; i++) {
			IClasspathEntry cpe = resolvedList[i];
			if (cpe != null && cpe.getEntryKind() == IClasspathEntry.CPE_LIBRARY && cpe.getPath().equals(path))
				return null;	// Already exists.
		}
		
		// Now see if one of ours.
		List ours = beaninfosList;
		for (int i = 0; i < ours.size(); i++) {
			BPBeaninfoListElement bpb = (BPBeaninfoListElement) ours.get(i);
			if (bpb.getEntry().getKind() == IClasspathEntry.CPE_LIBRARY && bpb.getEntry().getPath().equals(path))
				return null;	// Already exists
		}
		
		IClasspathEntry cpe = JavaCore.newLibraryEntry(path, null, null);
		BeaninfoEntry bie = new BeaninfoEntry(cpe, null, true);
		return new BPBeaninfoListElement(bie, null, false);
	}
	

	private IPath[] getUsedJARFiles() {
		// Jars used by both the classpath and the beaninfo search path.
		// Don't want jars used by classpath because those would then be
		// in the buildpath and user would select paths through the search path page.
		ArrayList res= new ArrayList();
		for (int i= 0; i < resolvedList.length; i++) {
			if (resolvedList[i] != null && resolvedList[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
				IPath path = resolvedList[i].getPath();
				IResource resource= workspaceRoot.findMember(path);
				if (resource instanceof IFile)
					res.add(path);
			}
		}

		List bilist= beaninfosList;
		for (int i= 0; i < bilist.size(); i++) {
			BPListElement elem= (BPListElement)bilist.get(i);
			if (elem.getEntry().getKind() == IClasspathEntry.CPE_LIBRARY) {
				IPath path = elem.getEntry().getPath();
				IResource resource= workspaceRoot.findMember(path);
				if (resource instanceof IFile)
					res.add(path);
			}
		}
		return (IPath[]) res.toArray(new IPath[res.size()]);
	}	

	/**
	 * Create a new BPListElement for the given object.
	 */
	private BPBeaninfoListElement newBPBeaninfoListElement(IResource element) {
		if (element instanceof IContainer || element instanceof IFile) {
			return newBPBeaninfoListElementFromFullpath(element.getFullPath());
		}
		return null;
	}
	
	/**
	 * Create a new BPListElement for the given object.
	 * @since 1.2.0
	 */
	private BPBeaninfoListElement newBPBeaninfoListElementFromFullpath(IPath fullPath) {
		if(fullPath!=null){
			IClasspathEntry cpe = JavaCore.newLibraryEntry(fullPath, null, null);
			BeaninfoEntry bie = new BeaninfoEntry(cpe, null, true);
			return new BPBeaninfoListElement(bie, null, false);
		}
		return null;
	}

	private List chooseClassContainers() {	
		Class[] acceptedClasses= new Class[] { IFolder.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, true);
			
		acceptedClasses= new Class[] { IProject.class, IFolder.class };

		ViewerFilter filter= new TypedViewerFilter(acceptedClasses, getUsedContainers());	
			
		ILabelProvider lp= new WorkbenchLabelProvider();
		ITreeContentProvider cp= new WorkbenchContentProvider();

		ElementTreeSelectionDialog dialog= new ElementTreeSelectionDialog(addFoldersbutton.getShell(), lp, cp);
		dialog.setValidator(validator);
		dialog.setTitle(BeanInfoUIMessages.BeanInfosWorkbookPage_SelectionDialog_Classes_Title); 
		dialog.setMessage(BeanInfoUIMessages.BeanInfosWorkbookPage_SelectionDialog_Classes_Prompt);  
		dialog.addFilter(filter);
		dialog.setInput(workspaceRoot);
		dialog.setInitialSelection(javaProject.getProject());
		
		if (dialog.open() == Window.OK) {
			Object[] elements= dialog.getResult();
			List res= new ArrayList(elements.length);
			for (int i= 0; i < elements.length; i++) {
				BPBeaninfoListElement newGuy = newBPBeaninfoListElement((IResource) elements[i]);
				if (newGuy != null)
					res.add(newGuy);
			}
			return res;
		}
		return null;		
	}
	
	private IContainer[] getUsedContainers() {
		// Containers used by both the classpath and the beaninfo search path.
		// Don't want containers used by classpath because those would then be
		// in the buildpath and user would select paths through the search path page.
		ArrayList res= new ArrayList();
		try {
			IPath outputLocation= javaProject.getOutputLocation();
			if (outputLocation != null) {
				IResource resource= workspaceRoot.findMember(outputLocation);
				if (resource instanceof IContainer) {
					res.add(resource);
				}
			}
		} catch (JavaModelException e) {
			// ignore it here, just log
			JEMUIPlugin.getPlugin().getLogger().log(e.getStatus());
		}
			
		for (int i= 0; i < resolvedList.length; i++) {
			if (resolvedList[i] != null && resolvedList[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IResource resource= workspaceRoot.findMember(resolvedList[i].getPath());
				if (resource instanceof IContainer) {
					res.add(resource);
				}
			}
		}

		List bilist= beaninfosList;
		for (int i= 0; i < bilist.size(); i++) {
			BPListElement elem= (BPListElement)bilist.get(i);
			if (elem.getEntry().getKind() == IClasspathEntry.CPE_SOURCE) {
				IResource resource= workspaceRoot.findMember(elem.getEntry().getPath());
				res.add(resource);
			}
		}
		return (IContainer[]) res.toArray(new IContainer[res.size()]);
	}
	

	protected void addToBeaninfosList(final List toAdd){
		if (beaninfosList != null && toAdd!=null) {
			beaninfosList.addAll(toAdd);
			tableViewer.refresh();
			table.getDisplay().asyncExec(new Runnable(){
				public void run() {
					tableViewer.setSelection(new StructuredSelection(toAdd));
				}
			});
			pageChanged();
		}
	}
	
	private void pageChanged() {
		if (javaProject != null) {
			// already initialized
			updateSearchpathList();
		}
	}

	private void updateSearchpathList() {
		List searchelements = beaninfoPathsBlock.getSearchpathOrderingPage().getElements();

		List beaninfoelements = new ArrayList(beaninfosList);

		boolean changeDone = false;
		// First go through the search path and remove any Beaninfo list elements that
		// are not in this pages beaninfos list.
		for (ListIterator spitr = searchelements.listIterator(searchelements.size());
			spitr.hasPrevious();
			) {
			BPListElement element = (BPListElement) spitr.previous();
			if (element instanceof BPBeaninfoListElement && !beaninfoelements.remove(element)) {
				// Search element and not found in packages list so remove it from searchpath list.
				spitr.remove();
				changeDone = true;
			}
		}
		// Any left over in beaninfos list are new and need to be added.
		searchelements.addAll(beaninfoelements);
		changeDone = changeDone || !beaninfoelements.isEmpty();

		if (changeDone)
			beaninfoPathsBlock.setSearchOrderElements(searchelements);
	}

	public void init(IJavaProject javaProject){
		this.javaProject = javaProject;
		labelProvider.setJavaProject(this.javaProject);
		try {
			rawList = this.javaProject.getRawClasspath();
			resolvedList = new IClasspathEntry[rawList.length];
			for (int i = 0; i < rawList.length; i++) {
				resolvedList[i] = JavaCore.getResolvedClasspathEntry(rawList[i]);
			}
		} catch (JavaModelException e) {
			rawList = resolvedList = new IClasspathEntry[0];
		}
		updateBeaninfosList();
	}

	private void updateBeaninfosList() {
		List spelements = beaninfoPathsBlock.getSearchpathOrderingPage().getElements();

		List biElements = new ArrayList(spelements.size());
		for (int i = 0; i < spelements.size(); i++) {
			BPListElement spe = (BPListElement) spelements.get(i);
			if (spe instanceof BPBeaninfoListElement) {
				biElements.add(spe);
			}
		}
		beaninfosList.clear();
		beaninfosList.addAll(biElements);
		if(tableViewer!=null && !table.isDisposed())
			tableViewer.refresh();
	}

	public List getSelection() {
		if(tableViewer!=null)
			return BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
		return null;
	}

	public void setSelection(List selection) {
		if(tableViewer!=null)
			tableViewer.setSelection(new StructuredSelection(selection));
	}

	
	protected void updateButtons(){
		List selected = BeaninfoPathsBlock.getSelectedList(tableViewer.getSelection());
		addExtJarsButton.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		addFoldersbutton.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		addJarsButton.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		addProjectButton.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		addVariableButton.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		
		removeButton.setEnabled(selected!=null && selected.size()>0 && beaninfoPathsBlock.isBeaninfoEnabled());
		modifyPathsButton.setEnabled(selected!=null && selected.size()>0 && beaninfoPathsBlock.isBeaninfoEnabled());
	}
	
	private List chooseProjects() {	
		Class[] acceptedClasses= new Class[] { IJavaProject.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, true);

		List allProjects = null;
		try {
			allProjects = new ArrayList(Arrays.asList(javaProject.getJavaModel().getChildren()));
		} catch(JavaModelException e) {
			allProjects = Collections.EMPTY_LIST;
		}
		allProjects.removeAll(getUsedProjects());
			
		ILabelProvider lp= new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT);

		ElementListSelectionDialog dialog= new ElementListSelectionDialog(addProjectButton.getShell(), lp);
		dialog.setValidator(validator);
		dialog.setTitle(BeanInfoUIMessages.BeanInfosWorkbookPage_SelectionDialog_Projects_Title); 
		dialog.setMessage(BeanInfoUIMessages.BeanInfosWorkbookPage_SelectionDialog_Projects_Prompt); 
		dialog.setElements(allProjects.toArray());
		
		if (dialog.open() == Window.OK) {
			Object[] elements= dialog.getResult();
			List res= new ArrayList(elements.length);
			for (int i= 0; i < elements.length; i++) {
				try {
					IClasspathEntry cpe = JavaCore.newProjectEntry(((IJavaProject) elements[i]).getCorrespondingResource().getFullPath());
					BeaninfoEntry bie = new BeaninfoEntry(cpe, null, true);
					res.add(new BPBeaninfoListElement(bie, null, false));
				} catch(JavaModelException e) {
				}
			}
			return res;
		}
		return null;		
	}
	
	private List getUsedProjects() {
		// Projects used by both the classpath and the beaninfo search path.
		// Don't want projects used by classpath because those would then be
		// in the buildpath and user would select paths through the search path page.
		ArrayList res= new ArrayList();
		res.add(javaProject);	// Plus our own project is used.
		IJavaModel jmodel = javaProject.getJavaModel();
		for (int i= 0; i < resolvedList.length; i++) {
			if (resolvedList[i] != null && resolvedList[i].getEntryKind() == IClasspathEntry.CPE_PROJECT) {
				res.add(jmodel.getJavaProject(resolvedList[i].getPath().segment(0)));
			}
		}

		List bilist= beaninfosList;
		for (int i= 0; i < bilist.size(); i++) {
			BPListElement elem= (BPListElement)bilist.get(i);
			if (elem.getEntry().getKind() == IClasspathEntry.CPE_PROJECT) 
				res.add(jmodel.getJavaProject(elem.getEntry().getPath().segment(0)));			
		}
		return res;
	}

	private void modifySearchPaths() {
		// Bring up the dialog for modifying search paths of a particular beaninfo entry.
		BPBeaninfoListElement elem = (BPBeaninfoListElement) ((IStructuredSelection)tableViewer.getSelection()).getFirstElement();	// There should only be one, button not enabled if none or more than one selected.
		BeaninfoEntrySearchpathDialog dialog = new BeaninfoEntrySearchpathDialog(modifyPathsButton.getShell(), elem, javaProject);
		dialog.open();
	}
	
	protected void updateEnabledStates(){
		updateButtons();
		table.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
		label.setEnabled(beaninfoPathsBlock.isBeaninfoEnabled());
	}
	
	public void setBeaninfoEnabled(boolean enable) {
		if(javaProjectsComposite!=null && !javaProjectsComposite.isDisposed()){ 
			// ui populated
			updateEnabledStates();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
