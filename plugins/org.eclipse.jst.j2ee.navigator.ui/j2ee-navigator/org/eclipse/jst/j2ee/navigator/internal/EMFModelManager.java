/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Feb 17, 2005
 */
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.resources.IProject;

public abstract class EMFModelManager {
	private final IProject project;
	private final EMFRootObjectProvider provider;
	public abstract Object[] getModels();
	public final IProject getProject() {
		return project;
	}
	public final EMFRootObjectProvider getEMFRootObjectProvider(){
		return provider;
	}
	public EMFModelManager(IProject aProject, EMFRootObjectProvider aProvider) {
		project = aProject;
		provider = aProvider;
	}
	
	/**
	 * @param affectedProject
	 */
	protected void notifyListeners(IProject affectedProject) {
		provider.notifyListeners(affectedProject);
	}
	/**
	 * 
	 */
	public abstract void dispose();
	
}
