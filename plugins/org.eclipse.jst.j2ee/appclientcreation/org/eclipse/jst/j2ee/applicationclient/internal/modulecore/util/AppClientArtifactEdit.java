package org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.client.ClientFactory;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;
import org.eclipse.wst.common.modulecore.WorkbenchModule;

public class AppClientArtifactEdit extends EnterpriseArtifactEdit {
	
	/**
	 * <p>
	 * Identifier used to link AppClientArtifactEdit to a AppClientEditAdapterFactory {@see
	 * AppClientEditAdapterFactory} stored in an AdapterManger (@see AdapterManager)
	 * </p>
	 */

	public static final Class ADAPTER_TYPE = AppClientArtifactEdit.class;

	/**
	 * <p>
	 * Identifier used to group and query common artifact edits.
	 * </p>
	 */

	public static String TYPE_ID = "jst.applicationClient"; //$NON-NLS-1$

	public AppClientArtifactEdit(ArtifactEditModel anArtifactEditModel) {
		super(anArtifactEditModel);
		// TODO Auto-generated constructor stub
	}

	public AppClientArtifactEdit(ModuleCoreNature aNature, WorkbenchModule aModule, boolean toAccessAsReadOnly) {
		super(aNature, aModule, toAccessAsReadOnly);
		// TODO Auto-generated constructor stub
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
		return getArtifactEditModel().getResource(J2EEConstants.APP_CLIENT_DD_URI_OBJ);
	}
	
	/**
	 * <p>
	 * Obtains the ApplicationClient (@see ApplicationClient) root object from the ApplicationClientResource. If the root object does
	 * not exist, then one is created (@link addAppClientJarIfNecessary(getAppClientXmiResource())).
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
		addAppClientIfNecessary(getApplicationClientXmiResource());
		return (EObject) contents.get(0);
	}
	
	/**
	 * 
	 * @return ApplicationClientResource from (@link getDeploymentDescriptorResource())
	 *  
	 */

	public ApplicationClientResource getApplicationClientXmiResource() {
		return (ApplicationClientResource) getDeploymentDescriptorResource();
	}
	
	/**
	 * <p>
	 * Creates a deployment descriptor root object (ApplicationClient) and populates with data. Adds the root
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
	protected void addAppClientIfNecessary(XMLResource aResource) {

		if (aResource != null && aResource.getContents().isEmpty()) {
			ApplicationClient appClient = ClientFactory.eINSTANCE.createApplicationClient();
			aResource.getContents().add(appClient);
			URI moduleURI = getArtifactEditModel().getModuleURI();
			try {
				appClient.setDisplayName(ModuleCore.getDeployedName(moduleURI));
			} catch (UnresolveableURIException e) {
			}
			aResource.setID(appClient, J2EEConstants.APP_CLIENT_ID);
			//TODO add more mandatory elements
		}
	}
	
	/**
	 * <p>
	 * Method used for adding a j2ee project to an ear project; subclasses must override to create a
	 * new instance of the correct kind of Module
	 * </p>
	 */
	public Module createNewModule() {
		return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createJavaClientModule();
	}
	
	/**
	 * 
	 * @return ApplicationClient from (@link getDeploymentDescriptorRoot())
	 *  
	 */
	public ApplicationClient getApplicationClient() {
		return (ApplicationClient) getDeploymentDescriptorRoot();
	}
	
	/**
	 * <p>
	 * Retrieves J2EE version information from ApplicationClientResource.
	 * </p>
	 * 
	 * @return an integer representation of a J2EE Spec version
	 *  
	 */

	public int getJ2EEVersion() {
		return getApplicationClientXmiResource().getJ2EEVersionID();
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchModule}. Instances of AppClientArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an AppClientArtifactEdit facade for a specific {@see WorkbenchModule}&nbsp;that will not
	 * be used for editing. Invocations of any save*() API on an instance returned from this method
	 * will throw exceptions.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchModule}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of AppClientArtifactEdit that may only be used to read the underlying content
	 *         model
	 * @throws UnresolveableURIException
	 *             could not resolve uri.
	 */
	public static AppClientArtifactEdit getAppClientArtifactEditForRead(WorkbenchModule aModule) {
		try {
			if (isValidApplicationClientModule(aModule)) {
				IProject project = ModuleCore.getContainingProject(aModule.getHandle());
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new AppClientArtifactEdit(nature, aModule, true);
			}
		} catch (UnresolveableURIException uue) {
		}
		return null;
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchModule}. Instances of AppClientArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an AppClientArtifactEdit facade for a specific {@see WorkbenchModule}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchModule}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of AppClientArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static AppClientArtifactEdit getAppClientArtifactEditForWrite(WorkbenchModule aModule) {
		try {
			if (isValidApplicationClientModule(aModule)) {
				IProject project = ModuleCore.getContainingProject(aModule.getHandle());
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new AppClientArtifactEdit(nature, aModule, false);
			}
		} catch (UnresolveableURIException uue) {
		}
		return null;
	}
	
	/**
	 * @param module
	 *            A {@see WorkbenchModule}
	 * @return True if the supplied module
	 *         {@see ArtifactEdit#isValidEditableModule(WorkbenchModule)}and the moduleTypeId is a
	 *         JST module
	 */
	public static boolean isValidApplicationClientModule(WorkbenchModule aModule) throws UnresolveableURIException {
		if (!isValidEditableModule(aModule))
			return false;
		/* and match the JST_ApplicationClient_MODULE type */
		if (!TYPE_ID.equals(aModule.getModuleType().getModuleTypeId()))
			return false;
		return true;
	}

}
