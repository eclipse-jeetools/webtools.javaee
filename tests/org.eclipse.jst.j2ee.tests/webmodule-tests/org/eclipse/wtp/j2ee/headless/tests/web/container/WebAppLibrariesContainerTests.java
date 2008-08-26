/******************************************************************************
 * Copyright (c) 2006 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.wtp.j2ee.headless.tests.web.container;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.internal.web.classpath.WebAppLibrariesContainer;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.osgi.framework.Bundle;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebAppLibrariesContainerTests

    extends TestCase
    
{
    private static final String WEBCONTENT_DIR = "WebContent";
    private static final String WEBINF_DIR = WEBCONTENT_DIR + "/WEB-INF";
    private static final String WEBINFLIB_DIR = WEBINF_DIR + "/lib"; 
    
    private static final String ABC_JAR_NAME = "abc.jar";
    
    private static final String ABC_JAR_PATH 
        = "TestData/WebAppLibrariesContainerTests/" + ABC_JAR_NAME;
    
    private static final String FOO_JAR_NAME = "foo.jar";
    
    private static final String FOO_JAR_PATH
        = "TestData/WebAppLibrariesContainerTests/" + FOO_JAR_NAME;
    
    private static final IProjectFacetVersion UTILITY_FACET
        = ProjectFacetsManager.getProjectFacet( "jst.utility" ).getVersion( "1.0" );
    
    private IProject webProject;
    private IProject utilityProject;
    
    private IFile jarFile1;
    private IPath jarFile1Path;
    private IFile jarFile2;
    private IPath jarFile2Path;
    
    private final Set resourcesToCleanup = new HashSet();

    private WebAppLibrariesContainerTests( final String name )
    {
        super( name );
    }
    
    public static Test suite()
    {
        final TestSuite suite = new TestSuite();
        
        suite.setName( "Web App Libraries Container Tests" );

        suite.addTest( new WebAppLibrariesContainerTests( "testDetectionOfAddedJar" ) );
        suite.addTest( new WebAppLibrariesContainerTests( "testDetectionOfTwoAddedJars" ) );
        suite.addTest( new WebAppLibrariesContainerTests( "testDetectionOfRemovedJar" ) );
        suite.addTest( new WebAppLibrariesContainerTests( "testDetectionOfTwoRemovedJars" ) );
        
        return suite;
    }
    
    public void testDetectionOfAddedJar()
    
        throws Exception
        
    {
        copyFromPlugin( ABC_JAR_PATH, this.jarFile1 );
        waitForCondition( cpContains( this.webProject, this.jarFile1Path ) );
        waitForCondition( cpContains( this.utilityProject, this.jarFile1Path ) );
    }

    public void testDetectionOfTwoAddedJars()
    
        throws Exception
        
    {
        copyFromPlugin( ABC_JAR_PATH, this.jarFile1 );
        waitForCondition( cpContains( this.webProject, this.jarFile1Path ) );
        waitForCondition( cpContains( this.utilityProject, this.jarFile1Path ) );

        copyFromPlugin( FOO_JAR_PATH, this.jarFile2 );
        waitForCondition( cpContains( this.webProject, this.jarFile2Path ) );
        waitForCondition( cpContains( this.utilityProject, this.jarFile2Path ) );
    }

    public void testDetectionOfRemovedJar()
    
        throws Exception
        
    {
        copyFromPlugin( ABC_JAR_PATH, this.jarFile1 );
        waitForCondition( cpContains( this.webProject, this.jarFile1Path ) );
        waitForCondition( cpContains( this.utilityProject, this.jarFile1Path ) );
        
        this.jarFile1.delete( true, null );
        waitForCondition( cpDoesNotContain( this.webProject, this.jarFile1Path ) );
        waitForCondition( cpDoesNotContain( this.utilityProject, this.jarFile1Path ) );
    }

    public void testDetectionOfTwoRemovedJars()
    
        throws Exception
        
    {
        copyFromPlugin( ABC_JAR_PATH, this.jarFile1 );
        waitForCondition( cpContains( this.webProject, this.jarFile1Path ) );
        waitForCondition( cpContains( this.utilityProject, this.jarFile1Path ) );
        
        copyFromPlugin( FOO_JAR_PATH, this.jarFile2 );
        waitForCondition( cpContains( this.webProject, this.jarFile2Path ) );
        waitForCondition( cpContains( this.utilityProject, this.jarFile2Path ) );
        
        this.jarFile1.delete( true, null );
        waitForCondition( cpDoesNotContain( this.webProject, this.jarFile1Path ) );
        assertCondition( cpContains( this.webProject, this.jarFile2Path ) );
        waitForCondition( cpDoesNotContain( this.utilityProject, this.jarFile1Path ) );
        assertCondition( cpContains( this.utilityProject, this.jarFile2Path ) );
        
        this.jarFile2.delete( true, null );
        waitForCondition( cpDoesNotContain( this.webProject, this.jarFile2Path ) );
        waitForCondition( cpDoesNotContain( this.utilityProject, this.jarFile2Path ) );
    }
    
    protected void setUp()
    
        throws Exception
        
    {
        System.setProperty( "wtp.autotest.noninteractive", "true" );
        
        IFacetedProject fpj;
        
        fpj = ProjectFacetsManager.create( "abc", null, null );

        fpj.installProjectFacet( JavaFacetUtils.JAVA_50, null, null );
        fpj.installProjectFacet( WebFacetUtils.WEB_24, null, null );
        
        this.webProject = fpj.getProject();
        
        this.jarFile1 = this.webProject.getFile( WEBINFLIB_DIR + "/" + ABC_JAR_NAME );
        this.jarFile1Path = this.jarFile1.getLocation();
        
        this.jarFile2 = this.webProject.getFile( WEBINFLIB_DIR + "/" + FOO_JAR_NAME );
        this.jarFile2Path = this.jarFile2.getLocation();
        
        addResourceToCleanup( this.webProject );

        fpj = ProjectFacetsManager.create( "subordinate", null, null );
        
        fpj.installProjectFacet( JavaFacetUtils.JAVA_50, null, null );
        fpj.installProjectFacet( UTILITY_FACET, null, null );
        
        final IPath path 
            = ( new Path( WebAppLibrariesContainer.CONTAINER_ID ) ).append( "abc" );
        
        final IClasspathEntry cpe = JavaCore.newContainerEntry( path );
        
        addToClasspath( JavaCore.create( fpj.getProject() ), cpe );
        
        this.utilityProject = fpj.getProject();
        
        addResourceToCleanup( this.utilityProject );
    }
    
    protected void tearDown() 
    
        throws Exception 
        
    {
        for( Iterator itr = this.resourcesToCleanup.iterator(); itr.hasNext(); )
        {
            final IResource r = (IResource) itr.next();
            r.delete( true, null );
        }
    }
    
    private void addResourceToCleanup( final IResource resource )
    {
        this.resourcesToCleanup.add( resource );
    }

    private static void copyFromPlugin( final String src,
                                        final IFile dest )

        throws CoreException, IOException

    {
        final Bundle bundle = Platform.getBundle( "org.eclipse.jst.j2ee.tests" );
        final URL entryUrl = bundle.getEntry( src );
        final InputStream in = entryUrl.openStream();
        
        try
        {
            dest.create( in, true, null );
        }
        finally
        {
            try
            {
                in.close();
            }
            catch( IOException e ) {}
        }
    }
    
    private static void addToClasspath( final IJavaProject jproj,
                                        final IClasspathEntry cpe )
    
        throws JavaModelException
        
    {
        final IClasspathEntry[] oldcp = jproj.getRawClasspath();
        final IClasspathEntry[] newcp = new IClasspathEntry[ oldcp.length + 1 ];
        
        System.arraycopy( oldcp, 0, newcp, 0, oldcp.length );
        newcp[ oldcp.length ] = cpe;
        
        jproj.setRawClasspath( newcp, null );
    }
    
    private static void waitForCondition( final ICondition condition )
    
        throws Exception
        
    {
        waitForCondition( condition, 10 );
    }
    
    private static void waitForCondition( final ICondition condition,
                                          final int seconds )
    
        throws Exception
        
    {
        for( int i = 0; i < seconds && ! condition.check(); i++ )
        {
            try
            {
                Thread.sleep( 1000 );
            }
            catch( InterruptedException e ) {}
        }
        
        assertCondition( condition );
    }
    
    private static void assertCondition( final ICondition condition )
    
        throws Exception
        
    {
        assertTrue( condition.check() );
    }
    
    private static ICondition cpContains( final IProject project,
                                          final IPath entry )
    {
        return new ClasspathContainsCondition( project, entry );
    }
    
    private static ICondition cpDoesNotContain( final IProject project,
                                                final IPath entry )
    {
        return new ClasspathDoesNotContainCondition( project, entry );
    }
    
    private static interface ICondition
    {
        boolean check() throws Exception;
    }
    
    private static abstract class AbstractClasspathCondition
    
        implements ICondition
        
    {
        private final IProject project;
        private final IPath entry;
        
        public AbstractClasspathCondition( final IProject project,
                                           final IPath entry )
        {
            this.project = project;
            this.entry = entry;
        }
        
        protected boolean internalCheck()
        
            throws Exception
            
        {
            final IJavaProject jproj = JavaCore.create( this.project );
            final IClasspathEntry[] cp = jproj.getResolvedClasspath( true );
            
            for( int i = 0; i < cp.length; i++ )
            {
                if( cp[ i ].getPath().equals( this.entry ) )
                {
                    return true;
                }
            }
            
            return false;
        }
    }

    private static final class ClasspathContainsCondition
    
        extends AbstractClasspathCondition
        
    {
        public ClasspathContainsCondition( final IProject project,
                                           final IPath entry )
        {
            super( project, entry );
        }
        
        public boolean check()
        
            throws Exception
            
        {
            return internalCheck();
        }
    }

    private static final class ClasspathDoesNotContainCondition
    
        extends AbstractClasspathCondition
        
    {
        public ClasspathDoesNotContainCondition( final IProject project,
                                                 final IPath entry )
        {
            super( project, entry );
        }
        
        public boolean check()
        
            throws Exception
            
        {
            return ! internalCheck();
        }
    }
    
}
