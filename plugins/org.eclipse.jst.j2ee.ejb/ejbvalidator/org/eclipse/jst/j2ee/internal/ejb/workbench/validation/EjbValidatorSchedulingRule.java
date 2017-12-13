/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

public class EjbValidatorSchedulingRule implements ISchedulingRule {

	private IPath path;
	
	public EjbValidatorSchedulingRule(IPath path){
		this.path = path;
	}
	public boolean contains(ISchedulingRule rule) {

		if( this == rule )
			return true;
		
		if( rule instanceof EjbValidatorSchedulingRule ){
			String otherPath = ((EjbValidatorSchedulingRule)rule).path.toString();
			return path.toString().equals( otherPath );
		}
		return false;
	}

	public boolean isConflicting(ISchedulingRule rule) {
        if (!(rule instanceof EjbValidatorSchedulingRule))
            return false;
         String otherPath = ((EjbValidatorSchedulingRule)rule).path.toString();
         return path.toString().equals( otherPath );
	}

	
}
