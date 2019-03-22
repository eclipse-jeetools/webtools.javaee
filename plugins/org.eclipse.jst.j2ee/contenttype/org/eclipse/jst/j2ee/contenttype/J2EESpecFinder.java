/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.contenttype;

import java.io.InputStream;

import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;

/**
 * @deprecated use {@link JavaEEQuickPeek}
 * @author jasholl
 * 
 */
public class J2EESpecFinder {

	/**
	 * 
	 * @deprecated use {@link JavaEEQuickPeek}
	 * 
	 * @param in
	 * @return
	 */
	public static String getFastSpecVersion(InputStream in) {
		JavaEEQuickPeek qp = new JavaEEQuickPeek(in);
		int version = qp.getVersion();
		return J2EEVersionUtil.convertVersionIntToString(version);
	}

}
