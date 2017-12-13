/*******************************************************************************
 * Copyright (c) 2007, 2012 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This class has been generated from a javajet template. 
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.operations;

public class RemoteBusinessInterfaceTemplate
{
  protected static String nl;
  public static synchronized RemoteBusinessInterfaceTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    RemoteBusinessInterfaceTemplate result = new RemoteBusinessInterfaceTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl; //$NON-NLS-1$
  protected final String TEXT_1 = "package "; //$NON-NLS-1$
  protected final String TEXT_2 = ";"; //$NON-NLS-1$
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import javax.ejb.Remote;" + NL + "" + NL + "@Remote"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_5 = NL + "public interface "; //$NON-NLS-1$
  protected final String TEXT_6 = " {" + NL + "" + NL + "}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateSessionBeanTemplateModel model = (CreateSessionBeanTemplateModel) argument; 
    
	if (model.getBusinessInterfaceJavaPackageName() != null && model.getBusinessInterfaceJavaPackageName().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(model.getBusinessInterfaceJavaPackageName());
    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
    
	if (model.addBusinessAnnotationToInterface()) {

    stringBuffer.append(TEXT_4);
    
	}

    stringBuffer.append(TEXT_5);
    stringBuffer.append(model.getBusinessInterfaceClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
