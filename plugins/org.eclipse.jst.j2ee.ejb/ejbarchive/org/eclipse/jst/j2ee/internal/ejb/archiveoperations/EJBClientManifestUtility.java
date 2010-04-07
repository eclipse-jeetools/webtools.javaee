package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.internal.modulecore.util.ArchiveManifest;
import org.eclipse.jst.common.internal.modulecore.util.ManifestUtilities;
import org.eclipse.jst.j2ee.application.internal.operations.ClassPathSelection;
import org.eclipse.jst.j2ee.application.internal.operations.ClasspathElement;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * This class was basically written to clean up the core remaining use-cases
 * for some deprecated classes.
 * 
 */
public class EJBClientManifestUtility {
	public static final String NESTED_MODEL_UPDATE_MAINFEST = "AbstractJARDependencyDataModel.NESTED_MODEL_UPDATE_MAINFEST"; //$NON-NLS-1$
	public static final int JAR_MANIPULATION_ADD = 0;
	public static final int JAR_MANIPULATION_REMOVE = 1;
	public static final int JAR_MANIPULATION_INVERT = 2;

	
	private ClassPathSelection classPathSelection;
	private ArchiveManifest manifest;
	private IProject project, earProject, referencedProject, oppositeProject;
	private List jarList;
	public EJBClientManifestUtility() {
	}

	public UpdateManifestOperation getAddOperation(IProject project, IProject ear, List jars) {
		return handle(JAR_MANIPULATION_ADD, project, ear, jars);
	}

	public UpdateManifestOperation getRemoveOperation(IProject project, IProject ear, List jars, IProject referenced) {
		return handle(JAR_MANIPULATION_REMOVE, project, ear, jars, null, referenced);
	}
	
	public UpdateManifestOperation getInvertOperation(IProject project, IProject ear, 
			IProject opposite, IProject referenced) {
		return handle(JAR_MANIPULATION_INVERT, project, ear, null, opposite, referenced);
	}

	private UpdateManifestOperation handle(int type, IProject project, IProject ear, List jars) {
		return handle(type, project, ear, jars, null, null);
	}
	
	private UpdateManifestOperation handle(int type, IProject project, IProject ear, List jars, IProject opposite, IProject referenced) {
		this.project = project;
		this.earProject = ear;
		this.jarList = jars;
		this.oppositeProject = opposite;
		this.referencedProject = referenced;
		IVirtualComponent earComponent = ComponentCore.createComponent( earProject );
		if( !earComponent.exists() )
			return null;
		IVirtualComponent component = ComponentCore.createComponent(project);
		manifest = ManifestUtilities.getNonBinaryComponentManifest(component, new Path(J2EEConstants.MANIFEST_URI));
		classPathSelection = new ClassPathSelection(component, earComponent, manifest);
		switch (type) {
			case JAR_MANIPULATION_ADD : handleAdd(); break;
			case JAR_MANIPULATION_REMOVE : handleRemove(); break;
			case JAR_MANIPULATION_INVERT : handleInvert(); break;
		}
		try {
			validateEdit();
			UpdateManifestOperation mfOperation = createManifestOperation();
			return mfOperation;
		} catch( CoreException ce ) {
			return null;
		}
	}
	

	/**
	 * @param model
	 */
	protected void validateEdit() throws CoreException {
		Set affectedFiles = getAffectedFiles(project);
		IFile[] files = (IFile[]) affectedFiles.toArray(new IFile[affectedFiles.size()]);
		IStatus result = J2EEPlugin.getWorkspace().validateEdit(files, null);
		if (!result.isOK())
			throw new CoreException(result);
	}
	
	/** 
	 * This is basically just .project, .classpath, and .manifest
	 * @param project
	 * @return
	 */
	public Set getAffectedFiles(IProject project) {
		Set result = new HashSet();
		IFile aFile = J2EEProjectUtilities.getManifestFile(project);
		if (aFile != null && aFile.exists())
			result.add(aFile);
		result.addAll(ProjectUtilities.getFilesAffectedByClasspathChange(project));
		return result;
	}


	/*
	 * Create the manifest operation
	 */
	private UpdateManifestOperation createManifestOperation() {
		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, project.getName());
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, UpdateManifestDataModelProvider.convertClasspathStringToList(classPathSelection.toString()));
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, J2EEProjectUtilities.getManifestFile( project));
		return new UpdateManifestOperation(updateManifestDataModel);
	}
	
	protected void handleAdd() {
		if (!jarList.isEmpty()) {
			for (int i = 0; i < jarList.size(); i++) {
				String jarName = (String) jarList.get(i);
				selectDependencyIfNecessary(jarName);
			}
		}
	}
	
	protected void handleRemove() {
		if( !jarList.isEmpty() ) {
			for (int i = 0; i < jarList.size(); i++) {
				String jarName = (String) jarList.get(i);
				removeDependency(jarName);
			}
		} else {
			
		}
	}

	protected void handleInvert() {
		classPathSelection.invertClientJARSelection( referencedProject, oppositeProject);
	}
	
	protected void setSelection(ClasspathElement element, boolean val) {
		element.setSelected(val);
		manifest.setClassPath(classPathSelection.toString());
	}
	
	public boolean selectDependencyIfNecessary(IProject referencedProject) {
		if (classPathSelection == null || classPathSelection.hasDirectOrIndirectDependencyTo(referencedProject))
			return false;
		ClasspathElement element = classPathSelection.getClasspathElement(referencedProject);
		if (element != null) {
			setSelection(element, true);
			return true;
		}
		return false;
	}

	public boolean selectDependencyIfNecessary(String jarName) {
		if (classPathSelection == null || classPathSelection.hasDirectOrIndirectDependencyTo(jarName))
			return false;
		ClasspathElement element = classPathSelection.getClasspathElement(jarName);
		if (element != null) {
			setSelection(element, true);
			return true;
		}
		return false;
	}

	public void removeDependency(String jarName) {
		if (classPathSelection == null)
			return;
		ClasspathElement element = classPathSelection.getClasspathElement(jarName);
		if (element != null && element.isValid())
			setSelection(element, false);
	}

	public void removeDependency(IProject referencedProject) {
		if (classPathSelection == null)
			return;
		ClasspathElement element = classPathSelection.getClasspathElement(referencedProject);
		if (element != null && element.isValid())
			setSelection(element, false);
	}
}
