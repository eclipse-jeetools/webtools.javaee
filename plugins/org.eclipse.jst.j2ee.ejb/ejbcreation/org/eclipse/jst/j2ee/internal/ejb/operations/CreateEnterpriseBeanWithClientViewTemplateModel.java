/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 26, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.operations;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class CreateEnterpriseBeanWithClientViewTemplateModel extends CreateEnterpriseBeanTemplateModel {
	Boolean hasLocal;
	Boolean hasRemote;

	/**
	 * @param model
	 */
	public CreateEnterpriseBeanWithClientViewTemplateModel(CreateEnterpriseBeanWithClientViewDataModel model) {
		super(model);
	}

	public String getJndiName() {
		//TODO use the real JNDI Name - for now compute a default
		String jndi = null;
		if (hasRemoteClient())
			jndi = getRemoteHome();
		else if (hasLocalClient())
			jndi = getLocalHome();
		if (jndi != null)
			return "ejb/" + jndi.replace('.', '/'); //$NON-NLS-1$
		return ""; //$NON-NLS-1$
	}

	private CreateEnterpriseBeanWithClientViewDataModel getClientViewModel() {
		return (CreateEnterpriseBeanWithClientViewDataModel) model;
	}

	public boolean hasRemoteClient() {
		if (hasRemote == null)
			hasRemote = (Boolean) getClientViewModel().getProperty(CreateEnterpriseBeanWithClientViewDataModel.ADD_REMOTE);
		return hasRemote.booleanValue();
	}

	public String getRemoteHome() {
		return getProperty(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_HOME);
	}

	public String getRemote() {
		return getProperty(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_INTERFACE);
	}

	public boolean hasLocalClient() {
		if (hasLocal == null)
			hasLocal = (Boolean) getClientViewModel().getProperty(CreateEnterpriseBeanWithClientViewDataModel.ADD_LOCAL);
		return hasLocal.booleanValue();
	}

	public String getLocalHome() {
		return getProperty(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_HOME);
	}

	public String getLocal() {
		return getProperty(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_INTERFACE);
	}

	public String getViewType() {
		if (hasRemoteClient() && hasLocalClient())
			return "both"; //$NON-NLS-1$
		else if (hasLocalClient())
			return "local"; //$NON-NLS-1$
		return "remote"; //$NON-NLS-1$
	}

	public String getLocalExtends() {
		return getExtends(CreateEnterpriseBeanWithClientViewDataModel.LOCAL_INTERFACE_EXTENSIONS);
	}

	private String getExtends(String property) {
		String[] ext = (String[]) model.getProperty(property);
		if (ext == null || ext.length == 0)
			return null;
		else if (ext.length == 1)
			return ext[0];
		else {
			StringBuffer sb = new StringBuffer();
			sb.append(ext[0]);
			for (int i = 1; i < ext.length; i++) {
				sb.append(", "); //$NON-NLS-1$
				sb.append(ext[i]);
			}
			return sb.toString();
		}
	}

	public String getRemoteExtends() {
		return getExtends(CreateEnterpriseBeanWithClientViewDataModel.REMOTE_INTERFACE_EXTENSIONS);
	}
}