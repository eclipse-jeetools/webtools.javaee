/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 * David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
/*
 * Created on Dec 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties.IArchiveExportParticipantData;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.ui.archive.IArchiveExportParticipantPanelFactory;
import org.eclipse.jst.j2ee.ui.archive.internal.ArchiveExportParticipantPanelsExtensionPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizardPage;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public abstract class J2EEExportPage extends DataModelWizardPage {

	public static boolean isWindows = SWT.getPlatform().toLowerCase().startsWith("win"); //$NON-NLS-1$

	protected IStructuredSelection currentResourceSelection;
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	protected static final String STORE_LABEL = "J2EE_EXPORT_"; //$NON-NLS-1$
	protected static final String OVERWRITE_LABEL = "OVERWRITE"; //$NON-NLS-1$
	protected static final String SOURCE_LABEL = "SOURCE"; //$NON-NLS-1$
	protected static final String defBrowseButtonLabel = J2EEUIMessages.getResourceString(J2EEUIMessages.BROWSE_LABEL);
	protected String LABEL_DESTINATION = J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_DESTINATION);
	protected String LABEL_RUNTIME = J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_RUNTIME);
	private Combo resourceNameCombo;
	private Combo destinationNameCombo;
	private Button optimizeForRuntimeCheckbox;
	private Combo runtimeNameCombo;
	private Button destinationBrowseButton;
	protected Button overwriteExistingFilesCheckbox;
	private Button sourceFilesCheckbox;

	/**
	 * @param model
	 * @param pageName
	 */
	public J2EEExportPage(IDataModel model, String pageName, IStructuredSelection selection) {
		super(model, pageName);
		currentResourceSelection = selection;
		Object element = currentResourceSelection.getFirstElement();
		IProject project = ProjectUtilities.getProject(element);
		if (project != null) {
			String projectName = project.getName();
			DataModelPropertyDescriptor [] validProjectNames = model.getValidPropertyDescriptors(IJ2EEComponentExportDataModelProperties.PROJECT_NAME);
			boolean projectNameSet = false;
			for(int i=0;i<validProjectNames.length && !projectNameSet; i++){
				if(projectName.equals(validProjectNames[i].getPropertyDescription())){
					projectNameSet = true;
					model.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, projectName);
				}
			}
			if(!projectNameSet && validProjectNames.length > 0){
				//if export dialog is invoked by selecting a non EAR project, get the corresponding EAR
				IProject[] earProjects = EarUtilities.getReferencingEARProjects(project);
				if( earProjects.length > 0 ){
					model.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, earProjects[0].getName());						
				}else
					model.setProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME, validProjectNames[0].getPropertyDescription());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#getValidationPropertyNames()
	 */
	@Override
	protected String[] getValidationPropertyNames() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#createTopLevelComposite(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Composite createTopLevelComposite(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		//WorkbenchHelp.setHelp(composite, getInfopopID());
		GridLayout layout = new GridLayout(1, false);
		composite.setLayout(layout);

		createSourceAndDestinationGroup(composite);
        createRuntimeGroup(composite);
		createOptionsGroup(composite);

		//setupBasedOnInitialSelections();
		setupInfopop(composite);
		restoreWidgetValues();
	    Dialog.applyDialogFont(parent);
		return composite;
	}

	/**
	 * @param composite
	 */
	private void createSourceAndDestinationGroup(Composite parent) {

		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(3, false);
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
        composite.setLayoutData(data);
		createExportComponentGroup(composite);
		createDestinationGroup(composite);

	}
    /**
     * Creates the export source resource specification widgets.
     * 
     * @param parent
     *            a <code>Composite</code> that is to be used as the parent of this group's
     *            collection of visual components
     * @see org.eclipse.swt.widgets.Composite
     */
    protected void createExportComponentGroup(Composite parent) {
    	
        //Project label
        Label projectLabel = new Label(parent, SWT.NONE);
        projectLabel.setText(getComponentLabel());
        //Project combo
        resourceNameCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        resourceNameCombo.setLayoutData(data);
        synchHelper.synchCombo(resourceNameCombo, IJ2EEComponentExportDataModelProperties.PROJECT_NAME, null);

        	
        new Label(parent, SWT.NONE);//Pad label
    }

    /**
     * @return
     */
    protected abstract String getComponentLabel();

    protected void createDestinationGroup(org.eclipse.swt.widgets.Composite parent) {

        //Destination label
        Label destinationLabel = new Label(parent, SWT.NONE);
        destinationLabel.setText(LABEL_DESTINATION);
        // destination name combo field
        destinationNameCombo = new Combo(parent, SWT.SINGLE | SWT.BORDER);
        destinationNameCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        synchHelper.synchCombo(destinationNameCombo, IJ2EEComponentExportDataModelProperties.ARCHIVE_DESTINATION, null);

        // destination browse button
        destinationBrowseButton = new Button(parent, SWT.PUSH);
        destinationBrowseButton.setText(defBrowseButtonLabel);
        // bug 290709
//        destinationBrowseButton.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
        destinationBrowseButton.addSelectionListener(new SelectionAdapter() {
            @Override
			public void widgetSelected(SelectionEvent e) {
                handleDestinationBrowseButtonPressed();
            }
        });
        destinationBrowseButton.setEnabled(true);

    }
    
    protected void createRuntimeGroup( final Composite parent ) 
    {
        final Group group = new Group( parent, SWT.NONE );
        group.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        group.setLayout( new GridLayout( 1, false ) );
    	group.setText( LABEL_RUNTIME );

    	this.optimizeForRuntimeCheckbox = new Button( group, SWT.CHECK );
    	this.optimizeForRuntimeCheckbox.setText( J2EEWizardNLS.J2EEExportPage_Optimize_for_a_specific_server_runt_ );
    	this.synchHelper.synchCheckbox( this.optimizeForRuntimeCheckbox, IJ2EEComponentExportDataModelProperties.OPTIMIZE_FOR_SPECIFIC_RUNTIME, null );
    	
    	final GridData gd = new GridData();
    	gd.verticalIndent = 2;
    	
    	this.optimizeForRuntimeCheckbox.setLayoutData( gd );
    	
    	this.runtimeNameCombo = new Combo( group, SWT.READ_ONLY | SWT.SINGLE | SWT.BORDER );
        this.runtimeNameCombo.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        this.synchHelper.synchCombo( this.runtimeNameCombo, IJ2EEComponentExportDataModelProperties.RUNTIME, null );
        
        final Composite extComposite = new EnhancedComposite( group, SWT.NONE );
        extComposite.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );
        
        final GridLayout layout = new GridLayout( 1, false );
        layout.marginWidth = 10;
        layout.marginHeight = 1;
        
        extComposite.setLayout( layout );
        
        getDataModel().addListener
        (
            new IDataModelListener()
            {
                @Override
				public void propertyChanged( final DataModelEvent event )
                {
                    if( event.getPropertyName().equals( IJ2EEComponentExportDataModelProperties.RUNTIME ) && 
                        event.getFlag() == IDataModel.VALUE_CHG )
                    {
                        refreshExtensionsComposite( extComposite, true );
                    }
                    else if( event.getPropertyName().equals( IJ2EEComponentExportDataModelProperties.OPTIMIZE_FOR_SPECIFIC_RUNTIME  ) &&
                             event.getFlag() == IDataModel.VALUE_CHG )
                    {
                        final boolean optimize = ( (Boolean) event.getProperty() ).booleanValue();
                        extComposite.setEnabled( optimize );
                    }
                }
            }
        );
        
        refreshExtensionsComposite( extComposite, false );
	}
    
    private void refreshExtensionsComposite( final Composite extComposite, boolean forceRefresh )
    {
        for( Control child : extComposite.getChildren() )
        {
            child.dispose();
        }
        
        final List<IArchiveExportParticipantData> extensions 
            = (List<IArchiveExportParticipantData>) getDataModel().getProperty( IJ2EEComponentExportDataModelProperties.RUNTIME_SPECIFIC_PARTICIPANTS );
        
        if( extensions != null )
        {
            Composite innerComposite = null;
            
            for( IArchiveExportParticipantData extension : extensions )
            {
                final String id = extension.getId();
                
                final ArchiveExportParticipantPanelsExtensionPoint.PanelFactoryInfo panelExtInfo
                    = ArchiveExportParticipantPanelsExtensionPoint.getExtension( id );
                
                if( panelExtInfo != null )
                {
                    final IArchiveExportParticipantPanelFactory panelFactory = panelExtInfo.loadPanelFactory();
                    
                    if( panelFactory != null )
                    {
                        if( innerComposite == null )
                        {
                            innerComposite = new EnhancedComposite( extComposite, SWT.NONE );
                            innerComposite.setLayoutData( new GridData( GridData.FILL_BOTH ) );
                            
                            final GridLayout innerCompositeLayout = new GridLayout( 1, false );
                            innerCompositeLayout.marginWidth = 0;
                            innerCompositeLayout.marginHeight = 0;
                            
                            innerComposite.setLayout( innerCompositeLayout );
                        }
                        
                        try
                        {
                            panelFactory.createPanel( innerComposite, extension.getDataModel() );
                        }
                        catch( Exception e )
                        {
                            J2EEUIPlugin.logError( -1, e.getMessage(), e );
                        }
                    }
                }
            }
        }
        
        if (forceRefresh) {
        	extComposite.getShell().layout( true, true );
        }
    }
    
    /**
     * Create the export options specification widgets.
     * 
     * @param parent
     *            org.eclipse.swt.widgets.Composite
     */
    protected void createOptionsGroup(Composite parent) {

        // options group
        Composite optionsGroup = new Composite(parent, SWT.NULL);
        GridLayout layout = new GridLayout(1, false);
        optionsGroup.setLayout(layout);


        // source files... checkbox
        createSourceFilesCheckbox(optionsGroup);

        // overwrite... checkbox
        createOverwriteExistingFilesCheckbox(optionsGroup);

        // advanced button
        if (shouldShowProjectFilesCheckbox()) {
            createProjectFilesCheckbox(optionsGroup);
        }
    }
    
	protected void createOverwriteExistingFilesCheckbox(Composite optionsGroup) {
		//Overwrite checkbox
		overwriteExistingFilesCheckbox = new Button(optionsGroup, SWT.CHECK | SWT.LEFT);
		overwriteExistingFilesCheckbox.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_OVERWRITE_CHECKBOX)); 
		overwriteExistingFilesCheckbox.setEnabled(true);
		synchHelper.synchCheckbox(overwriteExistingFilesCheckbox, IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, null);
	}

	protected void createSourceFilesCheckbox(Composite optionsGroup) {
		sourceFilesCheckbox = new Button(optionsGroup, SWT.CHECK | SWT.LEFT);
		sourceFilesCheckbox.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_EXPORT_SOURCE_CHECKBOX));
		synchHelper.synchCheckbox(sourceFilesCheckbox, IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, null);
	}

	/**
	 * @return
	 */
	protected boolean shouldShowProjectFilesCheckbox() {
		return false;
	}

	protected void createProjectFilesCheckbox(Composite composite) {
		//do nothing
	}

	/**
	 * Populates the resource name field based upon the currently-selected resources.
	 */
	protected void setupBasedOnInitialSelections() {

		if (currentResourceSelection.isEmpty() || setupBasedOnRefObjectSelection())
			return; // no setup needed

		java.util.List selections = new ArrayList();
		Iterator aenum = currentResourceSelection.iterator();
		while (aenum.hasNext()) {
			IResource currentResource = (IResource) aenum.next();
			// do not add inaccessible elements
			if (currentResource.isAccessible())
				selections.add(currentResource);
		}
		if (selections.isEmpty())
			return; // setup not needed anymore

//		int selectedResourceCount = selections.size();
//TODO: find a way to select an existing component
//		if (selectedResourceCount == 1) {
//			IResource resource = (IResource) selections.get(0);
//			if ((resource instanceof IProject) && checkForNature((IProject) resource)) {
//				resourceNameCombo.setText(resource.getName().toString());
//			}
//		}
	}

	/**
	 * @return
	 */
	protected String getProjectImportLabel() {
		return null;
	}

	/**
	 *  
	 */
	protected void handleDestinationBrowseButtonPressed() {

		FileDialog dialog = new FileDialog(destinationNameCombo.getShell(), SWT.SAVE);
		String fileName = getDataModel().getStringProperty(IJ2EEComponentExportDataModelProperties.PROJECT_NAME);
		String[] filters = getFilterExpression();
		if (!isWindows) {
			if (filters.length != 0 && filters[0] != null && filters[0].indexOf('.') != -1) {
				fileName += filters[0].substring(filters[0].indexOf('.'));
			}
		}
		dialog.setFileName(fileName);
		if (isWindows) {
			dialog.setFilterExtensions(filters);
		}
		String filename = dialog.open();
		if (filename != null)
			destinationNameCombo.setText(filename);
	}

	protected void restoreWidgetValues() {

		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			String[] sourceNames = settings.getArray(STORE_LABEL + getFileNamesStoreID());
			if (sourceNames == null)
				return; // ie.- no settings stored
			for (int i = 0; i < sourceNames.length; i++) {
				if (sourceNames[i] == null)
					sourceNames[i] = ""; //$NON-NLS-1$
			}
			destinationNameCombo.setItems(sourceNames);
			boolean overwrite = settings.getBoolean(STORE_LABEL + OVERWRITE_LABEL);
			model.setBooleanProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING, overwrite);
			boolean includeSource = settings.getBoolean(STORE_LABEL + SOURCE_LABEL);
			model.setBooleanProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES, includeSource);
		}
	}

	@Override
	public void storeDefaultSettings() {
		IDialogSettings settings = getDialogSettings();
		if (settings != null) {
			// update source names history
			String[] sourceNames = settings.getArray(STORE_LABEL + getFileNamesStoreID());
			if (sourceNames == null) {
				sourceNames = new String[0];
			}

			String newName = destinationNameCombo.getText();

			//rip out any empty filenames and trim length to 5
			ArrayList newNames = new ArrayList();
			for (int i = 0; i < sourceNames.length && i < 5; i++) {
				if (sourceNames[i].trim().length() > 0 && !newName.equals(sourceNames[i])) {
					newNames.add(sourceNames[i]);
				}
			}
			newNames.add(0, destinationNameCombo.getText());
			sourceNames = new String[newNames.size()];
			newNames.toArray(sourceNames);

			settings.put(STORE_LABEL + getFileNamesStoreID(), sourceNames);
			settings.put(STORE_LABEL + OVERWRITE_LABEL, model.getBooleanProperty(IJ2EEComponentExportDataModelProperties.OVERWRITE_EXISTING));
			settings.put(STORE_LABEL + SOURCE_LABEL, model.getBooleanProperty(IJ2EEComponentExportDataModelProperties.EXPORT_SOURCE_FILES));
		}
	}

	/**
	 * @return
	 */
	protected String getFileNamesStoreID() {
		return getComponentID();
	}
	
	@Deprecated
	protected String getCompnentID() {
		return null;
	}

	protected String getComponentID() {
		return getCompnentID();
	}

    /**
	 * @return
	 */
	protected String[] getFilterExpression() {
		return new String[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.WTPWizardPage#enter()
	 */
	@Override
	protected void enter() {
		super.enter();
		restoreWidgetValues();
	}

	/**
	 * @return
	 */
	//protected abstract String getNatureID();

	protected abstract boolean isMetaTypeSupported(Object o);

	/**
	 * Populates the resource name field based upon the currently-selected resources.
	 */
	protected boolean setupBasedOnRefObjectSelection() {

		if (currentResourceSelection.size() != 1)
			return false;

		Object o = currentResourceSelection.getFirstElement();
		if (!isMetaTypeSupported(o))
			return false;

		EObject ref = (EObject) o;
		IResource resource = ProjectUtilities.getProject(ref);
		if (resource != null) {
			resourceNameCombo.setText(resource.getName().toString());
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.WizardPage#getDialogSettings()
	 */
	@Override
	protected IDialogSettings getDialogSettings() {
		return J2EEUIPlugin.getDefault().getDialogSettings();
	}
	
	private static class EnhancedComposite

	    extends Composite
	    
	{
	    public EnhancedComposite( final Composite parent,
	                              final int style )
	    {
	        super( parent, style );
	    }
	    
	    @Override
	    public void setEnabled( boolean enabled )
	    {
	        super.setEnabled( enabled );
	        
            for( Control child : getChildren() )
            {
                child.setEnabled( enabled );
            }
	    }
	}

}
