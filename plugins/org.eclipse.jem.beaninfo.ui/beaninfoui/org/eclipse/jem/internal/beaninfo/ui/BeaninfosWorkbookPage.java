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
 *  $RCSfile: BeaninfosWorkbookPage.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:20:50 $ 
 */

import java.util.*;
import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoEntry;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.internal.ui.dialogs.StatusDialog;
import org.eclipse.jdt.internal.ui.util.PixelConverter;
import org.eclipse.jdt.internal.ui.wizards.*;
import org.eclipse.jdt.internal.ui.wizards.buildpaths.ArchiveFileFilter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.*;
import org.eclipse.jdt.ui.JavaElementLabelProvider;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.dialogs.*;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * @version 	1.0
 * @author
 */
public class BeaninfosWorkbookPage extends BuildSearchBasePage {
	
	private BeaninfoPathsBlock biPathsBlock;
	private IJavaProject fCurrJProject;
	
	private ListDialogField fBeaninfosList;
	private IWorkspaceRoot fWorkspaceRoot;
	
	private IDialogSettings fDialogSettings;
	
	private Control fSWTControl;
	
	private IClasspathEntry[] resolvedList;
	private IClasspathEntry[] rawList;
	
	private static final String DIALOGSTORE_LASTEXTJAR = BeaninfoUIPlugin.PI_BEANINFO_UI + ".lastextjar"; //$NON-NLS-1$
	private static final String DIALOGSTORE_LASTVARIABLE = BeaninfoUIPlugin.PI_BEANINFO_UI + ".lastvar"; //$NON-NLS-1$
		
	public BeaninfosWorkbookPage(IWorkspaceRoot root, BeaninfoPathsBlock biPathsBlock, List interestedFieldsForEnableControl) {
		this.biPathsBlock = biPathsBlock;
		fWorkspaceRoot= root;
		fSWTControl= null;
		
		fDialogSettings= BeaninfoUIPlugin.getPlugin().getDialogSettings();
		
		String[] buttonLabels= new String[] { 
			/* 0 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.AddFolders"), //$NON-NLS-1$
			/* 1 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.AddJARs"), //$NON-NLS-1$
			/* 2 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.AddExternalJAR"), //$NON-NLS-1$
			/* 3 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.AddVariable"), //$NON-NLS-1$
			/* 4 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.AddProjects"), //$NON-NLS-1$
			/* 5 */ null,
			/* 6 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.ModifyPaths"), //$NON-NLS-1$
			/* 7 */ null,  
			/* 8 */ BeanInfoUIMessages.getString("BeanInfosWorkbookPage.Remove") //$NON-NLS-1$
		};		
				
		BeaninfosAdapter adapter= new BeaninfosAdapter();
				
		fBeaninfosList= new ListDialogField(adapter, buttonLabels, new SearchPathListLabelProvider());
		fBeaninfosList.setDialogFieldListener(adapter);
		fBeaninfosList.setLabelText(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.List.Text")); //$NON-NLS-1$
		fBeaninfosList.setRemoveButtonIndex(8);
		fBeaninfosList.enableButton(6, false);	// Need to initially disable it and let the selection state handle it from there.
		
		fBeaninfosList.setViewerSorter(new BIListElementSorter());
		
		interestedFieldsForEnableControl.add(fBeaninfosList);

	}
		
	public void init(IJavaProject jproject) {
		fCurrJProject= jproject;
		try {
			rawList = fCurrJProject.getRawClasspath();
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
		List spelements = biPathsBlock.getSearchOrder().getElements();

		List biElements = new ArrayList(spelements.size());
		for (int i = 0; i < spelements.size(); i++) {
			BPListElement spe = (BPListElement) spelements.get(i);
			if (spe instanceof BPBeaninfoListElement) {
				biElements.add(spe);
			}
		}
		fBeaninfosList.setElements(biElements);
	}	
		
	// -------- ui creation
	
	public Control getControl(Composite parent) {
		PixelConverter converter= new PixelConverter(parent);
		
		Composite composite= new Composite(parent, SWT.NONE);
			
		LayoutUtil.doDefaultLayout(composite, new DialogField[] { fBeaninfosList }, true, SWT.DEFAULT, SWT.DEFAULT);
		int buttonBarWidth= converter.convertWidthInCharsToPixels(24);
		fBeaninfosList.setButtonsMinWidth(buttonBarWidth);
		
		fSWTControl = composite;
				
		return composite;
	}
	
	private Shell getShell() {
		if (fSWTControl != null) {
			return fSWTControl.getShell();
		}
		return BeaninfoUIPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell();
	}
	
	
	private class BeaninfosAdapter implements IDialogFieldListener, IListAdapter {
		
		// -------- IListAdapter --------
		public void customButtonPressed(ListDialogField field, int index) {
			beaninfosPageCustomButtonPressed(index);
		}
		
		public void selectionChanged(ListDialogField field) {
			ListDialogField list = (ListDialogField) field;
			int selCnt = list.getSelectedElements().size();
			list.enableButton(6, selCnt == 1);	// Only want the button enabled if one beaninfo is selected.
			
		}
			
		// ---------- IDialogFieldListener --------
	
		public void dialogFieldChanged(DialogField field) {
			beaninfosPageDialogFieldChanged();
		}
		/**
		 * @see org.eclipse.jdt.internal.ui.wizards.dialogfields.IListAdapter#doubleClicked(ListDialogField)
		 */
		public void doubleClicked(ListDialogField field) {
		}

	}
	
	private void beaninfosPageCustomButtonPressed(int index) {
		List beaninfoEntries= null;
		switch (index) {
		case 0: /* add existing */
			beaninfoEntries= chooseClassContainers();
			break;
		case 1: /* add jar */
			beaninfoEntries= chooseJarFiles();
			break;
		case 2: /* add external jar */
			beaninfoEntries= chooseExtJarFiles();
			break;
		case 3: /* add variable */
			beaninfoEntries= chooseVariableEntries();
			break;
		case 4: /* add projects */
			beaninfoEntries= chooseProjects();
			break;
		case 6: /* Modify search paths within beaninfos. */
			modifySearchPaths();
			return;
		}
		
		if (beaninfoEntries != null) {
			fBeaninfosList.addElements(beaninfoEntries);
			fBeaninfosList.postSetSelection(new StructuredSelection(beaninfoEntries));
		}
	}
	
	private void modifySearchPaths() {
		// Bring up the dialog for modifying search paths of a particular beaninfo entry.
		BPBeaninfoListElement elem = (BPBeaninfoListElement) fBeaninfosList.getSelectedElements().get(0);	// There should only be one, button not enabled if none or more than one selected.
		BeaninfoEntrySearchpathDialog dialog = new BeaninfoEntrySearchpathDialog(getShell(), elem, fCurrJProject);
		dialog.open();
	}
	
	private void beaninfosPageDialogFieldChanged() {
		if (fCurrJProject != null) {
			// already initialized
			updateSearchpathList();
		}
	}			
	
	private void updateSearchpathList() {
		List searchelements = biPathsBlock.getSearchOrder().getElements();

		List beaninfoelements = fBeaninfosList.getElements();

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
			biPathsBlock.setSearchOrderElements(searchelements);
	}
			
			
	private List chooseClassContainers() {	
		Class[] acceptedClasses= new Class[] { IFolder.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, true);
			
		acceptedClasses= new Class[] { IProject.class, IFolder.class };

		ViewerFilter filter= new TypedViewerFilter(acceptedClasses, getUsedContainers());	
			
		ILabelProvider lp= new WorkbenchLabelProvider();
		ITreeContentProvider cp= new WorkbenchContentProvider();

		ElementTreeSelectionDialog dialog= new ElementTreeSelectionDialog(getShell(), lp, cp);
		dialog.setValidator(validator);
		dialog.setTitle(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.Classes.Title")); //$NON-NLS-1$
		dialog.setMessage(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.Classes.Prompt"));  //$NON-NLS-1$
		dialog.addFilter(filter);
		dialog.setInput(fWorkspaceRoot);
		dialog.setInitialSelection(fCurrJProject.getProject());
		
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
	
	private List chooseJarFiles() {
		Class[] acceptedClasses= new Class[] { IFile.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, true);
		ViewerFilter filter= new ArchiveFileFilter(getUsedJARFiles(), true);
		
		ILabelProvider lp= new WorkbenchLabelProvider();
		ITreeContentProvider cp= new WorkbenchContentProvider();

		ElementTreeSelectionDialog dialog= new ElementTreeSelectionDialog(getShell(), lp, cp);
		dialog.setValidator(validator);
		dialog.setTitle(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.JARs.Title")); //$NON-NLS-1$
		dialog.setMessage(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.JARs.Message")); //$NON-NLS-1$
		dialog.addFilter(filter);
		dialog.setInput(fWorkspaceRoot);
		dialog.setInitialSelection(fCurrJProject.getProject());		

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
			IPath outputLocation= fCurrJProject.getOutputLocation();
			if (outputLocation != null) {
				IResource resource= fWorkspaceRoot.findMember(outputLocation);
				if (resource instanceof IContainer) {
					res.add(resource);
				}
			}
		} catch (JavaModelException e) {
			// ignore it here, just log
			BeaninfoUIPlugin.getPlugin().getMsgLogger().log(e.getStatus());
		}
			
		for (int i= 0; i < resolvedList.length; i++) {
			if (resolvedList[i] != null && resolvedList[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IResource resource= fWorkspaceRoot.findMember(resolvedList[i].getPath());
				if (resource instanceof IContainer) {
					res.add(resource);
				}
			}
		}

		List bilist= fBeaninfosList.getElements();
		for (int i= 0; i < bilist.size(); i++) {
			BPListElement elem= (BPListElement)bilist.get(i);
			if (elem.getEntry().getKind() == IClasspathEntry.CPE_SOURCE) {
				IResource resource= fWorkspaceRoot.findMember(elem.getEntry().getPath());
				res.add(resource);
			}
		}
		return (IContainer[]) res.toArray(new IContainer[res.size()]);
	}
	
	private IFile[] getUsedJARFiles() {
		// Jars used by both the classpath and the beaninfo search path.
		// Don't want jars used by classpath because those would then be
		// in the buildpath and user would select paths through the search path page.
		ArrayList res= new ArrayList();
		for (int i= 0; i < resolvedList.length; i++) {
			if (resolvedList[i] != null && resolvedList[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
				IResource resource= fWorkspaceRoot.findMember(resolvedList[i].getPath());
				if (resource instanceof IFile)
					res.add(resource);
			}
		}

		List bilist= fBeaninfosList.getElements();
		for (int i= 0; i < bilist.size(); i++) {
			BPListElement elem= (BPListElement)bilist.get(i);
			if (elem.getEntry().getKind() == IClasspathEntry.CPE_LIBRARY) {
				IResource resource= fWorkspaceRoot.findMember(elem.getEntry().getPath());
				if (resource instanceof IFile)
					res.add(resource);
			}
		}
		return (IFile[]) res.toArray(new IFile[res.size()]);
	}	
	
	/**
	 * Create a new BPListElement for the given object.
	 */
	private BPBeaninfoListElement newBPBeaninfoListElement(IResource element) {
		if (element instanceof IContainer || element instanceof IFile) {
			IClasspathEntry cpe = JavaCore.newLibraryEntry(element.getFullPath(), null, null);
			BeaninfoEntry bie = new BeaninfoEntry(cpe, null, true);
			return new BPBeaninfoListElement(bie, null, false);
		}
		
		return null;
	}

	
	private List chooseExtJarFiles() {
		String lastUsedPath= fDialogSettings.get(DIALOGSTORE_LASTEXTJAR);
		if (lastUsedPath == null) {
			lastUsedPath= ""; //$NON-NLS-1$
		}
		FileDialog dialog= new FileDialog(getShell(), SWT.MULTI);
		dialog.setText(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.ExtJARs.Text")); //$NON-NLS-1$
		dialog.setFilterExtensions(new String[] {"*.jar;*.zip"}); //$NON-NLS-1$
		dialog.setFilterPath(lastUsedPath);
		String res= dialog.open();
		if (res == null) {
			return null;
		}
		String[] fileNames= dialog.getFileNames();
		int nChosen= fileNames.length;
			
		IPath filterPath= new Path(dialog.getFilterPath());
		List elems = new ArrayList(nChosen);
		for (int i= 0; i < nChosen; i++) {
			IPath path= filterPath.append(fileNames[i]).makeAbsolute();	
			BPBeaninfoListElement newGuy = newBPBeaninfoListElement(path);
			if (newGuy != null)
				elems.add(newGuy);
		}
		fDialogSettings.put(DIALOGSTORE_LASTEXTJAR, filterPath.toOSString());
		
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
		List ours = fBeaninfosList.getElements();
		for (int i = 0; i < ours.size(); i++) {
			BPBeaninfoListElement bpb = (BPBeaninfoListElement) ours.get(i);
			if (bpb.getEntry().getKind() == IClasspathEntry.CPE_LIBRARY && bpb.getEntry().getPath().equals(path))
				return null;	// Already exists
		}
		
		IClasspathEntry cpe = JavaCore.newLibraryEntry(path, null, null);
		BeaninfoEntry bie = new BeaninfoEntry(cpe, null, true);
		return new BPBeaninfoListElement(bie, null, false);
	}
	
	
	private List chooseProjects() {	
		Class[] acceptedClasses= new Class[] { IJavaProject.class };
		ISelectionStatusValidator validator= new TypedElementSelectionValidator(acceptedClasses, true);

		List allProjects = null;
		try {
			allProjects = new ArrayList(Arrays.asList(fCurrJProject.getJavaModel().getChildren()));
		} catch(JavaModelException e) {
			allProjects = Collections.EMPTY_LIST;
		}
		allProjects.removeAll(getUsedProjects());
			
		ILabelProvider lp= new JavaElementLabelProvider(JavaElementLabelProvider.SHOW_DEFAULT);

		ElementListSelectionDialog dialog= new ElementListSelectionDialog(getShell(), lp);
		dialog.setValidator(validator);
		dialog.setTitle(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.Projects.Title")); //$NON-NLS-1$
		dialog.setMessage(BeanInfoUIMessages.getString("BeanInfosWorkbookPage.SelectionDialog.Projects.Prompt")); //$NON-NLS-1$
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
		res.add(fCurrJProject);	// Plus our own project is used.
		IJavaModel jmodel = fCurrJProject.getJavaModel();
		for (int i= 0; i < resolvedList.length; i++) {
			if (resolvedList[i] != null && resolvedList[i].getEntryKind() == IClasspathEntry.CPE_PROJECT) {
				res.add(jmodel.getJavaProject(resolvedList[i].getPath().segment(0)));
			}
		}

		List bilist= fBeaninfosList.getElements();
		for (int i= 0; i < bilist.size(); i++) {
			BPListElement elem= (BPListElement)bilist.get(i);
			if (elem.getEntry().getKind() == IClasspathEntry.CPE_PROJECT) 
				res.add(jmodel.getJavaProject(elem.getEntry().getPath().segment(0)));			
		}
		return res;
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
		
		List ours = fBeaninfosList.getElements();
		for (int i = 0; i < ours.size(); i++) {
			BPBeaninfoListElement bpb = (BPBeaninfoListElement) ours.get(i);
			if (bpb.getEntry().getKind() == IClasspathEntry.CPE_VARIABLE)
				existingPaths.add(bpb.getEntry().getPath());
		}
		
		VariableSelectionDialog dialog= new VariableSelectionDialog(getShell(), existingPaths);
		if (dialog.open() == Window.OK) {
			IPath path= dialog.getVariable();
			IClasspathEntry cpe = JavaCore.newVariableEntry(path, null, null);
			IPath resolvedPath= JavaCore.getResolvedVariablePath(path);
			boolean isMissing = resolvedPath == null || !resolvedPath.toFile().isFile();
			return Collections.singletonList(new BPBeaninfoListElement(new BeaninfoEntry(cpe, null, true), null, isMissing));
		}
		return null;
	}	
	

	// a dialog to choose a variable
	private class VariableSelectionDialog extends StatusDialog implements IStatusChangeListener {	
		private VariableSelectionBlock fVariableSelectionBlock;
				
		public VariableSelectionDialog(Shell parent, List existingPaths) {
			super(parent);
			setTitle(NewWizardMessages.getString("LibrariesWorkbookPage.VariableSelectionDialog.title")); //$NON-NLS-1$
			String initVar= fDialogSettings.get(DIALOGSTORE_LASTVARIABLE);
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
			message.setText(NewWizardMessages.getString("LibrariesWorkbookPage.VariableSelectionDialog.message")); //$NON-NLS-1$
			message.setLayoutData(new GridData());	
						
			Control inner= fVariableSelectionBlock.createControl(composite);
			inner.setLayoutData(new GridData(GridData.FILL_BOTH));
			return composite;
		}
		
		/*
		 * @see Dialog#okPressed()
		 */
		protected void okPressed() {
			fDialogSettings.put(DIALOGSTORE_LASTVARIABLE, getVariable().segment(0));
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
				
	// a dialog to set the source attachment properties
	
	/*
	 * @see BuildPathBasePage#getSelection
	 */
	public List getSelection() {
		return fBeaninfosList.getSelectedElements();
	}

	/*
	 * @see BuildPathBasePage#setSelection
	 */	
	public void setSelection(List selElements) {
		fBeaninfosList.selectElements(new StructuredSelection(selElements));
	}	


}
