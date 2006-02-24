/*
 * Created on Aug 8, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.ejb.ui.internal.actions;

import org.eclipse.jst.j2ee.internal.actions.AbstractActionDelegate;
import org.eclipse.swt.widgets.Shell;



/**
 * @author dfholttp
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class EJBClientRemovalActionDelegate extends AbstractActionDelegate {
	private EJBClientRemovalAction realAction = new EJBClientRemovalAction();
    /**
     * 
     */
    public EJBClientRemovalActionDelegate() {
        super();
       	setAllowsMultiSelect(false);
    }
	protected void primRun(Shell shell) {
		realAction.setSelection(getStructuredSelection());
		realAction.primRun(shell);
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.j2ee.common.actions.AbstractActionDelegate#isSupportedAction(java.lang.Object)
	 */
	protected boolean isSupportedAction(Object element) {
		return false;
	}

}
