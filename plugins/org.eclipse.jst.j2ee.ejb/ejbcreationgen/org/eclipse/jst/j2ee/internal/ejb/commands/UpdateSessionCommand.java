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
package org.eclipse.jst.j2ee.internal.ejb.commands;



import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (9/12/2000 1:25:51 PM)
 * 
 * @author: Administrator
 */
public class UpdateSessionCommand extends SessionCommand {
	/**
	 * UpdateSessionCommand constructor comment.
	 * 
	 * @param aName
	 *            java.lang.String
	 * @param aFolder
	 *            com.ibm.itp.core.api.resources.IFolder
	 */
	public UpdateSessionCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/**
	 * Insert the method's description here. Creation date: (12/4/2000 1:20:02 PM)
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTaskName() {
		return getUpdatingTaskName();
	}

	/**
	 * Insert the method's description here. Creation date: (10/13/2000 6:12:50 PM)
	 * 
	 * @return boolean
	 */
	public boolean shouldGenerateJava() {
		return shouldGenerateJavaForModify();
	}
}