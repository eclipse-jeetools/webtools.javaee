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
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.jdom.IDOMMember;

/**
 * The default field merglet.
 */
public class JavaFieldMerglet extends JavaMemberMerglet {
	private static final String FIELD_COLLISION_INFORMATIONAL = JavaCodeGenResourceHandler.getString("The_field_will_not_gen_INFO_"); //$NON-NLS-1$ = "The field \"{1}\" will not be generated because a non-generated field with the same name already exists."
	private static final String FIELD_NOT_DELETED_INFORMATIONAL = JavaCodeGenResourceHandler.getString("The_field_will_not_del_INFO_"); //$NON-NLS-1$ = "The field \"{0}\" will not be deleted because it is not marked generated."

	/**
	 * JavaFieldMerglet default constructor.
	 */
	public JavaFieldMerglet(IJavaMergeStrategy jms) {
		super(jms);
	}

	/**
	 * Returns the typical reason why a field is not generated during a collision.
	 */
	protected Object getMemberCollisionReason() {
		return FIELD_COLLISION_INFORMATIONAL;
	}

	/**
	 * Returns the typical reason why an old field is not deleted during a generation.
	 */
	protected Object getMemberNotDeletedReason() {
		return FIELD_NOT_DELETED_INFORMATIONAL;
	}

	/**
	 * No merge for fields. Returns false.
	 * 
	 * @return boolean
	 * @param oldMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean mergeMember(IMember oldMember, IDOMMember newMember) throws MergeException {
		return false;
	}

	/**
	 * Returns false if the field declarations are the same, ingnoring leading and trailing
	 * whitespace.
	 * 
	 * @return boolean
	 * @param collisionMember
	 *            org.eclipse.jdt.core.IMember
	 * @param newMember
	 *            org.eclipse.jdt.core.jdom.IDOMMember
	 */
	protected boolean needToGenerate(IMember collisionMember, IDOMMember newMember) throws MergeException {
		try {
			return !collisionMember.getSource().trim().equals(newMember.getContents().trim());
		} catch (JavaModelException exc) {
			throw new MergeException(exc);
		}
	}
}