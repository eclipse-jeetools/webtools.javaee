package org.eclipse.jst.j2ee.common.test;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

public class EjbRefTest extends TestCase {

	private EjbRef getInstance() {
		return CommonPackage.eINSTANCE.getCommonFactory().createEjbRef();
	}
    public void test_EjbRef() {
        EjbRef tmpEjbRef = getInstance();
    }

    public void test_getLinkedEjb() {
        EjbRef objEjbRef = getInstance();
        EJBJar ejbJar = null;
        EnterpriseBean retValue = null;
        retValue = objEjbRef.getLinkedEjb(ejbJar);
    }

    public void test_isEquivalent() {

        EjbRef objEjbRef = getInstance();
        EjbRef anEjbRef = null;
        boolean retValue = false;
        retValue = objEjbRef.isEquivalent(anEjbRef);
    }

    public void test_isLinked() {

        EjbRef objEjbRef = getInstance();
        EnterpriseBean anEJB = null;
        boolean retValue = false;
        retValue = objEjbRef.isLinked(anEJB);
    }

    public void test_isLocal() {

        EjbRef objEjbRef = getInstance();
        boolean retValue = false;
        retValue = objEjbRef.isLocal();
        assertEquals(retValue,false);
        EJBLocalRef objLocalRef = CommonPackage.eINSTANCE.getCommonFactory().createEJBLocalRef();
        assertEquals(retValue,true);
    }

   /* public void test_getName() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getName();
    }

    public void test_setName() {

        EjbRef objEjbRef = getInstance();
        String newName = "";
        objEjbRef.setName(newName);
    }

    public void test_getType() {

        EjbRef objEjbRef = getInstance();
        EjbRefType retValue = null;
        retValue = objEjbRef.getType();
    }

    public void test_setType() {

        EjbRef objEjbRef = getInstance();
        EjbRefType newType = null;
        objEjbRef.setType(newType);
    }

    public void test_unsetType() {

        EjbRef objEjbRef = getInstance();
        objEjbRef.unsetType();
    }

    public void test_isSetType() {

        EjbRef objEjbRef = getInstance();
        boolean retValue = false;
        retValue = objEjbRef.isSetType();
    }

    public void test_getHome() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getHome();
    }

    public void test_setHome() {

        EjbRef objEjbRef = getInstance();
        String newHome = "";
        objEjbRef.setHome(newHome);
    }

    public void test_getRemote() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getRemote();
    }

    public void test_setRemote() {

        EjbRef objEjbRef = getInstance();
        String newRemote = "";
        objEjbRef.setRemote(newRemote);
    }

    public void test_getLink() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getLink();
    }

    public void test_setLink() {

        EjbRef objEjbRef = getInstance();
        String newLink = "";
        objEjbRef.setLink(newLink);
    }

    public void test_setDescription() {

        EjbRef objEjbRef = getInstance();
        String newDescription = "";
        objEjbRef.setDescription(newDescription);
    }

    public void test_getDescription() {

        EjbRef objEjbRef = getInstance();
        String retValue = "";
        retValue = objEjbRef.getDescription();
    }

    public void test_getDescriptions() {

        EjbRef objEjbRef = getInstance();
        EList retValue = null;
        retValue = objEjbRef.getDescriptions();
    }*/
}
