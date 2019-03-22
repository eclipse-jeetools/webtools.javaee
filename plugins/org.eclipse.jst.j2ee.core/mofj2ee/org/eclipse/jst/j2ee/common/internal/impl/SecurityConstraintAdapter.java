/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;

/**
	 */
	public class SecurityConstraintAdapter extends AdapterImpl {

			/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeature() == WebapplicationPackage.eINSTANCE.getSecurityConstraint_DisplayNames()) {
					synchDisplayName();
					return;
			} else if (msg.getFeature() == CommonPackage.eINSTANCE.getDescriptionGroup_DisplayNames() ||
					msg.getFeature() == CommonPackage.eINSTANCE.getDisplayName_Value()) {
					synchDisplayName();
					return;
				}
		}

		/**
		 * 
		 */
		private void synchDisplayName() {
			SecurityConstraint myTarget = (SecurityConstraint)getTarget();
			if (myTarget.getDisplayNames().isEmpty())
				myTarget.setDisplayName(null);
			else {
				DisplayName aDisplayName = (DisplayName)myTarget.getDisplayNames().get(0);
				if (!aDisplayName.eAdapters().contains(this))
					aDisplayName.eAdapters().add(this);
				myTarget.setDisplayName(aDisplayName.getValue());
			}
		}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	@Override
	public void setTarget(Notifier newTarget) {
		if (newTarget instanceof SecurityConstraint)
			super.setTarget(newTarget);
	}
}
