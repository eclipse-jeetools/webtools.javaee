/******************************************************************************
 * Copyright (c) 2010 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui;

import static org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil.checkForComponentDependencyAttribute;
import static org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil.getDefaultRuntimePath;
import static org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil.isClassFolderEntry;
import static org.eclipse.jst.j2ee.classpathdep.ClasspathDependencyUtil.modifyDependencyPath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.ui.ISharedImages;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants.DependencyAttributeType;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyExtensions;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;
import org.eclipse.jst.j2ee.internal.ui.J2EEModuleDependenciesPropertyPage.ClasspathEntryProxy;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.ui.internal.taskwizard.IWizardHandle;
import org.eclipse.wst.common.componentcore.ui.internal.taskwizard.WizardFragment;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;

@SuppressWarnings( "restriction" )

public final class AddJavaBuildPathEntriesWizardFragment 

    extends WizardFragment 
    
{
    public static final String PROP_SELECTION = "selection"; //$NON-NLS-1$
    
    private IWizardHandle wizard;
    private TreeViewer viewer;
    private IJavaProject jproj;
    
    public AddJavaBuildPathEntriesWizardFragment() 
    {
        setComplete( false );
    }
    
    @Override
    public boolean hasComposite() 
    {
        return true;
    }

    @Override
    public Composite createComposite( final Composite parent, 
                                      final IWizardHandle handle ) 
    {
        this.wizard = handle;
        
        handle.setTitle( Resources.title );
        handle.setDescription( Resources.description );
        handle.setImageDescriptor( JavaPluginImages.DESC_WIZBAN_ADD_LIBRARY );
        
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( new GridLayout() );
        
        this.viewer = new TreeViewer( composite, SWT.MULTI | SWT.BORDER );

        final GridData gd = new GridData( GridData.FILL_BOTH );
        gd.widthHint = 390;
        gd.heightHint = 185;
        
        this.viewer.getTree().setLayoutData( gd );
        
        this.viewer.setContentProvider( new BuildPathEntriesContentProvider() );
        this.viewer.setLabelProvider( new BuildPathEntriesLabelProvider() );
        
        this.viewer.addSelectionChangedListener
        (
            new ISelectionChangedListener() 
            {
                public void selectionChanged( final SelectionChangedEvent event ) 
                {
                    handleSelectionChanged();
                }
            }
        );
        
        this.viewer.addDoubleClickListener
        (
            new IDoubleClickListener() 
            {
                public void doubleClick( final DoubleClickEvent event ) 
                {
                    handleDoubleClick();
                }
            }
        );
        
        final IProject proj = (IProject) getTaskModel().getObject( IReferenceWizardConstants.PROJECT );
        this.jproj = JavaCore.create( proj );
        
        this.viewer.setInput( new Object() );

        return composite;
    }
    
    private void handleSelectionChanged() 
    {
        final List<IClasspathEntry> list = new ArrayList<IClasspathEntry>();
        final IStructuredSelection selection = (IStructuredSelection) this.viewer.getSelection();
        final boolean isWebApp = JavaEEProjectUtilities.isDynamicWebProject( this.jproj.getProject() );
        
        for( Iterator<?> itr = selection.iterator(); itr.hasNext(); )
        {
            final Object obj = itr.next();
            
            if( obj instanceof IClasspathEntry )
            {
                final IClasspathEntry cpeOriginal = (IClasspathEntry) obj;
                final IPath runtimePath = getDefaultRuntimePath( isWebApp, isClassFolderEntry( cpeOriginal ) );
                final IClasspathEntry cpeTagged = modifyDependencyPath( cpeOriginal, runtimePath );
                
                list.add( cpeTagged );
            }
            else
            {
                list.clear();
                break;
            }
        }
        
        getTaskModel().putObject( PROP_SELECTION, list );
        
        setComplete( ! list.isEmpty() );
        this.wizard.update();
    }
    
    private void handleDoubleClick()
    {
        advanceToNextPageOrFinish();
    }
    
    private IJavaProject getJavaProject()
    {
        return this.jproj;
    }

    public static String getClasspathEntryText( final IJavaProject jproj,
                                                final IClasspathEntry cpe )
    {
        final int type = cpe.getEntryKind();
        
        if( type == IClasspathEntry.CPE_CONTAINER )
        {
            try
            {
                final IClasspathContainer container = JavaCore.getClasspathContainer( cpe.getPath(), jproj );
                return container.getDescription();
            }
            catch( Exception e )
            {
            	J2EEUIPlugin.logError(e);
            }
        }
        else if( type == IClasspathEntry.CPE_LIBRARY )
        {
            final IPath path = cpe.getPath();
            
            final StringBuilder buf = new StringBuilder();
            buf.append( path.lastSegment() );
            buf.append( " - " ); //$NON-NLS-1$
            buf.append( path.removeLastSegments( 1 ).toOSString() );
            
            return buf.toString();
        }
        
        return cpe.getPath().toPortableString();
    }

    public static Image getClasspathEntryImage( final IJavaProject jproj,
                                                final IClasspathEntry cpe )
    {
        final int type = cpe.getEntryKind();
        final String imgId;
        
        if( type == IClasspathEntry.CPE_CONTAINER )
        {
            imgId = ISharedImages.IMG_OBJS_LIBRARY;
        }
        else if( type == IClasspathEntry.CPE_LIBRARY )
        {
            imgId = ISharedImages.IMG_OBJS_JAR;
        }
        else if( type == IClasspathEntry.CPE_VARIABLE )
        {
            imgId = ISharedImages.IMG_OBJS_CLASSPATH_VAR_ENTRY;
        }
        else
        {
            imgId = null;
        }
        
        return ( imgId == null ? null : JavaUI.getSharedImages().getImage( imgId ) );
    }

    private final class BuildPathEntriesContentProvider
    
        implements ITreeContentProvider
        
    {
        public Object[] getElements( final Object input ) 
        {
            final List<IClasspathEntry> elements = new ArrayList<IClasspathEntry>();
            final List<?> filteredContainerIds = ClasspathDependencyExtensions.get().getFilteredClasspathContainerIDs();
            
            try
            {
                for( IClasspathEntry cpe : getJavaProject().getRawClasspath() )
                {
                    final int type = cpe.getEntryKind();
                    final IPath path = cpe.getPath();
                    boolean relevant = false;
                    
                    if( type == IClasspathEntry.CPE_CONTAINER )
                    {
                        if( path.segmentCount() > 0 && ! filteredContainerIds.contains( path.segment( 0 ) ) )
                        {
                            relevant = true;
                        }
                    }
                    else if( type == IClasspathEntry.CPE_LIBRARY || type == IClasspathEntry.CPE_VARIABLE  )
                    {
                        relevant = true;
                    }
                    
                    if( relevant )
                    {
                        if( checkForComponentDependencyAttribute( cpe, DependencyAttributeType.CLASSPATH_COMPONENT_NONDEPENDENCY ) != null )
                        {
                            relevant = false;
                        }
                    }
                    
                    if( relevant )
                    {
                        boolean found = false;
                        
                        for( Object directive : (List<?>) getTaskModel().getObject( IReferenceWizardConstants.ALL_DIRECTIVES ) )
                        {
                            if( directive instanceof ClasspathEntryProxy )
                            {
                                final ClasspathEntryProxy proxy = (ClasspathEntryProxy) directive;
                                
                                if( type == proxy.entry.getEntryKind() && path.equals( proxy.entry.getPath() ) )
                                {
                                    found = true;
                                    break;
                                }
                            }
                        }
                        
                        if( found )
                        {
                            relevant = false;
                        }
                    }
                    
                    if( relevant )
                    {
                        elements.add( cpe );
                    }
                }
            }
            catch( CoreException e )
            {
            	J2EEUIPlugin.logError(e);
            }
            
            return elements.toArray();
        }

        public boolean hasChildren( final Object element ) 
        {
            return ( element instanceof IClasspathEntry ) && 
                   ( ( (IClasspathEntry) element ).getEntryKind() == IClasspathEntry.CPE_CONTAINER );
        }
    
        public Object[] getChildren( final Object parent ) 
        {
            if( parent instanceof IClasspathEntry )
            {
                final IClasspathEntry cpe = (IClasspathEntry) parent;
                
                if( cpe.getEntryKind() == IClasspathEntry.CPE_CONTAINER )
                {
                    final List<ChildClasspathEntry> children = new ArrayList<ChildClasspathEntry>();
                    
                    try
                    {
                        final IClasspathContainer container = JavaCore.getClasspathContainer( cpe.getPath(), getJavaProject() );
                        
                        if( container != null )
                        {
                            for( IClasspathEntry child : container.getClasspathEntries() )
                            {
                                children.add( new ChildClasspathEntry( cpe, child ) );
                            }
                        }
                    }
                    catch( Exception e )
                    {
                    	J2EEUIPlugin.logError(e);
                    }
                    
                    return children.toArray();
                }
            }
            
            return new Object[ 0 ];
        }

        public Object getParent( final Object element ) 
        {
            if( element instanceof ChildClasspathEntry )
            {
                return ( (ChildClasspathEntry) element ).parent;
            }
            
            return null;
        }

        public void inputChanged( final Viewer viewer, 
                                  final Object oldInput, 
                                  final Object newInput ) 
        {
        }

        public void dispose() 
        {
        }
    }
    
    private final class BuildPathEntriesLabelProvider
    
        extends LabelProvider
        
    {
        @Override
        public String getText( final Object element ) 
        {
            final IClasspathEntry cpe;
            
            if( element instanceof IClasspathEntry )
            {
                cpe = (IClasspathEntry) element;
            }
            else
            {
                cpe = ( (ChildClasspathEntry) element ).entry;
            }
            
            return getClasspathEntryText( getJavaProject(), cpe );
        }

        @Override
        public Image getImage( final Object element ) 
        {
            final IClasspathEntry cpe;
            
            if( element instanceof IClasspathEntry )
            {
                cpe = (IClasspathEntry) element;
            }
            else
            {
                cpe = ( (ChildClasspathEntry) element ).entry;
            }
            
            return getClasspathEntryImage( getJavaProject(), cpe );
        }
    }
    
    private static final class ChildClasspathEntry
    {
        public final IClasspathEntry parent;
        public final IClasspathEntry entry;
        
        public ChildClasspathEntry( final IClasspathEntry parent,
                                    final IClasspathEntry entry )
        {
            this.parent = parent;
            this.entry = entry;
        }
    }
    
    public static final class Resources extends NLS
    {
        public static String title;
        public static String description;
        
        static
        {
            initializeMessages( AddJavaBuildPathEntriesWizardFragment.class.getName(), Resources.class );
        }
    }
    
}
