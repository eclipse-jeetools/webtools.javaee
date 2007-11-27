package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jdt.core.*;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsController;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager;
import org.eclipse.jst.common.internal.annotations.controller.AnnotationsControllerManager.Descriptor;
import org.eclipse.jst.j2ee.application.internal.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.enablement.nonui.WFTWrappedException;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * The NewFilterClassOperation is an IDataModelOperation following the
 * IDataModel wizard and operation framework.
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * 
 * It extends ArtifactEditProviderOperation to provide filter specific java
 * class generation.
 * @see org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditProviderOperation
 * 
 * This operation is used by the AddFilterOperation to generate an
 * non annotated java class for an added filter. It shares the
 * NewFilterClassDataModelProvider with the AddFilterOperation to store the
 * appropriate properties required to generate the new filter.
 * @see org.eclipse.jst.j2ee.internal.web.operations.AddFilterOperation
 * @see org.eclipse.jst.j2ee.internal.web.operations.NewFilterClassDataModelProvider
 * 
 * A WTPJetEmitter filter template is used to create the class with the filter template. 
 * @see org.eclipse.jst.j2ee.internal.project.WTPJETEmitter
 * @see org.eclipse.jst.j2ee.internal.web.operations.CreateFilterTemplateModel
 * 
 * Subclasses may extend this operation to provide their own specific filter
 * java class generation. The execute method may be extended to do so. Also,
 * generateUsingTemplates is exposed.
 * 
 * The use of this class is EXPERIMENTAL and is subject to substantial changes.
 */
public class NewFilterClassOperation extends AbstractDataModelOperation {

	/**
	 * XDoclet facet constants
	 */
	private static final String JST_WEB_XDOCLET_VERSION = "1.2.3"; //$NON-NLS-1$

	/**
	 * The extension name for a java class
	 */
	private static final String DOT_JAVA = ".java"; //$NON-NLS-1$

	/**
	 * variable for the web plugin
	 */
	protected static final String WEB_PLUGIN = "WEB_PLUGIN"; //$NON-NLS-1$

	/**
	 * folder location of the filter creation templates diretory
	 */
	protected static final String TEMPLATE_FILE = "/templates/filter.javajet"; //$NON-NLS-1$

	/**
	 * name of the template emitter to be used to generate the deployment
	 * descriptor from the tags
	 */
	protected static final String TEMPLATE_EMITTER = "org.eclipse.jst.j2ee.ejb.annotations.emitter.template"; //$NON-NLS-1$

	/**
	 * id of the builder used to kick off generation of web metadata based on
	 * parsing of annotations
	 */
	protected static final String BUILDER_ID = "builderId"; //$NON-NLS-1$

	/**
	 * This is the constructor which should be used when creating a
	 * NewFilterClassOperation. An instance of the NewFilterClassDataModel
	 * should be passed in. This does not accept null parameter. It will not
	 * return null.
	 * 
	 * @see ArtifactEditProviderOperation#ArtifactEditProviderOperation(IDataModel)
	 * @see NewFilterClassDataModel
	 * 
	 * @param dataModel
	 * @return NewFilterClassOperation
	 */
	public NewFilterClassOperation(IDataModel dataModel) {
		super(dataModel);
	}

	/**
	 * Subclasses may extend this method to add their own actions during
	 * execution. The implementation of the execute method drives the running of
	 * the operation. This implementation will create the java source folder,
	 * create the java package, and then the filter java class file will be created 
	 * using templates. Optionally, subclasses may extend the
	 * generateUsingTemplates or createJavaFile method rather than extend the
	 * execute method. This method will accept a null parameter.
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 * @see NewFilterClassOperation#generateUsingTemplates(IProgressMonitor,
	 *      IPackageFragment)
	 * 
	 * @param monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 */
	public IStatus doExecute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// Create source folder if it does not exist
		createJavaSourceFolder();
		// Create java package if it does not exist
		IPackageFragment pack = createJavaPackage();
		// Generate filter class using templates
		try {
			generateUsingTemplates(monitor, pack);
		} catch (Exception e) {
			return WTPCommonPlugin.createErrorStatus(e.toString());
		}
		return OK_STATUS;
	}

	/**
	 * This method will return the java package as specified by the new java
	 * class data model. If the package does not exist, it will create the
	 * package. This method should not return null.
	 * 
	 * @see INewJavaClassDataModelProperties#JAVA_PACKAGE
	 * @see IPackageFragmentRoot#createPackageFragment(java.lang.String,
	 *      boolean, org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IPackageFragment the java package
	 */
	protected final IPackageFragment createJavaPackage() {
		// Retrieve the package name from the java class data model
		String packageName = model.getStringProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE);
		IPackageFragmentRoot packRoot = (IPackageFragmentRoot) model
				.getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE_FRAGMENT_ROOT);
		IPackageFragment pack = packRoot.getPackageFragment(packageName);
		// Handle default package
		if (pack == null) {
			pack = packRoot.getPackageFragment(""); //$NON-NLS-1$
		}
		
		
		// Create the package fragment if it does not exist
		if (!pack.exists()) {
			String packName = pack.getElementName();
			try {
				pack = packRoot.createPackageFragment(packName, true, null);
			} catch (JavaModelException e) {
				Logger.getLogger().log(e);
			}
		}
		// Return the package
		return pack;
	}

	/**
	 * Subclasses may extend this method to provide their own template based
	 * creation of an annotated filter java class file. This implementation
	 * uses the creation of a CreateFilterTemplateModel and the WTPJetEmitter
	 * to create the java class with the annotated tags. This method accepts
	 * null for monitor, it does not accept null for fragment. If annotations
	 * are not being used the tags will be omitted from the class.
	 * 
	 * @see CreateFilterTemplateModel
	 * @see NewFilterClassOperation#generateTemplateSource(CreateFilterTemplateModel,
	 *      IProgressMonitor)
	 * 
	 * @param monitor
	 * @param fragment
	 * @throws CoreException
	 * @throws WFTWrappedException
	 */
	protected void generateUsingTemplates(IProgressMonitor monitor, IPackageFragment fragment) throws WFTWrappedException, CoreException {
	    // Create the filter template model
	    CreateFilterTemplateModel tempModel = createTemplateModel();
        IProject project = getTargetProject();
        String source;
        // Using the WTPJetEmitter, generate the java source based on the filter template model
        try {
            source = generateTemplateSource(tempModel, monitor);
        } catch (Exception e) {
            throw new WFTWrappedException(e);
        }
        if (fragment != null) {
            // Create the java file
            String javaFileName = tempModel.getClassName() + DOT_JAVA;
            ICompilationUnit cu = fragment.getCompilationUnit(javaFileName);
            // Add the compilation unit to the java file
            if (cu == null || !cu.exists())
                cu = fragment.createCompilationUnit(javaFileName, source, true, monitor);
            IFile aFile = (IFile) cu.getResource();
            // Let the annotations controller process the annotated resource
            AnnotationsController controller = AnnotationsControllerManager.INSTANCE.getAnnotationsController(project);
            if (controller != null)
                controller.process(aFile);
        }
	}
	


	/**
	 * This method is intended for internal use only. This will add an webdoclet facet to the project.
	 * 
	 * @throws CoreException 
	 * @throws ExecutionException 
	 */
	private void installXDocletFacet(IProgressMonitor monitor, IProject project) throws CoreException, ExecutionException {
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		Set fixedFacets = facetedProject.getFixedProjectFacets();
		IDataModel dm = DataModelFactory.createDataModel(new FacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_ID, IJ2EEFacetConstants.DYNAMIC_WEB_XDOCLET);
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, project.getName());
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, JST_WEB_XDOCLET_VERSION);
		IDataModel fdm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		fdm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, project.getName());

		FacetDataModelMap map = (FacetDataModelMap) fdm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		map.add(dm);

		fdm.getDefaultOperation().execute(monitor, null);
		facetedProject.setFixedProjectFacets(fixedFacets);
	}
	
	/**
	 * This method is intended for internal use only. This will check to see if it needs to add an 
	 * webdoclet facet to the project.
	 * 
	 * @throws CoreException 
	 * @throws ExecutionException 
	 */
	private void installXDocletFacetIfNecessary(IProgressMonitor monitor, IProject project) throws CoreException, ExecutionException {

		// If not using annotations, ignore the xdoclet facet
		if (!model.getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			return;
		
		// If an extended annotations processor is added, ignore the default xdoclet one
		Descriptor descriptor = AnnotationsControllerManager.INSTANCE.getDescriptor(getTargetComponent().getProject());
		if (descriptor != null)
			return;

		// Otherwise check and see if the xdoclet facet is on the project yet
		IFacetedProject facetedProject = ProjectFacetsManager.create(project);
		if (!facetedProject.hasProjectFacet(WebFacetUtils.WEB_XDOCLET_FACET))
			return;
			
		// Install xdoclet facet
		installXDocletFacet(monitor, project);
	}
	
	/**
     * This method is intended for internal use only. This method will create an
     * instance of the CreateFilterTemplate model to be used in conjunction
     * with the WTPJETEmitter. This method will not return null.
     * 
     * @see CreateFilterTemplateModel
     * @see NewFilterClassOperation#generateUsingTemplates(IProgressMonitor,
     *      IPackageFragment)
     * 
     * @return CreateFilterTemplateModel
     */
    private CreateFilterTemplateModel createTemplateModel() {
        // Create the CreateFilterTemplateModel instance with the new filter
        // class data model
        CreateFilterTemplateModel templateModel = new CreateFilterTemplateModel(model);
        return templateModel;
    }
    
    /**
     * This method is intended for internal use only. This will use the
     * WTPJETEmitter to create an annotated java file based on the passed in
     * filter class template model. This method does not accept null
     * parameters. It will not return null. If annotations are not used, it will
     * use the non annotated template to omit the annotated tags.
     * 
     * @see NewFilterClassOperation#generateUsingTemplates(IProgressMonitor,
     *      IPackageFragment)
     * @see JETEmitter#generate(org.eclipse.core.runtime.IProgressMonitor,
     *      java.lang.Object[])
     * @see CreateFilterTemplateModel
     * 
     * @param tempModel
     * @param monitor
     * @return String the source for the java file
     * @throws JETException
     */
    private String generateTemplateSource(CreateFilterTemplateModel tempModel, IProgressMonitor monitor) throws JETException {
        URL templateURL = FileLocator.find(WebPlugin.getDefault().getBundle(), new Path(TEMPLATE_FILE), null);
        cleanUpOldEmitterProject();
        WTPJETEmitter emitter = new WTPJETEmitter(templateURL.toString(), this.getClass().getClassLoader());
        emitter.setIntelligentLinkingEnabled(true);
        emitter.addVariable(WEB_PLUGIN, WebPlugin.PLUGIN_ID);
        return emitter.generate(monitor, new Object[] { tempModel });
    }
    
	private void cleanUpOldEmitterProject() {
		IProject project = ProjectUtilities.getProject(WTPJETEmitter.PROJECT_NAME);
		if (project == null || !project.exists())
			return;
		try {
			IMarker[] markers = project.findMarkers(IJavaModelMarker.BUILDPATH_PROBLEM_MARKER, false, IResource.DEPTH_ZERO);
			for (int i = 0, l = markers.length; i < l; i++) {
				if (((Integer) markers[i].getAttribute(IMarker.SEVERITY)).intValue() == IMarker.SEVERITY_ERROR) {
					project.delete(true, new NullProgressMonitor());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method will return the java source folder as specified in the java
	 * class data model. It will create the java source folder if it does not
	 * exist. This method may return null.
	 * 
	 * @see INewJavaClassDataModelProperties#SOURCE_FOLDER
	 * @see IFolder#create(boolean, boolean,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 * 
	 * @return IFolder the java source folder
	 */
	protected final IFolder createJavaSourceFolder() {
		// Get the source folder name from the data model
		String folderFullPath = model.getStringProperty(INewJavaClassDataModelProperties.SOURCE_FOLDER);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IFolder folder = root.getFolder(new Path(folderFullPath));
		// If folder does not exist, create the folder with the specified path
		if (!folder.exists()) {
			try {
				folder.create(true, true, null);
			} catch (CoreException e) {
				Logger.getLogger().log(e);
			}
		}
		// Return the source folder
		return folder;
	}

	@Override
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		return doExecute(monitor, info);
	}
	
	public IProject getTargetProject() {
		String projectName = model.getStringProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME);
		return ProjectUtilities.getProject(projectName);
	}
	
	private IVirtualComponent getTargetComponent() {
		return ComponentCore.createComponent(getTargetProject());
	}
}
