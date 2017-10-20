/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.componentcore;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveTypeDiscriminator;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.EJBJarFileImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.EjbJar11ImportStrategyImpl;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategy;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

/**
 * @deprecated see {@link JavaEEBinaryComponentHelper}
 * @author jasholl
 *
 */
public class EJBBinaryComponentHelper extends EnterpriseBinaryComponentHelper {

	public static boolean handlesComponent(IVirtualComponent component) {
		EJBBinaryComponentHelper helper = null;
		try {
			helper = new EJBBinaryComponentHelper(component);
			return helper.isArchiveValid();
		} catch (Exception e) {
			return false;
		} finally {
			if(helper != null)
				helper.dispose();
		}
	}

	protected static class Discriminator extends EjbJar11ImportStrategyImpl.Discriminator {

		private static Discriminator instance;

		public static Discriminator getInstance() {
			if (instance == null) {
				instance = new Discriminator();
			}
			return instance;
		}

		@Override
		public Archive createConvertedArchive() {
			ReferenceCountedEJBJarFileImpl archive = new ReferenceCountedEJBJarFileImpl();
			return archive;
		}
	}

	protected static class ReferenceCountedEJBJarFileImpl extends EJBJarFileImpl implements IReferenceCountedArchive {

		private int count = 0;

		@Override
		public void access() {
			synchronized (this) {
				count++;
			}
		}

		@Override
		public void close() {
			helper.aboutToClose();
			synchronized (this) {
				count--;
				if (count > 0) {
					return;
				}
			}
			physicallyClose(this);
		}
		
		@Override
		public void forceClose(){
			count = 0;
			helper.aboutToClose();
			super.close();
		}
		
		private EnterpriseBinaryComponentHelper helper = null;
		
		@Override
		public EnterpriseBinaryComponentHelper getEnterpriseBinaryComponentHelper() {
			return helper;
		}

		@Override
		public void setEnterpriseBinaryComponentHelper(EnterpriseBinaryComponentHelper helper) {
			this.helper = helper;
		}
		
		@Override
		protected LoadStrategy createLoadStrategyForReopen(Archive parent) throws IOException {
			try {
				return createBinaryLoadStrategy(getEnterpriseBinaryComponentHelper());
			} catch (OpenFailureException e) {
				throw new IOException(e.getMessage());
			}
		}
		
		@Override
		protected void cleanupAfterTempSave(String uri, File original, File destinationFile) throws SaveFailureException {
			helper.preCleanupAfterTempSave(uri, original, destinationFile);
			super.cleanupAfterTempSave(uri, original, destinationFile);
			helper.postCleanupAfterTempSave(uri, original, destinationFile);
		}
	}

	@Override
	protected ArchiveTypeDiscriminator getDiscriminator() {
		return Discriminator.getInstance();
	}

	public EJBBinaryComponentHelper(IVirtualComponent component) {
		super(component);
	}

	@Override
	public EObject getPrimaryRootObject() {
		return ((EJBJarFile) getArchive()).getDeploymentDescriptor();
	}

}
