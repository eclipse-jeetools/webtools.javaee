package ejbs;
/**
 * Key class for Entity Bean: CMP2
 */
public class CMP2Key implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Integer id;
	/**
	 * Creates an empty key for Entity Bean: CMP2
	 */
	public CMP2Key() {
	}
	/**
	 * Creates a key for Entity Bean: CMP2
	 */
	public CMP2Key(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof ejbs.CMP2Key) {
			ejbs.CMP2Key o = (ejbs.CMP2Key) otherKey;
			return ((this.id.equals(o.id)));
		}
		return false;
	}
	/**
	 * Returns the hash code for the key.
	 */
	public int hashCode() {
		return (id.hashCode());
	}
}