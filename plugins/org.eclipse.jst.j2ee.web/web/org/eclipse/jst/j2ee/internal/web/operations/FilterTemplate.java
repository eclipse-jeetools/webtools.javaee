/*******************************************************************************
 * Copyright (c) 2007, 2014 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This class has been generated from a javajet template. 
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.*;
import org.eclipse.jst.j2ee.internal.common.operations.*;

public class FilterTemplate
{
  protected static String nl;
  public static synchronized FilterTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    FilterTemplate result = new FilterTemplate();
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
  protected final String TEXT_8 = "/**" + NL + " * Servlet Filter implementation class "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_9 = NL + " *" + NL + " * @web.filter" + NL + " *   name=\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_10 = "\"" + NL + " *   display-name=\""; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_11 = "\""; //$NON-NLS-1$
  protected final String TEXT_12 = NL + " *   description=\""; //$NON-NLS-1$
  protected final String TEXT_13 = "\""; //$NON-NLS-1$
  protected final String TEXT_14 = NL + " *" + NL + " * @web.filter-mapping"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_15 = NL + " *   url-pattern=\""; //$NON-NLS-1$
  protected final String TEXT_16 = "\""; //$NON-NLS-1$
  protected final String TEXT_17 = NL + " *   servlet-name=\""; //$NON-NLS-1$
  protected final String TEXT_18 = "\""; //$NON-NLS-1$
  protected final String TEXT_19 = NL + " *   dispatcher=\""; //$NON-NLS-1$
  protected final String TEXT_20 = "\""; //$NON-NLS-1$
  protected final String TEXT_21 = NL + " *" + NL + " * @web.filter-init-param" + NL + " *    name=\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_22 = "\"" + NL + " *    value=\""; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_23 = "\""; //$NON-NLS-1$
  protected final String TEXT_24 = NL + " *    description=\""; //$NON-NLS-1$
  protected final String TEXT_25 = "\""; //$NON-NLS-1$
  protected final String TEXT_26 = NL + " */"; //$NON-NLS-1$
  protected final String TEXT_27 = NL + "@WebFilter"; //$NON-NLS-1$
  protected final String TEXT_28 = "(\""; //$NON-NLS-1$
  protected final String TEXT_29 = "\")"; //$NON-NLS-1$
  protected final String TEXT_30 = "({ "; //$NON-NLS-1$
  protected final String TEXT_31 = ", "; //$NON-NLS-1$
  protected final String TEXT_32 = "\""; //$NON-NLS-1$
  protected final String TEXT_33 = "\""; //$NON-NLS-1$
  protected final String TEXT_34 = " })"; //$NON-NLS-1$
  protected final String TEXT_35 = "("; //$NON-NLS-1$
  protected final String TEXT_36 = ", "; //$NON-NLS-1$
  protected final String TEXT_37 = NL + "\t\t"; //$NON-NLS-1$
  protected final String TEXT_38 = " = \""; //$NON-NLS-1$
  protected final String TEXT_39 = "\""; //$NON-NLS-1$
  protected final String TEXT_40 = " = { "; //$NON-NLS-1$
  protected final String TEXT_41 = ", "; //$NON-NLS-1$
  protected final String TEXT_42 = NL + "\t\t\t\t"; //$NON-NLS-1$
  protected final String TEXT_43 = "\""; //$NON-NLS-1$
  protected final String TEXT_44 = "\""; //$NON-NLS-1$
  protected final String TEXT_45 = NL + "\t\t"; //$NON-NLS-1$
  protected final String TEXT_46 = " "; //$NON-NLS-1$
  protected final String TEXT_47 = "}"; //$NON-NLS-1$
  protected final String TEXT_48 = " = { "; //$NON-NLS-1$
  protected final String TEXT_49 = ", "; //$NON-NLS-1$
  protected final String TEXT_50 = NL + "\t\t\t\t"; //$NON-NLS-1$
  protected final String TEXT_51 = "\""; //$NON-NLS-1$
  protected final String TEXT_52 = "\""; //$NON-NLS-1$
  protected final String TEXT_53 = NL + "\t\t"; //$NON-NLS-1$
  protected final String TEXT_54 = " "; //$NON-NLS-1$
  protected final String TEXT_55 = "}"; //$NON-NLS-1$
  protected final String TEXT_56 = " = { "; //$NON-NLS-1$
  protected final String TEXT_57 = ", "; //$NON-NLS-1$
  protected final String TEXT_58 = NL + "\t\t\t\t@WebInitParam(name = \""; //$NON-NLS-1$
  protected final String TEXT_59 = "\", value = \""; //$NON-NLS-1$
  protected final String TEXT_60 = "\""; //$NON-NLS-1$
  protected final String TEXT_61 = ", description = \""; //$NON-NLS-1$
  protected final String TEXT_62 = "\""; //$NON-NLS-1$
  protected final String TEXT_63 = ")"; //$NON-NLS-1$
  protected final String TEXT_64 = NL + "\t\t}"; //$NON-NLS-1$
  protected final String TEXT_65 = " = {"; //$NON-NLS-1$
  protected final String TEXT_66 = ", "; //$NON-NLS-1$
  protected final String TEXT_67 = NL + "\t\t\t\t"; //$NON-NLS-1$
  protected final String TEXT_68 = NL + "\t\t"; //$NON-NLS-1$
  protected final String TEXT_69 = " "; //$NON-NLS-1$
  protected final String TEXT_70 = "}" + NL + "\t\t\t\t\t"; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_71 = " = "; //$NON-NLS-1$
  protected final String TEXT_72 = ")"; //$NON-NLS-1$
  protected final String TEXT_73 = NL + "public "; //$NON-NLS-1$
  protected final String TEXT_74 = "abstract "; //$NON-NLS-1$
  protected final String TEXT_75 = "final "; //$NON-NLS-1$
  protected final String TEXT_76 = "class "; //$NON-NLS-1$
  protected final String TEXT_77 = " extends "; //$NON-NLS-1$
  protected final String TEXT_78 = " implements "; //$NON-NLS-1$
  protected final String TEXT_79 = ", "; //$NON-NLS-1$
  protected final String TEXT_80 = " {"; //$NON-NLS-1$
  protected final String TEXT_81 = NL + NL + "    /**" + NL + "     * Default constructor. " + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
  protected final String TEXT_82 = "() {" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_83 = NL + "       " + NL + "    /**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_84 = "#"; //$NON-NLS-1$
  protected final String TEXT_85 = "("; //$NON-NLS-1$
  protected final String TEXT_86 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_87 = "("; //$NON-NLS-1$
  protected final String TEXT_88 = ") {" + NL + "        super("; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_89 = ");" + NL + "        // TODO Auto-generated constructor stub" + NL + "    }"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_90 = NL + NL + "\t/**" + NL + "     * @see "; //$NON-NLS-1$ //$NON-NLS-2$
  protected final String TEXT_91 = "#"; //$NON-NLS-1$
  protected final String TEXT_92 = "("; //$NON-NLS-1$
  protected final String TEXT_93 = ")" + NL + "     */" + NL + "    public "; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
  protected final String TEXT_94 = " "; //$NON-NLS-1$
  protected final String TEXT_95 = "("; //$NON-NLS-1$
  protected final String TEXT_96 = ") "; //$NON-NLS-1$
  protected final String TEXT_97 = "throws "; //$NON-NLS-1$
  protected final String TEXT_98 = " { "; //$NON-NLS-1$
  protected final String TEXT_99 = " { "; //$NON-NLS-1$
  protected final String TEXT_100 = NL + "         // TODO Auto-generated method stub"; //$NON-NLS-1$
  protected final String TEXT_101 = NL + "\t\t\treturn "; //$NON-NLS-1$
  protected final String TEXT_102 = ";"; //$NON-NLS-1$
  protected final String TEXT_103 = NL + "    }"; //$NON-NLS-1$
  protected final String TEXT_104 = NL + NL + "\t/**" + NL + "\t * @see Filter#destroy()" + NL + "\t */" + NL + "\tpublic void destroy() {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
  protected final String TEXT_105 = NL + NL + "\t/**" + NL + "\t * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)" + NL + "\t */" + NL + "\tpublic void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t\t// place your code here" + NL + "" + NL + "\t\t// pass the request along the filter chain" + NL + "\t\tchain.doFilter(request, response);" + NL + "\t}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$ //$NON-NLS-10$
  protected final String TEXT_106 = NL + NL + "\t/**" + NL + "\t * @see Filter#init(FilterConfig)" + NL + "\t */" + NL + "\tpublic void init(FilterConfig fConfig) throws ServletException {" + NL + "\t\t// TODO Auto-generated method stub" + NL + "\t}"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
  protected final String TEXT_107 = NL + NL + "}"; //$NON-NLS-1$
  protected final String TEXT_108 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     CreateFilterTemplateModel model = (CreateFilterTemplateModel) argument; 
    
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
     
	if (model.isAnnotated()) { 

    stringBuffer.append(TEXT_9);
    stringBuffer.append( model.getFilterName() );
    stringBuffer.append(TEXT_10);
    stringBuffer.append( model.getFilterName() );
    stringBuffer.append(TEXT_11);
    
		if (model.getDescription() != null && model.getDescription().length() > 0) { 

    stringBuffer.append(TEXT_12);
    stringBuffer.append( model.getDescription() );
    stringBuffer.append(TEXT_13);
     
		} 
		
		List<IFilterMappingItem> mappings = model.getFilterMappings();
 		for (IFilterMappingItem mapping : mappings) { 

    stringBuffer.append(TEXT_14);
    
			if (mapping.isUrlPatternType()) { 

    stringBuffer.append(TEXT_15);
    stringBuffer.append( mapping.getName() );
    stringBuffer.append(TEXT_16);
    
			} else if (mapping.isServletNameType()) { 

    stringBuffer.append(TEXT_17);
    stringBuffer.append( mapping.getName() );
    stringBuffer.append(TEXT_18);
    
			}
		 
			String dispatcher = model.getDispatcherList(mapping);
			if (dispatcher.length() > 0) { 

    stringBuffer.append(TEXT_19);
    stringBuffer.append( dispatcher );
    stringBuffer.append(TEXT_20);
     
			} 
		} 

		List<String[]> initParams = model.getInitParams();
		if (initParams != null && initParams.size() > 0) {
			for (int i = 0; i < initParams.size(); i++) {
				String name = model.getInitParam(i, CreateFilterTemplateModel.NAME);
				String value = model.getInitParam(i, CreateFilterTemplateModel.VALUE);
				String description = model.getInitParam(i, CreateFilterTemplateModel.DESCRIPTION); 

    stringBuffer.append(TEXT_21);
    stringBuffer.append( name );
    stringBuffer.append(TEXT_22);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_23);
    
				if (description != null && description.length() > 0) { 

    stringBuffer.append(TEXT_24);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_25);
    
				}
			} 
		} 
	}

    stringBuffer.append(TEXT_26);
     
	if ("3.0".equals(model.getJavaEEVersion()) || "3.1".equals(model.getJavaEEVersion()) || "4.0".equals(model.getJavaEEVersion())) {  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    stringBuffer.append(TEXT_27);
    
		Map<String, Object> params = model.getClassAnnotationParams();
		if (params.size() == 1 && params.containsKey(CreateServletTemplateModel.ATT_URL_PATTERNS)) {
			List<String> mappings = (List<String>) params.get(CreateServletTemplateModel.ATT_URL_PATTERNS);
			if (mappings.size() == 1) {
				String value = mappings.get(0);

    stringBuffer.append(TEXT_28);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_29);
    
			} else {

    stringBuffer.append(TEXT_30);
    
				boolean needComma = false;
				for (String mapping : mappings) {
					if (needComma) {

    stringBuffer.append(TEXT_31);
    
					}

    stringBuffer.append(TEXT_32);
    stringBuffer.append( mapping );
    stringBuffer.append(TEXT_33);
    
					needComma = true;
				}

    stringBuffer.append(TEXT_34);
    
			}
		} else if (!params.isEmpty()) { 

    stringBuffer.append(TEXT_35);
    
			Set<String> keys = params.keySet();
			boolean needNewLine = keys.contains(CreateFilterTemplateModel.ATT_INIT_PARAMS) || 
					(keys.contains(CreateFilterTemplateModel.ATT_URL_PATTERNS) && 
							((List<String>) params.get(CreateFilterTemplateModel.ATT_URL_PATTERNS)).size() > 1) || 
					(keys.contains(CreateFilterTemplateModel.ATT_SERVLET_NAMES) && 
							((List<String>) params.get(CreateFilterTemplateModel.ATT_SERVLET_NAMES)).size() > 1);
			boolean needComma = false;
			for (String key : keys) {
				if (needComma) {

    stringBuffer.append(TEXT_36);
    
				}
				
				if (needNewLine) {

    stringBuffer.append(TEXT_37);
    
				} 
			
				if (key.equals(CreateFilterTemplateModel.ATT_FILTER_NAME) || key.equals(CreateFilterTemplateModel.ATT_DESCRIPTION)) { 
					String value = (String) params.get(key);

    stringBuffer.append( key );
    stringBuffer.append(TEXT_38);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_39);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_URL_PATTERNS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_40);
    
					List<String> mappings = (List<String>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = mappings.size() > 1;
					for (String mapping : mappings) {
						if (needComma2) {

    stringBuffer.append(TEXT_41);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_42);
    
						} 

    stringBuffer.append(TEXT_43);
    stringBuffer.append( mapping );
    stringBuffer.append(TEXT_44);
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_45);
    
					} else {

    stringBuffer.append(TEXT_46);
    
					}

    stringBuffer.append(TEXT_47);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_SERVLET_NAMES)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_48);
    
					List<String> servletNames = (List<String>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = servletNames.size() > 1;
					for (String servletName : servletNames) {
						if (needComma2) {

    stringBuffer.append(TEXT_49);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_50);
    
						} 

    stringBuffer.append(TEXT_51);
    stringBuffer.append( servletName );
    stringBuffer.append(TEXT_52);
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_53);
    
					} else {

    stringBuffer.append(TEXT_54);
    
					}

    stringBuffer.append(TEXT_55);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_INIT_PARAMS)) {

    stringBuffer.append( key );
    stringBuffer.append(TEXT_56);
    
					List<String[]> initParams = (List<String[]>) params.get(key);
					boolean needComma2 = false;
					for (String[] initParam : initParams) {
						if (needComma2) {

    stringBuffer.append(TEXT_57);
    
						}
						
						String name = initParam[CreateFilterTemplateModel.NAME];
						String value = initParam[CreateFilterTemplateModel.VALUE];
						String description = initParam[CreateFilterTemplateModel.DESCRIPTION];

    stringBuffer.append(TEXT_58);
    stringBuffer.append( name );
    stringBuffer.append(TEXT_59);
    stringBuffer.append( value );
    stringBuffer.append(TEXT_60);
    				
						if (description != null && description.length() > 0) {

    stringBuffer.append(TEXT_61);
    stringBuffer.append( description );
    stringBuffer.append(TEXT_62);
    
						}

    stringBuffer.append(TEXT_63);
    
						needComma2 = true;
					}

    stringBuffer.append(TEXT_64);
    
				} else if (key.equals(CreateFilterTemplateModel.ATT_DISPATCHER_TYPES)) {
				List<String> dispatcherTypes = (List<String>) params.get(key);
					boolean needComma2 = false;
					boolean needNewLine2 = dispatcherTypes.size() > 1;
					if (dispatcherTypes.size()>0){
					
    stringBuffer.append( key );
    stringBuffer.append(TEXT_65);
     
					}
					for (String dispType : dispatcherTypes) {
						if (needComma2) {

    stringBuffer.append(TEXT_66);
    
						}
				
						if (needNewLine2) {

    stringBuffer.append(TEXT_67);
    
						} 

    stringBuffer.append( dispType );
    				
						needComma2 = true;
					}
				
					if (needNewLine2) {

    stringBuffer.append(TEXT_68);
    
					} else {

    stringBuffer.append(TEXT_69);
    
					}
					
    stringBuffer.append(TEXT_70);
    
				}
				else if (key.equals(CreateFilterTemplateModel.ATT_ASYNC_SUPPORT)) {
					Boolean value =(Boolean) params.get(key);
				    if (value){

    stringBuffer.append( key );
    stringBuffer.append(TEXT_71);
    stringBuffer.append( value );
    				    }				
				}				
				needComma = true;
  			}

    stringBuffer.append(TEXT_72);
    
		}
	}

    
	if (model.isPublic()) { 

    stringBuffer.append(TEXT_73);
     
	} 

	if (model.isAbstract()) { 

    stringBuffer.append(TEXT_74);
    
	}

	if (model.isFinal()) {

    stringBuffer.append(TEXT_75);
    
	}

    stringBuffer.append(TEXT_76);
    stringBuffer.append( model.getClassName() );
    
	String superClass = model.getSuperclassName();
 	if (superClass != null && superClass.length() > 0) {

    stringBuffer.append(TEXT_77);
    stringBuffer.append( superClass );
    
	}

	List<String> interfaces = model.getInterfaces(); 
 	if ( interfaces.size() > 0) { 

    stringBuffer.append(TEXT_78);
    
	}
	
 	for (int i = 0; i < interfaces.size(); i++) {
   		String INTERFACE = interfaces.get(i);
   		if (i > 0) {

    stringBuffer.append(TEXT_79);
    
		}

    stringBuffer.append( INTERFACE );
    
	}

    stringBuffer.append(TEXT_80);
     
	if (!model.hasEmptySuperclassConstructor()) { 

    stringBuffer.append(TEXT_81);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_82);
     
	} 

	if (model.shouldGenSuperclassConstructors()) {
		List<Constructor> constructors = model.getConstructors();
		for (Constructor constructor : constructors) {
			if (constructor.isPublic() || constructor.isProtected()) { 

    stringBuffer.append(TEXT_83);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_84);
    stringBuffer.append( model.getSuperclassName() );
    stringBuffer.append(TEXT_85);
    stringBuffer.append( constructor.getParamsForJavadoc() );
    stringBuffer.append(TEXT_86);
    stringBuffer.append( model.getClassName() );
    stringBuffer.append(TEXT_87);
    stringBuffer.append( constructor.getParamsForDeclaration() );
    stringBuffer.append(TEXT_88);
    stringBuffer.append( constructor.getParamsForCall() );
    stringBuffer.append(TEXT_89);
    
			} 
		} 
	} 

    
	if (model.shouldImplementAbstractMethods()) {
		for (Method method : model.getUnimplementedMethods()) { 

    stringBuffer.append(TEXT_90);
    stringBuffer.append( method.getContainingJavaClass() );
    stringBuffer.append(TEXT_91);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_92);
    stringBuffer.append( method.getParamsForJavadoc() );
    stringBuffer.append(TEXT_93);
    stringBuffer.append( method.getReturnType() );
    stringBuffer.append(TEXT_94);
    stringBuffer.append( method.getName() );
    stringBuffer.append(TEXT_95);
    stringBuffer.append( method.getParamsForDeclaration() );
    stringBuffer.append(TEXT_96);
      if (method.getExceptions().length() > 0){ 
    stringBuffer.append(TEXT_97);
    stringBuffer.append(method.getExceptions());
    stringBuffer.append(TEXT_98);
    }else {
    stringBuffer.append(TEXT_99);
     } 
    stringBuffer.append(TEXT_100);
     
			String defaultReturnValue = method.getDefaultReturnValue();
			if (defaultReturnValue != null) { 

    stringBuffer.append(TEXT_101);
    stringBuffer.append( defaultReturnValue );
    stringBuffer.append(TEXT_102);
    
			} 

    stringBuffer.append(TEXT_103);
     
		}
	} 

     if (model.shouldGenDestroy()) { 
    stringBuffer.append(TEXT_104);
     } 
     if (model.shouldGenDoFilter()) { 
    stringBuffer.append(TEXT_105);
     } 
     if (model.shouldGenInit()) { 
    stringBuffer.append(TEXT_106);
     } 
    stringBuffer.append(TEXT_107);
    stringBuffer.append(TEXT_108);
    return stringBuffer.toString();
  }
}
