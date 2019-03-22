/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ejb;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;
/**
 * Deployment Descriptor root node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupEJBProvider extends AbstractEjbGroupProvider implements IAdaptable {

	private GroupEjbSession groupEjbSession;
	private GroupEjbMessageDriven groupEjbMessageDriven;
	private GroupEjbEntity groupEjbEntity;
	private String projectName = null;


	public GroupEJBProvider(EJBJar ejbJar) {
		super(ejbJar);

		groupEjbSession = new GroupEjbSession(ejbJar);
		groupEjbMessageDriven = new GroupEjbMessageDriven(ejbJar);
		groupEjbEntity = new GroupEjbEntity(ejbJar);

		children.add(groupEjbSession);
		children.add(groupEjbMessageDriven);
		children.add(groupEjbEntity);
	}

	@Override
	public List getChildren() {
		return children;
	}

	public EJBJar getEjbJar() {
		return (EJBJar) javaee;
	}

	@Override
	public String getText() {
		return NLS.bind(Messages.DEPLOYMENT_DESCRIPTOR, projectName);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public Image getImage() {
		return null;
	}

	@Override
	public void reinit(JavaEEObject modelObject) {
		super.reinit(modelObject);
		groupEjbSession.reinit(modelObject);
		groupEjbMessageDriven.reinit(modelObject);
		groupEjbEntity.reinit(modelObject);
	}

	public Object getAdapter(Class adapter) {
		if (IProject.class == adapter){
			return ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
		}
		return null;
	}
}
