package org.eclipse.jst.j2ee.common.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DescriptionGroup;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.Listener;
import org.eclipse.jst.j2ee.common.MessageDestination;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.common.UseCallerIdentity;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;

public class CommonFactoryTest extends TestCase {

	private CommonFactory getInstance() {
		return CommonPackage.eINSTANCE.getCommonFactory();
	}
	
    public void test_CommonFactory() {

        CommonFactory tmpCommonFactory =getInstance();
    }

   
   public void test_createEjbRef() {

        CommonFactory objCommonFactory =getInstance();
        EjbRef retValue = null;
        retValue = objCommonFactory.createEjbRef();
        assertNotNull(retValue);
    }

    public void test_createEnvEntry() {

        CommonFactory objCommonFactory =getInstance();
        EnvEntry retValue = null;
        retValue = objCommonFactory.createEnvEntry();
        assertNotNull(retValue);
    }

    public void test_createResourceRef() {

        CommonFactory objCommonFactory =getInstance();
        ResourceRef retValue = null;
        retValue = objCommonFactory.createResourceRef();
        assertNotNull(retValue);
    }

    public void test_createSecurityRoleRef() {

        CommonFactory objCommonFactory =getInstance();
        SecurityRoleRef retValue = null;
        retValue = objCommonFactory.createSecurityRoleRef();
        assertNotNull(retValue);
    }

    public void test_createSecurityRole() {

        CommonFactory objCommonFactory =getInstance();
        SecurityRole retValue = null;
        retValue = objCommonFactory.createSecurityRole();
        assertNotNull(retValue);
    }

    public void test_createResourceEnvRef() {

        CommonFactory objCommonFactory =getInstance();
        ResourceEnvRef retValue = null;
        retValue = objCommonFactory.createResourceEnvRef();
        assertNotNull(retValue);
    }

    public void test_createEJBLocalRef() {

        CommonFactory objCommonFactory =getInstance();
        EJBLocalRef retValue = null;
        retValue = objCommonFactory.createEJBLocalRef();
        assertNotNull(retValue);
    }

    public void test_createRunAsSpecifiedIdentity() {

        CommonFactory objCommonFactory =getInstance();
        RunAsSpecifiedIdentity retValue = null;
        retValue = objCommonFactory.createRunAsSpecifiedIdentity();
        assertNotNull(retValue);
    }

    public void test_createIdentity() {

        CommonFactory objCommonFactory =getInstance();
        Identity retValue = null;
        retValue = objCommonFactory.createIdentity();
        assertNotNull(retValue);
    }

    public void test_createIconType() {

        CommonFactory objCommonFactory =getInstance();
        IconType retValue = null;
        retValue = objCommonFactory.createIconType();
        assertNotNull(retValue);
    }

    public void test_createDisplayName() {

        CommonFactory objCommonFactory =getInstance();
        DisplayName retValue = null;
        retValue = objCommonFactory.createDisplayName();
        assertNotNull(retValue);
    }

    public void test_createMessageDestinationRef() {

        CommonFactory objCommonFactory =getInstance();
        MessageDestinationRef retValue = null;
        retValue = objCommonFactory.createMessageDestinationRef();
        assertNotNull(retValue);
    }

    public void test_createMessageDestination() {

        CommonFactory objCommonFactory =getInstance();
        MessageDestination retValue = null;
        retValue = objCommonFactory.createMessageDestination();
        assertNotNull(retValue);
    }

    public void test_createParamValue() {

        CommonFactory objCommonFactory =getInstance();
        ParamValue retValue = null;
        retValue = objCommonFactory.createParamValue();
        assertNotNull(retValue);
    }

    public void test_createDescriptionGroup() {

        CommonFactory objCommonFactory =getInstance();
        DescriptionGroup retValue = null;
        retValue = objCommonFactory.createDescriptionGroup();
        assertNotNull(retValue);
    }

    public void test_createSecurityIdentity() {

        CommonFactory objCommonFactory =getInstance();
        SecurityIdentity retValue = null;
        retValue = objCommonFactory.createSecurityIdentity();
        assertNotNull(retValue);
    }

    public void test_createUseCallerIdentity() {

        CommonFactory objCommonFactory =getInstance();
        UseCallerIdentity retValue = null;
        retValue = objCommonFactory.createUseCallerIdentity();
        assertNotNull(retValue);
    }

    public void test_createDescription() {

        CommonFactory objCommonFactory =getInstance();
        Description retValue = null;
        retValue = objCommonFactory.createDescription();
        assertNotNull(retValue);
    }

    public void test_createQName() {

        CommonFactory objCommonFactory =getInstance();
        QName retValue = null;
        retValue = objCommonFactory.createQName();
        assertNotNull(retValue);
    }

    public void test_createListener() {

        CommonFactory objCommonFactory =getInstance();
        Listener retValue = null;
        retValue = objCommonFactory.createListener();
        assertNotNull(retValue);
    }

    public void test_createCompatibilityDescriptionGroup() {

        CommonFactory objCommonFactory =getInstance();
        CompatibilityDescriptionGroup retValue = null;
        retValue = objCommonFactory.createCompatibilityDescriptionGroup();
        assertNotNull(retValue);
    }

    public void test_getCommonPackage() {

        CommonFactory objCommonFactory =getInstance();
        CommonPackage retValue = null;
        retValue = objCommonFactory.getCommonPackage();
        assertNotNull(retValue);
    }   
    
    /**
	 * @return
	 */
	public static Test suite() {
		return new TestSuite(CommonFactoryTest.class);
	}
}
