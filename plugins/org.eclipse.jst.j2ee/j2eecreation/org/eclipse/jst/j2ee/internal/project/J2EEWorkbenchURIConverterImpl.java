/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.project;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.wst.common.internal.emfworkbench.CompatibilityWorkbenchURIConverterImpl;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ISynchronizerExtender;
import com.ibm.wtp.emf.workbench.ResourceSetWorkbenchSynchronizer;

public class J2EEWorkbenchURIConverterImpl extends CompatibilityWorkbenchURIConverterImpl implements ISynchronizerExtender {

	protected J2EENature nature;
	protected List inputChangedListeners;

	public interface InputChangedListener {
		void inputsChanged(J2EEWorkbenchURIConverterImpl aConverter);
	}

	/**
	 * Constructor for J2EEWorkbenchURIConverterImpl.
	 */
	public J2EEWorkbenchURIConverterImpl(J2EENature aNature, ResourceSetWorkbenchSynchronizer aSynchronizer) {
		super(aNature.getProject(), aSynchronizer);
		nature = aNature;
		initialize();
	}

	protected void initialize() {
		if (resourceSetSynchronizer != null)
			resourceSetSynchronizer.addExtender(this);
	}


	protected void deNormalize(List resources) {
		for (int i = 0; i < resources.size(); i++) {
			Resource aResource = (Resource) resources.get(i);
			aResource.setURI(deNormalize(aResource.getURI()));
		}
	}

	protected void normalize(List resources) {
		for (int i = 0; i < resources.size(); i++) {
			Resource aResource = (Resource) resources.get(i);
			aResource.setURI(normalize(aResource.getURI()));
		}
	}

	protected boolean isBroken() {
		return getInputContainer() == null;
	}

	/**
	 * Gets the nature.
	 * 
	 * @return Returns a IJ2EENature
	 */
	public J2EENature getNature() {
		return nature;
	}

	/**
	 * Sets the nature.
	 * 
	 * @param nature
	 *            The nature to set
	 */
	public void setNature(J2EENature nature) {
		this.nature = nature;
	}

	/**
	 * @see org.eclipse.wst.common.internal.emfworkbench.ISynchronizerExtender#projectChanged(IResourceDelta)
	 */
	public void projectChanged(IResourceDelta delta) {
		if (shouldNotifyChangedListeners(delta))
			notifyInputChangedListeners();
	}

	protected boolean shouldNotifyChangedListeners(IResourceDelta delta) {
		if (isInputContainerChanged(delta) || (isBroken() && isFolderAdded(delta)))
			return true;
		return false;
	}

	public void addListener(InputChangedListener aListener) {
		if (inputChangedListeners == null)
			inputChangedListeners = new ArrayList();
		inputChangedListeners.add(aListener);
	}

	/**
	 *  
	 */
	private void notifyInputChangedListeners() {
		if (inputChangedListeners != null && !inputChangedListeners.isEmpty()) {
			InputChangedListener listener;
			for (int i = 0; i < inputChangedListeners.size(); i++) {
				listener = (InputChangedListener) inputChangedListeners.get(i);
				listener.inputsChanged(this);
			}
		}
	}

	protected boolean isFolderAdded(IResourceDelta delta) {
		final boolean[] result = new boolean[1];
		result[0] = false;
		IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
			public boolean visit(IResourceDelta aDelta) throws CoreException {
				if (result[0] || (aDelta.getResource() == null))
					return false;
				switch (aDelta.getResource().getType()) {
					case IResource.FOLDER :
						if (aDelta.getKind() == IResourceDelta.ADDED) {
							result[0] = true;
							return false;
						}
						return true;
					default :
						return true;
				}
			}
		};
		try {
			visitor.visit(delta);
		} catch (CoreException core) {
			Logger.getLogger().logError(core);
		}
		return result[0];
	}

	protected boolean isInputContainerChanged(IResourceDelta delta) {
		IContainer input = getInputContainer();
		if (input == null)
			return false;
		IResourceDelta child = delta.findMember(input.getProjectRelativePath());
		return (child != null) && (child.getKind() == IResourceDelta.REMOVED || child.getKind() == IResourceDelta.CHANGED);
	}

	protected boolean objectsEqual(Object o1, Object o2) {
		if (o1 == null)
			return o2 == null;
		return o1.equals(o2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.ISynchronizerExtender#projectClosed()
	 */
	public void projectClosed() {
	}
	public IContainer getInputContainer() {
		List list = getInputContainers();
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				IContainer container = (IContainer)list.get(i);
				if (container instanceof IFolder) {
					IFolder sourceFolder = (IFolder)container;
					if (J2EEProjectUtilities.isSourceFolderAnInputContainer(sourceFolder)) {
						return sourceFolder;
					}
				}
			}
			return (IContainer) list.get(0);
		}
		return null;
	}

}