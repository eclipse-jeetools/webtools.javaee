/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.net.URL;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.swt.graphics.Image;

/**
 * Deployment Descriptor node that contains information about JNDI references.
 * Like: Ejb reference, Ejb Local Reference, Resource Reference, Resource Environment Reference,
 * Environment Entry, Service Reference 
 * 
 * @author Dimitar Giormov
 *
 */
public class JndiRefNode extends AbstractDDNode {

  private final int kind;

  public class KINDS {
    public static final int EJBLOCALREF = 0;
    public static final int EJBREF = 1;
    public static final int ENVENTRY = 2;
    public static final int RESENVENTY = 3;
    public static final int RESREF = 4;
    public static final int SERVICEREF = 5;
  }

  public JndiRefNode(Object adapterNode, int kind) {
    super(adapterNode);
    this.kind = kind;

  }

  public int getKind() {
    return kind;
  }

  @Override
  public List getChildren() {
    return (List) adapterNode;
  }

  @Override
  public String getText() {
    switch (kind) {
    case KINDS.EJBLOCALREF:
      return Messages.EJBLOCAL_REFERENCES;

    case KINDS.EJBREF:
      return Messages.EJB_REFERENCES;
    case KINDS.ENVENTRY:
      return Messages.ENVIROMENT_ENTRIES;
    case KINDS.RESENVENTY:
      return Messages.RESOURCE_ENVIROMENT_ENTRIES;
    case KINDS.RESREF:
      return Messages.RESOURCE_REFERENCES;
    case KINDS.SERVICEREF:
      return Messages.SERVICE_REFERENCES;

    default:
      break;
    }
    return super.getText();
  }
  
  @Override
  public Image getImage() {
    switch (kind) {
    case KINDS.EJBLOCALREF:{
      URL url = (URL) J2EEPlugin.getPlugin().getImage("ejbRef_obj"); //$NON-NLS-1$
      return ImageDescriptor.createFromURL(url).createImage();
    }
    case KINDS.EJBREF:{
      URL url = (URL) J2EEPlugin.getPlugin().getImage("ejbRef_obj"); //$NON-NLS-1$
      return ImageDescriptor.createFromURL(url).createImage();
      }
    
    case KINDS.ENVENTRY:
      {
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
    case KINDS.RESENVENTY:
      {
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
    case KINDS.RESREF:
      {
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }
    case KINDS.SERVICEREF:
      {
        URL url = (URL) J2EEPlugin.getPlugin().getImage("resourceRef_obj"); //$NON-NLS-1$
        return ImageDescriptor.createFromURL(url).createImage();
      }

    default:
      break;
    }
    return super.getImage();
  }

  
  @Override
  public boolean hasChildren() {

    return true;
  }

}
