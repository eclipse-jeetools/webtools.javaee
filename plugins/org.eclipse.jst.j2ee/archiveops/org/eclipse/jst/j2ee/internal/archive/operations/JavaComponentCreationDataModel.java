package org.eclipse.jst.j2ee.internal.archive.operations;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.application.internal.operations.JavaUtilityComponentCreationOperation;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.modulecore.internal.operation.ComponentCreationDataModel;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class JavaComponentCreationDataModel extends ComponentCreationDataModel {

	/**
	 * type String
	 */
	public static final String JAVASOURCE_FOLDER = "J2EEComponentCreationDataModel.JAVASOURCE_FOLDER";
	/**
	 * type String
	 */
	public static final String MANIFEST_FOLDER = "J2EEComponentCreationDataModel.MANIFEST_FOLDER";

	public JavaComponentCreationDataModel() {
		super();
		// TODO Auto-generated constructor stub
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

}
