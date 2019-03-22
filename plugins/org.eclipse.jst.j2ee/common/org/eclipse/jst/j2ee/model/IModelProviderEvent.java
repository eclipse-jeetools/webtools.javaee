/*******************************************************************************
 * Copyright (c) 2007, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;

public interface IModelProviderEvent {

	// Used when the edit model is saved.
	public static final int SAVE = 0x1;
	// Used when the command stack becomes dirty.
	public static final int DIRTY = 0x2;
	// Used when a referenced resource is removed from the ResourceSet.
	public static final int REMOVED_RESOURCE = 0x4;
	// Used when a referenced resource is added to the ResourceSet.
	public static final int ADDED_RESOURCE = 0x8;
	// Used when the edit model is disposed
	public static final int PRE_DISPOSE = 0x10;
	// Used when a Resource is loaded or the first object
	// is added to the contents when created.
	public static final int LOADED_RESOURCE = 0x20;
	// Used when a Resource is unloaded.
	public static final int UNLOADED_RESOURCE = 0x40;
	// Indicates that the list of known resources managed by the edit model is about to change
	public static final int KNOWN_RESOURCES_ABOUT_TO_CHANGE = 0x80;
	// Indicates that the list of known resources managed by the edit model has changed
	public static final int KNOWN_RESOURCES_CHANGED = 0x100;
	// Indicates that the list of known resources in the models project could affect classpath
	public static final int RESOURCE_AFFECTING_CLASSPATH_CHANGED = 0x200;

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