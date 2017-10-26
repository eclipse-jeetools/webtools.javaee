/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveHandler;

public class WebComponentArchiveTypeHandler implements IArchiveHandler {

	@Override
	public boolean handlesArchive(IArchive archive) {
		if (archive.getLoadAdapter() instanceof WebComponentArchiveLoadAdapter) {
			return true;
		} else if (archive.containsArchiveResource(new Path(J2EEConstants.WEBAPP_DD_URI))) {
			return true;
		} else {
			// TODO handle the no DD case
			return false;
		}
	}

}
