/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 25, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.common.jdt.internal.integration.ui;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jst.common.jdt.internal.integration.JavaInsertionHelper;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;
import org.eclipse.ui.IEditorInput;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;

/**
 * @author DABERG
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
public class JavaInsertionOperation implements IHeadlessRunnableWithProgress {
	private static final String NEW_LINE = System.getProperty("line.separator"); //$NON-NLS-1$
	protected JavaInsertionHelper insertionHelper;
	protected IEditorInput editorInput;
	protected IDocument document;
	protected ITextSelection textSelection;
	protected IProgressMonitor monitor;

	/**
	 *  
	 */
	public JavaInsertionOperation(JavaInsertionHelper insertionHelper, IEditorInput editorInput, IDocument document, ITextSelection textSelection) {
		super();
		this.insertionHelper = insertionHelper;
		this.editorInput = editorInput;
		this.document = document;
		this.textSelection = textSelection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.IHeadlessRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitorArg) throws InvocationTargetException, InterruptedException {
		this.monitor = monitorArg;
		insertCodeSnippet();
		processJavaHelper();
	}

	/**
	 *  
	 */
	protected void insertCodeSnippet() throws InvocationTargetException {
		String replacementString = insertionHelper.getInsertionString();
		if (replacementString == null || replacementString.length() == 0)
			return;
		if (textSelection == null)
			throw new RuntimeException("No text selection for inserting text."); //$NON-NLS-1$
		try {
			document.replace(textSelection.getOffset(), textSelection.getLength(), replacementString);
		} catch (BadLocationException e) {
			throw new InvocationTargetException(e);
		}
		int start = textSelection.getStartLine();
		int lines = document.computeNumberOfLines(replacementString) + 1;
		format(document, start, lines, 0);
	}

	protected void processJavaHelper() {
		ICompilationUnit cu = JavaUI.getWorkingCopyManager().getWorkingCopy(editorInput);
		if (cu != null) {
			IType type = null;
			try {
				type = cu.getTypes()[0];
			} catch (JavaModelException e) {
				//Ignore
			}
			if (type != null) {
				if (insertionHelper.hasFields())
					createFields(type, insertionHelper.getFields());
				if (insertionHelper.hasMethods())
					createMethods(type, insertionHelper.getMethods());
				if (insertionHelper.hasImports())
					createImports(cu, insertionHelper.getImportStatements());
				executeExtendedOperations();
			}
		}
	}

	/**
	 *  
	 */
	private void executeExtendedOperations() {
		List ops = insertionHelper.getExtendedOperations();
		if (ops != null) {
			for (int i = 0; i < ops.size(); i++)
				executedExtendedOperation((IHeadlessRunnableWithProgress) ops.get(i));
		}
	}

	/**
	 * @param operation
	 */
	private void executedExtendedOperation(IHeadlessRunnableWithProgress operation) {
		try {
			operation.run(null);
		} catch (Exception e) {
			Logger log = Logger.getLogger();
			log.log("Executing extended operation failed:  " + operation); //$NON-NLS-1$
			log.log(e);
		}
	}

	protected void createFields(IType aType, List fields) {
		IDOMField field;
		for (int i = 0; i < fields.size(); i++) {
			field = (IDOMField) fields.get(i);
			if (!aType.getField(field.getName()).exists()) {
				try {
					aType.createField(format(field.getContents(), 1, true), null, true, null);
				} catch (JavaModelException e) {
					Logger.getLogger().logError(e);
				}
			}
		}
	}

	/**
	 * @param wc
	 */
	protected void createMethods(IType aType, List methods) {
		IDOMMethod method;
		for (int i = 0; i < methods.size(); i++) {
			method = (IDOMMethod) methods.get(i);
			if (!aType.getMethod(method.getName(), getParamaterTypeSignatures(method)).exists()) {
				try {
					aType.createMethod(format(method.getContents(), 1, true), null, true, null);
				} catch (JavaModelException e) {
					Logger.getLogger().logError(e);
				}
			}
		}
	}

	protected void createImports(ICompilationUnit cu, List imports) {
		String importStmt;
		for (int i = 0; i < imports.size(); i++) {
			importStmt = (String) imports.get(i);
			if (!cu.getImport(importStmt).exists() && !importStmt.startsWith("java.lang")) { //$NON-NLS-1$
				try {
					cu.createImport(importStmt, null, null);
				} catch (JavaModelException e) {
					Logger.getLogger().logError(e);
				}
			}
		}
	}

	protected String format(String contents, int indent, boolean ensureEndLineReturn) {
		Document doc = new Document(contents);
		int lines = doc.getNumberOfLines();
		format(doc, 0, lines - 1, indent);
		String result = doc.get();
		if (ensureEndLineReturn)
			result = ensureLineReturn(result);
		return result;
	}

	protected void format(IDocument documentArg, int startLine, int lines, int indent) {
		try {
			int end = documentArg.getLineOffset(startLine + lines);
			int length = end - startLine;
			CodeFormatter formatter = ToolFactory.createCodeFormatter(null);
			TextEdit edit = formatter.format(CodeFormatter.K_UNKNOWN, documentArg.get(), startLine, length, indent, null);
			if (edit != null) {
				try {
					edit.apply(documentArg);
				} catch (MalformedTreeException e) {
					//Ignore
				}
			}
		} catch (BadLocationException e) {
			Logger log = Logger.getLogger();
			log.log("Failed to format text."); //$NON-NLS-1$
			log.log(e);
		}
	}

	protected String formatString(String pattern, String[] arguments) {
		return MessageFormat.format(pattern, arguments);
	}

	/**
	 * @param result
	 * @return
	 */
	protected String ensureLineReturn(String aString) {
		if (!aString.endsWith(NEW_LINE))
			return aString + NEW_LINE;
		return aString;
	}

	protected String[] getParamaterTypeSignatures(IDOMMethod aMethod) {
		String[] result = null;
		String[] parms = aMethod.getParameterTypes();
		if (parms != null) {
			if (parms.length == 0)
				result = parms;
			else
				result = new String[parms.length];
			boolean isResolved = false;
			String parm;
			for (int i = 0; i < parms.length; i++) {
				parm = parms[i];
				isResolved = parm.indexOf('.') > 0;
				result[i] = Signature.createTypeSignature(parm, isResolved);
			}
		}
		return result;
	}
}
