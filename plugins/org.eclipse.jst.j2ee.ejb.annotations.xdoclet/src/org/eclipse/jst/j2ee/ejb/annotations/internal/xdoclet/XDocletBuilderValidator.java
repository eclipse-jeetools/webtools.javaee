/***************************************************************************************************
 * Copyright (c) 2006 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui.Messages;

public class XDocletBuilderValidator {

	public static final String MARKER_ID = XDocletAnnotationPlugin.PLUGINID
			+ ".xdoclet.marker"; //$NON-NLS-1$

	public boolean validate(final IProject currentProject) throws CoreException

	{
		final boolean isValidRuntime = isValidRuntime(currentProject);
		clearMarkers(currentProject, MARKER_ID);

		if (!isValidRuntime) {
			createWarningMarker(currentProject, MARKER_ID,
					Messages.xdoclet_runtime_invalid);
		}
		return isValidRuntime;
	}

	public IMarker createWarningMarker(IProject project, String type,
			final String message)

	throws CoreException

	{
		return createMarker(project, IMarker.SEVERITY_ERROR, type, message);
	}

	public void clearMarkers(IProject project)
			throws CoreException {
		clearMarkers(project,MARKER_ID);
	}

	private void clearMarkers(IProject project, String type)
			throws CoreException {
		final IMarker[] existing = project.findMarkers(type, false,
				IResource.DEPTH_ZERO);
		for (int i = 0; i < existing.length; i++) {
			IMarker marker = existing[i];
			marker.delete();

		}
	}

	private IMarker createMarker(IProject project, int severity, String type,
			String message)

	throws CoreException

	{
		final IMarker[] existing = project.findMarkers(type, false,
				IResource.DEPTH_ZERO);

		for (int i = 0; i < existing.length; i++) {
			final IMarker m = existing[i];

			if (m.getAttribute(IMarker.SEVERITY, -1) == severity
					&& m.getAttribute(IMarker.MESSAGE, "").equals(message)) //$NON-NLS-1$
			{
				return m;
			}
		}

		final IMarker m = project.createMarker(type);

		m.setAttribute(IMarker.MESSAGE, message);
		m.setAttribute(IMarker.SEVERITY, severity);

		return m;
	}

	public boolean isValidRuntime(IProject currentProject) {

		XDocletPreferenceStore store = XDocletPreferenceStore
				.forProject(currentProject);
		XDocletRuntime runtime = XDocletExtensionUtil.getRuntime(store
				.getProperty(XDocletPreferenceStore.XDOCLETVERSION));
		runtime.setHome(store.getProperty(XDocletPreferenceStore.XDOCLETHOME));
		return runtime.isValid();
	}

}
