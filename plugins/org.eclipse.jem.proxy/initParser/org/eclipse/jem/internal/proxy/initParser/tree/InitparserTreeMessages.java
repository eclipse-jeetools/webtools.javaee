/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.initParser.tree;

import org.eclipse.osgi.util.NLS;

public final class InitparserTreeMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.proxy.initParser.tree.messages";//$NON-NLS-1$

	private InitparserTreeMessages() {
		// Do not instantiate
	}

	public static String ExpressionProcesser_CannotCastXToY_EXC_;
	public static String ExpressionProcesser_InvalidOperandOfPrefixOperator_EXC_;
	public static String ExpressionProcesser_PopExpressionType_ExpressionVoid_EXC_;
	public static String ExpressionProcesser_InvalidOperandOfOperator_EXC_;
	public static String ExpressionProcesser_XIsGreaterThanNumberOfDimensionsInArray_EXC_;
	public static String ExpressionProcesser_CreateArrayAccessReference_OutOfBounds_EXC_;
	public static String ExpressionProcesser_PushTryEnd_TryEndReceivedOutOfOrder_EXC_;
	public static String ExpressionProcesser_PushEndmark_EndMarkOnNonExistingID_EXC_;
	public static String ExpressionProcesser_NotAnArray_EXC_;
	public static String ExpressionProcesser_ArraytypeHasFewerDimensionsThanRequested_EXC_;
	public static String ExpressionProcesser_CreateFieldAccessReference_FieldsTypesNotMatching_EXC_;
	public static String ExpressionProcesser_GetExpressionProxyValue_ExpressionProxyNotSet_EXC_;
	public static String ExpressionProcesser_GetExpressionProxyValue_ExpressionProxyDoesntExist_EXC_;
	public static String ExpressionProcesser_PushBlockEnd_ReceivedEndBlocksOutOfOrder_EXC_;
	public static String ExpressionProcesser_PushTryCatchClause_CatchReceivedOutOfOrder_EXC_;
	public static String ExpressionProcesser_PushTryFinallyClause_FinallyReceivedOutOfOrder_EXC_;
	public static String ExpressionProcesser_PushTryRethrow_RethrowReceivedOutOfOrder_EXC_;
	public static String ExpressionProcesser_PushTryRethrow_RetryReceivedOutOfExecutingCatchClause_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, InitparserTreeMessages.class);
	}
}