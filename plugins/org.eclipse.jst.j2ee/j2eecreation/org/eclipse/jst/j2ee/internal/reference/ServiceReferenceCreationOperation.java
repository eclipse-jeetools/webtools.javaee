/*
 * Created on Feb 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.reference;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.client.impl.ClientFactoryImpl;
import org.eclipse.jst.j2ee.internal.webservices.WebServiceClientGenerator;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesClientDataRegistry;
import org.eclipse.jst.j2ee.internal.webservices.WebServicesManager;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webservice.wsclient.ComponentScopedRefs;
import org.eclipse.jst.j2ee.webservice.wsclient.PortComponentRef;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;


/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class ServiceReferenceCreationOperation extends ModelModifierOperation {
	ServiceReferenceDataModel dataModel;
	ServiceRef ref;
	private int j2eeVersion = J2EEVersionConstants.J2EE_1_4_ID;

	/**
	 * @param dataModel
	 */
	public ServiceReferenceCreationOperation(ServiceReferenceDataModel dataModel) {
		super(dataModel);
		this.dataModel = dataModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ibm.wtp.emf.workbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		WebServiceClientGenerator actualGen = getWebServiceClientGenerator();
		if (actualGen != null)
			actualGen.genWebServiceClientArtifacts(dataModel);
		if (!dataModel.didGenDescriptors())
			modifier.addHelper(createHelpers());

		if (actualGen != null)
			createPortCompRefHelpers();
	}

	protected WebServiceClientGenerator getWebServiceClientGenerator() {
		List generators = WebServicesClientDataRegistry.getInstance().getWSClientGeneratorExtensions();
		WebServiceClientGenerator actualGen = null;

		IRuntime runtime = ServerCore.getProjectProperties(dataModel.getTargetProject()).getRuntimeTarget();
		if (runtime == null)
			return null;
		String serverTargetID = runtime.getRuntimeType().getId();

		for (int i = 0; i < generators.size(); i++) {
			WebServiceClientGenerator gen = (WebServiceClientGenerator) generators.get(i);
			if (gen.isRuntimeSupported(serverTargetID)) {
				if (serverTargetID.equals(WebServiceClientGenerator.GENERIC_J2EE_CONTAINER)) {
					// AXIS is only supported for web projects
					if (dataModel.getDeploymentDescriptorType() != XMLResource.WEB_APP_TYPE)
						break;
				}
				actualGen = gen;
				break;
			}
		}
		return actualGen;
	}

	/*
	 * @see SimpleCommandWizardPage#createCommandHelper()
	 */
	public ModifierHelper createHelpers() {
		ModifierHelper helper = null;
		ref = Webservice_clientFactory.eINSTANCE.createServiceRef();

		if (dataModel != null) {
			helper = new ModifierHelper();
			EObject owner = (EObject) dataModel.getProperty(ReferenceDataModel.OWNER);
			switch (dataModel.getDeploymentDescriptorType()) {
				case XMLResource.APP_CLIENT_TYPE :
					if (((ApplicationClient) owner).getVersionID() == J2EEVersionConstants.J2EE_1_4_ID) {
						helper.setFeature(ClientFactoryImpl.getPackage().getApplicationClient_ServiceRefs());
						helper.setOwner(owner);
						Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
						descriptionObj.setValue(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
						ref.getDescriptions().add(descriptionObj);
						ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
					} else {
						helper.setFeature(Webservice_clientPackage.eINSTANCE.getWebServicesClient_ServiceRefs());
						helper.setOwner(WebServicesManager.getInstance().getDefaultWebServicesResource(owner, (J2EEEditModel) editModel).getWebServicesClient());
						ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
						j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
					}
					break;
				case XMLResource.EJB_TYPE :
					if (((EnterpriseBean) owner).getVersionID() == J2EEVersionConstants.EJB_2_1_ID) {
						helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ServiceRefs());
						helper.setOwner(owner);
						Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
						descriptionObj.setValue(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
						ref.getDescriptions().add(descriptionObj);
						ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
					} else {
						j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
						return createEJB13Helper(owner, helper);
					}
					break;
				case XMLResource.WEB_APP_TYPE :
					if (((WebApp) owner).getVersionID() == J2EEVersionConstants.WEB_2_4_ID) {
						helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_ServiceRefs());
						helper.setOwner(owner);
						Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
						descriptionObj.setValue(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
						ref.getDescriptions().add(descriptionObj);
						ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
					} else {
						helper.setFeature(Webservice_clientPackage.eINSTANCE.getWebServicesClient_ServiceRefs());
						helper.setOwner(WebServicesManager.getInstance().getDefaultWebServicesResource(owner, (J2EEEditModel) editModel).getWebServicesClient());
						ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
						j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
					}
					break;
			}
		}

		ref.setServiceRefName(dataModel.getStringProperty(ReferenceDataModel.REF_NAME).trim());
		String serviceInterfaceName = dataModel.getServiceInterfaceName();
		ref.setServiceInterface((JavaClass) JavaRefFactory.eINSTANCE.reflectType(serviceInterfaceName, (EObject) dataModel.getProperty(ReferenceDataModel.OWNER)));
		ref.setWsdlFile(dataModel.getStringProperty(ServiceReferenceDataModel.WSDL_FILE).trim());
		if (dataModel.getStringProperty(ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE) != null && !dataModel.getStringProperty(ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE).equals("")) //$NON-NLS-1$
			ref.setJaxrpcMappingFile(dataModel.getStringProperty(ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE).trim());
		QName qName = ((CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI)).getCommonFactory().createQName();

		if (j2eeVersion == J2EEVersionConstants.J2EE_1_3_ID) {
			qName.setLocalPart(dataModel.getStringProperty(ServiceReferenceDataModel.QNAME_lOCAL_PART));
			qName.setNamespaceURI(dataModel.getStringProperty(ServiceReferenceDataModel.QNAME_NAMESPACE_URI));
		} else {
			qName.setValues("prefix", dataModel.getStringProperty(ServiceReferenceDataModel.QNAME_NAMESPACE_URI), dataModel.getStringProperty(ServiceReferenceDataModel.QNAME_lOCAL_PART)); //$NON-NLS-1$         
		}
		ref.setServiceQname(qName);
		ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());
		helper.setValue(ref);
		return helper;
	}

	/*
	 * @see SimpleCommandWizardPage#createCommandHelper()
	 */
	public void createPortCompRefHelpers() {
		ModifierHelper helper = null;
		if (dataModel != null) {
			helper = new ModifierHelper();
			String[] serEndpoints = dataModel.getServiceEndpointInterfaceNames();
			if (serEndpoints == null || serEndpoints.length == 0)
				return;
			String serviceEndpointInterface;
			for (int i = 0; i < serEndpoints.length; i++) {
				PortComponentRef portCompRef = Webservice_clientFactory.eINSTANCE.createPortComponentRef();
				helper.setOwner(ref);
				serviceEndpointInterface = serEndpoints[i];
				portCompRef.setServiceEndpointInterface((JavaClass) JavaRefFactory.eINSTANCE.reflectType(serviceEndpointInterface, (EObject) dataModel.getProperty(ReferenceDataModel.OWNER)));
				helper.setFeature(Webservice_clientPackage.eINSTANCE.getServiceRef_PortComponentRefs());
				helper.setValue(portCompRef);
				modifier.addHelper(helper);
			}
		}
	}

	/**
	 * @return
	 */
	private ModifierHelper createEJB13Helper(EObject ownerEJB, ModifierHelper helper) {
		ref = Webservice_clientFactory.eINSTANCE.createServiceRef();
		WebServicesClient webServiceClient = WebServicesManager.getInstance().getDefaultWebServicesResource(ownerEJB, (J2EEEditModel) editModel).getWebServicesClient();

		ref.setServiceRefName(dataModel.getStringProperty(ReferenceDataModel.REF_NAME).trim());
		String serviceInterfaceName = dataModel.getServiceInterfaceName();
		ref.setServiceInterface((JavaClass) JavaRefFactory.eINSTANCE.reflectType(serviceInterfaceName, (EObject) dataModel.getProperty(ReferenceDataModel.OWNER)));
		ref.setWsdlFile(dataModel.getStringProperty(ServiceReferenceDataModel.WSDL_FILE).trim());
		if (dataModel.getStringProperty(ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE) != null && !dataModel.getStringProperty(ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE).equals("")) //$NON-NLS-1$
			ref.setJaxrpcMappingFile(dataModel.getStringProperty(ServiceReferenceDataModel.JAX_RPC_MAPPING_FILE).trim());
		QName qName = ((CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI)).getCommonFactory().createQName();
		qName.setLocalPart(dataModel.getStringProperty(ServiceReferenceDataModel.QNAME_lOCAL_PART));
		qName.setNamespaceURI(dataModel.getStringProperty(ServiceReferenceDataModel.QNAME_NAMESPACE_URI));
		ref.setServiceQname(qName);
		ref.setDescription(dataModel.getStringProperty(ReferenceDataModel.DESCRIPTION).trim());

		ComponentScopedRefs compScopedRef = getComponentScopedRef((EnterpriseBean) ownerEJB, webServiceClient);
		if (compScopedRef == null) {
			compScopedRef = Webservice_clientFactory.eINSTANCE.createComponentScopedRefs();
			compScopedRef.setComponentName(((EnterpriseBean) ownerEJB).getName());
			helper.setOwner(webServiceClient);
			helper.setFeature(Webservice_clientPackage.eINSTANCE.getWebServicesClient_ComponentScopedRefs());
			compScopedRef.getServiceRefs().add(ref);
			helper.setValue(compScopedRef);
		} else {
			helper.setOwner(compScopedRef);
			helper.setFeature(Webservice_clientPackage.eINSTANCE.getComponentScopedRefs_ServiceRefs());
			helper.setValue(ref);
		}
		return helper;
	}

	/**
	 * @param webServiceRes
	 * @return
	 */
	private ComponentScopedRefs getComponentScopedRef(EnterpriseBean ownerEJB, WebServicesClient webServiceClient) {
		List existingRefs = webServiceClient.getComponentScopedRefs();
		if (existingRefs != null) {
			for (int i = 0; i < existingRefs.size(); i++) {
				if (((ComponentScopedRefs) existingRefs.get(i)).getComponentName().equals(ownerEJB.getName())) {
					return (ComponentScopedRefs) existingRefs.get(i);
				}
			}
		}
		return null;
	}
}
