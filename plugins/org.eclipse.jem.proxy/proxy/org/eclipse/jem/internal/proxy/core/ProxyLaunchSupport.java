/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ProxyLaunchSupport.java,v $
 *  $Revision: 1.3 $  $Date: 2004/03/05 22:06:34 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.text.MessageFormat;
import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.*;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
 
/**
 * This is the used to launch the proxy registries.
 * This is a static helper class, it is not meant to be instantiated.
 * 
 * @since 1.0.0
 */
public class ProxyLaunchSupport {
	
	/**
	 * These are public only so that jem.ui can access this constant. Not meant to be accessed by others.
	 */
	public static final QualifiedName PROPERTY_LAUNCH_CONFIGURATION = new QualifiedName("org.eclipse.jem.proxy", "proxyLaunchConfiguration");
	// If a project's persistent property is set with this value, that means there is at least one
	// launch configuration with this project, but none are selected as the default. This is here
	// so that we can check in the object contribution that if not set then don't show the menu
	// item at all. This is to clean up the popup menu so not so cluttered.
	// If the property is trully not set, then there is no default and there are no configurations for it.
	public static final String NOT_SET = "...not..set..";	 
	
	private static Map LAUNCH_CONTRIBUTORS = new HashMap(2);
	private static Map LAUNCH_REGISTRY_RETURN = new HashMap(2);
	
	/**
	 * Start an implementation using the default config for the given project.
	 * 
	 * @param project The project. It must be a java project, and it cannot be <code>null</code>.
	 * @param vmTitle
	 * @param aContribs The contributions array. It may be <code>null</code>.
	 * @param pm
	 * @return The created registry.
	 * @throws CoreException
	 * 
	 * @since 1.0.0
	 */
	public static ProxyFactoryRegistry startImplementation(
			IProject project,
			String vmTitle,
			IConfigurationContributor[] aContribs,
			IProgressMonitor pm)
				throws CoreException {
		
		// First find the appropriate launch configuration to use for this project.
		// The process is:
		//	1) See if the project's persistent property has a setting for "proxyLaunchConfiguration", if it does,
		//		get the configuration of that name and create a working copy of it.
		//	2) If not, then get the "org.eclipse.jem.proxy.LocalProxyLaunchConfigurationType"
		//		and create a new instance working copy.

		IJavaProject javaProject = JavaCore.create(project);
		if (javaProject == null) {
			throw new CoreException(
					new Status(
							IStatus.WARNING,
							ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(),
							0,
							MessageFormat.format(
									ProxyMessages.getString(ProxyMessages.NOT_JAVA_PROJECT),
									new Object[] { project.getName()}),
							null));
		}

		// First if specific set.
		String launchName = project.getPersistentProperty(PROPERTY_LAUNCH_CONFIGURATION);
		ILaunchConfiguration config = null;		
		if (launchName != null && !NOT_SET.equals(launchName)) {
			ILaunchConfiguration[] configs = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations();
			for (int i = 0; i < configs.length; i++) {
				if (configs[i].getName().equals(launchName)) {
					config = configs[i];
					break;
				}
			}
			if (config == null || !config.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "").equals(project.getName())) {
				project.setPersistentProperty(PROPERTY_LAUNCH_CONFIGURATION, (String) null);	// Config not found, or for a different project, so no longer the default.
				config = null;
			}
		}
		
		if (config == null) {
			ILaunchConfigurationWorkingCopy configwc = DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(IProxyConstants.LOCAL_LAUNCH_TYPE).newInstance(null, DebugPlugin.getDefault().getLaunchManager().generateUniqueLaunchConfigurationNameFrom("LocalProxy_"+project.getName()));
			configwc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, project.getName()); 
			config = configwc;
		}
		
		return startImplementation(config, vmTitle, aContribs, pm);
	}
	
	/**
	 * Launch a registry using the given configuration.
	 * @param config 
	 * @param vmTitle
	 * @param aContribs The contributions array. It may be <code>null</code>.
	 * @param pm
	 * @return The registry from this configuration.
	 * @throws CoreException
	 * 
	 * @since 1.0.0
	 */
	public static ProxyFactoryRegistry startImplementation(
			ILaunchConfiguration config,
			String vmTitle,
			IConfigurationContributor[] aContribs,
			IProgressMonitor pm)
			throws CoreException {
		
		if (pm == null)
			pm = new NullProgressMonitor();
		
		final ILaunchConfigurationWorkingCopy configwc = config.getWorkingCopy();
		
		// See if build needed or waiting or inprogress, if so, wait for it to complete. We've
		// decided
		// too difficult to determine if build would affect us or not, so just wait.
		pm.beginTask(ProxyMessages.getString("ProxyLaunch"), 400);
		handleBuild(new SubProgressMonitor(pm, 100));
				
		if (aContribs != null) {
			IConfigurationContributor[] newContribs = new IConfigurationContributor[aContribs.length+1];
			System.arraycopy(aContribs, 0, newContribs, 1, aContribs.length);
			newContribs[0] = new ProxyContributor();
			aContribs = newContribs;
		} else
			aContribs = new IConfigurationContributor[] {new ProxyContributor()};

		String projectName = configwc.getAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, (String) null);
		if (projectName != null) {
			projectName = projectName.trim();
			if (projectName.length() > 0) {
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				IJavaProject javaProject = JavaCore.create(project);
				if (javaProject != null && javaProject.exists()) {
					Set containerIds = new HashSet(5);
					Set containers = new HashSet(5);
					Set pluginIds = new HashSet(5);
					ProxyPlugin.getPlugin().getIDsFound(javaProject, containerIds, containers, pluginIds);					
					if (!containerIds.isEmpty() || !containers.isEmpty() || !pluginIds.isEmpty()) {
						List computedContributors = new ArrayList(containerIds.size()+containers.size()+pluginIds.size());
						// First handle explicit classpath containers that implement IConfigurationContributor
						for (Iterator iter = containers.iterator(); iter.hasNext();) {
							IConfigurationContributor containerContributor = (IConfigurationContributor) iter.next();
							computedContributors.add(containerContributor);
						}
						
						// Second add in contributors that exist for a container id.
						for (Iterator iter = containerIds.iterator(); iter.hasNext();) {
							String containerid = (String) iter.next();
							IConfigurationElement[] contributors = ProxyPlugin.getPlugin().getContainerConfigurations(containerid);
							if (contributors != null)
								for (int i = 0; i < contributors.length; i++) {
									computedContributors.add(contributors[i].createExecutableExtension(ProxyPlugin.PI_CLASS));
								}
						}
						
						// Finally add in contributors that exist for a plugin id.
						for (Iterator iter = pluginIds.iterator(); iter.hasNext();) {
							String pluginId = (String) iter.next();
							IConfigurationElement[] contributors = ProxyPlugin.getPlugin().getPluginConfigurations(pluginId);
							if (contributors != null)
								for (int i = 0; i < contributors.length; i++) {
									computedContributors.add(contributors[i].createExecutableExtension(ProxyPlugin.PI_CLASS));
								}
						}
						
						// Now turn into array
						if (!computedContributors.isEmpty()) {
							IConfigurationContributor[] newContribs = new IConfigurationContributor[aContribs.length+computedContributors.size()];
							System.arraycopy(aContribs, 0, newContribs, 0, aContribs.length);
							IConfigurationContributor[] cContribs = (IConfigurationContributor[]) computedContributors.toArray(new IConfigurationContributor[computedContributors.size()]);
							System.arraycopy(cContribs, 0, newContribs, aContribs.length, cContribs.length);
							aContribs = newContribs;
						}
					}
				}
			}
		}
		
		
		String launchKey = String.valueOf(System.currentTimeMillis());
		synchronized (ProxyLaunchSupport.class) {
			while (LAUNCH_CONTRIBUTORS.containsKey(launchKey)) {
				launchKey += 'a'; // Just add something on to make it unique.
			}
			LAUNCH_CONTRIBUTORS.put(launchKey, aContribs);
		}
		ProxyFactoryRegistry registry = null;
		try {		
			configwc.setAttribute(IProxyConstants.ATTRIBUTE_LAUNCH_KEY, launchKey);
			if (vmTitle != null && vmTitle.length()>0)
				configwc.setAttribute(IProxyConstants.ATTRIBUTE_VM_TITLE, vmTitle);
			
			if (ATTR_PRIVATE != null)
				configwc.setAttribute(ATTR_PRIVATE, true);			
			
			// Let contributors modify the configuration.
			final IConfigurationContributor[] contribs = aContribs;
			for (int i = 0; i < contribs.length; i++) {
				// Run in safe mode so that anything happens we don't go away.
				final int ii = i;
				Platform.run(new ISafeRunnable() {
					public void handleException(Throwable exception) {
						// Don't need to do anything. Platform.run logs it for me.
					}

					public void run() throws Exception {
						contribs[ii].contributeToConfiguration(configwc);
					}
				});
			}
			pm.worked(100);
			
			configwc.launch(ILaunchManager.RUN_MODE, new SubProgressMonitor(pm, 100));
			
			final ProxyFactoryRegistry reg = (ProxyFactoryRegistry) LAUNCH_REGISTRY_RETURN.remove(launchKey);
			for (int i = 0; i < contribs.length; i++) {
				final int ii = i;
				// Run in safe mode so that anything happens we don't go away.
				Platform.run(new ISafeRunnable() {
					public void handleException(Throwable exception) {
						// Don't need to do anything. Platform.run logs it for me.
					}

					public void run() throws Exception {
						contribs[ii].contributeToRegistry(reg);
					}
				});
			}
			
			registry = reg;	// Now we have something to return.
		} finally {
			// Clean up and return.
			LAUNCH_CONTRIBUTORS.remove(launchKey);
			LAUNCH_REGISTRY_RETURN.remove(launchKey);
		}	
		
		pm.done();
		return registry;
	}
	
	private static void handleBuild(IProgressMonitor pm) throws CoreException {
		boolean autobuilding = ResourcesPlugin.getWorkspace().isAutoBuilding();
		if (!autobuilding) {
			// We are not autobuilding. So kick off a build right here and
			// wait for it.
			ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, pm);			
		} else {
			Job[] build = Platform.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD);
			if (build.length == 1) {
				if (build[0].getState() == Job.RUNNING || build[0].getState() == Job.WAITING || build[0].getState() == Job.SLEEPING) {
					pm.beginTask(ProxyMessages.getString("ProxyWaitForBuild"), 100); //$NON-NLS-1$
					try {						
						build[0].join();						
					} catch (InterruptedException e) {
						throw new CoreException(
								new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), IStatus.ERROR, "", e)); //$NON-NLS-1$
					}
				}
			}
			pm.done();
		}
	}
	
	
	/*
	 * This prevents the launch from being shown. However these constants are in UI component, and we don't
	 * want to pre-req that. So we will get them reflectively instead.
	 * public but only so that launch delegate can get to it.
	 */
	public static String ATTR_PRIVATE;
	static {
		ATTR_PRIVATE = null;
		try {
			// So that we can run headless (w/o ui), need to do class forName for debugui contants
			Plugin debuguiPlugin = Platform.getPlugin("org.eclipse.debug.ui"); //$NON-NLS-1$
			if (debuguiPlugin != null) {
				Class debugUIConstants = debuguiPlugin.getDescriptor().getPluginClassLoader().loadClass("org.eclipse.debug.ui.IDebugUIConstants"); //$NON-NLS-1$
				ATTR_PRIVATE = (String) debugUIConstants.getField("ATTR_PRIVATE").get(null); //$NON-NLS-1$
			}
		} catch (SecurityException e) {
		} catch (ClassNotFoundException e) {
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}	

	/* (non-Javadoc)
	 * Only referenced by launch delegates. public because they are in other packages,
	 * or even external developers packages. Not meant to be generally available.
	 * 
	 * This is needed because we can't pass the contributors into a launch configuration
	 * because a launch configuration can't take objects. Only can take strings and numbers.  
	 */
	public static IConfigurationContributor[] getContributors(String key) {
		return (IConfigurationContributor[]) LAUNCH_CONTRIBUTORS.get(key);
	}
	
	/* (non-Javadoc)
	 * Only referenced by launch delegates. public because they are in other packages,
	 * or even external developers packages. Not meant to be generally available.
	 * 
	 * This is needed because we can't get the registry returned from a launch. So
	 * the launch needs to put the registry in here.
	 * 
	 * It will use the same launch key as for contributors.
	 */
	public static void setRegistry(String key, ProxyFactoryRegistry registry) {
		LAUNCH_REGISTRY_RETURN.put(key, registry);
	}	
	
	/* (non-Javadoc)
	 * Local contributor used to make sure that certain jars are in the path.
	 * 
	 * @since 1.0.0
	 */
	static class ProxyContributor implements IConfigurationContributor {
		public void contributeClasspaths(IConfigurationContributionController controller) {
			// Add the required jars to the end of the classpath.
			controller.contributeClasspath(ProxyPlugin.getPlugin(), "proxycommon.jar", IConfigurationContributionController.APPEND_USER_CLASSPATH, false);	//$NON-NLS-1$
			controller.contributeClasspath(ProxyPlugin.getPlugin(), "initparser.jar", IConfigurationContributionController.APPEND_USER_CLASSPATH, true);	//$NON-NLS-1$			
		}
		public void contributeToConfiguration(ILaunchConfigurationWorkingCopy config) {
		}
		public void contributeToRegistry(ProxyFactoryRegistry registry) {
		}
	}
	
}
