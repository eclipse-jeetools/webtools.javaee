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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;


public class ConnectorComponentCreationOperation extends J2EEComponentCreationOperation {

    public ConnectorComponentCreationOperation(ConnectorComponentCreationDataModel dataModel) {
        super(dataModel);
    }


    protected void createProjectStructure() throws CoreException {
        IProject rootProject = getProject();
        URI metainfURI = URI.createURI(IPath.SEPARATOR + getModuleName() + ".rar" + IPath.SEPARATOR + "connectorModule" + IPath.SEPARATOR + "META-INF");
        IPath absMetaRoot = rootProject.getLocation().append(metainfURI.toString());
        createFolder(absMetaRoot);
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
    
    protected void addResources(WorkbenchComponent component) {
        addResource(component, getModuleRelativeFile(getContentSourcePath(), getProject()), getContentDeployPath());
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath(), getProject()), getJavaSourceDeployPath());	
    }

    public String getJavaSourceSourcePath() {
        return "/connectorModule"; //$NON-NLS-1$
    }

    public String getJavaSourceDeployPath() {
        return "/connectorModule/"; //$NON-NLS-1$
    }

    public String getContentSourcePath() {
        return "/connectorModule/META-INF"; //$NON-NLS-1$
    }

    public String getContentDeployPath() {
        return "/connectorModule/META-INF"; //$NON-NLS-1$
    }


	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.J2EE_MODULE_VERSION);
		return J2EEVersionUtil.getJCATextVersion(version);
	}
}
