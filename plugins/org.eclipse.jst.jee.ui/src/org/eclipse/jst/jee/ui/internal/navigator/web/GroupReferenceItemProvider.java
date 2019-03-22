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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.plugin.JEEUIPluginIcons;
import org.eclipse.swt.graphics.Image;

/**
 * JNDI References group in Deployment Descriptor node. 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupReferenceItemProvider extends AbstractWebGroupProvider{

    private static Image REFERENCES;

    public GroupReferenceItemProvider(WebApp webApp) {
        super(webApp);
        text = Messages.REFERENCES;
    }


    @Override
    public List getChildren() {
      List result = new ArrayList();
        if (javaee != null){
          result.addAll(((WebApp) javaee).getEjbRefs());
          result.addAll(((WebApp) javaee).getEjbLocalRefs());
          result.addAll(((WebApp) javaee).getResourceRefs());
          result.addAll(((WebApp) javaee).getResourceEnvRefs());
          result.addAll(((WebApp) javaee).getEnvEntries());
          result.addAll(((WebApp) javaee).getMessageDestinationRefs());
          result.addAll(((WebApp) javaee).getPersistenceContextRefs());
          result.addAll(((WebApp) javaee).getPersistenceUnitRefs());
          result.addAll(((WebApp) javaee).getServiceRefs());
          return result;
        }
        return null;
    }

    @Override
    public Image getImage() {
        return getReferencesImage();
    }


    public static Image getReferencesImage() {
        if (REFERENCES == null) {
            URL url = (URL) J2EEPlugin.getPlugin().getImage(JEEUIPluginIcons.RESOURCE_REFERENCE_OBJ);
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(url); 
            REFERENCES = imageDescriptor.createImage();
        }
        return REFERENCES;
    }

}
