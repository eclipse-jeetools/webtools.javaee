/*******************************************************************************
 * Copyright (c) 2001, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.contenttype;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.core.runtime.content.IContentDescriber;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.jst.j2ee.contenttype.J2EESpecFinder;

/**
 * A content describer for detecting a j2ee module
 */
public final class JEEContentDescriber implements IContentDescriber {
	public JEEContentDescriber() {
		super();
	}

	public final static QualifiedName JEEVERSION = new QualifiedName("jee-version", "5.0"); //$NON-NLS-1$

	public int describe(InputStream contents, IContentDescription description)
			throws IOException {
		
		String specString = new String();
		
		try {
			specString = J2EESpecFinder.getFastSpecVersion(contents);
		} catch (Exception e) {
			// do nothing...exception is thrown during sax parse for badly formed file.
			//This may happen when this is called during project creation. this is handled below.
		}

		if (specString == null) {
			if(description != null){
			description.setProperty(JEEVERSION, specString);
			}
			return INDETERMINATE;
		}
			
		//checking for various jee module versions here. if its not ee5, return true.
		if((specString.equals("3.0") || specString.equals("2.5") || specString.equals("5.0") || specString.equals("1.5"))){
			if(description != null){
				description.setProperty(JEEVERSION, specString);
				}
			return VALID;
		}
			
		return INVALID;
	}

	

	public QualifiedName[] getSupportedOptions() {
		
		return new QualifiedName[] { JEEVERSION };
	}



	public List getValidVersions() {
		return Arrays.asList("3.0","2.5","5.0","1.5");
	}
	
	
}
