package org.eclipse.jst.j2ee.dependency.tests;

import java.util.ArrayList;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.dependency.tests.util.DependencyCreationUtil;
import org.eclipse.jst.j2ee.dependency.tests.util.ProjectUtil;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;

public class EarLibDirContainerTests extends AbstractTests {
	
	private static final String EAR_PROJECT_NAME = "TestEAR";
	private static final String LIB_PROJECT_NAME = "TestLib";
	private static final String UTIL_PROJECT_NAME = "TestUtil";
	
	public EarLibDirContainerTests(String name) {
		super(name);
	}
	
	public static Test suite() {
		final TestSuite suite = new TestSuite();
        suite.setName("EAR Container Library Directory Tests");
        suite.addTest(new EarLibDirContainerTests("testEarLibDirDependency"));
        return suite;
	}
	
	public void testEarLibDirDependency() throws Exception {
		ResourcesPlugin.getWorkspace().getRoot().getProjects();
		IProject earProject = ProjectUtil.createEARProject(EAR_PROJECT_NAME, J2EEVersionConstants.JEE_5_0_ID, false);
		IProject utilProject = ProjectUtil.createUtilityProject(UTIL_PROJECT_NAME, null);
		DependencyCreationUtil.createEARDependency(earProject, utilProject, false);
		IProject libProject = ProjectUtil.createUtilityProject(LIB_PROJECT_NAME, null);
		DependencyCreationUtil.createEARDependency(earProject, libProject, true);
		updateEARLibrariesContainer(utilProject);
        waitForCondition(cpContains(utilProject, new Path(IPath.SEPARATOR + LIB_PROJECT_NAME)));
	}
	
	private static void updateEARLibrariesContainer(IProject project) {
		ArrayList projectList = new ArrayList();
		projectList.add(project);
		J2EEComponentClasspathUpdater.getInstance().forceUpdate(projectList);
	}
	
	private static ICondition cpContains(final IProject project, final IPath entry) {
		return new ClasspathContainsCondition( project, entry );
	}
    
    private static void waitForCondition( final ICondition condition ) throws Exception {
        waitForCondition( condition, 10 );
    }
    
    private static void waitForCondition( final ICondition condition, final int seconds ) throws Exception {
        for( int i = 0; i < seconds && ! condition.check(); i++ ) {
            try {
                Thread.sleep( 1000 );
            }
            catch( InterruptedException e ) {}
        }
        
        assertCondition( condition );
    }
    
    private static void assertCondition( final ICondition condition ) throws Exception {
        assertTrue( condition.check() );
    }
    
    private static interface ICondition {
        boolean check() throws Exception;
    }
    
    private static abstract class AbstractClasspathCondition implements ICondition {
        private final IProject project;
        private final IPath entry;
        
        public AbstractClasspathCondition( final IProject project, final IPath entry ) {
            this.project = project;
            this.entry = entry;
        }
        
        protected boolean internalCheck() throws Exception {
            final IJavaProject jproj = JavaCore.create( this.project );
            final IClasspathEntry[] cp = jproj.getResolvedClasspath( true );
            
            for( int i = 0; i < cp.length; i++ ) {
                if( cp[ i ].getPath().equals( this.entry ) ) {
                    return true;
                }
            }
            
            return false;
        }
    }
    
    private static final class ClasspathContainsCondition extends AbstractClasspathCondition {
	    public ClasspathContainsCondition( final IProject project, final IPath entry ) {
	        super( project, entry );
	    }
	    
	    public boolean check() throws Exception {
	        return internalCheck();
	    }
	}

}
