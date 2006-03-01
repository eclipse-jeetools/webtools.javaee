/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.emitter;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;
import org.eclipse.jst.j2ee.internal.project.WTPJETEmitter;

public abstract class EjbEmitter {
	public static final String CLASSPATHPROVIDER = "classpathProvider";
	public static final String JAVACLASSPATHVARIABLEPREFIX = "WTPEJBEMITTER";
	public static final String EJBEMITTERPROJECT = ".WtpEjbEmitter";
	protected IConfigurationElement emitterConfig;
	protected IEmitterClasspathProvider classpathProvider;
	protected String base;
	protected IProgressMonitor monitor;

	public EjbEmitter(IConfigurationElement emitterConfig) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException {
		this.emitterConfig = emitterConfig;
		String pluginDescriptor = emitterConfig.getDeclaringExtension().getNamespace();

		org.osgi.framework.Bundle bundle = Platform.getBundle(pluginDescriptor);
		Class c = bundle.loadClass(emitterConfig.getAttribute(CLASSPATHPROVIDER));
		classpathProvider = (IEmitterClasspathProvider) c.newInstance();
		base = bundle.getEntry("/").toString();
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(EJBEMITTERPROJECT);
		IProgressMonitor monitor = new NullProgressMonitor();
		project.delete(true, true, monitor);
	}

	public abstract String emitTypeComment(IEnterpriseBean enterpriseBean) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException;

	public abstract String emitTypeStub(IEnterpriseBean enterpriseBean) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException;

	public abstract String emitInterfaceMethods(IEnterpriseBean enterpriseBean) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException;

	public abstract String emitFields(IEnterpriseBean enterpriseBean) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, CoreException;

	/**
	 * @param uri
	 * @return
	 * @throws JETException
	 */
	protected WTPJETEmitter createJetEmitter(String uri) throws JETException {
		WTPJETEmitter emitter = new WTPJETEmitter(uri, this.getClass().getClassLoader());
		emitter.setProjectName(WTPJETEmitter.PROJECT_NAME);
		emitter.setIntelligentLinkingEnabled(true);
		Iterator iterator = classpathProvider.getClasspathItems().iterator();
		int count = 0;
		while (iterator.hasNext()) {
			String cpItem = (String) iterator.next();
			emitter.addVariable(JAVACLASSPATHVARIABLEPREFIX + count++, cpItem);
		}
		return emitter;
	}

	public String generate(String templatesBase, String template, IEnterpriseBean enterpriseBean) throws CoreException {
		String uri = base + templatesBase + template;
		String result = "";
		IProgressMonitor aMonitor = this.getMonitor();
		try {
			WTPJETEmitter emitter = createJetEmitter(uri);
			result = emitter.generate(aMonitor, new Object[] { enterpriseBean });
		}
		catch (JETException e) {
			throw new CoreException(e.getStatus());
		}
		return result;
	}

	/**
	 * @return Returns the monitor.
	 */
	public IProgressMonitor getMonitor() {
		if (monitor == null) {
			monitor = new IProgressMonitor() {
				private boolean cancelled = false;

				public void beginTask(String name, int totalWork) {
					System.out.println(this.getClass() + " Progress (" + totalWork + "): " + name);
				}

				public void done() {
				}

				public void internalWorked(double work) {
				}

				public boolean isCanceled() {
					return cancelled;
				}

				public void setCanceled(boolean value) {
					cancelled = value;
				}

				public void setTaskName(String name) {
					System.out.println(this.getClass() + " Progress Task(" + name + "): ");
				}

				public void subTask(String name) {
					System.out.println(this.getClass() + " Progress SubTask(" + name + "): ");
				}

				public void worked(int work) {
				}
			};
		}
		return monitor;
	}

	/**
	 * @param monitor
	 *            The monitor to set.
	 */
	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public void deleteProject() throws CoreException {
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(EJBEMITTERPROJECT);
		IProgressMonitor monitor = new NullProgressMonitor();
		project.delete(true, true, monitor);
	}
}