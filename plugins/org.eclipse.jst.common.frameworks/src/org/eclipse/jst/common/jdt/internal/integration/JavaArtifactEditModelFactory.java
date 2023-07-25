/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.integration;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public class JavaArtifactEditModelFactory extends EditModelFactory {
	
	public static final String MODULE_EDIT_MODEL_ID = "org.eclipse.jst.modulecore.editModel"; //$NON-NLS-1$
	
	public static final String PARAM_MODULE_URI = "MODULE_URI"; //$NON-NLS-1$
	
	public static final String PARAM_ROOT_URI = "ROOT_URI"; //$NON-NLS-1$
	
	public static final String PARAM_ROOT_CONTENT_TYPE = "ROOT_CONTENT_TYPE"; //$NON-NLS-1$
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForRead(java.lang.String, org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext, java.util.Map)
	 */
	@Override
	public EditModel createEditModelForRead(String editModelID, EMFWorkbenchContext context, Map params) {
		URI moduleURI = (URI) ((params != null) ? params.get(PARAM_MODULE_URI) : null);
		URI rootURI = (URI) ((params != null) ? params.get(PARAM_ROOT_URI) : null);
		String rootContentType = (String) ((params != null) ? params.get(PARAM_ROOT_CONTENT_TYPE) : null);
		if(moduleURI == null)
			throw new IllegalStateException("A Module URI must be provided"); //$NON-NLS-1$
		
		return  new JavaArtifactEditModel(editModelID, context, true, moduleURI,rootURI,rootContentType);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#createEditModelForWrite(java.lang.String, org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext, java.util.Map)
	 */
	@Override
	public EditModel createEditModelForWrite(String editModelID, EMFWorkbenchContext context, Map params) {
		URI moduleURI = (URI) ((params != null) ? params.get(PARAM_MODULE_URI) : null);
		URI rootURI = (URI) ((params != null) ? params.get(PARAM_ROOT_URI) : null);
		String rootContentType = (String) ((params != null) ? params.get(PARAM_ROOT_CONTENT_TYPE) : null);
		if(moduleURI == null)
			throw new IllegalStateException("A Module URI must be provided"); //$NON-NLS-1$
		return  new JavaArtifactEditModel(editModelID, context, false,false, moduleURI,rootURI,rootContentType);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emfworkbench.integration.EditModelFactory#getCacheID(java.lang.String, java.util.Map)
	 */
	@Override
	public String getCacheID(String editModelID, Map params) { 
		URI moduleURI = (URI)params.get(PARAM_MODULE_URI);
		if(moduleURI != null)
			return editModelID+":"+moduleURI.toString(); //$NON-NLS-1$
		return editModelID+":NOURI"; //$NON-NLS-1$
	}

	
}
