package org.eclipse.jst.j2ee.project.facet.tests;
import java.util.HashSet;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallConfig;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.runtime.RuntimeManager;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class ProjectFacetCreationTest extends TestCase {
	
	
	public ProjectFacetCreationTest(String name) {
		super(name);
	}
	
	public ProjectFacetCreationTest() {
		super();
	}
    public static Test suite() {
        return new SimpleTestSuite(ProjectFacetCreationTest.class);
    }

   public void testWebCreation() throws Exception {
		IFacetedProject facetProj = ProjectFacetsManager.create("SampleWebProject",null,new NullProgressMonitor());
		// Set runtime if available
		setRuntime(facetProj);
		
		Set actions = new HashSet();
		
		//Setting up the java and web install action
		actions.add(setupJavaInstallAction());
		actions.add(setupWebInstallAction());
		
		
		facetProj.modify( actions,null);
		
		IVirtualComponent comp = ComponentCore.createComponent(facetProj.getProject());
		assertTrue(J2EEProjectUtilities.isDynamicWebProject(comp.getProject()));
		
    }

	private IFacetedProject.Action setupWebInstallAction() {
		IProjectFacetVersion webfacetversion =  ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion( "2.4" );
		WebFacetInstallConfig config = new WebFacetInstallConfig();
		config.setContentDir("WebContent");
		IFacetedProject.Action action = new IFacetedProject.Action( Action.Type.INSTALL, webfacetversion, config );
		return action;
	}
	private IFacetedProject.Action setupJavaInstallAction() {
		IProjectFacetVersion webfacetversion =  ProjectFacetsManager.getProjectFacet( "jst.java" ).getVersion( "1.4" );
		IFacetedProject.Action action = new IFacetedProject.Action( Action.Type.INSTALL, webfacetversion, null );
		return action;
	}

	protected void setRuntime(IFacetedProject facetProj) throws CoreException {
		//Setting the runtime
//		RuntimeManager.bridge();
		try {
		IRuntime runtime = RuntimeManager.getRuntime("org.eclipse.jst.server.tomcat");
		facetProj.setRuntime(runtime,null);
		} catch (IllegalArgumentException ex) {
			System.out.println("Runtime not found: org.eclipse.jst.server.tomcat");
		}
		
	}		

}
