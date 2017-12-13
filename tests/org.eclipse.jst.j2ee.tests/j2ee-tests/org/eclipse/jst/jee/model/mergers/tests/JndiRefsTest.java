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

import org.eclipse.jst.javaee.core.EjbLocalRef;
import org.eclipse.jst.javaee.core.EjbRef;
import org.eclipse.jst.javaee.core.EjbRefType;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.EnvEntryType;
import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.MessageDestinationRef;
import org.eclipse.jst.javaee.core.MessageDestinationUsageType;
import org.eclipse.jst.javaee.core.PersistenceContextRef;
import org.eclipse.jst.javaee.core.PersistenceContextType;
import org.eclipse.jst.javaee.core.PersistenceUnitRef;
import org.eclipse.jst.javaee.core.ResAuthType;
import org.eclipse.jst.javaee.core.ResSharingScopeType;
import org.eclipse.jst.javaee.core.ResourceEnvRef;
import org.eclipse.jst.javaee.core.ResourceRef;
import org.eclipse.jst.javaee.core.ServiceRef;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.model.internal.mergers.JNDIRefsMerger;

/**
 * Tester class for JNDI references artifacts.
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
public class JndiRefsTest extends TestCase{

  /**
   * Base suffix means that the base object has some info and 
   * toMerge is empty: nothing should be merged
   * @throws Exception
   */
  //@Test
  public void testResourceEnvRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceEnvRef envRefBase = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefBase.setMappedName("mapped1");
    envRefBase.setResourceEnvRefName("refName1");
    envRefBase.setResourceEnvRefType("type1");

    ResourceEnvRef envRefToMerge = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefToMerge.setMappedName("mapped2");
    envRefToMerge.setResourceEnvRefName("refName2");
    envRefToMerge.setResourceEnvRefType("type2");

    beanBase.getResourceEnvRefs().add(envRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceEnvRefs().size());
    assertEquals("mapped1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getResourceEnvRefName());
    assertEquals("type1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getResourceEnvRefType());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testResourceEnvRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceEnvRef envRefBase = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefBase.setMappedName("mapped1");
    envRefBase.setResourceEnvRefName("refName1");
    envRefBase.setResourceEnvRefType("type1");

    ResourceEnvRef envRefToMerge = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefToMerge.setMappedName("mapped2");
    envRefToMerge.setResourceEnvRefName("refName2");
    envRefToMerge.setResourceEnvRefType("type2");

    beanToMerge.getResourceEnvRefs().add(envRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceEnvRefs().size());
    assertEquals("mapped1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getResourceEnvRefName());
    assertEquals("type1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getResourceEnvRefType());

  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testResourceEnvRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceEnvRef envRefBase = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefBase.setMappedName("mapped1");
    envRefBase.setResourceEnvRefName("refName1");
    envRefBase.setResourceEnvRefType("type1");

    ResourceEnvRef envRefToMerge = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefToMerge.setMappedName("mapped2");
    envRefToMerge.setResourceEnvRefName("refName2");
    envRefToMerge.setResourceEnvRefType("type2");

    beanBase.getResourceEnvRefs().add(envRefBase);
    beanToMerge.getResourceEnvRefs().add(envRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceEnvRefs().size());
    assertEquals("mapped1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getResourceEnvRefName());
    assertEquals("type1", ((ResourceEnvRef)beanBase.getResourceEnvRefs().get(0)).getResourceEnvRefType());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testResourceEnvRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceEnvRef envRefBase = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefBase.setMappedName("mapped1");
    envRefBase.setResourceEnvRefName("refName1");
    envRefBase.setResourceEnvRefType("type1");

    ResourceEnvRef envRefToMerge = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefToMerge.setMappedName("mapped2");
    envRefToMerge.setResourceEnvRefName("refName2");
    envRefToMerge.setResourceEnvRefType("type2");

    beanBase.getResourceEnvRefs().add(envRefBase);
    beanToMerge.getResourceEnvRefs().add(envRefBase);
    beanToMerge.getResourceEnvRefs().add(envRefToMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getResourceEnvRefs().size());

    ResourceEnvRef test = getResEnvRef(beanBase.getResourceEnvRefs(), "refName1");
    ResourceEnvRef test2 = getResEnvRef(beanBase.getResourceEnvRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals("type1", test.getResourceEnvRefType());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals("type2", test2.getResourceEnvRefType());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testResourceEnvRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceEnvRef envRefBase = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefBase.setResourceEnvRefName("refName1");

    ResourceEnvRef envRefToMerge = JavaeeFactory.eINSTANCE.createResourceEnvRef();
    envRefToMerge.setMappedName("mapped2");
    envRefToMerge.setResourceEnvRefName("refName1");
    envRefToMerge.setResourceEnvRefType("type2");

    beanBase.getResourceEnvRefs().add(envRefBase);
    beanToMerge.getResourceEnvRefs().add(envRefBase);
    beanToMerge.getResourceEnvRefs().add(envRefToMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceEnvRefs().size());

    ResourceEnvRef test = getResEnvRef(beanBase.getResourceEnvRefs(), "refName1");
    assertEquals("type2", test.getResourceEnvRefType());
    assertEquals("mapped2", test.getMappedName());

  }

  private ResourceEnvRef getResEnvRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      ResourceEnvRef ref = (ResourceEnvRef) object;
      if(name.equals(ref.getResourceEnvRefName())){
        return ref; 
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
  public void testResourceRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceRef resRefBase = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefBase.setMappedName("mapped1");
    resRefBase.setResRefName("refName1");
    resRefBase.setResType("type1");
    resRefBase.setResAuth(ResAuthType.APPLICATION_LITERAL);
    resRefBase.setResSharingScope(ResSharingScopeType.SHAREABLE_LITERAL);


    ResourceRef resRefToMerge = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefToMerge.setMappedName("mapped2");
    resRefToMerge.setResRefName("refName2");
    resRefToMerge.setResType("type2");
    resRefToMerge.setResAuth(ResAuthType.CONTAINER_LITERAL);
    resRefToMerge.setResSharingScope(ResSharingScopeType.UNSHAREABLE_LITERAL);

    beanBase.getResourceRefs().add(resRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceRefs().size());
    assertEquals("mapped1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getResRefName());
    assertEquals("type1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getResType());
    assertEquals(ResAuthType.APPLICATION_LITERAL, ((ResourceRef)beanBase.getResourceRefs().get(0)).getResAuth());
    assertEquals(ResSharingScopeType.SHAREABLE_LITERAL, ((ResourceRef)beanBase.getResourceRefs().get(0)).getResSharingScope());
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testResourceRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceRef resRefBase = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefBase.setMappedName("mapped1");
    resRefBase.setResRefName("refName1");
    resRefBase.setResType("type1");
    resRefBase.setResAuth(ResAuthType.APPLICATION_LITERAL);
    resRefBase.setResSharingScope(ResSharingScopeType.SHAREABLE_LITERAL);

    ResourceRef resRefToMerge = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefToMerge.setMappedName("mapped2");
    resRefToMerge.setResRefName("refName2");
    resRefToMerge.setResType("type2");
    resRefToMerge.setResAuth(ResAuthType.CONTAINER_LITERAL);
    resRefToMerge.setResSharingScope(ResSharingScopeType.UNSHAREABLE_LITERAL);

    beanToMerge.getResourceRefs().add(resRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceRefs().size());
    assertEquals("mapped1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getResRefName());
    assertEquals("type1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getResType());
    assertEquals(ResAuthType.APPLICATION_LITERAL, ((ResourceRef)beanBase.getResourceRefs().get(0)).getResAuth());
    assertEquals(ResSharingScopeType.SHAREABLE_LITERAL, ((ResourceRef)beanBase.getResourceRefs().get(0)).getResSharingScope());
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testResourceRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceRef resRefBase = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefBase.setMappedName("mapped1");
    resRefBase.setResRefName("refName1");
    resRefBase.setResType("type1");
    resRefBase.setResAuth(ResAuthType.APPLICATION_LITERAL);
    resRefBase.setResSharingScope(ResSharingScopeType.SHAREABLE_LITERAL);


    ResourceRef resRefToMerge = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefToMerge.setMappedName("mapped2");
    resRefToMerge.setResRefName("refName2");
    resRefToMerge.setResType("type2");
    resRefToMerge.setResAuth(ResAuthType.CONTAINER_LITERAL);
    resRefToMerge.setResSharingScope(ResSharingScopeType.UNSHAREABLE_LITERAL);

    beanBase.getResourceRefs().add(resRefBase);
    beanToMerge.getResourceRefs().add(resRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceRefs().size());
    assertEquals("mapped1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getResRefName());
    assertEquals("type1", ((ResourceRef)beanBase.getResourceRefs().get(0)).getResType());
    assertEquals(ResAuthType.APPLICATION_LITERAL, ((ResourceRef)beanBase.getResourceRefs().get(0)).getResAuth());
    assertEquals(ResSharingScopeType.SHAREABLE_LITERAL, ((ResourceRef)beanBase.getResourceRefs().get(0)).getResSharingScope());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testResourceRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceRef resRefBase = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefBase.setMappedName("mapped1");
    resRefBase.setResRefName("refName1");
    resRefBase.setResType("type1");
    resRefBase.setResAuth(ResAuthType.APPLICATION_LITERAL);
    resRefBase.setResSharingScope(ResSharingScopeType.SHAREABLE_LITERAL);

    ResourceRef resRefToMerge = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefToMerge.setMappedName("mapped2");
    resRefToMerge.setResRefName("refName2");
    resRefToMerge.setResType("type2");
    resRefToMerge.setResAuth(ResAuthType.CONTAINER_LITERAL);
    resRefToMerge.setResSharingScope(ResSharingScopeType.UNSHAREABLE_LITERAL);

    beanBase.getResourceRefs().add(resRefBase);
    beanToMerge.getResourceRefs().add(resRefBase);
    beanToMerge.getResourceRefs().add(resRefToMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getResourceRefs().size());

    ResourceRef test = getResRef(beanBase.getResourceRefs(), "refName1");
    ResourceRef test2 = getResRef(beanBase.getResourceRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals("type1", test.getResType());
    assertEquals(ResAuthType.APPLICATION_LITERAL, test.getResAuth());
    assertEquals(ResSharingScopeType.SHAREABLE_LITERAL, test.getResSharingScope());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals("type2", test2.getResType());
    assertEquals(ResAuthType.CONTAINER_LITERAL, test2.getResAuth());
    assertEquals(ResSharingScopeType.UNSHAREABLE_LITERAL, test2.getResSharingScope());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testResourceRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ResourceRef envRefBase = JavaeeFactory.eINSTANCE.createResourceRef();
    envRefBase.setResRefName("refName1");

    ResourceRef resRefToMerge = JavaeeFactory.eINSTANCE.createResourceRef();
    resRefToMerge.setMappedName("mapped2");
    resRefToMerge.setResRefName("refName1");
    resRefToMerge.setResType("type2");
    resRefToMerge.setResAuth(ResAuthType.CONTAINER_LITERAL);
    resRefToMerge.setResSharingScope(ResSharingScopeType.UNSHAREABLE_LITERAL);

    beanBase.getResourceRefs().add(envRefBase);
    beanToMerge.getResourceRefs().add(envRefBase);
    beanToMerge.getResourceRefs().add(resRefToMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getResourceRefs().size());

    ResourceRef test = getResRef(beanBase.getResourceRefs(), "refName1");
    assertEquals("type2", test.getResType());
    assertEquals("mapped2", test.getMappedName());
    assertEquals(ResAuthType.CONTAINER_LITERAL, test.getResAuth());
    assertEquals(ResSharingScopeType.UNSHAREABLE_LITERAL, test.getResSharingScope());

  }

  private ResourceRef getResRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      ResourceRef ref = (ResourceRef) object;
      if(name.equals(ref.getResRefName())){
        return ref; 
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
  public void testServiceRefBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ServiceRef serviceRefBase = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefBase.setMappedName("mapped1");
    serviceRefBase.setServiceRefName("refName1");
    //    ServiceRefHandlerChains chains = JavaeeFactory.eINSTANCE.createServiceRefHandlerChains();
    //    ServiceRefHandlerChain chain = JavaeeFactory.eINSTANCE.createServiceRefHandlerChain();
    //    chains.getHandlerChains().add(chain);
    //    chain.
    //    resRefBase.setHandlerChains(chains);
    serviceRefBase.setServiceInterface("interface1");
    serviceRefBase.setWsdlFile("file1");


    ServiceRef serviceRefMerge = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefMerge.setMappedName("mapped2");
    serviceRefMerge.setServiceRefName("refName2");
    serviceRefMerge.setServiceInterface("interface2");
    serviceRefMerge.setWsdlFile("file2");

    beanBase.getServiceRefs().add(serviceRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getServiceRefs().size());
    assertEquals("mapped1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getServiceRefName());
    assertEquals("interface1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getServiceInterface());
    assertEquals("file1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getWsdlFile());
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testServiceRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ServiceRef serviceRefBase = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefBase.setMappedName("mapped1");
    serviceRefBase.setServiceRefName("refName1");
    serviceRefBase.setServiceInterface("interface1");
    serviceRefBase.setWsdlFile("file1");

    ServiceRef serviceRefMerge = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefMerge.setMappedName("mapped2");
    serviceRefMerge.setServiceRefName("refName2");
    serviceRefMerge.setServiceInterface("interface2");
    serviceRefMerge.setWsdlFile("file2");

    beanToMerge.getServiceRefs().add(serviceRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getServiceRefs().size());
    assertEquals("mapped1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getServiceRefName());
    assertEquals("interface1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getServiceInterface());
    assertEquals("file1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getWsdlFile());
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testServiceRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ServiceRef serviceRefBase = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefBase.setMappedName("mapped1");
    serviceRefBase.setServiceRefName("refName1");
    serviceRefBase.setServiceInterface("interface1");
    serviceRefBase.setWsdlFile("file1");


    ServiceRef resRefToMerge = JavaeeFactory.eINSTANCE.createServiceRef();
    resRefToMerge.setMappedName("mapped2");
    resRefToMerge.setServiceRefName("refName2");
    resRefToMerge.setServiceInterface("interface2");
    resRefToMerge.setWsdlFile("file2");

    beanBase.getServiceRefs().add(serviceRefBase);
    beanToMerge.getServiceRefs().add(serviceRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getServiceRefs().size());
    assertEquals("mapped1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getMappedName());
    assertEquals("refName1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getServiceRefName());
    assertEquals("interface1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getServiceInterface());
    assertEquals("file1", ((ServiceRef)beanBase.getServiceRefs().get(0)).getWsdlFile());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testServiceRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ServiceRef serviceRefBase = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefBase.setMappedName("mapped1");
    serviceRefBase.setServiceRefName("refName1");
    serviceRefBase.setServiceInterface("interface1");
    serviceRefBase.setWsdlFile("file1");

    ServiceRef serviceRefMerge = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefMerge.setMappedName("mapped2");
    serviceRefMerge.setServiceRefName("refName2");
    serviceRefMerge.setServiceInterface("interface2");
    serviceRefMerge.setWsdlFile("file2");

    beanBase.getServiceRefs().add(serviceRefBase);
    beanToMerge.getServiceRefs().add(serviceRefBase);
    beanToMerge.getServiceRefs().add(serviceRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getServiceRefs().size());

    ServiceRef test = getServiceRef(beanBase.getServiceRefs(), "refName1");
    ServiceRef test2 = getServiceRef(beanBase.getServiceRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals("interface1", test.getServiceInterface());
    assertEquals("file1", test.getWsdlFile());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals("interface2", test2.getServiceInterface());
    assertEquals("file2", test2.getWsdlFile());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testServiceRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    ServiceRef serviceRefBase = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefBase.setServiceRefName("refName1");

    ServiceRef serviceRefMerge = JavaeeFactory.eINSTANCE.createServiceRef();
    serviceRefMerge.setMappedName("mapped2");
    serviceRefMerge.setServiceRefName("refName1");
    serviceRefMerge.setServiceInterface("interface2");
    serviceRefMerge.setWsdlFile("file2");

    beanBase.getServiceRefs().add(serviceRefBase);
    beanToMerge.getServiceRefs().add(serviceRefBase);
    beanToMerge.getServiceRefs().add(serviceRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getServiceRefs().size());

    ServiceRef test = getServiceRef(beanBase.getServiceRefs(), "refName1");
    assertEquals("interface2", test.getServiceInterface());
    assertEquals("mapped2", test.getMappedName());
    assertEquals("file2", test.getWsdlFile());

  }

  private ServiceRef getServiceRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      ServiceRef ref = (ServiceRef) object;
      if(name.equals(ref.getServiceRefName())){
        return ref; 
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
  public void testPersistenceUnitRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceUnitRef persistenceUnitRefBase = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefBase.setMappedName("mapped1");
    persistenceUnitRefBase.setPersistenceUnitRefName("refName1");
    persistenceUnitRefBase.setPersistenceUnitName("type1");


    PersistenceUnitRef persistenceUnitRefMerge = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefMerge.setMappedName("mapped2");
    persistenceUnitRefMerge.setPersistenceUnitRefName("refName2");
    persistenceUnitRefMerge.setPersistenceUnitName("type2");

    beanBase.getPersistenceUnitRefs().add(persistenceUnitRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceUnitRefs().size());
    assertEquals("mapped1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getMappedName());
    assertEquals("refName1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getPersistenceUnitRefName());
    assertEquals("type1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getPersistenceUnitName());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testPersistenceUnitRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceUnitRef persistenceUnitRefBase = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefBase.setMappedName("mapped1");
    persistenceUnitRefBase.setPersistenceUnitRefName("refName1");
    persistenceUnitRefBase.setPersistenceUnitName("type1");

    PersistenceUnitRef persistenceUnitRefMerge = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefMerge.setMappedName("mapped2");
    persistenceUnitRefMerge.setPersistenceUnitRefName("refName2");
    persistenceUnitRefMerge.setPersistenceUnitName("type2");

    beanToMerge.getPersistenceUnitRefs().add(persistenceUnitRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceUnitRefs().size());
    assertEquals("mapped1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getMappedName());
    assertEquals("refName1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getPersistenceUnitRefName());
    assertEquals("type1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getPersistenceUnitName());

  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testPersistenceUnitRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceUnitRef persistenceUnitRefBase = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefBase.setMappedName("mapped1");
    persistenceUnitRefBase.setPersistenceUnitRefName("refName1");
    persistenceUnitRefBase.setPersistenceUnitName("type1");

    PersistenceUnitRef persistenceUnitRefMerge = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefMerge.setMappedName("mapped2");
    persistenceUnitRefMerge.setPersistenceUnitRefName("refName2");
    persistenceUnitRefMerge.setPersistenceUnitName("type2");

    beanBase.getPersistenceUnitRefs().add(persistenceUnitRefBase);
    beanToMerge.getPersistenceUnitRefs().add(persistenceUnitRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceUnitRefs().size());
    assertEquals("mapped1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getMappedName());
    assertEquals("refName1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getPersistenceUnitRefName());
    assertEquals("type1", ((PersistenceUnitRef)beanBase.getPersistenceUnitRefs().get(0)).getPersistenceUnitName());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testPersistenceUnitRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceUnitRef persistenceUnitRefBase = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefBase.setMappedName("mapped1");
    persistenceUnitRefBase.setPersistenceUnitRefName("refName1");
    persistenceUnitRefBase.setPersistenceUnitName("type1");

    PersistenceUnitRef persistenceUnitRefMerge = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefMerge.setMappedName("mapped2");
    persistenceUnitRefMerge.setPersistenceUnitRefName("refName2");
    persistenceUnitRefMerge.setPersistenceUnitName("type2");

    beanBase.getPersistenceUnitRefs().add(persistenceUnitRefBase);
    beanToMerge.getPersistenceUnitRefs().add(persistenceUnitRefBase);
    beanToMerge.getPersistenceUnitRefs().add(persistenceUnitRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getPersistenceUnitRefs().size());

    PersistenceUnitRef test = getPersistUnitRef(beanBase.getPersistenceUnitRefs(), "refName1");
    PersistenceUnitRef test2 = getPersistUnitRef(beanBase.getPersistenceUnitRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals("type1", test.getPersistenceUnitName());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals("type2", test2.getPersistenceUnitName());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testPersistenceUnitRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceUnitRef persistenceUnitRefBase = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefBase.setPersistenceUnitRefName("refName1");

    PersistenceUnitRef persistenceUnitRefMerge = JavaeeFactory.eINSTANCE.createPersistenceUnitRef();
    persistenceUnitRefMerge.setMappedName("mapped2");
    persistenceUnitRefMerge.setPersistenceUnitRefName("refName1");
    persistenceUnitRefMerge.setPersistenceUnitName("type2");

    beanBase.getPersistenceUnitRefs().add(persistenceUnitRefBase);
    beanToMerge.getPersistenceUnitRefs().add(persistenceUnitRefBase);
    beanToMerge.getPersistenceUnitRefs().add(persistenceUnitRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceUnitRefs().size());

    PersistenceUnitRef test = getPersistUnitRef(beanBase.getPersistenceUnitRefs(), "refName1");
    assertEquals("type2", test.getPersistenceUnitName());
    assertEquals("mapped2", test.getMappedName());

  }

  private PersistenceUnitRef getPersistUnitRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      PersistenceUnitRef ref = (PersistenceUnitRef) object;
      if(name.equals(ref.getPersistenceUnitRefName())){
        return ref; 
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
  public void testPersistenceContextRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceContextRef persistenceContextRefBase = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefBase.setMappedName("mapped1");
    persistenceContextRefBase.setPersistenceContextRefName("refName1");
    persistenceContextRefBase.setPersistenceContextType(PersistenceContextType.EXTENDED_LITERAL);

    PersistenceContextRef persistenceContextRefMerge = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefMerge.setMappedName("mapped2");
    persistenceContextRefMerge.setPersistenceContextRefName("refName2");
    persistenceContextRefMerge.setPersistenceContextType(PersistenceContextType.TRANSACTION_LITERAL);

    beanBase.getPersistenceContextRefs().add(persistenceContextRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceContextRefs().size());
    assertEquals("mapped1", ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getMappedName());
    assertEquals("refName1", ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getPersistenceContextRefName());
    assertEquals(PersistenceContextType.EXTENDED_LITERAL, ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getPersistenceContextType());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testPersistenceContextRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceContextRef persistenceContextRefBase = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefBase.setMappedName("mapped1");
    persistenceContextRefBase.setPersistenceContextRefName("refName1");
    persistenceContextRefBase.setPersistenceContextType(PersistenceContextType.EXTENDED_LITERAL);

    PersistenceContextRef persistenceContextRefMerge = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefMerge.setMappedName("mapped2");
    persistenceContextRefMerge.setPersistenceContextRefName("refName2");
    persistenceContextRefMerge.setPersistenceContextType(PersistenceContextType.TRANSACTION_LITERAL);

    beanToMerge.getPersistenceContextRefs().add(persistenceContextRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceContextRefs().size());
    assertEquals("mapped1", ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getMappedName());
    assertEquals("refName1", ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getPersistenceContextRefName());
    assertEquals(PersistenceContextType.EXTENDED_LITERAL, ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getPersistenceContextType());

  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testPersistenceContextRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceContextRef persistenceContextRefBase = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefBase.setMappedName("mapped1");
    persistenceContextRefBase.setPersistenceContextRefName("refName1");
    persistenceContextRefBase.setPersistenceContextType(PersistenceContextType.EXTENDED_LITERAL);

    PersistenceContextRef persistenceContextRefMerge = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefMerge.setMappedName("mapped2");
    persistenceContextRefMerge.setPersistenceContextRefName("refName2");
    persistenceContextRefMerge.setPersistenceContextType(PersistenceContextType.TRANSACTION_LITERAL);

    beanBase.getPersistenceContextRefs().add(persistenceContextRefBase);
    beanToMerge.getPersistenceContextRefs().add(persistenceContextRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceContextRefs().size());
    assertEquals("mapped1", ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getMappedName());
    assertEquals("refName1", ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getPersistenceContextRefName());
    assertEquals(PersistenceContextType.EXTENDED_LITERAL, ((PersistenceContextRef)beanBase.getPersistenceContextRefs().get(0)).getPersistenceContextType());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testPersistenceContextRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceContextRef persistenceContextRefBase = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefBase.setMappedName("mapped1");
    persistenceContextRefBase.setPersistenceContextRefName("refName1");
    persistenceContextRefBase.setPersistenceContextType(PersistenceContextType.EXTENDED_LITERAL);

    PersistenceContextRef persistenceContextRefMerge = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefMerge.setMappedName("mapped2");
    persistenceContextRefMerge.setPersistenceContextRefName("refName2");
    persistenceContextRefMerge.setPersistenceContextType(PersistenceContextType.TRANSACTION_LITERAL);

    beanBase.getPersistenceContextRefs().add(persistenceContextRefBase);
    beanToMerge.getPersistenceContextRefs().add(persistenceContextRefBase);
    beanToMerge.getPersistenceContextRefs().add(persistenceContextRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getPersistenceContextRefs().size());

    PersistenceContextRef test = getPersistenceCintextRef(beanBase.getPersistenceContextRefs(), "refName1");
    PersistenceContextRef test2 = getPersistenceCintextRef(beanBase.getPersistenceContextRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals(PersistenceContextType.EXTENDED_LITERAL, test.getPersistenceContextType());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals(PersistenceContextType.TRANSACTION_LITERAL, test2.getPersistenceContextType());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testPersistenceContextRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    PersistenceContextRef persistenceContextRefBase = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefBase.setPersistenceContextRefName("refName1");

    PersistenceContextRef persistenceContextRefMerge = JavaeeFactory.eINSTANCE.createPersistenceContextRef();
    persistenceContextRefMerge.setMappedName("mapped2");
    persistenceContextRefMerge.setPersistenceContextRefName("refName1");
    persistenceContextRefMerge.setPersistenceContextType(PersistenceContextType.TRANSACTION_LITERAL);

    beanBase.getPersistenceContextRefs().add(persistenceContextRefBase);
    beanToMerge.getPersistenceContextRefs().add(persistenceContextRefBase);
    beanToMerge.getPersistenceContextRefs().add(persistenceContextRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getPersistenceContextRefs().size());

    PersistenceContextRef test = getPersistenceCintextRef(beanBase.getPersistenceContextRefs(), "refName1");
    assertEquals(PersistenceContextType.TRANSACTION_LITERAL, test.getPersistenceContextType());
    assertEquals("mapped2", test.getMappedName());

  }

  private PersistenceContextRef getPersistenceCintextRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      PersistenceContextRef ref = (PersistenceContextRef) object;
      if(name.equals(ref.getPersistenceContextRefName())){
        return ref; 
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
  public void testMessageDestinationRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    MessageDestinationRef messageDestRefBase = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefBase.setMappedName("mapped1");
    messageDestRefBase.setMessageDestinationRefName("refName1");
    messageDestRefBase.setMessageDestinationType("type1");
    messageDestRefBase.setMessageDestinationLink("link1");
    messageDestRefBase.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_LITERAL);

    MessageDestinationRef messageDestRefMerge = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefMerge.setMappedName("mapped2");
    messageDestRefMerge.setMessageDestinationRefName("refName2");
    messageDestRefMerge.setMessageDestinationType("type2");
    messageDestRefMerge.setMessageDestinationLink("link2");
    messageDestRefMerge.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);

    beanBase.getMessageDestinationRefs().add(messageDestRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getMessageDestinationRefs().size());
    assertEquals("mapped1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMappedName());
    assertEquals("refName1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationRefName());
    assertEquals("type1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationType());
    assertEquals("link1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationLink());
    assertEquals(MessageDestinationUsageType.CONSUMES_LITERAL, ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationUsage());

  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testMessageDestinationRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    MessageDestinationRef messageDestRefBase = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefBase.setMappedName("mapped1");
    messageDestRefBase.setMessageDestinationRefName("refName1");
    messageDestRefBase.setMessageDestinationType("type1");
    messageDestRefBase.setMessageDestinationLink("link1");
    messageDestRefBase.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_LITERAL);

    MessageDestinationRef messageDestRefMerge = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefMerge.setMappedName("mapped2");
    messageDestRefMerge.setMessageDestinationRefName("refName2");
    messageDestRefMerge.setMessageDestinationType("type2");
    messageDestRefMerge.setMessageDestinationLink("link2");
    messageDestRefMerge.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);


    beanToMerge.getMessageDestinationRefs().add(messageDestRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getMessageDestinationRefs().size());
    assertEquals("mapped1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMappedName());
    assertEquals("refName1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationRefName());
    assertEquals("type1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationType());
    assertEquals("link1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationLink());
    assertEquals(MessageDestinationUsageType.CONSUMES_LITERAL, ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationUsage());

  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testMessageDestinationRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    MessageDestinationRef messageDestRefBase = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefBase.setMappedName("mapped1");
    messageDestRefBase.setMessageDestinationRefName("refName1");
    messageDestRefBase.setMessageDestinationType("type1");
    messageDestRefBase.setMessageDestinationLink("link1");
    messageDestRefBase.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_LITERAL);

    MessageDestinationRef messageDestRefMerge = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefMerge.setMappedName("mapped2");
    messageDestRefMerge.setMessageDestinationRefName("refName2");
    messageDestRefMerge.setMessageDestinationType("type2");
    messageDestRefMerge.setMessageDestinationLink("link2");
    messageDestRefMerge.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);


    beanBase.getMessageDestinationRefs().add(messageDestRefBase);
    beanToMerge.getMessageDestinationRefs().add(messageDestRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getMessageDestinationRefs().size());
    assertEquals("mapped1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMappedName());
    assertEquals("refName1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationRefName());
    assertEquals("type1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationType());
    assertEquals("link1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationLink());
    assertEquals(MessageDestinationUsageType.CONSUMES_LITERAL, ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationUsage());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testMessageDestinationRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    MessageDestinationRef messageDestRefBase = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefBase.setMappedName("mapped1");
    messageDestRefBase.setMessageDestinationRefName("refName1");
    messageDestRefBase.setMessageDestinationType("type1");
    messageDestRefBase.setMessageDestinationLink("link1");
    messageDestRefBase.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_LITERAL);

    MessageDestinationRef messageDestRefMerge = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefMerge.setMappedName("mapped2");
    messageDestRefMerge.setMessageDestinationRefName("refName2");
    messageDestRefMerge.setMessageDestinationType("type2");
    messageDestRefMerge.setMessageDestinationLink("link2");
    messageDestRefMerge.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);


    beanBase.getMessageDestinationRefs().add(messageDestRefBase);
    beanToMerge.getMessageDestinationRefs().add(messageDestRefBase);
    beanToMerge.getMessageDestinationRefs().add(messageDestRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getMessageDestinationRefs().size());

    MessageDestinationRef test = getMessageRef(beanBase.getMessageDestinationRefs(), "refName1");
    MessageDestinationRef test2 = getMessageRef(beanBase.getMessageDestinationRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals("type1", test.getMessageDestinationType());
    assertEquals("link1", ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationLink());
    assertEquals(MessageDestinationUsageType.CONSUMES_LITERAL, ((MessageDestinationRef)beanBase.getMessageDestinationRefs().get(0)).getMessageDestinationUsage());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals("type2", test2.getMessageDestinationType());
    assertEquals("link2", test2.getMessageDestinationLink());
    assertEquals(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL, test2.getMessageDestinationUsage());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testMessageDestinationRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    MessageDestinationRef messageDestRefBase = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefBase.setMessageDestinationRefName("refName1");

    MessageDestinationRef messageDestRefMerge = JavaeeFactory.eINSTANCE.createMessageDestinationRef();
    messageDestRefMerge.setMappedName("mapped2");
    messageDestRefMerge.setMessageDestinationRefName("refName1");
    messageDestRefMerge.setMessageDestinationType("type2");
    messageDestRefMerge.setMessageDestinationLink("link2");
    messageDestRefMerge.setMessageDestinationUsage(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL);


    beanBase.getMessageDestinationRefs().add(messageDestRefBase);
    beanToMerge.getMessageDestinationRefs().add(messageDestRefBase);
    beanToMerge.getMessageDestinationRefs().add(messageDestRefMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getMessageDestinationRefs().size());

    MessageDestinationRef test = getMessageRef(beanBase.getMessageDestinationRefs(), "refName1");
    assertEquals("type2", test.getMessageDestinationType());
    assertEquals("mapped2", test.getMappedName());
    assertEquals("link2", test.getMessageDestinationLink());
    assertEquals(MessageDestinationUsageType.CONSUMES_PRODUCES_LITERAL, test.getMessageDestinationUsage());

  }

  private MessageDestinationRef getMessageRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      MessageDestinationRef ref = (MessageDestinationRef) object;
      if(name.equals(ref.getMessageDestinationRefName())){
        return ref; 
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
  public void testEjbRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbRef ejbRefBase = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefBase.setMappedName("mapped1");
    ejbRefBase.setEjbRefName("refName1");
    ejbRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbRefBase.setEjbLink("link1");
    ejbRefBase.setHome("home1");
    ejbRefBase.setRemote("remote1");

    EjbRef ejbRefToMerge = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefToMerge.setMappedName("mapped2");
    ejbRefToMerge.setEjbRefName("refName2");
    ejbRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbRefToMerge.setEjbLink("link2");
    ejbRefToMerge.setHome("home2");
    ejbRefToMerge.setRemote("remote2");

    beanBase.getEjbRefs().add(ejbRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbRefs().size());
    assertEquals("mapped1", ((EjbRef)beanBase.getEjbRefs().get(0)).getMappedName());
    assertEquals("refName1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbRefName());
    assertEquals(EjbRefType.SESSION_LITERAL, ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbRefType());
    assertEquals("link1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbLink());
    assertEquals("home1", ((EjbRef)beanBase.getEjbRefs().get(0)).getHome());
    assertEquals("remote1", ((EjbRef)beanBase.getEjbRefs().get(0)).getRemote());
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testEjbRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbRef ejbRefBase = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefBase.setMappedName("mapped1");
    ejbRefBase.setEjbRefName("refName1");
    ejbRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbRefBase.setEjbLink("link1");
    ejbRefBase.setHome("home1");
    ejbRefBase.setRemote("remote1");

    EjbRef ejbRefToMerge = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefToMerge.setMappedName("mapped2");
    ejbRefToMerge.setEjbRefName("refName2");
    ejbRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbRefToMerge.setEjbLink("link2");
    ejbRefToMerge.setHome("home2");
    ejbRefToMerge.setRemote("remote2");


    beanToMerge.getEjbRefs().add(ejbRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbRefs().size());
    assertEquals("mapped1", ((EjbRef)beanBase.getEjbRefs().get(0)).getMappedName());
    assertEquals("refName1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbRefName());
    assertEquals(EjbRefType.SESSION_LITERAL, ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbRefType());
    assertEquals("link1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbLink());
    assertEquals("home1", ((EjbRef)beanBase.getEjbRefs().get(0)).getHome());
    assertEquals("remote1", ((EjbRef)beanBase.getEjbRefs().get(0)).getRemote());
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testEjbRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbRef ejbRefBase = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefBase.setMappedName("mapped1");
    ejbRefBase.setEjbRefName("refName1");
    ejbRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbRefBase.setEjbLink("link1");
    ejbRefBase.setHome("home1");
    ejbRefBase.setRemote("remote1");

    EjbRef ejbRefToMerge = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefToMerge.setMappedName("mapped2");
    ejbRefToMerge.setEjbRefName("refName2");
    ejbRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbRefToMerge.setEjbLink("link2");
    ejbRefToMerge.setHome("home2");
    ejbRefToMerge.setRemote("remote2");


    beanBase.getEjbRefs().add(ejbRefBase);
    beanToMerge.getEjbRefs().add(ejbRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbRefs().size());
    assertEquals("mapped1", ((EjbRef)beanBase.getEjbRefs().get(0)).getMappedName());
    assertEquals("refName1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbRefName());
    assertEquals(EjbRefType.SESSION_LITERAL, ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbRefType());
    assertEquals("link1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbLink());
    assertEquals("home1", ((EjbRef)beanBase.getEjbRefs().get(0)).getHome());
    assertEquals("remote1", ((EjbRef)beanBase.getEjbRefs().get(0)).getRemote());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testEjbRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbRef ejbRefBase = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefBase.setMappedName("mapped1");
    ejbRefBase.setEjbRefName("refName1");
    ejbRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbRefBase.setEjbLink("link1");
    ejbRefBase.setHome("home1");
    ejbRefBase.setRemote("remote1");

    EjbRef ejbRefToMerge = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefToMerge.setMappedName("mapped2");
    ejbRefToMerge.setEjbRefName("refName2");
    ejbRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbRefToMerge.setEjbLink("link2");
    ejbRefToMerge.setHome("home2");
    ejbRefToMerge.setRemote("remote2");


    beanBase.getEjbRefs().add(ejbRefBase);
    beanToMerge.getEjbRefs().add(ejbRefBase);
    beanToMerge.getEjbRefs().add(ejbRefToMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getEjbRefs().size());

    EjbRef test = getEjbRef(beanBase.getEjbRefs(), "refName1");
    EjbRef test2 = getEjbRef(beanBase.getEjbRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals(EjbRefType.SESSION_LITERAL, test.getEjbRefType());
    assertEquals("link1", ((EjbRef)beanBase.getEjbRefs().get(0)).getEjbLink());
    assertEquals("home1", test.getHome());
    assertEquals("remote1", test.getRemote());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals(EjbRefType.ENTITY_LITERAL, test2.getEjbRefType());
    assertEquals("link2", test2.getEjbLink());
    assertEquals("home2", test2.getHome());
    assertEquals("remote2", test2.getRemote());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testEjbRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    SessionBean beanToLink = EjbFactory.eINSTANCE.createSessionBean();


    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");
    beanToLink.setEjbName("name");

    EjbRef ejbRefBase = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefBase.setEjbRefName("refName1");

    EjbRef ejbRefToMerge = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefToMerge.setMappedName("mapped2");
    ejbRefToMerge.setEjbRefName("refName1");
    ejbRefToMerge.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbRefToMerge.setEjbLink("link2");
    ejbRefToMerge.setHome("home2");
    ejbRefToMerge.setRemote("remote2");


    EjbRef ejbRefToLink = JavaeeFactory.eINSTANCE.createEjbRef();
    ejbRefToLink.setEjbRefName("refName199");
    ejbRefToLink.setEjbLink("link2");


    beanBase.getEjbRefs().add(ejbRefBase);
    beanToMerge.getEjbRefs().add(ejbRefBase);
    beanToMerge.getEjbRefs().add(ejbRefToMerge);
    beanToLink.getEjbRefs().add(ejbRefToLink);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbRefs().size());

    EjbRef test = getEjbRef(beanBase.getEjbRefs(), "refName1");
    assertEquals(EjbRefType.SESSION_LITERAL, test.getEjbRefType());
    assertEquals("mapped2", test.getMappedName());
    assertEquals("link2", test.getEjbLink());
    assertEquals("home2", test.getHome());
    assertEquals("remote2", test.getRemote());

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

  }

  private EjbRef getEjbRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      EjbRef ref = (EjbRef) object;
      if(name.equals(ref.getEjbRefName())){
        return ref; 
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
  public void testEjbLocalRefsBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbLocalRef ejbLocalRefBase = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefBase.setMappedName("mapped1");
    ejbLocalRefBase.setEjbRefName("refName1");
    ejbLocalRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbLocalRefBase.setEjbLink("link1");
    ejbLocalRefBase.setLocalHome("home1");
    ejbLocalRefBase.setLocal("remote1");

    EjbLocalRef ejbLocalRefToMerge = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefToMerge.setMappedName("mapped2");
    ejbLocalRefToMerge.setEjbRefName("refName2");
    ejbLocalRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbLocalRefToMerge.setEjbLink("link2");
    ejbLocalRefToMerge.setLocalHome("home2");
    ejbLocalRefToMerge.setLocal("remote2");

    beanBase.getEjbLocalRefs().add(ejbLocalRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbLocalRefs().size());
    assertEquals("mapped1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getMappedName());
    assertEquals("refName1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbRefName());
    assertEquals(EjbRefType.SESSION_LITERAL, ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbRefType());
    assertEquals("link1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbLink());
    assertEquals("home1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getLocalHome());
    assertEquals("remote1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getLocal());
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testEjbLocalRefsMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbLocalRef envRefBase = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    envRefBase.setMappedName("mapped1");
    envRefBase.setEjbRefName("refName1");
    envRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    envRefBase.setEjbLink("link1");
    envRefBase.setLocalHome("home1");
    envRefBase.setLocal("remote1");

    EjbLocalRef ejbLocalRefToMerge = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefToMerge.setMappedName("mapped2");
    ejbLocalRefToMerge.setEjbRefName("refName2");
    ejbLocalRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbLocalRefToMerge.setEjbLink("link2");
    ejbLocalRefToMerge.setLocalHome("home2");
    ejbLocalRefToMerge.setLocal("remote2");


    beanToMerge.getEjbLocalRefs().add(envRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbLocalRefs().size());
    assertEquals("mapped1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getMappedName());
    assertEquals("refName1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbRefName());
    assertEquals(EjbRefType.SESSION_LITERAL, ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbRefType());
    assertEquals("link1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbLink());
    assertEquals("home1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getLocalHome());
    assertEquals("remote1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getLocal());
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testEjbLocalRefsSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbLocalRef ejbLocalRefBase = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefBase.setMappedName("mapped1");
    ejbLocalRefBase.setEjbRefName("refName1");
    ejbLocalRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbLocalRefBase.setEjbLink("link1");
    ejbLocalRefBase.setLocalHome("home1");
    ejbLocalRefBase.setLocal("remote1");

    EjbLocalRef ejbLocalRefToMerge = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefToMerge.setMappedName("mapped2");
    ejbLocalRefToMerge.setEjbRefName("refName2");
    ejbLocalRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbLocalRefToMerge.setEjbLink("link2");
    ejbLocalRefToMerge.setLocalHome("home2");
    ejbLocalRefToMerge.setLocal("remote2");


    beanBase.getEjbLocalRefs().add(ejbLocalRefBase);
    beanToMerge.getEjbLocalRefs().add(ejbLocalRefBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbLocalRefs().size());
    assertEquals("mapped1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getMappedName());
    assertEquals("refName1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbRefName());
    assertEquals(EjbRefType.SESSION_LITERAL, ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbRefType());
    assertEquals("link1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbLink());
    assertEquals("home1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getLocalHome());
    assertEquals("remote1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getLocal());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testEjbLocalRefsComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EjbLocalRef ejbLocalRefBase = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefBase.setMappedName("mapped1");
    ejbLocalRefBase.setEjbRefName("refName1");
    ejbLocalRefBase.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbLocalRefBase.setEjbLink("link1");
    ejbLocalRefBase.setLocalHome("home1");
    ejbLocalRefBase.setLocal("remote1");

    EjbLocalRef ejbLocalRefToMerge = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefToMerge.setMappedName("mapped2");
    ejbLocalRefToMerge.setEjbRefName("refName2");
    ejbLocalRefToMerge.setEjbRefType(EjbRefType.ENTITY_LITERAL);
    ejbLocalRefToMerge.setEjbLink("link2");
    ejbLocalRefToMerge.setLocalHome("home2");
    ejbLocalRefToMerge.setLocal("remote2");


    beanBase.getEjbLocalRefs().add(ejbLocalRefBase);
    beanToMerge.getEjbLocalRefs().add(ejbLocalRefBase);
    beanToMerge.getEjbLocalRefs().add(ejbLocalRefToMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getEjbLocalRefs().size());

    EjbLocalRef test = getEjbLocalRef(beanBase.getEjbLocalRefs(), "refName1");
    EjbLocalRef test2 = getEjbLocalRef(beanBase.getEjbLocalRefs(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals(EjbRefType.SESSION_LITERAL, test.getEjbRefType());
    assertEquals("link1", ((EjbLocalRef)beanBase.getEjbLocalRefs().get(0)).getEjbLink());
    assertEquals("home1", test.getLocalHome());
    assertEquals("remote1", test.getLocal());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals(EjbRefType.ENTITY_LITERAL, test2.getEjbRefType());
    assertEquals("link2", test2.getEjbLink());
    assertEquals("home2", test2.getLocalHome());
    assertEquals("remote2", test2.getLocal());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testEjbLocalRefsComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    SessionBean beanToLink = EjbFactory.eINSTANCE.createSessionBean();


    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");
    beanToLink.setEjbName("name");

    EjbLocalRef ejbLocalRefBase = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefBase.setEjbRefName("refName1");

    EjbLocalRef ejbLocalRefToMerge = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefToMerge.setMappedName("mapped2");
    ejbLocalRefToMerge.setEjbRefName("refName1");
    ejbLocalRefToMerge.setEjbRefType(EjbRefType.SESSION_LITERAL);
    ejbLocalRefToMerge.setEjbLink("link2");
    ejbLocalRefToMerge.setLocalHome("home2");
    ejbLocalRefToMerge.setLocal("remote2");


    EjbLocalRef ejbLocalRefToLink = JavaeeFactory.eINSTANCE.createEjbLocalRef();
    ejbLocalRefToLink.setEjbRefName("refName199");
    ejbLocalRefToLink.setEjbLink("link2");


    beanBase.getEjbLocalRefs().add(ejbLocalRefBase);
    beanToMerge.getEjbLocalRefs().add(ejbLocalRefBase);
    beanToMerge.getEjbLocalRefs().add(ejbLocalRefToMerge);
    beanToLink.getEjbLocalRefs().add(ejbLocalRefToLink);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEjbLocalRefs().size());

    EjbLocalRef test = getEjbLocalRef(beanBase.getEjbLocalRefs(), "refName1");
    assertEquals(EjbRefType.SESSION_LITERAL, test.getEjbRefType());
    assertEquals("mapped2", test.getMappedName());
    assertEquals("link2", test.getEjbLink());
    assertEquals("home2", test.getLocalHome());
    assertEquals("remote2", test.getLocal());

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

  }

  private EjbLocalRef getEjbLocalRef(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      EjbLocalRef ref = (EjbLocalRef) object;
      if(name.equals(ref.getEjbRefName())){
        return ref; 
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
  public void testEnvEntrysBase() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EnvEntry envEntryBase = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryBase.setMappedName("mapped1");
    envEntryBase.setEnvEntryName("refName1");
    envEntryBase.setEnvEntryType(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL);
    envEntryBase.setEnvEntryValue("true");


    EnvEntry envEntryMerge = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryMerge.setMappedName("mapped2");
    envEntryMerge.setEnvEntryName("refName2");
    envEntryMerge.setEnvEntryType(EnvEntryType.JAVA_LANG_BYTE_LITERAL);
    envEntryMerge.setEnvEntryValue("00");

    beanBase.getEnvEntries().add(envEntryBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEnvEntries().size());
    assertEquals("mapped1", ((EnvEntry)beanBase.getEnvEntries().get(0)).getMappedName());
    assertEquals("refName1", ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryName());
    assertEquals(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL.getLiteral(), ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryType());
    assertEquals("true", ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryValue());
  }

  /**
   * ToMerge suffix means that the base is empty object and 
   * toMerge has some info: all from merge should be present in base.
   * @throws Exception
   */
  //@Test
  public void testEnvEntrysMerge() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EnvEntry envEntryBase = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryBase.setMappedName("mapped1");
    envEntryBase.setEnvEntryName("refName1");
    envEntryBase.setEnvEntryType(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL);
    envEntryBase.setEnvEntryValue("true");

    EnvEntry envEntryMerge = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryMerge.setMappedName("mapped2");
    envEntryMerge.setEnvEntryName("refName2");
    envEntryMerge.setEnvEntryType(EnvEntryType.JAVA_LANG_BYTE_LITERAL);
    envEntryMerge.setEnvEntryValue("00");

    beanToMerge.getEnvEntries().add(envEntryBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEnvEntries().size());
    assertEquals("mapped1", ((EnvEntry)beanBase.getEnvEntries().get(0)).getMappedName());
    assertEquals("refName1", ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryName());
    assertEquals(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL.getLiteral(), ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryType());
    assertEquals("true", ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryValue());
  }

  /**
   *  Same suffix means that the information in merge and base is one 
   * and the same: no merge should occurred and additional checks for 
   * doubling of the elements are present.
   * @throws Exception
   */
  //@Test
  public void testEnvEntrysSame() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EnvEntry envEntryBase = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryBase.setMappedName("mapped1");
    envEntryBase.setEnvEntryName("refName1");
    envEntryBase.setEnvEntryType(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL);
    envEntryBase.setEnvEntryValue("true");


    EnvEntry envEntryMerge = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryMerge.setMappedName("mapped2");
    envEntryMerge.setEnvEntryName("refName2");
    envEntryMerge.setEnvEntryType(EnvEntryType.JAVA_LANG_BYTE_LITERAL);
    envEntryMerge.setEnvEntryValue("00");

    beanBase.getEnvEntries().add(envEntryBase);
    beanToMerge.getEnvEntries().add(envEntryBase);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEnvEntries().size());
    assertEquals("mapped1", ((EnvEntry)beanBase.getEnvEntries().get(0)).getMappedName());
    assertEquals("refName1", ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryName());
    assertEquals(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL.getLiteral(), ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryType());
    assertEquals("true", ((EnvEntry)beanBase.getEnvEntries().get(0)).getEnvEntryValue());

  }

  /**
   *  Complex suffix means variety of information is present in base 
   * and to merge: consistent information combined by base and toMerge
   * should be available at the end.
   * @throws Exception
   */
  //@Test
  public void testEnvEntrysComplex() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EnvEntry envEntryBase = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryBase.setMappedName("mapped1");
    envEntryBase.setEnvEntryName("refName1");
    envEntryBase.setEnvEntryType(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL);
    envEntryBase.setEnvEntryValue("true");

    EnvEntry envEntryMerge = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryMerge.setMappedName("mapped2");
    envEntryMerge.setEnvEntryName("refName2");
    envEntryMerge.setEnvEntryType(EnvEntryType.JAVA_LANG_BYTE_LITERAL);
    envEntryMerge.setEnvEntryValue("00");

    beanBase.getEnvEntries().add(envEntryBase);
    beanToMerge.getEnvEntries().add(envEntryBase);
    beanToMerge.getEnvEntries().add(envEntryMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(2, beanBase.getEnvEntries().size());

    EnvEntry test = getEnvEntry(beanBase.getEnvEntries(), "refName1");
    EnvEntry test2 = getEnvEntry(beanBase.getEnvEntries(), "refName2");
    assertEquals("mapped1", test.getMappedName());
    assertEquals(EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL.getLiteral(), test.getEnvEntryType());
    assertEquals("true", test.getEnvEntryValue());

    assertEquals("mapped2", test2.getMappedName());
    assertEquals(EnvEntryType.JAVA_LANG_BYTE_LITERAL.getLiteral(), test2.getEnvEntryType());
    assertEquals("00", test2.getEnvEntryValue());

  }

  /**
   * ComplexOverlapped suffix means variety of information is present 
   * in base and to merge: consistent information combined by base and toMerge
   * should be available at the end. There are artifacts with one and the same 
   * name and different values: values should be merged into base.
   * @throws Exception
   */
  //@Test
  public void testEnvEntrysComplexOverlapped() throws Exception {
    SessionBean beanBase = EjbFactory.eINSTANCE.createSessionBean();
    SessionBean beanToMerge = EjbFactory.eINSTANCE.createSessionBean();

    beanBase.setEjbName("name");
    beanToMerge.setEjbName("name");

    EnvEntry envEntryBase = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryBase.setEnvEntryName("refName1");

    EnvEntry envEntryMerge = JavaeeFactory.eINSTANCE.createEnvEntry();
    envEntryMerge.setMappedName("mapped2");
    envEntryMerge.setEnvEntryName("refName1");
    envEntryMerge.setEnvEntryType(EnvEntryType.JAVA_LANG_BYTE_LITERAL);
    envEntryMerge.setEnvEntryValue("00");

    beanBase.getEnvEntries().add(envEntryBase);
    beanToMerge.getEnvEntries().add(envEntryBase);
    beanToMerge.getEnvEntries().add(envEntryMerge);

    (new JNDIRefsMerger(beanBase, beanToMerge, 0)).process();

    assertEquals(1, beanBase.getEnvEntries().size());

    EnvEntry test = getEnvEntry(beanBase.getEnvEntries(), "refName1");
    assertEquals(EnvEntryType.JAVA_LANG_BYTE_LITERAL.getLiteral(), test.getEnvEntryType());
    assertEquals("mapped2", test.getMappedName());
    assertEquals("00", test.getEnvEntryValue());

  }

  private EnvEntry getEnvEntry(List refs, String name){
    if (name == null){
      return null;
    }
    for (Object object : refs) {
      EnvEntry ref = (EnvEntry) object;
      if(name.equals(ref.getEnvEntryName())){
        return ref; 
      }
    }
    return null;
  }
}
