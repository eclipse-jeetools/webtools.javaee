/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.componentcore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.RARFileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.RarImportStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JCABinaryComponentHelper extends EnterpriseBinaryComponentHelper {

	public static boolean handlesComponent(IVirtualComponent component) {
		JCABinaryComponentHelper helper = null;
		try {
			helper = new JCABinaryComponentHelper(component);
			return helper.isArchiveValid();
		} catch (Exception e) {
			return false;
		} finally {
			helper.dispose();
		}
	}

	protected static class Discriminator extends RarImportStrategyImpl.Discriminator {

		private static Discriminator instance;

		public static Discriminator getInstance() {
			if (instance == null) {
				instance = new Discriminator();
			}
			return instance;
		}

		public Archive createConvertedArchive() {
			ReferenceCountedRARFileImpl archive = new ReferenceCountedRARFileImpl();
			return archive;
		}
	}

	protected static class ReferenceCountedRARFileImpl extends RARFileImpl implements IReferenceCountedArchive {

		private int count = 0;

		public void access() {
			synchronized (this) {
				count++;
			}
		}

		public void close() {
			synchronized (this) {
				count--;
				if (count > 0) {
					return;
				}
			}
			ArchiveCache.getInstance().removeArchive(this);
			super.close();
		}
	}

	protected ArchiveTypeDiscriminator getDiscriminator() {
		return Discriminator.getInstance();
	}

	public JCABinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	public EObject getPrimaryRootObject() {
		return ((RARFile) getArchive()).getDeploymentDescriptor();
	}

}
