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
package org.eclipse.jst.j2ee.internal.codegen;



import java.util.List;

/**
 * This is a formatting helper interface for code generation.
 */
public interface IGenerationBuffer {
	/**
	 * Appends the specified String[] to the buffer as a comma separated list.
	 */
	void append(String[] strings);

	/**
	 * Appends the specified String to the buffer.
	 */
	void append(String aString);

	/**
	 * Appends the specified List to the buffer as a comma separated list. The toString() method is
	 * used on each object.
	 */
	void append(List strings);

	/**
	 * Appends the specified String to the buffer and expands line breaks to line break plus current
	 * margin. It also starts with a margin.
	 */
	void appendWithMargin(String aString);

	/**
	 * Appends the specified String to the buffer and expands line breaks to line break plus current
	 * margin. It starts with a margin if startMargin is true.
	 */
	void appendWithMargin(String aString, boolean startMargin);

	/**
	 * Appends the specified String to the buffer and expands line breaks to line break plus current
	 * margin plus prefix. It starts with a margin and prefix.
	 */
	void appendWithMarginAndPrefix(String aString, String prefix);

	/**
	 * Appends the specified String to the buffer and expands line breaks to line break plus current
	 * margin plus prefix. It starts with a margin and prefix if startMargin is true.
	 */
	void appendWithMarginAndPrefix(String aString, String prefix, boolean startMargin);

	/**
	 * Appends the format string to the buffer while substituting the parms for markers in the
	 * format string. For example: <code>
	 * <br>&nbsp;&nbsp;&nbsp;String[] parms = {"foo", "public"};
	 * <br>&nbsp;&nbsp;&nbsp;genBuf.format("The field %0 should be %1.", parms);
	 * </code><br>
	 * would add "The field foo should be public." to the buffer.
	 */
	void format(String format, String[] parms);

	/**
	 * Appends the format string to the buffer while substituting the parms in for markers in the
	 * format string. The toString() method is called on the parms. For example: <code>
	 * <br>&nbsp;&nbsp;&nbsp;List parms = new ArrayList();
	 * <br>&nbsp;&nbsp;&nbsp;parms.add("foo");
	 * <br>&nbsp;&nbsp;&nbsp;parms.add("public");
	 * <br>&nbsp;&nbsp;&nbsp;genBuf.format("The field %0 should be %1.", parms);
	 * </code> would add "The
	 * field foo should be public." to the buffer.
	 */
	void format(String format, List parms);

	/**
	 * Formats as in the {@link IGenerationBuffer#format(String, String[])}method. It also expands
	 * line breaks to line break plus current margin. It also starts with a margin.
	 */
	void formatWithMargin(String format, String[] parms);

	/**
	 * Formats as in the {@link IGenerationBuffer#format(String, String[])}method. It also expands
	 * line breaks to line break plus current margin. It starts with a margin if startMargin is
	 * true.
	 */
	void formatWithMargin(String format, String[] parms, boolean startMargin);

	/**
	 * Formats as in the {@link IGenerationBuffer#format(String, List)}method. It also expands line
	 * breaks to line break plus current margin. It also starts with a margin.
	 */
	void formatWithMargin(String format, List parms);

	/**
	 * Formats as in the {@link IGenerationBuffer#format(String, List)}method. It also expands line
	 * breaks to line break plus current margin. It starts with a margin if startMargin is true.
	 */
	void formatWithMargin(String format, List parms, boolean startMargin);

	/**
	 * Returns the character used to start parameter insert points on the format methods.
	 */
	char getFormatParmMarker();

	/**
	 * Increments the indent count.
	 * 
	 * @see IGenerationBuffer#margin()
	 */
	void indent();

	/**
	 * Appends the current margin to the buffer. The current margin is the current indent string
	 * appended for the current indent count. The buffer starts with no margin (indent count = 0)
	 * and an indent string of one tab. Calling {@link IGenerationBuffer#indent()}once would set
	 * the indent count to one and the margin would become one tab. Calling
	 * {@link IGenerationBuffer#indent()}again would result in the margin becoming two tabs. Calls
	 * to {@link IGenerationBuffer#unindent()}is used to decrement the indent count.
	 */
	void margin();

	/**
	 * Sets the character used to start parameter insert points on the format methods.
	 */
	void setFormatParmMarker(char newFormatParmMarker);

	/**
	 * Sets the indent count to an explicit value.
	 * 
	 * @see IGenerationBuffer#margin()
	 */
	void setIndent(int indentCount);

	/**
	 * Decrements the indent count.
	 * 
	 * @see IGenerationBuffer#margin()
	 */
	void unindent();
}