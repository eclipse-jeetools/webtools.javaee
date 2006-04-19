package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;

public interface IXDocletRuntimeAnnotation {

	public final static String entity = "entity";

	public final static String session = "session";

	public final static String mdb = "mdb";

	String getTypeAnnotations(IProject project, IEnterpriseBean beanModel);
	public abstract void setPreferenceStore(XDocletPreferenceStore preferenceStore);
	public abstract XDocletPreferenceStore getPreferenceStore();

}
