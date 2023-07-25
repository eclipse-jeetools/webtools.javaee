/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *	   David Schneider, david.schneider@unisys.com - [142500] WTP properties pages fonts don't follow Eclipse preferences
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.BuildPathDialogAccess;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jst.j2ee.application.internal.operations.ClasspathElement;
import org.eclipse.jst.j2ee.internal.common.ClasspathModel;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.internal.ui.WorkspaceModifyComposedOperation;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

/**
 * @deprecated wtp 3.2
 */
public class WebLibDependencyPropertiesPage extends JARDependencyPropertiesPage {

	public WebLibDependencyPropertiesPage(final IProject project, final J2EEDependenciesPage page) {
		super(project, page);
	}

	@Override
	protected ClasspathModel createClasspathModel() {
		return new ClasspathModel(null, true);
	}
	
	@Override
	public Composite createContents(Composite parent) {
		initialize();
		Composite composite = createBasicComposite(parent);
		if (model.getComponent() != null) {
			if (!isValidWebModule())
				return composite;
			J2EEDependenciesPage.createDescriptionComposite(composite, ManifestUIResourceHandler.Web_Libraries_Desc);
			// createProjectLabelsGroup(composite);
			createListGroup(composite);
			handleWLPSupport();
			setEnablement();
		}
	    Dialog.applyDialogFont(parent);
	    postCreateContents();
		return composite;
	}

	@Override
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

	@Override
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

	@Override
	protected void createTableComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridData gData = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(gData);
		tableManager = new ClasspathTableManager(this, model, validateEditListener);
		tableManager.fillWLPComposite(composite);
	}

	protected boolean isValidWebModule() {
		if (!JavaEEProjectUtilities.isDynamicWebProject(project)) {
			propPage.setErrorMessage(ManifestUIResourceHandler.Web_Lib_Error);
			return false;
		}
		return true;
	}
	private boolean equals(final Object obj1, final Object obj2) {
		if (obj1 == obj2) {
			return true;
		} else if (obj1 == null || obj2 == null) {
			return false;
		} else {
			return obj1.equals(obj2);
		}
	}

	protected void setEnablement() {
		if (tableManager.availableJARsViewer.getTable().getItems().length == 0) {
			tableManager.selectAllButton.setEnabled(false);
			tableManager.deselectAllButton.setEnabled(false);
		} else {
			tableManager.selectAllButton.setEnabled(true);
			tableManager.deselectAllButton.setEnabled(true);
		}
	}
	private IProgressMonitor submon(final IProgressMonitor parent, final int ticks) {
		return (parent == null ? null : new SubProgressMonitor(parent, ticks));
	}

	private void handleWLPSupport() {
		availableDependentJars.setText(ManifestUIResourceHandler.WEB_LIB_LIST_DESCRIPTION);
		tableManager.refresh();
	}
	
	@Override
	protected void handleClasspathChange() {
		model.resetClassPathSelectionForWLPs();
		super.handleClasspathChange();
		setEnablement();
	}

	@Override
	public boolean performOk() {
		if (model.getComponent() == null || !isValidWebModule()) {
			return true;
		}
		if (!isDirty) {
			return true;
		}
		try {
			IProject[] javaProjects = getJavaProjectsWithoutFacets();
			boolean createdFlexProjects = runWLPOp(createFlexProjectOperations());
			
			boolean createdComponentDependency = false;
			if (createdFlexProjects) {
				addTargetRuntimesToNewJavaProjects(javaProjects,new NullProgressMonitor());
				createdComponentDependency = runWLPOp(createComponentDependencyOperations());
				isDirty = false;
			}
			// treat as a classpath change for refresh purposes
			// XXX this refresh is not working - suspect it is because the virtual component dependencies are
			// not consistently being recomputed
			//handleClasspathChange();
			return createdComponentDependency;
		} finally {
			model.dispose();
		}
	}

	private IProject[] getJavaProjectsWithoutFacets() {
		List projectsList = new ArrayList();
		Object[] elements = tableManager.availableJARsViewer.getCheckedElements();
		for (int i = 0; i < elements.length; i++) {
			ClasspathElement element = (ClasspathElement) elements[i];
			IProject elementProject = element.getProject();
			try {
				if (elementProject != null && !elementProject.hasNature(IModuleConstants.MODULE_NATURE_ID))
					projectsList.add(elementProject);
			} catch (CoreException e) {
				J2EEPlugin.logError(e);
			}
		}
		return (IProject[])projectsList.toArray(new IProject[projectsList.size()]);
	}

	private boolean runWLPOp(WorkspaceModifyComposedOperation composed) {
		try {
			if (composed != null)
				new ProgressMonitorDialog(propPage.getShell()).run(true, true, composed);
		} catch (InvocationTargetException ex) {
			if (ex.getCause() != null) {
				J2EEUIPlugin.logError(ex.getCause());
			}
			String title = ManifestUIResourceHandler.An_internal_error_occurred_ERROR_;
			String msg = title;
			if (ex.getTargetException() != null && ex.getTargetException().getMessage() != null)
				msg = ex.getTargetException().getMessage();
			MessageDialog.openError(propPage.getShell(), title, msg);
			J2EEUIPlugin.logError(ex);
			return false;
		} catch (InterruptedException e) {
			// cancelled
			return false;
		}
		return true;
	}

	private void addTargetRuntimesToNewJavaProjects(IProject[] javaProjects, final IProgressMonitor monitor )

	{
		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}
		IProject targetProject = project;
		for (int i = 0; i < javaProjects.length; i++) {
			IProject moduleProject = javaProjects[i];

			try {
				final IFacetedProject targetFacetedProject = ProjectFacetsManager.create(targetProject);

				final IRuntime targetRuntime = targetFacetedProject.getRuntime();

				final IFacetedProject moduleFacetedProject = ProjectFacetsManager.create(moduleProject);

				if (moduleFacetedProject != null && !equals(targetRuntime, moduleFacetedProject.getRuntime())) {
					boolean supports = true;

					if (targetRuntime != null) {
						for (Iterator itr = moduleFacetedProject.getProjectFacets().iterator(); itr.hasNext();) {
							final IProjectFacetVersion fver = (IProjectFacetVersion) itr.next();

							if (!targetRuntime.supports(fver)) {
								supports = false;
								break;
							}
						}
					}

					if (supports) {
						moduleFacetedProject.setRuntime(targetRuntime, submon(monitor, 1));
					}
				}
			} catch(CoreException ex) {
				J2EEPlugin.logError(ex);
			} finally {
				if (monitor != null) {
					monitor.done();
				}
			}
		}
	}

	private void createRef(String aComponentName){
		IVirtualComponent archive = ComponentCore.createArchiveComponent(model.getComponent().getProject(), aComponentName);

		// To do: check if archive component already exists
		IVirtualReference ref = ComponentCore.createReference(model.getComponent(), archive, new Path("/WEB-INF/lib")); //$NON-NLS-1$
		model.getComponent().addReferences(new IVirtualReference [] { ref });

		ClasspathElement element = createClassPathElement(archive, archive.getName());
//		ClassPathSelection selection = createClassPathSelectionForExternalJar(element);
		model.getClassPathSelectionForWLPs().getClasspathElements().add(element);
	}
	
	public void handleSelectExternalJarButton() {
		if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
			IPath[] selected = BuildPathDialogAccess.chooseExternalJAREntries(propPage.getShell());
			if (selected != null) {
				String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
				for (int i = 0; i < selected.length; i++) {
					createRef(type + selected[i].toString());
				}
				refresh();
			}
		}
	}

	public void handleSelectVariableButton() {
		if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
			IPath existingPath[] = new Path[0];
			IPath[] selected = BuildPathDialogAccess.chooseVariableEntries(propPage.getShell(), existingPath);

			if (selected != null) {
				String type = VirtualArchiveComponent.VARARCHIVETYPE + IPath.SEPARATOR;
				for (int i = 0; i < selected.length; i++) {
					IPath resolvedPath = JavaCore.getResolvedVariablePath(selected[i]);
					java.io.File file = new java.io.File(resolvedPath.toOSString());
					if (file.isFile() && file.exists()) {
						createRef(type + selected[i].toString());
					} else {
						// display error
					}
				}
				refresh();
			}
		}
	}

	private ClasspathElement createClassPathElement(IVirtualComponent archiveComp, String unresolvedName) {

		URI uri = URI.createURI(ModuleURIUtil.getHandleString(archiveComp));
		ClasspathElement element = new ClasspathElement(uri);
		element.setValid(false);
		element.setSelected(true);
		element.setRelativeText(unresolvedName);
		element.setText(unresolvedName);
		element.setEarProject(null);
		return element;
	}

//	private ClassPathSelection createClassPathSelectionForExternalJar(ClasspathElement element) {
//		ClassPathSelection selection = new ClassPathSelection();
//		selection.getClasspathElements().add(element);
//		return selection;
//	}

//	private ClassPathSelection createClassPathSelectionForProjectJar(ClasspathElement element) {
//		ClassPathSelection selection = new ClassPathSelection();
//		selection.getClasspathElements().add(element);
//		return selection;
//	}

	public void handleSelectProjectJarButton() {
		if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
			IPath[] selected = BuildPathDialogAccess.chooseJAREntries(propPage.getShell(), project.getLocation(), new IPath[0]);
			if (selected != null) {
				String type = VirtualArchiveComponent.LIBARCHIVETYPE + IPath.SEPARATOR;
				for (int i = 0; i < selected.length; i++) {
					createRef(type + selected[i].makeRelative().toString());
				}
				refresh();
			}
		}

	}
}
