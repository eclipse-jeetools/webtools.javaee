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

package org.eclipse.jst.j2ee.project.facet;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class EarFacetInstallConfig 
{
    private String contentDir;
    
    public EarFacetInstallConfig()
    {
        this.contentDir = "ear";
    }
    
    public String getContentDir()
    {
        return this.contentDir;
    }
    
    public void setContentDir( final String contentDir )
    {
        this.contentDir = contentDir;
    }
    
}
