package org.eclipse.jst.javaee.ltk.core.refactoringchecker;

import org.eclipse.core.resources.IProject;


public abstract class AbstractRefactoringCheckStateTester implements IRefactoringCheckStateTester{

	public boolean testUncheckState(IProject project){
		return false;
	}
}
