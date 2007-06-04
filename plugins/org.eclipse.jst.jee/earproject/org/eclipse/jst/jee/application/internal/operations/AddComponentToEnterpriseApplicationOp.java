/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.application.internal.operations;

import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jee.application.ICommonApplication;
import org.eclipse.jst.jee.application.ICommonModule;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
//TODO this is temporary until we have jee 5 model support ready
public class AddComponentToEnterpriseApplicationOp extends org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationOp {
	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

	public AddComponentToEnterpriseApplicationOp(IDataModel model) {
		super(model);
	}
	protected void updateEARDD(IProgressMonitor monitor) {
		
		StructureEdit se = null;
		try {
			IVirtualComponent sourceComp = (IVirtualComponent) model.getProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT);
			final IEARModelProvider earModel = (IEARModelProvider)ModelProviderManager.getModelProvider(sourceComp.getProject());
			
			se = StructureEdit.getStructureEditForWrite(sourceComp.getProject());
			if (earModel != null) {
				List list = (List) model.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				final Map map = (Map) model.getProperty(IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP);
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						StructureEdit compse = null;
						final IVirtualComponent wc = (IVirtualComponent) list.get(i);
						WorkbenchComponent earwc = se.getComponent();
						try {
							compse = StructureEdit.getStructureEditForWrite(wc.getProject());
							WorkbenchComponent refwc = compse.getComponent();
							final ReferencedComponent ref = se.findReferencedComponent(earwc, refwc);
							earModel.modify(new Runnable() {
								public void run() {
									final ICommonApplication application = (ICommonApplication)earModel.getModelObject();
									if(application != null) {
										ICommonModule mod = addModule(application, wc, (String) map.get(wc));
										if (ref!=null)
											ref.setDependentObject((EObject)mod);
									}
								}
							}, null);
						} finally {
							if (compse != null) {
								compse.saveIfNecessary(monitor);
								compse.dispose();
							}
						}
					}
				}
			}
			se.saveIfNecessary(monitor);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			if (se != null)
				se.dispose();
		}
	}

//	protected Module createNewModule(IVirtualComponent wc) {
//		if (J2EEProjectUtilities.isDynamicWebProject(wc.getProject())) {
//			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createWebModule();
//		} else if (J2EEProjectUtilities.isEJBProject(wc.getProject())) {
//			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createEjbModule();
//		} else if (J2EEProjectUtilities.isApplicationClientProject(wc.getProject())) {
//			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createJavaClientModule();
//		} else if (J2EEProjectUtilities.isJCAProject(wc.getProject())) {
//			return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createConnectorModule();
//		}
//		return null;
//	}

//	protected Module addModule(Application application, IVirtualComponent wc, String name) {
//		Application dd = application;
//		Module existingModule = dd.getFirstModule(name);
//
//		if (existingModule == null) {
//			existingModule = createNewModule(wc);
//			if (existingModule != null) {
//				existingModule.setUri(name);
//				if (existingModule instanceof WebModule) {
//					Properties props = wc.getMetaProperties();
//					String contextroot = ""; //$NON-NLS-1$
//					if ((props != null) && (props.containsKey(J2EEConstants.CONTEXTROOT)))
//						contextroot = props.getProperty(J2EEConstants.CONTEXTROOT);
//					((WebModule) existingModule).setContextRoot(contextroot);
//				}
//				dd.getModules().add(existingModule);
//			}
//		}
//		return existingModule;
//	}

	private static IProgressMonitor submon(final IProgressMonitor parent, final int ticks) {
		return (parent == null ? null : new SubProgressMonitor(parent, ticks));
	}

}
