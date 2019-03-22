/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.web;

import java.net.URL;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.web.ErrorPage;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * Error pages group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupErrorPagesItemProvider extends AbstractWebGroupProvider{

    private static Image ERROR_PAGES;
    private static Image EXCEPTION_ERROR_PAGES;

    public GroupErrorPagesItemProvider(WebApp webApp) {
        super(webApp);
        text = Messages.ERROR_PAGES_ITEM_PROVIDER;
    }

    @Override
    public List getChildren() {
        if (javaee != null){
            return ((WebApp) javaee).getErrorPages();
        }
        return null;
    }

    @Override
    public Image getImage() {
        return getErrorPagesImage();
    }

    public static Image getErrorPagesImage() {
        if (ERROR_PAGES == null) {
            URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_ERROR_PAGE);
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
            ERROR_PAGES = imageDescriptor.createImage();
        }
        return ERROR_PAGES;
    }

    public static Image getErrorPagesImage(ErrorPage element) {
      if (element.getErrorCode() != null){
        
      }
      if (EXCEPTION_ERROR_PAGES == null) {
        URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.GROUP_ERROR_PAGE);
        ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
        EXCEPTION_ERROR_PAGES = imageDescriptor.createImage();
    }
    return EXCEPTION_ERROR_PAGES;
    }

}
