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
	
	public void setProject(IProject project) {
		proj = project;
		
	}

	public void addResource(Object resource) {
		changedResources.add(resource);

	}

	public void addResources(Collection<Object> someResources) {
		changedResources.addAll(someResources);

	}

	public List<Object> getChangedResources() {
		
		return changedResources;
	}

	public int getEventCode() {
		// TODO Auto-generated method stub
		return event;
	}

	public IModelProvider getModel() {
		
		return model;
	}

	public void setChangedResources(List<Object> newChangedResources) {
		changedResources = newChangedResources;

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
