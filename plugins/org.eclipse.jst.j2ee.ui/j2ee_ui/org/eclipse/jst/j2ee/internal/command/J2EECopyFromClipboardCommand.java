/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.command;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.provider.J2EEUIEditingDomain;
import org.eclipse.wst.common.emf.utilities.CopyGroup;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;


/**
 * Insert the type's description here. Creation date: (06/11/01 8:45:21 AM)
 * 
 * @author: Administrator
 */
public class J2EECopyFromClipboardCommand extends AbstractCommand {
	private J2EEUIEditingDomain domain;
	private J2EEClipboard result;
	private EtoolsCopyUtility copyUtil;

	public J2EECopyFromClipboardCommand(J2EEUIEditingDomain editingDomain) {
		domain = editingDomain;

	}

	/**
	 * This will perform the command activity required for the effect. The effect of calling execute
	 * when canExecute returns false, or when canExecute hasn't been called, is undefined.
	 */
	public void execute() {
		if (copyUtil != null)
			return;
		copyUtil = new EtoolsCopyUtility();
		J2EEClipboard clipboard = domain.getJ2EEClipboard();
		result = new J2EEClipboard(new ArrayList(0));
		for (int i = 0; i < clipboard.size(); i++) {
			CopyGroup group = new CopyGroup();
			EObject o = (EObject) clipboard.get(i);
			group.add(o);
			EObject bnd = clipboard.getBinding(o);
			if (bnd != null)
				group.add(bnd);
			EObject ext = clipboard.getExtension(o);
			if (ext != null)
				group.add(ext);
			copyUtil.copy(group);
			EObject copy = copyUtil.getCopy(o);
			result.add(copy);
			if (bnd != null)
				result.addBinding(copy, copyUtil.getCopy(bnd));
			if (ext != null)
				result.addExtension(copy, copyUtil.getCopy(ext));
		}
		//Reset the util so redo will actuall redo
		copyUtil = null;
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
		execute();
	}

	public void undo() {
		result = null;
	}
}