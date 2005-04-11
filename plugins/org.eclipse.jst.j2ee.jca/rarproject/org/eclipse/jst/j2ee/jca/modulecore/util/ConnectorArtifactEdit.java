package org.eclipse.jst.j2ee.jca.modulecore.util;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.jca.JcaFactory;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;

public class ConnectorArtifactEdit extends EnterpriseArtifactEdit {
	/**
	 * <p>
	 * Identifier used to link ConnectorArtifactEdit to a ConnectorEditAdapterFactory {@see
	 * ConnectorEditAdapterFactory} stored in an AdapterManger (@see AdapterManager)
	 * </p>
	 */

	public static final Class ADAPTER_TYPE = ConnectorArtifactEdit.class;

	/**
	 * <p>
	 * Identifier used to group and query common artifact edits.
	 * </p>
	 */

	public static String TYPE_ID = IModuleConstants.JST_CONNECTOR_MODULE; //$NON-NLS-1$
	
	/**
	 * @param aHandle
	 * @param toAccessAsReadOnly
	 * @throws IllegalArgumentException
	 */
	public ConnectorArtifactEdit(ComponentHandle aHandle, boolean toAccessAsReadOnly) throws IllegalArgumentException {
		super(aHandle, toAccessAsReadOnly);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}.
	 * </p>
	 * 
	 * @param anArtifactEditModel
	 */

	public ConnectorArtifactEdit(ArtifactEditModel anArtifactEditModel) {
		super(anArtifactEditModel);
	}
	
	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}
	 * </p>
	 * 
	 * @param aNature
	 *            A non-null {@see ModuleCoreNature}for an accessible project
	 * @param aModule
	 *            A non-null {@see WorkbenchComponent}pointing to a module from the given
	 *            {@see ModuleCoreNature}
	 */

	public ConnectorArtifactEdit(ModuleCoreNature aNature, WorkbenchComponent aModule, boolean toAccessAsReadOnly) {
		super(aNature, aModule, toAccessAsReadOnly);
	}
	
	/**
	 * 
	 * @return ConnectorResource from (@link getDeploymentDescriptorResource())
	 *  
	 */

	public ConnectorResource getConnectorXmiResource() {
		return (ConnectorResource) getDeploymentDescriptorResource();
	}
	
	/**
	 * <p>
	 * Retrieves the underlying resource from the ArtifactEditModel using defined URI.
	 * </p>
	 * 
	 * @return Resource
	 *  
	 */

	public Resource getDeploymentDescriptorResource() {
		return getArtifactEditModel().getResource(J2EEConstants.RAR_DD_URI_OBJ);
	}
	
	/**
	 * <p>
	 * Obtains the Connector (@see Connector) root object from the ConnectorResource. If the root object does
	 * not exist, then one is created (@link addConnectorIfNecessary(getConnectorXmiResource())).
	 * The root object contains all other resource defined objects.
	 * </p>
	 * 
	 * @return EObject
	 *  
	 */
	public EObject getDeploymentDescriptorRoot() {
		List contents = getDeploymentDescriptorResource().getContents();
		if (contents.size() > 0)
			return (EObject) contents.get(0);
		addConnectorIfNecessary(getConnectorXmiResource());
		return (EObject) contents.get(0);
	}
	
	/**
	 * <p>
	 * Creates a deployment descriptor root object (Connector) and populates with data. Adds the root
	 * object to the deployment descriptor resource.
	 * </p>
	 * 
	 * <p>
	 * 
	 * @param aModule
	 *            A non-null pointing to a {@see XMLResource}
	 * 
	 * Note: This method is typically used for JUNIT - move?
	 * </p>
	 */
	protected void addConnectorIfNecessary(XMLResource aResource) {

		if (aResource != null && aResource.getContents().isEmpty()) {
			Connector connector = JcaFactory.eINSTANCE.createConnector();
			aResource.getContents().add(connector);
			URI moduleURI = getArtifactEditModel().getModuleURI();
			try {
				connector.setDisplayName(StructureEdit.getDeployedName(moduleURI));
			} catch (UnresolveableURIException e) {
			}
			aResource.setID(connector, J2EEConstants.CONNECTOR_ID);
			//TODO add more mandatory elements
		}
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of ArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an ArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will not be used for editing. Invocations of any save*() API on an instance returned from
	 * this method will throw exceptions.
	 * </p>
	 * <p>
	 * <b>The following method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an
	 *            accessible project in the workspace
	 * @return An instance of ArtifactEdit that may only be used to read the underlying content
	 *         model
	 */
	public static ConnectorArtifactEdit getConnectorArtifactEditForRead(ComponentHandle aHandle) {
		ConnectorArtifactEdit artifactEdit = null;
		try {
			artifactEdit = new ConnectorArtifactEdit(aHandle, true);
		} catch (IllegalArgumentException iae) {
			artifactEdit = null;
		}
		return artifactEdit;
	}
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of ArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an ArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>The following method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an
	 *            accessible project in the workspace
	 * @return An instance of ArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static ConnectorArtifactEdit getConnectorArtifactEditForWrite(ComponentHandle aHandle) {
		ConnectorArtifactEdit artifactEdit = null;
		try {
			artifactEdit = new ConnectorArtifactEdit(aHandle, false);
		} catch (IllegalArgumentException iae) {
			artifactEdit = null;
		}
		return artifactEdit;
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of ConnectorArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an ConnectorArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that will not
	 * be used for editing. Invocations of any save*() API on an instance returned from this method
	 * will throw exceptions.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of ConnectorArtifactEdit that may only be used to read the underlying content
	 *         model
	 * @throws UnresolveableURIException
	 *             could not resolve uri.
	 */
	public static ConnectorArtifactEdit getConnectorArtifactEditForRead(WorkbenchComponent aModule) {
		try {
			if (isValidConnectorModule(aModule)) {
				IProject project = StructureEdit.getContainingProject(aModule);
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new ConnectorArtifactEdit(nature, aModule, true);
			}
		} catch (UnresolveableURIException uue) {
		}
		return null;
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of ConnectorArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an ConnectorArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of ConnectorArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static ConnectorArtifactEdit getConnectorArtifactEditForWrite(WorkbenchComponent aModule) {
		try {
			if (isValidConnectorModule(aModule)) {
				IProject project = StructureEdit.getContainingProject(aModule);
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new ConnectorArtifactEdit(nature, aModule, false);
			}
		} catch (UnresolveableURIException uue) {
		}
		return null;
	}
	
	/**
	 * @param module
	 *            A {@see WorkbenchComponent}
	 * @return True if the supplied module
	 *         {@see ArtifactEdit#isValidEditableModule(WorkbenchComponent)}and the moduleTypeId is a
	 *         JST module
	 */
	public static boolean isValidConnectorModule(WorkbenchComponent aModule) throws UnresolveableURIException {
		if (!isValidEditableModule(aModule))
			return false;
		/* and match the JST_Connector_MODULE type */
		if (!TYPE_ID.equals(aModule.getComponentType().getComponentTypeId()))
			return false;
		return true;
	}
	
	/**
	 * <p>
	 * Retrieves J2EE version information from ConnectorResource.
	 * </p>
	 * 
	 * @return an integer representation of a J2EE Spec version
	 *  
	 */

	public int getJ2EEVersion() {
		return getConnectorXmiResource().getJ2EEVersionID();
	}
	
	/**
	 * 
	 * @return Connector from (@link getDeploymentDescriptorRoot())
	 *  
	 */
	public Connector getConnector() {
		return (Connector) getDeploymentDescriptorRoot();
	}

	public EObject createModelRoot() {
	    return createModelRoot(getJ2EEVersion());
	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#createModelRoot(java.lang.Integer)
     */
    public EObject createModelRoot(int version) {
        ConnectorResource res = (ConnectorResource)getDeploymentDescriptorResource();
		res.setModuleVersionID(version);
	    addConnectorIfNecessary(res);
		return ((ConnectorResource)getDeploymentDescriptorResource()).getRootObject();
    }
}
