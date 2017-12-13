/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.ejb.ui;

import org.eclipse.jdt.ui.dialogs.ITypeInfoFilterExtension;
import org.eclipse.jdt.ui.dialogs.ITypeInfoRequestor;
import org.eclipse.jst.ejb.ui.internal.wizard.BusinessInterfaceSelectionExtension;

import junit.framework.TestCase;

/**
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class BusinessInterfaceSelectionExtensionTest extends TestCase {

	private BusinessInterfaceSelectionExtension fixture;

	private final class MockTypeInfoRequestor implements ITypeInfoRequestor {
		private String packageName;
		private String typeName;

		public String getEnclosingName() {
			return null;
		}

		public int getModifiers() {
			return 0;
		}

		public String getPackageName() {
			return packageName;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setPackageName(String packageName) {
			this.packageName = packageName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		fixture = new BusinessInterfaceSelectionExtension();
	}

	public void testSelectJavaxEjbPackage() {
		MockTypeInfoRequestor requestor = new MockTypeInfoRequestor();
		requestor.setPackageName("javax.ejb"); //$NON-NLS-1$
		ITypeInfoFilterExtension filter = fixture.getFilterExtension();
		assertFalse(filter.select(requestor));
	}

	public void testSelectJavaIOSerizlizablePackage() {
		MockTypeInfoRequestor requestor = new MockTypeInfoRequestor();
		requestor.setPackageName("java.io"); //$NON-NLS-1$
		requestor.setTypeName("Serializable"); //$NON-NLS-1$
		ITypeInfoFilterExtension filter = fixture.getFilterExtension();
		assertFalse(filter.select(requestor));
	}

	public void testSelectJavaIOExternalizablePackage() {
		MockTypeInfoRequestor requestor = new MockTypeInfoRequestor();
		requestor.setPackageName("java.io"); //$NON-NLS-1$
		requestor.setTypeName("Externalizable"); //$NON-NLS-1$
		ITypeInfoFilterExtension filter = fixture.getFilterExtension();
		assertFalse(filter.select(requestor));
	}
}
