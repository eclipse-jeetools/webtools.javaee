package org.eclipse.jst.j2ee.internal.archive.operations;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public abstract class JavaComponentCreationDataModel extends ComponentCreationDataModel {

	/**
	 * type String
	 */
	public static final String JAVASOURCE_FOLDER = "J2EEComponentCreationDataModel.JAVASOURCE_FOLDER";
	/**
	 * type String
	 */
	public static final String MANIFEST_FOLDER = "J2EEComponentCreationDataModel.MANIFEST_FOLDER";
	
	
	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(JAVASOURCE_FOLDER);
		addValidBaseProperty(MANIFEST_FOLDER);
	}
	

	public JavaComponentCreationDataModel() {
		super();
	}

	protected EClass getComponentType() {
        return CommonarchivePackage.eINSTANCE.getModuleFile();
    }

	protected Integer getDefaultComponentVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EECreationDataModel#getModuleID()
     */
    protected String getComponentID() {
        return IModuleConstants.JST_UTILITY_MODULE;
    }

	public WTPOperation getDefaultOperation() {
        return new JavaUtilityComponentCreationOperation(this);
    }

	protected String getComponentExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel#getValidComponentVersionDescriptors()
	 */
	protected WTPPropertyDescriptor[] getValidComponentVersionDescriptors() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel#getVersion()
	 */
	protected String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel#getProperties()
	 */
	protected List getProperties() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getJavaSourceFolder() {
		return getStringProperty(JAVASOURCE_FOLDER);
	}
	public String getManifestFolder() {
		return getStringProperty(MANIFEST_FOLDER);
	}	
	
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(JAVASOURCE_FOLDER)) {
			return  getComponentName();
		}else if (propertyName.equals(MANIFEST_FOLDER)) {
			return  getComponentName() +  "/" + J2EEConstants.META_INF; //$NON-NLS-1$
		}	
		return super.getDefaultProperty(propertyName);
	}	
}
