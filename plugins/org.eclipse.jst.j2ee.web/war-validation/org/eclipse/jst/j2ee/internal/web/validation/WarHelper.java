/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.validation;



//import org.eclipse.wst.validation.internal.core.core.IMessage;
import java.util.Hashtable;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.validation.J2EEValidationHelper;
import org.eclipse.jst.j2ee.model.internal.validation.WARMessageConstants;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;



public class WarHelper extends J2EEValidationHelper {
	Hashtable warFileMap = new Hashtable();
	final static String HelperID = "org.eclipse.wst.validation.internal.core.war.workbenchimpl.WarHelper"; //$NON-NLS-1$


	/**
	 * WarHelper constructor comment.
	 */

	public WarHelper() {
		super();

		registerModel(WARMessageConstants.WAR_MODEL_NAME, "loadWarFile"); //$NON-NLS-1$
	}

	/**
	 * This method will be invoked indirectly from the WarValidator (via. the WorkbenchReporter). It
	 * will expect the coming object to be a EObject for the node who generated this message. Note:
	 * This methods may need to be implemented higher up in the class stack.
	 */
	public int getLineNo(Object object) {


		if (object instanceof Integer) { // Error discovered using the XML parser
			return ((Integer) object).intValue();
		}

		return super.getLineNo(object);
	}

	/**
	 * Given a resource, return its non-eclipse-specific location. If this resource, or type of
	 * resource, isn't handled by this helper, return null.
	 */
	public String getPortableName(IResource resource) {
		if (!(resource instanceof IFile)) {
			return null;
		}

		IPath resourcePath = resource.getFullPath();
		if (resourcePath != null)
			return resourcePath.toString();

		return null;
	}

	/**
	 * Similar to the getFile() method, we know that we are talking about web.xml, but need a unique
	 * ID.
	 * 
	 * @return java.lang.String
	 * @param object
	 *            Object
	 */
	public String getTargetObjectName(Object object) {
		String ret = null;
		if (object != null) {
			IFile warFile = (IFile) warFileMap.get(getProject().toString());// validator framework
																			// will call getFile()
																			// first.
			if (warFile != null) {
				ret = (warFile.toString() + HelperID);
			}
		}
		return ret;
	}

	
	//public EObject loadWarFile(ComponentHandle handle) {
	public EObject loadWarFile() {
			IVirtualComponent comp = ComponentCore.createComponent(getProject());
			ArtifactEdit edit = ComponentUtilities.getArtifactEditForRead(comp);
			
			try {
				Archive archive = ((WebArtifactEdit) edit).asArchive(false);
				return archive;
			} catch (OpenFailureException e1) {
				Logger.getLogger().log(e1);
			}finally {
				if (edit != null) {
					edit.dispose();
				}
			}
		return null;
	}	
	
}
