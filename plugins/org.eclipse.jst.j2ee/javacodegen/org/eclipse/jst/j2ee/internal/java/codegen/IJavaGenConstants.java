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
package org.eclipse.jst.j2ee.internal.java.codegen;



import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;

/**
 * Defines useful Java code generation constants.
 */
public interface IJavaGenConstants extends org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants {
	String VOID_RETURN = "void"; //$NON-NLS-1$
	String START_PARMS = "("; //$NON-NLS-1$
	String END_PARMS = ")"; //$NON-NLS-1$
	String JAVA_FILE_EXTENSION = ".java"; //$NON-NLS-1$
	String JAVADOC_COMMENT_START = "/**" + LINE_SEPARATOR; //$NON-NLS-1$
	String JAVADOC_COMMENT_LINE = " * "; //$NON-NLS-1$
	String JAVADOC_COMMENT_END = " */" + LINE_SEPARATOR; //$NON-NLS-1$
	String MEMBER_CONTENT_START = " {" + LINE_SEPARATOR; //$NON-NLS-1$
	String MEMBER_CONTENT_END = "}" + LINE_SEPARATOR; //$NON-NLS-1$
	/**
	 * A Compilation unit is about to be overwritten during a move/copy operation. This is a
	 * warning.
	 * 
	 * @see AnalysisResult#getReasonCode()
	 */
	int CU_MOVE_COPY_COLLISION_WARNING_CODE = AnalysisResult.MIN_FW_REASON_CODE + 0;
	/**
	 * A member will not be generated because is collides with a non-generated member. This is
	 * informational.
	 * 
	 * @see AnalysisResult#getReasonCode()
	 */
	int MEMBER_COLLISION_INFO_CODE = AnalysisResult.MIN_FW_REASON_CODE + 1;
	/**
	 * A member will not be deleted because it was not generated. This is informational.
	 * 
	 * @see AnalysisResult#getReasonCode()
	 */
	int MEMBER_NO_DELETE_INFO_CODE = AnalysisResult.MIN_FW_REASON_CODE + 2;
}