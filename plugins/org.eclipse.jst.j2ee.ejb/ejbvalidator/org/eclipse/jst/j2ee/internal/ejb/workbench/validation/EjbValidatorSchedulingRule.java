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
