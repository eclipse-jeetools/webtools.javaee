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



import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jem.internal.adapters.jdom.JDOMSearchHelper;
import org.eclipse.jst.j2ee.internal.codegen.GenerationBuffer;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;


/**
 * A Java method generator is used to create, remove or update a Java method.
 * 
 * <p>
 * Subclasses must implement these methods:
 * <ul>
 * <li>getName - returns the name of the method
 * </ul>
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>getComment - returns the comment for the method
 * <li>getBody() - returns a simple method body. Alternatively, the subclass can create a set of
 * set of dependent generators (in initialize or analyze) and they will be run in
 * getBody(IGenerationBuffer) if getBody() has not been overridden.
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>getReturnType - returns the return type for the method (default is void)
 * <li>getParameterDescriptors - override if parameter count is not 0
 * <li>deriveFlags - returns the modifier flags for the method
 * <li>getExceptions - to return a set of exceptions for the throws clause
 * </ul>
 * <p>
 * Subclasses may occasionally override these methods:
 * <ul>
 * <li>dispatchToMergeStrategy - when a hook is needed for pre and/or post merge processing
 * <li>terminate - to null object references and release resources
 * </ul>
 */
public abstract class JavaMethodGenerator extends JavaMemberGenerator {
	//	/**
	//	 * I DON'T THINK THIS INNER CLASS IS NEEDED ANYMORE.
	//	 */
	//	private static class TypeResolveRequestor implements ISelectionRequestor {
	//		private String fTypeName;
	//
	//		public void init() {
	//			fTypeName = null;
	//		}
	//
	//		public String getResult() {
	//			return fTypeName;
	//		}
	//
	//		public void acceptClass(char[] packageName, char[] className, boolean needQualification) {
	//			fTypeName = new String(packageName) + '.' + new String(className);
	//		}
	//
	//		public void acceptInterface(char[] packageName, char[] interfaceName, boolean
	// needQualification) {
	//			fTypeName = new String(packageName) + '.' + new String(interfaceName);
	//		}
	//
	//		public void acceptError(org.eclipse.jdt.core.compiler.IProblem error) {
	//		}
	//
	//		public void acceptField(char[] declaringTypePackageName, char[] declaringTypeName, char[]
	// name) {
	//		}
	//
	//		public void acceptMethod(char[] declaringTypePackageName, char[] declaringTypeName, char[]
	// selector, char[][] parameterPackageNames, char[][] parameterTypeNames) {
	//		}
	//
	//		public void acceptPackage(char[] packageName) {
	//		}
	//
	//		/**
	//		 * @see ISelectionRequestor#acceptMethod(char[], char[], char[], char[][], char[][],
	//		 * boolean)
	//		 */
	//		public void acceptMethod(char[] declaringTypePackageName, char[] declaringTypeName, char[]
	// selector, char[][] parameterPackageNames, char[][] parameterTypeNames, boolean isConstructor)
	// {
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 *
	//		 * @see org.eclipse.jdt.internal.codeassist.ISelectionRequestor#acceptMethod(char[], char[],
	//		 * char[], char[][], char[][], boolean, boolean, int, int)
	//		 */
	//		public void acceptMethod(char[] declaringTypePackageName, char[] declaringTypeName, char[]
	// selector, char[][] parameterPackageNames, char[][] parameterTypeNames, boolean isConstructor,
	// boolean isDeclaration, int start, int end) {
	//			// TODO Auto-generated method stub
	//
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 *
	//		 * @see org.eclipse.jdt.internal.codeassist.ISelectionRequestor#acceptClass(char[], char[],
	//		 * boolean, boolean, int, int)
	//		 */
	//		public void acceptClass(char[] packageName, char[] className, boolean needQualification,
	// boolean isDeclaration, int start, int end) {
	//			fTypeName = new String(packageName) + '.' + new String(className);
	//
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 *
	//		 * @see org.eclipse.jdt.internal.codeassist.ISelectionRequestor#acceptField(char[], char[],
	//		 * char[], boolean, int, int)
	//		 */
	//		public void acceptField(char[] declaringTypePackageName, char[] declaringTypeName, char[]
	// name, boolean isDeclaration, int start, int end) {
	//			// TODO Auto-generated method stub
	//
	//		}
	//
	//		/*
	//		 * (non-Javadoc)
	//		 *
	//		 * @see org.eclipse.jdt.internal.codeassist.ISelectionRequestor#acceptInterface(char[],
	//		 * char[], boolean, boolean, int, int)
	//		 */
	//		public void acceptInterface(char[] packageName, char[] interfaceName, boolean
	// needQualification, boolean isDeclaration, int start, int end) {
	//			fTypeName = new String(packageName) + '.' + new String(interfaceName);
	//
	//		}
	//	}

	private static final String WITH_RETURN_TEMPLATE = "%0 %1";//$NON-NLS-1$
	private static final String THROWS = " throws ";//$NON-NLS-1$
	private static final String ABSTRACT_METHOD_BODY = ";\n";//$NON-NLS-1$

	//private static final String RESOLVED_NAME = " :: RESOLVED_NAME :: ";//$NON-NLS-1$

	/**
	 * JavaMethodGenerator default constructor.
	 */
	public JavaMethodGenerator() {
		super();
	}

	/**
	 * Adds a user code point to the buffer using the given name and content. The content can be
	 * null. If there is content, it must not have margins and must have a line separator at the
	 * end.
	 * <p>
	 * Note that the support of VAJ style user code points is here mainly for compatibility and
	 * migration purposes. For new development, the use of specialized merglets is encouraged
	 * instead.
	 */
	protected void addUserCodePoint(IGenerationBuffer buffer, String codePointName, String content) throws GenerationException {
		((JavaGenerationBuffer) buffer).addUserCodePoint(codePointName, content, getCompilationUnitGenerator().getMergeStrategy());
	}

	/**
	 * Override of superclass signature redirects to derived implementation.
	 */
	protected MergeResults calculateMergeResults(JavaMemberHistoryDescriptor memberHistory, JavaMemberDescriptor desc) throws GenerationException {
		return calculateMergeResults((JavaMethodHistoryDescriptor) memberHistory, (JavaMethodDescriptor) desc);
	}

	/**
	 * Compares the field history and the new method description to arrive at the needed actions
	 * described by the merge results.
	 */
	protected MergeResults calculateMergeResults(JavaMethodHistoryDescriptor methodHistory, JavaMethodDescriptor desc) throws GenerationException {
		// If not remove only, get the new field contents.
		String source = null;
		IDOMMethod newMethod = null;
		if (!methodHistory.isDeleteOnly()) {
			IGenerationBuffer methodBuf = getGenerationBuffer();
			generateMethod(desc, methodBuf, (methodHistory.getCollisionMember() == null));
			source = methodBuf.toString();
			newMethod = getDOMFactory().createMethod(source);
		}

		// Check for merging and deletes. If merged, get the updated contents.
		MergeResults mr = dispatchToMergeStrategy(methodHistory, newMethod);
		if (mr.isMerged())
			source = mr.getSource();

		mr.setSource(source);

		return mr;
	}

	/**
	 * Creates a history descriptor using the same properties that are used to describe the new
	 * method. That is, the default history descriptor indicates that the old member and the
	 * collision member are the same.
	 * 
	 * @see JavaMethodHistoryDescriptor
	 */
	protected final JavaMethodHistoryDescriptor createDefaultHistoryDescriptor(JavaMethodDescriptor desc) throws GenerationException {
		JavaMethodHistoryDescriptor historyDesc = new JavaMethodHistoryDescriptor();
		historyDesc.setName(desc.getName());
		// Get the parm types.
		JavaParameterDescriptor[] parmDescs = desc.getParameterDescriptors();
		String[] parmTypes = null;
		if ((parmDescs != null) && (parmDescs.length > 0)) {
			parmTypes = new String[parmDescs.length];
			for (int i = 0; i < parmTypes.length; i++)
				parmTypes[i] = parmDescs[i].getType();
		}
		historyDesc.setParameterTypes(parmTypes);

		getMatchingMember(historyDesc);
		historyDesc.setCollisionMember(historyDesc.getOldMember());

		return historyDesc;
	}

	/**
	 * If no history descriptor is set,
	 * {@link JavaMethodGenerator#createDefaultHistoryDescriptor(JavaMethodDescriptor)}is used to
	 * create one. Otherwise, the existing history descriptor's old and collision members are set
	 * appropriately.
	 * 
	 * @see JavaMethodHistoryDescriptor
	 */
	protected JavaMemberHistoryDescriptor createHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		JavaMethodHistoryDescriptor historyDesc = (JavaMethodHistoryDescriptor) getHistoryDescriptor();
		if (historyDesc == null) {
			historyDesc = createDefaultHistoryDescriptor((JavaMethodDescriptor) desc);
		} else {
			IType type = getDeclaringType();
			if (type != null) {
				getMatchingMember(historyDesc);
				if (!historyDesc.isDeleteOnly()) {
					if (doDescriptorsMatch(type, historyDesc, (JavaMethodDescriptor) desc))
						historyDesc.setCollisionMember(historyDesc.getOldMember());
					else
						historyDesc.setCollisionMember(createDefaultHistoryDescriptor((JavaMethodDescriptor) desc).getCollisionMember());
				}
			}
		}

		return historyDesc;
	}

	/**
	 * Override of superclass signature redirects to derived implementation.
	 */
	protected JavaMemberDescriptor createMemberDescriptor() throws GenerationException {
		return createMethodDescriptor();
	}

	/**
	 * Creates the method descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected JavaMethodDescriptor createMethodDescriptor() throws GenerationException {
		JavaMethodDescriptor desc = new JavaMethodDescriptor();
		populateMethodDescriptor(desc);
		return desc;
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators. For example, a generator could override this method to use a specialized merglet
	 * rather than the default merglet for the merge strategy.
	 * 
	 * @see IJavaMergeStrategy
	 * @see IJavaMerglet
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMethodHistoryDescriptor methodHistory, IDOMMethod newMethod) throws GenerationException {
		return getCompilationUnitGenerator().getMergeStrategy().merge(methodHistory, newMethod);
	}

	/**
	 * Returns true if the history and new method descriptors describe the same method.
	 */
	protected final boolean doDescriptorsMatch(IType type, JavaMethodHistoryDescriptor historyDesc, JavaMethodDescriptor desc) throws GenerationException {
		boolean doMatch = true;
		if (!historyDesc.getName().equals(desc.getName())) {
			doMatch = false;
		} else {
			String[] historyTypes = historyDesc.getParameterTypes();
			int htLen = (historyTypes == null) ? 0 : historyTypes.length;
			JavaParameterDescriptor[] descParms = desc.getParameterDescriptors();
			int dpLen = (descParms == null) ? 0 : descParms.length;
			if (htLen != dpLen) {
				doMatch = false;
			} else {
				String historyTypeSig = null;
				String descParmTypeSig = null;
				for (int i = 0; doMatch && (i < htLen); i++) {
					historyTypeSig = Signature.createTypeSignature(historyTypes[i], false);
					descParmTypeSig = Signature.createTypeSignature(descParms[i].getType(), false);
					doMatch &= matchTypeSignatures(type, historyTypeSig, descParmTypeSig);
				}
			}
		}
		return doMatch;
	}

	/**
	 * Generate the described method into the buffer.
	 */
	protected void generateMethod(JavaMethodDescriptor desc, IGenerationBuffer methodBuf, boolean isNew) throws GenerationException {
		// Perhaps a blank line and then the comment.
		//dcb - remove blank line when using workbench code formatting.
		//if (isNew) // Blank line to start for new methods.
		//	methodBuf.append(IJavaGenConstants.LINE_SEPARATOR);
		appendComment(methodBuf, desc.getComment());

		// Put the flags in the buffer if there are any.
		String flags = formatFlags(desc.getFlags());
		if (flags.length() > 0) {
			methodBuf.appendWithMargin(flags);
			methodBuf.append(IBaseGenConstants.SPACE);
		} else {
			methodBuf.margin();
		}

		// Choose the declaration template.
		String template = getDeclarationTemplate();

		// Put the start of the declaration in the buffer.
		String[] parms = {desc.getReturnType(), desc.getName()};
		methodBuf.format(template, parms);

		// Now the parms.
		JavaParameterListGenerator parmsGen = getParameterListGenerator();
		parmsGen.initialize(desc.getParameterDescriptors());
		parmsGen.run(methodBuf);

		// Do the throws clause.
		String[] exceptions = desc.getExceptions();
		if ((exceptions != null) && (exceptions.length > 0)) {
			methodBuf.append(THROWS);
			methodBuf.append(exceptions);
		}

		// Do the body.
		if (getDeclaringTypeGenerator().isInterface() || Flags.isAbstract(desc.getFlags()) || Flags.isNative(desc.getFlags())) {
			methodBuf.append(ABSTRACT_METHOD_BODY);
		} else {
			methodBuf.append(IJavaGenConstants.MEMBER_CONTENT_START);
			getBody(methodBuf);
			methodBuf.appendWithMargin(IJavaGenConstants.MEMBER_CONTENT_END);
		}
	}

	/**
	 * Generates the new method.
	 */
	void generateTarget(IJavaElement sibling, MergeResults mr) throws GenerationException {
		try {
			// Create the method in the type.
			IMethod method = getDeclaringType().createMethod(formatIfNecessary(mr.getSource(), 1), sibling, false, getTargetContext().getProgressMonitor());
			// The field is this generator's Java element.
			setTargetElement(method);
		} catch (JavaModelException exc) {
			throw new GenerationException(getExceptionIndicator(exc), exc);
		}
	}

	private String getExceptionIndicator(JavaModelException e) {
		GenerationBuffer b = new GenerationBuffer();
		try {
			b.append(getDeclaringTypeGenerator().getName());
			b.append("#"); //$NON-NLS$ //$NON-NLS-1$
		} catch (GenerationException ge) {
		}
		try {
			b.append(getName());
			JavaParameterListGenerator parmsGen = getParameterListGenerator();
			parmsGen.initialize(getParameterDescriptors());
			parmsGen.run(b);
			b.append(" :: "); //$NON-NLS$ //$NON-NLS-1$
		} catch (GenerationException ge) {
		}
		b.append(e.getMessage());
		return b.toString();
	}

	/**
	 * This implementation returns null for an empty body. Subclasses can override this method to
	 * return the method body. Alternatively, the subclass can create a set of set of dependent
	 * generators (in initialize or analyze) and they will be run in getBody(IGenerationBuffer).
	 */
	protected String getBody() throws GenerationException {
		return null;
	}

	/**
	 * This implementation indents, and checks for a "String getBody()" override. If there was an
	 * override, its result is put in the buffer with margin. If there was not, the dependent
	 * children are run to create the body. This implementation then unindents.
	 */
	protected void getBody(IGenerationBuffer bodyBuf) throws GenerationException {
		bodyBuf.indent();
		String body = getBody();
		if (body == null)
			runDependents(bodyBuf);
		else
			bodyBuf.appendWithMargin(body);
		bodyBuf.unindent();
	}

	/**
	 * Returns the Javadoc comment for the member. The default is the method name. May get marked
	 * generated. Subclasses will usually want to override. Overrides just need to return the
	 * description. The framework will handle adding the Javadoc comment delimiters.
	 */
	protected String getComment() throws GenerationException {
		return getName() + IJavaGenConstants.LINE_SEPARATOR;
	}

	/**
	 * The constructor generator overrides to use a template with no return value.
	 */
	String getDeclarationTemplate() {
		return WITH_RETURN_TEMPLATE;
	}

	/**
	 * Subclasses must implement to get the exceptions thrown by the method from the source model.
	 * By default there are no exceptions.
	 */
	protected String[] getExceptions() throws GenerationException {
		return null;
	}

	/**
	 * Looks for a matching method in the declaring type. If one is found, the old member of the
	 * member history is set to it. If one is not found, the old member of the member history is set
	 * to null.
	 */
	protected void getMatchingMember(JavaMemberHistoryDescriptor memberHistory) throws GenerationException {
		IType type = getDeclaringType();
		if (type != null) {
			// Get the parm type signatures.
			String[] parmTypes = ((JavaMethodHistoryDescriptor) memberHistory).getParameterTypes();
			String[] parmSigs = null;
			if ((parmTypes != null) && (parmTypes.length > 0)) {
				parmSigs = new String[parmTypes.length];
				for (int i = 0; i < parmSigs.length; i++)
					parmSigs[i] = Signature.createTypeSignature(parmTypes[i], false);
			}

			// Get the method and make sure it is real.
			IMethod method = type.getMethod(memberHistory.getName(), parmSigs);
			memberHistory.setOldMember(null);
			if (method.exists())
				memberHistory.setOldMember(method);
			else
				searchForMatchingMethod(type, (JavaMethodHistoryDescriptor) memberHistory, parmSigs);
		} else {
			memberHistory.setOldMember(null);
		}
	}

	/**
	 * Subclasses must implement to get the method parameter list from the source model. The default
	 * is no parameters.
	 */
	protected JavaParameterDescriptor[] getParameterDescriptors() throws GenerationException {
		return null;
	}

	/**
	 * Returns an instance of the default parameter list generator.
	 */
	protected JavaParameterListGenerator getParameterListGenerator() {
		return new JavaParameterListGenerator();
	}

	/**
	 * Subclasses must implement to get the method return type from the source model. The default is
	 * void.
	 */
	protected String getReturnType() throws GenerationException {
		return null;
	}

	/**
	 * Returns true if the two signatures match within the scope of the specified type.
	 */
	protected boolean matchTypeSignatures(IType type, String signature1, String signature2) throws GenerationException {
		try {
			return JDOMSearchHelper.matchTypeSignatures(type, signature1, signature2, getTargetContext().getNavigator().getDictionary());
		} catch (JavaModelException e) {
			throw new GenerationException(e);
		}
	}

	/**
	 * Populates the method descriptor. Subclasses generally do not need to override this unless
	 * doing so will save significant time.
	 */
	protected void populateMethodDescriptor(JavaMethodDescriptor desc) throws GenerationException {
		super.populateMemberDescriptor(desc);
		String temp = getReturnType();
		if (temp != null)
			temp = temp.trim();
		desc.setReturnType(temp);
		desc.setParameterDescriptors(getParameterDescriptors());
		desc.setExceptions(getExceptions());
	}

	/**
	 * Reaccess the member from the working copy.
	 */
	IMember reaccess(IMember oldMember) throws GenerationException {
		IMember result = null;
		if (oldMember != null) {
			IType type = getDeclaringType();
			if (type != null)
				result = getDeclaringType().getMethod(oldMember.getElementName(), ((IMethod) oldMember).getParameterTypes());
			if ((result != null) && (!result.exists()))
				result = null;
		}
		return result;
	}

	/**
	 * Searches for a matching method and sets it as the old member in the descriptor.
	 */
	protected void searchForMatchingMethod(IType type, JavaMethodHistoryDescriptor historyDesc, String[] parmSigs) throws GenerationException {
		String methodName = historyDesc.getName();

		try {
			IMethod foundMethod = JDOMSearchHelper.searchForMatchingMethod(type, methodName, parmSigs, getTargetContext().getNavigator().getDictionary());
			// If found, set the old method in the history.
			historyDesc.setOldMember(foundMethod);
		} catch (JavaModelException exc) {
			throw new GenerationException(exc);
		}
	}
}