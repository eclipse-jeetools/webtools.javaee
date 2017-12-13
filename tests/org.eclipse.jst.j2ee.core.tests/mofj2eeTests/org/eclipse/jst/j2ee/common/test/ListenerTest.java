package org.eclipse.jst.j2ee.common.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Listener;

public class ListenerTest extends TestCase {

	private Listener getInstance() {
		return CommonFactory.eINSTANCE.createListener();
	}
   /* public void test_getListenerClass() {
        Listener objListener = getInstance();
        JavaClass retValue = null;
        retValue = objListener.getListenerClass();
    } 

    public void test_setListenerClass() {

        Listener objListener = getInstance();
        JavaClass newListenerClass = null;
        objListener.setListenerClass(newListenerClass);
    }*/


    public void test_getListenerClassName() {

        Listener objListener = getInstance();
        String listenerClassName = "org.eclipse.jst.j2ee.common.Listener1" ;
        objListener.setListenerClassName(listenerClassName);
        String retValue = "";
        retValue = objListener.getListenerClassName();
        assertEquals(listenerClassName, retValue);
    }

    public void test_setListenerClassName() {

        Listener objListener = getInstance();
        String listenerClassName = "org.eclipse.jst.j2ee.common.Listener1";
        objListener.setListenerClassName(listenerClassName);
        String retValue = "";
        retValue = objListener.getListenerClassName();
        assertEquals(listenerClassName, retValue);
    }
    
    /**
	 * @return
	 */
	public static Test suite() {
		return new TestSuite(ListenerTest.class);
	}

}
