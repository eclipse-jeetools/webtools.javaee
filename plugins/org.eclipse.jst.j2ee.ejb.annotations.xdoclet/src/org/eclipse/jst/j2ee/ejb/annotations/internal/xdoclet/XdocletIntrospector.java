/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotations.internal.emitter.DefaultEjbIntrospector;



public class XdocletIntrospector extends DefaultEjbIntrospector {


	

	protected ICompilationUnit compilationUnit;
	protected EnterpriseBean docRoot;
	
	public XdocletIntrospector() {
		super();
		this.compilationUnit = null; 
		this.docRoot = null; 
	}

	
	
	public EnterpriseBean getEnterpriseBean() {
		EnterpriseBean bean = null;
		if( isSessionBean(this.getCompilationUnit())){
			bean = EjbFactory.eINSTANCE.createSession();
		}else if( isMDBBean(this.getCompilationUnit()))
		{
			bean = EjbFactory.eINSTANCE.createMessageDriven();
		}else if( isEntityBean(this.getCompilationUnit()))
		{
			bean = EjbFactory.eINSTANCE.createEntity();
		}

		return bean;

	}

	
	public ICompilationUnit getCompilationUnit() {
		return compilationUnit;
	}
	public void setCompilationUnit(ICompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

}