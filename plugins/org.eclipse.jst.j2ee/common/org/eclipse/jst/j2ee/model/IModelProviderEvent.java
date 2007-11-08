package org.eclipse.jst.j2ee.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;

public interface IModelProviderEvent {

	// Used when the edit model is saved.
	public static final int SAVE = 1;
	// Used when the command stack becomes dirty.
	public static final int DIRTY = 2;
	// Used when a referenced resource is removed from the ResourceSet.
	public static final int REMOVED_RESOURCE = 3;
	// Used when a referenced resource is added to the ResourceSet.
	public static final int ADDED_RESOURCE = 4;
	// Used when the edit model is disposed
	public static final int PRE_DISPOSE = 5;
	// Used when a Resource is loaded or the first object
	// is added to the contents when created.
	public static final int LOADED_RESOURCE = 6;
	// Used when a Resource is unloaded.
	public static final int UNLOADED_RESOURCE = 7;
	// Indicates that the list of known resources managed by the edit model is about to change
	public static final int KNOWN_RESOURCES_ABOUT_TO_CHANGE = 8;
	// Indicates that the list of known resources managed by the edit model has changed
	public static final int KNOWN_RESOURCES_CHANGED = 9;

	public abstract void addResource(Object aResource);

	public abstract void addResources(Collection<Object> someResources);

	public abstract List<Object> getChangedResources();

	public abstract IModelProvider getModel();
	
	public abstract IProject getProject();

	public abstract int getEventCode();

	public abstract void setChangedResources(List<Object> newChangedResources);

	public abstract void setModel(IModelProvider newModel);
	
	public abstract void setProject(IProject project);

	public abstract void setEventCode(int newEventCode);

}