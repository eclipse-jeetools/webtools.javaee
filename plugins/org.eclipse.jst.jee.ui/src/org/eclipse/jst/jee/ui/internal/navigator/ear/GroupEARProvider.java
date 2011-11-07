/***********************************************************************
 * Copyright (c) 2008, 2010 by SAP AG, Walldorf. 
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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.j2ee.componentcore.util.EARVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.javaee.application.Application;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * Ear 5 Deployment descriptor node.
 * 
 * @author Dimitar Giormov
 * @author Kaloyan Raev
 */
public class GroupEARProvider extends AbstractEarGroupProvider implements IAdaptable {

	private static final String PROJECT_RELATIVE_PATH = "META-INF/application.xml"; //$NON-NLS-1$
	public final static String EAR_DEFAULT_LIB = "lib"; //$NON-NLS-1$
	private static Image ear50Image;
	private static Image ear60Image;
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

	@Override
	public String getText() {
		return NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, earComponent.getName());
	}

	public EARVirtualComponent getEARVirtualComponent() {
		return this.earComponent;
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		IProject project = getProject();

		try {
			IFacetedProject facetedProject = ProjectFacetsManager.create(project);
			//EE6TODO
			if (facetedProject != null && 
					(facetedProject.hasProjectFacet(
							ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE).getVersion(
									J2EEVersionConstants.VERSION_5_0_TEXT)) || facetedProject.hasProjectFacet(
											ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE).getVersion(
													J2EEVersionConstants.VERSION_6_0_TEXT)))) {

				if (bundledLibsNode == null) {
					BundledNode bundledLibsDirectoryNode = new BundledNode(project, Messages.LIBRARY_DIRECTORY + ": " + EarUtilities.getEARLibDir(earComponent), null);//$NON-NLS-1$
					bundledLibsNode = new BundledNode(project, Messages.BUNDLED_LIBRARIES_NODE, bundledLibsDirectoryNode);
				} else {
					bundledLibsNode.getBundledLibsDirectoryNode().setText(Messages.LIBRARY_DIRECTORY + ": " + EarUtilities.getEARLibDir(earComponent));//$NON-NLS-1$
				}
				
				if(modulesNode == null){
					modulesNode = new ModulesNode(project);
				}
				

				children.add(modulesNode);
				children.add(bundledLibsNode);
			}
		} catch (CoreException e) {
			String msg = "Error in the JEEContentProvider.getChildren() for parent:" +  this; //$NON-NLS-1$
			JEEUIPlugin.logError(msg, e);
		}
		return children;
	}

	@Override
	public Image getImage() {
		String version = ((Application) javaee).getVersion();
		if (J2EEVersionConstants.VERSION_5_TEXT.equals(version)) {
			return getEar50Image();
		} else if (J2EEVersionConstants.VERSION_6_TEXT.equals(version)) {
			return getEar60Image();
		}
		return getEar50Image();
	}

	public IFile getDDFile() {
		if (ddFile != null){
			return ddFile;
		}
		IVirtualFolder virtualFolder = ComponentCore.createComponent(getProject()).getRootFolder();
		ddFile = virtualFolder.getFile(PROJECT_RELATIVE_PATH).getUnderlyingFile();
		return ddFile;
	}
	
	public Object getAdapter(Class adapter) {
		if (IProject.class == adapter){
			return getProject();
		}
		return null;
	}

	private Image getEar50Image() {
		if (ear50Image == null) {
			ear50Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.EAR_IMAGE).createImage();
		}
		return ear50Image;
	}

	private Image getEar60Image() {
		if (ear60Image == null) {
			ear60Image = JEEUIPlugin.getDefault().getImageDescriptor(JEEUIPluginIcons.EAR6_IMAGE).createImage();
		}
		return ear60Image;
	}

}
