/*******************************************************************************
 * Copyright (c) 2010, 2013 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Rob Stryker / Red Hat Inc	Created Class
 * IBM Corporation - Logic taken from ProjectRefactoringListener
 *******************************************************************************/
package org.eclipse.jst.javaee.ltk.core.participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.javaee.ltk.core.nls.RefactoringResourceHandler;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.DeleteParticipant;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerUtil;

public class JavaEEServerRefRefactorParticipant extends DeleteParticipant {

	private IProject projectToDelete = null;
	private static final String MODS_FROM_SERVERS = RefactoringResourceHandler.JavaEE_Server_Cleanup; 
	private IStatus updateServerRefs(IModule[] modulesToRemove, IServer affectedServer, 
			IProgressMonitor monitor ) throws CoreException {
		if (modulesToRemove == null || modulesToRemove.length == 0) {
			return Status.OK_STATUS;
		}
		monitor.beginTask(MODS_FROM_SERVERS, modulesToRemove.length * 100);
		for( int j = 0; j < modulesToRemove.length; j++ ) {
			IServerWorkingCopy wc = null;
			try {
				wc = affectedServer.createWorkingCopy();
				List list = Arrays.asList(affectedServer.getModules());
				if (list.contains(modulesToRemove[j])) {
					ServerUtil.modifyModules(wc, null, new IModule[]{modulesToRemove[j]}, null);
				}
			} catch (CoreException ce) {
				// Add it to a multistatus list? 
				throw ce;
				//J2EEPlugin.logError(ce);
			} finally {
				try {
					if (wc != null) {
						IServer newServer = wc.saveAll(true, null);
						int state = newServer.getServerState();
						if(state == IServer.STATE_STARTED) {
							newServer.publish(IServer.PUBLISH_INCREMENTAL, new NullProgressMonitor()); // TODO use child progress?
						}
					}
					
				} catch (CoreException ce) {
					throw ce;
				}
			}
		}
		return Status.OK_STATUS;
	}

	@Override
	protected boolean initialize(Object element) {
		if(!(element instanceof IProject))
			return false;
		projectToDelete = (IProject) element;
		return true;
	}

	@Override
	public String getName() {
		// TODO See JavaEERefactoringParticipant to add name string properly
		return MODS_FROM_SERVERS;
	}

	@Override
	public RefactoringStatus checkConditions(IProgressMonitor pm,
			CheckConditionsContext context) throws OperationCanceledException {
		return new RefactoringStatus();
	}

	@Override
	public Change createChange(IProgressMonitor pm) throws CoreException,
			OperationCanceledException {
		CompositeChange change = new CompositeChange(getName());
		ArrayList<IModule> tmpList;
		IServer[] allServers = ServerCore.getServers();
		for( int i = 0; i < allServers.length; i++ ) {
			IModule[] serversMods = allServers[i].getModules();
			tmpList = new ArrayList<IModule>();
			for( int j = 0; j < serversMods.length; j++ ) {
				if( serversMods[j].getProject() != null && serversMods[j].getProject().equals(projectToDelete)) {
					tmpList.add(serversMods[j]);
				}
			}
			IModule[] modsToRemove = tmpList.toArray(new IModule[tmpList.size()]);
			// If modsToRemove is empty, that means that the server does not have the affected project deployed, or
			// the affected project is not a top-level module (for example, and EAR, or stand-alone WAR), but we 
			// need to check if the affected project is a child of a top-level module. 
			if( modsToRemove.length > 0 ) 
				change.add(new RemoveProjectFromServersChange(modsToRemove, allServers[i]));
			else if (hasParentInServer(projectToDelete, allServers[i], pm)){
					change.add(new RefreshServerChange(allServers[i]));
			}
		}
		return change;
	}	
	
	private boolean hasParentInServer(IProject project, IServer server, IProgressMonitor pm) {
		IModule[] modules = ServerUtil.getModules(project);
		if (modules == null || modules.length == 0){
			return false;
		}
		IModule[] parents = null;
		boolean parentFound = false;
		for (IModule module:modules){
			try {
				parents = server.getRootModules(module, pm);
			} catch (CoreException e) {
				org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.log(new Status(IStatus.WARNING, e.getStatus().getPlugin(), e.getLocalizedMessage(), null));
			}

			if (parents == null || parents.length == 0){
				return false;
			}
			for (IModule parent:parents){
				if (!parent.equals(module) && ServerUtil.containsModule(server, parent, pm)){
					parentFound = true;
					break;
				}
			}			
		}
		return parentFound;
	}

	
	public class RemoveProjectFromServersChange extends Change {

		private IModule[] modules;
		private IServer server;
		public RemoveProjectFromServersChange(IModule[] changeMods, IServer changeServer) {
			this.modules = changeMods;
			this.server = changeServer;
		}
		
		@Override
		public String getName() {
			return MODS_FROM_SERVERS;
		}

		@Override
		public void initializeValidationData(IProgressMonitor pm) {
			// Do Nothing
		}

		@Override
		public RefactoringStatus isValid(IProgressMonitor pm)
				throws CoreException, OperationCanceledException {
			// Do Nothing
			return null;
		}

		@Override
		public Change perform(IProgressMonitor pm) throws CoreException {
			updateServerRefs(modules, server, pm);
			return null;
		}

		@Override
		public Object getModifiedElement() {
			return server;
		}
		
	}
	
	public class RefreshServerChange extends RemoveProjectFromServersChange{

		private IServer server;
		
		public RefreshServerChange(IServer server){
			super(null, server);
			this.server = server;
		}

		@Override
		public Change perform(IProgressMonitor progressMonitor) throws CoreException {
			IServerWorkingCopy wc = null;

			wc = server.createWorkingCopy();
			if (wc != null) {
				wc.saveAll(true, null);
			}					
			return null;
		}

	}	
	
}
