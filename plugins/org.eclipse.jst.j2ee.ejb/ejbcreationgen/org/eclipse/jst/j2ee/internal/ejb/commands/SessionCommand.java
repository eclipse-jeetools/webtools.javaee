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
import org.eclipse.jst.j2ee.ejb.Session;
import org.eclipse.jst.j2ee.ejb.SessionType;
import org.eclipse.jst.j2ee.ejb.TransactionType;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;


/**
 * Insert the type's description here. Creation date: (9/12/2000 1:23:11 PM)
 * 
 * @author: Administrator
 */
public abstract class SessionCommand extends EnterpriseBeanCommand {
	private boolean isStatefull;
	private boolean isContainerManaged;

	/**
	 * SessionCommand constructor comment.
	 */
	public SessionCommand(EnterpriseBean anEjb, EJBEditModel anEditModel) {
		super(anEjb, anEditModel);
	}

	/**
	 * Constructor method for an SessionCommand.
	 * 
	 * @param java.lang.String
	 *            aName
	 * @param java.lang.String
	 *            aFolderName
	 * 
	 * aName - The name and ID of the EJB. aProject - The project in which to create the folder.
	 */
	public SessionCommand(String aName, EJBEditModel anEditModel) {
		super(aName, anEditModel);
	}

	/**
	 * Insert the method's description here. Creation date: (9/6/2000 10:01:03 AM)
	 */
	protected EnterpriseBeanCodegenCommand createCodegenCommand() {
		return new SessionCodegenCommand(getSession());
	}

	/**
	 * createEJB method comment.
	 */
	protected EnterpriseBean createEJB() {
		Session session = getEJBFactory().createSession();
		session.setName(getName());
		getEditModel().getEjbXmiResource().setID(session, getName());
		return session;
	}

	protected SessionType getSessionType() {
		if (isStatefull)
			return SessionType.STATEFUL_LITERAL;
		return SessionType.STATELESS_LITERAL;
	}

	public void setIsStateful(boolean aBool) {

		isStatefull = aBool;
	}

	public void setIsContainerManaged(boolean aBool) {

		isContainerManaged = aBool;
	}

	protected TransactionType getTransactionType() {
		if (isContainerManaged)
			return TransactionType.CONTAINER_LITERAL;
		return TransactionType.BEAN_LITERAL;
	}


	protected Session getSession() {
		return (Session) getEjb();
	}
}