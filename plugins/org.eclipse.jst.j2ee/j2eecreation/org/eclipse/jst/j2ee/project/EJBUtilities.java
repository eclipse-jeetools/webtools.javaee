/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project;

import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jee.archive.ArchiveModelLoadException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class EJBUtilities extends JavaEEProjectUtilities {

	public EJBUtilities() {
	}

	public static IVirtualComponent getEJBClientJar(IVirtualComponent ejbComponent){
		if(!ejbComponent.isBinary()){
			return getEJBClientJar(ejbComponent.getProject());
		} 
		if(ejbComponent.isBinary()){
			JavaEEBinaryComponentHelper helper = null; 
			try{
				helper = new JavaEEBinaryComponentHelper(ejbComponent);
				IArchive archive = null;
				try {
					archive = helper.accessArchive();
					IPath ddPath = new Path("META-INF/ejb-jar.xml"); //$NON-NLS-1$
					if(archive.containsArchiveResource(ddPath)){
						Object jar = archive.getModelObject(ddPath);
						String clientJAR = null;
						if (jar != null)
						{
							if (jar instanceof org.eclipse.jst.j2ee.ejb.EJBJar)
							{
								clientJAR = ((org.eclipse.jst.j2ee.ejb.EJBJar)jar).getEjbClientJar();
							}
							else if (jar instanceof org.eclipse.jst.javaee.ejb.EJBJar)
							{
								clientJAR = ((org.eclipse.jst.javaee.ejb.EJBJar)jar).getEjbClientJar();
							}
						}
						if (clientJAR != null) {
							IVirtualComponent earComponent = ComponentCore.createComponent(ejbComponent.getProject());
							IVirtualReference[] refs = earComponent.getReferences();
							for (int i = 0; i < refs.length; i++) {
								if (refs[i].getArchiveName().equals(clientJAR)) {
									return refs[i].getReferencedComponent();
								}
							}
						}
					}
				} catch (ArchiveModelLoadException e) {
					org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
				} finally {
					if(archive != null){
						helper.releaseArchive(archive);
					}
				}
			} finally {
				if(helper != null){
					helper.dispose();
				}
			}
		} 
		return null;
	}
	
	
	/**
	 * This method will return the IVirtualComponent of the EJBClientJar for a given EJB project
	 * @param project
	 * @return IVirtualComponent
	 */
	public static IVirtualComponent getEJBClientJar(IProject ejbProject) {
		Object jar = ModelProviderManager.getModelProvider(ejbProject).getModelObject();
		IVirtualComponent ejbComponent, ejbClientComponent = null;
		ejbComponent = ComponentCore.createComponent(ejbProject);
		if (ejbComponent == null)
			return null;
		Properties props = ejbComponent.getMetaProperties();
		String clientCompName = props.getProperty(CreationConstants.EJB_CLIENT_NAME);
		if (clientCompName != null && !clientCompName.equals("")) { //$NON-NLS-1$
			IVirtualReference vRef = ejbComponent.getReference(clientCompName);
			if (vRef != null)
				ejbClientComponent = vRef.getReferencedComponent();
		} else {
			String clientJAR = null;
			if (jar != null)
			{
				if (jar instanceof org.eclipse.jst.j2ee.ejb.EJBJar)
				{
					clientJAR = ((org.eclipse.jst.j2ee.ejb.EJBJar)jar).getEjbClientJar();
				}
				else if (jar instanceof org.eclipse.jst.javaee.ejb.EJBJar)
				{
					clientJAR = ((org.eclipse.jst.javaee.ejb.EJBJar)jar).getEjbClientJar();
				}
			}
			if (clientJAR != null) {
				clientJAR = clientJAR.substring(0, clientJAR.length() - 4);
				ejbComponent = ComponentCore.createComponent(ejbProject);
				if (ejbComponent == null)
					return null;
				IVirtualReference ref = ejbComponent.getReference(clientJAR);
				if (ref != null)
					ejbClientComponent = ref.getReferencedComponent();
			}
		}
		return ejbClientComponent;
	}

	/**
	 * <p>
	 * Checks is a EJB Client Jar exists for the ejb module project
	 * </p>
	 * 
	 * @return boolean
	 * 
	 */

	public static boolean hasEJBClientJARProject(IProject ejbProject) {

		if (getEJBClientJar(ejbProject) != null)
			return true;
		return false;
	}
}
