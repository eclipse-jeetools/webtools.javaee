/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
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
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.javaee.jca.ConnectorDeploymentDescriptor;
import org.eclipse.jst.javaee.jca.IConnectorResource;
import org.eclipse.jst.javaee.jca.JcaFactory;

public class Connector16ModelProvider extends JEE5ModelProvider {

	private static final String CONNECTOR16_CONTENT_TYPE = "org.eclipse.jst.jee.ee6connectorDD"; //$NON-NLS-1$
	public Connector16ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.RAR_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public Object getModelObject(IPath modelPath) {
		IConnectorResource connectorRes = (IConnectorResource)getModelResource(modelPath);
		if (connectorRes != null && connectorRes.getRootObject() != null)
			return connectorRes.getConnector();
		return null;
	}
	@Override
	protected String getContentTypeDescriber() {
		return CONNECTOR16_CONTENT_TYPE;
	}
	@Override
	public void populateRoot(XMLResourceImpl res, String name) {
		ConnectorDeploymentDescriptor dd = JcaFactory.eINSTANCE.createConnectorDeploymentDescriptor();
		dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE_NS_URL);  //$NON-NLS-1$
		dd.getXMLNSPrefixMap().put("xsi", J2EEConstants.XSI_NS_URL); //$NON-NLS-1$

		Connector connector = JcaFactory.eINSTANCE.createConnector();
		DisplayName dn = JavaeeFactory.eINSTANCE.createDisplayName();
		dn.setValue(name);
		connector.getDisplayNames().add(dn);

		String version = J2EEProjectUtilities.getJ2EEProjectVersion(proj);
		if(version != null && version.equals(J2EEVersionConstants.VERSION_1_6_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.CONNECTOR_SCHEMA_LOC_1_6);
			connector.setVersion(J2EEVersionConstants.VERSION_1_6_TEXT);
		}
		else if(version != null && version.equals(J2EEVersionConstants.VERSION_2_1_TEXT)) {
            dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.CONNECTOR_SCHEMA_LOC_2_1);
            connector.setVersion(J2EEVersionConstants.VERSION_2_1_TEXT);
        }
		else 
		{
			// default to 1.7 for now
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.CONNECTOR_SCHEMA_LOC_1_7);
			connector.setVersion(J2EEVersionConstants.VERSION_1_7_TEXT);
		}
		dd.setConnector(connector);
		res.getContents().add((EObject) dd);
	}

}
