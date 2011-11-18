/*******************************************************************************
 * Copyright (c) 2003, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 10, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.ui.project.facet.EarSelectionPanel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.web.ui.internal.wizards.DataModelFacetCreationWizardPage;

public abstract class J2EEComponentFacetCreationWizardPage extends DataModelFacetCreationWizardPage {

    private static final String STORE_LABEL = "LASTEARNAME_"; //$NON-NLS-1$
    
	protected EarSelectionPanel earPanel;
	
	private boolean shouldAddEARComposite = true;
  
	

	public J2EEComponentFacetCreationWizardPage(IDataModel dataModel, String pageName) {
		super(dataModel, pageName);
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
        final Composite top = super.createTopLevelComposite(parent);
		if(isShouldAddEARComposite()){
        createEarComposite(top);
        createWorkingSetGroupPanel(top, new String[] { RESOURCE_WORKING_SET, JAVA_WORKING_SET });
		}
        return top;
	}

	private void createEarComposite(Composite top) 
	{
	    final IFacetedProjectWorkingCopy fpjwc
	        = (IFacetedProjectWorkingCopy) this.model.getProperty( FACETED_PROJECT_WORKING_COPY );
	    
	    final String moduleFacetId = getModuleFacetID();
	    final IProjectFacet moduleFacet = ProjectFacetsManager.getProjectFacet( moduleFacetId );
	    final IFacetedProject.Action action = fpjwc.getProjectFacetAction( moduleFacet );
	    
		earPanel = new EarSelectionPanel( (IDataModel) action.getConfig(), top );
	}

	protected abstract String getModuleFacetID();

	@Override
	protected String getModuleTypeID() {
		return getModuleFacetID();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (earPanel != null)
			earPanel.dispose();
	}
	
    @Override
	public void storeDefaultSettings() {
    	super.storeDefaultSettings();
        IDialogSettings settings = getDialogSettings();
        if (settings != null) {
        	FacetDataModelMap map = (FacetDataModelMap)model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
	    	String facetID = getModuleFacetID();
	    	IDataModel j2eeModel = map.getFacetDataModel(facetID);
        	if(j2eeModel.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR)){
	        	String lastEARName = j2eeModel.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
	            settings.put(STORE_LABEL, lastEARName);
        	}
        }
    }
    
    @Override
	public void restoreDefaultSettings() {
    	//get the values from the selected EAR to restore.
    	IProject selectedEARProject = getSelectedEAR();
    	if (selectedEARProject != null){
    		IRuntime earRuntime = getTargetRuntime(selectedEARProject);
    		if (runtimeSupportFacets(earRuntime)) {
    			restoreEARName(selectedEARProject);
    			restoreEARRuntime(selectedEARProject);
    		}
    	} else{
    		restoreStoredLabelEARName();
    	}
    	super.restoreDefaultSettings();
	}
    
    /* Restore the EAR Name from the user selected EAR if there is any
	 * @param earName
	 */
    private void restoreEARName(IProject earProject){
    	String earName = earProject.getName();
    	if (earName != null){
    		setEarName(earName, false);
    	}
    }
    
    /* Restore the EAR Name from the last created EAR Project
	 */
    private void restoreStoredLabelEARName(){
    	IDialogSettings settings = getDialogSettings();
    	String earName = settings.get(STORE_LABEL);
        if (earName != null){
        	IProject earProject = ResourcesPlugin.getWorkspace().getRoot().getProject(earName);
        	if (earProject != null){
        			setEarName(earName, true); //last ear created, old behavior
        	}
        }
    }
    
    private boolean runtimeSupportFacets(IRuntime runtime){
    	 final IFacetedProjectWorkingCopy fpjwc
	        = (IFacetedProjectWorkingCopy) this.model.getProperty( FACETED_PROJECT_WORKING_COPY );
    	 
    	 for (IProjectFacet facet:fpjwc.getFixedProjectFacets()){
    		 try {
				IProjectFacetVersion facetVersion = facet.getLatestSupportedVersion(runtime);
				if (facetVersion == null){
					return false;
				}
			} catch (CoreException e) {
				 throw new RuntimeException( e );
			}
    	 }
    	return true;
    }

	/* Sets the EAR Name to the the J2ee model and model
	 * @param String EAR Project Name
	 * @param boolean specifying if only the LAST_EAR_NAME property should be updated.
	 */
	private void setEarName(String earName, boolean lastEAROnly) {
		if (earName != null){
			FacetDataModelMap map = (FacetDataModelMap)model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			String facetID = getModuleFacetID();
			IDataModel j2eeModel = map.getFacetDataModel(facetID);
		    j2eeModel.setProperty(IJ2EEModuleFacetInstallDataModelProperties.LAST_EAR_NAME, earName);
		    if (!lastEAROnly)
		    	j2eeModel.setProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME, earName);

		}
	}

	/* Sets the Runtime to the J2EE model and common model
	 * @param IProject
	 */
	public void restoreEARRuntime(IProject proj) {
			FacetDataModelMap map = (FacetDataModelMap)model.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			String facetID = getModuleFacetID();
			IDataModel j2eeModel = map.getFacetDataModel(facetID);
		    IRuntime currentRuntime = getTargetRuntime(proj);
		    if (currentRuntime != null){
		    	j2eeModel.setProperty(IJ2EEModuleFacetInstallDataModelProperties.RUNTIME_TARGET_ID, currentRuntime);
		    	model.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME, currentRuntime);
		    }
		    
	}
    
	/* Gets the Runtime from the user selected EAR if there is any
	 * @param IRuntime
	 */
	public static IRuntime getTargetRuntime(IProject project) {
		IFacetedProject fProject = null;
		try {
			fProject = ProjectFacetsManager.create(project);
		} catch (CoreException ex) {
			J2EEUIPlugin.logError(ex);
		}
		if(fProject != null)
			return fProject.getRuntime() ;
		return null;
	}
	
    /*
	 * Gets the EAR Name selected on the view (ActivePart).
	 * @return IProject or null if there is nothing selected.
	 */
    private IProject getSelectedEAR(){
    	IProject retVal = null;
    	ISelection selection = getSelectionFromWorkbenchWindow();
    	if (selection instanceof IStructuredSelection) {
    		IProject selProject = getEARProjectFromSelection((IStructuredSelection)selection);
    		if (selProject != null){
    			if (JavaEEProjectUtilities.isEARProject(selProject)) {
    				retVal = selProject;
    			}
    		}
    	}
		return retVal;
	}
    
    /*
	 * Gets the Selection from Workbench Window.
	 * @return ISelection
	 */
	private ISelection getSelectionFromWorkbenchWindow() {
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    	ISelection selection = window.getSelectionService().getSelection();
		return selection;
	}
    
    
	/*
	 * Extract the first element selected and checks for an EAR Project
	 * @param IStructuredSelection selection
	 * @return IProject EAR or null if there is Not selected EAR project.
	 */
	private IProject getEARProjectFromSelection(IStructuredSelection selection) {
		if (selection != null){
			if (!selection.isEmpty()){
				Object firstSelectedElement = selection.getFirstElement();
				if (firstSelectedElement instanceof IProject) {
					return (IProject)firstSelectedElement;
					
				}
			}
		}
		return null;
	}
    
	@Override
	protected IDialogSettings getDialogSettings() {
        return J2EEUIPlugin.getDefault().getDialogSettings();
    }
	
	@Override
	protected String[] getValidationPropertyNames() {
		String[] superProperties = super.getValidationPropertyNames();
		List list = Arrays.asList(superProperties);
		ArrayList arrayList = new ArrayList();
		arrayList.addAll( list );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR );
		arrayList.add( IJ2EEFacetProjectCreationDataModelProperties.FACET_RUNTIME);
		return (String[])arrayList.toArray( new String[0] );
	}
	
	protected boolean isShouldAddEARComposite() {
		return shouldAddEARComposite;
	}

	protected void setShouldAddEARComposite(boolean shouldAddEARComposite) {
		this.shouldAddEARComposite = shouldAddEARComposite;
	}
}
