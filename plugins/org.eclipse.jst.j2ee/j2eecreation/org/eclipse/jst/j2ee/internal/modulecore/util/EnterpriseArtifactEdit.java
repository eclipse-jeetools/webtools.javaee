/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;

/**
 * @author cbridgha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class EnterpriseArtifactEdit extends ArtifactEdit {
	/**
	 * @param model
	 */
	public EnterpriseArtifactEdit(ArtifactEditModel model) {
		super(model);
	}
	public abstract int getJ2EEVersion();
	public abstract Resource getDeploymentDescriptorResource();
	public EObject getDeploymentDescriptorRoot() {
		Resource res = getDeploymentDescriptorResource();
		return (EObject)res.getContents().get(0);
	}
}
