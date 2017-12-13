/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotation.internal.model;

import java.util.List;

/**
 * @author naci
 */
public interface IContainerManagedEntityBean extends IEnterpriseBean {

	public String getDatasource();

	public String getSchema();

	public String getTable();

	public List getAttributes();

	public String getPrimaryKeyClass();

	public String getVersion();

	public List getKeyAttributes();
}
