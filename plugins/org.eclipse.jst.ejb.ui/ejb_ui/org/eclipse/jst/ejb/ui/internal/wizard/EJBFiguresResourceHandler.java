/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.osgi.util.NLS;

public final class EJBFiguresResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "ejb_figures";//$NON-NLS-1$

	private EJBFiguresResourceHandler() {
		// Do not instantiate
	}

	public static String RelationshipPolyLine_UI_0;
	public static String RelationshipPolyLine_UI_1;
	public static String RelationshipPolyLine_UI_2;
	public static String RelationshipPolyLine_UI_3;
	public static String RelationshipPolyLine_UI_4;
	public static String RelationshipPolyLine_UI_5;
	public static String RelationshipPolyLine_UI_6;
	public static String RelationshipPolyLine_UI_7;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EJBFiguresResourceHandler.class);
	}
}