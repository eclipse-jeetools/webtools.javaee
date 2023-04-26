/*******************************************************************************
 * Copyright (c) 2005, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.web.IWebResource;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebAppDeploymentDescriptor;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFactory;

public class Web25ModelProvider extends JEE5ModelProvider {
	
	private static final String WAR25_CONTENT_TYPE = "org.eclipse.jst.jee.ee5webDD"; //$NON-NLS-1$
	public Web25ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.WEBAPP_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public Object getModelObject(IPath modelPath) {
		IWebResource webRes = (IWebResource)getModelResource(modelPath);
		if (webRes != null && webRes.getRootObject() != null) 
			return webRes.getWebApp();
		return null;
	}
	@Override
	protected String getContentTypeDescriber() {
		return WAR25_CONTENT_TYPE;
	}
	@Override
	public void populateRoot(XMLResourceImpl res, String name) {
		WebAppDeploymentDescriptor dd = WebFactory.eINSTANCE.createWebAppDeploymentDescriptor();
		dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE_NS_URL);  //$NON-NLS-1$
		dd.getXMLNSPrefixMap().put("xsi", J2EEConstants.XSI_NS_URL); //$NON-NLS-1$
		
		WebApp war = WebFactory.eINSTANCE.createWebApp();
		DisplayName dn = JavaeeFactory.eINSTANCE.createDisplayName();
		dn.setValue(name);
		war.getDisplayNames().add(dn);
		
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(proj);
		if (version != null && version.equals(J2EEVersionConstants.VERSION_2_5_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.WEB_APP_SCHEMA_LOC_2_5);
			war.setVersion(WebAppVersionType._25_LITERAL);
		}
		else if (version != null && version.equals(J2EEVersionConstants.VERSION_3_0_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.WEB_APP_SCHEMA_LOC_3_0);
			war.setVersion(WebAppVersionType._30_LITERAL);
		}
		else if (version != null && version.equals(J2EEVersionConstants.VERSION_3_1_TEXT)) {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.WEB_APP_SCHEMA_LOC_3_1);
			// note that later EMF generations remove the _LITERAL from the enum
			war.setVersion(WebAppVersionType._31);
		}
		else if (J2EEVersionConstants.VERSION_4_0_TEXT.equals(version)) {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.WEB_APP_SCHEMA_LOC_4_0);
			// note that later EMF generations remove the _LITERAL from the enum
			war.setVersion(WebAppVersionType._40);
		}
		else if (J2EEVersionConstants.VERSION_5_0_TEXT.equals(version)) {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAKARTAEE9_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAKARTAEE9_NS_URL, J2EEConstants.WEB_APP_SCHEMA_LOC_5_0);
			// note that later EMF generations remove the _LITERAL from the enum
			war.setVersion(WebAppVersionType._50);
		}
		else { //if (J2EEVersionConstants.VERSION_6_0_TEXT.equals(version)) {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAKARTAEE10_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAKARTAEE10_NS_URL, J2EEConstants.WEB_APP_SCHEMA_LOC_6_0);
			// note that later EMF generations remove the _LITERAL from the enum
			war.setVersion(WebAppVersionType._60);
		}
		dd.setWebApp(war);
		res.getContents().add((EObject) dd);
	}

}
