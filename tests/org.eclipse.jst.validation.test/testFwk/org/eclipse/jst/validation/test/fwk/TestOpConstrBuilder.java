package org.eclipse.jst.validation.test.fwk;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 */
public class TestOpConstrBuilder extends IncrementalProjectBuilder {
	public static final String BUILDER_ID = "org.eclipse.jst.validation.test.fwk.bvtbuilder"; //$NON-NLS-1$
	private IResourceDelta _delta = null;
	private static TestOpConstrBuilder _inst = null;
	
	public TestOpConstrBuilder() {
		_inst = this;
	}

	/**
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int, java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor) throws CoreException {
		// Nothing to do; this class exists only so that an IResourceDelta can be constructed.
		_delta = getDelta(getProject());
		return null;
	}

	public IResourceDelta getDelta() {
		return _delta;
	}
	
	public static TestOpConstrBuilder singleton() {
		return _inst;
	}
}
