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

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.ui.internal.Messages;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractDDNode;
import org.eclipse.swt.graphics.Image;

/**
 * Deployment Descriptor node that contains information about Enterprise beans.
 * 
 * 
 * @author Dimitar Giormov
 *
 */
public class BeanNode extends AbstractDDNode {
  
  public BeanNode(JavaEEObject bean) {
    super(bean);
    image = null;
    String qualified = getEjbClassQualifiedName();
    if (qualified != null) {
      text = Signature.getSimpleName(qualified);
      description = Messages.ENTERPRISE_BEAN_CLASS_DESCRIPTION + qualified;
    }
  }
  
  public String getEjbClassQualifiedName() {
    if (adapterNode instanceof SessionBean){
      return ((SessionBean)adapterNode).getEjbClass();
    } else if (adapterNode instanceof MessageDrivenBean){
      return ((MessageDrivenBean)adapterNode).getEjbClass();
    }else if (adapterNode instanceof EntityBean){
      return ((EntityBean)adapterNode).getEjbClass();
    }
    return null;
  }

  public JavaEEObject getEnterpriseBean(){
	  return (JavaEEObject) adapterNode;
  }

  @Override
  public List getChildren() {
    return null;
  }

  @Override
  public boolean hasChildren() {
    return false;
  }
  
  @Override
  public Image getImage() {
    return JavaPluginImages.get(JavaPluginImages.IMG_OBJS_CUNIT);
  }
  
}
