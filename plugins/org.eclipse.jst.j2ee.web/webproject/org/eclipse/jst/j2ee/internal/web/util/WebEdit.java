/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;

/**
 * @author cbridgha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class WebEdit extends EnterpriseArtifactEdit {
	
	public static String TYPE_ID = "WEBAPP_TYPE";
	/**
	 * @param model
	 */
	public WebEdit(ArtifactEditModel model) {
		super(model);
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {
		return getWebApplicationXmiResource().getJ2EEVersionID();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.modulecore.util.EnterpriseArtifactEdit#getDeploymentDescriptorRoot()
	 */
	public EObject getDeploymentDescriptorRoot() {
		// TODO Auto-generated method stub
		return null;
	}
	public WebAppResource getWebApplicationXmiResource() {
		return (WebAppResource)getDeploymentDescriptorResource();
	}

	public Resource getDeploymentDescriptorResource() {
		return getArtifactEditModel().getResource(URI.createURI(ArchiveConstants.WEBAPP_DD_URI));
	}
	public WebApp getWebApplication() {
		return (WebApp)getDeploymentDescriptorRoot();
	}
	public int getServletVersion() {
		return getWebApplicationXmiResource().getModuleVersionID();
	}
}
