/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ear;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractGroupProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Ear 5 Deployment descriptor node.
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupEARProvider extends AbstractGroupProvider {

	private static final String PROJECT_RELATIVE_PATH = "META-INF/application.xml"; //$NON-NLS-1$
	public final static String EAR_DEFAULT_LIB = "lib"; //$NON-NLS-1$
	private static Image EAR_IMAGE;
	private EARVirtualComponent earComponent;
	private IFile ddFile;
	
	private BundledNode bundledLibsNode;
	private ModulesNode modulesNode;
	
	public GroupEARProvider(Application application, EARVirtualComponent wtpComponent) {
		super(application);
		earComponent = wtpComponent;
	}

	public IProject getProject() {
		return earComponent.getProject();
	}

	public String getText() {
		return "Deployment Descriptor: " + earComponent.getName(); //$NON-NLS-1$
	}

	public EARVirtualComponent getEARVirtualComponent() {
		return this.earComponent;
	}

	public List getChildren() {
		List children = new ArrayList();
		IProject project = getProject();

		IVirtualComponent projectComponent = ComponentCore.createComponent(project);
		try {
			IFacetedProject facetedProject = ProjectFacetsManager.create(project);
			if (facetedProject != null && 
					facetedProject.hasProjectFacet(
							ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE).getVersion(
									J2EEVersionConstants.VERSION_5_0_TEXT))) {

				if(bundledLibsNode == null){
					BundledNode bundledLibsDirectoryNode = new BundledNode(project, Messages.LIBRARY_DIRECTORY + ": /" + EAR_DEFAULT_LIB, null);//$NON-NLS-1$
					bundledLibsNode = new BundledNode(project, Messages.BUNDLED_LIBRARIES_NODE, bundledLibsDirectoryNode);
				}
				
				if(modulesNode == null){
					modulesNode = new ModulesNode(project);
				}
				

				children.add(modulesNode);
				children.add(bundledLibsNode);
			}
		} catch (CoreException e) {
			String msg = "Error in the JEEContentProvider.getChildren() for parent:" +  this; //$NON-NLS-1$
			JEEUIPlugin.getDefault().logError(msg, e);
		}
		return children;
	}

	public Image getImage() {
		return getEarImage();
	}

	public static Image getEarImage() {
		if (EAR_IMAGE == null) {
			ImageDescriptor imageDescriptor = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.EAR_IMAGE);
			EAR_IMAGE = imageDescriptor.createImage();
		}
		return EAR_IMAGE;
	}

	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}
	public IFile getDDFile() {
		if (ddFile != null){
			return ddFile;
		}
		IVirtualFolder virtualFolder = ComponentCore.createComponent(getProject()).getRootFolder();
		ddFile = virtualFolder.getFile(PROJECT_RELATIVE_PATH).getUnderlyingFile();
		return ddFile;
	}

}
