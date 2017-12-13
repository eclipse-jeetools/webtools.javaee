package ejbs;
/**
 * Local Home interface for Enterprise Bean: CMP1
 */
public interface CMP1LocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: CMP1
	 */
	public ejbs.CMP1Local create(java.lang.Integer id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: CMP1
	 */
	public ejbs.CMP1Local findByPrimaryKey(ejbs.CMP1Key primaryKey)
		throws javax.ejb.FinderException;
}