/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.util;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.webservice.internal.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType;
import org.eclipse.jst.j2ee.webservice.wscommon.DisplayNameType;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.Handler;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;


public class DescriptionGroupHelper
{

  public static String[] getValues(WebServices webServices, DescriptionGroupItem item)
  {
    return getValues(webServices, item, false);
  }
  
  public static String[] getValues(WebServices webServices, DescriptionGroupItem item, boolean getLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)webServices.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        return getDescriptions(webServices, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        return getDisplayNames(webServices, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        return getSmallIcons(webServices, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        return getLargeIcons(webServices, isJ2EE14, getLang);        
        //break;
      default:
        return null;      
    }
  }


  private static String[] getDescriptions(WebServices webServices, boolean isJ2EE14, boolean lang)
  {

  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] descriptions = webServices.getDescriptions().toArray();
      values = new String[descriptions.length];
      if (lang)
      {
        for(int i =0; i<descriptions.length; i++)
        {
          values[i] = ((Description)descriptions[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<descriptions.length; i++)
        {
          values[i] = ((Description)descriptions[i]).getValue();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
    	values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = webServices.getDescription();
      }
    }
    return values;
  }

  private static String[] getDisplayNames(WebServices webServices, boolean isJ2EE14, boolean lang)
  {
  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] displayNames = webServices.getDisplayNames().toArray();
      values = new String[displayNames.length];      
      if (lang)
      {
        for(int i =0; i<displayNames.length; i++)
        {
          values[i] = ((DisplayName)displayNames[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<displayNames.length; i++)
        {
          values[i] = ((DisplayName)displayNames[i]).getValue();
        }
      }

     
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = webServices.getDisplayName();
      }

    }
    return values;
  }

  private static String[] getSmallIcons(WebServices webServices, boolean isJ2EE14, boolean lang)
  {
  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] icons = webServices.getIcons().toArray();
      values = new String[icons.length];      
      if (lang)
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getSmallIcon();
        }
      }

      
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = webServices.getSmallIcon();
      }

    }
    return values;
  }

  private static String[] getLargeIcons(WebServices webServices, boolean isJ2EE14, boolean lang)
  {
  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] icons = webServices.getIcons().toArray();
      values = new String[icons.length];      
      if (lang)
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getLargeIcon();
        }
      }
      
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = webServices.getLargeIcon();
      }
      
    }
    return values;
  }


  //WebServicesDescription methods
  public static String getValue(WebServiceDescription webServicesDescription, DescriptionGroupItem item)
  {
    return getValue(webServicesDescription, item, false);
  }
  
  public static String getValue(WebServiceDescription webServicesDescription, DescriptionGroupItem item, boolean getLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)webServicesDescription.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        return getDescription(webServicesDescription, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        return getDisplayName(webServicesDescription, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        return getSmallIcon(webServicesDescription, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        return getLargeIcon(webServicesDescription, isJ2EE14, getLang);        
        //break;
      default:
        return null;      
    }
  }

  private static String getDescription(WebServiceDescription webServicesDescription, boolean isJ2EE14, boolean lang)
  {
  	String ret = null;
    if (isJ2EE14)
    {
      DescriptionType descType = webServicesDescription.getDescriptionType();
      if (descType!=null)
      {
        if (lang)
        {
        	ret =  descType.getLang();
        }
        else
        {
        	ret =  descType.getValue();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {	
  
		if( !lang )
		{
			ret = webServicesDescription.getDescription();
		}
    }
    return ret;
    
  }

  private static String getDisplayName(WebServiceDescription webServicesDescription, boolean isJ2EE14, boolean lang)
  {
  	String ret = null;
    if (isJ2EE14)
    {
      DisplayNameType dnType = webServicesDescription.getDisplayNameType();
      if (dnType!=null)
      {
        if (lang)
        {
        	ret =  dnType.getLang();
        }
        else
        {
        	ret =  dnType.getValue();
        }
      }

    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if( !lang )
      {
        ret = webServicesDescription.getDisplayName();
      }
    }
    return ret;
  }

  private static String getSmallIcon(WebServiceDescription webServicesDescription, boolean isJ2EE14, boolean lang)
  {
  	String ret = null;
    if (isJ2EE14)
    {
      IconType iconType = webServicesDescription.getIconType();
      if (iconType!=null)
      {
        if (lang)
        {
        	ret =  iconType.getLang();
        }
        else
        {
        	ret =  iconType.getSmallIcon();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if( !lang)
      {
      	ret =  webServicesDescription.getSmallIcon();
      }
    }
    return ret;
  }

  private static String getLargeIcon(WebServiceDescription webServicesDescription, boolean isJ2EE14, boolean lang)
  {
    String ret = null;
    if (isJ2EE14)
    {
      IconType iconType = webServicesDescription.getIconType();
      if (iconType!=null)
      {
        if (lang)
        {
        	ret =  iconType.getLang();
        }
        else
        {
        	ret =  iconType.getLargeIcon();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if( !lang )
      {
      	ret =  webServicesDescription.getLargeIcon();
      }
    }
    return ret;
  }

  //PortComponent methods
  public static String getValue(PortComponent portComponent, DescriptionGroupItem item)
  {
    return getValue(portComponent, item, false);
  }
  
  public static String getValue(PortComponent portComponent, DescriptionGroupItem item, boolean getLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)portComponent.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        return getDescription(portComponent, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        return getDisplayName(portComponent, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        return getSmallIcon(portComponent, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        return getLargeIcon(portComponent, isJ2EE14, getLang);        
        //break;
      default:
        return null;      
    }
  }

  private static String getDescription(PortComponent portComponent, boolean isJ2EE14, boolean lang)
  {
  	String ret = null;
    if (isJ2EE14)
    {
      DescriptionType descType = portComponent.getDescriptionType();
      if (descType!=null)
      {
        if (lang)
        {
        	ret =  descType.getLang();
        }
        else
        {
        	ret =  descType.getValue();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if( !lang )
      {
      	ret =  portComponent.getDescription();
      }
    }
    return ret;
  }

  private static String getDisplayName(PortComponent portComponent, boolean isJ2EE14, boolean lang)
  {
  	String ret = null;
    if (isJ2EE14)
    {
      DisplayNameType dnType = portComponent.getDisplayNameType();
      if (dnType!=null)
      {
        if (lang)
        {
        	ret =  dnType.getLang();
        }
        else
        {
        	ret =  dnType.getValue();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if( !lang )
      {
      	ret =  portComponent.getDisplayName();
      }
    }
    return ret;
  }

  private static String getSmallIcon(PortComponent portComponent, boolean isJ2EE14, boolean lang)
  {
  	String ret = null;
    if (isJ2EE14)
    {
      IconType iconType = portComponent.getIconType();
      if (iconType!=null)
      {
        if (lang)
        {
        	ret = iconType.getLang();
        }
        else
        {
        	ret = iconType.getSmallIcon();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if( !lang )
      {
      	ret = portComponent.getSmallIcon();
      }
    }
    return ret;
  }

  private static String getLargeIcon(PortComponent portComponent, boolean isJ2EE14, boolean lang)
  {
    String ret = null;
    if (isJ2EE14)
    {
      IconType iconType = portComponent.getIconType();
      if (iconType!=null)
      {
        if (lang)
        {
        	ret = iconType.getLang();
        }
        else
        {
        	ret = iconType.getLargeIcon();
        }
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if ( !lang )
      {
      	ret = portComponent.getLargeIcon();
      }
    }
    return ret;
  }

  public static String[] getValues(Handler handler, DescriptionGroupItem item)
  {
    return getValues(handler, item, false);
  }
  
  public static String[] getValues(Handler handler, DescriptionGroupItem item, boolean getLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)handler.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        return getDescriptions(handler, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        return getDisplayNames(handler, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        return getSmallIcons(handler, isJ2EE14, getLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        return getLargeIcons(handler, isJ2EE14, getLang);        
        //break;
      default:
        return null;      
    }
  }

  private static String[] getDescriptions(Handler handler, boolean isJ2EE14, boolean lang)
  {

  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] descriptions = handler.getDescriptions().toArray();
      values = new String[descriptions.length];      
      if (lang)
      {
        for(int i =0; i<descriptions.length; i++)
        {
          values[i] = ((Description)descriptions[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<descriptions.length; i++)
        {
          values[i] = ((Description)descriptions[i]).getValue();
        }
      }


      
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = handler.getDescription();
      }

    }
    return values;
  }

  private static String[] getDisplayNames(Handler handler, boolean isJ2EE14, boolean lang)
  {
  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] displayNames = handler.getDisplayNames().toArray();
      values = new String[displayNames.length];      
      if (lang)
      {
        for(int i =0; i<displayNames.length; i++)
        {
          values[i] = ((DisplayName)displayNames[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<displayNames.length; i++)
        {
          values[i] = ((DisplayName)displayNames[i]).getValue();
        }
      }
      
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = handler.getDisplayName();
      }

    }
    return values;
  }

  private static String[] getSmallIcons(Handler handler, boolean isJ2EE14, boolean lang)
  {
  	String[] values = null;
    if (isJ2EE14)
    {
      Object[] icons = handler.getIcons().toArray();
      values = new String[icons.length];      
      if (lang)
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getSmallIcon();
        }
      }
      
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = handler.getSmallIcon();
      }

    }
    return values;
  }

  private static String[] getLargeIcons(Handler handler, boolean isJ2EE14, boolean lang)
  {
  	String[] values =  null;
    if (isJ2EE14)
    {
      Object[] icons = handler.getIcons().toArray();
      values = new String[icons.length];      
      if (lang)
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getLang();
        }
      }
      else
      {
        for(int i =0; i<icons.length; i++)
        {
          values[i] = ((IconType)icons[i]).getLargeIcon();
        }
      }
      
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      values = new String[1];
      if (lang)
      {
        //return null since language cannot be specified in J2EE 1.3.
        values[0] = null;
      }
      else
      {
        values[0] = handler.getLargeIcon();
      }

    }
    return values;
  }

  //Setter for WebServices
  public static void setValue(WebServices webServices, DescriptionGroupItem item, String value)
  {
    setValue(webServices, item, value, false);
  }
  
  public static void setValue(WebServices webServices, DescriptionGroupItem item, String value, boolean setLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)webServices.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        setDescription(webServices, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        setDisplayName(webServices, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        setSmallIcon(webServices, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        setLargeIcon(webServices, isJ2EE14, value, setLang);        
        //break;        
      default://do nothing ;
    }
  }

  private static void setDescription(WebServices webServices, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Description object
      Object[] descriptions = webServices.getDescriptions().toArray();
      Description firstDescription = null;
      if (descriptions.length < 1)
      {
        //create a Description
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        Description newDescription = factory.createDescription();
        if (setLang)
        {
          newDescription.setLang(value);
        }
        else
        {
          newDescription.setValue(value);
        }
        webServices.getDescriptions().add(newDescription);
      }
      else
      {
        firstDescription = (Description)descriptions[0];
        if (setLang)
        {
          firstDescription.setLang(value);
        }
        else
        {
          firstDescription.setValue(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServices.setDescription(value);
    }
  }

  private static void setDisplayName(WebServices webServices, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the DisplayName object
      Object[] displayNames = webServices.getDisplayNames().toArray();
      DisplayName firstDisplayName = null;
      if (displayNames.length < 1)
      {
        //create a DisplayName
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        DisplayName newDisplayName = factory.createDisplayName();
        if (setLang)
        {
          newDisplayName.setLang(value);
        }
        else
        {
          newDisplayName.setValue(value);
        }

        webServices.getDisplayNames().add(newDisplayName);
      }
      else
      {
        firstDisplayName = (DisplayName)displayNames[0];
        if (setLang)
        {
          firstDisplayName.setLang(value);
        }
        else
        {
          firstDisplayName.setValue(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServices.setDisplayName(value);
    }
    
  }

  private static void setSmallIcon(WebServices webServices, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      Object[] icons = webServices.getIcons().toArray();
      IconType firstIcon = null;
      if (icons.length < 1)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType newIcon = factory.createIconType();
        if (setLang)
        {
          newIcon.setLang(value);
        }
        else
        {
          newIcon.setSmallIcon(value);
        }
        webServices.getIcons().add(newIcon);
      }
      else
      {
        firstIcon = (IconType)icons[0];
        if (setLang)
        {
          firstIcon.setLang(value);
        }
        else
        {
          firstIcon.setSmallIcon(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServices.setSmallIcon(value);
    }
    
  }

  private static void setLargeIcon(WebServices webServices, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      Object[] icons = webServices.getIcons().toArray();
      IconType firstIcon = null;
      if (icons.length < 1)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType newIcon = factory.createIconType();
        if (setLang)
        {
          newIcon.setLang(value);
        }
        else
        {
          newIcon.setLargeIcon(value);
        }
        webServices.getIcons().add(newIcon);
      }
      else
      {
        firstIcon = (IconType)icons[0];
        if (setLang)
        {
          firstIcon.setLang(value);
        }
        else
        {
          firstIcon.setLargeIcon(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServices.setLargeIcon(value);
    }
    
  }


//Setters for WebServiceDescriptions
  public static void setValue(WebServiceDescription webServiceDescription, DescriptionGroupItem item, String value)
  {
    setValue(webServiceDescription, item, value, false);
  }
  
  public static void setValue(WebServiceDescription webServiceDescription, DescriptionGroupItem item, String value, boolean setLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)webServiceDescription.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        setDescription(webServiceDescription, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        setDisplayName(webServiceDescription, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        setSmallIcon(webServiceDescription, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        setLargeIcon(webServiceDescription, isJ2EE14, value, setLang);        
        //break;        
      default:
    }
  }

  private static void setDescription(WebServiceDescription webServiceDescription, boolean isJ2EE14, String value, boolean setLang)
  {
    
    if (isJ2EE14)
    {
      //Get or create the Description object
      
      DescriptionType description = webServiceDescription.getDescriptionType();
      if (description == null)
      {
        //create a DescriptionType
        WscommonFactory factory = WscommonPackage.eINSTANCE.getWscommonFactory();
        DescriptionType descriptionNew = factory.createDescriptionType();

        if (setLang)
        {
          descriptionNew.setLang(value);
        }
        else
        {

          descriptionNew.setValue(value);
        }
        
        webServiceDescription.setDescriptionType(descriptionNew);
      }
      else
      {
        if (setLang)
        {
          description.setLang(value);
        }
        else
        {

          description.setValue(value);
        }
        
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServiceDescription.setDescription(value);
    }
  }

  private static void setDisplayName(WebServiceDescription webServiceDescription, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the DisplayName object
      
      DisplayNameType displayName = webServiceDescription.getDisplayNameType();
      if (displayName == null)
      {
        //create a DisplayNameType
        WscommonFactory factory = WscommonPackage.eINSTANCE.getWscommonFactory();
        DisplayNameType displayNameNew = factory.createDisplayNameType();
        if (setLang)
        {
          displayNameNew.setLang(value);
        }
        else
        {
          displayNameNew.setValue(value);
        }
        webServiceDescription.setDisplayNameType(displayNameNew);
      }
      else
      {
        if (setLang)
        {
          displayName.setLang(value);
        }
        else
        {
          displayName.setValue(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServiceDescription.setDisplayName(value);
    }
    
  }

  private static void setSmallIcon(WebServiceDescription webServiceDescription, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      IconType icon = webServiceDescription.getIconType();
      if (icon == null)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType iconNew = factory.createIconType();
        if (setLang)
        {
          iconNew.setLang(value);
        }
        else
        {
          iconNew.setSmallIcon(value);
        }

        webServiceDescription.setIconType(iconNew);
      }
      else
      {
        if (setLang)
        {
          icon.setLang(value);
        }
        else
        {
          icon.setSmallIcon(value);
        }
      }
        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServiceDescription.setSmallIcon(value);
    }
    
  }

  private static void setLargeIcon(WebServiceDescription webServiceDescription, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      IconType icon = webServiceDescription.getIconType();
      if (icon == null)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType iconNew = factory.createIconType();
        if (setLang)
        {
          iconNew.setLang(value);
        }
        else
        {
          iconNew.setLargeIcon(value);
        }

        webServiceDescription.setIconType(iconNew);
      }
      else
      {
        if (setLang)
        {
          icon.setLang(value);
        }
        else
        {
          icon.setLargeIcon(value);
        }
      }
        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        webServiceDescription.setLargeIcon(value);
    }
    
  }


//Setters for PortComponents
  public static void setValue(PortComponent portComponent, DescriptionGroupItem item, String value)
  {

    setValue(portComponent, item, value, false);
  }
  
  public static void setValue(PortComponent portComponent, DescriptionGroupItem item, String value, boolean setLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)portComponent.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        setDescription(portComponent, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        setDisplayName(portComponent, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        setSmallIcon(portComponent, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        setLargeIcon(portComponent, isJ2EE14, value, setLang);        
        //break;        
      default: 
    }
  }

  private static void setDescription(PortComponent portComponent, boolean isJ2EE14, String value, boolean setLang)
  {
    
    if (isJ2EE14)
    {
      //Get or create the Description object
      
      DescriptionType description = portComponent.getDescriptionType();
      if (description == null)
      {
        //create a DescriptionType
        WscommonFactory factory = WscommonPackage.eINSTANCE.getWscommonFactory();
        DescriptionType descriptionNew = factory.createDescriptionType();
        if (setLang)
        {
          descriptionNew.setLang(value);
        }
        else
        {
          descriptionNew.setValue(value);
        }
        
        portComponent.setDescriptionType(descriptionNew);
      }
      else
      {
        if (setLang)
        {
          description.setLang(value);
        }
        else
        {
          description.setValue(value);
        }
        
      }
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        portComponent.setDescription(value);
    }
  }

  private static void setDisplayName(PortComponent portComponent, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the DisplayName object
      
      DisplayNameType displayName = portComponent.getDisplayNameType();
      if (displayName == null)
      {
        //create a DisplayNameType
        WscommonFactory factory = WscommonPackage.eINSTANCE.getWscommonFactory();
        DisplayNameType displayNameNew = factory.createDisplayNameType();
        if (setLang)
        {
          displayNameNew.setLang(value);
        }
        else
        {
          displayNameNew.setValue(value);
        }
        portComponent.setDisplayNameType(displayNameNew);
      }
      else
      {
        if (setLang)
        {
          displayName.setLang(value);
        }
        else
        {
          displayName.setValue(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        portComponent.setDisplayName(value);
    }
    
  }

  private static void setSmallIcon(PortComponent portComponent, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      IconType icon = portComponent.getIconType();
      if (icon == null)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType iconNew = factory.createIconType();
        if (setLang)
        {
          iconNew.setLang(value);
        }
        else
        {
          iconNew.setSmallIcon(value);
        }

        portComponent.setIconType(iconNew);
      }
      else
      {
        if (setLang)
        {
          icon.setLang(value);
        }
        else
        {
          icon.setSmallIcon(value);
        }
      }
        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        portComponent.setSmallIcon(value);
    }
    
  }

  private static void setLargeIcon(PortComponent portComponent, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      IconType icon = portComponent.getIconType();
      if (icon == null)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType iconNew = factory.createIconType();
        if (setLang)
        {
          iconNew.setLang(value);
        }
        else
        {
          iconNew.setLargeIcon(value);
        }

        portComponent.setIconType(iconNew);
      }
      else
      {
        if (setLang)
        {
          icon.setLang(value);
        }
        else
        {
          icon.setLargeIcon(value);
        }
      }
        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        portComponent.setLargeIcon(value);
    }
    
  }

  //Setter for Handlers
  public static void setValue(Handler handler, DescriptionGroupItem item, String value)
  {
    setValue(handler, item, value, false);
  }
  
  public static void setValue(Handler handler, DescriptionGroupItem item, String value, boolean setLang)
  {
  	J2EEVersionResource res = (J2EEVersionResource)handler.eResource();
    boolean isJ2EE14=false;
    if (res.getJ2EEVersionID()==J2EEVersionConstants.J2EE_1_4_ID)
    {
      isJ2EE14 = true;
    }
    
    switch (item.getValue())
    {
      case DescriptionGroupItem.DESCRIPTION:
        setDescription(handler, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.DISPLAY_NAME:
        setDisplayName(handler, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.SMALL_ICON:
        setSmallIcon(handler, isJ2EE14, value, setLang);
        //break;
      case DescriptionGroupItem.LARGE_ICON:
        setLargeIcon(handler, isJ2EE14, value, setLang);        
        //break;        
      default://do nothing ;
    }
  }

  private static void setDescription(Handler handler, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Description object
      Object[] descriptions = handler.getDescriptions().toArray();
      Description firstDescription = null;
      if (descriptions.length < 1)
      {
        //create a Description
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        Description newDescription = factory.createDescription();
        if (setLang)
        {
          newDescription.setLang(value);
        }
        else
        {
          newDescription.setValue(value);
        }
        handler.getDescriptions().add(newDescription);
      }
      else
      {
        firstDescription = (Description)descriptions[0];
        if (setLang)
        {
          firstDescription.setLang(value);
        }
        else
        {
          firstDescription.setValue(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        handler.setDescription(value);
    }
  }

  private static void setDisplayName(Handler handler, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the DisplayName object
      Object[] displayNames = handler.getDisplayNames().toArray();
      DisplayName firstDisplayName = null;
      if (displayNames.length < 1)
      {
        //create a DisplayName
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        DisplayName newDisplayName = factory.createDisplayName();
        if (setLang)
        {
          newDisplayName.setLang(value);
        }
        else
        {
          newDisplayName.setValue(value);
        }

        handler.getDisplayNames().add(newDisplayName);
      }
      else
      {
        firstDisplayName = (DisplayName)displayNames[0];
        if (setLang)
        {
          firstDisplayName.setLang(value);
        }
        else
        {
          firstDisplayName.setValue(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        handler.setDisplayName(value);
    }
    
  }

  private static void setSmallIcon(Handler handler, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      Object[] icons = handler.getIcons().toArray();
      IconType firstIcon = null;
      if (icons.length < 1)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType newIcon = factory.createIconType();
        if (setLang)
        {
          newIcon.setLang(value);
        }
        else
        {
          newIcon.setSmallIcon(value);
        }
        handler.getIcons().add(newIcon);
      }
      else
      {
        firstIcon = (IconType)icons[0];
        if (setLang)
        {
          firstIcon.setLang(value);
        }
        else
        {
          firstIcon.setSmallIcon(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        handler.setSmallIcon(value);
    }
    
  }

  private static void setLargeIcon(Handler handler, boolean isJ2EE14, String value, boolean setLang)
  {
    if (isJ2EE14)
    {
      //Get or create the Icon object
      Object[] icons = handler.getIcons().toArray();
      IconType firstIcon = null;
      if (icons.length < 1)
      {
        //create an Icon
        CommonFactory factory = CommonPackage.eINSTANCE.getCommonFactory();
        IconType newIcon = factory.createIconType();
        if (setLang)
        {
          newIcon.setLang(value);
        }
        else
        {
          newIcon.setLargeIcon(value);
        }
        handler.getIcons().add(newIcon);
      }
      else
      {
        firstIcon = (IconType)icons[0];
        if (setLang)
        {
          firstIcon.setLang(value);
        }
        else
        {
          firstIcon.setLargeIcon(value);
        }
        
      }

        
    }
    else //must be J2EE 1.3 (JSR-109 1.0)
    {
      if (!setLang)
        handler.setLargeIcon(value);
    }
    
  }
  
}
