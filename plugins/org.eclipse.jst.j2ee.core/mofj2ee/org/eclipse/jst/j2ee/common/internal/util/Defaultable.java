/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.util;

import org.eclipse.emf.common.notify.Notifier;
/**
 * Interface used for objects that can be "fluffed up" on demand, but not serialized unless modified
 * @since 1.0 */
public interface Defaultable extends Notifier {
public boolean isDefault();
}


