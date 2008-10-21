package org.eclipse.jst.j2ee.ejb.internal.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class SessionBeanTemplate
{
  protected static String nl;
  public static synchronized SessionBeanTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    SessionBeanTemplate result = new SessionBeanTemplate();
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
  protected final String TEXT_8 = "/**" + NL + " * Session Bean implementation class ";
  protected final String TEXT_9 = NL + " */";
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = " = \"";
  protected final String TEXT_14 = "\"";
  protected final String TEXT_15 = ")";
  protected final String TEXT_16 = NL + "@TransactionManagement(TransactionManagementType.BEAN)";
  protected final String TEXT_17 = NL + "@Local( { ";
  protected final String TEXT_18 = ", ";
  protected final String TEXT_19 = ".class";
  protected final String TEXT_20 = " })";
  protected final String TEXT_21 = NL + "@Remote( { ";
  protected final String TEXT_22 = ", ";
  protected final String TEXT_23 = ".class";
  protected final String TEXT_24 = " })";
  protected final String TEXT_25 = NL + "@LocalHome(";
  protected final String TEXT_26 = ".class)";
  protected final String TEXT_27 = NL + "@RemoteHome(";
  protected final String TEXT_28 = ".class)";
  protected final String TEXT_29 = NL + "public ";
  protected final String TEXT_30 = "abstract ";
  protected final String TEXT_31 = "final ";
  protected final String TEXT_32 = "class ";
  protected final String TEXT_33 = " extends ";
  protected final String TEXT_34 = " implements ";
  protected final String TEXT_35 = ", ";
  protected final String TEXT_36 = " {";
  protected final String TEXT_37 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public ";
  protected final String TEXT_38 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }";
  protected final String TEXT_39 = NL + "       " + NL + "    /**" + NL + "     * @see ";
  protected final String TEXT_40 = "#";
  protected final String TEXT_41 = "(";
  protected final String TEXT_42 = ")" + NL + "     */" + NL + "    public ";
  protected final String TEXT_43 = "(";
  protected final String TEXT_44 = ") {" + NL + "        super(";
  protected final String TEXT_45 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }";
  protected final String TEXT_46 = NL + NL + "\t/**" + NL + "     * @see ";
  protected final String TEXT_47 = "#";
  protected final String TEXT_48 = "(";
  protected final String TEXT_49 = ")" + NL + "     */" + NL + "    public ";
  protected final String TEXT_50 = " ";
  protected final String TEXT_51 = "(";
  protected final String TEXT_52 = ") {" + NL + "        // TODO Auto-generated method stub";
  protected final String TEXT_53 = NL + "\t\t\treturn ";
  protected final String TEXT_54 = ";";
  protected final String TEXT_55 = NL + "    }";
  protected final String TEXT_56 = NL + NL + "}";
  protected final String TEXT_57 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateSessionBeanTemplateModel model = (CreateSessionBeanTemplateModel) argument; 
    
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
    stringBuffer.append(TEXT_10);
    stringBuffer.append( model.getClassAnnotation() );
    
	Map<String, String> params = model.getClassAnnotationParams();
	if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_11);
    
		Set<String> keys = params.keySet();
		boolean needComma = false;
		for (String key : keys) {
			if (needComma) {

    stringBuffer.append(TEXT_12);
    
			}
			
			String value = params.get(key);

    stringBuffer.append( key );
    stringBuffer.append(TEXT_13);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_14);
    
			needComma = true;
  		}

    stringBuffer.append(TEXT_15);
    
	}

	if (!model.isContainerType()) {

    stringBuffer.append(TEXT_16);
    
	}

	List<BusinessInterface> localInterfaces = model.getExistingLocalBusinessInterfaces();
	if (!localInterfaces.isEmpty()) {

    stringBuffer.append(TEXT_17);
    
		boolean needComma = false;
		for (BusinessInterface iface : localInterfaces) {
			if (needComma) {

    stringBuffer.append(TEXT_18);
    
			}

    stringBuffer.append( iface.getSimpleName() );
    stringBuffer.append(TEXT_19);
    
			needComma = true;
 		}

    stringBuffer.append(TEXT_20);
    
	}

	List<BusinessInterface> remoteInterfaces = model.getExistingRemoteBusinessInterfaces();
	if (!remoteInterfaces.isEmpty()) {

    stringBuffer.append(TEXT_21);
    
		boolean needComma = false;
		for (BusinessInterface iface : remoteInterfaces) {
			if (needComma) {

    stringBuffer.append(TEXT_22);
    
			}

    stringBuffer.append( iface.getSimpleName() );
    stringBuffer.append(TEXT_23);
    
			needComma = true;
		}

    stringBuffer.append(TEXT_24);
    
	}
	
	if (model.isLocalHomeChecked()) {

    stringBuffer.append(TEXT_25);
    stringBuffer.append(model.getLocalHomeClassSimpleName());
    stringBuffer.append(TEXT_26);
    
	}

	if (model.isRemoteHomeChecked()) {

    stringBuffer.append(TEXT_27);
    stringBuffer.append(model.getRemoteHomeClassSimpleName());
    stringBuffer.append(TEXT_28);
    
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_29);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_30);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_31);
    
	}

    stringBuffer.append(TEXT_32);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_33);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_34);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = (String) interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_35);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_36);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_37);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_38);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_39);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_40);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_41);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_42);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_43);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_44);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_45);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_46);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_47);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_48);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_50);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_51);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_52);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_53);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_54);
    
			} 

    stringBuffer.append(TEXT_55);
     
		}
	} 

    stringBuffer.append(TEXT_56);
    stringBuffer.append(TEXT_57);
    return stringBuffer.toString();
  }
}
