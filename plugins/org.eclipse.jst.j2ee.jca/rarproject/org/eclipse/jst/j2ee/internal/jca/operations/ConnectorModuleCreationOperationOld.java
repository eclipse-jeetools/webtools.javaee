/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Nov 6, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModelOld;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationOperationOld;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperation;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;

public class ConnectorModuleCreationOperationOld extends J2EEModuleCreationOperationOld {
	public ConnectorModuleCreationOperationOld(ConnectorModuleCreationDataModelOld dataModel) {
		super(dataModel);
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		EditModelOperation op = new EditModelOperation((J2EEModuleCreationDataModelOld) operationDataModel) {
			protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
				ConnectorEditModel model = (ConnectorEditModel) editModel;
				IFolder metainf = model.getConnectorNature().getEMFRoot().getFolder(new Path(J2EEConstants.META_INF));
				if (!metainf.exists()) {
					metainf.create(true, true, null);
				}
				IFile aFile = model.getConnectorNature().getEMFRoot().getFile(new Path(J2EEConstants.RAR_DD_URI));
				OutputStream out = new WorkbenchByteArrayOutputStream(aFile);
				String template = model.getJ2EENature().getModuleVersion() == J2EEVersionConstants.JCA_1_0_ID ? IConnectorNatureConstants.CONNECTOR_XML_TEMPLATE_10 : IConnectorNatureConstants.CONNECTOR_XML_TEMPLATE_15;
				InputStream in = getClass().getResourceAsStream(template);
				if (in != null & out != null) {
					try {
						ArchiveUtil.copy(in, out);
					} catch (IOException ioe) {
						Logger.getLogger().logError(ioe);
					} finally {
						try {
							if (null != out) {
								out.close();
							}
							if (null != in) {
								in.close();
							}
						} catch (IOException ioe) {
							Logger.getLogger().logError(ioe);
						}
					}
					Resource resource = model.getResource(URI.createURI(J2EEConstants.RAR_DD_URI));
					if (null != resource) {
						resource.unload();
					}

					Connector connector = model.getConnector();
					if (connector != null) {
						IProject project = model.getProject();
						if (project != null)
							connector.setDisplayName(project.getName());
					}
				} else {
					//without a template
					model.makeDeploymentDescriptorWithRoot();
				} // if
			}
		};
		op.doRun(monitor);
	}
}