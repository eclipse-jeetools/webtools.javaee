/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 23, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceFilesContribution {

	protected WebServiceDescription webService;
	protected List allFiles = null;

	/**
	 * Constructor
	 */
	public WebServiceFilesContribution(WebServiceDescription webService) {
		super();
		this.webService = webService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return WebServiceUIResourceHandler.WebServiceFilesContribution_UI_0; 
	}

	public String getWSDLFile() {
		return webService.getWsdlFile();
	}

	public String getMappingFile() {
		return webService.getJaxrpcMappingFile();
	}

	public List getAllFiles() {
		if (allFiles == null) {
			allFiles = new ArrayList();
			allFiles.add(getWSDLFile());
			allFiles.add(getMappingFile());
		}
		return allFiles;
	}

	/**
	 * @return Returns the webService.
	 */
	public WebServiceDescription getWebService() {
		return webService;
	}

}
