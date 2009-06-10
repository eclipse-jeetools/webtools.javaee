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
  protected final String TEXT_12 = NL + "public "; //$NON-NLS-1$
  protected final String TEXT_13 = "abstract "; //$NON-NLS-1$
  protected final String TEXT_14 = "final "; //$NON-NLS-1$
  protected final String TEXT_15 = "class "; //$NON-NLS-1$
  protected final String TEXT_16 = " extends "; //$NON-NLS-1$
  protected final String TEXT_17 = " implements "; //$NON-NLS-1$
  protected final String TEXT_18 = ", "; //$NON-NLS-1$
  protected final String TEXT_19 = " {"; //$NON-NLS-1$
  protected final String TEXT_20 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  protected final String TEXT_21 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_22 = NL + "       " + NL + "    /**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_23 = "#"; //$NON-NLS-1$
  protected final String TEXT_24 = "("; //$NON-NLS-1$
  protected final String TEXT_25 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_26 = "("; //$NON-NLS-1$
  protected final String TEXT_27 = ") {" + NL + "        super("; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_28 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_29 = NL + NL + "\t/**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_30 = "#"; //$NON-NLS-1$
  protected final String TEXT_31 = "("; //$NON-NLS-1$
  protected final String TEXT_32 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_33 = " "; //$NON-NLS-1$
  protected final String TEXT_34 = "("; //$NON-NLS-1$
  protected final String TEXT_35 = ") {" + NL + "        // TODO Auto-generated method stub"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_36 = NL + "\t\t\treturn "; //$NON-NLS-1$
  protected final String TEXT_37 = ";"; //$NON-NLS-1$
  protected final String TEXT_38 = NL + "    }"; //$NON-NLS-1$
  protected final String TEXT_39 = NL + "\t" + NL + "}"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_40 = NL;

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
    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_12);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_13);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_14);
    
	}

    stringBuffer.append(TEXT_15);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_16);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_17);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_18);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_19);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_20);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_21);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_22);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_23);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_25);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_27);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_28);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_29);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_30);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_32);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_34);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_35);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_36);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_37);
    
			} 

    stringBuffer.append(TEXT_38);
     
		}
	} 

    stringBuffer.append(TEXT_39);
    stringBuffer.append(TEXT_40);
    return stringBuffer.toString();
  }
}
