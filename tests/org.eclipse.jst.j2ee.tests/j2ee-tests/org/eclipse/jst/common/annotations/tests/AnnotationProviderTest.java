package org.eclipse.jst.common.annotations.tests;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;

import org.eclipse.jst.common.internal.annotations.core.AnnotationsProviderManager;
import org.eclipse.jst.common.internal.annotations.core.IAnnotationsProvider;
import org.eclipse.wst.common.tests.SimpleTestSuite;

public class AnnotationProviderTest extends TestCase {

	public AnnotationProviderTest() {
		super();
	}

	public AnnotationProviderTest(String name) {
		super(name);
	}
	
	public static Test suite() {
	    return new SimpleTestSuite(AnnotationProviderTest.class);
	}
	
	public void testAnnotationProviderFramework() throws Exception {
		List providers = AnnotationsProviderManager.INSTANCE.getAnnotationsProviders();
		boolean success = false;
		for (int i=0; i<providers.size(); i++) {
			IAnnotationsProvider provider = (IAnnotationsProvider) providers.get(i);
			provider.isAnnotated(null);
			provider.getPrimaryAnnotatedCompilationUnit(null);
			provider.getPrimaryTagset(null);
			success = true;
		}
		assertTrue(success);
	}

}
