package org.eclipse.jst.j2ee.xdoclet.runtime.internal.annotation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IContainerManagedEntityBean;
import org.eclipse.jst.j2ee.ejb.annotation.internal.model.IEnterpriseBean;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.xdoclet.runtime.internal.XDocletRuntimeAnnotationImpl;

public class JBossAnnotationProvider extends XDocletRuntimeAnnotationImpl {

	public String getTypeAnnotations(IProject project, IEnterpriseBean bean) {
		if (!IContainerManagedEntityBean.class.isAssignableFrom(bean.getClass()))
			return "";
		IContainerManagedEntityBean cmp = (IContainerManagedEntityBean) bean;
		XDocletPreferenceStore ps = getPreferenceStore();
		StringBuffer annotation = new StringBuffer();
		annotation.append(" * @jboss.persistence ");
		annotation.append("datasource=\"" + ps.getProperty("ejbdoclet.jboss.datasource") + "\" ");
		annotation.append("datasource-mapping=\"" + ps.getProperty("ejbdoclet.jboss.datasourcemapping") + "\" ");
		annotation.append("table-name=\"" + cmp.getTable() + "\" ");
		annotation.append("create-table=\"" + ps.getBooleanProperty("ejbdoclet.jboss.createtable") + "\" ");
		annotation.append("remove-table=\"" + ps.getBooleanProperty("ejbdoclet.jboss.removetable") + "\" ");
		annotation.append("alter-table=\"" + ps.getBooleanProperty("ejbdoclet.jboss.altertable") + "\"" );
		return annotation.toString();
	}

}
