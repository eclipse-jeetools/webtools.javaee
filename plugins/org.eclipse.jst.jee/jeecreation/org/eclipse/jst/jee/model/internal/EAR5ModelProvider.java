/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.model.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IEARModelProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.javaee.application.ApplicationDeploymentDescriptor;
import org.eclipse.jst.javaee.application.ApplicationFactory;
import org.eclipse.jst.javaee.application.IApplicationResource;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EAR5ModelProvider extends JEE5ModelProvider implements IEARModelProvider {
	
	private static final String EAR5_CONTENT_TYPE = "org.eclipse.jst.jee.ee5earDD"; //$NON-NLS-1$
	public EAR5ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.APPLICATION_DD_URI));
	}
	@Override
	protected String getContentTypeDescriber() {
		return EAR5_CONTENT_TYPE;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public Object getModelObject(IPath modelPath) {
		IApplicationResource earRes = (IApplicationResource)getModelResource(modelPath);
		if (earRes != null && earRes.getRootObject() != null) 
			return earRes.getApplication();
		return null;
	}

	public String getModuleURI(IVirtualComponent moduleComp) {
		IVirtualComponent comp = ComponentCore.createComponent(proj);
		IVirtualReference [] refs = comp.getReferences();
		for(int i=0; i<refs.length; i++){
			if(refs[i].getDependencyType() == IVirtualReference.DEPENDENCY_TYPE_USES){
				if(refs[i].getReferencedComponent().equals(moduleComp)){
					return refs[i].getArchiveName();
				}
			}
		}
		return null;		
	}
	/**
	 * This method will return the context root in this application for the passed in web project.
	 * 
	 * @param webProject
	 * @return contextRoot String
	 */
	public String getWebContextRoot(IProject webProject) {
		if (webProject == null || !JavaEEProjectUtilities.isDynamicWebProject(webProject))
			return null;
		IVirtualComponent webComp = ComponentCore.createComponent(webProject);
		String webModuleURI = getModuleURI(webComp);
		if (webModuleURI != null) {
			Module webModule = ((Application)getModelObject()).getModule(webModuleURI, null);
			if (webModule != null)
				return webModule.getWeb().getContextRoot();
		}
		return null;
	}
	@Override
	public void populateRoot(XMLResourceImpl res, String name) {
		ApplicationDeploymentDescriptor dd = ApplicationFactory.eINSTANCE.createApplicationDeploymentDescriptor();
		dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE_NS_URL);  //$NON-NLS-1$
		dd.getXMLNSPrefixMap().put("xsi", J2EEConstants.XSI_NS_URL); //$NON-NLS-1$
		
		Application ear = ApplicationFactory.eINSTANCE.createApplication();
		DisplayName dn = JavaeeFactory.eINSTANCE.createDisplayName();
		dn.setValue(name);
		ear.getDisplayNames().add(dn);
		
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(proj);
		if(version != null && version.equals(J2EEVersionConstants.VERSION_5_0_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.APPLICATION_SCHEMA_LOC_5);
			ear.setVersion(J2EEVersionConstants.VERSION_5_TEXT);
		}
		else if(version != null && version.equals(J2EEVersionConstants.VERSION_6_0_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.APPLICATION_SCHEMA_LOC_6);
			ear.setVersion(J2EEVersionConstants.VERSION_6_TEXT);
		} else {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.APPLICATION_SCHEMA_LOC_7);
			ear.setVersion(J2EEVersionConstants.VERSION_7_TEXT);
		}

		dd.setApplication(ear);
		res.getContents().add((EObject) dd);
	}

	/**
	 * This method will set the context root on the application for the passed in contextRoot.
	 * 
	 * @param webProject
	 * @param aContextRoot
	 */
	public void setWebContextRoot(IProject webProject, String aContextRoot) {
		if (webProject == null || !JavaEEProjectUtilities.isDynamicWebProject(webProject))
			return;
		IVirtualComponent webComp = ComponentCore.createComponent(webProject);
		String webModuleURI = getModuleURI(webComp);
		if (webModuleURI != null) {
			WebModule webModule = (WebModule) ((Application)getModelObject()).getModule(webModuleURI, null);
			if (webModule != null)
				webModule.setContextRoot(aContextRoot);
			// TODO - should this method save?  Or should the caller be required to save?
		}
	}
}
