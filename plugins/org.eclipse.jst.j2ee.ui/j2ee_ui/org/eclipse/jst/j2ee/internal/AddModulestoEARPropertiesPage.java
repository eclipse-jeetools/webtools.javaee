/*
 * Created on Jan 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.BuildPathDialogAccess;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationOperation;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIMessages;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJavaProjectMigrationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationDataModelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

/**
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AddModulestoEARPropertiesPage extends PropertyPage implements Listener, ICommonManifestUIConstants {

	protected IProject project;
	protected IVirtualComponent earComponent = null;
	protected Text componentNameText;
	protected Label availableModules;
	protected CheckboxTableViewer availableComponentsViewer;
	protected Button selectAllButton;
	protected Button deselectAllButton;
	protected Button externalJarButton;
	protected Button addVariableButton;
	protected Composite buttonColumn;

	protected List j2eeComponentList = new ArrayList();
	protected List javaProjectsList = new ArrayList();
	protected static final IStatus OK_STATUS = IDataModelProvider.OK_STATUS;

	/**
	 * Constructor for AddModulestoEARPropertiesPage.
	 */
	public AddModulestoEARPropertiesPage() {
		super();
	}
	
	protected Control createContents(Composite parent) {
		initialize();


		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		if (earComponent == null)
			return composite;
		createProjectLabelsGroup(composite);
		createListGroup(composite);
		refresh();
		return composite;
	}
	
	protected void initialize() {
		project = (IProject) getElement().getAdapter(IResource.class);

		if (ModuleCoreNature.isFlexibleProject(project)) {
			IVirtualComponent component = ComponentCore.createComponent(project);
				if (J2EEProjectUtilities.isEARProject(project))
					earComponent = component;
		}
	}

	protected void createProjectLabelsGroup(Composite parent) {

		Composite labelsGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		labelsGroup.setLayout(layout);
		labelsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(labelsGroup, SWT.NONE);
		label.setText(ManifestUIResourceHandler.Project_name__UI_); 

		componentNameText = new Text(labelsGroup, SWT.BORDER);
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		componentNameText.setEditable(false);
		componentNameText.setLayoutData(data);
		componentNameText.setText(project.getName());
	}

	protected void createListGroup(Composite parent) {
		Composite listGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		listGroup.setLayout(layout);
		GridData gData = new GridData(GridData.FILL_BOTH);
		gData.horizontalIndent = 5;
		listGroup.setLayoutData(gData);

		availableModules = new Label(listGroup, SWT.NONE);
		gData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
		availableModules.setText(J2EEUIMessages.getResourceString("AVAILABLE_J2EE_COMPONENTS")); //$NON-NLS-1$ = "Available dependent JARs:"
		availableModules.setLayoutData(gData);
		createTableComposite(listGroup);
	}

	public void dispose() {
		super.dispose();
	}

	public boolean performOk() {
		NullProgressMonitor monitor = new NullProgressMonitor();
		addModulesToEAR(monitor);
		removeModulesFromEAR(monitor);
		return true;
	}

	private List newJ2EEModulesToAdd(){
		List newComps = new ArrayList();
		if (j2eeComponentList != null && !j2eeComponentList.isEmpty()){
			for (int i = 0; i < j2eeComponentList.size(); i++){
				IVirtualComponent handle = (IVirtualComponent)j2eeComponentList.get(i);
				if( !inEARAlready(handle))
					newComps.add(handle);
			}
		}
		return newComps;
	}
	
	private IStatus addModulesToEAR(IProgressMonitor monitor) {
		IStatus stat = OK_STATUS;
		try {
			if( earComponent != null ){
				List list = newJ2EEModulesToAdd();				
				if (list != null && !list.isEmpty()) {
					IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
					
					dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComponent);					
					dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, list);
					stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
					if (stat != OK_STATUS)
						return stat;
					dm.getDefaultOperation().execute(monitor, null);
				}
				
				if (!javaProjectsList.isEmpty()) {
	
					for (int i = 0; i < javaProjectsList.size(); i++) {
						IProject proj = (IProject) javaProjectsList.get(i);
						IDataModel migrationdm = DataModelFactory.createDataModel(new JavaProjectMigrationDataModelProvider());
						migrationdm.setProperty(IJavaProjectMigrationDataModelProperties.PROJECT_NAME, proj.getName());
						migrationdm.getDefaultOperation().execute(monitor, null);
	
	
						IDataModel refdm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
						List targetCompList = (List) refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
	
						IVirtualComponent targetcomponent = ComponentCore.createComponent(proj);
						targetCompList.add(targetcomponent);
	
						refdm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComponent);
						refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetCompList);
						refdm.getDefaultOperation().execute(monitor, null);
						j2eeComponentList.add(targetcomponent);
					}
				}
			}

		} catch (Exception e) {
			Logger.getLogger().log(e);
		}
		return OK_STATUS;
	}
	
	private IStatus removeModulesFromEAR(IProgressMonitor monitor) {
		IStatus stat = OK_STATUS;
		if( earComponent != null && j2eeComponentList != null){
			List list = getComponentsToRemove();
			if( !list.isEmpty()){
				try {
					RemoveComponentFromEnterpriseApplicationOperation op = removeComponentFromEAROperation(earComponent, list);
					op.execute(null, null);
				} catch (ExecutionException e) {
					Logger.getLogger().log(e);
				}
			}
		}
		return stat;
	}		
	
	protected  RemoveComponentFromEnterpriseApplicationOperation removeComponentFromEAROperation(IVirtualComponent sourceComponent, List targetComponentsHandles) {
		IDataModel model = DataModelFactory.createDataModel(new RemoveReferenceComponentsDataModelProvider());
		model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, sourceComponent);
		List modHandlesList = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		modHandlesList.addAll(targetComponentsHandles);
		model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modHandlesList);
		return new RemoveComponentFromEnterpriseApplicationOperation(model);
	}
	
	protected List getComponentsToRemove(){
		//j2eeComponentList = getCheckedJ2EEElementsAsList();
		List list = new ArrayList();
		if( earComponent != null && list != null ){
			IVirtualReference[] oldrefs = earComponent.getReferences();
			for (int j = 0; j < oldrefs.length; j++) {
				IVirtualReference ref = oldrefs[j];
				IVirtualComponent handle = ref.getReferencedComponent();
				if( !j2eeComponentList.contains(handle)){
					list.add(handle);
				}
			}
		}
		return list;		
	}
	
	
	public void handleEvent(Event event) {
		if (event.widget == selectAllButton)
			handleSelectAllButtonPressed();
		else if (event.widget == deselectAllButton)
			handleDeselectAllButtonPressed();
		else if(event.widget == externalJarButton)
			handleSelectExternalJarButton();
		else if(event.widget == addVariableButton)
			handleSelectVariableButton();		
	}

	private void handleSelectAllButtonPressed() {
		availableComponentsViewer.setAllChecked(true);
		j2eeComponentList = getCheckedJ2EEElementsAsList();
		javaProjectsList = getCheckedJavaProjectsAsList();
	}

	private void handleDeselectAllButtonPressed() {
		availableComponentsViewer.setAllChecked(false);
		j2eeComponentList = new ArrayList();
		javaProjectsList = new ArrayList();
	}
	
	private void handleSelectExternalJarButton(){
		IPath[] selected= BuildPathDialogAccess.chooseExternalJAREntries(getShell());

		if (selected != null) {
			for (int i= 0; i < selected.length; i++) {
				
				String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
				IVirtualComponent archive = ComponentCore.createArchiveComponent( earComponent.getProject(), type +
							selected[i].toString());
				
				ArrayList vlist = new ArrayList();
			
				//To do: check if archive component already exists
				IVirtualReference ref = ComponentCore.createReference( earComponent, archive );
				vlist.add(ref);	
				
				IVirtualReference[] refs = (IVirtualReference[]) vlist.toArray(new IVirtualReference[vlist.size()]);
				earComponent.addReferences(refs);
				j2eeComponentList.add(archive);
			}
			refresh();
		}
		
	}

	private void handleSelectVariableButton(){
		IPath existingPath[] = new Path[0];
		IPath[] paths =  BuildPathDialogAccess.chooseVariableEntries(getShell(), existingPath);
		
		if (paths != null) {
			refresh();
			for (int i = 0; i < paths.length; i++) {
				IPath resolvedPath= JavaCore.getResolvedVariablePath(paths[i]);

				java.io.File file = new java.io.File(resolvedPath.toOSString());
				if( file.isFile() && file.exists()){
					String type = VirtualArchiveComponent.VARARCHIVETYPE + IPath.SEPARATOR;
					
					IVirtualComponent archive = ComponentCore.createArchiveComponent( earComponent.getProject(), type +
								paths[i].toString());
					
					ArrayList vlist = new ArrayList();
					IVirtualReference[] oldrefs = earComponent.getReferences();
					for (int j = 0; j < oldrefs.length; j++) {
						IVirtualReference ref = oldrefs[j];
						vlist.add(ref);
					}		
				
					//To do: check if archive component already exists
					IVirtualReference ref = ComponentCore.createReference( earComponent, archive );
					vlist.add(ref);	
					
					IVirtualReference[] refs = new IVirtualReference[vlist.size()];
					for (int j = 0; j < vlist.size(); j++) {
						IVirtualReference tmpref = (IVirtualReference) vlist.get(j);
						refs[j] = tmpref;
					}				
					earComponent.setReferences(refs);
					j2eeComponentList.add(archive);
				}else{
					//display error
				}
			}
			refresh();
		}	
	}
	
	protected void createTableComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gData);
		fillComposite(composite);
	}

	public void fillComposite(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		parent.setLayout(layout);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		createTable(parent);
		createButtonColumn(parent);
	}

	protected void createButtonColumn(Composite parent) {
		buttonColumn = createButtonColumnComposite(parent);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END);
		buttonColumn.setLayoutData(data);
		createPushButtons();
	}

	protected void createPushButtons() {
		selectAllButton = createPushButton(SELECT_ALL_BUTTON);
		deselectAllButton = createPushButton(DE_SELECT_ALL_BUTTON);
		externalJarButton = createPushButton(J2EEUIMessages.getResourceString("EXTERNAL_JAR"));//$NON-NLS-1$
		addVariableButton = createPushButton(J2EEUIMessages.getResourceString("ADDVARIABLE"));//$NON-NLS-1$
	}

	protected Button createPushButton(String label) {
		Button aButton = primCreatePushButton(label, buttonColumn);
		aButton.addListener(SWT.Selection, this);
		aButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		return aButton;
	}

	public Button primCreatePushButton(String label, Composite aButtonColumn) {
		Button aButton = new Button(aButtonColumn, SWT.PUSH);
		aButton.setText(label);
		return aButton;
	}

	public Composite createButtonColumnComposite(Composite parent) {
		Composite aButtonColumn = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		aButtonColumn.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING);
		aButtonColumn.setLayoutData(data);
		return aButtonColumn;
	}

	public Group createGroup(Composite parent) {
		return new Group(parent, SWT.NULL);
	}

	protected void createTable(Composite parent) {
		availableComponentsViewer = createavailableComponentsViewer(parent);
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.FILL_VERTICAL);
		availableComponentsViewer.getTable().setLayoutData(gd);

		if (earComponent != null) {
			int j2eeVersion = J2EEVersionUtil.convertVersionStringToInt(earComponent);
			AvailableJ2EEComponentsForEARContentProvider provider = new AvailableJ2EEComponentsForEARContentProvider(earComponent, j2eeVersion);
			availableComponentsViewer.setContentProvider(provider);
			availableComponentsViewer.setLabelProvider(provider);
			
			addTableListeners();
		}
	}

	protected void addTableListeners() {
		addCheckStateListener();
	}

	protected void addCheckStateListener() {
		availableComponentsViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent event) {
				j2eeComponentList = getCheckedJ2EEElementsAsList();
				javaProjectsList = getCheckedJavaProjectsAsList();

			}
		});
	}

	protected Object[] getComponentsInEar() {
		List list = new ArrayList();
		IVirtualReference refs[] = earComponent.getReferences();
		for( int i=0; i< refs.length; i++){
			IVirtualReference ref = refs[i];
			list.add(ref.getReferencedComponent());
		}
		return list.toArray();
	}
	
	/**
	 * 
	 * @param componentHandle
	 * @return
	 * @description  returns true is a component is already in the EAR as a dependent
	 */
	protected boolean inEARAlready(IVirtualComponent component){
		IVirtualReference refs[] = earComponent.getReferences();
		for( int i=0; i< refs.length; i++){
			IVirtualReference ref = refs[i];
			if  ( ref.getReferencedComponent().equals( component ))
				return true;
		}	
		return false;
	}
	
	protected List getCheckedJ2EEElementsAsList() {
		Object[] elements = availableComponentsViewer.getCheckedElements();
		List list;
		if (elements == null || elements.length == 0)
			list = Collections.EMPTY_LIST;
		else {
			list = new ArrayList();
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] instanceof IVirtualComponent) {
					list.add(elements[i]);
				}
			}
		}
		return list;
	}

	protected List getCheckedJavaProjectsAsList() {
		Object[] elements = availableComponentsViewer.getCheckedElements();
		List list;
		if (elements == null || elements.length == 0)
			list = Collections.EMPTY_LIST;
		else {
			list = new ArrayList();
			for (int i = 0; i < elements.length; i++) {
				if (elements[i] instanceof IProject) {
					list.add(elements[i]);
				}
			}
		}
		return list;
	}

	public CheckboxTableViewer createavailableComponentsViewer(Composite parent) {
		int flags = SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI;

		Table table = new Table(parent, flags);
		availableComponentsViewer = new CheckboxTableViewer(table);

		// set up table layout
		TableLayout tableLayout = new org.eclipse.jface.viewers.TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		table.setLayout(tableLayout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		availableComponentsViewer.setSorter(null);

		// table columns
		TableColumn fileNameColumn = new TableColumn(table, SWT.NONE, 0);
		fileNameColumn.setText(ManifestUIResourceHandler.JAR_Module_UI_); 
		fileNameColumn.setResizable(true);

		TableColumn projectColumn = new TableColumn(table, SWT.NONE, 1);
		projectColumn.setText(ManifestUIResourceHandler.Project_UI_); 
		projectColumn.setResizable(true);
		tableLayout.layout(table, true);
		return availableComponentsViewer;

	}

	public void refresh() {

		IWorkspaceRoot input = ResourcesPlugin.getWorkspace().getRoot();
		availableComponentsViewer.setInput(input);
		GridData data = new GridData(GridData.FILL_BOTH);
		int numlines = Math.min(10, availableComponentsViewer.getTable().getItemCount());
		data.heightHint = availableComponentsViewer.getTable().getItemHeight() * numlines;
		availableComponentsViewer.getTable().setLayoutData(data);

		TableItem [] items = availableComponentsViewer.getTable().getItems();

		List list = new ArrayList();
		Object[] comps = getComponentsInEar();
		
		for( int i=0; i< items.length; i++ ){
			Object element = items[i].getData();
			if( element instanceof IVirtualComponent){
				IVirtualComponent comp = (IVirtualComponent)element;
				for( int j=0; j< comps.length; j++ ){
					IVirtualComponent tempcomp = (IVirtualComponent)comps[j];
					if( comp.equals(tempcomp)){
						list.add(comp);
					}
				}
			}	
		}
		
		availableComponentsViewer.setCheckedElements(list.toArray());
		j2eeComponentList.addAll(list);
		GridData btndata = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING);
		buttonColumn.setLayoutData(btndata);

	}
}