/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.ejb.ui.internal.actions;
import org.eclipse.jst.j2ee.internal.actions.AbstractActionDelegate;
import org.eclipse.swt.widgets.Shell;




public class EJBClientCreationActionDelegate extends AbstractActionDelegate {
	private EJBClientCreationAction realAction = new EJBClientCreationAction();
    /**
     * 
     */
    public EJBClientCreationActionDelegate() {
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
