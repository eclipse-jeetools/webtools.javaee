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
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.javaee.applicationclient.ApplicationClient;
import org.eclipse.jst.javaee.applicationclient.ApplicationClientDeploymentDescriptor;
import org.eclipse.jst.javaee.applicationclient.ApplicationclientFactory;
import org.eclipse.jst.javaee.applicationclient.IApplicationClientResource;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;

public class AppClient5ModelProvider extends JEE5ModelProvider {
	
	private static final String APPCLIENT5_CONTENT_TYPE = "org.eclipse.jst.jee.ee5appclientDD"; //$NON-NLS-1$
	public AppClient5ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.APP_CLIENT_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public Object getModelObject(IPath modelPath) {
		IApplicationClientResource appRes = (IApplicationClientResource)getModelResource(modelPath);
		if (appRes != null && appRes.getRootObject() != null) 
			return appRes.getApplicationClient();
		return null;
	}
	@Override
	protected String getContentTypeDescriber() {
		return APPCLIENT5_CONTENT_TYPE;
	}
	@Override
	public void populateRoot(XMLResourceImpl res, String name) {
		ApplicationClientDeploymentDescriptor dd = ApplicationclientFactory.eINSTANCE.createApplicationClientDeploymentDescriptor();
		dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE_NS_URL);  //$NON-NLS-1$
		dd.getXMLNSPrefixMap().put("xsi", J2EEConstants.XSI_NS_URL); //$NON-NLS-1$
		
		ApplicationClient client = ApplicationclientFactory.eINSTANCE.createApplicationClient();
		DisplayName dn = JavaeeFactory.eINSTANCE.createDisplayName();
		dn.setValue(name);
		client.getDisplayNames().add(dn);
		
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(proj);
		if(version != null && version.equals(J2EEVersionConstants.VERSION_5_0_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.APP_CLIENT_SCHEMA_LOC_5);
			client.setVersion(J2EEVersionConstants.VERSION_5_TEXT);
		}
		else if(version != null && version.equals(J2EEVersionConstants.VERSION_6_0_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.APP_CLIENT_SCHEMA_LOC_6);
			client.setVersion(J2EEVersionConstants.VERSION_6_TEXT);
		}
		else {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.APP_CLIENT_SCHEMA_LOC_7);
			client.setVersion(J2EEVersionConstants.VERSION_7_TEXT);
		}

		dd.setApplicationClient(client);
		res.getContents().add((EObject) dd);
	}


}
