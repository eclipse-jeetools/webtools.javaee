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
package org.eclipse.jst.jee.ui.internal.navigator.ejb;

import java.net.URL;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractDDNode;
import org.eclipse.swt.graphics.Image;

/**
 * Deployment Descriptor node that contains information about ActivationConfigProperties.
 * 
 * @author Dimitar Giormov
 *
 */
public class ActivationConfigProperties extends AbstractDDNode {

  public ActivationConfigProperties(Object adapterNode) {
    super(adapterNode);
  }

  @Override
  public List getChildren() {
    return (List) adapterNode;
  }

  @Override
  public boolean hasChildren() {
    return true;
  }
  
  @Override
  public String getText() {
    return Messages.ACTIVATION_CONFIG_PROPERTIES;
  }
  
  @Override
  public Image getImage() {
    URL url = (URL) J2EEPlugin.getPlugin().getImage("access_intent_obj"); //$NON-NLS-1$
    return ImageDescriptor.createFromURL(url).createImage();
  }

}
