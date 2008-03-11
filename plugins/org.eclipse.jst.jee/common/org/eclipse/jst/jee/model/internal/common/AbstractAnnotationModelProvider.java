/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.internal.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public abstract class AbstractAnnotationModelProvider<T> implements IResourceChangeListener, IModelProvider {

	/**
	 * Find the security role with the given name in the given assembly
	 * descriptor.
	 * 
	 * @param assembly
	 * @param name
	 * @return <code>null</code> if a security role with this name can not be
	 *         found
	 */
	private static SecurityRole findRole(Collection<SecurityRole> securityRoles, String name) {
		for (SecurityRole role : securityRoles) {
			if (role.getRoleName().equals(name))
				return role;
		}
		return null;
	}

	protected T modelObject;

	private Collection<IModelProviderListener> listeners;

	private Lock listenersLock = new ReentrantLock();

	protected IFacetedProject facetedProject;

	private ManyToOneRelation<SecurityRoleRef, SecurityRole> rolesToRolesRef = new ManyToOneRelation<SecurityRoleRef, SecurityRole>();

	/**
	 * Constructs a new AnnotationReader for this faceted project. An illegal
	 * argument if a project with value <code>null</code> is passed. No
	 * loading is done in this constructor. Loading the model is made on demand
	 * when calling {@link #getModelObject()}.
	 * 
	 * @param project
	 *            the ejb project. Can not be <code>null</code>
	 */
	public AbstractAnnotationModelProvider(IFacetedProject project) {
		if (project == null)
			throw new IllegalArgumentException("The project argument can not be null");
		this.facetedProject = project;
	}

	public T getConcreteModel() {
		if (modelObject == null) {
			preLoad();
			try {
				loadModel();
				/*
				 * Adding the resource change listener after loading the model.
				 * No resource change event are acceptable while loading the
				 * model.
				 */
				postLoad();
			} catch (CoreException e) {
				log(e.getStatus());
				return null;
			}
		}
		return modelObject;
	}

	public Object getModelObject() {
		return getConcreteModel();
	}

	public Object getModelObject(IPath modelPath) {
		return getConcreteModel();
	}

	protected abstract void loadModel() throws CoreException;

	protected void preLoad() {
	}

	protected void postLoad() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this,
				IResourceChangeEvent.POST_BUILD | IResourceChangeEvent.PRE_CLOSE | IResourceChangeEvent.PRE_DELETE);
	}

	/**
	 * Notifies the currently registered listeners with this model event. If the
	 * {@link IModelProviderEvent#getChangedResources()} is empty or
	 * <code>null</code> the method returns immediately.
	 * 
	 * @param event
	 *            the event that should be send to the listeners
	 */
	protected void notifyListeners(final IModelProviderEvent event) {
		notifyListeners(listeners, event);
	}

	/**
	 * Clears the list of listeners. No notifications can occur while clearing
	 * the listeners.
	 */
	protected void clearListeners() {
		if (listeners == null)
			return;
		try {
			listenersLock.lock();
			listeners.clear();
			listeners = null;
		} finally {
			listenersLock.unlock();
		}
	}

	private void notifyListeners(final Collection<IModelProviderListener> aListeners, final IModelProviderEvent event) {
		if (listeners == null)
			return;
		listenersLock.lock();
		try {
			if (event.getChangedResources() == null || event.getChangedResources().isEmpty())
				return;
			for (final IModelProviderListener listener : aListeners) {
				SafeRunner.run(new ISafeRunnable() {
					public void handleException(Throwable exception) {
					}

					public void run() throws Exception {
						listener.modelsChanged(event);
					}
				});
			}
		} finally {
			listenersLock.unlock();
		}

	}

	/**
	 * @return the currently registered listeners.
	 */
	protected Collection<IModelProviderListener> getListeners() {
		if (listeners == null) {
			listeners = new HashSet<IModelProviderListener>();
		}
		return listeners;
	}

	/**
	 * Adds a listener to this instance. No listeners can be added during
	 * notifying the current listeners.
	 * 
	 * @param listener
	 */
	public void addListener(IModelProviderListener listener) {
		listenersLock.lock();
		try {
			getModelObject();
			getListeners().add(listener);
		} finally {
			listenersLock.unlock();
		}
	}

	/**
	 * Removes the listener from this instance. Has no effect if an identical
	 * listener is not registered.
	 * 
	 * @param listener
	 *            the listener to be removed.
	 */
	public void removeListener(IModelProviderListener listener) {
		listenersLock.lock();
		try {
			getListeners().remove(listener);
		} finally {
			listenersLock.unlock();
		}

	}

	/**
	 * @param project
	 * @return true if the given project contains resources that are relative to
	 *         the model. This method returns <code>true</code> for the
	 *         ejbProject on which this instance is working a <code>true</code>
	 *         for its client project.
	 */
	protected boolean isProjectRelative(IProject project) {
		if (project == null || facetedProject == null)
			return false;
		else if (project.equals(facetedProject.getProject()))
			return true;
		return false;
	}

	/**
	 * Dispose the current instance. The actual dispose may occur in another
	 * thread. Use {@link #addListener(IModelProviderListener)} to register a
	 * listener that will be notified when the instance is disposed. After all
	 * the listeners are notified the list of listeners is cleared.
	 */
	public abstract void dispose();

	/**
	 * An internal refresh of the model. Depending on the type of the event and
	 * the amount of work that must be done the refresh may occur in the same or
	 * in another thread. Use {@link #addListener(IModelProviderListener)} to
	 * register a listener that will be notified when updating the model is
	 * finished.
	 * 
	 * Changing a resource can also mean disposing the instance it the resource
	 * change event describes that a project is deleted or closed.
	 * 
	 * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
	 */
	public void resourceChanged(final IResourceChangeEvent event) {
		// if (ejbJar == null)
		// return;
		final IModelProviderEvent modelEvent = new MyModelProviderEvent(0, null, facetedProject.getProject());

		switch (event.getType()) {
		case IResourceChangeEvent.PRE_DELETE:
		case IResourceChangeEvent.PRE_CLOSE:
			IProject project = (IProject) event.getResource();
			if (!isProjectRelative(project))
				return;
			dispose();
			break;
		case IResourceChangeEvent.POST_BUILD:
			internalRefresh(modelEvent, event.getDelta());
		}
	}

	/**
	 * Internal refresh of the model maintained by this instance. Calling this
	 * method usually means that there is a descent amount of work to be
	 * processed. The method is processing the added/remove/change java files
	 * described by "projectDelta"
	 * 
	 * Specific information for which objects were changed will be added in the
	 * modelEvent
	 * 
	 * Refreshing the model may occurs in another thread. Use
	 * {@link #addListener(IModelProviderListener)} to register a listener. The
	 * listener will be notified when refreshing is finished.
	 * 
	 * @param modelEvent
	 * @param projectDelta
	 */
	protected void internalRefresh(final IModelProviderEvent modelEvent, final IResourceDelta projectDelta) {
		final CollectiveResourceDeltaVisitor visitor = collectDeltaFiles(projectDelta);
		Job job = new Job("Refresh annotation model") {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					for (IFile file : visitor.getRemovedFiles()) {
						processRemovedFile(modelEvent, file);
					}
					for (IFile file : visitor.getAddedFiles()) {
						processAddedFile(modelEvent, file);
					}
					for (IFile file : visitor.getChangedFiles()) {
						processChangedFile(modelEvent, file);
					}
					visitor.clear();
				} catch (CoreException e) {
					return e.getStatus();
				} finally {
					/*
					 * I do not provide a backup version of the model from which
					 * a revert is possible so I am notifying the listeners even
					 * if an error has occurred. Since the model might be
					 * changed in one thread an the notification will occur in
					 * another thread there might be listener depending on this
					 * notifications to unblock a blocked thread.
					 * 
					 * It is possible in case of an exception to rebuild the
					 * model from scratch or to send a notification that the
					 * model is not valid. This is is still open question.
					 */
					notifyListeners(modelEvent);
				}
				return Status.OK_STATUS;
			}
		};
		job.schedule();
	}

	protected CollectiveResourceDeltaVisitor collectDeltaFiles(IResourceDelta projectDelta) {
		CollectiveResourceDeltaVisitor visitor = new CollectiveResourceDeltaVisitor(facetedProject.getProject());
		try {
			projectDelta.accept(visitor);
		} catch (CoreException e1) {
			log(e1.getStatus());
			e1.printStackTrace();
		}
		return visitor;
	}

	/**
	 * Process a file as "removed". The method is allowed not to make checks
	 * whether the file was added/removed/change. It is processing the file as
	 * "removed".
	 * 
	 * <p>
	 * This method changes the model. The method is called by
	 * {@link #internalRefresh(IModelProviderEvent, IResourceDelta)}.
	 * Implementations should assure changes to the model are synchronized
	 * between each other.
	 * </p>
	 * 
	 * If no model object depends on the given file "modelEvent" is not changed.
	 * 
	 * @see #internalRefresh(IModelProviderEvent, IResourceDelta)
	 * @see #processAddedFile(IModelProviderEvent, IFile)
	 * @param modelEvent
	 * @param file
	 *            the file to be removed.
	 * @throws CoreException
	 *             if there was an error during parsing the file
	 */
	protected abstract void processRemovedFile(IModelProviderEvent modelEvent, IFile file) throws CoreException;

	/**
	 * Process a file as "added". The method is allowed not to make checks
	 * whether the file was added/removed/change. It is processing the file as
	 * "added". It is the responsibility of the caller to make sure the
	 * processing of the file as added will not leave the model in a wrong
	 * state.
	 * 
	 * modelEvent is changed to contain information about the added modelObject.
	 * 
	 * <p>
	 * This method changes the model. The method is called by
	 * {@link #internalRefresh(IModelProviderEvent, IResourceDelta)}.
	 * Implementations should assure changes to the model are synchronized
	 * between each other.
	 * </p>
	 * 
	 * @see #processChangedFile(IModelProviderEvent, IFile)
	 * @see #processRemovedFile(IModelProviderEvent, IFile)
	 * @param modelEvent
	 * @param file
	 *            the file that was added
	 * @throws CoreException
	 */
	protected abstract void processAddedFile(IModelProviderEvent modelEvent, IFile file) throws CoreException;

	/**
	 * Process a file as "changed". The method is allowed not to make checks
	 * whether the file was added/removed/change. It is processing the file as
	 * "changed". It is the responsibility of the caller to make sure the
	 * processing of the file as "changed" will not leave the model in a wrong
	 * state.
	 * 
	 * <p>
	 * This method changes the model. The method is called by
	 * {@link #internalRefresh(IModelProviderEvent, IResourceDelta)}.
	 * Implementations should assure changes to the model are synchronized
	 * between each other.
	 * </p>
	 * 
	 * @see #processAddedFile(IModelProviderEvent, IFile)
	 * @see #processRemovedFile(IModelProviderEvent, IFile)
	 * @param modelEvent
	 * @param file
	 *            the file that was changed
	 * @throws CoreException
	 */
	protected abstract void processChangedFile(IModelProviderEvent modelEvent, IFile file) throws CoreException;

	protected void log(IStatus status) {
	}

	protected MyModelProviderEvent createModelProviderEvent() {
		return new MyModelProviderEvent(0, null, facetedProject.getProject());
	}

	/**
	 * @author Kiril Mitov k.mitov@sap.com
	 * 
	 */
	protected class CollectiveResourceDeltaVisitor implements IResourceDeltaVisitor {

		private Collection<IFile> addedFiles;

		private Collection<IFile> removedFiles;

		private Collection<IFile> changedFiles;

		private IJavaProject ejbProjectJavaView;

		public CollectiveResourceDeltaVisitor(IProject ejbProject) {
			addedFiles = new ArrayList<IFile>();
			removedFiles = new ArrayList<IFile>();
			changedFiles = new ArrayList<IFile>();
			this.ejbProjectJavaView = JavaCore.create(ejbProject);
		}

		public Collection<IFile> getAddedFiles() {
			return addedFiles;
		}

		public Collection<IFile> getRemovedFiles() {
			return removedFiles;
		}

		public Collection<IFile> getChangedFiles() {
			return changedFiles;
		}

		public void clear() {
			addedFiles.clear();
			removedFiles.clear();
			changedFiles.clear();
		}

		private boolean isOnClasspath(IFile file) {
			return ejbProjectJavaView.isOnClasspath(file);
		}

		public boolean visit(IResourceDelta delta) throws CoreException {

			if (delta.getResource().getType() == IResource.PROJECT) {
				return isProjectRelative((IProject) delta.getResource());
			}
			if (delta.getResource().getType() == IResource.FILE) {
				IFile file = (IFile) delta.getResource();
				if (!isOnClasspath(file))
					return false;
				if ("java".equals(file.getFileExtension())) {
					switch (delta.getKind()) {
					case IResourceDelta.ADDED:
						addedFiles.add((IFile) delta.getResource());
						break;
					case IResourceDelta.CHANGED:
						changedFiles.add((IFile) delta.getResource());
						break;
					case IResourceDelta.REMOVED:
						removedFiles.add((IFile) delta.getResource());
						break;
					}
				}
				return false;
			}
			return true;
		}
	}

	// ---------------SECURITY ROLES ---------------------------//
	protected abstract Collection<SecurityRole> getSecurityRoles();

	protected abstract Collection<SecurityRoleRef> getSecurityRoleRefs(JavaEEObject target);

	/**
	 * Deletes the connection maintained by the given bean and the security
	 * roles defined in the bean. If this is the only bean in which the role is
	 * defined, the role will also be deleted. Calling this method makes sense
	 * only if the bean and the security role and the bean were connected with
	 * {@link #connectWithRole(SecurityRole, SessionBean)}
	 * 
	 * <p>
	 * If the bean is not of type org.eclipse.jst.javaee.ejb.SessionBean the
	 * method returns immediately.
	 * </p>
	 * 
	 * @see #connectWithRole(SecurityRole, SessionBean)
	 * @see #rolesToRolesRef
	 * @param bean
	 */
	protected void disconnectFromRoles(JavaEEObject target) {
		Collection<SecurityRole> roles = getSecurityRoles();
		if (roles == null)
			return;
		Collection<SecurityRoleRef> refs = getSecurityRoleRefs(target);
		if (refs == null)
			return;
		for (SecurityRoleRef ref : refs) {
			SecurityRole role = rolesToRolesRef.getTarget(ref);
			rolesToRolesRef.disconnectSource(ref);
			if (!rolesToRolesRef.containsTarget(role)) {
				getSecurityRoles().remove(role);
			}
		}
	}

	/**
	 * A security role was found in the given file. Add this security role to
	 * the assembly descriptor. If the ejbJar does not have an assembly
	 * descriptor a new one is created.
	 * 
	 * @see #connectRoleWithBean(SecurityRole, SessionBean)s
	 * @param file
	 * @param securityRole
	 */
	protected void securityRoleFound(JavaEEObject object, SecurityRole securityRole) {
		connectWithRole(securityRole, object);
	}

	/**
	 * A security role can be defined in more the one bean. A bean can define
	 * more then one security role. This means we have a many-to-many relation
	 * between sessionBeans and securityRoles.
	 * 
	 * <p>
	 * Luckily a sessionBean contains a list of securityRoleRefs. This method
	 * creates a connection between the securityRole contained in the assembly
	 * descriptor and the security role ref contained in the bean.
	 * 
	 * If a security role is define only in one bean, deleting the bean means
	 * deleting the security role. But if the security role is defined in two
	 * beans only deleting both beans will result in deleting the security role.
	 * </p>
	 * 
	 * @see #disconnectFromRoles(JavaEEObject)
	 * @see #rolesToRolesRef
	 * @param securityRole
	 * @param target
	 */
	private void connectWithRole(SecurityRole securityRole, JavaEEObject target) {
		Collection<SecurityRole> roles = getSecurityRoles();
		if (roles == null)
			return;
		Collection<SecurityRoleRef> refs = getSecurityRoleRefs(target);
		if (refs == null)
			return;
		/*
		 * If there is a security role with this name use the existing security
		 * role.
		 */
		SecurityRole role = findRole(roles, securityRole.getRoleName());
		if (role == null) {
			roles.add(securityRole);
			role = securityRole;
		}
		for (SecurityRoleRef ref : refs) {
			if (ref.getRoleName().equals(role.getRoleName()))
				rolesToRolesRef.connect(ref, role);
		}
	}
}
