/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

public class J2EEClipboard extends ArrayList {
	private Map bindings;
	private Map extensions;

	/**
	 * J2EEClipboard constructor comment.
	 */
	public J2EEClipboard(Collection defaultClipboard) {
		super(defaultClipboard);
	}

	public boolean addAll(Collection c) {
		boolean result = super.addAll(c);
		if (result && (c instanceof J2EEClipboard))
			addAllExtra((J2EEClipboard) c);
		return result;
	}

	protected void addAllExtra(J2EEClipboard c) {
		getBindings().putAll(c.getBindings());
		getExtensions().putAll(c.getExtensions());
	}

	protected void addBinding(EObject boundObject, EObject binding) {
		getBindings().put(boundObject, binding);
	}

	protected void addExtension(EObject extendedObject, EObject extension) {
		getExtensions().put(extendedObject, extension);
	}

	public EObject getBinding(EObject o) {
		return (EObject) getBindings().get(o);
	}

	protected Map getBindings() {
		if (bindings == null)
			bindings = new HashMap(10);
		return bindings;
	}

	public EObject getExtension(EObject o) {
		return (EObject) getExtensions().get(o);
	}

	protected Map getExtensions() {
		if (extensions == null)
			extensions = new HashMap(10);
		return extensions;
	}

	public boolean hasBindings() {
		return bindings != null && !bindings.isEmpty();
	}

	public boolean hasExtensions() {
		return extensions != null && !extensions.isEmpty();
	}
}