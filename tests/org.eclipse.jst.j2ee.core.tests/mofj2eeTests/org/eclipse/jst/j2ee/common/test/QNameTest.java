package org.eclipse.jst.j2ee.common.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.QName;

public class QNameTest extends TestCase {
	
	private QName getInstance() {
		return CommonFactory.eINSTANCE.createQName();
	}

  /*  public void test_getNamespaceURI() {

        QName objQName = getInstance();
        String retValue = "";
        retValue = objQName.getNamespaceURI();
    }

   
    public void test_setNamespaceURI() {

        QName objQName = getInstance();
        String newNamespaceURI = "";
        objQName.setNamespaceURI(newNamespaceURI);
    }

    public void test_getLocalPart() {

        QName objQName = getInstance();
        String retValue = "";
        retValue = objQName.getLocalPart();
    }

    public void test_setLocalPart() {

        QName objQName = getInstance();
        String newLocalPart = "";
        objQName.setLocalPart(newLocalPart);
    }

    public void test_getCombinedQName() {

        QName objQName = getInstance();
        String retValue = "";
        retValue = objQName.getCombinedQName();
    }


    public void test_setCombinedQName() {

        QName objQName = getInstance();
        String newCombinedQName = "";
        objQName.setCombinedQName(newCombinedQName);
    }

    public void test_getInternalPrefixOrNsURI() {

        QName objQName = getInstance();
        String retValue = "";
        retValue = objQName.getInternalPrefixOrNsURI();
    }

    

    public void test_setInternalPrefixOrNsURI() {

        QName objQName = getInstance();
        String newInternalPrefixOrNsURI = "";
        objQName.setInternalPrefixOrNsURI(newInternalPrefixOrNsURI);
    }*/


    public void test_setValues() {

        QName objQName = getInstance();
        String prefix = "prefix1";
        String nsURI = "nsURI1";
        String localPart = "localPart1";
        objQName.setValues(prefix, nsURI, localPart);
        assertEquals(prefix,objQName.getInternalPrefixOrNsURI());
        assertEquals(nsURI,objQName.getNamespaceURI());
        assertEquals(localPart,objQName.getLocalPart());
    }
    
    /**
	 * @return
	 */
	public static Test suite() {
		return new TestSuite(QNameTest.class);
	}

}
