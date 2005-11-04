package org.eclipse.jst.j2ee.flexible.project.tests;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.flexible.project.apitests.artifactedit.Test0_7Workspace;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;

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

	private void migrateEJBProject(IProject ejbProject2) {
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForRead(ejbProject2);
			String facetid = edit.getComponent().getComponentType().getComponentTypeId();
			Assert.assertEquals(facetid,J2EEProjectUtilities.EJB);
		}
		finally {
			if (edit != null)
				edit.dispose();
		}
		
	}

}
