package com.ibm.test.ejb;
/**
 * Key class for Entity Bean: MyCMP
 */
public class MyCMPKey extends com.ibm.test.A implements java.io.Serializable {

	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implemetation field for persistent attribute: k1
	 */
	public java.lang.String k1;
	/**
	 * Creates an empty key for Entity Bean: MyCMP
	 */
	public MyCMPKey() {
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof com.ibm.test.ejb.MyCMPKey) {
			com.ibm.test.ejb.MyCMPKey o = (com.ibm.test.ejb.MyCMPKey)otherKey;
			return ((this.k1.equals(o.k1)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (k1.hashCode());
	}
	/**
	 * Creates a key for Entity Bean: MyCMP
	 */
	public MyCMPKey(java.lang.String k1) {
		this.k1 = k1;
	}
}
