/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations;

import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperationDataModel;

/**
 * @author naci
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface IEnterpriseBeanDelegate {

	public EnterpriseBean getEjb();
	public WTPOperationDataModel getDataModel();

	public String getTransactionType();
	public String getDisplayName();
	public String getDescription();
	public String getJndiName();
	public String getInterfaces();
	public String getSimpleClassName();

}
