/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * This class has been generated from a javajet template. 
 *******************************************************************************/

package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class ListenerTemplate
{
  protected static String nl;
  public static synchronized ListenerTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ListenerTemplate result = new ListenerTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl; //$NON-NLS-1$
  protected final String TEXT_1 = "package "; //$NON-NLS-1$
  protected final String TEXT_2 = ";"; //$NON-NLS-1$
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import "; //$NON-NLS-1$
  protected final String TEXT_5 = ";"; //$NON-NLS-1$
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = "/**" + NL + " * Application Lifecycle Listener implementation class "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_9 = NL + " *"; //$NON-NLS-1$
  protected final String TEXT_10 = NL + " * @web.listener"; //$NON-NLS-1$
  protected final String TEXT_11 = NL + " */"; //$NON-NLS-1$
  protected final String TEXT_12 = NL + "@WebListener"; //$NON-NLS-1$
  protected final String TEXT_13 = NL + "public "; //$NON-NLS-1$
  protected final String TEXT_14 = "abstract "; //$NON-NLS-1$
  protected final String TEXT_15 = "final "; //$NON-NLS-1$
  protected final String TEXT_16 = "class "; //$NON-NLS-1$
  protected final String TEXT_17 = " extends "; //$NON-NLS-1$
  protected final String TEXT_18 = " implements "; //$NON-NLS-1$
  protected final String TEXT_19 = ", "; //$NON-NLS-1$
  protected final String TEXT_20 = " {"; //$NON-NLS-1$
  protected final String TEXT_21 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  protected final String TEXT_22 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_23 = NL + "       " + NL + "    /**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_24 = "#"; //$NON-NLS-1$
  protected final String TEXT_25 = "("; //$NON-NLS-1$
  protected final String TEXT_26 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_27 = "("; //$NON-NLS-1$
  protected final String TEXT_28 = ") {" + NL + "        super("; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_29 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_30 = NL + NL + "\t/**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_31 = "#"; //$NON-NLS-1$
  protected final String TEXT_32 = "("; //$NON-NLS-1$
  protected final String TEXT_33 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_34 = " "; //$NON-NLS-1$
  protected final String TEXT_35 = "("; //$NON-NLS-1$
  protected final String TEXT_36 = ") "; //$NON-NLS-1$
  protected final String TEXT_37 = "throws "; //$NON-NLS-1$
  protected final String TEXT_38 = " { "; //$NON-NLS-1$
  protected final String TEXT_39 = " { "; //$NON-NLS-1$
  protected final String TEXT_40 = NL + "         // TODO Auto-generated method stub"; //$NON-NLS-1$
  protected final String TEXT_41 = NL + "\t\t\treturn "; //$NON-NLS-1$
  protected final String TEXT_42 = ";"; //$NON-NLS-1$
  protected final String TEXT_43 = NL + "    }"; //$NON-NLS-1$
  protected final String TEXT_44 = NL + "\t" + NL + "}"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_45 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateListenerTemplateModel model = (CreateListenerTemplateModel) argument; 
    
	model.removeFlags(CreateJavaEEArtifactTemplateModel.FLAG_QUALIFIED_SUPERCLASS_NAME); 

    
	if (model.getJavaPackageName() != null && model.getJavaPackageName().length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append( model.getJavaPackageName() );
    stringBuffer.append(TEXT_2);
    
	}

    stringBuffer.append(TEXT_3);
     
	Collection<String> imports = model.getImports();
	for (String anImport : imports) { 

    stringBuffer.append(TEXT_4);
    stringBuffer.append( anImport );
    stringBuffer.append(TEXT_5);
     
	}

    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_9);
     
	if (model.isAnnotated()) { 

    stringBuffer.append(TEXT_10);
     
	}

    stringBuffer.append(TEXT_11);
     
	if ("3.0".equals(model.getJavaEEVersion()) || "3.1".equals(model.getJavaEEVersion()) || "4.0".equals(model.getJavaEEVersion())) {  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    stringBuffer.append(TEXT_12);
    
}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_13);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_14);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_15);
    
	}

    stringBuffer.append(TEXT_16);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_18);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_19);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_20);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_21);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_22);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_23);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_25);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_27);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_28);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_29);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_30);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_34);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_35);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_36);
      if (method.getExceptions().length() > 0){ 
    stringBuffer.append(TEXT_37);
    stringBuffer.append(method.getExceptions());
    stringBuffer.append(TEXT_38);
    }else {
    stringBuffer.append(TEXT_39);
     } 
    stringBuffer.append(TEXT_40);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_41);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_42);
    
			} 

    stringBuffer.append(TEXT_43);
     
		}
	} 

    stringBuffer.append(TEXT_44);
    stringBuffer.append(TEXT_45);
    return stringBuffer.toString();
  }
}
