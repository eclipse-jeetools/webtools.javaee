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
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.jdt.ui.dialogs.ITypeInfoFilterExtension;
import org.eclipse.jdt.ui.dialogs.ITypeInfoRequestor;
import org.eclipse.jdt.ui.dialogs.TypeSelectionExtension;

/**
 * A type selection extension used in the
 * {@link BusinessInterfaceSelectionDialog}. The extension filters the types
 * shown in the business interface selection dialog.
 * 
 * Interfaces from the javax.ejb package can not be used as business interfaces
 * for a bean. The same applies for {@link java.io.Serializable} and
 * {@link java.io.Externalizable}
 * 
 * @author Kiril Mitov k.mitov@sap.com
 * 
 */
public class BusinessInterfaceSelectionExtension extends TypeSelectionExtension {

	private static final String EXTERNALIZABLE = "Externalizable"; //$NON-NLS-1$
	private static final String SERIALIZABLE = "Serializable"; //$NON-NLS-1$
	private static final String JAVA_IO = "java.io"; //$NON-NLS-1$
	private static final String JAVAX_EJB = "javax.ejb"; //$NON-NLS-1$

	public BusinessInterfaceSelectionExtension() {
	}

	@Override
	public ITypeInfoFilterExtension getFilterExtension() {
		return new ITypeInfoFilterExtension() {

			public boolean select(ITypeInfoRequestor typeInfoRequestor) {
				String packageName = typeInfoRequestor.getPackageName();
				if (packageName.equals(JAVAX_EJB))
					return false;
				else if (packageName.equals(JAVA_IO)) {
					String typeName = typeInfoRequestor.getTypeName();
					if (typeName.equals(SERIALIZABLE) || typeName.equals(EXTERNALIZABLE))
						return false;
				}
				return true;
			}
		};
	}
}
