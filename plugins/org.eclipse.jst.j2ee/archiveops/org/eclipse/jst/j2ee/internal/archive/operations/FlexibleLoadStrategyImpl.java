/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.LoadStrategyImpl;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public abstract class FlexibleLoadStrategyImpl extends LoadStrategyImpl {

	protected IVirtualComponent vComponent;
	protected boolean includeSource;
	protected ArrayList filesList;
	
	public FlexibleLoadStrategyImpl(IVirtualComponent vComponent){
		this.vComponent = vComponent;
		filesList = new ArrayList();
	}
	
	protected boolean primContains(String uri) {
		// TODO Auto-generated method stub
		return false;
	}

	public List getFiles() {
		filesList.clear();
		try {
			IVirtualResource [] members = vComponent.members();
			for(int i=0;i<members.length; i++){
				filesList.add(Arrays.asList(members[i].getUnderlyingResources()));
			}
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getInputStream(String uri) throws IOException, FileNotFoundException {
		return null;
	}

}
