package org.eclipse.jem.internal.proxy.ide;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IDERegistration.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.ide.awt.IDERegisterAWT;
/**
 * This is the registration class for starting an IDERemote VM.
 */

public class IDERegistration implements IRegistration {
	
	public static ProxyFactoryRegistry startAnImplementation(
		IConfigurationContributor[] contributors,
		boolean attachAWT,
		IProject project,
		String vmName,
		String pluginName,
		IProgressMonitor pm) throws CoreException {
			IDERegistration idereg = new IDERegistration(pluginName);
			return idereg.startImplementation(contributors, attachAWT, project, vmName, pm); 
		}
	
	public IDERegistration() {
	}
	
	private IDERegistration(String pluginName) {
		this.pluginName = pluginName;
	}
	private String pluginName;	

	/**
	 * This will create a remote VM and return an initialized REMProxyFactoryRegistry.
	 * Passed in are:
	 *      project: The project this is being started on. Must not be null and must be a JavaProject. (Currently ignored for IDE).
	 *      attachAWT: Should AWT be attached to this implementation.
	 *      contributors: Contributors to the configuration. Can be null.
	 *      pm: ProgressMonitor to use. Must not be null.
	 *      vmName: Name for the vm. Can be null.
	 */
	public ProxyFactoryRegistry startImplementation(
		IConfigurationContributor[] contributors,
		boolean attachAWT,
		IProject project,
		String vmName,
		IProgressMonitor pm)
		throws CoreException {

		ArrayList classPaths = null;
		if (project != null) {
			IJavaProject javaProject = JavaCore.create(project);
			// Add in the paths for the project	 	
			classPaths = new ArrayList(Arrays.asList(JavaRuntime.computeDefaultRuntimeClassPath(javaProject)));
		} else
			classPaths = new ArrayList();

		IClasspathContributionController controller = new IClasspathContributionController() {
			public void contributeProject(IProject project, List classpaths, int insertBeforeIndex) throws CoreException {
				List projectPaths = new ArrayList(Arrays.asList(JavaRuntime.computeDefaultRuntimeClassPath(JavaCore.create(project))));
				// Now we need to add to the list of paths, but we don't want to add dups.
				Iterator itr2 = projectPaths.iterator();
				while (itr2.hasNext()) {
					Object path = itr2.next();
					if (classpaths.contains(path))
						itr2.remove(); // Don't add it
				}
				if (insertBeforeIndex == -1)
					classpaths.addAll(projectPaths);
				else
					classpaths.addAll(insertBeforeIndex, projectPaths);
			}

			public void contributeClasspath(String classpath, List classpaths, int insertBeforeIndex) {
				if (!classpaths.contains(classpath))
					if (insertBeforeIndex == -1)
						classpaths.add(classpath);
					else
						classpaths.add(insertBeforeIndex, classpath);
			}

			public void contributeClasspath(String[] classpathsToAdd, List classpaths, int insertBeforeIndex) {
				for (int i = 0; i < classpathsToAdd.length; i++) {
					contributeClasspath(classpathsToAdd[i], classpaths, insertBeforeIndex);
				}
			}

			public void contributeClasspath(URL[] classpathsURLs, List classpaths, int insertBeforeIndex) {
				for (int i = 0; i < classpathsURLs.length; i++) {
					contributeClasspath(classpathsURLs[i].getFile(), classpaths, insertBeforeIndex);
				}
			}

		};

		// Add in any classpaths the contributors want to add.
		if (contributors != null) {
			for (int i = 0; i < contributors.length; i++) {
				contributors[i].contributeClasspaths(classPaths, controller);
			}
		}

		URL[] othersURLs = new URL[classPaths.size()];
		for (int i = 0; i < othersURLs.length; i++) {
			String path = (String) classPaths.get(i);
			// These are paths to file system, so just put "file:" on front to turn into URL.
			try {
				othersURLs[i] = new URL("file:" + path);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		ProxyFactoryRegistry registry = createIDEProxyFactoryRegistry(vmName, pluginName, othersURLs);
		// Contribute to the registry from contributors.
		if (contributors != null) {
			for (int i = 0; i < contributors.length; i++) {
				contributors[i].contributeToRegistry(registry);
			}
		}

		return registry;
	}

	public static ProxyFactoryRegistry createIDEProxyFactoryRegistry(String aName, String aPluginName, URL[] otherURLs) {
		// Create the registry.
		IDEProxyFactoryRegistry registry =
			new IDEProxyFactoryRegistry(aName, IDEProxyFactoryRegistry.createSpecialLoader(aPluginName, otherURLs));
		initRegistry(registry);
		return registry;
	}

	public static ProxyFactoryRegistry createIDEProxyFactoryRegistry(String aName, ClassLoader loader) {
		// Create the registry.
		IDEProxyFactoryRegistry registry = new IDEProxyFactoryRegistry(aName, loader);
		initRegistry(registry);
		return registry;
	}

	private static void initRegistry(IDEProxyFactoryRegistry registry) {
		new IDEStandardBeanTypeProxyFactory(registry);
		new IDEStandardBeanProxyFactory(registry);
		new IDEMethodProxyFactory(registry);
		// Always support AWT for now
		IDERegisterAWT.registerAWT(registry);
	}
}
