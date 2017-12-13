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

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class MessageDrivenBeanTemplate
{
  protected static String nl;
  public static synchronized MessageDrivenBeanTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    MessageDrivenBeanTemplate result = new MessageDrivenBeanTemplate();
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
  protected final String TEXT_8 = "/**" + NL + " * Message-Driven Bean implementation class for: "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_9 = NL + " */" + NL + "@MessageDriven"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_10 = "("; //$NON-NLS-1$
  protected final String TEXT_11 = NL + "\t\tactivationConfig = { "; //$NON-NLS-1$
  protected final String TEXT_12 = ", "; //$NON-NLS-1$
  protected final String TEXT_13 = "@ActivationConfigProperty(" + NL + "\t\t\t\tpropertyName = \""; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_14 = "\", propertyValue = \""; //$NON-NLS-1$
  protected final String TEXT_15 = "\")"; //$NON-NLS-1$
  protected final String TEXT_16 = NL + "\t\t}"; //$NON-NLS-1$
  protected final String TEXT_17 = ", "; //$NON-NLS-1$
  protected final String TEXT_18 = NL + "\t\t"; //$NON-NLS-1$
  protected final String TEXT_19 = " = "; //$NON-NLS-1$
  protected final String TEXT_20 = ")"; //$NON-NLS-1$
  protected final String TEXT_21 = NL + "@TransactionManagement(TransactionManagementType.BEAN)"; //$NON-NLS-1$
  protected final String TEXT_22 = NL + "public "; //$NON-NLS-1$
  protected final String TEXT_23 = "abstract "; //$NON-NLS-1$
  protected final String TEXT_24 = "final "; //$NON-NLS-1$
  protected final String TEXT_25 = "class "; //$NON-NLS-1$
  protected final String TEXT_26 = " extends "; //$NON-NLS-1$
  protected final String TEXT_27 = " implements "; //$NON-NLS-1$
  protected final String TEXT_28 = ", "; //$NON-NLS-1$
  protected final String TEXT_29 = " {"; //$NON-NLS-1$
  protected final String TEXT_30 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  protected final String TEXT_31 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_32 = NL + "       " + NL + "    /**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_33 = "#"; //$NON-NLS-1$
  protected final String TEXT_34 = "("; //$NON-NLS-1$
  protected final String TEXT_35 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_36 = "("; //$NON-NLS-1$
  protected final String TEXT_37 = ") {" + NL + "        super("; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_38 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_39 = NL + NL + "\t/**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_40 = "#"; //$NON-NLS-1$
  protected final String TEXT_41 = "("; //$NON-NLS-1$
  protected final String TEXT_42 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_43 = " "; //$NON-NLS-1$
  protected final String TEXT_44 = "("; //$NON-NLS-1$
  protected final String TEXT_45 = ") {" + NL + "        // TODO Auto-generated method stub"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_46 = NL + "\t\t\treturn "; //$NON-NLS-1$
  protected final String TEXT_47 = ";"; //$NON-NLS-1$
  protected final String TEXT_48 = NL + "    }"; //$NON-NLS-1$
  protected final String TEXT_49 = NL + "\t" + NL + "\t/**" + NL + "     * @see MessageListener#onMessage(Message)" + NL + "     */" + NL + "    public void onMessage(Message message) {" + NL + "        // TODO Auto-generated method stub" + NL + "        " + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
  protected final String TEXT_50 = NL + NL + "}"; //$NON-NLS-1$
  protected final String TEXT_51 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateMessageDrivenBeanTemplateModel model = (CreateMessageDrivenBeanTemplateModel) argument; 
    
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
    stringBuffer.append(model.getClassName());
    stringBuffer.append(TEXT_9);
    
	Map<String, Object> params = model.getClassAnnotationParams();
	if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_10);
    
		boolean needComma = false;
		boolean needNewLine = false;
		Map<String, String> activationConfigProperties = (Map<String, String>) params.get(CreateMessageDrivenBeanTemplateModel.ATT_ACTIVATION_CONFIG);
		if (activationConfigProperties.size() > 0) {

    stringBuffer.append(TEXT_11);
    
			Set<String> props = activationConfigProperties.keySet();
			for (String prop : props) {
				if (needComma) {

    stringBuffer.append(TEXT_12);
    
				}
				
				String value = activationConfigProperties.get(prop);

    stringBuffer.append(TEXT_13);
    stringBuffer.append( prop );
    stringBuffer.append(TEXT_14);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_15);
    				
				needComma = true;
			}

    stringBuffer.append(TEXT_16);
    
			needNewLine = true;
		}
		
		Set<String> keys = params.keySet();
		for (String key : keys) {
			Object obj = params.get(key);
			if (obj instanceof String) {
				if (needComma) { 

    stringBuffer.append(TEXT_17);
    
					if (needNewLine) {

    stringBuffer.append(TEXT_18);
    
					}
				}
				String value = (String) obj;

    stringBuffer.append( key );
    stringBuffer.append(TEXT_19);
    stringBuffer.append( value );
    
			}
			needComma = true;
		} 

    stringBuffer.append(TEXT_20);
    
	}
	
	if (!model.isContainerType()) {

    stringBuffer.append(TEXT_21);
    
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_22);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_23);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_24);
    
	}

    stringBuffer.append(TEXT_25);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_27);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_28);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_29);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_30);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_31);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_32);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_34);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_35);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_36);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_37);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_38);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_39);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_40);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_41);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_42);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_43);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_44);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_45);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_46);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_47);
    
			} 

    stringBuffer.append(TEXT_48);
     
		}
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		if (model.isJMS()) { 

    stringBuffer.append(TEXT_49);
    
		}
	}

    stringBuffer.append(TEXT_50);
    stringBuffer.append(TEXT_51);
    return stringBuffer.toString();
  }
}
