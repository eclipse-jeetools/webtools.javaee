/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy;
/*
 *  $RCSfile: ExpressionThreadTransferCallBack.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/16 19:11:16 $ 
 */

import java.io.InputStream;

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.initParser.tree.ForExpression;
import org.eclipse.jem.internal.proxy.initParser.tree.InfixOperator;
/**
 * This is the callback for the expression test thread transfer. This runs on the IDE side.
 */
public class ExpressionThreadTransferCallBack implements ICallback {
	
	Expression expression;
	public Throwable error;
	public ExpressionProxy ep;
		
	public ExpressionThreadTransferCallBack(Expression expression) {
		this.expression = expression;
	}
	
	public Object calledBack(int msgID, IBeanProxy parm){
		return null;
	}
	
	public Object calledBack(int msgID, Object parm) {
		try {
			expression.transferThread();
			// The test here is for equality.
			
			ep = expression.createProxyAssignmentExpression(ForExpression.ROOTEXPRESSION);
			expression.createInfixExpression(ForExpression.ASSIGNMENT_RIGHT, InfixOperator.IN_EQUALS, 0);
			expression.createTypeLiteral(ForExpression.INFIX_LEFT, "java.lang.String");
			expression.createTypeLiteral(ForExpression.INFIX_RIGHT, "java.lang.String");
			
			
		} catch (RuntimeException e) {
			error = e;
		} finally {
			if (expression.isValid())
				try {
					expression.beginTransferThread();	// Set to return expression to other thread.
				} catch (IllegalStateException e) {
					error = e;
				} catch (ThrowableProxy e) {
					error = e;
				}	
		}		
		return null;
	}
	
	public Object calledBack(int msgID, Object[] parms){
		return null;
	}
	
	public void calledBackStream(int msgID, InputStream in){
	}	
}
