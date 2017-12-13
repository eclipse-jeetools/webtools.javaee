package ejbs;
/**
 * Local Home interface for Enterprise Bean: CMP2
 */
public interface CMP2LocalHome extends javax.ejb.EJBLocalHome {
	/**
	 * Creates an instance from a key for Entity Bean: CMP2
	 */
	public ejbs.CMP2Local create(java.lang.Integer id)
		throws javax.ejb.CreateException;
	/**
	 * Finds an instance using a key for Entity Bean: CMP2
	 */
	public ejbs.CMP2Local findByPrimaryKey(ejbs.CMP2Key primaryKey)
		throws javax.ejb.FinderException;
}