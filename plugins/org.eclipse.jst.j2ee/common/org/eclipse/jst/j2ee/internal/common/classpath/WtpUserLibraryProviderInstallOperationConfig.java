/******************************************************************************
 * Copyright (c) 2008 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.jst.j2ee.internal.common.classpath;

import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class WtpUserLibraryProviderInstallOperationConfig

    extends UserLibraryProviderInstallOperationConfig
    
{
    private static final String CLASS_NAME 
        = WtpUserLibraryProviderInstallOperationConfig.class.getName();
    
    public static final String PROP_COPY_ON_PUBLISH_ENABLED 
        = CLASS_NAME + ".COPY_ON_PUBLISH_ENABLED"; //$NON-NLS-1$

    private boolean copyOnPublish = true;
    
    public boolean isCopyOnPublishEnabled()
    {
        return this.copyOnPublish;
    }
    
    public void setCopyOnPublishEnabled( final boolean copyOnPublish )
    {
        final boolean oldValue = this.copyOnPublish;
        this.copyOnPublish = copyOnPublish;
        notifyListeners( PROP_COPY_ON_PUBLISH_ENABLED, oldValue, this.copyOnPublish );
    }
}
