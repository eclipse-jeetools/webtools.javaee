/******************************************************************************
 * Copyright (c) 2009 Red Hat, IBM
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - additional support
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.UpdateManifestOperation;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEModuleHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.DependencyType;
import org.eclipse.wst.common.componentcore.internal.IModuleHandler;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.ui.ModuleCoreUIPlugin;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.ComponentDependencyContentProvider;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.NewReferenceWizard;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager.ReferenceExtension;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;

public class J2EEModuleDependenciesPropertyPage extends
		AddModuleDependenciesPropertiesPage {
	protected TableViewer availableDerivedComponentsViewer;
	// Manifest Mappings that existed when the page was opened (or last saved)
	protected HashMap<IVirtualComponent, String> derivedRefsOldComponentToRuntimePath = new HashMap<IVirtualComponent, String>();

	// Manifest Mappings that are current
	protected HashMap<IVirtualComponent, String> derivedRefsObjectToRuntimePath = new HashMap<IVirtualComponent, String>();

	protected ArrayList<IVirtualComponent> derivedRefsConsumedReferences = new ArrayList<IVirtualComponent>();
	protected Listener derivedTableListener;
	protected Listener derivedLabelListener;
	
	private class RuntimePathCellModifier implements ICellModifier {

		public boolean canModify(Object element, String property) {
			if( property.equals(DEPLOY_PATH_PROPERTY)) {
				
				return true;
			}
			return false;
		}

		public Object getValue(Object element, String property) {
			Object data = element; //((TableItem)element).getData();
			if( data instanceof IVirtualComponent ) {
				return derivedRefsObjectToRuntimePath.get(element) == null ? new Path("/") //$NON-NLS-1$
						.toString() : derivedRefsObjectToRuntimePath.get(element);
			} else if( data instanceof ComponentResourceProxy) {
				return ((ComponentResourceProxy)data).runtimePath.toString();
			}
			return new Path("/"); //$NON-NLS-1$
		}

		public void modify(Object element, String property, Object value) {
			if (property.equals(DEPLOY_PATH_PROPERTY)) {
				TableItem item = (TableItem) element;
				if( item.getData() instanceof IVirtualComponent) {
					derivedRefsObjectToRuntimePath.put((IVirtualComponent)item.getData(), (String) value);
				} else if( item.getData() instanceof ComponentResourceProxy) {
					ComponentResourceProxy c = ((ComponentResourceProxy)item.getData());
					c.runtimePath = new Path((String)value);
					resourceMappingsChanged = true;
				}
				refresh();
			}
		}

	}

	public J2EEModuleDependenciesPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}
	
	@Override
	public boolean postHandleChanges(IProgressMonitor monitor) {
		return true;
	}
	
	@Override
	protected void handleRemoved(ArrayList<IVirtualComponent> removed) {
		super.handleRemoved(removed);
		
		J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(rootComponent.getProject());
	}
	@Override
	protected void handleRemoveSelectedButton() {
		//Remove from either table
		ISelection sel = availableComponentsViewer.getSelection();
		if( sel instanceof IStructuredSelection ) {
			Object o = ((IStructuredSelection)sel).getFirstElement();
			if( o instanceof IVirtualComponent)
				objectToRuntimePath.remove(o);
			else if( o instanceof ComponentResourceProxy) 
				resourceMappings.remove(o);
			refresh();
		}
		sel = availableDerivedComponentsViewer.getSelection();
		if( sel instanceof IStructuredSelection ) {
			Object o = ((IStructuredSelection)sel).getFirstElement();
			if( o instanceof IVirtualComponent)
				derivedRefsObjectToRuntimePath.remove(o);
			refresh();
		}
	}


	@Override
	protected String getAddFolderLabel() {
		
		if (JavaEEProjectUtilities.isEJBProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_0;
		if (JavaEEProjectUtilities.isApplicationClientProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_1;
		if (JavaEEProjectUtilities.isJCAProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_2;
		return super.getAddFolderLabel();
	}
	
	/*
	 * Listeners of various events
	 */

	protected void addDerivedTableListeners() {
		addDerivedHoverHelpListeners();
		addDerivedDoubleClickListener();
		addDerivedSelectionListener();
	}
	
	protected void addDerivedDoubleClickListener() {
		availableDerivedComponentsViewer.setColumnProperties(new String[] { 
				DEPLOY_PATH_PROPERTY, SOURCE_PROPERTY });
		
		CellEditor[] editors = new CellEditor[] { 
				new TextCellEditor(availableDerivedComponentsViewer.getTable()),
				new TextCellEditor()};
		availableDerivedComponentsViewer.setCellEditors(editors);
		availableDerivedComponentsViewer
				.setCellModifier(new RuntimePathCellModifier());
	}
	
	protected void addDerivedHoverHelpListeners() {
		final Table table = availableDerivedComponentsViewer.getTable();
		createDerivedLabelListener(table);
		createDerivedTableListener(table);
		table.addListener(SWT.Dispose, derivedTableListener);
		table.addListener(SWT.KeyDown, derivedTableListener);
		table.addListener(SWT.MouseMove, derivedTableListener);
		table.addListener(SWT.MouseHover, derivedTableListener);
	}

	@Override
	protected String getModuleAssemblyRootPageDescription() {
		
		if (JavaEEProjectUtilities.isEJBProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_3;
		if (JavaEEProjectUtilities.isApplicationClientProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_4;
		if (JavaEEProjectUtilities.isJCAProject(project))
			return Messages.J2EEModuleDependenciesPropertyPage_5;
		return super.getModuleAssemblyRootPageDescription();
	}


	@Override
	protected void addComponents(ArrayList<IVirtualComponent> components) throws CoreException {
		// First add the components
		super.addComponents(components);
		// Now add to MANIFEST
		addToManifest(components);
		
	}
	@Override
	protected IDataModelProvider getAddReferenceDataModelProvider(IVirtualComponent component) {
		return new AddComponentToEnterpriseApplicationDataModelProvider();
	}
	
	
	
	protected void addToManifest(ArrayList<IVirtualComponent> components) {
		
		StringBuffer newComps = getCompsForManifest(components);
		if(newComps.toString().length() > 0) {
			UpdateManifestOperation op = createManifestOperation(newComps.toString());
			try {
				op.run(new NullProgressMonitor());
			} catch (InvocationTargetException e) {
				J2EEUIPlugin.logError(e);
			} catch (InterruptedException e) {
				J2EEUIPlugin.logError(e);
			}	
		}
	}
	@Override
	protected void addOneComponent(IVirtualComponent component) throws CoreException {
		String path, archiveName;
		path = new Path(derivedRefsObjectToRuntimePath.get(component)).removeLastSegments(1).toString();
		archiveName = new Path(derivedRefsObjectToRuntimePath.get(component)).lastSegment();

		
		//Find the Ear's that contain this component
		IProject[] earProjects = EarUtilities.getReferencingEARProjects(rootComponent.getProject());
		for (int i = 0; i < earProjects.length; i++) {
			IProject project = earProjects[i];
			
			IDataModelProvider provider = getAddReferenceDataModelProvider(component);
			IDataModel dm = DataModelFactory.createDataModel(provider);
			
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, ComponentCore.createComponent(project));
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, Arrays.asList(component));
			
			//[Bug 238264] the uri map needs to be manually set correctly
			Map<IVirtualComponent, String> uriMap = new HashMap<IVirtualComponent, String>();
			uriMap.put(component, archiveName);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP, uriMap);
	        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, path);
	
			IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			if (stat != OK_STATUS)
				throw new CoreException(stat);
			try {
				dm.getDefaultOperation().execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				ModuleCoreUIPlugin.logError(e);
			}	
		}
	}
	

	protected StringBuffer getCompsForManifest(ArrayList<IVirtualComponent> components) {
		StringBuffer newComps = new StringBuffer();
		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			IVirtualComponent comp = (IVirtualComponent) iterator.next();
			String archiveName = new Path(derivedRefsObjectToRuntimePath.get(comp)).lastSegment();
			newComps.append(archiveName);
			newComps.append(' ');
		}
		return newComps;
	}
	
	protected UpdateManifestOperation createManifestOperation(String newComps) {
		return new UpdateManifestOperation(project.getName(), newComps, false);
	}

	@Override
	protected void removeComponents(ArrayList<IVirtualComponent> removed) {
		// We will leave the references in the Ear's
		
		// Now remove from MANIFEST
		removeFromManifest(removed);
		
	}
	

	private void removeFromManifest(ArrayList<IVirtualComponent> removed) {
		String sourceProjName = project.getName();
		IProgressMonitor monitor = new NullProgressMonitor();
		IFile manifestmf = J2EEProjectUtilities.getManifestFile(project);
		ArchiveManifest mf = J2EEProjectUtilities.readManifest(project);
		if (mf == null)
			return;
		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(new UpdateManifestDataModelProvider());
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, sourceProjName);
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
		String[] cp = mf.getClassPathTokenized();
		List cpList = new ArrayList();
		
		for (int i = 0; i < cp.length; i++) {
			boolean foundMatch = false;
			for (Iterator iterator = removed.iterator(); iterator.hasNext();) {
				IVirtualComponent comp = (IVirtualComponent) iterator.next();
				String cpToRemove = new Path(derivedRefsOldComponentToRuntimePath.get(comp)).lastSegment();
				if (cp[i].equals(cpToRemove))
					foundMatch = true;
			}
			if (!foundMatch)
				cpList.add(cp[i]);
		}
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, cpList);
		try {
			updateManifestDataModel.getDefaultOperation().execute(monitor, null );
		} catch (ExecutionException e) {
			J2EEUIPlugin.logError(e);
		}
		
	}

	@Override
	protected IModuleHandler getModuleHandler() {
		if(moduleHandler == null)
			moduleHandler = new JavaEEModuleHandler();
		return moduleHandler;
	}
	
	@Override
	public void fillComposite(Composite parent) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		parent.setLayout(layout);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		createTable(parent);
		createButtonColumn(parent);
		availableModules = new Label(parent, SWT.NONE);
		GridData gData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL);
		availableModules.setText(Messages.J2EEModuleDependenciesPropertyPage_6); 
		availableModules.setLayoutData(gData);
		//empty space label
		new Label(parent, SWT.LEFT);
		createDerivedTable(parent);
	}
	
	protected void createDerivedTable(Composite parent) {
		if (rootComponent != null) {
			availableDerivedComponentsViewer = createAvailableComponentsViewer(parent);
			GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL
					| GridData.FILL_VERTICAL);
			availableDerivedComponentsViewer.getTable().setLayoutData(gd);
			

			ComponentDependencyContentProvider provider = createProvider();
			provider.setRuntimePaths(derivedRefsObjectToRuntimePath);
			provider.setResourceMappings(new ArrayList());
			availableDerivedComponentsViewer.setContentProvider(provider);
			//availableComponentsViewer.setLabelProvider(new DecoratingLabelProvider(
	        //        new WorkbenchLabelProvider(), PlatformUI.getWorkbench().
	        //         getDecoratorManager().getLabelDecorator()));
			availableDerivedComponentsViewer.setLabelProvider(provider);
			addDerivedTableListeners();
		}
	}

	@Override
	protected void initialize() {
		super.initialize();
		// Now initialize MANIFEST and classpath derived refs
		IVirtualReference[] hardRefs = ((J2EEModuleVirtualComponent)rootComponent).getNonManifestReferences(false);
		IVirtualReference[] javaRefs = ((J2EEModuleVirtualComponent)rootComponent).getJavaClasspathReferences(hardRefs); 
		List dynamicReferences = J2EEModuleVirtualComponent.getManifestReferences(rootComponent, hardRefs, false);
		IVirtualReference[] manifestRefs;
		if (dynamicReferences != null)
			manifestRefs = (IVirtualReference[])dynamicReferences.toArray(new IVirtualReference[dynamicReferences.size()]);
		else
			manifestRefs = new IVirtualReference[0];
		
		IVirtualComponent comp;
		//Add to derived table
		for( int i = 0; i < manifestRefs.length; i++ ) { 
			comp = manifestRefs[i].getReferencedComponent();
			String archiveName = manifestRefs[i].getDependencyType() == DependencyType.CONSUMES ? null : manifestRefs[i].getArchiveName();
			String val = (archiveName != null) ? manifestRefs[i].getRuntimePath().append(archiveName).toString() : manifestRefs[i].getRuntimePath().toString();
			derivedRefsObjectToRuntimePath.put(comp, val);
			derivedRefsOldComponentToRuntimePath.put(comp, val);
			if( manifestRefs[i].getDependencyType() == DependencyType.CONSUMES)
				derivedRefsConsumedReferences.add(comp);
		}
		//Add java classpath entries to derived table
		for( int i = 0; i < javaRefs.length; i++ ) { 
			comp = javaRefs[i].getReferencedComponent();
			String archiveName = javaRefs[i].getDependencyType() == DependencyType.CONSUMES ? null : javaRefs[i].getArchiveName();
			String val = (archiveName != null) ? javaRefs[i].getRuntimePath().append(archiveName).toString() : javaRefs[i].getRuntimePath().toString();
			derivedRefsObjectToRuntimePath.put(comp, val);
			derivedRefsOldComponentToRuntimePath.put(comp, val);
			if( javaRefs[i].getDependencyType() == DependencyType.CONSUMES)
				derivedRefsConsumedReferences.add(comp);
		}
	}

	@Override
	protected void resetTableUI() {
		// TODO Auto-generated method stub
		super.resetTableUI();
		
			IWorkspaceRoot input = ResourcesPlugin.getWorkspace().getRoot();
			availableDerivedComponentsViewer.setInput(input);
			GridData data = new GridData(GridData.FILL_BOTH);
			int numlines = Math.min(10, availableDerivedComponentsViewer.getTable()
					.getItemCount());
			data.heightHint = availableDerivedComponentsViewer.getTable().getItemHeight()
					* numlines;
			availableDerivedComponentsViewer.getTable().setLayoutData(data);
			
	}
	protected void addDerivedSelectionListener() {
		availableDerivedComponentsViewer.addSelectionChangedListener(
				new ISelectionChangedListener(){
					public void selectionChanged(SelectionChangedEvent event) {
						derivedViewerSelectionChanged();
					}
				});
	}
	
	protected void derivedViewerSelectionChanged() {
		Object selected = getSelectedDerivedObject();
		if(selected == null) return;
		//Remove selection from other table
		availableComponentsViewer.setSelection(null, true);
		editReferenceButton.setEnabled(hasEditWizardPage(selected));
		removeButton.setEnabled(getSelectedDerivedObject() != null && canEdit(selected));
		
	}
	@Override
	protected void viewerSelectionChanged() {
		super.viewerSelectionChanged();
		availableDerivedComponentsViewer.setSelection(null, true);
	}
	
	protected Object getSelectedDerivedObject() {
		IStructuredSelection sel = (IStructuredSelection)availableDerivedComponentsViewer.getSelection();
		return sel.getFirstElement();
	}
	
	@Override
	protected boolean saveReferenceChanges() {
		boolean subResult = super.saveReferenceChanges();
		// Fill our delta lists
		ArrayList<IVirtualComponent> added = new ArrayList<IVirtualComponent>();
		ArrayList<IVirtualComponent> removed = new ArrayList<IVirtualComponent>();
		ArrayList<IVirtualComponent> changed = new ArrayList<IVirtualComponent>();

		Iterator<IVirtualComponent> j = derivedRefsOldComponentToRuntimePath.keySet().iterator();
		Object key, val;
		while (j.hasNext()) {
			key = j.next();
			val = derivedRefsOldComponentToRuntimePath.get(key);
			if( !derivedRefsObjectToRuntimePath.containsKey(key))
				removed.add((IVirtualComponent)key);
			else if (!val.equals(derivedRefsObjectToRuntimePath.get(key)))
				changed.add((IVirtualComponent)key);
		}

		j = derivedRefsObjectToRuntimePath.keySet().iterator();
		while (j.hasNext()) {
			key = j.next();
			if (!derivedRefsOldComponentToRuntimePath.containsKey(key))
				added.add((IVirtualComponent)key);
		}

		NullProgressMonitor monitor = new NullProgressMonitor();
		subResult &= preHandleChanges(monitor);
		if( !subResult )
			return false;
		
		handleDeltas(removed, changed, added);
		subResult &= postHandleChanges(monitor);
		
		// Now update the variables
		derivedRefsOldComponentToRuntimePath.clear();
		ArrayList<IVirtualComponent> keys = new ArrayList<IVirtualComponent>();
		keys.addAll(derivedRefsObjectToRuntimePath.keySet());
		Iterator<IVirtualComponent> i = keys.iterator();
		while(i.hasNext()) {
			IVirtualComponent vc = i.next();
			String path = derivedRefsObjectToRuntimePath.get(vc);
			derivedRefsOldComponentToRuntimePath.put(vc, path);
		}
		return subResult;
	}
	
	@Override
	protected void showReferenceWizard(boolean editing) {
		ReferenceExtension[] extensions = 
			DependencyPageExtensionManager.getManager().getExposedReferenceExtensions();
		extensions = filterReferenceTypes(extensions);
		NewReferenceWizard wizard = new NewReferenceWizard(extensions);
		// fill the task model
		wizard.getTaskModel().putObject(IReferenceWizardConstants.PROJECT, project);
		wizard.getTaskModel().putObject(IReferenceWizardConstants.ROOT_COMPONENT, rootComponent);
		wizard.getTaskModel().putObject(IReferenceWizardConstants.MODULEHANDLER, getModuleHandler());

		IVirtualComponent selected = null;
		if( editing ) {
			Object o = ((IStructuredSelection)availableDerivedComponentsViewer.getSelection()).getFirstElement();
			if( o instanceof IVirtualComponent ) {
				selected = (IVirtualComponent)o;
				wizard.getTaskModel().putObject(IReferenceWizardConstants.COMPONENT, selected);
				wizard.getTaskModel().putObject(IReferenceWizardConstants.COMPONENT_PATH, derivedRefsObjectToRuntimePath.get(selected));
			}
		}
		
		WizardDialog wd = new WizardDialog(addReferenceButton.getShell(), wizard);
		if( wd.open() != Window.CANCEL) {
			if( editing && selected != null) {
				// remove old
				derivedRefsObjectToRuntimePath.remove(selected); 
				consumedReferences.remove(selected);
			}
			
			Object c1 = wizard.getTaskModel().getObject(IReferenceWizardConstants.COMPONENT);
			Object p1 = wizard.getTaskModel().getObject(IReferenceWizardConstants.COMPONENT_PATH);
			DependencyType type = (DependencyType)wizard.getTaskModel().getObject(IReferenceWizardConstants.DEPENDENCY_TYPE);
			boolean consumed = type == null ? false : type.equals(DependencyType.CONSUMES_LITERAL);
			
			IVirtualComponent[] compArr = c1 instanceof IVirtualComponent ? 
					new IVirtualComponent[] { (IVirtualComponent)c1 } : 
						(IVirtualComponent[])c1;
			String[] pathArr = p1 instanceof String ? 
							new String[] { (String)p1 } : 
								(String[])p1;
			insertIntoTable(consumed, compArr, pathArr);
			refresh();
		}
	}

	protected void insertIntoTable(boolean consumed, IVirtualComponent[] compArr, String[] pathArr) {
		for( int i = 0; i < compArr.length; i++ ) {
			derivedRefsObjectToRuntimePath.put(compArr[i], 
					getRuntimePath(compArr[i], pathArr[i]));
			if( consumed ) 
				consumedReferences.add(compArr[i]);
		}
	}

	protected void createDerivedLabelListener(final Table table) {
		derivedLabelListener = new Listener() {
			public void handleEvent(Event event) {
				Label label = (Label) event.widget;
				Shell shell = label.getShell();
				switch (event.type) {
				case SWT.MouseDown:
					Event e = new Event();
					e.item = (TableItem) label.getData("_TABLEITEM"); //$NON-NLS-1$
					table.setSelection(new TableItem[] { (TableItem) e.item });
					table.notifyListeners(SWT.Selection, e);
					shell.dispose();
					table.setFocus();
					break;
				case SWT.MouseExit:
					shell.dispose();
					break;
				}
			}
		};
	}

	protected void createDerivedTableListener(final Table table) {
		derivedTableListener = new Listener() {
			Shell tip = null;
			Label label = null;
	
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.Dispose:
				case SWT.KeyDown:
				case SWT.MouseMove: {
					if (tip == null)
						break;
					tip.dispose();
					tip = null;
					label = null;
					break;
				}
				case SWT.MouseHover: {
					TableItem item = table.getItem(new Point(event.x, event.y));
					if (item != null && item.getData() != null && !canEdit(item.getData())) {
						if (tip != null && !tip.isDisposed())
							tip.dispose();
						tip = new Shell(PlatformUI.getWorkbench()
								.getActiveWorkbenchWindow().getShell(),
								SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
						tip.setBackground(Display.getDefault().getSystemColor(
								SWT.COLOR_INFO_BACKGROUND));
						FillLayout layout = new FillLayout();
						layout.marginWidth = 2;
						tip.setLayout(layout);
						label = new Label(tip, SWT.WRAP);
						label.setForeground(Display.getDefault()
								.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
						label.setBackground(Display.getDefault()
								.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
						label.setData("_TABLEITEM", item); //$NON-NLS-1$
						label.setText( org.eclipse.wst.common.componentcore.ui.Messages.InternalLibJarWarning);
						label.addListener(SWT.MouseExit, labelListener);
						label.addListener(SWT.MouseDown, labelListener);
						Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
						Rectangle rect = item.getBounds(0);
						Point pt = table.toDisplay(rect.x, rect.y);
						tip.setBounds(pt.x, pt.y - size.y, size.x, size.y);
						tip.setVisible(true);
					}
				}
				}
			}
		};
	}
	
	
	/*
	 * Temporary fix during cleanup for Bug 305959
	 */
	protected boolean canEdit(Object data) {
		if( data == null ) return false;
		if( !(data instanceof VirtualArchiveComponent)) return true;
		
		VirtualArchiveComponent d2 = (VirtualArchiveComponent)data;
		boolean sameProject = d2.getWorkspaceRelativePath() != null
			&& d2.getWorkspaceRelativePath().segment(0)
				.equals(rootComponent.getProject().getName());
		return !(sameProject && isPhysicallyAdded(d2));
	}

	protected boolean isPhysicallyAdded(VirtualArchiveComponent component) {
		try {
			component.getProjectRelativePath();
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	
}
