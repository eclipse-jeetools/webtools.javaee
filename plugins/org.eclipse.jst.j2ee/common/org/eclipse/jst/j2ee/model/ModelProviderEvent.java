/*******************************************************************************
 * Copyright (c) 2007, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;

public class ModelProviderEvent implements IModelProviderEvent {

	private IModelProvider model;
	private IProject proj;
	private int event;
	private List changedResources = new ArrayList();

	public ModelProviderEvent(int anEventCode, IModelProvider model, IProject proj) {
		setEventCode(anEventCode);
		setModel(model);
		setProject(proj);
	}
	
	@Override
	public void setProject(IProject project) {
		proj = project;
		
	}

	@Override
	public void addResource(Object resource) {
		changedResources.add(resource);

	}

	@Override
	public void addResources(Collection<Object> someResources) {
		changedResources.addAll(someResources);

	}

	@Override
	public List<Object> getChangedResources() {
		
		return changedResources;
	}

	@Override
	public int getEventCode() {
		return event;
	}

	@Override
	public IModelProvider getModel() {
		
		return model;
	}

	@Override
	public void setChangedResources(List<Object> newChangedResources) {
		changedResources = newChangedResources;

	}

	@Override
	public void setEventCode(int newEventCode) {
		event = newEventCode;

	}

	@Override
	public void setModel(IModelProvider newModel) {
		model = newModel;

	}

	@Override
	public IProject getProject() {
		return proj;
	}

}
