/******************************************************************************
 * Copyright (c) 2005, 2019 BEA Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 *    David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *    Milen Manov, milen.manov@sap.com - bugs 248623
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.project.facet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.earcreation.IEarFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.AvailableJ2EEComponentsForEARContentProvider;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.actions.IJ2EEUIContextIds;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.DefaultJ2EEComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.IDefaultJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPluginIcons;
import org.eclipse.jst.j2ee.internal.wizard.DefaultJ2EEComponentCreationWizard;
import org.eclipse.jst.j2ee.internal.wizard.J2EEComponentLabelProvider;
import org.eclipse.jst.j2ee.internal.wizard.J2EEModuleFacetInstallPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.internal.FacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public class EarFacetInstallPage extends J2EEModuleFacetInstallPage implements IEarFacetInstallDataModelProperties {
	
	private Button selectAllButton;
	private Button deselectAllButton;
	private Button newModuleButton;
	
	private Label moduleProjectsLabel; 
	private CheckboxTableViewer moduleProjectsViewer;
	
	private boolean ignoreCheckedState = false;
	
	private Label contentDirLabel;
	private Text contentDir;

	public EarFacetInstallPage() {
		super("ear.facet.install.page"); //$NON-NLS-1$
		setTitle(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_SECOND_PG_TITLE));
		setDescription(J2EEUIMessages.getResourceString(J2EEUIMessages.EAR_COMPONENT_SECOND_PG_DESC));
		setImageDescriptor(J2EEUIPlugin.getDefault().getImageDescriptor(J2EEUIPluginIcons.EAR_WIZ_BANNER));
	}

	@Override
	protected String[] getValidationPropertyNames() {
		return new String[]{CONTENT_DIR, J2EE_PROJECTS_LIST};
	}

	@Override
	protected Composite createTopLevelComposite(Composite parent) {
		setInfopopID(IJ2EEUIContextIds.NEW_EAR_ADD_MODULES_PAGE);
		
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(gdhfill());
		
		createModuleProjectOptions(composite);
		createContentDirGroup(composite);
		
		createGenerateDescriptorControl(composite, J2EEConstants.APPLICATION_DD_SHORT_NAME);
		registerFacetVersionChangeListener();
		
	    Dialog.applyDialogFont(parent);
		return composite;
	}

	protected int getJ2EEVersion() {
		IProjectFacetVersion version = (IProjectFacetVersion)getDataModel().getProperty(FACET_VERSION);
		return J2EEVersionUtil.convertVersionStringToInt(version.getVersionString());
	}
	
	/**
	 * @param parent
	 */
	private void createModuleProjectOptions(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		moduleProjectsLabel = new Label(composite, SWT.NONE);
		moduleProjectsLabel.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_MODULE_DEPENDENCIES_LABEL));
		GridData gd = gdhfill();
		gd.horizontalSpan = 2;
		moduleProjectsLabel.setLayoutData(gd);
		
		moduleProjectsViewer = CheckboxTableViewer.newCheckList(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gData = new GridData(GridData.FILL_BOTH);
		gData.widthHint = 200;
		gData.heightHint = 80;
		moduleProjectsViewer.getControl().setLayoutData(gData);
		int j2eeVersion = getJ2EEVersion();
		ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
		AvailableJ2EEComponentsForEARContentProvider provider = new AvailableJ2EEComponentsForEARContentProvider(null, j2eeVersion, decorator);
		moduleProjectsViewer.setContentProvider(provider);
		final J2EEComponentLabelProvider labelProvider = new J2EEComponentLabelProvider(provider);
		decorator.addListener(new ILabelProviderListener(){
		
			@Override
			public void labelProviderChanged(LabelProviderChangedEvent event) {
				if(!moduleProjectsViewer.getTable().isDisposed()){
					moduleProjectsViewer.refresh(true);
				}
			}
		});
		moduleProjectsViewer.setLabelProvider(labelProvider);
		setCheckedItemsFromModel();
		
		moduleProjectsViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (!ignoreCheckedState) {
					getDataModel().setProperty(J2EE_PROJECTS_LIST, getCheckedJ2EEElementsAsList());
					getDataModel().setProperty(JAVA_PROJECT_LIST, getCheckedJavaProjectsAsList());
                }
			}
		});
		TableLayout tableLayout = new TableLayout();
		moduleProjectsViewer.getTable().setLayout(tableLayout);
		moduleProjectsViewer.getTable().setHeaderVisible(false);
		moduleProjectsViewer.getTable().setLinesVisible(false);
		moduleProjectsViewer.setSorter(null);
		this.moduleProjectsViewer.getTable().getAccessible().addAccessibleListener(
        		new AccessibleAdapter() {			
        			@Override
        			public void getName(AccessibleEvent e) {
        				e.result = J2EEUIMessages.getResourceString(J2EEUIMessages.J2EE_MODULE_DEPENDENCIES_LABEL_ACCESSIBILITY);
        		}});
		
		createButtonsGroup(composite);
	}

	/**
	 *  
	 */
	private void setCheckedItemsFromModel() {
		List components = (List) getDataModel().getProperty(J2EE_PROJECTS_LIST);
		
		TableItem [] items = moduleProjectsViewer.getTable().getItems();

		List list = new ArrayList();
		
		for( int i=0; i< items.length; i++ ){
			Object element = items[i].getData();
			if( element instanceof IVirtualComponent){
				IVirtualComponent comp = (IVirtualComponent)element;				
				Iterator it = components.iterator();
				while( it.hasNext() ){
					IProject project = (IProject)it.next();
					if( comp.getProject().getName().equals(project.getName()) ){
						list.add(comp);
					}					
				}
			}	
		}
		moduleProjectsViewer.setCheckedElements(list.toArray());		
	}

	private void refreshModules() {
		moduleProjectsViewer.refresh();
		setCheckedItemsFromModel();
	}

	protected List getCheckedJ2EEElementsAsList() {
		Object[] elements = moduleProjectsViewer.getCheckedElements();
		List list;
		if (elements == null || elements.length == 0)
			list = Collections.EMPTY_LIST;
		else{
			list = new ArrayList(); 
			for( int i=0; i< elements.length; i++){
				if( elements[i] instanceof IVirtualComponent ) {
					list.add(((IVirtualComponent)elements[i]).getProject());
				}
			}
		}	
		return list;
	}
	
	protected List getCheckedJavaProjectsAsList() {
		Object[] elements = moduleProjectsViewer.getCheckedElements();
		List list;
		if (elements == null || elements.length == 0)
			list = Collections.EMPTY_LIST;
		else{
			list = new ArrayList(); 
			for( int i=0; i< elements.length; i++){
				if( elements[i] instanceof IProject ) {
					list.add(elements[i]);
				}
			}
		}	
		return list;
	}
	
	
	protected void createButtonsGroup(Composite parent) {
		Composite buttonGroup = new Composite(parent, SWT.NONE);
		buttonGroup.setLayout(new GridLayout());
		buttonGroup.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		
		selectAllButton = new Button(buttonGroup, SWT.PUSH);
		selectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_SELECT));
		selectAllButton.addListener(SWT.Selection, this);
		GridDataFactory.defaultsFor( selectAllButton ).applyTo( selectAllButton );
		
		deselectAllButton = new Button(buttonGroup, SWT.PUSH);
		deselectAllButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_DESELECT));
		deselectAllButton.addListener(SWT.Selection, this);
		GridDataFactory.defaultsFor( deselectAllButton ).applyTo( deselectAllButton );
		
		new Label(buttonGroup, SWT.NONE); // pad
		
		newModuleButton = new Button(buttonGroup, SWT.PUSH);
		newModuleButton.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.APP_PROJECT_MODULES_PG_NEW));
		newModuleButton.addListener(SWT.Selection, this);
		GridDataFactory.defaultsFor( newModuleButton ).applyTo( newModuleButton );
	}
	
	private void createContentDirGroup(Composite modulesGroup) {
		final Composite composite = new Composite(modulesGroup, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(gdhfill());

		this.contentDirLabel = new Label(composite, SWT.NONE);
		this.contentDirLabel.setText(Resources.contentDirLabel);
		this.contentDirLabel.setLayoutData(new GridData());

		this.contentDir = new Text(composite, SWT.BORDER);
		this.contentDir.setLayoutData(gdhfill());
		
		synchHelper.synchText(contentDir, CONTENT_DIR, null);
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(Event)
	 */
	@Override
	public void handleEvent(Event evt) {
		if (evt.widget == selectAllButton)
			handleSelectAllButtonPressed();
		else if (evt.widget == deselectAllButton)
			handleDeselectAllButtonPressed();
		else if (evt.widget == newModuleButton)
			handleNewModuleButtonPressed();
		else
			super.handleEvent(evt);
	}

	@Override
	protected void handleFacetVersionChangedEvent()
	{
	    String fv = model.getStringProperty(FACET_VERSION_STR);
	    boolean isEAR50OrGreater = J2EEVersionUtil.convertVersionStringToInt(fv) >= J2EEVersionConstants.VERSION_5_0;
	    this.addDD.setVisible(isEAR50OrGreater);
	}
	
	/**
	 *  
	 */
	private void handleNewModuleButtonPressed() {
		IDataModel aModel = createNewModuleModel();
		DefaultJ2EEComponentCreationWizard wizard = new DefaultJ2EEComponentCreationWizard(aModel);
		WizardDialog dialog = new WizardDialog(getShell(), wizard);
		dialog.create();
		if (dialog.open() != IDialogConstants.CANCEL_ID) {
			IWorkspaceRoot input = ResourcesPlugin.getWorkspace().getRoot();
			moduleProjectsViewer.setInput(input);
            setNewModules(aModel);
            refreshModules();
		}
	}
    /**
     * @param model
     */
    private void setNewModules(IDataModel defaultModel) {
        List newComponents = new ArrayList();
        collectNewComponents(defaultModel, newComponents);
        List oldComponents = (List) getDataModel().getProperty(J2EE_PROJECTS_LIST);
        newComponents.addAll(oldComponents);
        getDataModel().setProperty(J2EE_PROJECTS_LIST, newComponents);
    }
    
    private void collectNewComponents(IDataModel defaultModel, List newProjects) {
        collectComponents(defaultModel.getNestedModel(IDefaultJ2EEComponentCreationDataModelProperties.NESTED_MODEL_EJB), newProjects);
        collectComponents(defaultModel.getNestedModel(IDefaultJ2EEComponentCreationDataModelProperties.NESTED_MODEL_WEB), newProjects);
        collectComponents(defaultModel.getNestedModel(IDefaultJ2EEComponentCreationDataModelProperties.NESTED_MODEL_CLIENT), newProjects);
        collectComponents(defaultModel.getNestedModel(IDefaultJ2EEComponentCreationDataModelProperties.NESTED_MODEL_JCA), newProjects);
    }
    private void collectComponents(IDataModel compDM, List newProjects) {
        if (compDM != null) {
        	String projectName = compDM.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME);
            if(projectName == null) return;
            IProject project = ProjectUtilities.getProject(projectName);
            if (project != null && project.exists())
                newProjects.add(project);
        }
    }
    
	private IDataModel createNewModuleModel() {
		IDataModel defaultModel = DataModelFactory.createDataModel(new DefaultJ2EEComponentCreationDataModelProvider());
		// transfer properties, project name
		String projectName = model.getStringProperty(FACET_PROJECT_NAME);
		defaultModel.setProperty(IDefaultJ2EEComponentCreationDataModelProperties.PROJECT_NAME, projectName);
		// ear component name
		String earName = model.getStringProperty(FACET_PROJECT_NAME);
		defaultModel.setProperty(IDefaultJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME, earName);
		// ear j2ee version
		int j2eeVersion = getJ2EEVersion();
		defaultModel.setProperty(IDefaultJ2EEComponentCreationDataModelProperties.J2EE_VERSION, new Integer(j2eeVersion));
		
		FacetedProjectWorkingCopy fpwc = (FacetedProjectWorkingCopy)model.getProperty(FACETED_PROJECT_WORKING_COPY);
		IRuntime rt = fpwc.getPrimaryRuntime();
		defaultModel.setProperty(IDefaultJ2EEComponentCreationDataModelProperties.FACET_RUNTIME, rt);
		
		return defaultModel;
	}

	/**
	 *  
	 */
	private void handleDeselectAllButtonPressed() {
		ignoreCheckedState = true;
		try {
			moduleProjectsViewer.setAllChecked(false);
			//getDataModel().setProperty(J2EE_COMPONENT_LIST, null);
			//IDataModel nestedModel = (IDataModel)getDataModel().getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);	
			//(nestedModel).setProperty(AddComponentToEnterpriseApplicationDataModelProvider., getCheckedJ2EEElementsAsList());
			getDataModel().setProperty(J2EE_PROJECTS_LIST, null);
			getDataModel().setProperty(JAVA_PROJECT_LIST, null);			
		} finally {
			ignoreCheckedState = false;
		}
	}

	/**
	 *  
	 */
	private void handleSelectAllButtonPressed() {
		ignoreCheckedState = true;
		try {
			moduleProjectsViewer.setAllChecked(true);
			//getDataModel().setProperty(J2EE_COMPONENT_LIST, getCheckedElementsAsList());
			//IDataModel nestedModel = (IDataModel)getDataModel().getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);
			//(nestedModel).setProperty(AddComponentToEnterpriseApplicationDataModelProvider., getCheckedJ2EEElementsAsList());
			
			getDataModel().setProperty(J2EE_PROJECTS_LIST, getCheckedJ2EEElementsAsList());
			getDataModel().setProperty(JAVA_PROJECT_LIST, getCheckedJavaProjectsAsList());
			
		} finally {
			ignoreCheckedState = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.ui.wizard.J2EEWizardPage#enter()
	 */
	@Override
	protected void enter() {
		IWorkspaceRoot input = ResourcesPlugin.getWorkspace().getRoot();
		moduleProjectsViewer.setInput(input);
		super.enter();
	}
	
	
	private static final class Resources

	extends NLS
	{
		public static String contentDirLabel;

		static {
			initializeMessages(EarFacetInstallPage.class.getName(), Resources.class);
		}
	}

}
