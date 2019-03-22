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
 * Entity Beans group Deployment Descriptor node that has a number of sub nodes 
 * 
 * @author Dimitar Giormov
 *
 */
public class GroupEjbEntity extends AbstractDDNode {

	public GroupEjbEntity(EJBJar parent) {
		super(parent);
    image = null;
    text = Messages.ENTITY_BEANS;
	}

	@Override
	public List getChildren() {
		List children = new ArrayList();
		List entities = null;
		 EnterpriseBeans enterpriseBeans = ((EJBJar) adapterNode).getEnterpriseBeans();
		 if (enterpriseBeans != null)
			 entities = enterpriseBeans.getEntityBeans();
		if (entities != null && entities.size() >0){
			children.addAll(entities);
		}
		return children;
	}

	@Override
	public boolean hasChildren() {
		return !getChildren().isEmpty();
	}
	
	@Override
	public Image getImage() {
	  URL url = (URL) J2EEPlugin.getPlugin().getImage("cmpEntity_obj"); //$NON-NLS-1$
      return ImageDescriptor.createFromURL(url).createImage();
	}
}
