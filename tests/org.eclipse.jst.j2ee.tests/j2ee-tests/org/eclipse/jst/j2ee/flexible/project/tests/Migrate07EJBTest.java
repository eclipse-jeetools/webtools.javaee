package org.eclipse.jst.j2ee.flexible.project.tests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit.Test0_7Workspace;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.server.core.FacetUtil;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.server.core.ServerUtil;

public class Migrate07EJBTest extends TestCase {
	
	private IProject ejbProject;
	public Migrate07EJBTest() {
		super();

		if (Test0_7Workspace.init()) {
			ejbProject = Test0_7Workspace.getTargetProject("MyEarEJB");
		} else {
			fail();

		}
	}

	private void pass() {
		assertTrue(true);
	}
	public String getFacetFromProject() {
		return J2EEProjectUtilities.getJ2EEProjectType(ejbProject);
	}

	public void testMigrate() {
		String facetid = getFacetFromProject();
		if (facetid.length() == 0)
			migrateEJBProject(ejbProject);
		EJBArtifactEdit edit = null;
		try {
			edit = EJBArtifactEdit.getEJBArtifactEditForWrite(ejbProject);
			EJBJar ejb = edit.getEJBJar();
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	protected IDataModel setupJavaInstallAction(IProject aProject) {
		IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
		dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.4"); //$NON-NLS-1$
		return dm;
	}
	protected IDataModel setupEjbInstallAction(IProject aProject,String ejbVersion) {
		IDataModel ejbFacetInstallDataModel = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
		ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject);
		ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, ejbVersion);
		
		return ejbFacetInstallDataModel;
	}

	private void migrateEJBProject(IProject ejbProject2) {
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForRead(ejbProject2);
			String facetid = edit.getComponent().getComponentType().getComponentTypeId();
			String ejbVersion = edit.getComponent().getComponentType().getVersion();
			Assert.assertEquals(facetid,J2EEProjectUtilities.EJB);
			installEJBFacets(ejbProject2,ejbVersion);
			
		}
		finally {
			if (edit != null)
				edit.dispose();
		}
		
	}

	private void installEJBFacets(IProject ejbProject2,String ejbVersion) {
		IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, ejbProject2.getName());
		FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
		facetDMs.add(setupJavaInstallAction(ejbProject2));
		IDataModel newModel = setupEjbInstallAction(ejbProject2,ejbVersion);
		facetDMs.add(newModel);
		setRuntime(ejbProject2,dm); //Setting runtime property
		try {
			IStatus stat =  dm.getDefaultOperation().execute(null,null);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected IRuntime getRuntimeByID(String id) {
		IRuntime[] targets = ServerUtil.getRuntimes("", "");
		for (int i = 0; i < targets.length; i++) {
			IRuntime target = targets[i];
			if (id.equals(target.getId()))
				return target;
		}
		return null;
	}

	private void setRuntime(IProject aProject,IDataModel facetModel) {

		IRuntime runtime = ServerCore.getProjectProperties(aProject).getRuntimeTarget();
		try {
			if (runtime != null) {
				IRuntime run = getRuntimeByID(runtime.getId());
				org.eclipse.wst.common.project.facet.core.runtime.IRuntime facetRuntime = null;
				try {
					if (run != null)
						facetRuntime = FacetUtil.getRuntime(run);
				}
				catch (IllegalArgumentException ex)
				{}
				if (facetRuntime != null) {
					facetModel.setProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME,facetRuntime);
				}
			}
			} catch (IllegalArgumentException e) {
			Logger.getLogger().logError(e);
		}
	
		
	}

}
