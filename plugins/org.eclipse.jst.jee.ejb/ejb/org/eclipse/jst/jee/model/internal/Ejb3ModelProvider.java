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
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EJBJarDeploymentDescriptor;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.internal.util.EjbResourceImpl;

public class Ejb3ModelProvider extends JEE5ModelProvider {
	
	private static final String EJB3_CONTENT_TYPE = "org.eclipse.jst.jee.ee5ejbDD"; //$NON-NLS-1$
	public Ejb3ModelProvider(IProject proj) {
		super();
		this.proj = proj;
		setDefaultResourcePath(new Path(J2EEConstants.EJBJAR_DD_URI));
	}


	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.model.IModelProvider#getModelObject(org.eclipse.core.runtime.IPath)
	 */
	public Object getModelObject(IPath modelPath) {
		EjbResourceImpl ejbRes = (EjbResourceImpl)getModelResource(modelPath);
		if (ejbRes != null && ejbRes.getContents().size() > 0) 
			return ejbRes.getEjbJar();
		return null;
	}
	protected String getContentTypeDescriber() {
		return EJB3_CONTENT_TYPE;
	}


	public void populateRoot(XMLResourceImpl res, String name) {
		EJBJarDeploymentDescriptor dd = EjbFactory.eINSTANCE.createEJBJarDeploymentDescriptor();
		dd.getXMLNSPrefixMap().put("", J2EEConstants.JAVAEE_NS_URL);  //$NON-NLS-1$
		dd.getXMLNSPrefixMap().put("xsi", J2EEConstants.XSI_NS_URL); //$NON-NLS-1$
		dd.getXSISchemaLocation().put(J2EEConstants.JAVAEE_NS_URL, J2EEConstants.EJB_JAR_SCHEMA_LOC_3_0);
		EJBJar jar = EjbFactory.eINSTANCE.createEJBJar();
		DisplayName dn = JavaeeFactory.eINSTANCE.createDisplayName();
		dn.setValue(name);
		jar.getDisplayNames().add(dn);
		dd.setEjbJar(jar);
		jar.setVersion(J2EEVersionConstants.VERSION_3_0_TEXT);
		res.getContents().add((EObject) dd);
	}



}
