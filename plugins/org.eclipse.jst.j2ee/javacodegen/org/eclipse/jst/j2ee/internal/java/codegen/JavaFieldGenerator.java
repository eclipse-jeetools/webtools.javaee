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



import org.eclipse.jdt.core.IField;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;


/**
 * A Java field generator is used to create, remove or update a Java field.
 * 
 * <p>
 * Subclasses must implement these methods:
 * <ul>
 * <li>getName - returns the name of the field
 * <li>getType - returns the type of the field
 * </ul>
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>getComment - returns the comment for the field
 * <li>getInitializer - returns the initialization code for the field
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>deriveFlags - returns the modifier flags for the field
 * </ul>
 * <p>
 * Subclasses may occasionally override these methods:
 * <ul>
 * <li>dispatchToMergeStrategy - when a hook is needed for pre and/or post merge processing
 * <li>terminate - to null object references and release resources
 * </ul>
 */
public abstract class JavaFieldGenerator extends JavaMemberGenerator {
	private static final String WITH_INIT_TEMPLATE = "%0 %1 = %2;" + IJavaGenConstants.LINE_SEPARATOR;//$NON-NLS-1$
	private static final String WITHOUT_INIT_TEMPLATE = "%0 %1;" + IJavaGenConstants.LINE_SEPARATOR;//$NON-NLS-1$

	/**
	 * JavaFieldGenerator default constructor.
	 */
	public JavaFieldGenerator() {
		super();
	}

	/**
	 * Compares the field history and the new field description to arrive at the needed actions
	 * described by the merge results.
	 */
	protected MergeResults calculateMergeResults(JavaMemberHistoryDescriptor fieldHistory, JavaFieldDescriptor desc) throws GenerationException {
		// If not remove only, get the new field contents.
		String source = null;
		IDOMField newField = null;
		if (!fieldHistory.isDeleteOnly()) {
			IGenerationBuffer fieldBuf = getGenerationBuffer();
			generateField(desc, fieldBuf);
			source = fieldBuf.toString();
			newField = getDOMFactory().createField(source);
		}

		// Check for merging and deletes. If merged, get the updated contents.
		MergeResults mr = dispatchToMergeStrategy(fieldHistory, newField);
		if (mr.isMerged())
			source = mr.getSource();

		mr.setSource(source);

		return mr;
	}

	/**
	 * Override of superclass signature redirects to derived implementation.
	 */
	protected MergeResults calculateMergeResults(JavaMemberHistoryDescriptor memberHistory, JavaMemberDescriptor desc) throws GenerationException {
		return calculateMergeResults(memberHistory, (JavaFieldDescriptor) desc);
	}

	/**
	 * Creates a history descriptor using the same properties that are used to describe the new
	 * field. That is, the default history descriptor indicates that the old member and the
	 * collision member are the same.
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
	 * Creates the field descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected JavaFieldDescriptor createFieldDescriptor() throws GenerationException {
		JavaFieldDescriptor desc = new JavaFieldDescriptor();
		populateFieldDescriptor(desc);
		return desc;
	}

	/**
	 * If no history descriptor is set,
	 * {@link JavaFieldGenerator#createDefaultHistoryDescriptor(JavaMemberDescriptor)}is used to
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
				if (historyDesc.getName().equals(desc.getName()))
					historyDesc.setCollisionMember(historyDesc.getOldMember());
				else
					historyDesc.setCollisionMember(createDefaultHistoryDescriptor(desc).getCollisionMember());
			}
		}
		return historyDesc;
	}

	/**
	 * Override of superclass signature redirects to derived implementation.
	 */
	protected JavaMemberDescriptor createMemberDescriptor() throws GenerationException {
		return createFieldDescriptor();
	}

	/**
	 * Override to make default private.
	 */
	protected int deriveFlags() throws GenerationException {
		return org.eclipse.jdt.internal.compiler.env.IConstants.AccPrivate;
	}

	/**
	 * Dispatch to the merge strategy. This method is here simply to provide a hook for derived
	 * generators. For example, a generator could override this method to use a specialized merglet
	 * rather than the default merglet for the merge strategy.
	 * 
	 * @see IJavaMergeStrategy
	 * @see IJavaMerglet
	 */
	protected MergeResults dispatchToMergeStrategy(JavaMemberHistoryDescriptor fieldHistory, IDOMField newField) throws GenerationException {
		return getCompilationUnitGenerator().getMergeStrategy().merge(fieldHistory, newField);
	}

	/**
	 * Generates the field from the descriptor into the buffer.
	 */
	protected void generateField(JavaFieldDescriptor desc, IGenerationBuffer fieldBuf) throws GenerationException {
		// First the comment.
		appendComment(fieldBuf, desc.getComment());

		// Put the flags in the buffer if there are any.
		String flags = formatFlags(desc.getFlags());
		if (flags.length() > 0) {
			fieldBuf.appendWithMargin(flags);
			fieldBuf.append(IBaseGenConstants.SPACE);
		} else {
			fieldBuf.margin();
		}

		// Choose the declaration template base on if there is an initializer or not.
		String template = null;
		String fieldInitializer = desc.getInitializer();
		if ((fieldInitializer != null) && (fieldInitializer.length() > 0))
			template = WITH_INIT_TEMPLATE;
		else
			template = WITHOUT_INIT_TEMPLATE;

		// Put the declaration in the buffer.
		String[] parms = {desc.getType(), desc.getName(), fieldInitializer};
		fieldBuf.format(template, parms);
	}

	/**
	 * Generates the new field into the declaring type.
	 */
	void generateTarget(IJavaElement sibling, MergeResults mr) throws GenerationException {
		try {
			// Create the field in the type.
			IField field = getDeclaringType().createField(formatIfNecessary(mr.getSource(), 1), sibling, false, getTargetContext().getProgressMonitor());
			// The field is this generator's Java element.
			setTargetElement(field);
		} catch (JavaModelException exc) {
			throw new GenerationException(getExceptionIndicator(exc), exc);
		}
	}

	private String getExceptionIndicator(JavaModelException e) {
		StringBuffer b = new StringBuffer();
		try {
			b.append(getDeclaringTypeGenerator().getName());
			b.append("#"); //$NON-NLS$ //$NON-NLS-1$
		} catch (GenerationException ge) {
		}
		try {
			b.append(getName());
			b.append(" :: "); //$NON-NLS$ //$NON-NLS-1$
		} catch (GenerationException ge) {
		}
		b.append(e.getMessage());
		return b.toString();
	}

	/**
	 * Subclasses must override to get the field initializer from the source model. The default is
	 * no initializer.
	 */
	protected String getInitializer() throws GenerationException {
		return null;
	}

	/**
	 * Looks for a matching field in the declaring type. If one is found, the old member of the
	 * member history is set to it. If one is not found, the old member of the member history is set
	 * to null.
	 */
	protected void getMatchingMember(JavaMemberHistoryDescriptor memberHistory) throws GenerationException {
		IType declType = getDeclaringType();
		IField field = null;
		if (declType != null)
			field = declType.getField(memberHistory.getName());
		if ((field != null) && (field.exists()))
			memberHistory.setOldMember(field);
		else
			memberHistory.setOldMember(null);
	}

	/**
	 * Returns the sibling after the given sibling. This implementation returns the member after the
	 * last field if null is passed in. The change in behavior from the base class with regard to a
	 * null parameter is to get fields to appear by default as a group at the top of the class
	 * declaration.
	 */
	protected IJavaElement getNextSibling(IMember sibling) throws GenerationException {
		IJavaElement nextSibling = null;
		if (sibling != null) {
			nextSibling = super.getNextSibling(sibling);
		} else {
			try {
				// Get just the fields. If there are some get the member after the last field.
				// The field will be inserted before that member.
				IType declType = getDeclaringType();
				IField[] fields = declType.getFields();
				if ((fields != null) && (fields.length > 0)) {
					nextSibling = super.getNextSibling(fields[fields.length - 1]);
				} else {
					// No fields, get the first child of any type and put it before that.
					// If no children, return null and this will be first.
					IJavaElement[] members = declType.getChildren();
					if ((members != null) && (members.length > 0))
						nextSibling = members[0];
				}
			} catch (JavaModelException exc) {
				throw new GenerationException(exc);
			}
		}
		return nextSibling;
	}

	/**
	 * Subclasses must implement to get the field type from the source model.
	 */
	protected abstract String getType() throws GenerationException;

	/**
	 * Populates the field descriptor. Subclasses generally do not need to override this unless
	 * doing so will save significant time.
	 */
	protected void populateFieldDescriptor(JavaFieldDescriptor desc) throws GenerationException {
		super.populateMemberDescriptor(desc);
		String temp = getType();
		if (temp != null)
			temp = temp.trim();
		desc.setType(temp);
		temp = getInitializer();
		if (temp != null)
			temp = temp.trim();
		desc.setInitializer(temp);
	}

	/**
	 * Reaccess the member from the working copy.
	 */
	IMember reaccess(IMember oldMember) throws GenerationException {
		IMember result = null;
		if (oldMember != null) {
			IType type = getDeclaringType();
			if (type != null)
				result = getDeclaringType().getField(oldMember.getElementName());
			if ((result != null) && (!result.exists()))
				result = null;
		}
		return result;
	}
}