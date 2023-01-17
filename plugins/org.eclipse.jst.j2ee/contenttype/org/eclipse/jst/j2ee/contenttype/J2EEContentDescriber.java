/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.contenttype;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;

/**
 * A content describer for detecting a j2ee module
 */
public final class J2EEContentDescriber implements IContentDescriber {
	public J2EEContentDescriber() {
		super();
	}

	public final static QualifiedName JEEVERSION = new QualifiedName("jee-version", "1.4"); //$NON-NLS-1$ //$NON-NLS-2$

	@Override
	public int describe(InputStream contents, IContentDescription description) throws IOException {

		JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(contents);
		switch (quickPeek.getType()) {
		case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
		case JavaEEQuickPeek.APPLICATION_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.J2EE_1_2_ID:
			case JavaEEQuickPeek.J2EE_1_3_ID:
			case JavaEEQuickPeek.J2EE_1_4_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.CONNECTOR_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.JCA_1_0_ID:
			case JavaEEQuickPeek.JCA_1_5_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.EJB_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.EJB_1_1_ID:
			case JavaEEQuickPeek.EJB_2_0_ID:
			case JavaEEQuickPeek.EJB_2_1_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.WEB_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.WEB_2_2_ID:
			case JavaEEQuickPeek.WEB_2_3_ID:
			case JavaEEQuickPeek.WEB_2_4_ID:
			case JavaEEQuickPeek.WEB_3_0_ID:
			case JavaEEQuickPeek.WEB_3_1_ID:
			case JavaEEQuickPeek.WEB_4_0_ID:
			case JavaEEQuickPeek.WEB_5_0_ID:
			case JavaEEQuickPeek.WEB_6_0_ID:
				return VALID;
			}
			return INVALID;
		}

		return INVALID;
	}

	@Override
	public QualifiedName[] getSupportedOptions() {
		// this is not used
		return new QualifiedName[] { JEEVERSION };
	}

}
