/*******************************************************************************
 * Copyright (c) 2007, 2011 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This class has been generated from a javajet template. 
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

public class LocalHomeInterfaceTemplate
{
  protected static String nl;
  public static synchronized LocalHomeInterfaceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    LocalHomeInterfaceTemplate result = new LocalHomeInterfaceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl; //$NON-NLS-1$
  protected final String TEXT_1 = "package "; //$NON-NLS-1$
  protected final String TEXT_2 = ";"; //$NON-NLS-1$
  protected final String TEXT_3 = NL + "" + NL + "import javax.ejb.CreateException;" + NL + "import javax.ejb.EJBLocalHome;"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_4 = NL + "import "; //$NON-NLS-1$
  protected final String TEXT_5 = "."; //$NON-NLS-1$
  protected final String TEXT_6 = ";"; //$NON-NLS-1$
  protected final String TEXT_7 = NL + NL + "public interface "; //$NON-NLS-1$
  protected final String TEXT_8 = " extends EJBLocalHome {" + NL + "" + NL + "\tpublic "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_9 = " create() throws CreateException;" + NL + "\t" + NL + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_10 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateSessionBeanTemplateModel model = (CreateSessionBeanTemplateModel) argument; 
    
	if (model.getLocalHomePackage() != null && model.getLocalHomePackage().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(model.getLocalHomePackage());
    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
     if(!model.getLocalComponentPackage().equals(model.getLocalHomePackage())){
    stringBuffer.append(TEXT_4);
    stringBuffer.append( model.getLocalComponentPackage());
    stringBuffer.append(TEXT_5);
    stringBuffer.append( model.getLocalComponentClassSimpleName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    stringBuffer.append(model.getLocalHomeClassSimpleName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append( model.getLocalComponentClassSimpleName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
