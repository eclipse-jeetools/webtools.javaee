/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.resources.IVirtualContainer;
import org.eclipse.wst.common.modulecore.resources.IVirtualFolder;


public class ConnectorComponentCreationOperation extends J2EEComponentCreationOperation {

    public ConnectorComponentCreationOperation(ConnectorComponentCreationDataModel dataModel) {
        super(dataModel);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponents() throws CoreException {
		IVirtualContainer component = ModuleCore.create(getProject(), getModuleDeployName());
        component.commit();
		//create and link connectorModule Source Folder
		IVirtualFolder connectorModuleFolder = component.getFolder(new Path("/connectorModule")); //$NON-NLS-1$		
		connectorModuleFolder.createLink(new Path("/" + getModuleName() + "/connectorModule"), 0, null);
		
		//create and link META-INF folder
		IVirtualFolder metaInfFolder = component.getFolder(new Path("/" + "connectorModule" + "/" + J2EEConstants.META_INF)); //$NON-NLS-1$		
		metaInfFolder.createLink(new Path("/" + getModuleName() + "/" + "connectorModule" + "/"  + J2EEConstants.META_INF), 0, null);
    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
//        ConnectorEditModel model = (ConnectorEditModel) editModel;
//		IFolder metainf = model.getConnectorNature().getEMFRoot().getFolder(new Path(J2EEConstants.META_INF));
//		if (!metainf.exists()) {
//			metainf.create(true, true, null);
//		}
//		IFile aFile = model.getConnectorNature().getEMFRoot().getFile(new Path(J2EEConstants.RAR_DD_URI));
//		OutputStream out = new WorkbenchByteArrayOutputStream(aFile);
//		String template = model.getJ2EENature().getModuleVersion() == J2EEVersionConstants.JCA_1_0_ID ? IConnectorNatureConstants.CONNECTOR_XML_TEMPLATE_10 : IConnectorNatureConstants.CONNECTOR_XML_TEMPLATE_15;
//		InputStream in = getClass().getResourceAsStream(template);
//		if (in != null & out != null) {
//			try {
//				ArchiveUtil.copy(in, out);
//			} catch (IOException ioe) {
//				Logger.getLogger().logError(ioe);
//			} finally {
//				try {
//					if (null != out) {
//						out.close();
//					}
//					if (null != in) {
//						in.close();
//					}
//				} catch (IOException ioe) {
//					Logger.getLogger().logError(ioe);
//				}
//			}
//			Resource resource = model.getResource(URI.createURI(J2EEConstants.RAR_DD_URI));
//			if (null != resource) {
//				resource.unload();
//			}
//
//			Connector connector = model.getConnector();
//			if (connector != null) {
//				IProject project = model.getProject();
//				if (project != null)
//					connector.setDisplayName(project.getName());
//			}
//		} else {
//			//without a template
//			model.makeDeploymentDescriptorWithRoot();
//		} // if
    }
    
    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        super.execute(IModuleConstants.JST_CONNECTOR_MODULE, monitor);
    }
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getJCATextVersion(version);
	}



}
