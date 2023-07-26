/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.navigator.internal;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class NonConflictingRule implements ISchedulingRule {
	
	public static final NonConflictingRule INSTANCE = new NonConflictingRule();

	@Override
	public boolean contains(ISchedulingRule rule) { 
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) { 
		return rule == this;
	}

}
