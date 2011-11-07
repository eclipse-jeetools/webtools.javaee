/***********************************************************************
 * Copyright (c) 2011 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.List;

import org.eclipse.jst.j2ee.navigator.internal.LoadingDDNode;
import org.eclipse.swt.graphics.Image;

public class LoadingGroupProvider extends AbstractGroupProvider {

	private final LoadingDDNode placeHolder;

	public LoadingDDNode getPlaceHolder() {
		return placeHolder;
	}

	public LoadingGroupProvider(LoadingDDNode placeHolder) {
		super(null);
		this.placeHolder = placeHolder;
	}
	
	@Override
	public boolean hasChildren() {
		return false;
	}

	@Override
	public List getChildren() {
		return children;
	}

	@Override
	public Image getImage() {
		return placeHolder.getImage();
	}
	
	@Override
	public String getText() {
		return placeHolder.getText();
	}

	public void dispose() {
		placeHolder.dispose();
		
	}

}
