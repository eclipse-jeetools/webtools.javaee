package org.eclipse.jem.internal.proxy.remote;
/***************************************************************************************************
 * Copyright (c) 2001, 2003 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * $RCSfile: ProxyRegistration.java,v $ $Revision: 1.3 $ $Date: 2004/02/06 20:43:52 $
 */

import java.io.IOException;
import java.net.*;
import java.text.MessageFormat;
import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.*;
import org.eclipse.debug.core.model.*;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.launching.StandardVMRunner;
import org.eclipse.jdt.launching.*;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.remote.awt.REMRegisterAWT;

/**
 * This is the registration class for starting a Remote VM.
 */

public class ProxyRegistration implements IRegistration {

	/**
	 * This will create a remote VM and return an initialized REMProxyFactoryRegistry. Passed in
	 * are: project: The project this is being started on. Must not be null and must be a
	 * JavaProject. attachAWT: Should AWT be attached to this implementation. contributors:
	 * Contributors to the configuration. Can be null. pm: ProgressMonitor to use. Must not be
	 * null. vmName: Name for the vm. Can be null.
	 */
	public ProxyFactoryRegistry startImplementation(
		final IConfigurationContributor[] contributors,
		boolean attachAWT,
		IProject project,
		String vmName,
		IProgressMonitor pm)
		throws CoreException {
		// Not NLS'd because name is only used in debugging.
		String name = null;
		if (vmName == null)
			name = MessageFormat.format(ProxyRemoteMessages.getString("ProxyRemoteVMName"), new Object[] { project.getName()}); //$NON-NLS-1$
		else
			name = MessageFormat.format(ProxyRemoteMessages.getString("ProxyRemoteVMNameWithComment"), new Object[] { project.getName(), vmName }); //$NON-NLS-1$

		// Problem with launch, can't have double-quotes in vmName.
		if (name.indexOf('"') != -1)
			name = name.replace('"', '\'');
		
		pm.beginTask(MessageFormat.format(ProxyRemoteMessages.getString("ProxyRemoteLaunchVM"), new Object[] { name }), 500); //$NON-NLS-1$

		try {
			// See if build needed or waiting or inprogress, if so, wait for it to complete. We've
			// decided
			// too difficult to determine if build would affect us or not, so just wait.
			handleBuild(new SubProgressMonitor(pm, 100));

			IJavaProject javaProject = JavaCore.create(project);
			// Add in the paths for the project
			final ArrayList classPaths = new ArrayList(Arrays.asList(JavaRuntime.computeDefaultRuntimeClassPath(javaProject)));

			final IClasspathContributionController controller = new IClasspathContributionController() {
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
					// Run in safe mode so that anything happens we don't go away.
					final int ii = i;
					Platform.run(new ISafeRunnable() {
						public void handleException(Throwable exception) {
							// Don't need to do anything. Platform.run logs it for me.
						}

						public void run() throws Exception {
							contributors[ii].contributeClasspaths(classPaths, controller);
						}
					});
				}
			}

			// Add in the required ones by the Proxy support. These are hard-coded since they are
			// required.
			ProxyRemoteUtil.updateClassPaths(classPaths, controller);

			pm.worked(100); // Worked the classpaths step

			final VMRunnerConfiguration config = new VMRunnerConfiguration("org.eclipse.jem.internal.proxy.vm.remote.RemoteVMApplication", (String[]) classPaths.toArray(new String[classPaths.size()])); //$NON-NLS-1$		

			REMProxyFactoryRegistry registry = new REMProxyFactoryRegistry(ProxyRemoteUtil.getRegistryController(), name);
			Integer registryKey = registry.getRegistryKey();

			Integer bufSize = Integer.getInteger("proxyvm.bufsize"); //$NON-NLS-1$
			if (bufSize == null)
				bufSize = new Integer(16000);

			int masterServerPort = ProxyRemoteUtil.getRegistryController().getMasterSocketPort();

			config.setVMArguments(new String[] { "-Dproxyvm.registryKey=" + registryKey, "-Dproxyvm.masterPort=" + String.valueOf(masterServerPort), "-Dproxyvm.bufsize=" + bufSize, "-Dproxyvm.servername=" + name }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

			// Let contributors modify the configuration.
			if (contributors != null) {
				for (int i = 0; i < contributors.length; i++) {
					// Run in safe mode so that anything happens we don't go away.
					final int ii = i;
					Platform.run(new ISafeRunnable() {
						public void handleException(Throwable exception) {
							// Don't need to do anything. Platform.run logs it for me.
						}

						public void run() throws Exception {
							contributors[ii].contributeToConfiguration(config);
						}
					});
				}
			}

			// See if debug mode is requested.
			DebugModeHelper dh = new DebugModeHelper();
			boolean debugMode = dh.debugMode(name);

			// If in debug mode, we need to find a port for it to use.
			int dport = -1;
			if (debugMode) {
				dport = findUnusedLocalPort("localhost", 5000, 15000, new int[0]); //$NON-NLS-1$
				String[] dArgs = new String[] { "-Djava.compiler=NONE", "-Xdebug", "-Xnoagent", "-Xrunjdwp:transport=dt_socket,server=y,address=" + dport }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				String[] newVMArgs = new String[config.getVMArguments().length + dArgs.length];
				System.arraycopy(config.getVMArguments(), 0, newVMArgs, 0, config.getVMArguments().length);
				System.arraycopy(dArgs, 0, newVMArgs, config.getVMArguments().length, dArgs.length);
				config.setVMArguments(newVMArgs);
			}

			IVMRunner runner = null;
			JavaRuntime.getVMInstallTypes(); // Needed to force initialization of VM types.
			IVMInstall vm = JavaRuntime.getVMInstall(javaProject);
			if (vm == null)
				vm = JavaRuntime.getDefaultVMInstall();
			if (vm != null)
				runner = vm.getVMRunner(ILaunchManager.RUN_MODE);
			if (runner instanceof StandardVMRunner) {
				runner = new ProxyVMRunner(vm);
			}

			if (runner == null) {
				// this is bad, we will pop up a msg.
				String msg = null;
				if (vm == null)
					msg = MessageFormat.format(ProxyRemoteMessages.getString("Proxy_NoVM_ERROR_"), new Object[] { name }); //$NON-NLS-1$
				else
					msg = MessageFormat.format(ProxyRemoteMessages.getString("Proxy_NoRunner_ERROR_"), new Object[] { name }); //$NON-NLS-1$
				dh.displayErrorMessage(ProxyRemoteMessages.getString("Proxy_Error_Title"), msg); //$NON-NLS-1$
				ProxyPlugin.getPlugin().getMsgLogger().log(
					new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, msg, null));
				return null; // Can't find the requested VM.
			}

			ILaunchConfigurationType lcType =
				DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurationType(
					IJavaLaunchConfigurationConstants.ID_JAVA_APPLICATION);
			ILaunchConfigurationWorkingCopy wc = lcType.newInstance(null, vmName);
			applyDebugUIConstants(wc);
			ILaunch runresult = new Launch(wc, ILaunchManager.RUN_MODE, null);

			// See if we have any environment variables
			Map vmMap = config.getVMSpecificAttributesMap();
			if (vmMap != null) {
				String[] environmentVariables = (String[]) vmMap.get(ProxyPlugin.ENVIRONMENT_VARIABLE);
				if (environmentVariables != null) {
					((ProxyVMRunner) runner).fEnvironmentVariables = environmentVariables;
				}
			}
			pm.worked(100); // Worked Setup launch step

			// Launch the vm.
			IProgressMonitor spm = new SubProgressMonitor(pm, 100);
			runner.run(config, runresult, spm);

			if (runresult != null) {
				IProcess[] processes = runresult.getProcesses();
				IProcess process = processes[0]; // There is only one.
				// Check if it is already terminated. If it is, then there was a bad error, so just
				// print out the results from it.
				if (process.isTerminated()) {
					IStreamsProxy stProxy = process.getStreamsProxy();
					// Using a printWriter for println capability, but it needs to be on another
					// writer, which will be string
					java.io.StringWriter s = new java.io.StringWriter();
					java.io.PrintWriter w = new java.io.PrintWriter(s);

					w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED));
					w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED_LINE1));
					w.println(stProxy.getErrorStreamMonitor().getContents());
					w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED_LINE2));
					w.println(stProxy.getOutputStreamMonitor().getContents());
					w.println(ProxyRemoteMessages.getString(ProxyRemoteMessages.VM_TERMINATED_LINE3));
					w.close();

					String msg = MessageFormat.format(ProxyRemoteMessages.getString("Proxy_Terminated_too_soon_ERROR_"), new Object[] { name }); //$NON-NLS-1$
					dh.displayErrorMessage(ProxyRemoteMessages.getString("Proxy_Error_Title"), msg); //$NON-NLS-1$
					ProxyPlugin.getPlugin().getMsgLogger().log(
						new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, s.toString(), null));
					return null;
				} else {
					final String traceName = name;
					IStreamsProxy fStreamsProxy = process.getStreamsProxy();

					class StreamListener implements IStreamListener {
						String tracePrefix;
						int level;

						public StreamListener(String type, int level) {
							tracePrefix = traceName + ':' + type + '>';
							this.level = level;
						}

						public void streamAppended(String newText, IStreamMonitor monitor) {
							ProxyPlugin.getPlugin().getMsgLogger().log(
								new Status(
									level,
									ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(),
									0,
									tracePrefix + newText,
									null));
						}
					};

					// Always listen to System.err output.
					IStreamMonitor monitor = fStreamsProxy.getErrorStreamMonitor();
					if (monitor != null)
						monitor.addListener(new StreamListener("err", IStatus.WARNING)); //$NON-NLS-1$

					// If debug trace is requested, then attach trace listener for System.out
					if ("true".equalsIgnoreCase(Platform.getDebugOption(ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier() + ProxyRemoteUtil.DEBUG_VM_TRACEOUT))) { //$NON-NLS-1$
						// Want to trace the output of the remote vm's.

						monitor = fStreamsProxy.getOutputStreamMonitor();
						if (monitor != null)
							monitor.addListener(new StreamListener("out", IStatus.INFO)); //$NON-NLS-1$							
					}
				}

				// If in debug mode, tester must start debugger before going on.
				if (debugMode) {
					if (!dh.promptPort(dport)) {
						process.terminate();
						return null;
					}
				}

				// Now set up the registry.
				registry.initializeRegistry(process);
				new REMStandardBeanTypeProxyFactory(registry);
				new REMStandardBeanProxyFactory(registry);
				new REMMethodProxyFactory(registry);

				if (debugMode || REMProxyFactoryRegistry.fGlobalNoTimeouts)
					registry.fNoTimeouts = true;
				if (attachAWT)
					REMRegisterAWT.registerAWT(registry);
				if (contributors != null) {
					final ProxyFactoryRegistry reg = registry;
					for (int i = 0; i < contributors.length; i++) {
						final int ii = i;
						// Run in safe mode so that anything happens we don't go away.
						Platform.run(new ISafeRunnable() {
							public void handleException(Throwable exception) {
								// Don't need to do anything. Platform.run logs it for me.
							}

							public void run() throws Exception {
								contributors[ii].contributeToRegistry(reg);
							}
						});
					}
				}
				return registry;
			} else {
				// this is bad, we will pop up a msg.
				String msg = MessageFormat.format(ProxyRemoteMessages.getString("PRoxy_NoLaunch_ERROR_"), new Object[] { name }); //$NON-NLS-1$
				dh.displayErrorMessage(ProxyRemoteMessages.getString("Proxy_Error_Title"), msg); //$NON-NLS-1$
				ProxyPlugin.getPlugin().getMsgLogger().log(
					new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, msg, null));
				return null; // Couldn't launch for some reason.
			}
		} finally {
			pm.done();
		}
	}

	private void handleBuild(IProgressMonitor pm) throws CoreException {
		boolean autobuilding = ResourcesPlugin.getWorkspace().isAutoBuilding();
		if (!autobuilding) {
			// We are not autobuilding. So kick off a build right here and
			// wait for it.
			ResourcesPlugin.getWorkspace().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, pm);
		} else {
			Job[] build = Platform.getJobManager().find(ResourcesPlugin.FAMILY_AUTO_BUILD);
			if (build.length == 1) {
				if (build[0].getState() == Job.RUNNING || build[0].getState() == Job.WAITING) {
					pm.beginTask(ProxyRemoteMessages.getString("ProxyRemoteWaitForBuild"), 100); //$NON-NLS-1$
					try {
						build[0].join();
					} catch (InterruptedException e) {
						throw new CoreException(
							new Status(IStatus.ERROR, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), IStatus.ERROR, "", e)); //$NON-NLS-1$
					} finally {
						pm.done();
					}
				}
			}
		}
	}

	// Utilities to find the free port
	private static final Random fgRandom = new Random(System.currentTimeMillis());

	private static int findUnusedLocalPort(String host, int searchFrom, int searchTo, int[] exclude) {
		for (int i = 0; i < 10; i++) {
			int port = 0;
			newport : while (true) {
				port = getRandomPort(searchFrom, searchTo);
				if (exclude != null)
					for (int e = 0; e < exclude.length; e++)
						if (port == exclude[e])
							continue newport;
				break;
			}
			try {
				new Socket(host, port);
			} catch (ConnectException e) {
				return port;
			} catch (IOException e) {
			}
		}
		return -1;
	}

	private static int getRandomPort(int low, int high) {
		return (int) (fgRandom.nextFloat() * (high - low)) + low;
	}

	protected static String makeAbsolute(IWorkspace ws, IPath path) {

		IResource resource = ws.getRoot().findMember(path);
		if (resource != null)
			return resource.getLocation().toOSString();
		return path.toOSString();
	}

	private static String ATTR_TARGET_RUN_PERSPECTIVE, ATTR_PRIVATE;
	static {
		ATTR_TARGET_RUN_PERSPECTIVE = ATTR_PRIVATE = null;
		try {
			// So that we can run headless (w/o ui), need to do class forName for debugui contants
			Class debugUIConstants = Class.forName("org.eclipse.debug.ui.IDebugUIConstants"); //$NON-NLS-1$
			ATTR_TARGET_RUN_PERSPECTIVE = (String) debugUIConstants.getField("ATTR_TARGET_RUN_PERSPECTIVE").get(null); //$NON-NLS-1$
			ATTR_PRIVATE = (String) debugUIConstants.getField("ATTR_PRIVATE").get(null); //$NON-NLS-1$
		} catch (SecurityException e) {
		} catch (ClassNotFoundException e) {
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}
	}

	private void applyDebugUIConstants(ILaunchConfigurationWorkingCopy wc) {
		if (ATTR_TARGET_RUN_PERSPECTIVE != null)
			wc.setAttribute(ATTR_TARGET_RUN_PERSPECTIVE, (String) null);
		if (ATTR_PRIVATE != null)
			wc.setAttribute(ATTR_PRIVATE, true);
	}
}