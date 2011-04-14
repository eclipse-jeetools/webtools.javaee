/*******************************************************************************
 * Copyright (c) 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.servlet.ui.internal.navigator;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.team.core.diff.IDiff;
import org.eclipse.team.core.diff.IThreeWayDiff;
import org.eclipse.team.internal.ccvs.core.CVSProviderPlugin;
import org.eclipse.team.internal.ccvs.core.CVSWorkspaceSubscriber;
import org.eclipse.team.internal.ccvs.ui.CVSDecoration;
import org.eclipse.team.internal.ccvs.ui.CVSLightweightDecorator;
import org.eclipse.team.internal.ccvs.ui.CVSUIPlugin;
import org.eclipse.team.internal.ccvs.ui.ICVSUIConstants;
import org.eclipse.team.internal.ui.Utils;
import org.eclipse.team.ui.mapping.SynchronizationStateTester;
import org.osgi.framework.Bundle;

/**
 * CVS Decorator for the "Java Resources" virtual node.
 */
@SuppressWarnings("restriction")
public class CompressedJavaProjectCVSDecorator extends CVSLightweightDecorator {
	
	private static final SynchronizationStateTester DEFAULT_TESTER = new SynchronizationStateTester();
	
	
	@Override
	/**
	 * This method only decorates the "Java Resources" virtual node.
	 * It should be only called by the decorator thread, once it detects a CompressedJavaProject needs to be decorated. 
	 * @param element
	 * @param decoration
	 */
	public void decorate(Object element, IDecoration decoration) {
		if (element instanceof org.eclipse.jst.servlet.ui.internal.navigator.CompressedJavaProject){
			// Get the sync state tester from the context
			SynchronizationStateTester tester = getTester(decoration);
			try {
				if (tester.isDecorationEnabled(element)) {
					// Calculate the decoration for the father of the node
					CVSDecoration cvsDecoration = getDecoration(getElementFatherProject(element), tester);
					// apply the decoration on this node
					cvsDecoration.apply(decoration);
				}
			} catch(CoreException e) {
				ServletUIPlugin.log(e);
			} catch (IllegalStateException e) {
				ServletUIPlugin.log(e);
			    // This is thrown by Core if the workspace is in an illegal state
			    // If we are not active, ignore it. Otherwise, propagate it.
			    // (see bug 78303)
			    if (Platform.getBundle(CVSUIPlugin.ID).getState() == Bundle.ACTIVE) {
			    	if (Platform.getBundle(ServletUIPlugin.PLUGIN_ID).getState() == Bundle.ACTIVE) {
			    		throw e;
			    		}
			    }
			}
		}
	}

	protected IProject getElementFatherProject(Object element) {
		CompressedJavaProject cjp = (CompressedJavaProject) element;
		IProject fatherIproject = cjp.getProject();
		return fatherIproject;
	}

	protected SynchronizationStateTester getTester(IDecoration decoration) {
		IDecorationContext context = decoration.getDecorationContext();
		SynchronizationStateTester tester = DEFAULT_TESTER;
		Object property = context.getProperty(SynchronizationStateTester.PROP_TESTER);
		if (property instanceof SynchronizationStateTester) {
			tester = (SynchronizationStateTester) property;
		}
		return tester;
	}
	
	/**
	 * This method creates a new CVSDecoration that only decorates the synchronization state.
	 * @param element
	 * @param tester
	 * @return CVSDecoration
	 * @throws CoreException
	 */
	public static CVSDecoration getDecoration(Object element, SynchronizationStateTester tester) throws CoreException {
        CVSDecoration decoration = new CVSDecoration();
        // this only decorate the synchronization state
    	int state = IDiff.NO_CHANGE;
		if (isSupervised(element)) {
			// as taken from CVSDecoration decorate()
			decoration.setHasRemote(true);
			state = getElementCVSState(element, tester);
			decoration.setStateFlags(state);
        } else {
        	decoration.setIgnored(true);
        }
		//we cut here, we will not set that this element is decorated, because this decoration is only for a virtual node.
        return decoration;
    }

	private static int getElementCVSState(Object element,
			SynchronizationStateTester tester)
			throws CoreException {
		IPreferenceStore store = CVSUIPlugin.getPlugin().getPreferenceStore();
		int state;
		state = tester.getState(element, 
				store.getBoolean(ICVSUIConstants.PREF_CALCULATE_DIRTY) 
					? IDiff.ADD | IDiff.REMOVE | IDiff.CHANGE | IThreeWayDiff.OUTGOING 
					: 0, 
				new NullProgressMonitor());
		return state;
	}
	
	/* 
	 * private method on CVSLightweightDecorator
	 */
	protected static IResource[] getTraversalRoots(Object element) throws CoreException {
		Set<IResource> result = new HashSet<IResource>();
		ResourceMapping mapping = Utils.getResourceMapping(element);
		if (mapping != null) {
			ResourceTraversal[] traversals = mapping.getTraversals(ResourceMappingContext.LOCAL_CONTEXT, null);
			for (int i = 0; i < traversals.length; i++) {
				ResourceTraversal traversal = traversals[i];
				IResource[] resources = traversal.getResources();
				for (int j = 0; j < resources.length; j++) {
					IResource resource = resources[j];
					result.add(resource);
				}
			}
		}
		return  result.toArray(new IResource[result.size()]);
	}
	
	/* 
	 * private method on CVSLightweightDecorator
	 */
	protected static boolean isSupervised(Object element) throws CoreException {
		IResource[] resources = getTraversalRoots(element);
		for (int i = 0; i < resources.length; i++) {
			IResource resource = resources[i];
			if (getSubscriber().isSupervised(resource))
				return true;
		}
		return false;
	}
	
	/* 
	 * private method on CVSLightweightDecorator
	 */
	protected static CVSWorkspaceSubscriber getSubscriber() {
		return CVSProviderPlugin.getPlugin().getCVSWorkspaceSubscriber();
	}
}
