package com.ibm.test.ejb;
/**
 * Home interface for Enterprise Bean: MyCMP
 */
public interface MyCMPHome extends javax.ejb.EJBHome {
	/**
	 * Creates an instance from a key for Entity Bean: MyCMP
	 */
	public com.ibm.test.ejb.MyCMP create(java.lang.String k1) throws javax.ejb.CreateException, java.rmi.RemoteException;
	/**
	 * Finds an instance using a key for Entity Bean: MyCMP
	 */
	public com.ibm.test.ejb.MyCMP findByPrimaryKey(com.ibm.test.ejb.MyCMPKey primaryKey) throws javax.ejb.FinderException, java.rmi.RemoteException;
}
