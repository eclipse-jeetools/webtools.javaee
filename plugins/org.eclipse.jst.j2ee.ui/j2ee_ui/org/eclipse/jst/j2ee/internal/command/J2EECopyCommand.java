/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.command;


import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.wst.common.emf.utilities.CopyGroup;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


public class J2EECopyCommand extends AbstractCommand {
	protected EObject objectToCopy;
	protected EObject bindingToCopy;
	protected EObject extensionToCopy;
	protected J2EEClipboard result;
	protected EtoolsCopyUtility copyUtil;

	public J2EECopyCommand(EObject object, EObject binding, EObject extension, EtoolsCopyUtility copyUtility) {
		objectToCopy = object;
		bindingToCopy = binding;
		extensionToCopy = extension;
		copyUtil = copyUtility;
	}

	/**
	 * This will perform the command activity required for the effect. The effect of calling execute
	 * when canExecute returns false, or when canExecute hasn't been called, is undefined.
	 */
	public void execute() {
		CopyGroup group = new CopyGroup();

		group.add(objectToCopy);

		if (bindingToCopy != null)
			group.add(bindingToCopy);
		if (extensionToCopy != null)
			group.add(extensionToCopy);
		copyUtil.copy(group);
		EObject copy = copyUtil.getCopy(objectToCopy);
		result = new J2EEClipboard(Collections.singleton(copy));

		if (bindingToCopy != null)
			result.addBinding(copy, copyUtil.getCopy(bindingToCopy));
		if (extensionToCopy != null)
			result.addExtension(copy, copyUtil.getCopy(extensionToCopy));
	}

	public Collection getAffectedObjects() {
		return result;
	}

	public Collection getResult() {
		return result;
	}

	protected boolean prepare() {
		return true;
	}

	/**
	 * This will again perform the command activity required to redo the effect after undoing the
	 * effect. The effect, if any, of calling redo before undo is called is undefined. Note that if
	 * you implement redo to call execute then any derived class will be restricted to by that
	 * decision also.
	 */
	public void redo() {
		//redo
	}
}