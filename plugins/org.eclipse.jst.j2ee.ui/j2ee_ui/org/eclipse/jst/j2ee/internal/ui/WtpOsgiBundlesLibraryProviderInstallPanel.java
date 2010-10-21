/******************************************************************************
 * Copyright (c) 2010, 2008 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 *    Paul Fullbright - [324111] Need better enablement behavior for WTP library providers
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.ui;

import static org.eclipse.jst.j2ee.internal.common.classpath.WtpOsgiBundlesLibraryProviderInstallOperationConfig.PROP_INCLUDE_WITH_APPLICATION_ENABLED;
import static org.eclipse.jst.j2ee.internal.common.classpath.WtpOsgiBundlesLibraryProviderInstallOperationConfig.PROP_INCLUDE_WITH_APPLICATION_SETTING_ENABLED;

import org.eclipse.jst.common.project.facet.core.libprov.IPropertyChangeListener;
import org.eclipse.jst.common.project.facet.ui.libprov.osgi.OsgiBundlesLibraryProviderInstallPanel;
import org.eclipse.jst.j2ee.internal.common.classpath.WtpOsgiBundlesLibraryProviderInstallOperationConfig;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class WtpOsgiBundlesLibraryProviderInstallPanel

    extends OsgiBundlesLibraryProviderInstallPanel
    
{
    @Override
    protected Control createFooter( final Composite composite )
    {
        final WtpOsgiBundlesLibraryProviderInstallOperationConfig cfg
            = (WtpOsgiBundlesLibraryProviderInstallOperationConfig) getOperationConfig();
        
        final Button copyOnPublishCheckBox = new Button( composite, SWT.CHECK );
        copyOnPublishCheckBox.setText( Resources.copyLibraries );
        copyOnPublishCheckBox.setSelection( cfg.isIncludeWithApplicationEnabled() );
        copyOnPublishCheckBox.setEnabled( cfg.isIncludeWithApplicationSettingEnabled() );
        
        copyOnPublishCheckBox.addSelectionListener
        (
            new SelectionAdapter()
            {
                @Override
                public void widgetSelected( final SelectionEvent event )
                {
                    cfg.setIncludeWithApplicationEnabled( copyOnPublishCheckBox.getSelection() );
                }
            }
        );
        
        final IPropertyChangeListener listener = new IPropertyChangeListener()
        {
            public void propertyChanged( final String property,
                                         final Object oldValue,
                                         final Object newValue )
            {
                if( PROP_INCLUDE_WITH_APPLICATION_ENABLED.equals( property ) ) 
                {
                    copyOnPublishCheckBox.setSelection( cfg.isIncludeWithApplicationEnabled() );
                }
                else if( PROP_INCLUDE_WITH_APPLICATION_SETTING_ENABLED.equals( property ) ) 
                {
                    copyOnPublishCheckBox.setEnabled( cfg.isIncludeWithApplicationSettingEnabled() );
                }
            }
        };
        
        cfg.addListener( listener, PROP_INCLUDE_WITH_APPLICATION_ENABLED, PROP_INCLUDE_WITH_APPLICATION_SETTING_ENABLED );
        
        copyOnPublishCheckBox.addDisposeListener
        (
            new DisposeListener()
            {
                public void widgetDisposed( final DisposeEvent event )
                {
                    cfg.removeListener( listener );
                }
            }
        );
        
        return copyOnPublishCheckBox;
    }

    private static final class Resources extends NLS
    {
        public static String copyLibraries;

        static
        {
            initializeMessages( WtpOsgiBundlesLibraryProviderInstallPanel.class.getName(), Resources.class );
        }
    }
    
}
