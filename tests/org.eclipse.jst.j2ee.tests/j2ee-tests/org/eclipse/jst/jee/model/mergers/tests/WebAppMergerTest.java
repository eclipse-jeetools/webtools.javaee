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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.javaee.core.JavaeeFactory;
import org.eclipse.jst.javaee.core.RunAs;
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.web.Servlet;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.javaee.web.WebFactory;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;
import org.eclipse.jst.jee.model.internal.mergers.WebAppMerger;

/**
 * Tester class for EjbJar artifact.
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
public class WebAppMergerTest extends TestCase{

  /**
   * Tests the merger with Base security role only.
   * No merge is necessary
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleSecurityRoleCaseBase() throws ModelException{
    WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
    role.setRoleName("test1");
    
    descriptorBase.getSecurityRoles().add(role);
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 1);
    Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 0);
    Assert.assertTrue(((SecurityRole)descriptorBase.getSecurityRoles().get(0)).getRoleName().equals("test1"));
  }
  
  /**
   * Tests the merger with toMerge security role only.
   * The role should be copied in base.
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleSecurityRoleCaseToMerge() throws ModelException{
   WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
    role.setRoleName("test1");
    
    descriptorToMerge.getSecurityRoles().add(role);
    
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertEquals(1, descriptorBase.getSecurityRoles().size());
    Assert.assertEquals(1, descriptorToMerge.getSecurityRoles().size());
    Assert.assertTrue(((SecurityRole)descriptorBase.getSecurityRoles().get(0)).getRoleName().equals("test1"));
  }
  
  /**
   * Tests the merger with one and the same security role.
   * The result should be non merged 1 security role.
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleSecurityRoleCaseSameRole() throws ModelException{
   WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
    role.setRoleName("test1");
    descriptorBase.getSecurityRoles().add(role);
    descriptorToMerge.getSecurityRoles().add(role);
    
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 1);
    Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 1);
    Assert.assertTrue(((SecurityRole)descriptorBase.getSecurityRoles().get(0)).getRoleName().equals("test1"));
  }
  
  /**
   * Tests the merger with variety of security roles.
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleSecurityRoleCaseComplex() throws ModelException{
   WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    SecurityRole role0 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role1 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role2 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role3 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role4 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role5 = JavaeeFactory.eINSTANCE.createSecurityRole();
    role0.setRoleName("test1");
    role1.setRoleName("test2");
    role2.setRoleName("test3");
    role3.setRoleName("test4");
    role4.setRoleName("test5");
    role5.setRoleName("test6");
    
    
    
    descriptorBase.getSecurityRoles().add(role1);
    descriptorBase.getSecurityRoles().add(role3);
    descriptorBase.getSecurityRoles().add(role4);
    descriptorBase.getSecurityRoles().add(role5);
    
    
    descriptorToMerge.getSecurityRoles().add(role0);
    descriptorToMerge.getSecurityRoles().add(role1);
    descriptorToMerge.getSecurityRoles().add(role2);
   
    
    
    
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 6);
    Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 3);
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role0.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role1.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role2.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role3.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role4.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role5.getRoleName()));
  }

  private boolean containsSecRole(List list, String roleName) {
    
    
    for (Object object : list) {
      SecurityRole role = (SecurityRole) object;
      if(roleName == null){
        if(roleName == role.getRoleName()){
          return true;
        }
      } else if(roleName.equals(role.getRoleName())){
        return true;
      }
    }
    return false;
  }
  
  
  /**
   * Tests the merger with Base security role only.
   * No merge is necessary
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleRunAsCaseBase() throws ModelException{
    WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    RunAs role = JavaeeFactory.eINSTANCE.createRunAs();
    Servlet servletBase = WebFactory.eINSTANCE.createServlet();
    servletBase.setServletName("servName");
    Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
    servletMerge.setServletName("servName");
    role.setRoleName("test1");
    servletBase.setRunAs(role);
    
    descriptorBase.getServlets().add(servletBase);
    descriptorToMerge.getServlets().add(servletMerge);
    
    
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(servletBase.getRunAs());
    Assert.assertTrue(((RunAs)servletBase.getRunAs()).getRoleName().equals("test1"));
  }
  
  /**
   * Tests the merger with toMerge security role only.
   * The role should be copied in base.
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleRunAsCaseToMerge() throws ModelException{
    WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    RunAs role = JavaeeFactory.eINSTANCE.createRunAs();
    Servlet servletBase = WebFactory.eINSTANCE.createServlet();
    servletBase.setServletName("servName");
    Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
    servletMerge.setServletName("servName");
    role.setRoleName("test1");
    servletMerge.setRunAs(role);
    
    descriptorBase.getServlets().add(servletBase);
    descriptorToMerge.getServlets().add(servletMerge);
    
    
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(servletBase.getRunAs());
    Assert.assertTrue(((RunAs)servletBase.getRunAs()).getRoleName().equals("test1"));
    Assert.assertEquals(1, descriptorBase.getServlets().size());
  }
  
  /**
   * Tests the merger with one and the same security role.
   * The result should be non merged 1 security role.
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleRunAsCaseSameRole() throws ModelException{
    WebApp descriptorBase = WebFactory.eINSTANCE.createWebApp();
    WebApp descriptorToMerge = WebFactory.eINSTANCE.createWebApp();
    RunAs role = JavaeeFactory.eINSTANCE.createRunAs();
    Servlet servletBase = WebFactory.eINSTANCE.createServlet();
    servletBase.setServletName("servName");
    Servlet servletMerge = WebFactory.eINSTANCE.createServlet();
    servletMerge.setServletName("servName");
    role.setRoleName("test1");
    servletBase.setRunAs(role);
    servletMerge.setRunAs(role);
    
    descriptorBase.getServlets().add(servletBase);
    descriptorToMerge.getServlets().add(servletMerge);
    
    
    WebAppMerger result = new WebAppMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(servletBase.getRunAs());
    Assert.assertTrue(((RunAs)servletBase.getRunAs()).getRoleName().equals("test1"));
  }
}
