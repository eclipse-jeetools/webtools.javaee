package org.eclipse.jst.j2ee.internal.wizard;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFlexibleProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelSynchHelper;
import org.eclipse.wst.common.frameworks.internal.datamodel.ui.DataModelWizard;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

public class NewModuleDataModelGroup implements IFlexibleJavaProjectCreationDataModelProperties{
	
	private IDataModel model;
	protected Combo projectNameCombo = null;
	protected Text moduleNameText = null;
	protected Button newButton = null;
	protected Text serverTargetText;
	private DataModelSynchHelper synchHelper;
	private Composite parentComposite;
	
	private static final int SIZING_TEXT_FIELD_WIDTH = 305;
	private static final String PROJECT_NAME = J2EEUIMessages.getResourceString(J2EEUIMessages.MODULES_DEPENDENCY_PAGE_TABLE_PROJECT)+ ":"; //$NON-NLS-1$
	private static final String NEW_LABEL = J2EEUIMessages.getResourceString(J2EEUIMessages.NEW_THREE_DOTS_E); //$NON-NLS-1$
	private static final String MODULE_NAME = J2EEUIMessages.getResourceString(J2EEUIMessages.MODULE_NAME); //$NON-NLS-1$
	
	/**
	 * @param parent
	 * @param style
	 */
	public NewModuleDataModelGroup(Composite parent, IDataModel model, DataModelSynchHelper helper) {
		this.model = model;
		this.parentComposite = parent;
		synchHelper = helper;
		buildComposites(parent);
	}

	/**
	 * Create the controls within this composite
	 */
	public void buildComposites(Composite parent) {
        createModuleGroup(parent);
        moduleNameText.setFocus();
		createProjectNameGroup(parent);
		initializeProjectList();
		createServerTargetComposite(parent);
		addSeperator(parent,3);
	}
	
	/**
	 * 
	 *
	 */
	public void initializeProjectList() {
		IProject[] workspaceProjects = ProjectUtilities.getAllProjects();
		List items = new ArrayList();
		for (int i=0; i<workspaceProjects.length; i++) {
			IProject project = workspaceProjects[i];
			try {
				if (project.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
					items.add(project.getName());
				}
			} catch (CoreException ce) {
				//Ignore
			}
		}
		String[] names = new String[items.size()];
		for (int i=0; i<items.size(); i++) {
			names[i]= (String) items.get(i);
		}
//		model.setIgnorePropertyChanges(true);
		projectNameCombo.setItems(names);
//		model.setIgnorePropertyChanges(false);
		
		if (!model.isPropertySet(IComponentCreationDataModelProperties.PROJECT_NAME) 
				|| model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME).length()==0) {
			IProject selectedProject = getSelectedProject();
			if (selectedProject!=null) {
				projectNameCombo.setText(selectedProject.getName());
				model.setProperty(IComponentCreationDataModelProperties.PROJECT_NAME,
						selectedProject.getName());
			}
			else if (names.length>0) {
				projectNameCombo.setText(names[0]);
				model.setProperty(IComponentCreationDataModelProperties.PROJECT_NAME, names[0]);
			}
		} else {
			projectNameCombo.add(model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
			projectNameCombo.setText(model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
		}
	}

	/**
	 *  
	 */
	private void createProjectNameGroup(Composite parent) {
		// set up project name label
		Label projectNameLabel = new Label(parent, SWT.NONE);
		projectNameLabel.setText(PROJECT_NAME);
		GridData data = new GridData();
		projectNameLabel.setLayoutData(data);
		// set up project name entry field
		projectNameCombo = new Combo(parent, SWT.BORDER | SWT.READ_ONLY);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		projectNameCombo.setLayoutData(data);
		projectNameCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				IProject project = ProjectUtilities.getProject(projectNameCombo.getText());
				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (runtime != null){
					serverTargetText.setText(runtime.getName());
					synchHelper.getDataModel().setProperty(IJavaComponentCreationDataModelProperties.RUNTIME_TARGET_ID, runtime.getName());
				}	
			}
		});
		newButton = new Button(parent, SWT.NONE);
		newButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		newButton.setText(NEW_LABEL);
		newButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				handleNewProjectSelected();
			}
		});
		synchHelper.synchCombo(projectNameCombo, 
				IComponentCreationDataModelProperties.PROJECT_NAME, 
				new Control[]{projectNameLabel});
	}
	
	/**
	 * @return
	 */
	private IProject getSelectedProject() {
		IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
		if (window == null)
			return null;
		ISelection selection = window.getSelectionService().getSelection();
		if (selection == null || !(selection instanceof StructuredSelection))
			return null;
		StructuredSelection stucturedSelection = (StructuredSelection) selection;
		Object obj = stucturedSelection.getFirstElement();
		if (obj instanceof IProject)
			return (IProject) obj;
		return null;
	}
	
	private void handleNewProjectSelected() {   
        IDataModel javaProjModel = DataModelFactory.createDataModel(new FlexibleJavaProjectCreationDataModelProvider());
        DataModelWizard newProjectWizard = new FlexibleProjectCreationWizard(javaProjModel);
        WizardDialog dialog = new WizardDialog(parentComposite.getShell(), newProjectWizard);
		if (Window.OK == dialog.open()) {
			String newProjectName = javaProjModel.getStringProperty(IFlexibleProjectCreationDataModelProperties.PROJECT_NAME);
			projectNameCombo.add(newProjectName);
			projectNameCombo.setText(newProjectName);
			IProject project = ProjectUtilities.getProject(projectNameCombo.getText());
			IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
			if (runtime != null){
				serverTargetText.setText(runtime.getName());
				synchHelper.getDataModel().setProperty(IJavaComponentCreationDataModelProperties.RUNTIME_TARGET_ID, runtime.getName());
			}
		}
	}
	
	/**
	 * 
	 * @param parent
	 */
	private void createModuleGroup(Composite parent) {
		GridData data = new GridData();
		// Add the module name label
		Label moduleNameLabel = new Label(parent, SWT.NONE);
		moduleNameLabel.setText(MODULE_NAME);
		// Add the module name entry field
		moduleNameText = new Text(parent, SWT.BORDER);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.widthHint = SIZING_TEXT_FIELD_WIDTH;
		moduleNameText.setLayoutData(data);
		synchHelper.synchText(moduleNameText, 
				IComponentCreationDataModelProperties.COMPONENT_NAME,
				new Control[] {});
		new Label(parent,SWT.NONE);
	}

	/**
	 * 
	 *
	 */
	public void dispose() {
		if (synchHelper != null) {
			if (model != null)
				model.removeListener(synchHelper);
			synchHelper.dispose();
		}
		model = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getProjectName() {
		return projectNameCombo.getText();
	}
	
	/**
	 * 
	 * @param parent
	 * @param hSpan
	 */
	public void addSeperator(Composite parent, int hSpan) {
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = hSpan;
		separator.setLayoutData(gd);
	}
	
	/**
	 * 
	 * @param parent
	 */
	protected void createServerTargetComposite(Composite parent) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(J2EEUIMessages.getResourceString(J2EEUIMessages.TARGET_RUNTIME_LBL));
		serverTargetText = new Text(parent, SWT.BORDER | SWT.READ_ONLY);
//		serverTargetText..addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				IProject project = ProjectUtilities.getProject(projectNameCombo.getText());
//				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
//				if (runtime != null)
//					serverTargetText.setText(runtime.getName());
//			}
//		});
		
		serverTargetText.setLayoutData((new GridData(GridData.FILL_HORIZONTAL)));
		new Label(parent, SWT.NONE);
		String projectName = projectNameCombo.getText();
		if (projectName!=null && projectName.length()!=0) {
			IProject project = ProjectUtilities.getProject(projectName);
			if (project !=null) {
				IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();
				if (runtime != null){
					serverTargetText.setText(runtime.getName());
					synchHelper.getDataModel().setProperty(IJavaComponentCreationDataModelProperties.RUNTIME_TARGET_ID, runtime.getName());
				}	
			}
		}
	}
}