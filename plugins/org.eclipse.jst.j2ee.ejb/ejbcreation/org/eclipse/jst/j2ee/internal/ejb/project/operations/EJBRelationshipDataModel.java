/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaConventions;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclispe.wst.common.framework.plugin.WTPCommonPlugin;

public class EJBRelationshipDataModel extends EditModelOperationDataModel {
	private final String EMPTY_STRING = ""; //$NON-NLS-1$
	private static final String multiplictyMany = "0..*"; //$NON-NLS-1$

	private static final String multiplictyNEWONE = "0..1"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String RELATIONSHIP_NAME = "EJBRelationshipDataModel.RELATIONSHIP_NAME"; //$NON-NLS-1$

	/**
	 * optional, type String
	 */

	public static final String RELATIONSHIP = "EJBRelationshipDataModel.RELATIONSHIP"; //$NON-NLS-1$

	/**
	 * optional, type String
	 */

	public static final String EJB_ROLE_A = "EJBRelationshipDataModel.EJB_ROLE_A"; //$NON-NLS-1$

	/**
	 * optional, type String
	 */

	public static final String EJB_ROLE_B = "EJBRelationshipDataModel.EJB_ROLE_B"; //$NON-NLS-1$

	/**
	 * optional, type String
	 */
	public static final String DESCRIPTION = "EJBRelationshipDataModel.DESCRIPTION"; //$NON-NLS-1$

	/**
	 * required, type EnterpriseBean
	 */
	public static final String BEAN_A = "EJBRelationshipDataModel.BEAN_A"; //$NON-NLS-1$

	/**
	 * required, type EnterpriseBean
	 */
	public static final String BEAN_B = "EJBRelationshipDataModel.BEAN_B"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_A_ROLE_NAME = "EJBRelationshipDataModel.BEAN_A_ROLE_NAME"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_B_ROLE_NAME = "EJBRelationshipDataModel.BEAN_B_ROLE_NAME"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_A_MULTIPLICITY = "EJBRelationshipDataModel.BEAN_A_MULTIPLICITY"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_B_MULTIPLICITY = "EJBRelationshipDataModel.BEAN_B_MULTIPLICITY"; //$NON-NLS-1$

	/**
	 * do not set calculated internall via mulitpicity value, type Boolean
	 */
	public static final String BEAN_A_IS_MANY = "EJBRelationshipDataModel.BEAN_A_IS_MANY"; //$NON-NLS-1$

	/**
	 * do not set calculated internall via mulitpicity value, type Boolean
	 */
	public static final String BEAN_B_IS_MANY = "EJBRelationshipDataModel.BEAN_B_IS_MANY"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */
	public static final String BEAN_A_NAVIGABILITY = "EJBRelationshipDataModel.BEAN_A_NAVIGABILITY"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */
	public static final String BEAN_B_NAVIGABILITY = "EJBRelationshipDataModel.BEAN_B_NAVIGABILITY"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_A_CMR_NAME = "EJBRelationshipDataModel.BEAN_A_CMR_NAME"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_B_CMR_NAME = "EJBRelationshipDataModel.BEAN_B_CMR_NAME"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_A_CMR_TYPE = "EJBRelationshipDataModel.BEAN_A_CMR_TYPE"; //$NON-NLS-1$

	/**
	 * required, type String
	 */
	public static final String BEAN_B_CMR_TYPE = "EJBRelationshipDataModel.BEAN_B_CMR_TYPE"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */
	public static final String BEAN_A_CASCADE_DELETE = "EJBRelationshipDataModel.BEAN_A_CASCADE_DELETE"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */
	public static final String BEAN_B_CASCADE_DELETE = "EJBRelationshipDataModel.BEAN_B_CASCADE_DELETE"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */
	public static final String BEAN_A_FOREIGN_KEY = "EJBRelationshipDataModel.BEAN_A_FOREIGN_KEY"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */
	public static final String BEAN_END_COMPLETED = "EJBRelationshipDataModel.BBEAN_END_COMPLETED"; //$NON-NLS-1$

	/**
	 * required, type Boolean
	 */

	public static final String IS_EDITING = "EJBRelationshipDataModel.IS_EDITING"; //$NON-NLS-1$

	public static final String EJB_JAR = "EJBRelationshipDataModel.EJB_JAR"; //$NON-NLS-1$

	public static final String IS_FIRST_TIME = "EJBRelationshipDataModel.IS_FIRST_TIME"; //$NON-NLS-1$

	public static final String EDITING_DOMAIN = "EJBRelationshipDataModel.EDITING_DOMAIN"; //$NON-NLS-1$

	public static final String BEAN_B_FOREIGN_KEY = "EJBRelationshipDataModel.BEAN_B_FOREIGN_KEY"; //$NON-NLS-1$

	protected void init() {
		setProperty(EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(RELATIONSHIP_NAME);
		addValidBaseProperty(DESCRIPTION);
		addValidBaseProperty(BEAN_A);
		addValidBaseProperty(BEAN_B);
		addValidBaseProperty(BEAN_A_IS_MANY);
		addValidBaseProperty(BEAN_B_IS_MANY);
		addValidBaseProperty(BEAN_A_ROLE_NAME);
		addValidBaseProperty(BEAN_B_ROLE_NAME);
		addValidBaseProperty(BEAN_A_MULTIPLICITY);
		addValidBaseProperty(BEAN_B_MULTIPLICITY);
		addValidBaseProperty(BEAN_A_NAVIGABILITY);
		addValidBaseProperty(BEAN_B_NAVIGABILITY);
		addValidBaseProperty(BEAN_A_CMR_NAME);
		addValidBaseProperty(BEAN_B_CMR_NAME);
		addValidBaseProperty(BEAN_A_CMR_TYPE);
		addValidBaseProperty(BEAN_B_CMR_TYPE);
		addValidBaseProperty(BEAN_A_CASCADE_DELETE);
		addValidBaseProperty(BEAN_B_CASCADE_DELETE);
		addValidBaseProperty(BEAN_A_FOREIGN_KEY);
		addValidBaseProperty(BEAN_B_FOREIGN_KEY);
		addValidBaseProperty(IS_EDITING);
		addValidBaseProperty(EJB_JAR);
		addValidBaseProperty(RELATIONSHIP);
		addValidBaseProperty(IS_FIRST_TIME);
		addValidBaseProperty(EDITING_DOMAIN);
		addValidBaseProperty(EJB_ROLE_A);
		addValidBaseProperty(EJB_ROLE_B);
		addValidBaseProperty(BEAN_END_COMPLETED);
		super.initValidBaseProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new EJBRelationshipCreationOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultProperty(java.lang.String)
	 */
	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(BEAN_END_COMPLETED))
			return Boolean.FALSE;
		if (propertyName.equals(BEAN_A_MULTIPLICITY) || propertyName.equals(BEAN_B_MULTIPLICITY))
			return multiplictyNEWONE;
		if (propertyName.equals(BEAN_A_IS_MANY)) {
			if (getStringProperty(BEAN_A_MULTIPLICITY).equals(multiplictyMany))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		if (propertyName.equals(BEAN_B_IS_MANY)) {
			if (getStringProperty(BEAN_B_MULTIPLICITY).equals(multiplictyMany))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		if (propertyName.equals(BEAN_A_NAVIGABILITY) || propertyName.equals(BEAN_B_NAVIGABILITY))
			return Boolean.TRUE;
		if (propertyName.equals(BEAN_A_CASCADE_DELETE) || propertyName.equals(BEAN_B_CASCADE_DELETE))
			return Boolean.FALSE;
		if (propertyName.equals(BEAN_B_FOREIGN_KEY)) {
			if (!getBooleanProperty(IS_EDITING))
				return Boolean.TRUE;
			if (getBooleanProperty(BEAN_A_IS_MANY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		if (propertyName.equals(BEAN_A_FOREIGN_KEY)) {
			if (getBooleanProperty(BEAN_B_IS_MANY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		if (propertyName.equals(BEAN_A_CMR_NAME)) {
			if (!getBooleanProperty(BEAN_A_NAVIGABILITY))
				return ""; //$NON-NLS-1$
			return getStringProperty(BEAN_A_ROLE_NAME);
		} else if (propertyName.equals(BEAN_B_CMR_NAME)) {
			if (!getBooleanProperty(BEAN_B_NAVIGABILITY))
				return ""; //$NON-NLS-1$
			return getStringProperty(BEAN_B_ROLE_NAME);
		} else if (BEAN_A_CMR_TYPE.equals(propertyName)) {
			if (getBooleanProperty(BEAN_A_IS_MANY) && getBooleanProperty(BEAN_A_NAVIGABILITY))
				return "java.util.Collection"; //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		} else if (BEAN_B_CMR_TYPE.equals(propertyName)) {
			if (getBooleanProperty(BEAN_B_IS_MANY) && getBooleanProperty(BEAN_B_NAVIGABILITY))
				return "java.util.Collection"; //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
		if (propertyName.equals(RELATIONSHIP_NAME))
			return getDefaultRelationshipName();
		if (propertyName.equals(BEAN_A_ROLE_NAME)) {
			return getRoleName_CMRName((EnterpriseBean) getProperty(BEAN_A));
		}
		if (propertyName.equals(BEAN_B_ROLE_NAME)) {
			return getRoleName_CMRName((EnterpriseBean) getProperty(BEAN_B));
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doSetProperty(java.lang.String,
	 *      java.lang.Object)
	 */
	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean status = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(BEAN_A)) {
			setUpBeanBProperties(propertyValue);
			//setDefaultRelationshipName();
		} else if (propertyName.equals(BEAN_B)) {
			setUpBeanAProperties(propertyValue);
			//setDefaultRelationshipName();
		} else if (propertyName.equals(BEAN_A_MULTIPLICITY)) {
			if (getStringProperty(BEAN_A_MULTIPLICITY).equals(multiplictyMany))
				setProperty(BEAN_A_IS_MANY, Boolean.TRUE);
			else
				setProperty(BEAN_A_IS_MANY, Boolean.FALSE);
			notifyDefaultChange(BEAN_A_IS_MANY);
			notifyEnablementChange(BEAN_A_CMR_TYPE);
			notifyDefaultChange(BEAN_A_CMR_TYPE);
			setBooleanProperty(BEAN_A_FOREIGN_KEY, getBooleanProperty(BEAN_B_IS_MANY));
			setBooleanProperty(BEAN_B_FOREIGN_KEY, (getBooleanProperty(BEAN_A_IS_MANY)));
			notifyDefaultChange(BEAN_A_FOREIGN_KEY);
			notifyDefaultChange(BEAN_B_FOREIGN_KEY);
		} else if (propertyName.equals(BEAN_B_MULTIPLICITY)) {
			if (getStringProperty(BEAN_B_MULTIPLICITY).equals(multiplictyMany))
				setProperty(BEAN_B_IS_MANY, Boolean.TRUE);
			else
				setProperty(BEAN_B_IS_MANY, Boolean.FALSE);
			notifyDefaultChange(BEAN_B_IS_MANY);
			notifyEnablementChange(BEAN_B_CMR_TYPE);
			notifyDefaultChange(BEAN_B_CMR_TYPE);
			setBooleanProperty(BEAN_A_FOREIGN_KEY, getBooleanProperty(BEAN_B_IS_MANY));
			setBooleanProperty(BEAN_B_FOREIGN_KEY, (getBooleanProperty(BEAN_A_IS_MANY)));
			notifyDefaultChange(BEAN_B_FOREIGN_KEY);
			notifyDefaultChange(BEAN_A_FOREIGN_KEY);
		} else if (propertyName.equals(BEAN_A_NAVIGABILITY)) {
			notifyEnablementChange(BEAN_A_CASCADE_DELETE);
			notifyDefaultChange(BEAN_A_CMR_NAME);
			notifyEnablementChange(BEAN_A_CMR_NAME);
			notifyDefaultChange(BEAN_A_CMR_TYPE);
			notifyEnablementChange(BEAN_A_CMR_TYPE);
			if (!getBooleanProperty(BEAN_A_NAVIGABILITY))
				setProperty(BEAN_A_CMR_NAME, EMPTY_STRING);
			else
				setProperty(BEAN_A_CMR_NAME, getProperty(BEAN_A_ROLE_NAME));
		} else if (propertyName.equals(BEAN_B_NAVIGABILITY)) {
			notifyEnablementChange(BEAN_B_CASCADE_DELETE);
			notifyDefaultChange(BEAN_B_CMR_NAME);
			notifyEnablementChange(BEAN_B_CMR_NAME);
			notifyDefaultChange(BEAN_B_CMR_TYPE);
			notifyEnablementChange(BEAN_B_CMR_TYPE);
			if (!getBooleanProperty(BEAN_B_NAVIGABILITY))
				setProperty(BEAN_B_CMR_NAME, EMPTY_STRING);
			else
				setProperty(BEAN_B_CMR_NAME, getProperty(BEAN_B_ROLE_NAME));
		}
		if (propertyName.equals(BEAN_A_MULTIPLICITY) || propertyName.equals(BEAN_B_MULTIPLICITY)) {
			notifyDefaultChange(BEAN_A_FOREIGN_KEY);
			notifyDefaultChange(BEAN_B_FOREIGN_KEY);
			notifyEnablementChange(BEAN_A_FOREIGN_KEY);
			notifyEnablementChange(BEAN_B_FOREIGN_KEY);
			notifyDefaultChange(BEAN_A_CASCADE_DELETE);
			notifyDefaultChange(BEAN_B_CASCADE_DELETE);
			notifyEnablementChange(BEAN_A_CASCADE_DELETE);
			notifyEnablementChange(BEAN_B_CASCADE_DELETE);
		}
		if (propertyName.equals(BEAN_A_ROLE_NAME) && getBooleanProperty(BEAN_A_NAVIGABILITY))
			setProperty(BEAN_A_CMR_NAME, getProperty(BEAN_A_ROLE_NAME));
		if (propertyName.equals(BEAN_B_ROLE_NAME) && getBooleanProperty(BEAN_B_NAVIGABILITY))
			setProperty(BEAN_B_CMR_NAME, getProperty(BEAN_B_ROLE_NAME));
		return status;
	}

	protected Boolean basicIsEnabled(String propertyName) {
		if (BEAN_A_CMR_TYPE.equals(propertyName)) {
			if (getBooleanProperty(BEAN_A_IS_MANY) && getBooleanProperty(BEAN_A_NAVIGABILITY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		} else if (BEAN_B_CMR_TYPE.equals(propertyName)) {
			if (getBooleanProperty(BEAN_B_IS_MANY) && getBooleanProperty(BEAN_B_NAVIGABILITY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		} else if (BEAN_A_FOREIGN_KEY.equals(propertyName) || BEAN_B_FOREIGN_KEY.equals(propertyName)) {
			if (getBooleanProperty(BEAN_A_IS_MANY) || getBooleanProperty(BEAN_B_IS_MANY))
				return Boolean.FALSE;
			return Boolean.TRUE;
		} else if (BEAN_A_CASCADE_DELETE.equals(propertyName)) {
			if (!getBooleanProperty(BEAN_B_IS_MANY) && getBooleanProperty(BEAN_A_NAVIGABILITY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		} else if (BEAN_B_CASCADE_DELETE.equals(propertyName)) {
			if (!getBooleanProperty(BEAN_A_IS_MANY) && getBooleanProperty(BEAN_B_NAVIGABILITY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		} else if (BEAN_A_CMR_NAME.equals(propertyName)) {
			if (getBooleanProperty(BEAN_A_NAVIGABILITY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		} else if (BEAN_B_CMR_NAME.equals(propertyName)) {
			if (getBooleanProperty(BEAN_B_NAVIGABILITY))
				return Boolean.TRUE;
			return Boolean.FALSE;
		}
		return super.basicIsEnabled(propertyName);
	}

	/**
	 *  
	 */
	private String getDefaultRelationshipName() {
		String beanAName = getBeanName(BEAN_A);
		String beanBName = getBeanName(BEAN_B);
		String dash = "-"; //$NON-NLS-1$
		if (beanAName == null && beanBName == null)
			return "A Relationship Name"; //$NON-NLS-1$
		if (beanAName == null || beanBName == null)
			dash = EMPTY_STRING;
		if (beanAName == null)
			beanAName = EMPTY_STRING;
		if (beanBName == null)
			beanBName = EMPTY_STRING;
		return beanAName + dash + beanBName;
		//setProperty(RELATIONSHIP_NAME, beanAName == null?"" + "-" + beanBName); //$NON-NLS-1$
	}

	private void setUpBeanAProperties(Object propertyValue) {
		ContainerManagedEntity cmp = (ContainerManagedEntity) propertyValue;
		setProperty(EJBRelationshipDataModel.BEAN_B, cmp);


		//		if (beanBName != null && !getBooleanProperty(EJBRelationshipDataModel.IS_EDITING)) {
		//			setProperty(BEAN_B_ROLE_NAME, beanBName.toLowerCase());
		//			if (getBooleanProperty(BEAN_B_NAVIGABILITY))
		//				setProperty(BEAN_B_CMR_NAME, beanBName.toLowerCase());
		//		}
	}

	private String getRoleName_CMRName(EnterpriseBean bean) {
		return bean != null ? bean.getName().toLowerCase() : EMPTY_STRING;
	}

	/**
	 * @param propertyValue
	 */
	private void setUpBeanBProperties(Object propertyValue) {
		ContainerManagedEntity cmp = (ContainerManagedEntity) propertyValue;
		setProperty(EJBRelationshipDataModel.BEAN_A, cmp);

		//		if (beanAName != null && !getBooleanProperty(EJBRelationshipDataModel.IS_EDITING)) {
		//			setProperty(BEAN_A_ROLE_NAME, beanAName.toLowerCase());
		//			if (getBooleanProperty(BEAN_A_NAVIGABILITY))
		//				setProperty(BEAN_A_CMR_NAME, beanAName.toLowerCase());
		//		}
	}

	/**
	 * @param propertyValue
	 */

	private String getBeanName(String beanPropertyName) {
		EnterpriseBean bean = (EnterpriseBean) getProperty(beanPropertyName);
		if (bean != null)
			return bean.getName();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#doValidateProperty(java.lang.String)
	 */
	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = super.doValidateProperty(propertyName);
		if (propertyName.equals(BEAN_A)) {
			EnterpriseBean bean = (EnterpriseBean) getProperty(BEAN_A);
			if (bean == null) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Bean_empty_")); //$NON-NLS-1$
			} else if (!(bean instanceof ContainerManagedEntity)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Selected_bean_a_must_be_a_cmp_")); //$NON-NLS-1$
			} else if (bean.getVersionID() < J2EEVersionConstants.EJB_2_0_ID) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Selected_bean_a_must_be_2_x_cmp_")); //$NON-NLS-1$
			}
		} else if (propertyName.equals(BEAN_B)) {
			EnterpriseBean bean = (EnterpriseBean) getProperty(BEAN_B);
			if (bean == null) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Bean_empty_")); //$NON-NLS-1$
			} else if (!(bean instanceof ContainerManagedEntity)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Selected_bean_b_must_be_a_cmp_")); //$NON-NLS-1$
			} else if (bean.getVersionID() < J2EEVersionConstants.EJB_2_0_ID) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Selected_bean_b_must_be_2_x_cmp_")); //$NON-NLS-1$
			}
		} else if (propertyName.equals(BEAN_A_ROLE_NAME)) {
			String roleName = getStringProperty(BEAN_A_ROLE_NAME);
			if (roleName == null || roleName.equals(EMPTY_STRING)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Role_name_for_Bean_A_cannot_be_empty_UI_")); //$NON-NLS-1$
			} else if (!checkTextIsValidJavaName(roleName)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Role_name_for_Bean_A_must_be_java_valid_field_UI_")); //$NON-NLS-1$
			} else if (checkRoleUniq(roleName, BEAN_A_ROLE_NAME)) {
				if (getBooleanProperty(BEAN_A_NAVIGABILITY))
					return WTPCommonPlugin.createErrorStatus(roleName + " " + EJBCreationResourceHandler.getString("is_not_an_unique_role_name_")); //$NON-NLS-1$//$NON-NLS-2$
			} else if (roleName.equals(getStringProperty(BEAN_B_ROLE_NAME))) {
				if (getBooleanProperty(BEAN_A_NAVIGABILITY) && getBooleanProperty(BEAN_B_NAVIGABILITY))
					return WTPCommonPlugin.createErrorStatus(roleName + EJBCreationResourceHandler.getString("Role_name_cannot_be_the_same_UI_")); //$NON-NLS-1$
			}
		} else if (propertyName.equals(BEAN_B_ROLE_NAME)) {
			String roleName = getStringProperty(BEAN_B_ROLE_NAME);
			if (roleName == null || roleName.equals(EMPTY_STRING)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Role_name_for_Bean_B_cannot_be_empty_UI_")); //$NON-NLS-1$
			} else if (!checkTextIsValidJavaName(roleName)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Role_name_for_Bean_B_must_be_java_valid_field_UI_")); //$NON-NLS-1$
			} else if (checkRoleUniq(roleName, BEAN_B_ROLE_NAME)) {
				if (getBooleanProperty(BEAN_B_NAVIGABILITY))
					return WTPCommonPlugin.createErrorStatus(roleName + " " + EJBCreationResourceHandler.getString("is_not_an_unique_role_name_")); //$NON-NLS-1$ //$NON-NLS-2$
			} else if (roleName.equals(getStringProperty(BEAN_A_ROLE_NAME))) {
				if (getBooleanProperty(BEAN_A_NAVIGABILITY) && getBooleanProperty(BEAN_B_NAVIGABILITY))
					return WTPCommonPlugin.createErrorStatus(roleName + EJBCreationResourceHandler.getString("Role_name_cannot_be_the_same_UI_")); //$NON-NLS-1$
			}
		} else if (propertyName.equals(BEAN_A_MULTIPLICITY)) {
			String multi = getStringProperty(BEAN_A_MULTIPLICITY);
			if (multi == null || multi.equals(EMPTY_STRING)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Multiplicity_for_Bean_A_cannot_be_empty_UI_")); //$NON-NLS-1$
			}
		} else if (propertyName.equals(BEAN_B_MULTIPLICITY)) {
			String multi = getStringProperty(BEAN_B_MULTIPLICITY);
			if (multi == null || multi.equals(EMPTY_STRING)) {
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("Multiplicity_for_Bean_B_cannot_be_empty_UI_")); //$NON-NLS-1$
			}
		} else if (propertyName.equals(BEAN_A_CMR_TYPE)) {
			if (isEnabled(BEAN_A_CMR_TYPE).booleanValue()) {
				String cmrType = getStringProperty(BEAN_A_CMR_TYPE);
				if (cmrType == null || cmrType.equals(EMPTY_STRING)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CMR_field_type_for_Bean_A_cannot_be_empty_UI_")); //$NON-NLS-1$
				}
			}
		} else if (propertyName.equals(BEAN_A_FOREIGN_KEY) || propertyName.equals(BEAN_B_FOREIGN_KEY)) {
			if (!getBooleanProperty(BEAN_A_FOREIGN_KEY) && !getBooleanProperty(BEAN_B_FOREIGN_KEY) && !getBooleanProperty(BEAN_A_IS_MANY) && !getBooleanProperty(BEAN_B_IS_MANY))
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("ERR_PLEASE_SELECT_A_FOREIGN_KEY")); //$NON-NLS-1$
			else if (getBooleanProperty(BEAN_A_FOREIGN_KEY) && getBooleanProperty(BEAN_B_FOREIGN_KEY) && !getBooleanProperty(BEAN_A_IS_MANY) && !getBooleanProperty(BEAN_B_IS_MANY))
				return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("ERR_FOREIGN_KEY_SELECTED")); //$NON-NLS-1$

		}

		else if (propertyName.equals(BEAN_B_CMR_TYPE)) {
			if (isEnabled(BEAN_B_CMR_TYPE).booleanValue()) {
				String cmrType = getStringProperty(BEAN_B_CMR_TYPE);
				if (cmrType == null || cmrType.equals(EMPTY_STRING)) {
					return WTPCommonPlugin.createErrorStatus(EJBCreationResourceHandler.getString("CMR_field_type_for_Bean_B_cannot_be_empty_UI_")); //$NON-NLS-1$
				}
			}

		} else if (propertyName.equals(BEAN_A_NAVIGABILITY) || propertyName.equals(BEAN_B_NAVIGABILITY)) {
			if (((!getBooleanProperty(BEAN_A_NAVIGABILITY) && !getBooleanProperty(BEAN_B_NAVIGABILITY))))
				return WTPCommonPlugin.createErrorStatus("One end must be navigable, please change navigablity."); //$NON-NLS-1$
		}

		return status;
	}

	/**
	 * Check the input name whether is valid java field name.
	 * 
	 * @param String
	 *            text
	 * @return boolean
	 */
	private boolean checkTextIsValidJavaName(String text) {
		if (text == null)
			return false;
		else if (text.length() == 0)
			return false;
		else
			return JavaConventions.validateFieldName(text).isOK();
	}

	private boolean checkRoleUniq(String name, String property) {

		if (getBooleanProperty(IS_EDITING)) {
			if (property.equals(BEAN_A_ROLE_NAME)) {
				CommonRelationshipRole role = (CommonRelationshipRole) getProperty(EJB_ROLE_A);
				if (name != null && name.equals(role.getName())) {
					return false;
				}
				return checkIfExistingRole(name, role, property);
			} else if (property.equals(BEAN_B_ROLE_NAME)) {
				CommonRelationshipRole role = (CommonRelationshipRole) getProperty(EJB_ROLE_B);
				if (name != null && name.equals(role.getName())) {
					return false;
				}
				return checkIfExistingRole(name, role, property);
			}
		} else
			return checkIfExistingRole(name, null, property);

		return false;
	}

	/**
	 * @param name
	 * @param role
	 * @param property
	 */
	private boolean checkIfExistingRole(String name, CommonRelationshipRole role, String property) {
		EnterpriseBean right = (EnterpriseBean) getProperty(BEAN_A);
		EnterpriseBean left = (EnterpriseBean) getProperty(BEAN_B);
		ContainerManagedEntity cmpRight = (ContainerManagedEntity) right;
		ContainerManagedEntity cmpLeft = (ContainerManagedEntity) left;

		if (cmpRight == null || cmpLeft == null)
			return false;
		CommonRelationshipRole ejbRelRole = null;
		try {
			ejbRelRole = cmpRight.getRole(name);
		} catch (Exception e) {
			return false;
		};
		if (ejbRelRole != null) {
			if (role != null && ejbRelRole == role)
				return false;
			return true;
		}

		try {
			ejbRelRole = cmpLeft.getRole(name);
		} catch (Exception e) {
			return false;
		}

		if (ejbRelRole != null) {
			if (role != null && ejbRelRole == role)
				return false;
			return true;
		}

		return false;

	}

	public boolean shouldUpdateRoles() {
		return getProperty(EJB_ROLE_A) != null && getProperty(EJB_ROLE_B) != null;
	}

	public void setUpDataModelForEdit(EJBRelation ejbRelationship) {
		setBooleanProperty(IS_EDITING, true);
		EJBRelationshipRole roleA = (EJBRelationshipRole) ejbRelationship.getFirstCommonRole();
		EJBRelationshipRole roleB = (EJBRelationshipRole) ejbRelationship.getSecondCommonRole();
		setProperty(RELATIONSHIP, ejbRelationship);
		setProperty(RELATIONSHIP_NAME, ejbRelationship.getName());
		setProperty(DESCRIPTION, EJBRelationshipDataModel.DESCRIPTION);
		setProperty(EJB_ROLE_B, roleB);
		setProperty(EJB_ROLE_A, roleA);
		setBooleanProperty(BEAN_B_NAVIGABILITY, roleB.isNavigable());
		setBooleanProperty(BEAN_A_NAVIGABILITY, roleA.isNavigable());
		setBooleanProperty(BEAN_A_CASCADE_DELETE, roleA.isCascadeDelete());
		setBooleanProperty(BEAN_B_CASCADE_DELETE, roleB.isCascadeDelete());
		if (roleB.getCmrField() != null && roleB.getCmrField().getType() != null && roleB.getCmrField().getType().getName() != null)
			setProperty(BEAN_B_CMR_NAME, roleB.getCmrField().getName());
		if (roleA.getCmrField() != null && roleA.getCmrField().getType() != null && roleA.getCmrField().getType().getName() != null)
			setProperty(BEAN_A_CMR_NAME, roleA.getCmrField().getName());
		if (roleB.getCmrField() != null && roleB.getCmrField().getCollectionType() != null && roleB.isMany())
			setProperty(BEAN_B_CMR_TYPE, roleB.getCmrField().getCollectionType().getJavaName());
		else
			setProperty(BEAN_B_CMR_TYPE, EMPTY_STRING);
		if (roleA.getCmrField() != null && roleA.getCmrField().getCollectionType() != null && roleA.isMany())
			setProperty(BEAN_A_CMR_TYPE, roleA.getCmrField().getCollectionType().getJavaName());
		else
			setProperty(BEAN_A_CMR_TYPE, EMPTY_STRING);
		setBooleanProperty(BEAN_A_FOREIGN_KEY, roleA.isForward());
		setBooleanProperty(BEAN_B_FOREIGN_KEY, roleB.isForward());
		setBooleanProperty(BEAN_A_IS_MANY, roleA.isMany());
		setBooleanProperty(BEAN_A_IS_MANY, roleA.isMany());
		if (roleA.isMany())
			setProperty(BEAN_A_MULTIPLICITY, multiplictyMany);
		if (roleB.isMany())
			setProperty(BEAN_B_MULTIPLICITY, multiplictyMany);

		setProperty(BEAN_A, roleA.getTypeEntity());
		setProperty(BEAN_B, roleB.getTypeEntity());
		setProperty(BEAN_B_ROLE_NAME, roleB.getRoleName());
		setProperty(BEAN_A_ROLE_NAME, roleA.getRoleName());

	}
}