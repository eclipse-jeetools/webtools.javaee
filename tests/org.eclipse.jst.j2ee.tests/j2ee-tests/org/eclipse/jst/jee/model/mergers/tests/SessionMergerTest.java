/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.model.mergers.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRoleRef;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.InitMethodType;
import org.eclipse.jst.javaee.ejb.MethodParams;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.RemoveMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.javaee.ejb.TransactionType;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;
import org.eclipse.jst.jee.model.internal.mergers.SessionBeanMerger;

/**
 * Tester class for SessionBean artifact.
 * 
 * Base suffix means that the base object has some info and 
 * toMerge is empty: nothing should be merged
 * 
 * ToMerge suffix means that the base is empty object and 
 * toMerge has some info: all from merge should be present in base.
 * 
 * Same suffix means that the information in merge and base is one 
 * and the same: no merge should occurred and additional checks for 
 * doubling of the elements are present.
 * 
 * Complex suffix means variety of information is present in base 
 * and to merge: consistent information combined by base and toMerge
 * should be available at the end.
 * 
 * ComplexOverlapped suffix means variety of information is present 
 * in base and to merge: consistent information combined by base and toMerge
 * should be available at the end. There are artifacts with one and the same 
 * name and different values: values should be merged into base.
 *  
 * 
 * @author Dimitar Giormov
 *
 */
public class SessionMergerTest extends TestCase{

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutComplexNoParams() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);

    baseBean1.setTimeoutMethod(base);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();

    assertNotNull(baseBean1.getTimeoutMethod());
    assertNotNull(baseBean1.getTimeoutMethod().getMethodName().equals(value));
    assertNull(baseBean1.getTimeoutMethod().getMethodParams());


  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutComplexWithParams() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    params.getMethodParams().add("java.lang.String");
    base.setMethodParams(params);

    NamedMethodType merge = EjbFactory.eINSTANCE.createNamedMethodType();
    merge.setMethodName(value);

    baseBean1.setTimeoutMethod(base);
    toMergeBean1.setTimeoutMethod(merge);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();

    assertNotNull(baseBean1.getTimeoutMethod());
    assertNotNull(baseBean1.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(baseBean1.getTimeoutMethod().getMethodParams());
    assertEquals(1, baseBean1.getTimeoutMethod().getMethodParams().getMethodParams().size());

  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutComplexWithParams2() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    String string = "java.lang.String";
    params.getMethodParams().add(string);
    base.setMethodParams(params);

    NamedMethodType merge = EjbFactory.eINSTANCE.createNamedMethodType();
    merge.setMethodName(value);

    baseBean1.setTimeoutMethod(base);
    toMergeBean1.setTimeoutMethod(merge);

    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();

    assertNotNull(baseBean1.getTimeoutMethod());
    assertNotNull(baseBean1.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(baseBean1.getTimeoutMethod().getMethodParams());
    assertNotNull(baseBean1.getTimeoutMethod().getMethodParams().getMethodParams().get(0).equals(string));

  }

  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testTimeOutBase() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    String string = "java.lang.String";
    params.getMethodParams().add(string);
    base.setMethodParams(params);

    baseBean1.setTimeoutMethod(base);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();

    assertNotNull(baseBean1.getTimeoutMethod());
    assertNotNull(baseBean1.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(baseBean1.getTimeoutMethod().getMethodParams());
    assertNotNull(baseBean1.getTimeoutMethod().getMethodParams().getMethodParams().get(0).equals(string));

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutMerge() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    String string = "java.lang.String";
    params.getMethodParams().add(string);
    base.setMethodParams(params);

    baseBean1.setTimeoutMethod(base);

    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();

    assertNotNull(baseBean1.getTimeoutMethod());
    assertNotNull(toMergeBean1.getTimeoutMethod());
    assertNotNull(toMergeBean1.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(toMergeBean1.getTimeoutMethod().getMethodParams());
    assertNotNull(toMergeBean1.getTimeoutMethod().getMethodParams().getMethodParams().get(0).equals(string));   
  }

  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentityBase() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());
    toMergeBean1.setSecurityIdentities(type);

    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();
    assertNotNull(toMergeBean1.getSecurityIdentities().getUseCallerIdentity());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentityMerge() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());
    toMergeBean1.setSecurityIdentities(type);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();
    assertNotNull(baseBean1.getSecurityIdentities().getUseCallerIdentity());    
  }

  /**
   * 
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentitySame() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());
    toMergeBean1.setSecurityIdentities(type);
    baseBean1.setSecurityIdentities(type);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();
    assertNotNull(baseBean1.getSecurityIdentities().getUseCallerIdentity());

  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentityComplex() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());

    SecurityIdentityType type2 = EjbFactory.eINSTANCE.createSecurityIdentityType();
    RunAs createRunAs = JavaeeFactory.eINSTANCE.createRunAs();
    createRunAs.setRoleName("ttt");
    type2.setRunAs(createRunAs);

    toMergeBean1.setSecurityIdentities(type);
    baseBean1.setSecurityIdentities(type2);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();
    assertNull(baseBean1.getSecurityIdentities().getUseCallerIdentity());
    assertNotNull(baseBean1.getSecurityIdentities().getRunAs().getRoleName());


  }


  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeBase() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    toMergeBean1.setTransactionType(TransactionType.BEAN_LITERAL);

    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();
    assertEquals(TransactionType.BEAN_LITERAL, toMergeBean1.getTransactionType());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeMerge() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    baseBean1.setTransactionType(TransactionType.BEAN_LITERAL);

    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();
    assertEquals(TransactionType.BEAN_LITERAL, toMergeBean1.getTransactionType());    
  }

  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeSame() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    toMergeBean1.setTransactionType(TransactionType.BEAN_LITERAL);
    baseBean1.setTransactionType(TransactionType.BEAN_LITERAL);
    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();
    assertEquals(TransactionType.BEAN_LITERAL, toMergeBean1.getTransactionType());     
  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeComplex() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");
    toMergeBean1.setTransactionType(TransactionType.CONTAINER_LITERAL);
    baseBean1.setTransactionType(TransactionType.BEAN_LITERAL);
    (new SessionBeanMerger(toMergeBean1, baseBean1, 0)).process();
    assertEquals(TransactionType.CONTAINER_LITERAL, toMergeBean1.getTransactionType());

  }


  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testInitMethodBase() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");




    String value = "getMethod";


    String mParam1 = "java.lang.String";

    InitMethodType methodType = generateInitType(value, new String[]{mParam1});

    baseBean1.getInitMethods().add(methodType);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();

    assertNotNull(baseBean1.getInitMethods());
    assertEquals(1, baseBean1.getInitMethods().size());

    InitMethodType aMethod = findInitMethod(baseBean1.getInitMethods(), methodType.getBeanMethod());

    assertNotNull(aMethod);
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testInitMethodToMerge() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    String value = "getMethod";    
    String mParam1 = "java.lang.String";
    InitMethodType methodType = generateInitType(value, new String[]{mParam1});

    baseBean1.getInitMethods().add(methodType);

    (new SessionBeanMerger(toMergeBean1, baseBean1,  0)).process();

    assertNotNull(toMergeBean1.getInitMethods());
    assertEquals(1, toMergeBean1.getInitMethods().size());

    InitMethodType aMethod = findInitMethod(toMergeBean1.getInitMethods(), methodType.getBeanMethod());
    assertNotNull(aMethod);

  }

  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testInitMethodSame() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    String value = "getMethod";    
    String mParam1 = "java.lang.String";
    InitMethodType methodType = generateInitType(value, new String[]{mParam1});

    baseBean1.getInitMethods().add(methodType);
    toMergeBean1.getInitMethods().add(methodType);

    (new SessionBeanMerger(toMergeBean1, baseBean1,  0)).process();

    assertNotNull(toMergeBean1.getInitMethods());
    assertEquals(1, toMergeBean1.getInitMethods().size());

    InitMethodType aMethod = findInitMethod(toMergeBean1.getInitMethods(), methodType.getBeanMethod());
    assertNotNull(aMethod);

  }


  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testInitMethodComplex() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String value = "getMethod";
    String mParam1 = "java.lang.String";
    InitMethodType methodType = generateInitType(value, new String[]{mParam1});
    InitMethodType methodType2 = generateInitType(value, new String[]{mParam1, mParam1});
    InitMethodType methodType3 = generateInitType(value, new String[]{mParam1});

    baseBean1.getInitMethods().add(methodType);
    toMergeBean1.getInitMethods().add(methodType2);
    toMergeBean1.getInitMethods().add(methodType3);

    (new SessionBeanMerger(toMergeBean1, baseBean1,  0)).process();

    assertNotNull(toMergeBean1.getInitMethods());
    assertEquals(2, toMergeBean1.getInitMethods().size());

    InitMethodType aMethod = findInitMethod(toMergeBean1.getInitMethods(), methodType.getBeanMethod());
    assertNotNull(aMethod);
    InitMethodType aMethod2 = findInitMethod(toMergeBean1.getInitMethods(), methodType2.getBeanMethod());
    assertNotNull(aMethod2);


  }

  private InitMethodType findInitMethod(List namedMethodsBase, NamedMethodType object) {
    for (Object base: namedMethodsBase) {
      InitMethodType tmpBase = (InitMethodType)base;
      if ( tmpBase.getBeanMethod().getMethodName().equals(object.getMethodName()) 
          && sameParams(tmpBase.getBeanMethod().getMethodParams(), object.getMethodParams())) {
        return (InitMethodType) base;
      }
    }
    return null;
  }


  private boolean sameParams(MethodParams methodParams,
      MethodParams methodParams2) {
    if(methodParams == null && methodParams2 == null){
      return true;
    }
    if(methodParams == null | methodParams2 == null){
      return false;
    }

    if (methodParams.getMethodParams().size() != methodParams2.getMethodParams().size()){
      return false;
    }

    for (int i = 0; i < methodParams.getMethodParams().size(); i++) {
      if(!methodParams.getMethodParams().get(i).equals(methodParams2.getMethodParams().get(i))){
        return false;
      }
    }
    return true;
  }

  private InitMethodType generateInitType(String name, String[] parameters) {
    InitMethodType methodType3 = EjbFactory.eINSTANCE.createInitMethodType();
    NamedMethodType nMethodType3 = EjbFactory.eINSTANCE.createNamedMethodType();

    nMethodType3.setMethodName(name);
    MethodParams params3 = EjbFactory.eINSTANCE.createMethodParams();
    if (parameters != null){
      for (int i = 0; i < parameters.length; i++) {
        params3.getMethodParams().add(parameters[i]);  
      }
      nMethodType3.setMethodParams(params3);
    }

    methodType3.setBeanMethod(nMethodType3);    
    return methodType3;
  }


  private RemoveMethodType generateRemoveMethodType(String name, String[] parameters) {
    RemoveMethodType methodType3 = EjbFactory.eINSTANCE.createRemoveMethodType();
    NamedMethodType nMethodType3 = EjbFactory.eINSTANCE.createNamedMethodType();

    nMethodType3.setMethodName(name);
    MethodParams params3 = EjbFactory.eINSTANCE.createMethodParams();
    if (parameters != null){
      for (int i = 0; i < parameters.length; i++) {
        params3.getMethodParams().add(parameters[i]);  
      }
      nMethodType3.setMethodParams(params3);
    }

    methodType3.setBeanMethod(nMethodType3);    
    return methodType3;
  }


  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testRemoveMethodBase() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String value = "getMethod";

    String mParam1 = "java.lang.String";

    RemoveMethodType methodType = generateRemoveMethodType(value, new String[]{mParam1});

    baseBean1.getRemoveMethods().add(methodType);

    (new SessionBeanMerger(baseBean1, toMergeBean1, 0)).process();

    assertNotNull(baseBean1.getRemoveMethods());
    assertEquals(1, baseBean1.getRemoveMethods().size());

    RemoveMethodType aMethod = findRemoveMethod(baseBean1.getRemoveMethods(), methodType.getBeanMethod());

    assertNotNull(aMethod);
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testRemoveMethodToMerge() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    String value = "getMethod";    
    String mParam1 = "java.lang.String";
    RemoveMethodType methodType = generateRemoveMethodType(value, new String[]{mParam1});

    baseBean1.getRemoveMethods().add(methodType);

    (new SessionBeanMerger(toMergeBean1, baseBean1,  0)).process();

    assertNotNull(toMergeBean1.getRemoveMethods());
    assertEquals(1, toMergeBean1.getRemoveMethods().size());

    RemoveMethodType aMethod = findRemoveMethod(toMergeBean1.getRemoveMethods(), methodType.getBeanMethod());
    assertNotNull(aMethod);

  }

  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testRemoveMethodSame() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");


    String value = "getMethod";    
    String mParam1 = "java.lang.String";
    RemoveMethodType methodType = generateRemoveMethodType(value, new String[]{mParam1});

    baseBean1.getRemoveMethods().add(methodType);
    toMergeBean1.getRemoveMethods().add(methodType);

    (new SessionBeanMerger(toMergeBean1, baseBean1,  0)).process();

    assertNotNull(toMergeBean1.getRemoveMethods());
    assertEquals(1, toMergeBean1.getRemoveMethods().size());

    RemoveMethodType aMethod = findRemoveMethod(toMergeBean1.getRemoveMethods(), methodType.getBeanMethod());
    assertNotNull(aMethod);

  }


  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testRemoveMethodComplex() throws ModelException{
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String value = "getMethod";
    String mParam1 = "java.lang.String";
    RemoveMethodType methodType = generateRemoveMethodType(value, new String[]{mParam1});
    RemoveMethodType methodType2 = generateRemoveMethodType(value, new String[]{mParam1, mParam1});
    RemoveMethodType methodType3 = generateRemoveMethodType(value, new String[]{mParam1});

    baseBean1.getRemoveMethods().add(methodType);
    toMergeBean1.getRemoveMethods().add(methodType2);
    toMergeBean1.getRemoveMethods().add(methodType3);

    (new SessionBeanMerger(toMergeBean1, baseBean1,  0)).process();

    assertNotNull(toMergeBean1.getRemoveMethods());
    assertEquals(2, toMergeBean1.getRemoveMethods().size());

    RemoveMethodType aMethod = findRemoveMethod(toMergeBean1.getRemoveMethods(), methodType.getBeanMethod());
    assertNotNull(aMethod);
    RemoveMethodType aMethod2 = findRemoveMethod(toMergeBean1.getRemoveMethods(), methodType2.getBeanMethod());
    assertNotNull(aMethod2);


  }

  private RemoveMethodType findRemoveMethod(List namedMethodsBase, NamedMethodType object) {
    for (Object base: namedMethodsBase) {
      RemoveMethodType tmpBase = (RemoveMethodType)base;
      if ( tmpBase.getBeanMethod().getMethodName().equals(object.getMethodName()) 
          && sameParams(tmpBase.getBeanMethod().getMethodParams(), object.getMethodParams())) {
        return (RemoveMethodType) base;
      }
    }
    return null;
  }


  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws Exception
   */
  //@Test
  public void testSecurityRoleRefsBase() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    SecurityRoleRef ref = JavaeeFactory.eINSTANCE.createSecurityRoleRef();
    String name0 = "roleName1";
    ref.setRoleName(name0);
    String link0 = "roleLink1";
    ref.setRoleLink(link0);

    baseBean1.getSecurityRoleRefs().add(ref);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertNotNull(baseBean1.getSecurityRoleRefs());
    assertEquals(1, baseBean1.getSecurityRoleRefs().size());
    assertNotNull(getSecurityRoleRefByName(baseBean1.getSecurityRoleRefs(), name0));
  }

  private SecurityRoleRef getSecurityRoleRefByName(List securityRoleRefs, String name0) {
    for (Object object : securityRoleRefs) {
      SecurityRoleRef sr = (SecurityRoleRef) object;
      if (sr.getRoleName().equals(name0)){
        return sr;
      }
    }
    return null;
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testSecurityRoleRefsMerge() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    SecurityRoleRef ref = JavaeeFactory.eINSTANCE.createSecurityRoleRef();
    String name0 = "roleName1";
    ref.setRoleName(name0);
    String link0 = "roleLink1";
    ref.setRoleLink(link0);

    toMergeBean1.getSecurityRoleRefs().add(ref);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(1, baseBean1.getSecurityRoleRefs().size());
    assertNotNull(getSecurityRoleRefByName(baseBean1.getSecurityRoleRefs(), name0));
  }

  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * 
   * @throws Exception
   */
  //@Test
  public void testSecurityRoleRefsSame() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    SecurityRoleRef ref = JavaeeFactory.eINSTANCE.createSecurityRoleRef();
    String name0 = "roleName1";
    ref.setRoleName(name0);
    String link0 = "roleLink1";
    ref.setRoleLink(link0);

    baseBean1.getSecurityRoleRefs().add(ref);
    toMergeBean1.getSecurityRoleRefs().add(ref);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(1, baseBean1.getSecurityRoleRefs().size());
    assertNotNull(getSecurityRoleRefByName(baseBean1.getSecurityRoleRefs(), name0));
  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testSecurityRoleRefsComplex() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    SecurityRoleRef ref = JavaeeFactory.eINSTANCE.createSecurityRoleRef();
    String name0 = "roleName1";
    ref.setRoleName(name0);
    String link0 = "roleLink1";
    ref.setRoleLink(link0);

    SecurityRoleRef ref2 = JavaeeFactory.eINSTANCE.createSecurityRoleRef();
    String name1 = "roleName2";
    ref2.setRoleName(name1);
    ref2.setRoleLink(link0);

    baseBean1.getSecurityRoleRefs().add(ref);
    toMergeBean1.getSecurityRoleRefs().add(ref2);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(2, baseBean1.getSecurityRoleRefs().size());
    assertNotNull(getSecurityRoleRefByName(baseBean1.getSecurityRoleRefs(), name0));

    assertNotNull(getSecurityRoleRefByName(baseBean1.getSecurityRoleRefs(), name1));
  }

  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws Exception
   */
  //@Test
  public void testBeanClassBase() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String expected = "test.Test";
    baseBean1.setEjbClass(expected);
    String notExpected = "test.Test2";
    toMergeBean1.setEjbClass(notExpected);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();
    assertEquals(expected,baseBean1.getEjbClass());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testBeanClassMerge() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String expected = "test.Test";
    toMergeBean1.setEjbClass(expected);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();
    assertEquals(expected,baseBean1.getEjbClass());

  }

  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testBeanClassSame() throws Exception {
    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String expected = "test.Test";
    baseBean1.setEjbClass(expected);
    toMergeBean1.setEjbClass(expected);

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();
    assertEquals(expected,baseBean1.getEjbClass());
  }

  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws Exception
   */
  //@Test
  public void testBussinessInterfacesBase() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    baseBean1.getBusinessLocals().add("intfs1");
    baseBean1.getBusinessRemotes().add("intfs2");

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(1, baseBean1.getBusinessLocals().size());
    assertEquals(1, baseBean1.getBusinessRemotes().size());
    assertEquals("intfs1", baseBean1.getBusinessLocals().get(0));
    assertEquals("intfs2", baseBean1.getBusinessRemotes().get(0));
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testBussinessInterfacesMerge() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    toMergeBean1.getBusinessLocals().add("intfs1");
    toMergeBean1.getBusinessRemotes().add("intfs2");

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(1, baseBean1.getBusinessLocals().size());
    assertEquals(1, baseBean1.getBusinessRemotes().size());
    assertEquals("intfs1", baseBean1.getBusinessLocals().get(0));
    assertEquals("intfs2", baseBean1.getBusinessRemotes().get(0));
  }


  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testBussinessInterfacesSame() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    toMergeBean1.getBusinessLocals().add("intfs1");
    toMergeBean1.getBusinessRemotes().add("intfs2");

    baseBean1.getBusinessLocals().add("intfs1");
    baseBean1.getBusinessRemotes().add("intfs2");

    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(1, baseBean1.getBusinessLocals().size());
    assertEquals(1, baseBean1.getBusinessRemotes().size());
    assertEquals("intfs1", baseBean1.getBusinessLocals().get(0));
    assertEquals("intfs2", baseBean1.getBusinessRemotes().get(0));
  }


  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testBussinessInterfacesComplex() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    toMergeBean1.getBusinessLocals().add("intfs1");
    toMergeBean1.getBusinessRemotes().add("intfs2");
    toMergeBean1.getBusinessLocals().add("intfs3");
    toMergeBean1.getBusinessRemotes().add("intfs4");
    toMergeBean1.getBusinessLocals().add("intfs5");
    toMergeBean1.getBusinessRemotes().add("intfs6");

    baseBean1.getBusinessLocals().add("intfs1");
    baseBean1.getBusinessRemotes().add("intfs9");
    baseBean1.getBusinessLocals().add("intfs0");
    baseBean1.getBusinessRemotes().add("intfs2");


    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(4, baseBean1.getBusinessLocals().size());
    assertEquals(4, baseBean1.getBusinessRemotes().size());
    assertTrue(baseBean1.getBusinessLocals().contains("intfs1"));
    assertTrue(baseBean1.getBusinessLocals().contains("intfs3"));
    assertTrue(baseBean1.getBusinessLocals().contains("intfs5"));
    assertTrue(baseBean1.getBusinessLocals().contains("intfs0"));

    assertTrue(baseBean1.getBusinessRemotes().contains("intfs2"));
    assertTrue(baseBean1.getBusinessRemotes().contains("intfs4"));
    assertTrue(baseBean1.getBusinessRemotes().contains("intfs6"));
    assertTrue(baseBean1.getBusinessRemotes().contains("intfs9"));

  }


  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws Exception
   */
  //@Test
  public void test2xInterfacesBase() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String l = "local1";
    baseBean1.setLocal(l);
    String lh = "localhome1";
    baseBean1.setLocalHome(lh);
    String r = "remote1";
    baseBean1.setRemote(r);
    String h = "home1";
    baseBean1.setHome(h);


    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(l, baseBean1.getLocal());   
    assertEquals(lh, baseBean1.getLocalHome());
    assertEquals(r, baseBean1.getRemote());
    assertEquals(h, baseBean1.getHome());
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void test2xInterfacesMerge() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String l = "local1";
    toMergeBean1.setLocal(l);
    String lh = "localhome1";
    toMergeBean1.setLocalHome(lh);
    String r = "remote1";
    toMergeBean1.setRemote(r);
    String h = "home1";
    toMergeBean1.setHome(h);


    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(l, baseBean1.getLocal());   
    assertEquals(lh, baseBean1.getLocalHome());
    assertEquals(r, baseBean1.getRemote());
    assertEquals(h, baseBean1.getHome());
  }

  /**
   * Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void test2xInterfacesSame() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String l = "local1";
    baseBean1.setLocal(l);
    toMergeBean1.setLocal(l);
    String lh = "localhome1";
    baseBean1.setLocalHome(lh);
    toMergeBean1.setLocalHome(lh);
    String r = "remote1";
    baseBean1.setRemote(r);
    toMergeBean1.setRemote(r);
    String h = "home1";
    baseBean1.setHome(h);
    toMergeBean1.setHome(h);


    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(l, baseBean1.getLocal());   
    assertEquals(lh, baseBean1.getLocalHome());
    assertEquals(r, baseBean1.getRemote());
    assertEquals(h, baseBean1.getHome());
  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void test2xInterfacesComplex() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String l = "local1";
    baseBean1.setLocal(l);

    String lh = "localhome1";

    toMergeBean1.setLocalHome(lh);
    String r = "remote1";
    baseBean1.setRemote(r);

    String h = "home1";

    toMergeBean1.setHome(h);


    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(l, baseBean1.getLocal());   
    assertEquals(lh, baseBean1.getLocalHome());
    assertEquals(r, baseBean1.getRemote());
    assertEquals(h, baseBean1.getHome());
  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void test2xInterfacesComplexInversed() throws Exception {

    SessionBean baseBean1 = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean toMergeBean1 = EjbFactory.eINSTANCE.createSessionBean();
    baseBean1.setEjbName("name");
    toMergeBean1.setEjbName("name");

    String l = "local1";
    toMergeBean1.setLocal(l);

    String lh = "localhome1";

    baseBean1.setLocalHome(lh);
    String r = "remote1";
    toMergeBean1.setRemote(r);

    String h = "home1";

    baseBean1.setHome(h);


    (new SessionBeanMerger(baseBean1, toMergeBean1,  0)).process();

    assertEquals(l, baseBean1.getLocal());   
    assertEquals(lh, baseBean1.getLocalHome());
    assertEquals(r, baseBean1.getRemote());
    assertEquals(h, baseBean1.getHome());
  }

}
