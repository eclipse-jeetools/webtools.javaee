/*
 * Created on Aug 6, 2004
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateFilterTemplateModel {

	IDataModel dataModel = null;
	public static final String INIT = "init"; //$NON-NLS-1$
	public static final String TO_STRING = "toString"; //$NON-NLS-1$
	public static final String DO_FILTER = "doFilter"; //$NON-NLS-1$
	public static final String DESTROY = "destroy"; //$NON-NLS-1$

	public static final int NAME = 0;
	public static final int VALUE = 1;
	public static final int DESCRIPTION = 2;

	/**
	 * Constructor
	 */
	public CreateFilterTemplateModel(IDataModel dataModel) {
		super();
		this.dataModel = dataModel;
	}

	public String getFilterClassName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public String getJavaPackageName() {
		return getProperty(INewJavaClassDataModelProperties.JAVA_PACKAGE).trim();
	}

	public String getQualifiedJavaClassName() {
		return getJavaPackageName() + "." + getFilterClassName(); //$NON-NLS-1$
	}

	public String getSuperclassName() {
		return getProperty(INewJavaClassDataModelProperties.SUPERCLASS).trim();
	}

	public String getFilterName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public boolean isPublic() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_PUBLIC);
	}

	public boolean isFinal() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_FINAL);
	}

	public boolean isAbstract() {
		return dataModel.getBooleanProperty(INewJavaClassDataModelProperties.MODIFIER_ABSTRACT);
	}

	protected String getProperty(String propertyName) {
		return dataModel.getStringProperty(propertyName);
	}

	public boolean shouldGenInit() {
		return implementImplementedMethod(INIT);
	}

	public boolean shouldGenToString() {
		return implementImplementedMethod(TO_STRING);
	}

	public boolean shouldGenDoFilter() {
		return implementImplementedMethod(DO_FILTER);
	}

	public boolean shouldGenDestroy() {
		return implementImplementedMethod(DESTROY);
	}

	public List getInitParams() {
		return (List) dataModel.getProperty(INewFilterClassDataModelProperties.INIT_PARAM);
	}

	public String getInitParam(int index, int type) {
		List params = getInitParams();
		if (index < params.size()) {
			String[] stringArray = (String[]) params.get(index);
			return stringArray[type];
		}
		return null;
	}

	public List getFilterMappings() {
		return (List) dataModel.getProperty(INewFilterClassDataModelProperties.URL_MAPPINGS);
	}

	public String getFilterMapping(int index) {
		List mappings = getFilterMappings();
		if (index < mappings.size()) {
			String[] map = (String[]) mappings.get(index);
			return map[0];
		}
		return null;
	}

	public String getFilterDescription() {
		return dataModel.getStringProperty(INewFilterClassDataModelProperties.DESCRIPTION);
	}

	public List getInterfaces() {
		return (List) this.dataModel.getProperty(INewJavaClassDataModelProperties.INTERFACES);
	}

	protected boolean implementImplementedMethod(String methodName) {
		if (dataModel.getBooleanProperty(INewJavaClassDataModelProperties.ABSTRACT_METHODS)) {
			if (methodName.equals(INIT))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.INIT);
			else if (methodName.equals(TO_STRING))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.TO_STRING);
			else if (methodName.equals(DO_FILTER))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.DO_FILTER);
			else if (methodName.equals(DESTROY))
				return dataModel.getBooleanProperty(INewFilterClassDataModelProperties.DESTROY);
		}		
		return false;
	}

}
