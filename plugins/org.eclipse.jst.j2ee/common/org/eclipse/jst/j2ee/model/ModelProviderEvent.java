package org.eclipse.jst.j2ee.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;

public class ModelProviderEvent implements IModelProviderEvent {

	private IModelProvider model;
	private IProject proj;
	private int event;

	public ModelProviderEvent(int anEventCode, IModelProvider model, IProject proj) {
		setEventCode(anEventCode);
		setModel(model);
		setProject(proj);
	}
	
	public void setProject(IProject project) {
		proj = project;
		
	}

	public void addResource(Object resource) {
		// TODO Auto-generated method stub

	}

	public void addResources(Collection<Object> someResources) {
		// TODO Auto-generated method stub

	}

	public List<Object> getChangedResources() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getEventCode() {
		// TODO Auto-generated method stub
		return event;
	}

	public IModelProvider getModel() {
		
		return model;
	}

	public void setChangedResources(List<Object> newChangedResources) {
		// TODO Auto-generated method stub

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
