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
package org.eclipse.jst.jee.model.internal.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;

/**
 * @author  Kiril Mitov k.mitov@sap.com
 *
 */
class MyModelProviderEvent implements IModelProviderEvent {
	private IModelProvider model;
	private IProject proj;
	private int event;

	private List resources;

	public MyModelProviderEvent(int anEventCode, IModelProvider model, IProject proj) {
		setEventCode(anEventCode);
		setModel(model);
		setProject(proj);
		resources = new ArrayList();
	}

	public void setProject(IProject project) {
		proj = project;

	}

	public void addResource(Object resource) {
		resources.add(resource);
	}

	public void addResources(Collection<Object> someResources) {
		resources.addAll(someResources);

	}

	public List<Object> getChangedResources() {
		return resources;
	}

	public int getEventCode() {
		return event;
	}

	public IModelProvider getModel() {
		return model;
	}

	public void setChangedResources(List<Object> newChangedResources) {
		throw new UnsupportedOperationException();
	}

	public void setEventCode(int newEventCode) {
		event = newEventCode;
	}

	public void setModel(IModelProvider newModel) {
		model = newModel;

	}

	public IProject getProject() {
		return proj;
	}

}
