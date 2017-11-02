/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
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
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.web.IWebFragmentResource;
import org.eclipse.jst.javaee.web.WebAppVersionType;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.javaee.web.WebFragment;
import org.eclipse.jst.javaee.webfragment.WebAppDeploymentDescriptor;
import org.eclipse.jst.javaee.webfragment.WebfragmentFactory;

public class WebFragment30ModelProvider extends JEE5ModelProvider {
	
	private static final String WEBFRAGMENT_CONTENT_TYPE = "org.eclipse.jst.jee.ee6webfragmentDD"; //$NON-NLS-1$
	public WebFragment30ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.WEBFRAGMENT_DD_URI));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	@Override
	public Object getModelObject(IPath modelPath) {
		IWebFragmentResource webFragmentRes = (IWebFragmentResource)getModelResource(modelPath);
		if (webFragmentRes != null && webFragmentRes.getRootObject() != null) 
			return webFragmentRes.getWebFragment();
		return null;
	}
	@Override
	protected String getContentTypeDescriber() {
		return WEBFRAGMENT_CONTENT_TYPE;
	}
	@Override
	public void populateRoot(XMLResourceImpl res, String name) {
		WebAppDeploymentDescriptor dd = WebfragmentFactory.eINSTANCE.createWebAppDeploymentDescriptor();
		dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE_NS_URL);  //$NON-NLS-1$
		dd.getXMLNSPrefixMap().put("xsi", J2EEConstants.XSI_NS_URL); //$NON-NLS-1$
		
		WebFragment webFragment = WebFactory.eINSTANCE.createWebFragment();
		DisplayName dn = JavaeeFactory.eINSTANCE.createDisplayName();
		dn.setValue(name);
		webFragment.getDisplayNames().add(dn);
		
		String version = J2EEProjectUtilities.getJ2EEProjectVersion(proj);
		if(version != null && version.equals(J2EEVersionConstants.VERSION_3_0_TEXT)) {
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_3_0);
			webFragment.setVersion(WebAppVersionType._30_LITERAL);
		}
		else if (version != null && version.equals(J2EEVersionConstants.VERSION_3_1_TEXT)) {
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_3_1);
			// note that later EMF generations remove the _LITERAL from the enum
			webFragment.setVersion(WebAppVersionType._31);
		}
		else
		{
			dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE7_NS_URL);  //$NON-NLS-1$
			dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE7_NS_URL, J2EEConstants.WEBFRAGMENT_SCHEMA_LOC_4_0);
			// note that later EMF generations remove the _LITERAL from the enum
			webFragment.setVersion(WebAppVersionType._40);
		}
		dd.setWebFragment(webFragment);		
		res.getContents().add((EObject) dd);
	}

}
