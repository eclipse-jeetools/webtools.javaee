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

package org.eclipse.jst.j2ee.web.project.facet;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class WebFacetInstallConfig 
{
    private String earProjectName;
    private String contextRoot;
    private String contentDir;
    private boolean createWebInfSrc;
    
    public WebFacetInstallConfig()
    {
        this.earProjectName = null;
        this.contextRoot = null;
        this.contentDir = "web";
        this.createWebInfSrc = false;
    }
    
    public String getEarProjectName()
    {
        return this.earProjectName;
    }
    
    public void setEarProjectName( final String earProjectName )
    {
        this.earProjectName = earProjectName;
    }
    
    public String getContextRoot()
    {
        return this.contextRoot;
    }
    
    public void setContextRoot( final String contextRoot )
    {
        this.contextRoot = contextRoot;
    }
    
    public String getContentDir()
    {
        return this.contentDir;
    }
    
    public void setContentDir( final String contentDir )
    {
        this.contentDir = contentDir;
    }
    
    public boolean getCreateWebInfSrc()
    {
        return this.createWebInfSrc;
    }
    
    public void setCreateWebInfSrc( final boolean createWebInfSrc )
    {
        this.createWebInfSrc = createWebInfSrc;
    }
    
}
