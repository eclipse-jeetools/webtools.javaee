/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.classgen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.rewrite.ImportRewrite;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.corext.codemanipulation.AddUnimplementedConstructorsOperation;
import org.eclipse.jdt.internal.corext.codemanipulation.AddUnimplementedMethodsOperation;
import org.eclipse.jdt.internal.corext.dom.NodeFinder;
import org.eclipse.jdt.ui.CodeGeneration;
import org.eclipse.jdt.ui.CodeStyleConfiguration;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

public class EjbBuilder {

	protected IProgressMonitor monitor;

	protected IPackageFragmentRoot packageFragmentRoot;

	protected String packageName = "";

	protected String typeName = "";

	protected String typeStub;

	protected String typeComment;

	protected String methodStub;

	protected String fields;

	protected boolean createInheritedMethods;

	protected boolean createInheritedConstructors;

	protected IConfigurationElement configurationElement;

	IType fCreatedType;

	public EjbBuilder() {
		super();
	}

	/**
	 * Creates the new type using the entered field values.
	 * 
	 * @param monitor
	 *            a progress monitor to report progress.
	 */
	public void createType() throws CoreException, InterruptedException {
		monitor = getMonitor();

		monitor.beginTask("Creating a new type", 10);

		ICompilationUnit createdWorkingCopy = null;
		try {
			IPackageFragmentRoot root = getPackageFragmentRoot(); // Current
			// source
			// folder
			IPackageFragment pack = root.createPackageFragment(this.packageName, true, monitor); // Package
			// that
			// contains
			// the class
			monitor.worked(1);

			String clName = this.typeName;

			IType createdType;
			ImportsManager imports;
			int indent = 0;

			String lineDelimiter = null;
			lineDelimiter = System.getProperty("line.separator", "\n"); //$NON-NLS-1$ //$NON-NLS-2$

			ICompilationUnit parentCU = pack.getCompilationUnit(clName + ".java");
			if (!parentCU.exists())
				parentCU = pack.createCompilationUnit(clName + ".java", "", false, new SubProgressMonitor(monitor, 2)); //$NON-NLS-1$ //$NON-NLS-2$

			// create a working copy with a new owner
			createdWorkingCopy = parentCU.getWorkingCopy(null);

			// use the compiler template a first time to read the imports
			String content = CodeGeneration.getCompilationUnitContent(createdWorkingCopy, null, "", lineDelimiter); //$NON-NLS-1$
			if (content != null) {
				createdWorkingCopy.getBuffer().setContents(content);
			}

			imports = new ImportsManager(createdWorkingCopy);

			String cuContent = content + lineDelimiter + typeComment + lineDelimiter + typeStub;
			createdWorkingCopy.getBuffer().setContents(cuContent);
			createdType = createdWorkingCopy.getType(clName);

			// add imports for superclass/interfaces, so types can be resolved
			// correctly
			ICompilationUnit cu = createdType.getCompilationUnit();
			imports.create(cu, true);

			cu.reconcile(ICompilationUnit.NO_AST, false, null, null);

			createTypeMembers(createdType, imports, new SubProgressMonitor(monitor, 1));

			createInheritedMethods(createdType, isCreateInheritedConstructors(), isCreateInheritedMethods(), imports,
					new SubProgressMonitor(monitor, 1));
			// add imports
			imports.create(cu, true);

			if (removeUnused(cu, imports)) {
				imports.create(cu, true);
			}

			cu.reconcile(ICompilationUnit.NO_AST, false, null, null);

			ISourceRange range = createdType.getSourceRange();

			IBuffer buf = cu.getBuffer();
			String originalContent = buf.getText(range.getOffset(), range.getLength());
			CodeFormatter codeFormatter = ToolFactory.createCodeFormatter(JavaCore.getOptions());
			TextEdit textEdit = codeFormatter.format(CodeFormatter.K_CLASS_BODY_DECLARATIONS, originalContent, 0, originalContent
					.length(), indent, lineDelimiter);
			IDocument document = new Document(originalContent);
			try {
				textEdit.apply(document);
			} catch (Exception e) {
			}
			String formattedContent = document.get();

			buf.replace(range.getOffset(), range.getLength(), formattedContent);
			cu.getBuffer().setContents(buf.getContents());
			cu.commitWorkingCopy(false, new SubProgressMonitor(monitor, 1));

			fCreatedType = (IType) createdType.getPrimaryElement();

		} finally {
			if (createdWorkingCopy != null) {
				createdWorkingCopy.discardWorkingCopy();
			}
			monitor.done();
		}
	}

	/**
	 * @param createdType
	 * @param imports
	 * @param monitor2
	 */
	private void createTypeMembers(IType createdType, ImportsManager imports, SubProgressMonitor monitor2) throws JavaModelException {
		if (fields != null && fields.length() > 0)
			createdType.createField(fields, null, false, this.getMonitor());
		if (methodStub != null && methodStub.length() > 0)
			createdType.createMethod(methodStub, null, false, this.getMonitor());
	}

	/**
	 * Creates the bodies of all unimplemented methods and constructors and adds
	 * them to the type. Method is typically called by implementers of
	 * <code>NewTypeWizardPage</code> to add needed method and constructors.
	 * 
	 * @param type
	 *            the type for which the new methods and constructor are to be
	 *            created
	 * @param doConstructors
	 *            if <code>true</code> unimplemented constructors are created
	 * @param doUnimplementedMethods
	 *            if <code>true</code> unimplemented methods are created
	 * @param imports
	 *            an import manager to add all needed import statements
	 * @param monitor
	 *            a progress monitor to report progress
	 * @return the created methods.
	 * @throws CoreException
	 *             thrown when the creation fails.
	 */
	protected IMethod[] createInheritedMethods(IType type, boolean doConstructors, boolean doUnimplementedMethods,
			ImportsManager imports, IProgressMonitor monitor) throws CoreException {
		final ICompilationUnit cu = type.getCompilationUnit();
		cu.reconcile(ICompilationUnit.NO_AST, false, null, null);
		IMethod[] typeMethods = type.getMethods();
		Set handleIds = new HashSet(typeMethods.length);
		for (int index = 0; index < typeMethods.length; index++)
			handleIds.add(typeMethods[index].getHandleIdentifier());
		ArrayList newMethods = new ArrayList();
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setResolveBindings(true);
		parser.setSource(cu);
		CompilationUnit unit = (CompilationUnit) parser.createAST(new SubProgressMonitor(monitor, 1));
		ASTNode node = NodeFinder.perform(unit, type.getNameRange());
		do {
			node = node.getParent();
		} while (node != null && !AbstractTypeDeclaration.class.isInstance(node));

		final AbstractTypeDeclaration declaration = (AbstractTypeDeclaration) node;
		ITypeBinding binding = null;
		if (declaration != null)
			binding = declaration.resolveBinding();

		if (binding != null) {
			if (doUnimplementedMethods) {
				AddUnimplementedMethodsOperation operation = new AddUnimplementedMethodsOperation(unit, binding, null, -1, false,
						true, false);
				operation.setCreateComments(true);
				operation.run(monitor);
				createImports(imports, operation.getCreatedImports());
			}
			if (doConstructors) {	
				AddUnimplementedConstructorsOperation operation = new AddUnimplementedConstructorsOperation(unit, binding, null, -1,
						false, true, false);
				operation.setOmitSuper(true);
				operation.setCreateComments(true);
				operation.run(monitor);
				createImports(imports, operation.getCreatedImports());
			}
		}
		cu.reconcile(ICompilationUnit.NO_AST, false, null, null);
		typeMethods = type.getMethods();
		for (int index = 0; index < typeMethods.length; index++)
			if (!handleIds.contains(typeMethods[index].getHandleIdentifier()))
				newMethods.add(typeMethods[index]);
		IMethod[] methods = new IMethod[newMethods.size()];
		newMethods.toArray(methods);
		return methods;
	}

	private void createImports(ImportsManager imports, String[] createdImports) {
		for (int index = 0; index < createdImports.length; index++)
			imports.addImport(createdImports[index]);
	}

	/**
	 * @return
	 */
	private IPackageFragmentRoot getPackageFragmentRoot() {
		return packageFragmentRoot;
	}

	private boolean removeUnused(ICompilationUnit cu, ImportsManager imports) {
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setSource(cu);
		parser.setResolveBindings(true);
		CompilationUnit root = (CompilationUnit) parser.createAST(null);
		IProblem[] problems = root.getProblems();
		boolean importRemoved = false;
		for (int i = 0; i < problems.length; i++) {
			if (problems[i].getID() == IProblem.UnusedImport) {
				String imp = problems[i].getArguments()[0];
				imports.removeImport(imp);
				importRemoved = true;
			}
		}
		return importRemoved;
	}

	/**
	 * @return Returns the monitor.
	 */
	public IProgressMonitor getMonitor() {
		if (monitor == null) {
			monitor = new NullProgressMonitor();
		}
		return monitor;
	}

	/**
	 * @param monitor
	 *            The monitor to set.
	 */
	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public static class ImportsManager {

		private ImportRewrite fImportsStructure;

		private HashSet fAddedTypes;

		ImportsManager(ICompilationUnit createdWorkingCopy) throws CoreException {
			fAddedTypes = new HashSet();
			fImportsStructure = CodeStyleConfiguration.createImportRewrite(createdWorkingCopy, true);
		}

		/* package */ImportRewrite getImportsStructure() {
			return fImportsStructure;
		}

		public String addImport(String qualifiedTypeName) {
			fAddedTypes.add(qualifiedTypeName);
			return fImportsStructure.addImport(qualifiedTypeName);
		}

		void create(ICompilationUnit cu, boolean toRestore) throws CoreException {
			ImportRewrite.create(cu, toRestore);
			TextEdit edit = fImportsStructure.rewriteImports(new NullProgressMonitor());
			IDocument document = new Document(cu.getSource());
			try {
				edit.apply(document);
			} catch (Exception e) {
			}
			String imports = document.get();
			cu.getBuffer().setContents(imports);
			cu.commitWorkingCopy(false, new NullProgressMonitor());
		}

		void removeImport(String qualifiedName) {
			if (fAddedTypes.contains(qualifiedName)) {
				fImportsStructure.removeImport(qualifiedName);
			}
		}

	}

	/**
	 * @return Returns the fCreatedType.
	 */
	public IType getCreatedType() {
		return fCreatedType;
	}

	/**
	 * @param createdType
	 *            The fCreatedType to set.
	 */
	public void setCreatedType(IType createdType) {
		fCreatedType = createdType;
	}

	/**
	 * @return Returns the methodStub.
	 */
	public String getMethodStub() {
		return methodStub;
	}

	/**
	 * @param methodStub
	 *            The methodStub to set.
	 */
	public void setMethodStub(String methodStub) {
		this.methodStub = methodStub;
	}

	/**
	 * @return Returns the packageName.
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName
	 *            The packageName to set.
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return Returns the typeComment.
	 */
	public String getTypeComment() {
		return typeComment;
	}

	/**
	 * @param typeComment
	 *            The typeComment to set.
	 */
	public void setTypeComment(String typeComment) {
		this.typeComment = typeComment;
	}

	/**
	 * @return Returns the typeName.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 *            The typeName to set.
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return Returns the typeStub.
	 */
	public String getTypeStub() {
		return typeStub;
	}

	/**
	 * @param typeStub
	 *            The typeStub to set.
	 */
	public void setTypeStub(String typeStub) {
		this.typeStub = typeStub;
	}

	/**
	 * @param packageFragmentRoot
	 *            The packageFragmentRoot to set.
	 */
	public void setPackageFragmentRoot(IPackageFragmentRoot packageFragmentRoot) {
		this.packageFragmentRoot = packageFragmentRoot;
	}

	/**
	 * @return Returns the fields.
	 */
	public String getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            The fields to set.
	 */
	public void setFields(String fields) {
		this.fields = fields;
	}

	/**
	 * @return Returns the configurationElement.
	 */
	public IConfigurationElement getConfigurationElement() {
		return configurationElement;
	}

	/**
	 * @param configurationElement
	 *            The configurationElement to set.
	 */
	public void setConfigurationElement(IConfigurationElement configurationElement) {
		this.configurationElement = configurationElement;
	}

	public boolean isCreateInheritedConstructors() {
		return createInheritedConstructors;
	}

	public void setCreateInheritedConstructors(boolean createInheritedConstructors) {
		this.createInheritedConstructors = createInheritedConstructors;
	}

	public boolean isCreateInheritedMethods() {
		return createInheritedMethods;
	}

	public void setCreateInheritedMethods(boolean createInheritedMethods) {
		this.createInheritedMethods = createInheritedMethods;
	}

}
