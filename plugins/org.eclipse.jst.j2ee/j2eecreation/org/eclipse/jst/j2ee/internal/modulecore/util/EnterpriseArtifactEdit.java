/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.modulecore.util.ArtifactEdit;

/**
 * @author cbridgha
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class EnterpriseArtifactEdit extends ArtifactEdit {
	public abstract int getJ2EEVersion();
	public abstract EObject getDeploymentDescriptorRoot();
}
