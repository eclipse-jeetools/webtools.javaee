/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: NoASTResolver.java,v $
 *  $Revision: 1.1 $  $Date: 2004/05/18 18:15:21 $ 
 */
package org.eclipse.jem.workbench.utility;

import org.eclipse.jdt.core.dom.Name;

import org.eclipse.jem.internal.instantiation.InstantiationFactory;
import org.eclipse.jem.internal.instantiation.PTExpression;
import org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST.InvalidExpressionException;
 

/**
 * This is used for AST Resolution, but it simply turns Name into PTName. Useful when
 * just creating a parse tree where we know the names are ok and just types. 
 * @since 1.0.0
 */
public class NoASTResolver extends ASTBoundResolver {

	/* (non-Javadoc)
	 * @see org.eclipse.jem.workbench.utility.ParseTreeCreationFromAST.Resolver#resolveName(org.eclipse.jdt.core.dom.Name)
	 */
	public PTExpression resolveName(Name name) throws InvalidExpressionException {
		return InstantiationFactory.eINSTANCE.createPTName(name.toString());
	}

}
