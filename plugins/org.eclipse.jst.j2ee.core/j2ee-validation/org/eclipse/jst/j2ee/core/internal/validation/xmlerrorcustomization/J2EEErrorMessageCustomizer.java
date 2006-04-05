/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.core.internal.validation.xmlerrorcustomization;

import java.util.Iterator;

import org.eclipse.wst.xml.core.internal.validation.errorcustomization.ElementInformation;
import org.eclipse.wst.xml.core.internal.validation.errorcustomization.IErrorMessageCustomizer;

/**
 * A J2EE message customizer for the XML validator. This error customizer will
 * customize errors on application.xml.
 */
public class J2EEErrorMessageCustomizer implements IErrorMessageCustomizer 
{

  /* (non-Javadoc)
   * @see org.eclipse.wst.xml.core.internal.validation.errorcustomization.IErrorMessageCustomizer#customizeMessage(org.eclipse.wst.xml.core.internal.validation.errorcustomization.ElementInformation, java.lang.String, java.lang.Object[])
   */
  public String customizeMessage(ElementInformation elementInfo, String errorKey, Object[] arguments) 
  {
	if ("cvc-complex-type.2.4.a".equals(errorKey) || "cvc-complex-type.2.4.b".equals(errorKey))
	{
      if ("application".equals(elementInfo.getLocalname()))
      {
        boolean applicationHasModule = false;
        for (Iterator i = elementInfo.getChildren().iterator(); i.hasNext(); )
        {
          ElementInformation child = (ElementInformation)i.next();
          if ("module".equals(child.getLocalname()))
          {
            applicationHasModule = true;
            break;
          }  
        }  
        if (!applicationHasModule)
        {  
          return J2EEXMLCustomValidationMessages.J2EE_APPLICATION_ONE_OR_MORE_MODULES;
        }  
      }
    }
    return null;
  }
}
