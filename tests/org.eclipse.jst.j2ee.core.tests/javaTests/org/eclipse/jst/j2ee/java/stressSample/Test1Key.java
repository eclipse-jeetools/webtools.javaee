package org.eclipse.jst.j2ee.java.stressSample;
/**
 * Key class for Entity Bean: Test1
 */
public class Test1Key implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public int id;
	/**
	 * Creates an empty key for Entity Bean: Test1
	 */
	public Test1Key() {
	}
	/**
	 * Creates a key for Entity Bean: Test1
	 */
	public Test1Key(int id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof org.eclipse.jst.j2ee.java.stressSample.Test1Key) {
			org.eclipse.jst.j2ee.java.stressSample.Test1Key o = (org.eclipse.jst.j2ee.java.stressSample.Test1Key) otherKey;
			return ((this.id == o.id));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return ((new java.lang.Integer(id).hashCode()));
	}
	/**
	 * Get accessor for persistent attribute: id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Set accessor for persistent attribute: id
	 */
	public void setId(int newId) {
		id = newId;
	}
}
