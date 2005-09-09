/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

/**
 * @author DABERG
 */
public class J2EEMultiStatus extends J2EEStatus {
	private J2EEStatus[] children = new J2EEStatus[0];

	public void add(J2EEStatus status) {
		J2EEStatus[] result =
			new J2EEStatus[children.length + 1];
		System.arraycopy(children, 0, result, 0, children.length);
		result[result.length - 1] = status;
		children = result;
		int newSev = status.getSeverity();
		int currentSev = getSeverity();
		/* If all the statuses are NOT_NEEDED OR NOT_POSSIBLE, you want the combined status to be the highest severity
		 * however, if all but one status are NOT_NEEDED or NOT_POSSIBLE, and one status completed, then the combined status
		 * should be OK.  If there is a warning or error, then they take precedence
		 */
		if (children.length == 1)
			setSeverity(newSev);
		else if (currentSev > COMPLETED_OK && currentSev < WARNING && newSev == COMPLETED_OK)
			setSeverity(newSev);
		else if (newSev > currentSev && (currentSev != COMPLETED_OK || newSev >= WARNING) )
			setSeverity(newSev);

	}
	/**
	 * Adds all of the children of the given status to this multi-status.
	 * Does nothing if the given status has no children (which includes
	 * the case where it is not a multi-status).
	 *
	 * @param status the status whose children are to be added to this one
	 */
	public void addAll(J2EEStatus status) {
		if (status == null)
			return;
		J2EEStatus[] statuses = status.getChildren();
		for (int i = 0; i < statuses.length; i++) {
			add(statuses[i]);
		}
	}

	public J2EEStatus[] getChildren() {
		return children;
	}

	public boolean isMultiStatus() {
		return true;
	}
	/**
	 * Merges the given status into this multi-status.
	 * Equivalent to <code>add(status)</code> if the
	 * given status is not a multi-status. 
	 * Equivalent to <code>addAll(status)</code> if the
	 * given status is a multi-status. 
	 *
	 * @param status the status to merge into this one
	 * @see #add
	 * @see #addAll
	 */
	public void merge(J2EEStatus status) {
		if (status == null)
			return;
		if (!status.isMultiStatus()) {
			add(status);
		} else {
			addAll(status);
		}
	}
	/**
	 * Returns a string representation of the status, suitable 
	 * for debugging purposes only.
	 */
	public String toString() {
		StringBuffer buf = new StringBuffer(super.toString());
		buf.append(" children={"); //$NON-NLS-1$
		for (int i = 0; i < children.length; i++) {
			if (i != 0) {
				buf.append("\n"); //$NON-NLS-1$
			}
			buf.append(children[i].toString());
		}
		buf.append("}"); //$NON-NLS-1$
		return buf.toString();
	}

	/**
	 * @see com.ibm.ejs.models.base.extensions.helper.J2EEStatus#append(J2EEStatus)
	 */
	public J2EEStatus append(J2EEStatus aStatus) {
		if (aStatus != null)
			merge(aStatus);
		return this;
	}
	
	public boolean isEmpty() {
		return children.length == 0;
	}


}

