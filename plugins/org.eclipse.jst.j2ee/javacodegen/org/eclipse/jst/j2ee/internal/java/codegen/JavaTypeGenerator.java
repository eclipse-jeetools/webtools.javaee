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



import org.eclipse.jdt.core.IBuffer;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IOpenable;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.jdom.IDOMType;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;


/**
 * A Java type generator is used to create or update a Java class or interface.
 */
public abstract class JavaTypeGenerator extends JavaMemberGenerator {
	private boolean fPrepared = false;
	static final String EXTENDS = " extends ";//$NON-NLS-1$
	static final String BEGIN_BRACE = "{"; //$NON-NLS-1$
	static final String END_BRACE = "}"; //$NON-NLS-1$

	/**
	 * JavaTypeGenerator default constructor.
	 */
	public JavaTypeGenerator() {
		super();
	}

	protected MergeResults calculateMergeResults(JavaMemberHistoryDescriptor memberHistory, JavaMemberDescriptor desc) throws GenerationException {
		return calculateMergeResults(memberHistory, (JavaTypeDescriptor) desc);
	}

	/**
	 * Compares the type history and the new type description to arrive at the needed actions
	 * described by the merge results.
	 */
	protected MergeResults calculateMergeResults(JavaMemberHistoryDescriptor typeHistory, JavaTypeDescriptor desc) throws GenerationException {
		// If not remove only, get the new type contents.
		String source = null;
		IDOMType newType = null;
		if (!typeHistory.isDeleteOnly()) {
			IGenerationBuffer typeBuf = getGenerationBuffer();
			generateType(desc, typeBuf, (typeHistory.getCollisionMember() == null));
			source = typeBuf.toString();
			newType = getDOMFactory().createType(source);
		}

		// Check for merging and deletes. If merged, get the updated contents.
		MergeResults mr = dispatchToMergeStrategy(typeHistory, newType);
		if (mr.isMerged())
			source = mr.getSource();

		mr.setSource(source);

		return mr;
	}

	/**
	 * Creates a history descriptor using the same properties that are used to describe the new
	 * type. That is, the default history descriptor indicates that the old member and the collision
	 * member are the same.
	 * 
	 * @see JavaMemberHistoryDescriptor
	 */
	protected final JavaMemberHistoryDescriptor createDefaultHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		JavaMemberHistoryDescriptor historyDesc = new JavaMemberHistoryDescriptor();
		historyDesc.setName(desc.getName());
		getMatchingMember(historyDesc);
		historyDesc.setCollisionMember(historyDesc.getOldMember());

		return historyDesc;
	}

	/**
	 * If no history descriptor is set,
	 * {@link JavaTypeGenerator#createDefaultHistoryDescriptor(JavaMemberDescriptor)}is used to
	 * create one. Otherwise, the existing history descriptor's old and collision members are set
	 * appropriately.
	 * 
	 * @see JavaMemberHistoryDescriptor
	 */
	protected JavaMemberHistoryDescriptor createHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		JavaMemberHistoryDescriptor historyDesc = getHistoryDescriptor();
		if (historyDesc == null) {
			historyDesc = createDefaultHistoryDescriptor(desc);
		} else {
			getMatchingMember(historyDesc);
			if (!historyDesc.isDeleteOnly()) {
				if (historyDesc.getName().equals(desc.getName())) {
					historyDesc.setCollisionMember(historyDesc.getOldMember());
				} else {
					// Renames of main types are only supported in the framework
					// via a compilation unit rename.
					IType oldType = (IType) historyDesc.getOldMember();
					if (!isInner() && (oldType != null)) {
						ICompilationUnit oldCU = oldType.getCompilationUnit();
						String oldCUMainTypeName = oldCU.getElementName();
						oldCUMainTypeName = oldCUMainTypeName.substring(0, oldCUMainTypeName.length() - 5);
						if (oldType.getElementName().equals(oldCUMainTypeName))
							throw new GenerationException(JavaCodeGenResourceHandler.getString("Renames_of_main_types_are__EXC_")); //$NON-NLS-1$ = "Renames of main types are only supported in the Java code generation framework via a compilation unit rename."
					}
					// Other type renames can be done this way.
					historyDesc.setCollisionMember(createDefaultHistoryDescriptor(desc).getCollisionMember());
				}
			}
		}
		return historyDesc;
	}

	/**
	 * Override of superclass signature redirects to derived implementation.
	 */
	protected JavaMemberDescriptor createMemberDescriptor() throws GenerationException {
		return createTypeDescriptor();
	}

	/**
	 * Creates the type descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected abstract JavaTypeDescriptor createTypeDescriptor() throws GenerationException;

	/**
	 * Makes a top level type in a compilation unit.
	 */
	private IType createTypeInCU(String source, IJavaElement sibling) throws GenerationException {
		ICompilationUnit compilationUnit = getCompilationUnit();
		IType type = null;
		try {
			type = compilationUnit.createType(source, sibling, false, getTargetContext().getProgressMonitor());
		} catch (JavaModelException exc) {
			throw new GenerationException(getExceptionIndicator(exc), exc);
		}
		return type;
	}

	/**
	 * Makes an inner type inside another type.
	 */
	private IType createTypeInType(String source, IJavaElement sibling) throws GenerationException {
		IType outerType = getDeclaringType();
		IType type = null;

		try {
			type = outerType.createType(source, sibling, false, getTargetContext().getProgressMonitor());
		} catch (JavaModelException exc) {
			throw new GenerationException(getExceptionIndicator(exc), exc);
		}

		return type;
	}

	private String getExceptionIndicator(JavaModelException e) {
		StringBuffer b = new StringBuffer();
		try {
			b.append(getName());
			b.append(" :: "); //$NON-NLS$ //$NON-NLS-1$
		} catch (GenerationException ge) {
		}
		b.append(e.getMessage());
		return b.toString();
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators. For example, a generator could override this method to use a specialized merglet
	 * rather than the default merglet for the merge strategy.
	 * 
	 * @see IJavaMergeStrategy
	 * @see IJavaMerglet
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMemberHistoryDescriptor typeHistory, IDOMType newType) throws GenerationException {
		return getCompilationUnitGenerator().getMergeStrategy().merge(typeHistory, newType);
	}

	/**
	 * Generates the new type.
	 */
	void generateTarget(IJavaElement sibling, MergeResults mr) throws GenerationException {
		// Create the type.
		IType type = null;
		if (isInner())
			type = createTypeInType(mr.getSource(), sibling);
		else
			type = createTypeInCU(mr.getSource(), sibling);

		// The type is this generator's Java element.
		setTargetElement(type);
	}

	/**
	 * Populates the generation buffer with the basic class definition.
	 */
	protected void generateType(JavaTypeDescriptor desc, IGenerationBuffer typeBuf, boolean isNew) throws GenerationException {
		// Comment and start the declaration.
		if (isNew) // Blank line to start for new types.
			typeBuf.append(IJavaGenConstants.LINE_SEPARATOR);
		appendComment(typeBuf, desc.getComment());
		String temp = formatFlags(desc.getFlags());
		if ((temp != null) && (temp.length() > 0)) {
			typeBuf.appendWithMargin(temp);
			typeBuf.append(IBaseGenConstants.SPACE);
		} else {
			typeBuf.margin();
		}

		// Do the class/interface specific declaration bit.
		primTypeKindDecl(desc, typeBuf);

		// Put the body in the buffer.
		typeBuf.append(IJavaGenConstants.MEMBER_CONTENT_START);
		getBody(typeBuf);
		typeBuf.appendWithMargin(IJavaGenConstants.MEMBER_CONTENT_END);
	}

	/**
	 * This implementation returns null for an empty body. Subclasses can override this method to
	 * return the type body. Alternatively, the subclass can create a set of set of dependent
	 * generators (in initialize or analyze) and they will be run in getBody(IGenerationBuffer).
	 * <p>
	 * However, the typical usage is to have child base generators the the members of the type (see
	 * {@link JavaMemberGenerator}and it's subclasses).
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
	 * Returns the enclosing compilation unit for the type. The compilation unit is prepared only if
	 * it is created new or has previously been prepared.
	 */
	private ICompilationUnit getCompilationUnit() throws GenerationException {
		return getCompilationUnitGenerator().getCompilationUnit();
	}

	/**
	 * Override to return null for top level types.
	 */
	protected IType getDeclaringType() throws GenerationException {
		IType declType = null;
		if (isInner())
			declType = super.getDeclaringType();
		return declType;
	}

	/**
	 * Returns the type generator for enclosing type. Returns null for a top level type.
	 * 
	 * @exception GenerationException
	 *                if the declaring type generator can not be found.
	 */
	public JavaTypeGenerator getDeclaringTypeGenerator() throws GenerationException {
		JavaTypeGenerator declTypeGen = null;
		if (isInner())
			declTypeGen = super.getDeclaringTypeGenerator();
		return declTypeGen;
	}

	/**
	 * Looks for a matching method in the declaring type or compilation unit for top level types. If
	 * one is found, the old member of the member history is set to it. If one is not found, the old
	 * member of the member history is set to null.
	 */
	protected void getMatchingMember(JavaMemberHistoryDescriptor memberHistory) throws GenerationException {
		IType type = null;
		if (isInner()) {
			IType declType = getDeclaringType();
			if (declType != null)
				type = declType.getType(memberHistory.getName());
		} else {
			ICompilationUnit cu = getCompilationUnit();
			if (cu != null)
				type = cu.getType(memberHistory.getName());
		}

		if ((type != null) && (type.exists()))
			memberHistory.setOldMember(type);
		else
			memberHistory.setOldMember(null);
	}

	/**
	 * Subclasses must implement to get the list of super interfaces. The default is no super
	 * interfaces.
	 */
	protected String[] getSuperInterfaceNames() throws GenerationException {
		return null;
	}

	/**
	 * Returns the type that is being generated. The type is prepared only if it was previously
	 * prepared or was created new. If an unprepared type is accessed and the generator is
	 * subsequently prepared, the declaring type must be reaccessed. Must only be called by child
	 * generators during run().
	 */
	IType getType() throws GenerationException {
		IType type = (IType) getTargetElement();

		if (type == null) {
			if (isInner()) {
				IType declType = getDeclaringType();
				if (declType != null)
					type = declType.getType(getName().trim());
			} else {
				ICompilationUnit cu = getCompilationUnit();
				if (cu != null)
					type = cu.getType(getName().trim());
			}

			if ((type != null) && (type.exists()))
				setTargetElement(type);
		}
		return (IType) getTargetElement();
	}

	/**
	 * Returns true if the generated type is a class.
	 * 
	 * @return boolean
	 */
	public boolean isClass() {
		return false;
	}

	/**
	 * Returns true if a working copy of the enclosing compilation unit has been obtained.
	 * 
	 * @see JavaCompilationUnitGenerator#isPrepared()
	 */
	public boolean isCompilationUnitPrepared() throws GenerationException {
		return getCompilationUnitGenerator().isPrepared();
	}

	/**
	 * Returns true if the generated type is an inner type. The default is to return false. Inner
	 * class generators must override.
	 * 
	 * @return boolean
	 */
	public boolean isInner() {
		return false;
	}

	/**
	 * Returns true if the generated type is an interface.
	 * 
	 * @return boolean
	 */
	public boolean isInterface() {
		return false;
	}

	/**
	 * Returns true if {@link JavaTypeGenerator#prepare()}has been called on this generator.
	 * Generally speaking, a type is prepared when one of the child member generators needs the type
	 * to be prepared. The framework handles this during run().
	 */
	public boolean isPrepared() throws GenerationException {
		return fPrepared;
	}

	/**
	 * Populates the type descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected void populateTypeDescriptor(JavaTypeDescriptor desc) throws GenerationException {
		super.populateMemberDescriptor(desc);
		desc.setSuperInterfaceNames(getSuperInterfaceNames());
	}

	/**
	 * Generally speaking, a type is prepared when one of the child member generators needs the type
	 * to be prepared. The framework handles this during run().
	 */
	public void prepare() throws GenerationException {
		if (!fPrepared) {
			if (isInner()) {
				if (!(getDeclaringTypeGenerator().isPrepared()))
					getDeclaringTypeGenerator().prepare();
			} else {
				if (!isCompilationUnitPrepared())
					prepareCompilationUnit();
			}
			setTargetElement(null);
			fPrepared = true;
		}
	}

	/**
	 * Prepares the compilation unit for a type. Makes sure the enclosing type's compilation unit is
	 * prepared for an inner type.
	 * 
	 * @see JavaCompilationUnitGenerator#isPrepared()
	 */
	public void prepareCompilationUnit() throws GenerationException {
		getCompilationUnitGenerator().prepare();
	}

	/**
	 * Populates the generation buffer with the class/interface specific part of the type
	 * declaration. The immediate subclasses in the framework implement this method.
	 */
	protected abstract void primTypeKindDecl(JavaTypeDescriptor desc, IGenerationBuffer typeBuf);

	/**
	 * Reaccess the member from the working copy.
	 */
	IMember reaccess(IMember oldMember) throws GenerationException {
		IMember result = null;
		if (oldMember != null) {
			if (isInner()) {
				IType declType = getDeclaringType();
				if (declType != null)
					result = declType.getType(oldMember.getElementName());
			} else {
				ICompilationUnit cu = getCompilationUnit();
				if (cu != null)
					result = cu.getType(oldMember.getElementName());
			}

			if ((result != null) && (!result.exists()))
				result = null;
		}
		return result;
	}

	/**
	 * Adds the post process step through the merge strategy for types.
	 */
	public void run() throws GenerationException {
		super.run();
		getCompilationUnitGenerator().getMergeStrategy().postProcess(this);
		if (isNew())
			formatContents();
	}

	protected void formatContents() throws GenerationException {
		IType type = getType();
		if (type != null && !type.isBinary()) {
			try {
				IOpenable openable = getType().getOpenable();
				IBuffer buffer = openable.getBuffer();
				if (buffer != null) {
					String formatted = format(buffer.getContents());
					int start, end;
					start = formatted.indexOf(BEGIN_BRACE);
					end = formatted.indexOf(END_BRACE);
					if (end - start == 1) {
						//We cannot have a situation where the class has no methods
						//and ends with "{}" because it will not be able to gen any
						//additional methods into it.
						buffer.setContents(formatted.substring(0, start + 1));
						buffer.append(IJavaGenConstants.LINE_SEPARATOR);
						buffer.append(formatted.substring(end));
					} else
						buffer.setContents(formatted);
				}
			} catch (JavaModelException e) {
			}
		}
	}
}