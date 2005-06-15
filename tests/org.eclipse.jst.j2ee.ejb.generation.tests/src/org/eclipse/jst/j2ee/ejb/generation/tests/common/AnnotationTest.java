/*******************************************************************************
 * Copyright (c) 2002, 2003 Eteration Bilisim A.S., Naci Dai and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the GNU Lesser General Public License (LGPL)
 * which accompanies this distribution, and is available at
 *  http://www.gnu.org/licenses/lgpl.html
 * 
 * Contributors:
 *     Eteration Bilisim A.S. - initial API and implementation
 *     Naci M. Dai
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Eteration Bilisim A.S and Naci Dai (http://www.eteration.com/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "Lomboz", "ObjectLearn" and "Eteration" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact info@eteration.com.
 *
 * 5. Products derived from this software may not be called "Lomboz"
 *    nor may "Lomboz" appear in their names without prior written
 *    permission of the Eteration Bilisim A.S.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Eteration Bilisim A.S.  For more
 * information on eteration, please see
 * <http://www.eteration.com/>.
 ***************************************************************************/

package org.eclipse.jst.j2ee.ejb.generation.tests.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.EnterpriseBeanClassDataModel;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.SessionBeanDataModel;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.servertarget.J2EEProjectServerTargetDataModel;
import org.eclipse.jst.server.generic.core.internal.GenericServerRuntime;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.IRuntimeWorkingCopy;
import org.eclipse.wst.server.core.IServerType;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.model.RuntimeDelegate;

public abstract class AnnotationTest extends TestCase {

	protected static final String EJB_NAME = "Cow";
	protected static final String MODULE_NAME = "zoo";
	protected static final String PROJECT_NAME = "TestEjbProject";
	public static final String BEAN_PACKAGE = "com.farm";
	public static final String BEAN_CLASS = "CowBean";
	public final static String ID = "org.eclipse.jst.server.generic.jonas414";

	/**
	 * 
	 */
	public AnnotationTest() {
		super();
	}

	/**
	 * @param name
	 */
	public AnnotationTest(String name) {
		super(name);
	}

	protected TestProject testProject;
	public static final String XDOCLET = "XDoclet";

	protected IDataModelOperation createFlexibleProject() throws Exception {
		FlexibleJavaProjectCreationDataModelProvider provider = new FlexibleJavaProjectCreationDataModelProvider();
		IDataModel creationDataModel = provider.getDataModel();
		creationDataModel.setProperty(FlexibleJavaProjectCreationDataModelProvider.PROJECT_NAME, PROJECT_NAME);
		IRuntime runtime = getRuntimeTarget();
		creationDataModel.setProperty(J2EEProjectServerTargetDataModel.RUNTIME_TARGET_ID, runtime.getId());


		return provider.getDefaultOperation();
	}

	public IRuntime getRuntimeTarget() throws Exception {

		// Finds the generic server type
		IServerType[] sTypes = ServerCore.getServerTypes();
		IServerType serverType = null;
		for (int i = 0; i < sTypes.length; i++) {
			IServerType sType = sTypes[i];
			if (ID.equals(sType.getId()))
				serverType = sType;
		}
		assertNotNull("Could not find org.eclipse.jst.server.generic.jonas414 server type", serverType);

		// Finds the generic server runtime type
		IRuntimeType runtimeType = serverType.getRuntimeType();
		assertNotNull("Could not find runtime type for the generic server type", runtimeType);

		// Create a new server instance from the type
		IServerWorkingCopy server = serverType.createServer(TestSettings.runtimeid + ".Jonas.Server", null, (IRuntime) null, null);
		assertNotNull("Could not create server", server);

		// Create a new runtime instance from the type
		IRuntime runtime = runtimeType.createRuntime(TestSettings.runtimeid + ".Jonas.Runtime", null);

		assertNotNull("Could not create runtime", runtime);

		// Set the runtime for the server
		server.setRuntime(runtime);

		// Save the server
		server.save(false, null);

		// Set properties for the runtime
		IRuntimeWorkingCopy runtimeWorkingCopy = runtime.createWorkingCopy();
		assertNotNull("Could not create runtime working copy", runtimeWorkingCopy);

		// Set the JONAS runtime as the default runtime
		ServerUtil.setRuntimeDefaultName(runtimeWorkingCopy);
		assertNotNull("Runtime working copy has no name", runtimeWorkingCopy.getName());

		// Set properties for the JONAS runtime
		GenericServerRuntime runtimeDelegate = (GenericServerRuntime) runtimeWorkingCopy.getAdapter(RuntimeDelegate.class);
		assertNotNull("Could not obtain runtime delegate", runtimeDelegate);

		HashMap props = new HashMap();
		props.put("mappernames", "");
		props.put("classPathVariableName", "JONAS");
		props.put("serverAddress", "127.0.0.1");
		props.put("jonasBase", TestSettings.serverlocation);
		props.put("jonasRoot",TestSettings.serverlocation);
		props.put("classPath", TestSettings.serverlocation);
		props.put("protocols", TestSettings.serverlocation);
		props.put("port", "9000");
		runtimeDelegate.setServerInstanceProperties(props);

		// Save the runtime working copy
		runtimeWorkingCopy.save(false, null);

		return runtime;
	}

	protected EnterpriseBeanClassDataModel createDefaultSessionModel() {
		SessionBeanDataModel model = new SessionBeanDataModel();
		model.setProperty(SessionBeanDataModel.ANNOTATIONPROVIDER, XDOCLET);

		model.setProperty(SessionBeanDataModel.SUPERCLASS, model.getEjbSuperclassName());
		model.setProperty(SessionBeanDataModel.INTERFACES, model.getEJBInterfaces());
		model.setBooleanProperty(SessionBeanDataModel.MODIFIER_ABSTRACT, true);
		model.setProperty(SessionBeanDataModel.CLASS_NAME, BEAN_CLASS);
		model.setProperty(SessionBeanDataModel.JAVA_PACKAGE, BEAN_PACKAGE);

		model.setProperty(SessionBeanDataModel.PROJECT_NAME, PROJECT_NAME);
		model.setProperty(SessionBeanDataModel.MODULE_NAME, MODULE_NAME);

		model.setProperty(SessionBeanDataModel.EJB_NAME, EJB_NAME);
		model.setProperty(SessionBeanDataModel.JNDI_NAME, EJB_NAME);
		model.setProperty(SessionBeanDataModel.DISPLAY_NAME, EJB_NAME);
		return model;
	}

	protected IDataModelOperation createEjbModuleAndProject() throws Exception {

		IDataModelOperation flexibleJavaProjectCreationOperation = createFlexibleProject();
		flexibleJavaProjectCreationOperation.execute(new NullProgressMonitor(),null);
		EjbComponentCreationDataModelProvider aProvider = new EjbComponentCreationDataModelProvider();
		IDataModel a = aProvider.getDataModel();
		a.setBooleanProperty(EjbComponentCreationDataModelProvider.ADD_TO_EAR, false);
		a.setProperty(EjbComponentCreationDataModelProvider.COMPONENT_NAME, MODULE_NAME);
		a.setProperty(EjbComponentCreationDataModelProvider.COMPONENT_DEPLOY_NAME, MODULE_NAME);
		a.setBooleanProperty(EjbComponentCreationDataModelProvider.CREATE_CLIENT, false);
		a.setProperty(EjbComponentCreationDataModelProvider.PROJECT_NAME, PROJECT_NAME);
		a.getDefaultOperation().execute(new NullProgressMonitor(),null);

		return a.getDefaultOperation();
	}

	/**
	 * @throws CoreException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws JavaException
	 */
	protected void createTestJ2EEProject() throws CoreException, MalformedURLException, IOException {
		this.testProject = new TestProject();
		this.testProject.setSourceFolder(testProject.createSourceFolder());
	}

}
