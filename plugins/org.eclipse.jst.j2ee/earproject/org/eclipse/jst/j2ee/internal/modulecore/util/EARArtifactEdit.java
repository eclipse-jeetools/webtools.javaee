/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;

/**
 * @author cbridgha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EARArtifactEdit extends EnterpriseArtifactEdit {

	public static String TYPE_ID = "EAR_TYPE";
	/**
	 * @param model
	 */
	public EARArtifactEdit(ArtifactEditModel model) {
		super(model);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {
		
		return getApplicationXmiResource().getJ2EEVersionID();
	}
	public ApplicationResource getApplicationXmiResource() {
		return (ApplicationResource)getDeploymentDescriptorResource();
	}

	public Application getApplication() {
		return (Application)getDeploymentDescriptorRoot();
	}

	public Resource getDeploymentDescriptorResource() {
		return getArtifactEditModel().getResource(URI.createURI(ArchiveConstants.APPLICATION_DD_URI));
	}
}
