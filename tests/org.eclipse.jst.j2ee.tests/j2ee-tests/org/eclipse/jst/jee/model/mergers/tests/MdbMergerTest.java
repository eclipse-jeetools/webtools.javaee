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
import org.eclipse.jst.javaee.ejb.ActivationConfig;
import org.eclipse.jst.javaee.ejb.ActivationConfigProperty;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.MethodParams;
import org.eclipse.jst.javaee.ejb.NamedMethodType;
import org.eclipse.jst.javaee.ejb.SecurityIdentityType;
import org.eclipse.jst.javaee.ejb.TransactionType;
import org.eclipse.jst.jee.model.internal.mergers.MessageDrivenBeanMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;

/**
 * Tester class for MessageDrivenBean artifact.
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
public class MdbMergerTest extends TestCase {


  /**
   * 
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testActivationConfigPropComplex() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");

    ActivationConfig base = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean.setActivationConfig(base);
    ActivationConfig merge = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean1.setActivationConfig(merge);
    ActivationConfigProperty property = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property.setActivationConfigPropertyName("n1");
    property.setActivationConfigPropertyValue("v1");
    base.getActivationConfigProperties().add(property);

    ActivationConfigProperty property1 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property1.setActivationConfigPropertyName("n2");
    property1.setActivationConfigPropertyValue("v2");
    base.getActivationConfigProperties().add(property1);

    ActivationConfigProperty property2 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property2.setActivationConfigPropertyName("n3");
    property2.setActivationConfigPropertyValue("v3");
    base.getActivationConfigProperties().add(property2);

    ActivationConfigProperty property3 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property3.setActivationConfigPropertyName("n3");
    property3.setActivationConfigPropertyValue("v1");
    merge.getActivationConfigProperties().add(property3);

    ActivationConfigProperty property4 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property4.setActivationConfigPropertyName("n4");
    property4.setActivationConfigPropertyValue("v4");
    merge.getActivationConfigProperties().add(property4);
    ActivationConfigProperty property5 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property5.setActivationConfigPropertyName("n5");
    property5.setActivationConfigPropertyValue("v6");
    merge.getActivationConfigProperties().add(property5);

    ActivationConfigProperty property6 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property6.setActivationConfigPropertyName("n6");
    property6.setActivationConfigPropertyValue("v6");
    base.getActivationConfigProperties().add(property6);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertEquals(mdbBean.getActivationConfig().getActivationConfigProperties().size(), 6);
    assertEquals(mdbBean1.getActivationConfig().getActivationConfigProperties().size(), 3);
    assertTrue(checkActivationConfProp("n1", "v1", base.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n2", "v2", base.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n3", "v3", base.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n4", "v4", base.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n5", "v6", base.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n6", "v6", base.getActivationConfigProperties()));

  }


  /**
   * 
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testActivationConfigPropComplex2() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");

    ActivationConfig base = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean.setActivationConfig(base);
    ActivationConfig merge = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean1.setActivationConfig(merge);
    ActivationConfigProperty property = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property.setActivationConfigPropertyName("n1");
    property.setActivationConfigPropertyValue("v1");
    base.getActivationConfigProperties().add(property);

    ActivationConfigProperty property1 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property1.setActivationConfigPropertyName("n2");
    property1.setActivationConfigPropertyValue("v2");
    base.getActivationConfigProperties().add(property1);

    ActivationConfigProperty property2 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property2.setActivationConfigPropertyName("n3");
    property2.setActivationConfigPropertyValue("v3");
    base.getActivationConfigProperties().add(property2);

    ActivationConfigProperty property3 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property3.setActivationConfigPropertyName("n3");
    property3.setActivationConfigPropertyValue("v1");
    merge.getActivationConfigProperties().add(property3);

    ActivationConfigProperty property4 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property4.setActivationConfigPropertyName("n4");
    property4.setActivationConfigPropertyValue("v4");
    merge.getActivationConfigProperties().add(property4);
    ActivationConfigProperty property5 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property5.setActivationConfigPropertyName("n5");
    property5.setActivationConfigPropertyValue("v6");
    merge.getActivationConfigProperties().add(property5);

    ActivationConfigProperty property6 = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property6.setActivationConfigPropertyName("n6");
    property6.setActivationConfigPropertyValue("v6");
    base.getActivationConfigProperties().add(property6);

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();

    assertEquals(mdbBean.getActivationConfig().getActivationConfigProperties().size(), 4);
    assertEquals(mdbBean1.getActivationConfig().getActivationConfigProperties().size(), 6);
    assertTrue(checkActivationConfProp("n1", "v1", merge.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n2", "v2", merge.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n3", "v1", merge.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n4", "v4", merge.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n5", "v6", merge.getActivationConfigProperties()));
    assertTrue(checkActivationConfProp("n6", "v6", merge.getActivationConfigProperties()));

  }

  private boolean checkActivationConfProp(String key, String value, List properties){
    for (Object acProp : properties) {
      ActivationConfigProperty p = (ActivationConfigProperty) acProp;
      if (p.getActivationConfigPropertyName().equals(key) && p.getActivationConfigPropertyValue().equals(value)){
        return true;
      }
    }
    return false;

  }



  /**
   * 
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testActivationConfigPropBase() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");

    ActivationConfig base = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean.setActivationConfig(base);
    ActivationConfig merge = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean1.setActivationConfig(merge);
    ActivationConfigProperty property = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property.setActivationConfigPropertyName("n1");
    property.setActivationConfigPropertyValue("v1");
    base.getActivationConfigProperties().add(property);


    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertEquals(mdbBean.getActivationConfig().getActivationConfigProperties().size(), 1);
    assertEquals(mdbBean1.getActivationConfig().getActivationConfigProperties().size(), 0);
    assertTrue(checkActivationConfProp("n1", "v1", base.getActivationConfigProperties()));

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testActivationConfigPropMerge() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");

    ActivationConfig base = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean.setActivationConfig(base);
    ActivationConfig merge = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean1.setActivationConfig(merge);
    ActivationConfigProperty property = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property.setActivationConfigPropertyName("n1");
    property.setActivationConfigPropertyValue("v1");
    merge.getActivationConfigProperties().add(property);


    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertEquals(mdbBean.getActivationConfig().getActivationConfigProperties().size(), 1);
    assertEquals(mdbBean1.getActivationConfig().getActivationConfigProperties().size(), 1);
    assertTrue(checkActivationConfProp("n1", "v1", base.getActivationConfigProperties()));

  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testActivationConfigPropSameBean() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");

    ActivationConfig base = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean.setActivationConfig(base);
    ActivationConfig merge = EjbFactory.eINSTANCE.createActivationConfig();
    mdbBean1.setActivationConfig(merge);
    ActivationConfigProperty property = EjbFactory.eINSTANCE.createActivationConfigProperty();
    property.setActivationConfigPropertyName("n1");
    property.setActivationConfigPropertyValue("v1");
    base.getActivationConfigProperties().add(property);
    merge.getActivationConfigProperties().add(property);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertEquals(mdbBean.getActivationConfig().getActivationConfigProperties().size(), 1);
    assertEquals(mdbBean1.getActivationConfig().getActivationConfigProperties().size(), 1);
    assertTrue(checkActivationConfProp("n1", "v1", base.getActivationConfigProperties()));

  }




















  //Timeout
  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutComplexNoParams() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);

    mdbBean.setTimeoutMethod(base);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertNotNull(mdbBean.getTimeoutMethod());
    assertNotNull(mdbBean.getTimeoutMethod().getMethodName().equals(value));
    assertNull(mdbBean.getTimeoutMethod().getMethodParams());


  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutComplexWithParams() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    params.getMethodParams().add("java.lang.String");
    base.setMethodParams(params);

    NamedMethodType merge = EjbFactory.eINSTANCE.createNamedMethodType();
    merge.setMethodName(value);

    mdbBean.setTimeoutMethod(base);
    mdbBean1.setTimeoutMethod(merge);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertNotNull(mdbBean.getTimeoutMethod());
    assertNotNull(mdbBean.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(mdbBean.getTimeoutMethod().getMethodParams());
    assertEquals(1, mdbBean.getTimeoutMethod().getMethodParams().getMethodParams().size());

  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutComplexWithParams2() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    String string = "java.lang.String";
    params.getMethodParams().add(string);
    base.setMethodParams(params);

    NamedMethodType merge = EjbFactory.eINSTANCE.createNamedMethodType();
    merge.setMethodName(value);

    mdbBean.setTimeoutMethod(base);
    mdbBean1.setTimeoutMethod(merge);

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();

    assertNotNull(mdbBean.getTimeoutMethod());
    assertNotNull(mdbBean.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(mdbBean.getTimeoutMethod().getMethodParams());
    assertNotNull(mdbBean.getTimeoutMethod().getMethodParams().getMethodParams().get(0).equals(string));

  }

  /**
   * 
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testTimeOutBase() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    String string = "java.lang.String";
    params.getMethodParams().add(string);
    base.setMethodParams(params);

    mdbBean.setTimeoutMethod(base);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();

    assertNotNull(mdbBean.getTimeoutMethod());
    assertNotNull(mdbBean.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(mdbBean.getTimeoutMethod().getMethodParams());
    assertNotNull(mdbBean.getTimeoutMethod().getMethodParams().getMethodParams().get(0).equals(string));

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testTimeOutMerge() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");


    NamedMethodType base = EjbFactory.eINSTANCE.createNamedMethodType();
    String value = "getMethod";
    base.setMethodName(value);
    MethodParams params = EjbFactory.eINSTANCE.createMethodParams();
    String string = "java.lang.String";
    params.getMethodParams().add(string);
    base.setMethodParams(params);

    mdbBean.setTimeoutMethod(base);

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();

    assertNotNull(mdbBean.getTimeoutMethod());
    assertNotNull(mdbBean1.getTimeoutMethod());
    assertNotNull(mdbBean1.getTimeoutMethod().getMethodName().equals(value));
    assertNotNull(mdbBean1.getTimeoutMethod().getMethodParams());
    assertNotNull(mdbBean1.getTimeoutMethod().getMethodParams().getMethodParams().get(0).equals(string));   
  }

  /**
   * 
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentityBase() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());
    mdbBean1.setSecurityIdentity(type);

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertNotNull(mdbBean1.getSecurityIdentity().getUseCallerIdentity());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentityMerge() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());
    mdbBean1.setSecurityIdentity(type);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();
    assertNotNull(mdbBean.getSecurityIdentity().getUseCallerIdentity());    
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentitySame() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());
    mdbBean1.setSecurityIdentity(type);
    mdbBean.setSecurityIdentity(type);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();
    assertNotNull(mdbBean.getSecurityIdentity().getUseCallerIdentity());

  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testSecurityIdentityComplex() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    SecurityIdentityType type = EjbFactory.eINSTANCE.createSecurityIdentityType();
    type.setUseCallerIdentity(JavaeeFactory.eINSTANCE.createEmptyType());

    SecurityIdentityType type2 = EjbFactory.eINSTANCE.createSecurityIdentityType();
    RunAs createRunAs = JavaeeFactory.eINSTANCE.createRunAs();
    createRunAs.setRoleName("ttt");
    type2.setRunAs(createRunAs);

    mdbBean1.setSecurityIdentity(type);
    mdbBean.setSecurityIdentity(type2);

    (new MessageDrivenBeanMerger(mdbBean, mdbBean1, 0)).process();
    assertNull(mdbBean.getSecurityIdentity().getUseCallerIdentity());
    assertNotNull(mdbBean.getSecurityIdentity().getRunAs().getRoleName());


  }


  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeBase() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean1.setTransactionType(TransactionType.BEAN_LITERAL);

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals(TransactionType.BEAN_LITERAL, mdbBean1.getTransactionType());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeMerge() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean.setTransactionType(TransactionType.BEAN_LITERAL);

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals(TransactionType.BEAN_LITERAL, mdbBean1.getTransactionType());    
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeSame() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean1.setTransactionType(TransactionType.BEAN_LITERAL);
    mdbBean.setTransactionType(TransactionType.BEAN_LITERAL);
    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals(TransactionType.BEAN_LITERAL, mdbBean1.getTransactionType());     
  }

  /**
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testTransactionTypeComplex() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean1.setTransactionType(TransactionType.CONTAINER_LITERAL);
    mdbBean.setTransactionType(TransactionType.BEAN_LITERAL);
    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals(TransactionType.CONTAINER_LITERAL, mdbBean1.getTransactionType());

  }


  /**
   * 
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws ModelException
   */
  //@Test
  public void testMessagingTypeBase() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean1.setMessagingType("test");

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals("test", mdbBean1.getMessagingType());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws ModelException
   */
  //@Test
  public void testMessagingTypeMerge() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean.setMessagingType("test");

    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals("test", mdbBean1.getMessagingType());    
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws ModelException
   */
  //@Test
  public void testMessagingTypeSame() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean1.setMessagingType("test");
    mdbBean.setMessagingType("test");
    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals("test", mdbBean1.getMessagingType());     
  }

  /**
   * 
   * Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws ModelException
   */
  //@Test
  public void testMessagingTypeComplex() throws ModelException{
    MessageDrivenBean mdbBean = EjbFactory.eINSTANCE.createMessageDrivenBean();
    MessageDrivenBean mdbBean1 = EjbFactory.eINSTANCE.createMessageDrivenBean();
    mdbBean.setEjbName("name");
    mdbBean1.setEjbName("name");
    mdbBean1.setMessagingType("zero");
    mdbBean.setMessagingType("test");
    (new MessageDrivenBeanMerger(mdbBean1, mdbBean, 0)).process();
    assertEquals("zero", mdbBean1.getMessagingType());

  }
}
