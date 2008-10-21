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

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "package ";
  protected final String TEXT_2 = ";";
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "import ";
  protected final String TEXT_5 = ";";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL;
  protected final String TEXT_8 = "/**" + NL + " * Message-Driven Bean implementation class for: ";
  protected final String TEXT_9 = NL + " *" + NL + " */" + NL + "@MessageDriven";
  protected final String TEXT_10 = "(";
  protected final String TEXT_11 = ", ";
  protected final String TEXT_12 = NL + "\t\t";
  protected final String TEXT_13 = NL + "\t\tactivationConfig = { @ActivationConfigProperty(" + NL + "\t\t\t\tpropertyName = \"destinationType\", propertyValue = \"";
  protected final String TEXT_14 = "\"" + NL + "\t\t) }";
  protected final String TEXT_15 = " = ";
  protected final String TEXT_16 = ")";
  protected final String TEXT_17 = NL + "@TransactionManagement(TransactionManagementType.BEAN)";
  protected final String TEXT_18 = NL + "public ";
  protected final String TEXT_19 = "abstract ";
  protected final String TEXT_20 = "final ";
  protected final String TEXT_21 = "class ";
  protected final String TEXT_22 = " extends ";
  protected final String TEXT_23 = " implements ";
  protected final String TEXT_24 = ", ";
  protected final String TEXT_25 = " {";
  protected final String TEXT_26 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public ";
  protected final String TEXT_27 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }";
  protected final String TEXT_28 = NL + "       " + NL + "    /**" + NL + "     * @see ";
  protected final String TEXT_29 = "#";
  protected final String TEXT_30 = "(";
  protected final String TEXT_31 = ")" + NL + "     */" + NL + "    public ";
  protected final String TEXT_32 = "(";
  protected final String TEXT_33 = ") {" + NL + "        super(";
  protected final String TEXT_34 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }";
  protected final String TEXT_35 = NL + NL + "\t/**" + NL + "     * @see ";
  protected final String TEXT_36 = "#";
  protected final String TEXT_37 = "(";
  protected final String TEXT_38 = ")" + NL + "     */" + NL + "    public ";
  protected final String TEXT_39 = " ";
  protected final String TEXT_40 = "(";
  protected final String TEXT_41 = ") {" + NL + "        // TODO Auto-generated method stub";
  protected final String TEXT_42 = NL + "\t\t\treturn ";
  protected final String TEXT_43 = ";";
  protected final String TEXT_44 = NL + "    }";
  protected final String TEXT_45 = NL + "\t" + NL + "\t/**" + NL + "     * @see MessageListener#onMessage(Message)" + NL + "     */" + NL + "    public void onMessage(Message message) {" + NL + "        // TODO Auto-generated method stub" + NL + "        " + NL + "    }";
  protected final String TEXT_46 = NL + NL + "}";
  protected final String TEXT_47 = NL;

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
    
	Map<String, String> params = model.getClassAnnotationParams();
	if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_10);
    
		Set<String> keys = params.keySet();
		boolean needNewLine = keys.contains(CreateMessageDrivenBeanTemplateModel.ATT_ACTIVATION_CONFIG);
		boolean needComma = false;
		for (String key : keys) {
			if (needComma) { 

    stringBuffer.append(TEXT_11);
    
				if (needNewLine) {

    stringBuffer.append(TEXT_12);
    
				} 
			}
			
			String value = params.get(key);
			if (key.equals(CreateMessageDrivenBeanTemplateModel.ATT_ACTIVATION_CONFIG)) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_14);
    
			} else {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_15);
    stringBuffer.append( value );
    
			}
			needComma = true;
		} 

    stringBuffer.append(TEXT_16);
    
	}
	
	if (!model.isContainerType()) {

    stringBuffer.append(TEXT_17);
    
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_18);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_19);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_20);
    
	}

    stringBuffer.append(TEXT_21);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_22);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_23);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = (String) interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_24);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_25);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_26);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_27);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_28);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_30);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_34);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_35);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_36);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_37);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_38);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_39);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_40);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_41);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_42);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_43);
    
			} 

    stringBuffer.append(TEXT_44);
     
		}
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		Collection<Method> unimplementedMethods = model.getUnimplementedMethods();
		if (model.isJMS()) { 

    stringBuffer.append(TEXT_45);
    
		}
	}

    stringBuffer.append(TEXT_46);
    stringBuffer.append(TEXT_47);
    return stringBuffer.toString();
  }
}
