/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 17, 2005
 */
package org.eclipse.jst.j2ee.internal;

import java.util.List;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * WARNING: This class will be deleted
 * 
 * @deprecated use {@link org.eclipse.wst.common.frameworks.internal.ui.WorkspaceModifyComposedOperation}
 */
public class WorkspaceModifyComposedOperation extends org.eclipse.wst.common.frameworks.internal.ui.WorkspaceModifyComposedOperation {
	public WorkspaceModifyComposedOperation(ISchedulingRule rule) {
		super(rule);
	}

	public WorkspaceModifyComposedOperation() {
		super();
	}

	public WorkspaceModifyComposedOperation(ISchedulingRule rule, List nestedRunnablesWithProgress) {
		super(rule, nestedRunnablesWithProgress);
	}

	public WorkspaceModifyComposedOperation(List nestedRunnablesWithProgress) {
		super(nestedRunnablesWithProgress);
	}

	public WorkspaceModifyComposedOperation(IRunnableWithProgress nestedOp) {
		super(nestedOp);
	}

}