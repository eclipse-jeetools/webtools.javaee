package org.eclipse.jst.javaee.internal.adapter;

public class EjbArtifactAdapterFactory extends JavaEEArtifactAdapterFactory {

	@Override
	protected String getFullyQualifiedClassName(Object adaptableObject) {
		if (adaptableObject instanceof org.eclipse.jst.javaee.ejb.SessionBean) {
			org.eclipse.jst.javaee.ejb.SessionBean bean = (org.eclipse.jst.javaee.ejb.SessionBean) adaptableObject;
			return bean.getEjbClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.ejb.MessageDrivenBean) {
			org.eclipse.jst.javaee.ejb.MessageDrivenBean bean = (org.eclipse.jst.javaee.ejb.MessageDrivenBean) adaptableObject;
			return bean.getEjbClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.ejb.EntityBean) {
			org.eclipse.jst.javaee.ejb.EntityBean bean = (org.eclipse.jst.javaee.ejb.EntityBean) adaptableObject;
			return bean.getEjbClass();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.ejb.EnterpriseBean) {
			org.eclipse.jst.j2ee.ejb.EnterpriseBean bean = (org.eclipse.jst.j2ee.ejb.EnterpriseBean) adaptableObject;
			return bean.getEjbClassName();
		}
		return null;
	}

}
