package org.eclipse.jst.j2ee.internal.classpathdep;

public class ClasspathDependencyEnablement {

	/**
	 * This flag is used to control the enablement of the Classpath Dependency
	 * functionality.  The default value is true which enables this functionality.
	 * Setting this value to false will disable the functionality.
	 */
	private static boolean allowClasspathComponentDependnecy = true;
	
	public static void setAllowClasspathComponentDependency(boolean allow){
		allowClasspathComponentDependnecy = allow;
	}
	
	public static boolean isAllowClasspathComponentDependency(){
		return allowClasspathComponentDependnecy;
	}
	
}
