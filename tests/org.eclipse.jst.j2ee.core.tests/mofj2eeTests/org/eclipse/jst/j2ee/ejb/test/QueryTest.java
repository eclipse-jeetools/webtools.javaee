package org.eclipse.jst.j2ee.ejb.test;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.ejb.QueryMethod;
import org.eclipse.jst.j2ee.ejb.ReturnTypeMapping;

public class QueryTest extends TestCase {

    public void test_Query() {
        Query tmpQuery = EjbFactory.eINSTANCE.createQuery();
        assertNotNull(tmpQuery);
    }

    public void test_isLocalResultMapping() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        objQuery.setReturnTypeMapping(ReturnTypeMapping.LOCAL_LITERAL);
        assertEquals(objQuery.isLocalResultMapping(),true);
        objQuery.setReturnTypeMapping(ReturnTypeMapping.REMOTE_LITERAL);
        assertEquals(objQuery.isLocalResultMapping(),false);
       
    }

    public void test_isRemoteResultMapping() {

    	 Query objQuery = EjbFactory.eINSTANCE.createQuery();
         objQuery.setReturnTypeMapping(ReturnTypeMapping.REMOTE_LITERAL);
         assertEquals(objQuery.isLocalResultMapping(),false);
         objQuery.setReturnTypeMapping(ReturnTypeMapping.LOCAL_LITERAL);
         assertEquals(objQuery.isLocalResultMapping(),true);
     
    }

 /*   public void test_getDescription() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        String retValue = "";
        retValue = objQuery.getDescription();
    }

    public void test_setDescription() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        String newDescription = "";
        objQuery.setDescription(newDescription);
    }

    public void test_getEjbQL() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        String retValue = "";
        retValue = objQuery.getEjbQL();
    }

    public void test_setEjbQL() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        String newEjbQL = "";
        objQuery.setEjbQL(newEjbQL);
    } */

  /*  public void test_getReturnTypeMapping() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        ReturnTypeMapping retValue = null;
        retValue = objQuery.getReturnTypeMapping();
    }

    public void test_setReturnTypeMapping() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        ReturnTypeMapping newReturnTypeMapping = null;
        objQuery.setReturnTypeMapping(newReturnTypeMapping);
    }

    public void test_unsetReturnTypeMapping() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        objQuery.unsetReturnTypeMapping();
    }

    public void test_isSetReturnTypeMapping() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        boolean retValue = false;
        retValue = objQuery.isSetReturnTypeMapping();
    }*/

 /*   public void test_getQueryMethod() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        QueryMethod retValue = null;
        retValue = objQuery.getQueryMethod();
    }

    public void test_setQueryMethod() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        QueryMethod newQueryMethod = null;
        objQuery.setQueryMethod(newQueryMethod);
    }*/

  /*  public void test_getEntity() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        ContainerManagedEntity retValue = null;
        retValue = objQuery.getEntity();
    }

    public void test_setEntity() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        ContainerManagedEntity newEntity = null;
        objQuery.setEntity(newEntity);
    }*/

    /*public void test_getDescriptions() {

        Query objQuery = EjbFactory.eINSTANCE.createQuery();
        EList retValue = null;
        retValue = objQuery.getDescriptions();
    }*/
}
