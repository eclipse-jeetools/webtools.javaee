/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 27, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.reference;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.operations.AddUtilityProjectToEARDataModel;
import org.eclipse.jst.j2ee.client.impl.ClientFactoryImpl;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.jst.j2ee.internal.earcreation.AddUtilityJARMapCommand;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.moduleextension.EjbModuleExtension;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author dfholttp
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class EJBReferenceCreationOperation extends ModelModifierOperation {
	protected IProject ownerProject;
	protected IProject targetProject; //referencing project
	protected AddUtilityJARMapCommand addUtilityJarMapCommand = null;
	protected JARDependencyOperation jarDependencyOperation = null;
	protected String refName = null;
	protected String refType = null;
	protected String refHome = null;
	protected String refRemote = null;
	protected String des = null;
	protected String refLink = null;
	protected IProject newClientProject;


	/**
	 * @param dataModel
	 */
	public EJBReferenceCreationOperation(EJBReferenceDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#doInitialize(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void doInitialize(IProgressMonitor monitor) {
		super.doInitialize(monitor);
		setUpDataForCommand();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#postExecuteCommands()
	 */
	protected void postExecuteCommands(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		super.postExecuteCommands(monitor);
		if (jarDependencyOperation != null)
			jarDependencyOperation.run(monitor);
	}

	protected void addHelpers() throws CoreException {
		ModifierHelper baseHelper = null;
		if (!operationDataModel.getBooleanProperty(EJBReferenceDataModel.IS_LOCAL))
			baseHelper = createEjbRefHelper();
		else
			baseHelper = createEJBLocalRefHelper();

		modifier.addHelper(baseHelper);

		if (((EJBReferenceDataModel) operationDataModel).shouldCreateClientJar())
			copyClientJarToEAR(ownerProject);
		updateJARDependencyIfNecessary();
	}

	protected ModifierHelper createEJBLocalRefHelper() {
		ModifierHelper helper = new ModifierHelper();
		EJBLocalRef ref = CommonPackage.eINSTANCE.getCommonFactory().createEJBLocalRef();
		ref.setName(refName);
		ref.setType(EjbRefType.get(refType));
		ref.setLocal(refRemote);
		ref.setLocalHome(refHome);
		ref.setLink(refLink);
		Integer version = (Integer) operationDataModel.getProperty(EJBReferenceDataModel.J2EE_VERSION);
		if (version != null && version.intValue() <= J2EEVersionConstants.J2EE_1_3_ID) {
			ref.setDescription(des);
		} else {
			Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
			descriptionObj.setValue(des);
			ref.getDescriptions().add(descriptionObj);
		}
		helper.setOwner((EObject) operationDataModel.getProperty(EJBReferenceDataModel.OWNER));
		helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbLocalRefs());
		//TODO:implement in was ext
		//modifier.addHelper(createEJBRefBindingHelper(ref, owner,
		// getEJBModel().getSelectedEnterpriseBean()));
		helper.setValue(ref);
		return helper;
	}

	protected ModifierHelper createEjbRefHelper() {
		ModifierHelper helper = new ModifierHelper();
		EjbRef ref = CommonPackage.eINSTANCE.getCommonFactory().createEjbRef();
		ref.setName(refName);
		ref.setType(EjbRefType.get(refType));
		ref.setHome(refHome);
		ref.setRemote(refRemote);
		ref.setLink(refLink);
		helper.setOwner((EObject) operationDataModel.getProperty(EJBReferenceDataModel.OWNER));

		Integer version = (Integer) operationDataModel.getProperty(EJBReferenceDataModel.J2EE_VERSION);
		if (version != null && version.intValue() <= J2EEVersionConstants.J2EE_1_3_ID) {
			ref.setDescription(des);
		} else {
			Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
			descriptionObj.setValue(des);
			ref.getDescriptions().add(descriptionObj);
		}

		switch (((EJBReferenceDataModel) operationDataModel).getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				helper.setFeature(ClientFactoryImpl.getPackage().getApplicationClient_EjbReferences());
				break;
			case XMLResource.EJB_TYPE :
				helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs());
				break;
			case XMLResource.WEB_APP_TYPE :
				helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EjbRefs());
				break;
		}
		helper.setValue(ref);
		return helper;
	}

	private void copyClientJarToEAR(IProject ownerProj) throws CoreException {
		IProject clientProj = newClientProject;
		if (clientProj == null) {
			EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
			if (ejbExt == null)
				return;
			clientProj = ejbExt.getDefinedEJBClientJARProject(targetProject);
			if (clientProj == null) {
				WTPOperation clientOp = ejbExt.createEJBClientJARProject(targetProject);
				try {
					clientOp.run(null);
				} catch (InvocationTargetException e) {
					//TODO: do something w/ log
					e.printStackTrace();
				} catch (InterruptedException e) {
					//TODO: do something w/ log
					e.printStackTrace();
				}
				clientProj = ejbExt.getDefinedEJBClientJARProject(targetProject);
			}
			setNewClientProject(clientProj);
		}
		if (clientProj != null) {
			J2EENature ownerNature = J2EENature.getRegisteredRuntime(ownerProj);
			if (ownerNature != null && ownerNature instanceof J2EEModuleNature) {
				EARNatureRuntime[] natures = ((J2EEModuleNature) ownerNature).getReferencingEARProjects();
				for (int i = 0; i < natures.length; i++) {
					addUtilityJarIfNecessary(clientProj, natures[i]);
				}
			}
		}
	}

	private void addUtilityJarIfNecessary(IProject clientProj, EARNatureRuntime nature) {
		if (!J2EEProjectUtilities.hasProjectMapping(nature, clientProj)) {
			IProject earProject = nature.getProject();
			String clientUri = J2EEProjectUtilities.getUtilityJARUriInFirstEAR(clientProj);
			AddUtilityProjectToEARDataModel uDataModel = AddUtilityProjectToEARDataModel.createAddToEARDataModel(earProject.getName(), clientProj);
			uDataModel.setProperty(AddUtilityProjectToEARDataModel.ARCHIVE_URI, clientUri);
			try {
				runNestedDefaultOperation(uDataModel, new NullProgressMonitor());
				// addUtilityJarMapCommand = new AddUtilityJARMapCommand(earProject, clientUri,
				// clientProj);
				// modifier.addAdditionalCommand(addUtilityJarMapCommand);
			} catch (InvocationTargetException e) {
				Logger.getLogger().logError(e);
			} catch (InterruptedException e) {
				Logger.getLogger().logError(e);
			}
		}
	}

	public void updateJARDependencyIfNecessary() {
		if (ownerProject != null && J2EEProjectUtilities.getReferencingEARProjects(ownerProject) != null) {
			//TODO: handle multiple ears
			EARNatureRuntime earNature = J2EEProjectUtilities.getFirstReferencingEARProject(ownerProject);
			if (earNature == null)
				return;
			IProject earProject = earNature.getProject();
			JARDependencyDataModel dataModel = new JARDependencyDataModel();
			dataModel.setProperty(JARDependencyDataModel.PROJECT_NAME, ownerProject.getName());
			dataModel.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_ADD);
			dataModel.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earProject.getName());
			EjbModuleExtension ejbExt = EarModuleManager.getEJBModuleExtension();
			String clientProjectName = null;
			if (ejbExt != null) {
				EJBJar ejbJar = ejbExt.getEJBJar(targetProject);
				if (ejbJar != null) {
					IProject referencedProject = J2EEProjectUtilities.getProject(ejbJar);
					clientProjectName = ejbJar.getEjbClientJar();
					if (clientProjectName != null && !clientProjectName.equals("")) { //$NON-NLS-1$
						if (newClientProject != null) {
							referencedProject = newClientProject;
							clientProjectName = referencedProject.getName();
						} else {
							clientProjectName = referencedProject.getName();
						}
					} else {
						clientProjectName = referencedProject.getName();
					}
				}
				dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, clientProjectName);
			} else {
				dataModel.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, targetProject.getName());
			}
			jarDependencyOperation = new JARDependencyOperation(dataModel);
		}
	}

	private void setUpDataForCommand() {
		ownerProject = ProjectUtilities.getProject((EObject) operationDataModel.getProperty(EJBReferenceDataModel.OWNER));
		targetProject = ProjectUtilities.getProject((EObject) operationDataModel.getProperty(EJBReferenceDataModel.TARGET));

		refName = operationDataModel.getStringProperty(EJBReferenceDataModel.REF_NAME);
		refType = operationDataModel.getStringProperty(EJBReferenceDataModel.REF_TYPE);
		if (!operationDataModel.getBooleanProperty(EJBReferenceDataModel.TARGET_IN_DIFFERENT_EAR))
			refLink = operationDataModel.getStringProperty(EJBReferenceDataModel.LINK);
		refHome = operationDataModel.getStringProperty(EJBReferenceDataModel.HOME_INTERFACE);
		refRemote = operationDataModel.getStringProperty(EJBReferenceDataModel.REMOTE_INTERACE);
		des = operationDataModel.getStringProperty(EJBReferenceDataModel.DESCRIPTION);
	}

	/**
	 * @param project
	 */
	public void setNewClientProject(IProject project) {
		newClientProject = project;
	}

	//TODO: reimplement in was.ext
	//    protected ModifierHelper createEJBRefBindingHelper(EjbRef aRef, EObject owner, EnterpriseBean
	// linkedEjb) {
	//        boolean isEJBOwner = owner instanceof EnterpriseBean;
	//        boolean isWebOwner = owner instanceof WebApp;
	//        IProject linkedProject;
	//        boolean hasEJBClientJar, isInDiffEar;
	//        if (isEJBOwner || isWebOwner) {
	//            linkedProject = ProjectUtilities.getProject(linkedEjb);
	//            hasEJBClientJar = EJBNatureRuntime.getRuntime(linkedProject).hasEJBClientJARProject();
	//            isInDiffEar = getEJBModel().isInDifferentEAR();
	//        }
	//
	//        if (aRef == null || owner == null || linkedEjb == null)
	//            return null;
	//        String jndiName = getJndiName(linkedEjb);
	//        if (jndiName == null) {
	//            return null;
	//        }
	//
	//        ModifierHelper modifier = new ModifierHelper();
	//        if (isEJBOwner)
	//            modifier.setFeature(EjbbndFactoryImpl.getPackage().getEnterpriseBeanBinding_EjbRefBindings());
	//        else if (isWebOwner)
	//        	modifier.setFeature(WebappbndPackage.eINSTANCE.getWebAppBinding_EjbRefBindings());
	//        else
	//            modifier.setFeature(ClientbndFactoryImpl.getPackage().getApplicationClientBinding_EjbRefs());
	//        CommonbndPackage pack = CommonbndFactoryImpl.getPackage();
	//        modifier.addAttribute(pack.getEjbRefBinding_BindingEjbRef(), aRef);
	//        modifier.addAttribute(pack.getEjbRefBinding_JndiName(), jndiName);
	//        if (isEJBOwner) {
	//            EnterpriseBeanBinding ejbBinding = EJBBindingsHelper.getEjbBinding((EnterpriseBean) owner);
	//            modifier.setOwner(ejbBinding);
	//            new JNDINameValueHolder(aRef, jndiName, ejbBinding);
	//        } else if (isWebOwner) {
	//        	WebAppBinding webBinding = WebAppBindingsHelper.getWebAppBinding((WebApp) owner);
	//			modifier.setOwner(webBinding);
	//        } else
	//            modifier.setOwner(ApplicationClientBindingsHelper.getApplicationClientBinding((ApplicationClient)
	// owner));
	//        return modifier;
	//    }
}