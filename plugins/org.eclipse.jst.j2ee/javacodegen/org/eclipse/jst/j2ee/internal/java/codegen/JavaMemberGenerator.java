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
import org.eclipse.jdt.core.ICodeFormatter;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.ISourceRange;
import org.eclipse.jdt.core.ISourceReference;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;


/**
 * A Java member generator is used to create or update types, fields or methods. It must be a
 * descendant of a type generator, except in the case of a top level type. See the subclass
 * specializations for information of the methods regularly overridden.
 */
public abstract class JavaMemberGenerator extends JavaElementGenerator {
	private JavaTypeGenerator fDeclaringTypeGenerator = null;
	private JavaMemberHistoryDescriptor fHistoryDescriptor = null;
	private MergeResults fMergeResults = null;

	/**
	 * JavaTypeGenerator default constructor.
	 */
	public JavaMemberGenerator() {
		super();
	}

	/**
	 * Analyze this Java member and merge where needed. Uses the
	 * {@link JavaMemberGenerator#createMemberDescriptor()},
	 * {@link JavaMemberGenerator#createHistoryDescriptor(JavaMemberDescriptor)}, and
	 * {@link JavaMemberGenerator#calculateMergeResults(JavaMemberHistoryDescriptor, JavaMemberDescriptor)}
	 * methods.
	 * 
	 * @see IBaseGenerator
	 */
	public AnalysisResult analyze() throws GenerationException {
		// Get the bits of the declaration.
		JavaMemberDescriptor desc = createMemberDescriptor();

		// Check for an existing member that matches the one we want.
		setHistoryDescriptor(createHistoryDescriptor(desc));
		fMergeResults = calculateMergeResults(getHistoryDescriptor(), desc);
		AnalysisResult result = fMergeResults.getAnalysisResult();
		if (result == null && fMergeResults.isChangeNeeded()) {
			result = new AnalysisResult();
			result.setStatus(AnalysisResult.INFORMATIONAL);
			result.setReasonCode(JavaCompilationUnitGenerator.COMPILATION_UNIT_CHANGED_REASON_CODE);
		}
		setAnalysisResult(result);

		return super.analyze();
	}

	/**
	 * Appends the comment to the buffer. If the first non-whitespace character is a '/', the
	 * comment is used as is. If it is not, it is formatted as a JavaDoc comment. In the latter
	 * case, the
	 * 
	 * @generated tag is may be added depending on the merge strategy being used.
	 */
	protected void appendComment(IGenerationBuffer buffer, String comment) throws GenerationException {
		if (comment != null) {
			boolean commentIsComplete = false;
			boolean done = false;
			char next = ' ';
			for (int i = 0; ((!done) && (i < comment.length())); i++) {
				next = comment.charAt(i);
				if (!Character.isWhitespace(next)) {
					done = true;
					if (next == '/')
						commentIsComplete = true;
				}
			}

			if (commentIsComplete) {
				buffer.appendWithMargin(comment);
			} else {
				buffer.appendWithMargin(IJavaGenConstants.JAVADOC_COMMENT_START);
				buffer.appendWithMarginAndPrefix(comment, IJavaGenConstants.JAVADOC_COMMENT_LINE);
				if (getCompilationUnitGenerator().getMergeStrategy().isTagGenerated()) {
					buffer.appendWithMarginAndPrefix(getCompilationUnitGenerator().getMergeStrategy().getGeneratedMemberTag(), IJavaGenConstants.JAVADOC_COMMENT_LINE);
					buffer.append(IJavaGenConstants.LINE_SEPARATOR);
				}
				buffer.appendWithMargin(IJavaGenConstants.JAVADOC_COMMENT_END);
			}
		}
	}

	/**
	 * Immediate subclasses override this to calculate merge results.
	 */
	protected MergeResults calculateMergeResults(JavaMemberHistoryDescriptor memberHistory, JavaMemberDescriptor desc) throws GenerationException {
		return null;
	}

	/**
	 * Immediate subclasses override this to create the history descriptor.
	 */
	protected JavaMemberHistoryDescriptor createHistoryDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		return null;
	}

	/**
	 * Immediate subclasses override this to create the member descriptor.
	 */
	protected JavaMemberDescriptor createMemberDescriptor() throws GenerationException {
		return null;
	}

	/**
	 * Subclass generators can override this to examine the source model and derive the modifer
	 * flags for this target element. The modifier flags are defined in
	 * org.eclipse.jdt.internal.compiler.env.api.IConstants. The default value returned here is
	 * AccPublic for members. The field generator changes the default to AccPrivate.
	 */
	protected int deriveFlags() throws GenerationException {
		return org.eclipse.jdt.internal.compiler.env.IConstants.AccPublic;
	}

	/**
	 * Takes a set of Java modifier flags as defined in
	 * org.eclipse.jdt.internal.compiler.env.api.IConstants and returns a String with the
	 * corresponding Java syntax.
	 */
	protected String formatFlags(int flags) {
		return Flags.toString(flags);
	}

	/**
	 * Immediate subclasses override this to generate the target.
	 */
	void generateTarget(IJavaElement sibling, MergeResults mr) throws GenerationException {
	}

	/**
	 * Returns the Javadoc comment for the member. The default is no comment. May get marked
	 * generated. Subclasses will usually want to override. Overrides just need to return the
	 * description. The framework will handle adding the Javadoc comment delimiters.
	 */
	protected String getComment() throws GenerationException {
		String comment = null;
		if (getCompilationUnitGenerator().getMergeStrategy().isTagGenerated())
			comment = IBaseGenConstants.EMPTY_STRING;
		return comment;
	}

	/**
	 * Returns the enclosing compilation unit generator.
	 * 
	 * @exception GenerationException
	 *                if the enclosing compilation unit generator can not be found.
	 */
	public JavaCompilationUnitGenerator getCompilationUnitGenerator() throws GenerationException {
		IBaseGenerator ancestorGen = getParent();
		while ((ancestorGen != null) && !(ancestorGen instanceof JavaCompilationUnitGenerator))
			ancestorGen = ancestorGen.getParent();
		if (ancestorGen == null)
			throw new GenerationException(JavaCodeGenResourceHandler.getString("Enclosing_compilation_unit_EXC_")); //$NON-NLS-1$ = "Enclosing compilation unit generator not found."
		return (JavaCompilationUnitGenerator) ancestorGen;
	}

	/**
	 * Returns the type that will declare the generated member. The type is prepared only if it was
	 * previously prepared or was created new. If an unprepared type is accessed and the generator
	 * is subsequently prepared, the declaring type must be reaccessed. Returns null for top level
	 * types.
	 * 
	 * @return org.eclipse.jdt.core.api.IType
	 */
	protected IType getDeclaringType() throws GenerationException {
		return getDeclaringTypeGenerator().getType();
	}

	/**
	 * Returns the type generator for enclosing type. Returns null for a top level type.
	 * 
	 * @exception GenerationException
	 *                if the declaring type generator can not be found.
	 */
	public JavaTypeGenerator getDeclaringTypeGenerator() throws GenerationException {
		if (fDeclaringTypeGenerator == null) {
			IBaseGenerator ancestorGen = getParent();
			while ((ancestorGen != null) && !(ancestorGen instanceof JavaTypeGenerator))
				ancestorGen = ancestorGen.getParent();
			if (ancestorGen == null)
				throw new GenerationException(JavaCodeGenResourceHandler.getString("Member_generator_does_not__EXC_")); //$NON-NLS-1$ = "Member generator does not have a declaring type generator."
			fDeclaringTypeGenerator = (JavaTypeGenerator) ancestorGen;
		}
		return fDeclaringTypeGenerator;
	}

	/**
	 * Returns the history descriptor.
	 */
	public JavaMemberHistoryDescriptor getHistoryDescriptor() {
		return fHistoryDescriptor;
	}

	/**
	 * Immediate subclasses override to find the member.
	 */
	protected void getMatchingMember(JavaMemberHistoryDescriptor memberHistory) throws GenerationException {
	}

	/**
	 * Returns the merge results for the target. This only meaningful after
	 * {@link JavaMemberGenerator#analyze()}completes.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.MergeResults
	 */
	public MergeResults getMergeResults() {
		return fMergeResults;
	}

	/**
	 * Subclasses must implement to derive the target member name from the source model.
	 */
	protected abstract String getName() throws GenerationException;

	/**
	 * Returns the sibling after the given sibling. This implementation returns null if null is
	 * passed in. Other implementations may alter that behavior.
	 */
	protected IJavaElement getNextSibling(IMember sibling) throws GenerationException {
		IJavaElement nextSibling = null;
		if (sibling != null) {
			try {
				ISourceRange siblingRange = sibling.getSourceRange();
				ISourceRange nextSiblingRange = null;
				IJavaElement[] siblings = ((IParent) sibling.getParent()).getChildren();
				for (int i = 0; ((i < siblings.length) && (nextSibling == null)); i++) {
					nextSiblingRange = ((ISourceReference) siblings[i]).getSourceRange();
					if ((nextSiblingRange.getOffset() == siblingRange.getOffset()) && ((nextSiblingRange.getOffset() + nextSiblingRange.getLength() - 1) == (siblingRange.getOffset() + siblingRange.getLength() - 1)) && ((i + 1) < siblings.length))
						nextSibling = siblings[i + 1];
				}
			} catch (JavaModelException exc) {
				throw new GenerationException(exc);
			}
		}
		return nextSibling;
	}

	/**
	 * Returns true if this is a group generator.
	 */
	public boolean isGroup() {
		return false;
	}

	/**
	 * For a Java member other than a top level type, this method returns true if the enclosing type
	 * generator returns true.
	 * 
	 * @see JavaElementGenerator
	 */
	public boolean isPrepared() throws GenerationException {
		return getDeclaringTypeGenerator().isPrepared();
	}

	/**
	 * Populates the member descriptor. Subclasses generally do not need to override this unless
	 * doing so will save significant time.
	 */
	protected void populateMemberDescriptor(JavaMemberDescriptor desc) throws GenerationException {
		desc.setFlags(deriveFlags());
		String temp = getName();
		if (temp != null)
			temp = temp.trim();
		desc.setName(temp);
		desc.setComment(getComment());
	}

	/**
	 * If not already prepared (see {@link JavaMemberGenerator#isPrepared()}for a definition of
	 * what being prepared is), clear the target element (to make sure it is reaccessed) and prepare
	 * the enclosing type.
	 * 
	 * @see JavaElementGenerator
	 */
	public void prepare() throws GenerationException {
		if (!isPrepared()) {
			setTargetElement(null);
			getDeclaringTypeGenerator().prepare();
		}
	}

	/**
	 * Reaccess the member from the working copy. Immediate subclasses typically override this.
	 */
	IMember reaccess(IMember oldMember) throws GenerationException {
		return null;
	}

	/**
	 * Generates this target and child targets.
	 * 
	 * @see IBaseGenerator
	 */
	public void run() throws GenerationException {
		// If generating (includes delete only)...
		MergeResults mr = getMergeResults();
		if (mr.isGenerate()) {
			// If not prepared, prepare.
			if (!isPrepared())
				prepare();

			// Reaccess the old and collision members from the working copy.
			IMember oldMember = mr.getOldMember();
			if (oldMember == mr.getCollisionMember()) {
				if (oldMember != null) {
					mr.setOldMember(reaccess(oldMember));
					mr.setCollisionMember(mr.getOldMember());
				}
			} else {
				if (oldMember != null)
					mr.setOldMember(reaccess(oldMember));
				if (mr.getCollisionMember() != null)
					mr.setCollisionMember(reaccess(mr.getCollisionMember()));
			}

			// If the field exists, remove it. But get the sibling for positioning first.
			IJavaElement sibling = null;
			try {
				if (!mr.isDeleteOnly()) // Do not need a sibling for delete only.
					sibling = getNextSibling((mr.getCollisionMember() == null) ? mr.getOldMember() : mr.getCollisionMember());
				if (mr.getOldMember() != null) {
					mr.getOldMember().delete(false, getTargetContext().getProgressMonitor());
				}
				if ((!mr.isDeleteOnly()) && (mr.getCollisionMember() != null) && (mr.getCollisionMember() != mr.getOldMember())) {
					mr.getCollisionMember().delete(false, getTargetContext().getProgressMonitor());
				}
			} catch (JavaModelException exc) {
				throw new GenerationException(exc);
			}

			// Generate the new member relative to the sibling.
			if (!mr.isDeleteOnly()) {
				generateTarget(sibling, mr);
			} else {
				// If we started out wanting to generate, set the collision
				// member as our target. For one reason or another, the merge
				// decided to keep it.
				if (!getHistoryDescriptor().isDeleteOnly())
					setTargetElement(mr.getCollisionMember());
			}
		} else {
			// If we started out wanting to generate, set the collision
			// member as our target. For one reason or another, the merge
			// decided to keep it.
			if (!getHistoryDescriptor().isDeleteOnly())
				setTargetElement(mr.getCollisionMember());
		}

		super.run();
	}

	/**
	 * Sets the history descriptor. This method is sometimes used from the initialize method in
	 * order to set a particular history before the analyze phase sets one.
	 * 
	 * @param newHistoryDescriptor
	 *            org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.JavaMemberHistoryDescriptor
	 */
	public void setHistoryDescriptor(JavaMemberHistoryDescriptor newHistoryDescriptor) {
		fHistoryDescriptor = newHistoryDescriptor;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void terminate() throws GenerationException {
		super.terminate();
		fDeclaringTypeGenerator = null;
		fHistoryDescriptor = null;
		fMergeResults = null;
	}

	private ICodeFormatter getCodeFormatter() {
		return ToolFactory.createCodeFormatter();
	}

	protected String format(String source) {
		return format(source, 0);
	}

	protected String format(String source, int indentationLevel) {
		return getCodeFormatter().format(source, indentationLevel, null, null);
	}

	protected String formatIfNecessary(String source, int indentationLevel) {
		JavaTypeGenerator typeGen = null;
		try {
			typeGen = getDeclaringTypeGenerator();
		} catch (GenerationException e) {
		}
		if (typeGen != null && !typeGen.isNew()) {
			String formatted = getCodeFormatter().format(source, indentationLevel, null, null);
			if (!formatted.endsWith(IJavaGenConstants.LINE_SEPARATOR)) {
				formatted = formatted + IJavaGenConstants.LINE_SEPARATOR;
			}
			return formatted;
		}
		return source;
	}

	protected boolean isNew() {
		return getHistoryDescriptor() != null && getHistoryDescriptor().getCollisionMember() == null;
	}
}