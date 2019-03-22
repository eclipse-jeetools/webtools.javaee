/*******************************************************************************
 * Copyright (c) 2005, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *	   David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *     Stefan Dimov, stefan.dimov@sap.com - bug 207826
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Manifest;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.ElementChangedEvent;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IElementChangedListener;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaElementDelta;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.ClassPathSelection;
import org.eclipse.jst.j2ee.application.internal.operations.ClasspathElement;
import org.eclipse.jst.j2ee.application.internal.operations.RemoveComponentFromEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.classpathdep.UpdateClasspathAttributeUtil;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifestImpl;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualArchiveComponent;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyEnablement;
import org.eclipse.jst.j2ee.internal.common.ClasspathModel;
import org.eclipse.jst.j2ee.internal.common.ClasspathModelEvent;
import org.eclipse.jst.j2ee.internal.common.ClasspathModelListener;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.listeners.IValidateEditListener;
import org.eclipse.jst.j2ee.internal.listeners.ValidateEditListener;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.builder.IDependencyGraph;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.operation.RemoveReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider;
import org.eclipse.wst.common.frameworks.internal.ui.WTPUIPlugin;
import org.eclipse.wst.common.frameworks.internal.ui.WorkspaceModifyComposedOperation;

/**
 * @author jialin
 * @deprecated wtp 3.2
 */
public class JARDependencyPropertiesPage implements IJ2EEDependenciesControl, IClasspathTableOwner, Listener, ClasspathModelListener, IElementChangedListener {

	protected final IProject project;
	protected final J2EEDependenciesPage propPage;
	protected IOException caughtManifestException;
	protected boolean isDirty;
	protected Text classPathText;
	protected Text componentNameText;
	protected ClasspathModel model;
	protected CCombo availableAppsCombo;
	protected ClasspathTableManager tableManager;
	protected IValidateEditListener validateEditListener;
	protected Label manifestLabel;
	protected Label enterpriseApplicationLabel;
	protected Label availableDependentJars;
	private final Display display;
	private boolean isDisposed = false;
	
	/**
	 * Constructor for JARDependencyPropertiesControl
	 */
	public JARDependencyPropertiesPage(final IProject project, final J2EEDependenciesPage page) {
		super();
		J2EEComponentClasspathUpdater.getInstance().pauseUpdates();
		this.project = project;
		this.propPage = page;
		this.display = propPage.getShell().getDisplay();
	}

	/**
	 * Returns false if page should not be displayed for the project.
	 */
	protected void initialize() {
		model = createClasspathModel();
		model.setProject(project);
		if (model.getComponent() != null) {
			model.addListener(this);
			updateModelManifest();
			initializeValidateEditListener();
		}
	}

	protected ClasspathModel createClasspathModel(){
		return new ClasspathModel(J2EEProjectUtilities.readManifest(project), false);
	}

	public void dispose() {
		isDisposed = true;
		JavaCore.removeElementChangedListener(this);
		J2EEComponentClasspathUpdater.getInstance().resumeUpdates();
	}

	private void updateModelManifest() {
		if (JemProjectUtilities.isBinaryProject(project) || model.getAvailableEARComponents().length == 0)
			return;
		
		IVirtualComponent component = ComponentCore.createComponent(project);
		if(component != null){
			IVirtualFile vManifest = component.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
			if(vManifest.exists()){
				IFile iManifest = vManifest.getUnderlyingFile();
				if(iManifest != null && iManifest.exists()){
					InputStream in = null;
					try {
						in = iManifest.getContents();
						ArchiveManifest mf = new ArchiveManifestImpl(new Manifest(in));
						model.primSetManifest(mf);
					} catch (CoreException e) {
						Logger.getLogger().logError(e);
						model.primSetManifest(new ArchiveManifestImpl());
					} catch (IOException iox) {
						Logger.getLogger().logError(iox);
						model.primSetManifest(new ArchiveManifestImpl());
						caughtManifestException = iox;
					} finally {
						if (in != null) {
							try {
								in.close();
							} catch (IOException weTried) {
								// Ignore
							}
						}
					}
				}
			}
		}
	}


	protected void initializeValidateEditListener() {
		validateEditListener = new ValidateEditListener(null, model);
		validateEditListener.setShell(propPage.getShell());
	}

	public void setVisible(boolean visible) {
		if (visible) {
			if (caughtManifestException != null && !model.isDirty()) {
				ManifestErrorPrompter.showManifestException(propPage.getShell(), ERROR_READING_MANIFEST_DIALOG_MESSAGE_PROP_PAGE, false, caughtManifestException);
			}
		}
	}

	/**
	 * Refreshes the ClasspathModel if the project classpath is changed.
	 */
	public void elementChanged(final ElementChangedEvent event) {
		if (event.getType() == ElementChangedEvent.POST_CHANGE && classpathChanged(event.getDelta())) {
			// trigger a recomputation and refresh for the currently selected EAR
			if (!isDisposed) {
				display.asyncExec (new Runnable () {
					public void run () {
						if (!isDisposed) {
							handleClasspathChange();
						}
					}
				});
			}
		}
	}
	
	/**
	 * Called to refresh the UI when the classpath changes
	 */
	protected void handleClasspathChange() {
		model.resetClassPathSelection();
		refresh();		
	}
	
	private boolean classpathChanged(final IJavaElementDelta delta) {
		final int kind = delta.getKind();
		if (kind == IJavaElementDelta.CHANGED) {
			final int flags = delta.getFlags();
			final IJavaElement element = delta.getElement();
			if (element instanceof IJavaModel) {
				if ((flags & IJavaElementDelta.F_CHILDREN) == IJavaElementDelta.F_CHILDREN) {
					final IJavaElementDelta[] children = delta.getChangedChildren();
					for (int i = 0; i < children.length; i++) {
						// check all of the IJavaProject children
						if (classpathChanged(children[i])) {
							return true;
						}
					}
				}
			} else if (element instanceof IJavaProject) {
				// check if we either have a direct indication of a classpath change or a delta on the
				// .classpath file (changes to classpath entry attributes only give us this...)
				final IJavaProject jproject = (IJavaProject) element;
				final IProject eventProject = jproject.getProject();
				if (eventProject.equals(project)) {
					if ((flags & IJavaElementDelta.F_CLASSPATH_CHANGED) != 0) {
						return true; 	
					}
					final IResourceDelta[] deltas = delta.getResourceDeltas();
					if (deltas != null) {
						for (int i = 0; i < deltas.length; i++) {
							if (deltas[i].getProjectRelativePath().toString().equals(".classpath")) { //$NON-NLS-1$
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	public Composite createContents(Composite parent) {
		initialize();
		Composite composite = createBasicComposite(parent);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		if (model.getComponent() != null) {
			if (!isValidComponent())
				return composite;
			J2EEDependenciesPage.createDescriptionComposite(composite, ManifestUIResourceHandler.J2EE_Modules_Desc);
			createProjectLabelsGroup(composite);
			createListGroup(composite);
			createTextGroup(composite);
			refresh();
		}
	    Dialog.applyDialogFont(parent);
	    postCreateContents();
		return composite;
	}

	/**
	 * Called at the end of createContents().
	 */
	protected void postCreateContents() {
		// register this object as an IElementChangedListener so that it will react to user changes to the Java build path
		JavaCore.addElementChangedListener(this);	
	}
	
	/**
	 * @param comp
	 * @return
	 */
	protected Composite createBasicComposite(Composite comp) {
		Composite composite = new Composite(comp, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		return composite;
	}

	protected boolean isValidComponent() {
		if (JavaEEProjectUtilities.isEARProject(project)) {
			propPage.setErrorMessage(ManifestUIResourceHandler.EAR_Module_Dep_Error);
			return false;
		} else if (EarUtilities.isStandaloneProject(model.getComponent().getProject())) {
			propPage.setErrorMessage(ClasspathModel.NO_EAR_MESSAGE);
			return false;
		}
		return true;
	}

	protected void createProjectLabelsGroup(Composite parent) {

		Composite labelsGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		labelsGroup.setLayout(layout);
		labelsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		/*
		 * Label label = new Label(labelsGroup, SWT.NONE);
		 * label.setText(ManifestUIResourceHandler.Project_name__UI_);
		 * 
		 * componentNameText = new Text(labelsGroup, SWT.BORDER); GridData data = new
		 * GridData(GridData.FILL_HORIZONTAL); componentNameText.setEditable(false);
		 * componentNameText.setLayoutData(data); componentNameText.setText(project.getName());
		 */

		createEnterpriseAppsControls(labelsGroup);

	}

	private void createEnterpriseAppsControls(Composite labelsGroup) {

		enterpriseApplicationLabel = new Label(labelsGroup, SWT.NONE);
		enterpriseApplicationLabel.setText(ManifestUIResourceHandler.EAR_Project_Name__UI__UI_);

		availableAppsCombo = new CCombo(labelsGroup, SWT.READ_ONLY | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		availableAppsCombo.setLayoutData(gd);

		availableAppsCombo.addListener(SWT.Selection, this);

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

		availableDependentJars = new Label(listGroup, SWT.NONE);
		gData = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL);
		availableDependentJars.setText(ManifestUIResourceHandler.Available_dependent_JARs__UI_);
		availableDependentJars.setLayoutData(gData);
		createTableComposite(listGroup);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.etools.j2ee.common.ui.classpath.IClasspathTableOwner#createGroup(org.eclipse.swt.widgets.Composite)
	 */
	public Group createGroup(Composite parent) {
		return new Group(parent, SWT.NULL);
	}

	protected void createTextGroup(Composite parent) {

		Composite textGroup = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		textGroup.setLayout(layout);
		textGroup.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL));

		createClassPathText(textGroup);

	}

	protected void createClassPathText(Composite textGroup) {

		manifestLabel = new Label(textGroup, SWT.NONE);
		manifestLabel.setText(ManifestUIResourceHandler.Manifest_Class_Path__UI_);

		classPathText = new Text(textGroup, SWT.BORDER | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		GridData gData = new GridData(GridData.FILL_BOTH);
		gData.widthHint = 400;
		gData.heightHint = 100;
		classPathText.setLayoutData(gData);
		classPathText.setEditable(false);
	}

	protected void createTableComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gData);
		tableManager = new ClasspathTableManager(this, model, validateEditListener);
		tableManager.fillComposite(composite);
	}

	/**
	 * @see IClasspathTableOwner#createAvailableJARsViewer(Composite)
	 */
	public CheckboxTableViewer createAvailableJARsViewer(Composite parent) {
		int flags = SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI;

		Table table = new Table(parent, flags);
		CheckboxTableViewer availableJARsViewer = new CheckboxTableViewer(table);

		// set up table layout
		TableLayout tableLayout = new org.eclipse.jface.viewers.TableLayout();
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		tableLayout.addColumnData(new ColumnWeightData(200, true));
		table.setLayout(tableLayout);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		// do not create sorter otherwise order will go wrong
		//availableJARsViewer.setSorter(new ViewerSorter());
		
		// table columns
		TableColumn fileNameColumn = new TableColumn(table, SWT.NONE, 0);
		fileNameColumn.setText(ManifestUIResourceHandler.JAR_Module_UI_);
		fileNameColumn.setResizable(true);

		TableColumn projectColumn = new TableColumn(table, SWT.NONE, 1);
		projectColumn.setText(ManifestUIResourceHandler.Project_UI_);
		projectColumn.setResizable(true);
		tableLayout.layout(table, true);
		return availableJARsViewer;

	}

	/**
	 * @see IClasspathTableOwner#createButtonColumnComposite(Composite)
	 */
	public Composite createButtonColumnComposite(Composite parent) {
		Composite buttonColumn = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		buttonColumn.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_BEGINNING);
		buttonColumn.setLayoutData(data);
		return buttonColumn;
	}

	/**
	 * @see IClasspathTableOwner
	 */
	public Button primCreatePushButton(String label, Composite buttonColumn) {
		Button aButton = new Button(buttonColumn, SWT.PUSH);
		aButton.setText(label);
		return aButton;
	}

	/**
	 * @see IClasspathTableOwner
	 */
	public Button primCreateRadioButton(String label, Composite parent) {
		Button aButton = new Button(parent, SWT.RADIO);
		aButton.setText(label);
		return aButton;
	}

	/**
	 * @see Listener#handleEvent(Event)
	 */
	public void handleEvent(Event event) {
		if (event.widget == availableAppsCombo)
			availableAppsSelected(event);
	}

	protected void availableAppsSelected(Event event) {
		int index = availableAppsCombo.getSelectionIndex();
		model.selectEAR(index);
	}

	protected void populateApps() {
		IVirtualComponent[] components = model.getAvailableEARComponents();
		String[] values = new String[components.length];
		for (int i = 0; i < components.length; i++) {
			values[i] = components[i].getProject().getName();
		}
		if (availableAppsCombo != null) {
			availableAppsCombo.setItems(values);
			IVirtualComponent selected = model.getSelectedEARComponent();
			if (selected != null) {
				int index = Arrays.asList(components).indexOf(selected);
				availableAppsCombo.select(index);
			} else
				availableAppsCombo.clearSelection();
		}
	}

	protected void refresh() {
		populateApps();
		if (tableManager != null) {
			tableManager.refresh();
		}
		refreshText();
	}


	public void refreshText() {
		ClassPathSelection sel = model.getClassPathSelection();
		if (sel != null && classPathText != null)
			classPathText.setText(sel.toString());
	}

	/**
	 * @see ClasspathModelListener#modelChanged(ClasspathModelEvent)
	 */
	public void modelChanged(ClasspathModelEvent evt) {
		if (evt.getEventType() == ClasspathModelEvent.CLASS_PATH_CHANGED) {
			isDirty = true;
			refreshText();
		} else if (evt.getEventType() == ClasspathModelEvent.EAR_PROJECT_CHANGED) {			
			tableManager.refresh();
		}
	}

	public void performDefaults() {
		model.resetClassPathSelection();
		refresh();
		isDirty = false;
		model.dispose();
	}

	public boolean performCancel() {
		model.dispose();
		return true;
	}

	/**
	 * @see org.eclipse.jface.preference.IPreferencePage#performOk()
	 */
	public boolean performOk() {
		if (!isDirty)
			return true;
		modifyEARBundledLibs();		
		WorkspaceModifyComposedOperation composed = new WorkspaceModifyComposedOperation(createManifestOperation());
		createClasspathAttributeUpdateOperation(composed, model.getClassPathSelection(), false);
		try {
			new ProgressMonitorDialog(propPage.getShell()).run(true, true, composed);
		} catch (InvocationTargetException ex) {
			String title = ManifestUIResourceHandler.An_internal_error_occurred_ERROR_;
			String msg = title;
			if (ex.getTargetException() != null && ex.getTargetException().getMessage() != null)
				msg = ex.getTargetException().getMessage();
			MessageDialog.openError(propPage.getShell(), title, msg);
			org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ex);
			return false;
		} catch (InterruptedException e) {
			// cancelled
			return false;
		} finally {
			model.dispose();
		}
		isDirty = false;
		return true;
	}

	List getUnSelectedClassPathElementsForJ2EEDependency() {
		List unselectedForJ2EE = getUnSelectedClassPathSelection().getClasspathElements();

		List unselected = new ArrayList();
		if (model != null && model.getClassPathSelectionForWLPs() != null) {
			List wlpSelected = model.getClassPathSelectionForWLPs().getSelectedClasspathElements();

			java.util.Iterator it = unselectedForJ2EE.iterator();

			while (it.hasNext()) {
				ClasspathElement element = (ClasspathElement) it.next();
				java.util.Iterator wlpIterator = wlpSelected.iterator();
				boolean found = false;
				while (wlpIterator.hasNext()) {
					ClasspathElement wlpElement = (ClasspathElement) wlpIterator.next();
					String text = element.getText();
					int index = text.indexOf(IJ2EEModuleConstants.JAR_EXT);
					if (index != -1) {
						text = text.substring(0, index);
						if (text.equals(wlpElement.getText())) {
							found = true;
							break;
						}
					}
				}
				if (!found) {
					if (!unselected.contains(element))
						unselected.add(element);
				}

			}
		}
		return unselected;
	}


	/**
	 * @deprecated don't use this method it will be deleted
	 * @return
	 */
	List getUnSelectedClassPathElementsForWebDependency() {
		List unselectedForWLP = getUnSelectedClassPathSelectionForWLPs().getClasspathElements();
		List unselected = new ArrayList();
		if (model != null && model.getClassPathSelection() != null) {
			List j2eeSelected = model.getClassPathSelection().getSelectedClasspathElements();
			java.util.Iterator it = unselectedForWLP.iterator();

			while (it.hasNext()) {
				ClasspathElement element = (ClasspathElement) it.next();
				java.util.Iterator j2eeIterator = j2eeSelected.iterator();
				boolean found = false;
				while (j2eeIterator.hasNext()) {
					ClasspathElement j2eeElement = (ClasspathElement) j2eeIterator.next();
					String text = j2eeElement.getText();
					int index = text.indexOf(IJ2EEModuleConstants.JAR_EXT);
					if( index != -1 ){
						text = text.substring(0, index);
						if (element.getText().equals(text)) {
							found = true;
							break;
						}
					}
				}
				if (!found) {
					if (!unselected.contains(element))
						unselected.add(element);
				}

			}
		} else {
			unselected = unselectedForWLP;
		}
		return unselected;
	}

	/**
	 * DoNotUseMeThisWillBeDeletedPost15
	 * @return
	 */
	protected WorkspaceModifyComposedOperation createJ2EEComponentDependencyOperations() {
		WorkspaceModifyComposedOperation composedOp = null;
		List selected = getSelectedClassPathSelection().getClasspathElements();
		List unselected = getUnSelectedClassPathElementsForJ2EEDependency();

		List targetComponentsHandles = new ArrayList();
		for (int i = 0; i < selected.size(); i++) {
			ClasspathElement element = (ClasspathElement) selected.get(i);
			IVirtualComponent component = element.getComponent();
			if (null != component) {
				targetComponentsHandles.add(component);
			}
		}
		if (!targetComponentsHandles.isEmpty()) {
			composedOp = new WorkspaceModifyComposedOperation();
			composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(ComponentUtilities.createReferenceComponentOperation(model.getComponent(), targetComponentsHandles)));
		}
		targetComponentsHandles = new ArrayList();
		for (int i = 0; i < unselected.size(); i++) {
			ClasspathElement element = (ClasspathElement) unselected.get(i);
			IProject elementProject = element.getProject();
			if (elementProject != null) {
				if (ModuleCoreNature.isFlexibleProject(elementProject)) {
					IVirtualComponent targetComp = ComponentCore.createComponent(elementProject);
					targetComponentsHandles.add(targetComp);
				}
			} else {
				URI archiveURI = element.getArchiveURI();
				if (archiveURI != null && !archiveURI.equals("")) { //$NON-NLS-1$
					String name = ""; //$NON-NLS-1$
					try {
						String type = ModuleURIUtil.getArchiveType(archiveURI);
						String tmpname = ModuleURIUtil.getArchiveName(archiveURI);
						name = type + IPath.SEPARATOR + tmpname;
					} catch (UnresolveableURIException e) {
						Logger.getLogger().logError(e.getMessage());
					}
					if (!name.equals("")) { //$NON-NLS-1$
						IVirtualReference ref = model.getComponent().getReference(name);
						if (ref != null) {
							IVirtualComponent referenced = ref.getReferencedComponent();
							targetComponentsHandles.add(referenced);
						}
					}
				}
			}
		}
		if (!targetComponentsHandles.isEmpty()) {
			if (composedOp == null)
				composedOp = new WorkspaceModifyComposedOperation();
			composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(ComponentUtilities.removeReferenceComponentOperation(model.getComponent(), targetComponentsHandles)));
		}
		return composedOp;
	}


	/**
	 * This should be moved to the {@link WebLibDependencyPropertiesPage} because it is only used there.
	 * @return
	 */
	protected WorkspaceModifyComposedOperation createComponentDependencyOperations() {
		WorkspaceModifyComposedOperation composedOp = null;
		final ClassPathSelection selectedWLPs = getSelectedClassPathSelectionForWLPs();
		List selected = selectedWLPs.getClasspathElements();
		List unselected = getUnSelectedClassPathSelectionForWLPs().getClasspathElements();

		List targetComponentsHandles = new ArrayList();
		for (int i = 0; i < selected.size(); i++) {
			ClasspathElement element = (ClasspathElement) selected.get(i);
			if (element.isClasspathDependency() || element.isClasspathEntry()) {
				continue;
			}
			IProject elementProject = element.getProject();
			if (elementProject != null) {
				IVirtualComponent targetComp = ComponentCore.createComponent(elementProject);
				targetComponentsHandles.add(targetComp);
			}
		}
		if (!targetComponentsHandles.isEmpty()) {
			composedOp = new WorkspaceModifyComposedOperation();
			composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(ComponentUtilities.createWLPReferenceComponentOperation(model.getComponent(), targetComponentsHandles)));
		}
		targetComponentsHandles = new ArrayList();
		for (int i = 0; i < unselected.size(); i++) {
			ClasspathElement element = (ClasspathElement) unselected.get(i);
			if (element.isClasspathDependency() || element.isClasspathEntry()) {
				continue;
			}
			IProject elementProject = element.getProject();
			if (elementProject != null) {
				if (ModuleCoreNature.isFlexibleProject(elementProject)) {
					IVirtualComponent targetComp = ComponentCore.createComponent(elementProject);
					targetComponentsHandles.add(targetComp);
				}
			} else {
				URI archiveURI = element.getArchiveURI();
				if (archiveURI != null && !archiveURI.equals("")) { //$NON-NLS-1$
					String name = ""; //$NON-NLS-1$
					try {
						String type = ModuleURIUtil.getArchiveType(archiveURI);
						String tmpname = ModuleURIUtil.getArchiveName(archiveURI);
						name = type + IPath.SEPARATOR + tmpname;
					} catch (UnresolveableURIException e) {
						Logger.getLogger().logError(e.getMessage());
					}
					if (!name.equals("")) { //$NON-NLS-1$
						IVirtualReference ref = model.getComponent().getReference(name);
						IVirtualComponent referenced = ref.getReferencedComponent();
						targetComponentsHandles.add(referenced);
					}
				}
			}
		}
		if (!targetComponentsHandles.isEmpty()) {
			if (composedOp == null) {
				composedOp = new WorkspaceModifyComposedOperation();
			}
			composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(ComponentUtilities.removeWLPReferenceComponentOperation(model.getComponent(), targetComponentsHandles)));
		}

		if (composedOp == null) {
			composedOp = new WorkspaceModifyComposedOperation();
		}
		createClasspathAttributeUpdateOperation(composedOp, model.getClassPathSelectionForWLPs(), true);
		
		return composedOp;
	}

	protected WorkspaceModifyComposedOperation createFlexProjectOperations() {
		WorkspaceModifyComposedOperation composedOp = null;
		Object[] elements = tableManager.availableJARsViewer.getCheckedElements();
		for (int i = 0; i < elements.length; i++) {
			ClasspathElement element = (ClasspathElement) elements[i];
			IProject elementProject = element.getProject();
			try {
				if (elementProject != null && !elementProject.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
					if (composedOp == null) {
						composedOp = new WorkspaceModifyComposedOperation();
					}
					composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(J2EEProjectUtilities.createFlexJavaProjectForProjectOperation(elementProject, false)));
				}
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
			}
		}
		return composedOp;
	}

	protected ClassPathSelection getUnSelectedClassPathSelectionForWLPs() {
		ClassPathSelection selection = new ClassPathSelection();
		Object[] checkedElements = tableManager.availableJARsViewer.getCheckedElements();
		List modelElements = model.getClassPathSelectionForWLPs().getClasspathElements();
		for (int i = 0; i < modelElements.size(); i++) {
			List checkedElementsList = Arrays.asList(checkedElements);
			if (!checkedElementsList.contains(modelElements.get(i))) {
				selection.getClasspathElements().add(modelElements.get(i));
			}
		}
		return selection;
	}

	private ClassPathSelection getSelectedClassPathSelection() {
		ClassPathSelection selection = new ClassPathSelection();
		Object[] checkedElements = tableManager.availableJARsViewer.getCheckedElements();
		for (int i = 0; i < checkedElements.length; i++) {
			selection.getClasspathElements().add(checkedElements[i]);
		}
		return selection;
	}

	protected ClassPathSelection getUnSelectedClassPathSelection() {
		ClassPathSelection selection = new ClassPathSelection();
		Object[] checkedElements = tableManager.availableJARsViewer.getCheckedElements();
		List modelElements = model.getClassPathSelection().getClasspathElements();
		for (int i = 0; i < modelElements.size(); i++) {
			List checkedElementsList = Arrays.asList(checkedElements);
			if (!checkedElementsList.contains(modelElements.get(i))) {
				selection.getClasspathElements().add(modelElements.get(i));
			}
		}
		return selection;
	}


	private ClassPathSelection getSelectedClassPathSelectionForWLPs() {
		ClassPathSelection selection = new ClassPathSelection();
		Object[] checkedElements = tableManager.availableJARsViewer.getCheckedElements();
		for (int i = 0; i < checkedElements.length; i++) {
			selection.getClasspathElements().add(checkedElements[i]);
		}
		return selection;
	}

	protected UpdateManifestOperation createManifestOperation() {
		return new UpdateManifestOperation(project.getName(), model.getClassPathSelection().toString(), true);
	}
	
	protected void createClasspathAttributeUpdateOperation(final WorkspaceModifyComposedOperation composedOp, final ClassPathSelection selection, final boolean isWebApp) {
		final boolean webLibsOnly = isWebApp && !ClasspathDependencyEnablement.isAllowClasspathComponentDependency();
		final Map selectedEntriesToRuntimePath = new HashMap();
		final Map unselectedEntriesToRuntimePath = new HashMap();
		final List elements = selection.getClasspathElements();
		for (int i = 0; i < elements.size(); i++) {
			final ClasspathElement element = (ClasspathElement) elements.get(i);
			if (element.isClasspathEntry()) {
				final IClasspathEntry entry = element.getClasspathEntry();
				final IClasspathAttribute attrib = ClasspathDependencyUtil.checkForComponentDependencyAttribute(entry, DependencyAttributeType.DEPENDENCY_OR_NONDEPENDENCY, webLibsOnly);
				boolean hasDepAttrib = false;
				if (attrib != null && attrib.getName().equals(IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY)) {
					hasDepAttrib = true;
				}
				final IPath runtimePath = ClasspathDependencyUtil.getRuntimePath(attrib, isWebApp, ClasspathDependencyUtil.isClassFolderEntry(entry));
				if (element.isSelected()) {
					// only add if we don't already have the attribute
					if (!hasDepAttrib) {
						selectedEntriesToRuntimePath.put(entry, runtimePath);
					}
				} else {
					// only add if we already have the attribute
					if (hasDepAttrib) {
						unselectedEntriesToRuntimePath.put(entry, runtimePath);
					}
				}
			}
		}
		
		// if there are any attributes to add, create an operation to add all necessary attributes 
		if (!selectedEntriesToRuntimePath.isEmpty()) {
			IDataModelOperation op = UpdateClasspathAttributeUtil.createAddDependencyAttributesOperation(project.getName(), selectedEntriesToRuntimePath); 
			composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(op));
		}
		// if there are any attributes to remove, create an operation to remove all necessary attributes
		if (!unselectedEntriesToRuntimePath.isEmpty()) {
			IDataModelOperation op = UpdateClasspathAttributeUtil.createRemoveDependencyAttributesOperation(project.getName(), unselectedEntriesToRuntimePath); 
			composedOp.addRunnable(WTPUIPlugin.getRunnableWithProgress(op));
		}
	}

	protected boolean isReadOnly() {
		return JemProjectUtilities.isBinaryProject(project) && (project.findMember(IModuleConstants.COMPONENT_FILE_PATH) == null) ;
	}
	
	protected void modifyEARBundledLibs() {
		List compsToUncheckList = tableManager.getCheckedLibsAsList();
		if (compsToUncheckList.size() == 0) return;
		Set allCompsToUncheck = new HashSet();
		Set allCompNamesToUncheck = new HashSet();		
		for (int i = 0; i < compsToUncheckList.size(); i++) {
			IVirtualComponent comp = (IVirtualComponent)compsToUncheckList.get(i);
			allCompsToUncheck.add(comp);
			if (comp instanceof J2EEModuleVirtualArchiveComponent) allCompNamesToUncheck.add(comp.getName());
		}
 		IProject[] ears = EarUtilities.getReferencingEARProjects(project);
		for (int i = 0; i < ears.length; i++) {
			if (J2EEProjectUtilities.isJEEProject(ears[i])) {	
				IVirtualComponent earComponent = ComponentCore.createComponent(ears[i]);
				List listToUncheck = createListToUncheck(earComponent, allCompsToUncheck, allCompNamesToUncheck);
				if (listToUncheck.size() == 0) continue;
				removeModulesFromEAR(new NullProgressMonitor(), earComponent, listToUncheck);
				addModulesToEAR(new NullProgressMonitor(), earComponent, listToUncheck);
			}
		}			
	}
	//---------------------------------
	private IStatus removeModulesFromEAR(IProgressMonitor monitor, final IVirtualComponent earComponent, final List compsToUncheckList) {
		IStatus stat = IDataModelProvider.OK_STATUS;
		String libDir = ((Application)ModelProviderManager.getModelProvider(earComponent.getProject()).getModelObject()).getLibraryDirectory();
		libDir = (libDir == null) ? J2EEConstants.EAR_DEFAULT_LIB_DIR : libDir;
		Map dependentComps = getEARModuleDependencies(earComponent, compsToUncheckList);
		try {
			IDataModelOperation op = removeComponentFromEAROperation(earComponent, compsToUncheckList, libDir);
			op.execute(null, null);
			J2EEComponentClasspathUpdater.getInstance().queueUpdateEAR(earComponent.getProject());
			removeEARComponentDependencies(dependentComps);
		} catch (ExecutionException e) {
			Logger.getLogger().log(e);
		}
		return stat;
	}		
	
	
	private IStatus addModulesToEAR(IProgressMonitor monitor, final IVirtualComponent earComponent, final List compsToUncheckList) {
		try {
			IWorkspaceRunnable runnable = new IWorkspaceRunnable(){
				public void run(IProgressMonitor monitor) throws CoreException{
					execAddOp(monitor, compsToUncheckList, J2EEConstants.EAR_ROOT_DIR, earComponent);
				}
			};
			J2EEUIPlugin.getWorkspace().run(runnable, monitor);
		} catch (Exception e) {
			Logger.getLogger().log(e);
		}
		return IDataModelProvider.OK_STATUS;
	}

	private List createListToUncheck(IVirtualComponent earComponent, Set compsToUncheckList, Set compNamesToUncheck) {	
		LinkedList res = new LinkedList();
		IVirtualReference[] refs = earComponent.getReferences();
		for (int j = 0; j < refs.length; j++) {
			if (!refs[j].getRuntimePath().isRoot() &&
					(compsToUncheckList.contains(refs[j].getReferencedComponent()) ||
					compNamesToUncheck.contains(refs[j].getReferencedComponent().getName()))) {
				res.add(refs[j].getReferencedComponent());
			}		
		}
		return res;
	}	
		
	private void execAddOp(IProgressMonitor monitor, List list, String path, IVirtualComponent earComponent) throws CoreException {
		IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
		
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComponent);					
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, list);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, path);

		IStatus stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		if (stat != IDataModelProvider.OK_STATUS)
			throw new CoreException(stat);
		try {
			dm.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().log(e);
		}		
	}
		
	
	protected IDataModelOperation removeComponentFromEAROperation(IVirtualComponent sourceComponent, List targetComponentsHandles, String dir) {
		IDataModel model = DataModelFactory.createDataModel(new RemoveComponentFromEnterpriseApplicationDataModelProvider());
		model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, sourceComponent);
		List modHandlesList = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		modHandlesList.addAll(targetComponentsHandles);
		model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modHandlesList);
        model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_DEPLOY_PATH, dir);		
		return model.getDefaultOperation();
	}
	
	private Map getEARModuleDependencies(final IVirtualComponent earComponent, final List components) {
		final Map dependentComps = new HashMap();
		// get all current references to project within the scope of this EAR
		for (int i = 0; i < components.size(); i++) {
			
			final List compsForProject = new ArrayList();
			final IVirtualComponent comp = (IVirtualComponent) components.get(i);
			Set<IProject> referencingComponents = IDependencyGraph.INSTANCE.getReferencingComponents(project);
			IProject [] dependentProjects = referencingComponents.toArray(new IProject[referencingComponents.size()]);
			for (int j = 0; j < dependentProjects.length; j++) {
				final IProject project = dependentProjects[j];
				// if this is an EAR, can skip
				if (JavaEEProjectUtilities.isEARProject(project)) {
					continue;
				}
				final IVirtualComponent dependentComp = ComponentCore.createComponent(project);
				// ensure that the project's share an EAR
				final IProject[] refEARs = EarUtilities.getReferencingEARProjects(project);
				boolean sameEAR = false;
				for (int k = 0; k < refEARs.length; k++) {
					if (refEARs[k].equals(earComponent.getProject())) {
						sameEAR = true;
						break;
					}
				}
				if (!sameEAR) {
					continue;
				}
				// if the dependency is a web lib dependency, can skip
				if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
					IVirtualReference ref = dependentComp.getReference(comp.getName());
					if (ref != null && ref.getRuntimePath().equals(new Path("/WEB-INF/lib"))) { //$NON-NLS-1$
						continue;
					}
				}
				compsForProject.add(dependentComp);
			}
			dependentComps.put(comp, compsForProject);
		}
		return dependentComps;
	}
	
	private void removeEARComponentDependencies(final Map dependentComps) throws ExecutionException {
		final Iterator targets = dependentComps.keySet().iterator();
		while (targets.hasNext()) {
			final IVirtualComponent target = (IVirtualComponent) targets.next();
			final List sources = (List) dependentComps.get(target);
			for (int i = 0; i < sources.size(); i++) {
				final IVirtualComponent source = (IVirtualComponent) sources.get(i);
				final IDataModel model = DataModelFactory.createDataModel(new RemoveReferenceComponentsDataModelProvider());
				model.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, source);
				final List modHandlesList = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				modHandlesList.add(target);
				model.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modHandlesList);
				model.getDefaultOperation().execute(null, null);
				// update the manifest
				removeManifestDependency(source, target);
			}
		}
	}

	private void removeManifestDependency(final IVirtualComponent source, final IVirtualComponent target) 
		throws ExecutionException {
		final String sourceProjName = source.getProject().getName();
		final String targetProjName = target.getProject().getName();
		final IProgressMonitor monitor = new NullProgressMonitor();
		final IFile manifestmf = J2EEProjectUtilities.getManifestFile(source.getProject());
		final ArchiveManifest mf = J2EEProjectUtilities.readManifest(source.getProject());
		if (mf == null)
			return;
		final IDataModel updateManifestDataModel = DataModelFactory.createDataModel(new UpdateManifestDataModelProvider());
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, sourceProjName);
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
		String[] cp = mf.getClassPathTokenized();
		List cpList = new ArrayList();
		String cpToRemove = targetProjName + IJ2EEModuleConstants.JAR_EXT;
		for (int i = 0; i < cp.length; i++) {
			if (!cp[i].equals(cpToRemove)) {
				cpList.add(cp[i]);
			}
		}
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, cpList);
		updateManifestDataModel.getDefaultOperation().execute(monitor, null );
	}
	
}
