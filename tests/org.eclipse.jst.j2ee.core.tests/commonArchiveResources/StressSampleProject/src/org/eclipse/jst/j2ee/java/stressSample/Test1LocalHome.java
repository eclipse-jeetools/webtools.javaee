package org.eclipse.jst.j2ee.java.stressSample;
/**
 * Local Home interface for Enterprise Bean: Test1
 */
public interface Test1LocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: Test1
	 */
	public org.eclipse.jst.j2ee.java.stressSample.Test1Local create(int id) throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: Test1
	 */
	public org.eclipse.jst.j2ee.java.stressSample.Test1Local findByPrimaryKey(org.eclipse.jst.j2ee.java.stressSample.Test1Key primaryKey)
		throws javax.ejb.FinderException;
}
