package org.eclipse.jst.j2ee.ejb.componentcore.util;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.componentcore.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * <p>
 * EJBArtifactEdit obtains a EJB Deployment Descriptor metamodel specifec data from a
 * {@see org.eclipse.jst.j2ee.ejb.EJBResource}&nbsp; which stores the metamodel. The
 * {@see org.eclipse.jst.j2ee.ejb.EJBResource}&nbsp;is retrieved from the
 * {@see org.eclipse.wst.common.modulecore.ArtifactEditModel}&nbsp;using a constant {@see
 * J2EEConstants#EJBJAR_DD_URI_OBJ}. The defined methods extract data or manipulate the contents of
 * the underlying resource.
 * </p>
 * 
 */ 
public class EJBArtifactEdit extends EnterpriseArtifactEdit {
	
	/**
	 * <p>
	 * Identifier used to link EJBArtifactEdit to a EJBEditAdapterFactory {@see
	 * EJBEditAdapterFactory} stored in an AdapterManger (@see AdapterManager)
	 * </p>
	 */

	public static final Class ADAPTER_TYPE = EJBArtifactEdit.class;

	/**
	 * <p>
	 * Identifier used to group and query common artifact edits.
	 * </p>
	 */

	public static String TYPE_ID = "jst.ejb"; //$NON-NLS-1$

	/**
	 * @param aHandle
	 * @param toAccessAsReadOnly
	 * @throws IllegalArgumentException
	 */
	public EJBArtifactEdit(ComponentHandle aHandle, boolean toAccessAsReadOnly) throws IllegalArgumentException {
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
	public EJBArtifactEdit(ArtifactEditModel model) {
		super(model);
	}
	
	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}
	 * </p>
	 * 
	 * <p>Note: This method is for internal use only. Clients should not call this method.</p>
	 * @param aNature
	 *            A non-null {@see ModuleCoreNature}for an accessible project
	 * @param aModule
	 *            A non-null {@see WorkbenchComponent}pointing to a module from the given
	 *            {@see ModuleCoreNature}
	 */ 
	public EJBArtifactEdit(ModuleCoreNature aNature, IVirtualComponent aModule, boolean toAccessAsReadOnly) {
		super(aNature, aModule, toAccessAsReadOnly);
	}
	
	/**
	 * 
	 * @return EJBResource from (@link getDeploymentDescriptorResource())
	 *  
	 */

	public EJBResource getEJBJarXmiResource() {
		return (EJBResource) getDeploymentDescriptorResource();
	}
	
	/**
	 * <p>
	 * Retrieves J2EE version information from EJBResource.
	 * </p>
	 * 
	 * @return an integer representation of a J2EE Spec version
	 *  
	 */

	public int getJ2EEVersion() {
		return getEJBJarXmiResource().getJ2EEVersionID();
	}
	
	/**
	 * <p>
	 * Checks is a EJB Client Jar exists for the ejb module project
	 * </p>
	 * 
	 * @return boolean
	 *  
	 */
	
	public boolean hasEJBClientJARProject() {
		
		if(getEJBClientJarModule() != null)
			return true;
		return false;
	}
	
	/**
	 * <p>
	 * Creates a new EJB module
	 * </p>
	 * 
	 * @return 
	 */
	public Module createNewModule() {
		return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createEjbModule();
	}

	
	/**
	 * @param project
	 * @return WorkbenchComponent
	 */
	public IVirtualComponent getEJBClientJarModule() {
		EJBJar jar = getEJBJar();
		IVirtualComponent ejbComponent,ejbClientComponent = null;
		String clientJAR = null;
		if (jar != null)
			clientJAR = jar.getEjbClientJar();
		if (clientJAR != null) {
			 ejbComponent = ComponentCore.createComponent(getComponentHandle().getProject(),getComponentHandle().getName());
			 ejbClientComponent = ejbComponent.getReference(clientJAR).getReferencedComponent()
		}
		return ejbClientComponent;
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
		return getArtifactEditModel().getResource(J2EEConstants.EJBJAR_DD_URI_OBJ);
	}
	
	/**
	 * 
	 * @return EJBJar from (@link getDeploymentDescriptorRoot())
	 *  
	 */
	public EJBJar getEJBJar() {
		return (EJBJar) getDeploymentDescriptorRoot();
	}
	
	/**
	 * <p>
	 * Obtains the EJBJar (@see EJBJar) root object from the EJBResource. If the root object does
	 * not exist, then one is created (@link addEJBJarIfNecessary(getEJBJarXmiResource())).
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
		addEJBJarIfNecessary((EJBResource)getDeploymentDescriptorResource());
		return (EObject) contents.get(0);
	}
	
	/**
	 * Returns the deployment descriptor type of the EJB module.
	 * @return int 
	 */
	
	public int getDeploymentDescriptorType() {
		return XMLResource.EJB_TYPE;
	}
	
	/**
	 * <p>
	 * Creates a deployment descriptor root object (EJBJar) and populates with data. Adds the root
	 * object to the deployment descriptor resource.
	 * </p>
	 * 
	 * <p>
	 * 
	 * @param aModule
	 *            A non-null pointing to a {@see XMLResource}
	 * Note: This method is typically used for JUNIT - move?
	 * </p>
	 */
	protected void addEJBJarIfNecessary(XMLResource aResource) {
		if (aResource != null) {
		    if(aResource.getContents() == null || aResource.getContents().isEmpty()) {
				EJBJar ejbJar = EjbFactory.eINSTANCE.createEJBJar();
				aResource.getContents().add(ejbJar);
		    }
			EJBJar ejbJar = (EJBJar)aResource.getContents().get(0);
			URI moduleURI = getArtifactEditModel().getModuleURI();
			try {
				ejbJar.setDisplayName(StructureEdit.getDeployedName(moduleURI));
			} catch (UnresolveableURIException e) {
				//Ignore
			}
			aResource.setID(ejbJar, J2EEConstants.EJBJAR_ID);
			//TODO add more mandatory elements
			try {
				aResource.saveIfNecessary();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	public static EJBArtifactEdit getEJBArtifactEditForRead(ComponentHandle aHandle) {
		EJBArtifactEdit artifactEdit = null;
		try {
			artifactEdit = new EJBArtifactEdit(aHandle, true);
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
	public static EJBArtifactEdit getEJBArtifactEditForWrite(ComponentHandle aHandle) {
		EJBArtifactEdit artifactEdit = null;
		try {
			artifactEdit = new EJBArtifactEdit(aHandle, false);
		} catch (IllegalArgumentException iae) {
			artifactEdit = null;
		}
		return artifactEdit;
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of EJBArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an EJBArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that will not
	 * be used for editing. Invocations of any save*() API on an instance returned from this method
	 * will throw exceptions.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * <p>Note: This method is for internal use only. Clients should not call this method.</p>
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of EJBArtifactEdit that may only be used to read the underlying content
	 *         model
	 * @throws UnresolveableURIException
	 *             could not resolve uri.
	 */
	public static EJBArtifactEdit getEJBArtifactEditForRead(IVirtualComponent aModule) {
		try {
			if (isValidEJBModule(aModule)) {
				IProject project = aModule.getProject();
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new EJBArtifactEdit(nature, aModule, true);
			}
		} catch (UnresolveableURIException uue) {
			//Ignore
		}
		return null;
	}
	
	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchComponent}. Instances of EJBArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an EJBArtifactEdit facade for a specific {@see WorkbenchComponent}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * <p>Note: This method is for internal use only. Clients should not call this method.</p>
	 * @param aModule
	 *            A valid {@see WorkbenchComponent}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of EJBArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static EJBArtifactEdit getEJBArtifactEditForWrite(IVirtualComponent aModule) {
		try {
			if (isValidEJBModule(aModule)) {
				IProject project = aModule.getProject();
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new EJBArtifactEdit(nature, aModule, false);
			}
		} catch (UnresolveableURIException uue) {
			//Ignore
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
	public static boolean isValidEJBModule(IVirtualComponent aModule) throws UnresolveableURIException {
		if (!isValidEditableModule(aModule))
			return false;
		/* and match the JST_EJB_MODULE type */
		if (!TYPE_ID.equals(aModule.getComponentTypeId()))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#createModelRoot()
	 */
	public EObject createModelRoot() {
	    return createModelRoot(getJ2EEVersion());
	}
			
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#createModelRoot(int)
	 */
	public EObject createModelRoot(int version) {
	    EJBResource res = (EJBResource)getDeploymentDescriptorResource();
	    res.setModuleVersionID(version);
	    addEJBJarIfNecessary(res);
		return ((EJBResource)getDeploymentDescriptorResource()).getRootObject();
	}
}
