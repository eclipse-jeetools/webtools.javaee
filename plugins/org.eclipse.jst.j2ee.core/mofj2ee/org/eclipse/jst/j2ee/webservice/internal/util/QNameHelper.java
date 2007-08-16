/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.webservice.internal.util;

import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;



/**
* JSR-109 v1.1 changed the way qualified names are represented. As a result the EMF model changed as well. 
* The new model is capable of reading/writing both v1.0 and v1.1 descriptors but the QName APIs work differently
* depending on the version of the descriptor. This class provides static methods to set values on QName's in order
* to hide and isolate the version checking logic.
*/
public class QNameHelper
{

  private static String DEFAULT_PREFIX="pfx";//$NON-NLS-1$

  /*
  * Sets the namespaceURI and the localpart on the supplied QName to the supplied values respectively.
  * @param qnameEObject The properties on this QName will be set. If the QName does not belong to a Resource,
  *                                    the version is assumed to be J2EE 1.4.
  * @param namespaceURI The namespaceURI
  * @param localPart The localPart
  * @return boolean Returns true if values were set successfully. False otherwise.
  */
  public static void setQNameNamespaceURI(QName qnameEObject, String namespaceURI)
  {
    String localpart = qnameEObject.getLocalPart();
    setQNameValue(qnameEObject, namespaceURI, localpart);    
  }
  
  /*
  * Sets the namespaceURI and the localpart on the supplied QName to the supplied values respectively.
  * @param qnameEObject The properties on this QName will be set. If the QName does not belong to a Resource,
  *                                    the version is assumed to be J2EE 1.4.
  * @param namespaceURI The namespaceURI
  * @param localPart The localPart
  * @return boolean Returns true if values were set successfully. False otherwise.
  */
  public static void setQNameValue(QName qnameEObject, String namespaceURI, String localPart)
  {
    setQNameValue(qnameEObject, namespaceURI, localPart, ""); //$NON-NLS-1$   
  }

  /**
  * Sets the namespaceURI, localpart, and prefix on the supplied QName to the supplied values respectively.
  * @param qnameEObject The properties on this QName will be set. If the QName does not belong to a Resource,
  *                                    the version is assumed to be J2EE 1.4.
  * @param namespaceURI The namespaceURI
  * @param localPart The localPart
  * @param prefix The prefix. Used in JSR-109 v1.1 only. This is the prefix that will be used for the namespace declaration
                           If the supplied prefix is null or of length 0, the existing prefix is used. If no prefix exists, and this
  *                       is a JSR-109 v1.1 descriptor the default prefix defined in DEFAULT_PREFIX is used.
  * @return boolean Returns true if values were set successfully. False otherwise.
  */
  public static void setQNameValue(QName qnameEObject, String namespaceURI, String localPart, String prefix)
  {
    int versionID;
    
    //Get the versionID from the QName's Resource if available
    J2EEVersionResource res = (J2EEVersionResource)qnameEObject.eResource();
    if (res == null)
    {
      //use a J2EE 1.4 version ID as the default
      versionID = J2EEVersionConstants.J2EE_1_4_ID;
    }
    else
    {
      versionID = res.getJ2EEVersionID();
    }

    setQNameValue(qnameEObject, namespaceURI, localPart, prefix, versionID);
    
    
  }

  /**
  * Sets the namespaceURI, localpart, and prefix on the supplied QName to the supplied values respectively.
  * @param qnameEObject The properties on this QName will be set.
  * @param namespaceURI The namespaceURI
  * @param localPart The localPart
  * @param prefix The prefix. Used in JSR-109 v1.1 only. This is the prefix that will be used for the namespace declaration
                           If the supplied prefix is null or of length 0, the existing prefix is used. If no prefix exists, and this
  *                       is a JSR-109 v1.1 descriptor the default prefix defined DEFAULT_PREFIX is used.
  *@param versionID The J2EE version. Should be set to one of the version IDs defined in J2EEVersionConstants.
  * @return boolean Returns true if values were set successfully. False otherwise.
  */
  public static void setQNameValue(QName qnameEObject, String namespaceURI, String localPart, String prefix, int versionID)
  {      

    if (versionID==J2EEVersionConstants.J2EE_1_3_ID)
    {
      //ignore the prefix and set things the old way
      qnameEObject.setNamespaceURI(namespaceURI);
      qnameEObject.setLocalPart(localPart);    
    }
    else
    {
      //resource is J2EE 1.4
      if (prefix==null || prefix.length()==0)
      {
        //use the existing prefix
        prefix = qnameEObject.getInternalPrefixOrNsURI();
        if (prefix==null || prefix.length()==0)
        {
          //use the default prefix
          prefix = new String(DEFAULT_PREFIX);
        }
      }
      qnameEObject.setValues(prefix, namespaceURI, localPart);
    
    }
    
  }
  
}

