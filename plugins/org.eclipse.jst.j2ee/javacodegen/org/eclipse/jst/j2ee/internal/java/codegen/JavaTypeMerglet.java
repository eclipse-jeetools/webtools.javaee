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



import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jdt.core.jdom.IDOMType;


/**
 * The default type merglet.
 */
public class JavaTypeMerglet extends JavaMemberMerglet {
	private static final String TYPE_COLLISION_INFORMATIONAL = JavaCodeGenResourceHandler.getString("The_type_will_not_gen_INFO_"); //$NON-NLS-1$ = "The type \"{1}\" will not be generated because a non-generated type with the same name already exists."
	private static final String TYPE_NOT_DELETED_INFORMATIONAL = JavaCodeGenResourceHandler.getString("The_type_will_not_del_INFO_"); //$NON-NLS-1$ = "The type \"{0}\" will not be deleted because it is not marked generated."
	private String[] fNoMergeSuperInterfaceNames = null;

	/**
	 * JavaTypeMerglet default constructor.
	 */
	public JavaTypeMerglet(IJavaMergeStrategy jms) {
		super(jms);
	}

	/**
	 * Returns the typical reason why a type is not generated during a collision.
	 */
	protected Object getMemberCollisionReason() {
		return TYPE_COLLISION_INFORMATIONAL;
	}

	/**
	 * Returns the typical reason why an old type is not deleted during a generation.
	 */
	protected Object getMemberNotDeletedReason() {
		return TYPE_NOT_DELETED_INFORMATIONAL;
	}

	/**
	 * The type generator or merge strategy can specify super interfaces that should not be merged
	 * from the old type to the new type via this property.
	 * 
	 * @return java.lang.String[]
	 */
	public java.lang.String[] getNoMergeSuperInterfaceNames() {
		return fNoMergeSuperInterfaceNames;
	}

	/**
	 * Adds merging of the type's children to the inherited implementation.
	 * <p>
	 * Note that this does not involve other generators or merglets at this point. This is simply a
	 * copy of all the members in the old type over to the new type. Further analysis via child
	 * generators and their associated merglets may alter this result.
	 * <p>
	 * Uses super.merge and {@link JavaTypeMerglet#mergeChildren(IType, IDOMType)}.
	 * 
	 * @see IJavaMerglet
	 * @return boolean
	 * @param mr
	 *            org.eclipse.jst.j2ee.internal.internal.internal.java.codegen.MergeResults
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	public boolean merge(MergeResults mr, IDOMMember newMember) throws MergeException {
		boolean needToGenerate = super.merge(mr, newMember);

		// If we need to generate, merge the children.
		if (needToGenerate && (mr.getOldMember() != null)) {
			String mergeResult = mergeChildren((IType) mr.getOldMember(), (IDOMType) newMember);
			if (mergeResult != null) {
				mr.setMerged(true);
				mr.setSource(mergeResult);
			}
		}

		return needToGenerate;
	}

	/**
	 * Merge the children of the old type into the new type. Returns a string of merged source if
	 * there were children to merge and null if not.
	 * <p>
	 * Note that this does not involve other generators or merglets at this point. This is simply a
	 * copy of all the members in the old type over to the new type. Further analysis via child
	 * generators and their associated merglets may alter this result.
	 * 
	 * @return String
	 * @param oldType
	 *            org.eclipse.jdt.core.IType
	 * @param newType
	 *            org.eclipse.jdt.core.jdom.IDOMType
	 */
	protected String mergeChildren(IType oldType, IDOMType newType) throws MergeException {
		StringBuffer mergedSource = null;
		try {
			if (oldType.hasChildren()) {
				int oldTypeIndex = oldType.getNameRange().getOffset() - oldType.getSourceRange().getOffset();
				String oldSource = oldType.getSource();
				int oldBodyStart = oldSource.indexOf('{', oldTypeIndex) + 1;
				int oldBodyEnd = oldSource.lastIndexOf('}');
				if (!((oldBodyStart < 0) || (oldBodyStart > oldBodyEnd))) {
					String newSource = newType.getContents();
					int newBodyInsertPoint = newSource.lastIndexOf('}');
					if (newBodyInsertPoint >= 0) {
						mergedSource = new StringBuffer(newSource);
						mergedSource.insert(newBodyInsertPoint, oldSource.substring(oldBodyStart, oldBodyEnd));
					}
				}
			}
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
		return (mergedSource == null) ? null : mergedSource.toString();
	}

	/**
	 * Merges the structural elements of the type. This method is called only if the old member of
	 * the merge results is not null. If the old member is null, there is nothing to merge from.
	 * Returns true if merging was done. Uses
	 * {@link JavaTypeMerglet#mergeSuperInterfaces(IType, IDOMType)}.
	 * 
	 * @return boolean
	 * @param oldMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean mergeMember(IMember oldMember, IDOMMember newMember) throws MergeException {
		return mergeSuperInterfaces((IType) oldMember, (IDOMType) newMember);
	}

	/**
	 * If the old type has super interfaces that the new one does not have, merge in the missing
	 * ones. Returns true if there were interfaces to merge.
	 * 
	 * @return boolean
	 * @param oldType
	 *            org.eclipse.jdt.core.IType
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean mergeSuperInterfaces(IType oldType, IDOMType newType) throws MergeException {
		boolean merged = false;
		try {
			String[] oldSuperInterfaceNames = oldType.getSuperInterfaceNames();
			if (!((oldSuperInterfaceNames == null) || (oldSuperInterfaceNames.length == 0))) {
				String[] newSuperInterfaceNames = newType.getSuperInterfaces();
				String[] noMergeSuperInterfaceNames = getNoMergeSuperInterfaceNames();
				for (int i = 0; i < oldSuperInterfaceNames.length; i++) {
					int found = findTypeNameMatch(oldType, oldSuperInterfaceNames[i], newSuperInterfaceNames);
					if (found < 0) {
						found = findTypeNameMatch(oldType, oldSuperInterfaceNames[i], noMergeSuperInterfaceNames);
						if (found < 0) {
							newType.addSuperInterface(oldSuperInterfaceNames[i]);
							merged = true;
						}
					}
				}
			}
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
		return merged;
	}

	/**
	 * Check the collision type and the new type. 1. same flags 2. same superclass - uses
	 * {@link JavaMerglet#matchTypeNames(IType, String, String)}3. same super interfaces - uses
	 * {@link JavaMerglet#matchTypeNames(IType, String[], String[])}Do not have to check the name
	 * since that defines collision. Returns true if any of the above are not the same.
	 * 
	 * @return boolean
	 * @param collisionMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean needToGenerate(IMember collisionMember, IDOMMember newMember) throws MergeException {
		boolean needToGenerate = false;
		IType collisionType = (IType) collisionMember;
		IDOMType newType = (IDOMType) newMember;

		try {
			// First, check the flags.
			needToGenerate = !(collisionType.getFlags() == newMember.getFlags());

			// Second, check the superclass.
			if (!needToGenerate)
				needToGenerate = !matchTypeNames(collisionType, collisionType.getSuperclassName(), newType.getSuperclass());

			// Third, check the super interfaces.
			if (!needToGenerate)
				needToGenerate = !matchTypeNames(collisionType, collisionType.getSuperInterfaceNames(), newType.getSuperInterfaces());
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}

		return needToGenerate;
	}

	/**
	 * The type generator or merge strategy can specify super interfaces that should not be merged
	 * from the old type to the new type via this property.
	 * 
	 * @param newNoMergeSuperInterfaceNames
	 *            java.lang.String[]
	 */
	public void setNoMergeSuperInterfaceNames(java.lang.String[] newNoMergeSuperInterfaceNames) {
		fNoMergeSuperInterfaceNames = newNoMergeSuperInterfaceNames;
	}
}