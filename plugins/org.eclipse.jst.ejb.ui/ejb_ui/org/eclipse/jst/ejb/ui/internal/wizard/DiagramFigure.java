/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.ejb.ui.internal.wizard;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

public class DiagramFigure extends Figure {
	public static final Font FONT_DEFAULT = new Font(null, "", 8, SWT.NONE);///$NON-NLS-1$

	{
		init();
	}

	private void init() {
		setLayoutManager(new StackLayout());
		setFont(FONT_DEFAULT);
	}

}