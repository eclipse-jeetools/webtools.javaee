/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.commands;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jst.j2ee.ejb.Entity;



public class EJBPropagationCommand extends EJBCompoundRootCommand {
	private Map commandMap = new HashMap();

	/**
	 * @param desc
	 */
	public EJBPropagationCommand(String desc) {
		super(desc);
	}

	protected boolean shouldPropagate(Object object) {
		return !commandMap.containsKey(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.AbstractEJBRootCommand#isAdditionalCommand()
	 */
	protected boolean isAdditionalCommand() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.commands.EJBCompoundRootCommand#append(org.eclipse.jst.j2ee.internal.internal.commands.IRootCommand)
	 */
	public IRootCommand append(IRootCommand aCommand) {
		commandMap.put(aCommand.getEjb(), aCommand);
		return super.append(aCommand);
	}

	protected void cachePropagationCommand(IEJBCommand command) {
		commandMap.put(command.getSourceMetaType(), command);
	}

	/**
	 * @param entity
	 * @return
	 */
	protected IRootCommand getRootCommand(Entity entity) {
		return (IRootCommand) commandMap.get(entity);
	}

}