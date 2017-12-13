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
import org.eclipse.jst.javaee.core.SecurityRole;
import org.eclipse.jst.javaee.ejb.AssemblyDescriptor;
import org.eclipse.jst.javaee.ejb.EjbFactory;
import org.eclipse.jst.jee.model.internal.mergers.AssemblyDescriptorMerger;
import org.eclipse.jst.jee.model.internal.mergers.ModelException;


/**
 * Tests Assembly descriptor merger.
 * 
 * @author Dimitar Giormov
 *
 */
public class AssemblyDescriptorMergerTest extends TestCase{

  /**
   * Tests the merger with empty list of security roles.
   * 
   * @throws ModelException
   */
//  @Test
  public void testEmptySecurityRoleCase() throws ModelException{
    AssemblyDescriptor descriptorBase = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptor descriptorToMerge = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptorMerger result = new AssemblyDescriptorMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 0);
    Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 0);
  }
  
  /**
   * Tests the merger with Base security role only.
   * No merge is necessary
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleSecurityRoleCaseBase() throws ModelException{
    AssemblyDescriptor descriptorBase = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
    role.setRoleName("test1");
    
    descriptorBase.getSecurityRoles().add(role);
    AssemblyDescriptor descriptorToMerge = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptorMerger result = new AssemblyDescriptorMerger(descriptorBase, descriptorToMerge, 0);
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
    AssemblyDescriptor descriptorBase = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptor descriptorToMerge = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
    role.setRoleName("test1");
    
    descriptorToMerge.getSecurityRoles().add(role);
    
    AssemblyDescriptorMerger result = new AssemblyDescriptorMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 1);
    Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 1);
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
    AssemblyDescriptor descriptorBase = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptor descriptorToMerge = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    SecurityRole role = JavaeeFactory.eINSTANCE.createSecurityRole();
    role.setRoleName("test1");
    descriptorBase.getSecurityRoles().add(role);
    descriptorToMerge.getSecurityRoles().add(role);
    
    AssemblyDescriptorMerger result = new AssemblyDescriptorMerger(descriptorBase, descriptorToMerge, 0);
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
    AssemblyDescriptor descriptorBase = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptor descriptorToMerge = EjbFactory.eINSTANCE.createAssemblyDescriptor();
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
   
    
    
    
    AssemblyDescriptorMerger result = new AssemblyDescriptorMerger(descriptorBase, descriptorToMerge, 0);
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
   * Tests the merger with variety of security roles. In addition one of the roles has no name.
   * 
   * @throws ModelException
   */
  //@Test
  public void testSingleSecurityRoleCaseComplexWithoutName() throws ModelException{
    AssemblyDescriptor descriptorBase = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    AssemblyDescriptor descriptorToMerge = EjbFactory.eINSTANCE.createAssemblyDescriptor();
    SecurityRole role0 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role1 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role2 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role3 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role4 = JavaeeFactory.eINSTANCE.createSecurityRole();
    SecurityRole role5 = JavaeeFactory.eINSTANCE.createSecurityRole();
    role0.setRoleName("test1");
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
   
    
    
    
    AssemblyDescriptorMerger result = new AssemblyDescriptorMerger(descriptorBase, descriptorToMerge, 0);
    result.process();
    Assert.assertNotNull(descriptorBase.getSecurityRoles());
    Assert.assertTrue(descriptorBase.getSecurityRoles().size() == 6);
    Assert.assertTrue(descriptorToMerge.getSecurityRoles().size() == 3);
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role0.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), null));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role2.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role3.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role4.getRoleName()));
    Assert.assertTrue(containsSecRole(descriptorBase.getSecurityRoles(), role5.getRoleName()));
  }
}
