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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractDDNode;
import org.eclipse.swt.graphics.Image;


/**
 * Session Beans group Deployment Descriptor node that has a number of sub nodes 
 * 
 * @author Dimitar Giormov
 */
public class GroupEjbSession extends AbstractDDNode {

	public GroupEjbSession(EJBJar parent) {
		super(parent);
    image = null;
    text = Messages.SESSION_BEANS;
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		List sessionBeans = null;
		 EnterpriseBeans enterpriseBeans = ((EJBJar) adapterNode).getEnterpriseBeans();
		 if (enterpriseBeans != null)
			 sessionBeans = enterpriseBeans.getSessionBeans();
		 
		if (sessionBeans != null && sessionBeans.size()>0){
			children.addAll(sessionBeans);
		}
		return children;
	}

	@Override
	public boolean hasChildren() {
		return !this.getChildren().isEmpty();
	}
	
	@Override
	public Image getImage() {
	  URL url = (URL) J2EEPlugin.getPlugin().getImage("sessionBean_obj"); //$NON-NLS-1$
      return ImageDescriptor.createFromURL(url).createImage();
	}
}
