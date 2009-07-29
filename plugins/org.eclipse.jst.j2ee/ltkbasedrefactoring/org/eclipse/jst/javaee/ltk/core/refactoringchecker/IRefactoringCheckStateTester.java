package org.eclipse.jst.javaee.ltk.core.refactoringchecker;

import org.eclipse.core.resources.IProject;

public interface IRefactoringCheckStateTester {

	boolean testUncheckState(IProject project);
}
