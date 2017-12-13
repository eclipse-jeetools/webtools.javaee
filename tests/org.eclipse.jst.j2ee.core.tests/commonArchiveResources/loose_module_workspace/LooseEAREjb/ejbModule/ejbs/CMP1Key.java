package ejbs;
/**
 * Key class for Entity Bean: CMP1
 */
public class CMP1Key implements java.io.Serializable {
	static final long serialVersionUID = 3206093459760846163L;
	/**
	 * Implementation field for persistent attribute: id
	 */
	public java.lang.Integer id;
	/**
	 * Creates an empty key for Entity Bean: CMP1
	 */
	public CMP1Key() {
	}
	/**
	 * Creates a key for Entity Bean: CMP1
	 */
	public CMP1Key(java.lang.Integer id) {
		this.id = id;
	}
	/**
	 * Returns true if both keys are equal.
	 */
	public boolean equals(java.lang.Object otherKey) {
		if (otherKey instanceof ejbs.CMP1Key) {
			ejbs.CMP1Key o = (ejbs.CMP1Key) otherKey;
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