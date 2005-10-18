/******************************************************************************
 * Copyright (c) 2005 BEA Systems, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial API and implementation
 ******************************************************************************/

package org.eclipse.jst.j2ee.ui.project.facet;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarSelectionPanel

    extends Composite
    
{
    private final ArrayList listeners;
    private final Button addToEar;
    private final Combo combo;
    private final Button newButton;
   
    public EarSelectionPanel( final Composite parent, 
                              final int style ) 
    {
        super( parent, style );
        
        this.listeners = new ArrayList();
        
        final GridLayout layout = new GridLayout( 2, false );
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        
        setLayout( layout );
        
        this.addToEar = new Button( this, SWT.CHECK );
        this.addToEar.setText( Resources.addToEarLabel );
        this.addToEar.setLayoutData( gdhspan( gdhfill(), 2 ) );
        
        this.addToEar.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( final SelectionEvent event ) 
            {
                resetEnabledState();
                notifyListeners();
            }
        } );
        
        this.combo = new Combo( this, SWT.BORDER | SWT.READ_ONLY );
        this.combo.setLayoutData( gdhfill() );
        
        this.combo.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( final SelectionEvent event )
            {
                notifyListeners();
            }
        } );
        
        this.newButton = new Button( this, SWT.PUSH );
        this.newButton.setText( Resources.newButtonLabel );
        
        this.newButton.addSelectionListener( new SelectionAdapter()
        {
            public void widgetSelected( final SelectionEvent event )
            {
                handleAddButton();
                notifyListeners();
            }
        } );
        
        resetEnabledState();
        
        populateEarList();
        
        if( this.combo.getItemCount() > 0 )
        {
            this.combo.select( 0 );
        }
    }
    
    public boolean getAddToEar()
    {
        return this.addToEar.getSelection();
    }
    
    public String getEarProjectName()
    {
        if( this.addToEar.getSelection() )
        {
            final int index = this.combo.getSelectionIndex();
            return index == -1 ? null : this.combo.getItem( index );
        }
        else
        {
            return null;
        }
    }
    
    public void addListener( final Listener listener )
    {
        this.listeners.add( listener );
    }
    
    public void removeListener( final Listener listener )
    {
        this.listeners.remove( listener );
    }
    
    private void notifyListeners()
    {
        for( int i = 0, n = this.listeners.size(); i < n; i++ )
        {
            ( (Listener) this.listeners.get( i ) ).handleEvent( null );
        }
    }
    
    private void resetEnabledState()
    {
        final boolean enabled = this.addToEar.getSelection();
        
        this.combo.setEnabled( enabled );
        this.newButton.setEnabled( enabled );
    }
    
    private void populateEarList()
    {
        this.combo.removeAll();
        
        final IWorkspace ws = ResourcesPlugin.getWorkspace();
        final IProject[] projects = ws.getRoot().getProjects();
        
        for( int i = 0; i < projects.length; i++ )
        {
            final IProject project = projects[ i ];
            
            
            if( !ModuleCoreNature.isFlexibleProject(project) )
            {
                continue;
            }
            
            if(J2EEProjectUtilities.isEARProject(project))
            {
                this.combo.add( project.getName() );
            }
            }
    }

    private void handleAddButton()
    {
        final EarProjectWizard wizard = new EarProjectWizard();
        
        final WizardDialog dialog 
            = new WizardDialog( getShell(), wizard );
        
        if( dialog.open() != SWT.CANCEL )
        {
            final String earproj = wizard.getProjectName();
            
            if( earproj != null )
            {
                populateEarList();
                
                for( int i = 0, n = this.combo.getItemCount(); i < n; i++ )
                {
                    if( this.combo.getItem( i ).equals( earproj ) )
                    {
                        this.combo.select( i );
                        break;
                    }
                }
            }
        }
    }
    
    private static GridData gdhfill()
    {
        return new GridData( GridData.FILL_HORIZONTAL );
    }

    public static final GridData gdhspan( final GridData gd,
                                          final int span )
    {
        gd.horizontalSpan = span;
        return gd;
    }
    
    private static final class Resources
    
        extends NLS
        
    {
        public static String addToEarLabel;
        public static String newButtonLabel;
        
        static
        {
            initializeMessages( EarSelectionPanel.class.getName(), 
                                Resources.class );
        }
    }
    
}
