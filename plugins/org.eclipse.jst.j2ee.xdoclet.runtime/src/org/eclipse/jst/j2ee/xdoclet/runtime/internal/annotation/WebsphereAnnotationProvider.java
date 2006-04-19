package org.eclipse.jst.j2ee.xdoclet.runtime.internal.annotation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IContainerManagedEntityBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.xdoclet.runtime.internal.XDocletRuntimeAnnotationImpl;

public class WebsphereAnnotationProvider extends XDocletRuntimeAnnotationImpl {

	public String getTypeAnnotations(IProject project, IEnterpriseBean bean) {
		if (!IContainerManagedEntityBean.class.isAssignableFrom(bean.getClass()))
			return "";
		IContainerManagedEntityBean cmp = (IContainerManagedEntityBean) bean;
		XDocletPreferenceStore ps = getPreferenceStore();
		StringBuffer annotation = new StringBuffer();
		annotation.append(" * @websphere.cmp ");
		annotation.append("datasource=\"" + ps.getProperty("ejbdoclet.websphere.datasource") + "\" ");
		return annotation.toString();
	}

}
