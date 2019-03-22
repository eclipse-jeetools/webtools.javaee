/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator.ejb;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jst.jee.ui.internal.navigator.AbstractDDNode;
import org.eclipse.swt.graphics.Image;

/**
 * Deployment Descriptor node that contains information about Session or Entity
 * beans interfaces (Business, Component and Local interfaces).
 * 
 * @author Dimitar Giormov
 */
public class BeanInterfaceNode extends AbstractDDNode {

	private final int kind;
	private final String _fqn;

	public class KINDS {
		public static final int BUSSINESS_LOCAL = 0;
		public static final int BUSSINESS_REMOTE = 1;
		public static final int LOCAL = 2;
		public static final int LOCAL_HOME = 3;
		public static final int REMOTE = 4;
		public static final int REMOTE_HOME = 5;
	}

	public BeanInterfaceNode(Object adapterNode, String _fqn, int kind) {
		super(adapterNode);
		this._fqn = _fqn;
		this.kind = kind;
	}

	@Override
	public String getText() {
		return Signature.getSimpleName(_fqn);
	}

	@Override
	public List getChildren() {
		return new ArrayList();
	}

	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public Image getImage() {
		return JavaPluginImages.get(JavaPluginImages.IMG_OBJS_CUNIT);
	}

	public String get_fqn() {
		return _fqn;
	}

	public int getKind() {
		return kind;
	}

}
