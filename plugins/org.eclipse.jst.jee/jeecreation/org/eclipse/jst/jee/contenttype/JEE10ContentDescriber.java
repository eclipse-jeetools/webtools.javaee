/*******************************************************************************
 * Copyright (c) 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.contenttype;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;

/**
 * A content describer for detecting a jakarta ee module
 */
public final class JEE10ContentDescriber implements IContentDescriber {
	public JEE10ContentDescriber() {
		super();
	}

	public final static QualifiedName JEEVERSION = new QualifiedName("jee-version", "10.0"); //$NON-NLS-1$ //$NON-NLS-2$

	@Override
	public int describe(InputStream contents, IContentDescription description) throws IOException {

		JavaEEQuickPeek quickPeek = new JavaEEQuickPeek(contents);
		switch (quickPeek.getType()) {
		case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.JEE_10_0_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.APPLICATION_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.JEE_10_0_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.EJB_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.EJB_4_0_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.WEB_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.WEB_6_0_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.WEBFRAGMENT_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.WEBFRAGMENT_6_0_ID:
				return VALID;
			}
			return INVALID;
		case JavaEEQuickPeek.CONNECTOR_TYPE:
			switch (quickPeek.getVersion()) {
			case JavaEEQuickPeek.JCA_2_1_ID:
				return VALID;
			}
			return INVALID;
		}

		return INVALID;
	}

	@Override
	public QualifiedName[] getSupportedOptions() {

		return new QualifiedName[] { JEEVERSION };
	}

}
