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
/*
 * Created on Mar 3, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.common.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.common.IconType;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;



/**
	 * @author cbridgha
	 *
	 * To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Generation - Code and Comments
	 */
	public class DescriptionGroupAdapter extends AdapterImpl {

			/* (non-Javadoc)
		 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
		 */
		public void notifyChanged(Notification msg) {
			if (msg.getFeature() == CommonPackage.eINSTANCE.getDescriptionGroup_Icons() ||
					msg.getFeature() == CommonPackage.eINSTANCE.getIconType_LargeIcon() ||
					msg.getFeature() == CommonPackage.eINSTANCE.getIconType_SmallIcon()) {
					synchIcons();
					return;
				}
			else if (msg.getFeature() == CommonPackage.eINSTANCE.getDescriptionGroup_Descriptions() ||
					msg.getFeature() == CommonPackage.eINSTANCE.getDescription_Value()) {
					synchDescription();
					return;
				}
			else if (msg.getFeature() == CommonPackage.eINSTANCE.getDescriptionGroup_DisplayNames() ||
					msg.getFeature() == CommonPackage.eINSTANCE.getDisplayName_Value()) {
					synchDisplayName();
					return;
				}
			
		}

		/**
		 * 
		 */
		private void synchDescription() {
			CompatibilityDescriptionGroup myTarget = (CompatibilityDescriptionGroup)getTarget();
			if (myTarget.getDescriptions().isEmpty())
				myTarget.setDescriptionGen(null);
			else {
				Description aDescription = (Description)myTarget.getDescriptions().get(0);
				if (!aDescription.eAdapters().contains(this))
					aDescription.eAdapters().add(this);
				else 
					myTarget.setDescriptionGen(aDescription.getValue());
			}
		}

		/**
		 * 
		 */
		private void synchDisplayName() {
			CompatibilityDescriptionGroup myTarget = (CompatibilityDescriptionGroup)getTarget();
			if (myTarget.getDisplayNames().isEmpty())
				myTarget.setDisplayNameGen(null);
			else {
				DisplayName aDisplayName = (DisplayName)myTarget.getDisplayNames().get(0);
				if (!aDisplayName.eAdapters().contains(this))
					aDisplayName.eAdapters().add(this);
				myTarget.setDisplayNameGen(aDisplayName.getValue());
			}
		}

		/**
		 * 
		 */
		private void synchIcons() {
			CompatibilityDescriptionGroup myTarget = (CompatibilityDescriptionGroup)getTarget();
			if (myTarget.getIcons().isEmpty()) {
				myTarget.setLargeIconGen(null);
				myTarget.setSmallIconGen(null);
			}
			else {
				IconType anIcon = (IconType)myTarget.getIcons().get(0);
				if (!anIcon.eAdapters().contains(this))
					anIcon.eAdapters().add(this);
				else {
					myTarget.setLargeIconGen(anIcon.getLargeIcon());
					myTarget.setSmallIconGen(anIcon.getSmallIcon());
				}
			}
		}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		if (newTarget instanceof CompatibilityDescriptionGroup)
			super.setTarget(newTarget);
	}
}