package org.eclipse.jem.internal.plugin;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
/**
 * Insert the type's description here.
 * Creation date: (3/11/2001 9:02:15 PM)
 * @author: Administrator
 */
public class JavaMOFNatureRuntime extends AbstractJavaMOFNatureRuntime {
	
/**
 * JavaMOFNatureRuntime constructor comment.
 */
public JavaMOFNatureRuntime() {
	super();
}
/**
 * Get a IJavaMOFNatureRuntime that corresponds to the supplied project.
 * @return IJavaMOFNatureRuntime
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static IJavaMOFNature createRuntime(IProject project) throws CoreException {
	if(!hasRuntime(project))
		addNatureToProject(project, NATURE_ID);

	return getRuntime(project);
}
/**
 * Return the root location for loading mof resources; defaults to the source folder, subclasses may override
 */
public IContainer getMofRoot() {
	return getProject();
}
/**
 * Return the nature's ID.
 */
public java.lang.String getNatureID() {
	return NATURE_ID;
}
/**
 * Return the ID of the plugin that this nature is contained within.
 */
protected java.lang.String getPluginID() {
	return "com.ibm.etools.java";//$NON-NLS-1$
}
/**
 * Get a IJavaMOFNatureRuntime that corresponds to the supplied project.
 * First check for registered natures.
 * @return IJavaMOFNatureRuntime
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static IJavaMOFNature getRuntime(IProject project) {
	IJavaMOFNature nature = getRegisteredRuntime(project);
	if (nature == null)
		nature = primGetRuntime(project);
	return nature;
}
/**
 * Return whether or not the project has a runtime created on it.
 * Check for registered natures first.
 * @return boolean
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static boolean hasRuntime(IProject project) {
	if (getRegisteredRuntime(project) == null)
		return primHasRuntime(project);
	else
		return true;

}
/**
 * Get a IJavaMOFNatureRuntime that corresponds to the supplied project.
 * Do not check for other registered types.
 * @return IJavaMOFNatureRuntime
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static IJavaMOFNature primGetRuntime(IProject project) {
	try {
		return (IJavaMOFNature) project.getNature(NATURE_ID);
	} catch (CoreException e) {
		return null;
	}
}
/**
 * Return whether or not the project has a runtime created on it.
 * Do not check for registered nature ids.
 * @return boolean
 * @param project com.ibm.itp.core.api.resources.IProject
 */
public static boolean primHasRuntime(IProject project) {
	try {
		return project.hasNature(NATURE_ID);
	} catch (CoreException e) {
		return false;
	}
}
}
