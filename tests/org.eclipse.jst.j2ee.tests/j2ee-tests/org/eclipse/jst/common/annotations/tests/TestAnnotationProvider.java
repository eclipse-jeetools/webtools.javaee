package org.eclipse.jst.common.annotations.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jst.common.internal.annotations.core.IAnnotationsProvider;

public class TestAnnotationProvider implements IAnnotationsProvider {

	public TestAnnotationProvider() {
		super();
	}

	public boolean isAnnotated(EObject eObject) {
		return false;
	}

	public ICompilationUnit getPrimaryAnnotatedCompilationUnit(EObject eObject) {
		return null;
	}

	public String getPrimaryTagset(EObject eObject) {
		return null;
	}

}
