/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb20.commands;


import org.eclipse.jst.j2ee.ejb.MessageDriven;
import org.eclipse.jst.j2ee.internal.ejb.codegen.helpers.EnterpriseBeanHelper;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCodegenCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.EnterpriseBeanCommand;
import org.eclipse.jst.j2ee.internal.ejb20.codegen.IEJB20GenConstants;
import org.eclipse.jst.j2ee.internal.java.codegen.JavaTopLevelGenerationHelper;


public class MessageDrivenCodegenCommand extends EnterpriseBeanCodegenCommand {

	/**
	 * Constructor for MessageDrivenBeanCodegenCommand.
	 */
	public MessageDrivenCodegenCommand() {
		super();
	}

	/**
	 * Constructor for MessageDrivenBeanCodegenCommand.
	 * 
	 * @param anEJB
	 */
	public MessageDrivenCodegenCommand(MessageDriven anMDB) {
		super(anMDB);
	}

	/*
	 * @see EnterpriseBeanCodegenCommand#getGeneratorName()
	 */
	protected String getGeneratorName() {
		return IEJB20GenConstants.MDB_GENERATOR_NAME;
	}

	protected String getGeneratorDictionaryName() {
		return IEJB20GenConstants.DICTIONARY_NAME;
	}

	protected JavaTopLevelGenerationHelper createCodegenHelper() {
		EnterpriseBeanHelper ejbHelper = new EnterpriseBeanHelper(getEjb());
		EnterpriseBeanCommand root = getEnterpriseBeanCommand();
		if (root != null && root.isCreateCommand())
			ejbHelper.setIsCreate(true);
		return ejbHelper;
	}

}