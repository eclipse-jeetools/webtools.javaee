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
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.WARFileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.War22ImportStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class WebBinaryComponentHelper extends EnterpriseBinaryComponentHelper {

	public static boolean handlesComponent(IVirtualComponent component) {
		WebBinaryComponentHelper helper = null;
		try {
			helper = new WebBinaryComponentHelper(component);
			return helper.isArchiveValid();
		} catch (Exception e) {
			return false;
		} finally {
			helper.dispose();
		}
	}

	protected static class Discriminator extends War22ImportStrategyImpl.Discriminator {

		private static Discriminator instance;

		public static Discriminator getInstance() {
			if (instance == null) {
				instance = new Discriminator();
			}
			return instance;
		}

		public Archive createConvertedArchive() {
			ReferenceCountedWARFileImpl archive = new ReferenceCountedWARFileImpl();
			return archive;
		}
	}

	protected static class ReferenceCountedWARFileImpl extends WARFileImpl implements IReferenceCountedArchive {

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
			physicallyClose(this);
		}
		
		public void forceClose(){
			count = 0;
			super.close();
		}
	}

	protected ArchiveTypeDiscriminator getDiscriminator() {
		return Discriminator.getInstance();
	}

	public WebBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	public EObject getPrimaryRootObject() {
		return ((WARFile) getArchive()).getDeploymentDescriptor();
	}

}