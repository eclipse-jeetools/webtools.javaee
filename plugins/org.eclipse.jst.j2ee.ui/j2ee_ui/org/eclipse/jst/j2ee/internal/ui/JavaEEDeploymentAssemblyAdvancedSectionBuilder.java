/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.impl.TaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ResourceMappingFilterExtensionRegistry;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage.ComponentResourceProxy;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;

/**
 * This is a helper class to build and handle the logic of the "Advanced" section 
 * of the Deployment Assembly page that is common to all the Java EE modules (EAR
 * project, Web project, etc.)
 *  
 */

public class JavaEEDeploymentAssemblyAdvancedSectionBuilder implements IJavaEEDeploymentAssemblySectionBuilder, SelectionListener {
	
	private Label defaulDDFolderLabel;
	private Combo rootSourceMappings;
		
	private IVirtualComponent rootComponent;
	private AddModuleDependenciesPropertiesPage page;
	private String currentSelectedDDFolder = null;
	private List<String> resourceMappingsList = new ArrayList<String>();
	boolean shouldDisplaySection;
	
	String folderToLook;
	String fileToLook;
	String projectType;
	
	
	public JavaEEDeploymentAssemblyAdvancedSectionBuilder(IVirtualComponent component, AddModuleDependenciesPropertiesPage page){
		rootComponent = component;
		this.page = page;
		shouldDisplaySection = !JavaEEProjectUtilities.isUtilityProject(rootComponent.getProject());
		IVirtualFolder rootFolder = rootComponent.getRootFolder();
		IPath defaultDDFolder = J2EEModuleVirtualComponent.getDefaultDeploymentDescriptorFolder(rootFolder);
		shouldDisplaySection &= (defaultDDFolder == null);
		projectType = JavaEEProjectUtilities.getJ2EEProjectType(component.getProject());
		if (projectType.equals(IJ2EEFacetConstants.APPLICATION_CLIENT))	{
			folderToLook = J2EEConstants.META_INF;
			fileToLook = J2EEConstants.APP_CLIENT_DD_URI;
		}
		else if (projectType.equals(IJ2EEFacetConstants.JCA)) {
			folderToLook = J2EEConstants.META_INF;
			fileToLook = J2EEConstants.RAR_DD_URI;
		}
		else if	(projectType.equals(IJ2EEFacetConstants.EJB)){
			folderToLook = J2EEConstants.META_INF;
			fileToLook = J2EEConstants.EJBJAR_DD_URI;
		}
		else if (projectType.equals(IJ2EEFacetConstants.DYNAMIC_WEB)) {
			folderToLook = J2EEConstants.WEB_INF;
			fileToLook = J2EEConstants.WEBAPP_DD_URI;
		}
		else if (projectType.equals(IJ2EEFacetConstants.ENTERPRISE_APPLICATION)) {
			folderToLook = J2EEConstants.META_INF;
			fileToLook = J2EEConstants.APPLICATION_DD_URI;
		}	
		else if (projectType.equals(IJ2EEFacetConstants.WEBFRAGMENT)) {
			folderToLook = J2EEConstants.META_INF;
			fileToLook = J2EEConstants.WEBFRAGMENT_DD_URI;
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ui.IJavaEEDeploymentAssemblySectionBuilder#buildSection(org.eclipse.swt.widgets.Composite)
	 */
	public void buildSection(Composite parent){
		if (shouldDisplaySection()) { 
			Composite advancedSectionComposite = createAdvancedSection(parent);		
			addDefaultDeploymentDescriptorFolderFields(advancedSectionComposite);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ui.IJavaEEDeploymentAssemblySectionBuilder#loadContents()
	 */
	public void loadContents(){
		if (shouldDisplaySection()){
			loadDefaultDeploymentDescriptorFolderContents();
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ui.IJavaEEDeploymentAssemblySectionBuilder#saveContents()
	 */
	public boolean saveContents(){		
		boolean success = true;
		if (shouldDisplaySection()){
			success = saveDefaultDeploymentDescriptorFolderContents();
			loadContents();
		}
		return success;
	}
	
	
	private void loadDefaultDeploymentDescriptorFolderContents(){
		resourceMappingsList.clear();
		// First, retrieve all the mappings to root. Assume there are no duplicated mappings.
		IPath[] allRootMappings = findAllRootMappings();		
		// Now, see if any of that is tagged as default root mapping
		IVirtualFolder rootFolder = rootComponent.getRootFolder();
		IPath defaultDDFolder = J2EEModuleVirtualComponent.getDefaultDeploymentDescriptorFolder(rootFolder);
		
		currentSelectedDDFolder = defaultDDFolder == null?null:defaultDDFolder.toString();
				
		for (IPath mapping:allRootMappings){
			resourceMappingsList.add(mapping.toString());
		}
		
		updateSourceMappingsCombo(currentSelectedDDFolder, resourceMappingsList);
	}
	
	
	private List<String> filterMappings(List <String> mappings){
		Set<String> mappingWithDD = new HashSet<String>();
		Set<String> mappingWithFolder = new HashSet<String>();
		
		IProject project = this.rootComponent.getProject();
		for (String mapping :mappings){
			if (fileToLook != null && !fileToLook.equals("")){ //$NON-NLS-1$
				IFile ddFile = project.getFile(new Path(mapping).addTrailingSeparator() + fileToLook);
				if (ddFile != null && ddFile.exists()){
					mappingWithDD.add(mapping);				
				}
			}
			if (folderToLook != null && !folderToLook.equals("")){ //$NON-NLS-1$
				IFolder ddFolder = project.getFolder(new Path(mapping).addTrailingSeparator() + folderToLook);
				if (ddFolder != null && ddFolder.exists()){
					mappingWithFolder.add(mapping);
				}
			}
		}
		if (!mappingWithDD.isEmpty()){
			// return only the mappings that contain a DD file.
			return new ArrayList<String>(mappingWithDD);
		}
		return  new ArrayList<String>(mappingWithFolder);
	}
	
	private void updateSourceMappingsCombo(String selectedDDFolder, List<String> resourceMappings) {
		
		List<String> filteredMappings = filterMappings(resourceMappings);
		
		ArrayList<String> tmpList = new ArrayList<String>(filteredMappings);
		if (selectedDDFolder == null){
			tmpList.add(0, Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDER);
		}
		rootSourceMappings.setItems(tmpList.toArray(new String[]{}));
		if (selectedDDFolder == null){
			//No tagged source folder, so select "None"
			rootSourceMappings.select(tmpList.indexOf(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDER));
		}
		else {
			rootSourceMappings.select(tmpList.indexOf(selectedDDFolder.toString()));
		}					
	}

	private boolean saveDefaultDeploymentDescriptorFolderContents(){		
		if (currentSelectedDDFolder != null){
			IVirtualFolder rootFolder = rootComponent.getRootFolder();
			J2EEModuleVirtualComponent.setDefaultDeploymentDescriptorFolder(rootFolder, new Path(currentSelectedDDFolder), null);
		}
		return true;
	}
	
	private IPath[] findAllRootMappings(){
		
		StructureEdit structureEdit = null;
		try {
			structureEdit = StructureEdit.getStructureEditForRead(rootComponent.getProject());
			WorkbenchComponent component = structureEdit.getComponent();
			Object[] arr = component.getResources().toArray();
			Set <IPath> result = new LinkedHashSet<IPath>();
			for( int i = 0; i < arr.length; i++ ){
				ComponentResource resource = (ComponentResource)arr[i];
				if (resource.getRuntimePath().equals(IVirtualComponent.ROOT) && !ResourceMappingFilterExtensionRegistry.shouldFilter(resource.getSourcePath())){
					result.add(((ComponentResource)arr[i]).getSourcePath());
				}
			}
			return result.toArray(new IPath[]{});
		} catch (NullPointerException e) {
			J2EEUIPlugin.logError(e);
		} finally {
			if(structureEdit != null)
				structureEdit.dispose();
		}
		return new IPath[]{};
	}
	
	protected boolean shouldDisplaySection(){
		return shouldDisplaySection;
	}
		
	/*
	 * Creates the Advanced section. Returns the composite to which all the other 
	 * widgets should be added.
	 */
	private Composite createAdvancedSection(Composite parent){		
		
		// Build the expandable composite
		ExpandableComposite excomposite = new ExpandableComposite(parent, SWT.NONE, ExpandableComposite.TWISTIE
		| ExpandableComposite.CLIENT_INDENT | ExpandableComposite.COMPACT);
		excomposite.setText(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED);
		excomposite.setExpanded(false);
		excomposite.setFont(JFaceResources.getFontRegistry().getBold(JFaceResources.DIALOG_FONT));
		excomposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false, 1, 1));
		excomposite.addExpansionListener(new ExpansionAdapter() {
			@Override
			public void expansionStateChanged(ExpansionEvent e) {
				expandedStateChanged((ExpandableComposite) e.getSource());
			}
		});

		// Build the composite has the contents of the expandable widget
		Composite innerComposite = new Composite(excomposite, SWT.NONE);
		excomposite.setClient(innerComposite);
		GridLayout gl = new GridLayout(2, false);
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		innerComposite.setLayout(gl); 
		return innerComposite;		
	}
	
	private void addDefaultDeploymentDescriptorFolderFields(Composite parent) {
		defaulDDFolderLabel = new Label(parent, SWT.NONE);
		defaulDDFolderLabel.setText(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_DDFOLDER);
		rootSourceMappings = new Combo(parent, SWT.READ_ONLY);
		rootSourceMappings.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rootSourceMappings.addSelectionListener(this);
	}
	
	
	private final void expandedStateChanged(ExpandableComposite expandable) {
		// Get the scrolled composite of the deployment assembly page, and the child
		// composite of this scrolled composite that contains the expandable composite 
		Composite[] composites = getCompositesToResize(expandable);
		ScrolledComposite parentScrolledComposite = (ScrolledComposite)composites[0];
		Composite childComposite = composites[1];
		if (parentScrolledComposite != null && childComposite != null) {
			parentScrolledComposite.layout(true, true);
			// Resize the scrolled composite so the scroll bars are shown if necessary
			parentScrolledComposite.setMinSize(childComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true));
			
		}
	}
	
	/*
	 * Returns an array of composites used to resize the deployment assembly page
	 * when the advanced section is expanded.
	 * The first element is the scrolled composite (instance of ScrolledComposite) of the 
	 * deployment assembly page
	 * The second element is the composite with the contents of the deployment assembly page
	 */
	private Composite[] getCompositesToResize(Control control) {
		Control parent = control.getParent();
		Control previousParent = null;
		Composite[] result = new Composite[2];
		while (!(parent instanceof ScrolledComposite) && parent != null) {
			previousParent = parent;
			parent = parent.getParent();
		}
		if (parent instanceof ScrolledComposite) {
			result[0] = (ScrolledComposite)parent;
		}
		if (previousParent instanceof Composite) {
			result[1] = (Composite)previousParent;
		}
		return result;
	}

	public void directiveAdded(Object element) {
		if (shouldDisplaySection()){
			if (!(element instanceof TaskModel))
				return;
			TaskModel model = (TaskModel)element;
			final Object folderMapping = model.getObject(IReferenceWizardConstants.FOLDER_MAPPING);		
			if( folderMapping != null && folderMapping instanceof ComponentResourceProxy){
				ComponentResourceProxy proxy = (ComponentResourceProxy)folderMapping;
				//if ((proxy.runtimePath.equals(IVirtualComponent.ROOT) && !resourceMappingsList.contains(proxy.source.toString()))){
				if ((proxy.runtimePath.equals(IVirtualComponent.ROOT))){
					resourceMappingsList.add(proxy.source.toString());
					updateSourceMappingsCombo(currentSelectedDDFolder, resourceMappingsList);
				}
			}
		}		
	}

	public void directiveRemoved(Object element) {
		if (shouldDisplaySection()){
			if( element instanceof ComponentResourceProxy){
				ComponentResourceProxy proxy = (ComponentResourceProxy)element;
				if (proxy.runtimePath.equals(IVirtualComponent.ROOT)){
					String proxySource = proxy.source.toString();
					if (resourceMappingsList.contains(proxySource)){
						resourceMappingsList.remove(proxySource);
						if (proxySource.equals(currentSelectedDDFolder)){
							currentSelectedDDFolder = null;
						}
						updateSourceMappingsCombo(currentSelectedDDFolder, resourceMappingsList);
					}					
				}			
			}
		}
	}

	public void widgetDefaultSelected(SelectionEvent event) {
		// Intentionally left blank
		
	}

	public void widgetSelected(SelectionEvent event) {
		if (event.getSource() == rootSourceMappings){
			String tmp = rootSourceMappings.getText();
			if (tmp != null){
				if (tmp.equals(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDER) && currentSelectedDDFolder == null){
					// Do nothing, because the value did not change.
					return;
				}
				if (tmp.equals(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDER)){
					// This means the user selected None, but there was already a value selected (this should not happen)
					J2EEUIPlugin.logWarning("Unexpected condition when validating deployment descriptor folder"); //$NON-NLS-1$
					return;
				}
				// We now the user selected something different from None, so remove this item so it cannot be selected again
				if (rootSourceMappings.indexOf(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDER) != -1)
					rootSourceMappings.remove(Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDER);
				// Only refresh if changing from None to a folder
				boolean shouldRefresh = (currentSelectedDDFolder == null);
				if (!tmp.equals(currentSelectedDDFolder)){  
					currentSelectedDDFolder = tmp;
					if (shouldRefresh)
						page.refresh();
				}
			}
		}
				
	}

	public IStatus validate(IStatus currentStatus) {	
		IStatus status = currentStatus!=null?currentStatus:Status.OK_STATUS;
		if (shouldDisplaySection()){
			if (currentSelectedDDFolder == null && resourceMappingsList.size()>1){
				// Only show the warning if none of the root mappings is selected and there are more than 1 root mapping				
				int severity = Status.WARNING;
				status = appendStatusMessage(status, Messages.J2EEModuleDependenciesPropertyPage_ADVANCED_NODEFAULTDDFOLDERWARNING, severity);
			}
		}
		return status;
	}
	private IStatus appendStatusMessage(IStatus existingStatus, String message, int severity) {
        MultiStatus multiStatus;
        IStatus newStatus = new Status(severity, J2EEUIPlugin.PLUGIN_ID, message);
		int newSeverity = severity;
		if(existingStatus.getSeverity() > severity)
			newSeverity = existingStatus.getSeverity();
        if(existingStatus instanceof MultiStatus){
            multiStatus = (MultiStatus)existingStatus;
            multiStatus.merge(newStatus);
        } else {
        	if(!existingStatus.isMultiStatus() && existingStatus.isOK()) {
        		return newStatus;
        	}
            IStatus [] children = new IStatus [] {existingStatus, newStatus};
            multiStatus = new MultiStatus(J2EEUIPlugin.PLUGIN_ID, newSeverity, children, null, null);
        }
        return multiStatus;
    }

	public void componentResourceModified(ComponentResourceProxy originalResource, ComponentResourceProxy modifiedResource) {
		if (shouldDisplaySection()){
			// We are interested only in two cases:
			// 1. When the deploy path changes from / to any other thing...
			if (originalResource.runtimePath.isRoot() && !modifiedResource.runtimePath.isRoot()){
				resourceMappingsList.remove(originalResource.source.toString());
				if (originalResource.source.toString().equals(currentSelectedDDFolder)){
					currentSelectedDDFolder = null; 
				}
				updateSourceMappingsCombo(currentSelectedDDFolder, resourceMappingsList);
				page.refresh();
			}
			// 2. When the deploy path changes from any thing to /
			else if (!originalResource.runtimePath.isRoot() && modifiedResource.runtimePath.isRoot()){
				resourceMappingsList.add(originalResource.source.toString());
				updateSourceMappingsCombo(currentSelectedDDFolder, resourceMappingsList);
				page.refresh();		
			}
		}	
	}
}
